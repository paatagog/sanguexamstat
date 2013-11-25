package ge.sangu.renderer;

import ge.sangu.model.ScheduleItem;
import ge.sangu.utils.CalendarUtils;
import ge.sangu.utils.SystemParameters;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ScheduleExcellRenderer {
	
	private static void log(String message) {
		System.out.println(message);
	}
	
	private static String DEFAULT_OUTPUT_FILE = SystemParameters.INPUT_DATA_FOLDER + "/" + "generated-schedule.xls";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	private List<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem> ();
	
	private String outputFileName = DEFAULT_OUTPUT_FILE;
	
	public void render() throws Exception {
		WritableWorkbook workbook = Workbook.createWorkbook(new File(outputFileName));
		WritableSheet sheet = workbook.createSheet("შედეგი", 0);
		writeHeader(sheet);
		int row = 1;
		for (ScheduleItem si : scheduleItems) {
			write(sheet, row++, si);
		}
		
		workbook.write(); 
		workbook.close();
		log("შედეგის ფაილი წარმატებით შეიქმნა");
	}
	
	private void writeHeader(WritableSheet sheet) throws Exception {
		int i = 0;
		sheet.addCell(new Label(i++, 0, "საგანი"));
		sheet.addCell(new Label(i++, 0, "აუდიტორია"));
		sheet.addCell(new Label(i++, 0, "ლექტორი"));
		sheet.addCell(new Label(i++, 0, "ფორმა"));
		sheet.addCell(new Label(i++, 0, "საათი"));
		sheet.addCell(new Label(i++, 0, "საფეხური"));
		sheet.addCell(new Label(i++, 0, "კურსი"));
		sheet.addCell(new Label(i++, 0, "ჯგუფი"));
		sheet.addCell(new Label(i++, 0, "დღე"));
	}

	private void write(WritableSheet sheet, int row, ScheduleItem si) throws Exception {
		int i = 0;
		
		if (si.getLecture() != null && si.getLecture().getName() != null) {
			sheet.addCell(new Label(i, row, si.getLecture().getName()));
		}
		i++;
		
		sheet.addCell(new Label(i++, row, si.getRoom()));

		if (si.getLecturer() != null) {
			sheet.addCell(new Label(i, row, si.getLecturer().getShortName()));
		}
		i++;
		
		if (si.getLectureType() != null) {
			sheet.addCell(new Label(i, row, si.getLectureType().getName()));
		}
		i++;
		
		sheet.addCell(new Label(i++, row, sdf.format(si.getTime())));
		
		if (si.getAcademicLevel() != null) {
			sheet.addCell(new Label(i, row, si.getAcademicLevel().getName()));
		}
		i++;

		sheet.addCell(new Label(i++, row, String.valueOf(si.getCourse())));

		sheet.addCell(new Label(i++, row, si.getGroup()));

		sheet.addCell(new Label(i++, row, CalendarUtils.getWeekDayName(si.getDay())));
	}

	public List<ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}

	public void setScheduleItems(List<ScheduleItem> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

}
