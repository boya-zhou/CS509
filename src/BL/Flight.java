package BL;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Comparator;

public class Flight implements Serializable{
    public int flightNumber;
    public ZonedDateTime departureTime;
    public ZonedDateTime arrivalTime;
    public String depatureCode;
    public String arrivalCode;
    public String airplaneModel;
    public  int flightTime;
    public int remain_FirstClass;
    public int remain_Coach;
    public double price_FirstClass;
    public double price_Coach;


    public Flight() {
    }
    
    public Flight(int flightNumber, ZonedDateTime depatureTime, ZonedDateTime arrivalTime, String depatureCode, String arrivalCode, String airplaneModel, int flightTime,
                  int remain_FirstClass, int remain_Coach, double price_FirstClass, double price_Coach) {
        this.flightNumber = flightNumber;
        this.departureTime = depatureTime;
        this.arrivalTime = arrivalTime;
        this.depatureCode = depatureCode;
        this.arrivalCode = arrivalCode;
        this.airplaneModel = airplaneModel;
        this.flightTime = flightTime;
        this.remain_FirstClass = remain_FirstClass;
        this.remain_Coach = remain_Coach;
        this.price_FirstClass = price_FirstClass;
        this.price_Coach = price_Coach;
    }
    
    public Flight(Flight flight) {
        this.flightNumber = flight.flightNumber;
        this.departureTime = flight.departureTime;
        this.arrivalTime = flight.arrivalTime;
        this.depatureCode = flight.depatureCode;
        this.arrivalCode = flight.arrivalCode;
        this.airplaneModel = flight.airplaneModel;
        this.flightTime = flight.flightTime;
        this.remain_FirstClass = flight.remain_FirstClass;
        this.remain_Coach = flight.remain_Coach;
        this.price_FirstClass = flight.price_FirstClass;
        this.price_Coach = flight.price_Coach;
    	
    }


    @Override
    public String toString() {
        return "[" + flightNumber + "||" + departureTime + "||" + arrivalTime + "||" + depatureCode + "||" +
                arrivalCode + "||" + airplaneModel + "||" + flightTime + "||" + remain_FirstClass + "||" + 
                remain_Coach + "||" + price_FirstClass + "||" + price_Coach +"]\n";
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Flight && this.flightNumber == (((Flight) obj).flightNumber);
    }
    
    @Override
    public int hashCode() {
        return this.flightNumber;
    }
    
    public int getFlightNumber() {
        return flightNumber;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public ZonedDateTime getArrivalTime() {
        return arrivalTime;
    }

    public String getDepatureCode() {
        return depatureCode;
    }

    public String getArrivalCode() {
        return arrivalCode;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public int getFlightTime() {
        return flightTime;
    }

    public int getRemain_FirstClass() {
        return remain_FirstClass;
    }

    public int getRemain_Coach() {
        return remain_Coach;
    }

    public double getPrice_FirstClass() {
        return price_FirstClass;
    }

    public double getPrice_Coach() {
        return price_Coach;
    }
}
