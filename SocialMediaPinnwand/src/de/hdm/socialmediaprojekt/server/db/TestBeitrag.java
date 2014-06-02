package de.hdm.socialmediaprojekt.server.db;

import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.server.db.BeitragMapper;

public class TestBeitrag {

	public static void main(String[] args) {

		Beitrag b = new Beitrag();
		b.setBeitrag("Hallo Helene Fischer!!!");
		b.setSourceUserID(2);
		BeitragMapper.beitragMapper().insert(b);
	}

}
