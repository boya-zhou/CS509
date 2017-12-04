package BL;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * this class contains a lot of time conversion functionalities
 *
 */
public class TimeConvert {
	
	/**
	 * get a GMT-equivalent time from the local date time
	 * @param year local year
	 * @param month local month
	 * @param day local day
	 * @param hour local hour
	 * @param min local minute
	 * @param airPort the airport that the local time is at
	 * @return an equivalent ZonedDateTime object in GMT
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static ZonedDateTime localToGMT(int year, int month, int day, int hour, int min, String airPort) throws ClassNotFoundException, IOException {
		Map<String, String> eMap =  readCodeZone();
		ZoneId codeZone = ZoneId.of(eMap.get(airPort));
		
		LocalDateTime localDate = LocalDateTime.of(year, month, day, hour, min);
		ZonedDateTime localDateZone = ZonedDateTime.of(localDate, codeZone);
		
		ZonedDateTime utcDate = localDateZone.withZoneSameInstant(ZoneOffset.UTC);
		
		return utcDate;
		
	}
	
	/**
	 * read the code zone from file
	 * @throws IOException
	 */
	private static void genCodeZone() throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader("sources/airports.csv"));
		
		Map<String, String> codeOffset = new HashMap<>();
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			String[] s = line.split(",");
			codeOffset.put(s[0], s[1]);
		}
		
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path filePath = Paths.get(currentPath.toString(), "sources", "codezone");
		
		FileOutputStream fileOut = new FileOutputStream(filePath.toString());
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(codeOffset);
        out.close();
        fileOut.close();
        System.out.println("Serialized data is saved in " + filePath.toString());	
		
	}
	
	/**
	 * read the codezone from file
	 * @return Map of airport to timezone
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Map<String, String> readCodeZone() throws IOException, ClassNotFoundException {
		genCodeZone();
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path filePath = Paths.get(currentPath.toString(), "sources", "codezone");
		
		FileInputStream fileIn = new FileInputStream(filePath.toString());
        ObjectInputStream in = new ObjectInputStream(fileIn);
        	
        @SuppressWarnings("unchecked")
		Map<String, String> e = (Map<String, String>) in.readObject();
		in.close();
        fileIn.close();
        return e;
	}


	/**
	 * return the beginning of the day of date of the given date
	 * specifically 0 hours 0 minutes 0 seconds
	 * @param year the year
	 * @param month begin counting at 1 (January) and end at 12 (December)
	 * @param day begin counting at 1
	 * @return a ZonedDateTime representing the input
	 */
	public static ZonedDateTime getBeginOfZonedDayInGMT(int year, int month, int day, TimeZone timezone) {
		LocalDate ldt = LocalDate.of(year, month, day);
		LocalTime beginTime = LocalTime.of(0, 0, 0, 0);
		return ZonedDateTime.of(ldt, beginTime, timezone.toZoneId());
	}
	
	/**
	 * return the beginning of the day of date of the given date
	 * specifically 23 hours 59 minutes 59 seconds
	 * (note will not set nanosecond, i.e. nanosecond = 0)
	 * @param year the year
	 * @param month begin counting at 1 (January) and end at 12 (December)
	 * @param day begin counting at 1
	 * @return a ZonedDateTime representing the input
	 */
	public static ZonedDateTime getEndOfZonedDayInGMT(int year, int month, int day, TimeZone timezone) {
		LocalDate ldt = LocalDate.of(year, month, day);
		LocalTime endTime = LocalTime.of(23, 59, 59);
		return ZonedDateTime.of(ldt, endTime, timezone.toZoneId());
	}
}
