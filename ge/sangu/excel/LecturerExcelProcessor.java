package ge.sangu.excel;

import ge.sangu.model.Lecturer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LecturerExcelProcessor {

	public static List<Lecturer> read(String fileName) {
		return read(fileName, null, null);
	}

	public static List<Lecturer> read(String fileName, Integer sheetNumber, String sheetName) {
		List<Lecturer> list = new ArrayList<Lecturer> ();
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
			Lecturer o = read(sheet, row++);
			
			while (o != null) {
				list.add(o);
				o = read(sheet, row++);
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return list;
	}

	public static Lecturer read(Sheet sheet, int row) {
		Lecturer o = new Lecturer();
		
		try {
			int i = 0;
			o.setSanguId(sheet.getCell(i++, row).getContents());
			if (o.getSanguId() != null) {
				o.setId(Integer.parseInt(o.getSanguId()));
			}
			o.setLastName(sheet.getCell(i++, row).getContents());			
			o.setFirstName(sheet.getCell(i++, row).getContents());			
			o.setPaternalName(sheet.getCell(i++, row).getContents());			
			o.setSex(sheet.getCell(i++, row).getContents());			
			o.setNationality(sheet.getCell(i++, row).getContents());			
			o.setCitizenship(sheet.getCell(i++, row).getContents());			
			o.setPersonalNumber(sheet.getCell(i++, row).getContents());			
			o.setIdDocumentNumber(sheet.getCell(i++, row).getContents());			
			o.setBirthDate(sheet.getCell(i++, row).getContents());			
			o.setBirthPlace(sheet.getCell(i++, row).getContents());			
			o.setJuridicalAddress(sheet.getCell(i++, row).getContents());			
			o.setHomePhone(sheet.getCell(i++, row).getContents());			
			o.setMobilePhone(sheet.getCell(i++, row).getContents());			
			o.setEmail(sheet.getCell(i++, row).getContents());			
			if (o.isEmpty()) {
				o = null;
			}
		
		} catch (Exception e) {
			o = null;
		}
		
		return o;
	}

}
