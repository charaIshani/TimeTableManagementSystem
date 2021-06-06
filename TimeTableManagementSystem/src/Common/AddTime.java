package Common;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import Member1.DBConnect;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class AddTime {

	private JFrame frame;
	private JTextField sTime;
	private JTextField eTime;
	private JTable table;
	String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTime window = new AddTime();
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
	public AddTime() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1128, 770);
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
					
					String query="select * from TimeManage ";
					PreparedStatement pst=con.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));	
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}	
			}
		});
		
		JLabel lblNewLabel = new JLabel("Manage Time");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(447, 31, 209, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDay.setBounds(57, 132, 50, 25);
		frame.getContentPane().add(lblDay);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"}));
		comboBox.setBounds(57, 157, 249, 43);
		frame.getContentPane().add(comboBox);
		
		JLabel lblStartingTime = new JLabel("Starting Time");
		lblStartingTime.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblStartingTime.setBounds(57, 216, 101, 25);
		frame.getContentPane().add(lblStartingTime);
		
		sTime = new JTextField();
		sTime.setBounds(57, 244, 249, 43);
		frame.getContentPane().add(sTime);
		sTime.setColumns(10);
		
		JLabel lblStartingTime_1 = new JLabel("Ending Time");
		lblStartingTime_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblStartingTime_1.setBounds(57, 305, 101, 25);
		frame.getContentPane().add(lblStartingTime_1);
		
		eTime = new JTextField();
		eTime.setColumns(10);
		eTime.setBounds(57, 343, 249, 43);
		frame.getContentPane().add(eTime);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String comboDay= comboBox.getSelectedItem().toString();
				String startTime= sTime.getText();
				String endTime = eTime.getText();
				
				 try {
					 Connection conn = DBConnect.connect();
					//SQL Query to save data

					 String query = "INSERT INTO TimeManage  values(null,'" + comboDay + "','" + startTime + "','" + endTime + "')";
	                    
	                    Statement sta = conn.createStatement();
	                    int x = sta.executeUpdate(query);
	                    System.out.println(x);

	                    
	                    conn.close();
	                    try {
	    					//Connecting Database - W.N.S. Amaranayake - IT19009728
	    					Connection con = DBConnect.connect();
	    					
	    					String query1="select * from TimeManage ";
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
		btnNewButton.setBounds(57, 417, 249, 51);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setIcon(new ImageIcon(this.getClass().getResource("/update.png")));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnect.connect();					
					//SQL Query to update data
					String query="Update TimeManage set  id='"+id+"',day='"+comboBox.getSelectedItem()+"',stime='"+sTime.getText()+"',etime='"+eTime.getText()+"' where id="+Integer.parseInt(id);
					PreparedStatement pst=con.prepareStatement(query);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Updated");
					pst.close();
					
					try {
    					
    					String query1="select * from TimeManage ";
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
		btnUpdate.setBounds(57, 491, 249, 51);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setIcon(new ImageIcon(this.getClass().getResource("/remove.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnect.connect();
					//SQL Query to delete data
					String query="Delete from TimeManage where id="+Integer.parseInt(id);
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted");
					pst.close();
					
					try {
    					//Connecting Database - W.N.S. Amaranayake - IT19009728
    					
    					String query1="select * from TimeManage ";
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
		btnDelete.setBounds(57, 566, 249, 51);
		frame.getContentPane().add(btnDelete);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(398, 137, 663, 480);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow=table.getSelectedRow();
				
				
				 id = table.getValueAt(selectedRow, 0).toString();
				
			
				String comboDay = table.getValueAt(selectedRow, 1).toString();
				for(int i=0; i<comboBox.getItemCount();i++) {
				if(comboBox.getItemAt(i).toString().equalsIgnoreCase(comboDay)) {
					comboBox.setSelectedIndex(i);
				}
				}
				
				sTime.setText(table.getValueAt(selectedRow, 2).toString());
				eTime.setText(table.getValueAt(selectedRow, 3).toString());
			}
		});
		scrollPane_1.setColumnHeaderView(table);
		
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
		btnHome.setBounds(57, 36, 73, 49);
		frame.getContentPane().add(btnHome);
	}
}
