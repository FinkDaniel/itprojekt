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
     
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setVorname(rs.getString("vorname"));
        u.setNachname(rs.getString("nachname"));
        u.setNickname(rs.getString("nickname"));

        return u;
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
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setVorname(rs.getString("vorname"));
        u.setNachname(rs.getString("nachname"));
        u.setNickname(rs.getString("nickanme"));
        result.addElement(u);
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
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setVorname(rs.getString("firstName"));
        u.setNachname(rs.getString("lastName"));
        result.addElement(u);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

 
  public User insert(User u) {
    Connection con = LocalDBConnection.connection();
    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM users ");
      if (rs.next()) {
        
        u.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();
        stmt.executeUpdate ("INSERT INTO users (id, vorname, nachname, nickname) "
            + "VALUES (" + u.getId() + "," + u.getVorname() + ","
            + u.getNachname() +  "," + u.getNickname()+ ")");
           }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return u;
    }

  
  public User update(User u) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE users " + "SET vorname=\""
          + u.getVorname() + "\", " + "nachname=\"" + u.getNachname() + "\" , " + "nickname=\"" + u.getNickname() + "\" "
          + "WHERE id=" + u.getId());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return u;
  }

  
  public void delete(User u) {
    Connection con = LocalDBConnection.connection();
    try {
      Statement stmt = con.createStatement();
      stmt.executeUpdate("DELETE FROM users " + "WHERE id=" + u.getId());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Vector<Pinnwand> getPinnwandOf(User u) {
	   
	    return PinnwandMapper.pinnwandMapper().findByOwner(u);
	  }
	}
