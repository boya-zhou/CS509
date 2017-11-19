package java_demo_and_sandbox;

import java.sql.Date;

public class queryURLBuilder {
	private String url = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404";
	
	public String getListAirportsURL() {
		return(url+"&action=list&list_type=airports");
	}

	public String getListAirplanesURL() {
		return(url+"&action=list&list_type=airplanes");
	}
	
	public String getListDeparturesURL(String airportCode, Date date) {
		return(url+"&action=list&list_type=departing&airport=" + airportCode + "&day=" + date.toString());
	}
	
	public String getListArrivingsURL(String airportCode, Date date) {
		return(url+"&action=list&list_type=arriving&airport=" + airportCode + "&day=" + date.toString());
	}
}
