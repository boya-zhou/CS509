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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class TimeConvert {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		String deCode = "BOS";
		int deYear = 2017;
		int deMonth = Calendar.DECEMBER;
		int deDay = 12;
						
		System.out.println(localToGMT(deYear, deMonth, deDay, 23, 59, deCode));

		ZonedDateTime utcDate = localToGMT(deYear, deMonth, deDay, 23, 59, deCode);
//		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy_MM_dd");
//		Date gmtDate = new Date(utcDate.toOffsetDateTime().toInstant().toEpochMilli());
//		String dateString = dateformat.format(gmtDate);
		
		System.out.println(utcDate.getDayOfMonth());
	}
	
	public static ZonedDateTime localToGMT(int year, int month, int day, int hour, int min, String airPort) throws ClassNotFoundException, IOException {
		Map<String, String> eMap =  readCodeZone();
		ZoneId codeZone = ZoneId.of(eMap.get(airPort));
		
		LocalDateTime localDate = LocalDateTime.of(year, month, day, hour, min);
		ZonedDateTime localDateZone = ZonedDateTime.of(localDate, codeZone);
		
		ZonedDateTime utcDate = localDateZone.withZoneSameInstant(ZoneOffset.UTC);
		
		return utcDate;
		
	}
		
	public static void genCodeZone() throws IOException {
		
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
        System.out.printf("Serialized data is saved in " + filePath.toString());	
		
	}
	
	public static Map<String, String> readCodeZone() throws IOException, ClassNotFoundException {
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
}
