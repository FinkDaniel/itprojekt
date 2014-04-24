package de.hdm.socialmediaprojekt.shared.smo;



public class Beitrag extends SMObject {

//  private static final long serialVersionUID = 1L;

  
  private String beitrag = "";
  
  private int sourceUserID = 0;
  
  public String getBeitrag() {
    return this.beitrag;
  }

  public void setBeitrag(String beitrag) {
    this.beitrag = beitrag;
  }

  public int getSourceUserID() {
	    return this.sourceUserID;
	  }

		   
  public void setSourceUserID(int sourceID) {
	    this.sourceUserID = sourceID;
	  }
  
  
  
  public String toString() {
	  	return super.toString() + " " + this.beitrag+ " " + this.sourceUserID;
  }

}
