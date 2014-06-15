package de.hdm.socialmediaprojekt.client;

import java.io.Serializable;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import de.hdm.socialmediaprojekt.shared.smo.*;

/**
 * Anlegen und Auslesen des eingeloggten Users.
 * 
 * @author Team GUI
 * 
 */
public class CustomSuggest implements Suggestion, Serializable {

	private static final long serialVersionUID = 1L;
	private User value;

    public CustomSuggest(User value) {
        this.value = value;
    }

    @Override
    public String getDisplayString() {
        return value.getNachname() + ", " + value.getVorname();
    }

	@Override
	public String getReplacementString() {
		return null;
	} 

	public User getUser(){
		return this.value;
	}
}