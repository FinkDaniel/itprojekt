package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;

/**
 * Conent-Panel in welchem entweder die Pinnwand oder die Abo-Liste angezeigt
 * wird.
 * 
 * @author Team GUI
 * 
 */

public class Content extends DockPanel {

	public Content() {

	}

	/**
	 * Läd die PinnwandView in das Content Panel
	 * 
	 * @return
	 */
	public DockPanel addPinnwand() {

		this.clear();

		Label pinnwandMenüpunkt = new Label("Die Pinnwand von: "
				+ SocialMediaProjekt.getAktuellerNutzer().getVorname() + " "
				+ SocialMediaProjekt.getAktuellerNutzer().getNachname() + " ("
				+ SocialMediaProjekt.getAktuellerNutzer().getNickname() + ")");
		this.add(pinnwandMenüpunkt, DockPanel.NORTH);

		BeitragForm beitrag = new BeitragForm();
		this.add(beitrag, DockPanel.NORTH);

		PinnwandView pinnwandView = new PinnwandView();
		this.add(pinnwandView, DockPanel.SOUTH);

		return this;
	}

	/**
	 * Läd die AboView in das Content Panel
	 * 
	 * @return
	 */
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