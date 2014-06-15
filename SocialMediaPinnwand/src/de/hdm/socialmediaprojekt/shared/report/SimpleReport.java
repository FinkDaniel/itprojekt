package de.hdm.socialmediaprojekt.shared.report;

import java.util.Vector;

/**
 * Ein einfacher Report, der neben den Informationen der Superklasse eine
 * Tabelle mit den beiden Hilfskallen Zeile und Spalte erzeugt.
 * 
 * @author Team Applikationsschicht
 */
public abstract class SimpleReport extends Report {

	private static final long serialVersionUID = 1L;

	/**
	 * Eine ArrayList, die sich aus Zeilen-Objekten zusammensetzt
	 */
	private Vector<Zeile> tabelle = new Vector<Zeile>();

	/**
	 * HinzufÃ¼gen eines Zeilen-Objektes zur ArrayList
	 */
	public void addZeile(Zeile z) {
		this.tabelle.addElement(z);
	}

	/**
	 * Entfernen eines Zeilen-Objektes aus der ArrayList
	 */
	public void removeZeile(Zeile z) {
		this.tabelle.remove(z);
	}

	/**
	 * Ausgeben der ArrayList
	 */
	public Vector<Zeile> getZeilen() {
		return this.tabelle;
	}

}