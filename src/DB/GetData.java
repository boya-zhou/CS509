package DB;

import BL.Airplane;
import BL.Airport;

import javax.swing.text.Document;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXB;


public class GetData {
    String date;//yyyy_mm_dd
    String airportCode;//e.g. BOS

    String querylist_airport ="?team=404&action=list&list_type=airports";
    String querylist_airplane="?team=404&action=list&list_type=airplanes";

    String querylist_flightdepaturing="?team=404&action=list&list_type=departing&airport="+airportCode+"&day="+date;
    String querylist_flightarriving="?team=404&action=list&list_type=arriving&airport="+airportCode+"&day="+date;
    String url="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem";



    private void getXML(String query) throws IOException {
        URL url2=new URL(url+query);
        InputStream in = url2.openStream();

    }

    public static Airport listAirport(String xml) throws JAXBException{
        Airport airport = JAXB.unmarshal(new StringReader(xml), Airport.class);
        return airport;

    }

    public GetData() throws IOException {
    }


}
