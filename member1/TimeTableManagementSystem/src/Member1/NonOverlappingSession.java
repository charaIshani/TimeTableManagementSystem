package Member1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Common.Home;
import Member2.ManageSession;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class NonOverlappingSession {

	private JFrame frame;
	private JComboBox comboBox;
	private JTextField textField;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnHome;
	private JButton btnManageSession;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NonOverlappingSession window = new NonOverlappingSession();
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
	public NonOverlappingSession() {
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
				comboBox.addItem(lec1 +"."+ lec2 +"."+ subCode +"."+ subName +"."+ groupid +"."+ tag);
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

		frame.setBounds(100, 100, 1129, 703);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboBox.setBounds(76, 224, 969, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Lab");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(76, 203, 63, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Display Session");
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/GenerateID1.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("Non Overlapping Session:("+comboBox.getSelectedItem().toString()+")");
	
			}
		});
		btnNewButton.setBounds(70, 367, 465, 49);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add NonOverlapping Session");
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String session= textField.getText();
				try {
					Connection con = DBConnect.connect();
	
					String query = "INSERT INTO NonOverlappingSession values(null, '" + session + "')";
	
					Statement sta = con.createStatement();
					int x = sta.executeUpdate(query);
					if (x == 0) {
						JOptionPane.showMessageDialog(btnNewButton_1, "This is alredy exist");
					} else {
						JOptionPane.showMessageDialog(btnNewButton_1,"Are you sure you want to ADD NonOverlapping Session..");	
						showData();
					}
					con.close();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}	
		});
		btnNewButton_1.setBounds(588, 367, 457, 49);
		frame.getContentPane().add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(70, 332, 975, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton_2 = new JButton("Consecutive");
		btnNewButton_2.setBackground(new Color(224, 255, 255));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsectiveSession  itm = new ConsectiveSession ();
				ConsectiveSession .main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(76, 133, 153, 42);
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
		btnNewButton_3.setBounds(243, 133, 153, 42);
		frame.getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Non Overlapping");
		btnNewButton_4.setBackground(new Color(224, 255, 255));
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NonOverlappingSession itm = new NonOverlappingSession();
				NonOverlappingSession.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(402, 133, 160, 42);
		frame.getContentPane().add(btnNewButton_4);
		
		lblNewLabel_1 = new JLabel("NonOverlapping Session");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_1.setBounds(416, 23, 364, 49);
		frame.getContentPane().add(lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 442, 969, 250);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		Connection con = DBConnect.connect();
		String query="select * from NonOverlappingSession "; 
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
		btnHome.setBackground(new Color(255, 255, 255));
		btnHome.setBounds(76, 71, 73, 49);
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
		
		fillComboBox();
	}
	
	private void showData() {
		Connection con = DBConnect.connect();
		DefaultTableModel model = new DefaultTableModel();
	
	 
		try {
			
			model.addColumn("ID");
			model.addColumn("Non Overlapping Session");

			String query = "Select * from NonOverlappingSession ";
//			System.out.println( );ConsecutiveSession
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(query);
			
				
				while(rs.next())
				{
					model.addRow(new Object[] {
							rs.getString("NonOverlappingSesID"),
							rs.getString("NonOverlappingSes")
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
