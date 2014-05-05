package de.hdm.socialmediaprojekt.newserver;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.socialmediaprojekt.server.db.*;
import de.hdm.socialmediaprojekt.shared.*;
import de.hdm.socialmediaprojekt.shared.smo.*;

public class SocialMediaPiwanndVerwaltungImpl extends RemoteServiceServlet implements PinnwandVerwaltung {

	
	private static final long serialVersionUID = 1L;
	
	private UserMapper uMapper = null;
	
	public User createUser(String vorname, String nachname, String nickname) throws IllegalArgumentException {
		
		User u = new User();
		u.setVorname(vorname);
		u.setNachname(nachname);
		u.setNickname(nickname);
		u.setId(1);
		
		return this.uMapper.insert(u);
		
	}
	
	
}
