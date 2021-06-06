package Member4;

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

import Common.Home;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

import Member1.DBConnect;
import Member2.ManageSession;

import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPanel;


public class Addlocation {

	private JFrame frame;
	private JTable table;
	private JTextField capStd;
	private JComboBox lec1Box;
	private JComboBox lec2Box;
	private JComboBox sessionBox;
	private JComboBox tagBox;
	private JComboBox groupBox;
	private JComboBox roomNameBox;
	private JComboBox roomTypeBox;
	private JComboBox subCodeBox;
	private JLabel txtSubName;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addlocation window = new Addlocation();
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
	public Addlocation() {
		initialize();
		fillComboBoxRoomType();
		fillComboBoxRoomName();
		fillComboBoxSubName(subName);
		fillComboBoxSession();
	}
	
	String type;
	String getLec1;
	String sid;
	String lec1;
	String lec2;
	String subCode;
	String groupid;
	String tag;
	String subName ;
	
	
	public void fillComboBoxSession() {
		
		try {
			
			 String query ="select * from Session";
			 String query1 ="------------";
			 Connection con = DBConnect.connect();
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  sessionBox.setModel(new DefaultComboBoxModel(new String[] {"                     ------- Select Session In Here -------           "}));
			  
			  while(rs.next()) {
				   sid = rs.getString("ID");
				   lec1=rs.getString("Lecture1");
				   lec2=rs.getString("Lecture2");
				   subCode =rs.getString("SubjectCode");
				      subName =rs.getString("Subject");
				   groupid =rs.getString("GroupID");
				   tag=rs.getString("Tag");
				  String noStd=rs.getString("NoOfStudents");
				  
				  
				  
				  sessionBox.addItem(lec1 +"-"+ lec2 +"-"+ subCode +"-"+ subName +"-"+ groupid +"-"+ tag +"-"+ noStd );
//				  sessionBox.getString("-------------");
				  
					  
			} 
			 
			 
			
		}catch (Exception e){
			 e.printStackTrace();
			
		}finally {
        	try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        }
		
	}

	public void fillComboBoxLec1(String l1) {
		
		try {
//			System.out.println(lec1);
			
			  lec1Box.getModel().setSelectedItem(lec1);
			  
			  String query ="select LecturerName from Employee group by LecturerName";
			 
			  Connection con = DBConnect.connect();
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  lec1Box.removeAllItems();
			  lec1Box.addItem(l1); 
			  while(rs.next()) {
				 
				  String lec12=rs.getString("LecturerName");
				  lec1Box.addItem(lec12); 
				  
			}
			 
			
		}catch (Exception e){
			 e.printStackTrace();
			
		}finally {
        	try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        }
		
	}
	public void fillComboBoxLec2(String l2) {
			
			try {
	//			System.out.println(lec1);
				
				  lec2Box.getModel().setSelectedItem(lec2);
				  
				  String query ="select LecturerName from Employee group by LecturerName";
				  Connection con = DBConnect.connect();
				  
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  lec2Box.removeAllItems();
				  lec2Box.addItem(l2); 
				  while(rs.next()) {
	 
					 
					  String lec12=rs.getString("LecturerName");
					  
					  lec2Box.addItem(lec12); 
					  
				}  
				
			}catch (Exception e){
				 e.printStackTrace();
				
			}
			finally {
            	try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            }
			
		}
	
