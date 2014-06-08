package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Die Klasse <code>Footer</code> stellt die Fußzeile dar und ordnet sämtliche darin enthaltene Elemente, 
 * wie das Label <code>labelFooter</code>, vertikal an. 
 * @author Team Gui (Prell, Feininger)
 *
 */
public class Footer extends VerticalPanel{
	
	Label labelFooter = new Label();
	
	public Footer() {
		
		
		labelFooter.setText("Hier könnte auch Ihre Werbung erscheinen!");
		labelFooter.getElement().setId("labelFooter");
		
	}

}
