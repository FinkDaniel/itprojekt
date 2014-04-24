package de.hdm.socialmediaprojekt.server;


import de.hdm.socialmediaprojekt.shared.smo.User;
import de.hdm.socialmediaprojekt.server.UserMapper;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;

public class TestUser {

	
	
	public static void main(String[] args){
	
		
		User u = new User();
		u.setVorname("'Patrick'");
		u.setNachname("'Prell'");
		u.setNickname("'Preller'");
		UserMapper.userMapper().update(u);
		
		Pinnwand p = new Pinnwand();
		p.getOwnerID();
		PinnwandMapper.pinnwandMapper().insert(p);
			
		
}
			
	}