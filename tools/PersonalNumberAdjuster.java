package tools;

public class PersonalNumberAdjuster {

	public static String adjust(String pn) {
		if (pn == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(pn);
		while (sb.length() < 11) {
			sb.insert(0, '0');
		}
		return sb.toString();		
	}

}
