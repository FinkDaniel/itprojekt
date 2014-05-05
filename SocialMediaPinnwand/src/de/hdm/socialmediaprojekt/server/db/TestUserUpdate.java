package de.hdm.socialmediaprojekt.server.db;

import de.hdm.socialmediaprojekt.shared.smo.*;


public class TestUserUpdate {

public static void main(String[] args){
	
		User u = new User();
		u.setId(3);
		u.setVorname("Patrick");
		u.setNachname("Prell");
		u.setNickname("Preller");
		UserMapper.userMapper().update(u);
		
	}

}
