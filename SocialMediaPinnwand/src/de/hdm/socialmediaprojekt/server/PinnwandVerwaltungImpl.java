package de.hdm.socialmediaprojekt.server;

import java.util.Vector;




import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;



import de.hdm.socialmediaprojekt.client.LoginInfo;
import de.hdm.socialmediaprojekt.server.db.*;
import de.hdm.socialmediaprojekt.shared.smo.*;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltung;

/**
 * Diese Klasse ist die Implementierungsklasse des Interfaces PinnwandVerwaltung. Enthalten sind alle Methoden die von der GUI benötigt werden. Diese Klasse enthält die entsprechende Applikationslogik und greift auf die verschiedenen Mapper zu.
 * 
 * @author Daniel Fink
 * @extends
 * 
 *
 */

public class PinnwandVerwaltungImpl extends RemoteServiceServlet implements PinnwandVerwaltung {

	/**
	 * @author Daniel Fink
	 * Variablendefinition
	 * Es werden Instanzen für alle Mapper erstellt und mit dem Standardwert <code> null </code> belegt.
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private UserMapper uMapper = null;
	private BeitragMapper bMapper = null;
	private PinnwandMapper pMapper = null;
	private KommentarMapper kMapper = null;
	private AboMapper aMapper = null;
	private LikeMapper lMapper = null;

	private String email;
	
	/**
	 * Konstruktor-Methode
	 * 
	 * @author Daniel Fink
	 * @throws IllegalArgumentException
	 */
	
	public PinnwandVerwaltungImpl() throws IllegalArgumentException{
		
	}
	
