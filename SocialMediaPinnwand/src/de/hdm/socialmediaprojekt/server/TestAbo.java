package de.hdm.socialmediaprojekt.server;

import de.hdm.socialmediaprojekt.shared.smo.Abo;

public class TestAbo {

	public static void main(String[] args) {
		Abo a = new Abo();
		a.setSourcePinnwandID(1);
		a.setTargetPinnwandID(2);
		a.getErstellungsdatum();
		AboMapper.aboMapper().insert(a);
	}

}
