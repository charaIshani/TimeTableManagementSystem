package Member3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Common.Home;
import Member1.DBConnect;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.ImageIcon;

public class time_managment extends JFrame {

	
	private JPanel contentPane;
	private JTable table1;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JCheckBox c1,c2,c3,c4,c5,c6,c7; 
	
	
	String noDay,startTime,endTme,w_DayEnd;
	String dayType;
	String wday="";
    int wid;
    DefaultTableModel model;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					time_managment frame = new time_managment();
					frame.setVisible(true);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
     Connection conn =null;
     private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * Create the frame.
	 */
     
 
    
     
	public time_managment() {
		
		Connection con = DBConnect.connect(); 
		 tableAction();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1128, 769);
		contentPane =  new JPanel();
		contentPane.setBackground(new Color(135, 206, 235));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("No of working days");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(40, 107, 203, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("weekday/weekend");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(40, 171, 154, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("working days");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(40, 261, 149, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("time slot");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setBounds(40, 427, 67, 14);
		contentPane.add(lblNewLabel_3);
		
		
		//*********************get data to jtable *********************
		tableAction();
		
		JButton add = new JButton("ADD");
		add.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		add.setFont(new Font("Tahoma", Font.BOLD, 14));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        try {
		        	String query="insert into timeMng(noWday,startTime,endTime,wday,WekkDayEnd) values(?,?,?,?,?)";
			    	   PreparedStatement pst = conn.prepareStatement(query);
		             
                     
 			    	   pst.setString(1,txt1.getText());
 			    	   pst.setString(2,txt2.getText());
 			    	   pst.setString(3,txt3.getText());
 			    	   
 			    	 //get data from check box and convert  as a one string 
 			    	   
 			    	   String wday=" ";
 			    	   
					if(c1.isSelected()) {
 			    		   
 			    		   wday+=c1.getText()+"/";
 			    		   
 			    	   }
					if(c2.isSelected()) {
			    		   
			    		   wday+=c2.getText()+"/";
			    		   
			    	   }
					if(c3.isSelected()) {
			    		   
			    		   wday+=c3.getText()+"/";
			    		   
			    	   }
					if(c4.isSelected()) {
			    		   
			    		   wday+=c4.getText()+"/";
			    		   
			    	   }
					if(c5.isSelected()) {
			    		   
			    		   wday+=c5.getText()+"/";
			    		   
			    	   }
					if(c6.isSelected()) {
			    		   
			    		   wday+=c6.getText()+"/";
			    		   
			    	   }
 			    	   
 			    	   pst.setString(4,wday);
 			    	   pst.setString(5,dayType);
 			    	   pst.execute();
 			    	   
			    	   //messege show
			    	   JOptionPane.showMessageDialog(null, "data Saved");
			    	   
			    	    pst.close();
			    	   
			    	  ;
			    	   
			    	   //call the jtable data retrive function 
			    	   
			    	   tableAction() ;
			    	  
			    	   
			    }catch(Exception e1) {
			    	System.out.println(e1);
			    }	
				
		        
			}
		});
		
		add.setBounds(40, 487, 264, 52);
		contentPane.add(add);
		
