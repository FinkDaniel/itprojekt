package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;



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
          .executeQuery("SELECT id, vorname, nachname, nickname, password FROM users "
              + "WHERE id=" + id + " ORDER BY nachname");

     
      if (rs.next()) {
     
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setVorname(rs.getString("vorname"));
        u.setNachname(rs.getString("nachname"));
        u.setNickname(rs.getString("nickname"));
//        u.setPassword(rs.getString("password"));

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

      ResultSet rs = stmt.executeQuery("SELECT id, vorname, nachname, nickname, email "
          + "FROM users " + "ORDER BY nachname");
      while (rs.next()) {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setVorname(rs.getString("vorname"));
        u.setNachname(rs.getString("nachname"));
        u.setNickname(rs.getString("nickname"));
        u.setEmail(rs.getString("email"));

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
              + "FROM users " + "ORDER BY nachname");
      while (rs.next()) {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setVorname(rs.getString("vorname"));
        u.setNachname(rs.getString("nachname"));
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

        stmt.executeUpdate ("INSERT INTO users(id, vorname, nachname, nickname, email)"
            + "VALUES  ('" + u.getId() + "','" + u.getVorname() + "','"
            + u.getNachname() +  "','" + u.getNickname()+ "','" + u.getEmail()+ "')");
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

  public Pinnwand getPinnwandOf(User u) {
	   
	    return PinnwandMapper.pinnwandMapper().findBySourceUser(u);
	  }
  
  public User findByEmail(String email) {
	  
	  Connection con = LocalDBConnection.connection();
	  
	  try {
		    
	      Statement stmt = con.createStatement();

	 
	      ResultSet rs = stmt
	          .executeQuery("SELECT * FROM `users` WHERE `email` = "+email);
	      
	      if (rs.next()) {
	    	     
	          User u = new User();
	          u.setId(rs.getInt("id"));
	          u.setVorname(rs.getString("vorname"));
	          u.setNachname(rs.getString("nachname"));
	          u.setNickname(rs.getString("nickname"));
	          u.setEmail(rs.getString("email"));
	          return u;
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	    }
	return null;

	    
	  }
  }
