package de.hdm.socialmediaprojekt.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.socialmediaprojekt.client.LoginInfo;
import de.hdm.socialmediaprojekt.server.db.*;
import de.hdm.socialmediaprojekt.shared.report.BeitragReport;
import de.hdm.socialmediaprojekt.shared.report.CompositeParagraph;
import de.hdm.socialmediaprojekt.shared.report.HTMLReportWriter;
import de.hdm.socialmediaprojekt.shared.report.SimpleParagraph;
import de.hdm.socialmediaprojekt.shared.report.Spalte;
import de.hdm.socialmediaprojekt.shared.report.UserReport;
import de.hdm.socialmediaprojekt.shared.report.Zeile;
import de.hdm.socialmediaprojekt.shared.smo.*;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltung;

public class PinnwandVerwaltungImpl extends RemoteServiceServlet implements
		PinnwandVerwaltung {

	// Variablendefinition

	private static final long serialVersionUID = 1L;

	private UserMapper uMapper = null;
	private BeitragMapper bMapper = null;
	private PinnwandMapper pMapper = null;
	private KommentarMapper kMapper = null;
	private AboMapper aMapper = null;
	private LikeMapper lMapper = null;

	private String email;

	// Konstruktor

	public PinnwandVerwaltungImpl() throws IllegalArgumentException {

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

	public Vector<Kommentar> getKommentarBySourceUser(int sourceId)
			throws IllegalArgumentException {
		return this.kMapper.findBySourceUser(sourceId);
	}

	public Pinnwand getPinnwandBySourceUser(int sourceId)
			throws IllegalArgumentException {
		return this.pMapper.findBySourceUser(sourceId);
	}

	public Vector<Beitrag> getBeitragBySourceUser(int[] sourceId)
			throws IllegalArgumentException {
		return this.bMapper.findBySourceUser(sourceId);
	}

	public Vector<Like> getLikeBySourceUser(int sourceId)
			throws IllegalArgumentException {
		return this.lMapper.findBySourceUser(sourceId);
	}

	public Vector<Like> getLikeByTargetBeitrag(int beitragId)
			throws IllegalArgumentException {
		return this.lMapper.findByTargetBeitrag(beitragId);
	}

	public Vector<Abo> getAboBySourcePinnwand(int pinnwandId)
			throws IllegalArgumentException {
		return this.aMapper.findBySourcePinnwand(pinnwandId);
	}

	public Vector<Abo> getAboByTargetPinnwand(int pinnwandId)
			throws IllegalArgumentException {
		return this.aMapper.findByTargetPinnwand(pinnwandId);
	}

	public Vector<Kommentar> getKommentarByTargetBeitrag(int beitragId)
			throws IllegalArgumentException {
		return this.kMapper.findByTargetBeitrag(beitragId);
	}

	// "Find" by ID -Methoden f�r Alle SMOs

	public User getUserById(int id) throws IllegalArgumentException {
		return this.uMapper.findByKey(id);
	}

	public Abo getAboById(int id) throws IllegalArgumentException {
		return this.aMapper.findByKey(id);
	}

	public Pinnwand getPinnwandById(int id) throws IllegalArgumentException {
		return this.pMapper.findByKey(id);
	}

	public Beitrag getBeitragById(int id) throws IllegalArgumentException {
		return this.bMapper.findByKey(id);
	}

	public Like getLikeById(int id) throws IllegalArgumentException {
		return this.lMapper.findByKey(id);
	}

	public Kommentar getKommentarById(int id) throws IllegalArgumentException {
		return this.kMapper.findByKey(id);
	}

	public Vector<User> getUserByNachname(String nachname)
			throws IllegalArgumentException {

		return this.uMapper.findByNachname(nachname);
	}

	public User getUserByNickname(String nickname)
			throws IllegalArgumentException {
		return this.uMapper.findByNickname(nickname);
	}

	// "FIND ALL"- Methoden f�r alle SMOs
	// alle Methodennamen sind im Singular gehalten.

	public Vector<User> getAllUser() throws IllegalArgumentException {
		return this.uMapper.findAll();
	}

	public Vector<Pinnwand> getAllPinnwand() throws IllegalArgumentException {
		return this.pMapper.findAll();
	}

	public Vector<Like> getAllLike() throws IllegalArgumentException {
		return this.lMapper.findAll();
	}

	public Vector<Kommentar> getAllKommentar() throws IllegalArgumentException {
		return this.kMapper.findAll();
	}

	public Vector<Beitrag> getAllBeitrag() throws IllegalArgumentException {
		return this.bMapper.findAll();
	}

	public Vector<Abo> getAllAbo() throws IllegalArgumentException {
		return this.aMapper.findAll();
	}

	// ---- Create f�r alle ----

	// create User
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

	// create Abo

	public Abo createAbo(int sourcePinnwand, int targetPinnwand)
			throws IllegalArgumentException {
		Abo a = new Abo();
		a.setSourcePinnwandID(sourcePinnwand);
		a.setTargetPinnwandID(targetPinnwand);
		a.setId(1);

		return this.aMapper.insert(a);
	}

	// create Pinnwand

	public Pinnwand createPinnwand(int sourceUser)
			throws IllegalArgumentException {
		Pinnwand p = new Pinnwand();
		p.setSourceUserID(sourceUser);
		p.setId(1);

		return this.pMapper.insert(p);
	}

	// create Kommentar

	public Kommentar createKommentar(String text, int sourceUser,
			int targetBeitrag) throws IllegalArgumentException {
		Kommentar k = new Kommentar();
		k.setKommentar(text);
		k.setSourceUserID(sourceUser);
		k.setTargetBeitragID(targetBeitrag);
		k.setId(1);

		return this.kMapper.insert(k);
	}

	// create Like

	public Like createLike(int sourceUser, int targetBeitrag)
			throws IllegalArgumentException {
		Like l = new Like();
		l.setSourceUserID(sourceUser);
		l.setTargetBeitragID(targetBeitrag);
		l.setId(1);

		return this.lMapper.insert(l);
	}

	// create Beitrag

	public Beitrag createBeitrag(String text, int sourceUser)
			throws IllegalArgumentException {
		Beitrag b = new Beitrag();
		b.setBeitrag(text);
		b.setSourceUserID(sourceUser);
		b.setId(1);

		return this.bMapper.insert(b);
	}

	// ---- Delete f�r alle ----
	// delete User (inaktiv setzen des Users)

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

	// delete Abo

	public void deleteAbo(Abo a) throws IllegalArgumentException {

		aMapper.delete(a);
	}

	// delete Pinnwand wird nicht benutzt

	public void deletePinnwand(Pinnwand p) throws IllegalArgumentException {
		pMapper.delete(p);
	}

	// delete Kommentar

	public void deleteKommentar(Kommentar k) throws IllegalArgumentException {
		kMapper.delete(k);
	}

	// delete Like

	public void deleteLike(Like l) throws IllegalArgumentException {
		lMapper.delete(l);
	}

	// delete Beitrag

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

	// ---- update f�r Ausgew�hlte ---- (user, beitrag, kommentar)
	// update User
	public void save(User u) throws IllegalArgumentException {
		uMapper.update(u);
	}

	// update Beitrag
	public void save(Beitrag b) throws IllegalArgumentException {
		bMapper.update(b);
	}

	// update Kommentar
	public void save(Kommentar k) throws IllegalArgumentException {
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

	public User findUserbyEmail(String email) {
		String emailaddr = this.email;

		return uMapper.findByEmail(emailaddr);

	}

	public Vector<Abo> getAboBySourceUser(int sourceUserID)
			throws IllegalArgumentException {
		
		Pinnwand p = new Pinnwand();
		p = this.pMapper.findBySourceUser(sourceUserID);

		return this.aMapper.findBySourcePinnwand(p.getId());

	}

	/**
	 * Erstelle einen String welches durch den Report als HTML repräsentiert
	 * 
	 * @param datumVon
	 *            definiert den Anfang der Suchanfrage
	 * @param datumBis
	 *            definiert das Ende der Suchanfrage
	 * @return der fertige HTML-Report
	 */
	public String createBeitragReport(String datumVon, String datumBis)
			throws IllegalArgumentException {

		/*if (this.getPinnwandVerwaltung() == null)
			return null;
*/
		/**
		 * Zunächst legen wir uns einen leeren Report an.
		 */
		BeitragReport result = new BeitragReport();

		// Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		result.setTitel("Alle Beiträge");

		/**
		 * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		 * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		 */
		result.setDate(new Date());

		/**
		 * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die
		 * oben auf dem Report stehen) des Reports. Die Kopfdaten sind
		 * mehrzeilig, daher die Verwendung von CompositeParagraph.
		 */
		CompositeParagraph header = new CompositeParagraph();

		/**
		 * Zeitraum der Report Suchanfrage in die Kopfzeile hinzufügen
		 */
		header.addSubParagraph(new SimpleParagraph("Zeitraum Von: " + datumVon
				+ " Bis: " + datumBis));

		// Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		result.setKopfdaten(header);

		/**
		 * Zunächst legen wir eine Kopfzeile für die Nutzer-Tabelle an.
		 */
		Zeile headline = new Zeile();

		/**
		 * Wir wollen Zeilen mit 4 Spalten in der Tabelle erzeugen. In die erste
		 * Spalte schreiben wir die jeweilige Kontonummer und in die zweite den
		 * aktuellen Kontostand. In der Kopfzeile legen wir also entsprechende
		 * Überschriften ab.
		 */
		headline.addSpalte(new Spalte("Nutzer"));
		headline.addSpalte(new Spalte("Inhalt"));
		headline.addSpalte(new Spalte("Like-Anzahl"));
		headline.addSpalte(new Spalte("Kommentaranzahl"));

		// Hinzufügen der Kopfzeile
		result.addZeile(headline);

		/**
		 * Nun werden sämtliche Beiträge innerhalb eines Zeitraumes ausgelesen
		 * und Autor, Inhalt, Likes und die Anzahl der Kommentare in die Tabelle
		 * eingetragen.
		 */
		ArrayList<Beitrag> beitraege = BeitragMapper.beitragMapper()
				.getBeitraegeBetweenTwoDates(datumVon, datumBis);

		beitraege = sort(beitraege);

		for (Beitrag beitrag : beitraege) {
			// Eine leere Zeile anlegen.
			Zeile accountRow = new Zeile();

			// Erste Spalte: Nachname und Vorname hinzufügen
			// TODO AKTUELL ID --> muss angepasst werden
			int sourceUser = beitrag.getSourceUserID();
			User u = getUserById(sourceUser);
			
			accountRow.addSpalte(new Spalte(u.getNickname() + " "));

			// Zweite Spalte: Inhalt hinzufügen

			accountRow.addSpalte(new Spalte(beitrag.getBeitrag()));

			// Dritte Spalte: Like-Anzahl hinzufügen
			accountRow.addSpalte(new Spalte(String.valueOf(beitrag
					.getLikeList().size())));

			// Dritte Spalte: Kommentar-Anzahl hinzufügen
			accountRow.addSpalte(new Spalte(String.valueOf(beitrag
					.getKommentarListe().size())));

			// und schließlich die Zeile dem Report hinzufügen.
			result.addZeile(accountRow);
		}

		/**
		 * Übergebe den erstellten Report dem HTMLReportWriter um HTML zu
		 * erzeugen
		 */
		HTMLReportWriter writer = new HTMLReportWriter();
		writer.process(result);

		/**
		 * Zum Schluss müssen wir noch den fertigen HTML-Report zurückgeben.
		 */
		return writer.getReportText();
	}
	public String createUserReport(User u, String datumVon, String datumBis) throws IllegalArgumentException {
		
		
		    /**
		     * Zunächst legen wir uns einen leeren Report an.
		     */
		    UserReport result = new UserReport();

		    // Jeder Report hat einen Titel (Bezeichnung / Überschrift).
		    result.setTitel("Informationen über " + u.getVorname() + " " + u.getNachname());


		    /**
		     * Datum der Erstellung hinzufügen. new Date() erzeugt autom. einen
		     * "Timestamp" des Zeitpunkts der Instantiierung des Date-Objekts.
		     */
		    result.setDate(new Date());

		    /**
		     * Ab hier erfolgt die Zusammenstellung der Kopfdaten (die Dinge, die oben
		     * auf dem Report stehen) des Reports. Die Kopfdaten sind mehrzeilig, daher
		     * die Verwendung von CompositeParagraph.
		     */
		    CompositeParagraph header = new CompositeParagraph();

		    // Name und Vorname des Nutzers aufnehmen
		    header.addSubParagraph(new SimpleParagraph(u.getNachname() + ", "
		        + u.getVorname()));

		    // Email-Adresse aufnehmen
		    header.addSubParagraph(new SimpleParagraph("Email:" + u.getEmail()));

		    // Zeitraum aufnehmen
		    header.addSubParagraph(new SimpleParagraph("Zeitraum Von: " + datumVon + " Bis: "
			        + datumBis));

		    // Hinzufügen der zusammengestellten Kopfdaten zu dem Report
		    result.setKopfdaten(header);

		    /**
		     * Zunächst legen wir eine Kopfzeile für die Konto-Tabelle an.
		     */
		    Zeile headline = new Zeile();

		    /**
		     * Wir wollen Zeilen mit 4 Spalten in der Tabelle erzeugen. In die erste
		     * Spalte schreiben wir die jeweilige Kontonummer und in die zweite den
		     * aktuellen Kontostand. In der Kopfzeile legen wir also entsprechende
		     * Überschriften ab.
		     */
		    headline.addSpalte(new Spalte("Abonnentenanzahl"));
		    headline.addSpalte(new Spalte("Beitragsanzahl"));
		    headline.addSpalte(new Spalte("Likes bekommen"));
		    headline.addSpalte(new Spalte("Likes gegeben"));

		    // Hinzufügen der Kopfzeile
		    result.addZeile(headline);   

		    Zeile accountRow = new Zeile();

		    /**
		     * Berechne die Abonnentenanzahl
		     */
		   
		    ArrayList<Abo> aboListe = AboMapper.aboMapper().getAboBetweenTwoDates(datumVon, datumBis, u);  
		    if (aboListe != null){
		    	accountRow.addSpalte(new Spalte(String.valueOf(aboListe.size())));
		    }else{
		    	accountRow.addSpalte(new Spalte("0"));
		    }
		    
		    /**
		     * Berechne die Beitragsanzahl
		     */
		    ArrayList<Beitrag> beitragListe = BeitragMapper.beitragMapper().getBeiträgeBetweenTwoDates(datumVon, datumBis, 
		    		PinnwandMapper.pinnwandMapper().getPinnwandByNutzer(u).getId());
		    if (beitragListe != null){
		    	accountRow.addSpalte(new Spalte(String.valueOf(beitragListe.size())));
		    }else{
		    	accountRow.addSpalte(new Spalte("0"));
		    }

		    /**
		     * Berechne die Like Anzahl
		     */
		    int likeAnzahl = 0;
		    if (beitragListe != null){
			    for (Beitrag beitrag : beitragListe) {
				    if(beitrag.getLikeList() != null)
				    likeAnzahl += beitrag.getLikeList().size();
			    }
		    }
		    accountRow.addSpalte(new Spalte(String.valueOf(likeAnzahl)));

		    /**
		     * Berechne die gegeben Likes
		     */
		    accountRow.addSpalte(new Spalte(String.valueOf(LikeMapper.likeMapper().getLikeCountByNutzer(u, datumVon, datumBis))));

		    // und schließlich die Zeile dem Report hinzufügen.
		    result.addZeile(accountRow);
		    //F5 ist in Methode rein und F6 ist weiter
		    HTMLReportWriter writer = new HTMLReportWriter();
		    writer.process(result);
		    /*
		     * Zum Schluss müssen wir noch den fertigen Report zurückgeben.
		     */
		    return writer.getReportText();
	}
	private ArrayList<Beitrag> sort(ArrayList<Beitrag> array) {
		int n = array.size();
		for(int i = n-1; i >= 0; i--) {
			for (int j = 1; j <= i; j++) {
				if ( array.get(j-1).getLikeList().size() < array.get(j).getLikeList().size()) {
				Beitrag temp = array.get(j-1);
	        	array.set(j-1, array.get(j));
	        	array.set(j, temp);

				}
			}
		}
		return array;
	}

}
