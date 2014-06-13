package de.hdm.socialmediaprojekt.client.gui;


	import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.Kommentar;

	public class BeitragView extends VerticalPanel{

		Beitrag beitrag = new Beitrag();
		BeitragCell beitragCell = new BeitragCell();
		final VerticalPanel kommentarView = new VerticalPanel();
		final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();

		public BeitragView(Beitrag beitrag) {
			this.addStyleName("BeitragView");
			addBeitrag(beitrag);
			addKommentarList(beitrag);
		}



		public BeitragView addKommentarForm(Beitrag b){
			KommentarForm kommentarForm = new KommentarForm();
			this.add(kommentarForm);
			return this;
		}

		public void addBeitrag(Beitrag inhalt){
			beitragCell.clear();
			beitragCell.setText(inhalt.getBeitrag());
			beitragCell.setErsteller(inhalt.getSourceUserID());
			beitragCell.setErstellungszeitpunkt(inhalt.getHour(), inhalt.getMinute(), inhalt.getDay(), inhalt.getMonth(), inhalt.getYear());
			beitragCell.addButtons(inhalt);
			this.add(beitragCell);
		}
		public VerticalPanel addKommentarList(Beitrag beitrag){
			
			kommentarView.clear();
			pinnwandVerwaltung.getKommentarByTargetBeitrag(beitrag.getId(), new AsyncCallback <Vector<Kommentar>>(){
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Window.alert("Blubb");
				}

				@Override
				public void onSuccess(Vector<Kommentar> result) {
					
					if(result.size() != 0){
					Widget[] kommentList = new Widget[result.size()];
					
					for(int b=0; b<result.size(); b++){
					System.out.println(result.get(b).getKommentar());
					KommentarCell kommentarCell = new KommentarCell();
					kommentarCell.clear();
					kommentarCell.setText(result.get(b).getKommentar());
					kommentarCell.setErsteller(result.get(b).getSourceUserID());
					kommentarCell.addDeleteButton(result.get(b));
					kommentarCell.addEditButton(result.get(b));
					kommentList[b] = kommentarCell;
					kommentarView.add(kommentList[b]);
					
					}
					
					}
				}});
			this.add(kommentarView);
			return this;
		}
	}

