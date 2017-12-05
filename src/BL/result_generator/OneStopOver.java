package BL.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import BL.Flight;
import BL.Leg_Trip;
import DB.GetData;

public class OneStopOver {
	
	static long lowerTime = 30;
	static long upperTime = 240;
		
	/**
	 * Get valid one stop leg trip based on departure airport code,  local departure time, arrival airport code
	 * @param deCode - The departure airport code
	 * @param deDate - The departure time in local time
	 * @param aCode - The arrival airport code
	 * @return A list of leg trip which contain one stopover
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<Leg_Trip> generateOneStopOver(String deCode, LocalDate deDate, String aCode) throws IOException, ClassNotFoundException{
		// find all flight start from deCode
		// remove the one that can direct get to acode
		// for each inter-aCode(also inter-deCode), find the final-deCode equals aCode
		// for all flights above, fliter by layover time restriction
		ArrayList<Leg_Trip> oneStop = new ArrayList<>(); 
		
		Set<Flight> deFlightSet = HashFlight.getDeFlight(deCode, deDate);
		
		for (Flight f: deFlightSet) {
			if (! f.arrivalCode.equals(aCode)) {
				// March's refactor note: I added a these few lines of code to add the previous day flights if
				// it is possible, please check if it is correct
				Set<Flight> interDeFlightSet = HashFlight.findFlightsAfter(f, lowerTime, upperTime);
				for(Flight secF: interDeFlightSet) {
					//two restriction: final aCode equals aCode, time between interDeDate and final
					if (secF.arrivalCode.equals(aCode)) {
						ArrayList<Flight> validTrip = new ArrayList<>();
						validTrip.add(f);
						validTrip.add(secF);
						oneStop.add(new Leg_Trip(validTrip));
					}
				}
			}
		}
		
		return oneStop;
		
	}
}
