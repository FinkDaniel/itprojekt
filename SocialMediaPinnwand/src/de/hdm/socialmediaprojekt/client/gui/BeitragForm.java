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
		beitragBox.setText("Bitte Text eingeben");

		this.add(beitragBox);
		this.add(beitragButton);

		beitragButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (beitragBox.getText() == ""
						|| beitragBox.getText() == "Bitte Text eingeben") {
					Window.alert("Bitte Text eingeben");

				} else if (beitragBox.getText() != ""
						|| beitragBox.getText() != "Bitte Text eingeben") {

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
									beitragBox.setText("Bitte Text eingeben");
									SocialMediaProjekt smp = new SocialMediaProjekt();
									smp.clearContent();
									smp.addPinnwandToContent();
								}

							});

				}

<<<<<<< HEAD
			};
=======
				Beitrag beitrag = new Beitrag();
				beitrag.setBeitrag(beitragBox.getText());
				//pinnwandVerwaltung.createBeitrag(beitrag, new AsyncCallback<Void>() {
>>>>>>> refs/remotes/origin/master

<<<<<<< HEAD
		});
=======
					//public void onFailure(Throwable caught) {
					//	Window.alert("Failure1");
					//}

					//public void onSuccess(Void result) {

						pinnwandVerwaltung.createBeitrag(
								beitragBox.getText(),
								
								SocialMediaProjekt.getAktuellerNutzer().getId(),
								new AsyncCallback<Beitrag>() {

									
									public void onFailure(Throwable caught) {
										Window.alert("Failure2");
									}

									public void onSuccess(Beitrag result) {
										
										Window.alert("Beitrag wurde angelegt");
										SocialMediaProjekt smp = new SocialMediaProjekt();
										smp.clearContent();
										smp.addPinnwandToContent();
										beitragBox.setText("Bitte Text eingeben");
									}

								});
					}

				

				});

//			}
		//});

>>>>>>> refs/remotes/origin/master
	}
<<<<<<< HEAD
}
=======

}
>>>>>>> refs/remotes/origin/master
