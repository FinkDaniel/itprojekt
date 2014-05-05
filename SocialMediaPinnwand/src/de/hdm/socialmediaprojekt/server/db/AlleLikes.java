package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Like;

public class AlleLikes {

	public static void main(String[] args) {
		Connection con = LocalDBConnection.connection();

	    
	    Vector<Like> result = new Vector<Like>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt
	          .executeQuery("SELECT id, sourceUser, targetBeitrag FROM gelikt "
	              + " ORDER BY id");

	        while (rs.next()) {
	        Like l = new Like();
	        l.setId(rs.getInt("id"));
	        l.setSourceUserID(rs.getInt("sourceUser"));
	        l.setTargetBeitragID(rs.getInt("targetBeitrag"));
	        
	        result.addElement(l);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    System.out.println(result);
}
}