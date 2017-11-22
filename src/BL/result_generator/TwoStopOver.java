package BL.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import BL.Flight;
import BL.Leg_Trip;
import DB.GetData;

public class TwoStopOver {
	
	static long lowerTime = 30;
	static long upperTime = 240;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = Calendar.DECEMBER;
		int deDay = 11;
		
		LocalDate deDate = LocalDate.of(deYear, deMonth, deDay);
		
		String aCode = "DEN";
		int aYear = 2017;
		int aMonth = Calendar.DECEMBER;
		int aDay = 12;
		
		LocalDate aDate = LocalDate.of(aYear, aMonth, aDay);		
		
		long tStart = System.currentTimeMillis();
		
		System.out.println(generateTwoStopOver(deCode, deDate, aCode));
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds);
	
	}
	
	public static boolean validTimeRes(ZonedDateTime d1, ZonedDateTime d2) {
		long timeDiff = TimeUnit.MILLISECONDS.toMinutes(d1.toEpochSecond() - d2.toEpochSecond()); 
		if ((timeDiff >= lowerTime) & (timeDiff <= upperTime)) {
			return true;
		}
		return false;
	}
	
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
	
	public static ArrayList<Leg_Trip> generateTwoStopOver(String deCode, String aCode, LocalDate aDate) throws IOException{
		// find first set of valid from acode and aDate, remove straight
		// find second set of valid from acode and aDate, remove straight
		// find third valid flight

		ArrayList<Leg_Trip> twoStop = new ArrayList<>();
		Set<Flight> zeroAFlightSet = GetData.getArrivalFlightInfo(aCode, aDate);

		for (Flight zeroF : zeroAFlightSet) {
			if (!deCode.equals(zeroF.depatureCode)) {
				Set<Flight> firAFlightSet = HashFlight.findFlightsBefore(zeroF, lowerTime, lowerTime);
				for (Flight firF : firAFlightSet) {
					if ((!firF.depatureCode.equals(deCode)) & (!firF.depatureCode.equals(aCode))) {
						Set<Flight> secAFlightSet = HashFlight.findFlightsBefore(zeroF, lowerTime, lowerTime);
						for (Flight secF : secAFlightSet) {
							if (secF.depatureCode.equals(deCode)) {
								ArrayList<Flight> validTrip = new ArrayList<>();
								validTrip.add(secF);
								validTrip.add(firF);
								validTrip.add(zeroF);
								twoStop.add(new Leg_Trip(validTrip));
							}

						}
					}

				}
			}
		}

		return twoStop;
		
	}
		
	public static ArrayList<ArrayList<Flight>> generateTwoStopOver(String deCode, LocalDate deDate, String aCode, LocalDate aDate) throws IOException, ClassNotFoundException{
		// generate valid flight from decode and dDate
		// generate calid flight from acode and adate
		// iter first set, iter sec set, restrict by one hour

		ArrayList<ArrayList<Flight>> twoStop = new ArrayList<>();
		Set<Flight> zeroDeFlightSet = HashFlight.getDeFlight(deCode, deDate);
		Set<Flight> secAFlightSet = HashFlight.getAFlight(aCode, aDate);

		for (Flight zeroF : zeroDeFlightSet) {
			if (!zeroF.arrivalCode.equals(aCode)) {
				for (Flight secF : secAFlightSet) {
					if (!secF.depatureCode.equals(deCode)) {
						// find aDate for zeroF, find deDate for secF, diff should larger than two
						// lowertime
						ZonedDateTime zeroADate = zeroF.arrivalTime;
						ZonedDateTime secDeDate = secF.departureTime;

						long timeDiff = TimeUnit.MILLISECONDS
								.toMinutes(secDeDate.toEpochSecond() - zeroADate.toEpochSecond());

						if (timeDiff >= 2 * lowerTime) {
							String zeroACode = zeroF.arrivalCode;
							String secDeCode = secF.depatureCode;

							ArrayList<Flight> firFlight = ZeroStopOver.generateZeroStopOver(zeroACode,
									zeroADate.toLocalDate(), secDeCode, secDeDate.toLocalDate());
							for (Flight firF : firFlight) {
								if (validTimeRes(firF.arrivalTime, zeroADate)
										& validTimeRes(secDeDate, firF.departureTime)) {
									ArrayList<Flight> validTrip = new ArrayList<>();
									validTrip.add(zeroF);
									validTrip.add(firF);
									validTrip.add(secF);
									twoStop.add(validTrip);
								}
							}
						}
					}
				}
			}
		}

		return twoStop;
	}
}
