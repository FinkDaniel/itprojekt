package de.hdm.socialmediaprojekt.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.*;


public class AboMapper {


  private static AboMapper aboMapper = null;

 
  protected AboMapper() {
  }


  public static AboMapper aboMapper() {
    if (aboMapper == null) {
      aboMapper = new AboMapper();
    }

    return aboMapper;
  }

 
  public Abo findByKey(int id) {
 
    Connection con = LocalDBConnection.connection();

    try {
      
      Statement stmt = con.createStatement();

      
      ResultSet rs = stmt
          .executeQuery("SELECT id, sourcePinnwand, targetPinnwand FROM abo "
              + "WHERE id=" + id + " ORDER BY sourcePinnwand");

      
      if (rs.next()) {
       
        Abo a = new Abo();
        a.setId(rs.getInt("id"));
        a.setSourcePinnwandID(rs.getInt("sourcePinnwand"));
        a.setTargetPinnwandID(rs.getInt("targetPinnwand"));
        return a;
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
      return null;
    }

    return null;
  }

 
  public Vector<Abo> findAll() {
    Connection con = LocalDBConnection.connection();

    
    Vector<Abo> result = new Vector<Abo>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt
          .executeQuery("SELECT id, sourcePinnwand, targetPinnwand FROM abo "
              + " ORDER BY sourcePinnwand");

        while (rs.next()) {
        Abo a = new Abo();
        a.setId(rs.getInt("id"));
        a.setSourcePinnwandID(rs.getInt("sourcePinnwandt"));
        a.setTargetPinnwandID(rs.getInt("targetPinnwand"));
        
        result.addElement(a);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return result;
  }

 
  public Vector<Abo> findBySourcePinnwand(int pinnwandID) {
    Connection con = LocalDBConnection.connection();
    Vector<Abo> result = new Vector<Abo>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt
          .executeQuery("SELECT id, sourcePinnwand, targetPinnwand FROM abo "
              + "WHERE sourcePinnwand=" + pinnwandID + " ORDER BY id");

      
      while (rs.next()) {
        Abo a = new Abo();
        a.setId(rs.getInt("id"));
        a.setSourcePinnwandID(rs.getInt("sourcePinnwand"));
        a.setTargetPinnwandID(rs.getInt("targetPinnwand"));
        
        result.addElement(a);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return result;
  }

  
  public Vector<Abo> findByTargetPinnwand(int pinnwandID) {
    Connection con = LocalDBConnection.connection();
    Vector<Abo> result = new Vector<Abo>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt
          .executeQuery("SELECT id, sourcePinnwand, targetPinnwand FROM abo "
              + "WHERE targetPinnwand=" + pinnwandID + " ORDER BY id");

        while (rs.next()) {
        Abo a = new Abo();
        a.setId(rs.getInt("id"));
        a.setSourcePinnwandID(rs.getInt("sourcePinnwand"));
        a.setTargetPinnwandID(rs.getInt("targetPinnwand"));
       
        result.addElement(a);
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return result;
  }

  public Abo insert(Abo a) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM abo ");

      if (rs.next()) {
       
        a.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        
        stmt.executeUpdate("INSERT INTO abo (id, sourcePinnwand, targetPinnwand) "
            + "VALUES ("
            + a.getId()
            + ","
            + a.getSourcePinnwandID()
            + ","
            + a.getTargetPinnwandID() +")");
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return a;
  }

  public void delete(Abo a) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM abo " + "WHERE id=" + a.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

 
  public void deleteAbosOf(Pinnwand a) {
    Connection con = LocalDBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM abo " + "WHERE sourcePinnwand="
          + a.getId());
      stmt.executeUpdate("DELETE FROM abo " + "WHERE targetPinnwand="
          + a.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

 
  public Pinnwand getSourcePinnwand(Abo a) {
    
    return PinnwandMapper.pinnwandMapper().findByKey(a.getSourcePinnwandID());
  }

 
  public Pinnwand getTargetPinnwand(Abo a) {
    
    return PinnwandMapper.pinnwandMapper().findByKey(a.getTargetPinnwandID());
  }

}