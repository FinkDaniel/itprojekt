package de.hdm.socialmediaprojekt.client;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

public class LoginDialog extends DialogBox{
	
	DockPanel dockPanel = new DockPanel();
	Label datenEintragen = new Label("Bitte tragen Sie Ihre Daten ein");
	TextBox vorname = new TextBox();
	TextBox nachname = new TextBox();
	TextBox nickname = new TextBox();
	Button erstelleUser = new Button("Account erstellen");
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();
	boolean nicknameVorhanden = false;
	
	public LoginDialog (final LoginInfo googleNutzer){
		
		final User user = new User();
		user.setEmail(googleNutzer.getEmailAddress());
		user.setErstellungsdatum(new Date());
		vorname.setText("Vorname");
		nachname.setText("Nachname");
		nickname.setText(googleNutzer.getNickname());
		
		erstelleUser.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				pinnwandVerwaltung.getAllUser(new AsyncCallback<Vector<User>>(){

					@Override
					public void onFailure(Throwable caught) {
						
						
					}

					@Override
					public void onSuccess(Vector<User> result) {
						for (int i = 0; i < result.size(); i++){
							if(result.get(i).getNickname() == nickname.getText()){
								nicknameVorhanden = true;
							}
						}
						
					}});
				if (nicknameVorhanden== false || nickname.getText()== "" || vorname.getText()== "" 
						|| nachname.getText()== "" || nachname.getText()== "Nachname" 
						|| vorname.getText()== "Vorname"){
					
				user.setNickname(nickname.getText());
				user.setVorname(vorname.getText());
				user.setNachname(nachname.getText());
				pinnwandVerwaltung.createUser(user.getVorname(),
						user.getNachname(), user.getNickname(),
						user.getEmail(), new AsyncCallback<User>() {
							@Override
							public void onFailure(Throwable caught) {
							}

							public void onSuccess(User result) {
								SocialMediaProjekt.setAktuellerNutzer(result);
								SocialMediaProjekt.starteSocialMediaProjekt();
								hide();
							}

						});
				}
				else {
					Window.alert("Nickname schon vorhanden oder Angaben fehlen - bitte überprüfen Sie ihre Angaben");
									}
				
			}});
		
		dockPanel.add(datenEintragen, DockPanel.NORTH);
		dockPanel.add(vorname, DockPanel.NORTH);
		dockPanel.add(nachname, DockPanel.NORTH);
		dockPanel.add(nickname, DockPanel.NORTH);
		dockPanel.add(erstelleUser, DockPanel.NORTH);
		this.add(dockPanel);
		
	}

}
