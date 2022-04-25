package jp.co.matsusaka.model.driverlisence;

/**
 * 記載事項変更(外字)クラス
 * @author 101031
 *
 */
public class MF_DF1_EF05 {
	// 追記の有無（初回は"11"）
	private String tagA0ExsitAddition;
	
	// 外字3文字目（FFF3）（バイナリ）
	private byte[] tagA1Gaiji;

	// 外字4文字目（FFF4）（バイナリ）
	private byte[] tagA2Gaiji;
	
	// 外字5文字目（FFF5）（バイナリ）
	private byte[] tagA3Gaiji;

	// 外字6文字目（FFF6）（バイナリ）
	private byte[] tagA4Gaiji;
	
	// 外字7文字目（FFF7）（バイナリ）
	private byte[] tagA5Gaiji;
	
	// 外字3文字目ドット数
	private String tagA1GaijiDotNum;

	// 外字4文字目ドット数
	private String tagA2GaijiDotNum;
	
	// 外字5文字目ドット数
	private String tagA3GaijiDotNum;

	// 外字6文字目ドット数
	private String tagA4GaijiDotNum;
	
	// 外字7文字目ドット数
	private String tagA5GaijiDotNum;

	/**
	 * @return 追記の有無（初回は"11"）（バイナリ）
	 */
	public String getTagA0ExsitAddition() {
		return tagA0ExsitAddition;
	}

	/**
	 * @param 追記の有無（初回は"11"）（バイナリ）
	 */
	public void setTagA0ExsitAddition(String tagA0ExsitAddition) {
		this.tagA0ExsitAddition = tagA0ExsitAddition;
	}

	/**
	 * @return 外字3文字目（FFF3）（バイナリ）
	 */
	public byte[] getTagA1Gaiji() {
		return tagA1Gaiji;
	}

	/**
	 * @param 外字3文字目（FFF3）（バイナリ）
	 */
	public void setTagA1Gaiji(byte[] tagA1Gaiji) {
		this.tagA1Gaiji = tagA1Gaiji;
	}

	/**
	 * @return 外字4文字目（FFF4）（バイナリ）
	 */
	public byte[] getTagA2Gaiji() {
		return tagA2Gaiji;
	}

	/**
	 * @param 外字4文字目（FFF4）（バイナリ）
	 */
	public void setTagA2Gaiji(byte[] tagA2Gaiji) {
		this.tagA2Gaiji = tagA2Gaiji;
	}

	/**
	 * @return 外字5文字目（FFF5）（バイナリ）
	 */
	public byte[] getTagA3Gaiji() {
		return tagA3Gaiji;
	}

	/**
	 * @param 外字5文字目（FFF5）（バイナリ）
	 */
	public void setTagA3Gaiji(byte[] tagA3Gaiji) {
		this.tagA3Gaiji = tagA3Gaiji;
	}

	/**
	 * @return 外字6文字目（FFF6）（バイナリ）
	 */
	public byte[] getTagA4Gaiji() {
		return tagA4Gaiji;
	}

	/**
	 * @param 外字6文字目（FFF6）（バイナリ）
	 */
	public void setTagA4Gaiji(byte[] tagA4Gaiji) {
		this.tagA4Gaiji = tagA4Gaiji;
	}

	/**
	 * @return 外字7文字目（FFF7）（バイナリ）
	 */
	public byte[] getTagA5Gaiji() {
		return tagA5Gaiji;
	}

	/**
	 * @param 外字7文字目（FFF7）（バイナリ）
	 */
	public void setTagA5Gaiji(byte[] tagA5Gaiji) {
		this.tagA5Gaiji = tagA5Gaiji;
	}

	/**
	 * @return 外字3文字目ドット数
	 */
	public String getTagA1GaijiDotNum() {
		return tagA1GaijiDotNum;
	}

	/**
	 * @param 外字3文字目ドット数
	 */
	public void setTagA1GaijiDotNum(String tagA1GaijiDotNum) {
		this.tagA1GaijiDotNum = tagA1GaijiDotNum;
	}

	/**
	 * @return 外字4文字目ドット数
	 */
	public String getTagA2GaijiDotNum() {
		return tagA2GaijiDotNum;
	}

	/**
	 * @param 外字4文字目ドット数
	 */
	public void setTagA2GaijiDotNum(String tagA2GaijiDotNum) {
		this.tagA2GaijiDotNum = tagA2GaijiDotNum;
	}

	/**
	 * @return 外字5文字目ドット数
	 */
	public String getTagA3GaijiDotNum() {
		return tagA3GaijiDotNum;
	}

	/**
	 * @param 外字5文字目ドット数
	 */
	public void setTagA3GaijiDotNum(String tagA3GaijiDotNum) {
		this.tagA3GaijiDotNum = tagA3GaijiDotNum;
	}

	/**
	 * @return 外字6文字目ドット数
	 */
	public String getTagA4GaijiDotNum() {
		return tagA4GaijiDotNum;
	}

	/**
	 * @param 外字6文字目ドット数
	 */
	public void setTagA4GaijiDotNum(String tagA4GaijiDotNum) {
		this.tagA4GaijiDotNum = tagA4GaijiDotNum;
	}

	/**
	 * @return 外字7文字目ドット数
	 */
	public String getTagA5GaijiDotNum() {
		return tagA5GaijiDotNum;
	}

	/**
	 * @param 外字7文字目ドット数
	 */
	public void setTagA5GaijiDotNum(String tagA5GaijiDotNum) {
		this.tagA5GaijiDotNum = tagA5GaijiDotNum;
	}
}
