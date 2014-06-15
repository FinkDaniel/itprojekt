package de.hdm.socialmediaprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.socialmediaprojekt.client.gui.SocialMediaPinnwand;
import de.hdm.socialmediaprojekt.client.gui.report.ReportGenerator;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Die EntryPoint-Klasse der Applikation. Startet und prüft den Google Login und
 * öffnet eine Auswahl, ob der Nutzer zur SocialMediaPinnwand oder zum
 * ReportGenerator wechseln möchte.
 * @author Team GUI
 * @author Team Applikationsschicht
 * @author Team Datenbank
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
					}

					public void onSuccess(LoginInfo result) {

						loginInfo = result;
						if (loginInfo.isLoggedIn()) {
							nutzerInDatenbank(result);
						

							starteSocialMediaProjekt();
						} else {
							loadLogin();
						}

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
	 * Prüft ob der sich anmeldende Nutzer schon in der Datenbank eingetragen
	 * ist
	 * 
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
	 * 
	 * @param googleNutzer
	 */

	public void createUser(final LoginInfo googleNutzer) {
		final User user = new User();
		user.setEmail(googleNutzer.getEmailAddress());

		/**
		 * Fordert den Nutzer auf Vor-, Nachname und Nickname einzugeben, da
		 * diese Information nicht Ã¼ber die Google API bezogen werden kann bzw.
		 * geÃ¤ndert werden soll
		 * 
		 * @author Daniel Fink
		 */
		final LoginCustomDialog dialog = new LoginCustomDialog(
				googleNutzer.getNickname());
		DialogBox dlb = dialog;
		dlb.center();

		dlb.addCloseHandler(new CloseHandler<PopupPanel>() {

			public void onClose(CloseEvent<PopupPanel> event) {
				user.setVorname(dialog.getVorname());
				user.setNachname(dialog.getNachname());
				user.setNickname(dialog.getNickname());
				pinnwandVerwaltung.createUser(user.getVorname(),
						user.getNachname(), user.getNickname(),
						user.getEmail(), new AsyncCallback<User>() {
							@Override
							public void onFailure(Throwable caught) {
							}

							public void onSuccess(User result) {
								setAktuellerNutzer(result);
								

								starteSocialMediaProjekt();

								
							}

						});
			}
		});

	}

	/**
	 * Läd die Auswahlseite um zwischen Social Media Pinnwand und
	 * ReportGenerator zu wechseln
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

	public void loadLogout() {
		Window.Location.assign(loginInfo.getLogoutUrl());
	}

	/**
	 * Auslesen des aktuellen Nutzers
	 * 
	 * @return
	 */

	public static User getAktuellerNutzer() {
		return aktuellerNutzer;
	}

	/**
	 * Setzt den aktuellen Nutzer
	 * 
	 * @param aktuellerNutzer
	 */
	public static void setAktuellerNutzer(User aktuellerNutzer) {
		SocialMediaProjekt.aktuellerNutzer = aktuellerNutzer;
	}

}