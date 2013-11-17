package tools;

public class Exam {
	
	private String fullName;
	
	private String personalNumber;
	
	private String mark;
	
	private String year;
	
	private String semester;
	
	private String season;
	
	private String subject;
	
	private String code;
	
	private String creditNumber;
	
	private String group;
	
	private String program;
	
	private String lecturer;
	
	private String learningYear;
	
	private String learningLevel;
	
	private String stream;

	public boolean isEmpty() {
		return fullName == null && personalNumber == null && code == null && creditNumber == null && group == null 
				&& learningLevel == null && learningYear == null && lecturer == null && mark == null && program == null && season == null 
				&& semester == null && stream == null && subject == null && year == null;
	}
	
	public void trimFullName() {
		if (fullName != null) {
			String[] arr = fullName.split("\\s+");
			if (arr.length == 2) {
				fullName = arr[0] + " " +arr[1];
			}
		}
	}

	public void adjustFullName() {
		if (fullName != null) {
			String[] arr = fullName.split("\\s+");
			if (arr.length == 2) {
				fullName = arr[0] + " " + GeorgianNamesAdjuster.adjust(arr[1]);
			}
		}
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public String getLearningLevel() {
		return learningLevel;
	}

	public void setLearningLevel(String learningLevel) {
		this.learningLevel = learningLevel;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(String creditNumber) {
		this.creditNumber = creditNumber;
	}

	public String getLearningYear() {
		return learningYear;
	}

	public void setLearningYear(String learningYear) {
		this.learningYear = learningYear;
	}
	
	
	
}
