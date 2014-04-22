package de.hdm.socialmediaprojekt.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.*;
import de.hdm.socialmediaprojekt.server.LocalDBConnection;



public class UserMapper {

private static UserMapper userMapper = null;

  
  	protected UserMapper() {
  	}


  	public static UserMapper userMapper() {
    if (userMapper == null) {
      userMapper = new UserMapper();
    }

    return userMapper;
  	}

 
  public User
  	findByKey(int id) {

    Connection con = LocalDBConnection.connection();

    try {
    
      Statement stmt = con.createStatement();

 
      ResultSet rs = stmt
          .executeQuery("SELECT Uid, vorname, nachname, nichkname FROM users "
              + "WHERE Uid=" + id + " ORDER BY lastName");

     
      if (rs.next()) {
     
        User c = new User();
        c.setId(rs.getInt("id"));
        c.setVorname(rs.getString("vorname"));
        c.setNachname(rs.getString("nachname"));
        c.setNickname(rs.getString("nickname"));

        return c;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return null;
  }

 
  public Vector<User> findAll() {
    Connection con = LocalDBConnection.connection();
    Vector<User> result = new Vector<User>();
    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname, nickname "
          + "FROM users " + "ORDER BY lastName");
      while (rs.next()) {
        User c = new User();
        c.setId(rs.getInt("id"));
        c.setVorname(rs.getString("vorname"));
        c.setNachname(rs.getString("nachname"));
        c.setNickname(rs.getString("nickanme"));
        result.addElement(c);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

 
  public Vector<User> findByNachname(String name) {
    Connection con = LocalDBConnection.connection();
    Vector<User> result = new Vector<User>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname, nickname "
              + "FROM users " + "ORDER BY lastName");
      while (rs.next()) {
        User c = new User();
        c.setId(rs.getInt("id"));
        c.setVorname(rs.getString("firstName"));
        c.setNachname(rs.getString("lastName"));
        result.addElement(c);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

 
  public User insert(User c) {
    Connection con = LocalDBConnection.connection();
    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM users ");
      if (rs.next()) {
        
        c.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();
        stmt.executeUpdate ("INSERT INTO users (id, vorname, nachname, nickname) "
            + "VALUES (" + c.getId() + "," + c.getVorname() + ","
            + c.getNachname() +  "," + c.getNickname()+ ")");
           }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return c;
    }

  
  public User update(User c) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE users " + "SET vorname=\""
          + c.getVorname() + "\", " + "nachname=\"" + c.getNachname() + "\" , " + "nickname=\"" + c.getNickname() + "\" "
          + "WHERE id=" + c.getId());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return c;
  }

  
  public void delete(User c) {
    Connection con = LocalDBConnection.connection();
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate("DELETE FROM users " + "WHERE id=" + c.getId());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Vector<Pinnwand> getPinnwandOf(User c) {
	    /*
	     * Wir bedienen uns hier einfach des AccountMapper. Diesem geben wir einfach
	     * den in dem Customer-Objekt enthaltenen Primärschlüssel.Der CustomerMapper
	     * löst uns dann diese ID in eine Reihe von Konto-Objekten auf.
	     */
	    return PinnwandMapper.pinnwandMapper().findByOwner(c);
	  }
	}
