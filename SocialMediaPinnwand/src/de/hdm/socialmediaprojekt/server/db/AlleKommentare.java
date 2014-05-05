package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Kommentar;

public class AlleKommentare {

	public static void main(String[] args) {
		 Connection con = LocalDBConnection.connection();
		   
		    Vector<Kommentar> result = new Vector<Kommentar>();

		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id, sourceUser, targetBeitrag, kommentar "
		          + "FROM kommentar " + "ORDER BY sourceUser");

		        while (rs.next()) {
		        Kommentar k = new Kommentar();
		        k.setId(rs.getInt("id"));
		        k.setKommentar(rs.getString("kommentar"));
		        k.setSourceUserID(rs.getInt("sourceUser"));
		        k.setTargetBeitragID(rs.getInt("targetBeitrag"));
		        result.addElement(k);
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }

		    System.out.println(result);

}
}