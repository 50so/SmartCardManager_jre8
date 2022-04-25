package jp.co.matsusaka.model.driverlisence;

/**
 * 記載事項（本籍除く）クラス
 * @author 101031
 *
 */
public class MF_DF1_EF01 {
	// JIS X 0208 制定年番号
	private String tag11EnactmentYearNo;

	// 氏名
	private String tag12Name;

	// 呼び名(カナ)
	private String tag13NameKana;

	// 通称名
	private String tag14Tuushou;

	// 統一氏名(カナ)
	private String tag15TouitsuName;

	// 生年月日(元号YYMMDD)
	private String tag16DateOfBirth;

	// 住所
	private String tag17Address;

	// 交付年月日(元号YYMMDD)
	private String tag18IssuanceDate;

	// 照会番号
	private String tag19InquiryNo;

	// 免許証の色区分(優良・新規・その他)
	private String tag1ALicenseCollor;

	// 有効期間の末日(元号YYMMDD)
	private String tag1BEffectiveDate;

	// 免許の条件１
	private String tag1CLicenseConditions;

	// 免許の条件２
	private String tag1DLicenseConditions;

	// 免許の条件３
	private String tag1ELicenseConditions;

	// 免許の条件４
	private String tag1FLicenseConditions;

	// 公安委員会名
	private String tag20AddressKoanIinkaiName;

	// 免許証の番号
	private String tag21LicenseNo;

	// 免許の年月日(二・小・原)(元号YYMMDD)
	private String tag22NiKoGenLicenseAcquisitionDate;

	// 免許の年月日(他)(元号YYMMDD)
	private String tag23TaLicenseAcquisitionDate;

	// 免許の年月日(二種)(元号YYMMDD)
	private String tag24NishuLicenseAcquisitionDate;

	// 免許の年月日(大型)(元号YYMMDD)
	private String tag25OogataLicenseAcquisitionDate;

	// 免許の年月日(普通)(元号YYMMDD)
	private String tag26FutsuLicenseAcquisitionDate;

	// 免許の年月日(大特)(元号YYMMDD)
	private String tag27OoTokuLicenseAcquisitionDate;

	// 免許の年月日(大自二)(元号YYMMDD)
	private String tag28OoJiNiLicenseAcquisitionDate;

	// 免許の年月日(普自二)(元号YYMMDD)
	private String tag29FuJiNiLicenseAcquisitionDate;

	// 免許の年月日(小特)(元号YYMMDD)
	private String tag2AKoTokuLicenseAcquisitionDate;

	// 免許の年月日(原付)(元号YYMMDD)
	private String tag2BGentsukiLicenseAcquisitionDate;

	// 免許の年月日(け引)(元号YYMMDD)
	private String tag2CKeInLicenseAcquisitionDate;

	// 免許の年月日(大二)(元号YYMMDD)
	private String tag2DOoNiLicenseAcquisitionDate;

	// 免許の年月日(普二)(元号YYMMDD)
	private String tag2EFuNiLicenseAcquisitionDate;

	// 免許の年月日(大特二)(元号YYMMDD)
	private String tag2FOoTokuNiLicenseAcquisitionDate;

	// 免許の年月日(け引二)(元号YYMMDD)
	private String tag30KeInNiLicenseAcquisitionDate;

	// 免許の年月日(中型)(元号YYMMDD)
	private String tag31ChugataLicenseAcquisitionDate;

	// 免許の年月日(中二)(元号YYMMDD)
	private String tag32ChuNiLicenseAcquisitionDate;

	// 免許の年月日(準中型)(元号YYMMDD)
	private String tag33JunChugataLicenseAcquisitionDate;

	/**
	 * @return JIS X 0208 制定年番号
	 */
	public String getTag11EnactmentYearNo() {
		return tag11EnactmentYearNo;
	}

	/**
	 * @param JIS X 0208 制定年番号
	 */
	public void setTag11EnactmentYearNo(String tag11EnactmentYearNo) {
		this.tag11EnactmentYearNo = tag11EnactmentYearNo;
	}

	/**
	 * @return 氏名
	 */
	public String getTag12Name() {
		return tag12Name;
	}

	/**
	 * @param 氏名
	 */
	public void setTag12Name(String tag12Name) {
		this.tag12Name = tag12Name;
	}

