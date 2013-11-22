package ge.sangu.model;

public class Tax {
	
	private	String id;
	
	/**
	 * ბაკალავრიატი, მაგისტრატურა, დოქტორანტურა
	 */
	private String step;
	
	private String amount;
	
	private String faculty;
	
	private String year;
	
	/**
	 * სეზონი
	 */
	private String period;
	
	private String otherAmount;
	
	private String semester;
	
	private String currentSemester;
	
	private String studentFirstName;
	
	private String studentLastName;
	
	private String studentId;
	
	private String specialization;
	
	/**
	 * აქტიური, შეჩერებული, გარიცხული, დამთავრებული
	 */
	private String status;
	
	private String studentFullName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getOtherAmount() {
		return otherAmount;
	}

	public void setOtherAmount(String otherAmount) {
		this.otherAmount = otherAmount;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getCurrentSemester() {
		return currentSemester;
	}

	public void setCurrentSemester(String currentSemester) {
		this.currentSemester = currentSemester;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStudentFullName() {
		return studentFullName;
	}

	public void setStudentFullName(String studentFullName) {
		this.studentFullName = studentFullName;
	}
	
	public boolean isEmpty() {
		return amount == null && currentSemester == null && faculty == null && id == null && otherAmount == null
				&& period == null && semester == null && specialization == null && status == null && step == null && studentFirstName == null 
				&& studentLastName == null && studentFullName == null && studentId == null && id == null;
	}

}
