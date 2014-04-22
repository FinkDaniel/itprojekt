package de.hdm.socialmediaprojekt.shared.smo;

import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.SMObject;


public class Pinnwand extends SMObject {

  //private static final long serialVersionUID = 1L;

  private int ownerID = 0;

 
  public int getOwnerID() {
    return this.ownerID;
  }

 
  public void setOwnerID(int userID) {
    this.ownerID = userID;
  }

  public String toString() {
    return super.toString() + " inhaber, User-ID: #" + this.ownerID;
  }


  public boolean equals(Object o) {
   
    if (o != null && o instanceof Pinnwand) {
      Pinnwand c = (Pinnwand) o;
      try {
        return super.equals(c);
      }
      catch (IllegalArgumentException e) {
        return false;
      }
    }
    return false;
  }
}
