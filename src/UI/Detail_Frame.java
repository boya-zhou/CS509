package UI;

import javax.swing.*;
import BL.Flight;
import BL.Trip;
import BL.Airplane.SeatType;
import DB.ReserveFlight;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Detail_Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel Jpanel;
	public List<Flight> flightlist;
	private int oneOrRound;

	public Detail_Frame(ArrayList<Trip> trips,int RowNum,int seatclass) {
		setTitle("Detail");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		Jpanel = new JPanel();
		Jpanel.setBackground(Color.WHITE);
		getContentPane().add(Jpanel);
		Jpanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trip Details");
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setIcon(new ImageIcon(Detail_Frame.class.getResource("/com/sun/java/swing/plaf/windows/icons/Warn.gif")));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 40));
		lblNewLabel.setBounds(458, 0, 272, 95);
		Jpanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Detail_Frame.class.getResource("/image/download1.png")));
		lblNewLabel_1.setBounds(87, 8, 152, 95);
		Jpanel.add(lblNewLabel_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("WPI World Plane.Inc");
		textPane.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		textPane.setBounds(839, 707, 331, 46);
		Jpanel.add(textPane);
		
		//test.setText(trips.get(RowNum).toString());
		
		JButton btnNewButton = new JButton("Reserve");
		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				flightlist=null;
				List<SeatType> seat = new ArrayList<SeatType>();
				int respon = 0;
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

				
				try {
					ReserveFlight.lock();
					respon = ReserveFlight.reserve(flightlist,seat);
					ReserveFlight.unlock();
					System.out.println("try to reserve");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(respon == 0) {
					JOptionPane.showMessageDialog(Jpanel, "Reserve success!");
				}else {
					JOptionPane.showMessageDialog(Jpanel, "Reserve fail!");
				}
			}
		});
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnNewButton.setBounds(904, 635, 165, 59);
		Jpanel.add(btnNewButton);
		
		JButton Back = new JButton("Back");
		
		//not finish
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(true) {
					setVisible(false);
					Detail_Frame.this.dispatchEvent(new WindowEvent(Detail_Frame.this, WindowEvent.WINDOW_CLOSED));
				}
			}
		});
		
		Back.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		Back.setBounds(143, 635, 165, 59);
		Jpanel.add(Back);
		
		JLabel from = new JLabel("1");
		from.setHorizontalAlignment(SwingConstants.CENTER);
		from.setIcon(null);
		from.setForeground(Color.BLUE);
		from.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		from.setBounds(87, 183, 198, 71);
		Jpanel.add(from);
		
		JLabel stopover = new JLabel("2");
		stopover.setHorizontalAlignment(SwingConstants.CENTER);
		stopover.setForeground(Color.GREEN);
		stopover.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		stopover.setBounds(410, 197, 355, 42);
		Jpanel.add(stopover);
		
		JLabel to = new JLabel("3");
		to.setHorizontalAlignment(SwingConstants.CENTER);
		to.setIcon(null);
		to.setForeground(Color.MAGENTA);
		to.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		to.setBounds(857, 183, 212, 71);
		Jpanel.add(to);
		
		JLabel departTime = new JLabel("4");
		departTime.setHorizontalAlignment(SwingConstants.CENTER);
		departTime.setForeground(Color.BLUE);
		departTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		departTime.setBounds(12, 238, 291, 71);
		Jpanel.add(departTime);
		
		JLabel ArrivalTime = new JLabel("5");
		ArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		ArrivalTime.setForeground(Color.MAGENTA);
		ArrivalTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		ArrivalTime.setBounds(874, 238, 296, 71);
		Jpanel.add(ArrivalTime);
		
		JLabel TotalTime = new JLabel("TotalTime");
		TotalTime.setHorizontalAlignment(SwingConstants.CENTER);
		TotalTime.setForeground(Color.BLACK);
		TotalTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		TotalTime.setBounds(221, 100, 726, 42);
		Jpanel.add(TotalTime);
		
		JLabel back1 = new JLabel("3");
		back1.setHorizontalAlignment(SwingConstants.CENTER);
		back1.setForeground(Color.MAGENTA);
		back1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		back1.setBounds(96, 439, 212, 71);
		Jpanel.add(back1);
		
		JLabel back2 = new JLabel("1");
		back2.setHorizontalAlignment(SwingConstants.CENTER);
		back2.setForeground(Color.BLUE);
		back2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		back2.setBounds(874, 439, 198, 71);
		Jpanel.add(back2);
		
		JLabel backStopOver = new JLabel("2");
		backStopOver.setHorizontalAlignment(SwingConstants.CENTER);
		backStopOver.setForeground(Color.GREEN);
		backStopOver.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		backStopOver.setBounds(410, 453, 355, 42);
		Jpanel.add(backStopOver);
		
		JLabel back1Time = new JLabel("4");
		back1Time.setHorizontalAlignment(SwingConstants.CENTER);
		back1Time.setForeground(Color.MAGENTA);
		back1Time.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		back1Time.setBounds(12, 494, 291, 71);
		Jpanel.add(back1Time);
		
		JLabel back2Time = new JLabel("5");
		back2Time.setHorizontalAlignment(SwingConstants.CENTER);
		back2Time.setForeground(Color.BLUE);
		back2Time.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		back2Time.setBounds(874, 494, 296, 71);
		Jpanel.add(back2Time);
		
		JLabel TotalTime2 = new JLabel("TotalTime");
		TotalTime2.setHorizontalAlignment(SwingConstants.CENTER);
		TotalTime2.setForeground(Color.BLACK);
		TotalTime2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		TotalTime2.setBounds(221, 369, 726, 42);
		Jpanel.add(TotalTime2);
		
		JLabel lblNewLabel_2 = new JLabel("You need to pay:");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		lblNewLabel_2.setBounds(334, 633, 296, 59);
		Jpanel.add(lblNewLabel_2);
		
		JLabel Money = new JLabel("New label");
		Money.setForeground(Color.RED);
		Money.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		Money.setBounds(642, 634, 205, 60);
		Jpanel.add(Money);
		
		JLabel LegTrivalTime1 = new JLabel("7");
		LegTrivalTime1.setHorizontalAlignment(SwingConstants.CENTER);
		LegTrivalTime1.setForeground(Color.BLACK);
		LegTrivalTime1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		LegTrivalTime1.setBounds(301, 252, 576, 42);
		Jpanel.add(LegTrivalTime1);
		
		JLabel LegTravelTime2 = new JLabel("8");
		LegTravelTime2.setHorizontalAlignment(SwingConstants.CENTER);
		LegTravelTime2.setForeground(Color.BLACK);
		LegTravelTime2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		LegTravelTime2.setBounds(301, 508, 576, 42);
		Jpanel.add(LegTravelTime2);
		
		JLabel AirplaneModel1 = new JLabel("7");
		AirplaneModel1.setHorizontalAlignment(SwingConstants.CENTER);
		AirplaneModel1.setForeground(Color.BLACK);
		AirplaneModel1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		AirplaneModel1.setBounds(301, 294, 576, 42);
		Jpanel.add(AirplaneModel1);
		
		JLabel AirplaneModel2 = new JLabel("7");
		AirplaneModel2.setHorizontalAlignment(SwingConstants.CENTER);
		AirplaneModel2.setForeground(Color.BLACK);
		AirplaneModel2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		AirplaneModel2.setBounds(301, 550, 576, 42);
		Jpanel.add(AirplaneModel2);
		
		JLabel timeDetail = new JLabel("segTime");
		timeDetail.setHorizontalAlignment(SwingConstants.CENTER);
		timeDetail.setForeground(Color.BLACK);
		timeDetail.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		timeDetail.setBounds(132, 142, 905, 42);
		Jpanel.add(timeDetail);
		
		JLabel timeDetail2 = new JLabel("segTime");
		timeDetail2.setHorizontalAlignment(SwingConstants.CENTER);
		timeDetail2.setForeground(Color.BLACK);
		timeDetail2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		timeDetail2.setBounds(132, 409, 905, 42);
		Jpanel.add(timeDetail2);
		
		JLabel Money1 = new JLabel("Money1");
		Money1.setForeground(Color.RED);
		Money1.setHorizontalAlignment(SwingConstants.CENTER);
		Money1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		Money1.setBounds(978, 334, 121, 33);
		Jpanel.add(Money1);
		
		JLabel Money2 = new JLabel("Money2");
		Money2.setForeground(Color.RED);
		Money2.setHorizontalAlignment(SwingConstants.CENTER);
		Money2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		Money2.setBounds(978, 589, 121, 33);
		Jpanel.add(Money2);
		

		oneOrRound = trips.get(0).getLeg_tripList().size();
		if(oneOrRound==1) {
			//one-way trip
			Trip selectedTrip = trips.get(RowNum);
			from.setText(selectedTrip.getLeg_tripList().get(0).getFlightList().get(0).getDepatureCode());
			stopover.setText(selectedTrip.getLeg_tripList().get(0).getLegTripStopOver()+"-->");
			to.setText(selectedTrip.getLeg_tripList().get(0).getLegTripArrivalCode());
			departTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegDepartTime(selectedTrip.getDepartureTime()).toString());
			ArrivalTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegArrivalTime(selectedTrip.getArrivalTime()).toString());
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
			departTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegDepartTime(selectedTrip.getDepartureTime()).toString());
			ArrivalTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegArrivalTime(selectedTrip.getArrivalTime()).toString());
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
			back1Time.setText(selectedTrip.getLeg_tripList().get(1).getLocalLegDepartTime(selectedTrip.getDepartureTime2()).toString());
			back2Time.setText(selectedTrip.getLeg_tripList().get(1).getLocalLegArrivalTime(selectedTrip.getArrivalTime2()).toString());
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
	
	public static void  invoke(ArrayList<Trip> trips, int RowNum,int seatclass) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Detail_Frame detail_Frame = new Detail_Frame(trips, RowNum,seatclass);
				detail_Frame.setBounds(0, 0, 1200, 800);
				detail_Frame.setVisible(true);	
				detail_Frame.setAlwaysOnTop(true);
			}
		});
		
	}
	public JPanel getPanel() {
		return Jpanel;
	}
}
