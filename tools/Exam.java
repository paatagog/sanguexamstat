package tools;

public class Exam {
	
	private String fullName;
	
	private String personalNumber;
	
	private int mark;
	
	private int year;
	
	private int semester;
	
	private String season;
	
	private String subject;
	
	private String code;
	
	private int creditNumber;
	
	private String group;
	
	private String program;
	
	private String lecturer;
	
	private int learningYear;
	
	private String learningLevel;
	
	private String stream;

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

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
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

	public int getCreditNumber() {
		return creditNumber;
	}

	public void setCreditNumber(int creditNumber) {
		this.creditNumber = creditNumber;
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

	public int getLearningYear() {
		return learningYear;
	}

	public void setLearningYear(int learningYear) {
		this.learningYear = learningYear;
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

	public boolean isEmpty() {
		return fullName == null && personalNumber == null && code == null && creditNumber == 0 && group == null 
				&& learningLevel == null && learningYear == 0 && lecturer == null && mark == 0 && program == null && season == null 
				&& semester == 0 && stream == null && subject == null && year == 0;
	}	
	
}
