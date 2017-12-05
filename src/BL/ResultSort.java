package BL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class is responsible for sorting Trips according to criteria
 */
public class ResultSort {

	/**
	 * sort the array out-of-place
	 * the original trips remain unchanged
	 * @param trips original list of trips
	 * @param comparator the sort criterion 
	 * @return a new array that contain the same items as trips, but sorted according to comparator
	 */
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
	public static final Comparator<Trip> StopoverAsc = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			List<Leg_Trip> l1 = t1.getLeg_tripList();
			int t1stops = 0;
			for(Leg_Trip l: l1) {
				t1stops += l.getTripSize() - 1;
			}
			List<Leg_Trip> l2 = t2.getLeg_tripList();
			int t2stops = 0;
			for(Leg_Trip l: l2) {
				t2stops += l.getTripSize() - 1;
			}
			return Integer.compare(t1stops, t2stops);
		}
	};

	/**
     * sort descendingly by the number of stopover 
     */
	public static final Comparator<Trip> StopoverDes = new Comparator<Trip>() {
		@Override
		public int compare(Trip t1, Trip t2) {
			List<Leg_Trip> l1 = t1.getLeg_tripList();
			int t1stops = 0;
			for(Leg_Trip l: l1) {
				t1stops += l.getTripSize() - 1;
			}
			List<Leg_Trip> l2 = t2.getLeg_tripList();
			int t2stops = 0;
			for(Leg_Trip l: l2) {
				t2stops += l.getTripSize() - 1;
			}
			return Integer.compare(t2stops, t1stops);
		}
	};
}
