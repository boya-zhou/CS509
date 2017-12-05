package BL.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import BL.Flight;
import BL.Leg_Trip;
import DB.GetData;

public class ZeroStopOver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
				
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = 12;
		int deDay = 11;
		
		LocalDate deDate = LocalDate.of(deYear, deMonth, deDay);
		
		Set<Flight> deFlightSet = GetData.getDepartureFlightInfo(deCode, deDate);				
		
		String aCode = "DEN";
		int aYear = 2017;
		int aMonth = 12;
		int aDay = 12;
		
		LocalDate aDate = LocalDate.of(aYear, aMonth, aDay);
				
		System.out.println(generateZeroStopOver(deCode, deDate, aCode));
//		System.out.println(generateZeroStopOver(deCode, aCode, aDate)); 
//		System.out.println(generateZeroStopOver(deCode, deDate, 0, aCode, aDate, 0)); 
		
	}
	
	/**
	 * Get valid zero stop leg trip based on departure airport code,  local departure time, arrival airport code
	 * @param deCode - The departure airport code
	 * @param deDate - The departure time in local time
	 * @param aCode - The arrival airport code
	 * @return A list of leg trip which contain zero stopover
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<Leg_Trip> generateZeroStopOver(String deCode, LocalDate deDate, String aCode) throws IOException, ClassNotFoundException{
		
		// only give de airport, serach for arrival airport
		ArrayList<Leg_Trip> zeroStop = new ArrayList<>();
		
		Set<Flight> deFlightSet = HashFlight.getDeFlight(deCode, deDate);
		
		for (Flight f: deFlightSet) {
			if (aCode.equals(f.arrivalCode)) {
				zeroStop.add(new Leg_Trip(new ArrayList<Flight>(Arrays.asList(new Flight[] {f}))));
			}
		}
		return zeroStop;		
	}
		
}
