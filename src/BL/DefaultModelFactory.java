package BL;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class DefaultModelFactory {
	
	private HashMap<Integer,Flight> mapflight= new HashMap<Integer,Flight>();
	
	public static DefaultTableModel getRoundModel(ArrayList<Trip> trips,int seat) {
		//Round-trip model
		//seat=0 coach seat ,seat=1 first class seat
		
		DefaultTableModel model = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;
		};
		model.addColumn("test");
		if(seat==0) {
			//Round-way trip, coach seat
			for (Trip t: trips) {
				String[] temp = {t.leg_tripList.toString()};
				model.addRow(temp);
			}
		}else {
			//Round-way trip, first class seat
			for(Trip t: trips) {
				String[] temp= {t.leg_tripList.toString()};
				model.addRow(temp);
			}
		}
		return model;
	}
	
	public static DefaultTableModel getOneWayModel(ArrayList<Trip> trips,int seat) {
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("test");
		if(seat==0) {
			//One-way trip, coach seat
			for(Trip t: trips) {
				String[] temp= {t.leg_tripList.toString()};
				model.addRow(temp);
			}
		}else {
			for(Trip t:trips) {
				String[] temp= {t.leg_tripList.toString()};
				model.addRow(temp);
			}
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
