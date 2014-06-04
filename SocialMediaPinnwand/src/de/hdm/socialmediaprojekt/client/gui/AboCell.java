package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AboCell extends HorizontalPanel {

	public AboCell() {
		this.setStyleName("BeitragCell");
	}

	public void setText(String inhalt) {
		HorizontalPanel text = new HorizontalPanel();
		Label i = new Label(inhalt);
		text.add(i);
		this.add(text);
	}

	public AboCell addButton() {

		Button loeschen = new Button("X");
		loeschen.setStyleName("Button");
		this.add(loeschen);
		return this;
	}
}
