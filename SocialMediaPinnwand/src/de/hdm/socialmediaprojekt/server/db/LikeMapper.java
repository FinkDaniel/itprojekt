package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Like;
import de.hdm.socialmediaprojekt.shared.smo.User;


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
 
    Connection con = DBConnection.connection();

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
    Connection con = DBConnection.connection();

    
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
    Connection con = DBConnection.connection();
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
    Connection con = DBConnection.connection();
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
    Connection con = DBConnection.connection();

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
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM gelikt " + "WHERE id=" + l.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

 
  public void deleteLikeOf(Beitrag l) {
    Connection con = DBConnection.connection();

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


public ArrayList<Like> getLikeByBeitrag(Beitrag b) {
	Connection con = DBConnection.connection();
	ArrayList <Like> likeListe= new ArrayList<Like>();

	//Versuch der Abfrage
	try{
		Statement stmt = con.createStatement();
		//Suche alle Likes zu einem Beitrag
		ResultSet rs = stmt.executeQuery("SELECT * FROM `gelikt` WHERE sourceUser="+ b.getSourceUserID());

		while (rs.next()) {
	        // Ergebnis in Like- Objekt umwandeln
	        Like l = new Like();
	        l.setId(rs.getInt("id"));
	        l.setErstellungsdatum(rs.getDate("erstellungsdatum"));
	        l.setSourceUserID(rs.getInt("sourceUser"));
	        l.setTargetBeitragID(rs.getInt("targetBeitrag")); 

	        //LikeObjekt zu LikeListe hinzufügen
	        likeListe.add(l);

	      }
		return likeListe;
	}

    catch (SQLException e) {
    		e.printStackTrace();
    }
	//Falls keines gefunden leere Liste
	return likeListe;
 }


public int getLikeCountByNutzer(User u, String datumVon, String datumBis) {
	Connection con = DBConnection.connection();
	int anzahl = 0;
	try {
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(`id`) FROM `gelikt` WHERE `sourceUser` = " + u.getId()
				+ " AND erstellungsdatum between '" + datumVon + "' AND '" + datumBis + "'");
		if(rs.next()) {
			anzahl = rs.getInt(1);
		}
			return anzahl;
	}
	catch  (SQLException e) {
		e.printStackTrace();

	}
	return anzahl;
}
}
  


