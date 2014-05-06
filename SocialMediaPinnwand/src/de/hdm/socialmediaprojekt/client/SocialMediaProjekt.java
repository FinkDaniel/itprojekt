package de.hdm.socialmediaprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.socialmediaprojekt.client.Login;
import de.hdm.socialmediaprojekt.client.Registration;





public class SocialMediaProjekt implements EntryPoint {
	//* 
	
	public VerticalPanel header=new VerticalPanel(); 
	public HorizontalPanel h1=new HorizontalPanel();
	public HorizontalPanel h2=new HorizontalPanel();
	
	Button regButton = new Button("Registrieren");
	Button einlggButton = new Button("Einloggen");
	

	public void onModuleLoad() {
		
		
		RootPanel.get("header").add(header);
		header.setPixelSize(50, 80);
		
		header.add(new HTML("SocialMediaPinnwand"));
		h1.add(einlggButton);
		h1.add(regButton);
		
		RootPanel.get("h1").add(h1);
		h1.setPixelSize(150, 150);
		
		h2.setPixelSize(120,190);
		RootPanel.get("h2").add(h2);
		
		
		
		
		regButton.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				h2.clear();
				Registration registration = new Registration();
				h2.add(registration);
			}
		});
		
		einlggButton.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				h2.clear();
				Login login = new Login();
				h2.add(login);
				}
		});
		
	}
	
}