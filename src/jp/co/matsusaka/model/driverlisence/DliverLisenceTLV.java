package jp.co.matsusaka.model.driverlisence;

public class DliverLisenceTLV {
	// タグ
	String tlvTag;
	
	// 長さ(byte数）
	Integer tlvLength;
	
	// 値
	byte[] tlvValue;

	/**
	 * @return tlvTag
	 */
	public String getTlvTag() {
		return tlvTag;
	}

	/**
	 * @param tlvTag
	 */
	public void setTlvTag(String tlvTag) {
		this.tlvTag = tlvTag;
	}

	/**
	 * @return tlvLength
	 */
	public Integer getTlvLength() {
		return tlvLength;
	}

	/**
	 * @param tlvLength
	 */
	public void setTlvLength(Integer tlvLength) {
		this.tlvLength = tlvLength;
	}

	/**
	 * @return tlvValue
	 */
	public byte[] getTlvValue() {
		return tlvValue;
	}

	/**
	 * @param tlvValue
	 */
	public void setTlvValue(byte[] tlvValue) {
		this.tlvValue = tlvValue;
	}
}
