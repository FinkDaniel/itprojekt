package de.hdm.socialmediaprojekt.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Dies ist die Mapperklasse zur Pinnwand. Sie stellt die Verbindung zur
 * Pinnwand-Tabelle in der Datenbank her.
 * 
 * @mapper Beitrag
 * @author Team Datenbank
 * 
 */
public class PinnwandMapper {

	private static PinnwandMapper pinnwandMapper = null;

	/**
	 * Konstruktor des LikeMappers
	 * 
	 * @author Social Media Team
	 */
	protected PinnwandMapper() {
	}

	/**
	 * erweiterter Konstruktor der eine neue Instanz anlegt, sofern es noch
	 * keine gibt
	 * 
	 * @return PinnwandMapper
	 * @author Social Media Team
	 */
	public static PinnwandMapper pinnwandMapper() {
		if (pinnwandMapper == null) {
			pinnwandMapper = new PinnwandMapper();
		}

		return pinnwandMapper;
	}

	/**
	 * Diese Methode findet die Pinnwand nach zugehöriger ID. Die Pinnwand wird
	 * anschließend nach der Struktur des SMO-Objects gebaut und zurückgegeben.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Pinnwand
	 */
	public Pinnwand findByKey(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, sourceUser FROM pinnwand "
							+ "WHERE id=" + id + " ORDER BY sourceUser");

			if (rs.next()) {

				Pinnwand a = new Pinnwand();
				a.setId(rs.getInt("id"));
				a.setSourceUserID(rs.getInt("sourceUser"));
				return a;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Diese Methode gibt alle Pinnwände aus, die in der Datenbank hinterlegt
	 * sind
	 * 
	 * @return Vector<Pinnwand>
	 * @author Social Media Team
	 */
	public Vector<Pinnwand> findAll() {
		Connection con = DBConnection.connection();

		Vector<Pinnwand> result = new Vector<Pinnwand>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, sourceUser FROM pinnwand "
							+ " ORDER BY id");

			while (rs.next()) {
				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setSourceUserID(rs.getInt("sourceUser"));

				result.addElement(p);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	/**
	 * Diese Methode gibt die Pinnwand jenes SourceUsers zurück, dessen Integer
	 * ID der Methode übergeben wird.
	 * 
	 * @return Pinnwand
	 * @author Social Media Team
	 * @param Integer
	 */

	public Pinnwand findBySourceUser(int sourceID) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, sourceUser FROM pinnwand "
							+ "WHERE sourceUser=" + sourceID + " ORDER BY id");

			while (rs.next()) {
				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setSourceUserID(rs.getInt("sourceUser"));

				return p;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return null;
	}

	/**
	 * Diese Methode gibt die Pinnwand jenes SourceUsers zurück, dessen User
	 * Objekt der Methode übergeben wird.
	 * 
	 * @return Pinnwand
	 * @author Social Media Team
	 * @param User
	 */
	public Pinnwand findBySourceUser(User sourceUser) {

		return findBySourceUser(sourceUser.getId());
	}

	/**
	 * Diese Methode erstellt eine Pinnwand in der Datenbank. Selbige wird nach
	 * Struktur der SMO-Objekts gebaut und zurückgegeben.
	 * 
	 * @return Pinnwand
	 * @author Social Media Team
	 * @param Pinnwand
	 */
	public Pinnwand insert(Pinnwand p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM pinnwand ");

			if (rs.next()) {

				p.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO pinnwand (id, sourceUser) "
						+ "VALUES (" + p.getId() + "," + p.getSourceUserID()
						+ ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return p;
	}

	/**
	 * Diese Methode aktualisiert eine Pinnwand mit den Eigenschaften die im
	 * Parameter-Pinnwand-Objekt übergeben werden in der Datenbank. Selbiger
	 * wird nach Struktur der SMO-Objekts gebaut und zurückgegeben.
	 * 
	 * @return Pinnwand
	 * @author Social Media Team
	 * @param Pinnwand
	 */

	public Pinnwand update(Pinnwand p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE pinnwand " + "SET sourceUser=\""
					+ p.getSourceUserID() + "\" " + "WHERE id=" + p.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return p;
	}

	/**
	 * Diese Methode löscht eine Pinnwand aus der Datenbank.
	 * 
	 * @return void
	 * @author Social Media Team
	 * @param Pinnwand
	 */
	public void delete(Pinnwand p) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM pinnwand " + "WHERE id="
					+ p.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Diese Methode löscht die Pinnwand jenes User, welcher der Methode
	 * übergeben wird, aus der Datenbank.
	 * 
	 * @return void
	 * @author Social Media Team
	 * @param User
	 */
	public void deletePinnwandOf(User u) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM pinnwand " + "WHERE sourceUser="
					+ u.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Diese gibt das komplette User-Objekt jener Pinnwand zurück, welcher der
	 * Methode übergeben wird.
	 * 
	 * @return User
	 * @author Social Media Team
	 * @param Beitrag
	 */

	public User getSourceUser(Pinnwand p) {
		return UserMapper.userMapper().findByKey(p.getSourceUserID());
	}

	/**
	 * Diese Methode findet die Pinnwand nach zugehöriger User.
	 * 
	 * @author Social Media Team
	 * @param User
	 * @return Pinnwand
	 */
	public Pinnwand getPinnwandByNutzer(User user) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM pinnwand "
					+ "WHERE sourceUser=" + user.getId());

			if (rs.next()) {

				Pinnwand p = new Pinnwand();
				p.setId(rs.getInt("id"));
				p.setErstellungsdatum(rs.getDate("erstellungsdatum"));
				p.setSourceUserID(rs.getInt("sourceUser"));
				return p;
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

}