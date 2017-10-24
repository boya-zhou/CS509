package BL;

import java.util.Date;

public class Flight{
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

    public Flight(int flightNumber, Date depatureTime, Date arrivalTime, String depatureCode, String arrivalCode, String airplaneModel, int flightTime,
                  int remain_FirstClass, int remain_Coach, double price_FirstClass, double price_Coach) {
        this.flightNumber = flightNumber;
        this.depatureTime = depatureTime;
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
    public static Flight getFlight(int flightNumber){
        Flight flight=new Flight(/*need fill up*/);
    }

    @Override
    public String toString() {
        return "[" + flightNumber + "||" + depatureTime + "||" + arrivalTime + "||" + depatureCode +
                arrivalCode + "||" + airplaneModel + "||" + flightTime + "||" + remain_FirstClass+
                remain_Coach + "||" + price_FirstClass + "||" + price_Coach +"]\n";
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public Date getDepatureTime() {
        return depatureTime;
    }

    public Date getArrivalTime() {
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
