package BL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class ResultSort {

    public static Set<Flight> sort(Set<Flight> set, Comparator<Flight> comparator) {
        List<Flight> tempList = new ArrayList<Flight>(set);
        tempList.sort(comparator);
        return new LinkedHashSet<Flight>(tempList);
    }


    public static final Comparator<Flight> CoachPriceAsc = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return Double.compare(f1.getPrice_Coach(), f2.getPrice_Coach());
        }
    };
    public static final Comparator<Flight> CoachPriceDes = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return Double.compare(f2.getPrice_Coach(), f1.getPrice_Coach());
        }
    };
    public static final Comparator<Flight> FCPriceAsc = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return Double.compare(f1.getPrice_FirstClass(), f2.getPrice_FirstClass());
        }
    };
    public static final Comparator<Flight> FCPriceDes = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return Double.compare(f2.getPrice_FirstClass(), f1.getPrice_FirstClass());
        }
    };
    public static final Comparator<Flight> DepatureAsc = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return f1.getDepartureTime().compareTo(f2.getDepartureTime());
        }
    };
    public static final Comparator<Flight> DepatureDes = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return f2.getDepartureTime().compareTo(f1.getDepartureTime());
        }
    };
    public static final Comparator<Flight> ArrivalAsc = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return f1.getArrivalTime().compareTo(f2.getArrivalTime());
        }
    };
    public static final Comparator<Flight> ArrivalDes = new Comparator<Flight>() {
        @Override
        public int compare(Flight f1, Flight f2) {
            return  f2.getArrivalTime().compareTo(f1.getArrivalTime());
        }
    };
    /*need fill up*/
    public static final Comparator<Leg_Trip> TotalTimeAsc = new Comparator<Leg_Trip>() {
        @Override
        public int compare(Leg_Trip l1, Leg_Trip l2) {
            return  l2.getArrivalTime().compareTo(l1.getArrivalTime());
        }
    };
    /*need fill up*/
    public static final Comparator<Leg_Trip> TotalTimeDes = new Comparator<Leg_Trip>() {
        @Override
        public int compare(Leg_Trip l1, Leg_Trip l2) {
            return  l2.getArrivalTime().compareTo(l1.getArrivalTime());
        }
    };
    /*need fill up*/
    public static final Comparator<Leg_Trip> StopoverAsc = new Comparator<Leg_Trip>() {
        @Override
        public int compare(Leg_Trip l1, Leg_Trip l2) {
            return  l2.getArrivalTime().compareTo(l1.getArrivalTime());
        }
    };
    /*need fill up*/
    public static final Comparator<Leg_Trip> StopoverDes = new Comparator<Leg_Trip>() {
        @Override
        public int compare(Leg_Trip l1, Leg_Trip l2) {
            return  l2.getArrivalTime().compareTo(l1.getArrivalTime());
        }
    };
}
