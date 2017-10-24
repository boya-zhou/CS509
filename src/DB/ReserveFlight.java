package DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReserveFlight {
    private static String baseURL = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404";
    private static String lockDB = "&action=lockDB";
    private static String unlockDB = "&action=unlockDB";
    private static String resetDB = "&action=resetDB";
    private static String ReserveFlight = "&action=buyTickets&flightData=";

    /**
     * attempt to lock the database for writing
     * @return true if lock is acquired, false otherwise
     * TODO check to see what is the server response when it fails to acquire the lock
     */
    public static boolean lock() {
    	try {
    		String result = sendPOST(baseURL+lockDB);
    		return true;
    	} catch (IOException e) {
    		return false;
    	}
    }
    
    public static void reserve(String flightXML){

    }
    public static void unlock(){
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
			throw new IOException(responseCode + ": Error trying to post to URL: "+postURL+".");
		}
	}
}
