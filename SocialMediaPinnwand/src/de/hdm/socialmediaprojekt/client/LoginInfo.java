package de.hdm.socialmediaprojekt.client;

import java.io.Serializable;

/**
 * Die von Google vorgegebene Klasse zum GoogleLogin. Sie setzt alle für den
 * Login relevanten Attribute, wie die Mail-Adresse und den Nickname.
 * 
 * @author Team GUI
 * 
 */
public class LoginInfo implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String emailAddress;
	private String nickname;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}

/*
 * public LoginInfo(String niname, String mail) { this.nickname = niname;
 * this.emailAddress = mail;}
 * 
 * public String toString() { return super.toString() + " " + this.nickname +
 * " " + this.emailAddress; //+ " " + this.nickname+ " " + this.email; } }
 */