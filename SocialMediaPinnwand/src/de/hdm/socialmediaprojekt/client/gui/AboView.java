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

public class AboView extends ScrollPanel {

	VerticalPanel aboView = new VerticalPanel();

	public AboView() {

		this.clear();
		this.getElement().setId("AboView");
		this.setAlwaysShowScrollBars(true);

		createPinnwand();
		this.add(aboView);

	}

	private VerticalPanel createPinnwand() {

		aboView.getElement().setId("pinnwandView");

		final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
				.getPinnwandVerwaltung();

		pinnwandVerwaltung.getAboBySourcePinnwand(SocialMediaProjekt
				.getAktuellerNutzer().getId(),
				new AsyncCallback<Vector<Abo>>() {

					public void onFailure(Throwable caught) {
						aboView.add(new Label("Blubb"));

					}

					public void onSuccess(Vector<Abo> result) {
						Widget[] objectList = new Widget[result.size()];



						for (int i = 0; i < result.size(); i++) {

							final AboCell aboCell = new AboCell();
							aboCell.clear();
							pinnwandVerwaltung.getUserById(result.get(i).getTargetPinnwandID(), new AsyncCallback<User>(){

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onSuccess(User result) {
									// TODO Auto-generated method stub
									aboCell.setText(result.getNickname());
								}});
							aboCell.addButton(result.get(i));
							
							objectList[i] = aboCell;

							//System.out.print(objectList[i]);
							aboView.add(objectList[i]);
						}
					}


				});
		return aboView;
	}

}