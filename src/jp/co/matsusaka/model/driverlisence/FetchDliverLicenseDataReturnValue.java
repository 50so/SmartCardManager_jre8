package jp.co.matsusaka.model.driverlisence;

public class FetchDliverLicenseDataReturnValue {
	
	// 免許証情報
	private DliverLicenseData dliverLicenseData;
	
	// エラーコード
	private int errorCode;

	/**
	 * 免許証情報を返します。
	 * @return dliverLicenseData
	 */
	public DliverLicenseData getDliverLicenseData() {
		return dliverLicenseData;
	}

	/**
	 * 免許証情報を設定します。
	 * @param dliverLicenseData
	 */
	public void setDliverLicenseData(DliverLicenseData dliverLicenseData) {
		this.dliverLicenseData = dliverLicenseData;
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
