package de.hdm.socialmediaprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
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



public class Navigation extends VerticalPanel{


	private LoginInfo loginInfo = null;

	@SuppressWarnings("deprecation")
	public Navigation() {

		//SocialMediaProjekt smp = new SocialMediaProjekt();
		
		Button meinePinnwand = new Button("Meine Pinnwand");
		Button meineAbos = new Button("Meine Abos");
		Button logout = new Button("Logout");

		meinePinnwand.addStyleName("Button");
		meineAbos.addStyleName("Button");
		logout.addStyleName("Button");

		this.add(meinePinnwand);
		this.add(meineAbos);
		this.add(logout);
		
		logout.addClickListener(new ClickListener(){

			@Override
			public void onClick(Widget sender) {
				
				loadLogout();
				
				
			}});
		
		meinePinnwand.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {

				SocialMediaProjekt smp = new SocialMediaProjekt();
				smp.clearContent();
				smp.addPinnwandToContent();
			}
		});

		meineAbos.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				SocialMediaProjekt smp = new SocialMediaProjekt();
				smp.clearContent();
				smp.addAbosToContent();
			}
		});

		final MultiWordSuggestOracle suggestBox = new MultiWordSuggestOracle();

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

		final SuggestBox box = new SuggestBox(suggestBox);
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
						Window.alert("onSuccess");
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


						//Klammern passen
					}}

				});



			} 
		}); 
	}

	
		 private void loadLogout() {	  
			 //Window.Location.assign(loginInfo.getLogoutUrl());
			 Window.Location.assign("https://accounts.google.com/logout");
		  }
		
	
}