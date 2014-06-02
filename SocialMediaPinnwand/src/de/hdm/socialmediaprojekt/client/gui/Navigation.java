package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
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
		
	}

}
