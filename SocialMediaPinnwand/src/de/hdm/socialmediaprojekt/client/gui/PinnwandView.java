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
 * Die PinnwandView besteht aus der BeitragForm zum erzeugen eigener Beiträge
 * und der BeitragView, die sämtliche Beiträge des Nutzers oder abonnierter
 * Nutzer anzeigt
 * 
 * @author Paul
 * 
 */
public class PinnwandView extends ScrollPanel {

	VerticalPanel pinnwandView = new VerticalPanel();

	public PinnwandView() {

		this.clear();
		this.getElement().setId("pinnwand");
		this.setAlwaysShowScrollBars(true);

		createPinnwand();
		this.add(pinnwandView);

	}

	/**
	 * Erzeugt die Pinnwand
	 * 
	 * @return
	 */
	private VerticalPanel createPinnwand() {

		pinnwandView.getElement().setId("pinnwandView");

		final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
				.getPinnwandVerwaltung();
		/**
		 * Liest sämtliche abonnierte Nutzer des aktuellen Nutzers aus und und
		 * erstellt eine Liste aus allen Beiträgen der Nutzer
		 */
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

											BeitragView beitragView = new BeitragView(
													result.get(i));

											objectList[i] = beitragView;

											pinnwandView.add(objectList[i]);

										}

									}

								});

					}
				});

		return pinnwandView;
	}
}