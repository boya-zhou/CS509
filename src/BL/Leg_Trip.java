package BL;
import java.time.ZonedDateTime;
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

	public ZonedDateTime getArrivalTime() {
		ZonedDateTime zdt = flightList.get(0).getArrivalTime();
		for (Flight f : flightList) {
			if (f.getArrivalTime().isAfter(zdt)) {
				zdt = f.getArrivalTime();
			}
		}
		return zdt;
	}
    
	public ZonedDateTime getDepartureTime() {
		ZonedDateTime zdt = flightList.get(0).getDepartureTime();
		for (Flight f : flightList) {
			if (f.getDepartureTime().isBefore(zdt)) {
				zdt = f.getDepartureTime();
			}
		}
		return zdt;
	}
}
