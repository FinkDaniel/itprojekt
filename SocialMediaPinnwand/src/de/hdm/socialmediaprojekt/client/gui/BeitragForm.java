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

public class BeitragForm extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	// SocialMediaProjekt smp = new SocialMediaProjekt.aktuellerUser();

	public BeitragForm() {

		final TextArea beitragBox = new TextArea();
		Button beitragButton = new Button("Beitrag absenden");

		beitragButton.setStyleName("Button");

		beitragBox.setVisibleLines(5);
		beitragBox.setText("Dein Beitrag");

		this.add(beitragBox);
		this.add(beitragButton);

		beitragButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (beitragBox.getText() == "") {
					Window.alert("Bitte Text eingeben");
				}

				Beitrag beitrag = new Beitrag();
				beitrag.setBeitrag(beitragBox.getText());
				pinnwandVerwaltung.save(beitrag, new AsyncCallback<Void>() {

					public void onFailure(Throwable caught) {
						Window.alert("Failure1");
					}

					public void onSuccess(Void result) {

						pinnwandVerwaltung.createBeitrag(
								beitragBox.getText(),
								
								SocialMediaProjekt.getAktuellerNutzer().getId(),
								new AsyncCallback<Beitrag>() {

									
									public void onFailure(Throwable caught) {
										Window.alert("Failure2");
									}

									public void onSuccess(Beitrag result) {
										
										Window.alert("Beitrag wurde angelegt");
									}

								});
					}

				

				});

			}
		});

	}

}