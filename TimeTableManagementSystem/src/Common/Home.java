package Common;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import Member1.ConsectiveSession;
import Member1.Students;
import Member1.Tags;
import Member2.Employee;
import Member2.ManageSession;
import Member2.Subject;
import Member3.NotAvailableTime;
import Member3.time_managment;
import Member4.Location;
import Member4.Manageroom;
import Member4.Statistics;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("MANAGE STUDENT GROUPS   ");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(224,255,255));
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		//btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/homett.png")));
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/stGroups.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Students itm = new Students();
				Students.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(284, 64, 563, 49);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("MANAGE TAGS                        ");
		btnNewButton_1.setBackground(new Color(224,255,255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tags itm = new Tags();
				Tags.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/tag.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_1.setBounds(284, 126, 563, 49);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("MANAGE LECTURERS           ");
		btnNewButton_2.setBackground(new Color(224,255,255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee itm = new Employee();
				Employee.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(this.getClass().getResource("/lec.png")));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_2.setBounds(284, 188, 563, 46);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("MANAGE SUBJECTS             ");
		btnNewButton_3.setBackground(new Color(224,255,255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Subject itm = new Subject();
				Subject.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(this.getClass().getResource("/sub.png")));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3.setBounds(284, 247, 563, 49);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("WORKING DAYS & HOURS");
		btnNewButton_3_1.setBackground(new Color(224,255,255));
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time_managment itm = new time_managment();
				time_managment.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_3_1.setIcon(new ImageIcon(this.getClass().getResource("/working-time.png")));
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3_1.setBounds(284, 427, 563, 46);
		frame.getContentPane().add(btnNewButton_3_1);
		
		JButton btnNewButton_3_1_1 = new JButton("MANAGE LOCATION           ");
		btnNewButton_3_1_1.setBackground(new Color(224,255,255));
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Location itm = new Location();
				Location.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_3_1_1.setIcon(new ImageIcon(this.getClass().getResource("/map.png")));
		btnNewButton_3_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3_1_1.setBounds(284, 309, 563, 46);
		frame.getContentPane().add(btnNewButton_3_1_1);
		
		JButton btnNewButton_3_1_2 = new JButton("STATISTICS                        ");
		btnNewButton_3_1_2.setBackground(new Color(224,255,255));
		btnNewButton_3_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statistics itm = new Statistics();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Statistics.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_3_1_2.setIcon(new ImageIcon(this.getClass().getResource("/statis.png")));
		btnNewButton_3_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3_1_2.setBounds(284, 486, 563, 46);
		frame.getContentPane().add(btnNewButton_3_1_2);
		
		JButton btnNewButton_3_1_1_1 = new JButton("MANAGE SESSION             ");
		btnNewButton_3_1_1_1.setBackground(new Color(224,255,255));
		btnNewButton_3_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageSession itm = new ManageSession();
				ManageSession.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_3_1_1_1.setIcon(new ImageIcon(this.getClass().getResource("/session.png")));
		btnNewButton_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3_1_1_1.setBounds(284, 368, 563, 46);
		frame.getContentPane().add(btnNewButton_3_1_1_1);
		
		JButton btnNewButton_3_1_2_1 = new JButton("ADD TIME SLOTS              ");
		btnNewButton_3_1_2_1.setBackground(new Color(224,255,255));
		btnNewButton_3_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTime itm = new AddTime();
				AddTime.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_3_1_2_1.setIcon(new ImageIcon(this.getClass().getResource("/tslot.png")));
		btnNewButton_3_1_2_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3_1_2_1.setBounds(284, 545, 563, 46);
		frame.getContentPane().add(btnNewButton_3_1_2_1);
		
		JButton btnNewButton_3_1_2_1_1 = new JButton("VIEW TIME TABLE           ");
		btnNewButton_3_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewTimeTable itm = new ViewTimeTable();
				ViewTimeTable.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_3_1_2_1_1.setBackground(new Color(224,255,255));
		btnNewButton_3_1_2_1_1.setIcon(new ImageIcon(this.getClass().getResource("/timetable.png")));
		btnNewButton_3_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3_1_2_1_1.setBounds(284, 666, 563, 46);
		frame.getContentPane().add(btnNewButton_3_1_2_1_1);
		
		JButton btnManageRooms = new JButton("MANAGE LECTURE ROOMS ");
		btnManageRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manageroom itm = new Manageroom();
				Manageroom.main(null);
				frame.setVisible(false);	
			}
		});
		btnManageRooms.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\step-outside.png"));
		btnManageRooms.setForeground(Color.BLACK);
		btnManageRooms.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnManageRooms.setBackground(new Color(224, 255, 255));
		btnManageRooms.setBounds(284, 604, 563, 49);
		frame.getContentPane().add(btnManageRooms);
		frame.setBounds(100, 100, 1129, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
