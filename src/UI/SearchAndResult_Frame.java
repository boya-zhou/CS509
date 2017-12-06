package UI;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class SearchAndResult_Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel lblReturn;
	
	private SearchAndResultFrame_Controller controller;
	private SearchAndResult_Frame sFrame;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_2;
	private JLabel lblToWhere;
	private JLabel lblNewLabel_3;

	//Jpanel
	private JPanel panel;
	
	//Table
	private JTable table;
	
	//Depart Place and Arrival Place
	private JComboBox<String> FromWhere;
	private JComboBox<String> ToWhere;
	
	//Depart Date
	private JComboBox<String> departyyyy;
	private JComboBox<String> departdd;
	private JComboBox<String> departmm;
	
	//Return Date
	private JComboBox<String> returnyyyy;
	private JComboBox<String> returnmm;
	private JComboBox<String> returndd;
	
	//SeatClass
	private JComboBox<String> seat;
	
	//One-or-Round Trip
	public JComboBox<String> OneOrRound;
	
	//Search Button
	private JButton SearchButton;
	
	//StopOver
	private JComboBox<String> StopOver;
	
	//For Time window
	private JCheckBox TimeWindow;
	private JComboBox<String> StartTime;
	private JComboBox<String> StopTime;
	
	//Sort
	private JComboBox<String> Sort;
	
	//Detail Frame
	
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
		
		FromWhere = new JComboBox<String>();
		FromWhere.setForeground(Color.BLUE);
		FromWhere.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		FromWhere.setModel(new DefaultComboBoxModel<String>(new String[] {"ANC", "ATL", "AUS", "BDL", "BNA", "BOS", "BWI", "CLE", "CLT", "CMH", "CVG", "DCA", "DEN", "DFW", "DTW", "EWR", "FLL", "HNL", "HOU", "IAD", "IAH", "IND", "JFK", "LAS", "LAX", "LGA", "MCI", "MCO", "MDW", "MEM", "MIA", "MSP", "MSY", "OAK", "ONT", "ORD", "PDX", "PHL", "PHX", "PIT", "RDU", "RSW", "SAN", "SAT", "SEA", "SFO", "SJC", "SLC", "SMF", "SNA", "STL", "TPA"}));
		FromWhere.setBounds(37, 158, 176, 46);
		panel.add(FromWhere);
		
		ToWhere = new JComboBox<String>();
		ToWhere.setForeground(Color.MAGENTA);
		ToWhere.setModel(new DefaultComboBoxModel<String>(new String[] {"ANC", "ATL", "AUS", "BDL", "BNA", "BOS", "BWI", "CLE", "CLT", "CMH", "CVG", "DCA", "DEN", "DFW", "DTW", "EWR", "FLL", "HNL", "HOU", "IAD", "IAH", "IND", "JFK", "LAS", "LAX", "LGA", "MCI", "MCO", "MDW", "MEM", "MIA", "MSP", "MSY", "OAK", "ONT", "ORD", "PDX", "PHL", "PHX", "PIT", "RDU", "RSW", "SAN", "SAT", "SEA", "SFO", "SJC", "SLC", "SMF", "SNA", "STL", "TPA"}));
		ToWhere.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		ToWhere.setBounds(263, 158, 176, 46);
		panel.add(ToWhere);
		
		departyyyy = new JComboBox<String>();
		departyyyy.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		departyyyy.setModel(new DefaultComboBoxModel<String>(new String[] {"2017", "2018"}));
		departyyyy.setBounds(543, 141, 93, 22);
		panel.add(departyyyy);
		
		returnyyyy = new JComboBox<String>();
		returnyyyy.setEnabled(false);
		returnyyyy.setModel(new DefaultComboBoxModel<String>(new String[] {"2017", "2018"}));
		returnyyyy.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		returnyyyy.setBounds(543, 182, 93, 22);
		panel.add(returnyyyy);
		
		departmm = new JComboBox<String>();
		departmm.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		departmm.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		departmm.setBounds(648, 140, 70, 24);
		panel.add(departmm);
		
		returnmm = new JComboBox<String>();
		returnmm.setEnabled(false);
		returnmm.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		returnmm.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		returnmm.setBounds(648, 180, 70, 24);
		panel.add(returnmm);
		
		departdd = new JComboBox<String>();
		departdd.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		departdd.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		departdd.setBounds(730, 140, 70, 24);
		panel.add(departdd);
		
		returndd = new JComboBox<String>();
		returndd.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		returndd.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		returndd.setEnabled(false);
		returndd.setBounds(730, 180, 70, 24);
		panel.add(returndd);
		
		SearchButton = new JButton("Search");		
		SearchButton.setIcon(new ImageIcon(SearchAndResult_Frame.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		SearchButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		SearchButton.setBounds(1159, 141, 145, 73);
		panel.add(SearchButton);
		
		JPanel ResultPanel = new JPanel();
		ResultPanel.setBounds(37, 279, 1267, 553);
		panel.add(ResultPanel);
		ResultPanel.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		ResultPanel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tempus Sans ITC",Font.BOLD,20));
		table.setRowHeight(20);
		scrollPane.setViewportView(table);
		
		seat = new JComboBox<String>();
		seat.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		seat.setModel(new DefaultComboBoxModel<String>(new String[] {"Coach seating", "First class seating"}));
		seat.setToolTipText("First class seating\r\ncoach seating");
		seat.setBounds(812, 141, 206, 69);
		panel.add(seat);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SearchAndResult_Frame.class.getResource("/image/download1.png")));
		lblNewLabel.setBounds(31, 15, 197, 93);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Depart:");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblNewLabel_1.setBounds(451, 133, 93, 37);
		panel.add(lblNewLabel_1);
		
		lblReturn = new JLabel("Return:");
		lblReturn.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblReturn.setBounds(451, 177, 93, 27);
		panel.add(lblReturn);
		
		Sort = new JComboBox<String>();
		Sort.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		Sort.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Price$-$$", "Price$$-$", "TotalTime[Asc]", "TotalTime[Des]", "DepartTime[Asc]", "DepartTime[Des]", "ArrivalTime[Asc]", "ArrivalTime[Des]"}));
		Sort.setBounds(1076, 233, 228, 33);
		panel.add(Sort);
		
		JLabel lblSort = new JLabel("Sort:");
		lblSort.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblSort.setBounds(1021, 235, 54, 28);
		panel.add(lblSort);
		
		lblNewLabel_2 = new JLabel("From Where?");
		lblNewLabel_2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblNewLabel_2.setBounds(31, 110, 197, 35);
		panel.add(lblNewLabel_2);
		
		lblToWhere = new JLabel("To Where?");
		lblToWhere.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		lblToWhere.setBounds(263, 110, 197, 35);
		panel.add(lblToWhere);
		
		OneOrRound = new JComboBox<String>();		
		OneOrRound.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		OneOrRound.setModel(new DefaultComboBoxModel<String>(new String[] {"One-way trip", "Round-way trip"}));
		OneOrRound.setBounds(37, 217, 206, 46);
		panel.add(OneOrRound);
		
		StopOver = new JComboBox<String>();
		StopOver.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		StopOver.setModel(new DefaultComboBoxModel<String>(new String[] {"All", "0", "1", "2"}));
		StopOver.setBounds(1040, 178, 107, 30);
		panel.add(StopOver);
		
		lblNewLabel_3 = new JLabel("MaxStopOver");
		lblNewLabel_3.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblNewLabel_3.setBounds(1021, 141, 131, 22);
		panel.add(lblNewLabel_3);
		
		StartTime = new JComboBox<String>();
		StartTime.setEnabled(false);
		StartTime.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		StartTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		StartTime.setBounds(501, 229, 70, 24);
		panel.add(StartTime);
		
		JLabel lblNewLabel_5 = new JLabel("To");
		lblNewLabel_5.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblNewLabel_5.setBounds(576, 217, 40, 49);
		panel.add(lblNewLabel_5);
		
		StopTime = new JComboBox<String>();
		StopTime.setEnabled(false);
		StopTime.setModel(new DefaultComboBoxModel<String>(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"}));
		StopTime.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		StopTime.setBounds(623, 229, 70, 24);
		panel.add(StopTime);
		
		TimeWindow = new JCheckBox("TimeWindow:");
		TimeWindow.setBackground(Color.WHITE);
		TimeWindow.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		TimeWindow.setBounds(285, 220, 197, 46);
		panel.add(TimeWindow);
		
		SearchAndResultFrame_Controller controller =  new SearchAndResultFrame_Controller(panel,table,FromWhere,ToWhere,departyyyy,departmm,
				departdd,seat,OneOrRound,SearchButton,StopOver,returnyyyy,returnmm,returndd,TimeWindow,StartTime,StopTime, Sort);
		
	}
		
	public void update() {
	}
	
	public SearchAndResultFrame_Controller getController() {
		return controller;
	}
	
	/**
	 * 
	 * @param controller
	 */
	public void setController(SearchAndResultFrame_Controller controller) {
		this.controller = controller;
	}
	
	/**
	 * 
	 * @return
	 */
	public SearchAndResult_Frame getsFrame() {
		return sFrame;
	}
	public JTable getTable() {
		return table;
	}
}


