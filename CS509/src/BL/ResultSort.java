package BL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public class ResultSort {

    public static Set<Trip> sort(Set<Trip> set, Comparator<Trip> comparator) {
        List<Trip> tempList = new ArrayList<Trip>(set);
        tempList.sort(comparator);
        return new LinkedHashSet<Trip>(tempList);
    }


    public static final Comparator<Trip> CoachPriceAsc = new Comparator<Trip>() {
        @Override
        public int compare(Trip t1, Trip t2) {
            return Double.compare(t1.getPrice_Coach(), t2.getPrice_Coach());
        }
    };
    public static final Comparator<Trip> CoachPriceDes = new Comparator<Trip>() {
        @Override
        public int compare(Trip t1, Trip t2) {
            return Double.compare(t2.getPrice_Coach(), t1.getPrice_Coach());
        }
    };
    public static final Comparator<Trip> FCPriceAsc = new Comparator<Trip>() {
        @Override
        public int compare(Trip t1, Trip t2) {
            return Double.compare(t1.getPrice_FirstClass(), t2.getPrice_FirstClass());
        }
    };
    public static final Comparator<Trip> FCPriceDes = new Comparator<Trip>() {
        @Override
        public int compare(Trip t1, Trip t2) {
            return Double.compare(t2.getPrice_FirstClass(), t1.getPrice_FirstClass());
        }
    };
    public static final Comparator<Trip> DepatureAsc = new Comparator<Trip>() {
        @Override
        public int compare(Trip t1, Trip t2) {
            return t1.getDepatureTime().compareTo(t2.getDepatureTime());
        }
    };
    public static final Comparator<Trip> DepatureDes = new Comparator<Trip>() {
        @Override
        public int compare(Trip t1, Trip t2) {
            return t2.getDepatureTime().compareTo(t1.getDepatureTime());
        }
    };
    public static final Comparator<Trip> ArrivalAsc = new Comparator<Trip>() {
        @Override
        public int compare(Trip t1, Trip t2) {
            return t1.getArrivalTime().compareTo(t2.getArrivalTime());
        }
    };
    public static final Comparator<Trip> ArrivalDes = new Comparator<Trip>() {
        @Override
        public int compare(Trip t1, Trip t2) {
            return  t2.getArrivalTime().compareTo(t1.getArrivalTime());
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
