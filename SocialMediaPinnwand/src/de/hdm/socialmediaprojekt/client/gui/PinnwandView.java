package de.hdm.socialmediaprojekt.client.gui;



import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PinnwandView extends VerticalPanel{

	
	
	public PinnwandView() {
		
		this.getElement().setId("pinnwandView");
		
		this.add(new Label("Blubb"));
		BeitragCell testZelle = new BeitragCell();
		this.add(testZelle);
		
	}

}
