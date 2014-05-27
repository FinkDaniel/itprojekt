package de.hdm.socialmediaprojekt.server.db;

import de.hdm.socialmediaprojekt.shared.smo.Beitrag;

public class TestBeitrag {

	public static void main(String[] args) {

		Beitrag b = new Beitrag();
		b.setBeitrag("Der Wasen ist er�ffnet!");
		b.setSourceUserID(2);
		BeitragMapper.beitragMapper().insert(b);
	}

}
