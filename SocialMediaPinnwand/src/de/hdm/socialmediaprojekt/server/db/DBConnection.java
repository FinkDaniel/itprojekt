package de.hdm.socialmediaprojekt.server.db;

import java.sql.*;

import com.google.appengine.api.rdbms.AppEngineDriver;

/**
 * Verwalten einer Verbindung zur Datenbank.
 * <p>
 * <b>Vorteil:</b> Sehr einfacher Verbindungsaufbau zur Datenbank.
 * <p>
 * <b>Nachteil:</b> Durch die Singleton-Eigenschaft der Klasse kann nur auf eine
 * fest vorgegebene Datenbank zugegriffen werden.
 * <p>
 * In der Praxis kommen die meisten Anwendungen mit einer einzigen Datenbank
 * aus. Eine flexiblere Variante für mehrere gleichzeitige
 * Datenbank-Verbindungen w�re sicherlich leistungsfähiger. Dies w�rde
 * allerdings den Rahmen dieses Projekts sprengen bzw. die Software unn�tig
 * verkomplizieren, da dies f�r diesen Anwendungsfall nicht erforderlich ist.
 * 
 * @author T420
 */
public class DBConnection {

	/**
	 * Die Klasse DBConnection wird nur einmal instantiiert. Man spricht hierbei
	 * von einem sogenannten <b>Singleton</b>.
	 * <p>
	 * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal
	 * für sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie
	 * speichert die einzige Instanz dieser Klasse.
	 * 
	 * @see AboMapper.aboMapper()
	 * @see BeitragMapper.beitragMapper
	 */
	private static Connection con = null;

	/**
	 * Die URL, mit deren Hilfe die Datenbank angesprochen wird. In einer
	 * professionellen Applikation würde diese Zeichenkette aus einer
	 * Konfigurationsdatei eingelesen oder �ber einen Parameter von au�en
	 * mitgegeben, um bei einer Ver�nderung dieser URL nicht die gesamte
	 * Software neu komilieren zu müssen.
	 */
	private static String url = "jdbc:google:rdbms://social-media-pinnwand:itprojekt/socialmediapinnwand?user=root";

	/**
	 * Diese statische Methode kann aufgrufen werden durch
	 * <code>DBConnection.connection()</code>. Sie stellt die
	 * Singleton-Eigenschaft sicher, indem Sie daf�r sorgt, dass nur eine
	 * einzige Instanz von <code>DBConnection</code> existiert.
	 * <p>
	 * 
	 * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code>
	 * instantiiert werden, sondern stets durch Aufruf dieser statischen
	 * Methode.
	 * <p>
	 * 
	 * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies
	 * kann z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank
	 * ausgel�st werden - wird keine neue Verbindung aufgebaut, so dass die in
	 * einem solchen Fall die gesamte Software neu zu starten ist. In einer
	 * robusten L�sung w�rde man hier die Klasse dahingehend modifizieren, dass
	 * bei einer nicht mehr funktionsfähigen Verbindung stets versucht würde,
	 * eine neue Verbindung aufzubauen. Dies würde allerdings ebenfalls den
	 * Rahmen dieses Projekts sprengen.
	 * 
	 * @return DAS <code>DBConncetion</code>-Objekt.
	 * @throws SQLException
	 * @see con
	 */
	public static Connection connection() {
		// Wenn es bisher keine Conncetion zur DB gab, ...
		if (con == null) {
			try {
				// Ersteinmal muss der passende DB-Treiber geladen werden
				DriverManager.registerDriver(new AppEngineDriver());

				/*
				 * Dann erst kann uns der DriverManager eine Verbindung mit den
				 * oben in der Variable url angegebenen Verbindungsinformationen
				 * aufbauen.
				 * 
				 * Diese Verbindung wird dann in der statischen Variable con
				 * abgespeichert und fortan verwendet.
				 */
				con = DriverManager.getConnection(url);

			} catch (SQLException e1) {
				con = null;
				e1.printStackTrace();
			}

		}

		// Zurückgegeben der Verbindung
		return con;

	}

}
