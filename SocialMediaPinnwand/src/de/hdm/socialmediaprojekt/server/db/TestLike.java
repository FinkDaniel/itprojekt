package de.hdm.socialmediaprojekt.server.db;

import de.hdm.socialmediaprojekt.shared.smo.*;

public class TestLike {

	public static void main(String[] args) {
		Like l = new Like();
		l.setSourceUserID(2);
		l.setTargetBeitragID(1);
		LikeMapper.likeMapper().insert(l);
		
		
		
	}

}
