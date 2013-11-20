package ge.sangu.model;

public enum LectureType {
	LECTURE,
	SEMINAR,
	LABORATORY;
	
	public static LectureType getFromString(String message) {
		LectureType type = null;
		if ("ლექცია".equals(message)) {
			type =LECTURE; 
		} else if ("სემინარი".equals(message)) {
			type = SEMINAR; 
		} else if ("ლაბორატორია".equals(message)) {
			type = LABORATORY;
		}		
		return type;
	}
	
}
