package demo.result_generator;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import BL.Flight;
import BL.result_generator.HashFlight.FlightType;
import DB.GetData;
import BL.result_generator.HashFlight;

public class HashFlightDemo {
	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
		
		HashFlight.emptyCache(FlightType.DEPARTFROM);
		
		String deCode = "AUS";
		
		int deYear = 2017;
		int deMonth = 12;
		int deDay = 11;
		
		LocalDate deDate = LocalDate.of(deYear, deMonth, deDay);
		
		Set<Flight> deFlightSet = GetData.getDepartureFlightInfo(deCode, deDate);
		HashFlight.findFlightsAfter(deFlightSet.iterator().next(), 30, 240);
		HashFlight.findFlightsBefore(deFlightSet.iterator().next(), 30, 240);
		
		String aCode = "DEN";
		
		Map<String, Set<Flight>> cachedFlight = HashFlight.readFlightMap(FlightType.DEPARTFROM);
				
		long tStart = System.currentTimeMillis();
		
		Set<Flight> result1 = HashFlight.getResCache(cachedFlight, "AUS", deDate);
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds);
		
		
		elapsedSeconds = 0;
		tStart = System.currentTimeMillis();
		
		Set<Flight> aFlightSet = GetData.getArrivalFlightInfo("AUS", deDate);
		
		tEnd = System.currentTimeMillis();
		tDelta = tEnd - tStart;
		elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds);

	}
}
