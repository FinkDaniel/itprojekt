package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Diese Klasse stellt die lokale, vorübergehenede Datenbankverbindung zur lokalen MySQL Datenbank her. Ein entsprechender JDBC Treiber wurde in dein "Preferences" des Projekts hinzugefügt. <p>
 * Mit XAMPP kann entsprechender Service gestartet werden.<p><br>
 * der Datenbankname: <code>socialmediapinnwand</code><br>
 * der Benutzername: <code>demo</code><br>
 * das Passwort: <code>demo</code>
 * 
 * @author Social Media Team
 *
 */
public class LocalDBConnection {
	//Variablendeklaration
	private static Connection con = null;
	/**
	   * erweiterter Konstruktor der eine neue Instanz anlegt.
	   * @return BeitragMapper
	   * @author Social Media Team
	   */
	public static Connection connection() {
		
		try {
				
			Class.forName("com.mysql.jdbc.Driver").newInstance();
				
				 con = DriverManager.getConnection(
				"jdbc:mysql://localhost/socialmediapinnwand","demo","demo");
				con.setReadOnly(false);
				//con.close();
			}
		catch(Exception e){
				
				System.out.println("*****LOCAL DB FEHLER*****->"+e);
			}
		return con;
		
	}
}

