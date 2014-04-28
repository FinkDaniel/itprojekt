package de.hdm.socialmediapinnwand.shared.report;

import java.io.Serializable;
import java.util.Date;

public abstract class Report implements Serializable {

	  private static final long serialVersionUID = 1L;
	  
	  private Paragraph impressum = null;
	  
	  private Paragraph kopfdaten = null;
	  
	  private String titel = "Report";
	  
	  private Date erstellungsdatum = new Date();
	  
	  public Paragraph getImpressum(){
		  return this.impressum;
	  }
	  
	  public void setImpressum(Paragraph i){
		  this.impressum=i;
	  }
	  
	  public Paragraph getKopfdaten(){
		  return this.kopfdaten;
	  }
	  
	  public void setKopfdaten(Paragraph h){
		  this.kopfdaten = h;
	  }
	  
	  public String getTitel(){
		  return this.titel;
	  }
	  
	  public void setTitel(String t){
		  this.titel = t;
	  }
	  
	  public Date getErstellungsdatum(){
		  return this.erstellungsdatum;
	  }
	  
	  public void setDate(Date d){
		  this.erstellungsdatum = d;
	  }

	
}
