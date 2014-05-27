package de.hdm.socialmediaprojekt.server.db;

import de.hdm.socialmediaprojekt.shared.smo.Kommentar;

public class TestKommentar {

	public static void main(String[] args) {
		Kommentar k = new Kommentar();
		k.setKommentar("Nein ist er nicht!!");
		k.setSourceUserID(4);
		k.setTargetBeitragID(4);
		KommentarMapper.kommentarMapper().insert(k);
	}

}
