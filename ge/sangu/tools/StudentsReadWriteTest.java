package ge.sangu.tools;

import ge.sangu.excel.ExamExcelProcessor;
import ge.sangu.excel.StudentExcelProcessor;
import ge.sangu.model.Exam;
import ge.sangu.model.Student;
import ge.sangu.utils.GeorgianNamesAdjuster;
import ge.sangu.utils.PersonalNumberAdjuster;
import ge.sangu.utils.SystemParameters;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class StudentsReadWriteTest {
	
	public static void log(String message) {
		System.out.println(message);
	}
	
	public static void log(int message) {
		System.out.println(message);
	}
	
	public static void rewriteExample() {
		try {
			List<Student> studnets = StudentExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "students.xls");
			log("წაკითხულია " + studnets.size() + " ჩანაწერი");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "students-rewritten.xls"));
			WritableSheet sheet = workbook.createSheet("შედეგი", 0);
			StudentExcelProcessor.writeHeader(sheet);
			int row = 1;
			List<Integer> colors = new ArrayList<Integer> ();
			colors.add(0);
			colors.add(3);
			for (Student s : studnets) {
				s.setFirstName(GeorgianNamesAdjuster.adjust(s.getFirstName()));
				s.setPaternalName(GeorgianNamesAdjuster.adjust(s.getPaternalName()));
				s.setPersonalNumber(PersonalNumberAdjuster.adjust(s.getPersonalNumber()));
				if ("0".equals(s.getMobility())) {
					s.setMobility("არაა მობილობა");
				}
				s.setLocalSpeciality("N/A");
				if (s.getOrderNumber() != null && s.getOrderNumber().startsWith("#")) {
					s.setOrderNumber(s.getOrderNumber().substring(1));
				}
				StudentExcelProcessor.write(sheet, row++, s);
			}
			
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void correctPersonalNumber() {
		try {
			List<Student> studnets = StudentExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "students.xls");
			List<Exam> exams = ExamExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "nishnebi.xls");
			Map<String, String> personalNumbers = new HashMap<String, String>();
			for (Exam exam : exams) {
				personalNumbers.put(exam.getFullName(), exam.getPersonalNumber());
			}			
			log("წაკითხულია " + studnets.size() + " ჩანაწერი");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "students-rewritten.xls"));
			WritableSheet sheet = workbook.createSheet("შედეგი", 0);
			StudentExcelProcessor.writeHeader(sheet);
			int row = 1;
			for (Student s : studnets) {
				String fullName = s.getLastName() + " " + s.getFirstName();
				if (personalNumbers.containsKey(fullName)) {
					if (!personalNumbers.get(fullName).equals(s.getPersonalNumber())) {
						log("Found " + s.getPersonalNumber() + "; Should be " + personalNumbers.get(fullName));
						s.setPersonalNumber(personalNumbers.get(fullName));
					}
				}				
				StudentExcelProcessor.write(sheet, row++, s);
			}
			
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	public static void main(String[] args) {
		correctPersonalNumber();
	}

}
