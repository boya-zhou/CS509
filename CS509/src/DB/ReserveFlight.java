package DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

import BL.Flight;

public class ReserveFlight {
	/**
	 * attempt to acquire the lock to the database
	 * will always succeed (code 202 accepted) 
	 * regardless of whether the lock is acquired
	 * @TODO get a confirmation on whether how the lock actually works
	 * @throws IOException
	 */
    public static void lock()throws IOException{
    	//System.out.println("lock");
        String lockDB="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=lockDB";
        sendPOST(lockDB);

    }
    
    public static int reserve(Set<Flight> flights) throws IOException{
    	String xmlFlights = XMLparser.FlightSetToXML(flights);
    	return reserveXML(xmlFlights);
    }
    
    /**
     * attempt to reserve a flight
     * @param xmlFlights
     * @return the http status code of the post request
     * - 202 [need to check] = reservation is accepted
     * - 400 = the xmlFlights input is not formatted correctly
     * - 412 = the lock is not held by the requester
     * @throws IOException
     * 
     */
    public static int reserveXML(String xmlFlights) throws IOException{
    	//System.out.println("reserve");
        String ReserveFlight="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=buyTickets&flightData="+xmlFlights;
        int responseCode = sendPOST(ReserveFlight);
        return responseCode;
    }
    
    /**
     * attempt to release the lock
     * will always succeed
     * @throws IOException
     */
    public static void unlock()throws IOException{
    	//System.out.println("unlock");
        String unlockDB="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=unlockDB";
        sendPOST(unlockDB);
    }
    public static void reset()throws IOException{
    	System.out.println("reset");
        String resetDB="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=resetDB";
        sendPOST(resetDB);
    }


    private static int sendPOST(String POST_URL) throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        //os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        //System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_ACCEPTED) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            //System.out.println(response.toString());
        } else if (responseCode == HttpURLConnection.HTTP_PRECON_FAILED) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            //System.out.println(response.toString());
            throw new IOException("POST request not worked: Precondition is not met");
        } else {
            //System.out.println("POST request not worked");
            throw new IOException("POST request not worked:" + responseCode);
        }
        return responseCode;
    }

}
