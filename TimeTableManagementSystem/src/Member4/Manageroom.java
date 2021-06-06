

package Member4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Common.Home;
import Member1.DBConnect;
import Member2.ManageSession;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

//import timetableManager.DBConnecter;
import net.proteanit.sql.DbUtils;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class Manageroom {

	private JFrame frame;
	private JComboBox comboBox;
	private JComboBox RoomTypeBox;
	private JComboBox RoomNoBox;
	private JTable table;
	String id ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manageroom window = new Manageroom();
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
	public Manageroom() {
		initialize();
		fillComboBox1();
		fillComboBox2();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
public void fillComboBox1() {
		
		try {
			  String query ="select roomType from location group by roomType";
			  Connection con = DBConnect.connect();
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  while(rs.next()) {	  
				  String roomType=rs.getString("roomType");
				  RoomTypeBox.addItem(roomType); 
			}
			
		}catch (Exception e){
			 e.printStackTrace();
			
		}
		
	}

	String typeRoom ;
	private void fillComboBox2() {
		 String query ="select roomName from location where roomType = '"+typeRoom+"' ";
		 Connection con = DBConnect.connect();
		
		try {
			
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  while(rs.next()) {
				  
				  String roomName=rs.getString("roomName");
				  RoomNoBox.addItem(roomName);
				 
			}
			
			  
		}catch (Exception e){
			 
			 e.printStackTrace();
			
		}
	
	}

String type;

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 1129, 767);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Rooms");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(400, 30, 237, 42);
		frame.getContentPane().add(lblNewLabel);
		
		JButton subBtn = new JButton("For Subject");
		subBtn.setBackground(new Color(224, 255, 255));
		subBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		subBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = DBConnect.connect();
					 
					type = "Subject";
					 String tableQ ="select * from location ";
					 
					 System.out.print(tableQ);
					comboBox.removeAllItems();
//					Class.forName("com.m");
					
//					 Statement sta = con.createStatement();
//					 ResultSet rs = sta.executeQuery("select roomName from location ");
					 
					 String query ="select roomType from location group by roomType";
					 PreparedStatement pst =con.prepareStatement(query);
					 ResultSet rs = pst.executeQuery();
					  
					 PreparedStatement pst1 =con.prepareStatement(tableQ);
					 ResultSet rs1 = pst1.executeQuery();
					 
					 while(rs.next()) {
						 String roomName = rs.getString("roomType");
						 comboBox.addItem(roomName);
					 }
					 con.close();
					 showData();
					 
					 
					 
				}catch (Exception e1) {
					
					}
				
			}

			private void showData() {
				Connection con = DBConnect.connect();
				DefaultTableModel model = new DefaultTableModel();
			
			 
				try {
//					model.removeAllItems();
					System.out.println(type);
					
					model.addColumn("Loc ID");
					model.addColumn("Room Type");
					model.addColumn("Room Name");
					model.addColumn(type);
					
					if(type == "Subject")
					{
						String query = "Select  id, roomType, roomName , subject from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("subject")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Tag")
					{
						String query = "Select  id, roomType, roomName , tag from rooms  ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("tag")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Lecturer")
					{
						String query = "Select  id, roomType, roomName , lecturer from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("lecturer")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Group")
					{
						String query = "Select  id, roomType, roomName , group from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("group")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}				
					
					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(60);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.getColumnModel().getColumn(3).setPreferredWidth(100);
				 
				}
				catch(Exception ex)
				{
					System.out.println("Error: " + ex.getMessage());
				}
				
			}
		});
		
		subBtn.setBounds(35, 135, 131, 35);
		frame.getContentPane().add(subBtn);
		
		JButton lecBtn = new JButton("For Lectuers");
		lecBtn.setBackground(new Color(224, 255, 255));
		lecBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		lecBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.removeAllItems();
				try { 
					type = "Lecturer";
					Connection con = DBConnect.connect(); 	
					 //get data to comboBox
					 String query ="select LecturerName from Employee";
					 PreparedStatement pst =con.prepareStatement(query);
					 ResultSet rs = pst.executeQuery();
					  
					 
					 while(rs.next()) {
						 String lec = rs.getString("LecturerName");
						 comboBox.addItem(lec);
					 }
					 con.close();
					 showData();
				}catch (Exception e1) {
					
					}
			}
			private void showData() {
				Connection con = DBConnect.connect();
				DefaultTableModel model = new DefaultTableModel();
			
			 
				try {
//					model.removeAllItems();
					System.out.println(type);
					
					model.addColumn("Loc ID");
					model.addColumn("Room Type");
					model.addColumn("Room Name");
					model.addColumn(type);
					
					if(type == "Subject")
					{
						String query = "Select  id, roomType, roomName , subject from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("subject")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Tag")
					{
						String query = "Select  id, roomType, roomName , tag from rooms  ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("tag")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Lecturer")
					{
						String query = "Select  id, roomType, roomName , lecturer from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("lecturer")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Group")
					{
						String query = "Select  id, roomType, roomName , group from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("group")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}				
					
					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(60);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.getColumnModel().getColumn(3).setPreferredWidth(100);
				 
				}
				catch(Exception ex)
				{
					System.out.println("Error: " + ex.getMessage());
				}
				
			}
		});
		lecBtn.setBounds(302, 135, 149, 35);
		frame.getContentPane().add(lecBtn);
		
		JButton tagBtn = new JButton("For Tags");
		tagBtn.setBackground(new Color(224, 255, 255));
		tagBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		tagBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					type = "Tag";
					comboBox.removeAllItems();
					Connection con = DBConnect.connect();
