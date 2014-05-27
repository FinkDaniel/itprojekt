package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;

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
		
		MultiWordSuggestOracle suggestBox = new MultiWordSuggestOracle();
		
			suggestBox.add("test");
			suggestBox.add("Daniel Fink");
			suggestBox.add("Oberlotz");
			suggestBox.add("Peter Pan");
		

		   final SuggestBox box = new SuggestBox(suggestBox);
		  this.add(box);
		 
		  Button abonieren = new Button("User abonieren");
		  abonieren.setStyleName("Button");
		  this.add(abonieren);
		  
		  abonieren.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
			String boxInhalt = box.getText();
			Window.alert(boxInhalt);
			}
			  
		  });
		   
		   
	}

}
