package de.hdm.socialmediaprojekt.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.socialmediaprojekt.client.gui.Content;
import de.hdm.socialmediaprojekt.client.gui.Header;
import de.hdm.socialmediaprojekt.client.gui.Navigation;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class SocialMediaProjekt implements EntryPoint {

	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();

	//Klassenvariablen für Google Login
	private LoginInfo loginInfo = null;
	private Label loginLabel = new Label("Please sign in to your Google Account to access the SM application.");
	private Anchor signInLink = new Anchor("Sign In");
	private static User aktuellerNutzer = null;

	public Header header= new Header();
	public Navigation navigation = new Navigation();

	//public Content_BG content_bg = new Content_BG();
	public Content content = new Content();
	//public Footer footer = new Footer();


	public void onModuleLoad() {

		pinnwandVerwaltung.login("http://127.0.0.1:8888/SocialMediaProjekt.html?gwt.codesvr=127.0.0.1:9997", new AsyncCallback <LoginInfo>(){


			public void onFailure(Throwable error) {}

			public void onSuccess(LoginInfo result) {

				loginInfo = result;
				if(loginInfo.isLoggedIn()) {
					nutzerInDatenbank(result);
			        	seitenaufbau();
			    }
				else {
			        	loadLogin();
			    }

			}
		});  
	}


private void seitenaufbau() {

	  RootPanel.get().clear();
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


public void nutzerInDatenbank(final LoginInfo googleNutzer){
			pinnwandVerwaltung.getAllUser(new AsyncCallback<Vector<User>>() {

				@Override
				public void onFailure(Throwable caught) {

				}


				public void onSuccess(Vector<User> result) {
							for (User u : result){

								if (u.getEmail() == googleNutzer.getEmailAddress()){
									System.out.print("test 2");	
									setAktuellerNutzer(u);

								}
							}		
							if (getAktuellerNutzer() == null){
			    			createUser(googleNutzer);
					}
				}

			}
			);}




public void createUser(final LoginInfo googleNutzer){
	final User user = new User();
	user.setEmail(googleNutzer.getEmailAddress());
	//user.setErstellungsZeitpunkt(new Date());


					/**
					 * Fordert den Nutzer auf Vor-, Nachname und Nickname einzugeben,
					 * da diese Information nicht über die Google API bezogen werden kann
					 * bzw. geändert werden soll
					 * 
					 * @author Daniel Fink
					 */
	final LoginCustomDialog dialog = new LoginCustomDialog(googleNutzer.getNickname());
	DialogBox dlb = dialog;
	dlb.center();



	dlb.addCloseHandler(new CloseHandler<PopupPanel>(){

		public void onClose(CloseEvent<PopupPanel> event) {
				user.setVorname(dialog.getVorname());
				user.setNachname(dialog.getNachname());
				user.setNickname(dialog.getNickname());
				pinnwandVerwaltung.createUser(user.getVorname(),user.getNachname(), user.getNickname(), user.getEmail(), new AsyncCallback<User>(){
								
				public void onFailure(Throwable caught) {}

								
				public void onSuccess(User result) {
						setAktuellerNutzer(result);	
						Window.alert("Fotzkopf");
						seitenaufbau();

									 /* Update die SuggestBox mit neuen Nutzer
									 */
									//fillSuggestenBox();
				}

				});
		}
	});

}
private void loadLogin() {	  
		  Window.Location.assign(loginInfo.getLoginUrl());
	  }
private void loadLogout() {	  
	  Window.Location.assign(loginInfo.getLogoutUrl());
}





public static User getAktuellerNutzer() {
	return aktuellerNutzer;
}


public static void setAktuellerNutzer(User aktuellerNutzer) {
	SocialMediaProjekt.aktuellerNutzer = aktuellerNutzer;
}

}
		




