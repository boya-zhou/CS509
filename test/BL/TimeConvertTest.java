package BL;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.TimeZone;

import org.junit.Test;

public class TimeConvertTest {
	
	@Test
	public void testgetBeginOfZonedDayInGMT() {
		int year = 2017;
		int month = 12;
		int day = 13;
		ZonedDateTime zdt =TimeConvert.getBeginOfZonedDayInGMT(year, month, day, TimeZone.getTimeZone("America/Chicago"));
		assertEquals(LocalDate.of(year, month, day), zdt.toLocalDate());
		assertEquals(LocalTime.of(0, 0, 0, 0), zdt.toLocalTime());
		assertEquals(TimeZone.getTimeZone("America/Chicago").toZoneId(), zdt.getZone());
	}
	
	@Test
	public void testgetEndOfZonedDayInGMT() {
		int year = 2017;
		int month = 12;
		int day = 13;
		ZonedDateTime zdt =TimeConvert.getEndOfZonedDayInGMT(year, month, day, TimeZone.getTimeZone("America/Chicago"));
		assertEquals(LocalDate.of(year, month, day), zdt.toLocalDate());
		assertEquals(LocalTime.of(23, 59, 59), zdt.toLocalTime());
		assertEquals(TimeZone.getTimeZone("America/Chicago").toZoneId(), zdt.getZone());
	}
	
	@Test
	public void testlocalToGMT() throws ClassNotFoundException, IOException {
		int year = 2017;
		int month = 12;
		int day = 13;
		int hour = 18;
		int min = 12;
		ZonedDateTime zdt = TimeConvert.localToGMT(year, month, day, hour, min, "BOS");
		assertEquals(LocalDate.of(year, month, day), zdt.toLocalDate());
		assertEquals(LocalTime.of(hour+5, min, 0), zdt.toLocalTime());
		assertEquals(ZoneId.of("Z"), zdt.getZone());
	}
	
	@Test
	public void testlocalToGMTDayChange() throws ClassNotFoundException, IOException {
		int year = 2017;
		int month = 12;
		int day = 13;
		int hour = 23;
		int min = 12;
		ZonedDateTime zdt = TimeConvert.localToGMT(year, month, day, hour, min, "BOS");
		assertEquals(LocalDate.of(year, month, day + 1), zdt.toLocalDate());
		assertEquals(LocalTime.of(hour + 5 - 24, min, 0), zdt.toLocalTime());
		assertEquals(ZoneId.of("Z"), zdt.getZone());
	}

	@Test
	public void testlocalToGMTYearChange() throws ClassNotFoundException, IOException {
		int year = 2017;
		int month = 12;
		int day = 31;
		int hour = 23;
		int min = 12;
		ZonedDateTime zdt = TimeConvert.localToGMT(year, month, day, hour, min, "BOS");
		assertEquals(LocalDate.of(year+1, month +1 - 12, day + 1 - 31), zdt.toLocalDate());
		assertEquals(LocalTime.of(hour + 5 - 24, min, 0), zdt.toLocalTime());
		assertEquals(ZoneId.of("Z"), zdt.getZone());
	}

	@Test
	public void testreadCodeZone() throws ClassNotFoundException, IOException {
		Map<String, String> zonemap = TimeConvert.readCodeZone();
		assertEquals(zonemap.get("BOS"), "America/New_York");
		assertEquals(zonemap.get("ORD"), "America/Chicago");
	}

}
