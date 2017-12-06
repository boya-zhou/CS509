package BL;

import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.management.modelmbean.ModelMBean;
import javax.swing.CellEditor;
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
		
		model.addColumn("DepartTime");
		model.addColumn("DepartCode");
		model.addColumn("StopOver");
		model.addColumn("ArrivalCode");
		model.addColumn("ArrivalTime");
		model.addColumn("TravelTime");
		model.addColumn("Price");
		if(seat==0) {
			//Round-way trip, coach seat
			for (Trip t: trips) {
				String[] temp1= {t.getLeg_tripList().get(0).getLocalLegDepartTime(t.getDepartureTime()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(0).getLegTripDepartCode().toString(),
						t.getLeg_tripList().get(0).getLegTripStopOver()+"-->",
						t.getLeg_tripList().get(0).getLegTripArrivalCode().toString(),
						t.getLeg_tripList().get(0).getLocalLegArrivalTime(t.getArrivalTime()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(0).getTotalTime(),
						String.format("%1$,.2f", t.getLeg_tripList().get(0).getLegTripCoachPrice())+"$"};
				model.addRow(temp1);
				String[] temp2= {t.getLeg_tripList().get(1).getLocalLegDepartTime(t.getDepartureTime2()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(1).getLegTripDepartCode().toString(),
						t.getLeg_tripList().get(1).getLegTripStopOver()+"-->",
						t.getLeg_tripList().get(1).getLegTripArrivalCode().toString(),
						t.getLeg_tripList().get(1).getLocalLegArrivalTime(t.getArrivalTime2()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(1).getTotalTime(),
						String.format("%1$,.2f", t.getLeg_tripList().get(1).getLegTripCoachPrice())+"$"};
				String[] temp3= {"","","","","",""
						,"Total:"+String.format("%1$,.2f", t.getLeg_tripList().get(1).getLegTripCoachPrice()+t.getLeg_tripList().get(0).getLegTripCoachPrice())+"$"};
				model.addRow(temp2);
				model.addRow(temp3);
			}
		}else {
			//Round-way trip, first class seat
			for (Trip t: trips) {
				String[] temp1= {t.getLeg_tripList().get(0).getLocalLegDepartTime(t.getDepartureTime()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(0).getLegTripDepartCode().toString(),
						t.getLeg_tripList().get(0).getLegTripStopOver()+"-->",
						t.getLeg_tripList().get(0).getLegTripArrivalCode().toString(),
						t.getLeg_tripList().get(0).getLocalLegArrivalTime(t.getArrivalTime()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(0).getTotalTime(),
						String.format("%1$,.2f", t.getLeg_tripList().get(0).getLegTripFirstPrice())+"$"};
				model.addRow(temp1);
				String[] temp2= {t.getLeg_tripList().get(1).getLocalLegDepartTime(t.getDepartureTime2()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(1).getLegTripDepartCode().toString(),
						t.getLeg_tripList().get(1).getLegTripStopOver()+"-->",
						t.getLeg_tripList().get(1).getLegTripArrivalCode().toString(),
						t.getLeg_tripList().get(1).getLocalLegArrivalTime(t.getArrivalTime2()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(1).getTotalTime(),
						String.format("%1$,.2f", t.getLeg_tripList().get(1).getLegTripFirstPrice())+"$"};
				String[] temp3= {"","","","","",""
						,"Total:"+String.format("%1$,.2f", t.getLeg_tripList().get(1).getLegTripFirstPrice()+t.getLeg_tripList().get(0).getLegTripFirstPrice())+"$"};
				model.addRow(temp2);
				model.addRow(temp3);
			}
		}
		return model;
	}
	
	public static DefaultTableModel getOneWayModel(ArrayList<Trip> trips,int seat) {
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("DepartTime");
		model.addColumn("DepartCode");
		model.addColumn("StopOver");
		model.addColumn("ArrivalCode");
		model.addColumn("ArrivalTime");
		model.addColumn("TravelTime");
		model.addColumn("Price");
		
		if(seat==0) {
			//One-way trip, coach seat
			for(Trip t: trips) {
				String[] temp= {t.getLeg_tripList().get(0).getLocalLegDepartTime(t.getDepartureTime()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(0).getLegTripDepartCode().toString(),
						t.getLeg_tripList().get(0).getLegTripStopOver()+"-->",
						t.getLeg_tripList().get(0).getLegTripArrivalCode().toString(),
						t.getLeg_tripList().get(0).getLocalLegArrivalTime(t.getArrivalTime()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(0).getTotalTime(),
						String.format("%1$,.2f", t.getTripCoachPrice())+"$"};
				model.addRow(temp);
			}
		}else {
			//One-way trip first-class seat
			for(Trip t: trips) {
				String[] temp= {t.getLeg_tripList().get(0).getLocalLegDepartTime(t.getDepartureTime()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(0).getLegTripDepartCode().toString(),
						t.getLeg_tripList().get(0).getLegTripStopOver()+"-->",
						t.getLeg_tripList().get(0).getLegTripArrivalCode().toString(),
						t.getLeg_tripList().get(0).getLocalLegArrivalTime(t.getArrivalTime()).toString().replaceAll("T", " "),
						t.getLeg_tripList().get(0).getTotalTime(),
						String.format("%1$,.2f", t.getTripFirstPrice())+"$"};
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
