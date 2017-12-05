package BL.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import BL.Flight;
import BL.Leg_Trip;

public class ZeroStopOver {
		
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