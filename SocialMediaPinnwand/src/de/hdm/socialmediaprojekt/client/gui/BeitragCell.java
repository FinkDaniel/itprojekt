package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BeitragCell extends VerticalPanel {

	public BeitragCell() {

		this.addStyleName("BeitragCell");

	}

	public void setText(String inhalt) {
		HorizontalPanel text = new HorizontalPanel();
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
}