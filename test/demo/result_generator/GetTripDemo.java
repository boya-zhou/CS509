package demo.result_generator;

import java.io.IOException;
import java.time.LocalDate;
import BL.result_generator.GetTrip;

public class GetTripDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		long tStart = System.currentTimeMillis();
		
		String deCode = "DEN";
		int deYear = 2017;
		int deMonth = 12;
		int deDay = 12;
		
		LocalDate deDate = LocalDate.of(deYear, deMonth, deDay);
		
		String aCode = "AUS";
		int aYear = 2017;
		int aMonth = 12;
		int aDay = 16;
		
		LocalDate roundDate = LocalDate.of(aYear, aMonth, aDay);
		
		//System.out.println(getOneWayTrip(deCode, deDate, aCode));
		System.out.println(GetTrip.getRoundTrip(deCode, deDate, aCode, roundDate));
		
		long tEnd = System.currentTimeMillis();
		long tDelta = tEnd - tStart;
		double elapsedSeconds = tDelta / 1000.0;
		System.out.println(elapsedSeconds);
		
	}
}
