package Member4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Common.Home;
import Member1.DBConnect;
//import Member4.DBConnecter;
//import timetableManager.DBConnecter;
import net.proteanit.sql.DbUtils;

import java.awt.ScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Location {

	private JFrame frame;
	private JTextField roomName;
	private JTextField buldingName;
	private JTextField floorNum;
	private JTextField capC;
	private JRadioButton mlHall;
	private JRadioButton lHall;
	private JRadioButton laBo;
	private JTable table;
	String loc_id;
	String rtype;
	String type;
	private JTextField serachRoom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Location window = new Location();
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
	public Location() {
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.setForeground(SystemColor.textHighlight);
		frame.getContentPane().setForeground(SystemColor.textHighlight);
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				showData();
				
			}
			});

		frame.setBounds(100, 100, 1128, 769);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Manage Location (Lecture Hall & Laboratory)");
		lblNewLabel.setFont(new Font("Tekton Pro", Font.BOLD, 31));
		lblNewLabel.setBounds(298, 29, 657, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Room Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(32, 119, 109, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Bulding Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_1.setBounds(32, 171, 109, 24);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Floor Number");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_1_1.setBounds(32, 232, 135, 17);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Type");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(32, 298, 46, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		lHall = new JRadioButton("Lecture Hall");
		lHall.setFont(new Font("Tahoma", Font.BOLD, 14));
		lHall.setBackground(new Color(173, 216, 230));
		lHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lHall.isSelected()) 
				{
					mlHall.setSelected(false);
					laBo.setSelected(false);
				}
			}
		});
		lHall.setBounds(32, 330, 120, 23);
		frame.getContentPane().add(lHall);
		
		mlHall = new JRadioButton("Min Lecture Hall");
		mlHall.setFont(new Font("Tahoma", Font.BOLD, 14));
		mlHall.setBackground(new Color(173, 216, 230));
		mlHall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mlHall.isSelected()) 
				{
					lHall.setSelected(false);
					laBo.setSelected(false);
				}
				
			}
		});
		mlHall.setBounds(169, 330, 143, 23);
		frame.getContentPane().add(mlHall);
		
		laBo = new JRadioButton("Laboratory");
		laBo.setFont(new Font("Tahoma", Font.BOLD, 14));
		laBo.setBackground(new Color(173, 216, 230));
		laBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(laBo.isSelected()) 
				{
					mlHall.setSelected(false);
					lHall.setSelected(false);
				}
			}
		});
		laBo.setBounds(332, 330, 109, 23);
		frame.getContentPane().add(laBo);
		
		JButton btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setIcon(new ImageIcon(this.getClass().getResource("/remove.png")));
		btnNewButton_1.setBackground(new Color(224, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = DBConnect.connect();
					String query="Delete from location where loc_id='"+loc_id+"'";
					PreparedStatement pst=con.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Data Deleted");
					  showData();
					pst.close();
					
					}
					catch(Exception en) {
						en.printStackTrace();
						
					}
			}
		});
		btnNewButton_1.setBounds(32, 574, 408, 57);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnsavloc = new JButton("ADD");
		btnsavloc.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnsavloc.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		btnsavloc.setBackground(new Color(224, 255, 255));
		btnsavloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomname = roomName.getText();
				String buldingname = buldingName.getText();
				String floornum = floorNum.getText();
				rtype = null;
				String cap = capC.getText();
				
				if(lHall.isSelected())
				{
					rtype = "lecture hall";
				}
				if(mlHall.isSelected())
				{
					rtype = "min lecture hall";
				}
				if(laBo.isSelected())
				{
					rtype = "laboratory";
				}
				
				 try {
					 Connection con = DBConnect.connect();

	                    String query = "INSERT INTO location values(null, '" + roomname + "','" + buldingname + "','" + floornum + "', '"+ rtype +"' ,'"+ cap +"')";
	                    //type eka enter karanna hv by radio buttons
	                    Statement sta = con.createStatement();
	                    int x = sta.executeUpdate(query);
	                    if (x == 0) {
	                        JOptionPane.showMessageDialog(btnsavloc, "This is alredy exist");
	                    } else {
	                        JOptionPane.showMessageDialog(btnsavloc,
	                            "Welcome, Your account is sucessfully created");
	                        showData();
	                    }
	                    con.close();
	                } catch (Exception exception) {
	                    exception.printStackTrace();
	                	
	                }

				
			}
		});
		
	 
		btnsavloc.setBounds(32, 428, 405, 60);
		frame.getContentPane().add(btnsavloc);
		
		JButton btnNewButton_3 = new JButton("CLEAR");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_3.setIcon(new ImageIcon(this.getClass().getResource("/clear4.png")));
		btnNewButton_3.setBackground(new Color(224, 255, 255));
		btnNewButton_3.setBounds(32, 644, 408, 57);
		frame.getContentPane().add(btnNewButton_3);
		
		roomName = new JTextField();
		roomName.setBounds(32, 135, 409, 23);
		frame.getContentPane().add(roomName);
		roomName.setColumns(10);
		
		buldingName = new JTextField();
		buldingName.setBounds(32, 196, 409, 23);
		frame.getContentPane().add(buldingName);
		buldingName.setColumns(10);
		
		floorNum = new JTextField();
		floorNum.setBounds(32, 250, 409, 26);
		frame.getContentPane().add(floorNum);
		floorNum.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(489, 171, 591, 530);
		frame.getContentPane().add(scrollPane_1);
		
		

		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int selectedRow=table.getSelectedRow();
				loc_id = table.getValueAt(selectedRow, 0).toString();
				roomName.setText(table.getValueAt(selectedRow, 1).toString());
				buldingName.setText(table.getValueAt(selectedRow, 2).toString());
				floorNum.setText(table.getValueAt(selectedRow, 3).toString());
				capC.setText(table.getValueAt(selectedRow, 5).toString());
				
			    type = table.getValueAt(selectedRow, 4).toString();
			    System.out.println(type);
				
			    if(type.equals("lecture hall"))
			    {
			    	lHall.setSelected(true);	
			    	mlHall.setSelected(false);	
			    	laBo.setSelected(false);	
			    }
			    else if(type.equals("min lecture hall"))
			    {			    	
			    	lHall.setSelected(false);	
			    	mlHall.setSelected(true);	
			    	laBo.setSelected(false);	
			    }
			    else if (type.equals("laboratory"))
			    {			    	
			    	lHall.setSelected(false);
			    	mlHall.setSelected(false);	
			    	laBo.setSelected(true);	
			    }
