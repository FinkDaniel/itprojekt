package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Abo;

public class AboCell extends HorizontalPanel {

	public AboCell() {
		this.setStyleName("BeitragCell");
	}

	public void setText(String inhalt) {
		HorizontalPanel text = new HorizontalPanel();
		Label i = new Label(inhalt);
		text.add(i);
		this.add(text);
	}

	public AboCell addButton(final Abo abo) {

		Button loeschen = new Button("X");
		loeschen.setStyleName("Button");
		loeschen.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();
				pinnwandVerwaltung.deleteAbo(abo, new AsyncCallback<Void>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						Window.alert("Abo wurde beendet.");
						SocialMediaPinnwand smp = new SocialMediaPinnwand();
						smp.clearContent();
						smp.addAbosToContent();
						
					}});
				
				
			}});
		this.add(loeschen);
		return this;
	}
}