	public void fillComboBoxSubCode(String sudc) {
			
			try {
	//			System.out.println(lec1);
				
				subCodeBox.getModel().setSelectedItem(subCode);
				  
				  String query ="select SubjectCode from Subject group by SubjectCode";
				  Connection con = DBConnect.connect();
			 
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  subCodeBox.removeAllItems();
				  subCodeBox.addItem(sudc); 
				  while(rs.next()) { 
					 
					  String subCO=rs.getString("SubjectCode");
					  subCodeBox.addItem(subCO); 
					  
				}
				 
				
			}catch (Exception e){
				 e.printStackTrace();
				
			}
			finally {
            	try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
			
		}
	String subjectCo ;
	
	public void fillComboBoxSubName(String subName) {
		
		try {

			   String query ="select SubjectName from Subject where SubjectCode =  '"+subjectCo+"' ";
			 
			   Connection con = DBConnect.connect();
			  PreparedStatement pst =con.prepareStatement(query);
			  ResultSet rs = pst.executeQuery();
			  
			  while(rs.next()) { 
				 
				  String subCO=rs.getString("SubjectCode");
//				  txtSubName.addItem(subCO); 
				  
			}		
		}catch (Exception e){
			 e.printStackTrace();
			
		}finally {
        	try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
        }	
	}
	

	public void fillComboBoxGroup(String grp) {
			
			try {
	//			System.out.println(lec1);
				
				groupBox.getModel().setSelectedItem(groupid);
				Connection con = DBConnect.connect();
				  String query ="select GroupNo from studentGroups group by GroupNo";
				  String query1 ="select SubGroupNo from studentGroups group by SubGroupNo";
				 
			 
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  PreparedStatement pst1 =con.prepareStatement(query1);
				  ResultSet rs1 = pst1.executeQuery();
				  
				  groupBox.removeAllItems();
				  groupBox.addItem(grp); 
				  
				  while(rs.next()) { 
					  
					  String group1 =rs.getString("GroupNo");
					  String group2 =rs1.getString("SubGroupNo");
					  
					  groupBox.addItem(group1);
					  groupBox.addItem(group2); 
					  
				}
			 
			}catch (Exception e){
				 e.printStackTrace();
				
			}finally {
            	try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            }
			
		}

	public void fillComboBoxTag(String tg) {
			
			try {
	//			System.out.println(lec1);
				
				  lec2Box.getModel().setSelectedItem(tag);
				  Connection con = DBConnect.connect();
				  String query ="select TagName from Tags group by TagName";
				 
				  
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  tagBox.removeAllItems();
				  tagBox.addItem(tg); 
				  while(rs.next()) {
	//				  String lec11=rs.getString(getLec1);
					 
					  String lec12=rs.getString("TagName");
					  tagBox.addItem(lec12); 
					  
				} 
				
			}catch (Exception e){
				 e.printStackTrace();
				
			}
			finally {
            	try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            }
			
		}
	
		public void fillComboBoxRoomType() {
				
				try {
					Connection con = DBConnect.connect();
					  String query ="select roomType from location group by roomType";
					 
					  PreparedStatement pst =con.prepareStatement(query);
					  ResultSet rs = pst.executeQuery();
					  
					  roomTypeBox.setModel(new DefaultComboBoxModel(new String[] {"Select Room Type In Here"}));
					  
					  while(rs.next()) {	  
						  String roomType=rs.getString("roomType");
						  roomTypeBox.addItem(roomType); 
					}
					   
				}catch (Exception e){
					 e.printStackTrace();
					
				}finally {
                	try {
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                }
				
			}
		
		String typeRoom ;
		private void fillComboBoxRoomName() {
			 String query ="select roomName from location where roomType = '"+typeRoom+"' ";
//			 Connection con = DBConnecter.connect();
			
			try {
				 
				Connection con = DBConnect.connect();
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  while(rs.next()) {
					  
					  String roomName=rs.getString("roomName");
					  roomNameBox.addItem(roomName);
					 
				}   
				  
			}catch (Exception e){
				 
				 e.printStackTrace();
				
			}finally {
            	try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            }
		
		}

 
	/**
	 * Initialize the contents of the frame.
	 */
	
		Connection con = DBConnect.connect();
	private JButton btnManageSession;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lecturer1 ;
	private JLabel lecturer2 ;
	private JLabel subjectName ;
	private JLabel subjectCode ;
	private JLabel groupID ;
	private JLabel tagType ; 
	
	private JLabel sesID ;
	private JLabel SID;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JLabel lblNewLabel_13;
	private JLabel lblNewLabel_14;
	private JPanel panel_1;
	private JLabel lecturer1_1;
	private JPanel panel_2;
	private JLabel lecturer1_2;
	private JPanel panel_3;
	private JLabel subjectCode_1;
	private JLabel lecturer1_3;
	private JPanel panel_4;
	private JLabel subjectCode_2;
	private JLabel subjectName_1;
	private JLabel lecturer1_4;
	private JPanel panel_5;
	private JLabel subjectCode_3;
	private JLabel groupID_1;
	private JLabel subjectName_2;
	private JLabel lecturer1_5;
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 1127, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			
			
			
			
			
			@Override
			public void windowOpened(WindowEvent e) {
				showData();
				
			
				
			}
			});

		
		JLabel lblNewLabel = new JLabel("Manage Session Rooms");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(358, 29, 355, 57);
		frame.getContentPane().add(lblNewLabel);
		
	 
		sessionBox = new JComboBox();
		sessionBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		sessionBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					getLec1 = sessionBox.getSelectedItem().toString();
				
