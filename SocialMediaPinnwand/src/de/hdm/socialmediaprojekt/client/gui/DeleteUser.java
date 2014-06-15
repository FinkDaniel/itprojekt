package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * DialogBox die prüfen soll, ob der aktuelle Nutzer seinen Account wirklich
 * löschen möchte.
 * 
 * @author Team GUI
 * 
 */
public class DeleteUser extends DialogBox {

	User user = new User();
	HorizontalPanel sicherheitsAbfrage = new HorizontalPanel();
	Label sicherheitsText = new Label(
			"Willst du wirklich die Social Media Pinnwand nicht mehr benutzen können?");
	Button yes = new Button("Ja - User löschen");
	Button no = new Button("Niemals!");
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	public DeleteUser(User aktuellerUser) {
		user = aktuellerUser;
		/**
		 * Schließt die Dialogbox
		 */
		no.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				schließen();
			}

		});
		/**
		 * Löscht den User von der Pinnwand und aus der Datenbank
		 */
		yes.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				pinnwandVerwaltung.deleteUser(user, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Selbst Schuld!");
						Window.Location.assign("www.google.de");
					}
				});

			}

		});

	}

	public DialogBox addSicherheitsAbfrage() {
		sicherheitsAbfrage.add(sicherheitsText);
		sicherheitsAbfrage.add(yes);
		sicherheitsAbfrage.add(no);
		this.add(sicherheitsAbfrage);
		return this;
	}

	public void schließen() {
		this.hide();
	}

}