		//*********************update button**********************************
		JButton update = new JButton("UPDATE");
		update.setIcon(new ImageIcon(this.getClass().getResource("/update.png")));
		update.setFont(new Font("Tahoma", Font.BOLD, 14));
		update.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				try {
					   //get data from interface
					   int id = wid;
	 
			    	 //get data from check box and convert  as a one string 
						
					   String wday=" ";
			    	   
					   if(c1.isSelected()) {
			    		   
							wday+=c1.getText()+"/";
			    		   
							}
					   if(c2.isSelected()) {
			    		   
							wday+=c2.getText()+"/";
			    		   
							}
					   if(c3.isSelected()) {
			    		   
							wday+=c3.getText()+"/";
			    		   
							}
					   if(c4.isSelected()) {
			    		   
			    		   wday+=c4.getText()+"/";
			    		   
							}
					   if(c5.isSelected()) {
			    		   
			    		   wday+=c5.getText()+"/";   
			    	   		}
					   if(c6.isSelected()) {
			    		   
			    		   wday+=c6.getText()+"/";
			    		   
			    	   }
			    	//
					
					 String nodays=txt1.getText();
					 String sTime=txt2.getText();
					 String eTime=txt3.getText();
			         String dayTyp =dayType;
			         
			         
					String query="UPDATE timeMng set  noWday='"+nodays+"',startTime='"+sTime+"',endTime='"+eTime+"',wday='"+wday+"',WekkDayEnd='"+dayTyp+"'  where wid='"+id+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					
					  
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "updated");
					tableAction() ;  
					
