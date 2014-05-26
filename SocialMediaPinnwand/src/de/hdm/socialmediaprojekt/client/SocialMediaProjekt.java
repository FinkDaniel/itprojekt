package de.hdm.socialmediaprojekt.client;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.server.PinnwandVerwaltungImpl;
import de.hdm.socialmediaprojekt.server.db.PinnwandMapper;
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
	private User Currentuser = null;
	private Button logButton = new Button("Anmelden");
	
	
	public DockPanel dockPanel = new DockPanel();

	public Header header= new Header();
	public Navigation navigation = new Navigation();
	
	public Content_BG content_bg = new Content_BG();
	public Content content = new Content();
	public Footer footer = new Footer();



	public void onModuleLoad() {
		

	googlelogincheck();

		
	/*header.erstelleHeader();
	
	navigation.erstelleNavigation();
	content_bg.erstelleContentBG();

	dockPanel.addStyleName("dockPanel");

	
	
	dockPanel.add(header, DockPanel.NORTH);
	dockPanel.add(footer, DockPanel.SOUTH);
	dockPanel.add(navigation, DockPanel.WEST);
	dockPanel.add(content_bg, DockPanel.CENTER);

	//Google Login Funktion
		
		

	initialisieren();
	
*/
	}


private void googlelogincheck() {
		//aufrufen async call von LoginInfo
	
	
		PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();
		
		pinnwandVerwaltung.login(GWT.getHostPageBaseURL(), new AsyncCallback <LoginInfo>(){
			

			public void onFailure(Throwable error) {}

			   public void onSuccess(LoginInfo result) {
			          loginInfo = result;
			          if(loginInfo.isLoggedIn()) {
			        	  
			        	  loginInfo.getEmailAddress();
			        	  loginInfo.getNickname();
			  		    Window.alert("User-ID:"+ loginInfo.getEmailAddress() + loginInfo.getNickname()); 
			           
//			  		    nutzerInDatenbank(result);
			  		    if(nutzerInDatenbank(loginInfo.getEmailAddress()) == false){
			  		    	
			  		    	// Vorname, Nachname einlesen (Panel erzeugen
			  		    	//UPDATE in die Datenbank

			  		    }
			        	initialisieren();
			           
			           //überprüfen ob angemeldete Nutzer bereits in Datenbank ist (anhand email-adresse)
			           
			          } else {
			           loadLogin();
			          }
			   }

			  private boolean nutzerInDatenbank(String email) {
				
				
				if(pinnwandVerwaltung.findUserbyEmail(email); ==null){
					return false;
				}else{
					return true;
				};
				  
				  
				  
				  return false;
			}

			private void loadLogin() {
				      // Assemble login panel.
				      signInLink.setHref(loginInfo.getLoginUrl());
				      loginPanel.add(loginLabel);
				      loginPanel.add(signInLink);
				      logButton.addClickHandler(new ClickHandler(){

						@Override
						public void onClick(ClickEvent event) {
							Window.Location.assign(loginInfo.getLoginUrl());
						}
				    	 
				      });
				      RootPanel.get().add(logButton);
				   }
		});
		// TODO Auto-generated method stub
		
	}



		// Vorhergehende Seite löschen und Seite 1 erstellen
/*		RootPanel.get("socialMediaProjekt").clear();

		header.addUserEingeloggt();
		navigation.erstelleNavigation();
		content_bg.erstelleContentBG();
		Buttons buttons = new Buttons();
		buttons.erstelleButtonsSeite1();
		footer.erstelleFooter();


		initialisieren();

	}

*/
	public void initialisieren(){
		
		
		RootPanel.get("socialMediaProjekt").clear();
		
		header.erstelleHeader();
		navigation.erstelleNavigation();
		content_bg.erstelleContentBG();
		footer.erstelleFooter();
		
		dockPanel.clear();
		dockPanel.add(header, DockPanel.NORTH);
		dockPanel.add(footer, DockPanel.SOUTH);
		dockPanel.add(navigation, DockPanel.WEST);
		dockPanel.add(content_bg, DockPanel.CENTER);
		
		RootPanel.get("socialMediaProjekt").add(dockPanel);
		
		
		
	}
/*	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        com.google.appengine.api.users.UserService userService = UserServiceFactory.getUserService();

        String thisURL = req.getRequestURI();

        resp.setContentType("text/html");
        if (req.getUserPrincipal() != null) {
            resp.getWriter().println("<p>Hello, " +
                                     req.getUserPrincipal().getName() +
                                     "!  You can <a href=\"" +
                                     userService.createLogoutURL(thisURL) +
                                     "\">sign out</a>.</p>");
        } else {
            resp.getWriter().println("<p>Please <a href=\"" +
                                     userService.createLoginURL(thisURL) +
                                     "\">sign in</a>.</p>");
        }
    }*/

		}






