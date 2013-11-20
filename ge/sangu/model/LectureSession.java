package ge.sangu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * ლექტორის მიერ ერთი საგნის წაკითხვა ერთიდაიგივე აუდიტორიასთან (ერთ ან რამდენიმე ჯგუფთან) ზედიზედ.
 */
public class LectureSession {
	private List<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();

	public List<ScheduleItem> getScheduleItems() {
		return scheduleItems;
	}

	public void setScheduleItems(List<ScheduleItem> scheduleItems) {
		this.scheduleItems = scheduleItems;
	}
}
