	package de.hdm.socialmediaprojekt.client.gui;

	import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


	public class UserSuche extends Composite {

		public UserSuche (){
			initWidget(userSuche);
			//erstelleUserSuche();

		}

		VerticalPanel userSuche = new VerticalPanel();
	

		public void erstelleUserSuche(){

			TextBox textBox = new TextBox();
			textBox.getElement().setId("textBox");
			userSuche.addStyleName("userSuche");
			userSuche.add(textBox);
			Button userSucheButton = new Button("Nutzer suchen");
			userSucheButton.getElement().setId("LogButton");
			userSuche.add(userSucheButton);
		} 

	}

