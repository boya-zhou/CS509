package DB;

import java.io.IOException;

import org.junit.*;

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

	@After
	public void destroy() throws IOException {
		ReserveFlight.unlock();
	}

}
