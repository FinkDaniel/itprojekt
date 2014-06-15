package de.hdm.socialmediaprojekt.client.gui.report;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;

/**
 * Dies Klasse erstellt die Dateboxen für den zugehörigen Beitragreport.
 * 
 * @author Team GUI
 * @author Team Applikationsschicht
 * @extends VerticalPanel
 * 
 * 
 */
public class BeitragReportPanel extends VerticalPanel {
	/**
	 * Initalisieren der DateBoxen
	 */
	private DateBox dateBoxVon = new DateBox();
	private DateBox dateBoxBis = new DateBox();
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");

	private Button erstelleReport = new Button("Report erstellen");

	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	public BeitragReportPanel() {

		dateBoxVon.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateBoxBis.setFormat(new DateBox.DefaultFormat(dateFormat));

		erstelleReport.addClickHandler(new ClickHandler() {

			/**
			 * Erstellen des CreateBeitrag AsyncCallbacks
			 */
			public void onClick(ClickEvent event) {
				pinnwandVerwaltung.createBeitragReport(dateBoxVon.getTextBox()
						.getText(), dateBoxBis.getTextBox().getText(),
						new AsyncCallback<String>() {

							@Override
							public void onFailure(Throwable caught) {

								Window.alert("Der Porsche hat keinen Auspuff mehr");

							}

							/**
							 * Ausgabe des definierten Reports
							 */
							public void onSuccess(String result) {
								ReportContent reportContent = new ReportContent();
								reportContent.setInhalt(result);
								ReportGenerator reportGenerator = new ReportGenerator();
								reportGenerator.reportAddInhalt(result);

							}
						});

			}
		});

		this.addStyleName("beitragReport");
		this.add(dateBoxVon);
		this.add(dateBoxBis);
		this.add(erstelleReport);

	}

}
