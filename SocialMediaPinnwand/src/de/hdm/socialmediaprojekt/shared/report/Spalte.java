package de.hdm.socialmediaprojekt.shared.report;

import java.io.Serializable;

public class Spalte implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String inhalt = "";
	
	public Spalte(){
		
	}
	public Spalte(String i){
		this.inhalt=i;
	}
	
	public String getInhalt(){
		return this.inhalt;
	}
	
	public void setInhalt(String i){
		this.inhalt = i;
	}
	
	public String toString(){
		return this.inhalt;
	}
}
