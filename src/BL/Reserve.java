package BL;

import DB.ReserveFlight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reserve{	

	public final static int SLEEP_SECOND = 1;
	
	/**
	 * try to reserve all flight in the trip
	 * seatType must be in this order:
	 * trip.getLeg_tripList().get(0).getFlightList(), 
	 * trip.getLeg_tripList().get(1).getFlightList(),
	 * ...
	 * trip.getLeg_tripList().get(last).getFlightList()
	 * this will try to acquire lock as well as unlock for you
	 * the length of seatType must be the same as the number of all the flights in the trip
	 * @param trip
	 * @param seatType
	 * @return 0 on successful, -1 if failed
	 */
	public static int reserve(Trip trip, List<String> seatType) {
		return(reserve(tripToFlightList(trip), seatType));
	}
	
	/**
	 * call tryReserve multiple times until at successful or exceed maxAttempts
	 * if an attempt fails, it will wait SLEEP_SECOND second(s) before trying again
	 * @param trip
	 * @param seatType
	 * @param maxAttempts max number of attempts
	 * @return 0 if one is successful, -1 if all fails
	 */
	public static int tryReserve(Trip trip, List<String> seatType, int maxAttempts) {
		for(int i = 0; i < maxAttempts; i++) {
			int status = reserve(trip, seatType);
			if (status == 0) {
				return status;
			} else {
				try {
					Thread.sleep(SLEEP_SECOND * 1000);
				} catch (InterruptedException e) {
					//do nothing
				} 
			}
		}
		return -1; 
	}
	
	/**
	 * try to reserve all flight in the list
	 * this will try to acquire lock as well as unlock for you
	 * the length of seatType must be the same as the number of all the flights in the trip
	 * @param trip
	 * @param seatType
	 * @return 0 on successful, -1 if failed
	 */
	public static int reserve(List<Flight> trip, List<String> seatType) {
		try {
			ReserveFlight.lock();
	        ReserveFlight.reserve(trip, seatType);
			ReserveFlight.unlock();
			return 0;
		} catch(IOException e) {
			return -1;
		}
	}
	
	private static List<Flight> tripToFlightList(Trip trip) {
    	List<Flight> allFlights = new ArrayList<Flight>();
    	for(Leg_Trip leg: trip.getLeg_tripList()) {
    		allFlights.addAll(leg.getFlightList());
    	}
    	return allFlights;
	}
}
