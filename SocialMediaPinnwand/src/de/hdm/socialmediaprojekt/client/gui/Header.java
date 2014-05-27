package de.hdm.socialmediaprojekt.client.gui;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;


import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;


import com.google.gwt.user.client.ui.Label;

import de.hdm.socialmediaprojekt.client.*;
import de.hdm.socialmediaprojekt.server.PinnwandVerwaltungImpl;




public class Header extends Composite {

	
	public HorizontalPanel header=new HorizontalPanel();
	
	
	public Header() {

		initWidget(header);
			
	}

	public void erstelleHeader(){
		
		header.clear();
		
		header.addStyleName("header");
		HTML h1 = new HTML();
		h1.setText("Social Media Pinnwand");
		h1.getElement().setId("h1");
		header.add(h1);
		Label userEingeloggtAls = new Label("User eingeloggt als");
		userEingeloggtAls.getElement().setId("userEingeloggtAls");
		header.add(userEingeloggtAls);
	}
	
	   
}
