package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class Footer extends Composite{
	
	public HorizontalPanel footer= new HorizontalPanel();

	public Footer() {
		initWidget(footer);
		erstelleFooter();
	}
	public void erstelleFooter(){
		footer.clear();
		footer.addStyleName("footer");
		HTML footerText = new HTML();
		footerText.setText("Hier könnte auch Ihre Werbung erscheinen");
		footerText.getElement().setId("h1");
		footer.add(footerText);

	}
}
