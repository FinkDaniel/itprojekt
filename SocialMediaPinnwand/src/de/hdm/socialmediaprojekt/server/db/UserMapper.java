package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;


/**
 * Dies ist die Mapperklasse zum User. Sie stellt die Verbindung zur Pinnwand-Tabelle in der Datenbank her.
 * @mapper User
 * @author Social Media Team
 *
 */
public class UserMapper {
//Variablendeklaration
private static UserMapper userMapper = null;

/**
 * Konstruktor des UserMappers
 * @author Social Media Team
 */
  	protected UserMapper() {
  	}

  	/**
     * erweiterter Konstruktor der eine neue Instanz anlegt, sofern es noch keine gibt
     * @return UserMapper
     * @author Social Media Team
     */
  	public static UserMapper userMapper() {
    if (userMapper == null) {
      userMapper = new UserMapper();
    }

    return userMapper;
  	}

  	/**
     * Diese Methode findet den User nach zugehöriger ID. Der User wird anschließend nach der Struktur des SMO-Objects gebaut und zurückgegeben.
     * @author Social Media Team
     * @param Integer
     * @return User
     */
  public User
  	findByKey(int id) {

    Connection con = LocalDBConnection.connection();

    try {
    
      Statement stmt = con.createStatement();

 
      ResultSet rs = stmt
          .executeQuery("SELECT id, vorname, nachname, nickname, email FROM users "
              + "WHERE id=" + id + " ORDER BY nachname");

     
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

  /**
   * Diese Methode gibt alle User aus, die in der Datenbank hinterlegt sind
   * @return Vector<User>
   * @author Social Media Team
   */
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

  /**
   * Diese Methode gibt die User Objekte die den Nachnamen haben, welcher der Methode übergeben wird.
   * @return Vector<User>
   * @author Social Media Team
   * @param String
   */
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

  /**
   * Diese Methode erstellt einen User in der Datenbank. Selbige wird nach Struktur der SMO-Objekts gebaut und zurückgegeben.
   * @return User
   * @author Social Media Team
   * @param User
   */
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

  /**
   * Diese Methode aktualisiert einen User mit den Eigenschaften die im Parameter-User-Objekt übergeben werden in der Datenbank. Selbiger wird nach Struktur der SMO-Objekts gebaut und zurückgegeben.
   * @return User
   * @author Social Media Team
   * @param User
   */
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

  /**
   * Diese Methode löscht einen User aus der Datenbank.
   * @return void
   * @author Social Media Team
   * @param User
   */
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
  /**
   * Diese Methode gibt die Pinnwand jenes Users zurück, welcher der Methode übergeben wird.
   * @return void
   * @author Social Media Team
   * @param User
   */
  public Pinnwand getPinnwandOf(User u) {
	   
	    return PinnwandMapper.pinnwandMapper().findBySourceUser(u);
	  }
  /**
   * Diese Methode gibt jenen User zurück, welcher die entsprechende E-Mail enthält, welche der Methode übergeben wird.
   * @return void
   * @author Social Media Team
   * @param String
   */
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

  /**
   * Diese Methode gibt jenen User zurück, welcher den entsprechenden Nickname enthält, welcher der Methode übergeben wird.
   * @return void
   * @author Social Media Team
   * @param String
   */
public User findByNickname(String nickname) {
    Connection con = LocalDBConnection.connection();

    try {
    
      Statement stmt = con.createStatement();

 
      ResultSet rs = stmt

          .executeQuery("SELECT * FROM `users` WHERE `nickname` = '"+nickname+"'");
         
          

         

     
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
  
