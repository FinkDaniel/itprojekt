package de.hdm.socialmediapinnwand.shared.report;

public class SimpleParagraph extends Paragraph {
	
	private static final long serialVersionUID = 1L;
	
	private String text = "";
	
	public SimpleParagraph(){
		
	}
	
	public SimpleParagraph(String inhalt){
		this.text = inhalt;
	}
	
	public String getInhalt(){
		return this.text;
	}
	
	public void setInhalt(String inhalt){
		this.text = inhalt;
	}
	
	public String toString(){
		return this.text;
	}
}
