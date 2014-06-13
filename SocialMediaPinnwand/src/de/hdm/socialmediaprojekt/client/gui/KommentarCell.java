package de.hdm.socialmediaprojekt.client.gui;



	import java.util.Date;

	import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

	import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.User;

	public class KommentarCell extends VerticalPanel {
		
		HorizontalPanel text = new HorizontalPanel();
		final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();
		Button deleteKommentar = new Button("x");
		
		
		public KommentarCell() {

			this.addStyleName("KommentarCell");
		
			

		}
		
		
		public void setText(String inhalt) {
			
			Label i = new Label(inhalt);
			text.add(i);
			this.add(text);
		}

		
		
		public void setErsteller(int dersteller){
			
			pinnwandVerwaltung.getUserById(dersteller, new AsyncCallback<User>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(User result) {
					String dername = new String();
					dername = result.getNickname();
					Label l = new Label("geschrieben von:"+dername);
					l.setStyleName("geschrieben-von");
					text.add(l);
				}});
			this.add(text);
		}



		public void setErstellungszeitpunkt(int hour, int minute, int day,
				int month, int year) {
				Label i = new Label("um "+hour+":"+minute+"Uhr, am "+day+"."+month+"."+year);
				i.setStyleName("geschrieben-von");
				text.add(i);
				this.add(text);
			
		}

		public void addEditButton(Kommentar kommentar){
			final Button editKommentar = new Button("E");
			
			final Kommentar kmtr = kommentar;
			final DialogBox db = new DialogBox();
			final FlowPanel panel = new FlowPanel();
			final TextBox text1 = new TextBox();
			text1.setText(kmtr.getKommentar());
			db.setTitle("Kommentar ändern");
			panel.add(text1);
			final Button ok = new Button("speichern");
			panel.add(ok);
			
			db.add(panel);
			
			editKommentar.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if(kmtr.getSourceUserID() == SocialMediaProjekt.getAktuellerNutzer().getId()){
					db.show();
					db.center();
					
					}
					else if(kmtr.getSourceUserID() != SocialMediaProjekt.getAktuellerNutzer().getId()){
						Window.alert("Du kannst nur deine eigenen Beiträge editieren");
						
					}
					
				}});
			
			ok.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					kmtr.setKommentar(text1.getText());
					db.hide();
					pinnwandVerwaltung.editKommentar(kmtr, new AsyncCallback<Void>(){

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onSuccess(Void result) {
							Window.alert("Kommentar wurde geändert");
							SocialMediaPinnwand smp = new SocialMediaPinnwand();
							smp.clearContent();
							smp.addPinnwandToContent();
							
						}
							});
							
							
						
					
						
					
				}

					
				
			
				
			
		});
		text.add(editKommentar);	
			
		}
		
		public void addDeleteButton(Kommentar kommentar) {
			final Kommentar kmntar = kommentar;
			Button deleteButton = new Button("x");
			deleteButton.addClickHandler(new ClickHandler(){

				@Override
				public void onClick(ClickEvent event) {
					if(kmntar.getSourceUserID() == SocialMediaProjekt.getAktuellerNutzer().getId()){
					pinnwandVerwaltung.deleteKommentar(kmntar, new AsyncCallback<Void>(){

						@Override
						public void onFailure(Throwable caught) {}

						@Override
						public void onSuccess(Void result) {
							Window.alert("Kommentar wurde gelöscht");
							SocialMediaPinnwand smp = new SocialMediaPinnwand();
							smp.clearContent();
							smp.addPinnwandToContent();
							
						}});
					
				}else if(kmntar.getSourceUserID() != SocialMediaProjekt.getAktuellerNutzer().getId()){
				Window.alert("Du kannst nur deine eigenen Kommentare löschen!");
			}
			}
			
		});
			text.add(deleteButton);
	}
	}

