package de.hdm.socialmediaprojekt.shared.smo;

import java.sql.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import de.hdm.socialmediaprojekt.shared.smo.SMObject;




public class User extends SMObject {

	 private static final long serialVersionUID = 1L;

	 
	  private String vorname = "";
	  
	 
	  private String nachname = "";
	  
	  
	  private String nickname = "";
	  
	  private String email = "";
	  
	  
	  public String getVorname() {
	    return this.vorname;
	  }

	  
	  public void setVorname(String name) {
	    this.vorname = name;
	  }

	 
	  public String getNachname() {
	    return this.nachname;
	  }

	 
	  public void setNachname(String name) {
	    this.nachname = name;
	  }
	  
	  
	  public void setNickname(String name) {
	    this.nickname = name;
	  }

	 
	  public String getNickname() {
	    return this.nickname;
	  }

	  
	public User() {
		
	}

	  public User(String vname, String nname, String niname, String mail) {
		this.vorname = vname;
		this.nachname = nname;
		this.nickname = niname;
		this.email = mail;
		
	}


	  public String toString() {
	    return super.toString() + " " + this.vorname + " " + this.nachname + " " + this.nickname+ " " + this.email;
	  }


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


}

	