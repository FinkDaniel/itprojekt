package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;



public class LocalDBConnection {
	 
	private static Connection con = null;

	public static Connection connection() {
		
		try {
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				 con = DriverManager.getConnection(
				"jdbc:mysql://localhost/socialmediapinnwand","demo","demo");
				con.setReadOnly(false);
				
			}
		catch(Exception e){
				
				System.out.println("*****LOCAL DB FEHLER*****->"+e);
			}
		return con;
		
	}
	
}

