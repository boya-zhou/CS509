package demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Set;

import BL.Airplane;
import BL.Airport;
import BL.Flight;
import DB.GetData;

public class prototype1Demo {
	public static void main(String args[]) {
		Set<Airport> airports = GetData.getAllAirports();
		for(Airport a: airports) {
			System.out.println(a.toString());
		}
		Set<Airplane> Airplanes = GetData.getAllAirplanes();
		for(Airplane a: Airplanes) {
			System.out.println(a.toString());
		}
	}
	public static void findFlightLoop() {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		try {
			while(true) {
				System.out.print("please input your 3-letter departure airport code: ");
				String airport = buffer.readLine().toUpperCase();
				if(airport.length() != 3) {
					System.out.println("The input airport code has length not equal to 3.");
					continue;
				}
				System.out.print("please input your departure date in this format yyyy/MM/dd (all numeric): ");
				String yMdString = buffer.readLine();
				String[] yMd = yMdString.split("/");
				LocalDate date = LocalDate.of(Integer.parseInt(yMd[0]), 
						Integer.parseInt(yMd[1])-1, /* -1 because the GregorianCalendar start month = 0*/ 
						Integer.parseInt(yMd[2]));
				Set<Flight> flightSet = GetData.getDepartureFlightInfo(airport, date);
				System.out.println("Found " + flightSet.size() + " result(s):");
				int i = 1;
				for(Flight f: flightSet) {
					String s = String.format("Result# %d:\t"
							+ "Flight# %d from %s to %s. Depart at %s. Arrive at %s. "
							+ "Airplane model %s. "
							+ "FirstClass remaining %d ($%.2f each). "
							+ "Coach remaining %d ($.2f each).",
							i++,
							f.flightNumber, f.depatureCode, f.arrivalCode, f.departureTime, f.arrivalTime,
							f.getAirplaneModel(),
							f.getRemain_FirstClass(), f.getPrice_FirstClass(),
							f.getRemain_FirstClass(), f.getRemain_Coach());
					System.out.println(s);
				}
				System.out.println("-----------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
