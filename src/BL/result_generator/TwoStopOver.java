package BL.result_generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
		
		Date deDate = new GregorianCalendar(deYear, deMonth, deDay).getTime();
		
		String aCode = "DEN";
		int aYear = 2017;
		int aMonth = Calendar.DECEMBER;
		int aDay = 12;
		
		Date aDate = new GregorianCalendar(aYear, aMonth, aDay).getTime();		
		
		long tStart = System.currentTimeMillis();
		
		System.out.println(generateTwoStopOver(deCode, deDate, aCode));
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds);
	
	}
	
	public static boolean validTimeRes(Date d1, Date d2) {
		long timeDiff = TimeUnit.MILLISECONDS.toMinutes(d1.getTime() - d2.getTime()); 
		if ((timeDiff >= lowerTime) & (timeDiff <= upperTime)) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<Leg_Trip> generateTwoStopOver(String deCode, Date deDate, String aCode) throws IOException, ClassNotFoundException{
		// find fir set of valid flights, remove straight
		// find sec set of valid flights, remove stright
		// find third valid flight
		
		Map<String, Set<Flight>> cachedFlight = HashFlight.readFlightMap();
		ArrayList<Leg_Trip> twoStop = new ArrayList<>(); 
		
		Set<Flight> deFlightSet = HashFlight.getDeFlight(cachedFlight, deCode, deDate);
		
		for (Flight f: deFlightSet) {
			if (! f.arrivalCode.equals(aCode)) {
				String firCode = f.arrivalCode;
				Date firDate = f.arrivalTime;
				
				Set<Flight> firDeFlightSet = HashFlight.getDeFlight(cachedFlight, firCode, firDate);
				
				for(Flight secF: firDeFlightSet) {
					//two restrcition: final aCode equals aCode, time between interDeDate and final
					if ((!secF.arrivalCode.equals(aCode)) & (!secF.arrivalCode.equals(deCode))) {
						
						if (validTimeRes(secF.depatureTime, f.arrivalTime)){
							
							String secCode = secF.arrivalCode;
							Date secDate = secF.arrivalTime;
							
							Set<Flight> secDeFlightSet = HashFlight.getDeFlight(cachedFlight, secCode, secDate);
							
							for (Flight thF: secDeFlightSet) {
								if (thF.arrivalCode.equals(aCode)) {
									
									if (validTimeRes(thF.depatureTime, secF.arrivalTime)){
										
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
			}
		}
		
		return twoStop;
		
	}
	
	public static ArrayList<ArrayList<Flight>> generateTwoStopOver(String deCode, String aCode, Date aDate) throws IOException{
		// find first set of valid from acode and aDate, remove straight
		// find second set of valid from acode and aDate, remove straight
		// find third valid flight
		
		ArrayList<ArrayList<Flight>> twoStop = new ArrayList<>(); 
		Set<Flight> zeroAFlightSet = GetData.getArrivalFlightInfo(aCode, aDate);
		
		for (Flight zeroF: zeroAFlightSet) {
			if (!deCode.equals(zeroF.depatureCode)) {
				String zeroCode = zeroF.depatureCode;
				Date zeroDate = zeroF.depatureTime;
				
				Set<Flight> firAFlightSet = GetData.getArrivalFlightInfo(zeroCode, zeroDate);
				
				for(Flight firF: firAFlightSet) {
					
					if ((!firF.depatureCode.equals(deCode)) & (!firF.depatureCode.equals(aCode))) {
						if (validTimeRes(zeroF.depatureTime, firF.arrivalTime)){
							
							String firCode = firF.depatureCode;
							Date firDate = firF.depatureTime;
							
							Set<Flight> secAFlightSet = GetData.getArrivalFlightInfo(firCode, firDate);
							
							for (Flight secF: secAFlightSet) {
								
								if (secF.depatureCode.equals(deCode)) {
									if (validTimeRes(firF.depatureTime, secF.arrivalTime)){
										
										ArrayList<Flight> validTrip = new ArrayList<>();
										validTrip.add(secF);
										validTrip.add(firF);
										validTrip.add(zeroF);
										twoStop.add(validTrip);
									
									}
								}
								
							}
							
						}
					}
					
				}
			}
		}
		
		return twoStop;
		
	}
		
	public static ArrayList<ArrayList<Flight>> generateTwoStopOver(String deCode, Date deDate, String aCode, Date aDate) throws IOException, ClassNotFoundException{
		 		// generate valid flight  from decode and dDate
		 		// generate calid flight from acode and adate
		 		// iter first set, iter sec set, restrict by one hour
		 		
		 		ArrayList<ArrayList<Flight>> twoStop = new ArrayList<>(); 
		 		
		 		Map<String, Set<Flight>> cachedFlight = HashFlight.readFlightMap();
		 		
		 		Set<Flight> zeroDeFlightSet = HashFlight.getDeFlight(cachedFlight, deCode, deDate);
		 		Set<Flight> secAFlightSet = HashFlight.getAFlight(cachedFlight, aCode, aDate);
		 		
		 		for (Flight zeroF: zeroDeFlightSet) {
		 			
		 			if (! zeroF.arrivalCode.equals(aCode)) {
		 				
		 				for (Flight secF: secAFlightSet) {
		 					
		 					if (! secF.depatureCode.equals(deCode)) {
		 						// find aDate for zeroF, find deDate for secF, diff should larger than two lowertime
		 						
		 						Date zeroADate = zeroF.arrivalTime;
		 						Date secDeDate = secF.depatureTime;
		 						
		 						long timeDiff = TimeUnit.MILLISECONDS.toMinutes(secDeDate.getTime() - zeroADate.getTime()); 
		 					
		 						if (timeDiff >= 2* lowerTime) {
		 							
		 							String zeroACode = zeroF.arrivalCode;
		 							String secDeCode = secF.depatureCode;
		 							
		 							ArrayList<Flight> firFlight = ZeroStopOver.generateZeroStopOver(zeroACode, zeroADate, secDeCode, secDeDate);
		 							
		 							for (Flight firF: firFlight) {
		 								
		 								if (validTimeRes(firF.arrivalTime, zeroADate) & validTimeRes(secDeDate, firF.depatureTime)) {
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
