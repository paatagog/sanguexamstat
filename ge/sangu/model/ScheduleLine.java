package ge.sangu.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleLine {

	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	private void log(String message) {
		System.out.println(new Date() + " - ScheduleLine: " + message);
	}
	
	private String lectures;

	private String lectureTypes;

	private String lecturers;
	
	private String rooms;
	
	private String times;
	
	private AcademicLevel academicLevel;
	
	private Integer course;
	
	private String group;
	
	private String actualGroups;
	
	private Integer day;

	public List<String> getLecturerList() {
		return getListFromString(lecturers);
	}
	
	public List<String> getLectureList() {
		return getListFromString(lectures);
	}
	
	public List<String> getRoomList() {
		return getListFromString(rooms);
	}
	
	public List<Date> getTimeList() {
		List<Date> result = new ArrayList<Date> ();
		List<String> l = getListFromString(times);
		// TODO
		
		
		return result;
	}

	public List<LectureType> getLectureTypesList() throws Exception {
		List<LectureType> result = new ArrayList<LectureType> ();
		List<String> l = getListFromString(lectureTypes);
		for (String s : l) {
			LectureType lt = LectureType.getFromString(s);
			if (lt == null) {
				log("ERROR: invalid lecture type: " + s);
				throw new Exception();
			}
		}
		return result;
	}

	public List<String> getListFromString(String str) {
		List<String> l = new ArrayList<String> ();
		if (str != null && str.trim().length() != 0) {
			String[] arr = str.split(",");
			for (String s : arr) {
				l.add(s.trim());
			}
		}
		
		return l;
	}
	
	public String getLectures() {
		return lectures;
	}

	public void setLectures(String lectures) {
		this.lectures = lectures;
	}

	public String getLectureTypes() {
		return lectureTypes;
	}

	public void setLectureTypes(String lectureTypes) {
		this.lectureTypes = lectureTypes;
	}

	public String getLecturers() {
		return lecturers;
	}

	public void setLecturers(String lecturers) {
		this.lecturers = lecturers;
	}

	public String getRooms() {
		return rooms;
	}

	public void setRooms(String rooms) {
		this.rooms = rooms;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public AcademicLevel getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}

	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getActualGroups() {
		return actualGroups;
	}

	public void setActualGroups(String actualGroups) {
		this.actualGroups = actualGroups;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}
	
	
}
