package Member1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import net.proteanit.sql.DbUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import Common.Home;

import javax.swing.ImageIcon;

public class Students {

	private JFrame frame;
	private JTextField GID;
	private JTextField SubGroupID;
	private JTable table;
	private JComboBox AYS;
	private JComboBox Programme;
	private JSpinner GroupNumber;
	private JSpinner SubGroupNumber;
	private JTextField studentsID;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Students window = new Students();
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
	public Students() {
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
					//db connect******************************************************************************************************************************
					Connection con = DBConnect.connect();
					
					String query="select * from studentGroups ";
					PreparedStatement pst=con.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));	
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}	
			}
		});

		frame.setBounds(100, 100, 1128, 769);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox AYS = new JComboBox();
		AYS.setFont(new Font("Tahoma", Font.BOLD, 14));
		AYS.setModel(new DefaultComboBoxModel(new String[] {"", "Y1.S1", "Y1.S2", "Y2.S1", "Y2.S2", "Y3.S1", "Y3.S2", "Y4.S1", "Y4.S2"}));
		AYS.setBounds(38, 156, 320, 33);
		frame.getContentPane().add(AYS);
		
		JLabel lblNewLabel = new JLabel("Academic Year and Semester");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(38, 142, 218, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox Programme = new JComboBox();
		Programme.setFont(new Font("Tahoma", Font.BOLD, 14));
		Programme.setModel(new DefaultComboBoxModel(new String[] {"", "IT", "SE", "DS", "IM", "CS"}));
		Programme.setBounds(38, 220, 320, 33);
		frame.getContentPane().add(Programme);
		
		JLabel lblProgram = new JLabel("Program");
		lblProgram.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblProgram.setBounds(38, 202, 169, 16);
		frame.getContentPane().add(lblProgram);
		
		JLabel lblGroupNumber = new JLabel("Group Number");
		lblGroupNumber.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblGroupNumber.setBounds(38, 266, 169, 16);
		frame.getContentPane().add(lblGroupNumber);
		
		JSpinner GroupNumber = new JSpinner();
		GroupNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupNumber.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		GroupNumber.setBounds(38, 283, 320, 33);
		frame.getContentPane().add(GroupNumber);
		
		JSpinner SubGroupNumber = new JSpinner();
		SubGroupNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		SubGroupNumber.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		SubGroupNumber.setBounds(38, 345, 320, 33);
		frame.getContentPane().add(SubGroupNumber);
		
		JLabel lblSubGroupNumber = new JLabel("Sub Group Number");
		lblSubGroupNumber.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblSubGroupNumber.setBounds(38, 329, 169, 16);
		frame.getContentPane().add(lblSubGroupNumber);
		
		GID = new JTextField();
		GID.setFont(new Font("Tahoma", Font.BOLD, 14));
		GID.setEditable(false);
		GID.setBounds(38, 407, 320, 32);
		frame.getContentPane().add(GID);
		GID.setColumns(10);
		
		SubGroupID = new JTextField();
		SubGroupID.setFont(new Font("Tahoma", Font.BOLD, 14));
		SubGroupID.setEditable(false);
		SubGroupID.setColumns(10);
		SubGroupID.setBounds(38, 469, 320, 28);
		frame.getContentPane().add(SubGroupID);
		
		JLabel lblNewLabel_1 = new JLabel("Group ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(38, 391, 107, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sub Group ID");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(38, 452, 142, 16);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		//Generate ID button ********************************************************************************************************************
		JButton btnNewButton = new JButton("GENERATE ID");
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/GenerateID1.png")));
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GID.setText(AYS.getSelectedItem().toString()+"."+Programme.getSelectedItem().toString()+"."+ GroupNumber.getValue().toString());
				SubGroupID.setText(AYS.getSelectedItem().toString()+"."+Programme.getSelectedItem().toString()+"."+ GroupNumber.getValue().toString()+"."
				+SubGroupNumber.getValue().toString());	
			}
		});
		btnNewButton.setBounds(38, 510, 320, 51);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new CompoundBorder());
		scrollPane.setBounds(382, 97, 699, 586);
		frame.getContentPane().add(scrollPane);
		
		//selecting rows in table *****************************************************************************************************************
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow=table.getSelectedRow();
			    
				GID.setText(table.getValueAt(selectedRow, 5).toString());
				SubGroupID.setText(table.getValueAt(selectedRow, 6).toString());
				studentsID.setText(table.getValueAt(selectedRow, 0).toString());
				
				String comboLevel = table.getValueAt(selectedRow, 1).toString();
					for(int i=0; i<AYS.getItemCount();i++) {
						if(AYS.getItemAt(i).toString().equalsIgnoreCase(comboLevel)) {
						AYS.setSelectedIndex(i);
					}
				}
				
				String comboLevel1 = table.getValueAt(selectedRow, 2).toString();
					for(int i=0; i<Programme.getItemCount();i++) {
						if(Programme.getItemAt(i).toString().equalsIgnoreCase(comboLevel1)) {
							Programme.setSelectedIndex(i);
					}
				}
				
				GroupNumber.setValue((Integer)table.getValueAt(selectedRow, 3));
				SubGroupNumber.setValue((Integer)table.getValueAt(selectedRow, 4));
					
			}
		});
		scrollPane.setViewportView(table);
		
		//Add button **********************************************************************************************************************************
		JButton btnAddGroup = new JButton("ADD");
		btnAddGroup.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnAddGroup.setBackground(new Color(224, 255, 255));
		btnAddGroup.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ays= AYS.getSelectedItem().toString();
				String program= Programme.getSelectedItem().toString();
				String groupnumber= GroupNumber.getValue().toString();
				String subgroupnumber= SubGroupNumber.getValue().toString();
				String groupid = GID.getText();
				String subgroupid = SubGroupID.getText();
				
				if(GID.getText().equals("")||SubGroupID.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please Genarate IDs First!");
				}else {
				
				try {
					 Connection con = DBConnect.connect();

	                    String query = "INSERT INTO studentGroups values(null, '" + ays + "','" + program + "','" + groupnumber + "', '" + subgroupnumber + 
	                    		"', '" + groupid + "', '" + subgroupid + "')";

		                    Statement sta = con.createStatement();
		                    	int x = sta.executeUpdate(query);
		                    	if (x == 0) {
		                    		JOptionPane.showMessageDialog(btnAddGroup, "This is alredy exist");
		                    	} else {
		                    		JOptionPane.showMessageDialog(btnAddGroup,
		                    				"Are you sure you want to ADD Student Group details?");
		                    	}
		                    con.close();
		                } catch (Exception exception) {
		                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");	
	                }
				}	
			}
		});
		btnAddGroup.setBounds(38, 574, 161, 50);
		frame.getContentPane().add(btnAddGroup);
		
		//UPDATE button ******************************************************************************************************************************
		JButton UPDATE = new JButton("UPDATE");
		UPDATE.setIcon(new ImageIcon(this.getClass().getResource("/update.png")));
		UPDATE.setBackground(new Color(224, 255, 255));
		UPDATE.setFont(new Font("Tahoma", Font.BOLD, 14));
		UPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnect.connect();					
					String query="Update studentGroups set AcademicYearSemester='"+AYS.getSelectedItem()+"',Programme='"+Programme.getSelectedItem()
									+"',GroupNo='"+GroupNumber.getValue()+"',SubGroupNo='"+SubGroupNumber.getValue()+"',GroupID='"+GID.getText()+"',"
									+ "SubGroupID='"+SubGroupID.getText()+"' where SGroupID='"+studentsID.getText()+"'";
					PreparedStatement pst=con.prepareStatement(query);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Are you sure you want to UPDATE Student Group details?");
					pst.close();
							
				}catch(Exception ea) {
					ea.printStackTrace();
				}	
			}
		});
		UPDATE.setBounds(210, 576, 148, 46);
		frame.getContentPane().add(UPDATE);
		
		//Delete Button *************************************************************************************************************************************
		JButton btnNewButton_3 = new JButton("DELETE");
		btnNewButton_3.setIcon(new ImageIcon(this.getClass().getResource("/remove.png")));
		btnNewButton_3.setBackground(new Color(224, 255, 255));
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnect.connect();
					String query="Delete from studentGroups where SGroupID='"+studentsID.getText()+"'";
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Are you sure you want to DELETE Student Group details?");
					pst.close();	
					
				}catch(Exception en) {
						en.printStackTrace();
							
				}	
			}
		});
		btnNewButton_3.setBounds(38, 637, 160, 46);
		frame.getContentPane().add(btnNewButton_3);
		
		studentsID = new JTextField();
		studentsID.setFont(new Font("Tahoma", Font.BOLD, 14));
		studentsID.setEditable(false);
		studentsID.setBounds(37, 107, 321, 22);
		frame.getContentPane().add(studentsID);
		studentsID.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(38, 86, 56, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		
		//CLEAR BUTTON ******************************************************************************************************************
		JButton studentClear = new JButton("CLEAR");
		studentClear.setIcon(new ImageIcon(this.getClass().getResource("/clear4.png")));
		studentClear.setBackground(new Color(224, 255, 255));
		studentClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		studentClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				studentsID.setText(null);
				AYS.setSelectedItem(null);
				Programme.setSelectedItem(null);
				GroupNumber.setValue(0);
				SubGroupNumber.setValue(0);
				GID.setText("");
				SubGroupID.setText("");	
			}
		});
		studentClear.setBounds(211, 637, 148, 46);
		frame.getContentPane().add(studentClear);
		
		JLabel lblNewLabel_3 = new JLabel("Manage Student Groups");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_3.setBounds(359, 13, 450, 33);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home itm = new Home();
				Home.main(null);
				frame.setVisible(false);
			}
		});
		btnHome.setForeground(new Color(255, 255, 255));
		btnHome.setBackground(new Color(255, 250, 250));
		btnHome.setIcon(new ImageIcon(this.getClass().getResource("/home.png")));
		btnHome.setBounds(49, 13, 73, 49);
		frame.getContentPane().add(btnHome);
		

		
	}
}
