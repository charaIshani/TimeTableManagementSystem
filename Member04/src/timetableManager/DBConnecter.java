package timetableManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnecter {
	static Connection con=null;
	public static Connection connect() {
       
        try {
        Class.forName("org.sqlite.JDBC");
        con=DriverManager.getConnection("jdbc:sqlite:Member4.db");
//        con=DriverManager.getConnection("jdbc:sqlite:TimeTableManagerDB.db");
        
//        con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\shenel\\Downloads\\DB.Browser.for.SQLite-3.12.1-win64 (1)\\DB Browser for SQLite\\ITPM\\TimeTableManager.db");
        System.out.println("Connected");
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println(e+"");
        }
        return con;
    }
	
	

}



