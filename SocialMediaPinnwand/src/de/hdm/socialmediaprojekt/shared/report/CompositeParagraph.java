package de.hdm.socialmediaprojekt.shared.report;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Zusammengsetzter Pragraph, der sich aus mehreren SimpleParagraph-Objekten zusammensetzt
 * 
 * @author Paul
 */
public class CompositeParagraph extends Paragraph implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Eine ArrayList, die sich aus SimpleParagraph-Objekten zusammensetzt
	 */
	private ArrayList<SimpleParagraph> subParagraphs = new ArrayList<SimpleParagraph>();

	/**
	 * HinzufÃ¼gen eines SimpleParagraph-Objektes
	 */
	public void addSubParagraph(SimpleParagraph s){
		this.subParagraphs.add(s);
	}

	/**
	 * Entfernen eines SimpleParagraph-Objektes
	 */
	public void removeSubParagraph(SimpleParagraph s){
		this.subParagraphs.remove(s);
	}

	/**
	 * Auslesen aller SimpleParagraph-Objekte
	 */
	public int getSubParagraphAnzahl(){
		return this.subParagraphs.size();
	}

	/**
	 * Auslesen eines bestimmten SimpleParagraph-Objekte
	 */
	public SimpleParagraph getSubParagraph(int i){
		return this.subParagraphs.get(i);
	}

}