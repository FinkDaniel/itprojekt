package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;


public class Footer extends HorizontalPanel{
	
	
	
	public Footer() {
		
		
		this.add(new HTML("Hier könnte auch Ihre Werbung erscheinen!"));
		this.getElement().setId("footer");
		
	}

}
