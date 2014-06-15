package de.hdm.socialmediaprojekt.shared.report;

import java.io.Serializable;

/**
 * Ein SimpleParagraph stellt einen Absatz dar. 
 * Der Inhalt des Absatzes wird als String gespeichert
 * 
 * @author Team Applikationsschicht
 */

public class SimpleParagraph extends Paragraph implements Serializable{

	private static final long serialVersionUID = 1L;


	/**
	 * Inhalt des Absatzes
	 */
	private String text = "";

	/**
	 * No-Arguments Konstruktor
	 */
	public SimpleParagraph(){

	}

	/**
	 * Konstruktor, der es erlaubt bei der Erzeugung eines
	 * Absatzes gleich den Inhalt anzugeben
	 * 
	 * @param inhalt
	 */
	public SimpleParagraph(String inhalt){
		this.text = inhalt;
	}

	/**
	 * Methode zum auslesen des Inhaltes
	 */
	public String getInhalt(){
		return this.text;
	}

	/**
	 * Methode zum setzen des Inhaltes
	 */
	public void setInhalt(String inhalt){
		this.text = inhalt;
	}

	/**
	 * Methode zur textuellen Ausgabe des Inhaltes
	 */
	public String toString(){
		return this.text;
	}
}