	/**
	 * initialiseren Methode. Den Mappern werden die entsprechenden Konstruktoren zugewiesen und aufgerufen.
	 * @author Daniel Fink
	 * @throws IllegalArgumentException
	 * 
	 */
	
	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.bMapper = BeitragMapper.beitragMapper();
		this.pMapper = PinnwandMapper.pinnwandMapper();
		this.kMapper = KommentarMapper.kommentarMapper();
		this.aMapper = AboMapper.aboMapper();
		this.lMapper = LikeMapper.likeMapper();
		
	}
	
	


	// get-By Methoden
	
	/**
	 * Diese Methode liefert alle Kommentare eines Users. Die Kommentar-Objekte werden als Vektor zurückgegeben. Parameterwert ist die ID des Nutzers von dem die Kommentare angefordert werden sollen.
	 * 
	 * @author Daniel Fink
	 * @return Vector<Kommentar>
	 * @param Integer (sourceID)
	 * @throws IllegalException
	 * 
	 */
	public Vector<Kommentar> getKommentarBySourceUser(int sourceId) throws IllegalArgumentException{
		return this.kMapper.findBySourceUser(sourceId);
	}
	
	/**
	 * Diese Methode gibt die Pinnwand eines Nutzers zurück. Übergabewert ist die Integer ID des Nutzers
	 * @author Daniel Fink
	 * @param Integer
	 * @return Pinnwand
	 *  
	 * 
	 */
	public Pinnwand getPinnwandBySourceUser(int sourceId) throws IllegalArgumentException{
		return this.pMapper.findBySourceUser(sourceId);
	}
	/**
	 * Diese Methode gibt einen Vector an Beiträgen zurück. Übergabewert ist ein Array an Integer ID von Nutzern, von denen die Beiträge benötigt werden. Die Rückgabe der Werte erfolgt nach Erstellungszeitpunkt sortiert beginnend mit dem aktuellste, neuesten Beitrag.
	 * 
	 * @author Daniel Fink
	 * @return Vector<Beitrag>
	 * @param Array Integer
	 */
	public Vector<Beitrag> getBeitragBySourceUser(int[] sourceId) throws IllegalArgumentException{
		return this.bMapper.findBySourceUser(sourceId);
	}
	/**
	 * Diese Methode gibt einen Vector an Like-Objekten zurück. Übergabewert ist die Integer ID von dem  Nutzern, von dem die Likes benötigt werden.
	 * @author Daniel Fink
	 * @return Vector<Like>
	 * @param Integer
	 */
	public Vector<Like> getLikeBySourceUser(int sourceId) throws IllegalArgumentException{
		return this.lMapper.findBySourceUser(sourceId);
	}
	/**
	 * Diese Methode gibt einen Vector an Like-Objekten zurück. Übergabewert ist die Integer ID von dem  Beitrag, von dem die Likes benötigt werden.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Vector<Like>
	 */
	public Vector<Like> getLikeByTargetBeitrag(int beitragId) throws IllegalArgumentException{
		return this.lMapper.findByTargetBeitrag(beitragId);
	}
	/**
	 * Diese Methode gibt einen Vector von Abo-Objekten zurück. Übergabewert ist die Integer ID von der Ersteller(Source)Pinnwand, von der die Abos benötigt werden.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Vector<Abo>
	 */
	public Vector<Abo> getAboBySourcePinnwand(int pinnwandId) throws IllegalArgumentException{
		return this.aMapper.findBySourcePinnwand(pinnwandId);
	}
	/**
	 * Diese Methode gibt einen Vector von Abo-Objekten zurück. Übergabewert ist die Integer ID von der Ziel(Target)Pinnwand, von der die Abos benötigt werden.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Vector<Abo>
	 */
	public Vector<Abo> getAboByTargetPinnwand(int pinnwandId) throws IllegalArgumentException{
		return this.aMapper.findByTargetPinnwand(pinnwandId);
	}
	/**
	 * Diese Methode gibt einen Vector von Kommentar-Objekten zurück. Übergabewert ist die Integer ID von dem Beitrag, von dem die Kommentare benötigt werden.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Vector<Kommentar>
	 */
	public Vector<Kommentar> getKommentarByTargetBeitrag(int beitragId) throws IllegalArgumentException{
		return this.kMapper.findByTargetBeitrag(beitragId);
	}
	
	
	// "Find" by ID -Methoden f�r Alle SMOs
	/**
	 * Diese Methode gibt ein User-Objekt zurück. Übergabewert ist die Integer ID des Nutzers, von dem das ganze User-Objekt benötigt wird.
	 * @author Daniel Fink
	 * @param Integer
	 * @return User
	 */
	public User getUserById(int id) throws IllegalArgumentException{
		return this.uMapper.findByKey(id);
	}
	/**
	 * Diese Methode gibt ein Abo-Objekt zurück. Übergabewert ist die Integer ID des Abos, von dem das ganze Abo-Objekt benötigt wird.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Abo
	 */
	public Abo getAboById(int id) throws IllegalArgumentException{
		return this.aMapper.findByKey(id);
	}
	/**
	 * Diese Methode gibt ein Pinnwand-Objekt zurück. Übergabewert ist die Integer ID der Pinnwand, von der das ganze Pinnwand-Objekt benötigt wird.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Pinnwand
	 */
	public Pinnwand getPinnwandById(int id) throws IllegalArgumentException{
		return this.pMapper.findByKey(id);
	}
	/**
	 * Diese Methode gibt ein Beitrag-Objekt zurück. Übergabewert ist die Integer ID des Beitrags, von der das ganze Beitrag-Objekt benötigt wird.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Beitrag
	 */
	public Beitrag getBeitragById(int id) throws IllegalArgumentException{
		return this.bMapper.findByKey(id);
	}
	/**
	 * Diese Methode gibt ein Like-Objekt zurück. Übergabewert ist die Integer ID des Likes, von der das ganze Like-Objekt benötigt wird.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Pinnwand
	 */
	public Like getLikeById(int id) throws IllegalArgumentException{
		return this.lMapper.findByKey(id);
	}
	/**
	 * Diese Methode gibt ein Kommentar-Objekt zurück. Übergabewert ist die Integer ID des Kommentars, von dem das ganze Kommentar-Objekt benötigt wird.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Kommentar
	 */
	public Kommentar getKommentarById(int id) throws IllegalArgumentException{
		return this.kMapper.findByKey(id);
	}
	/**
	 * Mithilfe dieser Methode bekommt man alle User-Objekte mit dem gleichen Nachnamen zurück. Diese Methode gibt einen Vector an User-Objekten zurück. Übergabewert ist der Nachname in Form eines Strings.
	 * @author Daniel Fink
	 * @param Integer
	 * @return Vector<User>
	 */
	public Vector<User> getUserByNachname(String nachname) throws IllegalArgumentException{
		
	return this.uMapper.findByNachname(nachname);
	}
	/**
	 * Mithilfe dieser Methode bekommt man ein User-Objekt mit dem übergebenen Nickname zurück. Nicknames sind eindeutig. Übergabewert ist der Nickname in Form eines Strings.
	 * @author Daniel Fink
	 * @param Integer
	 * @return User
	 */
	public User getUserByNickname(String nickname) throws IllegalArgumentException{
		return this.uMapper.findByNickname(nickname);
	}
	
	// "FIND ALL"- Methoden f�r alle SMOs
	// alle Methodennamen sind im Singular gehalten.
	
	/**
	 * Mithilfe dieser Methode erhält man alle User-Objekte. Sie gibt einen Vector an User-Objekten zurück. Übergabewert gibt es keinen.
	 * @author Daniel Fink
	 * @return Vector<User>
	 */
	public Vector<User> getAllUser() throws IllegalArgumentException{
		return this.uMapper.findAll();
	}
	/**
	 * Mithilfe dieser Methode erhält man alle Pinnwand-Objekte. Sie gibt einen Vector an Pinnwand-Objekten zurück. Übergabewert gibt es keinen.
	 * @author Daniel Fink
	 * @return Vector<Pinnwand>
	 */
	public Vector<Pinnwand> getAllPinnwand() throws IllegalArgumentException{
		return this.pMapper.findAll();
	}
	/**
	 * Mithilfe dieser Methode erhält man alle Like-Objekte. Sie gibt einen Vector an Like-Objekten zurück. Übergabewert gibt es keinen.
	 * @author Daniel Fink
	 * @return Vector<Like>
	 */
	public Vector<Like> getAllLike() throws IllegalArgumentException{
		return this.lMapper.findAll();
	}
	/**
	 * Mithilfe dieser Methode erhält man alle Kommentar-Objekte. Sie gibt einen Vector an Kommentar-Objekten zurück. Übergabewert gibt es keinen.
	 * @author Daniel Fink
	 * @return Vector<Kommentar>
	 */
	public Vector<Kommentar> getAllKommentar() throws IllegalArgumentException{
		return this.kMapper.findAll();
	}
	/**
	 * Mithilfe dieser Methode erhält man alle Beitrags-Objekte. Sie gibt einen Vector an Beitrag-Objekten zurück. Übergabewert gibt es keinen.
	 * @author Daniel Fink
	 * @return Vector<Beitrag>
	 */
	public Vector<Beitrag> getAllBeitrag() throws IllegalArgumentException{
		return this.bMapper.findAll();
	}
	/**
	 * Mithilfe dieser Methode erhält man alle Abo-Objekte. Sie gibt einen Vector an Abo-Objekten zurück. Übergabewert gibt es keinen.
	 * @author Daniel Fink
	 * @return Vector<Abo>
	 */
	public Vector<Abo> getAllAbo() throws IllegalArgumentException{
		return this.aMapper.findAll();
	}
	
	// ---- Create f�r alle ----
	
	//create User
	/**
	 * Mithilfe dieser Methode erstellt man ein User-Objekt. Sie gibt dieses fertige Objekt anschließend auch zurück.
	 * @author Daniel Fink
	 * @param String vorname, String nachname, String nickname, String email
	 * @return Vector<Pinnwand>
	 */
	public User createUser(String vorname, String nachname, String nickname, String email) throws IllegalArgumentException {
		
		User u = new User();
		u.setNachname(nachname);
		u.setVorname(vorname);
		u.setNickname(nickname);
		u.setEmail(email);
		u.setId(1);
		
		this.uMapper.insert(u);
		
		createPinnwand(u.getId());
		
		return u;
		
		
	}
	//create Abo
	/**
	 * Mithilfe dieser Methode erstellt man ein Abo-Objekt. Sie gibt dieses fertige Objekt anschließend auch zurück.
	 * @author Daniel Fink
	 * @return Abo
	 */
	public Abo createAbo(int sourcePinnwand, int targetPinnwand) throws IllegalArgumentException{
		Abo a = new Abo();
		a.setSourcePinnwandID(sourcePinnwand);
		a.setTargetPinnwandID(targetPinnwand);
		a.setId(1);
		
		return this.aMapper.insert(a);
	}
	//create Pinnwand
	/**
	 * Mithilfe dieser Methode erstellt man ein Pinnwand-Objekt. Sie gibt dieses fertige Objekt anschließend auch zurück.
	 * @author Daniel Fink
	 * @return Pinnwand
	 */
	public Pinnwand createPinnwand(int sourceUser) throws IllegalArgumentException{
		Pinnwand p = new Pinnwand();
		p.setSourceUserID(sourceUser);
		p.setId(1);
		
		return this.pMapper.insert(p);
	}
	//create Kommentar
	/**
	 * Mithilfe dieser Methode erstellt man ein Kommenar-Objekt. Sie gibt dieses fertige Objekt anschließend auch zurück.
	 * @author Daniel Fink
	 * @return Kommentar
	 */
	public Kommentar createKommentar(String text, int sourceUser, int targetBeitrag) throws IllegalArgumentException{
		Kommentar k = new Kommentar();
		k.setKommentar(text);
		k.setSourceUserID(sourceUser);
		k.setTargetBeitragID(targetBeitrag);
		k.setId(1);
		
		return this.kMapper.insert(k);
	}
	//create Like
	/**
	 * Mithilfe dieser Methode erstellt man ein Like-Objekt. Sie gibt dieses fertige Objekt anschließend auch zurück.
	 * @author Daniel Fink
	 * @return Like
	 */
	public Like createLike(int sourceUser, int targetBeitrag) throws IllegalArgumentException{
		Like l = new Like();
		l.setSourceUserID(sourceUser);
		l.setTargetBeitragID(targetBeitrag);
		l.setId(1);
		
		return this.lMapper.insert(l);
	}	
	//create Beitrag
	/**
	 * Mithilfe dieser Methode erstellt man ein Beitrag-Objekt. Sie gibt dieses fertige Objekt anschließend auch zurück.
	 * @author Daniel Fink
	 * @return Beitrag
	 */
	public Beitrag createBeitrag(String text, int sourceUser) throws IllegalArgumentException{
		Beitrag b = new Beitrag();
		b.setBeitrag(text);
		b.setSourceUserID(sourceUser);
		b.setId(1);
		
		return this.bMapper.insert(b);
	}
	
	// ---- Delete f�r alle ----
	//delete User (inaktiv setzen des Users)
	/**
	 * Mithilfe dieser Methode setzt man einen User inaktiv. Der Username wird in <text>unbekannter User</text> geändert und es ist ihm nicht weiter möglich sich einzuloggen.
	 * @author Daniel Fink
	 * @return User
	 */
	public User deleteUser(User u) throws IllegalArgumentException{
		u.setNickname("Unbekannter User");
		//Variable f�r aktivit�t ---- auf false setzen
		return this.uMapper.update(u);
	}
	
	//delete Abo
	/**
	 * Durch Aufruf dieser Methode löscht man das im Parameter übergebene Abo. 
	 * @author Daniel Fink
	 * @return void
	 * @param Abo
	 */
	public void deleteAbo(Abo a) throws IllegalArgumentException{
		
		aMapper.delete(a);
	}
	//delete Pinnwand wird nicht benutzt
	/**
	 * Durch Aufruf dieser Methode löscht man die im Parameter übergebene Pinnwand. 
	 * @author Daniel Fink
	 * @return void
	 * @param Pinnwand
	 */
	public void deletePinnwand(Pinnwand p) throws IllegalArgumentException{
		pMapper.delete(p);
	}
	
	//delete Kommentar
	/**
	 * Durch Aufruf dieser Methode löscht man den im Parameter übergebene Kommentar. 
	 * @author Daniel Fink
	 * @return void
	 * @param Kommentar
	 */
	public void deleteKommentar(Kommentar k)throws IllegalArgumentException{
		kMapper.delete(k);
	}
	//delete Like
	
	
	
	/**
	 * Durch Aufruf dieser Methode löscht man den im Parameter übergebenen Like. 
	 * @author Daniel Fink
	 * @return void
	 * @param Like
	 */
	public void deleteLike(Like l) throws IllegalArgumentException{
		lMapper.delete(l);
	}
	//delete Beitrag
	/**
	 * Durch Aufruf dieser Methode löscht man das im Parameter übergebenen Beitrag. Hierbei werden auch saemtliche Kommentare dieses Beitrages geloescht.
	 * @author Daniel Fink
	 * @return void
	 * @param Beitrag
	 */
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
	/**
	 * Durch Aufruf dieser Methode aendert man den im Parameter übergebenen User mit den Eigenschaften die ihn charakterisieren. 
	 * @author Daniel Fink
	 * @return void
	 * @param Abo
	 */
	public void save(User u) throws IllegalArgumentException {
		uMapper.update(u);
	}
	
	//update Beitrag
	/**
	 * Durch Aufruf dieser Methode aendert man den im Parameter übergebenen Beitrag mit den Eigenschaften die ihn charakterisieren. 
	 * @author Daniel Fink
	 * @return void
	 * @param Abo
	 */
	public void save(Beitrag b) throws IllegalArgumentException{
		bMapper.update(b);
	}
	//update Kommentar
	/**
	 * Durch Aufruf dieser Methode aendert man den im Parameter übergebenen Kommentar mit den Eigenschaften die ihn charakterisieren. 
	 * @author Daniel Fink
	 * @return void
	 * @param Abo
	 */
	public void save(Kommentar k) throws IllegalArgumentException{
		kMapper.update(k);
	}

	@Override
	/**
	 * Diese Methode ist fuer die Schnittstelle mit dem Google Login. Durch Aufruf wird der Login Prozess gestartet
	 * @author Daniel Fink
	 * @return LoginInfo
	 * @param String requestUri
	 */
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
	/**
	 * Durch Aufruf dieser Methode erhaelt man den User mit der E Mail Adresse die der Methode in String Form uebergeben wurde. 
	 * @author Daniel Fink
	 * @return User
	 * @param String email
	 */
	public User findUserbyEmail(String email){
		String emailaddr = this.email;
		
		return uMapper.findByEmail(emailaddr);
		
	}
	/**
	 * Durch Aufruf dieser Methode erhaelt man einen Vector an Abos die der User mit der uebergebenen UserID abboniert hat.
	 * @author Daniel Fink
	 * @return User
	 * @param Integer
	 */
	public Vector<Abo> getAboBySourceUser(int sourceUserID) throws IllegalArgumentException{
		
		Pinnwand p = new Pinnwand();
		p = this.pMapper.findBySourceUser(sourceUserID);
		
		return this.aMapper.findBySourcePinnwand(p.getId());
		
		
		
		
	}
	
	

}


