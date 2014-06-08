package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Die Klasse <code>BeitragCell</code> stellt die einzelne Beitrag Zelle dar, 
 * diese enthält die vertikal angeordneten Elemente:<p>
 * <code>ersteller</code> zur Anzeige der Informationen über Ersteller<p>
 * <code>text</code> zur Anzeige des Beitraginhaltes<p>
 * <code>buttons</code> Panel zur Anzeige der <code>like</code> und <code>kommentieren</code> Buttons <p>
 *  
 * 
 * @author Team Gui (Prell, Feininger)
 *
 */


public class BeitragCell extends VerticalPanel {

	HorizontalPanel ersteller = new HorizontalPanel();
	HorizontalPanel text = new HorizontalPanel();
	HorizontalPanel buttons = new HorizontalPanel();
	Label beitragText = new Label();
	Label erstellungszeitpunkt = new Label();
	Button like = new Button("Dufte");
	Button kommentieren = new Button("kommentieren");
	
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();
	/**
	 * Der Standart Konstruktur fügt dem instanziierten Objekt die CSS Klasse <code>beitragCell</code> hinzu
	 */
	public BeitragCell() {

		this.addStyleName("beitragCell");

	}
	/**
	 * Die Methode <code>setText</code> fügt dem Objekt den übergebenen Text <code>inhalt</code> 
	 * mittels des Labels <code>beitragText</code> hinzu
	 * @param inhalt
	 */
	public void setText(String inhalt) {

		beitragText.setText(inhalt);
		text.add(beitragText);
		this.add(text);
	}
	
	/**
	 * Die Methode <code>addButtons</code> fügt dem Objekt das Button Panel <code>buttons</code>
	 * inklusive der Buttons <code>like</code> und <code>kommentieren</code> hinzu und 
	 * weist den Buttons die CSS Klassen <code>like</code> bzw. <code>kommentieren</code> hinzu <p>
	 * Nach erfolgreicher Abarbeitung wird das aktualisierte Objekt zurück gegeben. 
	 * @return this
	 */
	public BeitragCell addButtons() {

		like.setStyleName("like");
		kommentieren.setStyleName("kommentieren");

		buttons.add(like);
		buttons.add(kommentieren);

		this.add(buttons);

		return this;
	}
	/**
	 * Die Methode <code>setErsteller</code> fragt die Userdaten der übergebenen User Id <code>derErsteller</code>
	 * per Datenbankzugriff ab, und fügt diese dem Label <code>l</code> hinzu.<p>
	 * Nach erfolgreicher Datenbankabfrage <code>getUserById</code> und erfolgreicher Zuweisung der Userdaten 
	 * wird das Label <code>l</code> dem HorizontalPanel <code>ersteller</code> hinzugefügt
	 * @param derErsteller
	 */
	public void setErsteller(int derErsteller) {

		ersteller.setStyleName("panelErsteller");

		pinnwandVerwaltung.getUserById(derErsteller, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(User result) {
				String dername = new String();
				dername = result.getNickname();
				Label l = new Label(dername +" schrieb");
				l.setStyleName("labelVerfasser");
				ersteller.add(l);
			}
		});
		
	}
	/**
	 * Die Methode <code>addErsteller()</code> weist dem Objekt das aktuelle HorizontalPanel <code>ersteller</code> zu
	 * und gibt das Objekt zurück. 
	 * @return this
	 */
	public BeitragCell addErsteller(){
		this.add(ersteller);
		return this;
	}
	/**
	 * Die Methode <code>setErstellungszeitpunkt</code> fügt dem Objekt die übergebenen Parameter: <p>
	 * <code>hour</code>, <code>minute</code>, <code>day</code>, <code>month</code>, <code>year</code> <p>
	 * als, in das Label <code>erstellungsZeitpunkt</code> gespeicherten Text, hinzu.
	 * @param hour
	 * @param minute
	 * @param day
	 * @param month
	 * @param year
	 */
	public void setErstellungszeitpunkt(int hour, int minute, int day,
			int month, int year) {
		erstellungszeitpunkt.setStyleName("labelErstellungszeitpunkt");
		erstellungszeitpunkt.setText("am "+ day +"." +month +"." +year +",um " + hour + ":" +minute);
		
	
	}
	/**
	 * Die Methode <code>addErstellungszeitpunkt</code> weist dem Panel <code>ersteller<code> das Label <code>erstellungszeitpunkt</code> zu, 
	 * und fügt diese dem Objekt hinzu und gibt dieses anschließend zurück. 
	 * @return
	 */
	public BeitragCell addErstellungszeitpunkt(){
		ersteller.add(erstellungszeitpunkt);
		this.add(ersteller);
		return this;
	}
}