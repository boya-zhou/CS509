package java_demo_and_sandbox;

import java.awt.print.Printable;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class timeAddTest {
	public static void main(String[] args) throws ParseException {
		String deCode = "AUS";
		int deYear = 2017;
		int deMonth = Calendar.DECEMBER;
		int deDay = 12;
		
		Calendar deDate = new GregorianCalendar(deYear, deMonth, deDay);
		
		ArrayList<Integer> timeWindowL = timeWindow(2);
		
		System.out.println(timeWindowL);
		
		for (Integer deTime: timeWindowL) {
			Calendar newDeDate= (GregorianCalendar) deDate.clone();
			newDeDate.add(Calendar.DAY_OF_MONTH, deTime.intValue());
			System.out.println(deTime);
			System.out.println(newDeDate.getTime());
		}
		
		
	}
	
	public static ArrayList<Integer> timeWindow(int timeWin) {
		
		ArrayList<Integer> timeWinList = new ArrayList<Integer>();
		
		switch (timeWin) {
	
		case 0:
			timeWinList.add(0);
			break;
		case 1:
			timeWinList.add(-1);
			timeWinList.add(0);
			timeWinList.add(1);
			break;
		case 2:
			timeWinList.add(-2);
			timeWinList.add(-1);
			timeWinList.add(0);
			timeWinList.add(1);
			timeWinList.add(2);
			break;
		case 3:
			timeWinList.add(-3);
			timeWinList.add(-2);
			timeWinList.add(-1);
			timeWinList.add(0);
			timeWinList.add(1);
			timeWinList.add(2);
			timeWinList.add(3);
			break;
		}
		return timeWinList;
	}
	
}
