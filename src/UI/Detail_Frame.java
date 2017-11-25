package UI;

import javax.swing.*;

import BL.Trip;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

public class Detail_Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;

	public Detail_Frame(ArrayList<Trip> trips,int RowNum) {
		setTitle("Detail");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trip Details");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 35));
		lblNewLabel.setBounds(473, 32, 208, 95);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(Detail_Frame.class.getResource("/image/download1.png")));
		lblNewLabel_1.setBounds(87, 32, 152, 95);
		panel.add(lblNewLabel_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("WPI World Plane.Inc");
		textPane.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		textPane.setBounds(839, 681, 331, 46);
		panel.add(textPane);
		
		JLabel test = new JLabel("New label");
		test.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		test.setBounds(37, 238, 1053, 76);
		panel.add(test);
		
		//test.setText(trips.get(RowNum).toString());
		
		JButton btnNewButton = new JButton("Reserve");
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		btnNewButton.setBounds(906, 583, 165, 59);
		panel.add(btnNewButton);
		
		JButton Back = new JButton("Back");
		
		//not finish
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(true) {
					Detail_Frame.this.dispatchEvent(new WindowEvent(Detail_Frame.this, WindowEvent.WINDOW_CLOSED));
				}
			}
		});
		
		Back.setFont(new Font("Tempus Sans ITC", Font.BOLD, 30));
		Back.setBounds(142, 583, 165, 59);
		panel.add(Back);
		
	}
	
	public static void  invoke(ArrayList<Trip> trips, int RowNum) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Detail_Frame detail_Frame = new Detail_Frame(trips, RowNum);
				detail_Frame.setBounds(0, 0, 1200, 800);
				detail_Frame.setVisible(true);	
				detail_Frame.setAlwaysOnTop(true);
			}
		});
		
	}
	public JPanel getPanel() {
		return panel;
	}
}
