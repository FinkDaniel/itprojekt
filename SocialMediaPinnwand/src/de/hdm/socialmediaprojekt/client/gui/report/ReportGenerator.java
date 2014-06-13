package de.hdm.socialmediaprojekt.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ReportGenerator extends VerticalPanel {

	ReportHeader reportHeader = new ReportHeader();
	ReportTabPanel tabPanel = new ReportTabPanel();
	

	public ReportGenerator() {
		this.setStyleName("report");
		
	}

	public void reportSeitenaufbau() {
		RootPanel.get("report").clear();
		this.add(reportHeader);
		this.add(tabPanel);
		
		RootPanel.get("start").setVisible(false);
		RootPanel.get("report").setVisible(true);
		RootPanel.get("report").add(this);

	}

	public void reportAddInhalt(String report) {
		
		HTML inhalt = new HTML(report);
		inhalt.getElement().setId("reportInhalt");
		add(inhalt);
		reportSeitenaufbau();
		
		
		
		
	}

}
