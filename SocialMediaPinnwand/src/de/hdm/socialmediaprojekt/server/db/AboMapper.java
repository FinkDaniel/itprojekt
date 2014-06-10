package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;

/**
 * Dies ist die Mapperklasse zum Abo. Sie stellt die Verbindung zur Abo-Tabelle in der Datenbank her.
 * @mapper Abo
 * @author Social Media Team
 *
 */
public class AboMapper {

// Variablendeklaration
  private static AboMapper aboMapper = null;

 /**
  * Konstruktor des AboMappers
  */
  protected AboMapper() {
  }

/**
 * erweiterter Konstruktor der eine neue Instanz anlegt, sofern es noch keine gibt
 * @return AboMapper
 * @author Social Media Team
 */
  public static AboMapper aboMapper() {
    if (aboMapper == null) {
      aboMapper = new AboMapper();
    }

    return aboMapper;
  }

 /**
  * Diese Methode findet das Abo nach zugehöriger ID. Das Abo wird anschließend nach der Struktur des SMO-Objects gebaut und zurückgegeben.
  * @author Social Media Team
  * @param Integer
  * @return Abo
  */
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

  /**
   * Diese Methode gibt alle Abos aus, die in der Datenbank hinterlegt sind
   * @return Vector<Abo>
   * @author Social Media Team
   */
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

  /**
   * Diese Methode gibt alle Abos aus einer SourcePinnwand aus, die in der Datenbank hinterlegt sind
   * @return Vector<Abo>
   * @author Social Media Team
   * @param Integer
   */
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

  /**
   * Diese Methode gibt alle Abos aus einer TargetPinnwand aus, die in der Datenbank hinterlegt sind
   * @return Vector<Abo>
   * @author Social Media Team
   * @param Integer
   */
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
  /**
   * Diese Methode erstellt ein Abo in der Datenbank. Selbiges wird nach Struktur der SMO-Objekts gebaut und zurückgegeben.
   * @return Abo
   * @author Social Media Team
   * @param Abo
   */
  public Abo insert(Abo a) {
    Connection con = LocalDBConnection.connection();

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
  /**
   * Diese Methode löscht ein Abo aus der Datenbank.
   * @return void
   * @author Social Media Team
   * @param Abo
   */
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

  /**
   * Diese Methode löscht alle Abos in der Datenbank die einer Pinnwand zugeordnet sind. Selbiges wird nach Struktur der SMO-Objekts gebaut und zurückgegeben.
   * @return void
   * @author Social Media Team
   * @param Pinnwand
   */
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

  /**
   * Diese Methode gibt die SourcePinnwand anhand eines Abos zurück. Die Pinnwand wird nach Struktur der SMO-Objekts gebaut und zurückgegeben.
   * @return Pinnwand
   * @author Social Media Team
   * @param Abo
   */
  public Pinnwand getSourcePinnwand(Abo a) {
    
    return PinnwandMapper.pinnwandMapper().findByKey(a.getSourcePinnwandID());
  }

  /**
   * Diese Methode gibt die TargetPinnwand anhand eines Abos zurück. Die Pinnwand wird nach Struktur der SMO-Objekts gebaut und zurückgegeben.
   * @return Pinnwand
   * @author Social Media Team
   * @param Abo
   */
  public Pinnwand getTargetPinnwand(Abo a) {
    
    return PinnwandMapper.pinnwandMapper().findByKey(a.getTargetPinnwandID());
  }




}
