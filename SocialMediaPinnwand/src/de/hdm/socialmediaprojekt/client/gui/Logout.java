package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Logout extends Composite{

	VerticalPanel logout = new VerticalPanel();
	Button logoutButton = new Button("Logout");
	
	
	public Logout() {
		initWidget(logout);
		erstelleLogout();
	}
	public void erstelleLogout(){
		logout.clear();
		logoutButton.getElement().setId("logButton");
		logout.addStyleName("logout");
		logout.add(logoutButton);
		
	}

}
