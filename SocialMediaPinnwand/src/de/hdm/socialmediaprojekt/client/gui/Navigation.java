package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;




public class Navigation extends Composite {
	
	VerticalPanel navigation = new VerticalPanel();
	Buttons buttons = new Buttons();
	UserSuche userSuche = new UserSuche();
	Logout logout = new Logout();

	public Navigation() {
		initWidget(navigation);
		erstelleNavigation();
	}

	public void erstelleNavigation(){
		navigation.addStyleName("navigation");
		navigation.add(buttons);
		navigation.add(userSuche);
		navigation.add(logout);

		
	}

}
