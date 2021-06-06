package notAvailableTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBconnection {
    
	public static Connection connect() {
		  Connection con = null;
		try {
			  Class.forName("org.sqlite.JDBC");
			   con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\dtc\\Desktop\\database\\time.db");
			   System.out.println("connection");
			   JOptionPane.showMessageDialog(null, "sucessfull");
			
		}catch(ClassNotFoundException |SQLException e){
			
			 JOptionPane.showMessageDialog(null, e);
			 return null;
		}
		
		 return con;
	}
    

}
