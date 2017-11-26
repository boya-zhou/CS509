package BL;
import java.util.List;

public class Leg_Trip {
    
	List<Flight> flightList;
    int num_flight;

	Long TotalTime;
    int TripType;
    
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
    	arrivalcode = flightList.get(0).getArrivalCode();
    	return arrivalcode;
    	
    }
	public Integer getTotalTime() {
		int Stopover=flightList.size();
    	int hour = 0;
		Long TotalTime= flightList.get(0).getDepartureTime().toEpochSecond()-flightList.get(Stopover).getArrivalTime().toEpochSecond();
		hour = (int) (TotalTime/3600);

		return hour;
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
