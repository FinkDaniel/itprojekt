package de.hdm.socialmediaprojekt.server;

import de.hdm.socialmediaprojekt.shared.smo.*;

public class TestUser {

	
	public static void main(String[] args){
	
		    User u = new User();
		    u.setVorname("'Isabell'");
		    u.setNachname("'Feininger'");
		    u.setNickname("'Isabelle'");
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
					+ "\nPinnwandnummer: "+p.getId());
		  }
	
}
