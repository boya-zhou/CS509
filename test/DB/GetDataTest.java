package DB;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.*;
import org.junit.Test;

import BL.Airplane;

/**
 * test both GetData and XMLparser (the GET parts)
 * since XML parser by its own is meaningless
 * it has to be coupled with other classes to be meaningful
 * @author tpatikorn
 *
 */
public class GetDataTest {
	@Test
	public void testGetArrival() throws IOException {
		assertNotEquals(0, GetData.getArrivalFlightInfo("BOS", LocalDate.of(2017, 12, 12)).size());
	}

	@Test
	public void testGetDeparture() throws IOException {
		assertNotEquals(0, GetData.getDepartureFlightInfo("ORD", LocalDate.of(2017, 12, 13)).size());
	}

	@Test
	public void testGetAirplanesMap() {
		Map<String, Airplane> airplanes = GetData.getAllAirplaneMap();
		assertNotNull(airplanes);
		assertNotEquals(0, airplanes.size());
	}

	@Test
	public void testGetAirplanes() {
		assertNotEquals(0, GetData.getAllAirplanes().size());
	}

	@Test
	public void testGetAirports() {
		assertNotEquals(0, GetData.getAllAirports().size());
	}
}
