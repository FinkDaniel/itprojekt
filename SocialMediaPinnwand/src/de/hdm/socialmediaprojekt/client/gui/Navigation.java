package de.hdm.socialmediaprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.LoginInfo;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Die Klasse <code>Navigation</code> dient zur Darstellung der Navigations Buttons <code>meinePinnwand</code>, 
 * <code>meineAbos</code>, zur Darstellung und Funktionsweise der SuggestBox <code>box</code>, <a>
 * sowie zur Darstellung des Buttons <code>logoutButton</code>
 * @author Team Gui (Prell, Feininger)
 *
 */

public class Navigation extends VerticalPanel {
	/**
	 * Dieser Button löst bei anklicken durch die Methode <code>public void onclick</code> weitere Methoden zur Anzeige der Pinnwand aus
	 */
	Button meinePinnwand = new Button("Meine Pinnwand");
	/**
	 * Dieser Button löst bei anklicken durch die Methode <code>public void onclick</code> weitere Methoden zur Anzeige der Abo Übersicht aus
	 */
	Button meineAbos = new Button("Meine Abos");
	Button abbonieren = new Button("User abbonieren");
	Button logout = new Button("Logout");
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();
	private LoginInfo loginInfo = null;
	final MultiWordSuggestOracle suggestBox = new MultiWordSuggestOracle();
	final SuggestBox box = new SuggestBox(suggestBox);

	@SuppressWarnings("deprecation")
	/**
	 * Im Standart Konstruktor werden den Navigationselementen die CSS Klassen <code>buttonNavigation</code> <a>
	 * bzw. die CSS Id <code>buttonAbonieren</code> und <code>logoutButton</code> zugewiesen
	 */
	public Navigation() {

		meinePinnwand.setStyleName("buttonNavigation");
		meineAbos.setStyleName("buttonNavigation");
		abbonieren.getElement().setId("buttonAbonieren");
		logout.getElement().setId("logoutButton");

		logout.addClickHandler(new ClickHandler() {
			/**
			 * Diese Methode löst den Logout Vorgang aus
			 */
			@Override
			public void onClick(ClickEvent event) {
				// Window.Location.assign(loginInfo.getLogoutUrl());
				Window.Location.assign("https://accounts.google.com/logout");
			}
		});
		
		meinePinnwand.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				/**
				 * Diese Methode löst durch die Instanziierung des <code>smp</code> Objekts der Klasse <code>SocialMediaProjekt</code>
				 * die Anzeige der Abo Übersicht aus
				 */
				SocialMediaProjekt smp = new SocialMediaProjekt();
				smp.clearContent();
				smp.addPinnwandToContent();
			}
		});

		meineAbos.addClickHandler(new ClickHandler() {
			/**
			 * Diese Methode löst weitere Methoden zur Anzeige der Abo Übersicht aus
			 */
			public void onClick(ClickEvent event) {
				SocialMediaProjekt smp = new SocialMediaProjekt();
				smp.clearContent();
				smp.addAbosToContent();
			}
		});

		/*
		 * Durch diesen Aufruf werden die Inhalte der SuggestBox <code>box</code> 
		 * durch die Datenbankabfrage <code>getAllUser</code> hinzugefügt 
		 */
		
		pinnwandVerwaltung.getAllUser(new AsyncCallback<Vector<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Vector<User> result) {

				// System.out.print(result);
				for (int i = 0; i < result.size(); i++) {
					suggestBox.add(result.get(i).getNickname().toString());

				}
			}
		});

		box.getElement().setId("textbox");

		abbonieren.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (box.getText() == "") {
					Window.alert("Bitte einen Nickname eingeben");
				}

				pinnwandVerwaltung.getUserByNickname(box.getText(),
						new AsyncCallback<User>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(User result) {
								Window.alert(SocialMediaProjekt
										.getAktuellerNutzer().getEmail());
								Window.alert("onSuccess");
								if (SocialMediaProjekt.getAktuellerNutzer()
										.getId() == result.getId()) {
									Window.alert("Du kannst dich nicht selbst abbonieren!");
								} else if (SocialMediaProjekt
										.getAktuellerNutzer().getId() != result
										.getId()) {
									pinnwandVerwaltung.createAbo(
											SocialMediaProjekt
													.getAktuellerNutzer()
													.getId(), result.getId(),
											new AsyncCallback<Abo>() {

												@Override
												public void onFailure(
														Throwable caught) {
													// TODO Auto-generated
													// method stub
													Window.alert("onFailure createAbo");
												}

												@Override
												public void onSuccess(Abo result) {
													Window.alert("Abo wurde angelegt");
													box.setText("");
												}

											});

									// Klammern passen
								}
							}

						});

			}
		});
		
	this.add(meinePinnwand);
	this.add(meineAbos);
	this.add(box);
	this.add(abbonieren);
	this.add(logout);
	
	}

}