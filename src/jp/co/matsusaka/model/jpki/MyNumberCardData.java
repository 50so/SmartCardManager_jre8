package jp.co.matsusaka.model.jpki;

// 4情報クラス
public class MyNumberCardData {
	// 氏名
	private String name;
	// 生年月日
	private String dateOfBirth;
	// 性別
	private String gender;
	// 住所
	private String address;
	// 氏名の代用文字使用有無（name と同じ文字数の文字列。代替文字と同じ位置に1、その他に 0 が入る。）
	private String substituteCharacterOfName;
	// 住所の代用文字使用有無（address と同じ文字数の文字列。代替文字と同じ位置に1、その他に 0 が入る。）
	private String substituteCharacterOfAddress;
	
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
	 * 生年月日を取得する(GYYYYMMDD)
	 * @return　dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	/**
	 * 性別を取得する
	 * @return　gender
	 */
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	 * 氏名の代用文字使用有無を取得する
	 * @return　substituteCharacterOfName
	 */
	public String getSubstituteCharacterOfName() {
		return substituteCharacterOfName;
	}
	public void setSubstituteCharacterOfName(String substituteCharacterOfName) {
		this.substituteCharacterOfName = substituteCharacterOfName;
	}
	
	/**
	 * 住所の代用文字使用有無を取得する
	 * @return　substituteCharacterOfAddress
	 */
	public String getSubstituteCharacterOfAddress() {
		return substituteCharacterOfAddress;
	}
	public void setSubstituteCharacterOfAddress(String substituteCharacterOfAddress) {
		this.substituteCharacterOfAddress = substituteCharacterOfAddress;
	}
	
}
