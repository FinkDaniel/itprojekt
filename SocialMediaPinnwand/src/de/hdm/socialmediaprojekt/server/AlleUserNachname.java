package de.hdm.socialmediaprojekt.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.User;

public class AlleUserNachname {

	public static void main(String[] args) {
		    Connection con = LocalDBConnection.connection();
		    Vector<User> result = new Vector<User>();
		    try {
		      Statement stmt = con.createStatement();

		      ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname, nickname "
		          + "FROM users " + "ORDER BY nachname");
		      while (rs.next()) {
		        User u = new User();
		        u.setId(rs.getInt("id"));
		        u.setVorname(rs.getString("vorname"));
		        u.setNachname(rs.getString("nachname"));
		        u.setNickname(rs.getString("nickname"));
		        result.addElement(u);
		      }
		    }
		    catch (SQLException e) {
		      e.printStackTrace();
		    }
		    System.out.println(result);
		  }}
