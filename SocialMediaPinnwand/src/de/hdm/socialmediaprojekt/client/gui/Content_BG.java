package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;

public class Content_BG extends Composite{

	DockPanel content_bg = new DockPanel();
	Content content = new Content();
	
	public Content_BG() {
		initWidget(content_bg);
		
	}
	
	public void erstelleContentBG(){
		
		content_bg.clear();
		content_bg.addStyleName("content_bg");
		content_bg.add(content, DockPanel.NORTH);
		
		
		
	}
}
