package BL.result_generator;

import java.awt.print.Printable;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import BL.Flight;
import BL.TimeConvert;
import DB.GetData;

public class HashFlight {
	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
		
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = Calendar.DECEMBER;
		int deDay = 11;
		
		Date deDate = new GregorianCalendar(deYear, deMonth, deDay).getTime();
		
		System.out.println(readFlightMap());
		
//		System.out.println(deDate.toString());
//		DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
//		Date date = format.parse(deDate.toString());
//		System.out.println(deDate.toString());
//		
//		Set<Flight> deFlightSet = GetData.getDepartureFlightInfo(deCode, deDate);		
//	
//		String aCode = "DEN";
//		int aYear = 2017;
//		int aMonth = Calendar.DECEMBER;
//		int aDay = 12;
//		
//		Date aDate = new GregorianCalendar(aYear, aMonth, aDay).getTime();		
//		Map<String, Set<Flight>> cachedFlight = readFlightMap();
//				
//		long tStart = System.currentTimeMillis();
//		
//		Set<Flight> result1 = getResCache(cachedFlight, "AUS", aDate);
//		
//		long tEnd = System.currentTimeMillis();
//		long tDelta = tEnd - tStart;
//		double elapsedSeconds = tDelta / 1000.0;
//		System.out.println(elapsedSeconds);
//		
//		
//		elapsedSeconds = 0;
//		tStart = System.currentTimeMillis();
//		
//		Set<Flight> aFlightSet = GetData.getArrivalFlightInfo("AUS", aDate);	
//		
//		tEnd = System.currentTimeMillis();
//		tDelta = tEnd - tStart;
//		elapsedSeconds = tDelta / 1000.0;
//		System.out.println(elapsedSeconds);

	}
	
	public static Set<Flight> getResCache(Map<String, Set<Flight>> cachedFlight, String Code, Date date){
		
		Set<Flight> resultSet = cachedFlight.get(Code.concat(" ").concat(date.toString()));
	
		return resultSet;
	}
	
	public static void saveFlightMap(Map<String, Set<Flight>> cachedFlight) throws IOException {
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path filePath = Paths.get(currentPath.toString(), "sources", "flight");
		
		FileOutputStream fileOut = new FileOutputStream(filePath.toString());
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(cachedFlight);
        out.close();
        fileOut.close();
        System.out.printf("Serialized data is saved in " + filePath.toString());
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static Map<String, Set<Flight>> readFlightMap() throws IOException, ClassNotFoundException {
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path filePath = Paths.get(currentPath.toString(), "sources", "flight");
		Map<String, Set<Flight>> e = new HashMap<>();

		FileInputStream fileIn = new FileInputStream(filePath.toString());
					
		try {
			ObjectInputStream in = new ObjectInputStream(fileIn);
        		e = (Map<String, Set<Flight>>) in.readObject();
	        in.close();
	        fileIn.close();
		} catch (IOException i) {
		      i.printStackTrace();
	    }		
		return e;
	}
	
	public static Set<Flight> getDeFlight(Map<String, Set<Flight>> cachedFlight, String deCode, int year, int month, int day) throws IOException, ClassNotFoundException{
		// 1. transfer local date to GMT instant range
		// 2. get new two date from GMT instant range
		// 3. get two flight set
		// 4. filter flight set by two GMT instant range
		// 5. union and return set
		Set<Flight> deFlightSet = new HashSet<>();
		deFlightSet = HashFlight.getResCache(cachedFlight, deCode, new GregorianCalendar(year, month, day).getTime());
		
		if (deFlightSet== null) {
			ZonedDateTime dateGMTStart = TimeConvert.localToGMT(year, month, day, 0, 0,  deCode);
			ZonedDateTime dateGMTEnd = TimeConvert.localToGMT(year, month, day, 23, 59,  deCode);
			
			int yearStart = dateGMTStart.getYear();
			int monthStart = dateGMTStart.getMonthValue();
			int dayStart = dateGMTStart.getDayOfMonth();
			
			int yearEnd = dateGMTEnd.getYear();
			int monthEnd = dateGMTEnd.getMonthValue();
			int dayEnd = dateGMTEnd.getDayOfMonth();
			
	 		Set<Flight> deFlightSetStart = GetData.getDepartureFlightInfo(deCode, new GregorianCalendar(yearStart, monthStart, dayStart).getTime());
	 		Set<Flight> deFlightSetEnd = GetData.getDepartureFlightInfo(deCode, new GregorianCalendar(yearEnd, monthEnd, dayEnd).getTime());
	 		
	 		Set<Flight> deFlightSetStartFiltered = new HashSet<>();
	 		Set<Flight> deFlightSetEndFiltered = new HashSet<>();
	 		
	 		for (Flight f1: deFlightSetStart) {
	 			if (f1.depatureTime.toInstant().isAfter(dateGMTStart.toInstant())) {
	 				deFlightSetStartFiltered.add(f1);
	 			}
	 		}
	 		
	 		for (Flight f2: deFlightSetEnd) {
	 			if (f2.depatureTime.toInstant().isBefore(dateGMTEnd.toInstant())) {
	 				deFlightSetEndFiltered.add(f2);
	 			}
	 		}
	 		
	 		deFlightSetStartFiltered.addAll(deFlightSetEndFiltered);
	 		deFlightSet = deFlightSetStartFiltered;
	 		
 			cachedFlight.put(deCode.concat(" ").concat(new GregorianCalendar(year, month, day).getTime().toString()), deFlightSet);
 			HashFlight.saveFlightMap(cachedFlight);
		}
				
		return deFlightSet;
	}
	
//	public static Set<Flight> getAFlight(Map<String, Set<Flight>> cachedFlight, String aCode, Date aDate) throws IOException{
//		
//		Set<Flight> aFlightList = HashFlight.getResCache(cachedFlight, aCode, aDate);
// 		if (aFlightList == null) {
// 			aFlightList = GetData.getArrivalFlightInfo(aCode, aDate);
// 			cachedFlight.put(aCode.concat(" ").concat(aDate.toString()), aFlightList);
// 			HashFlight.saveFlightMap(cachedFlight);
// 		}
//		
//		return aFlightList;
//				
//	}

	
}
