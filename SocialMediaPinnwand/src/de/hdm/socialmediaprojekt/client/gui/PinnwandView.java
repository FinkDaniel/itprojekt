package de.hdm.socialmediaprojekt.client.gui;



import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;








import de.hdm.socialmediaprojekt.client.ClientSideSettings;
import de.hdm.socialmediaprojekt.client.SocialMediaProjekt;
import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.Abo;
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
		
		pinnwandVerwaltung.getAboBySourcePinnwand(SocialMediaProjekt.getAktuellerNutzer().getId(), new AsyncCallback <Vector<Abo>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(Vector<Abo> result) {
				int[] abos = new int[result.size()+1];
				abos[0] = SocialMediaProjekt.getAktuellerNutzer().getId();
				
				for(int i=1; i<result.size(); i++){
					abos[i] = result.get(i).getTargetPinnwandID();
					//System.out.print(result.get(i).getTargetPinnwandID());
					System.out.print(abos[i]);
				}
				pinnwandVerwaltung.getBeitragBySourceUser(abos, new AsyncCallback <Vector<Beitrag>>(){



					public void onFailure(Throwable caught) {
						pinnwandView.add(new Label("Blubb"));

					}


					public void onSuccess(Vector<Beitrag> result) {
						Widget[] objectList = new Widget[result.size()];
						
						
										
						for(int i=0; i< result.size(); i++){
							BeitragCell beitragCell = new BeitragCell();
							beitragCell.clear();
							beitragCell.setText(result.get(i).getBeitrag());
							beitragCell.setErsteller(result.get(i).getSourceUserID());
							beitragCell.setErstellungszeitpunkt(result.get(i).getHour(), result.get(i).getMinute(), result.get(i).getDay(), result.get(i).getMonth(), result.get(i).getYear());
							beitragCell.addButtons();
							objectList[i] = beitragCell;
							pinnwandView.add(objectList[i]);

						}
						
						
						//}
					//}});
					}
					
		});
			

	
				
			}});
		

		return pinnwandView;
}}
