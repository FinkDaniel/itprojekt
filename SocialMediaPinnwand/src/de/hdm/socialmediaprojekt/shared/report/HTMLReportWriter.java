package de.hdm.socialmediaprojekt.shared.report;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Formatiert Report mittels HTML und legt den HTML-Code dann in der Variable reportText ab.
 * 
 * @author Paul
 */
public class HTMLReportWriter extends ReportWriter{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Speichert den HTML-Text, in den der Report formatiert wird
	 */
	private String reportText = "";
	
	/**
	 * Setzt die Variable reportText zurÃ¼ck
	 */
	public void resetReportText(){
		this.reportText = "";
	}
	
	/**
	 * Umwandeln eines Pragraph-Objekts in HTML.
	 */
	public String paragraph2HTML(Paragraph p){
		
		if (p instanceof CompositeParagraph){
			return this.paragraph2HTML((CompositeParagraph) p);
		}
			else{
				return this.paragraph2HTML((SimpleParagraph) p);
			}		
	}
	
	/**
	 * Umwandeln eines CompositePragraph-Objekts in HTML.
	 */
	public String paragraph2HTML(CompositeParagraph p){
		StringBuffer ergebnis = new StringBuffer();
		
		for (int i = 0; i<p.getSubParagraphAnzahl();i++){
			ergebnis.append("<p>" + p.getSubParagraph(i) + "<p>");
		}
		return ergebnis.toString();
	}
	
	/**
	 * Umwandeln eines SimpleParagraph-Objekts in HTML.
	 */
	public String paragraph2HTML (SimpleParagraph p){
		return "<p>" + p.toString() + "<p>";
	}
	
	/**
	 * Produziert einen HTML-Text Header
	 */
	public String getHeader() {
		String test = new String();
		StringBuffer ergebnis = new StringBuffer();
	    ergebnis.append("<html><head><title></title></head><body>");
	   
	    
	    test = ergebnis.toString() ;
	    return ergebnis.toString();
	  }

	/**
	 * Produziert einen HTML-Text Trailer
	 */
	public String getTrailer() {
		StringBuffer ergebnis = new StringBuffer();
		ergebnis.append("</body></html>");
	    return ergebnis.toString();
	  }
	
	/**
	 * 
	 */
	public String process(SimpleReport r){
		
		
		StringBuffer ergebnis = new StringBuffer();
		this.resetReportText();
		ergebnis.append("<h1>"+r.getTitel()+"</h1>");
	    ergebnis.append("<table><tr>");
	    ergebnis.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getKopfdaten())
	        + "</b></td>");
	    /*ergebnis.append("<td valign=\"top\">" + paragraph2HTML(r.getImpressum())
	        + "</td>");*/
	    ergebnis.append("</tr><tr><td></td><td>" + r.getErstellungsdatum().toString()
	        + "</td></tr></table>");

	    Vector<Zeile> zeilen = r.getZeilen();
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
	    return reportText;
	}

	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();
	}
	
}
