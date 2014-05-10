package de.hdm.socialmediaprojekt.newserver;

import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.socialmediaprojekt.server.db.*;
import de.hdm.socialmediaprojekt.shared.*;
import de.hdm.socialmediaprojekt.shared.smo.*;

public class SocialMediaPiwanndVerwaltungImpl extends RemoteServiceServlet implements PinnwandVerwaltung {

	
	private static final long serialVersionUID = 1L;
	
	private UserMapper uMapper = null;
	private BeitragMapper bMapper = null;
	private PinnwandMapper pMapper = null;
	private KommentarMapper kMapper = null;
	private AboMapper aMapper = null;
	private LikeMapper lMapper = null;
	
	public User createUser(String vorname, String nachname, String nickname) throws IllegalArgumentException {
		
		User u = new User();
		u.setVorname(vorname);
		u.setNachname(nachname);
		u.setNickname(nickname);
		u.setId(1);
		
		return this.uMapper.insert(u);
		
	}
	public SocialMediaPiwanndVerwaltungImpl() throws IllegalArgumentException{
		
	}
	
	public void init() throws IllegalArgumentException {
		this.uMapper = UserMapper.userMapper();
		this.bMapper = BeitragMapper.beitragMapper();
		this.pMapper = PinnwandMapper.pinnwandMapper();
		this.kMapper = KommentarMapper.kommentarMapper();
		this.aMapper = AboMapper.aboMapper();
		this.lMapper = LikeMapper.likeMapper();
		
		
		
	}
	public void save(User u) throws IllegalArgumentException {
		uMapper.update(u);
	}
	
	public Vector<User> getUserByNachname(String nachname) throws IllegalArgumentException{
		
	return this.uMapper.findByNachname(nachname);
	}
	// getbySourceUser Methoden
	
	public Vector<Kommentar> getKommentarBySourceUser(int sourceId) throws IllegalArgumentException{
		return this.kMapper.findBySourceUser(sourceId);
	}
	public Vector<Pinnwand> getPinnwandBySourceUser(int sourceId) throws IllegalArgumentException{
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
	
	
	
	

	
	// "Find" by ID -Methoden für Alle SMOs
	
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
	
	// "FIND ALL"- Methoden für alle SMOs
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
	
	
}

