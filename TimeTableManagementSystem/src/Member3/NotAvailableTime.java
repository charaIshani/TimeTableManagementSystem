package Member3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

//import notAvailableTime.DBconnection;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Common.Home;
import Member1.DBConnect;
import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

public class NotAvailableTime {

	private JFrame frame;
	private JComboBox comboBoxname;
	private JComboBox comboBox2;
	private JComboBox  comboBox3;
	private JComboBox  comboBox4;
	private JComboBox  comboBox5;
	private JComboBox  comboBox6;
	private JTextField start;
	private JTextField end;
	private JTable table1;
	int aid;
	DefaultTableModel model;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JButton btnHome;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotAvailableTime window = new NotAvailableTime();
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
	public NotAvailableTime() {
		initialize();
		fillComboBox();
		fillComboBox2();
		fillComboBox3();
		fillComboBox4();
		fillComboBox5();
		tableAction();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	//****************************fill combobox******************************************************* 
		public void fillComboBox() {
			try {
				Connection con = DBConnect.connect();
				 String query ="select LecturerName from Employee";
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  while(rs.next()) {
					  String name=rs.getString("LecturerName");
					  comboBoxname.addItem(name); 
				}	  
			}catch (Exception e){
				 
				 e.printStackTrace();
				
			}	
		}
		
		//combobox 2
		public void fillComboBox2() {
				
				try {
					
					Connection con = DBConnect.connect();
					  String query ="select GroupID from studentGroups";
					  PreparedStatement pst =con.prepareStatement(query);
					  ResultSet rs = pst.executeQuery();
					  
					  while(rs.next()) {
						  
						  String year=rs.getString("GroupID");
						  comboBox2.addItem(year);
						 
					}
					
					  
				}catch (Exception e){
					 
					 e.printStackTrace();
					
				}
				
			}

		//combobox 3
		public void fillComboBox3() {
			
			try {
				Connection con = DBConnect.connect();
				  String query ="select * from studentGroups";
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  while(rs.next()) {
					  
					  String age=rs.getString("SubGroupID");
					  comboBox3.addItem(age);
					 
				}
				
				  
			}catch (Exception e){
				 
				 e.printStackTrace();
				
			}
			
		}
		public void fillComboBox4() {
			
			try {
				Connection con = DBConnect.connect();
				  String query ="select ID from Session";
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  while(rs.next()) {
					  
					  String age=rs.getString("ID");
					  comboBox4.addItem(age);
					 
				}
				
				  
			}catch (Exception e){
				 
				 e.printStackTrace();
				
			}
			
		}
		public void fillComboBox5() {
			
			try {
				Connection con = DBConnect.connect();
				  String query ="select roomName from rooms";
				  PreparedStatement pst =con.prepareStatement(query);
				  ResultSet rs = pst.executeQuery();
				  
				  while(rs.next()) {
					  
					  String age=rs.getString("roomName");
					  comboBox5.addItem(age);
					 
				}
				
				  
			}catch (Exception e){
				 
				 e.printStackTrace();
				
			}
			
		}


		//***************fetch data from the database and set to jtable *****************
		private void tableAction(){
		    // TODO Auto-generated method stub

		   try {
			
			   Connection con = DBConnect.connect();
			    String query="select * from notTable ";
			    PreparedStatement pst=con.prepareStatement(query);
			    ResultSet rs=pst.executeQuery();
			
			    table1.setModel(DbUtils.resultSetToTableModel(rs));	
		  }
		catch(Exception ex) {
			ex.printStackTrace();
		  }

		 }
	
	private void initialize() {
		
		Connection conn = DBConnect.connect();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1129, 768);
		frame.getContentPane().setBackground(new Color(135, 206, 235));
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBoxname = new JComboBox();
		comboBoxname.setBounds(72, 145, 311, 22);
		frame.getContentPane().add(comboBoxname);
		
	    comboBox2 = new JComboBox();
		comboBox2.setBounds(72, 217, 311, 22);
		frame.getContentPane().add(comboBox2);
		
	    comboBox3 = new JComboBox();
		comboBox3.setBounds(72, 286, 311, 22);
		frame.getContentPane().add(comboBox3);
		
		 comboBox4 = new JComboBox();
		comboBox4.setBounds(72, 362, 311, 22);
		frame.getContentPane().add(comboBox4);
		
		 comboBox5 = new JComboBox();
		comboBox5.setBounds(72, 420, 311, 22);
		frame.getContentPane().add(comboBox5);
		
		comboBox6 = new JComboBox();
		comboBox6.setModel(new DefaultComboBoxModel(new String[]{"","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"}));
		comboBox6.setBounds(72, 490, 311, 22);
		frame.getContentPane().add(comboBox6);
		
		//***************************************************Save button*********************************************
		JButton save = new JButton("ADD");
		save.setIcon(new ImageIcon(this.getClass().getResource("/add.png")));
		save.setFont(new Font("Tahoma", Font.BOLD, 14));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
                   try {
                	   
                   String query ="insert into notTable(lecture,grp,sub,sID,room,day,startTime,endTime) values(?,?,?,?,?,?,?,?)";
		    	   PreparedStatement pst = conn.prepareStatement(query);
		    	   
		    	   
		    	   pst.setString(1,comboBoxname.getSelectedItem().toString());
		    	   pst.setString(2,comboBox2.getSelectedItem().toString());
		    	   pst.setString(3,comboBox3.getSelectedItem().toString());
		    	   pst.setString(4,comboBox4.getSelectedItem().toString());
		    	   pst.setString(5,comboBox5.getSelectedItem().toString());
		    	   pst.setString(6,comboBox6.getSelectedItem().toString());
		    	   pst.setString(7,start.getText());
		    	   pst.setString(8,end.getText());
		    	   
		    	   pst.execute();
		    	   
		    	   JOptionPane.showMessageDialog(null, "data Saved");
		    	   
                   }catch(Exception e1) {
                	   
                	   System.out.println(e1);
                	   
                   }
                   tableAction();
			}
		});
		save.setBounds(62, 595, 156, 46);
		frame.getContentPane().add(save);
		
		JButton update = new JButton("UPDATE");
		update.setIcon(new ImageIcon(this.getClass().getResource("/update.png")));
		update.setFont(new Font("Tahoma", Font.BOLD, 14));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id =aid;
				String lectureA = comboBoxname.getSelectedItem().toString();
                String grpA = comboBox2.getSelectedItem().toString();
                String subA =comboBox3.getSelectedItem().toString();
                String sIDA=comboBox4.getSelectedItem().toString();
		    	   String roomA=comboBox5.getSelectedItem().toString();
		    	   String dayA=comboBox6.getSelectedItem().toString();
		    	   String startTimeA=start.getText().toString();
		    	   String endTimeA=end.getText().toString();
				
				
              try {    
            	  
              
					String query="UPDATE notTable set lecture='"+lectureA+"',grp='"+grpA+"',sub='"+subA+"',sID='"+sIDA+"',room='"+roomA+"',day='"+dayA+"',startTime='"+startTimeA+"',endTime='"+endTimeA+"'  where id='"+id+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "updated");
					
				
							
				}catch(Exception ea) {
					
					ea.printStackTrace();
				}	
		    	   
              tableAction();  
				  
			}
		});
		
		update.setBounds(230, 595, 156, 46);
		frame.getContentPane().add(update);
		
		JButton delete = new JButton("DELETE");
		delete.setIcon(new ImageIcon(this.getClass().getResource("/remove.png")));
		delete.setFont(new Font("Tahoma", Font.BOLD, 14));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			try {
				Connection con = DBConnect.connect();
					String query="Delete from notTable where id='"+aid+"'";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "data delete");
					pst.close();	
					//call the table load function
					tableAction();
				}catch(Exception en) {
						en.printStackTrace();
							
				}	
				
				
			}
				
				
		
				
		});
		delete.setBounds(62, 654, 156, 43);
		frame.getContentPane().add(delete);
		
		JButton clear = new JButton("CLEAR");
		clear.setIcon(new ImageIcon(this.getClass().getResource("/clear4.png")));
		clear.setFont(new Font("Tahoma", Font.BOLD, 14));
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				start.setText(null);
		    	end.setText(null);
		    	comboBoxname.setSelectedIndex(0);
		    	comboBox2.setSelectedIndex(0);
		    	comboBox3.setSelectedIndex(0);
		    	comboBox4.setSelectedIndex(0);
		    	comboBox5.setSelectedIndex(0);
		    	comboBox6.setSelectedIndex(0);
			}
		});
		clear.setBounds(230, 654, 153, 43);
		frame.getContentPane().add(clear);
		
		JList list = new JList();
		list.setBounds(10, 48, 1, 1);
		frame.getContentPane().add(list);
		
		start = new JTextField();
		start.setBounds(69, 560, 122, 22);
		frame.getContentPane().add(start);
		start.setColumns(10);
		
		end = new JTextField();
		end.setBounds(242, 560, 141, 22);
		frame.getContentPane().add(end);
		end.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(440, 123, 589, 550);
		frame.getContentPane().add(scrollPane);
		
		table1 = new JTable();
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int selectedRow=table1.getSelectedRow();
                  aid=(int) table1.getValueAt(selectedRow,0);
                
                String Combo1 = table1.getValueAt(selectedRow, 1).toString();
				for(int i=0; i<comboBoxname.getItemCount();i++) {
					if(comboBoxname.getItemAt(i).toString().equalsIgnoreCase(Combo1)) {
						comboBoxname.setSelectedIndex(i);
					}
					
				}
				
				 String Combo2 = table1.getValueAt(selectedRow, 2).toString();
					for(int i=0; i<comboBox2.getItemCount();i++) {
						if(comboBox2.getItemAt(i).toString().equalsIgnoreCase(Combo2)) {
							comboBox2.setSelectedIndex(i);
						}
					}
					 String Combo3 = table1.getValueAt(selectedRow, 3).toString();
						for(int i=0; i<comboBox3.getItemCount();i++) {
							if(comboBox3.getItemAt(i).toString().equalsIgnoreCase(Combo3)) {
								comboBox3.setSelectedIndex(i);
						}
				     }
						
						 String Combo4= table1.getValueAt(selectedRow, 4).toString();
							for(int i=0; i<comboBox4.getItemCount();i++) {
								if(comboBox4.getItemAt(i).toString().equalsIgnoreCase(Combo4)) {
									comboBox4.setSelectedIndex(i);
								}
							}
							
							 String Combo5 = table1.getValueAt(selectedRow, 5).toString();
								for(int i=0; i<comboBox5.getItemCount();i++) {
									if(comboBox5.getItemAt(i).toString().equalsIgnoreCase(Combo5)) {
										comboBox5.setSelectedIndex(i);
									}
								}
								String Combo6= table1.getValueAt(selectedRow, 6).toString();
								for(int i=0; i<comboBox6.getItemCount();i++) {
									if(comboBox6.getItemAt(i).toString().equalsIgnoreCase(Combo6)) {
										comboBox6.setSelectedIndex(i);
									}
								}
								
								start.setText(table1.getValueAt(selectedRow,7).toString());
								end.setText(table1.getValueAt(selectedRow,8).toString());
			}  
		});
		scrollPane.setViewportView(table1);
		
		JLabel lblNewLabel = new JLabel("Not Available Time Managment");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(328, 22, 616, 52);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Lecture");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(72, 120, 187, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_3 = new JLabel("Group");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setBounds(72, 192, 187, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_2 = new JLabel("Sub Group");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(72, 261, 187, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_4 = new JLabel("Session ID");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_4.setBounds(72, 337, 187, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_5.setBounds(72, 395, 187, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Room");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_6.setBounds(72, 395, 187, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_8.setBounds(72, 478, 187, 14);
		frame.getContentPane().add(lblNewLabel_8);
		
		lblNewLabel_7 = new JLabel("Day");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_7.setBounds(72, 453, 187, 26);
		frame.getContentPane().add(lblNewLabel_7);
		
		lblNewLabel_9 = new JLabel("Time");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_9.setBounds(72, 523, 187, 26);
		frame.getContentPane().add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("To");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_10.setBounds(203, 563, 31, 14);
		frame.getContentPane().add(lblNewLabel_10);	
		
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
		btnHome.setBounds(61, 22, 73, 49);
		frame.getContentPane().add(btnHome);
	
	}
}

