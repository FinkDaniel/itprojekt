package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;

public class MeinePinnwand extends Composite{


	public ScrollPanel pinnwand = new ScrollPanel();
	public VerticalPanel pinnwand_inhalt = new VerticalPanel();
	


	public MeinePinnwand() {
		initWidget(pinnwand);
		erstelleMeinePinnwand();
	}

	private void erstelleMeinePinnwand() {


		pinnwand.addStyleName("pinnwand");
		pinnwand_inhalt.addStyleName("pinnwand_inhalt");
		
		


		for(int i=10; i<10; i++){
			VerticalPanel beitrag = new VerticalPanel();
			beitrag.getElement().setId("beitrag");
			beitrag.add(new HTML("Test"+i));
			pinnwand_inhalt.add(beitrag);
		}
		
		pinnwand.add(pinnwand_inhalt);
		
	}

}