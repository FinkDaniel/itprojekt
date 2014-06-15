package de.hdm.socialmediaprojekt.shared.smo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Realisierung eines exemplarischen Abos. Ein Abo ist sowohl von der Pinnwand
 * des abonnierenden Users als auch von der zu abonnierenden Pinnwand abhängig.
 * Diese werden beide durch ihre ID festgelegt.
 * 
 * @author Team Datenbank
 */
public class Abo extends SMObject implements IsSerializable {

	/**
	 * Die ID der Pinnwand des abonnierenden Users
	 */

	private int sourcePinnwandID = 0;
	/**
	 * Die ID der zu abonnierenden Pinnwand
	 */
	private int targetPinnwandID = 0;

	/**
	 * Methode zum auslesen der SourceUserID
	 */

	public int getSourcePinnwandID() {
		return this.sourcePinnwandID;
	}

	/**
	 * Methode zum setzen der SourceUserID
	 */
	public void setSourcePinnwandID(int sourceID) {
		this.sourcePinnwandID = sourceID;
	}

	/**
	 * Methode zum auslesen der TargetUserID
	 */

	public int getTargetPinnwandID() {
		return this.targetPinnwandID;
	}

	/**
	 * Methode zum setzen der TargetUserID
	 */

	public void setTargetPinnwandID(int targetID) {
		this.targetPinnwandID = targetID;
	}

	/**
	 * Methode zum erzeugen einer einfachen textuellen Ausgabe aller Attribute
	 * eines Abo-Objektes. Erweitertt die
	 * <code>toString()<code>-Methode der Superklasse.
	 */
	public String toString() {

		return super.toString() + "Der Nutzer mit der Pinnwand-ID: #"
				+ this.sourcePinnwandID
				+ "hat die Pinnwand mit der Pinnwand-ID: #"
				+ this.targetPinnwandID + "aboniert";

	}
}