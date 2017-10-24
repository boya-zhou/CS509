package DB;

import BL.Airplane;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

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

