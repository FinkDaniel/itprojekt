package de.hdm.socialmediaprojekt.shared.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Die Basisklasse aller Reports gibt an welche elemente jeder Report
 * haben sollte.
 * 
 * @author Team Applikationsschicht
 */
public abstract class Report implements Serializable {

	  private static final long serialVersionUID = 1L;

	  /**
	   * Ein Impressum, dass die Basisdaten der Social-Media-Plattform enthÃ¤lt
	   */
	  private Paragraph impressum = null;

	  /**
	   * Kopfdaten des Reports
	   */
	  private Paragraph kopfdaten = null;

	  /**
	   * Titel des Reports
	   */
	  private String titel = "Report";

	  /**
	   * ErstellungsDatum des Reports
	   */
	  private Date erstellungsdatum = new Date();

	  /**
	   * Auslesen des Impressums
	   */
	  public Paragraph getImpressum(){
		  return this.impressum;
	  }

	  /**
	   * Setzen des Impressums
	   */
	  public void setImpressum(Paragraph i){
		  this.impressum=i;
	  }

	  /**
	   * Auslesen der Kopfdaten
	   */
	  public Paragraph getKopfdaten(){
		  return this.kopfdaten;
	  }

	  /**
	   * Setzen der Kopfdaten
	   */
	  public void setKopfdaten(Paragraph h){
		  this.kopfdaten = h;
	  }

	  /**
	   * Auslesen des Titels
	   */
	  public String getTitel(){
		  return this.titel;
	  }

	  /**
	   * Setzen des Titels
	   */
	  public void setTitel(String t){
		  this.titel = t;
	  }

	  /**
	   * Auslesen des Erstellungsdatums
	   */
	  public Date getErstellungsdatum(){
		  return this.erstellungsdatum;
	  }

	  /**
	   * Setzen des Erstellungsdatums
	   */
	  public void setDate(Date d){
		  this.erstellungsdatum = d;
	  }


}