package de.hdm.socialmediaprojekt.client;

import com.google.gwt.core.client.GWT;

import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltung;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;

/**
 * Anlegen und Auslesen der applikationsweit eindeutigen PinnwandVerwaltung.
 * 
 * @Team GUI
 *
 */
public class ClientSideSettings {

	private static PinnwandVerwaltungAsync pinnwandVerwaltung = null;

	public static PinnwandVerwaltungAsync getPinnwandVerwaltung() {

		if (pinnwandVerwaltung == null) {
			pinnwandVerwaltung = GWT.create(PinnwandVerwaltung.class);

		}
		return pinnwandVerwaltung;
	}

}