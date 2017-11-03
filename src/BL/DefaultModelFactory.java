package BL;

import java.util.HashMap;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class DefaultModelFactory {
	
	private HashMap<Integer,Flight> mapflight= new HashMap<Integer,Flight>();
	
	public static DefaultTableModel getTableModel(Set<Flight> set) {
		
		DefaultTableModel model = new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		};
		model.addColumn("Depart Code");
		model.addColumn("Depart Time");
		model.addColumn("Arrival Code");
		model.addColumn("Arrival Time");
		model.addColumn("Airplane Model");
		model.addColumn("Flight Time");
		for (Flight f : set) {
			String[] temp = {f.getDepatureCode(),f.getDepatureTime().toString(),f.getArrivalCode(),f.getArrivalTime().toString(),f.getAirplaneModel()};
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
