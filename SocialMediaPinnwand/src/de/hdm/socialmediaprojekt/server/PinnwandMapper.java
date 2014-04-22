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

   
      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM pinnwand "
          + "WHERE id=" + id + " ORDER BY owner");

      
      if (rs.next()) {
      
        Pinnwand a = new Pinnwand();
        a.setId(rs.getInt("id"));
        a.setOwnerID(rs.getInt("owner"));
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

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM pinnwand "
          + " ORDER BY id");

            while (rs.next()) {
        Pinnwand a = new Pinnwand();
        a.setId(rs.getInt("id"));
        a.setOwnerID(rs.getInt("owner"));

       
        result.addElement(a);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

   
    return result;
  }

  
  public Vector<Pinnwand> findByOwner(int ownerid) {
    Connection con = LocalDBConnection.connection();
    Vector<Pinnwand> result = new Vector<Pinnwand>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, owner FROM pinnwand "
          + "WHERE owner=" + ownerid + " ORDER BY id");

      
      while (rs.next()) {
    	Pinnwand a = new Pinnwand();
        a.setId(rs.getInt("id"));
        a.setOwnerID(rs.getInt("owner"));

        
        result.addElement(a);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    
    return result;
  }

  
  public Vector<Pinnwand> findByOwner(User owner) {

    
    return findByOwner(owner.getId());
  }

 
  public Pinnwand insert(Pinnwand a) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM pinnwand ");

    
      if (rs.next()) {
       
        a.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        
        stmt.executeUpdate("INSERT INTO pinnwand (id, owner) " + "VALUES ("
            + a.getId() + "," + a.getId() + ")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

   
    return a;
  }

 
  public Pinnwand update(Pinnwand a) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE pinnwand " + "SET owner=\"" + a.getId()
          + "\" " + "WHERE id=" + a.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

   
    return a;
  }

  public void delete(Pinnwand a) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM pinnwand " + "WHERE id=" + a.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

  
  public void deletePinnwandOf(User c) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM pinnwand " + "WHERE owner=" + c.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

 
  public User getOwner(User a) {
   
	  return UserMapper.userMapper().findByKey(a.getId());
  }

}