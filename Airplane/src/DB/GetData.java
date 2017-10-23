package DB;

import BL.Airplane;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.beans.XMLDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

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
        Document doc = parse(in);

    }


    public GetData() throws IOException {
    }

    /**
     * Constructs a Document object by reading from an input stream.
     */
    public static Document parse (InputStream is) {
        Document ret = null;
        DocumentBuilderFactory domFactory;
        DocumentBuilder builder;

        try {
            domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setValidating(false);
            domFactory.setNamespaceAware(false);
            builder = domFactory.newDocumentBuilder();

            ret = (Document) builder.parse(is);
        }
        catch (Exception ex) {
          //  System.error.println("unable to load XML: " + ex);
        }
        return ret;
    }

}
