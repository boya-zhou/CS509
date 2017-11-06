package BL.result_generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import BL.Leg_Trip;

public class GetOneWayResult {
	
	public static ArrayList<Leg_Trip> getResult(String deCode, Date deDate, int deWin, String aCode, Date aDate, int aWin) throws IOException {
		
		ArrayList<Leg_Trip> allRes = new ArrayList<>();
		ArrayList<Leg_Trip> zeroStop = ZeroStopOver.generateZeroStopOver(deCode, deDate, deWin, aCode, aDate, aWin);
		
		ArrayList<Leg_Trip> oneStop = OneStopOver.generateOneStopOver(deCode, deDate, aCode, aDate);
		
		ArrayList<Leg_Trip> twoStop = TwoStopOver.generateTwoStopOver(deCode, deDate, aCode, aDate);
		
		allRes.addAll(zeroStop);
		allRes.addAll(oneStop);
		allRes.addAll(twoStop);
		
		return allRes;
		
	}

}
