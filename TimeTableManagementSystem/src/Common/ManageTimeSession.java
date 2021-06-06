package Common;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Member1.DBConnect;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.ImageIcon;

public class ManageTimeSession {

	private JFrame frame;
	private JTextField stime;
	private JTextField etime;
	private JComboBox groupIDcomboBox;
	private JComboBox sessioncomboBox;
	private JComboBox daycomboBox;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageTimeSession window = new ManageTimeSession();
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
	public ManageTimeSession() {
		initialize();
		fillComboBoxGroupType();
		fillComboBoxSession();
		getManageRoomTableID();
	}

	public String Type;
	public String Session;
	public String getSessionRoonID;
	
	public String Date;
	public String StartTime;
	public String EndTime;
	
public void fillComboBoxGroupType() {
		
		try {
			
			System.out.println(Type);
			
			if(Type == "Group ID")
			{
				groupIDcomboBox.removeAllItems();
				Connection con = DBConnect.connect();
				  String query ="select GroupID from studentGroups group by GroupID ";
				 
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  while(rs.next()) {	  
					  String groupID = rs.getString("GroupID");
					  System.out.println(groupID);
					  groupIDcomboBox.addItem(groupID); 
				}
				  con.close();
			}
			else if(Type == "SubGroup ID")
			{
				groupIDcomboBox.removeAllItems();
				Connection con = DBConnect.connect();
				  String query ="select SubGroupID from studentGroups  ";
				 
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  while(rs.next()) {	  
					  String groupID = rs.getString("SubGroupID");
					  System.out.println(groupID);
					  groupIDcomboBox.addItem(groupID); 
				}
				  con.close();
			}
			else 
			{
				groupIDcomboBox.removeAllItems();
			}
			
			
			   
		}catch (Exception e){
			 e.printStackTrace();
			
		} 
		
	}
String getLec1;
String sid;
String lec1;
String lec2;
String subCode;
String groupid;
String tag;
String subName ;
String SID;
String getID;


	public void fillComboBoxSession() {
		
		try {
			sessioncomboBox.removeAllItems();
			Connection con = DBConnect.connect();
			  String query ="select * from Session where GroupID  = '"+Session+"'   ";
			 
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  while(rs.next()) {
				   sid = rs.getString("ID");
				   lec1=rs.getString("Lecture1");
				   lec2=rs.getString("Lecture2");
				   subCode =rs.getString("SubjectCode");
				   subName =rs.getString("Subject");
				   groupid =rs.getString("GroupID");
				   tag=rs.getString("Tag"); 
				  
 	  
				  sessioncomboBox.addItem(sid +"-"+lec1 +"-"+ lec2 +"-"+ subCode +"-"+ subName +"-"+ groupid +"-"+ tag);
//				  sessionBox.getString("-------------");
				  
					  
			} 
			  con.close();
//			  
//			  getManageRoomTableID();
			   
		}catch (Exception e){
			 e.printStackTrace();
			
		}
		
	}
public void getManageRoomTableID() {
		
		try {
			
			Connection con = DBConnect.connect();
			  String query ="select ID from manageRooms where SessionID =  '"+SID+"' ";
			 
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  while(rs.next()) {	  
				  System.out.println("Manage Room ID");
				  getSessionRoonID = rs.getString("ID");
				  System.out.println(getSessionRoonID);
//				  groupIDcomboBox.addItem(getSessionRoonID); 
			}
			  con.close(); 
			   
		}catch (Exception e){
			 e.printStackTrace();
			
		} 
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 1086, 769);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); 
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				showData();
				
			
				
			}
			});
		
		JLabel lblNewLabel = new JLabel("Manage  Session - Room - Time");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(309, 25, 531, 46);
		frame.getContentPane().add(lblNewLabel);
		
		 groupIDcomboBox = new JComboBox();
		 groupIDcomboBox.setFont(new Font("Sylfaen", Font.BOLD, 14));
		 groupIDcomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					Session = groupIDcomboBox.getSelectedItem().toString();
					System.out.println(Session);
					
//					groupIDcomboBox.removeAllItems();
					fillComboBoxSession();
				}
				
			}
		});
		groupIDcomboBox.setBounds(425, 104, 197, 46);
		frame.getContentPane().add(groupIDcomboBox);
		
		sessioncomboBox = new JComboBox();
		sessioncomboBox.setFont(new Font("Sylfaen", Font.BOLD, 14));
		sessioncomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					getID = sessioncomboBox.getSelectedItem().toString();
					System.out.println(getID);
					SID = getID.split("-")[0]; 
					System.out.println(SID);
					
					getManageRoomTableID();
					
					
					
				}
				
