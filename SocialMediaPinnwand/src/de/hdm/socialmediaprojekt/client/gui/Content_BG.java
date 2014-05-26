package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Content_BG extends Composite{

	public DockPanel content_bg = new DockPanel();
	Content content = new Content();
	
	
	public Content_BG() {
		
		initWidget(content_bg);
		
	}
	
	public void erstelleContentBG(){
		
		content_bg.clear();
		content.getContent();
		content_bg.addStyleName("content_bg");
		content_bg.add(content, DockPanel.NORTH);
		
	}
	public DockPanel getContent_BG() {
		return content_bg;
	}
}
