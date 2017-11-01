package BL;

import java.util.List;

public class Leg_Trip {
    List<Flight> flightList;
    int num_flight=flightList.size();

    public Leg_Trip(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public int getNum_flight() {
        return num_flight;
    }
}
