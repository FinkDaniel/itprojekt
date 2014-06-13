package de.hdm.socialmediaprojekt.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ReportHeader extends HorizontalPanel{

	public ReportHeader(){
		this.addStyleName("reportHeader");
		this.add(new HTML("Report Header"));
	}
	
}
