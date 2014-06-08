package de.hdm.socialmediaprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Die Klasse <code>AboView</code> stellt die Übersicht über alle abonierten
 * Pinnwände in Textform dar. Damit diese angezeigt werden kann, wird ein
 * flexibles ScrollPanel benötigt, welches durch hinzufügen eines Vertical Panel
 * <code>aboView</code> in der Größe des Inhalts angepasst werden kann. Durch
 * das Objekt <code>pinnwandVerwaltung</code> der Klasse
 * <code>PinnwandVerwaltungAsync</code> wird die Art des Datenbankzugriffs
 * gestaltet.
 * 
 * @author Team Gui (Prell, Feininger)
 * 
 */

public class AboView extends ScrollPanel {

	VerticalPanel aboView = new VerticalPanel();
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();
	final AboCell aboCell = new AboCell();

	/**
	 * Der Standart Konstruktor <code>AboView()</code> bereinigt das aktuelle
	 * Objekt, fügt diesem die CSS Klasse <code>aboView</code> hinzu und ruft
	 * die Methode <code>createAboView</code> auf, und fügt das VerticalPanel
	 * <code>aboView> dem instanziierten Objekt hinzu.
	 */
	public AboView() {

		this.clear();
		this.getElement().setId("aboView");
		createAboView();
		this.add(aboView);

	}
	/**
	 * Die Methode <code>createAboView()</code> ruft per Datenbankabfrage <code>getAboBySourcePinnwand</code> 
	 * alle mit der aktuellen Pinnwand des eingeloggten Users <code>SocialMediaProjekt.getAktuellerNutzer().getId()</code>
	 * verbundenen Abos auf und schreibt die einzelnen <code>aboCell</code>'s in ein Widget Array <code>objectList</code>. 
	 * Die fertige <code>objectList</code> wird dann dem Objekt <code>aboView</code> hinzugefügt und per <code>return this</code>
	 * zurück gegeben
	 * @return this
	 */
	private VerticalPanel createAboView() {

		pinnwandVerwaltung.getAboBySourcePinnwand(SocialMediaProjekt
				.getAktuellerNutzer().getId(),
				new AsyncCallback<Vector<Abo>>() {

					public void onFailure(Throwable caught) {
						aboView.add(new Label("Blubb"));

					}

					public void onSuccess(Vector<Abo> result) {
						Widget[] objectList = new Widget[result.size()];

						for (int i = 0; i < result.size(); i++) {

							aboCell.clear();
							pinnwandVerwaltung.getUserById(result.get(i)
									.getTargetPinnwandID(),
									new AsyncCallback<User>() {

										@Override
										public void onFailure(Throwable caught) {
											// TODO Auto-generated method stub

										}

										@Override
										public void onSuccess(User result) {
											// TODO Auto-generated method stub
											aboCell.setText(result
													.getNickname());
										}
									});
							aboCell.addButton(result.get(i));

							objectList[i] = aboCell;

							// System.out.print(objectList[i]);
							aboView.add(objectList[i]);
						}
					}

				});
		return aboView;
	}

}