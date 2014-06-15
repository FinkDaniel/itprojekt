package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Dies ist die Mapperklasse zum Kommentar. Sie stellt die Verbindung zur
 * Kommentar-Tabelle in der Datenbank her.
 * 
 * @mapper Abo
 * @author Social Media Team
 * 
 */
public class KommentarMapper {

	private static KommentarMapper kommentarMapper = null;

	protected KommentarMapper() {
	}

	/**
	 * erweiterter Konstruktor der eine neue Instanz anlegt, sofern es noch
	 * keine gibt
	 * 
	 * @return KommentarMapper
	 * @author Social Media Team
	 */
	public static KommentarMapper kommentarMapper() {
		if (kommentarMapper == null) {
			kommentarMapper = new KommentarMapper();
		}

		return kommentarMapper;
	}

	/**
	 * Diese Methode findet den Kommentar nach zugehöriger ID. Der Kommentar
	 * wird anschließend nach der Struktur des SMO-Objects gebaut und
	 * zurückgegeben.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Kommentar
	 */
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
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Diese Methode gibt alle Kommentare aus, die in der Datenbank hinterlegt
	 * sind
	 * 
	 * @return Vector<Kommentar>
	 * @author Social Media Team
	 */
	public Vector<Kommentar> findAll() {
		Connection con = DBConnection.connection();

		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, sourceUser, targetBeitrag, kommentar "
							+ "FROM kommentar " + "ORDER BY sourceUser");

			while (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setKommentar(rs.getString("kommentar"));
				k.setSourceUserID(rs.getInt("sourceUser"));
				k.setTargetBeitragID(rs.getInt("targetBeitrag"));
				result.addElement(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Diese Methode gibt alle Kommentare von einem SourceUser aus, die in der
	 * Datenbank hinterlegt sind. Übergabewert ist der Integer ID des
	 * SourceNutzers, von dem die Beiträge benötigt werden. Sie werden nach
	 * Erstellungsdatum sortiert zurückgegeben, beginnend mit dem Neuesten.
	 * 
	 * @return Vector<Kommentar>
	 * @author Social Media Team
	 * @param Integer
	 */
	public Vector<Kommentar> findBySourceUser(int sourceID) {
		Connection con = DBConnection.connection();
		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, sourceUser, targetBeitrag, kommentar "
							+ "FROM kommentar "
							+ "WHERE sourceUser LIKE '"
							+ sourceID + "' ORDER BY sourceUser");

			while (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setKommentar(rs.getString("kommentar"));
				k.setSourceUserID(rs.getInt("sourceUser"));
				k.setTargetBeitragID(rs.getInt("targetBeitrag"));

				result.addElement(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Diese Methode erstellt einen Kommentar in der Datenbank. Selbiger wird
	 * nach Struktur der SMO-Objekts gebaut und zurückgegeben.
	 * 
	 * @return Kommentar
	 * @author Social Media Team
	 * @param Kommentar
	 */
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
						+ "VALUES ("
						+ k.getId()
						+ ",'"
						+ k.getKommentar()
						+ "','"
						+ k.getSourceUserID()
						+ "','"
						+ k.getTargetBeitragID() + "')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return k;
	}

	/**
	 * Diese Methode aktualisiert einen Kommentar mit den Eigenschaften die im
	 * Parameter-Beitrags-Objekt übergeben werden in der Datenbank. Selbiger
	 * wird nach Struktur der SMO-Objekts gebaut und zurückgegeben.
	 * 
	 * @return Kommentar
	 * @author Social Media Team
	 * @param Kommentar
	 */
	public Kommentar update(Kommentar k) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE kommentar SET kommentar =\""
					+ k.getKommentar() + "\" WHERE id= " + k.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return k;
	}

	/**
	 * Diese Methode löscht einen Kommentar aus der Datenbank.
	 * 
	 * @return void
	 * @author Social Media Team
	 * @param Kommentar
	 */
	public void delete(Kommentar k) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM kommentar " + "WHERE id="
					+ k.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Diese gibt das komplette User-Objekt jenes Kommentars zurück, welcher der
	 * Methode übergeben wird.
	 * 
	 * @return User
	 * @author Social Media Team
	 * @param Kommentar
	 */
	public User getSourceID(Kommentar k) {

		return UserMapper.userMapper().findByKey(k.getSourceUserID());

	}

	/**
	 * Diese gibt das komplette Beitrag-Objekt jenes Kommentars zurück, welcher
	 * der Methode übergeben wird.
	 * 
	 * @return User
	 * @author Social Media Team
	 * @param Kommentar
	 */
	public Beitrag getTargetID(Kommentar k) {

		return BeitragMapper.beitragMapper().findByKey(k.getTargetBeitragID());
	}

	/**
	 * Diese Methode gibt alle Kommentare von einem TargetBeitrag aus, die in
	 * der Datenbank hinterlegt sind. Übergabewert ist der Integer ID des
	 * TargetBeitrags, von dem die Beiträge benötigt werden. Sie werden nach
	 * Erstellungsdatum sortiert zurückgegeben, beginnend mit dem Letzen.
	 * 
	 * @return Vector<Kommentar>
	 * @author Social Media Team
	 * @param Integer
	 */
	public Vector<Kommentar> findByTargetBeitrag(int beitragId) {

		Connection con = DBConnection.connection();
		Vector<Kommentar> result = new Vector<Kommentar>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT *" + "FROM kommentar "
					+ "WHERE targetBeitrag = '" + beitragId
					+ "' ORDER BY erstellungsdatum ASC");

			while (rs.next()) {
				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setKommentar(rs.getString("kommentar"));
				k.setSourceUserID(rs.getInt("sourceUser"));
				k.setTargetBeitragID(rs.getInt("targetBeitrag"));

				result.addElement(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Kommentar> getKommentarByBeitrag(Beitrag b) {
		Connection con = DBConnection.connection();
		ArrayList<Kommentar> kommentarListe = new ArrayList<Kommentar>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM kommentar WHERE id ="
							+ b.getId());

			while (rs.next()) {

				Kommentar k = new Kommentar();
				k.setId(rs.getInt("id"));
				k.setErstellungsdatum(rs.getDate("erstellungsdatum"));
				k.setKommentar(rs.getString("kommentar"));
				k.setTargetBeitragID(rs.getInt("targetBeitrag"));
				k.setSourceUserID(rs.getInt("sourceUser"));

				kommentarListe.add(k);
			}
			return kommentarListe;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return kommentarListe;
	}
}
