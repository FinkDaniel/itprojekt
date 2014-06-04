package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;

public class KommentarForm extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	// SocialMediaProjekt smp = new SocialMediaProjekt.aktuellerUser();

	public KommentarForm() {

		final TextArea kommentarBox = new TextArea();
		Button kommentarButton = new Button("Kommenatar absenden");

		kommentarButton.setStyleName("Button");

		kommentarBox.setVisibleLines(3);
		kommentarBox.setText("Bitte Text eingeben");

		this.add(kommentarBox);
		this.add(kommentarButton);

		kommentarButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (kommentarBox.getText() == ""
						|| kommentarBox.getText() == "Bitte Text eingeben") {
					Window.alert("Bitte Text eingeben");

				} else if (kommentarBox.getText() != ""
						|| kommentarBox.getText() != "Bitte Text eingeben") {

					Kommentar kommentar = new Kommentar();
					kommentar.setKommentar(kommentarBox.getText());

					pinnwandVerwaltung.createKommentar(kommentarBox.getText(),

					SocialMediaProjekt.getAktuellerNutzer().getId(), 2,
							new AsyncCallback<Kommentar>() {
								// 2 als Platzhalter für Abfrage nach zu
								// kommentierenden Beitrag
								public void onFailure(Throwable caught) {
									Window.alert("Failure2");
								}

								public void onSuccess(Kommentar result) {

									Window.alert("Kommentar wurde angelegt");
									kommentarBox.setText("Bitte Text eingeben");
									SocialMediaProjekt smp = new SocialMediaProjekt();
									smp.clearContent();
									smp.addPinnwandToContent();
								}

							});

				}

			};

		});
	}
}