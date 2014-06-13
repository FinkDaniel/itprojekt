package de.hdm.socialmediaprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.socialmediaprojekt.client.LoginInfo;
import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.Like;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;

public interface PinnwandVerwaltungAsync {

	void createAbo(int sourcePinnwand, int targetPinnwand,
			AsyncCallback<Abo> callback);

	void createBeitrag(String text, int sourceUser,
			AsyncCallback<Beitrag> callback);

	void createKommentar(String text, int sourceUser, int targetBeitrag,
			AsyncCallback<Kommentar> callback);

	void createLike(int sourceUser, int targetBeitrag,
			AsyncCallback<Like> callback);

	void createPinnwand(int sourceUser, AsyncCallback<Pinnwand> callback);

	void createUser(String vorname, String nachname, String nickname, String email,
			 AsyncCallback<User> callback);


	void deleteAbo(Abo a, AsyncCallback<Void> callback);

	void deleteBeitrag(Beitrag b, AsyncCallback<Void> callback);

	void deleteKommentar(Kommentar k, AsyncCallback<Void> callback);

	void deleteLike(Like l, AsyncCallback<Void> callback);

	void deletePinnwand(Pinnwand p, AsyncCallback<Void> callback);

	void deleteUser(User u, AsyncCallback<Void> callback);

	void getAboById(int id, AsyncCallback<Abo> callback);

	void getAboBySourcePinnwand(int pinnwandId,
			AsyncCallback<Vector<Abo>> callback);

	void getAboByTargetPinnwand(int pinnwandId,
			AsyncCallback<Vector<Abo>> callback);

	void getAllAbo(AsyncCallback<Vector<Abo>> callback);

	void getAllBeitrag(AsyncCallback<Vector<Beitrag>> callback);

	void getAllKommentar(AsyncCallback<Vector<Kommentar>> callback);

	void getAllLike(AsyncCallback<Vector<Like>> callback);

	void getAllPinnwand(AsyncCallback<Vector<Pinnwand>> callback);

	void getAllUser(AsyncCallback<Vector<User>> callback);

	void getBeitragById(int id, AsyncCallback<Beitrag> callback);

	void getBeitragBySourceUser(int[] abos,
			AsyncCallback<Vector<Beitrag>> callback);

	void getKommentarById(int id, AsyncCallback<Kommentar> callback);

	void getKommentarBySourceUser(int sourceId,
			AsyncCallback<Vector<Kommentar>> callback);

	void getKommentarByTargetBeitrag(int beitragId,
			AsyncCallback<Vector<Kommentar>> callback);

	void getLikeById(int id, AsyncCallback<Like> callback);

	void getLikeBySourceUser(int sourceId, AsyncCallback<Vector<Like>> callback);

	void getLikeByTargetBeitrag(int beitragId,
			AsyncCallback<Vector<Like>> callback);

	void getPinnwandById(int id, AsyncCallback<Pinnwand> callback);

	void getPinnwandBySourceUser(int sourceId, AsyncCallback<Pinnwand> callback);

	void getUserById(int id, AsyncCallback<User> callback);

	void getUserByNachname(String nachname, AsyncCallback<Vector<User>> callback);

	void init(AsyncCallback<Void> callback);

	void save(User u, AsyncCallback<Void> callback);

	void save(Beitrag b, AsyncCallback<Void> callback);

	void save(Kommentar k, AsyncCallback<Void> callback);

	void login(String requesturi, AsyncCallback<LoginInfo> callback);

	void findUserbyEmail(String email, AsyncCallback<User> callback);

	void getUserByNickname(String nickname, AsyncCallback<User> callback);

	void getAboBySourceUser(int sourceUserID, AsyncCallback<Vector<Abo>> callback);

	void editBeitrag(Beitrag b, AsyncCallback<Void> callback);

	void editKommentar(Kommentar k, AsyncCallback<Void> callback);

	void getBeitragByUniqueSourceUser(int id,
			AsyncCallback<Vector<Beitrag>> callback);

	void createUserReport(User u, String datumVon, String datumBis,
			AsyncCallback<String> callback);

	void createBeitragReport(String datumVon, String datumBis,
			AsyncCallback<String> callback);

	
	

	// void login(String hostPageBaseURL, AsyncCallback<LoginInfo> callback);



	


	}





