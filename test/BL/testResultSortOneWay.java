package BL;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import org.junit.Test;

import BL.result_generator.GetTrip;
import static BL.ResultSort.*;
import static org.junit.Assert.*;

/**
 * test ResultSort for oneway
 *
 */
public class testResultSortOneWay {
	
	private final ArrayList<Trip> trips1Final;
	{
		int year = 2017;
		int month = 12;
		int day = 22;
		try {
			trips1Final = GetTrip.getOneWayTrip("BOS", LocalDate.of(year, month, day), "ORD");
		} catch (ClassNotFoundException | IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Test
	public void testArrivalAsc() {
		Comparator<Trip> comp = ArrivalAsc;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getArrivalTime().isBefore(thisTrip.getArrivalTime()) || 
					previousTrip.getArrivalTime().equals(thisTrip.getArrivalTime()));
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testArrivalDes() {
		Comparator<Trip> comp = ArrivalDes;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getArrivalTime().isAfter(thisTrip.getArrivalTime()) || 
					previousTrip.getArrivalTime().equals(thisTrip.getArrivalTime()));
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testCoachPriceAsc() {
		Comparator<Trip> comp = CoachPriceAsc;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getTripCoachPrice() <= thisTrip.getTripCoachPrice());
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testCoachPriceDes() {
		Comparator<Trip> comp = CoachPriceDes;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getTripCoachPrice() >= thisTrip.getTripCoachPrice());
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testDepatureAsc() {
		Comparator<Trip> comp = DepatureAsc;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getDepartureTime().isBefore(thisTrip.getDepartureTime()) || 
					previousTrip.getDepartureTime().equals(thisTrip.getDepartureTime()));
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testDepatureDes() {
		Comparator<Trip> comp = DepatureDes;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getDepartureTime().isAfter(thisTrip.getDepartureTime()) || 
					previousTrip.getDepartureTime().equals(thisTrip.getDepartureTime()));
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testFCPriceAsc() {
		Comparator<Trip> comp = FCPriceAsc;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getTripFirstPrice() <= thisTrip.getTripFirstPrice());
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testFCPriceDes() {
		Comparator<Trip> comp = FCPriceDes;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getTripFirstPrice() >= thisTrip.getTripFirstPrice());
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testStopoverAsc() {
		Comparator<Trip> comp = StopoverAsc;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getLeg_tripList().get(0).getTripSize() <= thisTrip.getLeg_tripList().get(0).getTripSize());
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testStopoverDes() {
		Comparator<Trip> comp = StopoverDes;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getLeg_tripList().get(0).getTripSize() >= thisTrip.getLeg_tripList().get(0).getTripSize());
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testTotalTimeAsc() {
		Comparator<Trip> comp = TotalTimeAsc;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getTotalTimetoCompare() <= thisTrip.getTotalTimetoCompare());
			previousTrip = thisTrip;
		}
	}

	@Test
	public void testTotalTimeDes() {
		Comparator<Trip> comp = TotalTimeDes;
		ArrayList<Trip> original = trips1Final;
		
		ArrayList<Trip> trips = new ArrayList<>(original);
		ArrayList<Trip> sorted = sort(trips, comp);
		
		//check if same size 
		assertEquals(trips.size(), original.size());
		assertEquals(sorted.size(), original.size());
		
		//check if contain the same items
		assertTrue(trips.containsAll(original));
		assertTrue(sorted.containsAll(original));
		
		//check if the sort does not happen in-place
		for(int i = 0 ; i < trips.size(); i++) {
			assertEquals(trips.get(i), original.get(i));
		}
		
		//check if sorted
		Trip previousTrip = sorted.get(0);
		for(int i = 1; i < trips.size(); i++) {
			Trip thisTrip = sorted.get(i);
			assertTrue(previousTrip.getTotalTimetoCompare() >= thisTrip.getTotalTimetoCompare());
			previousTrip = thisTrip;
		}
	}
}
