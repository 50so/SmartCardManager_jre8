package jp.co.matsusaka.model.driverlisence;

// 免許証データクラス
public class DliverLicenseData {
	// 氏名
	private String name;
	// 生年月日
	private String dateOfBirth;
	// 住所
	private String address;
	// 本籍
	private String honseki;
	// 暗証番号1照合可能回数
	private int remainingTimesPin1;
	// 暗証番号2照合可能回数
	private int remainingTimesPin2;
	// 写真バイナリ(JPEG)
	private byte[] image;
	
	/**
	 * 氏名を取得する
	 * @return　name
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 生年月日を取得する(GYYMMDD)
	 * @return　dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * 住所を取得する
	 * @return　address
	 */
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 本籍を取得する
	 * @return honseki
	 */
	public String getHonseki() {
		return honseki;
	}
	public void setHonseki(String honseki) {
		this.honseki = honseki;
	}
	
	/**
	 * 暗証番号2照合可能回数を取得する
	 * @return remainingTimes
	 */
	public int getRemainingTimesPin1() {
		return remainingTimesPin1;
	}
	public void setRemainingTimesPin1(int remainingTimesPin1) {
		this.remainingTimesPin1 = remainingTimesPin1;
	}
	
	/**
	 * 暗証番号1照合可能回数を取得する
	 * @return remainingTimes
	 */
	public int getRemainingTimesPin2() {
		return remainingTimesPin2;
	}
	public void setRemainingTimesPin2(int remainingTimesPin2) {
		this.remainingTimesPin2 = remainingTimesPin2;
	}
	/**
	 * 写真バイナリ(JPEG)を取得する
	 * @return image
	 */
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
