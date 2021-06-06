package Member2;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Common.Home;
import Member1.ConsectiveSession;
import Member1.DBConnect;
import Member4.Addlocation;
import net.proteanit.sql.DbUtils;

import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class ManageSession {

	 JFrame frame;
	private JButton btnAddSession;
	private JButton btnRefresh;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblNewLabel;
	private JTable table;
	private JScrollPane scrollPane_1;
	private JComboBox comboLect;
	private JComboBox comboTag;
	private JComboBox comboGroup;
	private JComboBox comboSubName;
	private JTextField txtStudentNo;
	private JTextField txtDuration;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JTextField txtSearchLect;
	private JComboBox comboLect2;
	private JTextField txtID;
	private JLabel lblNewLabel_8;
	private JComboBox comboSubCode;
	private JLabel lblNewLabel_9;
	private JButton btnHome;
	private JButton btnNewButton;
	private JButton btnAddSessionRooms;
	String id;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageSession window = new ManageSession();
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
	public ManageSession() {
		initialize();

		fillComboBoxTag();
		fillComboBoxLect();
		fillComboBoxGroup();
		fillComboBoxSubject();
		fillComboBoxLect2();
		fillComboBoxsubCode();
	}

	/**
	 * Initialize the contents of the frame.
	 */
		
				//getting data combo box from Tag table to get Tag Name
				public void fillComboBoxTag() {
					try {
						Connection con = DBConnect.connect();
						String query="Select * from Tags";
						PreparedStatement prepare = con.prepareStatement(query);
						ResultSet result = prepare.executeQuery();
						
						while(result.next()) {
							comboTag.addItem(result.getString("TagName"));
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				//getting data combo box from Employee table to get Lecturer Name
				public void fillComboBoxLect() {
					try {
						Connection conn = DBConnect.connect();
						String query="Select * from Employee";
						PreparedStatement prepare = conn.prepareStatement(query);
						ResultSet result = prepare.executeQuery();
						
						while(result.next()) {
							comboLect.addItem(result.getString("LecturerName"));
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				//getting data combo box from studentGroups table to get Student Group ID
				public void fillComboBoxGroup() {
					try {
						Connection conn = DBConnect.connect();
						String query="Select * from studentGroups";
						PreparedStatement prepare = conn.prepareStatement(query);
						ResultSet result = prepare.executeQuery();
						
						while(result.next()) {
							comboGroup.addItem(result.getString("SGroupID"));
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				//getting data combo box from Subject table to get Subject Name
				public void fillComboBoxSubject() {
					try {
						Connection conn = DBConnect.connect();
						String query="Select * from Subject";
						PreparedStatement prepare = conn.prepareStatement(query);
						ResultSet result = prepare.executeQuery();
						
						while(result.next()) {
							comboSubName.addItem(result.getString("SubjectName"));
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				//getting data combo box from Employee table to get Lecturer Name
				public void fillComboBoxLect2() {
					try {
						Connection conn = DBConnect.connect();
						String query="Select * from Employee";
						PreparedStatement prepare = conn.prepareStatement(query);
						ResultSet result = prepare.executeQuery();
						
						while(result.next()) {
							comboLect2.addItem(result.getString("LecturerName"));
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
				//getting data combo box from Subject table to get Subject Code
				public void fillComboBoxsubCode() {
					try {
						Connection conn = DBConnect.connect();
						String query="Select * from Subject";
						PreparedStatement prepare = conn.prepareStatement(query);
						ResultSet result = prepare.executeQuery();
						
						while(result.next()) {
							comboSubCode.addItem(result.getString("SubjectCode"));
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1128, 769);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					//Connecting Database - W.N.S. Amaranayake - IT19009728
					Connection con = DBConnect.connect();
					
					String query="select * from Session ";
					PreparedStatement pst=con.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));	
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}	
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 575, 1018, -564);
		frame.getContentPane().add(scrollPane);
		
		//Redirect to session1 frame
		btnAddSession = new JButton("ADD NEW SESSION");
		btnAddSession.setIcon(new ImageIcon(this.getClass().getResource("/next-button.png")));
		btnAddSession.setBackground(new Color(224,255,255));
		btnAddSession.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Session1 session1 = new Session1();
				session1.frame.setVisible(true);
			}
		});
		btnAddSession.setBounds(815, 14, 267, 36);
		frame.getContentPane().add(btnAddSession);
		
		
		//Refresh Data in the table
		btnRefresh = new JButton("REFRESH");
		btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/clear4.png")));
		btnRefresh.setBackground(new Color(224, 225, 255));
		btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Connecting Database - W.N.S. Amaranayake - IT19009728
					Connection con = DBConnect.connect();
					
					String query="select * from Session ";
					PreparedStatement pst=con.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));	
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}	
			}
		});
		btnRefresh.setBounds(598, 633, 394, 55);
		frame.getContentPane().add(btnRefresh);
		
		
		//Update data
		btnUpdate = new JButton("UPDATE");
		btnUpdate.setIcon(new ImageIcon(this.getClass().getResource("/update.png")));
		btnUpdate.setBackground(new Color(224, 225, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//connecting to the database
					Connection con = DBConnect.connect();	
					
					//SQL Query to update data
					String query="Update Session set  ID='"+id+"',Lecture1='"+comboLect.getSelectedItem()+"',Lecture2='"+comboLect2.getSelectedItem()
					+"',Subject='"+comboSubName.getSelectedItem()+"',GroupID='"+comboGroup.getSelectedItem()+"',Tag='"+comboTag.getSelectedItem()
					+"',NoOfStudents='"+txtStudentNo.getText()+"',Duration='"+txtDuration.getText()+"' where ID="+Integer.parseInt(id);
					PreparedStatement pst=con.prepareStatement(query);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Updated");
					pst.close();
					
					try {
    					//Connecting Database - W.N.S. Amaranayake - IT19009728
//    					Connection con = DBConnect.connect();
    					
    					String query1="select * from Session ";
    					PreparedStatement pst1=con.prepareStatement(query1);
    					ResultSet rs=pst1.executeQuery();
    					table.setModel(DbUtils.resultSetToTableModel(rs));	
    					con.close();
    				}
    				catch(Exception ex) {
    					ex.printStackTrace();
    				}
				}
				catch(Exception ea) {
					ea.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(59, 633, 162, 55);
		frame.getContentPane().add(btnUpdate);
		
		//Delete data
		btnDelete = new JButton("DELETE");
		btnDelete.setIcon(new ImageIcon(this.getClass().getResource("/remove.png")));
		btnDelete.setBackground(new Color(224, 225, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//Connecting Database 
					Connection con = DBConnect.connect();
					//SQL Query to delete data
					String query="Delete from Session where ID="+Integer.parseInt(id);
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted");
					pst.close();
					
					try {
    					//Connecting Database - W.N.S. Amaranayake - IT19009728
//    					Connection con = DBConnect.connect();
    					
    					String query1="select * from Session ";
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
		btnDelete.setBounds(284, 629, 174, 55);
		frame.getContentPane().add(btnDelete);
		
		lblNewLabel = new JLabel("Manage Session");
		lblNewLabel.setBounds(403, 44, 246, 47);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		frame.getContentPane().add(lblNewLabel);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		scrollPane_1.setBounds(495, 208, 587, 402);
		frame.getContentPane().add(scrollPane_1);
		
		//Adding mouseclicked to table
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow=table.getSelectedRow();
				
//				txtID.setText(table.getValueAt(selectedRow, 0).toString());
				 id = table.getValueAt(selectedRow, 0).toString();
				
				String ComboLecturer = table.getValueAt(selectedRow, 1).toString();
				for(int i=0; i<comboLect.getItemCount();i++) {
					if(comboLect.getItemAt(i).toString().equalsIgnoreCase(ComboLecturer)) {
						comboLect.setSelectedIndex(i);
					}
				}
				
				String ComboLecturer2 = table.getValueAt(selectedRow, 2).toString();
				for(int i=0; i<comboLect2.getItemCount();i++) {
					if(comboLect2.getItemAt(i).toString().equalsIgnoreCase(ComboLecturer2)) {
						comboLect2.setSelectedIndex(i);
					}
				}
				
				String ComboSubjectCode = table.getValueAt(selectedRow, 3).toString();
				for(int i=0; i<comboSubCode.getItemCount();i++) {
					if(comboSubCode.getItemAt(i).toString().equalsIgnoreCase(ComboSubjectCode)) {
						comboSubCode.setSelectedIndex(i);
					}
				}
				
				String ComboSubjectName = table.getValueAt(selectedRow, 4).toString();
				for(int i=0; i<comboSubName.getItemCount();i++) {
					if(comboSubName.getItemAt(i).toString().equalsIgnoreCase(ComboSubjectName)) {
						comboSubName.setSelectedIndex(i);
					}
				}
				
				String ComboGroup = table.getValueAt(selectedRow, 5).toString();
				for(int i=0; i<comboGroup.getItemCount();i++) {
					if(comboGroup.getItemAt(i).toString().equalsIgnoreCase(ComboGroup)) {
						comboGroup.setSelectedIndex(i);
					}
				}
				
				String ComboTag = table.getValueAt(selectedRow, 6).toString();
				for(int i=0; i<comboTag.getItemCount();i++) {
					if(comboTag.getItemAt(i).toString().equalsIgnoreCase(ComboTag)) {
						comboTag.setSelectedIndex(i);
					}
				}
				
				txtStudentNo.setText(table.getValueAt(selectedRow, 7).toString());
				txtDuration.setText(table.getValueAt(selectedRow, 8).toString());
			}
		});
		
		scrollPane_1.setViewportView(table);
		
		JLabel lblSelectLecturerName = new JLabel("Type here  to  Search Name");
		lblSelectLecturerName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSelectLecturerName.setBounds(506, 159, 172, 36);
		frame.getContentPane().add(lblSelectLecturerName);
		
		comboLect = new JComboBox();
		comboLect.setBounds(59, 234, 355, 22);
		frame.getContentPane().add(comboLect);
		
		comboTag = new JComboBox();
		comboTag.setBounds(59, 281, 355, 22);
		frame.getContentPane().add(comboTag);
		
		comboGroup = new JComboBox();
		comboGroup.setBounds(59, 329, 355, 22);
		frame.getContentPane().add(comboGroup);
		
		comboSubName = new JComboBox();
		comboSubName.setBounds(59, 377, 355, 22);
		frame.getContentPane().add(comboSubName);
		
		txtStudentNo = new JTextField();
		txtStudentNo.setColumns(10);
		txtStudentNo.setBounds(59, 525, 355, 22);
		frame.getContentPane().add(txtStudentNo);
		
		txtDuration = new JTextField();
		txtDuration.setColumns(10);
		txtDuration.setBounds(59, 569, 355, 22);
		frame.getContentPane().add(txtDuration);
		
		JLabel lblNewLabel_1 = new JLabel("Lecturer");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(62, 206, 89, 22);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Tag");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(59, 258, 89, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Group");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setBounds(59, 304, 89, 22);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Subject Name");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_4.setBounds(59, 354, 118, 22);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Lecturer Name");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_5.setBounds(59, 402, 118, 22);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Number of Students");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_6.setBounds(62, 500, 159, 22);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("Duration (Hrs)");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_7.setBounds(59, 545, 127, 22);
		frame.getContentPane().add(lblNewLabel_7);
		
		//Search Data
		txtSearchLect = new JTextField();
		txtSearchLect.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					//Connecting Database - W.N.S. Amaranayake - IT19009728
					Connection con = DBConnect.connect();
					
					String query="select * from Session where Lecture1=?";
					PreparedStatement pst=con.prepareStatement(query);
					pst.setString(1, txtSearchLect.getText());
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));	
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}	 
			}
		});
		txtSearchLect.setColumns(10);
		txtSearchLect.setBounds(688, 168, 394, 27);
		frame.getContentPane().add(txtSearchLect);
		
		comboLect2 = new JComboBox();
		comboLect2.setBounds(59, 424, 355, 22);
		frame.getContentPane().add(comboLect2);
		
