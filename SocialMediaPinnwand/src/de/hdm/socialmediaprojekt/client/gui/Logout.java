package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Logout extends Composite{

	VerticalPanel logout = new VerticalPanel();
	
	
	public Logout() {
		initWidget(logout);
		erstelleLogout();
	}
	public void erstelleLogout(){
		logout.clear();
		
		logout.addStyleName("logout");
	}

}
