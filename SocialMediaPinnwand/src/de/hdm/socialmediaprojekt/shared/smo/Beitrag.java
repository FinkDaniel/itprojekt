package de.hdm.socialmediaprojekt.shared.smo;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Realisierung eines exemplarischen Beitrags. Ein Beitrag besitzt einen User,
 * von dem er gesetz wird. Dieser wird über seine ID festgelegt.
 * 
 * @author Team Datenbank
 */
public class Beitrag extends SMObject implements IsSerializable {

	/**
	 * Der Inhalt des Beitrags.
	 */

	private String text = "";
	private ArrayList<Like> likeList;
	private ArrayList<Kommentar> kommentarList;
	/**
	 * Die ID des zugehörigen Users.
	 */
	private int sourceUserID = 0;
	private int day = 0;
	private int month = 0;
	private int year = 0;
	private int hour = 0;
	private int minute = 0;

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	/**
	 * Methode zum auslesen des Inhaltes.
	 */
	public String getBeitrag() {
		return this.text;
	}

	/**
	 * Methode zum setzen des Inhaltes.
	 */
	public void setBeitrag(String beitrag) {
		this.text = beitrag;
	}

	/**
	 * Methode zum auslesen der SourceUserID.
	 */
	public int getSourceUserID() {
		return this.sourceUserID;
	}

	/**
	 * Methode zum setzen der SourceUserID.
	 */
	public void setSourceUserID(int sourceID) {
		this.sourceUserID = sourceID;
	}

	/**
	 * Methode zum erzeugen einer einfachen textuellen Ausgabe aller Attribute
	 * eines Beitrag-Objektes. Erweitertt die
	 * <code>toString()<code>-Methode der Superklasse.
	 */
	public String toString() {

		return super.toString() + " " + this.text + " " + this.sourceUserID
				+ "Der Nutzer mit der User-ID: #" + this.sourceUserID
				+ "hat den Beitrag mit der Beitrags-ID: #" + this.text
				+ "verfasst";

	}

	public void setDay(int i) {
		this.day = i;
	}

	public void setMonth(int int1) {
		this.month = int1;
	}

	public void setYear(int int1) {
		this.year = int1;
	}

	public void setHours(int int1) {
		this.hour = int1;

	}

	public void setMinutes(int int1) {
		this.minute = int1;
	}

	public void setLikeList(ArrayList<Like> likeList) {
		this.likeList = likeList;

	}

	public ArrayList<Like> getLikeList() {
		return likeList;
	}

	public ArrayList<Kommentar> getKommentarListe() {

		return kommentarList;
	}

	public void setKommentarListe(ArrayList<Kommentar> kommentarList) {

		this.kommentarList = kommentarList;
	}

}