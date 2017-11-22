package DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import BL.Airplane;
import BL.Airport;
import BL.Flight;

public class GetData {

	private static String baseURL = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404";

	public static Set<Flight> getDepartureFlightInfo(String departureAirportCode, LocalDate departureDate) throws IOException {
		String returnString = getDepartureFlightInfoXML(departureAirportCode, departureDate);
		return XMLparser.parseFlightSet(returnString);
	}

	public static Set<Flight> getArrivalFlightInfo(String departureAirportCode, LocalDate departureDate) throws IOException {
		String returnString = getArrivalFlightInfoXML(departureAirportCode, departureDate);
		return XMLparser.parseFlightSet(returnString);
	}
	
	public static String getDepartureFlightInfoXML(String departureAirportCode, LocalDate departureDate) {
		String dateString = String.format("%04d_%02d_%02d", departureDate.getYear(), departureDate.getMonthValue(), departureDate.getDayOfMonth());
		String url = baseURL+"&action=list&list_type=departing&airport=" + departureAirportCode + "&day=" + dateString;
//		System.out.println(url);
		
		try {
			return(getXML(url));
		} catch(IOException e) {
			return("<xml><Flights></Flights></xml>");
		}
	}
	
	public static String getArrivalFlightInfoXML(String arrivalAirportCode, LocalDate arrivalDate) {
		String dateString = String.format("%04d_%02d_%02d", arrivalDate.getYear(), arrivalDate.getMonthValue(), arrivalDate.getDayOfMonth());
		String url = baseURL+"&action=list&list_type=arriving&airport=" + arrivalAirportCode + "&day=" + dateString;
//		System.out.println(url);
		try {
			return(getXML(url));
		} catch(IOException e) {
			return("<xml><Flights></Flights></xml>");
		}
	}
	
	public static Set<Airport> getAllAirports() {
		String url = baseURL+"&action=list&list_type=airports";
//		System.out.println(url);
		try {
			String xmlString = getXML(url);
			System.out.println(xmlString);
			Set<Airport> airportSets = XMLparser.parseAirportSet(xmlString, getTimeZoneOffsetInfo());
			return airportSets;
		} catch(IOException e) {
			throw new RuntimeException("Cannot connect to the WPI API");
		}
	}	
	
	private static Map<String, String> getTimeZoneOffsetInfo() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("sources/airport_timezoneoffset.csv"));
		Map<String, String> timeMap = new HashMap<String, String>();
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] s = line.split(",");
			timeMap.put(s[0], s[2]);
		}
		return timeMap;
	}

	public static Set<Airplane> getAllAirplanes() {
		String url = baseURL+"&action=list&list_type=airplanes";
//		System.out.println(url);
		try {
			String xmlString = getXML(url);
			System.out.println(xmlString);
			return XMLparser.parseAirplaneSet(xmlString);
		} catch(IOException e) {
			throw new RuntimeException("Cannot connect to the WPI API");
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