package Member02;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Common.Home;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class Session2 {

	JFrame frame;
	private JTable table;
	private JTextField noOfStudents;
	private JTextField durationHrs;
	private JComboBox comboSubject;
	private JComboBox comboGroup;
	private JComboBox comboSubCode;
	
	static String lecturer;
	static String tag;
	static String selected_Lecturer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Session2 window = new Session2(lecturer, tag, selected_Lecturer);
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
	public Session2(String lecturer, String tag, String selected_lecturer) {
		initialize();
		fillComboBox();
		fillComboBox2();
		fillComboBoxsubCode();
		
		this.lecturer = lecturer;
		this.tag = tag;
		this.selected_Lecturer = selected_lecturer;
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	//getting data combo box from studentGroup table to get Student group ID
	public void fillComboBox() {
		try {
			Connection con = DBConnect.connect();
			String query="Select * from studentGroups";
			PreparedStatement prepare = con.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				comboGroup.addItem(result.getString("SGroupID"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//getting data combo box from Subject table to get Subject Name
	public void fillComboBox2() {
		try {
			Connection conn = DBConnect.connect();
			String query="Select * from Subject";
			PreparedStatement prepare = conn.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				comboSubject.addItem(result.getString("SubjectName"));
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
		scrollPane.setBounds(0, 543, 680, -527);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBounds(22, 529, 658, -507);
		frame.getContentPane().add(table);
		
		 comboGroup = new JComboBox();
		 comboGroup.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboGroup.setBounds(327, 192, 508, 29);
		frame.getContentPane().add(comboGroup);
		
		 comboSubject = new JComboBox();
		 comboSubject.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				
			}
		});
		comboSubject.setBounds(327, 253, 508, 29);
		frame.getContentPane().add(comboSubject);
		
		noOfStudents = new JTextField();
		noOfStudents.setColumns(10);
		noOfStudents.setBounds(327, 364, 508, 29);
		frame.getContentPane().add(noOfStudents);
		
		durationHrs = new JTextField();
		durationHrs.setColumns(10);
		durationHrs.setBounds(327, 429, 508, 29);
		frame.getContentPane().add(durationHrs);
		
		JLabel lblSelectGroups = new JLabel("Select Group");
		lblSelectGroups.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSelectGroups.setBounds(327, 163, 270, 36);
		frame.getContentPane().add(lblSelectGroups);
		
		JLabel lblSelectSubject = new JLabel("Select Subject");
		lblSelectSubject.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSelectSubject.setBounds(327, 224, 270, 36);
		frame.getContentPane().add(lblSelectSubject);
		
		JLabel lblNumberOfStudents = new JLabel("Number of Students");
		lblNumberOfStudents.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNumberOfStudents.setBounds(327, 336, 270, 36);
		frame.getContentPane().add(lblNumberOfStudents);
		
		JLabel lblDurationhrs = new JLabel("Duration(Hrs)");
		lblDurationhrs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDurationhrs.setBounds(327, 401, 270, 36);
		frame.getContentPane().add(lblDurationhrs);
		
		JButton btnNewButton = new JButton("CLEAR");
		btnNewButton.setIcon(new ImageIcon("E:\\Y3S1\\ITPM\\finalTimeTableManagementSystem\\Images\\clear4.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(224, 225, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboGroup.setSelectedIndex(0);
				comboSubject.setSelectedIndex(0);
				comboSubCode.setSelectedIndex(0);
				noOfStudents.setText("");
				durationHrs.setText("");
			}
		});
		btnNewButton.setBounds(504, 503, 163, 59);
		frame.getContentPane().add(btnNewButton);
		
		//Save
		JButton btnSubmit = new JButton("ADD");
		btnSubmit.setIcon(new ImageIcon("E:\\Y3S1\\ITPM\\finalTimeTableManagementSystem\\Images\\add.png"));
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmit.setBackground(new Color(224, 225, 255));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				System.out.println(tag);		
				String combogrp = comboGroup.getSelectedItem().toString();
				String combosub = comboSubject.getSelectedItem().toString();
				String combosubcode = comboSubCode.getSelectedItem().toString();
				String txtstudents= noOfStudents.getText();
				String txthrs= durationHrs.getText();
				
				 try {
					 Connection conn = DBConnect.connect();
					//SQL Query to save data
	                    String query = "INSERT INTO Session(ID, Lecture1, Tag, Lecture2, GroupID, Subject, NoOfStudents, Duration, SubjectCode) "
	                    + " VALUES("+null+",'" + lecturer + "','" + tag + "','" + selected_Lecturer + "','" + combogrp + "','" + combosub + "','" + txtstudents + "','" + txthrs + "','" + combosubcode + "')";
	                    
	                    Statement sta = conn.createStatement();
	                    int x = sta.executeUpdate(query);
	                    System.out.println(x);
	                    
	                    conn.close();
	                    try {
	    					//Connecting Database - W.N.S. Amaranayake - IT19009728
	    					Connection con = DBConnect.connect();
	    					
	    					String query2="select * from Session ";
	    					PreparedStatement pst=con.prepareStatement(query2);
	    					ResultSet rs=pst.executeQuery();
	    					table.setModel(DbUtils.resultSetToTableModel(rs));	
	    					con.close();
	    				}
	    				catch(Exception ex) {
	    					ex.printStackTrace();
	    				}
	                } catch (Exception exception) {
	                    exception.printStackTrace();
	                	System.out.println("adeee");
	                }
				 
				frame.dispose();
				ManageSession session3 = new ManageSession();
				session3.frame.setVisible(true);
			}
		});
		btnSubmit.setBounds(709, 503,126, 59);
		frame.getContentPane().add(btnSubmit);
		
		//redirect tp session1 frame
		JButton btnBack = new JButton("BACK");
		btnBack.setIcon(new ImageIcon("E:\\Y3S1\\ITPM\\finalTimeTableManagementSystem\\Images\\previous.png"));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBackground(new Color(224, 225, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				frame.dispose();
				Session1 session1 = new Session1();
				session1.frame.setVisible(true);

			}
		});
		btnBack.setBounds(327, 503, 126, 59);
		frame.getContentPane().add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("Select Group & Subject");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_1.setBounds(391, 95, 368, 50);
		frame.getContentPane().add(lblNewLabel_1);
		
		//Redirect to Managesessions frame
		JButton btnManageSessions = new JButton("MANAGE SESSIONS");
		btnManageSessions.setIcon(new ImageIcon("E:\\Y3S1\\ITPM\\finalTimeTableManagementSystem\\Images\\next-button.png"));
		btnManageSessions.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnManageSessions.setBackground(new Color(224, 225, 255));
		btnManageSessions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ManageSession session3 = new ManageSession();
				session3.frame.setVisible(true);
			}
		});
		btnManageSessions.setBounds(10, 13, 214, 36);
		frame.getContentPane().add(btnManageSessions);
		
		JLabel lblSelectSubjectCode = new JLabel("Select Subject Code");
		lblSelectSubjectCode.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSelectSubjectCode.setBounds(327, 273, 270, 36);
		frame.getContentPane().add(lblSelectSubjectCode);
		
	    comboSubCode = new JComboBox();
	    comboSubCode.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboSubCode.setBounds(327, 305, 508, 29);
		frame.getContentPane().add(comboSubCode);
		
		JButton btnHome = new JButton("");
		btnHome.setIcon(new ImageIcon("E:\\Y3S1\\ITPM\\finalTimeTableManagementSystem\\Images\\home.png"));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home itm = new Home();
				Home.main(null);
				frame.setVisible(false);
			}
		});
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(255, 250, 250));
		btnHome.setBounds(22, 62, 73, 49);
		frame.getContentPane().add(btnHome);
		
		fillComboBox();
		fillComboBox2();
		fillComboBoxsubCode();
	}
}
