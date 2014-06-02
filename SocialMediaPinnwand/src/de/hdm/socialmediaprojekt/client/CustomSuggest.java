package de.hdm.socialmediaprojekt.client;

import java.io.Serializable;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import de.hdm.socialmediaprojekt.shared.smo.*;

/**
 * Custome Suggest um Nutzer Objekte anzuzeigen
 * 
 * @author Eric Schmidt
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
		// TODO Auto-generated method stub
		return null;
	} 

	public User getUser(){
		return this.value;
	}
}