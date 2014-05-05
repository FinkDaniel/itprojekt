package de.hdm.socialmediaprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.socialmediaprojekt.shared.smo.User;

public interface PinnwandVerwaltung extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	public User createUser(String vorname, String nachname, String nickname) throws IllegalArgumentException;
	
	public void save(User u) throws IllegalArgumentException;
	
}
