package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * Erzeugt das Grundgerüst der SocialMediaPinnwand
 * 
 * @author Paul
 * 
 */
public class SocialMediaPinnwand {

	public Header header = new Header();
	public Navigation navigation = new Navigation();
	public Content content = new Content();
	public Footer footer = new Footer();
	private boolean aboViewStatus = false;

	public SocialMediaPinnwand() {

	}

	/**
	 * Erzeugt das Seitengrundgerüst
	 */
	public void seitenaufbau() {

		RootPanel.get().clear();
		RootPanel.get("start").setVisible(false);
		RootPanel.get("header").setVisible(true);
		RootPanel.get("navigation").setVisible(true);
		RootPanel.get("content").setVisible(true);
		RootPanel.get("footer").setVisible(true);

		RootPanel.get("header").add(header);
		RootPanel.get("navigation").add(navigation);
		RootPanel.get("content").add(content);
		RootPanel.get("footer").add(footer);

	}

	/**
	 * Setzt das Content Panel zurück
	 */
	public void clearContent() {
		RootPanel.get("content").clear();
	}

	public void addPinnwandToContent() {
		this.setAboViewStatus(false);
		content.addPinnwand();
		RootPanel.get("content").add(content);
	}

	public void addAbosToContent() {
		this.setAboViewStatus(true);
		content.addMeineAbos();
		RootPanel.get("content").add(content);
	}

	public boolean getAboViewStatus() {
		return this.aboViewStatus;
	}

	public void setAboViewStatus(boolean status) {
		aboViewStatus = status;
	}
}