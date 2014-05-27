package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class AboView extends HorizontalPanel{
	
	HorizontalPanel aboView = new HorizontalPanel();
	
	public AboView(){
		
		aboView.getElement().setId("aboView");
		aboView.add(new HTML("Beitrag"));
		this.add(aboView);
		
		
	}

}
