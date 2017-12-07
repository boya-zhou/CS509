package BL;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class BLreception {

    public static void reserve(Flight flight){reserve(flight);};
    public static void reset(Flight flight){reset(flight);}
    
    //public static Flight getFlight(int flightNumber){
    //    return Flight.getFlight(flightNumber);
    //}
    public static Airport getAirport(String airportCode){
        return Airport.getAirport(airportCode);
    }
    public static Airplane getAirplane(String Model){
        return Airplane.getAirplane(Model);
    }
    
    public static ArrayList<Trip> timeWindow(ArrayList<Trip> TripResult, LocalDateTime start ,LocalDateTime stop){
    	return ResultFilter.timeWindow(TripResult, start, stop);
    }
    
    public static ArrayList<Trip> timeWindow2(ArrayList<Trip> TripResult, 
    		LocalDateTime start, LocalDateTime stop, LocalDateTime start2,LocalDateTime stop2){
    	return ResultFilter.timeWindow2(TripResult, start, stop, start2, stop2);
    }
    
    public static ArrayList<Trip> sort(ArrayList<Trip> trips, Comparator<Trip> comparator) {
    	return ResultSort.sort(trips, comparator);
    }
	public static ArrayList<Trip> stopover(ArrayList<Trip> tripResult, int stopNum) {
		return ResultFilter.stopover(tripResult, stopNum);
	}
	public static ArrayList<Trip> getRoundTrip(String departPlace, LocalDate inputDate, String arrivalPlace,
			LocalDate roundDate) throws ClassNotFoundException, IOException {
		return BL.result_generator.GetTrip.getRoundTrip(departPlace, inputDate, arrivalPlace, roundDate);
	}
	public static ArrayList<Trip> getOneWayTrip(String departPlace, LocalDate inputDate, String arrivalPlace) throws ClassNotFoundException, IOException {
		return BL.result_generator.GetTrip.getOneWayTrip(departPlace, inputDate, arrivalPlace);
	}
    
    
}
