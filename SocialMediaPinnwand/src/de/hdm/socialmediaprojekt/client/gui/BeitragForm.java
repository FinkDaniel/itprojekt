package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BeitragForm extends VerticalPanel{
	
	//PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();
	//SocialMediaProjekt smp = new SocialMediaProjekt.aktuellerUser();

	public BeitragForm() {
		
		final TextArea beitragBox = new TextArea();
		Button beitragButton = new Button("Beitrag absenden");
		
		beitragButton.setStyleName("Button");
		
		beitragBox.setVisibleLines(5);
		beitragBox.setText("Dein Beitrag");
		
		this.add(beitragBox);
		this.add(beitragButton);
		
		beitragButton.addClickHandler(new ClickHandler(){

			
			public void onClick(ClickEvent event) {
				String beitragText = beitragBox.getText();
				//pinnwandVerwaltung.createBeitrag(beitragText, smp.aktuellerUser.getId(), new AsyncCallback<Beitrag>());
			}
			
		}); 
		
		
	}
	
	
}
