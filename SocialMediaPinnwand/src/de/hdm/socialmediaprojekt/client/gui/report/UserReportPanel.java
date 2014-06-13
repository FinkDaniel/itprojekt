package de.hdm.socialmediaprojekt.client.gui.report;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;

public class UserReportPanel extends VerticalPanel {

	private Date datumVon = new Date();
	private Date datumBis = new Date();
	private DatePicker datePickerVon = new DatePicker();
	private DatePicker datePickerBis = new DatePicker();
	private Button erstelleReport = new Button("Report erstellen");
	final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings
			.getPinnwandVerwaltung();
	
	ReportContent reportContent = new ReportContent();

	final MultiWordSuggestOracle suggestBox = new MultiWordSuggestOracle();
	final SuggestBox box = new SuggestBox(suggestBox);

	User u = new User();

	public UserReportPanel() {
		this.addStyleName("userReport");

	}

	
	public UserReportPanel addButtons() {

		datePickerBis.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				datumBis = event.getValue();

			}
		});
		
		@SuppressWarnings("deprecation")
		DateTimeFormat simpleDateFormat = DateTimeFormat.getFormat("yyyy-MM-dd' 'HH:mm:ss");

		datumBis.setHours(0);
		datumBis.setMinutes(0);
		datumBis.setSeconds(0);
		datumBis.setDate(datumBis.getDate() +1);

		datumVon.setHours(0);
		datumVon.setMinutes(0);
		datumVon.setSeconds(0);

		final String datumBisString = simpleDateFormat.format(datumBis);
		final String datumVonString = simpleDateFormat.format(datumVon);

		datePickerVon.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				datumVon = event.getValue();

			}
		});

		pinnwandVerwaltung.getAllUser(new AsyncCallback<Vector<User>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Vector<User> result) {

				// System.out.print(result);
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
								Window.alert(result.toString() + datumVonString + datumBisString);
								
								
								pinnwandVerwaltung.createUserReport(result,datumVonString , datumBisString, new
								  AsyncCallback<String>() {
								  
								  @Override public void onFailure( Throwable
								 caught) { // TODO Auto-generated method //
								  Window.alert("Der Porsche hat keinen Auspuff mehr");
								  
								  }
								  
								  @Override 
								  public void onSuccess(String
								  result) { 
									  Window.alert("Async läuft");
									  reportContent.setInhalt(result);
									  ReportGenerator reportGenerator = new ReportGenerator();
									  reportGenerator.reportAddInhalt(result);
								  
								  } });
								

							}

						});

			}
		});

		this.add(box);
		this.add(datePickerVon);
		this.add(datePickerBis);
		this.add(erstelleReport);
		return this;
	}
}
