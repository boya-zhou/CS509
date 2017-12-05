package BL.result_generator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import BL.Airport;
import BL.Flight;
import BL.TimeConvert;
import DB.GetData;

public class HashFlight {
	
	// singleton objects
	private static Map<String, Set<Flight>> cachedFlightDepartFrom = null;
	private static Map<String, Set<Flight>> cachedFlightArriveTo = null;
		
	/**
	 * Empty cache table used to save map relation between key(departure airport and departure local date) and value(list of Flight object)
	 * @param type - departFrom or arriveTo
	 * @throws IOException
	 */
	public static void emptyCache(FlightType type) throws IOException {
		Map<String, Set<Flight>> empty = new HashMap<String, Set<Flight>>();
		saveFlightMap(empty, type);
	}
	
	/**
	 * Get list of flights from a hashtable in memory based on departure airport and departure local date
	 * @param cachedFlight - a hashtable where key is departure airport and departure local date and value is list of Flight object
	 * @param Code - departure airport code
	 * @param date - departure airport local time
	 * @return
	 */
	public static Set<Flight> getResCache(Map<String, Set<Flight>> cachedFlight, String Code, LocalDate date){
		
		Set<Flight> resultSet = cachedFlight.get(Code.concat(" ").concat(date.toString()));
	
		return resultSet;
	}
	
	/**
	 * Save the hashtable in local disk
	 * In hashtable key is departure airport and departure local date and value is list of Flight object
	 * @param cachedFlight - a hashtable where key is departure airport and departure local date and value is list of Flight object
	 * @param type - departFrom or arriveTo
	 * @throws IOException
	 */
	public static void saveFlightMap(Map<String, Set<Flight>> cachedFlight, FlightType type) throws IOException {
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path filePath = Paths.get(currentPath.toString(), "sources", "flight" + type.toString());
		
		//try creating file. this operation will do nothing if the file already exists
		filePath.toFile().createNewFile();
		
		FileOutputStream fileOut = new FileOutputStream(filePath.toString());
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(cachedFlight);
        out.close();
        fileOut.close();
        //System.out.println("Serialized data is saved in " + filePath.toString());
	}
	
	/**
	 * Read hashtable from local disk
	 * In hashtable key is departure airport and departure local date and value is list of Flight object
	 * @param type - departFrom or arriveTo
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Set<Flight>> readFlightMap(FlightType type) throws IOException {
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		Path filePath = Paths.get(currentPath.toString(), "sources", "flight"+type.toString());
		
		//try creating file. this operation will do nothing if the file already exists
		if(filePath.toFile().createNewFile()) {
			Map<String, Set<Flight>> cachedFlight = new HashMap<String, Set<Flight>>();
			saveFlightMap(cachedFlight, type);
		}
		
		FileInputStream fileIn = new FileInputStream(filePath.toString());
        ObjectInputStream in = new ObjectInputStream(fileIn);
		
		try {
	        @SuppressWarnings("unchecked")
	        Map<String, Set<Flight>> e = (Map<String, Set<Flight>>) in.readObject();
			in.close();
	        fileIn.close();
	        return e;
		} catch (ClassNotFoundException ex) {
			in.close();
	        fileIn.close();
			throw new IOException(ex.getMessage());
		}
	}
	
	/**
	 * Get list of flights based on departure airport code or departure local time
	 * Try to get from hashtable first, if can't, request from server and update hashtable to local disk
	 * In hashtable key is departure airport and departure local date and value is list of Flight object
	 * @param deCode the airport code of the departure airport
	 * @param deDate the date of the departure in the deCode airport local time
	 * @return list of flights 
	 * @throws IOException
	 */
	public static Set<Flight> getDeFlight(String deCode, LocalDate deDate) throws IOException{
		if(cachedFlightDepartFrom == null) {
			cachedFlightDepartFrom = readFlightMap(FlightType.DEPARTFROM);	
		}
 		Set<Flight> deFlightSet = HashFlight.getResCache(cachedFlightDepartFrom, deCode, deDate);
  		if (deFlightSet == null || deFlightSet.isEmpty()) {
  	 		//create the midnight and end of day zoned date time
  			Set<Airport> allAirports = GetData.getAllAirports();
  			Airport airport = null;
  			for(Airport a: allAirports) {
  				if(a.getAirportCode().equals(deCode)) {
  					airport = a;
  				}
  			}
  			Map<String, String> codeZone = null;
  			try {
  				codeZone = TimeConvert.readCodeZone();
  			} catch (ClassNotFoundException e) {}
  			ZonedDateTime midnight = ZonedDateTime.of(deDate, LocalTime.of(0, 0, 0), airport.getTimeZone().toZoneId());
  			ZonedDateTime endOfDay = ZonedDateTime.of(deDate, LocalTime.of(23, 59, 59), airport.getTimeZone().toZoneId());
  			
  			ZonedDateTime midnightGMT = midnight.withZoneSameInstant(TimeZone.getTimeZone("GMT").toZoneId());
  			ZonedDateTime endOfDayGMT = endOfDay.withZoneSameInstant(TimeZone.getTimeZone("GMT").toZoneId());
  			
  	 		Set<Flight> midnightFlightSet = GetData.getDepartureFlightInfo(deCode, midnightGMT.toLocalDate());
  	 		Set<Flight> endOfDayFlightSet = GetData.getDepartureFlightInfo(deCode, endOfDayGMT.toLocalDate());		
  	 		
  	 		deFlightSet = new HashSet<Flight>();
  	 		for(Flight flight: midnightFlightSet) {
  	 			if(flight.getDepartureTime().isAfter(midnight)) {
  	 				deFlightSet.add(flight);
  	 			}
  	 		}
  	 		
  	 		for(Flight flight: endOfDayFlightSet) {
  	 			if(flight.getDepartureTime().isBefore(endOfDay)) {
  	 				deFlightSet.add(flight);
  	 			}
  	 		}
  	  		
 			cachedFlightDepartFrom.put(deCode.concat(" ").concat(deDate.toString()), deFlightSet);
 			HashFlight.saveFlightMap(cachedFlightDepartFrom, FlightType.DEPARTFROM);
  		}

		return deFlightSet;
	}
	
