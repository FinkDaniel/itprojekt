package de.hdm.socialmediaprojekt.client.gui;



import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

public class PinnwandView extends ScrollPanel{
	
	
	
	HorizontalPanel pinnwandView = new HorizontalPanel();
	
	public PinnwandView() {
		
		
		this.getElement().setId("pinnwand");
		this.setAlwaysShowScrollBars(true);
		
		
		
		
		createPinnwand();
		this.add(pinnwandView);
		
		
		
		
		
		
	}
	
	

	private HorizontalPanel createPinnwand(){
		
		pinnwandView.getElement().setId("pinnwandView");
		pinnwandView.add(new Label("Blubb"));
		
		
		return pinnwandView;
	}

}
