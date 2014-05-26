package de.hdm.socialmediaprojekt.client.gui;



import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class PinnwandView extends Composite{

	HorizontalPanel pinnwandView = new HorizontalPanel();
	
	public PinnwandView() {
		
		pinnwandView.getElement().setId("pinnwandView");
		
		pinnwandView.add(new Label("Blubb"));
		initWidget(pinnwandView);
		
	}

}
