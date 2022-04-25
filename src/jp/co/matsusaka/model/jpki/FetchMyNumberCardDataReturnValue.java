package jp.co.matsusaka.model.jpki;

public class FetchMyNumberCardDataReturnValue {
	
	// 4情報
	private MyNumberCardData myNumberCardData;
	
	// エラーコード
	private int errorCode;


	/**
	 * 4情報を返します。
	 * @return basicData
	 */
	public MyNumberCardData getMyNumberCardData() {
		return myNumberCardData;
	}

	/**
	 * 免許証情報を設定します。
	 * @param basicData
	 */
	public void setMyNumberCardData(MyNumberCardData basicData) {
		this.myNumberCardData = basicData;
	}

	/**
	 * エラーコードを返します。
	 * 
	 * @return errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * エラーコードを設定します。
	 * @param errorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
