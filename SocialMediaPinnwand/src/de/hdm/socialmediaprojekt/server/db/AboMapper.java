package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;


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
 
    Connection con = DBConnection.connection();

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
    Connection con = DBConnection.connection();

    
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
    Connection con = DBConnection.connection();
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
    Connection con = DBConnection.connection();
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
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
          + "FROM abo ");

      if (rs.next()) {
       
        a.setId(rs.getInt("maxid") + 1);

        stmt = con.createStatement();

        stmt.executeUpdate("INSERT INTO `socialmediapinnwand`.`abo` (`id`, `sourcePinnwand`, `targetPinnwand`, `erstellungsdatum`) VALUES ('"+a.getId()+"', '"+a.getSourcePinnwandID()+"', '"+a.getTargetPinnwandID()+"', CURRENT_TIMESTAMP);");
        
        
        
        
        
       
      }
    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }

    return a;
  }

  public void delete(Abo a) {
    Connection con = DBConnection.connection();

    try {
      Statement stmt = con.createStatement();

      stmt.executeUpdate("DELETE FROM abo " + "WHERE id=" + a.getId());

    }
    catch (SQLException e2) {
      e2.printStackTrace();
    }
  }

 
  public void deleteAbosOf(Pinnwand a) {
    Connection con = DBConnection.connection();

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


  public ArrayList<Abo> getAboBetweenTwoDates (String datumVon, String datumBis, User u){
		//Aufbau der DBVerbindung
		Connection con = DBConnection.connection();
		ArrayList <Abo> aboListe= new ArrayList<Abo>();
		//Versuch der Abfrage
		try{
			Statement stmt = con.createStatement();
			String sql = "SELECT * from abo WHERE sourcePinnwand = " + u.getId() + " AND erstellungsdatum between '" 
					+ datumVon + "' AND '" + datumBis + "'";
			ResultSet rs = stmt.executeQuery
					(sql);

			while (rs.next()) {
				Abo a = new Abo();
		        a.setId(rs.getInt("id"));
		        a.setErstellungsdatum(rs.getDate("erstellungsdatum"));
		        a.setSourcePinnwandID(rs.getInt("sourcePinnwand"));
		        a.setTargetPinnwandID(rs.getInt("targetPinnwand"));

		        //LikeObjekt zu LikeListe hinzuf�gen
		        aboListe.add(a);
			}
			return aboListe;		
		}
		   catch (SQLException e) {
	    		e.printStackTrace();
	    		return null;
		    }				
	}





}
