package BL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class is responsible for sorting Trips according to criteria
 */
public class ResultSort {

	public static ArrayList<Trip> sort(ArrayList<Trip> trips, Comparator<Trip> comparator) {
		List<Trip> tempList = new ArrayList<Trip>(trips);
		tempList.sort(comparator);
		return new ArrayList<Trip>(tempList);
	}

	/**
	 * sort ascendingly by the total price (coach seats)
	 */
	public static final Comparator<Trip> CoachPriceAsc = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return Double.compare(t1.getTripCoachPrice(), t2.getTripCoachPrice());
		}
	};

	/**
	 * sort descendingly by the total price (coach seats)
	 */
	public static final Comparator<Trip> CoachPriceDes = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return Double.compare(t2.getTripCoachPrice(), t1.getTripCoachPrice());
		}
	};
	
	/**
     * sort ascendingly by the total price (first class seats)
     */
	public static final Comparator<Trip> FCPriceAsc = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return Double.compare(t1.getTripFirstPrice(), t2.getTripFirstPrice());
		}
	};
	
	/**
     * sort descendingly by the total price (first class seats)
     */
	public static final Comparator<Trip> FCPriceDes = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return Double.compare(t2.getTripFirstPrice(), t1.getTripFirstPrice());
		}
	};
	
	/**
     * sort ascendingly by the departure time
     */
	public static final Comparator<Trip> DepatureAsc = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return t1.getDepartureTime().compareTo(t2.getDepartureTime());
		}
	};
	
	/**
     * sort descendingly by the departure time
     */
	public static final Comparator<Trip> DepatureDes = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return t2.getDepartureTime().compareTo(t1.getDepartureTime());
		}
	};
	
	/**
     * sort ascendingly by the arrival time 
     */
	public static final Comparator<Trip> ArrivalAsc = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return t1.getWholeTripArrivalTime().compareTo(t2.getWholeTripArrivalTime());
		}
	};
	
	/**
     * sort descendingly by the arrival time 
     */
	public static final Comparator<Trip> ArrivalDes = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return t2.getWholeTripArrivalTime().compareTo(t1.getWholeTripArrivalTime());
		}
	};
	
	/**
     * sort ascendingly by the total travel time (including flight time, stopover time)
     */
	public static final Comparator<Trip> TotalTimeAsc = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return Double.compare(t1.getTotalTimetoCompare(), t2.getTotalTimetoCompare());
		}
	};
	
	/**
     * sort descendingly by the total travel time (including flight time, stopover time)
     */
	public static final Comparator<Trip> TotalTimeDes = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			return Double.compare(t2.getTotalTimetoCompare(), t1.getTotalTimetoCompare());
		}
	};
	
	/**
     * sort ascendingly by the number of stopover 
     */
	public static final Comparator<Leg_Trip> StopoverAsc = new Comparator<Leg_Trip>() {
		@Override
		public int compare(Leg_Trip l1, Leg_Trip l2) {
			return l2.getLegTripStopOver().compareTo(l1.getLegTripStopOver());
		}
	};

	/**
     * sort descendingly by the number of stopover 
     */
	public static final Comparator<Leg_Trip> StopoverDes = new Comparator<Leg_Trip>() {
		@Override
		public int compare(Leg_Trip l1, Leg_Trip l2) {
			return l2.getLegTripStopOver().compareTo(l1.getLegTripStopOver());
		}
	};
}
