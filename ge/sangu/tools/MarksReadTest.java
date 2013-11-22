package ge.sangu.tools;

import ge.sangu.excel.ExamExcelProcessor;
import ge.sangu.model.Exam;
import ge.sangu.utils.SystemParameters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class MarksReadTest {
	
	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(int message) {
		log(String.valueOf(message));
	}
	
	public static void rewriteExample() {
		try {
			List<Exam> exams = ExamExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "nishnebi.xls");
			log("წაკითხულია " + exams.size() + " შეფასება");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "shedegi.xls"));
			WritableSheet sheet = workbook.createSheet("შედეგი", 0);
			ExamExcelProcessor.writeHeader(sheet);
			int row = 1;
			List<Integer> colors = new ArrayList<Integer> ();
			colors.add(0);
			colors.add(3);
			for (Exam exam : exams) {
				exam.trimFullName();
				exam.adjustFullName();
				ExamExcelProcessor.write(sheet, row++, exam, colors);
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
