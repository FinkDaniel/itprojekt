package de.hdm.socialmediaprojekt.server;


import de.hdm.socialmediaprojekt.shared.smo.User;
import de.hdm.socialmediaprojekt.server.UserMapper;
import de.hdm.socialmediaprojekt.shared.smo.Pinnwand;

public class TestUser {

	
	
	public static void main(String[] args){
	
		
		User u = new User();
		u.setVorname("'Test'");
		u.setNachname("'Peter'");
		u.setNickname("'Maier'");
		UserMapper.userMapper().insert(u);
		
		Pinnwand p = new Pinnwand();
		p.getOwnerID();
		PinnwandMapper.pinnwandMapper().insert(p);
			
		System.out.println("User-ID:"+ u.getId()
							+ "\nVorname:"+ u.getVorname()
							+ "\nNachname:"+ u.getNachname()
							+ "\nNickname:"+ u.getNickname()
							+ "\nPinnwandnummer: "+p.getId());
							
						
}
			
	}