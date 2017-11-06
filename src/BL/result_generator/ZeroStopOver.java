package BL.result_generator;

import java.awt.List;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import BL.Flight;
import BL.Leg_Trip;
import BL.XMLparser;
import DB.GetData;

public class ZeroStopOver {
	
	public static void main(String[] args) throws IOException {
				
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = Calendar.DECEMBER;
		int deDay = 11;
		
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
		System.out.println(generateZeroStopOver(deCode, deDate, 0, aCode, aDate, 0)); 
		
	}
	
	public static ArrayList<Integer> timeWindow(int timeWin) {
		
		ArrayList<Integer> timeWinList = new ArrayList<Integer>();
		
		switch (timeWin) {
	
		case 0:
			timeWinList.add(0);
			break;
		case 1:
			timeWinList.add(-1);
			timeWinList.add(0);
			timeWinList.add(1);
			break;
		case 2:
			timeWinList.add(-2);
			timeWinList.add(-1);
			timeWinList.add(0);
			timeWinList.add(1);
			timeWinList.add(2);
			break;
		case 3:
			timeWinList.add(-3);
			timeWinList.add(-2);
			timeWinList.add(-1);
			timeWinList.add(0);
			timeWinList.add(1);
			timeWinList.add(2);
			timeWinList.add(3);
			break;
		}
		return timeWinList;
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
	
	public static ArrayList<Leg_Trip> generateZeroStopOver(String deCode, String aCode, Date aDate) throws IOException{
		
		// only give a airport, search for de airport
		ArrayList<Leg_Trip> zeroStop = new ArrayList<>(); 
		
		Set<Flight> aFlightSet = GetData.getArrivalFlightInfo(aCode, aDate);
		
		for (Flight f: aFlightSet) {
			if (deCode.equals(f.depatureCode)) {
				ArrayList<Flight> validLeg = new ArrayList<Flight>();
				validLeg.add(f);
				zeroStop.add(new Leg_Trip(validLeg));
			
			}
		}
		
		return zeroStop;
		
	}
	
	public static Calendar dateToCalendar(Date date) {
		
		Calendar tCalendar = Calendar.getInstance();
		tCalendar.setTime(date);
		
		return tCalendar;
	}
	
	public static ArrayList<Leg_Trip> generateZeroStopOver(String deCode, Date deDate,int deWin, String aCode, Date aDate, int aWin) throws IOException{
		// give both, find if there are same flight number
				
		ArrayList<Leg_Trip> zeroStop = new ArrayList<>();
		
		ArrayList<Integer> deWinList = timeWindow(deWin);
		ArrayList<Integer> aWinList = timeWindow(aWin);
		
		deWinList.retainAll(aWinList);
				
		for (Integer deTime: deWinList) {
			
			Calendar newDeDate= (GregorianCalendar) dateToCalendar(deDate).clone();
			newDeDate.add(Calendar.DAY_OF_MONTH, deTime.intValue());
			
			String deXMLString = GetData.getDepartureFlightInfo(deCode, newDeDate.getTime());
			Set<Flight> deFlightSet = XMLparser.parseFlightSet(deXMLString);
			
			String aXMLString = GetData.getArrivalFlightInfo(aCode, newDeDate.getTime());
			Set<Flight> aFlightList = XMLparser.parseFlightSet(aXMLString);
			
			deFlightSet.retainAll(aFlightList);
			
			for (Flight flight: deFlightSet) {
				zeroStop.add(new Leg_Trip(new ArrayList<Flight>(Arrays.asList(new Flight[] {flight}))));
			}

		}
		
		return zeroStop;
		
	}
	
}
