package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import BL.Flight;
import DB.GetData;

public class prototype1Demo {
	public static void main(String args[]) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.print("please input your 3-letter departure airport code: ");
			String airport = buffer.readLine();
			System.out.print("please input your departure date in this format yyyy/MM/dd (all numeric): ");
			String yMdString = buffer.readLine();
			String[] yMd = yMdString.split("/");
			Date date = new GregorianCalendar(Integer.parseInt(yMd[0]), 
					Integer.parseInt(yMd[1])-1, /* -1 because the GregorianCalendar start month = 0*/ 
					Integer.parseInt(yMd[2])).getTime();
			Set<Flight> flightSet = GetData.getDepartureFlightInfo(airport, date);
			/*
    public int flightNumber;
    public Date depatureTime;
    public Date arrivalTime;
    public String depatureCode;
    public String arrivalCode;
    public String airplaneModel;
    public  int flightTime;
    public int remain_FirstClass;
    public int remain_Coach;
    public double price_FirstClass;
    public double price_Coach;
			 * 
			 */
			int i = 1;
			for(Flight f: flightSet) {
				String s = String.format("Result# %d:\t"
						+ "Flight# %d from %s to %s. Depart at %s. Arrive at %s. "
						+ "Airplane model %s. "
						+ "FirstClass remaining %d ($%.2f each). "
						+ "Coach remaining %d ($.2f each).",
						i++,
						f.flightNumber, f.depatureCode, f.arrivalCode, f.depatureTime.toGMTString(), f.arrivalTime.toGMTString(),
						f.getAirplaneModel(),
						f.getRemain_FirstClass(), f.getPrice_FirstClass(),
						f.getRemain_FirstClass(), f.getRemain_Coach());
				System.out.println(s);
			}
		}
	}

}
