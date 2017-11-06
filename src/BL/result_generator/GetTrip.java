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
	
	public static void main(String[] args) throws IOException {
		
		long tStart = System.currentTimeMillis();
		
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
		
		
		System.out.println(getTrip(deCode, deDate, 0, aCode, aDate, 0));
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds);
		
	}
	
	public static ArrayList<Trip> getTrip(String deCode, Date deDate, int deWin, String aCode, Date aDate, int aWin) throws IOException{
		
		ArrayList<Trip> res = new ArrayList<Trip>();
		
		ArrayList<Leg_Trip> singleRes = GetOneWayResult.getResult(deCode, deDate, deWin, aCode, aDate, aWin);
		
		for (Leg_Trip legTrip: singleRes) {
			res.add(new Trip(new ArrayList<Leg_Trip>(Arrays.asList(new Leg_Trip[] {legTrip}))));
		}
		
		
		return res;
		
	}
	
	public static ArrayList<Trip> getTrip(String deCode, Date deDate, int deWin, String aCode, Date aDate, int aWin, String deCode1, Date deDate1, int deWin1, String aCode1, Date aDate1, int aWin1) throws IOException{
		
		ArrayList<Trip> res = new ArrayList<Trip>();
		
		ArrayList<Leg_Trip> singleRes = GetOneWayResult.getResult(deCode, deDate, deWin, aCode, aDate, aWin);
		ArrayList<Leg_Trip> singleRes1 = GetOneWayResult.getResult(deCode1, deDate1, deWin1, aCode1, aDate1, aWin1);
		
		for (Leg_Trip legTrip: singleRes) {
			for (Leg_Trip legTrip1: singleRes1) {
				res.add(new Trip(new ArrayList<Leg_Trip>(Arrays.asList(new Leg_Trip[] {legTrip, legTrip1}))));
			}
			
		}
		
		return res;
		
	}
	

}
