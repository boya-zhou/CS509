package BL;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import BL.result_generator.GetTrip;

public class ResultFilter {
	
	public static ArrayList<Trip> seat(ArrayList<Trip> TripResult,int seatType){
		//I will put this filter in the GUI
		return null;
	}
	
	public static ArrayList<Trip> stopover(ArrayList<Trip> TripResult,int stopNum){
		
		Trip exTrip=new Trip(null);
		ArrayList<Trip> res=new ArrayList<Trip>();
		exTrip=TripResult.get(0);
		if(exTrip.leg_tripList.size()==1) {
			//one-way trip
			for (int i = 0; i < TripResult.size(); i++) {
				Trip each = TripResult.get(i);
				if(each.leg_tripList.get(0).flightList.size()==stopNum+1){
					res.add(each);
				}
			}
		}
		else if(exTrip.leg_tripList.size()==2) {
			//round trip
			for(int i=0;i<TripResult.size();i++) {
				Trip each = TripResult.get(i);
				if(each.leg_tripList.get(0).flightList.size()==stopNum+1 && each.leg_tripList.get(1).flightList.size()==stopNum+1) {
					res.add(each);
				}
			}
		}
		System.out.println(res);
		return res;	
	}
	
	public static ArrayList<Trip> timeWindow(ArrayList<Trip> TripResult,Date startTime,Date stopTime){
		long from = startTime.getTime();
		long to = stopTime.getTime();
		ArrayList<Trip> res=new ArrayList<Trip>();
		for (int i = 0; i < TripResult.size(); i++) {
			Trip each = TripResult.get(i);
			if(each.leg_tripList.get(0).flightList.get(0).depatureTime.getTime()>=from && each.leg_tripList.get(0).flightList.get(0).
					depatureTime.getTime()<=to) {
				res.add(each);
			}
		}
 		return res;
	}
	
	public static void main(String[] args){
		ArrayList<Trip> TripResult=new ArrayList<>();	
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

		try {
			TripResult=GetTrip.getRoundTrip(deCode, deDate, aCode, roundDate);
			//TripResult=GetTrip.getOneWayTrip(deCode, deDate, aCode);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stopover(TripResult, 1);
	}
}
