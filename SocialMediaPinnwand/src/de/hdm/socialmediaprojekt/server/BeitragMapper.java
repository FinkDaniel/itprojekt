package de.hdm.socialmediaprojekt.server;

import java.sql.*;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.*;


public class BeitragMapper {


  private static BeitragMapper beitragMapper = null;


  protected BeitragMapper() {
  }

  
  public static BeitragMapper beitragMapper() {
    if (beitragMapper == null) {
      beitragMapper = new BeitragMapper();
    }

    return beitragMapper;
  }


  public Beitrag findByKey(int id) {
   
    Connection con = LocalDBConnection.connection();

    try {
      
      Statement stmt = con.createStatement();
      ResultSet rs = stmt
    		  .executeQuery("SELECT id, sourceUser, beitrag FROM beitrag "
              + "WHERE id=" + id + " ORDER BY sourceUser");

      if (rs.next()) {
       
        Beitrag b = new Beitrag();
        b.setId(rs.getInt("id"));
        b.setBeitrag(rs.getString("beitrag"));
        b.setSourceUserID(rs.getInt("sourceUser"));

        return b;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return null;
  }

  public Vector<Beitrag> findAll() {
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

    return result;
  }


  public Vector<Beitrag> findBySourceUser(int sourceID) {
    Connection con = LocalDBConnection.connection();
    Vector<Beitrag> result = new Vector<Beitrag>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, sourceUser, beitrag "
          + "FROM beitrag " + "WHERE sourceUser" + sourceID
          + "ORDER BY sourceUser");

      
      while (rs.next()) {
        Beitrag b = new Beitrag();
        b.setId(rs.getInt("id"));
        b.setBeitrag(rs.getString("beitrag"));
        b.setSourceUserID(rs.getInt("sourceID"));
      
        result.addElement(b);
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  public Vector<Beitrag> findByBeitrag(String beitrag) {
	    Connection con = LocalDBConnection.connection();
	    Vector<Beitrag> result = new Vector<Beitrag>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, sourceUser, beitrag FROM beitrag "
	              + "WHERE sourceUser=" + beitrag + " ORDER BY id");
	    		  
//	    		  ("SELECT id, sourceUser, beitrag "
//	          + "FROM beitrag " + "WHERE sourceUser" + id
//	          + "ORDER BY id");

      
	      while (rs.next()) {
	        Beitrag b = new Beitrag();
	        b.setId(rs.getInt("id"));
	        b.setBeitrag(rs.getString("beitrag"));
	        b.setSourceUserID(rs.getInt("sourceID"));
	      
	        result.addElement(b);
	      }
	    }
	    catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return result;
	  }
  public Beitrag insert(Beitrag b) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM beitrag ");

      if (rs.next()) {
        
        b.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        stmt.executeUpdate("INSERT INTO beitrag (id, beitrag, sourceUser) "
            + "VALUES (" + b.getId() + ",'" + b.getBeitrag() + "','"
            + b.getSourceUserID() + "')");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return b;
  }

  public Beitrag update(Beitrag b) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE beitrag " + "SET beitrag=\""
          + b.getBeitrag() + "\", " + "sourceID=\"" + b.getSourceUserID() + "\" "
          + "WHERE id=" + b.getId());

    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return b;
  }

  public void delete(Beitrag b) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM beitrag " + "WHERE id=" + b.getId());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public User getSourceID(Beitrag b) {
	    
	    return UserMapper.userMapper().findByKey(b.getId());
	    		
	    		
  }
}

