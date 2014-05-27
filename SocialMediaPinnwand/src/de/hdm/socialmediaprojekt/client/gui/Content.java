package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

public class Content extends Composite{


	DockPanel content = new DockPanel();

	public Content() {
		content.addStyleName("content");
		content.add(new HTML("Test2353"), DockPanel.NORTH);
		initWidget(content);
	}
	public DockPanel addPinnwand(){

		content.clear();
		Label pinnwandMenüpunkt = new Label("Meine Pinnwand");
		content.add(pinnwandMenüpunkt, DockPanel.NORTH);

		ScrollPanel pinnwand = new ScrollPanel();
		pinnwand.getElement().setId("pinnwand");
		pinnwand.setAlwaysShowScrollBars(true);


		PinnwandView pinnwandView = new PinnwandView();

		pinnwand.add(pinnwandView);


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