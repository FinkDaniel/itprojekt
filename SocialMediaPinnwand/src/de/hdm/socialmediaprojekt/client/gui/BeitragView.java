package de.hdm.socialmediaprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;

/**
 * Die Beitrag View erzeugt eine Liste mit allen Beiträgen, die vom aktuellen
 * Nutzer oder seinen abonnierten Nutzeren geschrieben wurden und sortiert diese
 * nach Erstellungszeitpunkt mit dem neusten Beitrag oben.
 * 
 * @author T420
 * 
 */

public class BeitragView extends VerticalPanel {

	Beitrag beitrag = new Beitrag();
	BeitragCell beitragCell = new BeitragCell();
	final VerticalPanel kommentarView = new VerticalPanel();
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	/**
	 * Fügt ein Textfeld für den Kommentar hinzu
	 * 
	 * @param b
	 * @return
	 */
	public BeitragView(Beitrag beitrag) {
		this.addStyleName("BeitragView");
		addBeitrag(beitrag);
		addKommentarList(beitrag);
	}

	public BeitragView addKommentarForm(Beitrag b) {
		KommentarForm kommentarForm = new KommentarForm();
		this.add(kommentarForm);
		return this;
	}

	/**
	 * Erweitert die die Liste um einen Beitrag
	 * 
	 * @param inhalt
	 */
	public void addBeitrag(Beitrag inhalt) {
		beitragCell.clear();
		beitragCell.setText(inhalt.getBeitrag());
		beitragCell.setErsteller(inhalt.getSourceUserID());
		beitragCell.setErstellungszeitpunkt(inhalt.getHour(),
				inhalt.getMinute(), inhalt.getDay(), inhalt.getMonth(),
				inhalt.getYear());
		beitragCell.addButtons(inhalt);
		this.add(beitragCell);
	}

	/**
	 * Liest die Kommentare eines Beitrags aus und fügt eine Liste mit den
	 * Kommentaren sortiert nach dem Erstellungsdatum mit dem neusten Kommentar
	 * unten zum Beitrag hinzu
	 */
	public VerticalPanel addKommentarList(Beitrag beitrag) {

		kommentarView.clear();
		pinnwandVerwaltung.getKommentarByTargetBeitrag(beitrag.getId(),
				new AsyncCallback<Vector<Kommentar>>() {

					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(Vector<Kommentar> result) {

						if (result.size() != 0) {
							Widget[] kommentList = new Widget[result.size()];

							for (int b = 0; b < result.size(); b++) {

								KommentarCell kommentarCell = new KommentarCell();
								kommentarCell.clear();
								kommentarCell.setText(result.get(b)
										.getKommentar());
								kommentarCell.setErsteller(result.get(b)
										.getSourceUserID());
								kommentarCell.addDeleteButton(result.get(b));
								kommentarCell.addEditButton(result.get(b));
								kommentList[b] = kommentarCell;
								kommentarView.add(kommentList[b]);

							}

						}
					}
				});
		this.add(kommentarView);
		return this;
	}
}