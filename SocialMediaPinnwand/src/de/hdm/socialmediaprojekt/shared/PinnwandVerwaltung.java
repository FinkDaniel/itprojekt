package de.hdm.socialmediaprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.socialmediaprojekt.client.LoginInfo;
import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.Like;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;

@RemoteServiceRelativePath("pinnwandVerwaltung")
public interface PinnwandVerwaltung extends RemoteService {

	public void init() throws IllegalArgumentException;

	public User createUser(String vorname, String nachname, String nickname,
			String email) throws IllegalArgumentException;

	public void save(User u) throws IllegalArgumentException;

	public Vector<User> getUserByNachname(String nachname)
			throws IllegalArgumentException;

	// getbySourceUser Methoden

	public Vector<Kommentar> getKommentarBySourceUser(int sourceUser)
			throws IllegalArgumentException;

	public Pinnwand getPinnwandBySourceUser(int sourceUser)
			throws IllegalArgumentException;

	public Vector<Beitrag> getBeitragBySourceUser(int sourceUser)
			throws IllegalArgumentException;

	public Vector<Like> getLikeBySourceUser(int sourceUser)
			throws IllegalArgumentException;

	public Vector<Like> getLikeByTargetBeitrag(int beitragId)
			throws IllegalArgumentException;

	public Vector<Abo> getAboBySourcePinnwand(int pinnwandId)
			throws IllegalArgumentException;

	public Vector<Abo> getAboByTargetPinnwand(int pinnwandId)
			throws IllegalArgumentException;

	public Vector<Kommentar> getKommentarByTargetBeitrag(int beitragId)
			throws IllegalArgumentException;

	// "Find" by ID -Methoden f�r Alle SMOs

	public User getUserById(int id) throws IllegalArgumentException;

	public Abo getAboById(int id) throws IllegalArgumentException;

	public Pinnwand getPinnwandById(int id) throws IllegalArgumentException;

	public Beitrag getBeitragById(int id) throws IllegalArgumentException;

	public Like getLikeById(int id) throws IllegalArgumentException;

	public Kommentar getKommentarById(int id) throws IllegalArgumentException;

	// "FIND ALL"- Methoden f�r alle SMOs
	// alle Methodennamen sind im Singular gehalten.

	public Vector<User> getAllUser() throws IllegalArgumentException;

	public Vector<Pinnwand> getAllPinnwand() throws IllegalArgumentException;

	public Vector<Like> getAllLike() throws IllegalArgumentException;

	public Vector<Kommentar> getAllKommentar() throws IllegalArgumentException;

	public Vector<Beitrag> getAllBeitrag() throws IllegalArgumentException;

	public Vector<Abo> getAllAbo() throws IllegalArgumentException;

	// create methoden
	public Abo createAbo(int sourcePinnwand, int targetPinnwand)
			throws IllegalArgumentException;

	public Pinnwand createPinnwand(int sourceUser)
			throws IllegalArgumentException;

	public Kommentar createKommentar(String text, int sourceUser,
			int targetBeitrag) throws IllegalArgumentException;

	public Like createLike(int sourceUser, int targetBeitrag)
			throws IllegalArgumentException;

	public Beitrag createBeitrag(String text, int sourceUser)
			throws IllegalArgumentException;

	// delete methoden
	public User deleteUser(User u) throws IllegalArgumentException;

	public void deleteAbo(Abo a) throws IllegalArgumentException;

	public void deletePinnwand(Pinnwand p) throws IllegalArgumentException;

	public void deleteKommentar(Kommentar k) throws IllegalArgumentException;

	public void deleteLike(Like l) throws IllegalArgumentException;

	public void deleteBeitrag(Beitrag b) throws IllegalArgumentException;

	// update methoden
	public void save(Beitrag b) throws IllegalArgumentException;

	public void save(Kommentar k) throws IllegalArgumentException;

	public LoginInfo login(String requesturi) throws IllegalArgumentException;

	public User findUserbyEmail(String email);

	public User getUserByNickname(String nickname)
			throws IllegalArgumentException;
}
