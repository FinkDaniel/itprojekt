package de.hdm.socialmediaprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Der LoginDialog ist ein DialogBox, die sich öffnet falls der aktuelle Nutzer noch nicht in der Datenbank
 * eingetragen ist. Sie gibt dem Nutzer die Möglichkeit die gewünschten Daten einzutragen.
 * 
 * @Team GUI
 */
public class LoginDialog extends DialogBox {

	
	DockPanel dockPanel = new DockPanel();
	Label datenEintragen = new Label("Bitte tragen Sie Ihre Daten ein:");
	TextBox vorname = new TextBox();
	TextBox nachname = new TextBox();
	TextBox nickname = new TextBox();
	Button erstelleUser = new Button("Account erstellen");
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();
	boolean nicknameVorhanden;

	/**
	 * Erzeugt die Dialogbox mit einem Label, dass zum eintragen der Daten auffordert, drei Textboxen für Vorname, Nachname
	 * und Nickname und einen Button zum anmelden des Nutzers auf der Pinnwand.
	 * 
	 * @param googleNutzer
	 */
	public LoginDialog(final LoginInfo googleNutzer) {

		/**
		 * Eintragen von Standardwerten in die Textboxen
		 */
		vorname.setText("Vorname");
		nachname.setText("Nachname");
		nickname.setText(googleNutzer.getNickname());

		/**
		 * Erzeugt ein User-Objekt mit den übergeben Daten
		 */
		erstelleUser.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				/**
				 * Holt das User-Objekt aus der erstelleNutzer-Methode und weißt ihm die Google-Mailadresse zu.
				 */
				final User user = erstelleNutzer();
				if (user.getVorname() != null) {
					user.setEmail(googleNutzer.getEmailAddress());

					/**
					 * Trägt das User-Objekt in die Datenbank ein und setzt es als aktuellen Nutzer.
					 * Schließt die Dialogbox.
					 */
					pinnwandVerwaltung.createUser(user.getVorname(),
							user.getNachname(), user.getNickname(),
							user.getEmail(), new AsyncCallback<User>() {
								@Override
								public void onFailure(Throwable caught) {
								}

								public void onSuccess(User result) {
									SocialMediaProjekt
											.setAktuellerNutzer(result);
									SocialMediaProjekt
											.starteSocialMediaProjekt();
									hide();
								}

							});

				} else {
					Window.alert("Angaben falsch/fehlen - bitte überprüfen Sie ihre Angaben");
				}
			}
		});

		dockPanel.add(datenEintragen, DockPanel.NORTH);
		dockPanel.add(vorname, DockPanel.NORTH);
		dockPanel.add(nachname, DockPanel.NORTH);
		dockPanel.add(nickname, DockPanel.NORTH);
		dockPanel.add(erstelleUser, DockPanel.NORTH);
		this.add(dockPanel);

	}

	/**
	 * Prüft ob in den Textboxen Daten angegeben sind, die nicht erwünscht sind und weist ansonsten die Daten aus
	 * den Textboxen einem User-Objekt zu.
	 * @return
	 */
	public User erstelleNutzer() {
		final User user = new User();

		if (nickname.getText().equals("") || vorname.getText().equals("")
				|| nachname.getText().equals("")
				|| nachname.getText().equals("Nachname")
				|| vorname.getText().equals("Vorname")) {

			user.setVorname(null);

		} else {

			user.setNickname(nickname.getText());
			user.setVorname(vorname.getText());
			user.setNachname(nachname.getText());
		}

		return user;
	};
}
