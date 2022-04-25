package jp.co.matsusaka.model.driverlisence;

/**
 * 外字クラス
 * ３字以上の外字を有する場合又はDF1/EF03のファイル容量を超過する場合は、以降の外字は、DF1/EF05に記録すること。
 * @author 101031
 *
 */
public class MF_DF1_EF03 {
	// 外字1文字目（FFF1）（バイナリ）
	private byte[] tag48Gaiji;

	// 外字2文字目（FFF2）（バイナリ）
	private byte[] tag49Gaiji;
	
	// 外字1文字目ドット数
	private String tag48GaijiDotNum;

	// 外字2文字目ドット数
	private String tag49GaijiDotNum;

	/**
	 * @return 外字1文字目（FFF1）（バイナリ）
	 */
	public byte[] getTag48Gaiji() {
		return tag48Gaiji;
	}

	/**
	 * @param 外字1文字目（FFF1）（バイナリ）
	 */
	public void setTag48Gaiji(byte[] tag48Gaiji) {
		this.tag48Gaiji = tag48Gaiji;
	}

	/**
	 * @return 外字2文字目（FFF2）（バイナリ）
	 */
	public byte[] getTag49Gaiji() {
		return tag49Gaiji;
	}

	/**
	 * @param 外字2文字目（FFF2）（バイナリ）
	 */
	public void setTag49Gaiji(byte[] tag49Gaiji) {
		this.tag49Gaiji = tag49Gaiji;
	}

	/**
	 * @return 外字1文字目ドット数
	 */
	public String getTag48GaijiDotNum() {
		return tag48GaijiDotNum;
	}

	/**
	 * @param 外字1文字目ドット数
	 */
	public void setTag48GaijiDotNum(String tag48GaijiDotNum) {
		this.tag48GaijiDotNum = tag48GaijiDotNum;
	}

	/**
	 * @return 外字2文字目ドット数
	 */
	public String getTag49GaijiDotNum() {
		return tag49GaijiDotNum;
	}

	/**
	 * @param 外字2文字目ドット数
	 */
	public void setTag49GaijiDotNum(String tag49GaijiDotNum) {
		this.tag49GaijiDotNum = tag49GaijiDotNum;
	}
}
