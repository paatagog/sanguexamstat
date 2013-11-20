package ge.sangu.model;

public class Lecturer extends Person {

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
	
}
