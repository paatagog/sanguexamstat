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
	
	public static void rewriteExample() {
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
				TaxExcelProcessor.write(sheet, row++, t, colors);
			}
			
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		rewriteExample();
	}

}