//				SID.setText(sid);
//				System.out.println(SID);
			}
		});
		sessioncomboBox.setBounds(718, 104, 319, 46);
		frame.getContentPane().add(sessioncomboBox);
		
		 daycomboBox = new JComboBox();
		 daycomboBox.setFont(new Font("Sylfaen", Font.BOLD, 14));
		daycomboBox.setModel(new DefaultComboBoxModel(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sundayy"}));
		daycomboBox.setBounds(116, 161, 106, 46);
		frame.getContentPane().add(daycomboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Group ID");
		lblNewLabel_1.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblNewLabel_1.setBounds(332, 112, 122, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Session");
		lblNewLabel_1_1.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(640, 112, 122, 29);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Day");
		lblNewLabel_1_1_1.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(62, 169, 122, 29);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 297, 1038, 322);
		frame.getContentPane().add(scrollPane_1);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\add.png"));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date = daycomboBox.getSelectedItem().toString();
				StartTime = stime.getText().toString();
				EndTime = etime.getText().toString();
				
				try { 
					 
					 
					Connection con = DBConnect.connect();
 
					 
					 	String query = "INSERT INTO TimeTable (sesionRoomID,day,stime , etime ) values('" + getSessionRoonID + "',  '"+ Date +"' ,'"+ StartTime +"','"+ EndTime +"')";
	           
	                    Statement sta = con.createStatement();
	                    int x = sta.executeUpdate(query);
	                    System.out.println(x);
	                    con.close();	                   
	                    if (x == 0) {
	                        JOptionPane.showMessageDialog(btnSave,"This is Unsucessful exist");
	                    } else {
	                        JOptionPane.showMessageDialog(btnSave,
	                            "Welcome, Your account is sucessfully created");
	                        showData();
	                    }
	                    
	                    
	                } catch (Exception exception) {
	                    exception.printStackTrace();
	                    System.out.println("Error: " + exception.getMessage());
	                } 
				
				
			}
		});
		btnSave.setBounds(902, 161, 135, 46);
		frame.getContentPane().add(btnSave);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = DBConnect.connect();
					//SQL Query to delete data
					String query="Delete from TimeTable where id="+Integer.parseInt(getSessionRoonID);
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted");
					pst.close();
					
					try {
    					
    					String query1="select * from TimeTable ";
    					PreparedStatement pst2=con.prepareStatement(query1);
    					ResultSet rs=pst2.executeQuery();
    					table.setModel(DbUtils.resultSetToTableModel(rs));	
    					con.close();
    				}
    				catch(Exception ex) {
    					ex.printStackTrace();
    				}
					}
					catch(Exception en) {
						en.printStackTrace();
						
					}
			}
		});
		btnDelete.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\remove.png"));
		btnDelete.setBounds(902, 218, 135, 46);
		frame.getContentPane().add(btnDelete);
		
		stime = new JTextField();
		stime.setBounds(360, 161, 197, 46);
		frame.getContentPane().add(stime);
		stime.setColumns(10);
		
		etime = new JTextField();
		etime.setColumns(10);
		etime.setBounds(666, 161, 197, 46);
		frame.getContentPane().add(etime);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Start Time");
		lblNewLabel_1_1_1_1.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(255, 169, 95, 29);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("End Time");
		lblNewLabel_1_1_1_2.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblNewLabel_1_1_1_2.setBounds(574, 169, 122, 29);
		frame.getContentPane().add(lblNewLabel_1_1_1_2);
		
	
		
		JComboBox groupTypecomboBox = new JComboBox();
		groupTypecomboBox.setFont(new Font("Sylfaen", Font.BOLD, 14));
		groupTypecomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				 Type = groupTypecomboBox.getSelectedItem().toString();
				 fillComboBoxGroupType();
			}
		});
		groupTypecomboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Group ID", "SubGroup ID"}));
		groupTypecomboBox.setBounds(119, 104, 197, 46);
		frame.getContentPane().add(groupTypecomboBox);
		
		JLabel lblNewLabel_1_2 = new JLabel("Group Type");
		lblNewLabel_1_2.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(10, 112, 122, 29);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JButton btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home itm = new Home();
				Home.main(null);
				frame.setVisible(false);
			}
		});
		btnHome.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\home.png"));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(255, 250, 250));
		btnHome.setBounds(23, 25, 73, 49);
		frame.getContentPane().add(btnHome);
	}
	
	
	
	private void showData() {
		Connection con = DBConnect.connect();
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
			 
				String query = "Select t.ID ,s.Lecture1 ,s.Lecture2 ,s.Subject , s.GroupID , s.Tag , s.NoOfStudents , r.roomType , r.room , t.day ,t.stime , t.etime from  Session s inner join manageRooms r on  s.ID = r.SessionID inner join TimeTable t on t.sesionRoomID = r.ID ";

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
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100); 
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(60);
			table.getColumnModel().getColumn(6).setPreferredWidth(80);
			table.getColumnModel().getColumn(7).setPreferredWidth(90);
			table.getColumnModel().getColumn(8).setPreferredWidth(100);
			table.getColumnModel().getColumn(9).setPreferredWidth(80);
			table.getColumnModel().getColumn(10).setPreferredWidth(90);
			table.getColumnModel().getColumn(11).setPreferredWidth(90); 
		 
		 
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
