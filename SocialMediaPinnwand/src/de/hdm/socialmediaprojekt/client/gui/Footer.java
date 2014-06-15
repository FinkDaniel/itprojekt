package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

/**
 * Footer der Pinnwand
 * 
 * @author T420
 * 
 */
public class Footer extends Composite {

	public Footer() {

		HorizontalPanel footer = new HorizontalPanel();
		footer.add(new HTML("Hier könnte auch Ihre Werbung erscheinen!"));

		initWidget(footer);
	}

}