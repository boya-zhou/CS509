package DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetData {

	private static String baseURL = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404";

	public static String getDepartureFlightInfo(String departureAirportCode, Date departureDate) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy_mm_dd");
		String dateString = dateformat.format(departureDate);
		String url = baseURL+"&action=list&list_type=departing&airport=" + departureAirportCode + "&day=" + dateString;
		try {
			return(getXML(url));
		} catch(IOException e) {
			return("<xml><Flights></Flights></xml>");
		}
	}
	
	public static String getArrivalFlightInfo(String arrivalAirportCode, Date arrivalDate) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy_MM_dd");
		String dateString = dateformat.format(arrivalDate);
		String url = baseURL+"&action=list&list_type=arriving&airport=" + arrivalAirportCode + "&day=" + dateString;
		System.out.println(url);
		try {
			return(getXML(url));
		} catch(IOException e) {
			return("<xml><Flights></Flights></xml>");
		}
	}
	
	/**
	 * helper function to interface with the server GET API
	 * @param getURL
	 * @return
	 * @throws IOException
	 */
	private static String getXML(String getURL) throws IOException {
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
}