//					 Statement sta = con.createStatement();
//					 ResultSet rs = sta.executeQuery("select roomName from location ");
					 
					 String query ="select TagName from Tags";
					 PreparedStatement pst =con.prepareStatement(query);
					 ResultSet rs = pst.executeQuery();
					  
					 
					 while(rs.next()) {
						 String tagType = rs.getString("tagName");
						 comboBox.addItem(tagType);
					 }
					 con.close();
					 showData();
				}catch (Exception e1) {
					
					}
				
	
				
				
			}
			private void showData() {
				Connection con = DBConnect.connect();
				DefaultTableModel model = new DefaultTableModel();
			
			 
				try {
//					model.removeAllItems();
					System.out.println(type);
					
					model.addColumn("Loc ID");
					model.addColumn("Room Type");
					model.addColumn("Room Name");
					model.addColumn(type);
					
					if(type == "Subject")
					{
						String query = "Select  id, roomType, roomName , subject from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("subject")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Tag")
					{
						String query = "Select  id, roomType, roomName , tag from rooms  ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("tag")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Lecturer")
					{
						String query = "Select  id, roomType, roomName , lecturer from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("lecturer")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Group")
					{
						String query = "Select  id, roomType, roomName , group from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("group")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}				
					
					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(60);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.getColumnModel().getColumn(3).setPreferredWidth(100);
				 
				}
				catch(Exception ex)
				{
					System.out.println("Error: " + ex.getMessage());
				}
				
			}
		});
		tagBtn.setBounds(178, 135, 112, 35);
		frame.getContentPane().add(tagBtn);
		
		JButton grpBtn = new JButton("For Groups / Sub-Groups");
		grpBtn.setBackground(new Color(224, 255, 255));
		grpBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		grpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try { 
					type = "Group";
					comboBox.removeAllItems();
					Connection con = DBConnect.connect(); 	
					 
					 String query ="select GroupNo from studentGroups";
					 
					 PreparedStatement pst =con.prepareStatement(query);
					 ResultSet rs = pst.executeQuery();
					  
					 
					 while(rs.next()) {
						 String group = rs.getString("GroupNo");
						 comboBox.addItem(group);
					 }
					 con.close();
					 showData();
				}catch (Exception e1) {
					
					}
			}
			private void showData() {
				Connection con = DBConnect.connect();
				DefaultTableModel model = new DefaultTableModel();
			
			 
				try {
//					model.removeAllItems();
					System.out.println(type);
					
					model.addColumn("Loc ID");
					model.addColumn("Room Type");
					model.addColumn("Room Name");
					model.addColumn(type);
					
					if(type == "Subject")
					{
						String query = "Select  id, roomType, roomName , subject from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("subject")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Tag")
					{
						String query = "Select  id, roomType, roomName , tag from rooms  ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("tag")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Lecturer")
					{
						String query = "Select  id, roomType, roomName , lecturer from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("lecturer")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Group")
					{
						String query = "Select  id, roomType, roomName , group from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("group")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}				
					
					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(60);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.getColumnModel().getColumn(3).setPreferredWidth(100);
				 
				}
				catch(Exception ex)
				{
					System.out.println("Error: " + ex.getMessage());
				}
				
			}
		});
		grpBtn.setBounds(463, 135, 214, 35);
		frame.getContentPane().add(grpBtn);
		
		comboBox = new JComboBox();
		comboBox.setBackground(SystemColor.control);
		comboBox.setForeground(Color.BLACK);
	 
		comboBox.setBounds(35, 183, 320, 42);
		frame.getContentPane().add(comboBox);
		
		RoomTypeBox = new JComboBox();
		RoomTypeBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					typeRoom = RoomTypeBox.getSelectedItem().toString();
					
					RoomNoBox.removeAllItems();
					fillComboBox2();
				}	
			}
			private void showData() {
				Connection con = DBConnect.connect();
				DefaultTableModel model = new DefaultTableModel();
			
			 
				try {
//					model.removeAllItems();
					System.out.println(type);
					
					model.addColumn("Loc ID");
					model.addColumn("Room Type");
					model.addColumn("Room Name");
					model.addColumn(type);
					
					if(type == "Subject")
					{
						String query = "Select  id, roomType, roomName , subject from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("subject")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Tag")
					{
						String query = "Select  id, roomType, roomName , tag from rooms  ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("tag")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Lecturer")
					{
						String query = "Select  id, roomType, roomName , lecturer from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("lecturer")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Group")
					{
						String query = "Select  id, roomType, roomName , group from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("group")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}				
					
					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(60);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.getColumnModel().getColumn(3).setPreferredWidth(100);
				 
				}
				catch(Exception ex)
				{
					System.out.println("Error: " + ex.getMessage());
				}
				
			}
		});
		
		 
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		scrollPane_1.setBounds(35, 341, 1031, 344);
		frame.getContentPane().add(scrollPane_1);
		
		

		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedRow=table.getSelectedRow();
				id = table.getValueAt(selectedRow, 0).toString();
				System.out.println(id);
				
				String CombobOX = table.getValueAt(selectedRow, 3).toString();
				for(int i=0; i<comboBox.getItemCount();i++) {
					if(comboBox.getItemAt(i).toString().equalsIgnoreCase(CombobOX)) {
						comboBox.setSelectedIndex(i);
					}
				}
				
				String CombobOX2 = table.getValueAt(selectedRow, 1).toString();
				for(int i=0; i<RoomTypeBox.getItemCount();i++) {
					if(RoomTypeBox.getItemAt(i).toString().equalsIgnoreCase(CombobOX2)) {
						RoomTypeBox.setSelectedIndex(i);
					}
				}
				
				String CombobOX3 = table.getValueAt(selectedRow, 2).toString();
				for(int i=0; i<RoomNoBox.getItemCount();i++) {
					if(RoomNoBox.getItemAt(i).toString().equalsIgnoreCase(CombobOX3)) {
						RoomNoBox.setSelectedIndex(i);
					}
				}

				
			}
		});
		scrollPane_1.setViewportView(table);
		
		
		RoomTypeBox.setBounds(395, 183, 310, 42);
		frame.getContentPane().add(RoomTypeBox);
		
		RoomNoBox = new JComboBox();
		RoomNoBox.setBounds(756, 183, 310, 42);
		frame.getContentPane().add(RoomNoBox);
		
		JButton saveMangeRoom = new JButton("Save Room");
		saveMangeRoom.setBackground(new Color(224, 255, 255));
		saveMangeRoom.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		saveMangeRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
		saveMangeRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.print(type);
				String value1 = (String)comboBox.getSelectedItem();
				String value2 = (String)RoomTypeBox.getSelectedItem();
				String value3 = (String)RoomNoBox.getSelectedItem();
				
				System.out.print(value1 + "," + value2 + "," + value3);
				Connection con = DBConnect.connect();
				
			
				try {
					if(type == "Subject")
					{
						 String query = "INSERT INTO rooms values(null, '" + value2 + "','" + value3 + "','" + value1 + "', 'nothing' ,'nothing' ,'nothing','nothing'  )";
		                    //type eka enter karanna hv by radio buttons
						    
		                    Statement sta = con.createStatement();
		                     
		                    int x = sta.executeUpdate(query);
		                    if (x == 0) {
		                        JOptionPane.showMessageDialog(saveMangeRoom, "This is alredy exist");
		                    } else {
		                        JOptionPane.showMessageDialog(saveMangeRoom,
		                            "Welcome, Your account is sucessfully created");
//		                        showManageRoomData();
		                    }
					}
					else if(type == "Lecturer")
					{
						 String query = "INSERT INTO rooms values(null, '" + value2 + "','" + value3 + "','nothing' ,'nothing' ,'" + value1 + "' ,'nothing' ,'nothing')";
		                    //type eka enter karanna hv by radio buttons
						    
		                    Statement sta = con.createStatement();
		                     
		                    int x = sta.executeUpdate(query);
		                    if (x == 0) {
		                        JOptionPane.showMessageDialog(saveMangeRoom, "This is alredy exist");
		                    } else {
		                        JOptionPane.showMessageDialog(saveMangeRoom,
		                            "Welcome, Your account is sucessfully created");
//		                        showManageRoomData();
		                    }
					}
					else if(type == "Tag")
					{
						 String query = "INSERT INTO rooms values(null, '" + value2 + "','" + value3 + "', 'nothing' ,'" + value1 + "','nothing' ,'nothing'  ,'nothing')";
		                    //type eka enter karanna hv by radio buttons
						    
		                    Statement sta = con.createStatement();
		                     
		                    int x = sta.executeUpdate(query);
		                    if (x == 0) {
		                        JOptionPane.showMessageDialog(saveMangeRoom, "This is alredy exist");
		                    } else {
		                        JOptionPane.showMessageDialog(saveMangeRoom,
		                            "Welcome, Your account is sucessfully created");
//		                        showManageRoomData();
		                    }
					}
					else if(type == "Group")
					{
						 String query = "INSERT INTO rooms values(null, '" + value2 + "','" + value3 + "', 'nothing' ,'nothing','nothing' ,'" + value1 + "','nothing' )";
		                    //type eka enter karanna hv by radio buttons
						    
		                    Statement sta = con.createStatement();
		                     
		                    int x = sta.executeUpdate(query);
		                    if (x == 0) {
		                        JOptionPane.showMessageDialog(saveMangeRoom, "This is alredy exist");
		                    } else {
		                        JOptionPane.showMessageDialog(saveMangeRoom,
		                            "Welcome, Your account is sucessfully created");
//		                        showManageRoomData();
		                    }
					}
				
					else 
					{
						 JOptionPane.showMessageDialog(saveMangeRoom,
		                            "Somthing Not Right");
					}
					
	                   con.close();
	                } catch (Exception exception) {
	                    exception.printStackTrace();
	                	
	                }
			}
		});
		saveMangeRoom.setBounds(35, 269, 320, 57);
		frame.getContentPane().add(saveMangeRoom);
		
		JButton btnNewButton = new JButton("Update Room");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(type == "Subject") {
						
					
					Connection con = DBConnect.connect();				
					//SQL Query to update data
					String query="Update rooms set  id='"+id+"',roomtype='"+RoomTypeBox.getSelectedItem()+"',roomName='"+RoomNoBox.getSelectedItem()+"',subject='"+comboBox.getSelectedItem()+"' " + "where id="+Integer.parseInt(id);
					
					PreparedStatement pst=con.prepareStatement(query);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Updated");
					pst.close();
					
					try {
    					//Connecting Database - W.N.S. Amaranayake - IT19009728
//    					Connection con = DBConnect.connect();
    					
    					String query1="select * from rooms ";
    					PreparedStatement pst1=con.prepareStatement(query1);
    					ResultSet rs=pst1.executeQuery();
    					table.setModel(DbUtils.resultSetToTableModel(rs));	
    					con.close();
    				}
    				catch(Exception ex) {
    					ex.printStackTrace();
    				}
				}
				
			}catch(Exception ea) {
				ea.printStackTrace();
			}
			}
		});
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/update.png")));
		btnNewButton.setBounds(395, 269, 310, 59);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete Room");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(type == "Subject") {
						
					
						Connection con = DBConnect.connect();
						String query="Delete from rooms where id='"+id+"'";
						PreparedStatement pst=con.prepareStatement(query);
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Data Deleted");
						showData();
						pst.close();
						
						}
					else if(type == "Lecturer") {
						
						
						Connection con = DBConnect.connect();
						String query="Delete from rooms where id='"+id+"'";
						PreparedStatement pst=con.prepareStatement(query);
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Data Deleted");
						showData();
						pst.close();
						
						}
					else if(type == "Tag") {
						
						
						Connection con = DBConnect.connect();
						String query="Delete from rooms where id='"+id+"'";
						PreparedStatement pst=con.prepareStatement(query);
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Data Deleted");
						showData();
						pst.close();
						
						}
					else if(type == "Group") {
						
						
						Connection con = DBConnect.connect();
						String query="Delete from rooms where id='"+id+"'";
						PreparedStatement pst=con.prepareStatement(query);
						pst.execute();
						
						JOptionPane.showMessageDialog(null, "Data Deleted");
						showData();
						pst.close();
						
						}
				}
						catch(Exception en) {
							en.printStackTrace();
							
						}
					}
					
			
			
			private void showData() {
				Connection con = DBConnect.connect();
				DefaultTableModel model = new DefaultTableModel();
			
			 
				try {
//					model.removeAllItems();
					System.out.println(type);
					
					model.addColumn("Loc ID");
					model.addColumn("Room Type");
					model.addColumn("Room Name");
					model.addColumn(type);
					
					if(type == "Subject")
					{
						String query = "Select  id, roomType, roomName , subject from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("subject")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Tag")
					{
						String query = "Select  id, roomType, roomName , tag from rooms  ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("tag")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Lecturer")
					{
						String query = "Select  id, roomType, roomName , lecturer from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("lecturer")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}
					else if(type == "Group")
					{
						String query = "Select  id, roomType, roomName , group from rooms ";
						Statement sta = con.createStatement();
						ResultSet rs = sta.executeQuery(query);
						
						while(rs.next())
						{
							model.addRow(new Object[] {
									rs.getString("id"),
									rs.getString("roomType"),
									rs.getString("roomName"),
									rs.getString("group")
							});
						}
						rs.close();
						sta.close();
						con.close();
					}				
					
					table.setModel(model);
					table.setAutoResizeMode(0);
					table.getColumnModel().getColumn(0).setPreferredWidth(60);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.getColumnModel().getColumn(3).setPreferredWidth(100);
				 
				}
				catch(Exception ex)
				{
					System.out.println("Error: " + ex.getMessage());
				}
				
			}
		});

		btnNewButton_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/remove.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(756, 269, 310, 54);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home itm = new Home();
				Home.main(null);
				frame.setVisible(false);
			}
		});
		btnHome.setIcon(new ImageIcon(this.getClass().getResource("/home.png")));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(255, 250, 250));
		btnHome.setBounds(35, 73, 73, 49);
		frame.getContentPane().add(btnHome);
	}
}
