package Member1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Common.Home;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Tags {

	private JFrame frame;
	private JTextField TagName;
	private JTextField TagCode;
	private JTable table;
	private JTextField TagID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tags window = new Tags();
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
	public Tags() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				try {
					Connection con = DBConnect.connect();
					
					String query="select * from Tags ";
					PreparedStatement pst=con.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}		
			}
		});
		frame.setBounds(100, 100, 1129, 771);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		TagName = new JTextField();
		TagName.setFont(new Font("Tahoma", Font.BOLD, 14));
		TagName.setBounds(48, 210, 289, 38);
		frame.getContentPane().add(TagName);
		TagName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tag Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(48, 187, 86, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTagCode = new JLabel("Tag Code");
		lblTagCode.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTagCode.setBounds(48, 250, 86, 23);
		frame.getContentPane().add(lblTagCode);
		
		JLabel lblRelatedTag = new JLabel("Related Tag ");
		lblRelatedTag.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblRelatedTag.setBounds(48, 325, 86, 23);
		frame.getContentPane().add(lblRelatedTag);
		
		TagCode = new JTextField();
		TagCode.setFont(new Font("Tahoma", Font.BOLD, 14));
		TagCode.setColumns(10);
		TagCode.setBounds(48, 274, 289, 38);
		frame.getContentPane().add(TagCode);
		
		JComboBox Rtag = new JComboBox();
		Rtag.setFont(new Font("Tahoma", Font.BOLD, 14));
		Rtag.setModel(new DefaultComboBoxModel(new String[] {"", "Lecture", "Tutorial", "Lab"}));
		Rtag.setBounds(48, 350, 289, 35);
		frame.getContentPane().add(Rtag);
		
		//ADD Button*************************************************************************************************************************
		JButton btnAddGroup = new JButton("ADD");
		btnAddGroup.setIcon(new ImageIcon("C:\\Users\\IMAKA\\eclipse-workspace\\TimeTableManagementSystem\\Images\\add.png"));
		btnAddGroup.setBackground(new Color(224, 255, 255));
		btnAddGroup.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					String tagname= TagName.getText();
					String tagcode= TagCode.getText();
					String rtag = Rtag.getSelectedItem().toString();
					
					 try {
						 Connection con = DBConnect.connect();

		                    String query = "INSERT INTO Tags values(null, '" + tagname + "','" + tagcode + "','" + rtag + "')";

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
		                    exception.printStackTrace();
		                	
		                }
			}
		});
		btnAddGroup.setBounds(48, 417, 289, 52);
		frame.getContentPane().add(btnAddGroup);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(409, 83, 660, 569);
		frame.getContentPane().add(scrollPane);
		
		//Selecting Row ***************************************************************************************************************************
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int selectedRow=table.getSelectedRow();
			    TagID.setText(table.getValueAt(selectedRow, 0).toString());
			    TagName.setText(table.getValueAt(selectedRow, 1).toString());
			    TagCode.setText(table.getValueAt(selectedRow, 2).toString());
				
			    String comboLevel = table.getValueAt(selectedRow, 3).toString();
				
			    for(int i=0; i<Rtag.getItemCount();i++) {
					if(Rtag.getItemAt(i).toString().equalsIgnoreCase(comboLevel)) {
						Rtag.setSelectedIndex(i);
					}
				}		
			}
		});
		scrollPane.setViewportView(table);
		
		//UPDATE Button ******************************************************************************************************************************
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\IMAKA\\eclipse-workspace\\TimeTableManagementSystem\\Images\\update.png"));
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(TagName.getText().equals("")||TagCode.getText().equals("")|| Rtag.getSelectedItem().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please fill the form");
				}else {
					try {
						Connection con = DBConnect.connect();					
						String query="Update Tags set TagName='"+TagName.getText()+"',TagCode='"+TagCode.getText()+"',RelatedTag='"+Rtag.getSelectedItem()
										+"' where TagID='"+TagID.getText()+"'";
						PreparedStatement pst=con.prepareStatement(query);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Are you sure you want to UPDATE Student Group details?");
						pst.close();
						
					}catch(Exception ea) {
						ea.printStackTrace();
					}
				}	
			}
		});
		btnNewButton.setBounds(48, 482, 289, 48);
		frame.getContentPane().add(btnNewButton);
		
		TagID = new JTextField();
		TagID.setFont(new Font("Tahoma", Font.BOLD, 14));
		TagID.setEditable(false);
		TagID.setBounds(48, 136, 289, 38);
		frame.getContentPane().add(TagID);
		TagID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("TagID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(48, 114, 56, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		//DELETE Button **********************************************************************************************************************************
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\IMAKA\\eclipse-workspace\\TimeTableManagementSystem\\Images\\remove.png"));
		btnNewButton_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = DBConnect.connect();
					String query="Delete from Tags where TagID='"+TagID.getText()+"'";
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Are you sure you want to DELETE Student Group details?");
					pst.close();
					
					}
					catch(Exception en) {
						en.printStackTrace();
						
				}	
			}
		});
		btnNewButton_1.setBounds(48, 543, 289, 48);
		frame.getContentPane().add(btnNewButton_1);
		
		//CLEAR button ***************************************************************************************************************************************
		JButton TagClear = new JButton("CLEAR");
		TagClear.setIcon(new ImageIcon("C:\\Users\\IMAKA\\eclipse-workspace\\TimeTableManagementSystem\\Images\\clear1.png"));
		TagClear.setBackground(new Color(224, 255, 255));
		TagClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		TagClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TagID.setText(null);
				TagName.setText(null);
				TagCode.setText(null);
				Rtag.setSelectedItem(null);
			}
		});
		TagClear.setBounds(48, 604, 292, 48);
		frame.getContentPane().add(TagClear);
		
		JLabel lblNewLabel_2 = new JLabel("Manage Tags");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_2.setBounds(473, 13, 233, 45);
		frame.getContentPane().add(lblNewLabel_2);
		
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
		btnHome.setBounds(48, 28, 73, 49);
		frame.getContentPane().add(btnHome);
	}
}
