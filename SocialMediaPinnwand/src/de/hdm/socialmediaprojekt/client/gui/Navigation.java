package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;




public class Navigation extends Composite {
	
	public VerticalPanel navigation = new VerticalPanel();
	public Buttons buttons = new Buttons();
	public UserSuche userSuche = new UserSuche();
	public Logout logout = new Logout();

	public Navigation() {
		initWidget(navigation);
		//erstelleNavigation();
	}

	public void erstelleNavigation(){
		
		buttons.erstelleButtons();
		navigation.addStyleName("navigation");
		navigation.add(buttons);
		navigation.add(userSuche);
		navigation.add(logout);
	
	}
	

}
