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
	
	public String getHeader() {
	    return "<html><head><title></title></head><body>";
	  }

	  public String getTrailer() {
	    return "</body></html>";
	  }
	
	public void process(AlleInfosVonEinemUserReport r){
		this.resetReportText();
		StringBuffer ergebnis = new StringBuffer();
		
		ergebnis.append("<h1>"+r.getTitel()+"</h1>");
	    ergebnis.append("<table><tr>");
	    ergebnis.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getKopfdaten())
	        + "</b></td>");
	    ergebnis.append("<td valign=\"top\">" + paragraph2HTML(r.getImpressum())
	        + "</td>");
	    ergebnis.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
	        + "</td></tr></table>");

	    ArrayList<Zeile> zeilen = r.getZeilen();
	    ergebnis.append("<table>");

	    for (int i = 0; i < zeilen.size(); i++) {
	      Zeile zeile = zeilen.get(i);
	      ergebnis.append("<tr>");
	      for (int k = 0; k < zeile.getSpaltenanzahl(); k++) {
	        if (i == 0) {
	        	ergebnis.append("<td>" + zeile.getSpalte(k)
	              + "</td>");
	        }
	        else {
	          if (i > 1) {
	        	 ergebnis.append("<td>"
	                + zeile.getSpalte(k) + "</td>");
	          }
	          else {
	        	  ergebnis.append("<td valign=\"top\">" + zeile.getSpalte(k) + "</td>");
	          }
	        }
	      }
	      ergebnis.append("</tr>");
	    }

	    ergebnis.append("</table>");

	    this.reportText = ergebnis.toString();
	}
	
	public void process(AlleInfosVonEinemBeitragReport r){
		this.resetReportText();
		StringBuffer ergebnis = new StringBuffer();
		
		ergebnis.append("<h1>"+r.getTitel()+"</h1>");
	    ergebnis.append("<table><tr>");
	    ergebnis.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getKopfdaten())
	        + "</b></td>");
	    ergebnis.append("<td valign=\"top\">" + paragraph2HTML(r.getImpressum())
	        + "</td>");
	    ergebnis.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
	        + "</td></tr></table>");

	    ArrayList<Zeile> zeilen = r.getZeilen();
	    ergebnis.append("<table>");

	    for (int i = 0; i < zeilen.size(); i++) {
	      Zeile zeile = zeilen.get(i);
	      ergebnis.append("<tr>");
	      for (int k = 0; k < zeile.getSpaltenanzahl(); k++) {
	        if (i == 0) {
	        	ergebnis.append("<td>" + zeile.getSpalte(k)
	              + "</td>");
	        }
	        else {
	          if (i > 1) {
	        	 ergebnis.append("<td>"
	                + zeile.getSpalte(k) + "</td>");
	          }
	          else {
	        	  ergebnis.append("<td valign=\"top\">" + zeile.getSpalte(k) + "</td>");
	          }
	        }
	      }
	      ergebnis.append("</tr>");
	    }

	    ergebnis.append("</table>");

	    this.reportText = ergebnis.toString();
	}
	
	public void process(AlleInfosVonAllenUsernReport r){
		this.resetReportText();
		StringBuffer ergebnis = new StringBuffer();
		
		ergebnis.append("<h1>"+r.getTitel()+"</h1>");
	    ergebnis.append("<table><tr>");
	    ergebnis.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getKopfdaten())
	        + "</b></td>");
	    ergebnis.append("<td valign=\"top\">" + paragraph2HTML(r.getImpressum())
	        + "</td>");
	    ergebnis.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
	        + "</td></tr></table>");
	    
	    for (int i = 0; i < r.getAnzahlVonReports(); i++) {
	        
	        AlleInfosVonEinemUserReport subReport = (AlleInfosVonEinemUserReport) r.getReport(i);

	        this.process(subReport);

	        ergebnis.append(this.reportText + "\n");



	        this.resetReportText();
	      }

	      this.reportText = ergebnis.toString();
	}
	
	public void process(AlleInfosVonAllenBeitraegenReport r){
		this.resetReportText();
		StringBuffer ergebnis = new StringBuffer();
		
		ergebnis.append("<h1>"+r.getTitel()+"</h1>");
	    ergebnis.append("<table><tr>");
	    ergebnis.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getKopfdaten())
	        + "</b></td>");
	    ergebnis.append("<td valign=\"top\">" + paragraph2HTML(r.getImpressum())
	        + "</td>");
	    ergebnis.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
	        + "</td></tr></table>");
	    
	    for (int i = 0; i < r.getAnzahlVonReports(); i++) {
	        
	        AlleInfosVonEinemBeitragReport subReport = (AlleInfosVonEinemBeitragReport) r.getReport(i);

	        this.process(subReport);

	        ergebnis.append(this.reportText + "\n");



	        this.resetReportText();
	      }

	      this.reportText = ergebnis.toString();
	}
	
	public String getReportText(){
		return this.getHeader() + this.getReportText() + this.getTrailer();
	}

}
