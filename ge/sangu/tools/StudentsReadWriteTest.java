package ge.sangu.tools;

import ge.sangu.excel.ExamExcelProcessor;
import ge.sangu.excel.StudentExcelProcessor;
import ge.sangu.excel.TaxExcelProcessor;
import ge.sangu.model.Exam;
import ge.sangu.model.Student;
import ge.sangu.model.Tax;
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
		log(String.valueOf(message));
	}
	
	public static void rewriteExample() {
		try {
			List<Student> studnets = StudentExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "students.xls");
			log("წაკითხულია " + studnets.size() + " ჩანაწერი");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "students-rewritten.xls"));
			WritableSheet sheet = workbook.createSheet("შედეგი", 0);
			StudentExcelProcessor.writeHeader(sheet);
			int row = 1;
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
	
	public static void diffFiles() {
		try {
			List<Student> stOrig = StudentExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "studentsOriginal.xls");
			log("წაკითხულია " + stOrig.size() + " ჩანაწერი პირველი ფაილიდან");
			List<Student> stKaxa = StudentExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "studentsKaxa.xls");
			log("წაკითხულია " + stKaxa.size() + " ჩანაწერი მეორე ფაილიდან");
			Map<String, Student> studentsOriginal = new HashMap<String, Student>();
			Map<String, Student> studentsKaxa = new HashMap<String, Student>();
			for (Student student : stOrig) {
				studentsOriginal.put(student.getSanguId(), student);
			}
			for (Student student : stKaxa) {
				studentsKaxa.put(student.getSanguId(), student);
				
			}
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "students-diff.xls"));
			WritableSheet sheet = workbook.createSheet("განსხვავებები", 0);
			StudentExcelProcessor.writeHeader(sheet);
			int row = 1;
			for (Student s : stOrig) {
				Student ks = studentsKaxa.remove(s.getSanguId());
				List<Integer> colors = getDiffColors(s, ks);
				StudentExcelProcessor.write(sheet, row++, s, colors, ks == null ? false : null);
			}
			for (Map.Entry<String, Student> student : studentsKaxa.entrySet()) {
				List<Integer> colors = getDiffColors(null, student.getValue());
				StudentExcelProcessor.write(sheet, row++, student.getValue(), colors, true);
			}
			
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void incSemesters() {
		try {
			List<Student> stOrig = StudentExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "studentsOriginal.xls");
			log("წაკითხულია " + stOrig.size() + " ჩანაწერი პირველი ფაილიდან");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "students-increased.xls"));
			WritableSheet sheet = workbook.createSheet("მიმატებული", 0);
			StudentExcelProcessor.writeHeader(sheet);
			int row = 1;
			for (Student s : stOrig) {
				if (s.getAcademicStatus() != null && "აქტიური".equals(s.getAcademicStatus().trim())) {
					try {
						int newSemester = Integer.parseInt(s.getCurrentSemester()) + 1;
						s.setCurrentSemester(String.valueOf(newSemester));
						if (newSemester % 2 != 0) {
							System.out.println(s.getFirstName() + " " + s.getLastName());
						}
					} catch (Exception ignore) {
						
					}
				}
				StudentExcelProcessor.write(sheet, row++, s, null, false);
			}
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Integer> getDiffColors(Student s1, Student s2) {
		List<Integer> diff = new ArrayList<Integer> ();

		if (s1 != null && s2 == null) {
			diff.add(0);
		} else if (s1 == null && s2 != null) {
			diff.add(0);
		} else {
			if (!isEqualStrings(s1.getLastName(), s2.getLastName())) {
				diff.add(1);
			}
			if (!isEqualStrings(s1.getFirstName(), s2.getFirstName())) {
				diff.add(2);
			}
			if (!isEqualStrings(s1.getPaternalName(), s2.getPaternalName())) {
				diff.add(3);
			}
			if (!isEqualStrings(s1.getSex(), s2.getSex())) {
				diff.add(4);
			}
			if (!isEqualStrings(s1.getNationality(), s2.getNationality())) {
				diff.add(5);
			}
			if (!isEqualStrings(s1.getCitizenship(), s2.getCitizenship())) {
				diff.add(6);
			}
			if (!isEqualStrings(s1.getPersonalNumber(), s2.getPersonalNumber())) {
				if (!(s1.getPersonalNumber() != null && s1.getPersonalNumber() != null && s1.getPersonalNumber().equals("0" + s2.getPersonalNumber()))) {
					diff.add(7);
				}
			}
			if (!isEqualStrings(s1.getIdDocumentNumber(), s2.getIdDocumentNumber())) {
				diff.add(8);
			}
			if (!isEqualStrings(s1.getBirthDate(), s2.getBirthDate())) {
				diff.add(9);
			}
			if (!isEqualStrings(s1.getBirthPlace(), s2.getBirthPlace())) {
				diff.add(10);
			}
			if (!isEqualStrings(s1.getJuridicalAddress(), s2.getJuridicalAddress())) {
				diff.add(11);
			}
			if (!isEqualStrings(s1.getHomePhone(), s2.getHomePhone())) {
				diff.add(12);
			}
			if (!isEqualStrings(s1.getMobilePhone(), s2.getMobilePhone())) {
				diff.add(13);
			}
			if (!isEqualStrings(s1.getEmail(), s2.getEmail())) {
				diff.add(14);
			}
			if (!isEqualStrings(s1.getOrderNumber(), s2.getOrderNumber())) {
				diff.add(15);
			}
			if (!isEqualStrings(s1.getSocialStatus(), s2.getSocialStatus())) {
				diff.add(16);
			}
			if (!isEqualStrings(s1.getAcademicLevel(), s2.getAcademicLevel())) {
				diff.add(17);
			}
			if (!isEqualStrings(s1.getCurrentSemester(), s2.getCurrentSemester())) {
				diff.add(18);
			}
			if (!isEqualStrings(s1.getStartSemester(), s2.getStartSemester())) {
				diff.add(19);
			}
			if (!isEqualStrings(s1.getFaculty(), s2.getFaculty())) {
				diff.add(20);
			}
			if (!isEqualStrings(s1.getAcademicStatus(), s2.getAcademicStatus())) {
				diff.add(21);
			}
			if (!isEqualStrings(s1.getSpeciality(), s2.getSpeciality())) {
				diff.add(22);
			}
			if (!isEqualStrings(s1.getLocalSpeciality(), s2.getLocalSpeciality())) {
				diff.add(23);
			}
			if (!isEqualStrings(s1.getMobility(), s2.getMobility())) {
				diff.add(24);
			}
		}
		return diff;
	}
	
	public static boolean isEqualStrings(String s1, String s2) {
		return !((s1 != null && s2 == null) || (s1 == null && s2 != null) || (s1 != null && s2 != null && !s1.equals(s2)));
	}
	
	public static void missingTaxsChecker() {
		try {
			List<Student> students = StudentExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "students.xls");
			log("წაკითხულია " + students.size() + " ჩანაწერი პირველი ფაილიდან");
			Map<String, Student> studentsMap = new HashMap<String, Student>();
			for (Student student : students) {
				studentsMap.put(student.getSanguId(), student);
			}

			List<Tax> processedTaxes = TaxExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "daricxvebi.xls");
			log("წაკითხულია " + processedTaxes.size() + " ჩანაწერი დარიცხვების პირველი ფაილიდან");

			List<Tax> allTaxes = TaxExcelProcessor.read(SystemParameters.INPUT_DATA_FOLDER + "/" + "daricxvebiAll.xls");
			log("წაკითხულია " + allTaxes.size() + " ჩანაწერი დარიცხვების მეორე ფაილიდან");

			Map<String, Tax> processedtaxesMap = new HashMap<String, Tax>();
			Map<String, Tax> allTaxesMap = new HashMap<String, Tax>();
			for (Tax t: processedTaxes) {
				processedtaxesMap.put(t.getId(), t);
			}
			
			for (Tax tax : new ArrayList<Tax>(allTaxes)) {
				if (!studentsMap.containsKey(tax.getStudentId())) {
					allTaxes.remove(tax);
				} else {
					if (processedtaxesMap.containsKey(tax.getId())) {
						allTaxes.remove(tax);
					}
				}				
			}
			
			for (Tax t: allTaxes) {
				allTaxesMap.put(t.getId(), t);
			}
			
			WritableWorkbook workbook = Workbook.createWorkbook(new File(SystemParameters.INPUT_DATA_FOLDER + "/" + "tax-missed-records.xls"));
			WritableSheet sheet = workbook.createSheet("განსხვავებები", 0);
			TaxExcelProcessor.writeHeader(sheet);
			int row = 1;
			for (Tax tax : allTaxes) {
				TaxExcelProcessor.write(sheet, row++, tax);
			}
			
			workbook.write(); 
			workbook.close();
			log("შედეგის ფაილი წარმატებით შეიქმნა");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		diffFiles();
//		incSemesters();
//		rewriteExample();
//		missingTaxsChecker();
	}

}
