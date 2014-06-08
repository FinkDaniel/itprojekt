package de.hdm.socialmediaprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.socialmediaprojekt.client.gui.Content;
import de.hdm.socialmediaprojekt.client.gui.Footer;
import de.hdm.socialmediaprojekt.client.gui.Header;
import de.hdm.socialmediaprojekt.client.gui.Navigation;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Diese Klasse implementiert das Interface <code>EntryPoint</code>
 * es wird daher die Methode
 * <code>public void onModuleLoad()</code> benoetigt. Diese ist das GWT-Pendant der
 * <code>main()</code>-Methode normaler Java-Applikationen.
 * @Autor: Team Gui (Prell, Feininger)
 **/
public class SocialMediaProjekt implements EntryPoint {

	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	// Klassenvariablen für Google Login
	private LoginInfo loginInfo = null;
	private Label loginLabel = new Label(
			"Please sign in to your Google Account to access the SM application.");
	private Anchor signInLink = new Anchor("Sign In");
	private static User aktuellerNutzer = null;

	public Header header = new Header();
	public Navigation navigation = new Navigation();

	
	public Content content = new Content();

	public Footer footer = new Footer();

	public void onModuleLoad() {

		pinnwandVerwaltung
				.login("http://127.0.0.1:8888/SocialMediaProjekt.html?gwt.codesvr=127.0.0.1:9997",
						new AsyncCallback<LoginInfo>() {

							public void onFailure(Throwable error) {
							}

							public void onSuccess(LoginInfo result) {

								loginInfo = result;
								if (loginInfo.isLoggedIn()) {
									nutzerInDatenbank(result);
									// header.addUserStatus(aktuellerNutzer);
									seitenaufbau();
								} else {
									loadLogin();
								}

								loginInfo = result;
								if (loginInfo.isLoggedIn()) {
									nutzerInDatenbank(result);
									
									seitenaufbau();
								} else {

									loadLogin();

								}

							}
						});
	}
	/**
	 * Diese Methode baut das Grundgerüst der Seite durch die Instanzen der Objekte <code>header</code>, 
	 * <code>navigation</code>, <code>content</code> und <code>footer</code> auf. 
	 * Durch <code>RootPanel.get().add(object)</code> wird das ausgewählte Panel der Seite hinzugefügt. 
	 * @Autor: Team Gui (Prell, Feininger)
	 **/
	private void seitenaufbau() {

		RootPanel.get().clear();
		RootPanel.get("header").add(header);
		RootPanel.get("navigation").add(navigation);
		RootPanel.get("content").add(content);
		RootPanel.get("footer").add(footer);

	}
	/**
	 * Die Methode <code>clearContent()</code> bereinigt das Content- Panel 
	 * @author Team Gui (Prell, Feininger)
	 **/
	public void clearContent() {
		RootPanel.get("content").clear();
	}
	/**
	 * Die Methode <code>addPinnwandToContent</code> ruft die Methode <code>addPinnwand</code> in der KLasse <code>Content</code> auf und fügt diese
	 * dem instanzierten <code>content</code> Objekt hinzu.  
	 * @author Team Gui (Prell, Feininger)
	 **/
	public void addPinnwandToContent() {
		content.addPinnwand();
		RootPanel.get("content").add(content);
	}
	/**
	 * Die Methode <code>addAbosToContent</code> ruft die Methode <code>addAbos</code> in der KLasse <code>Content</code> auf und fügt diese
	 * dem instanzierten <code>content</code> Objekt hinzu.  
	 * @author Team Gui (Prell, Feininger)
	 **/
	public void addAbosToContent() {
		content.addMeineAbos();
		RootPanel.get("content").add(content);

	}
	/**
	 * Die Methode <code>nutzerInDatenbank</code> überprüft durch die Datenbankabfrage <code>getAllUser</code> 
	 * die vorhandenen Nutzer der Datenbank <code>result</code>
	 * mit dem eingeloggten User <code>googleNutzer</code> 
	 * @param googleNutzer
	 * @author Team Gui (Prell, Feininger)
	 */
	public void nutzerInDatenbank(final LoginInfo googleNutzer) {
		pinnwandVerwaltung.getAllUser(new AsyncCallback<Vector<User>>() {

			@Override
			public void onFailure(Throwable caught) {

			}
			/**
			 * Bei erfolgreicher Abfrage wird die E-Mail Adresse des Google Nutzers mit der in der Datenbank vorhandenen E-Mail Adresse
//			 * verglichen, ist kein Nutzer in der Datenbank vorhanden, wird die Methode <code>setAktuellerNutzer</code> aufgerufen
			 * @param result
			 * @author Team Gui (Prell, Feininger)
			 */
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

	public void createUser(final LoginInfo googleNutzer) {
		final User user = new User();
		user.setEmail(googleNutzer.getEmailAddress());
		// user.setErstellungsZeitpunkt(new Date());

		/**
		 * Fordert den Nutzer auf Vor-, Nachname und Nickname einzugeben, da
		 * diese Information nicht über die Google API bezogen werden kann bzw.
		 * geändert werden soll
		 * 
		 * @author Daniel Fink + Team Gui (Prell, Feininger)
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
								Window.alert("Fotzkopf");
								//addUserStatus(result);
								seitenaufbau();
								
								/*
								 * Update die SuggestBox mit neuen Nutzer
								 */
								// fillSuggestenBox();
							}

						});
			}
		});

	}
	
	private void loadLogin() {
		Window.Location.assign(loginInfo.getLoginUrl());
	}

	private void loadLogout() {
		Window.Location.assign(loginInfo.getLogoutUrl());
	}

	public static User getAktuellerNutzer() {
		return aktuellerNutzer;
	}

	public static void setAktuellerNutzer(User aktuellerNutzer) {
		SocialMediaProjekt.aktuellerNutzer = aktuellerNutzer;
	}
	/*public void addUserStatus(User user){
		header.clear();
		header.addUserStatus(user);
		
	}*/
}