package BL;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.sound.midi.Soundbank;

import org.omg.PortableServer.RequestProcessingPolicyOperations;

import BL.result_generator.GetTrip;
import BL.result_generator.ZeroStopOver;

public class ResultFilter {
	
	public static ArrayList<Trip> seat(ArrayList<Trip> TripResult,int seatType){
		//I will put this filter in the GUI
		return null;
	}
	
	/**
	 * Filter a list of trip using stopoverNum
	 * 
	 * @param TripResult 
	 * 			a list of trips we get from the Gettrip function
	 * @param stopNum the maximum stopover number get from the GUI
	 * @return a list of trips in which each trip's stopover number is less then the stopNum is returned
	 */
	
	public static ArrayList<Trip> stopover(ArrayList<Trip> TripResult,int stopNum){
		
		Trip exTrip=new Trip(null);
		ArrayList<Trip> res=new ArrayList<Trip>();
		exTrip=TripResult.get(0);
		if(exTrip.leg_tripList.size()==1) {
			//one-way trip
			for (int i = 0; i < TripResult.size(); i++) {
				Trip each = TripResult.get(i);
				if(each.leg_tripList.get(0).flightList.size()<=stopNum+1){
					res.add(each);
				}
			}
		}
		else if(exTrip.leg_tripList.size()==2) {
			//round trip
			for(int i=0;i<TripResult.size();i++) {
				Trip each = TripResult.get(i);
				if(each.leg_tripList.get(0).flightList.size()<=stopNum+1 && each.leg_tripList.get(1).flightList.size()<=stopNum+1) {
					res.add(each);
				}
			}
		}
		System.out.println(res);
		return res;	
	}
	
	/**
	 * 
	 * @param TripResult
	 * 			a list of trips we get from the Gettrip function
	 * @param start
	 * 			a LocalDateTime get from GUI
	 * @param stop
	 * 			a LocalDateTime get from GUI
	 * @return
	 * 			a list of trip
	 */	
	
	public static ArrayList<Trip> timeWindow(ArrayList<Trip> TripResult, LocalDateTime start ,LocalDateTime stop){
		ArrayList<Trip> res = new ArrayList<Trip>();
		for(int i=0; i<TripResult.size();i++) {
			Trip each = TripResult.get(i);
			LocalDateTime departTime =each.leg_tripList.get(0).getLocalLegDepartTime(each.leg_tripList.get(0).getFlightList().get(0).getDepartureTime());
			if(departTime.compareTo(start)>0 && departTime.compareTo(stop)<0) {
				res.add(each);
			}
		}
		return res;		
	}
	

	
	public static void main(String[] args) throws IOException{
		ArrayList<Trip> TripResult=new ArrayList<>();	
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth =12;
		int deDay = 12;
		
		LocalDate deDate = LocalDate.of(deYear, deMonth, deDay);
		
		String aCode = "DEN";
		int aYear = 2017;
		int aMonth = 12;
		int aDay = 16;
		
		try {
			//TripResult=GetTrip.getRoundTrip(deCode, deDate, aCode, roundDate);
			TripResult=GetTrip.getOneWayTrip(deCode, deDate, aCode);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.ReserveFlight.lock();
		DB.ReserveFlight.unlock();
		
		TripResult.get(0).getDepartureTime();
	}
}
