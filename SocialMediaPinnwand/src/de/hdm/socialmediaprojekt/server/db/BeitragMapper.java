package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Dies ist die Mapperklasse zum Beitrag. Sie stellt die Verbindung zur
 * Abo-Tabelle in der Datenbank her.
 * 
 * @mapper Beitrag
 * @author Team Datenbank
 * 
 */
public class BeitragMapper {

	// Variablendeklaration
	private static BeitragMapper beitragMapper = null;

	/**
	 * Konstruktor des BeitragMappers
	 * 
	 * @author Social Media Team
	 */

	protected BeitragMapper() {
	}

	/**
	 * erweiterter Konstruktor der eine neue Instanz anlegt, sofern es noch
	 * keine gibt
	 * 
	 * @return BeitragMapper
	 * @author Social Media Team
	 */

	public static BeitragMapper beitragMapper() {
		if (beitragMapper == null) {
			beitragMapper = new BeitragMapper();
		}

		return beitragMapper;
	}

	/**
	 * Diese Methode findet den Beitrag nach zugehöriger ID. Der Beitrag wird
	 * anschließend nach der Struktur des SMO-Objects gebaut und zurückgegeben.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Beitrag
	 */
	public Beitrag findByKey(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT id, sourceUser, beitrag FROM beitrag "
							+ "WHERE id=" + id + " ORDER BY sourceUser");

