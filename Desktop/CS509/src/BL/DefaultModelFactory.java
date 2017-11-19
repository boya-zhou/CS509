package BL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import java_demo_and_sandbox.timeAddTest;

public class DefaultModelFactory {
	
	private HashMap<Integer,Flight> mapflight= new HashMap<Integer,Flight>();
	
	public static DefaultTableModel getTableModel(ArrayList<Trip> trips) {
		
		DefaultTableModel model = new DefaultTableModel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		};
		model.addColumn("test");
		for (Trip t: trips) {
			String[] temp = {t.leg_tripList.toString()};
			model.addRow(temp);
		}
		return model;
	}
	
	public static DefaultTableModel getTableModelR(ArrayList<Trip> trips) {
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("test");
		for(Trip t: trips) {
			String[] temp= {t.leg_tripList.toString()};
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
