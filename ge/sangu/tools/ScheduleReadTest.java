package ge.sangu.tools;

import ge.sangu.excel.LectureExcelProcessor;
import ge.sangu.excel.LecturerExcelProcessor;
import ge.sangu.excel.ScheduleItemExcelProcessor;
import ge.sangu.model.Lecture;
import ge.sangu.model.Lecturer;
import ge.sangu.model.ScheduleItem;
import ge.sangu.utils.SystemParameters;

import java.util.List;

public class ScheduleReadTest {

	private static String DATASOURCE_EXCELL_FILE = SystemParameters.INPUT_DATA_FOLDER + "/" + "db.xls";
	
	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(int message) {
		log(String.valueOf(message));
	}

	public static void main(String[] args) {
		try {
			List<Lecturer> lecturers = LecturerExcelProcessor.read(DATASOURCE_EXCELL_FILE, null, "ლექტორები");
			List<Lecture> lectures = LectureExcelProcessor.read(DATASOURCE_EXCELL_FILE, null, "საგნები");
			ScheduleItemExcelProcessor proc = new ScheduleItemExcelProcessor();
			proc.setLecturers(lecturers);
			proc.setLectures(lectures);
			List<ScheduleItem> scheduleItems = proc.read(DATASOURCE_EXCELL_FILE, null, "ცხრილი");
			for (ScheduleItem scheduleItem : scheduleItems) {
				log(scheduleItem.toString());
			}
			
		} catch (Exception ex) {
			
		}
	}
}
