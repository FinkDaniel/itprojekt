package de.hdm.socialmediaprojekt.client.gui;



import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Beitrag;
import de.hdm.socialmediaprojekt.shared.smo.User;

public class PinnwandView extends ScrollPanel{
	
	
	
	VerticalPanel pinnwandView = new VerticalPanel();
	
	public PinnwandView() {
		
		this.clear();
		this.getElement().setId("pinnwand");
		this.setAlwaysShowScrollBars(true);
		
		
		
		
		createPinnwand();
		this.add(pinnwandView);
		
		
		
		
		
		
	}
	
	

	private VerticalPanel createPinnwand(){
		
		pinnwandView.getElement().setId("pinnwandView");
		
		
		final PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();

		pinnwandVerwaltung.getBeitragBySourceUser(SocialMediaProjekt.getAktuellerNutzer().getId(), new AsyncCallback <Vector<Beitrag>>(){


				public void onFailure(Throwable caught) {
					pinnwandView.add(new Label("Blubb"));

				}


				public void onSuccess(Vector<Beitrag> result) {


					//System.out.print(result);
					/*
					for(int i=0;i<result.size();i++){
						BeitragCell beitragCell = new BeitragCell();
						beitragCell.addButtons();
						beitragCell.setText(result.get(i).getBeitrag().toString());
					*/
					//BeitragCell[] beitragcell = new BeitragCell[result.size()];
					
					//BeitragCell[] beitragcell = new BeitragCell[result.size()];
					//beitragcell[result.size()] = new BeitragCell();
					BeitragCell[] beitragCell = new BeitragCell[2];
					beitragCell[1].setText("test");
					
					System.out.print(result.size());
					
					
					//beitragcell[3].setText(result.get(3).getBeitrag());
					pinnwandView.add(beitragCell[1]);
					
					
					/*
					for(int i=0; i<=result.size(); i++){
						//labelcell[i].addButtons();
						labelcell[i].setText("Text Nummer");
						//beitragcell[i].setText(result.get(i).getBeitrag());
						pinnwandView.add(labelcell[i]);
						//pinnwandView.add(beitragcell[i]);
					}
					*/
					
					//}
				//}});
				}
				
	});
		return pinnwandView;

}
}
