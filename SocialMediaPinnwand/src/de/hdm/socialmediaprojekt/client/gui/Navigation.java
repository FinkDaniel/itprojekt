package de.hdm.socialmediaprojekt.client.gui;


import java.util.Vector;

import javax.swing.JOptionPane;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.LoginInfo;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.User;

import javax.swing.JOptionPane; 



public class Navigation extends VerticalPanel{


	private LoginInfo loginInfo = null;
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();
	Button meinePinnwand = new Button("Meine Pinnwand");
	Button meineAbos = new Button("Meine Abos");
	Button logout = new Button("Logout");
	Button deleteUser = new Button("Account löschen");
	
	final MultiWordSuggestOracle suggestBox = new MultiWordSuggestOracle();
	final SuggestBox box = new SuggestBox(suggestBox);
	
	static Label eingeloggtals = new Label();
	@SuppressWarnings("deprecation")
	public Navigation() {

		//SocialMediaProjekt smp = new SocialMediaProjekt();
		
		
		
		
		
		meinePinnwand.addStyleName("Button");
		meineAbos.addStyleName("Button");
		logout.addStyleName("Button");
		//delete User Button eigene Klasse (hervorheben @Prell)
		deleteUser.addStyleName("Button");

		this.add(meinePinnwand);
		this.add(meineAbos);
		this.add(logout);
		this.add(deleteUser);
		this.add(eingeloggtals);
		
		
		
		deleteUser.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				pinnwandVerwaltung.deleteUser(SocialMediaProjekt.getAktuellerNutzer(), new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {}

					@Override
					public void onSuccess(Void result) {
						Window.alert("User wurde gelöscht");
						Window.Location.assign("http://127.0.0.1:8888/_ah/login?continue=http%3A%2F%2F127.0.0.1%3A8888%2FSocialMediaProjekt.html%3Fgwt.codesvr%3D127.0.0.1%3A9997");
					}});
					

			}});
		
		logout.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				
				loadLogout();
				
				
			}

		
			});
		
		meinePinnwand.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {

				SocialMediaPinnwand smp = new SocialMediaPinnwand();
				smp.clearContent();
				smp.addPinnwandToContent();
			}
		});

		meineAbos.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				SocialMediaPinnwand smp = new SocialMediaPinnwand();
				smp.clearContent();
				smp.addAbosToContent();
			}
		});

		

		final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();

		pinnwandVerwaltung.getAllUser(new AsyncCallback <Vector<User>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Vector<User> result) {


				//System.out.print(result);
				for(int i=0;i<result.size();i++){
					suggestBox.add(result.get(i).getNickname().toString());

				}
			}
		});

		
		this.add(box);

		Button abbonieren = new Button("User abbonieren");
		abbonieren.setStyleName("Button");
		this.add(abbonieren);

		abbonieren.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {


				if(box.getText() == ""){
					Window.alert("Bitte einen Nickname eingeben");
				}
				



				pinnwandVerwaltung.getUserByNickname(box.getText(), new AsyncCallback<User>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(User result) {
						Window.alert(SocialMediaProjekt.getAktuellerNutzer().getEmail());
						
						if(SocialMediaProjekt.getAktuellerNutzer().getId() == result.getId()){
							Window.alert("Du kannst dich nicht selbst abbonieren!");
						}else if(SocialMediaProjekt.getAktuellerNutzer().getId() != result.getId()){
						pinnwandVerwaltung.createAbo(SocialMediaProjekt.getAktuellerNutzer().getId(), result.getId(), new AsyncCallback<Abo>(){

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								Window.alert("onFailure createAbo");
							}

							@Override
							public void onSuccess(Abo result) {
								Window.alert("Abo wurde angelegt");

							}

						});



					}}

				});



			} 
		}); 
	}

	
		 private void loadLogout() {	  
			 //Window.Location.assign(loginInfo.getLogoutUrl());
			 Window.Location.assign("http://127.0.0.1:8888/_ah/login?continue=http%3A%2F%2F127.0.0.1%3A8888%2FSocialMediaProjekt.html%3Fgwt.codesvr%3D127.0.0.1%3A9997");
		  }
		public static void setEingeloggtAls(User u){
			eingeloggtals.setText("Eingeloggt als: "+u.getNickname());
		}
	
}