package ge.sangu.tools;

import ge.sangu.excel.LectureExcelProcessor;
import ge.sangu.excel.LecturerExcelProcessor;
import ge.sangu.excel.ScheduleItemExcelProcessor;
import ge.sangu.model.AcademicLevel;
import ge.sangu.model.Lecture;
import ge.sangu.model.Lecturer;
import ge.sangu.model.ScheduleItem;
import ge.sangu.renderer.ScheduleExcellRenderer;
import ge.sangu.renderer.ScheduleHtmlRenderer;
import ge.sangu.utils.SystemParameters;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ScheduleReadTest {

	private static String DATASOURCE_EXCELL_FILE = SystemParameters.INPUT_DATA_FOLDER + "/" + "db.xls";
	private static String OUTPUT_FILE = SystemParameters.INPUT_DATA_FOLDER + "/" + "Schedule.html";
	
	
	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(int message) {
		log(String.valueOf(message));
	}
	
	public static void generateReadableSchedule() throws Exception {
		List<Lecturer> lecturers = LecturerExcelProcessor.read(DATASOURCE_EXCELL_FILE, null, "ლექტორები");
		List<Lecture> lectures = LectureExcelProcessor.read(DATASOURCE_EXCELL_FILE, null, "საგნები");
		ScheduleItemExcelProcessor proc = new ScheduleItemExcelProcessor();
		proc.setLecturers(lecturers);
		proc.setLectures(lectures);
		List<ScheduleItem> scheduleItems = proc.read(DATASOURCE_EXCELL_FILE, null, "ცხრილი");
		for (ScheduleItem scheduleItem : scheduleItems) {
			log(scheduleItem.toString());
		}
		ScheduleExcellRenderer er = new ScheduleExcellRenderer();
		er.setScheduleItems(scheduleItems);
		er.render();
	}

	private static void filterScheduleItems(List<ScheduleItem> scheduleItems) {
		for (ScheduleItem si : new ArrayList<ScheduleItem>(scheduleItems)) {
			if (si.getCourse() == null || si.getCourse().intValue() != 1 || 
			    si.getGroup() == null || !si.getGroup().equals("4") || 
			    si.getAcademicLevel() != AcademicLevel.BACHELOR) {
				scheduleItems.remove(si);
			}
		}
	}
	
	public static void generateHtmlSchedule() throws Exception {
		List<Lecturer> lecturers = LecturerExcelProcessor.read(DATASOURCE_EXCELL_FILE, null, "ლექტორები");
		List<Lecture> lectures = LectureExcelProcessor.read(DATASOURCE_EXCELL_FILE, null, "საგნები");
		ScheduleItemExcelProcessor proc = new ScheduleItemExcelProcessor();
		proc.setLecturers(lecturers);
		proc.setLectures(lectures);
		List<ScheduleItem> scheduleItems = proc.read(DATASOURCE_EXCELL_FILE, null, "ცხრილი");
		filterScheduleItems(scheduleItems);
		for (ScheduleItem scheduleItem : scheduleItems) {
			log(scheduleItem.toString());
		}
		ScheduleHtmlRenderer r = new ScheduleHtmlRenderer();
		r.setScheduleItems(scheduleItems);
		FileOutputStream fos = new FileOutputStream(new File(OUTPUT_FILE));
		fos.write(r.render().getBytes("utf-8"));
		fos.close();
	}

	public static void main(String[] args) {
		try {
//			generateReadableSchedule();
			generateHtmlSchedule();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
