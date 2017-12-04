package DB;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import BL.Airplane;
import BL.Airport;
import BL.Flight;

/**
 * @author tpatikorn
 *
 */
public class XMLparser {

	public static void main(String args[]) throws IOException, ParseException {
		String inputFormat = "2017 Dec 12 01:31 GMT";
		ZonedDateTime zdt = serverDTStringToDate(inputFormat);
		System.out.println(zdt);
		String inputCode = "BOS";
		int inputYear = 2017;
		int inputMonth = 12;
		int inputDay = 12;

		LocalDate inputDate = LocalDate.of(inputYear, inputMonth, inputDay);
		System.out.println(inputDate);
		String xmlString = DB.GetData.getArrivalFlightInfoXML(inputCode, inputDate);
		System.out.println(xmlString);
		Set<Flight> flightList = parseFlightSet(xmlString);
		System.out.println(flightList);
		for (Flight f : flightList) {
			System.out.println(f);
		}
	}

	/**
	 * parse an input xml string to a Set of Flight objects if the server
	 * responds with nothing, an empty set is returned
	 * 
	 * @param xmlString
	 *            input string
	 * @return a Set of Flight objects
	 * @throws IOException
	 *             if something wrong happens, e.g. xml not in the right format,
	 *             some info missing
	 */
	public static Set<Flight> parseFlightSet(String xmlString) throws IOException {
		try {
			Set<Flight> flightList = new HashSet<Flight>();
			Document doc = loadXMLFromString(xmlString);
			NodeList flights = doc.getChildNodes().item(0).getChildNodes();
			for (int i = 0; i < flights.getLength(); i++) {
				Element flightElement = (Element) flights.item(i);
				flightList.add(elementToFlight(flightElement));
			}
			return flightList;
		} catch (IOException e) {
			throw e;
		} catch (DOMException | ParseException e) {
			e.printStackTrace();
			throw new IOException(e.getClass() + ": " + e.getMessage());
		}
	}

	/**
	 * attempt to convert an Element e into a Flight object if all information
	 * related to the Flight is present in the right format (server default
	 * format) a Flight object is return. Otherwise, the behavior is
	 * unpredictable.
	 * 
	 * @param e
	 *            input element
	 * @return a Flight object corresponding to e
	 * @throws DOMException
	 * @throws ParseException
	 */
	private static Flight elementToFlight(Element e) throws DOMException, ParseException {
		Map<String, Airplane> allAirplanes = GetData.getAllAirplaneMap();
		
		int flightNumber = Integer.parseInt(e.getAttribute("Number"));
		String airplaneModel = e.getAttribute("Airplane");
		int flightTime = Integer.parseInt(e.getAttribute("FlightTime"));
		Airplane airplane = allAirplanes.get(airplaneModel);

		Element depNode = (Element) e.getElementsByTagName("Departure").item(0);
		String departureCode = depNode.getElementsByTagName("Code").item(0).getTextContent();
		ZonedDateTime departureTime = serverDTStringToDate(depNode.getElementsByTagName("Time").item(0).getTextContent());

		Element arrNode = (Element) e.getElementsByTagName("Arrival").item(0);
		String arrivalCode = arrNode.getElementsByTagName("Code").item(0).getTextContent();
		ZonedDateTime arrivalTime = serverDTStringToDate(arrNode.getElementsByTagName("Time").item(0).getTextContent());

		Element seating = (Element) e.getElementsByTagName("Seating").item(0);
		Element firstClassNode = (Element) seating.getElementsByTagName("FirstClass").item(0);
		Element coachNode = (Element) seating.getElementsByTagName("Coach").item(0);
		int remainingFirstClass = airplane.getAmount_firstclassSeat() - Integer.parseInt(firstClassNode.getTextContent());
		int remainingCoach = airplane.getAmount_coachSeat() - Integer.parseInt(coachNode.getTextContent());
		
		double priceFirstClass = Double
				.parseDouble(firstClassNode.getAttribute("Price").replace("$", "").replace(",", ""));
		double priceCoach = Double.parseDouble(coachNode.getAttribute("Price").replace("$", ""));

		return new Flight(flightNumber, departureTime, arrivalTime, departureCode, arrivalCode, airplaneModel,
				flightTime, remainingFirstClass, remainingCoach, priceFirstClass, priceCoach);
	}

	/**
	 * convert server's datetime string (from xml) to a Date object
	 * 
	 * @param dtString
	 * @return
	 * @throws ParseException
	 */
	private static ZonedDateTime serverDTStringToDate(String dtString) throws ParseException {
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy MMM dd kk:mm 'GMT'")
				.withZone(TimeZone.getTimeZone("GMT").toZoneId());
		
		return ZonedDateTime.parse(dtString, dtFormat);
	}

