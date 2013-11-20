package ge.sangu.utils;

public class CalendarUtils {
	
	public static String[] weekDayNames = {"ორშაბათი", "სამშაბათი", "ოთხშაბათი", "ხუთშაბათი", "პარასკევი", "შაბათი", "კვირა"}; 
	
	public static String getWeekDayName(int weekDayNumber) {
		return weekDayNames[weekDayNumber - 1];
	}

}