					System.out.println(getLec1);
					String l1 = getLec1.split("-")[0]; 
					String l2 = getLec1.split("-")[1]; 
					String sudc = getLec1.split("-")[2]; 
					String subNam = getLec1.split("-")[3]; 
					String grp = getLec1.split("-")[4]; 
					String tg = getLec1.split("-")[5]; 
					
					System.out.print(l1);
//					
//					System.out.println();
//					lec1Box.removeAllItems();
//					lec2Box.removeAllItems();
//					subCodeBox.removeAllItems();
//					groupBox.removeAllItems();
//					tagBox.removeAllItems();
//					
//					fillComboBoxLec1(l1);
//					fillComboBoxLec2(l2);
//					fillComboBoxSubCode(sudc);
//					fillComboBoxGroup(grp);
//					fillComboBoxTag(tg);
					 
					SID.setText(sid);
					lecturer1.setText(l1);
					lecturer2.setText(l2);
					subjectCode.setText(sudc);
					subjectName.setText(subNam);
					groupID.setText(grp);
					tagType.setText(tg);
					
				}	
			}
		});
		sessionBox.setBounds(58, 134, 991, 40);
		frame.getContentPane().add(sessionBox);
//		
//		lec1Box = new JComboBox(); lec1Box.setBounds(55, 187, 111, 45);
//		frame.getContentPane().add(lec1Box);
//		
//		
//		lec2Box = new JComboBox();
//		lec2Box.setBounds(190, 187, 111, 45);
//		frame.getContentPane().add(lec2Box);
//		
//		subCodeBox = new JComboBox();
//		subCodeBox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//				
//				if(e.getStateChange() == ItemEvent.SELECTED)
//				{
//					subjectCo = roomTypeBox.getSelectedItem().toString();
//					roomNameBox.removeAllItems();
//					fillComboBoxRoomName();
//				}	
//			}
//		});
//		subCodeBox.setBounds(332, 187, 111, 45);
//		frame.getContentPane().add(subCodeBox);
		
		roomTypeBox = new JComboBox();
		roomTypeBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					typeRoom = roomTypeBox.getSelectedItem().toString();
					
					roomNameBox.removeAllItems();
					fillComboBoxRoomName();
				}	
			}
		});
		roomTypeBox.setBounds(58, 295, 333, 45);
		frame.getContentPane().add(roomTypeBox);
		
		roomNameBox = new JComboBox();
		roomNameBox.setBounds(419, 295, 294, 45);
		frame.getContentPane().add(roomNameBox);
		
		JButton btnNewButton = new JButton("ADD ROOM");
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnNewButton.setFont(new Font("Sitka Small", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				 
				String Sid = sid;
				String roomType = (String)roomTypeBox.getSelectedItem();
				String roomName = (String)roomNameBox.getSelectedItem();
//				String Lecturer2 = buldingName.getText();
//				String floornum = floorNum.getText();	
//				String cap = capC.getText();
				
				System.out.print("ID: " + Sid + "RoomType: " + roomType + "RoomName: " + roomName);
				System.out.println();
//				System.out.print("subName = " + SubjectCode);
			 
				
				
				 try { 
					 
					 
					 Connection con = DBConnect.connect();


//					 String subName = "Select SubjectName from Subject where subjectCode = 'SubjectCode' ";
//					  Statement sta1 = con.createStatement();
//	                    int y = sta1.executeUpdate(subName);
//	                    System.out.println(y);
					 
					 String query = "INSERT INTO manageRooms (SessionID,roomType,room ) values('" + Sid + "',  '"+ roomType +"' ,'"+ roomName +"')";
	           
	                    Statement sta = con.createStatement();
	                    int x = sta.executeUpdate(query);
	                    System.out.println(x);
	                    con.close();	                   
	                    if (x == 0) {
	                        JOptionPane.showMessageDialog(btnNewButton,"This is alredy exist");
	                    } else {
	                        JOptionPane.showMessageDialog(btnNewButton,
	                            "Welcome, Your account is sucessfully created");
	                        showData();
	                    }
	                    
	                    
	                } catch (Exception exception) {
	                    exception.printStackTrace();
	                    System.out.println("Error: " + exception.getMessage());
	                } 
//				 finally {
//	                	try {
//							con.close();
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//	                	
//	                }

				
			}
		});
		btnNewButton.setBounds(755, 293, 294, 48);
		frame.getContentPane().add(btnNewButton);


		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(36, 372, 1010, 314);
		frame.getContentPane().add(scrollPane_1);
		
