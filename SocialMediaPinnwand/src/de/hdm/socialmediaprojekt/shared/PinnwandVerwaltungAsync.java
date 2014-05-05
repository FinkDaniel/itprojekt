package de.hdm.socialmediaprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.Like;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;

public interface PinnwandVerwaltungAsync {
	
	void save(User u, AsyncCallback<Void> callback);
	
	void createUser(String vorname, String nachname, String nickname, AsyncCallback<User> callback);
	
	void init(AsyncCallback<Void> callback);
	
	void getUserByNachname(String nachname, AsyncCallback<Vector<User>> callback);
	
	void getUserById(int id, AsyncCallback<User> callback);

	// "Find" by ID -Methoden für Alle SMOs
	
	void getAboById(int id, AsyncCallback<Abo> callback);
	void getPinnwandById(int id, AsyncCallback<Pinnwand> callback);
	void getBeitragById(int id, AsyncCallback<Beitrag> callback);
	void getLikeById(int id, AsyncCallback<Like> callback);
	void getKommentarById(int id, AsyncCallback<Kommentar> callback);
	
	// "FIND ALL"- Methoden für alle SMOs
	// alle Methodennamen sind im Singular gehalten.
	
	void getAllUser(AsyncCallback<Vector<User>> callback);
	void getAllPinnwand(AsyncCallback<Vector<Pinnwand>> callback);
	void getAllLike(AsyncCallback<Vector<Like>> callback);
	void getAllKommentar(AsyncCallback<Vector<Kommentar>> callback);
	void getAllBeitrag(AsyncCallback<Vector<Beitrag>> callback);
	void getAllAbo(AsyncCallback<Vector<Abo>> callback);


	}


