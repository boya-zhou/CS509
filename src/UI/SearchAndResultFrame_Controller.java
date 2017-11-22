package UI;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SearchAndResultFrame_Controller {
	private JTextField txtFromWhere;
	private JTextField txtToWhere;
	private JRadioButton rdbtnNewRadioButton;
	private JComboBox<String> seat;
	private JButton btnNewButton;
	
	private JTextField Depart1;	
	private JTextField Arrival1;
	private JTextField Depart2;
	private JTextField Arrival2;
	
	private SearchAndResult_Frame Sframe;
	
	
	public SearchAndResultFrame_Controller(JTextField txtFromWhere,JTextField txtToWhere,JTextField Depart1,JTextField Arrival1,JButton btnNewButton,SearchAndResult_Frame sFrame) {
		super();
		this.txtFromWhere=txtFromWhere;
		this.txtToWhere=txtToWhere;
		this.Depart1=Depart1;
		this.Arrival1=Arrival1;
		Sframe=sFrame;
	}
	
	public void initializeAction() {
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String fromWhere = txtFromWhere.getText();
				String departTime= Depart1.getText();
				
				
			}
		});
				
	}
	public static void invoke() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					SearchAndResult_Frame frame=new SearchAndResult_Frame();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}		
			}
		});
	}

}
