package BL.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

import BL.Flight;
import BL.Leg_Trip;
import DB.GetData;

public class GetOneWayResult {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = Calendar.DECEMBER;
		int deDay = 12;
		
		LocalDate deDate = LocalDate.of(deYear, deMonth, deDay);
		
		Set<Flight> deFlightSet = GetData.getDepartureFlightInfo(deCode, deDate);				
		
		String aCode = "DEN";
		int aYear = 2017;
		int aMonth = Calendar.DECEMBER;
		int aDay = 12;
		
		LocalDate aDate = LocalDate.of(aYear, aMonth, aDay);
		
		System.out.println(getResult(deCode, deDate, aCode));
	}
	
	public static ArrayList<Leg_Trip> getResult(String deCode, LocalDate deDate, String aCode) throws IOException, ClassNotFoundException {
		
		ArrayList<Leg_Trip> allRes = new ArrayList<>();
		
		ArrayList<Leg_Trip> zeroStop = ZeroStopOver.generateZeroStopOver(deCode, deDate, aCode);		
		ArrayList<Leg_Trip> oneStop = OneStopOver.generateOneStopOver(deCode, deDate, aCode);		
		ArrayList<Leg_Trip> twoStop = TwoStopOver.generateTwoStopOver(deCode, deDate, aCode);
		
		allRes.addAll(zeroStop);
		allRes.addAll(oneStop);
		allRes.addAll(twoStop);
		
		return allRes;
		
	}

}