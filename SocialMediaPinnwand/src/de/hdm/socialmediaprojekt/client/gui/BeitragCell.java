package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BeitragCell extends VerticalPanel {

	public BeitragCell() {
		
		this.addStyleName("BeitragCell");
		
		HorizontalPanel text = new HorizontalPanel();
		HorizontalPanel buttons = new HorizontalPanel();
		Label beispiel = new Label("Beispielbeitrag");
		Button like = new Button ("Dufte");
		Button kommentieren = new Button ("kommentieren");
		
		
		like.setStyleName("Button");
		kommentieren.setStyleName("Button");
		
		text.add(beispiel);
		buttons.add(kommentieren);
		buttons.add(like);
		
		this.add(text);
		this.add(buttons);
		
		
		
		
	}
	
}
