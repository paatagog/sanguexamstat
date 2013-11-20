package ge.sangu.excel;

import ge.sangu.model.Lecture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

public class LectureExcelProcessor {

	public static List<Lecture> read(String fileName) {
		return read(fileName, null, null);
	}

	public static List<Lecture> read(String fileName, Integer sheetNumber, String sheetName) {
		List<Lecture> list = new ArrayList<Lecture> ();
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
			Lecture o = read(sheet, row++);
			
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

	public static Lecture read(Sheet sheet, int row) {
		Lecture o = new Lecture();
		
		try {
			int i = 0;
			o.setId(Integer.parseInt(sheet.getCell(i++, row).getContents()));
			o.setName(sheet.getCell(i++, row).getContents());			
			if (o.isEmpty()) {
				o = null;
			}
		
		} catch (Exception e) {
			o = null;
		}
		
		return o;
	}

}
