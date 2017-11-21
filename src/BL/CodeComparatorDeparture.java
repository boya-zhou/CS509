package BL;

import java.util.Comparator;

public class CodeComparatorDeparture implements Comparator<Flight>{
	@Override
	public int compare(Flight a, Flight b) {
		return a.depatureCode.compareToIgnoreCase(b.depatureCode);
	}
	
}
