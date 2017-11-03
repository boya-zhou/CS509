package BL;

import java.util.List;

public class Leg_Trip {
    List<Flight> flightList;
    int num_flight=flightList.size();
    Long TotalTime;
    int TripType;
    public Leg_Trip(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public int getNum_flight() {
        return num_flight;
    }
}
