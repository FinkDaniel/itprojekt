package de.hdm.socialmediaprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.socialmediaprojekt.client.Registration;





public class SocialMediaProjekt implements EntryPoint {
	
	Button regButton = new Button("Registrieren");
	Button einlggButton = new Button("Einloggen");

	public void onModuleLoad() {
		RootPanel.get("content").add(regButton);
		RootPanel.get("content").add(einlggButton);
		
		regButton.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				Registration registration = new Registration();
				DialogBox dialog = new DialogBox();
				dialog.setText("Fuer die Social Media Plattform registrieren");
				dialog.setModal(false);
				dialog.center();
				dialog.show();
				dialog.add(registration);
			}
			
			
		});
		
		einlggButton.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				Login login = new Login();
				DialogBox dialog = new DialogBox();
				dialog.setText("In die Social Media Plattform einloggen");
				dialog.setModal(false);
				dialog.center();
				dialog.show();
				dialog.add(login);
			}
			
			
		});
		
	}
	
}