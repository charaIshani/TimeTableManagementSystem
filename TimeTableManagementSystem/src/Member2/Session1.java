package Member2;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JToggleButton;

import Common.Home;
import Member1.DBConnect;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class Session1 {

	 JFrame frame;
	private JTable table;
	private JComboBox comboLecturer;
	private JComboBox comboTag;
	private JComboBox comboLecturer2;
	private final JLabel lblNewLabel = new JLabel("Select Lecturer(s)");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Session1 window = new Session1();
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
	public Session1() {
		initialize();
		fillComboBox();
		fillComboBox2();
		fillComboBox3();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	//getting data combo box from Employee table to get Lecturer Name
	public void fillComboBox() {
		try {
			Connection con = DBConnect.connect();
			String query="Select * from Employee";
			PreparedStatement prepare = con.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				comboLecturer.addItem(result.getString("LecturerName"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//getting data combo box from Tags table to get Tag Name
	public void fillComboBox2() {
		try {
			Connection conn = DBConnect.connect();
			String query="Select * from Tags";
			PreparedStatement prepare = conn.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			
			while(result.next()) {
				comboTag.addItem(result.getString("TagName"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//getting data combo box from Employee table to get Lecturer Name
		public void fillComboBox3() {
			try {
				Connection conn = DBConnect.connect();
				String query="Select * from Employee";
				PreparedStatement prepare = conn.prepareStatement(query);
				ResultSet result = prepare.executeQuery();
				
				while(result.next()) {
					comboLecturer2.addItem(result.getString("LecturerName"));
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
					
					String query="select * from Employee ";
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
		scrollPane.setBounds(12, 568, 662, -561);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBounds(27, 553, 647, -535);
		frame.getContentPane().add(table);
		
		 comboLecturer = new JComboBox();
		 comboLecturer.setToolTipText("\r\n");
		comboLecturer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
			}
		});
		
		comboLecturer.setBounds(418, 222, 458, 29);
		frame.getContentPane().add(comboLecturer);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(253, 217, 126, 36);
		frame.getContentPane().add(lblNewLabel);
		
		 comboTag = new JComboBox();
		 comboTag.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboTag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		comboTag.setBounds(418, 301, 458, 29);
		frame.getContentPane().add(comboTag);
		
		JLabel lblSelectTag = new JLabel("Select Tag");
		lblSelectTag.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSelectTag.setBounds(253, 296, 126, 36);
		frame.getContentPane().add(lblSelectTag);
		
		JLabel lblSelectedLecturers = new JLabel("Other Lecturer(s)");
		lblSelectedLecturers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSelectedLecturers.setBounds(253, 374, 156, 36);
		frame.getContentPane().add(lblSelectedLecturers);
		
		//Clear
		JButton btnClear = new JButton("CLEAR");
		btnClear.setIcon(new ImageIcon(this.getClass().getResource("/clear4.png")));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBackground(new Color(224, 225, 255));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			
				comboLecturer.setSelectedIndex(0);
				comboTag.setSelectedIndex(0);
				comboLecturer2.setSelectedIndex(0);
			}
		});
		btnClear.setBounds(256, 519, 179, 56);
		frame.getContentPane().add(btnClear);
		
		//Redirect to session2 frame
		JButton btnNext = new JButton("NEXT");
		btnNext.setIcon(new ImageIcon(this.getClass().getResource("/next-button.png")));
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNext.setBackground(new Color(224, 225, 255));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String leturer = comboLecturer.getSelectedItem().toString();
				String tag = comboTag.getSelectedItem().toString();
				String otherLecturer = comboLecturer2.getSelectedItem().toString();
				
				frame.dispose();
				Session2 session2 = new Session2(leturer, tag, otherLecturer);
				session2.frame.setVisible(true);		
			}

		});
		
		btnNext.setBounds(720, 519, 156, 56);
		frame.getContentPane().add(btnNext);
		
		JLabel lblNewLabel_1 = new JLabel("Select Lecturer & Tag");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_1.setBounds(418, 116, 373, 48);
		frame.getContentPane().add(lblNewLabel_1);
		
		//redirect to ManageSession frame
		JButton btnManageSessions = new JButton("MANAGE SESSIONS");
		btnManageSessions.setIcon(new ImageIcon(this.getClass().getResource("/previous.png")));
		btnManageSessions.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnManageSessions.setBackground(new Color(224, 225, 255));
		btnManageSessions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ManageSession session3 = new ManageSession();
				session3.frame.setVisible(true);
			}
		});
		btnManageSessions.setBounds(22, 13, 209, 36);
		frame.getContentPane().add(btnManageSessions);
		
		 comboLecturer2 = new JComboBox();
		 comboLecturer2.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboLecturer2.setBounds(421, 379, 455, 29);
		frame.getContentPane().add(comboLecturer2);
		
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
		btnHome.setBounds(27, 62, 73, 49);
		frame.getContentPane().add(btnHome);
		

		fillComboBox();
		fillComboBox2();
		fillComboBox3();
	}
}
