package BL.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import BL.Flight;
import BL.Leg_Trip;
import DB.GetData;

public class TwoStopOver {
	
	static long lowerTime = 30;
	static long upperTime = 240;
		
	public static boolean validTimeRes(ZonedDateTime d1, ZonedDateTime d2) {
		long timeDiff = TimeUnit.MILLISECONDS.toMinutes(d1.toEpochSecond() - d2.toEpochSecond()); 
		if ((timeDiff >= lowerTime) & (timeDiff <= upperTime)) {
			return true;
		}
		return false;
	}
	/**
	 * Get valid two stop leg trip based on departure airport code,  local departure time, arrival airport code
	 * @param deCode - The departure airport code
	 * @param deDate - The departure time in local time
	 * @param aCode - The arrival airport code
	 * @return A list of leg trip which contain two stopover
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	public static ArrayList<Leg_Trip> generateTwoStopOver(String deCode, LocalDate deDate, String aCode) throws IOException, ClassNotFoundException{
		// find fir set of valid flights, remove straight
		// find sec set of valid flights, remove stright
		// find third valid flight
		ArrayList<Leg_Trip> twoStop = new ArrayList<>();

		Set<Flight> deFlightSet = HashFlight.getDeFlight(deCode, deDate);

		for (Flight f : deFlightSet) {
			if (!f.arrivalCode.equals(aCode)) {
				Set<Flight> firDeFlightSet = HashFlight.findFlightsAfter(f, lowerTime, upperTime);
				for (Flight secF : firDeFlightSet) {
					// two restriction: final aCode equals aCode, time between interDeDate and final
					if ((!secF.arrivalCode.equals(aCode)) & (!secF.arrivalCode.equals(deCode))) {
						Set<Flight> secDeFlightSet = HashFlight.findFlightsAfter(secF, lowerTime, upperTime);
						for (Flight thF : secDeFlightSet) {
							if (thF.arrivalCode.equals(aCode)) {
								ArrayList<Flight> validTrip = new ArrayList<>();
								validTrip.add(f);
								validTrip.add(secF);
								validTrip.add(thF);
								twoStop.add(new Leg_Trip(validTrip));
							}
						}
					}
				}
			}
		}

		return twoStop;
		
	}
	
}
