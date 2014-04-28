package de.hdm.socialmediaprojekt.shared.smo;

import java.util.Date;
import java.io.Serializable;

public abstract class SMObject implements Serializable {

	  private static final long serialVersionUID = 1L;

	  private int uid = 0;
	  private Date erstellungsdatum = new Date();
	  
	  public int getUid() {
		    return this.uid;
		  }

	  public void setUid(int id) {
		    this.uid = id;
		  }
	  
	  public Date getErstellungsdatum(){
		  	return this.erstellungsdatum;
	  }
	  
	  public boolean equals(Object o){
		  if (o != null && o instanceof SMObject) {
		      SMObject bo = (SMObject) o;
		      try {
		        if (bo.getUid() == this.uid)
		          return true;
		      }
		      catch (IllegalArgumentException e) {
		
		        return false;
		      }
		  }
		  return false;
	  }	
	  
	  public String toString() {
		    return this.getClass().getName() + " #" + this.uid;
	  }
	  
	  public int hashCode() {
		  return this.uid;
	  }
}