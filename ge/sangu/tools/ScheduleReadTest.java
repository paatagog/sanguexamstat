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
	private static String OUTPUT_SCHEDULE_FILE_PREFIX = SystemParameters.INPUT_DATA_FOLDER + "/" + "schedule-";
	
	
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

	private static void filterScheduleItems(List<ScheduleItem> scheduleItems, Integer course, String group, AcademicLevel academicLevel) {
		for (ScheduleItem si : new ArrayList<ScheduleItem>(scheduleItems)) {
			if (si.getCourse() == null || si.getCourse().intValue() != course.intValue() || 
			    si.getGroup() == null || !si.getGroup().equals(group) || 
			    si.getAcademicLevel() != academicLevel) {
				scheduleItems.remove(si);
			}
		}
	}

	public static void generateHtmlSchedule() throws Exception {
		List<ScheduleItem> scheduleItems = readScheduleItemsFromFile();
		writeGroupScheduleToFile(scheduleItems, 1, "1", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 1, "2", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 1, "3", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 1, "4", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 2, "1", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 2, "2", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 2, "3", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 3, "1", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 3, "2", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 3, "3", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 4, "1", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 4, "2", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 4, "3", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 4, "4", AcademicLevel.BACHELOR);
		writeGroupScheduleToFile(scheduleItems, 1, "1", AcademicLevel.MAGISTRACY);
		writeGroupScheduleToFile(scheduleItems, 2, "1", AcademicLevel.MAGISTRACY);
		writeGroupScheduleToFile(scheduleItems, 1, "1", AcademicLevel.DOCTORATE);
		writeGroupScheduleToFile(scheduleItems, 1, "3", AcademicLevel.DOCTORATE);
		writeGroupScheduleToFile(scheduleItems, 2, "1", AcademicLevel.DOCTORATE);
		log("ცხრილის ფაილები წარმატებით შეიქმნა!");
	}
	
	private static List<ScheduleItem> readScheduleItemsFromFile() throws Exception {
		List<Lecturer> lecturers = LecturerExcelProcessor.read(DATASOURCE_EXCELL_FILE, null, "ლექტორები");
		List<Lecture> lectures = LectureExcelProcessor.read(DATASOURCE_EXCELL_FILE, null, "საგნები");
		ScheduleItemExcelProcessor proc = new ScheduleItemExcelProcessor();
		proc.setLecturers(lecturers);
		proc.setLectures(lectures);
		return proc.read(DATASOURCE_EXCELL_FILE, null, "ცხრილი");
	}
		
	private static void writeGroupScheduleToFile(List<ScheduleItem> scheduleItems, Integer course, String group, AcademicLevel academicLevel) throws Exception {
		List<ScheduleItem> siList = new ArrayList<ScheduleItem> (scheduleItems);
		filterScheduleItems(siList, course, group, academicLevel);
		writeScheduleItemsToFile(siList, OUTPUT_SCHEDULE_FILE_PREFIX + academicLevel + "-kursi-" + course + "-jgufi-" + group + ".inc");
	}

	private static void writeScheduleItemsToFile(List<ScheduleItem> scheduleItems, String fileName) throws Exception {
		ScheduleHtmlRenderer r = new ScheduleHtmlRenderer();
		r.setAppendHTMLHeader(false);
		r.setScheduleItems(scheduleItems);
		writeStringToFile(r.render(), fileName);
	}
	
	private static void writeStringToFile(String data, String fileName) throws Exception {
		FileOutputStream fos = new FileOutputStream(new File(fileName));
		fos.write(data.getBytes("utf-8"));
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
