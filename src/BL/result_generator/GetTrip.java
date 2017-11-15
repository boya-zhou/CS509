package BL.result_generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import BL.Leg_Trip;
import BL.Trip;

public class GetTrip {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		long tStart = System.currentTimeMillis();
		
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = Calendar.DECEMBER;
		int deDay = 12;
		
		Date deDate = new GregorianCalendar(deYear, deMonth, deDay).getTime();
		
		String aCode = "DEN";
		int aYear = 2017;
		int aMonth = Calendar.DECEMBER;
		int aDay = 16;
		
		Date roundDate = new GregorianCalendar(aYear, aMonth, aDay).getTime();

//		System.out.println(getOneWayTrip(deCode, deDate, aCode));
		System.out.println(getRoundTrip(deCode, deDate, aCode, roundDate));
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds);
		
	}
	
	public static ArrayList<Trip> getOneWayTrip(String deCode, Date deDate, String aCode) throws IOException, ClassNotFoundException{
		
		ArrayList<Trip> res = new ArrayList<Trip>();
		
		ArrayList<Leg_Trip> singleRes = GetOneWayResult.getResult(deCode, deDate, aCode);
		
		for (Leg_Trip legTrip: singleRes) {
			res.add(new Trip(new ArrayList<Leg_Trip>(Arrays.asList(new Leg_Trip[] {legTrip}))));
		}
		
		
		return res;
		
	}
	
	public static ArrayList<Trip> getRoundTrip(String deCode, Date deDate, String aCode, Date roundDate) throws IOException, ClassNotFoundException{
		
		ArrayList<Trip> res = new ArrayList<Trip>();
		
		ArrayList<Leg_Trip> singleRes = GetOneWayResult.getResult(deCode, deDate, aCode);
		ArrayList<Leg_Trip> singleRes1 = GetOneWayResult.getResult(aCode, roundDate, deCode);
		
		for (Leg_Trip legTrip: singleRes) {
			for (Leg_Trip legTrip1: singleRes1) {
				res.add(new Trip(new ArrayList<Leg_Trip>(Arrays.asList(new Leg_Trip[] {legTrip, legTrip1}))));
			}
			
		}
		
		return res;
		
	}
	

}
