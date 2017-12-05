package BL.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import BL.Flight;
import BL.Leg_Trip;
import DB.GetData;

public class GetOneWayResult {
		
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
