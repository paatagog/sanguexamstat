package tools.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import tools.Exam;
import tools.ExamExcelFileProcessor;
import tools.SystemParameters;

public class MarksReadTest {
	
	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(int message) {
		System.out.println(message);
	}
	
	public static void rewriteExample() {
		try {
			List<Exam> exams = ExamExcelFileProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "nishnebi.xls");
			log("წაკითხულია " + exams.size() + " შეფასება");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "shedegi.xls"));
			WritableSheet sheet = workbook.createSheet("შედეგი", 0);
			ExamExcelFileProcessor.writeHeader(sheet);
			int row = 1;
			List<Integer> colors = new ArrayList<Integer> ();
			colors.add(0);
			colors.add(3);
			for (Exam exam : exams) {
				ExamExcelFileProcessor.write(sheet, row++, exam, colors);
			}
			
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			List<Exam> exams = ExamExcelFileProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "nishnebi.xls");
			log("წაკითხულია " + exams.size() + " შეფასება");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "shedegi.xls"));
			WritableSheet sheet = workbook.createSheet("შედეგი", 0);
			ExamExcelFileProcessor.writeHeader(sheet);
			int row = 1;
			List<Integer> colors = new ArrayList<Integer> ();
			colors.add(0);
			colors.add(3);
			for (Exam exam : exams) {
				exam.trimFullName();
				exam.adjustFullName();
				ExamExcelFileProcessor.write(sheet, row++, exam, colors);
			}
			
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
