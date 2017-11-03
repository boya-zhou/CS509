package BL;

public interface BLreception {

    public static void reserve(Flight flight){reserve(flight);};
    public static void reset(Flight flight){reset(flight);}
    public static Flight getFlight(int flightNumber){
        return Flight.getFlight(flightNumber);
    }
    public static Airport getAirport(String airportCode){
        return Airport.getAirport(airportCode);
    }
    public static Airplane getAirplane(String Model){
        return Airplane.getAirplane(Model);
    }
}
