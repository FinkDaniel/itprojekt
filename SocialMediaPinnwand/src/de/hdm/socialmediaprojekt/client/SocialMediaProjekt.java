package de.hdm.socialmediaprojekt.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;





import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.shared.*;
import de.hdm.socialmediaprojekt.shared.smo.User;
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


	//Klassenvariablen für Google Login
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access the SM application.");
	private Anchor signInLink = new Anchor("Sign In");
	private User aktuellerNutzer = null;
	
	
	public DockPanel dockPanel = new DockPanel();

	Header header= new Header();
	Navigation navigation = new Navigation();
	Buttons buttons = new Buttons();
	Content_BG content_bg = new Content_BG();
	Content content = new Content();
	Footer footer = new Footer();








	public void onModuleLoad() {
		
	googlelogincheck();

		
	header.erstelleHeader();
	buttons.erstelleStartseite();
	
	navigation.erstelleNavigation();
	content_bg.erstelleContentBG();
	dockPanel.addStyleName("dockPanel");
	}


private void googlelogincheck() {
		//aufrufen async call von LoginInfo
		PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();
		
		pinnwandVerwaltung.login(GWT.getHostPageBaseURL(), new AsyncCallback <LoginInfo>(){
			
			  public void onFailure(Throwable error) {}

			   public void onSuccess(LoginInfo result) {
			          loginInfo = result;
			          if(loginInfo.isLoggedIn()) {
			        	  
			        	  
			           // nutzerInDatenbank(result);
			           
			           //für unseren Fall anpassen
			           
			        	initialisieren();
			           
			           //überprüfen ob angemeldete Nutzer bereits in Datenbank ist (anhand email-adresse)
			           
			          } else {
			           loadLogin();
			          }
			   }

			   private void loadLogin() {
				      // Assemble login panel.
				      signInLink.setHref(loginInfo.getLoginUrl());
				      loginPanel.add(loginLabel);
				      loginPanel.add(signInLink);
				      RootPanel.get().add(loginPanel);
				   }
		});
		// TODO Auto-generated method stub
		
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



