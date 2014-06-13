package de.hdm.socialmediaprojekt.client.gui.report;

import java.util.Date;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltung;






import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

public class BeitragReportPanel extends VerticalPanel {

	private Date datumVon = new Date();
	private Date datumBis = new Date();
	private DatePicker datePickerVon = new DatePicker();
	private DatePicker datePickerBis = new DatePicker();
	
	public BeitragReportPanel(){
		this.addStyleName("beitragReport");
		this.add(datePickerVon);
		this.add(datePickerBis);
	}
	
}






/*
	PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();
	
	@SuppressWarnings("deprecation")
	public BeitragReportPanel(Date datumVon, Date datumBis){
		
		DateTimeFormat simpleDateFormat = DateTimeFormat.getFormat("yyyy-MM-dd' 'HH:mm:ss");

		datumBis.setHours(0);
		datumBis.setMinutes(0);
		datumBis.setSeconds(0);
		datumBis.setDate(datumBis.getDate() +1);

		datumVon.setHours(0);
		datumVon.setMinutes(0);
		datumVon.setSeconds(0);

		String datumBisString = simpleDateFormat.format(datumBis);
		String datumVonString = simpleDateFormat.format(datumVon);
		
	
		
		
		
		pinnwandVerwaltung.createBeitragReport(datumVonString, datumBisString, new AsyncCallback<String>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler Beitrag Report "+caught.toString());
			}

			@Override
			public void onSuccess(String result) {
				Window.alert("Es hat geklappt "+result);
				//ScrollPanel sorgt dafür, dass das HTML scrollbar wird.
				ScrollPanel scrollPanel = new ScrollPanel();
				HTML html = new HTML(result);

				html.setWidth("100%");
				scrollPanel.setSize("300", "200");    
				scrollPanel.add(html);

				add(scrollPanel);	
			}	
		});
	}*/
