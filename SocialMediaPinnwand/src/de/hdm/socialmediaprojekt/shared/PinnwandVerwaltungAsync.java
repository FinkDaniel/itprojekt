package de.hdm.socialmediaprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.socialmediaprojekt.shared.smo.User;

public interface PinnwandVerwaltungAsync {
	
	void save(User u, AsyncCallback<Void> callback);
	
	void createUser(String vorname, String nachname, String nickname, AsyncCallback<User> callback);
	

}
