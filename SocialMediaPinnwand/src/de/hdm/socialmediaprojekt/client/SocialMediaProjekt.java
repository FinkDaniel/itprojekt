package de.hdm.socialmediaprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.socialmediaprojekt.client.gui.SocialMediaPinnwand;
import de.hdm.socialmediaprojekt.client.gui.report.ReportGenerator;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Die EntryPoint-Klasse der Applikation. Startet und prüft den Google Login und öffnet eine Auswahl,
 * ob der Nutzer zur SocialMediaPinnwand oder zum ReportGenerator wechseln möchte.
 */

public class SocialMediaProjekt implements EntryPoint {

	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	/** 
	 * Klassenvariablen für Google Login
	 */
	private LoginInfo loginInfo = null;
	private static User aktuellerNutzer = null;
	static HorizontalPanel loginSeite = new HorizontalPanel();
	public SocialMediaPinnwand smPinnwand = new SocialMediaPinnwand();
	public ReportGenerator rG = new ReportGenerator();
	static Button social = new Button("Social Media Pinnwand");
	static Button report = new Button("Report Generator");
	boolean nicknameVorhanden = false;

	/**
	 * Die onModuleLoad-Methode 
	 */
	public void onModuleLoad() {

		RootPanel.get().clear();
		RootPanel.get("report").setVisible(false);
		RootPanel.get("header").setVisible(false);
		RootPanel.get("navigation").setVisible(false);
		RootPanel.get("content").setVisible(false);
		RootPanel.get("footer").setVisible(false);

		/**
		 * Prüft ob der Nutzer schon eingeloggt ist
		 */
		pinnwandVerwaltung.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {

					public void onFailure(Throwable error) {
						Window.alert("FEHLER");
					}

					public void onSuccess(LoginInfo result) {

						loginInfo = result;
						if (loginInfo.isLoggedIn()) {
							nutzerInDatenbank(result);

							starteSocialMediaProjekt();

						} else {
							loadLogin();
						}

					}
				});

		/**
		 * Läd beim Klick die SocialMediaPinnwand
		 */
		social.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				smPinnwand.seitenaufbau();

			}

		});
		
		/**
		 * Läd beim Klick den ReportGenerator
		 */
		report.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				rG.reportSeitenaufbau();

			}

		});

	}

	/**
	 * Prüft ob der sich anmeldende Nutzer schon in der Datenbank eingetragen ist
	 * @param googleNutzer
	 */
	public void nutzerInDatenbank(final LoginInfo googleNutzer) {
		pinnwandVerwaltung.getAllUser(new AsyncCallback<Vector<User>>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			public void onSuccess(Vector<User> result) {
				for (User u : result) {

					if (u.getEmail() == googleNutzer.getEmailAddress()) {

						setAktuellerNutzer(u);

					}
				}
				if (getAktuellerNutzer() == null) {
					createUser(googleNutzer);
				}
			}

		});
	}

	/**
	 * Öffnet die Login Dialogbox
	 * @param googleNutzer
	 */
	public void createUser(final LoginInfo googleNutzer) {

		LoginDialog loginDialog = new LoginDialog(googleNutzer);
		loginDialog.center();
		loginDialog.show();
	}

	/**
	 * Läd die Auswahlseite um zwischen Social Media Pinnwand und ReportGenerator zu wechseln
	 */
	public static void starteSocialMediaProjekt() {

		RootPanel.get().clear();

		social.getElement().setId("socialButton");
		report.getElement().setId("reportButton");

		loginSeite.add(social);
		loginSeite.add(report);
		RootPanel.get("start").add(loginSeite);
	}

	/**
	 * Öffnet die Google Login Seite
	 */
	private void loadLogin() {
		Window.Location.assign(loginInfo.getLoginUrl());
	}

	/**
	 * Auslesen des aktuellen Nutzers
	 * @return
	 */
	public static User getAktuellerNutzer() {
		return aktuellerNutzer;
	}

	/**
	 * Setzt den aktuellen Nutzer
	 * @param aktuellerNutzer
	 */
	public static void setAktuellerNutzer(User aktuellerNutzer) {
		SocialMediaProjekt.aktuellerNutzer = aktuellerNutzer;
	}
	

}
