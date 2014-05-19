package de.hdm.socialmediaprojekt.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.socialmediaprojekt.client.gui.Buttons;
import de.hdm.socialmediaprojekt.client.gui.Content_BG;
import de.hdm.socialmediaprojekt.client.gui.Footer;
import de.hdm.socialmediaprojekt.client.gui.Header;
import de.hdm.socialmediaprojekt.client.gui.Navigation;




/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SocialMediaProjekt implements EntryPoint {



	public DockPanel dockPanel = new DockPanel();
	public Header header= new Header();
	public Navigation navigation = new Navigation();
	
	public Content_BG content_bg = new Content_BG();
	
	public Footer footer = new Footer();
	


	public void onModuleLoad() {
		
	header.erstelleHeader();
	
	navigation.erstelleNavigation();
	content_bg.erstelleContentBG();
	footer.erstelleFooter();
	
	dockPanel.addStyleName("dockPanel");
	
	
	dockPanel.add(header, DockPanel.NORTH);
	dockPanel.add(footer, DockPanel.SOUTH);
	dockPanel.add(navigation, DockPanel.WEST);
	dockPanel.add(content_bg, DockPanel.CENTER);
	initialisieren();
	
	}


	public void erstelleSeite1(){

		// Vorhergehende Seite löschen und Seite 1 erstellen
		RootPanel.get("socialMediaProjekt").clear();

		header.addUserEingeloggt();
		navigation.erstelleNavigation();
		content_bg.erstelleContentBG();
		Buttons buttons = new Buttons();
		buttons.erstelleButtonsSeite1();
		footer.erstelleFooter();
		
		

		initialisieren();

	}

	public void initialisieren(){
		
		
		RootPanel.get("socialMediaProjekt").clear();
		dockPanel.clear();
		dockPanel.add(header, DockPanel.NORTH);
		dockPanel.add(footer, DockPanel.SOUTH);
		dockPanel.add(navigation, DockPanel.WEST);
		dockPanel.add(content_bg, DockPanel.CENTER);
		
		RootPanel.get("socialMediaProjekt").add(dockPanel);

		}
}



