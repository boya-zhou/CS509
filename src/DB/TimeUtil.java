package DB;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeUtil {
	
	public static void main(String args[]) {
		int aYear = 2017;
		int aMonth = 12;
		int aDay = 12;
		ZonedDateTime zdt;
		zdt = getBeginOfZonedDayInGMT(aYear, aMonth, aDay, TimeZone.getTimeZone("EST"));
		System.out.println(zdt.withZoneSameInstant(TimeZone.getTimeZone("GMT").toZoneId()));
		zdt = getEndOfZonedDayInGMT(aYear, aMonth, aDay, TimeZone.getTimeZone("EST"));
		System.out.println(zdt.withZoneSameInstant(TimeZone.getTimeZone("GMT").toZoneId()));		
		zdt = getBeginOfZonedDayInGMT(aYear, aMonth, aDay, TimeZone.getTimeZone("GMT"));
		System.out.println(zdt);		
		zdt = getEndOfZonedDayInGMT(aYear, aMonth, aDay, TimeZone.getTimeZone("GMT"));
		System.out.println(zdt);		
	}

	/**
	 * return the beginning of the day of date of the given date
	 * specifically 0 hours 0 minutes 0 seconds
	 * @param year 
	 * @param month begin counting at 1 (January) and end at 12 (December)
	 * @param day begin counting at 1
	 * @return
	 */
	public static ZonedDateTime getBeginOfZonedDayInGMT(int year, int month, int day, TimeZone timezone) {
		Calendar cdr = new GregorianCalendar(year, month-1, day, 0, 0, 0);
		cdr.setTimeZone(timezone);
		LocalDate ldt = LocalDate.of(year, month, day);
		LocalTime beginTime = LocalTime.of(0, 0, 0, 0);
		return ZonedDateTime.of(ldt, beginTime, timezone.toZoneId());
	}
	
	public static ZonedDateTime getEndOfZonedDayInGMT(int year, int month, int day, TimeZone timezone) {
		Calendar cdr = new GregorianCalendar(year, month-1, day, 0, 0, 0);
		cdr.setTimeZone(timezone);
		LocalDate ldt = LocalDate.of(year, month, day);
		LocalTime endTime = LocalTime.of(23, 59, 59);
		return ZonedDateTime.of(ldt, endTime, timezone.toZoneId());
	}
}
