package BL;

public class Airport {

    String airportCode;
    String airportName;
    double latitude;
    double longitude;
    int timeZoneOffset;

	public Airport() {
    	throw new UnsupportedOperationException();
    }
    
    public Airport(String airportCode, String airportName, double latitude, double longitude, int timeZoneOffset) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeZoneOffset = timeZoneOffset;
    }

    public static Airport getAirport(String airportCode){
        Airport airport= new Airport(/*need fill up*/);
        return airport;
    }

    public String toString(){
        return "[" + airportCode + "||" + airportName + "||" + latitude + "||" + longitude + "||" + timeZoneOffset + "]\n";
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

    public int getTimeZoneOffset() {
		return timeZoneOffset;
	}
}
