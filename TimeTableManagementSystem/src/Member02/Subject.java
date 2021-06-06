package Member02;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Common.Home;
import Member1.DBConnect;
import net.proteanit.sql.DbUtils;

import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.ImageIcon;


public class Subject {

	private JFrame frame;
	private JTextField subName;
	private JTextField subCode;
	private JTextField SubID;
	private JTable table;
	String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Subject window = new Subject();
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
	public Subject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					//Connecting Database - W.N.S. Amaranayake - IT19009728
					Connection con = DBConnect.connect();
					
					String query="select * from Subject ";
					PreparedStatement pst=con.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));	
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}	
			}
		});
		frame.setBounds(100, 100, 1127, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Subject");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(446, 13, 246, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboYear = new JComboBox();
		comboYear.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2","3","4"}));
		comboYear.setBounds(51, 191, 327, 22);
		frame.getContentPane().add(comboYear);
		
		JLabel lblNewLabel_1 = new JLabel("Offered Year");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(51, 175, 104, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel sem = new JLabel("Offered Semester");
		sem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		sem.setBounds(51, 123, 123, 16);
		frame.getContentPane().add(sem);
		
		JLabel lblNewLabel_1_2 = new JLabel("Subject Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_2.setBounds(51, 226, 104, 16);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Subject Code");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_3.setBounds(51, 275, 104, 16);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Number of Lecture Hours");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_4.setBounds(51, 342, 183, 16);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Number of Tutorial Hours");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_5.setBounds(51, 405, 183, 16);
		frame.getContentPane().add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("Number of Lab Hours");
		lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_5_1.setBounds(51, 463, 152, 16);
		frame.getContentPane().add(lblNewLabel_1_5_1);
		
		JLabel lblNewLabel_1_5_2 = new JLabel("Number of Evaluation Hours");
		lblNewLabel_1_5_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_5_2.setBounds(51, 516, 209, 16);
		frame.getContentPane().add(lblNewLabel_1_5_2);
		
		JRadioButton radiobtn1 = new JRadioButton("1st semester");
		JRadioButton radiobtn2 = new JRadioButton("2nd semester");
		radiobtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radiobtn1.isSelected()) 
				{
					radiobtn2.setSelected(false);
				}
			}
		});
		radiobtn1.setBounds(51, 141, 152, 25);
		frame.getContentPane().add(radiobtn1);
		
		
		radiobtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(radiobtn2.isSelected()) 
				{
					radiobtn1.setSelected(false);
				}
			}
		});
		radiobtn2.setBounds(219, 141, 157, 25);
		frame.getContentPane().add(radiobtn2);
		
		subName = new JTextField();
		subName.setBounds(48, 240, 330, 22);
		frame.getContentPane().add(subName);
		subName.setColumns(10);
		
		subCode = new JTextField();
		subCode.setColumns(10);
		subCode.setBounds(51, 296, 327, 22);
		frame.getContentPane().add(subCode);
		
		JSpinner lectHrs = new JSpinner();
		lectHrs.setBounds(51, 360, 327, 22);
		frame.getContentPane().add(lectHrs);
		
		JSpinner tuteHrs = new JSpinner();
		tuteHrs.setBounds(51, 421, 327, 22);
		frame.getContentPane().add(tuteHrs);
		
		JSpinner labHrs = new JSpinner();
		labHrs.setBounds(51, 481, 327, 22);
		frame.getContentPane().add(labHrs);
		
		JSpinner evaHrs = new JSpinner();
		evaHrs.setBounds(51, 538, 327, 22);
		frame.getContentPane().add(evaHrs);
		
		//Save 
		JButton btnSave = new JButton("ADD");
		btnSave.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBackground(new Color(224, 225, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				int subId = Integer.parseInt(SubID.getText());
				String offerdYer = comboYear.getSelectedItem().toString();
				
				String subjectName= subName.getText();
				String subjectCode= subCode.getText();
				String lectureHrs = lectHrs.getValue().toString();
				String tutorialHrs =tuteHrs.getValue().toString();
				String labHours = labHrs.getValue().toString();
				String evaluationHrs =  evaHrs.getValue().toString();
				
				String sem = null;
				
				
				
				if(radiobtn1.isSelected())
				{
					sem = "1st";
				}
				if(radiobtn2.isSelected())
				{
					sem = "2nd";
				}
				
				
				 try {
					 Connection conn = DBConnect.connect();
					//SQL Query to save data
					 String query = "INSERT INTO Subject  values(null,'" + offerdYer + "','" + sem + "','" + subjectName + "','" + subjectCode + "','" + lectureHrs + "','" + tutorialHrs + "','" + labHours + "','" + evaluationHrs + "')";
					             
	                    Statement sta = conn.createStatement();
	                    int x = sta.executeUpdate(query);
	                    System.out.println(x);
	                    
	                    conn.close();
	                    try {
	    					//Connecting Database - W.N.S. Amaranayake - IT19009728
	    					Connection con = DBConnect.connect();
	    					
	    					String query1="select * from Subject ";
	    					PreparedStatement pst=con.prepareStatement(query1);
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
			}
		});
		btnSave.setBounds(58, 577, 152, 51);
		frame.getContentPane().add(btnSave);
		
		//Update
		JButton btnEdit = new JButton("UPDATE");
		btnEdit.setIcon(new ImageIcon(this.getClass().getResource("/update.png")));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEdit.setBackground(new Color(224, 225, 255));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(subName.getText().equals("")||subCode.getText().equals("")||sem.getText().equals("")||lectHrs.getValue().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please fill the form");
				}else {
				try {													
					
					String sem = null;
					if(radiobtn1.isSelected())
					{
						sem = "1st";
					}
					if(radiobtn2.isSelected())
					{
						sem = "2nd";
					}
					
					Connection con = DBConnect.connect();	
					//SQL Query to update data
					String query="Update Subject set SubID='"+id+"',OfferdSemester='"+sem+ "',OfferdYear='"+comboYear.getSelectedItem()+"',SubjectName='"+ subName.getText() +"', SubjectCode='"+subCode.getText()+"',NoLecHours='"+ lectHrs.getValue() +"' ,NoTuteHours='"+ tuteHrs.getValue() +"',NoLabHours='"+ labHrs.getValue() +"',NoEvaHours='"+ evaHrs.getValue() +"' where SubID='"+id+"'";
					PreparedStatement pst=con.prepareStatement(query);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Updated");
					pst.close();
					
				}
				catch(Exception ea) {
					ea.printStackTrace();
				}
				}
			}
		});
		btnEdit.setBounds(225, 577, 152, 51);
		frame.getContentPane().add(btnEdit);
		
		//Clear
		JButton btnClear = new JButton("CLEAR");
		btnClear.setIcon(new ImageIcon(this.getClass().getResource("/clear4.png")));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBackground(new Color(224, 225, 255));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				SubID.setText(null);
				subName.setText("");
				subCode.setText("");
				tuteHrs.setValue(0);
				comboYear.setSelectedIndex(0);
				lectHrs.setValue(0);
				labHrs.setValue(0);
				evaHrs.setValue(0);
			}
		});
		btnClear.setBounds(219, 639, 157, 53);
		frame.getContentPane().add(btnClear);
		
