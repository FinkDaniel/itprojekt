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
 * Die Klasse <code>BeitragForm</code> stellt das Formular zum Hinzufügen neuer Beiträge zur Pinnwand des Nutzers dar. 
 * Der enthaltene Button <code>beitragButton</code> löst die Fuktion <code>public void onclick</code> aus, 
 * mit der die TextArea <code>beitragBox</code> ausgelesen wird und deren Wert <code>beitragBox.getText()</code> 
 * der Datenbank per Aufruf der Methode <code>createBeitrag</code> hinzugefügt wird. <p>
 * Nach erfolgreicher Ausführung wird die Pinnwand über das instanziierte Objekt <code>smp</code> der Klasse 
 * SocialMediaProjekt und der Methode <code>clearContent</code>, sowie <code>addPinnwandToContent</code> aktualisiert. 
 * @author Patrick
 *
 */
public class BeitragForm extends VerticalPanel {

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();
	Button beitragButton = new Button("Beitrag absenden");
	Beitrag beitrag = new Beitrag();
	final TextArea beitragBox = new TextArea();
	/**
	 * Der Standart Konstruktur weist den enthaltenen Elementen <code>beitragButton</code> und <code>beitragBox</code>
	 * die CSS Klassen <code>beitragBox</code> und <code>beitragButton</code> zu. 
	 */
	public BeitragForm() {

		beitragButton.getElement().setId("buttonBeitragSenden");
		beitragBox.getElement().setId("beitragBox");
		beitragBox.setText("Bitte Text eingeben");
		beitragBox.setVisibleLines(5);

		this.add(beitragBox);
		this.add(beitragButton);
		
		
		beitragButton.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (beitragBox.getText() == "") {
					Window.alert("Bitte Text eingeben");
				}

				
				beitrag.setBeitrag(beitragBox.getText());

				pinnwandVerwaltung.createBeitrag(beitragBox.getText(),

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

	}

}