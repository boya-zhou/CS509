package BL;

import java.time.ZonedDateTime;
import java.util.List;

import org.junit.validator.PublicClassValidator;

public class Trip {
	List<Leg_Trip> leg_tripList;


	public ZonedDateTime departureTime;
	public ZonedDateTime arrivalTime;


	public Trip(List<Leg_Trip> leg_tripList) {
        this.leg_tripList = leg_tripList;
    }
    public List<Leg_Trip> getLeg_tripList() {
		return leg_tripList;
	}
    
    public double getTripCoachPrice() {
    	double price=0.00;
    	for(Leg_Trip l:leg_tripList) {
    		price+=l.getLegTripCoachPrice();
    	}
    	return price;
    }
    
    public double getTripFirstPrice() {
    	double price=0.00;
    	for(Leg_Trip l:leg_tripList) {
    		price+=l.getLegTripFirstPrice();
    	}
    	return price;
    }

    /**
     * @return departure time of the trip
     */
	public ZonedDateTime getDepartureTime() {
		ZonedDateTime departureTime =leg_tripList.get(0).getFlightList().get(0).getDepartureTime();
		return departureTime;
	}
	
	/**
	 * 
	 * @return arrival time of a one-way trip
	 */
	public ZonedDateTime getArrivalTime() {
		ZonedDateTime arrivalTime =leg_tripList.get(0).getFlightList().get(leg_tripList.get(0).getTripSize()-1).getArrivalTime();
		return arrivalTime;
	}
	
	/**
	 * 
	 * @return departure time of the second leg for a round-way trip 
	 */
	public ZonedDateTime getDepartureTime2() {
		ZonedDateTime departureTime = leg_tripList.get(1).flightList.get(0).getDepartureTime();
		return departureTime;
	}
	
	/**
	 * 
	 * @return arrival time of the second leg for a round-way trip
	 */
	public ZonedDateTime getArrivalTime2() {
		ZonedDateTime arrivalTime = leg_tripList.get(1).flightList.get(leg_tripList.get(1).getFlightList().size()-1).getArrivalTime();
		return arrivalTime;	
	}
	
	/**
	 * get total time in double format using for sorting
	 * @return total time use for sort
	 */
	public double getTotalTimetoCompare() {
		double TotalTime = 0;
		for(Leg_Trip l:leg_tripList) {
			TotalTime += (double)l.getTotalTimeToCompare();
		}
		return TotalTime;
	}

	/**
	 * 
	 * @return
	 */
	public ZonedDateTime getWholeTripArrivalTime() {
		ZonedDateTime arrivalTime =leg_tripList.get(leg_tripList.size()-1).flightList.get(leg_tripList.get(leg_tripList.size()-1).flightList.size()-1).getArrivalTime();
		return arrivalTime;
	}
	
	@Override
    public String toString() {
    	
    		String result = "";
		
    		for (Leg_Trip legTrip: leg_tripList) {
    			result += "\t\n" + legTrip;
    		}
    			
    		return result;
    	
    }
}
