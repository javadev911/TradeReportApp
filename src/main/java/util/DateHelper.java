package util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

	/*
	 * @author: Raghavendra Sai Akkinapragada
	 * Creates and return date
	 */
	public Date createDate(int day, int mon, int year) {
		return new GregorianCalendar(year, mon, day).getTime();
	}
	
	/*
	 * @author: Raghavendra Sai Akkinapragada
	 * Gets day of the week for the given Date
	 */
	public int getDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}
}
