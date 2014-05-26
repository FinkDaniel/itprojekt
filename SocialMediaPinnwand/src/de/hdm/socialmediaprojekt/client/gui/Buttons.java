package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;






public class Buttons extends Composite{

	public VerticalPanel buttons = new VerticalPanel();
	public Content content = new Content();
	//public SocialMediaProjekt smp = new SocialMediaProjekt();
	
	
	
	
	public Buttons() {
		
		initWidget(buttons);
		buttons.addStyleName("buttons");
	}
	/*public void erstelleStartseite(){
		
		buttons.clear();
		buttons.addStyleName("buttons");
		
		
		
		
		Button anmelden = new Button("Anmelden");
		Button reg= new Button("Registrieren");
		
		reg.getElement().setId("logButton");
		anmelden.getElement().setId("logButton");
		
		buttons.add(reg);
		buttons.add(anmelden);
		
		anmelden.addClickHandler(new ClickHandler(){
			
			public void onClick(ClickEvent event) {
				
				content.getContent();
				content.addLogin();
				Content_BG cbg = new Content_BG();
				cbg.erstelleContentBG();
				
			}
		});
		
		reg.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				content.addRegistration();

			}
		});
		
		buttons.add(new HTML("<h2>Buttons</h2>"));
		//content.add(new HTML("<h3>Content</h3"));
		
	}
	*/
	public void erstelleButtons(){
		
		buttons.clear();
		
		
		Button meinePinnwandButton = new Button("Meine Pinnwand");
		Button meineAbos = new Button("Meine Abos");
		
		

		meinePinnwandButton.getElement().setId("logButton");
		meineAbos.getElement().setId("logButton");
		

		meinePinnwandButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				
				content.getContent();
				
				
				
			}

		});

		buttons.add(meinePinnwandButton);
		buttons.add(meineAbos);
		
		
	}
}
