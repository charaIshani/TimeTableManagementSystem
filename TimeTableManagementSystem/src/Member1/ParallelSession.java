package Member1;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Common.Home;
import Member2.ManageSession;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class ParallelSession {

	private JFrame frame;
	private JTextField textField;
	private JComboBox comboo3;
	private JComboBox comboo4;
	private JButton btnNewButton3;
	private JButton btnNewButton5;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JButton btnHome;
	private JButton btnManageSession;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParallelSession window = new ParallelSession();
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
	public ParallelSession() {
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
				comboo3.addItem(lec1 +"."+ lec2 +"."+ subCode +"."+ subName +"."+ groupid +"."+ tag);
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
				comboo4.addItem(lec1 +"."+ lec2 +"."+ subCode +"."+ subName +"."+ groupid +"."+ tag);
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
		
		comboo3 = new JComboBox();
		comboo3.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboo3.setBounds(51, 215, 490, 22);
		frame.getContentPane().add(comboo3);
		
		comboo4 = new JComboBox();
		comboo4.setModel(new DefaultComboBoxModel(new String[] {""}));
		comboo4.setBounds(579, 215, 490, 22);
		frame.getContentPane().add(comboo4);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(51, 327, 1018, 22);
		frame.getContentPane().add(textField);
		
		JButton btnNewButton3 = new JButton("Display Session");
		btnNewButton3.setIcon(new ImageIcon(this.getClass().getResource("/GenerateID1.png")));
		btnNewButton3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton3.setBackground(new Color(224, 255, 255));
		btnNewButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("Parallel Session:("+comboo3.getSelectedItem().toString()+")+("+comboo4.getSelectedItem().toString()+")");
			}
		});
		btnNewButton3.setBounds(51, 362, 489, 49);
		frame.getContentPane().add(btnNewButton3);
		
		JButton btnNewButton5 = new JButton("Add Parallel Session");
		btnNewButton5.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnNewButton5.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton5.setBackground(new Color(224, 255, 255));
		btnNewButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String session= textField.getText();

				try {
					Connection con = DBConnect.connect();

					String query = "INSERT INTO ParallelSession values(null, '" + session + "')";

					Statement sta = con.createStatement();
					int x = sta.executeUpdate(query);
					if (x == 0) {
							JOptionPane.showMessageDialog(btnNewButton5, "This is alredy exist");
					} else {
							JOptionPane.showMessageDialog(btnNewButton5,"Are you sure you want to ADD Parallel Session ..");
							showData();
					}
					con.close();
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
		});
		btnNewButton5.setBounds(581, 362, 488, 49);
		frame.getContentPane().add(btnNewButton5);
		
		JLabel lblNewLabel_2 = new JLabel("Lecture 2");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(579, 201, 97, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Lecture 1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(51, 201, 108, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		btnNewButton = new JButton("Consective");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsectiveSession  itm = new ConsectiveSession ();
				ConsectiveSession .main(null);
				frame.setVisible(false);
			}	
		});
		btnNewButton.setBounds(51, 135, 152, 43);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Parallel");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ParallelSession itm = new ParallelSession();
				ParallelSession.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(215, 135, 152, 43);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Non Overlapping");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBackground(new Color(224, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NonOverlappingSession itm = new NonOverlappingSession();
				NonOverlappingSession.main(null);
				frame.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(381, 135, 160, 43);
		frame.getContentPane().add(btnNewButton_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 434, 1019, 261);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		Connection con = DBConnect.connect();
		String query="select * from ParallelSession "; 
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
		
		lblNewLabel = new JLabel("Parallel Session");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(459, 26, 243, 43);
		frame.getContentPane().add(lblNewLabel);
		
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
		btnHome.setBounds(51, 73, 73, 49);
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
		fillComboBox2();
		
	}
	
	
	private void showData() {
		Connection con = DBConnect.connect();
		DefaultTableModel model = new DefaultTableModel();
	
	 
		try {
			
			model.addColumn("ID");
			model.addColumn("Paralle Session");

			String query = "Select * from ParallelSession ";
//			System.out.println( );ConsecutiveSession
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(query);
			
				
				while(rs.next())
				{
					model.addRow(new Object[] {
							rs.getString("ParallelID"),
							rs.getString("ParalleSes")
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
