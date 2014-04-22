package de.hdm.socialmediaprojekt.server;

import java.sql.*;


public class LocalDBConnection {
	 
	private static Connection con = null;

	public static Connection connection() {
		
		try {
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				 con = DriverManager.getConnection(
						"jdbc:mysql://localhost/socialmediapinnwand","demo","demo");
				con.setReadOnly(false);
				//con.close();
			}
		catch(Exception e){
				
				System.out.println("*****FEHLER*****->" +e);
			}
		return con;
		
	}
}
