package DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReserveFlight {
    String xmlFlights;


    String ReserveFlight="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=buyTickets&flightData"+xmlFlights;

    public static void lock()throws IOException{
        String lockDB="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=lockDB";
        sendPOST(lockDB);

    }
    public static void reserve()throws IOException{

    }
    public static void unlock()throws IOException{
        String unlockDB="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=unlockDB";
        sendPOST(unlockDB);
    }
    public static void reset()throws IOException{
        String resetDB="http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=resetDB";
        sendPOST(resetDB);
    }


    private static void sendPOST(String POST_URL) throws IOException {
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
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }
    private static String sendGET(String GET_URL) throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return(response.toString());
        } else {
            throw new IOException("Cannot open a connection to the target URL: "+GET_URL+".");
        }

    }

}
