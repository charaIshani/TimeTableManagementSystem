package Member02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	public static Connection connect() {
        Connection con=null;
        try {
        Class.forName("org.sqlite.JDBC");
       // con=DriverManager.getConnection("jdbc:sqlite:E:\\Y3S1\\ITPM\\newdb\\TimeTableManagement.db");
        con=DriverManager.getConnection("jdbc:sqlite:TimeTabeManagement.db");

        if (con==null)
        {
        	
        	System.out.println(" NOT Connected");
        }
        else
        {
        System.out.println("Connected");
        }
        
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println(e+"");
        }
        return con;
    }
}
