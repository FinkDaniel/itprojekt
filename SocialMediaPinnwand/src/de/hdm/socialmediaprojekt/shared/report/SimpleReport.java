package de.hdm.socialmediaprojekt.shared.report;

import java.util.ArrayList;

public class SimpleReport extends Report {
	
	private static final long serialVersionUID = 1L;

	private ArrayList<Zeile> tabelle = new ArrayList<Zeile>();
	
	public void addZeile(Zeile z){
		this.tabelle.add(z);
	}
	
	public void removeZeile(Zeile z){
		this.tabelle.remove(z);
	}
	
	public ArrayList<Zeile> getZeilen(){
		return this.tabelle;
	}
	
}