	/**
	 * put the xml string into a Document object for reading
	 * 
	 * @param xml
	 * @return
	 * @throws IOException
	 */
	private static Document loadXMLFromString(String xml) throws IOException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			return builder.parse(is);
		} catch (IOException e) {
			throw e;
		} catch (SAXException | ParserConfigurationException e) {
			e.printStackTrace();
			throw new IOException(e.getClass() + ": " + e.getMessage());
		}
	}

	/**
	 * parse an input xml string to a Set of Airport objects if the server
	 * responds with nothing, an empty set is returned
	 * 
	 * @param xmlString
	 *            input string
	 * @return a Set of Airport objects
	 * @throws IOException
	 *             if something wrong happens, e.g. xml not in the right format,
	 *             some info missing
	 */
	public static Set<Airport> parseAirportSet(String xmlString, Map<String, String> timeMap) throws IOException {
		try {
			Set<Airport> airportSet = new HashSet<Airport>();
			Document doc = loadXMLFromString(xmlString);
			NodeList airports = doc.getChildNodes().item(0).getChildNodes();
			for (int i = 0; i < airports.getLength(); i++) {
				Element airportElement = (Element) airports.item(i);
				airportSet.add(elementToAirport(airportElement, timeMap));
			}
			return airportSet;
		} catch (IOException e) {
			throw e;
		} catch (DOMException | ParseException e) {
			e.printStackTrace();
			throw new IOException(e.getClass() + ": " + e.getMessage());
		}
	}

	/**
	 * parse an input xml string to a Set of Airplane objects if the server
	 * responds with nothing, an empty set is returned
	 * 
	 * @param xmlString
	 *            input string
	 * @return a Set of Airplane objects
	 * @throws IOException
	 *             if something wrong happens, e.g. xml not in the right format,
	 *             some info missing
	 */
	public static Set<Airplane> parseAirplaneSet(String xmlString) throws IOException {
		try {
			Set<Airplane> AirplaneSet = new HashSet<Airplane>();
			Document doc = loadXMLFromString(xmlString);
			NodeList Airplanes = doc.getChildNodes().item(0).getChildNodes();
			for (int i = 0; i < Airplanes.getLength(); i++) {
				Element AirplaneElement = (Element) Airplanes.item(i);
				AirplaneSet.add(elementToAirplane(AirplaneElement));
			}
			return AirplaneSet;
		} catch (IOException e) {
			throw e;
		} catch (DOMException | ParseException e) {
			e.printStackTrace();
			throw new IOException(e.getClass() + ": " + e.getMessage());
		}
	}

	/**
	 * attempt to convert an Element e into a Airport object if all information
	 * related to the Airport is present in the right format (server default
	 * format) a Airport object is return. Otherwise, the behavior is
	 * unpredictable.
	 * 
	 * @param e
	 *            input element
	 * @return a Airport object corresponding to e
	 * @throws DOMException
	 * @throws ParseException
	 */
	private static Airport elementToAirport(Element e, Map<String,String> timeMap) throws DOMException, ParseException {
		String airportCode = e.getAttribute("Code");
		String airportName = e.getAttribute("Name");
		double latitude = Double.parseDouble(e.getElementsByTagName("Latitude").item(0).getTextContent());
		double longitude = Double.parseDouble(e.getElementsByTagName("Longitude").item(0).getTextContent());
		String timeZone = timeMap.get(airportCode);
		return new Airport(airportCode, airportName, latitude, longitude, timeZone);
	}

	/**
	 * attempt to convert an Element e into an Airplane object if all
	 * information related to the Airplane is present in the right format
	 * (server default format) a Airport object is return. Otherwise, the
	 * behavior is unpredictable.
	 * 
	 * @param e
	 *            input element
	 * @return a Airplane object corresponding to e
	 * @throws DOMException
	 * @throws ParseException
	 */
	private static Airplane elementToAirplane(Element e) throws DOMException, ParseException {
		//<Airplane Manufacturer="Airbus" Model="A310">
		//<FirstClassSeats>24</FirstClassSeats>
		//<CoachSeats>200</CoachSeats></Airplane>
		String maunfacturer = e.getAttribute("Manufacturer");
		String model = e.getAttribute("Model");
		int firstclassSeat = Integer.parseInt(e.getElementsByTagName("FirstClassSeats").item(0).getTextContent());
		int coachSeat = Integer.parseInt(e.getElementsByTagName("CoachSeats").item(0).getTextContent());
		return new Airplane(model, maunfacturer, firstclassSeat, coachSeat);
	}
	
	public static String flightListToXML(List<Flight> flightSet, List<String> seatType) {
		String xml = "<Flights>";
		if(flightSet.size() != seatType.size()) {
			throw new RuntimeException("flightSet must be same size as seatType");
		}
		for(int i = 0; i < flightSet.size(); i++) {
			String flightXML = String.format("<Flight number=\"%d\" seating=\"%s\"/>", flightSet.get(i).getFlightNumber(), seatType.get(i));
			xml = xml + flightXML;
		}
		xml = xml + "</Flights>";
		return xml;
	}
}
