package jp.co.matsusaka.logic.driverlisence;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

import jp.co.matsusaka.constant.driverlisence.DriverLisenceConstants;
import jp.co.matsusaka.model.driverlisence.*;

/**
 * 免許証（スマートカード）を操作するクラス
 * @author 101031
 *
 */
public class DriverLisenceManager {
	
	// スマートカードオブジェクト
	private Card card;
	
	// スマートカードを操作する実体
	private CardChannel channel;
	
	// エラーコード
	private int errorcode;
	
	/**
	 * 暗証番号で照合を行い基本4情報を返す
	 * 暗証番号1によって本籍以外の記載事項
	 * 暗証番号2によって本籍を返す
	 * @param pw1　暗証番号1(4桁)
	 * @param pw2　暗証番号2(4桁)
	 * @return 最新の4情報
	 */
	public FetchDliverLicenseDataReturnValue fetchBasicData(String str_pw1, String str_pw2) {
		DliverLicenseData dld = new DliverLicenseData();
		byte[] pw1 = new byte[] {};
		byte[] pw2 = new byte[] {};
		errorcode = DriverLisenceConstants.ERROR_CODE_OK;
		
		try {
			// スマートカードに接続
			connect();
			
			// 暗証番号設定の取得
			int existPin = existPin();
			
			if (existPin == 1) { // 暗証番号設定がある場合引数で照合
				if (isNumberFourDigit(str_pw1)) {
					pw1 = convertPW(str_pw1);
				}
				if (isNumberFourDigit(str_pw2)) {
					pw2 = convertPW(str_pw2);
				}
				
			} else if (existPin == 0) { // 暗証番号設定が無い場合「****」で照合
				pw1 = new byte[] {0x2A, 0x2A, 0x2A, 0x2A};
				pw2 = new byte[] {0x2A, 0x2A, 0x2A, 0x2A};
				
			} else { // 暗証番号設定取得に失敗しているため終了
				throw new Exception();
			}
			
			// 暗証番号1の照合
			dld.setRemainingTimesPin1(verifyPinRemainingTimes(0x01));
			if (pw1.length == 0 || DriverLisenceConstants.SW_SUCCESS != verifyPin(0x01, pw1)) { // 暗証番号1が正しくない場合は処理不可
				errorcode = DriverLisenceConstants.ERROR_CODE_VERIFYPIN_FAILURE;
				throw new Exception();
			}
			
			// 記載事項の取得
			MF_DF1_EF01 df1ef01 = createDF1EF01();
			dld.setName(df1ef01.getTag12Name()); // 氏名
			dld.setAddress(df1ef01.getTag17Address()); // 住所
			dld.setDateOfBirth(df1ef01.getTag16DateOfBirth()); // 生年月日
			
			
			
			// 記載事項変更等（本籍除く）の取得
			MF_DF1_EF04 df1ef04 = createDF1EF04();
			
			// 氏名の上書き
			if (null != df1ef04.getTag60NewName()) dld.setName(df1ef04.getTag60NewName());
			if (null != df1ef04.getTag61NewName()) dld.setName(df1ef04.getTag61NewName());
			if (null != df1ef04.getTag62NewName()) dld.setName(df1ef04.getTag62NewName());
			if (null != df1ef04.getTag63NewName()) dld.setName(df1ef04.getTag63NewName());
			if (null != df1ef04.getTag64NewName()) dld.setName(df1ef04.getTag64NewName());
			if (null != df1ef04.getTag65NewName()) dld.setName(df1ef04.getTag65NewName());
			if (null != df1ef04.getTag66NewName()) dld.setName(df1ef04.getTag66NewName());
			if (null != df1ef04.getTag67NewName()) dld.setName(df1ef04.getTag67NewName());
			
			// 住所の上書き
			if (null != df1ef04.getTag70NewAddress()) dld.setAddress(df1ef04.getTag70NewAddress());
			if (null != df1ef04.getTag71NewAddress()) dld.setAddress(df1ef04.getTag71NewAddress());
			if (null != df1ef04.getTag72NewAddress()) dld.setAddress(df1ef04.getTag72NewAddress());
			if (null != df1ef04.getTag73NewAddress()) dld.setAddress(df1ef04.getTag73NewAddress());
			if (null != df1ef04.getTag74NewAddress()) dld.setAddress(df1ef04.getTag74NewAddress());
			if (null != df1ef04.getTag75NewAddress()) dld.setAddress(df1ef04.getTag75NewAddress());
			if (null != df1ef04.getTag76NewAddress()) dld.setAddress(df1ef04.getTag76NewAddress());
			if (null != df1ef04.getTag77NewAddress()) dld.setAddress(df1ef04.getTag77NewAddress());
			
			// 暗証番号2の照合
			dld.setRemainingTimesPin2(verifyPinRemainingTimes(0x02));
			if (pw2.length > 0 && DriverLisenceConstants.SW_SUCCESS == verifyPin(0x02, pw2)) { // 暗証番号2が正しくない場合は本籍を設定しない
				// 本籍の取得
				MF_DF1_EF02 df1ef02 = createDF1EF02();
				dld.setHonseki(df1ef02.getTag41Honseki());
				
				// 記載事項変更等（本籍）の取得
				MF_DF1_EF06 df1ef06 = createDF1EF06();
				
				// 本籍の上書き
				if (null != df1ef06.getTagABNewHonseki()) dld.setHonseki(df1ef06.getTagABNewHonseki());
				if (null != df1ef06.getTagACNewHonseki()) dld.setHonseki(df1ef06.getTagACNewHonseki());
				if (null != df1ef06.getTagADNewHonseki()) dld.setHonseki(df1ef06.getTagADNewHonseki());
				if (null != df1ef06.getTagAENewHonseki()) dld.setHonseki(df1ef06.getTagAENewHonseki());
				if (null != df1ef06.getTagAFNewHonseki()) dld.setHonseki(df1ef06.getTagAFNewHonseki());
				
				// 写真の取得
				MF_DF2_EF01 df2ef01 = createDF2EF01();
				dld.setImage(df2ef01.getTag5F40Image());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			disconnect();
		}
		
		// 戻り値の設定
		FetchDliverLicenseDataReturnValue returnvalue = new FetchDliverLicenseDataReturnValue();
		returnvalue.setDliverLicenseData(dld);
		returnvalue.setErrorCode(errorcode);
		return returnvalue;
	}
	
	/**
	 * カードリーダーに接続する
	 */
	private void connect() {
		try {
			   // 接続中のICカードリーダーを取得
			   TerminalFactory factory = TerminalFactory.getDefault();
			   List<CardTerminal> terminals = factory.terminals().list();

			   // 一つ目のICカードリーダーを取得
			   if (null != terminals && terminals.size() > 0) {
				   CardTerminal terminal = terminals.get(0);
				   
				   // ICカードリーダー上のカードに接続
				   this.card = terminal.connect("*");
				   this.channel = card.getBasicChannel();
			   }
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CONNECT_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CONNECT_UNKNOWN;
		}
	}
	
	/**
	 * 暗証番号設定がされているかどうかを返す
	 * @return result 0:設定なし 1:設定あり 0xXXXX:取得できなかった場合のエラーコード
	 */
	private int existPin() {
		int result = 0xFFF1;
		ResponseAPDU answer;
		byte r[];
		try {
			answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x00, 0x00)); // MF選択
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();
			
			answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x02, 0x0C, new byte[] {0x00, 0x0A})); // MF EF02選択
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();
			
			answer = channel.transmit(new CommandAPDU(0x00, 0xB0, 0x80, 0x00, 3)); // MF EF02データ取得
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();
			
			r = answer.getData();
			Vector<DliverLisenceTLV> list = analysisTLV(r);
			for (int i = 0; list.size() > i; i++) {
				DliverLisenceTLV tlv = list.get(i);
				if (DriverLisenceConstants.TAG_PINFLG.equals(tlv.getTlvTag())) { // 暗証番号設定有無の取得
					result = (int)tlv.getTlvValue()[0]; // 前0を削除して取得
				}
			}
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_EXISTPIN_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();;
			errorcode = DriverLisenceConstants.ERROR_CODE_EXISTPIN_UNKNOWN;
		}
		return result;
	}
	
	/**
	 * 受け取った暗証番号文字列をバイト配列（JIS X 0201）に変換して認証を行う
	 * 記載事項、外字、記載事項変更等（本籍除く）、記載事項変更（外字）、電子署名を取得するための認証
	 * 暗証番号設定が無い場合は"****（0x2A, 0x2A, 0x2A, 0x2A）"にて認証を行う
	 * @param num 0x01(Pin1) or 0x02(Pin2) pw 4バイト
	 * @return 暗証番号照合の結果（2バイト 16進数）
	 */
	private int verifyPin(int num, byte[] pw) {
		int result = 0xFFF2;
		ResponseAPDU answer;
		byte r[];
		try {
			answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x00, 0x00)); // MF選択
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();
			
			answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x02, 0x0C, new byte[] {0x00, (byte)num})); // MF IEF01選択
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();
			
			answer = channel.transmit(new CommandAPDU(0x00, 0x20, 0x00, 0x80, pw)); // PW照合
			result = answer.getSW();
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_VERIFYPIN_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();;
			errorcode = DriverLisenceConstants.ERROR_CODE_VERIFYPIN_UNKNOWN;
		}
		return result;
	}
	
	/**
	 * PW残り照合可能回数を取得する
	 * @param num 0x01(Pin1) or 0x02(Pin2)
	 * @return 残り照合回数
	 */
	private int verifyPinRemainingTimes(int num) {
		int result = 0xFFF3;
		ResponseAPDU answer;
		byte r[];
		try {
			answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x00, 0x00)); // MF選択
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();
			
			answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x02, 0x0C, new byte[] {0x00, (byte)num})); // MF IEF01選択
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();

			answer = channel.transmit(new CommandAPDU(0x00, 0x20, 0x00, 0x80)); // PW残り照合可能回数
			if (DriverLisenceConstants.SW_REMAINING_3 == answer.getSW()) {
				result = 0x03;
			} else if (DriverLisenceConstants.SW_REMAINING_2 == answer.getSW()) {
				result = 0x02;
			} else if (DriverLisenceConstants.SW_REMAINING_1 == answer.getSW()) {
				result = 0x01;
			} else {
				result = 0x00;
			}
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_VERIFYPIN_REMAININGTIMES_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_VERIFYPIN_REMAININGTIMES_UNKNOWN;
		}
		return result;
	}
	
	/**
	 * 記載事項（本籍除く）（DF1_EF01）を取得する
	 * @return 記載事項（本籍除く）オブジェクト
	 */
	private MF_DF1_EF01 createDF1EF01() {
		MF_DF1_EF01 df1ef01 = new MF_DF1_EF01();
		ResponseAPDU answer;
		byte r[];
		try {
			answer = selectDF1();
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();

			answer = channel.transmit(new CommandAPDU(0x00, 0xB0, 0x81, 0x00, 0x000370)); // 記載事項取得
			r = answer.getData();
			
			Vector<DliverLisenceTLV> list = analysisTLV(r);
			for (int i = 0; list.size() > i; i++) {
				DliverLisenceTLV tlv = list.get(i);
				if (DriverLisenceConstants.TAG_ENACTMENTYEARNO.equals(tlv.getTlvTag())) { // JIS X 0208 制定年番号
					df1ef01.setTag11EnactmentYearNo(tlv.getTlvValue().toString());
				} else if (DriverLisenceConstants.TAG_NAME.equals(tlv.getTlvTag())) { // 氏名	
				 	df1ef01.setTag12Name(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NAMEKANA.equals(tlv.getTlvTag())) { // 呼び名(カナ)	
				 	df1ef01.setTag13NameKana(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_TUUSHOU.equals(tlv.getTlvTag())) { // 通称名	
				 	df1ef01.setTag14Tuushou(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_TOUITSUNAME.equals(tlv.getTlvTag())) { // 統一氏名(カナ)	
				 	df1ef01.setTag15TouitsuName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_DATEOFBIRTH.equals(tlv.getTlvTag())) { // 生年月日(元号YYMMDD)	
				 	df1ef01.setTag16DateOfBirth(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_ADDRESS.equals(tlv.getTlvTag())) { // 住所	
				 	df1ef01.setTag17Address(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_ISSUANCEDATE.equals(tlv.getTlvTag())) { // 交付年月日(元号YYMMDD)	
				 	df1ef01.setTag18IssuanceDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_INQUIRYNO.equals(tlv.getTlvTag())) { // 照会番号	
				 	df1ef01.setTag19InquiryNo(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_LICENSECOLLOR.equals(tlv.getTlvTag())) { // 免許証の色区分(優良・新規・その他)	
				 	df1ef01.setTag1ALicenseCollor(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_EFFECTIVEDATE.equals(tlv.getTlvTag())) { // 有効期間の末日(元号YYMMDD)	
				 	df1ef01.setTag1BEffectiveDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_LICENSECONDITIONS1.equals(tlv.getTlvTag())) { // 免許の条件１	
				 	df1ef01.setTag1CLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_LICENSECONDITIONS2.equals(tlv.getTlvTag())) { // 免許の条件２	
				 	df1ef01.setTag1DLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_LICENSECONDITIONS3.equals(tlv.getTlvTag())) { // 免許の条件３	
				 	df1ef01.setTag1ELicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_LICENSECONDITIONS4.equals(tlv.getTlvTag())) { // 免許の条件４	
				 	df1ef01.setTag1FLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_ADDRESSKOANIINKAINAME.equals(tlv.getTlvTag())) { // 公安委員会名	
				 	df1ef01.setTag20AddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_LICENSENO.equals(tlv.getTlvTag())) { // 免許証の番号	
				 	df1ef01.setTag21LicenseNo(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NIKOGENLICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(二・小・原)(元号YYMMDD)	
				 	df1ef01.setTag22NiKoGenLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_TALICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(他)(元号YYMMDD)	
				 	df1ef01.setTag23TaLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NISHULICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(二種)(元号YYMMDD)	
				 	df1ef01.setTag24NishuLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_OOGATALICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(大型)(元号YYMMDD)	
				 	df1ef01.setTag25OogataLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_FUTSULICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(普通)(元号YYMMDD)	
				 	df1ef01.setTag26FutsuLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_OOTOKULICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(大特)(元号YYMMDD)	
				 	df1ef01.setTag27OoTokuLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_OOJINILICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(大自二)(元号YYMMDD)	
				 	df1ef01.setTag28OoJiNiLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_FUJINILICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(普自二)(元号YYMMDD)	
				 	df1ef01.setTag29FuJiNiLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_KOTOKULICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(小特)(元号YYMMDD)	
				 	df1ef01.setTag2AKoTokuLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_GENTSUKILICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(原付)(元号YYMMDD)	
				 	df1ef01.setTag2BGentsukiLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_KEINLICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(け引)(元号YYMMDD)	
				 	df1ef01.setTag2CKeInLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_OONILICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(大二)(元号YYMMDD)	
				 	df1ef01.setTag2DOoNiLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_FUNILICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(普二)(元号YYMMDD)	
				 	df1ef01.setTag2EFuNiLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_OOTOKUNILICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(大特二)(元号YYMMDD)	
				 	df1ef01.setTag2FOoTokuNiLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_KEINNILICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(け引二)(元号YYMMDD)	
				 	df1ef01.setTag30KeInNiLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CHUGATALICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(中型)(元号YYMMDD)	
				 	df1ef01.setTag31ChugataLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CHUNILICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(中二)(元号YYMMDD)	
				 	df1ef01.setTag32ChuNiLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_JUNCHUGATALICENSEACQUISITIONDATE.equals(tlv.getTlvTag())) { // 免許の年月日(準中型)(元号YYMMDD)	
					df1ef01.setTag33JunChugataLicenseAcquisitionDate(encodeJISX0201(tlv.getTlvValue()));
				}
			}
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF01_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF01_UNKNOWN;
		}
		
		return df1ef01;
	}
	
	/**
	 * 本籍（DF1_EF02）を取得する
	 * @return 本籍オブジェクト
	 */
	private MF_DF1_EF02 createDF1EF02() {
		MF_DF1_EF02 df1ef02 = new MF_DF1_EF02();
		ResponseAPDU answer;
		byte r[];
		try {
			answer = selectDF1();
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();

			answer = channel.transmit(new CommandAPDU(0x00, 0xB0, 0x82, 0x00, 0x52)); // 本籍取得
			r = answer.getData();
			
			Vector<DliverLisenceTLV> list = analysisTLV(r);
			for (int i = 0; list.size() > i; i++) {
				DliverLisenceTLV tlv = list.get(i);
				if (DriverLisenceConstants.TAG_HONSEKI.equals(tlv.getTlvTag())) { // 本籍
					df1ef02.setTag41Honseki(encodeJISX0208(tlv.getTlvValue()));
				}
			}
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF02_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF02_UNKNOWN;
		}
		
		return df1ef02;
	}
	
	/**
	 * 記載事変更等項（本籍除く）（DF1_EF04）を取得する
	 * @return 記載事項変更等項（本籍除く）オブジェクト
	 */
	private MF_DF1_EF04 createDF1EF04() {
		MF_DF1_EF04 df1ef04 = new MF_DF1_EF04();
		ResponseAPDU answer;
		byte r[];
		try {
			answer = selectDF1();
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();

			answer = channel.transmit(new CommandAPDU(0x00, 0xB0, 0x84, 0x00, 0x000280)); // 記載事変更等項（本籍除く）
			r = answer.getData();
			
			Vector<DliverLisenceTLV> list = analysisTLV(r);
			for (int i = 0; list.size() > i; i++) {
				DliverLisenceTLV tlv = list.get(i);
				if (DriverLisenceConstants.TAG_EXSITADDITION_EF04.equals(tlv.getTlvTag())) { // 追記の有無
					df1ef04.setTag50ExsitAddition(tlv.getTlvValue().toString());
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME1.equals(tlv.getTlvTag())) { // 新住所地公安委員会名1
				 	df1ef04.setTag51NewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME2.equals(tlv.getTlvTag())) { // 新住所地公安委員会名2
				 	df1ef04.setTag52NewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME3.equals(tlv.getTlvTag())) { // 新住所地公安委員会名3
				 	df1ef04.setTag53NewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME4.equals(tlv.getTlvTag())) { // 新住所地公安委員会名4
				 	df1ef04.setTag54NewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME5.equals(tlv.getTlvTag())) { // 新住所地公安委員会名5
				 	df1ef04.setTag55NewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME6.equals(tlv.getTlvTag())) { // 新住所地公安委員会名6
				 	df1ef04.setTag56NewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME7.equals(tlv.getTlvTag())) { // 新住所地公安委員会名7
				 	df1ef04.setTag57NewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME8.equals(tlv.getTlvTag())) { // 新住所地公安委員会名8
				 	df1ef04.setTag58NewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME9.equals(tlv.getTlvTag())) { // 新住所地公安委員会名9
				 	df1ef04.setTag59NewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME10.equals(tlv.getTlvTag())) { // 新住所地公安委員会名10
				 	df1ef04.setTag5ANewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME11.equals(tlv.getTlvTag())) { // 新住所地公安委員会名11
				 	df1ef04.setTag5BNewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME12.equals(tlv.getTlvTag())) { // 新住所地公安委員会名12
				 	df1ef04.setTag5CNewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME13.equals(tlv.getTlvTag())) { // 新住所地公安委員会名13
				 	df1ef04.setTag5DNewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME14.equals(tlv.getTlvTag())) { // 新住所地公安委員会名14
				 	df1ef04.setTag5ENewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME15.equals(tlv.getTlvTag())) { // 新住所地公安委員会名15
				 	df1ef04.setTag5FNewAddressKoanIinkaiName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAME1.equals(tlv.getTlvTag())) { // 新氏名1
				 	df1ef04.setTag60NewName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAME2.equals(tlv.getTlvTag())) { // 新氏名2
				 	df1ef04.setTag61NewName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAME3.equals(tlv.getTlvTag())) { // 新氏名3
				 	df1ef04.setTag62NewName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAME4.equals(tlv.getTlvTag())) { // 新氏名4
				 	df1ef04.setTag63NewName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAME5.equals(tlv.getTlvTag())) { // 新氏名5
				 	df1ef04.setTag64NewName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAME6.equals(tlv.getTlvTag())) { // 新氏名6
				 	df1ef04.setTag65NewName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAME7.equals(tlv.getTlvTag())) { // 新氏名7
				 	df1ef04.setTag66NewName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAME8.equals(tlv.getTlvTag())) { // 新氏名8
				 	df1ef04.setTag67NewName(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAMEKANA1.equals(tlv.getTlvTag())) { // 新呼び名（カナ）1
				 	df1ef04.setTag68NewNameKana(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAMEKANA2.equals(tlv.getTlvTag())) { // 新呼び名（カナ）2
				 	df1ef04.setTag69NewNameKana(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAMEKANA3.equals(tlv.getTlvTag())) { // 新呼び名（カナ）3
				 	df1ef04.setTag6ANewNameKana(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAMEKANA4.equals(tlv.getTlvTag())) { // 新呼び名（カナ）4
				 	df1ef04.setTag6BNewNameKana(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAMEKANA5.equals(tlv.getTlvTag())) { // 新呼び名（カナ）5
				 	df1ef04.setTag6CNewNameKana(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAMEKANA6.equals(tlv.getTlvTag())) { // 新呼び名（カナ）6
				 	df1ef04.setTag6DNewNameKana(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAMEKANA7.equals(tlv.getTlvTag())) { // 新呼び名（カナ）7
				 	df1ef04.setTag6ENewNameKana(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWNAMEKANA8.equals(tlv.getTlvTag())) { // 新呼び名（カナ）8
				 	df1ef04.setTag6FNewNameKana(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESS1.equals(tlv.getTlvTag())) { // 新住所1
				 	df1ef04.setTag70NewAddress(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESS2.equals(tlv.getTlvTag())) { // 新住所2
				 	df1ef04.setTag71NewAddress(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESS3.equals(tlv.getTlvTag())) { // 新住所3
				 	df1ef04.setTag72NewAddress(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESS4.equals(tlv.getTlvTag())) { // 新住所4
				 	df1ef04.setTag73NewAddress(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESS5.equals(tlv.getTlvTag())) { // 新住所5
				 	df1ef04.setTag74NewAddress(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESS6.equals(tlv.getTlvTag())) { // 新住所6
				 	df1ef04.setTag75NewAddress(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESS7.equals(tlv.getTlvTag())) { // 新住所7
				 	df1ef04.setTag76NewAddress(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWADDRESS8.equals(tlv.getTlvTag())) { // 新住所8
				 	df1ef04.setTag77NewAddress(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWLICENSECONDITIONS1.equals(tlv.getTlvTag())) { // 新条件1
				 	df1ef04.setTag78NewLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWLICENSECONDITIONS2.equals(tlv.getTlvTag())) { // 新条件2
				 	df1ef04.setTag79NewLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWLICENSECONDITIONS3.equals(tlv.getTlvTag())) { // 新条件3
				 	df1ef04.setTag7ANewLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWLICENSECONDITIONS4.equals(tlv.getTlvTag())) { // 新条件4
				 	df1ef04.setTag7BNewLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWLICENSECONDITIONS5.equals(tlv.getTlvTag())) { // 新条件5
				 	df1ef04.setTag7CNewLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWLICENSECONDITIONS6.equals(tlv.getTlvTag())) { // 新条件6
				 	df1ef04.setTag7DNewLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWLICENSECONDITIONS7.equals(tlv.getTlvTag())) { // 新条件7
				 	df1ef04.setTag7ENewLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWLICENSECONDITIONS8.equals(tlv.getTlvTag())) { // 新条件8
				 	df1ef04.setTag7FNewLicenseConditions(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CONDITIONSREMOVAL1.equals(tlv.getTlvTag())) { // 条件解除1
				 	df1ef04.setTag80ConditionsRemoval(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CONDITIONSREMOVAL2.equals(tlv.getTlvTag())) { // 条件解除2
				 	df1ef04.setTag81ConditionsRemoval(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CONDITIONSREMOVAL3.equals(tlv.getTlvTag())) { // 条件解除3
				 	df1ef04.setTag82ConditionsRemoval(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CONDITIONSREMOVAL4.equals(tlv.getTlvTag())) { // 条件解除4
				 	df1ef04.setTag83ConditionsRemoval(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CONDITIONSREMOVAL5.equals(tlv.getTlvTag())) { // 条件解除5
				 	df1ef04.setTag84ConditionsRemoval(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CONDITIONSREMOVAL6.equals(tlv.getTlvTag())) { // 条件解除6
				 	df1ef04.setTag85ConditionsRemoval(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CONDITIONSREMOVAL7.equals(tlv.getTlvTag())) { // 条件解除7
				 	df1ef04.setTag86ConditionsRemoval(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_CONDITIONSREMOVAL8.equals(tlv.getTlvTag())) { // 条件解除8
				 	df1ef04.setTag87ConditionsRemoval(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_REMARKS1.equals(tlv.getTlvTag())) { // 備考1
				 	df1ef04.setTag88Remarks(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_REMARKS2.equals(tlv.getTlvTag())) { // 備考2
				 	df1ef04.setTag89Remarks(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_REMARKS3.equals(tlv.getTlvTag())) { // 備考3
				 	df1ef04.setTag8ARemarks(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_REMARKS4.equals(tlv.getTlvTag())) { // 備考4
				 	df1ef04.setTag8BRemarks(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_REMARKS5.equals(tlv.getTlvTag())) { // 備考5
				 	df1ef04.setTag8CRemarks(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_REMARKS6.equals(tlv.getTlvTag())) { // 備考6
				 	df1ef04.setTag8DRemarks(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_REMARKS7.equals(tlv.getTlvTag())) { // 備考7
				 	df1ef04.setTag8ERemarks(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_REMARKS8.equals(tlv.getTlvTag())) { // 備考8
				 	df1ef04.setTag8FRemarks(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_RESERVEAREA1.equals(tlv.getTlvTag())) { // 予備1
				 	df1ef04.setTag90ReserveArea(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_RESERVEAREA2.equals(tlv.getTlvTag())) { // 予備2
				 	df1ef04.setTag91ReserveArea(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_RESERVEAREA3.equals(tlv.getTlvTag())) { // 予備3
				 	df1ef04.setTag92ReserveArea(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_RESERVEAREA4.equals(tlv.getTlvTag())) { // 予備4
				 	df1ef04.setTag93ReserveArea(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_RESERVEAREA5.equals(tlv.getTlvTag())) { // 予備5
				 	df1ef04.setTag94ReserveArea(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_RESERVEAREA6.equals(tlv.getTlvTag())) { // 予備6
				 	df1ef04.setTag95ReserveArea(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_RESERVEAREA7.equals(tlv.getTlvTag())) { // 予備7
				 	df1ef04.setTag96ReserveArea(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_RESERVEAREA8.equals(tlv.getTlvTag())) { // 予備8
				 	df1ef04.setTag97ReserveArea(encodeJISX0208(tlv.getTlvValue()));
				}
			}
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF04_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF04_UNKNOWN;
		}
		
		return df1ef04;
	}
	
	/**
	 * 記載事変更等項（本籍）（DF1_EF06）を取得する
	 * @return 記載事項変更等項（本籍）オブジェクト
	 */
	private MF_DF1_EF06 createDF1EF06() {
		MF_DF1_EF06 df1ef06 = new MF_DF1_EF06();
		ResponseAPDU answer;
		byte r[];
		try {
			answer = selectDF1();
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();

			answer = channel.transmit(new CommandAPDU(0x00, 0xB0, 0x86, 0x00, 0x000100)); // 記載事変更等項（本籍）
			r = answer.getData();
			
			Vector<DliverLisenceTLV> list = analysisTLV(r);
			for (int i = 0; list.size() > i; i++) {
				DliverLisenceTLV tlv = list.get(i);
				if (DriverLisenceConstants.TAG_EXSITADDITION_EF06.equals(tlv.getTlvTag())) { // 追記の有無
					df1ef06.setTagAAExsitAddition(tlv.getTlvValue().toString());
				} else if (DriverLisenceConstants.TAG_NEWHONSEKI1.equals(tlv.getTlvTag())) { // 新本籍1
					df1ef06.setTagABNewHonseki(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWHONSEKI2.equals(tlv.getTlvTag())) { // 新本籍2
					df1ef06.setTagACNewHonseki(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWHONSEKI3.equals(tlv.getTlvTag())) { // 新本籍3
					df1ef06.setTagADNewHonseki(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWHONSEKI4.equals(tlv.getTlvTag())) { // 新本籍4
					df1ef06.setTagAENewHonseki(encodeJISX0208(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_NEWHONSEKI5.equals(tlv.getTlvTag())) { // 新本籍5
					df1ef06.setTagAFNewHonseki(encodeJISX0208(tlv.getTlvValue()));
				}
			}
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF06_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF06_UNKNOWN;
		}
		
		return df1ef06;
	}
	
	/**
	 * 電子署名（DF1_EF07）を取得する
	 * @return 電子署名オブジェクト
	 */
	private MF_DF1_EF07 createDF1EF07() {
		MF_DF1_EF07 df1ef07 = new MF_DF1_EF07();
		ResponseAPDU answer;
		byte r[];
		try {
			answer = selectDF1();
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();

			answer = channel.transmit(new CommandAPDU(0x00, 0xB0, 0x87, 0x00, 0x000242)); // 電子証明の取得
			r = answer.getData();
			
			Vector<DliverLisenceTLV> list = analysisTLV(r);
			for (int i = 0; list.size() > i; i++) {
				DliverLisenceTLV tlv = list.get(i);
				if (DriverLisenceConstants.TAG_ESIGN.equals(tlv.getTlvTag())) { // 電子署名
					df1ef07.setTagB1ESign(tlv.getTlvValue());
				} else if (DriverLisenceConstants.TAG_SERIALNO.equals(tlv.getTlvTag())) { // シリアル番号
					df1ef07.setTagB2SerialNo(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_RFU.equals(tlv.getTlvTag())) { // （RFU）
					df1ef07.setTagB3RFU(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_PUBLISHERNAME.equals(tlv.getTlvTag())) { // 発行者名
					df1ef07.setTagB4PublisherName(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_MAINPERSONNAME.equals(tlv.getTlvTag())) { // 主体者名
					df1ef07.setTagB5MainPersonName(encodeJISX0201(tlv.getTlvValue()));
				} else if (DriverLisenceConstants.TAG_MAINPERSONIDENTIFIER.equals(tlv.getTlvTag())) { // 主体者識別子
					df1ef07.setTagB6MainPersonIdentifier(tlv.getTlvValue());
				}
			}
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF07_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF1EF07_UNKNOWN;
		}
		
		return df1ef07;
	}
	
	/**
	 * 写真（DF2_EF01）を取得する
	 * @return 電子署名オブジェクト
	 */
	private MF_DF2_EF01 createDF2EF01() {
		MF_DF2_EF01 df2ef01 = new MF_DF2_EF01();
		ResponseAPDU answer;
		byte r[];
		try {
			answer = selectDF2();
			if (answer.getSW() != DriverLisenceConstants.SW_SUCCESS) throw new Exception();

			answer = channel.transmit(new CommandAPDU(0x00, 0xB0, 0x81, 0x00, 0x0007D5)); // 電子証明の取得
			r = answer.getData();
			
			Vector<DliverLisenceTLV> list = analysisTLV(r);
			for (int i = 0; list.size() > i; i++) {
				DliverLisenceTLV tlv = list.get(i);
				if (DriverLisenceConstants.TAG_IMAGE.equals(tlv.getTlvTag())) { // 写真
					df2ef01.setTag5F40Image(tlv.getTlvValue());
				}
			}
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF2EF01_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_CREATE_DF2EF01_UNKNOWN;
		}
		
		return df2ef01;
	}
	
	/**
	 * スマートカードへの接続を終了する
	 */
	private void disconnect() {
		try {
			// ICカードの接続を終了
			this.card.disconnect(false);
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_DISCONNECT_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_DISCONNECT_UNKNOWN;
		}
	}

	/**
	 * DF1を選択する
	 * @return 選択した結果
	 */
	private ResponseAPDU selectDF1() {
		ResponseAPDU answer = null;
		try {
			answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x04, 0x0C, new byte[] {(byte)0xA0, 0x00, 0x00, 0x02, 0x31, 0x01, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00})); // MF DF1選択
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_SELECT_DF1_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_SELECT_DF1_UNKNOWN;
		}
		return answer;
	}

	/**
	 * DF2を選択する
	 * @return 選択した結果
	 */
	private ResponseAPDU selectDF2() {
		ResponseAPDU answer = null;
		try {
			answer = channel.transmit(new CommandAPDU(0x00, 0xA4, 0x04, 0x0C, new byte[] {(byte)0xA0, 0x00, 0x00, 0x02, 0x31, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00})); // MF DF2選択
			   
		} catch (CardException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_SELECT_DF2_FAILURE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			errorcode = DriverLisenceConstants.ERROR_CODE_SELECT_DF2_UNKNOWN;
		}
		return answer;
	}
	
	/**
	 * 取得したTLVを解析して返す
	 * @param data
	 * @return TLVモデル
	 */
	private Vector<DliverLisenceTLV> analysisTLV(byte[] data) {
		Vector<DliverLisenceTLV> list = new Vector<DliverLisenceTLV>();
		DliverLisenceTLV tlv = new DliverLisenceTLV();
		boolean tagflg = false;
		boolean lengthflg = false;
		boolean valueflg = false;
		boolean additionflg = false;
		int length = 0;
		byte[] value = {};
		int addvaluenum = 0;
		
		for (int i=0; i<data.length; i++) {
			
			if (data[i] == -1 && !tagflg) { // -1の場合はそれ以降に値が存在しないため処理を中止
				break;
			}
			
			String str = editByteToHexStr(data[i]);
			
			if (!tagflg) {
				tlv.setTlvTag(str);
				if ("5F".equals(str)) {// タグが0x5Fから始まる場合、4バイトタグになる
					String str2 = editByteToHexStr(data[i + 1]);
					if ("40".equals(str2)) {
						tlv.setTlvTag(str + str2);
						i++;
					}
				}
				tagflg = true;
				
			} else if (!lengthflg) {
				if ("82".equals(str)) {// 長さが0x82から始まる場合、0x82は無視して以降4バイトを長さとする（128~65536）
					String str2 = editByteToHexStr(data[++i]);
					String str3 = editByteToHexStr(data[++i]);
					length = calcDataLength(str2 + str3);
				} else {
					length = calcDataLength(str);
				}
				tlv.setTlvLength(length);
				lengthflg = true;
				
			} else if (!valueflg) {
				if (length <= 0) { // 長さが0以下の場合ループ変数を1つ戻す
					tlv.setTlvValue(value);
					valueflg = true;
					i--;
				} else {
					if (addvaluenum == 0) { // 初回のみ処理
						if (isAdditionTag(tlv.getTlvTag())) { // 追記されたデータの場合先頭15バイト,末尾10バイトを含めない
//							i++;
//							length--;
							i += 15;
							length -= 25;
							additionflg = true;
						}
						value = new byte[length];
					}
					if (value.length > 0) {
						value[addvaluenum++] = data[i];
					} else {
						i--;
					}
					if (length <= addvaluenum) { // 長さの分だけループ処理を実施する
						tlv.setTlvValue(value);
						valueflg = true;
					}
				}
			}
			if (tagflg && lengthflg && valueflg) { // 全て設定完了したため、変数を初期化してから再ループ
				tagflg = false;
				lengthflg = false;
				valueflg = false;
				list.add(tlv);
				addvaluenum = 0;
				tlv = new DliverLisenceTLV();
				if (additionflg) { // 追記されたデータの末尾10バイト分をスキップ
					i += 10;
					additionflg = false;
				}
			}
		}
		return list;
	}
	
	/**
	 * byteを16進数文字列に変換する
	 * @param b byte
	 * @return 16進数文字列（2桁）
	 */
	private String editByteToHexStr(byte b) {
		String str = String.format("%2s", Integer.toHexString(b)).replace(" ", "0");
		if (str.length() > 2) {
			str = str.substring(str.length() - 2); // byteが負の数の場合は、4bitで計算されるためFFがはいる頭3bitは削除する
		}
		str = str.toUpperCase();
		return str;
	}
	
	/**
	 * 16進数の文字列から10進数に変換する
	 * @param hex 16進数文字列
	 * @return 変換後の数値（データの長さ）
	 */
	private int calcDataLength(String hex) {
		int len = 0;
		len = Integer.decode("0x" + hex);
		return len;
	}
	
	/**
	 * バイト配列をJIS X 0208に変換する
	 * @param b
	 * @return 変換後文字列
	 */
	private String encodeJISX0208(byte[] b) {
		String result = "";
		try {
			result = new String(b, "x-JIS0208");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * バイト配列をJIS X 0201に変換する
	 * @param b
	 * @return 変換後文字列
	 */
	private String encodeJISX0201(byte[] b) {
		String result = "";
		try {
			result = new String(b, "JIS_X0201");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 値の先頭にJIS X 0208制定年（1バイト）が存在するタグかを判断する
	 * @param tag
	 * @return true：JIS X 0208制定年（1バイト）が存在する false:JIS X 0208制定年（1バイト）が存在しない
	 */
	private boolean isAdditionTag(String tag) {
		boolean result = false;
		if (DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME1.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME2.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME3.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME4.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME5.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME6.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME7.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME8.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME9.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME10.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME11.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME12.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME13.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME14.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESSKOANIINKAINAME15.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAME1.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAME2.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAME3.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAME4.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAME5.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAME6.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAME7.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAME8.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAMEKANA1.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAMEKANA2.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAMEKANA3.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAMEKANA4.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAMEKANA5.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAMEKANA6.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAMEKANA7.equals(tag) ||
				DriverLisenceConstants.TAG_NEWNAMEKANA8.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESS1.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESS2.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESS3.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESS4.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESS5.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESS6.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESS7.equals(tag) ||
				DriverLisenceConstants.TAG_NEWADDRESS8.equals(tag) ||
				DriverLisenceConstants.TAG_NEWLICENSECONDITIONS1.equals(tag) ||
				DriverLisenceConstants.TAG_NEWLICENSECONDITIONS2.equals(tag) ||
				DriverLisenceConstants.TAG_NEWLICENSECONDITIONS3.equals(tag) ||
				DriverLisenceConstants.TAG_NEWLICENSECONDITIONS4.equals(tag) ||
				DriverLisenceConstants.TAG_NEWLICENSECONDITIONS5.equals(tag) ||
				DriverLisenceConstants.TAG_NEWLICENSECONDITIONS6.equals(tag) ||
				DriverLisenceConstants.TAG_NEWLICENSECONDITIONS7.equals(tag) ||
				DriverLisenceConstants.TAG_NEWLICENSECONDITIONS8.equals(tag) ||
				DriverLisenceConstants.TAG_CONDITIONSREMOVAL1.equals(tag) ||
				DriverLisenceConstants.TAG_CONDITIONSREMOVAL2.equals(tag) ||
				DriverLisenceConstants.TAG_CONDITIONSREMOVAL3.equals(tag) ||
				DriverLisenceConstants.TAG_CONDITIONSREMOVAL4.equals(tag) ||
				DriverLisenceConstants.TAG_CONDITIONSREMOVAL5.equals(tag) ||
				DriverLisenceConstants.TAG_CONDITIONSREMOVAL6.equals(tag) ||
				DriverLisenceConstants.TAG_CONDITIONSREMOVAL7.equals(tag) ||
				DriverLisenceConstants.TAG_CONDITIONSREMOVAL8.equals(tag) ||
				DriverLisenceConstants.TAG_REMARKS1.equals(tag) ||
				DriverLisenceConstants.TAG_REMARKS2.equals(tag) ||
				DriverLisenceConstants.TAG_REMARKS3.equals(tag) ||
				DriverLisenceConstants.TAG_REMARKS4.equals(tag) ||
				DriverLisenceConstants.TAG_REMARKS5.equals(tag) ||
				DriverLisenceConstants.TAG_REMARKS6.equals(tag) ||
				DriverLisenceConstants.TAG_REMARKS7.equals(tag) ||
				DriverLisenceConstants.TAG_REMARKS8.equals(tag) ||
				DriverLisenceConstants.TAG_RESERVEAREA1.equals(tag) ||
				DriverLisenceConstants.TAG_RESERVEAREA2.equals(tag) ||
				DriverLisenceConstants.TAG_RESERVEAREA3.equals(tag) ||
				DriverLisenceConstants.TAG_RESERVEAREA4.equals(tag) ||
				DriverLisenceConstants.TAG_RESERVEAREA5.equals(tag) ||
				DriverLisenceConstants.TAG_RESERVEAREA6.equals(tag) ||
				DriverLisenceConstants.TAG_RESERVEAREA7.equals(tag) ||
				DriverLisenceConstants.TAG_RESERVEAREA8.equals(tag) ||
				DriverLisenceConstants.TAG_NEWHONSEKI1.equals(tag) ||
				DriverLisenceConstants.TAG_NEWHONSEKI2.equals(tag) ||
				DriverLisenceConstants.TAG_NEWHONSEKI3.equals(tag) ||
				DriverLisenceConstants.TAG_NEWHONSEKI4.equals(tag) ||
				DriverLisenceConstants.TAG_NEWHONSEKI5.equals(tag)
				) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 4桁の数値か判断する
	 * @param str 文字列（全角も可）
	 * @return 4桁数値かどうか
	 */
	private boolean isNumberFourDigit(String str) {
		boolean result = false;
		if (str.length() == 4) {
			try {
				Integer.parseInt(str);
				result = true;
			} catch (NumberFormatException e) {
			}
		}
		return result;
	}
	
	/**
	 * 4桁の数字文字列を16進数バイト配列に変換する
	 * @param str
	 * @return 変換後バイト配列
	 */
	private byte[] convertPW(String str) {
		byte[] result = new byte[str.length()];
		for (int i = 0; str.length() > i; i++) {
			if ("1".equals(str.substring(i, i + 1))) {
				result[i] = 0x31;
			} else if ("2".equals(str.substring(i, i + 1))) {
				result[i] = 0x32;
			} else if ("3".equals(str.substring(i, i + 1))) {
				result[i] = 0x33;
			} else if ("4".equals(str.substring(i, i + 1))) {
				result[i] = 0x34;
			} else if ("5".equals(str.substring(i, i + 1))) {
				result[i] = 0x35;
			} else if ("6".equals(str.substring(i, i + 1))) {
				result[i] = 0x36;
			} else if ("7".equals(str.substring(i, i + 1))) {
				result[i] = 0x37;
			} else if ("8".equals(str.substring(i, i + 1))) {
				result[i] = 0x38;
			} else if ("9".equals(str.substring(i, i + 1))) {
				result[i] = 0x39;
			} else if ("0".equals(str.substring(i, i + 1))) {
				result[i] = 0x30;
			}
		}
		return result;
	}
}
