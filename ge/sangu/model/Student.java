package ge.sangu.model;

public class Student extends Person {
	
	/**
	 * ჩარიცხვის ბრძანების ნომერი
	 */
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
		return super.isEmpty() && orderNumber == null && socialStatus == null && academicLevel == null && currentSemester == null && startSemester == null
				&& faculty == null && academicStatus == null && speciality == null && localSpeciality == null && mobility == null;
	}
	
	

}
