package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.omg.CORBA.PRIVATE_MEMBER;

import BL.DefaultModelFactory;
import BL.ResultFilter;
import BL.Trip;

public class SearchAndResultFrame_Controller{
	private ArrayList<Trip> trips;
	
	
	public SearchAndResultFrame_Controller(JPanel panel,JTable table,JComboBox<String> FromWhere, JComboBox<String> ToWhere,JComboBox<String> departyyyy,JComboBox<String> departmm,
			JComboBox<String> departdd,JComboBox<String> seat,JComboBox<String> OneOrRound,JButton SearchButton,JComboBox<String> StopOver,
			JComboBox<String> returnyyyy,JComboBox<String> returnmm,JComboBox<String> returndd,JCheckBox TimeWindow, JComboBox<String> StartTime,
			JComboBox<String> StopTime,JComboBox<String> Sort) {
		
		
		int seatclass = seat.getSelectedIndex();

		//Using the searchButton to search according to the parameter get from GUI
		SearchButton.addMouseListener(new MouseAdapter() {
			private String departPlace;
			private String arrivalPlace;
			ArrayList<Trip> TripResult=new ArrayList<Trip>();
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				departPlace=FromWhere.getSelectedItem().toString();
				arrivalPlace=ToWhere.getSelectedItem().toString();
				int departy=Integer.parseInt(departyyyy.getSelectedItem().toString());
				int departm=Integer.parseInt(departmm.getSelectedItem().toString());
				int departd=Integer.parseInt(departdd.getSelectedItem().toString());
				
				LocalDate inputDate = LocalDate.of(departy, departm, departd);
				
				int stopNum=StopOver.getSelectedIndex()-1;
				
			if(OneOrRound.getSelectedIndex()==1) {
				//for the round trip
				int returny=Integer.parseInt(returnyyyy.getSelectedItem().toString());
				int returnm=Integer.parseInt(returnmm.getSelectedItem().toString());
				int returnd=Integer.parseInt(returndd.getSelectedItem().toString());
				LocalDate roundDate = LocalDate.of(returny,returnm,returnd);
				
				if(roundDate.compareTo(inputDate)<0) {
					JOptionPane.showMessageDialog(panel, "Return time can't earlier than depart time!");
				}
				else if (TimeWindow.isSelected()&&(StartTime.getSelectedIndex()>StopTime.getSelectedIndex())) {
					JOptionPane.showMessageDialog(panel, "TimeWindow selected problem!Please select again!");
				}
				else {
					try {
						TripResult=BL.result_generator.GetTrip.getRoundTrip(departPlace, inputDate, arrivalPlace, roundDate);
						if(stopNum==-1) {
							trips=TripResult;
						}else {
							trips=ResultFilter.stopover(TripResult, stopNum);
						}
						if(TimeWindow.isSelected()) {
							int startHour = StartTime.getSelectedIndex();
							int stopHour = StopTime.getSelectedIndex();
							LocalDateTime start = LocalDateTime.of(departy, departm, departd, startHour, 0);
							LocalDateTime stop = LocalDateTime.of(departy, departm, departd, stopHour, 0);
							LocalDateTime start2 = LocalDateTime.of(departy,departm,departd,0,0);
							LocalDateTime stop2 = LocalDateTime.of(departy,departm,departd,23,59);
							trips =ResultFilter.timeWindow2(trips, start, stop,start2,stop2);
						}else {
							LocalDateTime start = LocalDateTime.of(departy, departm, departd, 0, 0);
							LocalDateTime stop = LocalDateTime.of(departy, departm, departd, 23, 59);
							LocalDateTime start2 = LocalDateTime.of(returny, returnm,returnd,0,0);
							LocalDateTime stop2 = LocalDateTime.of(returny, returnm,returnd,23,59);
							trips =ResultFilter.timeWindow2(trips, start, stop, start2 , stop2);
						}
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(trips.size()==0) {
						JOptionPane.showMessageDialog(panel, "There is no available flight according to your requirement!");
					}
					else {
						table.setModel(DefaultModelFactory.getRoundModel(trips, seat.getSelectedIndex()));
					}	
				}
			}
			else {
				//for one-way trip
				if (TimeWindow.isSelected()&&(StartTime.getSelectedIndex()>StopTime.getSelectedIndex())) {
					JOptionPane.showMessageDialog(panel, "TimeWindow selected problem!Please select again!");
				}else {
					try {
						TripResult=BL.result_generator.GetTrip.getOneWayTrip(departPlace, inputDate, arrivalPlace);
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(stopNum==-1) {
						trips=TripResult;
					}else {
						trips=ResultFilter.stopover(TripResult, stopNum);
					}
					if(TimeWindow.isSelected()) {
						int startHour = StartTime.getSelectedIndex();
						int stopHour = StopTime.getSelectedIndex()+1;
						LocalDateTime start = LocalDateTime.of(departy, departm, departd, startHour, 0);
						LocalDateTime stop = LocalDateTime.of(departy, departm, departd, stopHour, 0);
						trips =ResultFilter.timeWindow(trips, start, stop);
					}else {
						LocalDateTime start = LocalDateTime.of(departy,departm,departd,0,0);
						LocalDateTime stop = LocalDateTime.of(departy,departm,departd,23,59);
						trips =ResultFilter.timeWindow(trips, start, stop);
					}
					if(trips.size()==0) {
						JOptionPane.showMessageDialog(panel, "There is no available flight according to your requirement!");
					}
					else {
						table.setModel(DefaultModelFactory.getOneWayModel(trips, seat.getSelectedIndex()));
					}
				}
			}
			}
		});
		//Sort the trips
		Sort.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(arg0.getStateChange()== ItemEvent.SELECTED) {
					if(Sort.getSelectedIndex()!=-1) {
						switch(Sort.getSelectedIndex()) {
						
						case 1:
							//price low - high
							if(OneOrRound.getSelectedIndex()==0) {
								//one-way trip
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.CoachPriceAsc);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.FCPriceAsc);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
							}
							else {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.CoachPriceAsc);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.FCPriceAsc);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
							}
								break;
						case 2:
							//price high-low
							if(OneOrRound.getSelectedIndex()==0) {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.CoachPriceDes);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.FCPriceDes);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
							}
							else {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.CoachPriceDes);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.FCPriceDes);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
							}
							break;
						case 3:
							// Total travel time short to long
							if(OneOrRound.getSelectedIndex()==0) {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.TotalTimeAsc);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.TotalTimeAsc);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
							}
							else {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.TotalTimeAsc);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.TotalTimeAsc);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
							}							
							break;
						
						case 4:
							// Total travel time long to short
							if(OneOrRound.getSelectedIndex()==0) {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.TotalTimeDes);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.TotalTimeDes);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
							}
							else {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.TotalTimeDes);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.TotalTimeDes);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
							}							
							break;
						case 5:
							//Departure Time early to late
							if(OneOrRound.getSelectedIndex()==0) {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.DepatureAsc);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.DepatureAsc);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
							}
							else {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.DepatureAsc);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.DepatureAsc);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
							}	
							break;
						case 6:
							//Departure Time late to early 
							if(OneOrRound.getSelectedIndex()==0) {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.DepatureDes);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.DepatureDes);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
							}
							else {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.DepatureDes);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.DepatureDes);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
							}	
							break;
						case 7:
							//arrival time early to late
							if(OneOrRound.getSelectedIndex()==0) {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.ArrivalAsc);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.ArrivalAsc);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
							}
							else {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.ArrivalAsc);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.ArrivalAsc);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
							}
							break;
						case 8:
							//arrival time late to early
							if(OneOrRound.getSelectedIndex()==0) {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.ArrivalDes);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.ArrivalDes);
									table.setModel(DefaultModelFactory.getOneWayModel(trips, seatclass));
								}
							}
							else {
								if(seatclass==0) {
									trips = BL.ResultSort.sort(trips,BL.ResultSort.ArrivalDes);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
								if(seatclass==1){
									trips = BL.ResultSort.sort(trips,BL.ResultSort.ArrivalDes);
									table.setModel(DefaultModelFactory.getRoundModel(trips, seatclass));
								}
							}
							break;

						}
					}
				}
			}
		});
		//invoke detail frame
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()>=1) {
					int RowNum = table.getSelectedRow();
					Detail_Frame.invoke(trips, RowNum,seatclass);
				}
			}
		});
		
		//One-or-Round enable the combox
		OneOrRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(OneOrRound.getSelectedIndex()==1) {
					returnyyyy.setEnabled(true);
					returnmm.setEnabled(true);
					returndd.setEnabled(true);
					}
				else if (OneOrRound.getSelectedIndex()==0) {
					returnyyyy.setEnabled(false);
					returnmm.setEnabled(false);
					returndd.setEnabled(false);
				}
			}
		});
		
		//TimeWindow enable the combox
		TimeWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(TimeWindow.isSelected()) {
					StartTime.setEnabled(true);
					StopTime.setEnabled(true);
				}else {
					StartTime.setEnabled(false);
					StopTime.setEnabled(false);
				}
			}
		});
	}
}
