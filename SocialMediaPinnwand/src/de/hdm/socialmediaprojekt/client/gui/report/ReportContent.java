package de.hdm.socialmediaprojekt.client.gui.report;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
/**
 * Dies Klasse dient der Erstellungs und Ausgabe des Reports.
 * 
 * @author Social Media Team
 * @extends FlowPanel
 * 
 * 
 */
public class ReportContent extends FlowPanel{

	
	
	public ReportContent(){
		
		this.setStyleName("reportContent");
		
	}
	
	public void setInhalt(String html){
		HTML inhalt = new HTML(html);
		
		this.add(inhalt);
	}
}
