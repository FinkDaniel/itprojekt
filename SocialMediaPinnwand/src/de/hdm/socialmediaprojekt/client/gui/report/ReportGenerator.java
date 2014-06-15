package de.hdm.socialmediaprojekt.client.gui.report;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 * Dies Klasse dient zur grafischen Bereitsstellung der benötigten Pannels
 * 
 * @author Team GUI
 * @author Team Applikationsschicht
 * @extends VerticalPanel
 * 
 * 
 */
public class ReportGenerator extends VerticalPanel {

	ReportHeader reportHeader = new ReportHeader();
	ReportTabPanel tabPanel = new ReportTabPanel();
	private ScrollPanel reportInhalt = new ScrollPanel();

	public ReportGenerator() {
		this.setStyleName("report");

	}

	public void reportSeitenaufbau() {
		RootPanel.get("report").clear();
		this.add(reportHeader);
		this.add(tabPanel);
		this.add(reportInhalt);

		RootPanel.get("start").setVisible(false);
		RootPanel.get("report").setVisible(true);
		RootPanel.get("report").add(this);

	}

	public void reportAddInhalt(String report) {

		HTML inhalt = new HTML(report);
		reportInhalt.setStyleName("reportInhalt");
		reportInhalt.add(inhalt);
		
		reportSeitenaufbau();

	}

}