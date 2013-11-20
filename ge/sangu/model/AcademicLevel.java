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

	public String getName() {
		switch (this) {
		case BACHELOR:
			return "ბაკალავრიატი";
		case MAGISTRACY:
			return "მაგისტრატურა";
		case DOCTORATE:
			return "დოქტორანტურა";
		}
		return null;
	}
	
}
