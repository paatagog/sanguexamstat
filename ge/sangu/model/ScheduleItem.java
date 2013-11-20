package ge.sangu.model;

import java.util.Date;


public class ScheduleItem {
	
	private Integer id;
	
	private Lecture lecture;

	private LectureType lectureType;

	private Lecturer lecturer;
	
	private String room;
	
	private Date time;
	
	private AcademicLevel academicLevel;
	
	private Integer course;
	
	private String Group;
	
	private Integer day;
	
	public boolean isEmpty() {
		return id == null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Lecture getLecture() {
		return lecture;
	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public LectureType getLectureType() {
		return lectureType;
	}

	public void setLectureType(LectureType lectureType) {
		this.lectureType = lectureType;
	}

	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
		return Group;
	}

	public void setGroup(String group) {
		Group = group;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}
	
	
}
