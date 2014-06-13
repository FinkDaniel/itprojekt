package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.smo.User;

public class Header extends HorizontalPanel {
	
	Label userEingeloggtAls = new Label();

	public Header() {

		this.clear();
		HTML h1 = new HTML();
		h1.setText("Social Media Pinnwand");
		h1.getElement().setId("h1");
		
		this.add(h1);
		
		userEingeloggtAls.getElement().setId("userEingeloggtAls");
		this.add(userEingeloggtAls);
		this.add(h1);


	}
public void setUserEingeloggtAnzeige(User user){
			userEingeloggtAls.setText("Eingeloggt als: "+user.getNickname());
		}
	/*public void addUserStatus(User user) {

		Label userEingeloggtAls = new Label("User eingeloggt als"
				+ user.getVorname() + "" + user.getNachname() 
				+ "alias" + user.getNickname());

		userEingeloggtAls.getElement().setId("userEingeloggtAls");
		this.add(userEingeloggtAls);

	}*/
}
