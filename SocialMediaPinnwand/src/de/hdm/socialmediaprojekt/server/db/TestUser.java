package de.hdm.socialmediaprojekt.server.db;

import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;
import de.hdm.socialmediaprojekt.shared.smo.User;

public class TestUser {

	
	public static void main(String[] args){
	
		    User u = new User();
		    u.setVorname("sssss");
		    u.setNachname("FinksSchwester");
		    u.setNickname("ooooo");
		    u.setPassword("geil");
		    u.setId(1);
		    System.out.println(UserMapper.userMapper().insert(u));
		  
		    Pinnwand p = new Pinnwand();
		    p.setSourceUserID(u.getId());
		    p.setId(1);

		    System.out.println(PinnwandMapper.pinnwandMapper().insert(p));
		    System.out.println("User-ID:"+ u.getId()
					+ "\nVorname:"+ u.getVorname()
					+ "\nNachname:"+ u.getNachname()
					+ "\nNickname:"+ u.getNickname()
					+ "\nPinnwandnummer: "+p.getId()
		    		+ "\nPassword:"+ u.getPassword());
		  }
	
}
