package de.hdm.socialmediaprojekt.client.gui;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;


import com.google.gwt.user.client.ui.Label;

import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;

public class Header extends Composite {

	SocialMediaProjekt smp = new SocialMediaProjekt();
	public HorizontalPanel header=new HorizontalPanel();
	
	
	public Header() {

		initWidget(header);
		erstelleHeader();	
	}

	public void erstelleHeader(){
		
		header.addStyleName("header");
		
		HTML h1 = new HTML();
		h1.setText("Social Media Pinnwand");
		h1.getElement().setId("h1");
		header.add(h1);
	}
	public void addUserEingeloggt() {
		Label userEingeloggtAls = new Label("User eingeloggt als"+"Test134");
		userEingeloggtAls.getElement().setId("userEingeloggtAls");
	}
}
