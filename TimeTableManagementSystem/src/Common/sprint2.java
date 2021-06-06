package Common;

import java.awt.EventQueue;




import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JTable;


import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Member1.DBConnect;
import net.proteanit.sql.DbUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;


public class sprint2 {
    
	private JFrame frame;
	private JComboBox  groupcombo;
	private JComboBox  sessioncombo;
	private JComboBox  daycombo;
	private JComboBox end;
	private JComboBox start;
	private JTable table1;
	int aid;
	DefaultTableModel model;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	/**
	 * Launch the application.
	 */
    Connection conn =null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sprint2 window = new sprint2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	/**
	 * Create the application.
	 */
	public sprint2() {
		initialize();
		fillComboBox2();
		startcombo();
		fillComboBox4();
		endcombo();
	    tableAction();
	   
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	//****************************fill combobox******************************************************* 

	
	//combobox 2
public void fillComboBox2() {
		
		try {
			
			  String query ="select GroupID from Session";
			  PreparedStatement pst =conn.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  while(rs.next()) {
				  
				  String groupID=rs.getString("GroupID");
				  groupcombo.addItem(groupID);
				 
			}
			
			  
		}catch (Exception e){
			 
			 e.printStackTrace();
			
		}
		
	}


	

public void fillComboBox4() {
	
	try {
		
		  String query ="select ID from Session";
		  PreparedStatement pst =conn.prepareStatement(query);
		  ResultSet rs = pst.executeQuery();
		  
		  while(rs.next()) {
			  
			  String sid=rs.getString("ID");
			  sessioncombo.addItem(sid);
			 
		}
		
		  
	}catch (Exception e){
		 
		 e.printStackTrace();
		
	}
	
}

public void startcombo() {
	
	try {
		
		  String query ="select * from TimeManage";
		  PreparedStatement pst =conn.prepareStatement(query);
		  ResultSet rs = pst.executeQuery();
		  
		  while(rs.next()) {
			  
			  String sTime=rs.getString("stime");
			  start.addItem(sTime);
			 
		}
		
		  
	}catch (Exception e){
		 
		 e.printStackTrace();
		
	}
	
}
public void endcombo() {
	
	try {
		
		  String query ="select * from TimeManage";
		  PreparedStatement pst =conn.prepareStatement(query);
		  ResultSet rs = pst.executeQuery();
		  
		  while(rs.next()) {
			  
			  String endTime=rs.getString("etime");
			  end.addItem(endTime);
			 
		}
		
		  
	}catch (Exception e){
		 
		 e.printStackTrace();
		
	}
	
}





//***************fetch data from the database and set to jtable *****************
private void tableAction(){
    // TODO Auto-generated method stub

   try {
	
	
	    String query="select * from AvailableTime";
	    PreparedStatement pst=conn.prepareStatement(query);
	    ResultSet rs=pst.executeQuery();
	
	    table1.setModel(DbUtils.resultSetToTableModel(rs));	
  }
catch(Exception ex) {
	ex.printStackTrace();
  }

 }

	
	private void initialize() {
		
	
		Connection con = DBConnect.connect(); 
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1128, 769);
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	    groupcombo = new JComboBox();
		groupcombo.setBounds(72, 168, 287, 22);
		frame.getContentPane().add(groupcombo);
		
		 sessioncombo = new JComboBox();
		sessioncombo.setBounds(72, 230, 287, 22);
		frame.getContentPane().add(sessioncombo);
		
		daycombo = new JComboBox();
		daycombo.setBounds(72, 304, 287, 22);
		frame.getContentPane().add(daycombo);
		
		
		//add data to day combo
		daycombo.addItem("Sunday");
		daycombo.addItem("Monday");
		daycombo.addItem("Thusday");
		daycombo.addItem("Wednesday");
		daycombo.addItem("Thusday");
		daycombo.addItem("Friday");
		daycombo.addItem("Saturaday");
		
		//**************************Save button*********************
		JButton save = new JButton("ADD");
		save.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\add.png"));
		save.setFont(new Font("Tahoma", Font.BOLD, 14));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
                   try {
                	   
                   String query ="insert into AvailableTime(groupID,sessionID,day,startTime,endTime) values(?,?,?,?,?)";
		    	   PreparedStatement pst = conn.prepareStatement(query);
		    	   
		    	   
		    	   pst.setString(1,groupcombo.getSelectedItem().toString());
		    	   pst.setString(2,sessioncombo.getSelectedItem().toString());
		    	   pst.setString(3,daycombo.getSelectedItem().toString());
		    	   pst.setString(4,start.getSelectedItem().toString());
		    	   pst.setString(5,end.getSelectedItem().toString());
		    	   
		    	   pst.execute();
		    	  

		    	   JOptionPane.showMessageDialog(null, "data Saved");
		    	   
                   }catch(Exception e1) {
                	   
                	   System.out.println(e1);
                	   
                   }
                   tableAction();
			}
		});
		save.setBounds(72, 423, 287, 52);
		frame.getContentPane().add(save);
		
		//****************update button****************
		JButton update = new JButton("UPDATE");
		update.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\update.png"));
		update.setFont(new Font("Tahoma", Font.BOLD, 14));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id =aid;
			
                String grpID = groupcombo.getSelectedItem().toString();
               
                String sesID=sessioncombo.getSelectedItem().toString();
		    	  
		    	String day=daycombo.getSelectedItem().toString();
		      	String startTimeA=start.getSelectedItem().toString();
		      	String endTimeA=end.getSelectedItem().toString();
		    	   
				
				
              try {    
            	  
              
			     	String query="UPDATE AvailableTime set groupID='"+grpID+"',SessionID='"+sesID+"',day='"+day+"',startTime='"+startTimeA+"',endTime='"+endTimeA+"'  where id='"+id+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "updated");
					
				
							
				}catch(Exception ea) {
					
					ea.printStackTrace();
				}	
		    	   
              tableAction();  
				  
			}
		});
		
		update.setBounds(72, 480, 287, 44);
		frame.getContentPane().add(update);
		
		//*************Delete button******************
		
		JButton delete = new JButton("DELETE");
		delete.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\remove.png"));
		delete.setFont(new Font("Tahoma", Font.BOLD, 14));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try {
				 
					String query="Delete from AvailableTime where id='"+aid+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "data delete");
					pst.close();	
					//call the table load function
					tableAction();
				}catch(Exception en) {
						en.printStackTrace();
							
				}	
				
				
			}
				
				
		
				
		});
		delete.setBounds(72, 537, 287, 44);
		frame.getContentPane().add(delete);
		
		JButton clear = new JButton("CLEAR");
		clear.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\clear4.png"));
		clear.setFont(new Font("Tahoma", Font.BOLD, 14));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
		    	groupcombo.setSelectedIndex(0);
		    	sessioncombo.setSelectedIndex(0);
		    	daycombo.setSelectedIndex(0);
		    	start.setSelectedIndex(0);
		    	end.setSelectedIndex(0);
		    	
		    	
			}
		});
		clear.setBounds(72, 599, 287, 44);
		frame.getContentPane().add(clear);
		
		JList list = new JList();
		list.setBounds(10, 48, 1, 1);
		frame.getContentPane().add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(447, 101, 639, 550);
		frame.getContentPane().add(scrollPane);
		
		table1 = new JTable();
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedRow=table1.getSelectedRow();
                  aid=(int) table1.getValueAt(selectedRow,0);
                
            
				
				 String Combo2 = table1.getValueAt(selectedRow, 1).toString();
					for(int i=0; i<groupcombo.getItemCount();i++) {
						if(groupcombo.getItemAt(i).toString().equalsIgnoreCase(Combo2)) {
							groupcombo.setSelectedIndex(i);
						}
					}
					
						
						 String Combo4= table1.getValueAt(selectedRow, 2).toString();
							for(int i=0; i<sessioncombo.getItemCount();i++) {
								if(sessioncombo.getItemAt(i).toString().equalsIgnoreCase(Combo4)) {
									sessioncombo.setSelectedIndex(i);
								}
							}
							
							
								String Combo6= table1.getValueAt(selectedRow, 3).toString();
								for(int i=0; i<daycombo.getItemCount();i++) {
									if(daycombo.getItemAt(i).toString().equalsIgnoreCase(Combo6)) {
										daycombo.setSelectedIndex(i);
									}
								}
								

								String Combo7= table1.getValueAt(selectedRow, 4).toString();
								for(int i=0; i<start.getItemCount();i++) {
									if(start.getItemAt(i).toString().equalsIgnoreCase(Combo7)) {
										start.setSelectedIndex(i);
									}
								}
								
								String Combo8= table1.getValueAt(selectedRow, 5).toString();
								for(int i=0; i<start.getItemCount();i++) {
									if(start.getItemAt(i).toString().equalsIgnoreCase(Combo8)) {
										start.setSelectedIndex(i);
									}
								}


								
							
			}  
		});
		scrollPane.setViewportView(table1);
		
		JLabel lblNewLabel = new JLabel("Manage Session Room Time");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(328, 22, 616, 52);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_3 = new JLabel("Group");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setBounds(72, 141, 187, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Session ID");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_4.setBounds(72, 203, 187, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_8.setBounds(72, 478, 187, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		lblNewLabel_7 = new JLabel("Day");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_7.setBounds(72, 265, 187, 26);
		frame.getContentPane().add(lblNewLabel_7);
		
		lblNewLabel_9 = new JLabel("Time");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_9.setBounds(72, 338, 187, 26);
		frame.getContentPane().add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("To");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_10.setBounds(171, 377, 31, 14);
		frame.getContentPane().add(lblNewLabel_10);
		
		start = new JComboBox();
		start.setBounds(72, 377, 87, 22);
		frame.getContentPane().add(start);
		
	    end = new JComboBox();
		end.setBounds(226, 377, 87, 22);
		frame.getContentPane().add(end);
		
	
	}
}