package BL.result_generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import BL.Flight;
import BL.XMLparser;
import DB.GetData;

public class TwoStopOver {
	
	static long lowerTime = 30;
	static long upperTime = 240;
	
	public static void main(String[] args) throws IOException {
		
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = Calendar.DECEMBER;
		int deDay = 12;
		
		Date deDate = new GregorianCalendar(deYear, deMonth, deDay).getTime();
		
//		String deXMLString = GetData.getDepartureFlightInfo(deCode, deDate);		
//		Set<Flight> deFlightSet = XMLparser.parseFlightSet(deXMLString);
//		System.out.println(deFlightSet);
		
		String aCode = "DEN";
		int aYear = 2017;
		int aMonth = Calendar.DECEMBER;
		int aDay = 12;
		
		Date aDate = new GregorianCalendar(aYear, aMonth, aDay).getTime();
		
//		String aXMLString = GetData.getArrivalFlightInfo(aCode, aDate);		
//		Set<Flight> aFlightSet = XMLparser.parseFlightSet(aXMLString);
//		System.out.println(aFlightSet);	
		
//		System.out.println(generateTwoStopOver(deCode, deDate, aCode));
//		System.out.println(generateTwoStopOver(deCode, aCode, aDate));
		System.out.println(generateTwoStopOver(deCode, deDate, aCode, aDate));
	
	}
	
	public static boolean validTimeRes(Date d1, Date d2) {
		long timeDiff = TimeUnit.MILLISECONDS.toMinutes(d1.getTime() - d2.getTime()); 
		if ((timeDiff >= lowerTime) & (timeDiff <= upperTime)) {
			return true;
		}
		
		return false;
	}
	
	public static ArrayList<ArrayList<Flight>> generateTwoStopOver(String deCode, Date deDate, String aCode) throws IOException{
		// find fir set of valid flights, remove straight
		// find sec set of valid flights, remove stright
		// find third valid flight
		
		ArrayList<ArrayList<Flight>> twoStop = new ArrayList<>(); 
		
		String deXMLString = GetData.getDepartureFlightInfo(deCode, deDate);
		Set<Flight> deFlightSet = XMLparser.parseFlightSet(deXMLString);
		
		for (Flight f: deFlightSet) {
			if (! f.arrivalCode.equals(aCode)) {
				String firCode = f.arrivalCode;
				Date firDate = f.arrivalTime;
				
				String firDeXMLString = GetData.getDepartureFlightInfo(firCode, firDate);
				Set<Flight> firDeFlightSet = XMLparser.parseFlightSet(firDeXMLString);
				
				for(Flight secF: firDeFlightSet) {
					//two restrcition: final aCode equals aCode, time between interDeDate and final
					if ((!secF.arrivalCode.equals(aCode)) & (!secF.arrivalCode.equals(deCode))) {
						
//						long timeDiff = TimeUnit.MILLISECONDS.toMinutes(secF.depatureTime.getTime() - f.arrivalTime.getTime()); 
						if (validTimeRes(secF.depatureTime, f.arrivalTime)){
							
							String secCode = secF.arrivalCode;
							Date secDate = secF.arrivalTime;
							
							String secDeXMLString = GetData.getDepartureFlightInfo(secCode, secDate);
							Set<Flight> secDeFlightSet = XMLparser.parseFlightSet(secDeXMLString);
							
							for (Flight thF: secDeFlightSet) {
								if (thF.arrivalCode.equals(aCode)) {
									
									if (validTimeRes(thF.depatureTime, secF.arrivalTime)){
										
										ArrayList<Flight> validTrip = new ArrayList<>();
										validTrip.add(f);
										validTrip.add(secF);
										validTrip.add(thF);
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
	
	public static ArrayList<ArrayList<Flight>> generateTwoStopOver(String deCode, String aCode, Date aDate) throws IOException{
		// find first set of valid from acode and aDate, remove straight
		// find second set of valid from acode and aDate, remove straight
		// find third valid flight
		
		ArrayList<ArrayList<Flight>> twoStop = new ArrayList<>(); 
		String zeroXMLString = GetData.getArrivalFlightInfo(aCode, aDate);
		Set<Flight> zeroAFlightSet = XMLparser.parseFlightSet(zeroXMLString);
		
		for (Flight zeroF: zeroAFlightSet) {
			if (!deCode.equals(zeroF.depatureCode)) {
				String zeroCode = zeroF.depatureCode;
				Date zeroDate = zeroF.depatureTime;
				
				String firDeXMLString = GetData.getArrivalFlightInfo(zeroCode, zeroDate);
				Set<Flight> firAFlightSet = XMLparser.parseFlightSet(firDeXMLString);
				
				for(Flight firF: firAFlightSet) {
					
					if ((!firF.depatureCode.equals(deCode)) & (!firF.depatureCode.equals(aCode))) {
						if (validTimeRes(zeroF.depatureTime, firF.arrivalTime)){
							
							String firCode = firF.depatureCode;
							Date firDate = firF.depatureTime;
							
							String secAXMLString = GetData.getArrivalFlightInfo(firCode, firDate);
							Set<Flight> secAFlightSet = XMLparser.parseFlightSet(secAXMLString);
							
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
	
	
	public static ArrayList<ArrayList<Flight>> generateTwoStopOver(String deCode, Date dDate, String aCode, Date aDate) throws IOException{
		// generate valid flight  from decode and dDate
		// generate calid flight from acode and adate
		// iter first set, iter sec set, restrict by one hour
		
		ArrayList<ArrayList<Flight>> twoStop = new ArrayList<>(); 
		
		String zeroDeXMLString = GetData.getDepartureFlightInfo(deCode, dDate);
		Set<Flight> zeroDeFlightSet = XMLparser.parseFlightSet(zeroDeXMLString);
		
		String secAXMLString = GetData.getDepartureFlightInfo(aCode, aDate);
		Set<Flight> secAFlightSet = XMLparser.parseFlightSet(secAXMLString);
		
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
