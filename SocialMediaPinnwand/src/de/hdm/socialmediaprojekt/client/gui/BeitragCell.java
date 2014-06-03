package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BeitragCell extends VerticalPanel {
	
	Label text = new Label();
	

	public BeitragCell() {

		this.addStyleName("BeitragCell");
		this.add(text);
		

	}

	public Label setText(String inhalt) {
		this.text.setText(inhalt);
		return text;
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
}