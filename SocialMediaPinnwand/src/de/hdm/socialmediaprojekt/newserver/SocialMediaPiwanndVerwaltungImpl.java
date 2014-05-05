package de.hdm.socialmediaprojekt.newserver;

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
	
}
