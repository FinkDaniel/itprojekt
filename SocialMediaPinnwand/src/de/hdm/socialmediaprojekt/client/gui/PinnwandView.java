package de.hdm.socialmediaprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;

/**
 * Die Klasse <code>PinnwandView</code> stellt die Übersicht über aller, dem
 * Nutzer <code>SocialMediaProjekt.getgetAktuellerNutzer().getId()</code> 
 * zur Verfügung stehender Beiträge dar.
 * Damit diese angezeigt werden können, wird ein flexibles ScrollPanel benötigt,
 * welches durch hinzufügen eines Vertical Panel <code>pinnwandView</code> in der
 * Größe des Inhalts angepasst werden kann.
 * 
 * @author Team Gui (Prell, Feininger)
 * 
 */

public class PinnwandView extends ScrollPanel {

	VerticalPanel pinnwandView = new VerticalPanel();
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	public PinnwandView() {

		this.clear();
		this.setStyleName("pinnwand");

		createPinnwand();
		this.add(pinnwandView);

	}

	/**
	 * Die Methode <code>createPinnwand</code> ruft per Datenbankabfrage <code>getAboBySourcePinnwand</code> 
	 * alle mit der aktuellen Pinnwand des eingeloggten Users <code>SocialMediaProjekt.getAktuellerNutzer().getId()</code>
	 * verbundenen Abos auf und schreibt die einzelnen Id's in ein Array <code>abos</code>.<a>
	 * Im Anschluss daran werden alle Beiträge des jeweiligen Abos per Datenbankabfrage <code>getBeitragBySourceUser</code> abgefragt, 
	 * und dazu gehörige Objekte <code>beitragCell</code>'s der Klasse <code>BeitragCell</code> erstellt und in ein Widget Array <code>objectList</code> 
	 * gespeichert. 
	 * Die fertige <code>objectList</code> wird dann dem Objekt <code>pinnwandView</code> hinzugefügt und per <code>return this</code>
	 * zurück gegeben
	 * @return this
	 */
	private VerticalPanel createPinnwand() {

		pinnwandView.setStyleName("pinnwandView");

		pinnwandVerwaltung.getAboBySourcePinnwand(SocialMediaProjekt
				.getAktuellerNutzer().getId(),
				new AsyncCallback<Vector<Abo>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Vector<Abo> result) {
						int[] abos = new int[result.size() + 1];
						abos[0] = SocialMediaProjekt.getAktuellerNutzer()
								.getId();

						for (int i = 1; i < result.size(); i++) {
							abos[i] = result.get(i).getTargetPinnwandID();
							// System.out.print(result.get(i).getTargetPinnwandID());
							System.out.print(abos[i]);
						}
						pinnwandVerwaltung.getBeitragBySourceUser(abos,
								new AsyncCallback<Vector<Beitrag>>() {

									public void onFailure(Throwable caught) {
										pinnwandView.add(new Label("Blubb"));

									}

									public void onSuccess(Vector<Beitrag> result) {
										Widget[] objectList = new Widget[result
												.size()];

										for (int i = 0; i < result.size(); i++) {
											BeitragCell beitragCell = new BeitragCell();
											beitragCell.clear();

											beitragCell.setErsteller(result
													.get(i).getSourceUserID());
											beitragCell.addErsteller();
											beitragCell
													.setErstellungszeitpunkt(
															result.get(i)
																	.getHour(),
															result.get(i)
																	.getMinute(),
															result.get(i)
																	.getDay(),
															result.get(i)
																	.getMonth(),
															result.get(i)
																	.getYear());
											beitragCell
													.addErstellungszeitpunkt();
											beitragCell.setText(result.get(i)
													.getBeitrag());

											beitragCell.addButtons();
											objectList[i] = beitragCell;
											pinnwandView.add(objectList[i]);

										}

									}

								});

					}
				});

		return pinnwandView;
	}
}
