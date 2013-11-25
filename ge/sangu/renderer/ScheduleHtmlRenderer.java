package ge.sangu.renderer;

import ge.sangu.model.ScheduleItem;
import ge.sangu.utils.CalendarUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScheduleHtmlRenderer {
	
	private boolean appendHTMLHeader = true;

	private boolean appendEmptyDays = true;
		
	List<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();
	
	public String render() {
		for (ScheduleItem si : scheduleItems) {
			if (si.getDay() == null) {
				si.setDay(7);
			}
		}
		Collections.sort(scheduleItems);
		StringBuilder sb = new StringBuilder();
		if (appendHTMLHeader) {
			appendHTMLHeader(sb);
		}
		int currentDay = 1;
		State s = State.NONE;
		ScheduleItem oldSi = null;
		for (ScheduleItem si : scheduleItems) {
			if (si.getDay() != currentDay) {
				switch (s) {
				case LECTURE:
					endLecture(sb);
				case LECTURE_SESSION:
					endLectureSession(sb);
				case DAY:
					endDay(sb);
					break;
				case NONE:
				}

				if (appendEmptyDays) {
					currentDay++;
					while (++currentDay < si.getDay()) {
						startDay(sb, currentDay);
						endDay(sb);
					}
				}
				
				startDay(sb, si.getDay());
				startLectureSession(sb);
				startLecture(sb);
				appendLecture(sb, si);
				endLecture(sb);
				oldSi = si;
				s = State.LECTURE;
			} else {
				if (!ScheduleItem.isSameSession(si, oldSi)) {
					switch (s) {
					case LECTURE:
						endLecture(sb);
					case LECTURE_SESSION:
						endLectureSession(sb);
					case DAY:
					case NONE:
					}
					startLectureSession(sb);
				}
				startLecture(sb);
				appendLecture(sb, si);
				endLecture(sb);
				oldSi = si;
			}
			sb.append(si.toString()).append("<br/>");
		}		
		if (appendHTMLHeader) {
			appendHTMLFooter(sb);
		}
		return sb.toString();
	}
	
	public void appendHTMLHeader(StringBuilder sb) {
		sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
		sb.append("<HTML><HEAD>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append("</HEAD><BODY>");
	}

	public void appendHTMLFooter(StringBuilder sb) {
		sb.append("</BODY></HTML>");
	}
	
	public void appendLecture(StringBuilder sb, ScheduleItem si) {
		
	}
	
	public void startDay(StringBuilder sb, int day) {
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>");
		sb.append(CalendarUtils.getWeekDayName(day));
		sb.append("</td>");
		sb.append("</tr>");
	}
	
	public void endDay(StringBuilder sb) {
		sb.append("</table>");
	}

	public void startLectureSession(StringBuilder sb) {
		sb.append("<tr>");
		sb.append("<td>");
	}

	public void endLectureSession(StringBuilder sb) {
		sb.append("</td>");
		sb.append("</tr>");
	}

	public void startLecture(StringBuilder sb) {
		
	}

	public void endLecture(StringBuilder sb) {
		
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
