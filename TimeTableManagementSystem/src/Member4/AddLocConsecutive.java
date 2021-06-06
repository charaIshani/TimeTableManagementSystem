package Member4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Common.Home;
import Member1.ConsectiveSession;
import Member1.DBConnect;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AddLocConsecutive {

	private JFrame frame;
	private JTable table;
	private JComboBox RoomTypeBox;
	private JComboBox RoomNoBox;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLocConsecutive window = new AddLocConsecutive();
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
	public AddLocConsecutive() {
		initialize();
		fillComboBox();
		fillComboBox1();
		fillComboBox2();
	}
	
	public String getCSessionRoonID;
	
	String getLec1;
	String csess;
	String csid;
	String csname;
	String CID;
	String getID;
	
public void fillComboBox() {
		
		try {
			  String query ="select * from ConsecutiveSession ";
			  Connection con = DBConnect.connect();
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  comboBox.setModel(new DefaultComboBoxModel(new String[] {" -------------Select ConsecutiveSession In Here------------"}));
				 
			  while(rs.next()) {
				  csid = rs.getString("ConsecutiveSesID");
				  csname=rs.getString("ConsecutiveSes");
				   
				  String ConsecutiveSes=rs.getString("ConsecutiveSes");
				  comboBox.addItem(csid +"-"+csname);		  
			}
			
		}catch (Exception e){
			 e.printStackTrace();
			
		}
		
	}
	
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
	
public void getCSRoomTableID() {
		
		try {
			
			Connection con = DBConnect.connect();
			  String query ="select ConsecutiveSesID from ConsecutiveSession where ConsecutiveSesID =  '"+CID+"' ";
			 
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  while(rs.next()) {	  
				  System.out.println("Manage Room ID");
				  getCSessionRoonID = rs.getString("ConsecutiveSesID");
				  System.out.println(getCSessionRoonID);
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
		frame.setBounds(100, 100, 1126, 775);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			 
			@Override
			public void windowOpened(WindowEvent e) {
				showData();
				
			
				
			}
			});
		
		JLabel lblNewLabel = new JLabel("Manage Rooms For Consecutive Sessions");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(278, 32, 624, 77);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					getID = comboBox.getSelectedItem().toString();
					System.out.println(getID);
					CID = getID.split("-")[0]; 
					System.out.println(CID);
					
					getCSRoomTableID();
					
					
					
				}
				
//				SID.setText(sid);
//				System.out.println(SID);
			}
		});
		
		comboBox.setBounds(30, 133, 371, 45);
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
		});
		RoomTypeBox.setBounds(449, 133, 314, 45);
		frame.getContentPane().add(RoomTypeBox);
		
		RoomNoBox = new JComboBox();
		RoomNoBox.setBounds(775, 133, 284, 45);
		frame.getContentPane().add(RoomNoBox);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\add.png"));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String roomType = (String)RoomTypeBox.getSelectedItem();
				String roomName = (String)RoomNoBox.getSelectedItem();
				
				try { 
					 
					 
					Connection con = DBConnect.connect();
 
					 
					 String query = "INSERT INTO ConsecutiveSessionRoom (ConsecutiveSes,roomType,roomName ) values('" + getCSessionRoonID + "',  '"+ roomType +"' ,'"+ roomName +"')";
			           
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
		btnSave.setBounds(30, 210, 481, 45);
		frame.getContentPane().add(btnSave);
		
		JButton btnNewButton_1_1 = new JButton("DELETE");
		btnNewButton_1_1.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\remove.png"));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = DBConnect.connect();
					//SQL Query to delete data
					String query="Delete from ConsecutiveSessionRoom where id="+Integer.parseInt(getCSessionRoonID);
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted");
					pst.close();
					
					try {
    					
    					String query1="select * from ConsecutiveSessionRoom ";
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
		btnNewButton_1_1.setBounds(620, 208, 449, 48);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
			}
		});
		scrollPane_1.setBounds(30, 290, 1052, 413);
		frame.getContentPane().add(scrollPane_1);
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				
//				int selectedRow=table.getSelectedRow();
//				 id = table.getValueAt(selectedRow, 0).toString();
//				System.out.println(id);
//				
//				String CombobOX = table.getValueAt(selectedRow, 3).toString();
//				for(int i=0; i<comboBox.getItemCount();i++) {
//					if(comboBox.getItemAt(i).toString().equalsIgnoreCase(CombobOX)) {
//						comboBox.setSelectedIndex(i);
//					}
//				}
//				
//				String CombobOX2 = table.getValueAt(selectedRow, 1).toString();
//				for(int i=0; i<RoomTypeBox.getItemCount();i++) {
//					if(RoomTypeBox.getItemAt(i).toString().equalsIgnoreCase(CombobOX2)) {
//						RoomTypeBox.setSelectedIndex(i);
//					}
//				}
//				
//				String CombobOX3 = table.getValueAt(selectedRow, 2).toString();
//				for(int i=0; i<RoomNoBox.getItemCount();i++) {
//					if(RoomNoBox.getItemAt(i).toString().equalsIgnoreCase(CombobOX3)) {
//						RoomNoBox.setSelectedIndex(i);
//					}
//				}
//
//				
//			}
		});
		
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
		btnHome.setBounds(30, 60, 73, 49);
		frame.getContentPane().add(btnHome);
		
		JButton btnConsectiveSession = new JButton("CONSECTIVE SESSION");
		btnConsectiveSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsectiveSession itm = new ConsectiveSession();
				ConsectiveSession.main(null);
				frame.setVisible(false);
			
			}
		});
		btnConsectiveSession.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\previous.png"));
		btnConsectiveSession.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnConsectiveSession.setBackground(new Color(224, 255, 255));
		btnConsectiveSession.setBounds(12, 7, 233, 40);
		frame.getContentPane().add(btnConsectiveSession);
		
	}
	
	private void showData() {
		Connection con = DBConnect.connect();
		DefaultTableModel model = new DefaultTableModel();
	
	 
		try {
			
			model.addColumn("ID");
			model.addColumn("Consecutive Session"); 
			model.addColumn("Room Type");
			model.addColumn("Room Name");

		 
				String query = "Select  r.ID ,s.ConsecutiveSes , r.roomType , r.roomName from  ConsecutiveSession s inner join ConsecutiveSessionRoom r on  s.ConsecutiveSesID = r.ConsecutiveSes  ";
				
//				System.out.println( );
				Statement sta = con.createStatement();
				ResultSet rs = sta.executeQuery(query);
				
				while(rs.next())
				{
					model.addRow(new Object[] {
							rs.getString("ID"),
							rs.getString("ConsecutiveSes"), 
							rs.getString("roomType"),
							rs.getString("roomName")
					});
				}
				rs.close();
				sta.close();
				 
		 
		 
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(1).setPreferredWidth(140);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(100); 
		 
		 
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
