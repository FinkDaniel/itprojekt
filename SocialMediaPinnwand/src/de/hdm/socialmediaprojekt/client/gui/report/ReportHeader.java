package de.hdm.socialmediaprojekt.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
/**
 * Dies Klasse erstell den ReportHeader und deren vordefinierten Textinhalt.
 * 
 * @author Social Media Team
 * @extends HorizontalPanel
 * 
 * 
 */
public class ReportHeader extends HorizontalPanel{

	public ReportHeader(){
		this.addStyleName("reportHeader");
		this.add(new HTML("Report Header"));
	}
	
}
