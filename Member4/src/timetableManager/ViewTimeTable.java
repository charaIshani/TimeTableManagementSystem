package timetableManager;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import net.proteanit.sql.DbUtils;

import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;

public class ViewTimeTable {

	private JFrame frame;
	private JComboBox comboBox;
	private JComboBox lecComboBox;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTimeTable window = new ViewTimeTable();
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
	public ViewTimeTable() {
		initialize();
		fillComboBox1();
//		fillComboBox2();
	}

	String selected;
	/**
	 * Initialize the contents of the frame.
	 */
	public void fillComboBox1() {
		
//		try {
//			  String query ="select * from Timetable where = '"+selected+"'";
//			  Connection con = DBConnecter.connect();
//			  PreparedStatement pst =con.prepareStatement(query);
//			  ResultSet rs = pst.executeQuery();
//			  
//			  while(rs.next()) {	  
//				  String roomType=rs.getString("roomType");
//				  lecComboBox.addItem(roomType); 
//			}
//			
//		}catch (Exception e){
//			 e.printStackTrace();
//			
//		}
		
	}
	 
	
	String type;
	String getValue;
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 875, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {


			}
		});
		
		JLabel lblNewLabel = new JLabel("View Time Table");
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 19));
		lblNewLabel.setBounds(308, 22, 227, 40);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLecturer = new JButton("For Lecturer");
		
		btnLecturer.addActionListener(new ActionListener() {
		 
				public void actionPerformed(ActionEvent e) {
					comboBox.removeAllItems();
					try {
						 Connection con = DBConnecter.connect();
						 
						type = "Lecturer";
 
						System.out.println(type); 
						 
						 String query ="select Lecture1 from Session group by Lecture1";
						 String query1 ="select Lecture2 from Session group by Lecture2";
						 
						 PreparedStatement pst =con.prepareStatement(query);
						 ResultSet rs = pst.executeQuery(); 
						 
						 PreparedStatement pst1 =con.prepareStatement(query1);
						 ResultSet rs1 = pst1.executeQuery();
						 
						 System.out.println(rs);
						 comboBox.setModel(new DefaultComboBoxModel(new String[] {" -----------Select Leturer In Here-----------"}));
							
						 
						 while(rs.next()) {
							 String LecturerName = rs.getString("Lecture1");
							 String LecturerName1 = rs1.getString("Lecture2");
							 System.out.println(LecturerName);
							 comboBox.addItem(LecturerName);
							 comboBox.addItem("--Lectrer 2--");
							 comboBox.addItem(LecturerName1);
						 }
						 con.close();
					 
						 
						 
						 
					}catch (Exception e1) {
						
						}
					 
				}
			 
		});
		btnLecturer.setBounds(45, 87, 174, 40);
		frame.getContentPane().add(btnLecturer);
		
		JButton btnLecturer_1 = new JButton("For Student");
		btnLecturer_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.removeAllItems();
				try { 
					type = "StudentGroup";
					 Connection con = DBConnecter.connect(); 	
					 //get data to comboBox
					 String query ="select GroupID from Session group by GroupID";
					 PreparedStatement pst =con.prepareStatement(query);
					 ResultSet rs = pst.executeQuery();
					 
					 comboBox.setModel(new DefaultComboBoxModel(new String[] {" ------------Select GroupID In Here-----------"}));
					 
					 while(rs.next()) {
						 String GroupID = rs.getString("GroupID");
						 comboBox.addItem(GroupID);
					 }
					 con.close();
					 
				}catch (Exception e1) {
					
					}
			}
			 
		});
		btnLecturer_1.setBounds(307, 87, 174, 40);
		frame.getContentPane().add(btnLecturer_1);
		
		String roomName;
		
		JButton btnForLoction = new JButton("For Loction");
		btnForLoction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.removeAllItems();
				try { 
					type = "roomName";
					 Connection con = DBConnecter.connect(); 
					 String query ="select room from manageRooms group by room";
					 PreparedStatement pst =con.prepareStatement(query);
					 ResultSet rs = pst.executeQuery();
					 comboBox.setModel(new DefaultComboBoxModel(new String[] {" -------------Select Room Name In Here------------"}));
					 
					 while(rs.next()) {
						 String roomName = rs.getString("room");
						 comboBox.addItem(roomName);
					 }
					 con.close();
					 
				}catch (Exception e1) {
					
					}
			}
			
		});
		btnForLoction.setBounds(564, 87, 174, 40);
		frame.getContentPane().add(btnForLoction);
		
		  
		 comboBox = new JComboBox();
		 comboBox.addItemListener(new ItemListener() {
			 public void itemStateChanged(ItemEvent e) {
				try {
					
					if(e.getStateChange() == ItemEvent.SELECTED)
					{ 	
						
						
						getValue = comboBox.getSelectedItem().toString();
						System.out.println("selected Value");
						System.out.println(getValue);
						
						Connection con = DBConnecter.connect();
						
						System.out.println(type);
						showData() ;
						
					}
					
					
				}
					catch(Exception ex)
					{
						System.out.println("Error: " + ex.getMessage());
					}
			 }	
					 
					
		 });
		comboBox.setBounds(82, 142, 566, 40);
		frame.getContentPane().add(comboBox);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 222, 839, 322);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setColumnHeaderView(table);
	}

	
	
	private void showData() {
		Connection con = DBConnecter.connect();
		DefaultTableModel model = new DefaultTableModel();
		
	
	 
		try {
			
			model.addColumn("ID");
			model.addColumn("Lecturer 1");
			model.addColumn("Lecturer 2"); 
			model.addColumn("Subject");
			model.addColumn("GroupID");
			model.addColumn("Tag");
			model.addColumn("NoOfStudents");
			model.addColumn("Room Type");
			model.addColumn("Room Name");
			model.addColumn("Day");
			model.addColumn("Start Time");
			model.addColumn("End Time");
			 
									
				getValue = comboBox.getSelectedItem().toString();
				System.out.println("selected Value");
				System.out.println(getValue);
			 
				
			 
				
				if(type == "Lecturer") {
					
				String selected = comboBox.getSelectedItem().toString();
				
			 
			
				
				

			 
					String query = "Select t.ID ,s.Lecture1 ,s.Lecture2 ,s.Subject , s.GroupID , s.Tag , s.NoOfStudents , r.roomType , r.room , t.day ,t.stime , t.etime from  Session s inner join manageRooms r on  s.ID = r.SessionID inner join TimeTable t on t.sesionRoomID = r.ID  where s.Lecture1 = '"+getValue+"' ";
					
//					System.out.println( );
					Statement sta = con.createStatement();
					ResultSet rs = sta.executeQuery(query);
					
					String query1 = "Select t.ID ,s.Lecture1 ,s.Lecture2 ,s.Subject , s.GroupID , s.Tag , s.NoOfStudents , r.roomType , r.room , t.day ,t.stime , t.etime from  Session s inner join manageRooms r on  s.ID = r.SessionID inner join TimeTable t on t.sesionRoomID = r.ID  where s.Lecture2 = '"+getValue+"' ";
					
//					System.out.println( );
					Statement sta1 = con.createStatement();
					ResultSet rs1 = sta1.executeQuery(query1);
					
					while(rs.next())
					{
						model.addRow(new Object[] {
								rs.getString("ID"),
								rs.getString("Lecture1"),
								rs.getString("Lecture2"),
								rs.getString("Subject"),
								rs.getString("GroupID"),
								rs.getString("Tag"),
								rs.getString("NoOfStudents"),
								rs.getString("roomType"),
								rs.getString("room"),
								rs.getString("day"),
								rs.getString("stime"),
								rs.getString("etime")
						});
					}
					rs.close();
					sta.close();
					while(rs1.next())
					{
						model.addRow(new Object[] {
								rs1.getString("ID"),
								rs1.getString("Lecture1"),
								rs1.getString("Lecture2"),
								rs1.getString("Subject"),
								rs1.getString("GroupID"),
								rs1.getString("Tag"),
								rs1.getString("NoOfStudents"),
								rs1.getString("roomType"),
								rs1.getString("room"),
								rs1.getString("day"),
								rs1.getString("stime"),
								rs1.getString("etime")
						});
					}
					rs1.close();
					sta1.close();
					 
			 
			 
				
				table.setModel(model);
				table.setAutoResizeMode(0);
				table.getColumnModel().getColumn(0).setPreferredWidth(20);
				table.getColumnModel().getColumn(1).setPreferredWidth(80);
				table.getColumnModel().getColumn(2).setPreferredWidth(80); 
				table.getColumnModel().getColumn(3).setPreferredWidth(90);
				table.getColumnModel().getColumn(4).setPreferredWidth(80);
				table.getColumnModel().getColumn(5).setPreferredWidth(60);
				table.getColumnModel().getColumn(6).setPreferredWidth(80);
				table.getColumnModel().getColumn(7).setPreferredWidth(90);
				table.getColumnModel().getColumn(8).setPreferredWidth(90);
				table.getColumnModel().getColumn(9).setPreferredWidth(70);
				table.getColumnModel().getColumn(10).setPreferredWidth(40);
				table.getColumnModel().getColumn(11).setPreferredWidth(40); 
				
//					rs.close();
//					sta.close();
//					con.close();
//					
			}
				
				else if(type == "StudentGroup") {
					String selected = comboBox.getSelectedItem().toString();
					
					 

					model.addColumn("ID");
					model.addColumn("Lecturer 1");
					model.addColumn("Lecturer 2"); 
					model.addColumn("Subject");
					model.addColumn("GroupID");
					model.addColumn("Tag");
					model.addColumn("NoOfStudents");
					model.addColumn("Room Type");
					model.addColumn("Room Name");
					model.addColumn("Day");
					model.addColumn("Start Time");
					model.addColumn("End Time");
					

				 

					String query = "Select t.ID ,s.Lecture1 ,s.Lecture2 ,s.Subject , s.GroupID , s.Tag , s.NoOfStudents , r.roomType , r.room , t.day ,t.stime , t.etime from  Session s inner join manageRooms r on  s.ID = r.SessionID inner join TimeTable t on t.sesionRoomID = r.ID  where s.GroupID = '"+getValue+"' ";
					
//						System.out.println( );
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("ID"),
									rs.getString("Lecture1"),
									rs.getString("Lecture2"),
									rs.getString("Subject"),
									rs.getString("GroupID"),
									rs.getString("Tag"),
									rs.getString("NoOfStudents"),
									rs.getString("roomType"),
									rs.getString("room"),
									rs.getString("day"),
									rs.getString("stime"),
									rs.getString("etime")
							});
						}
						rs.close();
						sta.close();
						 
				 
				 
					
					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(20);
					table.getColumnModel().getColumn(1).setPreferredWidth(80);
					table.getColumnModel().getColumn(2).setPreferredWidth(80); 
					table.getColumnModel().getColumn(3).setPreferredWidth(90);
					table.getColumnModel().getColumn(4).setPreferredWidth(80);
					table.getColumnModel().getColumn(5).setPreferredWidth(60);
					table.getColumnModel().getColumn(6).setPreferredWidth(80);
					table.getColumnModel().getColumn(7).setPreferredWidth(90);
					table.getColumnModel().getColumn(8).setPreferredWidth(90);
					table.getColumnModel().getColumn(9).setPreferredWidth(70);
					table.getColumnModel().getColumn(10).setPreferredWidth(40);
					table.getColumnModel().getColumn(11).setPreferredWidth(40); 
				 
				}
				
				else if(type == "roomName") {
					String selected = comboBox.getSelectedItem().toString();
					
				 

					model.addColumn("ID");
					model.addColumn("Lecturer 1");
					model.addColumn("Lecturer 2"); 
					model.addColumn("Subject");
					model.addColumn("GroupID");
					model.addColumn("Tag");
					model.addColumn("NoOfStudents");
					model.addColumn("Room Type");
					model.addColumn("Room Name");
					model.addColumn("Day");
					model.addColumn("Start Time");
					model.addColumn("End Time");
					

				 

					String query = "Select t.ID ,s.Lecture1 ,s.Lecture2 ,s.Subject , s.GroupID , s.Tag , s.NoOfStudents , r.roomType , r.room , t.day ,t.stime , t.etime from  Session s inner join manageRooms r on  s.ID = r.SessionID inner join TimeTable t on t.sesionRoomID = r.ID  where r.room = '"+getValue+"' ";
						
//						System.out.println( );
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("ID"),
									rs.getString("Lecture1"),
									rs.getString("Lecture2"),
									rs.getString("Subject"),
									rs.getString("GroupID"),
									rs.getString("Tag"),
									rs.getString("NoOfStudents"),
									rs.getString("roomType"),
									rs.getString("room"),
									rs.getString("day"),
									rs.getString("stime"),
									rs.getString("etime")
							});
						}
						rs.close();
						sta.close();
						 
				 
				 
					
					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(20);
					table.getColumnModel().getColumn(1).setPreferredWidth(80);
					table.getColumnModel().getColumn(2).setPreferredWidth(80); 
					table.getColumnModel().getColumn(3).setPreferredWidth(90);
					table.getColumnModel().getColumn(4).setPreferredWidth(80);
					table.getColumnModel().getColumn(5).setPreferredWidth(60);
					table.getColumnModel().getColumn(6).setPreferredWidth(80);
					table.getColumnModel().getColumn(7).setPreferredWidth(90);
					table.getColumnModel().getColumn(8).setPreferredWidth(90);
					table.getColumnModel().getColumn(9).setPreferredWidth(70);
					table.getColumnModel().getColumn(10).setPreferredWidth(40);
					table.getColumnModel().getColumn(11).setPreferredWidth(40); 
				 
				}
		
			
			
		 
		 
		}
		
		catch(Exception ex)
		{
			System.out.println("Error: " + ex.getMessage());
		}finally {
        	try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        }
		
	}
}
