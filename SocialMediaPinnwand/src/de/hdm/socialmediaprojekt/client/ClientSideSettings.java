package de.hdm.socialmediaprojekt.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;

import de.hdm.socialmediaprojekt.shared.CommonSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltung;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;

/**
 * Anlegen und Auslesen der applikationsweit eindeutigen PinnwandVerwaltung.
 * 
 * @author Team GUI
 * 
 */
public class ClientSideSettings extends CommonSettings {

	private static PinnwandVerwaltungAsync pinnwandVerwaltung = null;

	private static final String LOGGER_NAME = "SocialMediaProjekt Web Client";
	private static final Logger log = Logger.getLogger(LOGGER_NAME);

	public static Logger getLogger() {
		return log;
	}

	public static PinnwandVerwaltungAsync getPinnwandVerwaltung() {

		if (pinnwandVerwaltung == null) {
			pinnwandVerwaltung = GWT.create(PinnwandVerwaltung.class);

		}
		return pinnwandVerwaltung;
	}

}