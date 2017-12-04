package DB;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BL.Airplane.SeatType;
import BL.Flight;

/**
 * test both ReserveFlight and XMLparser (The reserve part)
 * since XMLparser by its own is meaningless,
 * it has to be coupled with other classes to be meaningful
 * @author tpatikorn
 *
 */
public class ReserveFlightTest {
	@Before
	public void setup() {

	}

	@Test
	public void simpleTestLock() throws IOException {
		ReserveFlight.lock();
		
	}

	@Test
	public void simpleTestUnlock() throws IOException {
		ReserveFlight.unlock();
	}

	@Test
	public void testLockThenUnlock() throws IOException {
		ReserveFlight.lock();
		ReserveFlight.unlock();
	}

	@Test
	public void repeatedLockUnlock() throws IOException {
		for(int i = 0; i < 10; i++) {
			ReserveFlight.lock();
			ReserveFlight.unlock();
		}
	}

	@Test
	public void weirdLockUnlockStack() throws IOException {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j <= i; j++) {
				ReserveFlight.lock();
			}
			ReserveFlight.unlock();
		}
	}
	
	@Test
	public void testTryReserve() throws IOException {
		List<Flight> flights = new ArrayList<Flight>(GetData.getArrivalFlightInfo("BOS", LocalDate.of(2017, 12, 28)));
		List<Flight> toReserve = flights.subList(3, 6);
		System.out.println(toReserve);
		SeatType[] seats = {SeatType.COACH, SeatType.FIRST_CLASS, SeatType.COACH};
		List<SeatType> seatType = Arrays.asList(seats);
		ReserveFlight.lock();
        ReserveFlight.reserve(toReserve, seatType);
		ReserveFlight.unlock();
	}

	@After
	public void destroy() throws IOException {
		ReserveFlight.unlock();
	}
	
}
