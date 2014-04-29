package de.hdm.socialmediaprojekt.server;

import java.sql.*;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.*;
import de.hdm.socialmediaprojekt.server.LocalDBConnection;


public class PinnwandMapper {

  private static PinnwandMapper pinnwandMapper = null;


  protected PinnwandMapper() {
  }

  
  public static PinnwandMapper pinnwandMapper() {
    if (pinnwandMapper == null) {
      pinnwandMapper = new PinnwandMapper();
    }

    return pinnwandMapper;
  }

  
  public Pinnwand findByKey(int id) {
  
    Connection con = LocalDBConnection.connection();

    try {
     
      Statement stmt = con.createStatement();

   
      ResultSet rs = stmt.executeQuery("SELECT id, sourceUser FROM pinnwand "
          + "WHERE id=" + id + " ORDER BY sourceUser");

      
      if (rs.next()) {
      
        Pinnwand a = new Pinnwand();
        a.setId(rs.getInt("id"));
        a.setSourceUserID(rs.getInt("sourceUser"));
        return a;
      }
    }
    catch (SQLException e2) {
    	 e2.printStackTrace();
      return null;
    }

    return null;
  }

  
  public Vector<Pinnwand> findAll() {
    Connection con = LocalDBConnection.connection();

   
    Vector<Pinnwand> result = new Vector<Pinnwand>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, sourceUser FROM pinnwand "
          + " ORDER BY id");

            while (rs.next()) {
        Pinnwand p = new Pinnwand();
        p.setId(rs.getInt("id"));
        p.setSourceUserID(rs.getInt("sourceUser"));

       
        result.addElement(p);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

   
    return result;
  }

  
  public Vector<Pinnwand> findBySourceUser(int sourceID) {
    Connection con = LocalDBConnection.connection();
    Vector<Pinnwand> result = new Vector<Pinnwand>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, sourceUser FROM pinnwand "
          + "WHERE sourceUser=" + sourceID + " ORDER BY id");

      
      while (rs.next()) {
    	Pinnwand p = new Pinnwand();
        p.setId(rs.getInt("id"));
        p.setSourceUserID(rs.getInt("sourceUser"));

        
        result.addElement(p);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    
    return result;
  }

  
  public Vector<Pinnwand> findBySourceUser(User sourceUser) {

    
    return findBySourceUser(sourceUser.getId());
  }

 
  public Pinnwand insert(Pinnwand p) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM pinnwand ");

    
      if (rs.next()) {
       
        p.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        
        stmt.executeUpdate("INSERT INTO pinnwand (id, sourceUser) " + "VALUES ("
            + p.getId()+ "," + p.getSourceUserID() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

   
    return p;
  }

 
  public Pinnwand update(Pinnwand p) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE pinnwand " + "SET sourceUser=\"" + p.getSourceUserID()
          + "\" " + "WHERE id=" + p.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

   
    return p;
  }

  public void delete(Pinnwand p) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM pinnwand " + "WHERE id=" + p.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  
  public void deletePinnwandOf(User u) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM pinnwand " + "WHERE sourceUser=" + u.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

 

// public User getOwner(Pinnwand u) {
   
//  return UserMapper.userMapper().findByKey(u.getId());

//  }
  
public User getSourceUser(Pinnwand p) {
	 return UserMapper.userMapper().findByKey(p.getSourceUserID());


	  

}
}