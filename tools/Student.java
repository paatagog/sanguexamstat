package tools;

public class Student {
	
	private Integer id;
	
	private String sanguId;
	
	private String firstName;
	
	private String lastName;
	
	private String paternalName;
	
	private String sex;
	
	private String nationality;
	
	private String citizenship;
	
	private String personalNumber;
	
	private String idDocumentNumber;
	
	private String birthDate;
	
	private String birthPlace;
	
	private String juridicalAddress;
	
	private String homePhone;
	
	private String mobilePhone;
	
	private String email;
	
	private String orderNumber;
	
	private String socialStatus;
	
	/**
	 * ბაკალავრიატი, მაგისტრატურა, დოქტორანტურა
	 */
	private String academicLevel;
	
	private String currentSemester;
	
	private String startSemester;
	
	private String faculty;
	
	/**
	 * აქტიური, შეჩერებული, გარიცხული
	 */
	private String academicStatus;
	
	private String speciality;
	
	private String localSpeciality;
	
	private String mobility;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSanguId() {
		return sanguId;
	}

	public void setSanguId(String sanguId) {
		this.sanguId = sanguId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPaternalName() {
		return paternalName;
	}

	public void setPaternalName(String paternalName) {
		this.paternalName = paternalName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getIdDocumentNumber() {
		return idDocumentNumber;
	}

	public void setIdDocumentNumber(String idDocumentNumber) {
		this.idDocumentNumber = idDocumentNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getJuridicalAddress() {
		return juridicalAddress;
	}

	public void setJuridicalAddress(String juridicalAddress) {
		this.juridicalAddress = juridicalAddress;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(String socialStatus) {
		this.socialStatus = socialStatus;
	}

	public String getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(String academicLevel) {
		this.academicLevel = academicLevel;
	}

	public String getCurrentSemester() {
		return currentSemester;
	}

	public void setCurrentSemester(String currentSemester) {
		this.currentSemester = currentSemester;
	}

	public String getStartSemester() {
		return startSemester;
	}

	public void setStartSemester(String startSemester) {
		this.startSemester = startSemester;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getAcademicStatus() {
		return academicStatus;
	}

	public void setAcademicStatus(String academicStatus) {
		this.academicStatus = academicStatus;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getLocalSpeciality() {
		return localSpeciality;
	}

	public void setLocalSpeciality(String localSpeciality) {
		this.localSpeciality = localSpeciality;
	}

	public String getMobility() {
		return mobility;
	}

	public void setMobility(String mobility) {
		this.mobility = mobility;
	}

	public boolean isEmpty() {
		return sanguId == null && firstName == null && lastName == null && paternalName == null && sex == null && nationality == null
				&& citizenship == null && personalNumber == null && idDocumentNumber == null && birthDate == null
				&& birthPlace == null && juridicalAddress == null && homePhone == null && mobilePhone == null && email == null && orderNumber == null
				&& socialStatus == null && academicLevel == null && currentSemester == null && startSemester == null
				&& faculty == null && academicStatus == null && speciality == null && localSpeciality == null && mobility == null;
	}
	
	

}
