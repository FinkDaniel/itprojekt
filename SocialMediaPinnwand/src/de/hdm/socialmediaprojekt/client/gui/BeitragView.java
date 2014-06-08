package de.hdm.socialmediaprojekt.client.gui;

import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.shared.smo.Beitrag;

public class BeitragView extends VerticalPanel{
	
	Beitrag beitrag = new Beitrag();
	BeitragCell beitragCell = new BeitragCell();
	
	public BeitragView() {
		this.addStyleName("beitragView");
	}
	
	
	
	public BeitragView addKommentarForm(){
		KommentarForm kommentarForm = new KommentarForm();
		this.add(kommentarForm);
		return this;
	}
	
	public void setBeitrag(Beitrag inhalt){
		
		
	}
	
}
