package de.hdm.socialmediaprojekt.shared.smo;

import de.hdm.socialmediaprojekt.shared.smo.SMObject;




public class User extends SMObject {

	 private static final long serialVersionUID = 1L;

	 
	  private String vorname = "";
	  
	 
	  private String nachname = "";
	  
	  
	  private String nickname = "";
	  
	  private String password = "";
	  
	  
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

	  public String getPassword(){
		  return this.password;
	  }
	  
	  public void setPassword(String password){
		  this.password = password;
	  }
	public User() {
		
	}

	  public User(String vname, String nname, String niname, String pword) {
		this.vorname = vname;
		this.nachname = nname;
		this.nickname = niname;
		this.password = pword;
		
	}


	  public String toString() {
	    return super.toString() + " " + this.vorname + " " + this.nachname + " " + this.nickname+ " " + this.password;
	  }


	

	}