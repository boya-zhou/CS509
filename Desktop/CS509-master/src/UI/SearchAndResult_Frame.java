package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import BL.*;

public class SearchAndResult_Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField txtFromWhere;
	private JTextField txtToWhere;
	private JRadioButton rdbtnNewRadioButton;
	private JComboBox<String> seat;
	private JButton btnNewButton;
	private JLabel label;
	private JLabel label_1;
	
	private JTextField Arrival1;
	private JTextField Depart2;
	private JTextField Arrival2;
	
	private SearchAndResultFrame_Controller controller;
	private JPanel panel;
	private SearchAndResult_Frame sFrame;
	private JScrollPane scrollPane;
	private JTextField txtYyyy;
	private JTextField txtMm;
	private JTextField txtDd;
	private JTable table;
	
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
		
		txtFromWhere = new JTextField();
		txtFromWhere.setHorizontalAlignment(SwingConstants.CENTER);
		txtFromWhere.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		txtFromWhere.setText("From where?");
		txtFromWhere.setBounds(37, 131, 229, 69);
		panel.add(txtFromWhere);
		txtFromWhere.setColumns(10);
		
		txtToWhere = new JTextField();
		txtToWhere.setText("To where?");
		txtToWhere.setHorizontalAlignment(SwingConstants.CENTER);
		txtToWhere.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		txtToWhere.setColumns(10);
		txtToWhere.setBounds(278, 131, 237, 69);
		panel.add(txtToWhere);
		
		btnNewButton = new JButton("Search");		
		btnNewButton.addMouseListener(new MouseAdapter() {
			private String departPlace;
			private Set<Flight> flightList;
			@Override
			public void mouseClicked(MouseEvent arg0) {
				flightList = new HashSet<Flight>();
				departPlace=txtFromWhere.getText();
				int departy=Integer.parseInt(txtYyyy.getText());
				int departm=Integer.parseInt(txtMm.getText());
				int departd=Integer.parseInt(txtDd.getText());
				
				java.util.Date inputDate = new GregorianCalendar(departy, departm-1, departd).getTime();
			System.out.println(inputDate);
			try {
				flightList=XMLparser.parseFlightSet(DB.GetData.getDepartureFlightInfo(departPlace, inputDate));
			} catch (IOException e) {
				e.printStackTrace();
			}
			table.setModel(DefaultModelFactory.getTableModel(flightList));
			for(Flight f: flightList) {
				System.out.println(f);
			}
			}
		});
		
		btnNewButton.setIcon(new ImageIcon(SearchAndResult_Frame.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaPlayDisabled.png")));
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnNewButton.setBounds(1159, 131, 145, 73);
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
		seat.setBounds(917, 135, 230, 69);
		panel.add(seat);
		
		rdbtnNewRadioButton = new JRadioButton("Round-Trip");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0 != null) {
					Depart2.setEnabled(true);
					Depart2.setEditable(true);
					Arrival2.setEnabled(true);
					Arrival2.setEditable(true);
				}
				}
		});

		rdbtnNewRadioButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		rdbtnNewRadioButton.setBounds(38, 233, 156, 37);
		panel.add(rdbtnNewRadioButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SearchAndResult_Frame.class.getResource("/image/download (1).png")));
		lblNewLabel.setBounds(31, 15, 197, 93);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Depart:");
		lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblNewLabel_1.setBounds(537, 96, 93, 37);
		panel.add(lblNewLabel_1);
		
		JLabel lblArrival = new JLabel("Arrival:");
		lblArrival.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblArrival.setBounds(537, 142, 93, 27);
		panel.add(lblArrival);
		
		label = new JLabel("Depart:");
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		label.setBounds(537, 182, 93, 37);
		panel.add(label);
		
		label_1 = new JLabel("Arrival:");
		label_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		label_1.setBounds(537, 227, 93, 27);
		panel.add(label_1);
		
		Arrival1 = new JTextField();
		Arrival1.setText("YYYY_MM_DD");
		Arrival1.setHorizontalAlignment(SwingConstants.CENTER);
		Arrival1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		Arrival1.setColumns(10);
		Arrival1.setBounds(638, 147, 267, 22);
		panel.add(Arrival1);
		
		Depart2 = new JTextField();
		Depart2.setEnabled(false);
		Depart2.setEditable(false);
		Depart2.setText("YYYY_MM_DD");
		Depart2.setHorizontalAlignment(SwingConstants.CENTER);
		Depart2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		Depart2.setColumns(10);
		Depart2.setBounds(638, 192, 267, 22);
		panel.add(Depart2);
		
		Arrival2 = new JTextField();
		Arrival2.setEnabled(false);
		Arrival2.setEditable(false);
		Arrival2.setText("YYYY_MM_DD");
		Arrival2.setHorizontalAlignment(SwingConstants.CENTER);
		Arrival2.setFont(new Font("Tempus Sans ITC", Font.BOLD, 15));
		Arrival2.setColumns(10);
		Arrival2.setBounds(638, 232, 267, 22);
		panel.add(Arrival2);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Price"}));
		comboBox.setBounds(1159, 233, 145, 33);
		panel.add(comboBox);
		
		JLabel lblSort = new JLabel("Sort:");
		lblSort.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		lblSort.setBounds(1093, 236, 54, 28);
		panel.add(lblSort);
		
		txtYyyy = new JTextField();
		txtYyyy.setHorizontalAlignment(SwingConstants.CENTER);
		txtYyyy.setText("YYYY");
		txtYyyy.setBounds(638, 108, 93, 22);
		panel.add(txtYyyy);
		txtYyyy.setColumns(10);
		
		txtMm = new JTextField();
		txtMm.setText("MM");
		txtMm.setHorizontalAlignment(SwingConstants.CENTER);
		txtMm.setColumns(10);
		txtMm.setBounds(743, 106, 76, 22);
		panel.add(txtMm);
		
		txtDd = new JTextField();
		txtDd.setHorizontalAlignment(SwingConstants.CENTER);
		txtDd.setText("DD");
		txtDd.setColumns(10);
		txtDd.setBounds(829, 106, 76, 22);
		panel.add(txtDd);
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


