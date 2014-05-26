package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class Header extends Composite{

	HorizontalPanel header = new HorizontalPanel();
	
	public Header() {
		
		header.clear();
		HTML h1 = new HTML();
		h1.setText("Social Media Pinnwand");
		h1.getElement().setId("h1");
		header.add(h1);
		Label userEingeloggtAls = new Label("User eingeloggt als");;
		userEingeloggtAls.getElement().setId("userEingeloggtAls");
		header.add(userEingeloggtAls);
		initWidget(header);
	}


	

}
