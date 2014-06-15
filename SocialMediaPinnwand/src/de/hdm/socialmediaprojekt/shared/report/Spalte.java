package de.hdm.socialmediaprojekt.shared.report;

import java.io.Serializable;

/**
 * Ein Spalten-Objekt gibt den Inhalt eines einzelnen Feldes einer Tabelle an.
 * 
 * @author Paul Titze
 * 
 */
public class Spalte implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Inhalt des Spalten-Objektes
	 */
	private String inhalt = "";

	/**
	 * Konstruktor zur erzeugung eines leeren Spalten-Objektes
	 */
	public Spalte() {

	}

	/**
	 * Konstruktor zur erzeugung eines Spalten-Objektes
	 */
	public Spalte(String i) {
		this.inhalt = i;
	}

	/**
	 * Methode zum auslesen des Inhaltes
	 */
	public String getInhalt() {
		return this.inhalt;
	}

	/**
	 * Methode zum setzen des Inhaltes
	 */
	public void setInhalt(String i) {
		this.inhalt = i;
	}

	/**
	 * Methode zur textuellen Ausgabe des Inhaltes
	 */
	public String toString() {
		return this.inhalt;
	}
}