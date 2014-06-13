package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.User;


public class KommentarMapper {


  private static KommentarMapper kommentarMapper = null;


  protected KommentarMapper() {
  }

  
  public static KommentarMapper kommentarMapper() {
    if (kommentarMapper == null) {
      kommentarMapper = new KommentarMapper();
    }

    return kommentarMapper;
  }


  public Kommentar findByKey(int id) {
   
    Connection con = DBConnection.connection();

    try {
      
      Statement stmt = con.createStatement();

     
      ResultSet rs = stmt
          .executeQuery("SELECT id, sourceUser, targetBeitrag, kommentar FROM kommentar "
              + "WHERE id=" + id + " ORDER BY sourceUser");

      if (rs.next()) {
       
        Kommentar k = new Kommentar();
        k.setId(rs.getInt("id"));
        k.setKommentar(rs.getString("kommentar"));
        k.setSourceUserID(rs.getInt("sourceUser"));
        k.setTargetBeitragID(rs.getInt("targetBeitrag"));
        return k;
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      return null;
    }

    return null;
  }

  public Vector<Kommentar> findAll() {
    Connection con = DBConnection.connection();
   
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

    return result;
  }


  public Vector<Kommentar> findBySourceUser(int sourceID) {
    Connection con = DBConnection.connection();
    Vector<Kommentar> result = new Vector<Kommentar>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT id, sourceUser, targetBeitrag, kommentar "
          + "FROM kommentar " + "WHERE sourceUser LIKE '" + sourceID
          + "' ORDER BY sourceUser");

      
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

    return result;
  }

  public Kommentar insert(Kommentar k) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM kommentar ");

      if (rs.next()) {
        
        k.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        stmt.executeUpdate("INSERT INTO kommentar (id, kommentar, sourceUser, targetBeitrag) "
            + "VALUES (" + k.getId() + ",'" + k.getKommentar() + "','"
            + k.getSourceUserID() + "','"
            +k.getTargetBeitragID() + "')");
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return k;
  }

  public Kommentar update(Kommentar k) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("UPDATE kommentar SET kommentar =\"" + k.getKommentar() + "\" WHERE id= " + k.getId());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }

    return k;
  }

  public void delete(Kommentar k) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM kommentar " + "WHERE id=" + k.getId());
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public User getSourceID(Kommentar k) {
	    
	    return UserMapper.userMapper().findByKey(k.getSourceUserID());
	    		 		
  }
  public Beitrag getTargetID(Kommentar k) {
	    
	    return BeitragMapper.beitragMapper().findByKey(k.getTargetBeitragID());
  }


public Vector<Kommentar> findByTargetBeitrag(int beitragId) {

    Connection con = DBConnection.connection();
    Vector<Kommentar> result = new Vector<Kommentar>();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT *"
          + "FROM kommentar " + "WHERE targetBeitrag = '" + beitragId
          + "' ORDER BY erstellungsdatum ASC");

      
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

    return result;
}
}
