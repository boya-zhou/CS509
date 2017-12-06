package UI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import BL.Flight;
import BL.Trip;
import BL.Airplane.SeatType;

public class DetailFrame_Controller {
	
	public List<Flight> flightlist;
	private int oneOrRound;
	
	public DetailFrame_Controller(ArrayList<Trip> trips,int RowNum,int seatclass,JButton Reserve,JButton Back,Detail_Frame detail_Frame,
			JLabel from,JLabel to, JLabel departTime,JLabel ArrivalTime,JLabel TotalTime,JLabel timeDetail,JLabel LegTrivalTime1,
			JLabel AirplaneModel1,JLabel stopover,JLabel Money1,JLabel back1,JLabel backStopOver, JLabel back2,JLabel back1Time,
			JLabel LegTravelTime2,JLabel back2Time,JLabel AirplaneModel2,JLabel TotalTime2,JLabel timeDetail2,JLabel Money2,JLabel Money) {
		
		//Reserve Button
		Reserve.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				flightlist=null;
				List<SeatType> seat = new ArrayList<SeatType>();
				Trip selectedTrip;
				if(oneOrRound==1) {
					selectedTrip=trips.get(RowNum);
					flightlist = selectedTrip.getLeg_tripList().get(0).getFlightList();
				}else {
					selectedTrip=trips.get(RowNum/3);
					flightlist = selectedTrip.getLeg_tripList().get(0).getFlightList();
					flightlist.addAll(selectedTrip.getLeg_tripList().get(1).getFlightList());
				}
				
				System.out.println(flightlist);
				int num = flightlist.size();
				for(int i=0;i<num;i++) {
					if(seatclass==0) {
						seat.add(SeatType.COACH);
					}else {
						seat.add(SeatType.FIRST_CLASS);
					}
				}
				Confirmation confirmation = new Confirmation(flightlist,seat);
			}
		});
		//Back Button
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(true) {
					detail_Frame.setVisible(false);
					detail_Frame.dispatchEvent(new WindowEvent(detail_Frame, WindowEvent.WINDOW_CLOSED));
				}
			}
		});
		//
		oneOrRound = trips.get(0).getLeg_tripList().size();
		if(oneOrRound==1) {
			//one-way trip
			Trip selectedTrip = trips.get(RowNum);
			from.setText(selectedTrip.getLeg_tripList().get(0).getFlightList().get(0).getDepatureCode());
			stopover.setText(selectedTrip.getLeg_tripList().get(0).getLegTripStopOver()+"-->");
			to.setText(selectedTrip.getLeg_tripList().get(0).getLegTripArrivalCode());
			departTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegDepartTime(selectedTrip.getDepartureTime()).toString().replaceAll("T", " ").replaceAll("T", " "));
			ArrivalTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegArrivalTime(selectedTrip.getArrivalTime()).toString().replaceAll("T", " "));
			TotalTime.setText("----------------"+selectedTrip.getLeg_tripList().get(0).getTotalTime()+"----------------");
			if(seatclass == 0) {
				//coachPrice show
				Money.setText(String.format("%1$,.2f", selectedTrip.getTripCoachPrice())+"$");
			}else {
				//FisrtPrice show
				Money.setText(String.format("%1$,.2f", selectedTrip.getTripFirstPrice())+"$");
			}
			AirplaneModel1.setText(selectedTrip.getLeg_tripList().get(0).getAirplaneModel());
			LegTrivalTime1.setText(selectedTrip.getLeg_tripList().get(0).LegsegmentTime());
			timeDetail.setText(selectedTrip.getLeg_tripList().get(0).getintervalTime());
			if(seatclass==0) {
				Money1.setText(String.format("%1$,.2f", selectedTrip.getTripCoachPrice())+"$");
			}else {
				Money1.setText(String.format("%1$,.2f", selectedTrip.getTripFirstPrice())+"$");
			}
			Money2.setVisible(false);
			timeDetail2.setVisible(false);
			AirplaneModel2.setVisible(false);
			back1.setVisible(false);
			backStopOver.setVisible(false);
			back2.setVisible(false);
			back1Time.setVisible(false);
			back2Time.setVisible(false);
			TotalTime2.setVisible(false);
			LegTravelTime2.setVisible(false);
		}else {
			//Round-way trip
			Trip selectedTrip = trips.get(RowNum/3);
			from.setText(selectedTrip.getLeg_tripList().get(0).getFlightList().get(0).getDepatureCode());
			stopover.setText(selectedTrip.getLeg_tripList().get(0).getLegTripStopOver()+"-->");
			to.setText(selectedTrip.getLeg_tripList().get(0).getLegTripArrivalCode());
			departTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegDepartTime(selectedTrip.getDepartureTime()).toString().replaceAll("T", " "));
			ArrivalTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegArrivalTime(selectedTrip.getArrivalTime()).toString().replaceAll("T", " "));
			TotalTime.setText("----------------"+selectedTrip.getLeg_tripList().get(0).getTotalTime()+"----------------");
			LegTrivalTime1.setText(selectedTrip.getLeg_tripList().get(0).LegsegmentTime());
			AirplaneModel1.setText(selectedTrip.getLeg_tripList().get(0).getAirplaneModel());
			timeDetail.setText(selectedTrip.getLeg_tripList().get(0).getintervalTime());
			if(seatclass==0) {
				Money1.setText(String.format("%1$,.2f", selectedTrip.getLeg_tripList().get(0).getLegTripCoachPrice())+"$");
			}else {
				Money1.setText(String.format("%1$,.2f", selectedTrip.getLeg_tripList().get(0).getLegTripFirstPrice())+"$");
			}
			
			
			back1.setText(selectedTrip.getLeg_tripList().get(1).getFlightList().get(0).getDepatureCode());
			backStopOver.setText(selectedTrip.getLeg_tripList().get(1).getLegTripStopOver()+"-->");
			back2.setText(selectedTrip.getLeg_tripList().get(1).getLegTripArrivalCode());
			back1Time.setText(selectedTrip.getLeg_tripList().get(1).getLocalLegDepartTime(selectedTrip.getDepartureTime2()).toString().replaceAll("T", " ").replaceAll("T", " ").replaceAll("T", " "));
			back2Time.setText(selectedTrip.getLeg_tripList().get(1).getLocalLegArrivalTime(selectedTrip.getArrivalTime2()).toString().replaceAll("T", " ").replaceAll("T", " ").replaceAll("T", " "));
			TotalTime2.setText("----------------"+selectedTrip.getLeg_tripList().get(1).getTotalTime()+"----------------");
			LegTravelTime2.setText(selectedTrip.getLeg_tripList().get(1).LegsegmentTime());
			AirplaneModel2.setText(selectedTrip.getLeg_tripList().get(1).getAirplaneModel());
			timeDetail2.setText(selectedTrip.getLeg_tripList().get(1).getintervalTime());
			if(seatclass==0) {
				Money2.setText(String.format("%1$,.2f", selectedTrip.getLeg_tripList().get(1).getLegTripCoachPrice())+"$");
			}else {
				Money2.setText(String.format("%1$,.2f", selectedTrip.getLeg_tripList().get(1).getLegTripFirstPrice())+"$");
			}
			
			if(seatclass==0) {
				//coachPrice show
				Money.setText(String.format("%1$,.2f", selectedTrip.getTripCoachPrice())+"$");
			}else {
				//FirstClass show
				Money.setText(String.format("%1$,.2f", selectedTrip.getTripFirstPrice())+"$");
			}
		}
	}
}
