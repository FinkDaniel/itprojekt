package de.hdm.socialmediaprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Die Navigationsleiste bietet die meisten Werkzeuge für den Nutzer. Er kann
 * hier zwischen seiner Pinnwand und seiner Aboliste wechseln. Er kann andere
 * Nutzer über eine SuggestBox suchen und diese Aboonieren. Er kann sich
 * ausloggen oder seinen Account komplett löschen.
 * 
 * @author Paul
 * 
 */
public class Navigation extends VerticalPanel {

	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();
	Button meinePinnwand = new Button("Meine Pinnwand");
	Button meineAbos = new Button("Meine Abos");
	Button logout = new Button("Logout");
	Button deleteUser = new Button("Account löschen");

	final MultiWordSuggestOracle suggestBox = new MultiWordSuggestOracle();
	final SuggestBox box = new SuggestBox(suggestBox);

	static Label eingeloggtals = new Label();

	public Navigation() {

		meinePinnwand.addStyleName("Button");
		meineAbos.addStyleName("Button");
		logout.addStyleName("Button");
		deleteUser.addStyleName("Button");
		/**
		 * Öffnet die DialogBox welche den Nutzer fragt ob er sich sicher ist ob
		 * er löschen möchte
		 */
		deleteUser.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DeleteUser deleteUser = new DeleteUser(SocialMediaProjekt
						.getAktuellerNutzer());
				deleteUser.addSicherheitsAbfrage();
				deleteUser.center();
				deleteUser.show();

			}
		});

		this.add(meinePinnwand);
		this.add(meineAbos);
		this.add(logout);
		this.add(deleteUser);
		this.add(eingeloggtals);
		/**
		 * Loggt den Nutzer aus und öffnet den Google Login
		 */

		logout.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				loadLogout();

			}

		});
		/**
		 * Öffnet die PinnwandView im Content Panel
		 */
		meinePinnwand.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				SocialMediaPinnwand smp = new SocialMediaPinnwand();
				smp.setAboViewStatus(false);
				smp.clearContent();
				smp.addPinnwandToContent();
			}
		});
		/**
		 * Öffnet die AboView im Content Panel
		 */
		meineAbos.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				SocialMediaPinnwand smp = new SocialMediaPinnwand();
				smp.setAboViewStatus(true);
				smp.clearContent();
				smp.addAbosToContent();
			}
		});

		final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
				.getPinnwandVerwaltung();
		/**
		 * Füllt die SuggestBox mit den Nicknames aller angemeldeten Nutzer
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

		this.add(box);

		Button abbonieren = new Button("User abbonieren");
		abbonieren.setStyleName("Button");
		this.add(abbonieren);
		/**
		 * Prüft ob die SuggestBox gefüllt ist und liest sie aus.
		 */
		abbonieren.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {

				if (box.getText() == "") {
					Window.alert("Bitte einen Nickname eingeben");
				}
				/**
				 * Prüft ob der Inhalt der SuggestBox mit dem eigenen Nickname
				 * übereinstimmt und abonniert den Nutzer
				 */
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

												}

												@Override
												public void onSuccess(Abo result) {
													Window.alert("Abo wurde angelegt");
													SocialMediaPinnwand smp = new SocialMediaPinnwand();
													if (smp.getAboViewStatus() == true) {
														smp.clearContent();
														smp.addAbosToContent();
													}

												}

											});

								}
							}

						});

			}
		});
	}

	/**
	 * Läd die Google Login Seite
	 */
	private void loadLogout() {
		// Window.Location.assign(loginInfo.getLogoutUrl());
		Window.Location.assign(GWT.getHostPageBaseURL());
	}

	/**
	 * Erzeugt ein Label mit dem aktuellen Nutzer
	 * 
	 * @param u
	 */
	public static void setEingeloggtAls(User u) {
		eingeloggtals.setText("Eingeloggt als: " + u.getNickname());
	}

}