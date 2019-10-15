package myhostelproject.hostel.hstlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class hstlconnection {
	
	public static Connection getConnection(){
		
		Connection con=null;
		System.out.println("In the connection class....");
		try{           
			
			Class.forName("com.mysql.jdbc.Driver");	// for mysql
			
		}
		catch(ClassNotFoundException cnf){
			Logger.getLogger(hstlconnection.class.getName()).log(Level.SEVERE,null,cnf);
		}
		try{							
			
			
			con=DriverManager.getConnection("jdbc:mysql://localhost/hostel_db","root","");// connection for Mysql 
					
		}								
		catch(SQLException sqe){
			Logger.getLogger(hstlconnection.class.getName()).log(Level.SEVERE,null,sqe);
		}
		return con;
	}

}
