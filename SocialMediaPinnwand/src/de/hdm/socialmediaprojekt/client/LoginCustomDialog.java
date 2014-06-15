package de.hdm.socialmediaprojekt.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Der LoginDialog ist ein DialogBox, die sich öffnet falls der aktuelle Nutzer
 * noch nicht in der Datenbank eingetragen ist. Sie gibt dem Nutzer die
 * Möglichkeit die gewünschten Daten einzutragen.
 * 
 * @author Team GUI
 * 
 */

@SuppressWarnings("deprecation")
public class LoginCustomDialog extends DialogBox implements ClickListener {
	private String vorname, nachname, nickname;
	private TextBox nachnameTextBox, vornameTextBox, nicknameTextBox;

	/**
	 * Erzeugt die Dialogbox mit einem Label, dass zum eintragen der Daten
	 * auffordert, drei Textboxen für Vorname, Nachname und Nickname und einen
	 * Button zum anmelden des Nutzers auf der Pinnwand.
	 * 
	 * @param googleNutzer
	 */
	public LoginCustomDialog(String nickname) {
		setText("Herzlich Willkommen");
		Button abonnierenButton = new Button("Erstelle einen Account", this);
		HTML msg = new HTML("<center>Bitte registrieren Sie sich</center>",
				true);

		HTML vornameLabel = new HTML("Vorname");
		vornameTextBox = new TextBox();
		vornameTextBox.setTitle("Vorname");

		HTML nicknameLabel = new HTML("Nickname");
		nicknameTextBox = new TextBox();
		nicknameTextBox.setTitle("Nickname");
		nicknameTextBox.setText(nickname);

		HTML nachnameLabel = new HTML("Nachname");
		nachnameTextBox = new TextBox();
		nachnameTextBox.setTitle("Vorname");

		DockPanel dock = new DockPanel();
		dock.setSpacing(4);
		dock.add(abonnierenButton, DockPanel.SOUTH);
		dock.add(msg, DockPanel.NORTH);
		dock.add(vornameLabel, DockPanel.NORTH);
		dock.add(vornameTextBox, DockPanel.NORTH);
		dock.add(nachnameLabel, DockPanel.NORTH);
		dock.add(nachnameTextBox, DockPanel.NORTH);
		dock.add(nicknameLabel, DockPanel.NORTH);
		dock.add(nicknameTextBox, DockPanel.NORTH);
		dock.setCellHorizontalAlignment(abonnierenButton, DockPanel.ALIGN_RIGHT);
		dock.setWidth("100%");
		setWidget(dock);
	}

	public void onClick(Widget sender) {
		nachname = nachnameTextBox.getText();
		vorname = vornameTextBox.getText();
		nickname = nicknameTextBox.getText();
		hide();
	}

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public String getNickname() {
		return nickname;
	}
}
