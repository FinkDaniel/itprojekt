/**
 * 
 */
package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
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
		initWidget(content);
		content.addStyleName("content");
		this.getContent();
	}
	
	
	public VerticalPanel erstelleContent(){
		content.clear();
		content.addStyleName("content");
		return content;
		
	}
	public void addLogin(){
		content.clear();
		Login login = new Login(); 
		login.einloggen();
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
	public VerticalPanel getContent(){
		return content;
	}
}
