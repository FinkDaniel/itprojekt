package de.hdm.socialmediaprojekt.client;


import java.awt.Dialog;

import com.google.appengine.api.users.User;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;

import de.hdm.socialmediaprojekt.shared.smo.*;
import de.hdm.socialmediaprojekt.server.db.*;

public class Login extends Composite {
	FlowPanel panel = new FlowPanel();
	
	public Login() {
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
		Button reg = new Button("Login");
		panel.add(reg);
		
		reg.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				
//				Anzeigen der eingegebenen Werte
				
				Window.alert("Platzhalter f�r den Datenbankcheck der angegebenen Daten. Folgende Angaben wurden gemacht: \n\nVorname:" +vorname.getText()+"\nNachname:"+nachname.getText()+"\nNickname: "+nickname.getText());
				
//				Implementierung der Werte in die Datenbank
				
//				erfolgt wie im Beispiel TestUser.java
				
				User u = new User();
				
				
				
				
				
			}
			
		});
		
		
		
	}
		
}

