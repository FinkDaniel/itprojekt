package de.hdm.socialmediaprojekt.shared.report;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Ein Zeilen-Objekt setzt einen Array aus mehreren 
 * Spalten-Objekten zusammen.
 * 
 * @author Team Applikationsschicht
 */
public class Zeile implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Eine ArrayList aus Spalten-Objekten
	 */
	private ArrayList<Spalte> spalten = new ArrayList<Spalte>();

	/**
	 * Methode um ein Spalten-Objekt zur Array-List hinzuzufÃ¼gen
	 */
	public void addSpalte(Spalte s){
		this.spalten.add(s);
	}

	/**
	 * Methode um ein Spalten-Objekt zu entfernen
	 */
	public void removeSpalte(Spalte s){
		this.spalten.remove(s);
	}

	/**
	 * Methode um die Array-List auszulesen
	 */
	public ArrayList<Spalte> getAlleSpalten(){
		return this.spalten;
	}

	/**
	 * Methode um ein bestimmtes Spalten-Objekt auszulesen
	 */
	public Spalte getSpalte(int i){
		return this.spalten.get(i);
	}

	/**
	 * Methode um die Anzahl der Spalten auzulesen
	 */
	public int getSpaltenanzahl(){
		return this.spalten.size();
	}

}