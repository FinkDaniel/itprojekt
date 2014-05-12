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
	
	void createUser(String vorname, String nachname, String nickname, String password, AsyncCallback<User> callback);
	
	void init(AsyncCallback<Void> callback);
	
	void getUserByNachname(String nachname, AsyncCallback<Vector<User>> callback);
	
	void getUserById(int id, AsyncCallback<User> callback);
	
	// getbySourceUser Methoden
	
	void getKommentarBySourceUser(int sourceId, AsyncCallback<Vector<Kommentar>> callback);
	void getPinnwandBySourceUser(int sourceId, AsyncCallback<Pinnwand> callback);
	void getBeitragBySourceUser(int sourceId, AsyncCallback<Vector<Beitrag>> callback);
	void getLikeBySourceUser(int sourceId, AsyncCallback<Vector<Like>> callback);
	void getLikeByTargetBeitrag(int beitragId, AsyncCallback<Vector<Like>> callback);
	void getAboBySourcePinnwand(int pinnwandId, AsyncCallback<Vector<Abo>> callback);
	void getAboByTargetPinnwand(int pinnwandId, AsyncCallback<Vector<Abo>> callback);
	
	// "Find" by ID -Methoden f�r Alle SMOs
	
	void getAboById(int id, AsyncCallback<Abo> callback);
	void getPinnwandById(int id, AsyncCallback<Pinnwand> callback);
	void getBeitragById(int id, AsyncCallback<Beitrag> callback);
	void getLikeById(int id, AsyncCallback<Like> callback);
	void getKommentarById(int id, AsyncCallback<Kommentar> callback);
	
	// "FIND ALL"- Methoden f�r alle SMOs
	// alle Methodennamen sind im Singular gehalten.
	
	void getAllUser(AsyncCallback<Vector<User>> callback);
	void getAllPinnwand(AsyncCallback<Vector<Pinnwand>> callback);
	void getAllLike(AsyncCallback<Vector<Like>> callback);
	void getAllKommentar(AsyncCallback<Vector<Kommentar>> callback);
	void getAllBeitrag(AsyncCallback<Vector<Beitrag>> callback);
	void getAllAbo(AsyncCallback<Vector<Abo>> callback);

	void getKommentarByTargetBeitrag(int beitragId,
			AsyncCallback<Vector<Kommentar>> callback);

	void createAbo(int sourcePinnwand, int targetPinnwand,
			AsyncCallback<Abo> callback);

	void createPinnwand(int sourceUser, AsyncCallback<Pinnwand> callback);

	void createKommentar(String text, int sourceUser, int targetBeitrag,
			AsyncCallback<Kommentar> callback);

	void createLike(int sourceUser, int targetBeitrag,
			AsyncCallback<Like> callback);

	void createBeitrag(String text, int sourceUser,
			AsyncCallback<Beitrag> callback);

	void deleteUser(User u, AsyncCallback<User> callback);

	void deleteAbo(Abo a, AsyncCallback<Void> callback);

	void deletePinnwand(Pinnwand p, AsyncCallback<Void> callback);

	void deleteKommentar(Kommentar k, AsyncCallback<Void> callback);

	void deleteLike(Like l, AsyncCallback<Void> callback);

	void deleteBeitrag(Beitrag b, AsyncCallback<Void> callback);

	void save(Beitrag b, AsyncCallback<Void> callback);

	void save(Kommentar k, AsyncCallback<Void> callback);


	}


