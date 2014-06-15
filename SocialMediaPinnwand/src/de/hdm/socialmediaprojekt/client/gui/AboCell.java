package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Abo;

/**
 * Gibt ein Abonnement des Aktuellen Nutzers in Form des Nicknames an und gibt
 * ihm die Möglichkeit dieses wieder zu beenden.
 * 
 * @author Team GUI
 * 
 */
public class AboCell extends HorizontalPanel {
	/**
	 * No-Arguments-Konstruktor
	 */

	public AboCell() {
		this.setStyleName("BeitragCell");
	}

	/**
	 * Setzt den Text einer Abo-Cell, in diesem Fall der Nickname des
	 * Abonnierten
	 * 
	 * @param inhalt
	 */

	public void setText(String inhalt) {
		HorizontalPanel text = new HorizontalPanel();
		Label i = new Label(inhalt);
		text.add(i);
		this.add(text);
	}

	/**
	 * Erzeugt einen Button, welcher beim Klick das Abonnement beendet
	 * 
	 * @param abo
	 * @return
	 */

	public AboCell addButton(final Abo abo) {

		Button loeschen = new Button("X");
		loeschen.setStyleName("Button");
		loeschen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
						.getPinnwandVerwaltung();
				pinnwandVerwaltung.deleteAbo(abo, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Abo wurde beendet.");
						SocialMediaPinnwand smp = new SocialMediaPinnwand();
						smp.clearContent();
						smp.addAbosToContent();

					}
				});

			}
		});
		this.add(loeschen);
		return this;
	}
}