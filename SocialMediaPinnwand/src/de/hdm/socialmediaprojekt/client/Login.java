package de.hdm.socialmediaprojekt.client;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;

import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;


public class Login extends Composite {
	FlowPanel panel = new FlowPanel();
	SocialMediaProjekt smp = new SocialMediaProjekt();
	

	public Login() {
		
		initWidget(panel);
		einloggen();	
	}

	private  void einloggen() {
		panel.clear();
		final TextBox vorname = new TextBox();
		vorname.setText("Vorname");
		panel.add(vorname);
		final TextBox nachname = new TextBox();
		nachname.setText("Nachname");
		panel.add(nachname);
		final TextBox nickname = new TextBox();
		nickname.setText("Nickname");
		panel.add(nickname);
		Button log = new Button("Login");
		panel.add(log);
		log.getElement().setId("logButton");
		log.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
//				Anzeigen der eingegebenen Werte
				
				Window.alert("Platzhalter f�r den Datenbankcheck der angegebenen Daten. Folgende Angaben wurden gemacht: \n\nVorname:" +vorname.getText()+"\nNachname:"+nachname.getText()+"\nNickname: "+nickname.getText());
				
//				Implementierung der Werte in die Datenbank
				
//				erfolgt wie im Beispiel TestUser.java
				
			
				smp.erstelleSeite1();
			}
			
			});
		
		
		
	}
		
}

