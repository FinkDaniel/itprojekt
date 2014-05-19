package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.Registration;

public class Buttons extends Composite{

	VerticalPanel buttons = new VerticalPanel();
	Content content = new Content();
	
	
	
	public Buttons() {
		initWidget(buttons);
		erstelleStartseite();
	}
	public void erstelleStartseite(){
		
		buttons.clear();
		buttons.addStyleName("buttons");
		
		
		Button anmelden = new Button("Anmelden");
		Button reg= new Button("Registrieren");
		
		reg.getElement().setId("logButton");
		anmelden.getElement().setId("logButton");
		
		buttons.add(reg);
		buttons.add(anmelden);
		
		anmelden.addClickHandler(new ClickHandler(){
			
			public void onClick(ClickEvent event) {
				content.addLogin();

			}
		});
		reg.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				content.addRegistration();

			}
		});
		
		buttons.add(new HTML("<h2>Buttons</h2>"));
		//content.add(new HTML("<h3>Content</h3"));
		
	}
	public void erstelleButtonsSeite1(){
		buttons.clear();
		
		
		Button meinePinnwandButton = new Button("Meine Pinnwand");
		Button meineAbos = new Button("Meine Abos");
		Button ausloggen = new Button("Ausloggen");

		meinePinnwandButton.getElement().setId("logButton");
		meineAbos.getElement().setId("logButton");
		ausloggen.getElement().setId("logButton");

		meinePinnwandButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				content.addMeinePinnwand();

			}

		});

		buttons.add(meinePinnwandButton);
		buttons.add(meineAbos);
		
	}
}
