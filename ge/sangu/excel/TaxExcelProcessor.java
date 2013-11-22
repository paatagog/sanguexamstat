package ge.sangu.excel;

import ge.sangu.model.Tax;
import ge.sangu.utils.CalendarUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.Pattern;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class TaxExcelProcessor {
	public static List<Tax> read(String fileName) {
		return read(fileName, null, null);
	}

	public static List<Tax> read(String fileName, Integer sheetNumber, String sheetName) {
		List<Tax> list = new ArrayList<Tax> ();
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
			Tax o = read(sheet, row++);
			
			while (o != null) {
				list.add(o);
				o = read(sheet, row++);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return list;
	}

	public static Tax read(Sheet sheet, int row) {
		Tax o = new Tax();
		
		try {
			int i = 0;
			o.setId(sheet.getCell(i++, row).getContents());
			o.setStep(sheet.getCell(i++, row).getContents());			
			o.setAmount(sheet.getCell(i++, row).getContents());			
			o.setFaculty(sheet.getCell(i++, row).getContents());			
			o.setYear(sheet.getCell(i++, row).getContents());			
			o.setPeriod(sheet.getCell(i++, row).getContents());			
			o.setOtherAmount(sheet.getCell(i++, row).getContents());			
			o.setSemester(sheet.getCell(i++, row).getContents());			
			o.setCurrentSemester(sheet.getCell(i++, row).getContents());			
			o.setStudentFirstName(CalendarUtils.adjustDate(sheet.getCell(i++, row).getContents()));			
			o.setStudentLastName(sheet.getCell(i++, row).getContents());			
			o.setStudentId(sheet.getCell(i++, row).getContents());			
			o.setSpecialization(sheet.getCell(i++, row).getContents());			
			o.setStatus(sheet.getCell(i++, row).getContents());			
			o.setStudentFullName(sheet.getCell(i++, row).getContents());			
			if (o.isEmpty()) {
				o = null;
			}
		
		} catch (Exception e) {
			o = null;
		}
		
		return o;
	}

	public static void write(WritableSheet sheet, int row, Tax ex, List<Integer> colors, Boolean removed) throws RowsExceededException, WriteException {
		write(sheet, row, ex);
		WritableCellFormat format = getCellFormat(Colour.YELLOW2, Pattern.SOLID);
		if (Boolean.TRUE.equals(removed)) {
			format = getCellFormat(Colour.RED, Pattern.SOLID);
		} else if (Boolean.FALSE.equals(removed)) {
			format = getCellFormat(Colour.GREEN, Pattern.SOLID);
		}
		for (Integer i : colors) {
			sheet.addCell(new Label(i, row, sheet.getCell(i, row).getContents(), format));			
		}		
	}
	
	private static WritableCellFormat getCellFormat(Colour colour, Pattern pattern) throws WriteException {
	    WritableCellFormat cellFormat = new WritableCellFormat();
	    cellFormat.setBackground(colour, pattern);
	    return cellFormat;
	 }

	public static void write(WritableSheet sheet, int row, Tax s) throws RowsExceededException, WriteException {
		int i = 0;
		sheet.addCell(new Label(i++, row, s.getId()));
		sheet.addCell(new Label(i++, row, s.getStep()));
		sheet.addCell(new Label(i++, row, s.getAmount()));
		sheet.addCell(new Label(i++, row, s.getFaculty()));
		sheet.addCell(new Label(i++, row, s.getYear()));
		sheet.addCell(new Label(i++, row, s.getPeriod()));
		sheet.addCell(new Label(i++, row, s.getOtherAmount()));
		sheet.addCell(new Label(i++, row, s.getSemester()));
		sheet.addCell(new Label(i++, row, s.getCurrentSemester()));
		sheet.addCell(new Label(i++, row, s.getStudentFirstName()));
		sheet.addCell(new Label(i++, row, s.getStudentLastName()));
		sheet.addCell(new Label(i++, row, s.getStudentId()));
		sheet.addCell(new Label(i++, row, s.getSpecialization()));
		sheet.addCell(new Label(i++, row, s.getStatus()));
		sheet.addCell(new Label(i++, row, s.getStudentFullName()));
	}

	public static void writeHeader(WritableSheet sheet) throws RowsExceededException, WriteException {
		int i = 0;
		sheet.addCell(new Label(i++, 0, "tax_id"));
		sheet.addCell(new Label(i++, 0, "tax_step"));
		sheet.addCell(new Label(i++, 0, "tax_monn"));
		sheet.addCell(new Label(i++, 0, "tax_faculty"));
		sheet.addCell(new Label(i++, 0, "tax_year"));
		sheet.addCell(new Label(i++, 0, "tax_periud"));
		sheet.addCell(new Label(i++, 0, "tax_other"));
		sheet.addCell(new Label(i++, 0, "tax_enroled_sem"));
		sheet.addCell(new Label(i++, 0, "tax_current_semester"));
		sheet.addCell(new Label(i++, 0, "tax_name"));
		sheet.addCell(new Label(i++, 0, "tax_sur"));
		sheet.addCell(new Label(i++, 0, "tax_st_id"));
		sheet.addCell(new Label(i++, 0, "speci"));
		sheet.addCell(new Label(i++, 0, "status"));
		sheet.addCell(new Label(i++, 0, "key"));
	}

}
