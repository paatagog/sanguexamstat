package ge.sangu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CalendarUtils {
	
	public static SimpleDateFormat tikoFormat = new SimpleDateFormat("MM/dd/yy");
	public static SimpleDateFormat kaxaFormat = new SimpleDateFormat("dd.MM.yyyy");
	
	
	
	
	public static String[] weekDayNames = {"ორშაბათი", "სამშაბათი", "ოთხშაბათი", "ხუთშაბათი", "პარასკევი", "შაბათი", "კვირა"}; 
	
	public static String getWeekDayName(int weekDayNumber) {
		return weekDayNames[weekDayNumber - 1];
	}
	
	public static String adjustDate(String date) throws ParseException {
		String adjusted = date;
		if (date != null) {
			if (date.contains("/")) {
				adjusted = kaxaFormat.format((tikoFormat.parse(date)));				
			}
		}
		return adjusted;
	}

}