//		groupBox = new JComboBox();
//		groupBox.setBounds(638, 187, 111, 45);
//		frame.getContentPane().add(groupBox);
//		
//		capStd = new JTextField();
//		capStd.setColumns(10);
//		capStd.setBounds(154, 295, 111, 45);
//		frame.getContentPane().add(capStd);
//		
//		tagBox = new JComboBox();
//		tagBox.setBounds(780, 187, 111, 45);
//		frame.getContentPane().add(tagBox);
		
		txtSubName = new JLabel("");
		txtSubName.setFont(new Font("Sitka Display", Font.BOLD, 17));
		txtSubName.setBounds(468, 145, 138, 29);
		frame.getContentPane().add(txtSubName);
		
		
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
//		btnHome.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Home itm = new Home();
//				Home.main(null);
//				frame.setVisible(false);
//			}
//			
//		});
		btnHome.setIcon(new ImageIcon(this.getClass().getResource("/home.png")));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(255, 250, 250));
		btnHome.setBounds(55, 60, 73, 49);
		frame.getContentPane().add(btnHome);
		
		btnManageSession = new JButton("MANAGE SESSION");
		btnManageSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageSession itm = new ManageSession();
				ManageSession.main(null);
				frame.setVisible(false);
			}
		});
//		btnManageSession.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				ManageSession itm = new ManageSession();
//				ManageSession.main(null);
//				frame.setVisible(false);
//			}
//		});
		btnManageSession.setIcon(new ImageIcon(this.getClass().getResource("/previous.png")));
		btnManageSession.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnManageSession.setBackground(new Color(224, 255, 255));
		btnManageSession.setBounds(12, 7, 203, 40);
		frame.getContentPane().add(btnManageSession);
		
		lblNewLabel_6 = new JLabel("Room Type");
		lblNewLabel_6.setFont(new Font("Sylfaen", Font.BOLD, 17));
		lblNewLabel_6.setBounds(58, 283, 111, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Room No");
		lblNewLabel_7.setFont(new Font("Sylfaen", Font.BOLD, 17));
		lblNewLabel_7.setBounds(419, 283, 91, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		lblNewLabel_9 = new JLabel("Lecturer 1");
		lblNewLabel_9.setBounds(58, 244, 138, 14);
		frame.getContentPane().add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("Lecturer 1");
		lblNewLabel_10.setBounds(214, 244, 138, 14);
		frame.getContentPane().add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("Subject Code");
		lblNewLabel_11.setBounds(374, 244, 91, 14);
		frame.getContentPane().add(lblNewLabel_11);
		
		lblNewLabel_12 = new JLabel("Subject Name");
		lblNewLabel_12.setBounds(539, 244, 138, 14);
		frame.getContentPane().add(lblNewLabel_12);
		
		lblNewLabel_13 = new JLabel("Group ID");
		lblNewLabel_13.setBounds(713, 244, 91, 14);
		frame.getContentPane().add(lblNewLabel_13);
		
		lblNewLabel_14 = new JLabel("Tag");
		lblNewLabel_14.setBounds(897, 244, 61, 14);
		frame.getContentPane().add(lblNewLabel_14);
		
		JPanel panel = new JPanel();
		panel.setBounds(55, 191, 138, 51);
		frame.getContentPane().add(panel);
		
		 lecturer1 = new JLabel(" ");
		 panel.add(lecturer1);
		 lecturer1.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 
		 panel_1 = new JPanel();
		 panel_1.setBounds(213, 191, 139, 51);
		 frame.getContentPane().add(panel_1);
		 
		   lecturer2 = new JLabel(" ");
		   panel_1.add(lecturer2);
		   lecturer2.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 
		 lecturer1_1 = new JLabel(" ");
		 lecturer1_1.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 panel_1.add(lecturer1_1);
		 
		 panel_2 = new JPanel();
		 panel_2.setBounds(364, 191, 152, 51);
		 frame.getContentPane().add(panel_2);
		 
		 subjectCode = new JLabel(" ");
		 panel_2.add(subjectCode);
		 subjectCode.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 
		 lecturer1_2 = new JLabel(" ");
		 lecturer1_2.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 panel_2.add(lecturer1_2);
		 
		 panel_3 = new JPanel();
		 panel_3.setBounds(539, 191, 145, 51);
		 frame.getContentPane().add(panel_3);
		 
		 lecturer1_3 = new JLabel(" ");
		 lecturer1_3.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 panel_3.add(lecturer1_3);
		 
		 subjectCode_1 = new JLabel(" ");
		 subjectCode_1.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 panel_3.add(subjectCode_1);
		 
		   subjectName = new JLabel(" ");
		   panel_3.add(subjectName);
		   subjectName.setBackground(new Color(255, 255, 204));
		   subjectName.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 
		 panel_4 = new JPanel();
		 panel_4.setBounds(713, 193, 138, 49);
		 frame.getContentPane().add(panel_4);
		 
		  groupID = new JLabel(" ");
		  panel_4.add(groupID);
		  groupID.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 
		 lecturer1_4 = new JLabel(" ");
		 lecturer1_4.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 panel_4.add(lecturer1_4);
		 
		 subjectCode_2 = new JLabel(" ");
		 panel_4.add(subjectCode_2);
		 subjectCode_2.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 
		 panel_5 = new JPanel();
		 panel_5.setBounds(897, 193, 152, 49);
		 frame.getContentPane().add(panel_5);
		 
		  tagType = new JLabel(" ");
		  panel_5.add(tagType);
		  tagType.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 
		 subjectCode_3 = new JLabel(" ");
		 subjectCode_3.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 panel_5.add(subjectCode_3);
		 
		 SID = new JLabel(" ");
		 panel_5.add(SID);
		 SID.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 
		 groupID_1 = new JLabel(" ");
		 groupID_1.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 panel_5.add(groupID_1);
		 
		 lecturer1_5 = new JLabel(" ");
		 lecturer1_5.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 panel_5.add(lecturer1_5);
		 
		 subjectName_1 = new JLabel(" ");
		 subjectName_1.setBounds(678, 258, 35, 22);
		 frame.getContentPane().add(subjectName_1);
		 subjectName_1.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 subjectName_1.setBackground(new Color(255, 255, 204));
		 
		 subjectName_2 = new JLabel(" ");
		 subjectName_2.setBounds(876, 220, 47, 22);
		 frame.getContentPane().add(subjectName_2);
		 subjectName_2.setFont(new Font("Sylfaen", Font.BOLD, 16));
		 subjectName_2.setBackground(new Color(255, 255, 204));
		
//		lblNewLabel_8 = new JLabel("Subject Name");
//		lblNewLabel_8.setFont(new Font("Sylfaen", Font.BOLD, 17));
//		lblNewLabel_8.setBounds(478, 243, 121, 14);
//		frame.getContentPane().add(lblNewLabel_8);
		
		
	}
	
	private void showData() {
		Connection con = DBConnect.connect();
		DefaultTableModel model = new DefaultTableModel();
	
	 
		try {
			
			model.addColumn("ID");
			model.addColumn("Lecturer 1");
			model.addColumn("Lecturer 2");
			model.addColumn("SubjectCode");
			model.addColumn("Subject");
			model.addColumn("GroupID");
			model.addColumn("Tag");
			model.addColumn("NoOfStudents");
			model.addColumn("Room Type");
			model.addColumn("Room Name");

			String query = "Select  r.ID ,s.Lecture1 ,s.Lecture2 ,s.SubjectCode,s.Subject , s.GroupID , s.Tag , s.NoOfStudents , r.roomType , r.room from  Session s inner join manageRooms r on  s.ID = r.SessionID  ";
			
//			System.out.println( );
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(query);
			
				
				while(rs.next())
				{
					model.addRow(new Object[] {
							rs.getString("ID"),
							rs.getString("Lecture1"),
							rs.getString("Lecture2"),
							rs.getString("SubjectCode"),
							rs.getString("Subject"),
							rs.getString("GroupID"),
							rs.getString("Tag"),
							rs.getString("NoOfStudents"),
							rs.getString("roomType"),
							rs.getString("room")
					});
				}
				rs.close();
				sta.close();
				 
		 
		 
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setPreferredWidth(100);
			table.getColumnModel().getColumn(7).setPreferredWidth(100);
			table.getColumnModel().getColumn(8).setPreferredWidth(100);
			table.getColumnModel().getColumn(9).setPreferredWidth(100); 
		 
		 
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
