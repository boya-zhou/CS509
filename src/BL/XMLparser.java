package BL;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
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

public class XMLparser {
	
	public static void main(String args[]) throws IOException {
		String inputCode = "BOS";
		int inputYear = 2017;
		int inputMonth = Calendar.DECEMBER;
		int inputDay = 12;
		
		Date inputDate = new GregorianCalendar(inputYear, inputMonth, inputDay).getTime();
		System.out.println(inputDate);
		String xmlString = DB.GetData.getArrivalFlightInfo(inputCode, inputDate);
		System.out.println(xmlString);
		Set<Flight> flightList = parseFlightSet(xmlString);
		System.out.println(flightList);
		for(Flight f: flightList) {
			System.out.println(f);
		}
	}
	
	/**
	 * parse an input xml string to a Set of Flight objects
	 * if the server responds with nothing, an empty set is returned
	 * @param xmlString input string
	 * @return a Set of Flight objects
	 * @throws IOException if something wrong happens, e.g. xml not in the right format, some info missing
	 */
	public static Set<Flight> parseFlightSet(String xmlString) throws IOException {
		try {
			Set<Flight> flightList = new HashSet<Flight>();
			Document doc = loadXMLFromString(xmlString);
			NodeList flights = doc.getChildNodes().item(0).getChildNodes();
			for(int i = 0; i < flights.getLength(); i++) {
				Element flightElement = (Element) flights.item(i);
				flightList.add(elementToFlight(flightElement));
			}
			return flightList;
		} catch(IOException e) {
			throw e;
		} catch (DOMException|ParseException e) {
			e.printStackTrace();
			throw new IOException(e.getClass() + ": "+ e.getMessage());
		}
	}
	
	/**
	 * attempt to convert an Element e into a Flight object
	 * if all information related to the Flight is present in the right format (server default format)
	 * a Flight object is return.
	 * Otherwise, the behavior is unpredictable.
	 * @param e input element
	 * @return a Flight object corresponding to e
	 * @throws DOMException
	 * @throws ParseException
	 */
	private static Flight elementToFlight(Element e) throws DOMException, ParseException {
		int flightNumber = Integer.parseInt(e.getAttribute("Number"));
		String airplaneModel = e.getAttribute("Airplane");
		int flightTime = Integer.parseInt(e.getAttribute("FlightTime"));
		
		Element depNode = (Element) e.getElementsByTagName("Departure").item(0);
		String departureCode = depNode.getElementsByTagName("Code").item(0).getTextContent();
		Date departureTime = serverDTStringToDate(depNode.getElementsByTagName("Time").item(0).getTextContent());

		Element arrNode = (Element) e.getElementsByTagName("Arrival").item(0);
		String arrivalCode = arrNode.getElementsByTagName("Code").item(0).getTextContent();
		Date arrivalTime = serverDTStringToDate(arrNode.getElementsByTagName("Time").item(0).getTextContent());

		Element seating = (Element) e.getElementsByTagName("Seating").item(0);
		Element firstClassNode = (Element) seating.getElementsByTagName("FirstClass").item(0);
		Element coachNode = (Element) seating.getElementsByTagName("Coach").item(0);
		int remainingFirstClass = Integer.parseInt(firstClassNode.getTextContent());
		int remainingCoach = Integer.parseInt(coachNode.getTextContent());
		double priceFirstClass = Double.parseDouble(firstClassNode.getAttribute("Price").replace("$", ""));
		double priceCoach = Double.parseDouble(coachNode.getAttribute("Price").replace("$", ""));

		return new Flight(flightNumber, departureTime, arrivalTime, 
				departureCode, arrivalCode, airplaneModel, flightTime, 
				remainingFirstClass, remainingCoach, priceFirstClass, priceCoach);
	}
	
	/**
	 * convert server's datetime string (from xml) to a Date object
	 * @param dtString
	 * @return
	 * @throws ParseException
	 */
	private static Date serverDTStringToDate(String  dtString) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy MMM dd hh:mm 'GMT'");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		return df.parse(dtString);
		
	}
	
	/**
	 * put the xml string into a Document object for reading
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
		} catch (SAXException|ParserConfigurationException e) {
			e.printStackTrace();
			throw new IOException(e.getClass() + ": "+ e.getMessage());
		} 
	}
	
}