	public static Set<Flight> getAFlight(String aCode, LocalDate aDate) throws IOException{

		if(cachedFlightArriveTo == null) {
			cachedFlightArriveTo = readFlightMap(FlightType.ARRIVETO);	
		}
		Map<String, Set<Flight>> cachedFlight = cachedFlightArriveTo;
		
		Set<Flight> aFlightList = HashFlight.getResCache(cachedFlight, aCode, aDate);
 		if (aFlightList == null) {
 			aFlightList = GetData.getArrivalFlightInfo(aCode, aDate);
 			cachedFlight.put(aCode.concat(" ").concat(aDate.toString()), aFlightList);
 			HashFlight.saveFlightMap(cachedFlight, FlightType.ARRIVETO);
 		}
		
		return aFlightList;
				
	}
	
	/**
	 * find flights that arrive before flightAfter within minWaitMinutes and maxWaitMinutes
	 * @param flightAfter
	 * @param minWaitMinutes
	 * @param maxWaitMinutes
	 * @return
	 * @throws IOException
	 */
	public static Set<Flight> findFlightsBefore(Flight flightAfter, long minWaitMinutes, long maxWaitMinutes) throws IOException {
		ZonedDateTime departDateTime = flightAfter.getDepartureTime();
		ZonedDateTime departTimeGMT = departDateTime.withZoneSameLocal(TimeZone.getTimeZone("GMT").toZoneId());
		
		Set<Flight> arriveToday = HashFlight.getAFlight(flightAfter.getDepatureCode(), departTimeGMT.toLocalDate());
		Set<Flight> allFlights = arriveToday;
		//if the hour is less than 4, it is possible to find connecting flight the day before
		if(departTimeGMT.getHour() <  4) {
			LocalDate previousDate = departTimeGMT.toLocalDate().minusDays(1);
			Set<Flight> arriveDayBefore = HashFlight.getAFlight(flightAfter.getDepatureCode(), previousDate);
			allFlights.addAll(arriveDayBefore);
		}
		
		Set<Flight> possibleFlights = new HashSet<Flight>();
		for(Flight f: allFlights) {
			//-1 to lower and +1 to upper so that isAfter and isBefore also include the equal cases
			ZonedDateTime lowerBound = flightAfter.getDepartureTime().minusSeconds(maxWaitMinutes * 60 - 1);
			ZonedDateTime upperBound = flightAfter.getDepartureTime().minusSeconds(minWaitMinutes * 60 + 1);
			if(f.getArrivalTime().isAfter(lowerBound) && f.getArrivalTime().isBefore(upperBound)) {
				possibleFlights.add(f);
			}
		}
		return possibleFlights;
	}
	

	/**
	 * find flights that depart after flightBefore within minWaitMinutes and maxWaitMinutes
	 * @param flightBefore
	 * @param minWaitMinutes
	 * @param maxWaitMinutes
	 * @return
	 * @throws IOException
	 */
	public static Set<Flight> findFlightsAfter(Flight flightBefore, long minWaitMinutes, long maxWaitMinutes) throws IOException {
		ZonedDateTime arriveDateTime = flightBefore.getArrivalTime();
		
		Set<Flight> departToday = HashFlight.getDeFlight(flightBefore.getArrivalCode(), arriveDateTime.toLocalDate());
		Set<Flight> allFlights = departToday;
		//if the hour is greater than 20, it is possible to find connecting flight the next day
		if(arriveDateTime.getHour() >= 20) {
			LocalDate nextDay = arriveDateTime.toLocalDate().plusDays(1);
			Set<Flight> departNextDay = HashFlight.getDeFlight(flightBefore.getArrivalCode(), nextDay);
			allFlights.addAll(departNextDay);
		}
		
		Set<Flight> possibleFlights = new HashSet<Flight>();
		for(Flight f: allFlights) {
			//-1 to lower and +1 to upper so that isAfter and isBefore also include the equal cases (inclusive)
			ZonedDateTime lowerBound = flightBefore.getArrivalTime().plusSeconds(minWaitMinutes * 60 - 1);
			ZonedDateTime upperBound = flightBefore.getArrivalTime().plusSeconds(maxWaitMinutes * 60 + 1);
			if(f.getDepartureTime().isAfter(lowerBound) && f.getDepartureTime().isBefore(upperBound)) {
				possibleFlights.add(f);
			}
		}
		return possibleFlights;
	}
	
	public enum FlightType {
		DEPARTFROM,
		ARRIVETO;
		
		@Override
		public String toString() {
			if(this.equals(FlightType.DEPARTFROM)) {
				return "departFrom";
			} else if(this.equals(FlightType.ARRIVETO)) {
				return ("arriveTo");
			} else return "";
		}
	}
}
