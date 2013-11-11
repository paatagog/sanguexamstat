package tools.main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExamStatistics {
	public static String INPUT_FILE = "c:/dev/sanguexamstat/sources/resources/input.txt";
	
	private static String countMean(List<Integer> marks) {
		if (marks == null || marks.size() == 0) {
			return "0";
		}
		int sum = 0;
		for (Integer i : marks) {
			sum += i;
		}
		return String.valueOf((double)sum/marks.size());
	}

	private static String countMeanWithoutZeros(List<Integer> marks) {
		if (marks == null || marks.size() == 0) {
			return "0";
		}
		int sum = 0;
		int count = 0;
		for (Integer i : marks) {
			sum += i;
			count += i == 0 ? 0 : 1;
		}
		return String.valueOf((double)sum/count);
	}

	private static String countRange(List<Integer> marks, int min, int max) {
		if (marks == null || marks.size() == 0) {
			return "0";
		}
		int count = 0;
		for (Integer i : marks) {
			if (i >= min && i <= max) {
				count ++;
			}
		}
		return String.valueOf(count);
	}
	
	private static String countRangePie(List<List<Integer>> exams) {
		if (exams == null || exams.size() == 0) {
			return "0";
		}
		
		StringBuilder sb = new StringBuilder();
		
		int zero = 0;
		int count1_5 = 0;
		int count6_10 = 0;
		int count11_15 = 0;
		int count16_20 = 0;
		int count21_25 = 0;
		if (exams != null && exams.size() != 0) {
			for (List<Integer> marks : exams) {
				for (Integer i : marks) {
					if(i == 0) {
						zero ++;
					}
					if (i >= 1 && i <= 5) {
						count1_5 ++;
					}
					if (i >= 6 && i <= 10) {
						count6_10 ++;
					}
					if (i >= 11 && i <= 15) {
						count11_15 ++;
					}
					if (i >= 16 && i <= 20) {
						count16_20 ++;
					}
					if (i >= 21 && i <= 25) {
						count21_25 ++;
					}
				}
			}
		}
		sb.append("zero: ").append(zero).append(";\n")
		.append("1-5: ").append(count1_5).append(";\n")
		.append("6-10: ").append(count6_10).append(";\n")
		.append("11-15: ").append(count11_15).append(";\n")
		.append("16-20: ").append(count16_20).append(";\n")
		.append("21-25: ").append(count21_25).append(";\n");
		return sb.toString();
	}
	
	private static String countByMark(List<List<Integer>> exams) {
		if (exams == null || exams.size() == 0) {
			return "0";
		}
		
		Map<Integer, Integer> stat = new LinkedHashMap<Integer, Integer>();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 26; i++) {
			stat.put(i, 0);
		}
		if (exams != null && exams.size() != 0) {
			for (List<Integer> marks : exams) {
				for (Integer i : marks) {
					stat.put(i, stat.get(i) + 1);
				}
			}
		}
		for (int i = 0; i < 26; i++) {
			sb.append(stat.get(i)).append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			List<List<Integer>> exams = new ArrayList<List<Integer>> ();
			List<String> lines = Files.readAllLines(Paths.get(INPUT_FILE), Charset.forName("UTF-8"));
			for (String line : lines) {
				List<Integer> marks = new ArrayList<Integer>();
				if (!line.trim().equals("")) {
					String [] s = line.split(",");
					for (String str : s) {
						marks.add(Integer.parseInt(str.trim()));
					}
				}
				exams.add(marks);
			}

			System.out.println("##############################");
			System.out.println("# Mean -----------------------");
			System.out.println("##############################");
			for (List<Integer> marks : exams) {
				System.out.println(countMean(marks));
			}

			System.out.println("##############################");
			System.out.println("# Mean without zeros ---------");
			System.out.println("##############################");
			for (List<Integer> marks : exams) {
				System.out.println(countMeanWithoutZeros(marks));
			}
			
			System.out.println("##############################");
			System.out.println("# 0 --------------------------");
			System.out.println("##############################");
			for (List<Integer> marks : exams) {
				System.out.println(countRange(marks, 0, 0));
			}

			System.out.println("##############################");
			System.out.println("# 1-10 -----------------------");
			System.out.println("##############################");
			for (List<Integer> marks : exams) {
				System.out.println(countRange(marks, 1, 10));
			}

			System.out.println("##############################");
			System.out.println("# 11-20 ----------------------");
			System.out.println("##############################");
			for (List<Integer> marks : exams) {
				System.out.println(countRange(marks, 11, 20));
			}

			System.out.println("##############################");
			System.out.println("# 21-25 ----------------------");
			System.out.println("##############################");
			for (List<Integer> marks : exams) {
				System.out.println(countRange(marks, 21, 25));
			}
			
			System.out.println("##############################");
			System.out.println("# Range Pie ------------------");
			System.out.println("##############################");
			System.out.println(countRangePie(exams));

			System.out.println("##############################");
			System.out.println("# Count By Mark Pie ----------");
			System.out.println("##############################");
			System.out.println(countByMark(exams));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
