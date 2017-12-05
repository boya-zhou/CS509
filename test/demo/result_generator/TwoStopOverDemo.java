package demo.result_generator;

import java.io.IOException;
import java.time.LocalDate;

public class TwoStopOverDemo {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = 12;
		int deDay = 12;
		
		LocalDate deDate = LocalDate.of(deYear, deMonth, deDay);
		
		String aCode = "DEN";
		
		System.out.println(BL.result_generator.TwoStopOver.generateTwoStopOver(deCode, deDate, aCode));
	}
}
