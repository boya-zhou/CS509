package BL.result_generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import BL.Flight;
import DB.GetData;

public class ZeroStopOver {
	
	public static void main(String[] args) throws IOException {
				
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = Calendar.DECEMBER;
		int deDay = 12;
		
		Date deDate = new GregorianCalendar(deYear, deMonth, deDay).getTime();
		
		Set<Flight> deFlightSet = GetData.getDepartureFlightInfo(deCode, deDate);		
		System.out.println(deFlightSet);
		
		
		String aCode = "DEN";
		int aYear = 2017;
		int aMonth = Calendar.DECEMBER;
		int aDay = 12;
		
		Date aDate = new GregorianCalendar(aYear, aMonth, aDay).getTime();
		
		Set<Flight> aFlightSet = GetData.getArrivalFlightInfo(aCode, aDate);		
		System.out.println(aFlightSet);		
		
		
		System.out.println(generateZeroStopOver(deCode, deDate, aCode));
		System.out.println(generateZeroStopOver(deCode, aCode, aDate)); 
		System.out.println(generateZeroStopOver(deCode, deDate, aCode, aDate)); 
		
	}
	
	// 
	public static ArrayList<Flight> generateZeroStopOver(String deCode, Date deDate, String aCode) throws IOException{
		
		// only give de airport, serach for arrival airport
		ArrayList<Flight> zeroStop = new ArrayList<>(); 
		
		Set<Flight> deFlightSet = GetData.getDepartureFlightInfo(deCode, deDate);
		
		for (Flight f: deFlightSet) {
			if (aCode.equals(f.arrivalCode)) {
				zeroStop.add(f);
			}
		}
		
		return zeroStop;
		
	}
	
	public static ArrayList<Flight> generateZeroStopOver(String deCode, String aCode, Date aDate) throws IOException{
		
		// only give a airport, search for de airport
		ArrayList<Flight> zeroStop = new ArrayList<>(); 
		
		Set<Flight> aFlightSet = GetData.getArrivalFlightInfo(aCode, aDate);
		
		for (Flight f: aFlightSet) {
			if (deCode.equals(f.depatureCode)) {
				zeroStop.add(f);
			}
		}
		
		return zeroStop;
		
	}
	
	public static ArrayList<Flight> generateZeroStopOver(String deCode, Date deDate, String aCode, Date aDate) throws IOException{
		// give both, find if there are same flight number
				
		Set<Flight> deFlightSet = GetData.getDepartureFlightInfo(deCode, deDate);
		
		Set<Flight> aFlightList = GetData.getArrivalFlightInfo(aCode, aDate);
		
		deFlightSet.retainAll(aFlightList);
		return new ArrayList<Flight>(deFlightSet);
		
	}
	
}
