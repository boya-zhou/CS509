package BL;

import java.util.List;

public class Trip {
	List<Leg_Trip> leg_tripList;

    public Trip(List<Leg_Trip> leg_tripList) {
        this.leg_tripList = leg_tripList;
    }
    public List<Leg_Trip> getLeg_tripList() {
		return leg_tripList;
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
