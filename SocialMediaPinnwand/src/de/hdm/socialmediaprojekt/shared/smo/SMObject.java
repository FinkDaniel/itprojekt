package de.hdm.socialmediaprojekt.shared.smo;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Superklasse aller für die Umsetzung relevanter Klassen. Sie legt Attribute
 * fest, die für alle Social-Media-Objekte gelten, wie der ID und dem
 * Erstellungsdatum.
 * 
 * @author Paul Titze
 */
public abstract class SMObject implements IsSerializable {

	/**
	 * Die ID des Objektes.
	 */
	private int id = 0;
	/**
	 * Das Erstellungsdatum des Objektes.
	 */
	private Date erstellungsdatum;

	/**
	 * Methode zum auslesen der ID.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Methode zum setzen der ID.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Methode zum auslesen des Erstellungsdatums.
	 */
	public Date getErstellungsdatum() {
		return this.erstellungsdatum;
	}

	/**
	 * Methode zum setzen des Erstellungsdatums.
	 */
	public void setErstellungsdatum(Date date) {
		date = this.erstellungsdatum;

	}

	/**
	 * Methode zu Feststellung der inhaltlichen Gleichheit zweier
	 * Social-Media-Objekte. Die Gleichheit wird in diesem Beispiel auf eine
	 * identische ID beschränkt.
	 */
	public boolean equals(Object o) {
		if (o != null && o instanceof SMObject) {
			SMObject bo = (SMObject) o;
			try {
				if (bo.getId() == this.id)
					return true;
			} catch (IllegalArgumentException e) {

				return false;
			}
		}
		return false;
	}

	/**
	 * Methode zum erzeugen einer einfachen textuellen Ausgabe aller Attribute
	 * eines Social-Media-Objektes.
	 */
	public String toString() {
		return this.getClass().getName() + " #" + this.id;
	}

	/**
	 * Methode zum erzeugen eines Hashwertes, der für das Objekt eindeutig ist.
	 * In diesem Fall wird als Hashwert die ID verwendet.
	 */
	public int hashCode() {
		return this.id;
	}

}