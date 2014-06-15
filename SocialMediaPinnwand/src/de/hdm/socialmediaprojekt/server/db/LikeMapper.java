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

/**
 * Dies ist die Mapperklasse zum Like. Sie stellt die Verbindung zur
 * Like-Tabelle in der Datenbank her.
 * 
 * @mapper Like
 * @author Social Media Team
 * 
 */
public class LikeMapper {

	private static LikeMapper likeMapper = null;

	/**
	 * Konstruktor des LikeMappers
	 * 
	 * @author Social Media Team
	 */
	protected LikeMapper() {
	}

	/**
	 * erweiterter Konstruktor der eine neue Instanz anlegt, sofern es noch
	 * keine gibt
	 * 
	 * @return BeitragMapper
	 * @author Social Media Team
	 */
	public static LikeMapper likeMapper() {
		if (likeMapper == null) {
			likeMapper = new LikeMapper();
		}

		return likeMapper;
	}

	/**
	 * Diese Methode findet den Like nach zugehöriger ID. Der Like wird
	 * anschließend nach der Struktur des SMO-Objects gebaut und zurückgegeben.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Like
	 */
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
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Diese Methode gibt alle Likes aus, die in der Datenbank hinterlegt sind
	 * 
	 * @return Vector<Like>
	 * @author Social Media Team
	 */
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
		} catch (SQLException e2) {
			e2.printStackTrace();
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
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	/**
	 * Diese Methode gibt alle Beiträge aus der Datenbank aus, die zu einem
	 * Beitrag hinzugefügt wurden. Die ID eines Beitrags wird im Parameter
	 * übergeben.
	 * 
	 * @return Vector<Like>
	 * @author Social Media Team
	 * @param Integer
	 */
	public Vector<Like> findByTargetBeitrag(int beitragID) {
		Connection con = DBConnection.connection();
		Vector<Like> result = new Vector<Like>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, sourceUser, targetBeitrag FROM gelikt "
							+ "WHERE targetBeitrag="
							+ beitragID
							+ " ORDER BY id");

			while (rs.next()) {
				Like l = new Like();
				l.setId(rs.getInt("id"));
				l.setSourceUserID(rs.getInt("sourceUser"));
				l.setTargetBeitragID(rs.getInt("targetBeitrag"));

				result.addElement(l);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	/**
	 * Diese Methode erstellt einen Like in der Datenbank. Selbiger wird nach
	 * Struktur der SMO-Objekts gebaut und zurückgegeben.
	 * 
	 * @return Like
	 * @author Social Media Team
	 * @param Like
	 */
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
						+ "," + l.getTargetBeitragID() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return l;
	}

	/**
	 * Diese Methode löscht einen Like aus der Datenbank.
	 * 
	 * @return void
	 * @author Social Media Team
	 * @param Like
	 */
	public void delete(Like l) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM gelikt " + "WHERE id=" + l.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Diese Methode löscht alle Likes die zu einem Beitrag gehören aus der
	 * Datenbank.
	 * 
	 * @return void
	 * @author Social Media Team
	 * @param Beitrag
	 */
	public void deleteLikeOf(Beitrag l) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM gelikt " + "WHERE sourceUser="
					+ l.getId());
			stmt.executeUpdate("DELETE FROM gelikt " + "WHERE targetBeitrag="
					+ l.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Diese gibt das komplette User-Objekt jenes Likes zurück, welcher der
	 * Methode übergeben wird.
	 * 
	 * @return User
	 * @author Social Media Team
	 * @param Like
	 */
	public User getSourceUser(Like l) {

		return UserMapper.userMapper().findByKey(l.getSourceUserID());
	}

	/**
	 * Diese gibt das komplette Beitrag-Objekt jenes Likes zurück, welcher der
	 * Methode übergeben wird.
	 * 
	 * @return Beitrag
	 * @author Social Media Team
	 * @param Like
	 */
	public Beitrag getTargetBeitrag(Like l) {

		return BeitragMapper.beitragMapper().findByKey(l.getTargetBeitragID());
	}

	/**
	 * Diese Methode gibt alle Likes aus der Datenbank aus, die zu einem Beitrag
	 * hinzugefügt wurden. Der Beitrag wird im Parameter übergeben.
	 * 
	 * @return Array<Like>
	 * @author Social Media Team
	 * @param Integer
	 */

	public ArrayList<Like> getLikeByBeitrag(Beitrag b) {
		Connection con = DBConnection.connection();
		ArrayList<Like> likeListe = new ArrayList<Like>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT * FROM `gelikt` WHERE sourceUser="
							+ b.getSourceUserID());

			while (rs.next()) {

				Like l = new Like();
				l.setId(rs.getInt("id"));
				l.setErstellungsdatum(rs.getDate("erstellungsdatum"));
				l.setSourceUserID(rs.getInt("sourceUser"));
				l.setTargetBeitragID(rs.getInt("targetBeitrag"));

				likeListe.add(l);

			}
			return likeListe;
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return likeListe;
	}

	/**
	 * Diese Methode addiert alle Likes, die von einem bestimmten Nutzer
	 * hinzugefügt wurden, in einem bestimmten Zeitraum.
	 * 
	 * @author Social Media Team
	 * @return anzahl
	 */
	public int getLikeCountByNutzer(User u, String datumVon, String datumBis) {
		Connection con = DBConnection.connection();
		int anzahl = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT COUNT(`id`) FROM `gelikt` WHERE `sourceUser` = "
							+ u.getId()
							+ " AND erstellungsdatum between '"
							+ datumVon + "' AND '" + datumBis + "'");
			if (rs.next()) {
				anzahl = rs.getInt(1);
			}
			return anzahl;
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return anzahl;
	}
}
