package Member2;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;

import Common.Home;
import Member1.DBConnect;
import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class Employee {

	private JFrame frame;
	private JTextField empId;
	private JTextField empName;
	private JTextField employeeID;
	private JTextField EmpRank;
	private JTable table;
	String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
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
	public Employee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.setBounds(100, 100, 1025, 745);
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

		
		frame.setBounds(100, 100, 1124, 764);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Employee");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(465, 13, 278, 31);
		frame.getContentPane().add(lblNewLabel);
		
//		JLabel EmpId = new JLabel("Emp Id");
//		EmpId.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
//		EmpId.setBounds(58, 94, 143, 16);
//		frame.getContentPane().add(EmpId);
		
		JLabel lblEmployeeName = new JLabel("Employee Name");
		lblEmployeeName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblEmployeeName.setBounds(58, 135, 143, 16);
		frame.getContentPane().add(lblEmployeeName);
		
		JLabel EmployeeID = new JLabel("Employee ID");
		EmployeeID.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		EmployeeID.setBounds(58, 180, 143, 16);
		frame.getContentPane().add(EmployeeID);
		
		JLabel employeedepartment = new JLabel("Department");
		employeedepartment.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		employeedepartment.setBounds(58, 283, 143, 16);
		frame.getContentPane().add(employeedepartment);
		
		JLabel lblCenter = new JLabel("Center");
		lblCenter.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblCenter.setBounds(58, 338, 143, 16);
		frame.getContentPane().add(lblCenter);
		
		JLabel lblFaculty = new JLabel("Faculty");
		lblFaculty.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblFaculty.setBounds(58, 231, 143, 16);
		frame.getContentPane().add(lblFaculty);
		
		JLabel lblRank = new JLabel("Rank");
		lblRank.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblRank.setBounds(58, 491, 56, 16);
		frame.getContentPane().add(lblRank);
		
		JLabel lblBuilding = new JLabel("Building");
		lblBuilding.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblBuilding.setBounds(58, 389, 143, 16);
		frame.getContentPane().add(lblBuilding);
		
		JLabel lblCenter_1 = new JLabel("Level");
		lblCenter_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblCenter_1.setBounds(58, 440, 143, 16);
		frame.getContentPane().add(lblCenter_1);
		
//		empId = new JTextField();
//		empId.setBounds(58, 110, 398, 22);
//		frame.getContentPane().add(empId);
//		empId.setColumns(10);
		
		empName = new JTextField();
		empName.setColumns(10);
		empName.setBounds(58, 151, 398, 22);
		frame.getContentPane().add(empName);
		
		employeeID = new JTextField();
		employeeID.setColumns(10);
		employeeID.setBounds(58, 196, 398, 22);
		frame.getContentPane().add(employeeID);
		
		EmpRank = new JTextField();
		EmpRank.setColumns(10);
		EmpRank.setBounds(58, 509, 398, 22);
		frame.getContentPane().add(EmpRank);
		
		JComboBox comboFaculty = new JComboBox();
		comboFaculty.setModel(new DefaultComboBoxModel(new String[] {"", "Faculty Of Computing", "Faculty of Business", "Faculty of Engineering", "Faculty of Huminities and Science", "Faculty of Architecture"}));
		comboFaculty.setBounds(58, 247, 398, 22);
		frame.getContentPane().add(comboFaculty);
		
		JComboBox comboDep = new JComboBox();
		comboDep.setModel(new DefaultComboBoxModel(new String[] {"", "MU", "SE", "IM", "IT", "ELT", "HCM", "BM", "BA", "EEE", "ISE"}));
		comboDep.setBounds(58, 299, 398, 22);
		frame.getContentPane().add(comboDep);
		
		JComboBox comboBuilding = new JComboBox();
		comboBuilding.setModel(new DefaultComboBoxModel(new String[] {"", "Main Building", "New Building", "Engineering Building", "Business Building", "D-Block"}));
		comboBuilding.setBounds(58, 405, 398, 22);
		frame.getContentPane().add(comboBuilding);
		
		JComboBox comboCenter = new JComboBox();
		comboCenter.setModel(new DefaultComboBoxModel(new String[] {"", "Malabe", "Metro", "Matara", "Kandy", "Kurunagala", "Jaffna"}));
		comboCenter.setBounds(58, 353, 398, 22);
		frame.getContentPane().add(comboCenter);
		
		JComboBox comboLevel = new JComboBox();
		comboLevel.setModel(new DefaultComboBoxModel(new String[] {"", "Professor", "Assistant Professor", "Senior Lecturer(HG)", "Senior Lecturer", "Lecturer", "Assistant Lecturer"}));
		comboLevel.setBounds(58, 456, 398, 22);
		frame.getContentPane().add(comboLevel);
		//*************************************************
		JScrollPane scrollPane = new JScrollPane();
		
		
		//Generating Rank - W.N.S. Amaranayake- IT19009728
		JButton btnNewButton = new JButton("GENERATE RANK");
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/GenerateID1.png")));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(224, 225, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmpRank.setText(employeeID.getText().toString()+"."+ comboLevel.getSelectedItem().toString());
			}
		});
		btnNewButton.setBounds(58, 539, 403, 39);
		frame.getContentPane().add(btnNewButton);
		
		//Implementing Save Function - W.N.S Amaranayake - IT19009728
		JButton btnSave = new JButton("ADD");
		btnSave.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSave.setBackground(new Color(224, 225, 255));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String txtemployeeName= empName.getText();
				String txtemployeeId= employeeID.getText();
				String facultyname = comboFaculty.getSelectedItem().toString();
				String departmentname = comboDep.getSelectedItem().toString();
				String centername = comboCenter.getSelectedItem().toString();
				String buildingname = comboBuilding.getSelectedItem().toString();
				String selectlevel = comboLevel.getSelectedItem().toString();
				String generate = EmpRank.getText();

				
				 try {
					 Connection conn = DBConnect.connect();
					//SQL Query to save data
	                    String query = "INSERT INTO Employee  values(null,'" + txtemployeeName + "','" + txtemployeeId + "','" + facultyname + "','" + 
					departmentname + "','" + centername + "','" + buildingname + "','" + selectlevel + "','" + selectlevel + "')";
					 
	                    //String query="select * from studentGroups ";
	                    
	                    
	                    Statement sta = conn.createStatement();
	                    int x = sta.executeUpdate(query);
	                    System.out.println(x);

	                    
	                    conn.close();
	                    try {
	    					//Connecting Database - W.N.S. Amaranayake - IT19009728
	    					Connection con = DBConnect.connect();
	    					
	    					String query1="select * from Employee ";
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
		btnSave.setBounds(58, 591, 180, 50);
		frame.getContentPane().add(btnSave);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane.setBounds(353, 498, 376, -39);
		frame.getContentPane().add(scrollPane);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setIcon(new ImageIcon(this.getClass().getResource("/update.png")));
		btnUpdate.setBackground(new Color(224, 225, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnect.connect();					
					//SQL Query to update data
					String query="Update Employee set  EmployeeID='"+employeeID.getText()+"',Rank='"+EmpRank.getText()+"',lECTURERName='"+empName.getText()+"',"
							+ "Faculty='"+comboFaculty.getSelectedItem()+"',Department='"+comboDep.getSelectedItem()+"',Building='"+comboBuilding.getSelectedItem()
							+"',Center='"+comboCenter.getSelectedItem()+"',Level='"+comboLevel.getSelectedItem()+"' where EmpID="+Integer.parseInt(id);
					PreparedStatement pst=con.prepareStatement(query);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Updated");
					pst.close();
					
					try {
    					//Connecting Database - W.N.S. Amaranayake - IT19009728
//    					Connection con = DBConnect.connect();
    					
    					String query1="select * from Employee ";
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
		btnUpdate.setBounds(295, 591, 166, 50);
		frame.getContentPane().add(btnUpdate);
		
		//delete
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setIcon(new ImageIcon(this.getClass().getResource("/remove.png")));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBackground(new Color(224, 225, 255));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnect.connect();
					//SQL Query to delete data
					String query="Delete from Employee where EmpID="+Integer.parseInt(id);
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted");
					pst.close();
					
					try {
    					//Connecting Database - W.N.S. Amaranayake - IT19009728
    					
    					String query1="select * from Employee ";
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
		btnDelete.setBounds(58, 654, 180, 50);
		frame.getContentPane().add(btnDelete);
		
		//clear
		JButton btnClear = new JButton("CLEAR");
		btnClear.setIcon(new ImageIcon(this.getClass().getResource("/clear4.png")));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBackground(new Color(224, 225, 255));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				empId.setText(null);
				empName.setText("");
				employeeID.setText("");
				EmpRank.setText("");
				comboFaculty.setSelectedIndex(0);
				comboDep.setSelectedIndex(0);
				comboLevel.setSelectedIndex(0);
				comboCenter.setSelectedIndex(0);
				comboBuilding.setSelectedIndex(0);
			}
		});
		btnClear.setBounds(295, 654, 166, 50);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(496, 95, 583, 594);
		frame.getContentPane().add(scrollPane_1);
		
		//Add mouseclick listner to jtable
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int selectedRow=table.getSelectedRow();
				EmpRank.setText(table.getValueAt(selectedRow, 8).toString());
				employeeID.setText(table.getValueAt(selectedRow, 2).toString());
//				empId.setText(table.getValueAt(selectedRow, 0).toString());
				id = table.getValueAt(selectedRow, 0).toString();
				
				String Combolev = table.getValueAt(selectedRow, 7).toString();
				for(int i=0; i<comboLevel.getItemCount();i++) {
				if(comboLevel.getItemAt(i).toString().equalsIgnoreCase(Combolev)) {
					comboLevel.setSelectedIndex(i);
				}
				}
				String comboLeve2 = table.getValueAt(selectedRow, 3).toString();
				for(int i=0; i<comboFaculty.getItemCount();i++) {
				if(comboFaculty.getItemAt(i).toString().equalsIgnoreCase(comboLeve2)) {
					comboFaculty.setSelectedIndex(i);
				}
				}
				String comboLeve3 = table.getValueAt(selectedRow, 4).toString();
				for(int i=0; i<comboDep.getItemCount();i++) {
				if(comboDep.getItemAt(i).toString().equalsIgnoreCase(comboLeve3)) {
					comboDep.setSelectedIndex(i);
				}
				}
				String comboLeve4 = table.getValueAt(selectedRow, 5).toString();
				for(int i=0; i<comboCenter.getItemCount();i++) {
				if(comboCenter.getItemAt(i).toString().equalsIgnoreCase(comboLeve4)) {
					comboCenter.setSelectedIndex(i);
				}
				}
				String comboLeve5 = table.getValueAt(selectedRow, 6).toString();
				for(int i=0; i<comboBuilding.getItemCount();i++) {
				if(comboBuilding.getItemAt(i).toString().equalsIgnoreCase(comboLeve5)) {
					comboBuilding.setSelectedIndex(i);
				}


				empName.setText(table.getValueAt(selectedRow, 1).toString());
				}}	
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
		btnHome.setIcon(new ImageIcon(this.getClass().getResource("/home.png")));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(255, 250, 250));
		btnHome.setBounds(58, 23, 73, 49);
		frame.getContentPane().add(btnHome);
	}
}
