package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class Header extends HorizontalPanel{

	
	
	public Header() {
		
		this.clear();
		HTML h1 = new HTML();
		h1.setText("Social Media Pinnwand");
		h1.getElement().setId("h1");
		this.add(h1);
		Label userEingeloggtAls = new Label("User eingeloggt als");;
		userEingeloggtAls.getElement().setId("userEingeloggtAls");
		this.add(userEingeloggtAls);
		
		
	}


	

}
