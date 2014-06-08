package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

/**
 * Die KLasse <code>Content</code> wird als Rahmen zur Darstellung des instanziierten Objekts der <code>pinnwandView</code>
 * bzw. der <code>aboView<code> verwendet.  
 * @author Team Gui (Prell, Feininger)
 *
 */

public class Content extends DockPanel {

	Label pinnwandMenuepunkt = new Label("Meine Pinnwand");
	BeitragForm beitragForm = new BeitragForm();
	Label aboMenuepunkt = new Label("Meine Abos");
	PinnwandView pinnwandView = new PinnwandView();
	AboView aboView = new AboView();
	
	public Content() {

		this.add(new HTML("Test2353"), DockPanel.NORTH);

	}
	/**
	 * Die Methode <code>addPinnwand()</code> fügt dem Objekt das Label <code>pinnwandMenuepunkt</code>, <a>
	 * das instanziierte Objekt <code>beitragForm</code> der Klasse <code>BeitragForm</code>, <a>
	 * sowie das instanziierte Objekt <code>pinnwandView</code> der Klasse <code>PinnwandView</code> hinzu 
	 * und gibt das aktualisierte Objekt zurück.  
	 * @return this;
	 */
	public DockPanel addPinnwand() {

		this.clear();

		pinnwandMenuepunkt.getElement().setId("pinnwandMenuepunkt");
		this.add(pinnwandMenuepunkt, DockPanel.NORTH);

		this.add(beitragForm, DockPanel.WEST);

		
		this.add(pinnwandView, DockPanel.SOUTH);

		return this;
	}
	/**
	 * Die Methode <code>addMeineAbbos()</code> fügt dem Objekt das Label <code>aboMenuepunkt</code> <a>
	 * und das instanziierte Objekt <code>aboView</code> der Klasse <code>AboView</code> hinzu 
	 * und gibt das aktualisierte Objekt zurück.  
	 * @return this;
	 */
	public DockPanel addMeineAbos() {

		this.clear();

		aboMenuepunkt.getElement().setId("aboMenuepunkt");
		this.add(aboMenuepunkt, DockPanel.WEST);

		this.add(aboView, DockPanel.SOUTH);
		return this;
	}
}
