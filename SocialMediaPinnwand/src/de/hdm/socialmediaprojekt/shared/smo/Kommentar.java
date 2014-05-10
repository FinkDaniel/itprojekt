package de.hdm.socialmediaprojekt.shared.smo;



public class Kommentar extends SMObject {

private static final long serialVersionUID = 1L;

  
  private String kommentar = "";
  
  private int sourceUserID = 0;
  private int targetBeitragID = 0;
  
  public String getKommentar() {
    return this.kommentar;
  }

  public void setKommentar(String kommentar) {
    this.kommentar = kommentar;
  }

  public int getSourceUserID() {
	    return this.sourceUserID;
	  }

		   
  public void setSourceUserID(int sourceID) {
	    this.sourceUserID = sourceID;
	  }
  
  public int getTargetBeitragID() {
	    return this.targetBeitragID;
  } 

		   
public void setTargetBeitragID(int targetID){
	    this.targetBeitragID = targetID;
	  }
  
  public String toString() {
	  	return super.toString() 
	  	+"Der Nutzer mit der User-ID: #" + this.sourceUserID 
	   	+ "hat den Beitrag mit der Beitrags-ID: #" + this.targetBeitragID 
	   	+ "mit dem Kommentar:" + this.kommentar
	   	+"kommentiert";  		
  }

}
