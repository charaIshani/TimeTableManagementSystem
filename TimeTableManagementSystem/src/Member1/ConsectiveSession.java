package Member1;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Common.Home;
import Member2.ManageSession;
import Member4.AddLocConsecutive;
import Member4.ConsecutiveSessionRoomTime;
import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class ConsectiveSession {

	private JFrame frame;
	private JComboBox comboo;
	private JComboBox comboo2;
	private JTextField textField;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JTable table;
	private JButton btnHome;
	private JButton btnManageSession;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsectiveSession window = new ConsectiveSession();
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
	public ConsectiveSession() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void fillComboBox() {
		try {
		String query ="select * from Session";

		Connection con = DBConnect.connect();
		PreparedStatement pst =con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String lec1=rs.getString("Lecture1");
				String lec2=rs.getString("Lecture2");
				String subCode=rs.getString("SubjectCode");
				String subName=rs.getString("Subject");
				String groupid=rs.getString("GroupID");
				String tag=rs.getString("Tag");	
				comboo.addItem(lec1 +"."+ lec2 +"."+ subCode +"."+ subName +"."+ groupid +"."+ tag);
			}
		}catch (Exception e){
		e.printStackTrace();
		}
	}
	
	public void fillComboBox2() {
		try {
		String query ="select * from Session";

		Connection con = DBConnect.connect();
		PreparedStatement pst =con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String lec1=rs.getString("Lecture1");
				String lec2=rs.getString("Lecture2");
				String subCode=rs.getString("SubjectCode");
				String subName=rs.getString("Subject");
				String groupid=rs.getString("GroupID");
				String tag=rs.getString("Tag");	
				comboo2.addItem(lec1 +"."+ lec2 +"."+ subCode +"."+ subName +"."+ groupid +"."+ tag);
			}
		}catch (Exception e){
		e.printStackTrace();
		}
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setBounds(100, 100, 1128, 769);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				showData();
				
			}
			});
		
		comboo = new JComboBox();
		comboo.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboo.setBounds(44, 219, 475, 22);
		frame.getContentPane().add(comboo);
		
		comboo2 = new JComboBox();
		comboo2.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboo2.setBounds(544, 219, 526, 22);
		frame.getContentPane().add(comboo2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(222, 413, 725, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(44, 319, 1026, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Display Session");
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/GenerateID1.png")));
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("("+comboo.getSelectedItem().toString()+") + ("+comboo2.getSelectedItem().toString()+")");
			}
		});
		btnNewButton.setBounds(44, 358, 475, 51);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Add Consective Session");
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnNewButton_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String session= textField.getText();
				
				try {
				Connection con = DBConnect.connect();

				String query = "INSERT INTO ConsecutiveSession values(null, '" + session + "')";

				Statement sta = con.createStatement();
				int x = sta.executeUpdate(query);
					if (x == 0) {
						JOptionPane.showMessageDialog(btnNewButton_1, "This is alredy exist");
					} else {
						JOptionPane.showMessageDialog(btnNewButton_1,
								"Are you sure you want to ADD Consective sesstion..");
						showData();
					}
				con.close();
				} catch (Exception exception) {
				exception.printStackTrace();
				}
			}			
		});
		
		btnNewButton_1.setBounds(544, 358, 526, 51);
		frame.getContentPane().add(btnNewButton_1);
		
		lblNewLabel_1 = new JLabel("Lecture");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(44, 201, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Tute");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(544, 201, 56, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		btnNewButton_2 = new JButton("Consecutive");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBackground(new Color(224, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsectiveSession itm = new ConsectiveSession();
				ConsectiveSession.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(44, 136, 152, 42);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Parallel");
		btnNewButton_3.setBackground(new Color(224, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ParallelSession itm = new ParallelSession();
				ParallelSession.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_3.setBounds(204, 136, 152, 42);
		frame.getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("NonOverlapping");
		btnNewButton_4.setBackground(new Color(224, 255, 255));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NonOverlappingSession itm = new NonOverlappingSession();
				NonOverlappingSession.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(367, 136, 160, 42);
		frame.getContentPane().add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 442, 1026, 258);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		Connection con = DBConnect.connect();
		String query="select * from ConsecutiveSession"; 
		//String query="select Lecture1,Lecture2,SubjectCode,Subject,GroupID,Tag from Session "; 
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} ResultSet rs = null;
		try {
			rs = pst.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
		JLabel lblNewLabel_3 = new JLabel("Consective Session");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_3.setBounds(444, 43, 283, 42);
		frame.getContentPane().add(lblNewLabel_3);
		
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
		btnHome.setBounds(44, 74, 73, 49);
		frame.getContentPane().add(btnHome);
		
		btnManageSession = new JButton("MANAGE SESSION");
		btnManageSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageSession itm = new ManageSession();
				ManageSession.main(null);
				frame.setVisible(false);
			}
		});
		btnManageSession.setIcon(new ImageIcon(this.getClass().getResource("/previous.png")));
		btnManageSession.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnManageSession.setBackground(new Color(224, 255, 255));
		btnManageSession.setBounds(12, 13, 203, 40);
		frame.getContentPane().add(btnManageSession);
		
		btnNewButton_5 = new JButton("Add Consective Room");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddLocConsecutive itm = new AddLocConsecutive();
				AddLocConsecutive.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_5.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\next-button.png"));
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_5.setBackground(new Color(224, 255, 255));
		btnNewButton_5.setBounds(819, 13, 279, 42);
		frame.getContentPane().add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("Add Consective Room Time");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsecutiveSessionRoomTime itm = new ConsecutiveSessionRoomTime();
				ConsecutiveSessionRoomTime.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_6.setIcon(new ImageIcon("G:\\New folder\\TimeTableManagementSystem\\Images\\next-button.png"));
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_6.setBackground(new Color(224, 255, 255));
		btnNewButton_6.setBounds(819, 59, 279, 42);
		frame.getContentPane().add(btnNewButton_6);
		
		fillComboBox();
		fillComboBox2();
	}
	
	private void showData() {
		Connection con = DBConnect.connect();
		DefaultTableModel model = new DefaultTableModel();
	
	 
		try {
			
			model.addColumn("ID");
			model.addColumn("Consecutive Session");

			String query = "Select * from ConsecutiveSession ";
//			System.out.println( );ConsecutiveSession
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(query);
			
				
				while(rs.next())
				{
					model.addRow(new Object[] {
							rs.getString("ConsecutiveSesID"),
							rs.getString("ConsecutiveSes")
					});
				}
				rs.close();
				sta.close();
				 
		 
		 
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(1).setPreferredWidth(1000);

		 
		 
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
