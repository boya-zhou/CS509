package DB;

import java.time.LocalDate;

import org.junit.Test;

public class GetDataTest {
	@Test
	public void testGet() {
		System.out.println(GetData.getArrivalFlightInfoXML("BOS", LocalDate.of(2017, 12, 12)));
	}
}
