package de.hdm.socialmediaprojekt.client;



import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;

import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.client.ClientSideSettings;

public class Registration extends Composite {
	
	FlowPanel panel = new FlowPanel();
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();
	
	
	public Registration() {
		panel.setPixelSize(300, 300);
		initWidget(panel);
		registrieren();
		
		
	}

	private void registrieren() {
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
		final PasswordTextBox password = new PasswordTextBox();
		password.setText("Passwort");
		panel.add(password);
		Button reg = new Button("Registrieren");
		panel.add(reg);
		reg.addClickHandler(new ClickHandler(){

			


public void onClick(ClickEvent event) {
				
//Anzeigen der eingegebenen Werte
				
Window.alert("Folgende Angaben wurden gemacht.. \n\nVorname:" +vorname.getText()+"\nNachname:"+nachname.getText()+"\nNickname: "+nickname.getText());
				
//Implementierung der Werte in die Datenbank
String vnam = vorname.getText();
String nanam = nachname.getText();
String niname = nickname.getText();
String pword = password.getText();
			
pinnwandVerwaltung.createUser(vnam, nanam, niname, pword,  new CreateUserCallback());
				
				
}		
			
		});
		
		
		
	}
		
}

