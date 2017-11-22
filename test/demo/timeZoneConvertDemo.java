package demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;

public class timeZoneConvertDemo {
	public static void main(String args[]) throws IOException {
		Instant ts = Instant.now();
		System.out.println(ts.toString());
		// the ZoneOffset.of("+5")) is the time offset you need
		// more on time zone of each airport 
		// https://github.com/ryanburnette/airports-api/blob/master/airport-data/airports.csv
		//System.out.println(ts.atOffset(ZoneOffset.of(airport.getTimeZoneOffset())).toString());
		
		BufferedReader reader = new BufferedReader(new FileReader("sources/airport_timezoneoffset.csv"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] s = line.split(",");
			System.out.println(s[0]);
			System.out.println(s[1]);
		}
			
	}
}
