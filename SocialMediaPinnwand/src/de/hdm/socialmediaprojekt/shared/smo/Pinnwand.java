package de.hdm.socialmediaprojekt.shared.smo;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Realisierung einer exemplarischen Pinnwand. Eine Pinnwand besitzt einen User,
 * von dem sie abhängig ist. Dieser wird über seine ID festgelegt.
 * 
 * @author Team Datenbank
 */

public class Pinnwand extends SMObject implements IsSerializable {

	/**
	 * Die ID des zugehörigen Users.
	 */
	private int sourceUserID = 0;

	/**
	 * Methode zum auslesen der SourceUserID
	 */

	public int getSourceUserID() {
		return this.sourceUserID;
	}

	/**
	 * Methode zum setzen der SourceUserID
	 */
	public void setSourceUserID(int sourceID) {
		this.sourceUserID = sourceID;
	}

	/**
	 * Methode zum erzeugen einer einfachen textuellen Ausgabe aller Attribute
	 * eines Pinnwand-Objektes. Erweitertt die
	 * <code>toString()<code>-Methode der Superklasse.
	 */
	public String toString() {
		return super.toString() + " inhaber, User-ID: #" + this.sourceUserID;
	}

	/**
	 * Equals Methode zum Vergleichen zweier Pinnwände
	 */

	public boolean equals(Object o) {

		if (o != null && o instanceof Pinnwand) {
			Pinnwand c = (Pinnwand) o;
			try {
				return super.equals(c);
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}

}
