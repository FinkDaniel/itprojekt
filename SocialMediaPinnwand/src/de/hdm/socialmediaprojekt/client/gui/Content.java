package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

public class Content extends DockPanel{

	
	
	public Content() {
		
		this.add(new HTML("Test2353"), DockPanel.NORTH);
		
	}
	public DockPanel addPinnwand(){
		
		this.clear();
		Label pinnwandMenüpunkt = new Label("Meine Pinnwand");
		this.add(pinnwandMenüpunkt, DockPanel.NORTH);
		
		BeitragForm beitrag = new BeitragForm();
		this.add(beitrag, DockPanel.NORTH);
		
		ScrollPanel pinnwand = new ScrollPanel();
		pinnwand.getElement().setId("pinnwand");
		pinnwand.setAlwaysShowScrollBars(true);
		
		
		PinnwandView pinnwandView = new PinnwandView();
		pinnwand.add(pinnwandView);
		
		
		this.add(pinnwand, DockPanel.SOUTH);
		return this;
	}
	public DockPanel getContent(){
		return this;
	}
	public DockPanel addMeineAbos() {
		
		this.clear();
		Label aboMenüpunkt = new Label("Meine Abos");
		this.add(aboMenüpunkt, DockPanel.NORTH);
		
		ScrollPanel abos = new ScrollPanel();
		abos.getElement().setId("pinnwand");
		abos.setAlwaysShowScrollBars(true);
		
		AboView aboView = new AboView();
		
		abos.add(aboView);
		
		this.add(abos, DockPanel.SOUTH);
		return this;
		
		
	}

}
