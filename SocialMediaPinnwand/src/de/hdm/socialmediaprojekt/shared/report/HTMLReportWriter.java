package de.hdm.socialmediaprojekt.shared.report;

import java.util.ArrayList;

public class HTMLReportWriter extends ReportWriter {
	
	private String reportText = "";
	
	public void resetReportText(){
		this.reportText = "";
	}
	
	public String paragraph2HTML(Paragraph p){
		
		if (p instanceof CompositeParagraph){
			return this.paragraph2HTML((CompositeParagraph) p);
		}
			else{
				return this.paragraph2HTML((SimpleParagraph) p);
			}		
	}
	
	public String paragraph2HTML(CompositeParagraph p){
		StringBuffer ergebnis = new StringBuffer();
		
		for (int i = 0; i<p.getSubParagraphAnzahl();i++){
			ergebnis.append("<p>" + p.getSubParagraph(i) + "<p>");
		}
		return ergebnis.toString();
	}
	
	public String paragraph2HTML (SimpleParagraph p){
		return "<p>" + p.toString() + "<p>";
	}

	@Override
	public void process(AlleInfosVonEinemUserReport r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(AlleInfosVonEinemBeitragReport r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(AlleInfosVonAllenUsernReport r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(AlleInfosVonAllenBeitraegenReport r) {
		// TODO Auto-generated method stub
		
	}
	
	

}
