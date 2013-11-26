package ge.sangu.renderer;

import ge.sangu.model.ScheduleItem;
import ge.sangu.utils.CalendarUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleHtmlRenderer {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

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

		for (int i = 0; i < 8; i++) {
			if (days.get(i).size() != 0 || (appendEmptyDays && i < 6)) {
				startDay(sb, i + 1);
				List <List<ScheduleItem>> sessions = getLectureSessions(days.get(i));
				for (List<ScheduleItem> session : sessions) {
					startLectureSession(sb);
					for (ScheduleItem si: session) {
						startLecture(sb);
						appendLecture(sb, si);
						endLecture(sb);
					}
					endLectureSession(sb);
				}				
				endDay(sb);
			}
		}
		
		if (appendHTMLHeader) {
			appendHTMLFooter(sb);
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
		sb.append("<!-- adding html header -->\n");
		sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">").append("\n")
		.append("<HTML><HEAD>").append("\n")
		.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">").append("\n")
		.append("</HEAD><BODY>").append("\n");
	}

	public void appendHTMLFooter(StringBuilder sb) {
		sb.append("<!-- adding html footer -->\n");
		sb.append("\n").append("</BODY></HTML>");
	}
	
	public void appendLecture(StringBuilder sb, ScheduleItem si) {
		sb.append("<!-- appending lecture -->\n");
		if (si.getTime() != null) {
			sb.append(sdf.format(si.getTime())).append(" ");
		}
		if (si.getLecture() != null) {
			sb.append(si.getLecture().getName()).append(" ");
			if (si.getLectureType() != null) {
				sb.append("(").append(si.getLectureType().getName()).append(")").append(" ");
			}
		}		
		if (si.getLecturer() != null) {
			sb.append(si.getLecturer().getShortName()).append(" ");
		}
		sb.append(si.getRoom()).append(" ");
		sb.append("</br>").append("\n");
	}
	
	public void startDay(StringBuilder sb, int day) {
		sb.append("<!-- starting day -->\n");
		sb.append("<table cellspacing='0px' cellpadding='0px' style='width:800px;border-width:0px; border-style:solid;border-collapse:collapse;'>").append("\n")
		.append("<tr>").append("\n")
		.append("<td style='border-width:0px;border-style:double;text-align:center;'>").append("\n")
		.append(CalendarUtils.getWeekDayName(day)).append("\n")
		.append("</td>").append("\n")
		.append("</tr>").append("\n");
	}
	
	public void endDay(StringBuilder sb) {
		sb.append("<!-- ending day -->\n");
		sb.append("</table>").append("</br>").append("\n");
	}

	public void startLectureSession(StringBuilder sb) {
		sb.append("<!-- starting lecture session -->\n");
		sb.append("<tr>").append("\n")
		.append("<td style='border-width:1px;border-style:solid'>").append("\n");
	}

	public void endLectureSession(StringBuilder sb) {
		sb.append("<!-- ending lecture session -->\n");
		sb.append("</td>").append("\n")
		.append("</tr>").append("\n");
	}

	public void startLecture(StringBuilder sb) {
		sb.append("<!-- starting lecture -->\n");
		
	}

	public void endLecture(StringBuilder sb) {
		sb.append("<!-- ending lecture -->\n");
		
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
