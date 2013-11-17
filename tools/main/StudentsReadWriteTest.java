package tools.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import tools.Student;
import tools.StudentExcelFileProcessor;
import tools.SystemParameters;

public class StudentsReadWriteTest {
	
	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(int message) {
		System.out.println(message);
	}
	
	public static void rewriteExample() {
		try {
			List<Student> studnets = StudentExcelFileProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "students.xls");
			log("წაკითხულია " + studnets.size() + " ჩანაწერი");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "students-rewritten.xls"));
			WritableSheet sheet = workbook.createSheet("შედეგი", 0);
			StudentExcelFileProcessor.writeHeader(sheet);
			int row = 1;
			List<Integer> colors = new ArrayList<Integer> ();
			colors.add(0);
			colors.add(3);
			for (Student s : studnets) {
				StudentExcelFileProcessor.write(sheet, row++, s, colors);
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
