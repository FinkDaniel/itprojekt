package de.hdm.socialmediaprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.Like;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;

public interface PinnwandVerwaltung extends RemoteService {
	
	public void init() throws IllegalArgumentException;
	
	public User createUser(String vorname, String nachname, String nickname) throws IllegalArgumentException;
	
	public void save(User u) throws IllegalArgumentException;
	
	public Vector<User> getUserByNachname(String nachname) throws IllegalArgumentException;
	
	
	
	// "Find" by ID -Methoden für Alle SMOs
	
	public User getUserById(int id) throws IllegalArgumentException;
	
	public Abo getAboById(int id) throws IllegalArgumentException;
	
	public Pinnwand getPinnwandById(int id) throws IllegalArgumentException;
	
	public Beitrag getBeitragById(int id) throws IllegalArgumentException;
	
	public Like getLikeById(int id) throws IllegalArgumentException;
	
	public Kommentar getKommentarById(int id) throws IllegalArgumentException;
	
	// "FIND ALL"- Methoden für alle SMOs
	// alle Methodennamen sind im Singular gehalten.
	
	public Vector<User> getAllUser() throws IllegalArgumentException;
	
	public Vector<Pinnwand> getAllPinnwand() throws IllegalArgumentException;
	
	public Vector<Like> getAllLike() throws IllegalArgumentException;
	
	public Vector<Kommentar> getAllKommentar() throws IllegalArgumentException;
	
	public Vector<Beitrag> getAllBeitrag() throws IllegalArgumentException;
	
	public Vector<Abo> getAllAbo() throws IllegalArgumentException;
}
