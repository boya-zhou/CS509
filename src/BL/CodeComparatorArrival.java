package BL;

import java.util.Comparator;

public class CodeComparatorArrival implements Comparator<Flight>{
	@Override
	public int compare(Flight a, Flight b) {
		return a.arrivalCode.compareToIgnoreCase(b.arrivalCode);
	}
}
