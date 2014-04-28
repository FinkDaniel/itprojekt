package de.hdm.socialmediaprojekt.shared.report;

import java.util.ArrayList;

public class CompositeParagraph extends Paragraph {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<SimpleParagraph> subParagraphs = new ArrayList<SimpleParagraph>();
	
	public void addSubParagraph(SimpleParagraph s){
		this.subParagraphs.add(s);
	}
	
	public void removeSubParagraph(SimpleParagraph s){
		this.subParagraphs.remove(s);
	}
	
	public int getSubParagraphAnzahl(){
		return this.subParagraphs.size();
	}
	
	public SimpleParagraph getSubParagraph(int i){
		return this.subParagraphs.get(i);
	}

}
