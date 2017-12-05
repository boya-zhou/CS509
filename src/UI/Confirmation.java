package UI;

import javax.swing.*;

import BL.Airplane.SeatType;
import DB.ReserveFlight;
import BL.Flight;
import BL.Reserve;

import java.awt.Font;
import java.util.List;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Confirmation extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Confirmation(List<Flight> flightlist, List<SeatType> seat) {
		
		JPanel jPanel = (JPanel) getContentPane();
		setTitle("Confirmation");
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int respon = 0;
				
				respon = Reserve.reserve(flightlist, seat);

				if(respon == 0) {
					JOptionPane.showMessageDialog(jPanel, "Reserve success!");
				}else {
					JOptionPane.showMessageDialog(jPanel, "Sorry! Reserve fail!");
				}
			}

		});
		btnNewButton.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnNewButton.setBounds(37, 157, 161, 34);
		getContentPane().add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 25));
		btnCancel.setBounds(232, 157, 161, 34);
		getContentPane().add(btnCancel);
		
		JLabel lblAreYouSur = new JLabel("Are you sur you want to book this trips?");
		lblAreYouSur.setFont(new Font("Tempus Sans ITC", Font.BOLD, 20));
		lblAreYouSur.setForeground(Color.RED);
		lblAreYouSur.setBounds(36, 65, 384, 56);
		getContentPane().add(lblAreYouSur);	
		setBounds(500, 500,450, 300);
		setVisible(true);
		setAlwaysOnTop(true);
		
		
	}
	
}
