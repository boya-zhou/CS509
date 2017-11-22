package BL.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import BL.Leg_Trip;
import BL.Trip;

public class GetTrip {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		long tStart = System.currentTimeMillis();
		
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = 12;
		int deDay = 12;
		
		LocalDate deDate = LocalDate.of(deYear, deMonth, deDay);
		
		String aCode = "ORD";
		int aYear = 2017;
		int aMonth = 12;
		int aDay = 16;
		
		LocalDate roundDate = LocalDate.of(aYear, aMonth, aDay);
		
		//System.out.println(getOneWayTrip(deCode, deDate, aCode));
		System.out.println(getRoundTrip(deCode, deDate, aCode, roundDate));
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds);
		
	}
	
	public static ArrayList<Trip> getOneWayTrip(String deCode, LocalDate deDate, String aCode) throws IOException, ClassNotFoundException{
		
		ArrayList<Trip> res = new ArrayList<Trip>();
		
		ArrayList<Leg_Trip> singleRes = GetOneWayResult.getResult(deCode, deDate, aCode);
		
		for (Leg_Trip legTrip: singleRes) {
			res.add(new Trip(new ArrayList<Leg_Trip>(Arrays.asList(new Leg_Trip[] {legTrip}))));
		}
		
		
		return res;
		
	}
	
	public static ArrayList<Trip> getRoundTrip(String deCode, LocalDate deDate, String aCode, LocalDate roundDate) throws IOException, ClassNotFoundException{
		
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