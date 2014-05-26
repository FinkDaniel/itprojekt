package de.hdm.socialmediaprojekt.client.gui;



import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;

public class PinnwandView extends Composite{
	
	
	ScrollPanel pinnwand = new ScrollPanel();
	HorizontalPanel pinnwandView = new HorizontalPanel();
	
	public PinnwandView() {
		
		ScrollPanel pinnwand = new ScrollPanel();
		pinnwand.getElement().setId("pinnwand");
		pinnwand.setAlwaysShowScrollBars(true);
		
		
		
		
		createPinnwand();
		pinnwand.add(pinnwandView);
		
		
		initWidget(pinnwand);
		
		
		
	}
	
	

	private HorizontalPanel createPinnwand(){
		
		pinnwandView.getElement().setId("pinnwandView");
		pinnwandView.add(new Label("Blubb"));
		
		
		return pinnwandView;
	}

}
