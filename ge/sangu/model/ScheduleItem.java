package ge.sangu.model;

import ge.sangu.utils.CalendarUtils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ScheduleItem implements Comparable<ScheduleItem> {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	private Integer id;
	
	private Lecture lecture;

	private LectureType lectureType;

	private Lecturer lecturer;
	
	private String room;
	
	private Date time;
	
	private AcademicLevel academicLevel;
	
	private Integer course;
	
	private String group;
	
	private Integer day;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		if (lecture != null) {
			sb.append(" ").append(lecture.getName());
		}
		if (room != null) {
			sb.append(" ").append(room);
		}
		if (lecturer != null) {
			sb.append(" ").append(lecturer.getShortName());
		}
		if (lectureType != null) {
			sb.append(" ").append(lectureType.getName());
		}
		if (time != null) {
			sb.append(" ").append(sdf.format(time));
		}
		if (academicLevel != null) {
			sb.append(" ").append(academicLevel.getName());
		}
		if (course != null) {
			sb.append(" ").append(course);
		}
		if (group != null) {
			sb.append(" ").append(group);
		}
		if (day != null) {
			sb.append(" ").append(CalendarUtils.getWeekDayName(day));
		}
		return sb.toString();
	}
	
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
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	@Override
	public int compareTo(ScheduleItem s) {
		if (day != null && s.getDay() == null) {
			return 1;
		}
		if (day == null && s.getDay() != null) {
			return -1;
		}
		if (day != null && s.getDay() != null) {
			if (day.intValue() < s.getDay().intValue()) {
				return -1;
			} else if (day.intValue() > s.getDay().intValue()) {
				return +1;
			}
		}
		
		if (time != null && s.getTime() == null) {
			return 1;
		}
		if (time == null && s.getTime() != null) {
			return -1;
		}
		if (time != null && s.getTime() != null) {
			if (time.before(s.getTime())) {
				return -1;
			} else if (time.after(s.getTime())) {
				return 1;
			}
		}
		
		String lec = lecture == null ? null : lecture.getName(); 
		String anotherLec = s.getLecture() == null ? null : s.getLecture().getName(); 
		if (lec != null && anotherLec == null) {
			return -1;
		}
		if (lec == null && anotherLec != null) {
			return 1;
		}
		if (lec != null && anotherLec != null) {
			return lec.compareTo(anotherLec);
		}

		return 0;
	}
	
	
}
