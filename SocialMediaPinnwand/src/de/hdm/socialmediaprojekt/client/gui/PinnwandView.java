package de.hdm.socialmediaprojekt.client.gui;



import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.User;

public class PinnwandView extends ScrollPanel{
	
	
	
	HorizontalPanel pinnwandView = new HorizontalPanel();
	
	public PinnwandView() {
		
		this.clear();
		this.getElement().setId("pinnwand");
		this.setAlwaysShowScrollBars(true);
		
		
		
		
		createPinnwand();
		this.add(pinnwandView);
		
		
		
		
		
		
	}
	
	

	private HorizontalPanel createPinnwand(){
		
		pinnwandView.getElement().setId("pinnwandView");
		
		
		final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();

		pinnwandVerwaltung.getBeitragBySourceUser(1,new AsyncCallback <Vector<Beitrag>>(){


				public void onFailure(Throwable caught) {
					pinnwandView.add(new Label("Blubb"));

				}


				public void onSuccess(Vector<Beitrag> result) {


					//System.out.print(result);
					for(int i=0;i<result.size();i++){
						BeitragCell beitragCell = new BeitragCell();
						beitragCell.addButtons();
						beitragCell.setText(result.get(i).getBeitrag().toString());

					}
				}});
		return pinnwandView;
	}

}
