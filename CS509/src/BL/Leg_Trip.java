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

    
    @Override
    public String toString() {
    	
    		String result = "";
		
    		for (Flight flight: flightList) {
    			result += "\t" + flight;
    		}
    			
    		return result;
    	
    }
    
}
