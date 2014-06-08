package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.socialmediaprojekt.shared.smo.User;

public class Header extends HorizontalPanel {

	Label h1 = new Label();
	Label userEingeloggtAls = new Label("User eingeloggt als ");

	public Header() {

		this.clear();

		h1.setText("Social Media Pinnwand");
		h1.getElement().setId("h1");

		this.add(h1);
		

	}

	/*public Header addUserStatus(User user) {
		this.clear();
		this.add(h1);
		userEingeloggtAls.setText("User eingeloggt als" + user.getVorname()
				+ "" + user.getNachname() + "alias" + user.getNickname());

		userEingeloggtAls.getElement().setId("userEingeloggtAls");
		this.add(userEingeloggtAls);
		return this;
	}*/

}
