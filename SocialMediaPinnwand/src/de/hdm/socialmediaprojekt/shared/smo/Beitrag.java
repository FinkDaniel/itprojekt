package de.hdm.socialmediaprojekt.shared.smo;

import java.sql.Date;



public class Beitrag extends SMObject {

private static final long serialVersionUID = 1L;

  
  private String text = "";
  
  private int sourceUserID = 0;
  private int day = 0;
  private int month = 0;
  private int year = 0;
  private int hour = 0;
  private int minute = 0;
  
  
  public int getDay() {
	return day;
}

public int getMonth() {
	return month;
}

public int getYear() {
	return year;
}

public int getHour() {
	return hour;
}

public int getMinute() {
	return minute;
}

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

public void setDay(int i) {
	this.day= i;
}

public void setMonth(int int1) {
	this.month = int1;
}

public void setYear(int int1) {
	this.year = int1;
}

public void setHours(int int1) {
	this.hour = int1;
	
}

public void setMinutes(int int1) {
	this.minute = int1;
}



}
