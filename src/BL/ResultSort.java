package BL;

import java.util.*;

public class ResultSort {
    public Comparable<Flight> PriceAsc;
    public Comparable<Flight> PriceDes;
    public Comparable<Flight> DepatureAsc;
    public Comparable<Flight> DepatureDes;
    public Comparable<Flight> ArrivalAsc;
    public Comparable<Flight> ArrivalDes;
    public Comparable<Flight> TotalTravelTimeAsc;
    public Comparable<Flight> TotalTravelTimeDes;
    public Comparable<Flight> StopoverAsc;
    public Comparable<Flight> StopoverDes;

    public static Set<Flight> sort(Set<Flight> set,Comparable<Flight> comparator){
        List<Flight> templist =new ArrayList<Flight>(set);
        templist.sort(comparator);
        return new LinkedHashSet<Flight>(templist);

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
            f1.getDepatureTime().compareTo(f2.getDepatureTime());
            return Double.compare(f1.getDepatureTime(), f1.getPrice_FirstClass());
        }
    };
}
