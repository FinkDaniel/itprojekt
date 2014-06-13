package de.hdm.socialmediaprojekt.client.gui;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.gui.SocialMediaPinnwand;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.Like;
import de.hdm.socialmediaprojekt.shared.smo.User;

public class BeitragCell extends VerticalPanel {

	HorizontalPanel text = new HorizontalPanel();
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	public BeitragCell() {

		this.addStyleName("BeitragCell");

	}

	public void setText(String inhalt) {

		Label i = new Label(inhalt);
		i.setStyleName("beitrag-text-style");
		text.add(i);
		this.add(text);
	}

	public BeitragCell addButtons(Beitrag beitrag) {
		HorizontalPanel buttons = new HorizontalPanel();

		final Beitrag btrag = beitrag;
		final Button like = new Button("Dufte");
		final TextBox kommenttext = new TextBox();
		Button kommentieren = new Button("kommentieren");
		final Label anzahllikes = new Label();
		Button deleteBeitrag = new Button("x");
		Button editBeitrag = new Button("E");

		editBeitrag.setStyleName("Button");
		like.setStyleName("Button");
		kommentieren.setStyleName("Button");
		deleteBeitrag.setStyleName("Button");

		buttons.add(editBeitrag);
		buttons.add(like);
		buttons.add(kommenttext);
		buttons.add(kommentieren);

		final DialogBox db = new DialogBox();
		final FlowPanel panel = new FlowPanel();
		final TextBox text = new TextBox();
		text.setText(btrag.getBeitrag());
		db.setTitle("Beitrag ändern");
		panel.add(text);
		final Button ok = new Button("speichern");
		panel.add(ok);

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
				// fertig

			}
		});

		kommentieren.addClickHandler(new ClickHandler() {

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

	public void setErstellungszeitpunkt(int hour, int minute, int day,
			int month, int year) {
		Label i = new Label("um " + hour + ":" + minute + "Uhr, am " + day
				+ "." + month + "." + year);
		i.setStyleName("geschrieben-von");
		text.add(i);
		this.add(text);

	}
}