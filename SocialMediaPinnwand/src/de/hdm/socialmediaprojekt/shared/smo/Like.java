package de.hdm.socialmediaprojekt.shared.smo;

public class Like extends SMObject {

//  private static final long serialVersionUID = 1L;

  
//private int likeID = 0;
  
  private int sourceUserID = 0;
  
  private int targetBeitragID = 0;
  

  public int getSourceUserID() {
	    return this.sourceUserID;
	  }

		   
  public void setSourceUserID(int sourceID) {
	    this.sourceUserID = sourceID;
	  }
  
  public int getTargetBeitragID() {
	    return this.targetBeitragID;
	  }

		   
public void setTargetBeitragID(int targetID) {
	    this.targetBeitragID = targetID;
	  }
  
  
  public String toString() {
	  	return super.toString()
	  	+"Der Nutzer mit der User-ID: #" + this.sourceUserID 
	   	+ "hat den Beitrag mit der Beitrags-ID: #" + this.targetBeitragID 
	   	+ "mit der Like-ID: #" + this.getId()
	   	+ "geliked";  
  }

}
