package de.hdm.socialmediaprojekt.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.socialmediaprojekt.client.LoginInfo;
import de.hdm.socialmediaprojekt.server.db.AboMapper;
import de.hdm.socialmediaprojekt.server.db.BeitragMapper;
import de.hdm.socialmediaprojekt.server.db.KommentarMapper;
import de.hdm.socialmediaprojekt.server.db.LikeMapper;
import de.hdm.socialmediaprojekt.server.db.PinnwandMapper;
import de.hdm.socialmediaprojekt.server.db.UserMapper;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltung;
import de.hdm.socialmediaprojekt.shared.report.BeitragReport;
import de.hdm.socialmediaprojekt.shared.report.CompositeParagraph;
import de.hdm.socialmediaprojekt.shared.report.HTMLReportWriter;
import de.hdm.socialmediaprojekt.shared.report.SimpleParagraph;
import de.hdm.socialmediaprojekt.shared.report.Spalte;
import de.hdm.socialmediaprojekt.shared.report.UserReport;
import de.hdm.socialmediaprojekt.shared.report.Zeile;
import de.hdm.socialmediaprojekt.shared.smo.Abo;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;
import de.hdm.socialmediaprojekt.shared.smo.Like;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;

/**
 * Diese Klasse ist die Implementierungsklasse des Interfaces
 * PinnwandVerwaltung. Enthalten sind alle Methoden die von der GUI benötigt
 * werden. Diese Klasse enthält die entsprechende Applikationslogik und greift
 * auf die verschiedenen Mapper zu.
 * 
 * @author Social Media Team
 * @extends
 * 
 * 
 */
