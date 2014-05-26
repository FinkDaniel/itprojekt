package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;

public class Content extends Composite{

	DockPanel content = new DockPanel();
	
	public Content() {
		//content.setStyleName("content");
		content.add(new HTML("Test2353"), DockPanel.NORTH);
		initWidget(content);
	}
	public DockPanel addPinnwand(){
		
		content.clear();
		Label pinnwandMenüpunkt = new Label("Meine Pinnwand");
		content.add(pinnwandMenüpunkt, DockPanel.NORTH);
		
		BeitragForm beitragForm = new BeitragForm();
		
		content.add(beitragForm, DockPanel.CENTER);
			
		
		
		PinnwandView pinnwand = new PinnwandView();
		content.add(pinnwand, DockPanel.SOUTH);
		
		return content;
	}
	public DockPanel getContent(){
		return content;
	}
	public DockPanel addMeineAbos() {
		
		content.clear();
		Label aboMenüpunkt = new Label("Meine Abos");
		content.add(aboMenüpunkt, DockPanel.NORTH);
		
		ScrollPanel abos = new ScrollPanel();
		abos.getElement().setId("pinnwand");
		abos.setAlwaysShowScrollBars(true);
		
		AboView aboView = new AboView();
		
		abos.add(aboView);
		
		content.add(abos, DockPanel.SOUTH);
		return content;
		
		
	}

}