	/**
	 * @return 呼び名(カナ)
	 */
	public String getTag13NameKana() {
		return tag13NameKana;
	}

	/**
	 * @param 呼び名(カナ)
	 */
	public void setTag13NameKana(String tag13NameKana) {
		this.tag13NameKana = tag13NameKana;
	}

	/**
	 * @return 通称名
	 */
	public String getTag14Tuushou() {
		return tag14Tuushou;
	}

	/**
	 * @param 通称名
	 */
	public void setTag14Tuushou(String tag14Tuushou) {
		this.tag14Tuushou = tag14Tuushou;
	}

	/**
	 * @return 統一氏名(カナ)
	 */
	public String getTag15TouitsuName() {
		return tag15TouitsuName;
	}

	/**
	 * @param 統一氏名(カナ)
	 */
	public void setTag15TouitsuName(String tag15TouitsuName) {
		this.tag15TouitsuName = tag15TouitsuName;
	}

	/**
	 * @return 生年月日(元号YYMMDD)
	 */
	public String getTag16DateOfBirth() {
		return tag16DateOfBirth;
	}

	/**
	 * @param 生年月日(元号YYMMDD)
	 */
	public void setTag16DateOfBirth(String tag16DateOfBirth) {
		this.tag16DateOfBirth = tag16DateOfBirth;
	}

	/**
	 * @return 住所
	 */
	public String getTag17Address() {
		return tag17Address;
	}

	/**
	 * @param 住所
	 */
	public void setTag17Address(String tag17Address) {
		this.tag17Address = tag17Address;
	}

	/**
	 * @return 交付年月日(元号YYMMDD)
	 */
	public String getTag18IssuanceDate() {
		return tag18IssuanceDate;
	}

	/**
	 * @param 交付年月日(元号YYMMDD)
	 */
	public void setTag18IssuanceDate(String tag18IssuanceDate) {
		this.tag18IssuanceDate = tag18IssuanceDate;
	}

	/**
	 * @return 照会番号
	 */
	public String getTag19InquiryNo() {
		return tag19InquiryNo;
	}

	/**
	 * @param 照会番号
	 */
	public void setTag19InquiryNo(String tag19InquiryNo) {
		this.tag19InquiryNo = tag19InquiryNo;
	}

	/**
	 * @return 免許証の色区分(優良・新規・その他)
	 */
	public String getTag1ALicenseCollor() {
		return tag1ALicenseCollor;
	}

	/**
	 * @param 免許証の色区分(優良・新規・その他)
	 */
	public void setTag1ALicenseCollor(String tag1aLicenseCollor) {
		tag1ALicenseCollor = tag1aLicenseCollor;
	}

	/**
	 * @return 有効期間の末日(元号YYMMDD)
	 */
	public String getTag1BEffectiveDate() {
		return tag1BEffectiveDate;
	}

	/**
	 * @param 有効期間の末日(元号YYMMDD)
	 */
	public void setTag1BEffectiveDate(String tag1bEffectiveDate) {
		tag1BEffectiveDate = tag1bEffectiveDate;
	}

	/**
	 * @return 免許の条件１
	 */
	public String getTag1CLicenseConditions() {
		return tag1CLicenseConditions;
	}

	/**
	 * @param 免許の条件１
	 */
	public void setTag1CLicenseConditions(String tag1cLicenseConditions) {
		tag1CLicenseConditions = tag1cLicenseConditions;
	}

	/**
	 * @return 免許の条件２
	 */
	public String getTag1DLicenseConditions() {
		return tag1DLicenseConditions;
	}

	/**
	 * @param 免許の条件２
	 */
	public void setTag1DLicenseConditions(String tag1dLicenseConditions) {
		tag1DLicenseConditions = tag1dLicenseConditions;
	}

	/**
	 * @return 免許の条件３
	 */
	public String getTag1ELicenseConditions() {
		return tag1ELicenseConditions;
	}

	/**
	 * @param 免許の条件３
	 */
	public void setTag1ELicenseConditions(String tag1eLicenseConditions) {
		tag1ELicenseConditions = tag1eLicenseConditions;
	}

	/**
	 * @return 免許の条件４
	 */
	public String getTag1FLicenseConditions() {
		return tag1FLicenseConditions;
	}

