package UI;

import javax.swing.*;
import javax.xml.ws.Response;

import BL.Flight;
import BL.Leg_Trip;
import BL.Reserve;
import BL.Trip;
import DB.ReserveFlight;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		lblNewLabel.setBounds(473, 32, 243, 95);
		Jpanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Detail_Frame.class.getResource("/image/download1.png")));
		lblNewLabel_1.setBounds(87, 32, 152, 95);
		Jpanel.add(lblNewLabel_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("WPI World Plane.Inc");
		textPane.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		textPane.setBounds(839, 681, 331, 46);
		Jpanel.add(textPane);
		
		//test.setText(trips.get(RowNum).toString());
		
		JButton btnNewButton = new JButton("Reserve");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int respon = 0;
				List<Flight> flightlist;
				Trip selectedTrip = trips.get(RowNum);
				flightlist = selectedTrip.getLeg_tripList().get(0).getFlightList();
				Set<Flight> flights = new HashSet<Flight>(flightlist);
				try {
					ReserveFlight.lock();
					respon = ReserveFlight.reserve(flights,"FirstClass");
					ReserveFlight.unlock();
					System.out.println(respon);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(respon == 202) {
					JOptionPane.showMessageDialog(Jpanel, "Reserve success");
				}
			}
		});
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnNewButton.setBounds(904, 605, 165, 59);
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
		Back.setBounds(143, 605, 165, 59);
		Jpanel.add(Back);
		
		JLabel from = new JLabel("1");
		from.setHorizontalAlignment(SwingConstants.CENTER);
		from.setIcon(null);
		from.setForeground(Color.BLUE);
		from.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		from.setBounds(87, 219, 198, 71);
		Jpanel.add(from);
		
		JLabel stopover = new JLabel("2");
		stopover.setHorizontalAlignment(SwingConstants.CENTER);
		stopover.setForeground(Color.GREEN);
		stopover.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		stopover.setBounds(410, 233, 355, 42);
		Jpanel.add(stopover);
		
		JLabel to = new JLabel("3");
		to.setHorizontalAlignment(SwingConstants.CENTER);
		to.setIcon(null);
		to.setForeground(Color.MAGENTA);
		to.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		to.setBounds(840, 219, 212, 71);
		Jpanel.add(to);
		
		JLabel departTime = new JLabel("4");
		departTime.setHorizontalAlignment(SwingConstants.CENTER);
		departTime.setForeground(Color.BLUE);
		departTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		departTime.setBounds(12, 274, 291, 71);
		Jpanel.add(departTime);
		
		JLabel ArrivalTime = new JLabel("5");
		ArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		ArrivalTime.setForeground(Color.MAGENTA);
		ArrivalTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		ArrivalTime.setBounds(874, 274, 296, 71);
		Jpanel.add(ArrivalTime);
		
		JLabel TotalTime = new JLabel("6");
		TotalTime.setHorizontalAlignment(SwingConstants.CENTER);
		TotalTime.setForeground(Color.BLACK);
		TotalTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		TotalTime.setBounds(301, 140, 576, 42);
		Jpanel.add(TotalTime);
		
		JLabel back1 = new JLabel("3");
		back1.setHorizontalAlignment(SwingConstants.CENTER);
		back1.setForeground(Color.MAGENTA);
		back1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		back1.setBounds(87, 378, 212, 71);
		Jpanel.add(back1);
		
		JLabel back2 = new JLabel("1");
		back2.setHorizontalAlignment(SwingConstants.CENTER);
		back2.setForeground(Color.BLUE);
		back2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		back2.setBounds(861, 378, 198, 71);
		Jpanel.add(back2);
		
		JLabel backStopOver = new JLabel("2");
		backStopOver.setHorizontalAlignment(SwingConstants.CENTER);
		backStopOver.setForeground(Color.GREEN);
		backStopOver.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		backStopOver.setBounds(410, 392, 355, 42);
		Jpanel.add(backStopOver);
		
		JLabel back1Time = new JLabel("4");
		back1Time.setHorizontalAlignment(SwingConstants.CENTER);
		back1Time.setForeground(Color.MAGENTA);
		back1Time.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		back1Time.setBounds(12, 433, 291, 71);
		Jpanel.add(back1Time);
		
		JLabel back2Time = new JLabel("5");
		back2Time.setHorizontalAlignment(SwingConstants.CENTER);
		back2Time.setForeground(Color.BLUE);
		back2Time.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		back2Time.setBounds(874, 433, 296, 71);
		Jpanel.add(back2Time);
		
		JLabel TotalTime2 = new JLabel("6");
		TotalTime2.setHorizontalAlignment(SwingConstants.CENTER);
		TotalTime2.setForeground(Color.BLACK);
		TotalTime2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		TotalTime2.setBounds(301, 535, 576, 42);
		Jpanel.add(TotalTime2);
		
		JLabel lblNewLabel_2 = new JLabel("You need to pay:");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		lblNewLabel_2.setBounds(334, 603, 296, 59);
		Jpanel.add(lblNewLabel_2);
		
		JLabel Money = new JLabel("New label");
		Money.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		Money.setBounds(642, 602, 205, 60);
		Jpanel.add(Money);
		
		JLabel LegTrivalTime1 = new JLabel("7");
		LegTrivalTime1.setHorizontalAlignment(SwingConstants.CENTER);
		LegTrivalTime1.setForeground(Color.BLACK);
		LegTrivalTime1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		LegTrivalTime1.setBounds(301, 288, 576, 42);
		Jpanel.add(LegTrivalTime1);
		
		JLabel LegTravelTime2 = new JLabel("8");
		LegTravelTime2.setHorizontalAlignment(SwingConstants.CENTER);
		LegTravelTime2.setForeground(Color.BLACK);
		LegTravelTime2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		LegTravelTime2.setBounds(301, 447, 576, 42);
		Jpanel.add(LegTravelTime2);
		
		JLabel AirplaneModel1 = new JLabel("7");
		AirplaneModel1.setHorizontalAlignment(SwingConstants.CENTER);
		AirplaneModel1.setForeground(Color.BLACK);
		AirplaneModel1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		AirplaneModel1.setBounds(301, 183, 576, 42);
		Jpanel.add(AirplaneModel1);
		
		JLabel AirplaneModel2 = new JLabel("7");
		AirplaneModel2.setHorizontalAlignment(SwingConstants.CENTER);
		AirplaneModel2.setForeground(Color.BLACK);
		AirplaneModel2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		AirplaneModel2.setBounds(301, 489, 576, 42);
		Jpanel.add(AirplaneModel2);
		
		int oneOrRound=0;
		oneOrRound = trips.get(0).getLeg_tripList().size();
		if(oneOrRound==1) {
			//one-way trip
			Trip selectedTrip = trips.get(RowNum);
			from.setText(selectedTrip.getLeg_tripList().get(0).getFlightList().get(0).getDepatureCode());
			stopover.setText(selectedTrip.getLeg_tripList().get(0).getLegTripStopOver()+"-->");
			to.setText(selectedTrip.getLeg_tripList().get(0).getLegTripArrivalCode());
			departTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegDepartTime(selectedTrip.getDepartureTime()).toString());
			ArrivalTime.setText(selectedTrip.getLeg_tripList().get(0).getLocalLegArrivalTime(selectedTrip.getArrivalTime()).toString());
			TotalTime.setText("------"+selectedTrip.getLeg_tripList().get(0).getTotalTime()+"------");
			if(seatclass == 0) {
				//coachPrice show
				Money.setText(String.format("%1$,.2f", selectedTrip.getTripCoachPrice())+"$");
			}else {
				//FisrtPrice show
				Money.setText(String.format("%1$,.2f", selectedTrip.getTripFirstPrice())+"$");
			}
			AirplaneModel1.setText(selectedTrip.getLeg_tripList().get(0).getAirplaneModel());
			LegTrivalTime1.setText(selectedTrip.getLeg_tripList().get(0).LegsegmentTime());
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
			TotalTime.setText("------"+selectedTrip.getLeg_tripList().get(0).getTotalTime()+"------");
			LegTrivalTime1.setText(selectedTrip.getLeg_tripList().get(0).LegsegmentTime());
			AirplaneModel1.setText(selectedTrip.getLeg_tripList().get(0).getAirplaneModel());
			
			
			back1.setText(selectedTrip.getLeg_tripList().get(1).getFlightList().get(0).getDepatureCode());
			backStopOver.setText(selectedTrip.getLeg_tripList().get(1).getLegTripStopOver()+"-->");
			back2.setText(selectedTrip.getLeg_tripList().get(1).getLegTripArrivalCode());
			back1Time.setText(selectedTrip.getLeg_tripList().get(1).getLocalLegDepartTime(selectedTrip.getDepartureTime2()).toString());
			back2Time.setText(selectedTrip.getLeg_tripList().get(1).getLocalLegArrivalTime(selectedTrip.getArrivalTime2()).toString());
			TotalTime2.setText("------"+selectedTrip.getLeg_tripList().get(1).getTotalTime()+"------");
			LegTravelTime2.setText(selectedTrip.getLeg_tripList().get(1).LegsegmentTime());
			AirplaneModel2.setText(selectedTrip.getLeg_tripList().get(1).getAirplaneModel());
			
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
