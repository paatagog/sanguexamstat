package ge.sangu.model;

public enum AcademicLevel {
	BACHELOR,
	MAGISTRACY,
	DOCTORATE;

	public static AcademicLevel getFromString(String message) {
		AcademicLevel level = null;
		if ("ბაკალავრიატი".equals(message)) {
			level =BACHELOR; 
		} else if ("მაგისტრატურა".equals(message)) {
			level = MAGISTRACY; 
		} else if ("დოქტორანტურა".equals(message)) {
			level = DOCTORATE;
		}		
		return level;
	}
}
