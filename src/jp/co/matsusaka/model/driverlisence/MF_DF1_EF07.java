package jp.co.matsusaka.model.driverlisence;

/**
 * 電子署名クラス
 * @author 101031
 *
 */
public class MF_DF1_EF07 {
	// 電子署名
	private byte[] tagB1ESign;
	
	// シリアル番号
	private String tagB2SerialNo;
	
	// （RFU）
	private String tagB3RFU;
	
	// 発行者名
	private String tagB4PublisherName;
	
	// 主体者名
	private String tagB5MainPersonName;
	
	// 主体者識別子
	private byte[] tagB6MainPersonIdentifier;

	/**
	 * @return 電子署名
	 */
	public byte[] getTagB1ESign() {
		return tagB1ESign;
	}

	/**
	 * @param 電子署名
	 */
	public void setTagB1ESign(byte[] tagB1ESign) {
		this.tagB1ESign = tagB1ESign;
	}

	/**
	 * @return シリアル番号
	 */
	public String getTagB2SerialNo() {
		return tagB2SerialNo;
	}

	/**
	 * @param シリアル番号
	 */
	public void setTagB2SerialNo(String tagB2SerialNo) {
		this.tagB2SerialNo = tagB2SerialNo;
	}

	/**
	 * @return （RFU）
	 */
	public String getTagB3RFU() {
		return tagB3RFU;
	}

	/**
	 * @param （RFU）
	 */
	public void setTagB3RFU(String tagB3RFU) {
		this.tagB3RFU = tagB3RFU;
	}

	/**
	 * @return 発行者名
	 */
	public String getTagB4PublisherName() {
		return tagB4PublisherName;
	}

	/**
	 * @param 発行者名
	 */
	public void setTagB4PublisherName(String tagB4PublisherName) {
		this.tagB4PublisherName = tagB4PublisherName;
	}

	/**
	 * @return 主体者名
	 */
	public String getTagB5MainPersonName() {
		return tagB5MainPersonName;
	}

	/**
	 * @param 主体者名
	 */
	public void setTagB5MainPersonName(String tagB5MainPersonName) {
		this.tagB5MainPersonName = tagB5MainPersonName;
	}

	/**
	 * @return 主体者識別子
	 */
	public byte[] getTagB6MainPersonIdentifier() {
		return tagB6MainPersonIdentifier;
	}

	/**
	 * @param 主体者識別子
	 */
	public void setTagB6MainPersonIdentifier(byte[] tagB6MainPersonIdentifier) {
		this.tagB6MainPersonIdentifier = tagB6MainPersonIdentifier;
	}
}
