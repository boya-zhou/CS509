package BL;

public class Airport {

    String airportCode;
    String airportName;
    double latitude;
    double longitude;


    public Airport() {
    	throw new UnsupportedOperationException();
    }
    
    public Airport(String airportCode, String airportName, double latitude, double longitude) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Airport getAirport(String airportCode){
        Airport airport= new Airport(/*need fill up*/);
        return airport;
    }

    public String toString(){
        return "[" + airportCode + "||" + airportName + "||" + latitude + "||" + longitude + "]\n";
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
