package BL;

import java.util.List;

import BL.result_generator.GetTrip;

public class Trip {
	List<Leg_Trip> leg_tripList;

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
    
    
    @Override
    public String toString() {
    	
    		String result = "";
		
    		for (Leg_Trip legTrip: leg_tripList) {
    			result += "\t\n" + legTrip;
    		}
    			
    		return result;
    	
    }
}
