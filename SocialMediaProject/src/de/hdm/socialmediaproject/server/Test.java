package de.hdm.socialmediaproject.server;

import java.sql.*;

public class Test {
	
	public static void main(String[] args){
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/bankproject","demo","demo");
			con.setReadOnly(true);
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from customers");
			
			while(rs.next()){
				
				System.out.println(rs.getInt(1)+ " "+rs.getString(2));
				
				
			}
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e){
		
			System.out.println("*****FEHLER*****->" +e);
			
			
	}
		
		
	}
	
}