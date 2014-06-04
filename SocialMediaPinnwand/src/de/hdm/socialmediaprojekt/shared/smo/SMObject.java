package de.hdm.socialmediaprojekt.shared.smo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public abstract class SMObject implements Serializable {

	  private static final long serialVersionUID = 1L;

	  private int id = 0;
	  private Date erstellungsdatum;
	  
	  
	  public int getId() {
		    return this.id;
		  }

	  public void setId(int id) {
		    this.id = id;
		  }
	  
	  public Date getErstellungsdatum(){
		  	return this.erstellungsdatum;
	  }
	  public void setErstellungsdatum(Date date) {
			date = this.erstellungsdatum;
			
		}
	  public boolean equals(Object o){
		  if (o != null && o instanceof SMObject) {
		      SMObject bo = (SMObject) o;
		      try {
		        if (bo.getId() == this.id)
		          return true;
		      }
		      catch (IllegalArgumentException e) {
		
		        return false;
		      }
		  }
		  return false;
	  }	
	  
	  public String toString() {
		    return this.getClass().getName() + " #" + this.id;
	  }
	  
	  public int hashCode() {
		  return this.id;
	  }
	  
}