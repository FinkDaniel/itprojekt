package de.hdm.socialmediaprojekt.shared.smo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Realisierung eines exemplarischen Kommentaes. Ein Kommentar besitzt einen
 * User, von dem er geschrieben wird. Dieser wird über seine ID festgelegt.
 * Zusätzlich besitzt ein Like einen Beitrag dem er zugeordnet wird. Dieser wird
 * ebenfalls über seine ID festgelegt.
 * 
 * @author Paul Titze
 */

public class Kommentar extends SMObject implements IsSerializable {

	/**
	 * Der Inhalt des Kommentares.
	 */
	private String text = "";
	/**
	 * Die ID des zugehörigen Users.
	 */

	private int sourceUserID = 0;
	/**
	 * Die ID des zugehörigen Beitrags.
	 */
	private int targetBeitragID = 0;

	/**
	 * Methode zum auslesen des Inhaltes.
	 */
	public String getKommentar() {
		return this.text;
	}

	/**
	 * Methode zum setzen des Inhaltes.
	 */
	public void setKommentar(String kommentar) {
		this.text = kommentar;
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
	 * Methode zum auslesen der TargetBeitragID.
	 */
	public int getTargetBeitragID() {
		return this.targetBeitragID;
	}

	/**
	 * Methode zum setzen der TargetBeitragID.
	 */
	public void setTargetBeitragID(int targetID) {
		this.targetBeitragID = targetID;
	}

	/**
	 * Methode zum erzeugen einer einfachen textuellen Ausgabe aller Attribute
	 * eines Kommentar-Objektes. Erweitertt die
	 * <code>toString()<code>-Methode der Superklasse.
	 */
	public String toString() {
		return super.toString() + "Der Nutzer mit der User-ID: #"
				+ this.sourceUserID + "hat den Beitrag mit der Beitrags-ID: #"
				+ this.targetBeitragID + "mit dem Kommentar:" + this.text
				+ "kommentiert";
	}

}