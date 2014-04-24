package de.hdm.socialmediaprojekt.shared.smo;


public class Abo extends SMObject {

  //private static final long serialVersionUID = 1L;

  
  
  private int sourcePinnwandID = 0;

   private int targetPinnwandID = 0;

 

  public int getSourcePinnwandID() {
    return this.sourcePinnwandID;
  }

  
  public void setSourcePinnwandID(int sourceID) {
    this.sourcePinnwandID = sourceID;
  }

  
  public int getTargetPinnwandID() {
    return this.targetPinnwandID;
  }

  
  public void setTargetPinnwandID(int targetID) {
    this.targetPinnwandID = targetID;
  }

   public String toString() {
    return super.toString() + "von Pinnwand-ID: #" + this.sourcePinnwandID
        + "an Pinnwand-ID: #" + this.targetPinnwandID;
  }

}
