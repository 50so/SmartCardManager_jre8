package jp.co.matsusaka.logic.jpki;

import jp.co.matsusaka.model.jpki.MyNumberCardData;
import jp.co.matsusaka.constant.jpki.MyNumberCardConstants;
import jp.co.matsusaka.model.jpki.FetchMyNumberCardDataReturnValue;
import jp.go.jpki.appli.JPKIUserCertBasicData;
import jp.go.jpki.appli.JPKIUserCertException;
import jp.go.jpki.appli.JPKIUserCertService;

/**
 * マイナンバーカードを操作するクラス
 * @author 101031
 *
 */
public class MyNumberCardManager {
	
	// 利用者証明書情報
	private JPKIUserCertService ucs;
	
	// エラーコード
	private int errorCode;
	
	/**
	 * 証明書情報を設定し、メソッドから参照できるようにする。
	 * @param type 証明書種類（1:利用者証明用電子証明書 or 2:署名小電子証明書）
	 */
	public MyNumberCardManager(String type) {
		JPKIUserCertService ucs;
		
		if ("1".equals(type)) {
			// 利用者証明用電子証明書
			CryptAuth ca = new CryptAuth();
			ucs = ca.getAuthucs();
			
		} else if ("2".equals(type)) {
			// 署名用電子証明書
			CryptSign cs = new CryptSign();
			ucs = cs.getSignucs();
			
		} else {
			ucs = null;
		}
		
		this.ucs = ucs;
	}
	
	/**
	 *  証明書情報の表示
	 */
    public void showCertViewer(){
        try {
            //証明書を表示する
            ucs.showCertViewer();
        } catch (JPKIUserCertException e) {
            // JPKIUserCertException 発生時の処理
            int code = e.getErrorCode();
            String str_code = Integer.valueOf(code).toString();
            System.out.println("JPKIUserCertException 発生しました エラーコード=" + str_code);
            e.printStackTrace();
            errorCode = MyNumberCardConstants.JPKI_ERROR;
            
        } catch (Exception e) {
            // その他の例外
        	System.out.println("その他の例外発生");
        	System.out.println(e.getMessage());
        	System.out.println(e.getStackTrace());
        	errorCode = MyNumberCardConstants.OTHER_ERROR;
        }
    }
    
    /**
     * 4情報の取得
     * @return 4情報クラス（外字情報含む）
     */
    public FetchMyNumberCardDataReturnValue fetchBasicData() {
    	String name = "";
    	String address = "";
    	String gender = "";
    	String birth = "";
    	String substituteCharacterOfAddress = "";
    	String substituteCharacterOfName= "";
    	MyNumberCardData result = new MyNumberCardData();
    	errorCode = MyNumberCardConstants.SUCCESS;
    	
        try{
            // 基本4情報取得機能
            JPKIUserCertBasicData bd = ucs.getBasicData();
            name = bd.getName();
            address = bd.getAddress();
            gender = bd.getGender();
            birth = bd.getDateOfBirth();
            substituteCharacterOfAddress = bd.getSubstituteCharacterOfAddress();
            substituteCharacterOfName = bd.getSubstituteCharacterOfName();
            
            result.setName(name);
            result.setAddress(address);
            result.setGender(gender);
            result.setDateOfBirth(birth);
            result.setSubstituteCharacterOfAddress(substituteCharacterOfAddress);
            result.setSubstituteCharacterOfName(substituteCharacterOfName);
            
        } catch (JPKIUserCertException e) {
        	System.out.println("エラーコード：" + e.getErrorCode());
        	System.out.println(e.getMessage());
        	System.out.println(e.getStackTrace());
        	errorCode = MyNumberCardConstants.JPKI_ERROR;
     
        } catch (Exception e) {
            // その他の例外
        	System.out.println("その他の例外発生");
        	System.out.println(e.getMessage());
        	System.out.println(e.getStackTrace());
        	errorCode = MyNumberCardConstants.OTHER_ERROR;
        }
    	// 戻り値の設定
    	FetchMyNumberCardDataReturnValue returnvalue = new FetchMyNumberCardDataReturnValue();
    	returnvalue.setErrorCode(errorCode);
    	returnvalue.setMyNumberCardData(result);
        return returnvalue;
	}

}
