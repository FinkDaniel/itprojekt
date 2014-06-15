package de.hdm.socialmediaprojekt.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Dies Klasse erstell die Navigationsleiste zur Reportauswahl. Hier kann
 * zwischen User- und Beitragreport gewählt werden.
 * 
 * @author Social Media Team
 * @extends VerticalPanel
 * 
 * 
 */
public class ReportTabPanel extends VerticalPanel {

	UserReportPanel userReport = new UserReportPanel();
	BeitragReportPanel beitragReport = new BeitragReportPanel();
	HorizontalPanel inhalt = new HorizontalPanel();
	Button beitrag = new Button("Beitrag Report");
	Button user = new Button("User Report");

	public ReportTabPanel() {

		this.setStyleName("tabPanel");
		addButtons();

	}

	public void addButtons() {
		this.add(beitrag);
		this.add(user);

		beitrag.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				inhalt.clear();
				inhalt.add(beitragReport);

			}
		});

		user.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				inhalt.clear();
				userReport.addButtons();
				inhalt.add(userReport);

			}
		});
		this.add(inhalt);
	}

}
