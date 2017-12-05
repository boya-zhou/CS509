package BL.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import BL.Flight;
import BL.Leg_Trip;
import DB.GetData;

public class GetOneWayResult {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = 12;
		int deDay = 12;
		
		LocalDate deDate = LocalDate.of(deYear, deMonth, deDay);
		
		Set<Flight> deFlightSet = GetData.getDepartureFlightInfo(deCode, deDate);				
		
		String aCode = "DEN";
		int aYear = 2017;
		int aMonth = 12;
		int aDay = 12;
		
		LocalDate aDate = LocalDate.of(aYear, aMonth, aDay);
		
		System.out.println(getResult(deCode, deDate, aCode));
	}
	
	/**
	 * get list of leg of trip with zero, one, two stop over 
	 * @param deCode - The departure airport code
	 * @param deDate - The departure time in local time
	 * @param aCode - The arrival airport code
	 * @return A list of leg trip which contain zero, one, two stopover
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
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
