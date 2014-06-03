package de.hdm.socialmediaprojekt.client;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.socialmediaprojekt.shared.PinnwandVerwaltungAsync;
import de.hdm.socialmediaprojekt.shared.smo.User;



public class FindeUserByNachname extends Showcase{


protected String getHeadlineText() {
return "Finde User nach Nachname";
}



protected void run() {
this.append("");

PinnwandVerwaltungAsync pinnwandVerwaltung = ClientSideSettings.getPinnwandVerwaltung();

pinnwandVerwaltung.getUserByNachname("", new UserAusgebenCallback (this));


}
class UserAusgebenCallback implements AsyncCallback<Vector<User>> {
private Showcase showcase = null;

public UserAusgebenCallback(Showcase u) {
this.showcase = u;
}

@Override
public void onFailure(Throwable caught) {
this.showcase.append("Fehler bei der Abfrage " + caught.getMessage());
}

@Override
public void onSuccess(Vector<User> result) {
if (result != null) {
for (User u : result) {
if (u != null) {
// Kundennummer und Name ausgeben
this.showcase.append("Kunde #" + u.getId() + ": " + u.getNachname()
+ ", " + u.getVorname());

}
}

if (result.size() == 1)
this.showcase.append("1 Element erhalten");
else
this.showcase.append(result.size() + " Elemente erhalten");

}
else {
ClientSideSettings.getLogger().info("result == null");
}
}

}


}