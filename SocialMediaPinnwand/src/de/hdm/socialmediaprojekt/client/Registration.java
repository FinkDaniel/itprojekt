package de.hdm.socialmediaprojekt.client;



import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;

public class Registration extends Composite {

	VerticalPanel panel = new VerticalPanel();

	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();


	public Registration() {

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
		final TextBox password = new TextBox();
		password.setText("Passwort");
		panel.add(password);

		Button reg = new Button("Registrieren");
		reg.getElement().setId("logButton");
		panel.add(reg);

		reg.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {

//				Anzeigen der eingegebenen Werte

				Window.alert("Folgende Angaben wurden gemacht.. \n\nVorname:" +vorname.getText()+"\nNachname:"+nachname.getText()+"\nNickname: "+nickname.getText());

//				Implementierung der Werte in die Datenbank
			//					String vnam = vorname.getText();
			//					String nanam = nachname.getText();
			//				String niname = nickname.getText();
			//				String pword = password.getText();

			//					pinnwandVerwaltung.createUser(vnam, nanam, niname, pword, new CreateUserCallback());



				//* Bereinigung des Panels
				panel.clear();
				SocialMediaProjekt smp = new SocialMediaProjekt();

			

			}


		});



	}

}
