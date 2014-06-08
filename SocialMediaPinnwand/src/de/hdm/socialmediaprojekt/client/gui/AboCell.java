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
 * Die Klasse <code>AboCell</code> stellt die einzelne Abo Zelle dar, diese
 * enthält die horizontal angeordneten Elemente <code>text</code> und die
 * Möglichkeit das Abo über den Button <code>loeschen</code> zu entfernen
 * 
 * @author Team Gui (Prell, Feininger)
 * 
 */

public class AboCell extends HorizontalPanel {

	Button loeschen = new Button("X");
	HorizontalPanel text = new HorizontalPanel();
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	/**
	 * Über den Standart Konstruktor <code>AboCell()</code> wird dem
	 * instanziierten Objekt die CSS Klasse <code>aboCell</code> zugeordnet
	 * 
	 * @author Team Gui (Prell, Feininger)
	 */
	public AboCell() {
		this.setStyleName("aboCell");
	}

	/**
	 * Die Methode <code>setText</code> erstellt ein neues Label <code>i</code>
	 * und fügt dieses dem <code>text</code> Panel, sowie dem
	 * <code>AboCell</code> Objekt hinzu
	 * 
	 * @param inhalt
	 * @author Team Gui (Prell, Feininger)
	 */
	public void setText(String inhalt) {

		Label i = new Label(inhalt);
		text.add(i);
		this.add(text);
	}

	/**
	 * Die Methode <code>addButton</code> fügt dem <code>AboCell</code> Objekt
	 * den Button <code>loeschen</code> hinzu, damit wird erreicht, dass bei
	 * anklicken des <code>loeschen</code> Buttons, das bei Methodenaufruf
	 * übergebene Abo <code>abo</code> aus der Datenbank durch die Methode
	 * <code>deleteAbo</code> gelöscht wird
	 * 
	 * @param abo
	 * @return this
	 * @author Team Gui (Prell, Feininger)
	 */
	public AboCell addButton(final Abo abo) {

		loeschen.setStyleName("Button");
		loeschen.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				pinnwandVerwaltung.deleteAbo(abo, new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Abo wurde beendet.");

					}
				});

			}
		});
		this.add(loeschen);
		return this;
	}
}