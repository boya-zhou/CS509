package DB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import BL.Airplane;
import BL.Airport;
import BL.Flight;

/**
 * This is the class that contains functions
 * that do all the GET requests and return java objects
 * that are immediately usable by the rest of the programs
 *
 */
public class GetData {

	private static String baseURL = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404";
	private static Set<Airport> allAirports = null;
	private static Set<Airplane> allAirplanes = null;
	private static Map<String, Airplane> allAirplaneMap = null;

	public static Set<Flight> getDepartureFlightInfo(String departureAirportCode, LocalDate departureDate) throws IOException {
		String returnString = getDepartureFlightInfoXML(departureAirportCode, departureDate);
		return XMLparser.parseFlightSet(returnString);
	}

	public static Set<Flight> getArrivalFlightInfo(String departureAirportCode, LocalDate departureDate) throws IOException {
		String returnString = getArrivalFlightInfoXML(departureAirportCode, departureDate);
		return XMLparser.parseFlightSet(returnString);
	}
	
	private static String getDepartureFlightInfoXML(String departureAirportCode, LocalDate departureDate) {
		String dateString = String.format("%04d_%02d_%02d", departureDate.getYear(), departureDate.getMonthValue(), departureDate.getDayOfMonth());
		String url = baseURL+"&action=list&list_type=departing&airport=" + departureAirportCode + "&day=" + dateString;
//		System.out.println(url);
		
		try {
			return(getXML(url));
		} catch(IOException e) {
			return("<xml><Flights></Flights></xml>");
		}
	}
	
	private static String getArrivalFlightInfoXML(String arrivalAirportCode, LocalDate arrivalDate) {
		String dateString = String.format("%04d_%02d_%02d", arrivalDate.getYear(), arrivalDate.getMonthValue(), arrivalDate.getDayOfMonth());
		String url = baseURL+"&action=list&list_type=arriving&airport=" + arrivalAirportCode + "&day=" + dateString;
//		System.out.println(url);
		try {
			return(getXML(url));
		} catch(IOException e) {
			return("<xml><Flights></Flights></xml>");
		}
	}
	
	/**
	 * get all airports as a set
	 * will remember the set from previous requests
	 * using singleton
	 * @return a set of all airports
	 */
	public static Set<Airport> getAllAirports() {
		if(allAirports != null) {
			return allAirports;
		} else {
			String url = baseURL+"&action=list&list_type=airports";
	//		System.out.println(url);
			try {
				String xmlString = getXML(url);
				//System.out.println(xmlString);
				Set<Airport> airportSets = XMLparser.parseAirportSet(xmlString, getTimeZoneInfo());
				allAirports = airportSets;
				return airportSets;
			} catch(IOException e) {
				throw new RuntimeException("Cannot connect to the WPI API");
			}
		}
	}	
	
	/**
	 * read timezone from file
	 * @return a map from airport code to timezone of that airport
	 * @throws IOException if something's wrong
	 */
	private static Map<String, String> getTimeZoneInfo() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("sources/airport_timezoneoffset.csv"));
		Map<String, String> timeMap = new HashMap<String, String>();
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] s = line.split(",");
			timeMap.put(s[0], s[2]);
		}
		return timeMap;
	}

	/**
	 * get a set of all airplanes
	 * will remember the set from previous requests
	 * using singleton
	 * @return a set of all airplanes
	 */
	public static Set<Airplane> getAllAirplanes() {
		if(allAirplanes != null) {
			return allAirplanes;
		} else {
			String url = baseURL+"&action=list&list_type=airplanes";
			try {
				String xmlString = getXML(url);
				//System.out.println(xmlString);
				allAirplanes = XMLparser.parseAirplaneSet(xmlString);
				return allAirplanes;
			} catch(IOException e) {
				throw new RuntimeException("Cannot connect to the WPI API");
			}
		}
	}

	/**
	 * get all airplanes using a map object from airplane model (string) to airplane object
	 * will remember the set from previous requests
	 * using singleton
	 * @return a map of model -> airplane object
	 */
	public static Map<String, Airplane> getAllAirplaneMap() {
		if(allAirplaneMap != null) {
			return allAirplaneMap;
		} else {
			allAirplaneMap = new HashMap<String, Airplane>();
			for(Airplane airplane: getAllAirplanes()) {
				allAirplaneMap.put(airplane.getModel(), airplane);
			}
		return allAirplaneMap;
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