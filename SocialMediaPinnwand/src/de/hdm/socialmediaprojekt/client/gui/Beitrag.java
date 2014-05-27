package de.hdm.socialmediaprojekt.client.gui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;


public class Beitrag {

		private Button beitrag;
		private TextArea beitragText;
		private TextArea kommentarText;
		private Label kommentarLabel;
		private Label beitragLabel;
		private VerticalPanel beitragPanel; 
		private VerticalPanel kommentarPanel;
		private Button kommentar;
		private Button like;
		private Label dufte;
		int i = 0;
//		Test
		
	
	
	public void onModuleLoad() {	
		
				
		this.beitrag = new Button("Beitrag absenden");
		beitrag.addClickHandler(new BeitragClickHandler());
		this.beitragText = new TextArea();
		beitragText.setVisibleLines(5);
		this.beitragLabel = new Label();
		this.kommentarText =new TextArea();
		kommentarText.setVisibleLines(2);
		this.kommentarLabel = new Label();
		this.kommentar = new Button("kommentieren!");
		this.like = new Button("Dufte!");
		like.addClickHandler (new LikeClickHandler());
		this.dufte = new Label("Keiner findet das dufte!");
		
		
		
		
			
		VerticalPanel vPanel = new VerticalPanel();
		this.beitragPanel = new VerticalPanel();
		beitragPanel.setWidth("500px");
		this.kommentarPanel = new VerticalPanel();
			
		vPanel.add(beitragText);
		vPanel.add(beitrag);
		beitragPanel.add(beitragLabel);
		
		
		
			
		DockPanel dockNew = new DockPanel();
			
		dockNew.add(vPanel, DockPanel.WEST);
		dockNew.add(this.beitragPanel, DockPanel.EAST);
			
		RootPanel.get().add(dockNew);
		
		
		
		
		
		
	}

	 private class BeitragClickHandler implements ClickHandler{

		public void onClick(ClickEvent event) {
			//Text des Beitrages erhalten 
			String derBeitrag = beitragText.getText();
			
			
			//Beitragstext dem Beitragslabel zuweisen
			beitragLabel.setText(derBeitrag);
			
			
			//Rahmen des beitragPanel
			beitragPanel.setBorderWidth(5);
			
			//TextBox wieder leeren
			beitragText.setText("");
			
			
			HorizontalPanel likeUkommentieren = new HorizontalPanel();
			
			
			kommentar.addClickHandler(new KommentarClickHandler());
			
			VerticalPanel kommentarPanel = new VerticalPanel();
			
			kommentarPanel.add(kommentarText);
			kommentarPanel.add(kommentar);
			
			
			likeUkommentieren.add(kommentarPanel);
			likeUkommentieren.add(like);
			beitragPanel.add(dufte);
			kommentarText.setVisibleLines(3);
			beitragPanel.add(likeUkommentieren); 
			
			
		
			
			
			
			}
		 
	 }
	 private class KommentarClickHandler implements ClickHandler{

			public void onClick(ClickEvent event) {
				//Text des Beitrages erhalten
				String derKommentar = kommentarText.getText();
				
				kommentarLabel.setText(derKommentar);
				
				//Rahmen des beitragPanel
				kommentarPanel.setBorderWidth(5);
				
				//TextBox wieder leeren
				kommentarText.setText("");
				
				kommentarPanel.add(kommentarLabel);
				beitragPanel.add(kommentarPanel);
				beitragPanel.add(kommentarText);
				beitragPanel.add(kommentar);
				
				
				}
			 
		 }
	 private class LikeClickHandler implements ClickHandler{

			public void onClick(ClickEvent event) {
				
				i++;			
				if (i==1) {
				dufte.setText( i +" Person findet das dufte!");}
				else {dufte.setText( i +" Personen finden das dufte!");}
				
				
			}
	 }
} 
