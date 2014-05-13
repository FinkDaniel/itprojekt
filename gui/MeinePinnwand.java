package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;

public class MeinePinnwand extends Composite{

	
	ScrollPanel pinnwand = new ScrollPanel();
	VerticalPanel pinnwand_inhalt = new VerticalPanel();
	SocialMediaProjekt smp = new SocialMediaProjekt();
	
	
	public MeinePinnwand() {
		initWidget(pinnwand);
		erstelleMeinePinnwand();
	}

	private void erstelleMeinePinnwand() {
	
		
		pinnwand.addStyleName("pinnwand");
		pinnwand_inhalt.addStyleName("pinnwand_inhalt");
		VerticalPanel beitrag = new VerticalPanel();
		beitrag.getElement().setId("beitrag");
		beitrag.add(new HTML("Test134"));
		
		
		for(int i=10; i<10; i++){
			beitrag.add(new HTML("Test"+i));
			pinnwand_inhalt.add(beitrag);
		}
		pinnwand_inhalt.add(beitrag);
		pinnwand.add(pinnwand_inhalt);
	}
	
}
