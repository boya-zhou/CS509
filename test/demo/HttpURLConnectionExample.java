package demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.management.RuntimeErrorException;

public class HttpURLConnectionExample {



	public static void main(String[] args) throws IOException {
		String querylist_airport = "&list_type=airports";
		String querylist_airplane = "&list_type=airplanes";

		//String querylist_flightdepaturing = "&list_type=departing&airport=" + airportCode + "&day=" date;
		//String querylist_flightarriving = "&list_type=arriving&airport=" + airportCode + "&day="+ date;
		String url = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404";
		String listUrl = url + "&action=list";
		
		String EXAMPLE_GET_URL = listUrl+querylist_airport;
		
		
		String teststring = "http://cs509.cs.wpi.edu:8181/CS509.server/ReservationSystem?team=404&action=list&list_type=airplanes";
		sendGET(EXAMPLE_GET_URL);
		sendGET(teststring);
		System.out.println("GET DONE");
		//sendPOST(EXAMPLE_POST_URL);
		System.out.println("POST DONE");
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

}
