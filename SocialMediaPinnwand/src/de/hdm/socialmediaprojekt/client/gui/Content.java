/**
 * 
 */
package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.Registration;

/**
 * @author Patrick
 *
 */
public class Content extends Composite{

	/**
	 * 
	 */
	public VerticalPanel content = new VerticalPanel();
	public Content() {
		// TODO Auto-generated constructor stub
	}
	public void erstelleContent(){
		content.clear();
		content.addStyleName("content");
		
		Registration registrieren = new Registration();
		content.add(registrieren); 
	}
	public void addLogin(){
		content.clear();
		Login login = new Login(); 
		content.add(login);
	}
	public void addRegistration(){
		content.clear();
		Registration registration = new Registration();
		content.add(registration);
		
	}
	public void addMeinePinnwand(){
		content.clear();
		MeinePinnwand meinePinnwand = new MeinePinnwand();
		content.add(meinePinnwand);
	}
}
