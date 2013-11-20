package ge.sangu.model;

public class Person {

	protected Integer id;
	
	protected String sanguId;
	
	protected String firstName;
	
	protected String lastName;
	
	protected String paternalName;
	
	protected String sex;
	
	protected String nationality;
	
	protected String citizenship;
	
	protected String personalNumber;
	
	protected String idDocumentNumber;
	
	protected String birthDate;
	
	protected String birthPlace;
	
	protected String juridicalAddress;
	
	protected String homePhone;
	
	protected String mobilePhone;
	
	protected String email;
	
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
	
	public boolean isEmpty() {
		return sanguId == null && firstName == null && lastName == null && paternalName == null && sex == null && nationality == null
				&& citizenship == null && personalNumber == null && idDocumentNumber == null && birthDate == null
				&& birthPlace == null && juridicalAddress == null && homePhone == null && mobilePhone == null && email == null;
	}
	
}