//		JLabel lblNewLabel_2 = new JLabel("Subject ID");
//		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
//		lblNewLabel_2.setBounds(58, 70, 97, 16);
//		frame.getContentPane().add(lblNewLabel_2);
		
//		SubID = new JTextField();
//		SubID.setBounds(51, 88, 403, 22);
//		frame.getContentPane().add(SubID);
//		SubID.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(390, 91, 707, 601);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//Delete
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setIcon(new ImageIcon(this.getClass().getResource("/remove.png")));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBackground(new Color(224, 225, 255));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnect.connect();
					//SQL Query to delete data
					String query="Delete from Subject where SubID="+Integer.parseInt(id);
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted");
					pst.close();
					
					try {
    					//Connecting Database - W.N.S. Amaranayake - IT19009728
//    					Connection con = DBConnect.connect();
    					
    					String query1="select * from Subject ";
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
		btnDelete.setBounds(58, 641, 152, 51);
		frame.getContentPane().add(btnDelete);
		
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
		btnHome.setBounds(51, 13, 73, 49);
		frame.getContentPane().add(btnHome);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int selectedRow=table.getSelectedRow();
				
				
				
				String cYear = table.getValueAt(selectedRow, 1).toString();
				for(int i=0; i<comboYear.getItemCount();i++) {
				if(comboYear.getItemAt(i).toString().equalsIgnoreCase(cYear)) {
					comboYear.setSelectedIndex(i);
					}
				}
				
				
			    String sem = table.getValueAt(selectedRow, 2).toString();
			    System.out.println(sem);
				
			    if(sem.equals("1st"))
			    {
			    	radiobtn1.setSelected(true);	
			    	radiobtn2.setSelected(false);		
			    }
			    else if(sem.equals("2nd"))
			    {			    	
			    	radiobtn1.setSelected(false);	
			    	radiobtn2.setSelected(true);		
			    }
			    
//			    SubID.setText(table.getValueAt(selectedRow, 0).toString());
			    id = table.getValueAt(selectedRow, 0).toString();
			    subName.setText(table.getValueAt(selectedRow, 3).toString());
				
				subCode.setText(table.getValueAt(selectedRow, 4).toString());
				lectHrs.setValue(table.getValueAt(selectedRow, 5));
				tuteHrs.setValue(table.getValueAt(selectedRow, 6));
				labHrs.setValue(table.getValueAt(selectedRow, 7));
				evaHrs.setValue(table.getValueAt(selectedRow, 8));

			}
		});
	}
}