			      	   pst.close();
							
				}catch(Exception ea) {
					
					ea.printStackTrace();
				}	
		    	   
				
				
				
				
			}
		});
		
		update.setBounds(40, 543, 264, 52);
		contentPane.add(update);
		
		//********************delete  function********************************** 
		
		JButton delete = new JButton("DELETE");
		delete.setIcon(new ImageIcon(this.getClass().getResource("/remove.png")));
		delete.setFont(new Font("Tahoma", Font.BOLD, 14));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
						String query="Delete from timeMng where wid='"+wid+"'";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.execute();
					
						JOptionPane.showMessageDialog(null, "data delete");
					
						pst.close();	
					
						//call the table load function
						tableAction() ;
					
				}catch(Exception en) {
						en.printStackTrace();
							
				}	
				
				
			}
		});
		delete.setBounds(40, 601, 264, 43);
		contentPane.add(delete);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(359, 106, 717, 603);
		contentPane.add(scrollPane_1);
		table1 = new JTable();
		table1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		table1.setCellSelectionEnabled(true);
		table1.setColumnSelectionAllowed(true);
		table1.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(table1);
		table1.setSurrendersFocusOnKeystroke(true);
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//**************get data of selected row by using mouse******************
				
				int selectedRow=table1.getSelectedRow();
                wid =(int) table1.getValueAt(selectedRow,0);
				txt1.setText(table1.getValueAt(selectedRow,1).toString());
				txt2.setText(table1.getValueAt(selectedRow,2 ).toString());
				txt3.setText(table1.getValueAt(selectedRow,3).toString());
				table1.getValueAt(selectedRow,4 ).toString();
			}
		});
		
		//**********************
		table1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, "", ""},
				{null, null, null, null, "", null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"ID", "No Of Days", "Start Time", "End Time", "days", "Weekday/Weekend"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Float.class, Float.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table1.getColumnModel().getColumn(0).setPreferredWidth(33);
		table1.getColumnModel().getColumn(1).setPreferredWidth(74);
		table1.getColumnModel().getColumn(2).setPreferredWidth(80);
		table1.getColumnModel().getColumn(3).setPreferredWidth(68);
		table1.getColumnModel().getColumn(4).setPreferredWidth(156);
		table1.getColumnModel().getColumn(5).setResizable(false);
		table1.getColumnModel().getColumn(5).setPreferredWidth(104);
		table1.getColumnModel().getColumn(5).setMinWidth(17);
		table1.getColumnModel().getColumn(5).setMaxWidth(113);
		
		//************get data from redio buttons*******************
		
		 final JRadioButton weekday = new JRadioButton("weekday");
		 buttonGroup.add(weekday);
		weekday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dayType = "weekday";
			}
		});
		weekday.setBounds(40, 209, 96, 23);
		contentPane.add(weekday);
		
		final JRadioButton weekend = new JRadioButton("weekend");
		weekend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dayType = "weekend";
			}
		});
		buttonGroup.add(weekend);
		weekend.setBounds(154, 209, 89, 23);
		contentPane.add(weekend);
		
		txt1 = new JTextField();
		txt1.setBounds(40, 138, 225, 20);
		contentPane.add(txt1);
		txt1.setColumns(10);
		
		txt2 = new JTextField();
		txt2.setBounds(40, 454, 100, 20);
		contentPane.add(txt2);
		txt2.setColumns(10);
		
		txt3 = new JTextField();
		txt3.setBounds(201, 454, 103, 20);
		contentPane.add(txt3);
		txt3.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(387, 164, 2, 2);
		contentPane.add(scrollPane);
		
		//****************************Clear button**************************************
		
		JButton CLEAR = new JButton("CLERE");
		CLEAR.setIcon(new ImageIcon(this.getClass().getResource("/clear4.png")));
		CLEAR.setFont(new Font("Tahoma", Font.BOLD, 14));
		CLEAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 // clear the entered data 
		    	   txt1.setText(null);
		    	   txt2.setText(null);
		    	   txt3.setText(null);
		    	   c1.setSelected(false);
		      	   c2.setSelected(false);
		      	   c3.setSelected(false);
		      	   c4.setSelected(false);
		      	   c5.setSelected(false);
		      	   c6.setSelected(false);
		      	   c7.setSelected(false);
		      	   buttonGroup.clearSelection();
		      	   
			}
		});
		CLEAR.setBounds(40, 657, 264, 52);
		contentPane.add(CLEAR);
		
		JLabel lblNewLabel_5 = new JLabel("To");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_5.setBounds(154, 456, 26, 14);
		contentPane.add(lblNewLabel_5);
		
		 c1 = new JCheckBox("SUNDAY");
		c1.setBounds(201, 349, 103, 23);
		contentPane.add(c1);
		
		 c2 = new JCheckBox("MONDAY");
		c2.setBounds(40, 293, 111, 23);
		contentPane.add(c2);
		
		 c3 = new JCheckBox("TUESDAY");
		c3.setBounds(40, 321, 111, 23);
		contentPane.add(c3);
		
		 c4 = new JCheckBox("WEDNESDAY");
		c4.setBounds(40, 349, 111, 23);
		contentPane.add(c4);
		
        c5 = new JCheckBox("THURSDAY");
		c5.setBounds(40, 377, 111, 23);
		contentPane.add(c5);
		
		c6 = new JCheckBox("FRIDAY");
		c6.setBounds(201, 293, 103, 23);
		contentPane.add(c6);
		
		 c7 = new JCheckBox("SATURDAY");
		c7.setBounds(201, 321, 103, 23);
		contentPane.add(c7);
		
		JLabel lblNewLabel_4 = new JLabel("Work time and Days Managment");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_4.setBounds(337, 11, 457, 63);
		contentPane.add(lblNewLabel_4);
		
		JButton btnHome = new JButton("");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home itm = new Home();
				Home.main(null);
				//frame.setVisible(false);
			}
		});
		btnHome.setIcon(new ImageIcon(this.getClass().getResource("/home.png")));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBackground(new Color(255, 250, 250));
		btnHome.setBounds(40, 25, 73, 49);
		contentPane.add(btnHome);
		
		JButton btnNewButton = new JButton("NOT AVAILABLE TIME");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotAvailableTime itm = new NotAvailableTime();
				NotAvailableTime.main(null);
				//frame.setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(this.getClass().getResource("/next-button.png")));
		btnNewButton.setBackground(new Color(224,255,255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(866, 13, 232, 35);
		contentPane.add(btnNewButton);
		
		
	}
	    //***************fetch data from the database and set to jtable *****************
	
	    private void tableAction() {
		
		   try {
			   
			     	String query="select * from timeMng ";
			     	PreparedStatement pst=conn.prepareStatement(query);
			     	ResultSet rs=pst.executeQuery();
			
			     	table1.setModel(DbUtils.resultSetToTableModel(rs));	
		       }
		 catch(Exception ex) {
			
			     	ex.printStackTrace();
		       }
		
	    }
	}

