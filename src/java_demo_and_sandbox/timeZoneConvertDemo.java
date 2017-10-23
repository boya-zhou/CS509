package java_demo_and_sandbox;

import java.time.Instant;
import java.time.ZoneOffset;

public class timeZoneConvertDemo {
	public static void main(String args[]) {
		Instant ts = Instant.now();
		System.out.println(ts.toString());
		// the ZoneOffset.of("+5")) is the time offset you need
		// more on time zone of each airport 
		// https://nfdc.faa.gov/xwiki/bin/view/NFDC/56DaySub-2017-01-05
		// https://github.com/ryanburnette/airports-api/blob/master/airport-data/airports.csv
		System.out.println(ts.atOffset(ZoneOffset.of("+5")).toString());
		
	}
}
