package de.hdm.socialmediaprojekt.server;


import de.hdm.socialmediaprojekt.shared.smo.User;
import de.hdm.socialmediaprojekt.server.UserMapper;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;

public class TestUser {

	
	
	public static void main(String[] args){
	
		
		User a = new User();
		a.setVorname("'Daniel'");
		a.setNachname("'Fink'");
		a.setNickname("'FinkD'");
		UserMapper.userMapper().insert(a);
		
		Pinnwand b = new Pinnwand();
		b.getOwnerID();
		PinnwandMapper.pinnwandMapper().insert(b);
		
		
}
			
	}