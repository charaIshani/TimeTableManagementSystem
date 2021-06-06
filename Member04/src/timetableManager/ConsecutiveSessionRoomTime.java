package timetableManager;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ConsecutiveSessionRoomTime {

	private JFrame frame;
	private JTextField stime;
	private JTextField etime;
	private JComboBox cscomboBox;
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
					ConsecutiveSessionRoomTime window = new ConsecutiveSessionRoomTime();
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
	public ConsecutiveSessionRoomTime() {
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
	
	String csid;
	String csname;
	
public void fillComboBoxGroupType() {
		
		try {
			
			System.out.println(Type);
			
		 
		 
				cscomboBox.removeAllItems();
				Connection con = DBConnecter.connect();
				  String query ="select * from ConsecutiveSession ";
				 
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  while(rs.next()) {	  
					  String cs = rs.getString("ConsecutiveSesID");
					  String csname = rs.getString("ConsecutiveSes");
					  System.out.println(cs);
					  cscomboBox.addItem(cs  +"-"+ csname); 
				}
				  con.close();
			 
			 
			 
			
			
			   
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
			Connection con = DBConnecter.connect();
			  String query ="select * from ConsecutiveSessionRoom where ConsecutiveSes  = '"+Session+"'   ";
			  
//				String query = "Select t.ID ,s.Lecture1 ,s.Lecture2 ,s.Subject , s.GroupID , s.Tag , s.NoOfStudents , r.roomType , r.room , t.day ,t.stime , t.etime from  Session s inner join manageRooms r on  s.ID = r.SessionID inner join TimeTable t on t.sesionRoomID = r.ID ";

			 
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  while(rs.next()) {
				  csid = rs.getString("ID");
				  csname=rs.getString("ConsecutiveSes");
				   lec2=rs.getString("roomType");
				   subCode =rs.getString("roomName"); 
 	  
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
			
			Connection con = DBConnecter.connect();
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
		frame.setBounds(100, 100, 1086, 769);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); 
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				showData();
				
			
				
			}
			});
		
		JLabel lblNewLabel = new JLabel("Manage  Consecutive Session - Room - Time");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 19));
		lblNewLabel.setBounds(332, 25, 427, 46);
		frame.getContentPane().add(lblNewLabel);
		
		 cscomboBox = new JComboBox();
		 cscomboBox.setFont(new Font("Sylfaen", Font.BOLD, 14));
		 cscomboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					Session = cscomboBox.getSelectedItem().toString();
					System.out.println(Session);
					
//					groupIDcomboBox.removeAllItems();
					fillComboBoxSession();
				}
				
			}
		});
		cscomboBox.setBounds(210, 101, 197, 46);
		frame.getContentPane().add(cscomboBox);
		
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
		sessioncomboBox.setBounds(655, 101, 319, 46);
		frame.getContentPane().add(sessioncomboBox);
		
		 daycomboBox = new JComboBox();
		 daycomboBox.setFont(new Font("Sylfaen", Font.BOLD, 14));
		daycomboBox.setModel(new DefaultComboBoxModel(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sundayy"}));
		daycomboBox.setBounds(116, 161, 106, 46);
		frame.getContentPane().add(daycomboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Consecutive Session ");
		lblNewLabel_1.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblNewLabel_1.setBounds(23, 109, 177, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Consecutive Session Room");
		lblNewLabel_1_1.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(430, 109, 227, 29);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Day");
		lblNewLabel_1_1_1.setFont(new Font("Sitka Heading", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(62, 169, 122, 29);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 297, 1038, 322);
		frame.getContentPane().add(scrollPane_1);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date = daycomboBox.getSelectedItem().toString();
				StartTime = stime.getText().toString();
				EndTime = etime.getText().toString();
				
				try { 
					 
					 
					 Connection con = DBConnecter.connect();
 
					 
					 	String query = "INSERT INTO csTimeTable (csesionRoomID,day,stime , etime ) values('" + getSessionRoonID + "',  '"+ Date +"' ,'"+ StartTime +"','"+ EndTime +"')";
	           
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
		btnSave.setBounds(902, 161, 116, 46);
		frame.getContentPane().add(btnSave);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(902, 218, 116, 46);
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
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
	}
	
	
	
	private void showData() {
		Connection con = DBConnecter.connect();
		DefaultTableModel model = new DefaultTableModel();
	
	 
		try {
			
			model.addColumn("ID");
			model.addColumn("Consecutive Session");
			 
			model.addColumn("Room Type");
			model.addColumn("Room Name");
			model.addColumn("Day");
			model.addColumn("Start Time");
			model.addColumn("End Time");
			 
				String query = "Select t.ID ,s.ConsecutiveSes ,  r.roomType , r.room , t.day ,t.stime , t.etime from  ConsecutiveSession s inner join ConsecutiveSessionRoom r on  s.ID = r.SessionID inner join csTimeTable t on t.csesionRoomID = r.ID ";

				Statement sta = con.createStatement();
				ResultSet rs = sta.executeQuery(query);
				
				while(rs.next())
				{
					model.addRow(new Object[] {
							rs.getString("ID"),
							rs.getString("ConsecutiveSes"),
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
			table.getColumnModel().getColumn(1).setPreferredWidth(140); 
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

