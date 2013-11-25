package ge.sangu.model;

public class Lecturer extends Person {
	
	private String academicDegree;

	public String getShortName() {
		StringBuilder sb = new StringBuilder();
		if (firstName != null && firstName.length() != 0) {
			sb.append(firstName.charAt(0)).append(". ");
		}
		if (lastName != null) {
			sb.append(lastName);
		}
		
		return sb.toString();
	}
	
	public String getAcademicName() {
		StringBuilder sb = new StringBuilder();
		if (academicDegree != null) {
			sb.append(academicDegree).append(" ");
		}
		sb.append(getShortName());
		return sb.toString();
	}

	public String getAcademicDegree() {
		return academicDegree;
	}

	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}
	
}
