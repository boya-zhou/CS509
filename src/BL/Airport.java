package BL;

import java.time.ZonedDateTime;
import java.util.TimeZone;

public class Airport {

    String airportCode;
    String airportName;
    double latitude;
    double longitude;
    TimeZone timeZone;

	public Airport() {
    	throw new UnsupportedOperationException();
    }
    
    public Airport(String airportCode, String airportName, double latitude, double longitude, String timeZoneString) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeZone = TimeZone.getTimeZone(timeZoneString);
    }

    public static Airport getAirport(String airportCode){
        Airport airport= new Airport(/*need fill up*/);
        return airport;
    }

    public String toString(){
        return "[" + airportCode + "||" + airportName + "||" + latitude + "||" + longitude + "||" + timeZone + "]\n";
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

	public TimeZone getTimeZone() {
		return timeZone;
	}
	
	public ZonedDateTime getLocalTime(ZonedDateTime zdt) {
		return(zdt.withZoneSameInstant(this.timeZone.toZoneId()));
	}
}
