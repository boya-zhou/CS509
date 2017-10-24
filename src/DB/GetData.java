package DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;

public class GetData {

	private String getXML(String getURL) throws IOException {
		HttpURLConnection con = (HttpURLConnection) (new URL(getURL)).openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		//System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return(response.toString());
		} else {
			throw new IOException(responseCode + ": Error trying to open URL: "+getURL+".");
		}
	}

	private static String sendPOST(String postURL) throws IOException {
		HttpURLConnection con = (HttpURLConnection) (new URL(postURL)).openConnection();
		con.setRequestMethod("POST");
		int responseCode = con.getResponseCode();
		//System.out.println("POST Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return(response.toString());
		} else {
			throw new IOException(responseCode + ": Error trying to open URL: "+postURL+".");
		}
	}
    
    private class queryURLBuilder {
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
}