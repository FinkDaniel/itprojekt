package de.hdm.socialmediaprojekt.client;


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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.gui.Content;
import de.hdm.socialmediaprojekt.client.gui.Footer;
import de.hdm.socialmediaprojekt.client.gui.Header;
import de.hdm.socialmediaprojekt.client.gui.Navigation;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;






/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SocialMediaProjekt implements EntryPoint {



	//Klassenvariablen für Google Login
	/*private LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label("Please sign in to your Google Account to access the SM application.");
	private Anchor signInLink = new Anchor("Sign In");
	private User currentUser = null;
	private Button logButton = new Button("Anmelden");
	
	*/
	

	



	
	public Header header = new Header();
	public Navigation navigation = new Navigation();
	public Content content = new Content();

	


	//public Footer footer = new Footer();
	



	public void onModuleLoad() {
		

	//googlelogincheck();

		
	
		
		


		RootPanel.get("header").add(header);
		RootPanel.get("navigation").add(navigation);
		RootPanel.get("content").add(content);
		//RootPanel.get("footer").add(footer);

	}



/*private void googlelogincheck() {
		//aufrufen async call von LoginInfo
	
	
		final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();
		
		pinnwandVerwaltung.login(GWT.getHostPageBaseURL(), new AsyncCallback <LoginInfo>(){
			

			private boolean status;




			public void onFailure(Throwable error) {}

			   public void onSuccess(LoginInfo result) {
			          loginInfo = result;
			          if(loginInfo.isLoggedIn()) {
			        	  
			        	  loginInfo.getEmailAddress();
			        	  loginInfo.getNickname();
			  		    Window.alert("User-ID:"+ loginInfo.getEmailAddress() + loginInfo.getNickname()); 
			           
//			  		    nutzerInDatenbank(result);
			  		    if(nutzerInDatenbank(loginInfo.getEmailAddress()) == false){
			  		    	
			  		    // der eingeloggte Nutzer war zuvor noch nie auf der SM Plattform
			  		    	
			  		    VerticalPanel userInfoPanel = new VerticalPanel();
			  		    final TextBox vorname = new TextBox();
			  		    vorname.setText("Vorname");
			  		    final TextBox nachname = new TextBox();
			  		    nachname.setText("nachname");
			  		    final TextBox nickname = new TextBox();
			  		    nickname.setText(loginInfo.getNickname());
			  		    final TextBox email = new TextBox();
			  		    email.setText(loginInfo.getEmailAddress());
			  		    email.setEnabled(false);
			  		    Button abschicken = new Button();
			  		    abschicken.setText("User anlegen");
			  		    
			  		    userInfoPanel.add(vorname);
			  		    userInfoPanel.add(nachname);
			  		    userInfoPanel.add(nickname);
			  		    userInfoPanel.add(email);
			  		    userInfoPanel.add(abschicken);
			  		    RootPanel.get().add(userInfoPanel);
			  		    System.out.print("CLICK");
			  		    abschicken.addClickHandler(new ClickHandler (){	
			  		    	
							@Override
							public void onClick(ClickEvent event) {
								// TODO Auto-generated method stub
								pinnwandVerwaltung.createUser(vorname.getText(), nachname.getText(), nickname.getText(), email.getText(), new AsyncCallback<User>(){

									@Override
									public void onFailure(Throwable caught) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void onSuccess(User result) {
										// TODO Auto-generated method stub
										
									}
					  		    	
					  		    });
							}
			  		    	
			  		    });
			  		    

			  		    }
			        	//initialisieren();
			           
			           //überprüfen ob angemeldete Nutzer bereits in Datenbank ist (anhand email-adresse)
			           
			          } else {
			           loadLogin();
			          }
			   }

			  private boolean nutzerInDatenbank(String email) {
				  
				  pinnwandVerwaltung.findUserbyEmail(email, new AsyncCallback<User>(){
					  
					  public void onFailure(Throwable caught){
							System.out.println(caught.getMessage());
							caught.getCause();
							System.out.print("Fehler");
						}
	
						@Override
						public void onSuccess(User result) {
							// TODO Auto-generated method stub
							
							if(result==null){
								status = false;
							}else{
								status = true;
							}
						}  
					  
				  
				  
						  });
				return status;
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


*/
		
	public void initialisieren(){}

	
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





