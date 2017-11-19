package UI;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import BL.*;

public class SearchAndResult_Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JComboBox<String> seat;
	private JButton btnNewButton;
	private JLabel lblReturn;
	
	private SearchAndResultFrame_Controller controller;
	private JPanel panel;
	private SearchAndResult_Frame sFrame;
	private JScrollPane scrollPane;
	private JTextField txtDd;
	private JTable table;
	private JTextField returndd;
	private JLabel lblNewLabel_2;
	private JLabel lblToWhere;
	JComboBox<String> test;
	private JComboBox<String> returnyyyy;
	private JComboBox<String> departmm;
	private JComboBox<String> returnmm;
	private JComboBox<String> OneOrRound;
	private JComboBox<String> StopOver;
	private JLabel lblNewLabel_3;
	
	public SearchAndResult_Frame() throws Exception{
		setAlwaysOnTop(true);
		setBackground(new Color(240, 240, 240));
		setTitle("WPI World Plane");
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1364, 904);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnWpiWorldPlaneinc = new JTextPane();
		txtpnWpiWorldPlaneinc.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		txtpnWpiWorldPlaneinc.setText("WPI World Plane.Inc");
		txtpnWpiWorldPlaneinc.setBounds(1021, 845, 331, 46);
		panel.add(txtpnWpiWorldPlaneinc);
		
		JTextPane txtpnWpi = new JTextPane();
		txtpnWpi.setEditable(false);
		txtpnWpi.setForeground(Color.RED);
		txtpnWpi.setFont(new Font("Tempus Sans ITC", Font.BOLD, 50));
		txtpnWpi.setText("WPI World Plane");
		txtpnWpi.setBounds(208, 26, 441, 69);
		panel.add(txtpnWpi);
		
		JComboBox<String> FromWhere = new JComboBox<String>();
		FromWhere.setForeground(Color.BLUE);
		FromWhere.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		FromWhere.setModel(new DefaultComboBoxModel<String>(new String[] {"ANC", "ATL", "AUS", "BDL", "BNA", "BOS", "BWI", "CLE", "CLT", "CMH", "CVG", "DCA", "DEN", "DFW", "DTW", "EWR", "FLL", "HNL", "HOU", "IAD", "IAH", "IND", "JFK", "LAS", "LAX", "LGA", "MCI", "MCO", "MDW", "MEM", "MIA", "MSP", "MSY", "OAK", "ONT", "ORD", "PDX", "PHL", "PHX", "PIT", "RDU", "RSW", "SAN", "SAT", "SEA", "SFO", "SJC", "SLC", "SMF", "SNA", "STL", "TPA"}));
		FromWhere.setBounds(37, 158, 176, 46);
		panel.add(FromWhere);
		
		JComboBox<String> toWhere = new JComboBox<String>();
		toWhere.setForeground(Color.MAGENTA);
		toWhere.setModel(new DefaultComboBoxModel<String>(new String[] {"ANC", "ATL", "AUS", "BDL", "BNA", "BOS", "BWI", "CLE", "CLT", "CMH", "CVG", "DCA", "DEN", "DFW", "DTW", "EWR", "FLL", "HNL", "HOU", "IAD", "IAH", "IND", "JFK", "LAS", "LAX", "LGA", "MCI", "MCO", "MDW", "MEM", "MIA", "MSP", "MSY", "OAK", "ONT", "ORD", "PDX", "PHL", "PHX", "PIT", "RDU", "RSW", "SAN", "SAT", "SEA", "SFO", "SJC", "SLC", "SMF", "SNA", "STL", "TPA"}));
		toWhere.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		toWhere.setBounds(263, 158, 176, 46);
		panel.add(toWhere);
		
		JComboBox<String> departyyyyy = new JComboBox<String>();
		departyyyyy.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		departyyyyy.setModel(new DefaultComboBoxModel<String>(new String[] {"2017", "2018"}));
		departyyyyy.setBounds(566, 141, 93, 22);
		panel.add(departyyyyy);
		
		returnyyyy = new JComboBox<String>();
		returnyyyy.setEnabled(false);
		returnyyyy.setModel(new DefaultComboBoxModel<String>(new String[] {"2017", "2018"}));
		returnyyyy.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		returnyyyy.setBounds(566, 182, 93, 22);
		panel.add(returnyyyy);
		
		departmm = new JComboBox<String>();
		departmm.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		departmm.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		departmm.setBounds(671, 140, 70, 24);
		panel.add(departmm);
		
		returnmm = new JComboBox<String>();
		returnmm.setEnabled(false);
		returnmm.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		returnmm.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		returnmm.setBounds(671, 180, 70, 24);
		panel.add(returnmm);
		
		btnNewButton = new JButton("Search");		
		btnNewButton.addMouseListener(new MouseAdapter() {
			private String departPlace;
			private String arrivalPlace;
			ArrayList<Trip> trips =new ArrayList<Trip>();
			ArrayList<Trip> TripResult=new ArrayList<Trip>();
			@Override
			public void mouseClicked(MouseEvent arg0) {
				departPlace=FromWhere.getSelectedItem().toString();
				arrivalPlace=toWhere.getSelectedItem().toString();
				int departy=Integer.parseInt(departyyyyy.getSelectedItem().toString());
				int departm=Integer.parseInt(departmm.getSelectedItem().toString());
				int departd=Integer.parseInt(txtDd.getText());
				
				java.util.Date inputDate = new GregorianCalendar(departy, departm-1, departd).getTime();
				
				int stopNum=StopOver.getSelectedIndex()-1;
				
			if(OneOrRound.getSelectedIndex()==1) {
				//for the round trip
				int returny=Integer.parseInt(returnyyyy.getSelectedItem().toString());
				int returnm=Integer.parseInt(returnmm.getSelectedItem().toString());
				int returnd=Integer.parseInt(returndd.getText());
				Date roundDate=new GregorianCalendar(returny,returnm-1,returnd).getTime();
				try {
					TripResult=BL.result_generator.GetTrip.getRoundTrip(departPlace, inputDate, arrivalPlace, roundDate);
					trips=ResultFilter.stopover(TripResult, stopNum);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				table.setModel(DefaultModelFactory.getTableModelR(trips));
			}
			else {
				try {
					TripResult=BL.result_generator.GetTrip.getOneWayTrip(departPlace, inputDate, arrivalPlace);
					trips=ResultFilter.stopover(TripResult, stopNum);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				table.setModel(DefaultModelFactory.getTableModel(trips));
				}
			}
		});
		
		btnNewButton.setIcon(new ImageIcon(SearchAndResult_Frame.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnNewButton.setBounds(1159, 141, 145, 73);
		panel.add(btnNewButton);
		
		JPanel ResultPanel = new JPanel();
		ResultPanel.setBounds(37, 279, 1267, 553);
		panel.add(ResultPanel);
		ResultPanel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		ResultPanel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		
		seat = new JComboBox<String>();
		seat.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		seat.setModel(new DefaultComboBoxModel<String>(new String[] {"Both", "First class seating", "Coach seating"}));
		seat.setToolTipText("First class seating\r\ncoach seating");
		seat.setBounds(841, 141, 206, 69);
		panel.add(seat);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SearchAndResult_Frame.class.getResource("/image/download1.png")));
		lblNewLabel.setBounds(31, 15, 197, 93);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Depart:");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblNewLabel_1.setBounds(472, 133, 93, 37);
		panel.add(lblNewLabel_1);
		
		lblReturn = new JLabel("Return:");
		lblReturn.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblReturn.setBounds(472, 177, 93, 27);
		panel.add(lblReturn);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Price"}));
		comboBox.setBounds(1159, 233, 145, 33);
		panel.add(comboBox);
		
		JLabel lblSort = new JLabel("Sort:");
		lblSort.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblSort.setBounds(1093, 236, 54, 28);
		panel.add(lblSort);
		
		txtDd = new JTextField();
		txtDd.setHorizontalAlignment(SwingConstants.CENTER);
		txtDd.setText("DD");
		txtDd.setColumns(10);
		txtDd.setBounds(753, 143, 76, 22);
		panel.add(txtDd);
		
		returndd = new JTextField();
		returndd.setText("DD");
		returndd.setHorizontalAlignment(SwingConstants.CENTER);
		returndd.setColumns(10);
		returndd.setBounds(753, 182, 76, 22);
		panel.add(returndd);
		
		lblNewLabel_2 = new JLabel("From Where?");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_2.setBounds(31, 110, 197, 35);
		panel.add(lblNewLabel_2);
		
		lblToWhere = new JLabel("To Where?");
		lblToWhere.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblToWhere.setBounds(263, 110, 197, 35);
		panel.add(lblToWhere);
		
		OneOrRound = new JComboBox<String>();
		OneOrRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(OneOrRound.getSelectedIndex()==1) {
					returnyyyy.setEnabled(true);
					returnmm.setEnabled(true);
					returndd.setEditable(true);
					}
				else if (OneOrRound.getSelectedIndex()==0) {
					returnyyyy.setEnabled(false);
					returnmm.setEnabled(false);
					returndd.setEditable(false);
				}
			}
		});
		
		OneOrRound.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		OneOrRound.setModel(new DefaultComboBoxModel<String>(new String[] {"One-way trip", "Round-way trip"}));
		OneOrRound.setBounds(37, 217, 206, 46);
		panel.add(OneOrRound);
		
		StopOver = new JComboBox<String>();
		StopOver.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		StopOver.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "0", "1", "2"}));
		StopOver.setBounds(1059, 178, 88, 30);
		panel.add(StopOver);
		
		lblNewLabel_3 = new JLabel("StopOver");
		lblNewLabel_3.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblNewLabel_3.setBounds(1059, 141, 99, 22);
		panel.add(lblNewLabel_3);
		
	}
		
	public void update() {
	}
	
	public SearchAndResultFrame_Controller getController() {
		return controller;
	}

	public void setController(SearchAndResultFrame_Controller controller) {
		this.controller = controller;
	}

	public SearchAndResult_Frame getsFrame() {
		return sFrame;
	}

	public void setsFrame(SearchAndResult_Frame sFrame) {
		this.sFrame = sFrame;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JTextPane getTextPane() {
		return getTextPane();
	}
}


