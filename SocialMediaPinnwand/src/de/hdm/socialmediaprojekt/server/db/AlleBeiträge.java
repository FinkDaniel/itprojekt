package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Beitrag;

public class AlleBeiträge {

	public static void main(String[] args) {
		Connection con = LocalDBConnection.connection();
		   
	    Vector<Beitrag> result = new Vector<Beitrag>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, sourceUser, beitrag "
	          + "FROM beitrag " + "ORDER BY sourceUser");

	        while (rs.next()) {
	        Beitrag b = new Beitrag();
	        b.setId(rs.getInt("id"));
	        b.setBeitrag(rs.getString("beitrag"));
	        b.setSourceUserID(rs.getInt("sourceUser"));

	       
	        result.addElement(b);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    System.out.println(result);

}
}