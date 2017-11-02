package result_generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import BL.Flight;
import BL.XMLparser;
import DB.GetData;

// TODO: the layover time range from o.5 hour to 4 hour(30 min to 240 min), is this right? 


public class OneStopOver {
	
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
		
//		System.out.println(generateOneStopOver(deCode, deDate, aCode));
//		System.out.println(generateOneStopOver(deCode, aCode, aDate));
		System.out.println(generateOneStopOver(deCode, deDate, aCode, aDate));
		
		
	}
	
	public static ArrayList<ArrayList<Flight>> generateOneStopOver(String deCode, Date deDate, String aCode) throws IOException{
		
		// find all flight start from deCode
		// remove the one that can direct get to acode
		// for each inter-aCode(also inter-deCode), find the final-deCode equals aCode
		// for all flights above, fliter by layover time restriction
		
		ArrayList<ArrayList<Flight>> oneStop = new ArrayList<>(); 
		
		String deXMLString = GetData.getDepartureFlightInfo(deCode, deDate);
		Set<Flight> deFlightSet = XMLparser.parseFlightSet(deXMLString);
		
		for (Flight f: deFlightSet) {
			if (! f.arrivalCode.equals(aCode)) {
				String interDeCode = f.arrivalCode;
				Date interDeDate = f.arrivalTime;
				
				//TODO: ask the how server return result related with Date
				//TODO: if I ask flight in "2017_12_12", flight in which time range will be return? (2017_12_11 12:00PM ~ 2017_12_13 12:00AM)
				String interDeXMLString = GetData.getDepartureFlightInfo(interDeCode, interDeDate);
				Set<Flight> interDeFlightSet = XMLparser.parseFlightSet(interDeXMLString);
				
				for(Flight secF: interDeFlightSet) {
					//two restrcition: final aCode equals aCode, time between interDeDate and final
					if (secF.arrivalCode.equals(aCode)) {
						
						long timeDiff = TimeUnit.MILLISECONDS.toMinutes(secF.depatureTime.getTime() - f.arrivalTime.getTime()); 
						if ((timeDiff >= lowerTime) & (timeDiff <= upperTime)) {
							
							ArrayList<Flight> validTrip = new ArrayList<>();
							validTrip.add(f);
							validTrip.add(secF);
							oneStop.add(validTrip);
						}
					}
				}
			}
		}
		
		return oneStop;
		
	}
	
	public static ArrayList<ArrayList<Flight>> generateOneStopOver(String deCode, String aCode, Date aDate) throws IOException{
		
		// find all flight end with aCode
		// remove the one that can direct from deCode
		// for each inter-Code(also inter-deCode), find the start deCode equals deCode
		// for all flights above, fliter by layover time restriction
		// TODO: add current date restriction
		
		ArrayList<ArrayList<Flight>> oneStop = new ArrayList<>(); 
		
		String aXMLString = GetData.getArrivalFlightInfo(aCode, aDate);
		Set<Flight> aFlightSet = XMLparser.parseFlightSet(aXMLString);
		
		for (Flight f: aFlightSet) {
			if (!f.depatureCode.equals(deCode)) {
				
				String interACode = f.depatureCode;
				Date interADate = f.depatureTime;
				
				String interAXMLString = GetData.getArrivalFlightInfo(interACode, interADate);
				Set<Flight> interAFlightSet = XMLparser.parseFlightSet(interAXMLString);
				
				for (Flight firF: interAFlightSet) {
					if(firF.depatureCode.equals(deCode)) {
						long timeDiff = TimeUnit.MILLISECONDS.toMinutes(f.depatureTime.getTime() - firF.arrivalTime.getTime()); 
						if ((timeDiff <= upperTime ) & (timeDiff >= lowerTime)) {
							ArrayList<Flight> validTrip = new ArrayList<>();
							validTrip.add(firF);
							validTrip.add(f);
							oneStop.add(validTrip);
						}
					}
				}
				
			}
		}
	
		return oneStop;
		
	}

	public static ArrayList<ArrayList<Flight>> generateOneStopOver(String deCode, Date deDate, String aCode, Date aDate) throws IOException{
		// find all flight start with deCode, remove direct, sort by acode
		// find all flight end with aCode, remove direct, sort by decode
		// join by left-acode and right-decode, then filter by time restriction
		
		ArrayList<ArrayList<Flight>> oneStop = new ArrayList<>(); 
		
		ArrayList<Flight> firF = new ArrayList<>();
		ArrayList<Flight> secF = new ArrayList<>();
		
		String deXMLString = GetData.getDepartureFlightInfo(deCode, deDate);
		Set<Flight> deFlightSet = XMLparser.parseFlightSet(deXMLString);
		
		for (Flight f: deFlightSet) {
			if (!f.arrivalCode.equals(aCode)) {
				firF.add(f);
			}
		}
		
		Collections.sort(firF, new BL.CodeComparatorArrival());
		
		String aXMLString = GetData.getArrivalFlightInfo(aCode, aDate);
		Set<Flight> aFlightSet = XMLparser.parseFlightSet(aXMLString);
		
		for (Flight f: aFlightSet) {
			if (!f.depatureCode.equals(deCode)) {
				secF.add(f);
			}
		}
		
		Collections.sort(secF, new BL.CodeComparatorDeparture());
		
		for (Flight firEle: firF) {
			boolean flag = true;
			for (Flight secEle : secF) {
				if (firEle.arrivalCode.equals(secEle.depatureCode)) {
					long timeDiff = TimeUnit.MILLISECONDS.toMinutes(secEle.depatureTime.getTime() - firEle.arrivalTime.getTime());
					if ( (timeDiff <= upperTime ) & (timeDiff >= lowerTime)) {
						ArrayList<Flight> validTrip = new ArrayList<>();
						validTrip.add(firEle);
						validTrip.add(secEle);
						oneStop.add(validTrip);
					}
					flag = false;
				}else if (flag = false){
					break;
				}else {
					continue;
				}
			}
		}
		
		return oneStop;
		
	}
	
}
