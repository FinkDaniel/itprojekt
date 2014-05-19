package de.hdm.socialmediaprojekt.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.socialmediaprojekt.client.gui.Buttons;
import de.hdm.socialmediaprojekt.client.gui.Content;
import de.hdm.socialmediaprojekt.client.gui.Content_BG;
import de.hdm.socialmediaprojekt.client.gui.Footer;
import de.hdm.socialmediaprojekt.client.gui.Header;
import de.hdm.socialmediaprojekt.client.gui.Navigation;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SocialMediaProjekt implements EntryPoint {



	public DockPanel dockPanel = new DockPanel();
	Header header= new Header();
	Navigation navigation = new Navigation();
	Buttons buttons = new Buttons();
	Content_BG content_bg = new Content_BG();
	Content content = new Content();
	Footer footer = new Footer();
	







	public void onModuleLoad() {
		
	header.erstelleHeader();
	buttons.erstelleStartseite();
	
	navigation.erstelleNavigation();
		
	dockPanel.addStyleName("dockPanel");
	


	initialisieren();
	buttons.erstelleStartseite();

	
	}


	public void erstelleSeite1(){

		// Vorhergehende Seite löschen und Seite 1 erstellen
		RootPanel.get("socialMediaProjekt").clear();

		header.addUserEingeloggt();
		navigation.erstelleNavigation();
		content_bg.erstelleContentBG();
		buttons.erstelleButtonsSeite1();
		footer.erstelleFooter();
		
		
		
		
		
		

		initialisieren();

	}

	public void initialisieren(){
		
		
		RootPanel.get("socialMediaProjekt").clear();
		
		dockPanel.add(header, DockPanel.NORTH);
		dockPanel.add(footer, DockPanel.SOUTH);
		dockPanel.add(navigation, DockPanel.WEST);
		dockPanel.add(content_bg, DockPanel.CENTER);
		
		RootPanel.get("socialMediaProjekt").add(dockPanel);

		}
}