			if (rs.next()) {

				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setBeitrag(rs.getString("beitrag"));
				b.setSourceUserID(rs.getInt("sourceUser"));

				return b;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Diese Methode gibt alle Beiträge aus, die in der Datenbank hinterlegt
	 * sind
	 * 
	 * @return Vector<Beitrag>
	 * @author Social Media Team
	 */
	public Vector<Beitrag> findAll() {
		Connection con = DBConnection.connection();

		Vector<Beitrag> result = new Vector<Beitrag>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, sourceUser, beitrag "
					+ "FROM beitrag " + "ORDER BY sourceUser");

			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setBeitrag(rs.getString("beitrag"));
				b.setSourceUserID(rs.getInt("sourceUser"));

				result.addElement(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Diese Methode gibt alle Beiträge von einem bestimmten SourceUsern aus,
	 * der in der Datenbank hinterlegt ist. Übergabewert ist das Array an ID von
	 * den Nutzern, von denen die Beiträge benötigt werden. Sie werden nach
	 * Erstellungsdatum sortiert zurückgegeben, beginnend mit dem Neuesten.
	 * 
	 * @return Vector<Beitrag>
	 * @author Social Media Team
	 * @param Integer
	 *            Array
	 */
	public Vector<Beitrag> findByUniqueSourceUser(int uniqueUserId) {

		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT *" + "FROM beitrag "
					+ "WHERE sourceUser =" + uniqueUserId
					+ " ORDER BY sourceUser");

			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setBeitrag(rs.getString("beitrag"));
				b.setSourceUserID(rs.getInt("sourceUser"));

				result.addElement(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Diese Methode gibt alle Beiträge von beliebig vielen SourceUsern aus, die
	 * in der Datenbank hinterlegt sind. Übergabewert ist das Array an ID von
	 * den Nutzern, von denen die Beiträge benötigt werden. Sie werden nach
	 * Erstellungsdatum sortiert zurückgegeben, beginnend mit dem Neuesten.
	 * 
	 * @return Vector<Beitrag>
	 * @author Social Media Team
	 * @param Integer
	 *            Array
	 */
	public Vector<Beitrag> findBySourceUser(int[] sourceId) {

		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();

		try {
			Statement stmt = con.createStatement();
			StringBuffer query = new StringBuffer();
			String teil1 = new String();

			teil1 = ("SELECT `id`,`sourceUser`,`beitrag`,`erstellungsdatum`,DAYOFMONTH(`erstellungsdatum`) as day, MONTH(`erstellungsdatum`) as month, YEAR(`erstellungsdatum`) as year, HOUR(`erstellungsdatum`) as hours, MINUTE(`erstellungsdatum`) as minute FROM `beitrag` "
					+ "WHERE `sourceUser` = " + sourceId[0] + " ");
			query.append(teil1);

			if (sourceId.length != 1) {
				for (int i = 1; i < sourceId.length; i++) {
					query.append(" OR `sourceUser` = " + sourceId[i] + " ");
				}
			}
			String teil2 = new String();
			teil2 = ("ORDER BY `erstellungsdatum` DESC" + "");
			query.append(teil2);
			System.out.print(query.toString());
			ResultSet rs = stmt.executeQuery(query.toString());

			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setBeitrag(rs.getString("beitrag"));
				b.setSourceUserID(rs.getInt("sourceUser"));
				b.setDay(rs.getInt("day"));
				b.setMonth(rs.getInt("month"));
				b.setYear(rs.getInt("year"));
				b.setHours(rs.getInt("hours"));
				b.setMinutes(rs.getInt("minute"));

				result.addElement(b);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Diese Methode gibt alle Beiträge aus der Datenbank aus, die einen
	 * bestimmten Text enthalten. Dieser wird im Parameter übergeben.
	 * 
	 * @return Vector<Beitrag>
	 * @author Social Media Team
	 * @param String
	 */

	public Vector<Beitrag> findByBeitrag(String beitrag) {
		Connection con = DBConnection.connection();
		Vector<Beitrag> result = new Vector<Beitrag>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, sourceUser, beitrag FROM beitrag "
							+ "WHERE sourceUser=" + beitrag + " ORDER BY id");

			while (rs.next()) {
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setBeitrag(rs.getString("beitrag"));
				b.setSourceUserID(rs.getInt("sourceID"));

				result.addElement(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Diese Methode erstellt einen Beitrag in der Datenbank. Selbiger wird nach
	 * Struktur der SMO-Objekts gebaut und zurückgegeben.
	 * 
	 * @return Beitrag
	 * @author Social Media Team
	 * @param Beitrag
	 */
	public Beitrag insert(Beitrag b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM beitrag ");

			if (rs.next()) {

				b.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO beitrag (id, beitrag, sourceUser) "
						+ "VALUES ("
						+ b.getId()
						+ ",'"
						+ b.getBeitrag()
						+ "','" + b.getSourceUserID() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return b;
	}

	/**
	 * Diese Methode aktualisiert einen Beitrag mit den Eigenschaften die im
	 * Parameter-Beitrags-Objekt übergeben werden in der Datenbank. Selbiger
	 * wird nach Struktur der SMO-Objekts gebaut und zurückgegeben.
	 * 
	 * @return Beitrag
	 * @author Social Media Team
	 * @param Beitrag
	 */
	public Beitrag update(Beitrag b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE beitrag SET beitrag =\""
					+ b.getBeitrag() + "\" WHERE id= " + b.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return b;
	}

	/**
	 * Diese Methode löscht einen Beitrag aus der Datenbank.
	 * 
	 * @return void
	 * @author Social Media Team
	 * @param Beitrag
	 */
	public void delete(Beitrag b) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM beitrag " + "WHERE id=" + b.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Diese gibt das komplette User-Objekt jenes Beitrags zurück, welcher der
	 * Methode übergeben wird.
	 * 
	 * @return User
	 * @author Social Media Team
	 * @param Beitrag
	 */
	public User getSourceID(Beitrag b) throws SQLException {

		return UserMapper.userMapper().findByKey(b.getId());

	}

	public ArrayList<Beitrag> getBeitraegeBetweenTwoDates(String datumVon,
			String datumBis) {

		// Aufbau der DBVerbindung
		Connection con = DBConnection.connection();
		ArrayList<Beitrag> beitragListe = new ArrayList<Beitrag>();
		// Versuch der Abfrage
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * from beitrag WHERE erstellungsdatum between '"
					+ datumVon + "' AND '" + datumBis + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Ergebnis in Beitrag- Objekt umwandeln
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setErstellungsdatum(rs.getDate("erstellungsdatum"));
				b.setBeitrag(rs.getString("beitrag"));
				b.setSourceUserID(rs.getInt("sourceUser"));
				b.setLikeList(LikeMapper.likeMapper().getLikeByBeitrag(b));
				b.setKommentarListe(KommentarMapper.kommentarMapper()
						.getKommentarByBeitrag(b));
				beitragListe.add(b);
			}
			return beitragListe;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Gibt alle BeitrÃ¤ge zwischen einem Zeitraum aus
	 * 
	 * @param datumVon
	 *            String welches das Anfangsdatum der Suchanfrage bestimmt
	 * @param datumVis
	 *            String welches das Enddatum der Suchanfrage bestimmt
	 * @param pinnwandId
	 *            int verweiÃŸt auf eine spezifische Pinnwand
	 * 
	 * @return ArrayList mit allen Beitragobjekten in einem Zeitraum
	 */
	public ArrayList<Beitrag> getBeiträgeBetweenTwoDates(String datumVon,
			String datumBis, int pinnwandId) {
		// Aufbau der DBVerbindung
		Connection con = DBConnection.connection();
		ArrayList<Beitrag> beitragListe = new ArrayList<Beitrag>();
		// Versuch der Abfrage
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * from beitrag WHERE sourceUser ="
					+ pinnwandId + " AND erstellungsdatum between '" + datumVon
					+ "' AND '" + datumBis + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Ergebnis in Beitrag- Objekt umwandeln
				Beitrag b = new Beitrag();
				b.setId(rs.getInt("id"));
				b.setErstellungsdatum(rs.getDate("erstellungsdatum"));
				b.setBeitrag(rs.getString("beitrag"));
				b.setSourceUserID(rs.getInt("sourceUser"));
				b.setLikeList(LikeMapper.likeMapper().getLikeByBeitrag(b));
				b.setKommentarListe(KommentarMapper.kommentarMapper()
						.getKommentarByBeitrag(b));
				beitragListe.add(b);
			}
			return beitragListe;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
