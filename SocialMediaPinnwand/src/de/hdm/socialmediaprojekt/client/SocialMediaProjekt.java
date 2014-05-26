package de.hdm.socialmediaprojekt.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.socialmediaprojekt.client.gui.*;





/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SocialMediaProjekt implements EntryPoint {



	
	public Header header = new Header();
	public Navigation navigation = new Navigation();
	public Content content = new Content();
	//public Footer footer = new Footer();
	


	public void onModuleLoad() {
		
	//Google Login Funktion
		
		
		RootPanel.get("header").add(header);
		RootPanel.get("navigation").add(navigation);
		RootPanel.get("content").add(content);
		//RootPanel.get("footer").add(footer);
	}
	
	public void clearContent(){
		RootPanel.get("content").clear();
	}
	
	public void addPinnwandToContent(){
		content.addPinnwand();
		RootPanel.get("content").add(content);
	}
	public void addAbosToContent(){
		content.addMeineAbos();
		RootPanel.get("content").add(content);
		
	}	
}



