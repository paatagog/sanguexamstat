package ge.sangu.tools;

import ge.sangu.excel.StudentExcelProcessor;
import ge.sangu.excel.TaxExcelProcessor;
import ge.sangu.model.Student;
import ge.sangu.model.Tax;
import ge.sangu.utils.SystemParameters;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TaxReadWriteTest {

	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(int message) {
		log(String.valueOf(message));
	}
	
	public static void correcteAndRewrite() {
		try {
			List<Student> studnets = StudentExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "students.xls");
			Map<Integer, Student> studMap = new HashMap<Integer, Student>();
			for (Student student : studnets) {
				studMap.put(Integer.parseInt(student.getSanguId()), student);
			}			
			List<Tax> list = TaxExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "daricxvebi.xls");
			log("წაკითხულია " + list.size() + " ჩანაწერი");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "daricxvebi-rewrite.xls"));
			WritableSheet sheet = workbook.createSheet("დარიცხვები", 0);
			TaxExcelProcessor.writeHeader(sheet);
			int row = 1;
			List<Integer> colors = new ArrayList<Integer> ();
			for (Tax t : list) {
				Student s = studMap.get(Integer.parseInt(t.getStudentId()));
				if (s == null) {
					log("არ მოიძებნა დარიცხვის შესაბამისი სტუდენტი. იდენტიფიკატორი: " + t.getStudentId() + ", სახელი: " + t.getStudentFirstName() + ", გვარი: " + t.getStudentLastName());
				} else {
					t.setCurrentSemester(s.getCurrentSemester());
					t.setStudentFirstName(s.getFirstName());
					t.setStudentLastName(s.getLastName());
					t.setStudentFullName(s.getFirstName() + " " + s.getLastName());
				}
				TaxExcelProcessor.write(sheet, row++, t, colors, null);
			}
			
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void diffFiles() {
		try {
			List<Tax> orig = TaxExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "daricxvebiOriginal.xls");
			log("წაკითხულია " + orig.size() + " ჩანაწერი პირველი ფაილიდან");
			List<Tax> kaxa = TaxExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "daricxvebiKaxa.xls");
			log("წაკითხულია " + kaxa.size() + " ჩანაწერი მეორე ფაილიდან");
			Map<String, Tax> taxesOriginal = new HashMap<String, Tax>();
			Map<String, Tax> taxesKaxa = new HashMap<String, Tax>();
			for (Tax t: orig) {
				taxesOriginal.put(t.getId(), t);
			}
			for (Tax t : kaxa) {
				taxesKaxa.put(t.getId(), t);
				
			}
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "daricxvebi-diff.xls"));
			WritableSheet sheet = workbook.createSheet("განსხვავებები", 0);
			TaxExcelProcessor.writeHeader(sheet);
			int row = 1;
			for (Tax s : orig) {
				Tax k = taxesKaxa.remove(s.getId());
				List<Integer> colors = getDiffColors(s, k);
				TaxExcelProcessor.write(sheet, row++, s, colors, k == null ? false : null);
			}
			for (Map.Entry<String, Tax> t : taxesKaxa.entrySet()) {
				List<Integer> colors = getDiffColors(null, t.getValue());
				TaxExcelProcessor.write(sheet, row++, t.getValue(), colors, true);
			}
			
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Integer> getDiffColors(Tax t1, Tax t2) {
		List<Integer> diff = new ArrayList<Integer> ();

		if (t1 != null && t2 == null) {
			diff.add(0);
		} else if (t1 == null && t2 != null) {
			diff.add(0);
		} else {
			if (!isEqualStrings(t1.getStep(), t2.getStep())) {
				diff.add(1);
			}
			if (!isEqualStrings(t1.getAmount(), t2.getAmount())) {
				diff.add(2);
			}
			if (!isEqualStrings(t1.getFaculty(), t2.getFaculty())) {
				diff.add(3);
			}
			if (!isEqualStrings(t1.getYear(), t2.getYear())) {
				diff.add(4);
			}
			if (!isEqualStrings(t1.getPeriod(), t2.getPeriod())) {
				diff.add(5);
			}
			if (!isEqualStrings(t1.getOtherAmount(), t2.getOtherAmount())) {
				diff.add(6);
			}
			if (!isEqualStrings(t1.getSemester(), t2.getSemester())) {
				diff.add(7);
			}
			if (!isEqualStrings(t1.getCurrentSemester(), t2.getCurrentSemester())) {
				diff.add(8);
			}
			if (!isEqualStrings(t1.getStudentFirstName(), t2.getStudentFirstName())) {
				diff.add(9);
			}
			if (!isEqualStrings(t1.getStudentLastName(), t2.getStudentLastName())) {
				diff.add(10);
			}
			if (!isEqualStrings(t1.getStudentId(), t2.getStudentId())) {
				diff.add(11);
			}
			if (!isEqualStrings(t1.getSpecialization(), t2.getSpecialization())) {
				diff.add(12);
			}
			if (!isEqualStrings(t1.getStatus(), t2.getStatus())) {
				diff.add(13);
			}
			if (!isEqualStrings(t1.getStudentFullName(), t2.getStudentFullName())) {
				diff.add(14);
			}
		}
		return diff;
	}
	
	public static boolean isEqualStrings(String s1, String s2) {
		return !((s1 != null && s2 == null) || (s1 == null && s2 != null) || (s1 != null && s2 != null && !s1.equals(s2)));
	}
	
	public static void main(String[] args) {
//		correcteAndRewrite();
		diffFiles();
	}

}
