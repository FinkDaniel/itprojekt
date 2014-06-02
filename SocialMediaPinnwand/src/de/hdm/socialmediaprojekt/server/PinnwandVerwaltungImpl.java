package de.hdm.socialmediaprojekt.server;

import java.util.Vector;



import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


import de.hdm.socialmediaprojekt.client.LoginInfo;
import de.hdm.socialmediaprojekt.server.db.*;

import de.hdm.socialmediaprojekt.shared.smo.*;

import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltung;



public class PinnwandVerwaltungImpl extends RemoteServiceServlet implements PinnwandVerwaltung {

	// Variablendefinition

	private static final long serialVersionUID = 1L;

	private UserMapper uMapper = null;
	private BeitragMapper bMapper = null;
	private PinnwandMapper pMapper = null;
	private KommentarMapper kMapper = null;
	private AboMapper aMapper = null;
	private LikeMapper lMapper = null;

	private String email;

	//Konstruktor

	public PinnwandVerwaltungImpl() throws IllegalArgumentException{

	}

	// Initialisierung

	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.bMapper = BeitragMapper.beitragMapper();
		this.pMapper = PinnwandMapper.pinnwandMapper();
		this.kMapper = KommentarMapper.kommentarMapper();
		this.aMapper = AboMapper.aboMapper();
		this.lMapper = LikeMapper.likeMapper();

	}




	// getbySourceUser Methoden

	public Vector<Kommentar> getKommentarBySourceUser(int sourceId) throws IllegalArgumentException{
		return this.kMapper.findBySourceUser(sourceId);
	}
	public Pinnwand getPinnwandBySourceUser(int sourceId) throws IllegalArgumentException{
		return this.pMapper.findBySourceUser(sourceId);
	}
	public Vector<Beitrag> getBeitragBySourceUser(int sourceId) throws IllegalArgumentException{
		return this.bMapper.findBySourceUser(sourceId);
	}
	public Vector<Like> getLikeBySourceUser(int sourceId) throws IllegalArgumentException{
		return this.lMapper.findBySourceUser(sourceId);
	}

	public Vector<Like> getLikeByTargetBeitrag(int beitragId) throws IllegalArgumentException{
		return this.lMapper.findByTargetBeitrag(beitragId);
	}
	public Vector<Abo> getAboBySourcePinnwand(int pinnwandId) throws IllegalArgumentException{
		return this.aMapper.findBySourcePinnwand(pinnwandId);
	}	
	public Vector<Abo> getAboByTargetPinnwand(int pinnwandId) throws IllegalArgumentException{
		return this.aMapper.findByTargetPinnwand(pinnwandId);
	}
	public Vector<Kommentar> getKommentarByTargetBeitrag(int beitragId) throws IllegalArgumentException{
		return this.kMapper.findByTargetBeitrag(beitragId);
	}


	// "Find" by ID -Methoden f�r Alle SMOs

	public User getUserById(int id) throws IllegalArgumentException{
		return this.uMapper.findByKey(id);
	}
	public Abo getAboById(int id) throws IllegalArgumentException{
		return this.aMapper.findByKey(id);
	}
	public Pinnwand getPinnwandById(int id) throws IllegalArgumentException{
		return this.pMapper.findByKey(id);
	}
	public Beitrag getBeitragById(int id) throws IllegalArgumentException{
		return this.bMapper.findByKey(id);
	}
	public Like getLikeById(int id) throws IllegalArgumentException{
		return this.lMapper.findByKey(id);
	}
	public Kommentar getKommentarById(int id) throws IllegalArgumentException{
		return this.kMapper.findByKey(id);
	}
	public Vector<User> getUserByNachname(String nachname) throws IllegalArgumentException{

	return this.uMapper.findByNachname(nachname);
	}

	// "FIND ALL"- Methoden f�r alle SMOs
	// alle Methodennamen sind im Singular gehalten.

	public Vector<User> getAllUser() throws IllegalArgumentException{
		return this.uMapper.findAll();
	}
	public Vector<Pinnwand> getAllPinnwand() throws IllegalArgumentException{
		return this.pMapper.findAll();
	}
	public Vector<Like> getAllLike() throws IllegalArgumentException{
		return this.lMapper.findAll();
	}
	public Vector<Kommentar> getAllKommentar() throws IllegalArgumentException{
		return this.kMapper.findAll();
	}
	public Vector<Beitrag> getAllBeitrag() throws IllegalArgumentException{
		return this.bMapper.findAll();
	}
	public Vector<Abo> getAllAbo() throws IllegalArgumentException{
		return this.aMapper.findAll();
	}

	// ---- Create f�r alle ----

	//create User
	public User createUser(String vorname, String nachname, String nickname, String email) throws IllegalArgumentException {

		User u = new User();
		u.setNachname(nachname);
		u.setVorname(vorname);
		u.setNickname(nickname);
		u.setEmail(email);
		u.setId(1);
		System.out.println("PinnwandVerwaltungImpl Test");

		this.uMapper.insert(u);

		createPinnwand(u.getId());

		return u;


	}
	//create Abo

	public Abo createAbo(int sourcePinnwand, int targetPinnwand) throws IllegalArgumentException{
		Abo a = new Abo();
		a.setSourcePinnwandID(sourcePinnwand);
		a.setTargetPinnwandID(targetPinnwand);
		a.setId(1);

		return this.aMapper.insert(a);
	}
	//create Pinnwand

	public Pinnwand createPinnwand(int sourceUser) throws IllegalArgumentException{
		Pinnwand p = new Pinnwand();
		p.setSourceUserID(sourceUser);
		p.setId(1);

		return this.pMapper.insert(p);
	}
	//create Kommentar

	public Kommentar createKommentar(String text, int sourceUser, int targetBeitrag) throws IllegalArgumentException{
		Kommentar k = new Kommentar();
		k.setKommentar(text);
		k.setSourceUserID(sourceUser);
		k.setTargetBeitragID(targetBeitrag);
		k.setId(1);

		return this.kMapper.insert(k);
	}
	//create Like

	public Like createLike(int sourceUser, int targetBeitrag) throws IllegalArgumentException{
		Like l = new Like();
		l.setSourceUserID(sourceUser);
		l.setTargetBeitragID(targetBeitrag);
		l.setId(1);

		return this.lMapper.insert(l);
	}	
	//create Beitrag

	public Beitrag createBeitrag(String text, int sourceUser) throws IllegalArgumentException{
		Beitrag b = new Beitrag();
		b.setBeitrag(text);
		b.setSourceUserID(sourceUser);
		b.setId(1);

		return this.bMapper.insert(b);
	}

	// ---- Delete f�r alle ----
	//delete User (inaktiv setzen des Users)

	public User deleteUser(User u) throws IllegalArgumentException{
		u.setNickname("Unbekannter User");
		//Variable f�r aktivit�t ---- auf false setzen
		return this.uMapper.update(u);
	}

	//delete Abo

	public void deleteAbo(Abo a) throws IllegalArgumentException{

		aMapper.delete(a);
	}
	//delete Pinnwand wird nicht benutzt

	public void deletePinnwand(Pinnwand p) throws IllegalArgumentException{
		pMapper.delete(p);
	}

	//delete Kommentar

	public void deleteKommentar(Kommentar k)throws IllegalArgumentException{
		kMapper.delete(k);
	}
	//delete Like

	public void deleteLike(Like l) throws IllegalArgumentException{
		lMapper.delete(l);
	}
	//delete Beitrag

	public void deleteBeitrag(Beitrag b) throws IllegalArgumentException{

		Vector<Like> likes = this.getLikeByTargetBeitrag(b.getId());
		if (likes != null){
			for (Like l : likes){
				this.deleteLike(l);
			}
		}
		// findKommentarbyBeitrag im beitrag mapper
		Vector<Kommentar> kommentare = this.getKommentarByTargetBeitrag(b.getId());
		if (kommentare != null){
			for (Kommentar k : kommentare){
				this.deleteKommentar(k);
			}
		}

		bMapper.delete(b);
	}

	// ---- update f�r Ausgew�hlte ---- (user, beitrag, kommentar)
	//update User
	public void save(User u) throws IllegalArgumentException {
		uMapper.update(u);
	}

	//update Beitrag
	public void save(Beitrag b) throws IllegalArgumentException{
		bMapper.update(b);
	}
	//update Kommentar
	public void save(Kommentar k) throws IllegalArgumentException{
		kMapper.update(k);
	}

	@Override
	public LoginInfo login(String requestUri) {
	    UserService userService = UserServiceFactory.getUserService();
	    com.google.appengine.api.users.User user = userService.getCurrentUser();
	    LoginInfo loginInfo = new LoginInfo();

	    if (user != null) {
	      loginInfo.setLoggedIn(true);
	      loginInfo.setEmailAddress(user.getEmail());
	      loginInfo.setNickname(user.getNickname());
	      loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
	    } else {
	      loginInfo.setLoggedIn(false);
	      loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
	    }
	    return loginInfo;
	  }

	public User findUserbyEmail(String email){
		String emailaddr = this.email;

		return uMapper.findByEmail(emailaddr);

	}



}