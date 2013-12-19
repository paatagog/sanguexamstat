package ge.sangu.renderer;

import ge.sangu.model.ScheduleItem;
import ge.sangu.utils.CalendarUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ScheduleHtmlRenderer {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
	private static boolean commentsEnabled = false;

	private boolean appendHTMLHeader = true;

	private boolean appendEmptyDays = true;
		
	List<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();
	
	public String render() {
		Collections.sort(scheduleItems);
		List <List<ScheduleItem>> days = new ArrayList <List<ScheduleItem>>();
		for (int i = 0; i < 8; i++) {
			days.add(new ArrayList<ScheduleItem>());
		}
		for (ScheduleItem si : scheduleItems) {
			if (si.getDay() == null) {
				days.get(6).add(si);
			} else {
				days.get(si.getDay() - 1).add(si);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if (appendHTMLHeader) {
			appendHTMLHeader(sb);
		}
		
		sb.append("<a href=\"index.php?page=3\">ჯგუფების სია</a><br/><br/>\n\n");

		for (int i = 0; i < 8; i++) {
			if (days.get(i).size() != 0 || (appendEmptyDays && i < 6)) {
				startDay(sb, i + 1);
				List <List<ScheduleItem>> sessions = getLectureSessions(days.get(i));
				for (List<ScheduleItem> session : sessions) {
					appendLectureSession(sb, session);
				}				
				endDay(sb);
			}
		}
		
		if (appendHTMLHeader) {
			appendHTMLFooter(sb);
		}
		return sb.toString();
	}
	
	private void appendLectureSession(StringBuilder sb, List<ScheduleItem> session) {
		Set<Date> hours = new LinkedHashSet<Date>();
		for (ScheduleItem si : session) {
			hours.add(si.getTime());
		}
		String lectureTitle = session.get(0).getLecture() == null ? null : session.get(0).getLecture().getName();
		String lectureType = session.get(0).getLectureType() == null ? null : session.get(0).getLectureType().getName(); 
		appendLectureSession(sb, hours, lectureTitle, lectureType, getLecturers(session), getRooms(session));
	}
	
	private void appendLectureSession(StringBuilder sb, Set<Date> hours, String LectureTitle, String lectureType, String lecturer, String rooms) {
		startLectureSession(sb);
		int i =  0;
		for (Date h: hours) {

			sb.append("   ");
			if (h != null) {
				sb.append("<span style='font-size:12px;font-weight:bold'>");
				sb.append(sdf.format(h)).append("&nbsp;&nbsp;&nbsp;");
				sb.append("</span>");
			}
			
			if (i == 0) {
				if (LectureTitle != null) {
					sb.append("<span style='font-size:16px'>");
					sb.append(LectureTitle).append("&nbsp;&nbsp;&nbsp;");
					sb.append("</span>");
					if (lectureType != null) {
						sb.append("<span style='font-size:12px'>");
						sb.append("(").append(lectureType).append(")").append("&nbsp;&nbsp;&nbsp;");
						sb.append("</span>");
					}
				}		
				if (lecturer != null) {
					sb.append(lecturer).append("&nbsp;&nbsp;&nbsp;");
				}
				sb.append("<span style='font-size:12px'>");
				sb.append(rooms).append(" ");
				sb.append("</span>");
			}
			
			sb.append("</br>").append("\n");
			i++;
			
		}
		endLectureSession(sb);
	}

	private String getRooms(List<ScheduleItem> session) {
		StringBuilder sb = new StringBuilder();
		if (session != null && session.size() != 0) {
			Set <String> rooms = new HashSet<String>();
			for (ScheduleItem scheduleItem : session) {
				if (scheduleItem.getRoom() != null) {
					rooms.add(scheduleItem.getRoom());
				}
			}
			
			if (rooms.size() != 0) {
				if (session.get(0).isForeignLanguage() || session.get(0).isTraning()) {
					List<String> roomsList = new ArrayList<String>(rooms);
					Collections.sort(roomsList);
					sb.append(roomsList.get(0));
					for(int i = 1; i < roomsList.size(); i++) {
						sb.append(", ").append(roomsList.get(i));
					}
				} else {
					sb.append(session.get(0).getRoom());
					if (rooms.size() > 1) {
						for(int i = 1; i < session.size(); i++) {
							sb.append(", ").append(session.get(i).getRoom());
						}
					}
				}
			}
		}
		return sb.toString();
	}
	
	private String getLecturers(List<ScheduleItem> session) {
		StringBuilder sb = new StringBuilder();
		if (session != null && session.size() != 0) {
			Set <String> lecturers = new HashSet<String>();
			for (ScheduleItem scheduleItem : session) {
				if (scheduleItem.getLecturer() != null) {
					lecturers.add(scheduleItem.getLecturer().getShortName());
				}
			}
			
			if (lecturers.size() != 0) {
				if (session.get(0).isForeignLanguage() || session.get(0).isTraning()) {
					List<String> lecturerList = new ArrayList<String>(lecturers);
					Collections.sort(lecturerList);
					sb.append(lecturerList.get(0));
					for(int i = 1; i < lecturerList.size(); i++) {
						sb.append(", ").append(lecturerList.get(i));
					}
				} else {
					sb.append(session.get(0).getLecturer() == null ? "" : session.get(0).getLecturer().getShortName());
					if (lecturers.size() > 1) {
						for(int i = 1; i < session.size(); i++) {
							sb.append(", ").append(session.get(i).getLecturer() == null ? "" : session.get(i).getLecturer().getShortName());
						}
					}
				}
			}
		}
		return sb.toString();
	}

	private List <List<ScheduleItem>> getLectureSessions(List<ScheduleItem> scheduleItems) {
		List <List<ScheduleItem>> sessions = new ArrayList <List<ScheduleItem>>();
		ScheduleItem oldSi = null;
		for (ScheduleItem si : scheduleItems) {
			if (ScheduleItem.isSameSession(si, oldSi)) {
				sessions.get(sessions.size() - 1).add(si);
			} else {
				List<ScheduleItem> l = new ArrayList<ScheduleItem>();
				l.add(si);
				sessions.add(l);
			}
			oldSi = si;
		}
		return sessions;
	}
	
	public void appendHTMLHeader(StringBuilder sb) {
		if (commentsEnabled) {
			sb.append("<!-- adding html header -->\n");
		}
		sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">").append("\n")
		.append("<HTML><HEAD>").append("\n")
		.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">").append("\n")
		.append("</HEAD><BODY>").append("\n");
	}

	public void appendHTMLFooter(StringBuilder sb) {
		if (commentsEnabled) {
			sb.append("<!-- adding html footer -->\n");
		}
		sb.append("\n").append("</BODY></HTML>");
	}
	
	public void appendLecture(StringBuilder sb, ScheduleItem si) {
		if (commentsEnabled) {
			sb.append("<!-- appending lecture -->\n");
		}
		sb.append("   ");
		if (si.getTime() != null) {
			sb.append("<span style='font-size:12px;font-weight:bold'>");
			sb.append(sdf.format(si.getTime())).append("&nbsp;&nbsp;&nbsp;");
			sb.append("</span>");
		}
		if (si.getLecture() != null) {
			sb.append("<span style='font-size:16px'>");
			sb.append(si.getLecture().getName()).append("&nbsp;&nbsp;&nbsp;");
			sb.append("</span>");
			if (si.getLectureType() != null) {
				sb.append("<span style='font-size:12px'>");
				sb.append("(").append(si.getLectureType().getName()).append(")").append("&nbsp;&nbsp;&nbsp;");
				sb.append("</span>");
			}
		}		
		if (si.getLecturer() != null) {
			sb.append(si.getLecturer().getShortName()).append("&nbsp;&nbsp;&nbsp;");
		}
		sb.append("<span style='font-size:12px'>");
		sb.append(si.getRoom()).append(" ");
		sb.append("</span>");
		sb.append("</br>").append("\n");
	}
	
	public void startDay(StringBuilder sb, int day) {
		if (commentsEnabled) {
			sb.append("<!-- starting day -->\n");
		}
		sb.append("<table cellspacing='0px' cellpadding='3px' style='width:700px;border-width:0px; border-style:solid;border-collapse:collapse;'>").append("\n")
		.append(" <tr>").append("\n")
		.append("  <td style='border-width:0px;border-style:double;text-align:center;'>").append("\n")
		.append("   ")
		.append("<span style='font-weight:bold;font-size:18px'>")
		.append(CalendarUtils.getWeekDayName(day))
		.append("</span>")
		.append("  </td>").append("\n")
		.append(" </tr>").append("\n");
	}
	
	public void endDay(StringBuilder sb) {
		if (commentsEnabled) {
			sb.append("<!-- ending day -->\n");
		}
		sb.append("</table>").append("</br>").append("\n");
	}

	public void startLectureSession(StringBuilder sb) {
		if (commentsEnabled) {
			sb.append("<!-- starting lecture session -->\n");
		}
		sb.append(" <tr>").append("\n")
		.append("  <td style='border-width:1px;border-style:solid'>").append("\n");
	}

	public void endLectureSession(StringBuilder sb) {
		if (commentsEnabled) {
			sb.append("<!-- ending lecture session -->\n");
		}
		sb.append("  </td>").append("\n")
		.append(" </tr>").append("\n");
	}

	public void startLecture(StringBuilder sb) {
		if (commentsEnabled) {
			sb.append("<!-- starting lecture -->\n");
		}
		
	}

	public void endLecture(StringBuilder sb) {
		if (commentsEnabled) {
			sb.append("<!-- ending lecture -->\n");
		}		
	}

	public boolean isAppendEmptyDays() {
		return appendEmptyDays;
	}

	public void setAppendEmptyDays(boolean appendEmptyDays) {
		this.appendEmptyDays = appendEmptyDays;
	}

	public boolean isAppendHTMLHeader() {
		return appendHTMLHeader;
	}

	public void setAppendHTMLHeader(boolean appendHTMLHeader) {
		this.appendHTMLHeader = appendHTMLHeader;
	}

	public List<ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}

	public void setScheduleItems(List<ScheduleItem> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}
}

enum State {
	NONE,
	DAY,
	LECTURE_SESSION,
	LECTURE;	
}
