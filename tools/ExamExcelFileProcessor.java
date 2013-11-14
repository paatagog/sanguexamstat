package tools;

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

public class ExamExcelFileProcessor {

	public static List<Exam> read(String fileName) {
		List<Exam> exams = new ArrayList<Exam> ();
		try {
			
			Workbook workbook = Workbook.getWorkbook(new File(fileName));
			Sheet sheet = workbook.getSheet(0);
			
			int row = 1;
			Exam ex = readExam(sheet, row++);
			
			while (ex != null) {
				exams.add(ex);
				ex = readExam(sheet, row++);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return exams;
	}
	
	public static Exam readExam(Sheet sheet, int row) {
		Exam ex = new Exam();
		
		try {		
			ex.setPersonalNumber(sheet.getCell(0, row).getContents());
			ex.setFullName(sheet.getCell(1, row).getContents());
			ex.setMark(sheet.getCell(2, row).getContents());
			ex.setYear(sheet.getCell(3, row).getContents());
			ex.setSemester(sheet.getCell(7, row).getContents());
			ex.setSeason(sheet.getCell(4, row).getContents());
			ex.setSubject(sheet.getCell(5, row).getContents());
			ex.setCode(sheet.getCell(6, row).getContents());
			ex.setCreditNumber(sheet.getCell(8, row).getContents());
			ex.setGroup(sheet.getCell(9, row).getContents());
			ex.setProgram(sheet.getCell(10, row).getContents());
			ex.setLecturer(sheet.getCell(11, row).getContents());
			ex.setLearningYear(sheet.getCell(12, row).getContents());
			ex.setLearningLevel(sheet.getCell(13, row).getContents());
			ex.setStream(sheet.getCell(14, row).getContents());
			
			if (ex.isEmpty()) {
				ex = null;
			}
		
		} catch (Exception e) {
			ex = null;
		}
		
		return ex;
	}
	
	public static void writeExam(WritableSheet sheet, int row, Exam ex, List<Integer> colors) throws RowsExceededException, WriteException {
		writeExam(sheet, row, ex);
		for (Integer i : colors) {
			sheet.addCell(new Label(i, row, sheet.getCell(i, row).getContents(), getCellFormat(Colour.YELLOW2, Pattern.SOLID)));			
		}		
	}
	
	private static WritableCellFormat getCellFormat(Colour colour, Pattern pattern) throws WriteException {
//	    WritableFont cellFont = new WritableFont(WritableFont.TIMES, 16);
//	    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
	    WritableCellFormat cellFormat = new WritableCellFormat();
	    cellFormat.setBackground(colour, pattern);
	    return cellFormat;
	 }

	public static void writeExam(WritableSheet sheet, int row, Exam ex) throws RowsExceededException, WriteException {
		sheet.addCell(new Label(0, row, ex.getPersonalNumber()));
		sheet.addCell(new Label(1, row, ex.getFullName()));
		sheet.addCell(new Label(2, row, ex.getMark()));
		sheet.addCell(new Label(3, row, ex.getYear()));
		sheet.addCell(new Label(4, row, ex.getSeason()));
		sheet.addCell(new Label(5, row, ex.getSubject()));
		sheet.addCell(new Label(6, row, ex.getCode()));
		sheet.addCell(new Label(7, row, ex.getSemester()));
		sheet.addCell(new Label(8, row, ex.getCreditNumber()));
		sheet.addCell(new Label(9, row, ex.getGroup()));
		sheet.addCell(new Label(10, row, ex.getProgram()));
		sheet.addCell(new Label(11, row, ex.getLecturer()));
		sheet.addCell(new Label(12, row, ex.getLearningYear()));
		sheet.addCell(new Label(13, row, ex.getLearningLevel()));
		sheet.addCell(new Label(14, row, ex.getStream()));
	}

	public static void writeFileHeader(WritableSheet sheet) throws RowsExceededException, WriteException {
		sheet.addCell(new Label(0, 0, "პირადი ნომერი"));
		sheet.addCell(new Label(1, 0, "გვარი, სახელი"));
		sheet.addCell(new Label(2, 0, "ქულა"));
		sheet.addCell(new Label(3, 0, "წელიწადი"));
		sheet.addCell(new Label(4, 0, "სეზონი"));
		sheet.addCell(new Label(5, 0, "საგანი"));
		sheet.addCell(new Label(6, 0, "კოდი"));
		sheet.addCell(new Label(7, 0, "სემესტრი"));
		sheet.addCell(new Label(8, 0, "კრედიტი"));
		sheet.addCell(new Label(9, 0, "ჯგუფი"));
		sheet.addCell(new Label(10, 0, "პროგრამა"));
		sheet.addCell(new Label(11, 0, "ლექტორი"));
		sheet.addCell(new Label(12, 0, "კურსი"));
		sheet.addCell(new Label(13, 0, "სწავლების საფეხური"));
		sheet.addCell(new Label(14, 0, "ნაკადი"));
	}
	

}
