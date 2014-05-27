package de.hdm.socialmediaprojekt.shared.smo;



public class Beitrag extends SMObject {

private static final long serialVersionUID = 1L;

  
  private String text = "";
  
  private int sourceUserID = 0;
  
  public String getBeitrag() {
    return this.text;
  }

  public void setBeitrag(String beitrag) {
    this.text = beitrag;
  }

  public int getSourceUserID() {
	    return this.sourceUserID;
	  }

		   
  public void setSourceUserID(int sourceID) {
	    this.sourceUserID = sourceID;
	  }
  
  
  
  public String toString() {

	  return super.toString()+ " " + this.text+ " " + this.sourceUserID
	  	+"Der Nutzer mit der User-ID: #" + this.sourceUserID 
	   	+ "hat den Beitrag mit der Beitrags-ID: #" + this.text + "verfasst";  			

	  	


  }

}
