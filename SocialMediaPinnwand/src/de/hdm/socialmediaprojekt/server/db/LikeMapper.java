package de.hdm.socialmediaprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.*;


public class LikeMapper {


  private static LikeMapper likeMapper = null;

 
  protected LikeMapper() {
  }


  public static LikeMapper likeMapper() {
    if (likeMapper == null) {
      likeMapper = new LikeMapper();
    }

    return likeMapper;
  }

 
  public Like findByKey(int id) {
 
    Connection con = LocalDBConnection.connection();

    try {
      
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt
          .executeQuery("SELECT id, sourceUser, targetBeitrag FROM gelikt "
              + "WHERE id=" + id + " ORDER BY sourceUser");

      
      if (rs.next()) {
       
        Like l = new Like();
        l.setId(rs.getInt("id"));
        l.setSourceUserID(rs.getInt("sourceUser"));
        l.setTargetBeitragID(rs.getInt("targetBeitrag"));
        return l;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

 
  public Vector<Like> findAll() {
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

    return result;
  }

 
  public Vector<Like> findBySourceUser(int userID) {
    Connection con = LocalDBConnection.connection();
    Vector<Like> result = new Vector<Like>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt
          .executeQuery("SELECT id, sourceUser, targetBeitrag FROM gelikt "
              + "WHERE sourceUser=" + userID + " ORDER BY id");

      
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

    return result;
  }

  
  public Vector<Like> findByTargetBeitrag(int beitragID) {
    Connection con = LocalDBConnection.connection();
    Vector<Like> result = new Vector<Like>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt
          .executeQuery("SELECT id, sourceUser, targetBeitrag FROM gelikt "
              + "WHERE targetBeitrag=" + beitragID + " ORDER BY id");

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

    return result;
  }

  public Like insert(Like l) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM gelikt ");

      if (rs.next()) {
       
        l.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        
        stmt.executeUpdate("INSERT INTO gelikt (id, sourceUser, targetBeitrag) "
            + "VALUES ("
            + l.getId()
            + ","
            + l.getSourceUserID()
            + ","
            + l.getTargetBeitragID() +")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return l;
  }

  public void delete(Like l) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM gelikt " + "WHERE id=" + l.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

 
  public void deleteLikeOf(Beitrag l) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM gelikt " + "WHERE sourceUser="
          + l.getId());
      stmt.executeUpdate("DELETE FROM gelikt " + "WHERE targetBeitrag="
          + l.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

 
  public User getSourceUser(Like l) {
    
    return UserMapper.userMapper().findByKey(l.getSourceUserID());
  }

 
  public Beitrag getTargetBeitrag(Like l) {
    
    return BeitragMapper.beitragMapper().findByKey(l.getTargetBeitragID());
  }
  


}
