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
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;

/**
 * Erszeugt ein Textfeld, durch welches der aktuelle Nutzer einen Beitrag
 * schreiben und abschicken kann.
 * 
 * @author T420
 * 
 */

public class BeitragForm extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	/**
	 * Erzeugt das Textfeld und den Button zum abschicken
	 */

	public BeitragForm() {

		final TextArea beitragBox = new TextArea();
		Button beitragButton = new Button("Beitrag absenden");

		beitragButton.setStyleName("Button");

		beitragBox.setVisibleLines(5);
		beitragBox.setText("Dein Beitrag");

		this.add(beitragBox);
		this.add(beitragButton);
		/**
		 * Prüft ob ein Beitrag eingegeben wurde und sendet diesen dann ab
		 */
		beitragButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (beitragBox.getText() == "") {
					Window.alert("Bitte Text eingeben");
				}

				Beitrag beitrag = new Beitrag();

				beitrag.setBeitrag(beitragBox.getText());

				pinnwandVerwaltung.createBeitrag(beitragBox.getText(),

				SocialMediaProjekt.getAktuellerNutzer().getId(),
						new AsyncCallback<Beitrag>() {

							public void onFailure(Throwable caught) {
								Window.alert("Failure2");
							}

							public void onSuccess(Beitrag result) {

								Window.alert("Beitrag wurde angelegt");
								SocialMediaPinnwand smp = new SocialMediaPinnwand();
								smp.clearContent();
								smp.addPinnwandToContent();
								beitragBox.setText("Bitte Text eingeben");

							}

						});
			}

		});

	}

}