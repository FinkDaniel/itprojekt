package de.hdm.socialmediaprojekt.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.Login;



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
	
	
	
	

	
	
	public void onModuleLoad() {
		
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
				header.add(new HTML("<h1>Test134</h1>"));
				buttons.add(new HTML("<h2>Buttons</h2>"));
				content.add(new HTML("<h3>Content</h3"));
				footer.add(new HTML("<h1>Test456</h1>"));
		
		
		
		
		
		
		
		
		
	}
	
	
	public void erstelleSeite1(){
		
		// Vorhergehende Seite löschen und Seite 1 erstellen
		RootPanel.get("socialMediaProjekt").clear();
		
		
		VerticalPanel userSuche = new VerticalPanel();
		userSuche.addStyleName("userSuche");
		
		userSuche.add(new HTML("<h4>User Suchen</h4>"));
		
		VerticalPanel logout = new VerticalPanel();
		logout.addStyleName("logout");
		
		
		
		Button meinePinnwand = new Button("Meine Pinnwand");
		Button meineAbos = new Button("Meine Abos");
		Button ausloggen = new Button("Ausloggen");
		
		meinePinnwand.getElement().setId("logButton");
		meineAbos.getElement().setId("logButton");
		ausloggen.getElement().setId("logButton");
		
		logout.add(ausloggen);
		
		buttons.add(meinePinnwand);
		buttons.add(meineAbos);
		
		navigation.add(userSuche, DockPanel.CENTER);
		navigation.add(logout, DockPanel.SOUTH);
		
		initialisieren();
		
	}
	
	public void initialisieren(){
		
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
		dockPanel.add(content_bg, DockPanel.EAST);	
		
		
		
		
		
		
		RootPanel.get("socialMediaProjekt").add(dockPanel);
		
		}
}






