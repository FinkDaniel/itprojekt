package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.appengine.api.rdbms.AppEngineDriver;

/**
 * Verwalten einer Verbindung zur Datenbank (lediglich eine Datenbank möglich, reicht für das Social Media Projekt aus)
 * @author Daniel Fink
 */
public class DBConnection {

	/**
	 * Singleton Instanz der Klasse.
	 * @author Social Media Team
	 */
	private static Connection con = null;
	
	/**
	 * Die URL, mit deren Hilfe die Datenbank angesprochen wird wird im <code>String url</code> hinterlegt. 
	 * @author Social Media Team
	 */
	private static String url = "jdbc:google:rdbms://prof-thies.de:thies-bankproject:thies-bankproject/bankproject?user=demo&password=demo";
	
	/**
	 * Diese statische Methode stellt sicher, dass nur eine Connection besteht. Besteht keine wird diese angelegt.
	 * @author Social Media Team
	 */
	public static Connection connection() {
		// Wenn es bisher keine Conncetion zur DB gab, ... 
		if ( con == null ) {
			try {
				// Ersteinmal muss der passende DB-Treiber geladen werden
				DriverManager.registerDriver(new AppEngineDriver());

				/*
				 * Dann erst kann uns der DriverManager eine Verbindung mit den oben
				 * in der Variable url angegebenen Verbindungsinformationen aufbauen.
				 * 
				 * Diese Verbindung wird dann in der statischen Variable con 
				 * abgespeichert und fortan verwendet.
				 */
				con = DriverManager.getConnection(url);
			} 
			catch (SQLException e1) {
				con = null;
				e1.printStackTrace();
			}
		}
		
		// Zur�ckgegeben der Verbindung
		return con;
	}

}
