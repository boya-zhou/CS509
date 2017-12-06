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
	
	private JButton Reserve;

	//One-way trip
	private JLabel from;
	private JLabel to;
	private JLabel departTime;
	private JLabel ArrivalTime;
	private JLabel TotalTime;
	private JLabel timeDetail;
	private JLabel LegTrivalTime1;
	private JLabel AirplaneModel1;
	private JLabel stopover;
	private JLabel Money1;
	
	//Round-way trip
	private JLabel back1;
	private JLabel backStopOver;
	private JLabel back2;
	private JLabel back1Time;
	private JLabel LegTravelTime2;
	private JLabel back2Time;
	private JLabel AirplaneModel2;
	private JLabel TotalTime2;
	private JLabel timeDetail2;
	private JLabel Money2;
	
	//TotalPrice
	private JLabel Money;
	
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
		
		Reserve = new JButton("Reserve");
		Reserve.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		Reserve.setBounds(904, 635, 165, 59);
		Jpanel.add(Reserve);
		
		JButton Back = new JButton("Back");	
		Back.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		Back.setBounds(143, 635, 165, 59);
		Jpanel.add(Back);
		
		from = new JLabel("1");
		from.setHorizontalAlignment(SwingConstants.CENTER);
		from.setIcon(null);
		from.setForeground(Color.BLUE);
		from.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		from.setBounds(87, 183, 198, 71);
		Jpanel.add(from);
		
		stopover = new JLabel("2");
		stopover.setHorizontalAlignment(SwingConstants.CENTER);
		stopover.setForeground(Color.GREEN);
		stopover.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		stopover.setBounds(410, 197, 355, 42);
		Jpanel.add(stopover);
		
		to = new JLabel("3");
		to.setHorizontalAlignment(SwingConstants.CENTER);
		to.setIcon(null);
		to.setForeground(Color.MAGENTA);
		to.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		to.setBounds(857, 183, 212, 71);
		Jpanel.add(to);
		
		departTime = new JLabel("4");
		departTime.setHorizontalAlignment(SwingConstants.CENTER);
		departTime.setForeground(Color.BLUE);
		departTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		departTime.setBounds(12, 238, 291, 71);
		Jpanel.add(departTime);
		
		ArrivalTime = new JLabel("5");
		ArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
		ArrivalTime.setForeground(Color.MAGENTA);
		ArrivalTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		ArrivalTime.setBounds(874, 238, 296, 71);
		Jpanel.add(ArrivalTime);
		
		TotalTime = new JLabel("TotalTime");
		TotalTime.setHorizontalAlignment(SwingConstants.CENTER);
		TotalTime.setForeground(Color.BLACK);
		TotalTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		TotalTime.setBounds(221, 100, 726, 42);
		Jpanel.add(TotalTime);
		
		back1 = new JLabel("3");
		back1.setHorizontalAlignment(SwingConstants.CENTER);
		back1.setForeground(Color.MAGENTA);
		back1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		back1.setBounds(96, 439, 212, 71);
		Jpanel.add(back1);
		
		back2 = new JLabel("1");
		back2.setHorizontalAlignment(SwingConstants.CENTER);
		back2.setForeground(Color.BLUE);
		back2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		back2.setBounds(874, 439, 198, 71);
		Jpanel.add(back2);
		
		backStopOver = new JLabel("2");
		backStopOver.setHorizontalAlignment(SwingConstants.CENTER);
		backStopOver.setForeground(Color.GREEN);
		backStopOver.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		backStopOver.setBounds(410, 453, 355, 42);
		Jpanel.add(backStopOver);
		
		back1Time = new JLabel("4");
		back1Time.setHorizontalAlignment(SwingConstants.CENTER);
		back1Time.setForeground(Color.MAGENTA);
		back1Time.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		back1Time.setBounds(12, 494, 291, 71);
		Jpanel.add(back1Time);
		
		back2Time = new JLabel("5");
		back2Time.setHorizontalAlignment(SwingConstants.CENTER);
		back2Time.setForeground(Color.BLUE);
		back2Time.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		back2Time.setBounds(874, 494, 296, 71);
		Jpanel.add(back2Time);
		
		TotalTime2 = new JLabel("TotalTime");
		TotalTime2.setHorizontalAlignment(SwingConstants.CENTER);
		TotalTime2.setForeground(Color.BLACK);
		TotalTime2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		TotalTime2.setBounds(221, 369, 726, 42);
		Jpanel.add(TotalTime2);
		
		JLabel lblNewLabel_2 = new JLabel("You need to pay:");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		lblNewLabel_2.setBounds(334, 633, 296, 59);
		Jpanel.add(lblNewLabel_2);
		
		Money = new JLabel("New label");
		Money.setForeground(Color.RED);
		Money.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		Money.setBounds(642, 634, 205, 60);
		Jpanel.add(Money);
		
		LegTrivalTime1 = new JLabel("7");
		LegTrivalTime1.setHorizontalAlignment(SwingConstants.CENTER);
		LegTrivalTime1.setForeground(Color.BLACK);
		LegTrivalTime1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		LegTrivalTime1.setBounds(301, 252, 576, 42);
		Jpanel.add(LegTrivalTime1);
		
		LegTravelTime2 = new JLabel("8");
		LegTravelTime2.setHorizontalAlignment(SwingConstants.CENTER);
		LegTravelTime2.setForeground(Color.BLACK);
		LegTravelTime2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		LegTravelTime2.setBounds(301, 508, 576, 42);
		Jpanel.add(LegTravelTime2);
		
		AirplaneModel1 = new JLabel("7");
		AirplaneModel1.setHorizontalAlignment(SwingConstants.CENTER);
		AirplaneModel1.setForeground(Color.BLACK);
		AirplaneModel1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		AirplaneModel1.setBounds(301, 294, 576, 42);
		Jpanel.add(AirplaneModel1);
		
		AirplaneModel2 = new JLabel("7");
		AirplaneModel2.setHorizontalAlignment(SwingConstants.CENTER);
		AirplaneModel2.setForeground(Color.BLACK);
		AirplaneModel2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		AirplaneModel2.setBounds(301, 550, 576, 42);
		Jpanel.add(AirplaneModel2);
		
		timeDetail = new JLabel("segTime");
		timeDetail.setHorizontalAlignment(SwingConstants.CENTER);
		timeDetail.setForeground(Color.BLACK);
		timeDetail.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		timeDetail.setBounds(132, 142, 905, 42);
		Jpanel.add(timeDetail);
		
		timeDetail2 = new JLabel("segTime");
		timeDetail2.setHorizontalAlignment(SwingConstants.CENTER);
		timeDetail2.setForeground(Color.BLACK);
		timeDetail2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		timeDetail2.setBounds(132, 409, 905, 42);
		Jpanel.add(timeDetail2);
		
		Money1 = new JLabel("Money1");
		Money1.setForeground(Color.RED);
		Money1.setHorizontalAlignment(SwingConstants.CENTER);
		Money1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		Money1.setBounds(978, 334, 121, 33);
		Jpanel.add(Money1);
		
		Money2 = new JLabel("Money2");
		Money2.setForeground(Color.RED);
		Money2.setHorizontalAlignment(SwingConstants.CENTER);
		Money2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		Money2.setBounds(978, 589, 121, 33);
		Jpanel.add(Money2);
		
		
		DetailFrame_Controller controller = new DetailFrame_Controller(trips,RowNum,seatclass,Reserve,Back,this,
				from,to, departTime,ArrivalTime,TotalTime,timeDetail,LegTrivalTime1,AirplaneModel1,stopover,Money1,back1,backStopOver,back2,back1Time,
				LegTravelTime2,back2Time,AirplaneModel2,TotalTime2,timeDetail2,Money2,Money);
	}
	
	
	public static void  invoke(ArrayList<Trip> trips, int RowNum,int seatclass) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Detail_Frame detail_Frame = new Detail_Frame(trips, RowNum,seatclass);
				detail_Frame.setBounds(200, 200, 1200, 800);
				detail_Frame.setVisible(true);	
				detail_Frame.setAlwaysOnTop(true);
			}
		});
		
	}
	public JPanel getPanel() {
		return Jpanel;
	}
	public JButton getReserve() {
		return Reserve;
	}
	public JLabel getFrom() {
		return from;
	}
	public JLabel getStopover() {
		return stopover;
	}
	public JLabel getTo() {
		return to;
	}
	public JLabel getDepartTime() {
		return departTime;
	}
	public JLabel getArrivalTime() {
		return ArrivalTime;
	}
	public JLabel getLegTrivalTime1() {
		return LegTrivalTime1;
	}
	public JLabel getAirplaneModel1() {
		return AirplaneModel1;
	}
	public JLabel getTimeDetail() {
		return timeDetail;
	}
	public JLabel getTotalTime() {
		return TotalTime;
	}
	public JLabel getMoney1() {
		return Money1;
	}
	public JLabel getBack1() {
		return back1;
	}
	public JLabel getBackStopOver() {
		return backStopOver;
	}
	public JLabel getBack2() {
		return back2;
	}
	public JLabel getBack1Time() {
		return back1Time;
	}
	public JLabel getLegTravelTime2() {
		return LegTravelTime2;
	}
	public JLabel getBack2Time() {
		return back2Time;
	}
	public JLabel getAirplaneModel2() {
		return AirplaneModel2;
	}
	public JLabel getTotalTime2() {
		return TotalTime2;
	}
	public JLabel getTimeDetail2() {
		return timeDetail2;
	}
	public JLabel getMoney2() {
		return Money2;
	}
	public JLabel getMoney() {
		return Money;
	}
}
