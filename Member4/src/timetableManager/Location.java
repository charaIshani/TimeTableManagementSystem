

package timetableManager;

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


//import Member4.DBConnecter;
import timetableManager.DBConnecter;
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
		frame.getContentPane().setBackground(new Color(173, 216, 230));
		frame.setForeground(SystemColor.textHighlight);
		frame.getContentPane().setForeground(SystemColor.textHighlight);
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				showData();
				
			}
			});

		frame.setBounds(100, 100, 1129, 703);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Manage Location (Lecture Hall & Laboratory)");
		lblNewLabel.setFont(new Font("Tekton Pro", Font.BOLD, 31));
		lblNewLabel.setBounds(298, 29, 637, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Room Name");
		lblNewLabel_1.setBounds(122, 110, 109, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Bulding Name");
		lblNewLabel_1_1.setBounds(122, 173, 109, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Floor Number");
		lblNewLabel_1_1_1.setBounds(122, 237, 135, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Type");
		lblNewLabel_2.setBounds(122, 299, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lHall = new JRadioButton("Lecture Hall");
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
		lHall.setBounds(182, 295, 109, 23);
		frame.getContentPane().add(lHall);
		
		mlHall = new JRadioButton("Min Lecture Hall");
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
		mlHall.setBounds(182, 330, 135, 23);
		frame.getContentPane().add(mlHall);
		
		laBo = new JRadioButton("Laboratory");
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
		laBo.setBounds(182, 366, 109, 23);
		frame.getContentPane().add(laBo);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.setBackground(new Color(240, 128, 128));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = DBConnecter.connect();
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
		btnNewButton_1.setBounds(228, 538, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnsavloc = new JButton("Add New");
		btnsavloc.setBackground(new Color(238, 232, 170));
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
					 Connection con = DBConnecter.connect();

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
		
	 
		btnsavloc.setBounds(228, 490, 89, 23);
		frame.getContentPane().add(btnsavloc);
		
		JButton btnNewButton_3 = new JButton("Clear");
		btnNewButton_3.setBackground(new Color(245, 222, 179));
		btnNewButton_3.setBounds(100, 538, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		roomName = new JTextField();
		roomName.setBounds(118, 135, 191, 23);
		frame.getContentPane().add(roomName);
		roomName.setColumns(10);
		
		buldingName = new JTextField();
		buldingName.setBounds(122, 198, 187, 23);
		frame.getContentPane().add(buldingName);
		buldingName.setColumns(10);
		
		floorNum = new JTextField();
		floorNum.setBounds(122, 262, 187, 26);
		frame.getContentPane().add(floorNum);
		floorNum.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(400, 201, 605, 360);
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
		lblNewLabel_1_2.setBounds(126, 409, 69, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		capC = new JTextField();
		capC.setBounds(122, 434, 187, 26);
		frame.getContentPane().add(capC);
		capC.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(238, 232, 170));
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
					
					Connection con = DBConnecter.connect();					
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
		btnUpdate.setBounds(100, 490, 89, 23);
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
		serachRoom.setBounds(803, 135, 157, 23);
		frame.getContentPane().add(serachRoom);
		
		JLabel lblNewLabel_1_3 = new JLabel("Search ");
		lblNewLabel_1_3.setBounds(905, 121, 69, 14);
		frame.getContentPane().add(lblNewLabel_1_3);
	}
	
	private void showData()
	{
		Connection con = DBConnecter.connect();
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