//		txtID = new JTextField();
//		txtID.setColumns(10);
//		txtID.setBounds(59, 183, 355, 22);
//		frame.getContentPane().add(txtID);
		
//		lblNewLabel_8 = new JLabel("ID");
//		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
//		lblNewLabel_8.setBounds(62, 165, 42, 16);
//		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblID = new JLabel("  ");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID.setBounds(284, 148, 74, 27);
		frame.getContentPane().add(lblID);
		
		comboSubCode = new JComboBox();
		comboSubCode.setBounds(59, 472, 355, 22);
		frame.getContentPane().add(comboSubCode);
		
		lblNewLabel_9 = new JLabel("Subject Code");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_9.setBounds(59, 449, 111, 22);
		frame.getContentPane().add(lblNewLabel_9);
		
		btnHome = new JButton("");
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
		btnHome.setBounds(59, 42, 73, 49);
		frame.getContentPane().add(btnHome);
		
		btnNewButton = new JButton("ADD JOIN SESSION");
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/next-button.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsectiveSession itm = new ConsectiveSession();
				ConsectiveSession.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(224,255,255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(815, 63, 267, 36);
		frame.getContentPane().add(btnNewButton);
		
		btnAddSessionRooms = new JButton("ADD SESSION ROOMS");
		btnAddSessionRooms.setIcon(new ImageIcon(this.getClass().getResource("/next-button.png")));
		btnAddSessionRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addlocation itm = new Addlocation();
				Addlocation.main(null);
				frame.setVisible(false);
			}
		});
		btnAddSessionRooms.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddSessionRooms.setBackground(new Color(224, 255, 255));
		btnAddSessionRooms.setBounds(815, 107, 267, 36);
		frame.getContentPane().add(btnAddSessionRooms);
		
	
//		fillComboBox2();
		fillComboBoxTag();
		fillComboBoxGroup();
		fillComboBoxLect();
		fillComboBoxSubject();
		fillComboBoxLect2();
		fillComboBoxsubCode();
	}
}
