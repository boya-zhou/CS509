package UI;

import java.awt.EventQueue;
import UI.SearchAndResult_Frame;

public class frameTest {
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					SearchAndResult_Frame test=new SearchAndResult_Frame();
					test.setBounds(0, 0, 1360, 940);
					test.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