public class PinnwandVerwaltungImpl extends RemoteServiceServlet implements
		PinnwandVerwaltung {

	/**
	 * @author Social Media Team Variablendefinition Es werden Instanzen für
	 *         alle Mapper erstellt und mit dem Standardwert <code> null </code>
	 *         belegt.
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
	 * @author Social Media Team
	 * @throws IllegalArgumentException
	 */
	public PinnwandVerwaltungImpl() throws IllegalArgumentException {

	}

	/**
	 * initialiseren Methode. Den Mappern werden die entsprechenden
	 * Konstruktoren zugewiesen und aufgerufen.
	 * 
	 * @author Social Media Team
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

	/**
	 * Diese Methode liefert alle Kommentare eines Users. Die Kommentar-Objekte
	 * werden als Vektor zurückgegeben. Parameterwert ist die ID des Nutzers von
	 * dem die Kommentare angefordert werden sollen.
	 * 
	 * @author Social Media Team
	 * @return Vector<Kommentar>
	 * @param Integer
	 *            (sourceID)
	 * @throws IllegalException
	 * 
	 */

	public Vector<Kommentar> getKommentarBySourceUser(int sourceId)
			throws IllegalArgumentException {
		return this.kMapper.findBySourceUser(sourceId);
	}

	public Pinnwand getPinnwandBySourceUser(int sourceId)
			throws IllegalArgumentException {
		return this.pMapper.findBySourceUser(sourceId);
	}

	/**
	 * Diese Methode gibt einen Vector an Beiträgen zurück. Übergabewert ist ein
	 * Array an Integer ID von Nutzern, von denen die Beiträge benötigt werden.
	 * Die Rückgabe der Werte erfolgt nach Erstellungszeitpunkt sortiert
	 * beginnend mit dem aktuellste, neuesten Beitrag.
	 * 
	 * @author Social Media Team
	 * @return Vector<Beitrag>
	 * @param Array
	 *            Integer
	 */
	public Vector<Beitrag> getBeitragBySourceUser(int[] sourceId)
			throws IllegalArgumentException {
		return this.bMapper.findBySourceUser(sourceId);
	}

	/**
	 * Diese Methode gibt einen Vector an Like-Objekten zurück. Übergabewert ist
	 * die Integer ID von dem Nutzern, von dem die Likes benötigt werden.
	 * 
	 * @author Social Media Team
	 * @return Vector<Like>
	 * @param Integer
	 */
	public Vector<Like> getLikeBySourceUser(int sourceId)
			throws IllegalArgumentException {
		return this.lMapper.findBySourceUser(sourceId);
	}

	/**
	 * Diese Methode gibt einen Vector an Like-Objekten zurück. Übergabewert ist
	 * die Integer ID von dem Beitrag, von dem die Likes benötigt werden.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Vector<Like>
	 */
	public Vector<Like> getLikeByTargetBeitrag(int beitragId)
			throws IllegalArgumentException {
		return this.lMapper.findByTargetBeitrag(beitragId);
	}

	/**
	 * Diese Methode gibt einen Vector von Abo-Objekten zurück. Übergabewert ist
	 * die Integer ID von der Ersteller(Source)Pinnwand, von der die Abos
	 * benötigt werden.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Vector<Abo>
	 */
	public Vector<Abo> getAboBySourcePinnwand(int pinnwandId)
			throws IllegalArgumentException {
		return this.aMapper.findBySourcePinnwand(pinnwandId);
	}

	/**
	 * Diese Methode gibt einen Vector von Abo-Objekten zurück. Übergabewert ist
	 * die Integer ID von der Ziel(Target)Pinnwand, von der die Abos benötigt
	 * werden.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Vector<Abo>
	 */
	public Vector<Abo> getAboByTargetPinnwand(int pinnwandId)
			throws IllegalArgumentException {
		return this.aMapper.findByTargetPinnwand(pinnwandId);
	}

	/**
	 * Diese Methode gibt einen Vector von Kommentar-Objekten zurück.
	 * Übergabewert ist die Integer ID von dem Beitrag, von dem die Kommentare
	 * benötigt werden.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Vector<Kommentar>
	 */
	public Vector<Kommentar> getKommentarByTargetBeitrag(int beitragId)
			throws IllegalArgumentException {
		return this.kMapper.findByTargetBeitrag(beitragId);
	}

	/**
	 * Diese Methode gibt ein User-Objekt zurück. Übergabewert ist die Integer
	 * ID des Nutzers, von dem das ganze User-Objekt benötigt wird.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return User
	 */

	public User getUserById(int id) throws IllegalArgumentException {
		return this.uMapper.findByKey(id);
	}

	/**
	 * Diese Methode gibt ein Abo-Objekt zurück. Übergabewert ist die Integer ID
	 * des Abos, von dem das ganze Abo-Objekt benötigt wird.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Abo
	 */
	public Abo getAboById(int id) throws IllegalArgumentException {
		return this.aMapper.findByKey(id);
	}

	/**
	 * Diese Methode gibt ein Pinnwand-Objekt zurück. Übergabewert ist die
	 * Integer ID der Pinnwand, von der das ganze Pinnwand-Objekt benötigt wird.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Pinnwand
	 */
	public Pinnwand getPinnwandById(int id) throws IllegalArgumentException {
		return this.pMapper.findByKey(id);
	}

	/**
	 * Diese Methode gibt ein Beitrag-Objekt zurück. Übergabewert ist die
	 * Integer ID des Beitrags, von der das ganze Beitrag-Objekt benötigt wird.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Beitrag
	 */
	public Beitrag getBeitragById(int id) throws IllegalArgumentException {
		return this.bMapper.findByKey(id);
	}

	/**
	 * Diese Methode gibt ein Like-Objekt zurück. Übergabewert ist die Integer
	 * ID des Likes, von der das ganze Like-Objekt benötigt wird.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Pinnwand
	 */
	public Like getLikeById(int id) throws IllegalArgumentException {
		return this.lMapper.findByKey(id);
	}

	/**
	 * Diese Methode gibt ein Kommentar-Objekt zurück. Übergabewert ist die
	 * Integer ID des Kommentars, von dem das ganze Kommentar-Objekt benötigt
	 * wird.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Kommentar
	 */
	public Kommentar getKommentarById(int id) throws IllegalArgumentException {
		return this.kMapper.findByKey(id);
	}

	/**
	 * Mithilfe dieser Methode bekommt man alle User-Objekte mit dem gleichen
	 * Nachnamen zurück. Diese Methode gibt einen Vector an User-Objekten
	 * zurück. Übergabewert ist der Nachname in Form eines Strings.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return Vector<User>
	 */
	public Vector<User> getUserByNachname(String nachname)
			throws IllegalArgumentException {

		return this.uMapper.findByNachname(nachname);
	}

	/**
	 * Mithilfe dieser Methode bekommt man ein User-Objekt mit dem übergebenen
	 * Nickname zurück. Nicknames sind eindeutig. Übergabewert ist der Nickname
	 * in Form eines Strings.
	 * 
	 * @author Social Media Team
	 * @param Integer
	 * @return User
	 */
	public User getUserByNickname(String nickname)
			throws IllegalArgumentException {
		return this.uMapper.findByNickname(nickname);
	}

	/**
	 * Mithilfe dieser Methode erhält man alle User-Objekte. Sie gibt einen
	 * Vector an User-Objekten zurück. Übergabewert gibt es keinen.
	 * 
	 * @author Social Media Team
	 * @return Vector<User>
	 */
	public Vector<User> getAllUser() throws IllegalArgumentException {
		return this.uMapper.findAll();
	}

	/**
	 * Mithilfe dieser Methode erhält man alle Pinnwand-Objekte. Sie gibt einen
	 * Vector an Pinnwand-Objekten zurück. Übergabewert gibt es keinen.
	 * 
	 * @author Social Media Team
	 * @return Vector<Pinnwand>
	 */
	public Vector<Pinnwand> getAllPinnwand() throws IllegalArgumentException {
		return this.pMapper.findAll();
	}

	/**
	 * Mithilfe dieser Methode erhält man alle Like-Objekte. Sie gibt einen
	 * Vector an Like-Objekten zurück. Übergabewert gibt es keinen.
	 * 
	 * @author Social Media Team
	 * @return Vector<Like>
	 */
	public Vector<Like> getAllLike() throws IllegalArgumentException {
		return this.lMapper.findAll();
	}

	/**
	 * Mithilfe dieser Methode erhält man alle Kommentar-Objekte. Sie gibt einen
	 * Vector an Kommentar-Objekten zurück. Übergabewert gibt es keinen.
	 * 
	 * @author Social Media Team
	 * @return Vector<Kommentar>
	 */
	public Vector<Kommentar> getAllKommentar() throws IllegalArgumentException {
		return this.kMapper.findAll();
	}

	/**
	 * Mithilfe dieser Methode erhält man alle Beitrags-Objekte. Sie gibt einen
	 * Vector an Beitrag-Objekten zurück. Übergabewert gibt es keinen.
	 * 
	 * @author Social Media Team
	 * @return Vector<Beitrag>
	 */
	public Vector<Beitrag> getAllBeitrag() throws IllegalArgumentException {
		return this.bMapper.findAll();
	}

	/**
	 * Mithilfe dieser Methode erhält man alle Abo-Objekte. Sie gibt einen
	 * Vector an Abo-Objekten zurück. Übergabewert gibt es keinen.
	 * 
	 * @author Social Media Team
	 * @return Vector<Abo>
	 */
	public Vector<Abo> getAllAbo() throws IllegalArgumentException {
		return this.aMapper.findAll();
	}

	/**
	 * Mithilfe dieser Methode erstellt man ein User-Objekt. Sie gibt dieses
	 * fertige Objekt anschließend auch zurück.
	 * 
	 * @author Social Media Team
	 * @param String
	 *            vorname, String nachname, String nickname, String email
	 * @return Vector<Pinnwand>
	 */
	public User createUser(String vorname, String nachname, String nickname,
			String email) throws IllegalArgumentException {

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

	/**
	 * Mithilfe dieser Methode erstellt man ein Abo-Objekt. Sie gibt dieses
	 * fertige Objekt anschließend auch zurück.
	 * 
	 * @author Social Media Team
	 * @return Abo
	 */
	public Abo createAbo(int sourcePinnwand, int targetPinnwand)
			throws IllegalArgumentException {
		Abo a = new Abo();
		a.setSourcePinnwandID(sourcePinnwand);
		a.setTargetPinnwandID(targetPinnwand);
		a.setId(1);

		return this.aMapper.insert(a);
	}

	/**
	 * Mithilfe dieser Methode erstellt man ein Pinnwand-Objekt. Sie gibt dieses
	 * fertige Objekt anschließend auch zurück.
	 * 
	 * @author Social Media Team
	 * @return Pinnwand
	 */
	public Pinnwand createPinnwand(int sourceUser)
			throws IllegalArgumentException {
		Pinnwand p = new Pinnwand();
		p.setSourceUserID(sourceUser);
		p.setId(1);

		return this.pMapper.insert(p);
	}

	/**
	 * Mithilfe dieser Methode erstellt man ein Kommenar-Objekt. Sie gibt dieses
	 * fertige Objekt anschließend auch zurück.
	 * 
	 * @author Social Media Team
	 * @return Kommentar
	 */

	public Kommentar createKommentar(String text, int sourceUser,
			int targetBeitrag) throws IllegalArgumentException {
		Kommentar k = new Kommentar();
		k.setKommentar(text);
		k.setSourceUserID(sourceUser);
		k.setTargetBeitragID(targetBeitrag);
		k.setId(1);

		return this.kMapper.insert(k);
	}

	/**
	 * Mithilfe dieser Methode erstellt man ein Like-Objekt. Sie gibt dieses
	 * fertige Objekt anschließend auch zurück.
	 * 
	 * @author Social Media Team
	 * @return Like
	 */
	public Like createLike(int sourceUser, int targetBeitrag)
			throws IllegalArgumentException {
		Like l = new Like();
		l.setSourceUserID(sourceUser);
		l.setTargetBeitragID(targetBeitrag);
		l.setId(1);

		return this.lMapper.insert(l);
	}

	/**
	 * Mithilfe dieser Methode erstellt man ein Beitrag-Objekt. Sie gibt dieses
	 * fertige Objekt anschließend auch zurück.
	 * 
	 * @author Social Media Team
	 * @return Beitrag
	 */

	public Beitrag createBeitrag(String text, int sourceUser)
			throws IllegalArgumentException {
		Beitrag b = new Beitrag();
		b.setBeitrag(text);
		b.setSourceUserID(sourceUser);
		b.setId(1);

		return this.bMapper.insert(b);
	}

	/**
	 * Mithilfe dieser Methode löscht man einen User.
	 * 
	 * @author Social Media Team
	 * @return User
	 */

	public void deleteUser(User u) throws IllegalArgumentException {

		Vector<Beitrag> beitraege = new Vector<Beitrag>();

		beitraege = getBeitragByUniqueSourceUser(u.getId());
		for (int i = 0; i < beitraege.size(); i++) {
			this.deleteBeitrag(beitraege.get(i));

		}
		Vector<Kommentar> kommentarlist = new Vector<Kommentar>();
		kommentarlist = this.getAllKommentar();
		for (int i = 0; i < kommentarlist.size(); i++) {
			if (kommentarlist.get(i).getSourceUserID() == u.getId()) {
				this.deleteKommentar(kommentarlist.get(i));
			}
		}
		Vector<Like> likelist = new Vector<Like>();
		likelist = this.getAllLike();
		for (int i = 0; i < likelist.size(); i++) {
			if (likelist.get(i).getSourceUserID() == u.getId()) {
				this.deleteLike(likelist.get(i));
			}
		}

		Vector<Abo> abolist = new Vector<Abo>();
		abolist = this.getAllAbo();
		for (int i = 0; i < abolist.size(); i++) {
			if (abolist.get(i).getSourcePinnwandID() == u.getId()
					|| abolist.get(i).getTargetPinnwandID() == u.getId()) {
				this.deleteAbo(abolist.get(i));
			}
		}

		Vector<Abo> aboliste = new Vector<Abo>();
		aboliste = this.getAboBySourceUser(u.getId());

		for (int b = 0; b < aboliste.size(); b++) {
			this.deleteAbo(aboliste.get(b));
		}
		Vector<Abo> vonanderenabonniert = new Vector<Abo>();
		vonanderenabonniert = this.getAboByTargetPinnwand(u.getId());
		for (int c = 0; c < vonanderenabonniert.size(); c++) {
			this.deleteAbo(vonanderenabonniert.get(c));
		}

		this.deletePinnwand(this.getPinnwandById(u.getId()));
		uMapper.delete(u);
	}

	public Vector<Beitrag> getBeitragByUniqueSourceUser(int id)
			throws IllegalArgumentException {
		return bMapper.findByUniqueSourceUser(id);
	}

	public void editBeitrag(Beitrag b) throws IllegalArgumentException {

		bMapper.update(b);
	}

	public void editKommentar(Kommentar k) throws IllegalArgumentException {
		System.out.print("Die Impl wird aufgerufen");
		kMapper.update(k);
	}

	/**
	 * Durch Aufruf dieser Methode löscht man das im Parameter übergebene Abo.
	 * 
	 * @author Social Media Team
	 * @return void
	 * @param Abo
	 */

	public void deleteAbo(Abo a) throws IllegalArgumentException {

		aMapper.delete(a);
	}

	/**
	 * Durch Aufruf dieser Methode löscht man die im Parameter übergebene
	 * Pinnwand.
	 * 
	 * @author Social Media Team
	 * @return void
	 * @param Pinnwand
	 */

	public void deletePinnwand(Pinnwand p) throws IllegalArgumentException {
		pMapper.delete(p);
	}

	/**
	 * Durch Aufruf dieser Methode löscht man den im Parameter übergebene
	 * Kommentar.
	 * 
	 * @author Social Media Team
	 * @return void
	 * @param Kommentar
	 */

	public void deleteKommentar(Kommentar k) throws IllegalArgumentException {
		kMapper.delete(k);
	}

	/**
	 * Durch Aufruf dieser Methode löscht man den im Parameter übergebenen Like.
	 * 
	 * @author Social Media Team
	 * @return void
	 * @param Like
	 */

	public void deleteLike(Like l) throws IllegalArgumentException {
		lMapper.delete(l);
	}

	/**
	 * Durch Aufruf dieser Methode löscht man das im Parameter übergebenen
	 * Beitrag. Hierbei werden auch saemtliche Kommentare dieses Beitrages
	 * geloescht.
	 * 
	 * @author Social Media Team
	 * @return void
	 * @param Beitrag
	 */

	public void deleteBeitrag(Beitrag b) throws IllegalArgumentException {

		Vector<Like> likes = this.getLikeByTargetBeitrag(b.getId());
		if (likes != null) {
			for (Like l : likes) {
				this.deleteLike(l);
			}
		}
		// findKommentarbyBeitrag im beitrag mapper
		Vector<Kommentar> kommentare = this.getKommentarByTargetBeitrag(b
				.getId());
		if (kommentare != null) {
			for (Kommentar k : kommentare) {
				this.deleteKommentar(k);
			}
		}

		bMapper.delete(b);
	}

	/**
	 * Durch Aufruf dieser Methode aendert man den im Parameter übergebenen User
	 * mit den Eigenschaften die ihn charakterisieren.
	 * 
	 * @author Social Media Team
	 * @return void
	 * @param Abo
	 */
	public void save(User u) throws IllegalArgumentException {
		uMapper.update(u);
	}

	/**
	 * Durch Aufruf dieser Methode aendert man den im Parameter übergebenen
	 * Beitrag mit den Eigenschaften die ihn charakterisieren.
	 * 
	 * @author Social Media Team
	 * @return void
	 * @param Abo
	 */
	public void save(Beitrag b) throws IllegalArgumentException {
		bMapper.update(b);
	}

	/**
	 * Durch Aufruf dieser Methode aendert man den im Parameter übergebenen
	 * Kommentar mit den Eigenschaften die ihn charakterisieren.
	 * 
	 * @author Social Media Team
	 * @return void
	 * @param Abo
	 */
	public void save(Kommentar k) throws IllegalArgumentException {
		kMapper.update(k);
	}

	/**
	 * Diese Methode ist fuer die Schnittstelle mit dem Google Login. Durch
	 * Aufruf wird der Login Prozess gestartet
	 * 
	 * @author Social Media Team
	 * @return LoginInfo
	 * @param String
	 *            requestUri
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
	 * Durch Aufruf dieser Methode erhaelt man den User mit der E Mail Adresse
	 * die der Methode in String Form uebergeben wurde.
	 * 
	 * @author Social Media Team
	 * @return User
	 * @param String
	 *            email
	 */
	public User findUserbyEmail(String email) {
		String emailaddr = this.email;

		return uMapper.findByEmail(emailaddr);

	}

	/**
	 * Durch Aufruf dieser Methode erhaelt man einen Vector an Abos die der User
	 * mit der uebergebenen UserID abboniert hat.
	 * 
	 * @author Social Media Team
	 * @return Vector<Abo>
	 * @param Integer
	 */
	public Vector<Abo> getAboBySourceUser(int sourceUserID)
			throws IllegalArgumentException {

		Pinnwand p = new Pinnwand();
		p = this.pMapper.findBySourceUser(sourceUserID);

		return this.aMapper.findBySourcePinnwand(p.getId());

	}

	/**
	 * Diese Methode erstellt einen Report über alle Beiträge über einen
	 * vordefinierten Zeitraum. Hierzu kommen alle zugehörigen Nutzer-IDs, die
	 * Likeanzahl und die Kommentaranzahl.
	 * 
	 * @param datumVon
	 *            ist das Startdatum
	 * @param datumBis
	 *            ist das Zieldatum
	 * @return der fertige HTML-Report
	 */
	public String createBeitragReport(String datumVon, String datumBis)
			throws IllegalArgumentException {

		BeitragReport result = new BeitragReport();

		result.setTitel("Alle Beiträge");

		result.setDate(new Date());

		CompositeParagraph header = new CompositeParagraph();

		header.addSubParagraph(new SimpleParagraph("Zeitraum Von: " + datumVon
				+ " Bis: " + datumBis));

		result.setKopfdaten(header);

		Zeile headline = new Zeile();

		headline.addSpalte(new Spalte("Nutzer"));
		headline.addSpalte(new Spalte("Inhalt"));
		headline.addSpalte(new Spalte("Like-Anzahl"));
		headline.addSpalte(new Spalte("Kommentaranzahl"));

		result.addZeile(headline);

		ArrayList<Beitrag> beitraege = BeitragMapper.beitragMapper()
				.getBeitraegeBetweenTwoDates(datumVon, datumBis);

		beitraege = sort(beitraege);

		for (Beitrag beitrag : beitraege) {

			Zeile accountRow = new Zeile();

			accountRow.addSpalte(new Spalte(beitrag.getSourceUserID() + " "));

			accountRow.addSpalte(new Spalte(beitrag.getBeitrag()));

			accountRow.addSpalte(new Spalte(String.valueOf(beitrag
					.getLikeList().size())));

			accountRow.addSpalte(new Spalte(String.valueOf(beitrag
					.getKommentarListe().size())));

			result.addZeile(accountRow);
		}

		HTMLReportWriter writer = new HTMLReportWriter();
		writer.process(result);

		return writer.getReportText();
	}

	/**
	 * Diese Methode erstellt einen Report über alle User über einen
	 * vordefinierten Zeitraum. Hierzu gehört der ausgewählte Nutzer, die
	 * gegebenen Liks des Nutzers, die bekommenen Likes des Nutzers sowie die
	 * Anzahl der Beiträge des Nutzers.
	 * 
	 * @param datumVon
	 *            ist das Startdatum
	 * @param datumBis
	 *            ist das Zieldatum
	 * @return der fertige HTML-Report
	 */
	public String createUserReport(User u, String datumVon, String datumBis)
			throws IllegalArgumentException {

		UserReport result = new UserReport();

		result.setTitel("Informationen Über " + u.getVorname() + " "
				+ u.getNachname());

		result.setDate(new Date());

		CompositeParagraph header = new CompositeParagraph();

		header.addSubParagraph(new SimpleParagraph(u.getNachname() + ", "
				+ u.getVorname()));

		header.addSubParagraph(new SimpleParagraph("Email:" + u.getEmail()));

		header.addSubParagraph(new SimpleParagraph("Zeitraum Von: " + datumVon
				+ " Bis: " + datumBis));

		result.setKopfdaten(header);

		Zeile headline = new Zeile();

		headline.addSpalte(new Spalte("Abonnentenanzahl"));
		headline.addSpalte(new Spalte("Beitragsanzahl"));
		headline.addSpalte(new Spalte("Likes bekommen"));
		headline.addSpalte(new Spalte("Likes gegeben"));

		result.addZeile(headline);

		Zeile accountRow = new Zeile();

		ArrayList<Abo> aboListe = AboMapper.aboMapper().getAboBetweenTwoDates(
				datumVon, datumBis, u);
		if (aboListe != null) {
			accountRow.addSpalte(new Spalte(String.valueOf(aboListe.size())));
		} else {
			accountRow.addSpalte(new Spalte("0"));
		}

		ArrayList<Beitrag> beitragListe = BeitragMapper.beitragMapper()
				.getBeiträgeBetweenTwoDates(
						datumVon,
						datumBis,
						PinnwandMapper.pinnwandMapper().getPinnwandByNutzer(u)
								.getId());
		if (beitragListe != null) {
			accountRow
					.addSpalte(new Spalte(String.valueOf(beitragListe.size())));
		} else {
			accountRow.addSpalte(new Spalte("0"));
		}

		int likeAnzahl = 0;
		if (beitragListe != null) {
			for (Beitrag beitrag : beitragListe) {
				if (beitrag.getLikeList() != null)
					likeAnzahl += beitrag.getLikeList().size();
			}
		}
		accountRow.addSpalte(new Spalte(String.valueOf(likeAnzahl)));

		accountRow.addSpalte(new Spalte(String.valueOf(LikeMapper.likeMapper()
				.getLikeCountByNutzer(u, datumVon, datumBis))));

		result.addZeile(accountRow);

		HTMLReportWriter writer = new HTMLReportWriter();
		writer.process(result);

		return writer.getReportText();
	}

	/**
	 * Die Reports werden nach der Bubble Sort Methode sortiert.
	 * 
	 * 
	 */
	private ArrayList<Beitrag> sort(ArrayList<Beitrag> array) {
		int n = array.size();
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 1; j <= i; j++) {
				if (array.get(j - 1).getLikeList().size() < array.get(j)
						.getLikeList().size()) {
					Beitrag temp = array.get(j - 1);
					array.set(j - 1, array.get(j));
					array.set(j, temp);

				}
			}
		}
		return array;
	}

}
