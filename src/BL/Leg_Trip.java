package BL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import DB.GetData;

public class Leg_Trip {
    
	List<Flight> flightList;
    int num_flight;

	Long TotalTime;
    int TripType;
    Set<Airport> allAirports = GetData.getAllAirports();
    
    public Leg_Trip(List<Flight> flightList) {
        this.flightList = flightList;
    }

//    public int getNum_flight() {
//        return num_flight;
//    }
    
    public List<Flight> getFlightList(){
    		return flightList;
    }
    
    public int getTripSize() {
    		return getFlightList().size();
    }
    
    public double getLegTripCoachPrice() {
    	double Coachprice=0.00;
    	for(Flight f:flightList) {
    		Coachprice+=f.getPrice_Coach();
    	}
    	return Coachprice;
    }
    
    public double getLegTripFirstPrice() {
    	double  Firstprice=0.00;
    	for(Flight f:flightList) {
    		Firstprice+=f.getPrice_FirstClass();
    	}
    	return Firstprice;
    }
    
    public String getLegTripStopOver() {
    	
    	String stopover = "";
    	
    	for(int i=1;i<flightList.size();i++) {
    		
    		stopover+="-->"+flightList.get(i).getDepatureCode();    		
    	}
    	return  stopover;
    }

    public String getLegTripDepartCode() {
    	
    	String departcode = "";
    	departcode = flightList.get(0).getDepatureCode();
    	return departcode;
    	
    }
    
    public String getLegTripArrivalCode() {
    	
    	String arrivalcode = "";
    	arrivalcode = flightList.get(flightList.size()-1).arrivalCode;
    	return arrivalcode;
    	
    }
	public String getTotalTime() {
		int hour;
		int minutes;
		int minute;
		String Time;
		Long TotalTime= flightList.get(flightList.size()-1).getArrivalTime().toEpochSecond()-flightList.get(0).getDepartureTime().toEpochSecond();
		hour =(int)(TotalTime/3600);
		minutes=(int)(TotalTime/60);
		minute=minutes-hour*60;	
		Time=Integer.toString(hour)+"h"+Integer.toString(minute)+"m";	
		return Time;		
	}
    
	public Long getTotalTimeToCompare() {
		Long TotalTime = flightList.get(flightList.size()-1).getArrivalTime().toEpochSecond()-flightList.get(0).getDepartureTime().toEpochSecond();
		return TotalTime;
	}
	
	public LocalDateTime getLocalLegDepartTime(ZonedDateTime time1) {
		Airport airport = null;
		for(Airport a: allAirports) {
			if(a.getAirportCode().equals(flightList.get(0).getDepatureCode())) {
				airport = a;
			}
		}
		LocalDateTime time = airport.getLocalTime(time1).toLocalDateTime();
		return time;		
	}
	
	public LocalDateTime getLocalLegArrivalTime(ZonedDateTime time1) {
		Airport airport = null;
		for(Airport a: allAirports) {
			if(a.getAirportCode().equals(flightList.get(flightList.size()-1).getArrivalCode())){
				airport = a;
			}
		}
		LocalDateTime time = airport.getLocalTime(time1).toLocalDateTime();
		return time;
	}
	
	public String LegsegmentTime() {
		//to get the leg trip arrival place local time
		LocalDateTime time;
		String TravelTime = ""; 
		Airport airport = null;
		int i =0;
		for(Flight f: flightList) {
			i+=1;
			for(Airport a:allAirports) {
				if(a.getAirportCode().equals(f.getArrivalCode())) {
					airport = a;
				}
			}
			time = airport.getLocalTime(f.arrivalTime).toLocalDateTime();
			if(i<flightList.size()) {
				TravelTime +="-->"+time.toString();
			}
		}
		return TravelTime+"-->";
	}
	
    public String getAirplaneModel() {
    	//get airplane model for each flight
    	String airplaneModel ="";
    	for(Flight f:flightList) {
    		airplaneModel += "--"+f.airplaneModel;
    	}
    	return airplaneModel+"--";
    }
    
    public String getintervalTime() {
    	String time ="";
    	int hour=0;
    	int minutes = 0;
    	int minute = 0;
    	int hour2 = 0;
    	int minutes2 = 0;
    	int minute2 = 0;
    	
    	for(int i=0;i<flightList.size();i++) {
    		Flight flight =flightList.get(i);
    		Long flighttime = flight.getArrivalTime().toEpochSecond()-flight.getDepartureTime().toEpochSecond();
    		hour = (int) (flighttime/3600);
    		minutes = (int)(flighttime/60);
    		minute = minutes - hour*60;
    		
    		time += "-->"+Integer.toString(hour)+"h" + Integer.toString(minute)+"m";
    		
    		if(i+1<flightList.size()) {
    				
    			Flight flight2 = flightList.get(i+1);
    			Long interval = flight2.getDepartureTime().toEpochSecond()-flight.getArrivalTime().toEpochSecond();
    			hour2 = (int)(interval/3600);
    			minutes2= (int)(interval/60);
    			minute2 = minutes2 - hour2*60;
    			time += "--Stop:"+Integer.toString(hour2)+"h" + Integer.toString(minute2)+"m";
    		}
    	}
    	return time+"-->";
    }
    
    @Override
    public String toString() {
    	
    		String result = "";
		
    		for (Flight flight: flightList) {
    			result += "\t" + flight;
    		}
    			
    		return result;
    	
    }
    
}
