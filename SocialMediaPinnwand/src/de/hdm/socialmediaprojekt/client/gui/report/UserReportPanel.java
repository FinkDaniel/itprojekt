package de.hdm.socialmediaprojekt.client.gui.report;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Dies Klasse erstellt die Dateboxen für den zugehörigen Userreport.
 * 
 * @author Team GUI
 * @author Team Applikationsschicht
 * @extends VerticalPanel
 * 
 * 
 */
public class UserReportPanel extends VerticalPanel {

	private DateBox dateBoxVon = new DateBox();
	private DateBox dateBoxBis = new DateBox();
	private DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
	private Button erstelleReport = new Button("Report erstellen");
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();

	final MultiWordSuggestOracle suggestBox = new MultiWordSuggestOracle();
	final SuggestBox box = new SuggestBox(suggestBox);

	User u = new User();

	public UserReportPanel() {
		this.addStyleName("userReport");

	}

	public UserReportPanel addButtons() {

		dateBoxVon.setFormat(new DateBox.DefaultFormat(dateFormat));
		dateBoxBis.setFormat(new DateBox.DefaultFormat(dateFormat));

		pinnwandVerwaltung.getAllUser(new AsyncCallback<Vector<User>>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Vector<User> result) {

				for (int i = 0; i < result.size(); i++) {
					suggestBox.add(result.get(i).getNickname().toString());

				}
			}
		});

		erstelleReport.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				if (box.getText() == "") {
					Window.alert("Bitte einen Nickname eingeben");
				}

				pinnwandVerwaltung.getUserByNickname(box.getText(),
						new AsyncCallback<User>() {

							public void onFailure(Throwable caught) {
								Window.alert("Der Porsche hat kein Rad mehr");

							}

							public void onSuccess(User result) {

								pinnwandVerwaltung.createUserReport(result,
										dateBoxVon.getTextBox().getText(),
										dateBoxBis.getTextBox().getText(),
										new AsyncCallback<String>() {

											@Override
											public void onFailure(
													Throwable caught) {
												Window.alert("Der Porsche hat keinen Auspuff mehr");

											}

											@Override
											public void onSuccess(String result) {
												Window.alert("Async läuft");
												ReportContent reportContent = new ReportContent();
												reportContent.setInhalt(result);
												ReportGenerator reportGenerator = new ReportGenerator();
												reportGenerator
														.reportAddInhalt(result);

											}
										});

							}

						});

			}
		});

		this.add(box);
		this.add(dateBoxVon);
		this.add(dateBoxBis);
		this.add(erstelleReport);
		return this;
	}
}