//			    for(int i=0; i<Rtag.getItemCount();i++) {
//					if(Rtag.getItemAt(i).toString().equalsIgnoreCase(comboLevel)) {
//						Rtag.setSelectedIndex(i);
//					}
//				}
					
			}
		});
		scrollPane_1.setViewportView(table);
		
		JLabel lblNewLabel_1_2 = new JLabel("Capacity");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_2.setBounds(32, 365, 69, 23);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		capC = new JTextField();
		capC.setBounds(32, 385, 405, 26);
		frame.getContentPane().add(capC);
		capC.setColumns(10);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setIcon(new ImageIcon(this.getClass().getResource("/update.png")));
		btnUpdate.setBackground(new Color(224, 255, 255));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(roomName.getText().equals("")||buldingName.getText().equals("")||floorNum.getText().equals("")||capC.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please fill the form");
				}else {
				try {													
					
					if(lHall.isSelected())
					{
						rtype = "lecture hall";
					}
					if(mlHall.isSelected())
					{
						rtype = "min lecture hall";
					}
					if(laBo.isSelected())
					{
						rtype = "laboratory";
					}
					
					Connection con = DBConnect.connect();				
					String query="Update location set roomName='"+roomName.getText()+"',buldingName='"+buldingName.getText()+"',floorNum='"+floorNum.getText()+"',roomType='"+ rtype +"', cap='"+capC.getText()+"' where loc_id='"+loc_id+"'";
					PreparedStatement pst=con.prepareStatement(query);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Data Updated");
					showData();
					pst.close();
					
				}
				catch(Exception ea) {
					ea.printStackTrace();
				}
				}
				
			}
		});
		btnUpdate.setBounds(32, 501, 408, 60);
		frame.getContentPane().add(btnUpdate);
		
		serachRoom = new JTextField();
		serachRoom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel defulttablemodel = (DefaultTableModel)table.getModel();
				String search = serachRoom.getText().toString();
				TableRowSorter<DefaultTableModel>tr = new TableRowSorter<DefaultTableModel>(defulttablemodel);
				table.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		serachRoom.setColumns(10);
		serachRoom.setBounds(759, 135, 321, 23);
		frame.getContentPane().add(serachRoom);
		
		JLabel lblNewLabel_1_3 = new JLabel("Search ");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1_3.setBounds(694, 138, 59, 20);
		frame.getContentPane().add(lblNewLabel_1_3);
		
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
		btnHome.setBounds(32, 29, 73, 49);
		frame.getContentPane().add(btnHome);
	}
	
	private void showData()
	{
		Connection con = DBConnect.connect();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Loc ID");
		model.addColumn("Room Name");
		model.addColumn("Building Name");
		model.addColumn("Floor Number");
		model.addColumn("Room Type");
		model.addColumn("Capacity");
		try {
			String query = "Select *from location";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(query);
			while(rs.next())
			{
				model.addRow(new Object[] {
						rs.getString("loc_id"),
						rs.getString("roomName"),
						rs.getString("buldingName"),
						rs.getString("floorNum"),
						rs.getString("roomType"),
						rs.getString("cap")
				});
			}
			
			rs.close();
			sta.close();
			con.close();
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(60);
			table.getColumnModel().getColumn(1).setPreferredWidth(100);
			table.getColumnModel().getColumn(2).setPreferredWidth(100);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(150);
			table.getColumnModel().getColumn(5).setPreferredWidth(80);
		}
		catch(Exception ex)
		{
			System.out.println("Error: " + ex.getMessage());
		}
	}
}

