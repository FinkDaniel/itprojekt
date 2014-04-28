package de.hdm.socialmediaprojekt.shared.report;

import java.io.Serializable;
import java.util.ArrayList;

public class Zeile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Spalte> spalten = new ArrayList<Spalte>();
	
	public void addSpalte(Spalte s){
		this.spalten.add(s);
	}
	
	public void removeSpalte(Spalte s){
		this.spalten.remove(s);
	}
	
	public ArrayList<Spalte> getAlleSpalten(){
		return this.spalten;
	}
	
	public Spalte getSpalte(int i){
		return this.spalten.get(i);
	}
	
	public int getSpaltenanzahl(){
		return this.spalten.size();
	}

}