	/**
	 * @param 免許の条件４
	 */
	public void setTag1FLicenseConditions(String tag1fLicenseConditions) {
		tag1FLicenseConditions = tag1fLicenseConditions;
	}

	/**
	 * @return 公安委員会名
	 */
	public String getTag20AddressKoanIinkaiName() {
		return tag20AddressKoanIinkaiName;
	}

	/**
	 * @param 公安委員会名
	 */
	public void setTag20AddressKoanIinkaiName(String tag20AddressKoanIinkaiName) {
		this.tag20AddressKoanIinkaiName = tag20AddressKoanIinkaiName;
	}

	/**
	 * @return 免許証の番号
	 */
	public String getTag21LicenseNo() {
		return tag21LicenseNo;
	}

	/**
	 * @param 免許証の番号
	 */
	public void setTag21LicenseNo(String tag21LicenseNo) {
		this.tag21LicenseNo = tag21LicenseNo;
	}

	/**
	 * @return 免許の年月日(二・小・原)(元号YYMMDD)
	 */
	public String getTag22NiKoGenLicenseAcquisitionDate() {
		return tag22NiKoGenLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(二・小・原)(元号YYMMDD)
	 */
	public void setTag22NiKoGenLicenseAcquisitionDate(String tag22NiKoGenLicenseAcquisitionDate) {
		this.tag22NiKoGenLicenseAcquisitionDate = tag22NiKoGenLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(他)(元号YYMMDD)
	 */
	public String getTag23TaLicenseAcquisitionDate() {
		return tag23TaLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(他)(元号YYMMDD)
	 */
	public void setTag23TaLicenseAcquisitionDate(String tag23TaLicenseAcquisitionDate) {
		this.tag23TaLicenseAcquisitionDate = tag23TaLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(二種)(元号YYMMDD)
	 */
	public String getTag24NishuLicenseAcquisitionDate() {
		return tag24NishuLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(二種)(元号YYMMDD)
	 */
	public void setTag24NishuLicenseAcquisitionDate(String tag24NishuLicenseAcquisitionDate) {
		this.tag24NishuLicenseAcquisitionDate = tag24NishuLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(大型)(元号YYMMDD)
	 */
	public String getTag25OogataLicenseAcquisitionDate() {
		return tag25OogataLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(大型)(元号YYMMDD)
	 */
	public void setTag25OogataLicenseAcquisitionDate(String tag25OogataLicenseAcquisitionDate) {
		this.tag25OogataLicenseAcquisitionDate = tag25OogataLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(普通)(元号YYMMDD)
	 */
	public String getTag26FutsuLicenseAcquisitionDate() {
		return tag26FutsuLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(普通)(元号YYMMDD)
	 */
	public void setTag26FutsuLicenseAcquisitionDate(String tag26FutsuLicenseAcquisitionDate) {
		this.tag26FutsuLicenseAcquisitionDate = tag26FutsuLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(大特)(元号YYMMDD)
	 */
	public String getTag27OoTokuLicenseAcquisitionDate() {
		return tag27OoTokuLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(大特)(元号YYMMDD)
	 */
	public void setTag27OoTokuLicenseAcquisitionDate(String tag27OoTokuLicenseAcquisitionDate) {
		this.tag27OoTokuLicenseAcquisitionDate = tag27OoTokuLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(大自二)(元号YYMMDD)
	 */
	public String getTag28OoJiNiLicenseAcquisitionDate() {
		return tag28OoJiNiLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(大自二)(元号YYMMDD)
	 */
	public void setTag28OoJiNiLicenseAcquisitionDate(String tag28OoJiNiLicenseAcquisitionDate) {
		this.tag28OoJiNiLicenseAcquisitionDate = tag28OoJiNiLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(普自二)(元号YYMMDD)
	 */
	public String getTag29FuJiNiLicenseAcquisitionDate() {
		return tag29FuJiNiLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(普自二)(元号YYMMDD)
	 */
	public void setTag29FuJiNiLicenseAcquisitionDate(String tag29FuJiNiLicenseAcquisitionDate) {
		this.tag29FuJiNiLicenseAcquisitionDate = tag29FuJiNiLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(小特)(元号YYMMDD)
	 */
	public String getTag2AKoTokuLicenseAcquisitionDate() {
		return tag2AKoTokuLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(小特)(元号YYMMDD)
	 */
	public void setTag2AKoTokuLicenseAcquisitionDate(String tag2aKoTokuLicenseAcquisitionDate) {
		tag2AKoTokuLicenseAcquisitionDate = tag2aKoTokuLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(原付)(元号YYMMDD)
	 */
	public String getTag2BGentsukiLicenseAcquisitionDate() {
		return tag2BGentsukiLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(原付)(元号YYMMDD)
	 */
	public void setTag2BGentsukiLicenseAcquisitionDate(String tag2bGentsukiLicenseAcquisitionDate) {
		tag2BGentsukiLicenseAcquisitionDate = tag2bGentsukiLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(け引)(元号YYMMDD)
	 */
	public String getTag2CKeInLicenseAcquisitionDate() {
		return tag2CKeInLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(け引)(元号YYMMDD)
	 */
	public void setTag2CKeInLicenseAcquisitionDate(String tag2cKeInLicenseAcquisitionDate) {
		tag2CKeInLicenseAcquisitionDate = tag2cKeInLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(大二)(元号YYMMDD)
	 */
	public String getTag2DOoNiLicenseAcquisitionDate() {
		return tag2DOoNiLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(大二)(元号YYMMDD)
	 */
	public void setTag2DOoNiLicenseAcquisitionDate(String tag2dOoNiLicenseAcquisitionDate) {
		tag2DOoNiLicenseAcquisitionDate = tag2dOoNiLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(普二)(元号YYMMDD)
	 */
	public String getTag2EFuNiLicenseAcquisitionDate() {
		return tag2EFuNiLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(普二)(元号YYMMDD)
	 */
	public void setTag2EFuNiLicenseAcquisitionDate(String tag2eFuNiLicenseAcquisitionDate) {
		tag2EFuNiLicenseAcquisitionDate = tag2eFuNiLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(大特二)(元号YYMMDD)
	 */
	public String getTag2FOoTokuNiLicenseAcquisitionDate() {
		return tag2FOoTokuNiLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(大特二)(元号YYMMDD)
	 */
	public void setTag2FOoTokuNiLicenseAcquisitionDate(String tag2fOoTokuNiLicenseAcquisitionDate) {
		tag2FOoTokuNiLicenseAcquisitionDate = tag2fOoTokuNiLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(け引二)(元号YYMMDD)
	 */
	public String getTag30KeInNiLicenseAcquisitionDate() {
		return tag30KeInNiLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(け引二)(元号YYMMDD)
	 */
	public void setTag30KeInNiLicenseAcquisitionDate(String tag30KeInNiLicenseAcquisitionDate) {
		this.tag30KeInNiLicenseAcquisitionDate = tag30KeInNiLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(中型)(元号YYMMDD)
	 */
	public String getTag31ChugataLicenseAcquisitionDate() {
		return tag31ChugataLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(中型)(元号YYMMDD)
	 */
	public void setTag31ChugataLicenseAcquisitionDate(String tag31ChugataLicenseAcquisitionDate) {
		this.tag31ChugataLicenseAcquisitionDate = tag31ChugataLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(中二)(元号YYMMDD)
	 */
	public String getTag32ChuNiLicenseAcquisitionDate() {
		return tag32ChuNiLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(中二)(元号YYMMDD)
	 */
	public void setTag32ChuNiLicenseAcquisitionDate(String tag32ChuNiLicenseAcquisitionDate) {
		this.tag32ChuNiLicenseAcquisitionDate = tag32ChuNiLicenseAcquisitionDate;
	}

	/**
	 * @return 免許の年月日(準中型)(元号YYMMDD)
	 */
	public String getTag33JunChugataLicenseAcquisitionDate() {
		return tag33JunChugataLicenseAcquisitionDate;
	}

	/**
	 * @param 免許の年月日(準中型)(元号YYMMDD)
	 */
	public void setTag33JunChugataLicenseAcquisitionDate(String tag33JunChugataLicenseAcquisitionDate) {
		this.tag33JunChugataLicenseAcquisitionDate = tag33JunChugataLicenseAcquisitionDate;
	}
}
