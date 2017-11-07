package BL;

import java.util.HashMap;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class DefaultModelFactory {
	
	private HashMap<Integer,Flight> mapflight= new HashMap<Integer,Flight>();
	
	/**
	 * @param set
	 * @return
	 */
	public static DefaultTableModel getTableModel(Set<Flight> set) {
		
		DefaultTableModel model = new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		};
		model.addColumn("Flight Number");
		model.addColumn("Depart Code");
		model.addColumn("Depart Time");
		model.addColumn("Arrival Code");
		model.addColumn("Arrival Time");
		//model.addColumn("Airplane Model");
		model.addColumn("Flight Time");
		model.addColumn("First class seats");
		model.addColumn("Fisrt class price");
		model.addColumn("Economy seats");
		model.addColumn("Economy price");
		
		for (Flight f : set) {
			String[] temp = {Integer.toString(f.getFlightNumber()),f.getDepatureCode(),f.getDepatureTime().toString(),f.getArrivalCode(),f.getArrivalTime().toString(),
					Integer.toString(f.getFlightTime()),Integer.toString(f.getRemain_FirstClass()),Double.toString(f.getPrice_FirstClass()),
					Integer.toString(f.getRemain_Coach()),Double.toString(f.getPrice_Coach())};
			model.addRow(temp);
		}
		return model;
	}

	public HashMap<Integer,Flight> getMapflight() {
		return mapflight;
	}

	public void setMapflight(HashMap<Integer,Flight> mapflight) {
		this.mapflight = mapflight;
	}

	public void addTableModelListener(TableModelListener arg0) {
		
	}
}
