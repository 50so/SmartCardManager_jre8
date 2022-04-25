package jp.co.matsusaka.constant.driverlisence;

public class DriverLisenceConstants {
	/**
	* スマートカード操作成功
	*/
	public static final int SW_SUCCESS = 0x9000;
	/**
	* 残り照合可能回数1回
	*/
	public static final int SW_REMAINING_1 = 0x63C1;
	/**
	* 残り照合可能回数2回
	*/
	public static final int SW_REMAINING_2 = 0x63C2;
	/**
	* 残り照合可能回数3回
	*/
	public static final int SW_REMAINING_3 = 0x63C3;
	/**
	* エラーなし
	*/
	public static final int ERROR_CODE_OK = 0;
	/**
	* スマートカード接続失敗
	*/
	public static final int ERROR_CODE_CONNECT_FAILURE = 1;
	/**
	* スマートカード接続不明エラー
	*/
	public static final int ERROR_CODE_CONNECT_UNKNOWN = 901;
	/**
	* 暗証番号設定取得失敗
	*/
	public static final int ERROR_CODE_EXISTPIN_FAILURE = 2;
	/**
	* 暗証番号設定取得不明エラー
	*/
	public static final int ERROR_CODE_EXISTPIN_UNKNOWN = 902;
	/**
	* 暗証番号認証失敗
	*/
	public static final int ERROR_CODE_VERIFYPIN_FAILURE = 3;
	/**
	* 暗証番号認証不明エラー
	*/
	public static final int ERROR_CODE_VERIFYPIN_UNKNOWN = 903;
	/**
	* 暗証番号照合可能回数取得失敗
	*/
	public static final int ERROR_CODE_VERIFYPIN_REMAININGTIMES_FAILURE = 4;
	/**
	* 暗証番号照合可能回数取得不明エラー
	*/
	public static final int ERROR_CODE_VERIFYPIN_REMAININGTIMES_UNKNOWN = 904;
	/**
	* DF1EF01作成失敗
	*/
	public static final int ERROR_CODE_CREATE_DF1EF01_FAILURE = 5;
	/**
	* DF1EF01作成不明エラー
	*/
	public static final int ERROR_CODE_CREATE_DF1EF01_UNKNOWN = 905;
	/**
	* DF1EF02作成失敗
	*/
	public static final int ERROR_CODE_CREATE_DF1EF02_FAILURE = 6;
	/**
	* DF1EF02作成不明エラー
	*/
	public static final int ERROR_CODE_CREATE_DF1EF02_UNKNOWN = 906;
	/**
	* DF1EF04作成失敗
	*/
	public static final int ERROR_CODE_CREATE_DF1EF04_FAILURE = 7;
	/**
	* DF1EF04作成不明エラー
	*/
	public static final int ERROR_CODE_CREATE_DF1EF04_UNKNOWN = 907;
	/**
	* DF1EF06作成失敗
	*/
	public static final int ERROR_CODE_CREATE_DF1EF06_FAILURE = 8;
	/**
	* DF1EF06作成不明エラー
	*/
	public static final int ERROR_CODE_CREATE_DF1EF06_UNKNOWN = 908;
	/**
	* DF1EF07作成失敗
	*/
	public static final int ERROR_CODE_CREATE_DF1EF07_FAILURE = 9;
	/**
	* DF1EF07作成不明エラー
	*/
	public static final int ERROR_CODE_CREATE_DF1EF07_UNKNOWN = 909;
	/**
	* DF2EF01作成失敗
	*/
	public static final int ERROR_CODE_CREATE_DF2EF01_FAILURE = 10;
	/**
	* DF2EF01作成不明エラー
	*/
	public static final int ERROR_CODE_CREATE_DF2EF01_UNKNOWN = 910;
	/**
	* スマートカード接続終了失敗
	*/
	public static final int ERROR_CODE_DISCONNECT_FAILURE = 11;
	/**
	* スマートカード接続終了不明エラー
	*/
	public static final int ERROR_CODE_DISCONNECT_UNKNOWN = 911;
	/**
	* DF1選択失敗
	*/
	public static final int ERROR_CODE_SELECT_DF1_FAILURE = 12;
	/**
	* DF1選択不明エラー
	*/
	public static final int ERROR_CODE_SELECT_DF1_UNKNOWN = 912;
	/**
	* DF2選択失敗
	*/
	public static final int ERROR_CODE_SELECT_DF2_FAILURE = 13;
	/**
	* DF2選択不明エラー
	*/
	public static final int ERROR_CODE_SELECT_DF2_UNKNOWN = 913;
	/**
	* 暗証番号設定
	*/
	public static final String TAG_PINFLG = "05";
	/**
	* JIS X 0208 制定年番号
	*/
	public static final String TAG_ENACTMENTYEARNO = "11";
	/**
	* 氏名
	*/
	public static final String TAG_NAME = "12";
	/**
	* 呼び名(カナ)
	*/
	public static final String TAG_NAMEKANA = "13";
	/**
	* 通称名
	*/
	public static final String TAG_TUUSHOU = "14";
	/**
	* 統一氏名(カナ)
	*/
	public static final String TAG_TOUITSUNAME = "15";
	/**
	* 生年月日(元号YYMMDD)
	*/
	public static final String TAG_DATEOFBIRTH = "16";
	/**
	* 住所
	*/
	public static final String TAG_ADDRESS = "17";
	/**
	* 交付年月日(元号YYMMDD)
	*/
	public static final String TAG_ISSUANCEDATE = "18";
	/**
	* 照会番号
	*/
	public static final String TAG_INQUIRYNO = "19";
	/**
	* 免許証の色区分(優良・新規・その他)
	*/
	public static final String TAG_LICENSECOLLOR = "1A";
	/**
	* 有効期間の末日(元号YYMMDD)
	*/
	public static final String TAG_EFFECTIVEDATE = "1B";
	/**
	* 免許の条件１
	*/
	public static final String TAG_LICENSECONDITIONS1 = "1C";
	/**
	* 免許の条件２
	*/
	public static final String TAG_LICENSECONDITIONS2 = "1D";
	/**
	* 免許の条件３
	*/
	public static final String TAG_LICENSECONDITIONS3 = "1E";
	/**
	* 免許の条件４
	*/
	public static final String TAG_LICENSECONDITIONS4 = "1F";
	/**
	* 公安委員会名
	*/
	public static final String TAG_ADDRESSKOANIINKAINAME = "20";
	/**
	* 免許証の番号
	*/
	public static final String TAG_LICENSENO = "21";
	/**
	* 免許の年月日(二・小・原)(元号YYMMDD)
	*/
	public static final String TAG_NIKOGENLICENSEACQUISITIONDATE = "22";
	/**
	* 免許の年月日(他)(元号YYMMDD)
	*/
	public static final String TAG_TALICENSEACQUISITIONDATE = "23";
	/**
	* 免許の年月日(二種)(元号YYMMDD)
	*/
	public static final String TAG_NISHULICENSEACQUISITIONDATE = "24";
	/**
	* 免許の年月日(大型)(元号YYMMDD)
	*/
	public static final String TAG_OOGATALICENSEACQUISITIONDATE = "25";
	/**
	* 免許の年月日(普通)(元号YYMMDD)
	*/
	public static final String TAG_FUTSULICENSEACQUISITIONDATE = "26";
	/**
	* 免許の年月日(大特)(元号YYMMDD)
	*/
	public static final String TAG_OOTOKULICENSEACQUISITIONDATE = "27";
	/**
	* 免許の年月日(大自二)(元号YYMMDD)
	*/
	public static final String TAG_OOJINILICENSEACQUISITIONDATE = "28";
	/**
	* 免許の年月日(普自二)(元号YYMMDD)
	*/
	public static final String TAG_FUJINILICENSEACQUISITIONDATE = "29";
	/**
	* 免許の年月日(小特)(元号YYMMDD)
	*/
	public static final String TAG_KOTOKULICENSEACQUISITIONDATE = "2A";
	/**
	* 免許の年月日(原付)(元号YYMMDD)
	*/
	public static final String TAG_GENTSUKILICENSEACQUISITIONDATE = "2B";
	/**
	* 免許の年月日(け引)(元号YYMMDD)
	*/
	public static final String TAG_KEINLICENSEACQUISITIONDATE = "2C";
	/**
	* 免許の年月日(大二)(元号YYMMDD)
	*/
	public static final String TAG_OONILICENSEACQUISITIONDATE = "2D";
	/**
	* 免許の年月日(普二)(元号YYMMDD)
	*/
	public static final String TAG_FUNILICENSEACQUISITIONDATE = "2E";
	/**
	* 免許の年月日(大特二)(元号YYMMDD)
	*/
	public static final String TAG_OOTOKUNILICENSEACQUISITIONDATE = "2F";
	/**
	* 免許の年月日(け引二)(元号YYMMDD)
	*/
	public static final String TAG_KEINNILICENSEACQUISITIONDATE = "30";
	/**
	* 免許の年月日(中型)(元号YYMMDD)
	*/
	public static final String TAG_CHUGATALICENSEACQUISITIONDATE = "31";
	/**
	* 免許の年月日(中二)(元号YYMMDD)
	*/
	public static final String TAG_CHUNILICENSEACQUISITIONDATE = "32";
	/**
	* 免許の年月日(準中型)(元号YYMMDD)
	*/
	public static final String TAG_JUNCHUGATALICENSEACQUISITIONDATE = "33";
	/**
	* 本籍
	*/
	public static final String TAG_HONSEKI = "41";
	/**
	* 追記の有無（初回は"11"）
	*/
	public static final String TAG_EXSITADDITION_EF04 = "50";
	/**
	* 新住所地公安委員会名1
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME1 = "51";
	/**
	* 新住所地公安委員会名2
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME2 = "52";
	/**
	* 新住所地公安委員会名3
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME3 = "53";
	/**
	* 新住所地公安委員会名4
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME4 = "54";
	/**
	* 新住所地公安委員会名5
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME5 = "55";
	/**
	* 新住所地公安委員会名6
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME6 = "56";
	/**
	* 新住所地公安委員会名7
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME7 = "57";
	/**
	* 新住所地公安委員会名8
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME8 = "58";
	/**
	* 新住所地公安委員会名9
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME9 = "59";
	/**
	* 新住所地公安委員会名10
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME10 = "5A";
	/**
	* 新住所地公安委員会名11
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME11 = "5B";
	/**
	* 新住所地公安委員会名12
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME12 = "5C";
	/**
	* 新住所地公安委員会名13
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME13 = "5D";
	/**
	* 新住所地公安委員会名14
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME14 = "5E";
	/**
	* 新住所地公安委員会名15
	*/
	public static final String TAG_NEWADDRESSKOANIINKAINAME15 = "5F";
	/**
	* 新氏名1
	*/
	public static final String TAG_NEWNAME1 = "60";
	/**
	* 新氏名2
	*/
	public static final String TAG_NEWNAME2 = "61";
	/**
	* 新氏名3
	*/
	public static final String TAG_NEWNAME3 = "62";
	/**
	* 新氏名4
	*/
	public static final String TAG_NEWNAME4 = "63";
	/**
	* 新氏名5
	*/
	public static final String TAG_NEWNAME5 = "64";
	/**
	* 新氏名6
	*/
	public static final String TAG_NEWNAME6 = "65";
	/**
	* 新氏名7
	*/
	public static final String TAG_NEWNAME7 = "66";
	/**
	* 新氏名8
	*/
	public static final String TAG_NEWNAME8 = "67";
	/**
	* 新呼び名（カナ）1
	*/
	public static final String TAG_NEWNAMEKANA1 = "68";
	/**
	* 新呼び名（カナ）2
	*/
	public static final String TAG_NEWNAMEKANA2 = "69";
	/**
	* 新呼び名（カナ）3
	*/
	public static final String TAG_NEWNAMEKANA3 = "6A";
	/**
	* 新呼び名（カナ）4
	*/
	public static final String TAG_NEWNAMEKANA4 = "6B";
	/**
	* 新呼び名（カナ）5
	*/
	public static final String TAG_NEWNAMEKANA5 = "6C";
	/**
	* 新呼び名（カナ）6
	*/
	public static final String TAG_NEWNAMEKANA6 = "6D";
	/**
	* 新呼び名（カナ）7
	*/
	public static final String TAG_NEWNAMEKANA7 = "6E";
	/**
	* 新呼び名（カナ）8
	*/
	public static final String TAG_NEWNAMEKANA8 = "6F";
	/**
	* 新住所1
	*/
	public static final String TAG_NEWADDRESS1 = "70";
	/**
	* 新住所2
	*/
	public static final String TAG_NEWADDRESS2 = "71";
	/**
	* 新住所3
	*/
	public static final String TAG_NEWADDRESS3 = "72";
	/**
	* 新住所4
	*/
	public static final String TAG_NEWADDRESS4 = "73";
	/**
	* 新住所5
	*/
	public static final String TAG_NEWADDRESS5 = "74";
	/**
	* 新住所6
	*/
	public static final String TAG_NEWADDRESS6 = "75";
	/**
	* 新住所7
	*/
	public static final String TAG_NEWADDRESS7 = "76";
	/**
	* 新住所8
	*/
	public static final String TAG_NEWADDRESS8 = "77";
	/**
	* 新条件1
	*/
	public static final String TAG_NEWLICENSECONDITIONS1 = "78";
	/**
	* 新条件2
	*/
	public static final String TAG_NEWLICENSECONDITIONS2 = "79";
	/**
	* 新条件3
	*/
	public static final String TAG_NEWLICENSECONDITIONS3 = "7A";
	/**
	* 新条件4
	*/
	public static final String TAG_NEWLICENSECONDITIONS4 = "7B";
	/**
	* 新条件5
	*/
	public static final String TAG_NEWLICENSECONDITIONS5 = "7C";
	/**
	* 新条件6
	*/
	public static final String TAG_NEWLICENSECONDITIONS6 = "7D";
	/**
	* 新条件7
	*/
	public static final String TAG_NEWLICENSECONDITIONS7 = "7E";
	/**
	* 新条件8
	*/
	public static final String TAG_NEWLICENSECONDITIONS8 = "7F";
	/**
	* 条件解除1
	*/
	public static final String TAG_CONDITIONSREMOVAL1 = "80";
	/**
	* 条件解除2
	*/
	public static final String TAG_CONDITIONSREMOVAL2 = "81";
	/**
	* 条件解除3
	*/
	public static final String TAG_CONDITIONSREMOVAL3 = "82";
	/**
	* 条件解除4
	*/
	public static final String TAG_CONDITIONSREMOVAL4 = "83";
	/**
	* 条件解除5
	*/
	public static final String TAG_CONDITIONSREMOVAL5 = "84";
	/**
	* 条件解除6
	*/
	public static final String TAG_CONDITIONSREMOVAL6 = "85";
	/**
	* 条件解除7
	*/
	public static final String TAG_CONDITIONSREMOVAL7 = "86";
	/**
	* 条件解除8
	*/
	public static final String TAG_CONDITIONSREMOVAL8 = "87";
	/**
	* 備考1
	*/
	public static final String TAG_REMARKS1 = "88";
	/**
	* 備考2
	*/
	public static final String TAG_REMARKS2 = "89";
	/**
	* 備考3
	*/
	public static final String TAG_REMARKS3 = "8A";
	/**
	* 備考4
	*/
	public static final String TAG_REMARKS4 = "8B";
	/**
	* 備考5
	*/
	public static final String TAG_REMARKS5 = "8C";
	/**
	* 備考6
	*/
	public static final String TAG_REMARKS6 = "8D";
	/**
	* 備考7
	*/
	public static final String TAG_REMARKS7 = "8E";
	/**
	* 備考8
	*/
	public static final String TAG_REMARKS8 = "8F";
	/**
	* 予備1
	*/
	public static final String TAG_RESERVEAREA1 = "90";
	/**
	* 予備2
	*/
	public static final String TAG_RESERVEAREA2 = "91";
	/**
	* 予備3
	*/
	public static final String TAG_RESERVEAREA3 = "92";
	/**
	* 予備4
	*/
	public static final String TAG_RESERVEAREA4 = "93";
	/**
	* 予備5
	*/
	public static final String TAG_RESERVEAREA5 = "94";
	/**
	* 予備6
	*/
	public static final String TAG_RESERVEAREA6 = "95";
	/**
	* 予備7
	*/
	public static final String TAG_RESERVEAREA7 = "96";
	/**
	* 予備8
	*/
	public static final String TAG_RESERVEAREA8 = "97";
	/**
	* 追記の有無（初回は"11"）
	*/
	public static final String TAG_EXSITADDITION_EF06 = "AA";
	/**
	* 本籍1
	*/
	public static final String TAG_NEWHONSEKI1 = "AB";
	/**
	* 本籍2
	*/
	public static final String TAG_NEWHONSEKI2 = "AC";
	/**
	* 本籍3
	*/
	public static final String TAG_NEWHONSEKI3 = "AD";
	/**
	* 本籍4
	*/
	public static final String TAG_NEWHONSEKI4 = "AE";
	/**
	* 本籍5
	*/
	public static final String TAG_NEWHONSEKI5 = "AF";
	/**
	* 電子署名
	*/
	public static final String TAG_ESIGN = "B1";
	/**
	* シリアル番号
	*/
	public static final String TAG_SERIALNO = "B2";
	/**
	* （RFU）
	*/
	public static final String TAG_RFU = "B3";
	/**
	* 発行者名
	*/
	public static final String TAG_PUBLISHERNAME = "B4";
	/**
	* 主体者名
	*/
	public static final String TAG_MAINPERSONNAME = "B5";
	/**
	* 主体者識別子
	*/
	public static final String TAG_MAINPERSONIDENTIFIER = "B6";
	/**
	* 写真（JPEG2000）
	*/
	public static final String TAG_IMAGE = "5F40";
	

}
