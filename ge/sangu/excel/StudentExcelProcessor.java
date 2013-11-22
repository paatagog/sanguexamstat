package ge.sangu.excel;

import ge.sangu.model.Student;
import ge.sangu.utils.CalendarUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class StudentExcelProcessor {
	
	public static List<Student> read(String fileName) {
		return read(fileName, null, null);
	}

	public static List<Student> read(String fileName, Integer sheetNumber, String sheetName) {
		List<Student> exams = new ArrayList<Student> ();
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
			Student ex = read(sheet, row++);
			
			while (ex != null) {
				exams.add(ex);
				ex = read(sheet, row++);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return exams;
	}

	public static Student read(Sheet sheet, int row) {
		Student s = new Student();
		
		try {
			int i = 0;
			s.setSanguId(sheet.getCell(i++, row).getContents());
			s.setLastName(sheet.getCell(i++, row).getContents());			
			s.setFirstName(sheet.getCell(i++, row).getContents());			
			s.setPaternalName(sheet.getCell(i++, row).getContents());			
			s.setSex(sheet.getCell(i++, row).getContents());			
			s.setNationality(sheet.getCell(i++, row).getContents());			
			s.setCitizenship(sheet.getCell(i++, row).getContents());			
			s.setPersonalNumber(sheet.getCell(i++, row).getContents());			
			s.setIdDocumentNumber(sheet.getCell(i++, row).getContents());			
			s.setBirthDate(CalendarUtils.adjustDate(sheet.getCell(i++, row).getContents()));			
			s.setBirthPlace(sheet.getCell(i++, row).getContents());			
			s.setJuridicalAddress(sheet.getCell(i++, row).getContents());			
			s.setHomePhone(sheet.getCell(i++, row).getContents());			
			s.setMobilePhone(sheet.getCell(i++, row).getContents());			
			s.setEmail(sheet.getCell(i++, row).getContents());			
			s.setOrderNumber(sheet.getCell(i++, row).getContents());			
			s.setSocialStatus(sheet.getCell(i++, row).getContents());			
			s.setAcademicLevel(sheet.getCell(i++, row).getContents());			
			s.setCurrentSemester(sheet.getCell(i++, row).getContents());			
			s.setStartSemester(sheet.getCell(i++, row).getContents());			
			s.setFaculty(sheet.getCell(i++, row).getContents());			
			s.setAcademicStatus(sheet.getCell(i++, row).getContents());			
			s.setSpeciality(sheet.getCell(i++, row).getContents());			
			s.setLocalSpeciality(sheet.getCell(i++, row).getContents());			
			s.setMobility(sheet.getCell(i++, row).getContents());			
			if (s.isEmpty()) {
				s = null;
			}
		
		} catch (Exception e) {
			s = null;
		}
		
		return s;
	}

	public static void write(WritableSheet sheet, int row, Student ex, List<Integer> colors) throws RowsExceededException, WriteException {
		write(sheet, row, ex);
		for (Integer i : colors) {
			sheet.addCell(new Label(i, row, sheet.getCell(i, row).getContents(), getCellFormat(Colour.YELLOW2, Pattern.SOLID)));			
		}		
	}
	
	private static WritableCellFormat getCellFormat(Colour colour, Pattern pattern) throws WriteException {
	    WritableCellFormat cellFormat = new WritableCellFormat();
	    cellFormat.setBackground(colour, pattern);
	    return cellFormat;
	 }

	public static void write(WritableSheet sheet, int row, Student s) throws RowsExceededException, WriteException {
		int i = 0;
		sheet.addCell(new Label(i++, row, s.getSanguId()));
		sheet.addCell(new Label(i++, row, s.getLastName()));
		sheet.addCell(new Label(i++, row, s.getFirstName()));
		sheet.addCell(new Label(i++, row, s.getPaternalName()));
		sheet.addCell(new Label(i++, row, s.getSex()));
		sheet.addCell(new Label(i++, row, s.getNationality()));
		sheet.addCell(new Label(i++, row, s.getCitizenship()));
		sheet.addCell(new Label(i++, row, s.getPersonalNumber()));
		sheet.addCell(new Label(i++, row, s.getIdDocumentNumber()));
		sheet.addCell(new Label(i++, row, s.getBirthDate()));
		sheet.addCell(new Label(i++, row, s.getBirthPlace()));
		sheet.addCell(new Label(i++, row, s.getJuridicalAddress()));
		sheet.addCell(new Label(i++, row, s.getHomePhone()));
		sheet.addCell(new Label(i++, row, s.getMobilePhone()));
		sheet.addCell(new Label(i++, row, s.getEmail()));
		sheet.addCell(new Label(i++, row, s.getOrderNumber()));
		sheet.addCell(new Label(i++, row, s.getSocialStatus()));
		sheet.addCell(new Label(i++, row, s.getAcademicLevel()));
		sheet.addCell(new Label(i++, row, s.getCurrentSemester()));
		sheet.addCell(new Label(i++, row, s.getStartSemester()));
		sheet.addCell(new Label(i++, row, s.getFaculty()));
		sheet.addCell(new Label(i++, row, s.getAcademicStatus()));
		sheet.addCell(new Label(i++, row, s.getSpeciality()));
		sheet.addCell(new Label(i++, row, s.getLocalSpeciality()));
		sheet.addCell(new Label(i++, row, s.getMobility()));
	
	}

	public static void writeHeader(WritableSheet sheet) throws RowsExceededException, WriteException {
		int i = 0;
		sheet.addCell(new Label(i++, 0, "სტუდენტის იდენტიფიკატორი"));
		sheet.addCell(new Label(i++, 0, "გვარი"));
		sheet.addCell(new Label(i++, 0, "სახელი"));
		sheet.addCell(new Label(i++, 0, "მამის სახელი"));
		sheet.addCell(new Label(i++, 0, "სქესი"));
		sheet.addCell(new Label(i++, 0, "ეროვნება"));
		sheet.addCell(new Label(i++, 0, "მოქალაქეობა"));
		sheet.addCell(new Label(i++, 0, "პირადი ნომერი"));
		sheet.addCell(new Label(i++, 0, "პასპორტის ნომერი"));
		sheet.addCell(new Label(i++, 0, "დაბადების თარიღი"));
		sheet.addCell(new Label(i++, 0, "დაბადების ადგილი"));
		sheet.addCell(new Label(i++, 0, "იურიდიული მისამართი"));
		sheet.addCell(new Label(i++, 0, "სახლის ტელეფონი"));
		sheet.addCell(new Label(i++, 0, "მობილური ტელეფონი"));
		sheet.addCell(new Label(i++, 0, "ელექტრონული ფოსტა"));
		sheet.addCell(new Label(i++, 0, "ჩარიცხვის ბრძანება"));
		sheet.addCell(new Label(i++, 0, "სოციალური სტატუსი"));
		sheet.addCell(new Label(i++, 0, "აკადემიური საფეხური"));
		sheet.addCell(new Label(i++, 0, "მიმდინარე სემესტრი"));
		sheet.addCell(new Label(i++, 0, "ჩარიცხვის სემესტრი"));
		sheet.addCell(new Label(i++, 0, "ფაკულტეტი"));
		sheet.addCell(new Label(i++, 0, "აკადემიური სტატუსი"));
		sheet.addCell(new Label(i++, 0, "სპეციალობა"));
		sheet.addCell(new Label(i++, 0, "სპეციალიზაცია"));
		sheet.addCell(new Label(i++, 0, "მობილობა"));
	}

}
