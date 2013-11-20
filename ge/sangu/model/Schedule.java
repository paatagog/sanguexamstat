package ge.sangu.model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
	private List<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();

	public List<ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}

	public void setScheduleItems(List<ScheduleItem> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}
}
