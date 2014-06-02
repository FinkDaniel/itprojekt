package de.hdm.socialmediaprojekt.client.gui;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.FindeUserByNachname;
import de.hdm.socialmediaprojekt.client.LoginInfo;
import de.hdm.socialmediaprojekt.client.Showcase;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;
import de.hdm.thies.bankProjekt.shared.bo.Customer;


public class Navigation extends VerticalPanel{

	
	
	
	public Navigation() {
		
		Button meinePinnwand = new Button("Meine Pinnwand");
		Button meineAbos = new Button("Meine Abos");
		Button logout = new Button("Logout");
		
		meinePinnwand.addStyleName("Button");
		meineAbos.addStyleName("Button");
		logout.addStyleName("Button");
		
		this.add(meinePinnwand);
		this.add(meineAbos);
		this.add(logout);
		
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

		pinnwandVerwaltung.getUserByNachname("" , new AsyncCallback <Vector<User>>(){
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<User> result) {
				

				//System.out.print(result);
				for(int i=0;i<result.size();i++){
					suggestBox.add(result.get(i).getNachname().toString()+", "+result.get(i).getVorname().toString());
				}
			}
		});
		
		final SuggestBox box = new SuggestBox(suggestBox);
		
		Button abbonieren = new Button("User abbonieren");
		abbonieren.setStyleName("Button");
		this.add(abbonieren);
		  
		abbonieren.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
				String boxInhalt = box.getText();
				
				
			}  
		});  
	} 
}
