package de.hdm.socialmediaprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.Like;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Eine Beitrag-Cell enthält einen Beitrag des Nutzers oder eines abonnierten
 * Nutzers.
 * 
 * @author Team GUI
 * 
 */

public class BeitragCell extends VerticalPanel {

	HorizontalPanel text = new HorizontalPanel();
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	public BeitragCell() {

		this.addStyleName("beitragCell");

	}

	/**
	 * Setzt den Text des Beitrags
	 * 
	 * @param inhalt
	 */

	public void setText(String inhalt) {

		Label i = new Label(inhalt);
		i.setStyleName("beitrag-text-style");
		text.add(i);
		this.add(text);
	}

	/**
	 * Eine Beitrag-Cell beinhaltet neben dem Text jeweils Buttons zum Liken,
	 * Kommentieren, Editieren und Löschen des Beitrags. Dazu kommt eine Angabe
	 * über den Verfasser und das Erstellungsdatum, sowie über die Anzahl der
	 * Likes.
	 * 
	 * @param beitrag
	 * @return
	 */
	public BeitragCell addButtons(Beitrag beitrag) {
		HorizontalPanel buttons = new HorizontalPanel();

		final Beitrag btrag = beitrag;
		final Button like = new Button("Dufte");
		like.setStyleName("Button like");
		final TextBox kommenttext = new TextBox();
		kommenttext.getElement().setId("kommentarBox");
		Button kommentieren = new Button("kommentieren");
		final Label anzahllikes = new Label();
		Button deleteBeitrag = new Button("löschen");

		Button editBeitrag = new Button("E");

		editBeitrag.setStyleName("Button edit");
		like.setStyleName("Button like");
		kommentieren.setStyleName("Button add");
		deleteBeitrag.setStyleName("Button delete");

		buttons.add(editBeitrag);
		buttons.add(like);
		buttons.add(kommenttext);
		buttons.add(kommentieren);
		/**
		 * Dialogbox zum editieren des Beitrags
		 */
		final DialogBox db = new DialogBox();
		db.getElement().setId("beitragEditBox");
		final FlowPanel panel = new FlowPanel();
		final TextBox text = new TextBox();
		text.setText(btrag.getBeitrag());
		db.setTitle("Beitrag ändern");
		panel.add(text);
		final Button ok = new Button("speichern");
		panel.add(ok);
		/**
		 * Der OK-Button schließt die Dialogbox und editiert den Beitrag
		 */
		ok.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btrag.setBeitrag(text.getText());
				db.hide();
				pinnwandVerwaltung.editBeitrag(btrag,
						new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(Void result) {
								Window.alert("Beitrag wurde geändert");
								SocialMediaPinnwand smp = new SocialMediaPinnwand();
								smp.clearContent();
								smp.addPinnwandToContent();

							}
						});

			}

		});

		db.add(panel);
		/**
		 * Prüft ob der aktuelle Nutzer auch der Verfasser des Beitrags ist und
		 * öffnet dann die Dialogbox zum editieren
		 */
		editBeitrag.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (btrag.getSourceUserID() == SocialMediaProjekt
						.getAktuellerNutzer().getId()) {
					db.show();
					db.center();

				} else if (btrag.getSourceUserID() != SocialMediaProjekt
						.getAktuellerNutzer().getId()) {
					Window.alert("Du kannst nur deine eigenen Beiträge editieren");

				}

			}
		});

		deleteBeitrag.addClickHandler(new ClickHandler() {
			/**
			 * Prüft ob der aktuelle Nutzer auch der Verfasser des Beitrags ist
			 * und löscht dann den Beitrag
			 */
			@Override
			public void onClick(ClickEvent event) {
				if (SocialMediaProjekt.getAktuellerNutzer().getId() == btrag
						.getSourceUserID()) {
					pinnwandVerwaltung.deleteBeitrag(btrag,
							new AsyncCallback<Void>() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onSuccess(Void result) {

									Window.alert("Der Beitrag wurde gelöscht.");
									SocialMediaPinnwand smp = new SocialMediaPinnwand();
									smp.clearContent();
									smp.addPinnwandToContent();

								}
							});
				} else {
					Window.alert("Du kannst nur deine eigenen Beiträge löschen");
				}

			}
		});
		buttons.add(deleteBeitrag);
		/**
		 * Prüft ob und wie viele Personen den Beitrag geliket haben und passt
		 * das Label entsprechend an.
		 */
		pinnwandVerwaltung.getLikeByTargetBeitrag(beitrag.getId(),
				new AsyncCallback<Vector<Like>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Vector<Like> result) {
						if (result.size() == 0) {
							anzahllikes
									.setText("Aktuell findet das keiner dufte.");
						} else if (result.size() == 1) {
							anzahllikes.setText("1 Person findet das dufte.");
						} else if (result.size() > 1) {
							anzahllikes.setText(result.size()
									+ " Personen finden das dufte.");
						}
					}

				});

		buttons.add(anzahllikes);
		/**
		 * Prüft beim Klick ob der aktuelle Nutzer den Button schon geliket hat
		 * wenn nicht setzt er einen Like. Im Fall das der Beitrag schon geliket
		 * wurde wird der Like wieder gelöscht
		 */
		like.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// überprüfen ob schon geliket, wenn nicht machen, ansonsten
				// like löschen.
				pinnwandVerwaltung.getLikeByTargetBeitrag(btrag.getId(),
						new AsyncCallback<Vector<Like>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(Vector<Like> result) {
								boolean flag = false;
								Like l = new Like();
								for (int i = 0; i < result.size(); i++) {

									if (result.get(i).getSourceUserID() == SocialMediaProjekt
											.getAktuellerNutzer().getId()) {
										flag = true;
										l = result.get(i);
									} else {
										flag = false;
									}

								}
								if (flag == true) {
									// like loeschen
									pinnwandVerwaltung.deleteLike(l,
											new AsyncCallback<Void>() {

												@Override
												public void onFailure(
														Throwable caught) {
													// TODO Auto-generated
													// method stub

												}

												@Override
												public void onSuccess(
														Void result) {
													Window.alert("Du findest das nicht mehr dufte.");
													SocialMediaPinnwand smp = new SocialMediaPinnwand();
													smp.clearContent();
													smp.addPinnwandToContent();

												}
											});
								} else if (flag == false) {
									// like setzen
									pinnwandVerwaltung.createLike(
											SocialMediaProjekt
													.getAktuellerNutzer()
													.getId(), btrag.getId(),
											new AsyncCallback<Like>() {

												@Override
												public void onFailure(
														Throwable caught) {
													// TODO Auto-generated
													// method stub

												}

												@Override
												public void onSuccess(
														Like result) {
													Window.alert("Ihnen gefällt dieser Beitrag!");
													like.setText("Entduften");
													SocialMediaPinnwand smp = new SocialMediaPinnwand();
													smp.clearContent();
													smp.addPinnwandToContent();

												}
											});
								}

							}
						});

			}
		});

		kommentieren.addClickHandler(new ClickHandler() {
			/**
			 * Setzt beim Klick den im Kommentar Feld geschriebenen Text als
			 * Kommentar unter den Beitrag
			 */
			@Override
			public void onClick(ClickEvent event) {
				pinnwandVerwaltung.createKommentar(kommenttext.getText(),
						SocialMediaProjekt.getAktuellerNutzer().getId(),
						btrag.getId(), new AsyncCallback<Kommentar>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(Kommentar result) {
								Window.alert("Kommentar wurde gespeichert");
								SocialMediaPinnwand smp = new SocialMediaPinnwand();
								smp.clearContent();
								smp.addPinnwandToContent();

							}
						});

			}
		});

		this.add(buttons);

		return this;
	}

	/**
	 * Erzeugt ein Label mit dem Ersteller des Beitrags
	 * 
	 * @param dersteller
	 */

	public void setErsteller(int dersteller) {

		pinnwandVerwaltung.getUserById(dersteller, new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(User result) {
				String dername = new String();
				dername = result.getNickname();
				Label l = new Label("geschrieben von:" + dername);
				l.setStyleName("geschrieben-von");
				text.add(l);
			}
		});
		this.add(text);
	}

	/**
	 * Erzeugt ein Label mit dem Erstellungszeitpunkt des Beitrags
	 * 
	 * @param hour
	 * @param minute
	 * @param day
	 * @param month
	 * @param year
	 */
	public void setErstellungszeitpunkt(int hour, int minute, int day,
			int month, int year) {
		Label i = new Label("um " + hour + ":" + minute + "Uhr, am " + day
				+ "." + month + "." + year);
		i.setStyleName("geschrieben-von");
		text.add(i);
		this.add(text);

	}
}