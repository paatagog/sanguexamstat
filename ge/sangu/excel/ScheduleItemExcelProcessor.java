package ge.sangu.excel;

import ge.sangu.model.AcademicLevel;
import ge.sangu.model.Lecture;
import ge.sangu.model.LectureType;
import ge.sangu.model.Lecturer;
import ge.sangu.model.ScheduleItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

public class ScheduleItemExcelProcessor {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	private Map<Integer, Lecture> lectures = new HashMap<Integer, Lecture>();
	
	private Map<Integer, Lecturer> lecturers = new HashMap<Integer, Lecturer>();
	
	public List<ScheduleItem> read(String fileName) throws Exception {
		return read(fileName, null, null);
	}

	public List<ScheduleItem> read(String fileName, Integer sheetNumber, String sheetName) throws Exception {
		List<ScheduleItem> list = new ArrayList<ScheduleItem> ();
		try {
			
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = null;
			if (sheetNumber == null && sheetName == null) {
				sheet = workbook.getSheet(0);
			} else if (sheetNumber != null) {
				sheet = workbook.getSheet(sheetNumber);
			} else {
				sheet = workbook.getSheet(sheetName);
			}
			
			int row = 1;
			ScheduleItem o = read(sheet, row++);
			
			while (row < sheet.getRows() && o != null) {
				list.add(o);
				o = read(sheet, row++);
			}
			list.add(o);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}

	public ScheduleItem read(Sheet sheet, int row) throws Exception {
		ScheduleItem o = new ScheduleItem();
		
		int i = 0;
		String id = sheet.getCell(i++, row).getContents();
		if (id == null) {
			return null;
		}
		o.setId(Integer.parseInt(id));
		String lId = sheet.getCell(i++, row).getContents();
		Lecture l = null;
		if (lId != null && !lId.equals("")) {
			l = lectures.get(Integer.parseInt(lId));
			if (l == null) {
				throw new Exception("Error reding schedule items. Lecture not found. Schedule number = " + i);
			}
		}
		o.setLecture(l);
		o.setRoom(sheet.getCell(i++, row).getContents());
		
		
		
		lId = sheet.getCell(i++, row).getContents();
		Lecturer le = null;
		if (lId != null && !lId.equals("")) {
			le = lecturers.get(Integer.parseInt(lId));
			if (le == null) {
				throw new Exception("Error reding schedule items. Lecturer not found. Schedule number = " + i);
			}
		}
		o.setLecturer(le);
		o.setLectureType(LectureType.getFromString(sheet.getCell(i++, row).getContents()));
		String time = sheet.getCell(i++, row).getContents();
		if (time != null) {
			o.setTime(sdf.parse(time));
		}
		o.setAcademicLevel(AcademicLevel.getFromString(sheet.getCell(i++, row).getContents()));
		o.setCourse(Integer.parseInt(sheet.getCell(i++, row).getContents()));
		o.setGroup(sheet.getCell(i++, row).getContents());
		o.setDay(Integer.parseInt(sheet.getCell(i++, row).getContents()));
		
		return o;
	}

	public void setLectures(List<Lecture> lectures) {
		if (lectures != null) {
			for (Lecture lecture : lectures) {
				this.lectures.put(lecture.getId(), lecture);
			}
		}
	}

	public void setLecturers(List<Lecturer> lecturers) {
		if (lecturers != null) {
			for (Lecturer lecturer : lecturers) {
				this.lecturers.put(lecturer.getId(), lecturer);
			}
		}
	}

}
