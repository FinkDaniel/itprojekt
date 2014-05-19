package de.hdm.socialmediaprojekt.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;





import de.hdm.socialmediaprojekt.client.gui.MeinePinnwand;
import de.hdm.socialmediaprojekt.client.gui.UserSuche;;
import de.hdm.socialmediaprojekt.shared.*;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SocialMediaProjekt implements EntryPoint {



	public DockPanel dockPanel = new DockPanel();
	public HorizontalPanel header=new HorizontalPanel();
	public DockPanel navigation=new DockPanel();
	public VerticalPanel buttons = new VerticalPanel();
	public DockPanel content_bg=new DockPanel();
	public VerticalPanel content=new VerticalPanel();
	public HorizontalPanel footer= new HorizontalPanel();
	
	//Klassenvariablen für Google Login
	private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access the SM application.");
	private Anchor signInLink = new Anchor("Sign In");
	private Nutzer aktuellerNutzer = null;
	private static PinnwandVerwaltungAsync pinnwandVerwaltung = null;







	public void onModuleLoad() {
		googlelogincheck();

		initialisieren();
		Button anmelden = new Button("Anmelden");
		Button reg= new Button("Registrieren");

		reg.getElement().setId("logButton");
		anmelden.getElement().setId("logButton");

		buttons.add(reg);
		buttons.add(anmelden);


		anmelden.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				content.clear();
				Login login = new Login();
				content.add(login);

			}
		});



		reg.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				content.clear();
				Registration registrieren = new Registration();

				content.add(registrieren); 

			}
		});




		//*navigation.add(child);

				buttons.add(new HTML("<h2>Buttons</h2>"));
				//content.add(new HTML("<h3>Content</h3"));


	}


	private void googlelogincheck() {
		//aufrufen async call von LoginInfo
		pinnwandVerwaltung.login(GWT.getHostPageBaseURL(), new AsyncCallback <LoginInfo>(){
			
			  public void onFailure(Throwable error) {}

			   public void onSuccess(LoginInfo result) {
			          loginInfo = result;
			          if(loginInfo.isLoggedIn()) {
			           nutzerInDatenbank(result);
			           
			           //für unseren Fall anpassen
			           loadSocialMediaPinnwand();
			           
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


		UserSuche userSuche = new UserSuche();
		userSuche.addStyleName("userSuche");

		VerticalPanel logout = new VerticalPanel();
		logout.addStyleName("logout");


		Button meinePinnwandButton = new Button("Meine Pinnwand");
		Button meineAbos = new Button("Meine Abos");
		Button ausloggen = new Button("Ausloggen");

		meinePinnwandButton.getElement().setId("logButton");
		meineAbos.getElement().setId("logButton");
		ausloggen.getElement().setId("logButton");

		meinePinnwandButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				content.clear();
				MeinePinnwand meinePinnwand = new MeinePinnwand();
				content.add(meinePinnwand);

			}

		});

		buttons.add(meinePinnwandButton);
		buttons.add(meineAbos);

		navigation.add(userSuche, DockPanel.CENTER);
		navigation.add(logout, DockPanel.SOUTH);

		logout.add(ausloggen);

		initialisieren();

	}

	public void initialisieren(){
		RootPanel.get("socialMediaProjekt").clear();
		dockPanel.addStyleName("dockPanel");
		header.addStyleName("header");
		content_bg.addStyleName("content_bg");
		content.addStyleName("content");
		navigation.addStyleName("navigation");
		buttons.addStyleName("buttons");
		footer.addStyleName("footer");

		navigation.add(buttons, DockPanel.NORTH);

		content_bg.add(content, DockPanel.NORTH);

		dockPanel.add(header, DockPanel.NORTH);
		dockPanel.add(footer, DockPanel.SOUTH);
		dockPanel.add(navigation, DockPanel.WEST);
		dockPanel.add(content_bg, DockPanel.CENTER);	

		HTML h1 = new HTML();
		h1.setText("Social Media Pinnwand");
		h1.getElement().setId("h1");
		header.add(h1);
		HTML footerText = new HTML();
		footerText.setText("Hier könnte auch Ihre Werbung erscheinen");
		footerText.getElement().setId("h1");
		footer.add(footerText);


		RootPanel.get("socialMediaProjekt").add(dockPanel);

		}
}



