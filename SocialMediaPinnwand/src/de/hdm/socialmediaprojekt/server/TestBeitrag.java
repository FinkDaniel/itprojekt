package de.hdm.socialmediaprojekt.server;

import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.server.BeitragMapper;

public class TestBeitrag {

	public static void main(String[] args) {

		Beitrag b = new Beitrag();
		b.setBeitrag("Der Wasen ist eröffnet!");
		b.setSourceUserID(2);
		BeitragMapper.beitragMapper().insert(b);
	}

}
