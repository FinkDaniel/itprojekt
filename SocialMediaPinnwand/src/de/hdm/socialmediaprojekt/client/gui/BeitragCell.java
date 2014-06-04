package de.hdm.socialmediaprojekt.client.gui;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

public class BeitragCell extends VerticalPanel {
	
	HorizontalPanel text = new HorizontalPanel();
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();

	public BeitragCell() {

		this.addStyleName("BeitragCell");
	
		

	}

	public void setText(String inhalt) {
		
		Label i = new Label(inhalt);
		text.add(i);
		this.add(text);
	}

	public BeitragCell addButtons() {
		HorizontalPanel buttons = new HorizontalPanel();

		Button like = new Button("Dufte");
		Button kommentieren = new Button("kommentieren");

		like.setStyleName("Button");
		kommentieren.setStyleName("Button");

		buttons.add(like);
		buttons.add(kommentieren);

		this.add(buttons);

		return this;
	}
	public void setErsteller(int dersteller){
		
		pinnwandVerwaltung.getUserById(dersteller, new AsyncCallback<User>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(User result) {
				String dername = new String();
				dername = result.getNickname();
				Label l = new Label("geschrieben von:"+dername);
				l.setStyleName("geschrieben-von");
				text.add(l);
			}});
		this.add(text);
	}



	public void setErstellungszeitpunkt(int hour, int minute, int day,
			int month, int year) {
			Label i = new Label("um "+hour+":"+minute+"Uhr, am "+day+"."+month+"."+year);
			i.setStyleName("geschrieben-von");
			text.add(i);
			this.add(text);
		
	}
}