package de.hdm.socialmediaprojekt.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class Showcase extends VerticalPanel {

	  /**
	   * Jedes GWT Widget muss diese Methode implementieren. Sie gibt an, sas
	   * geschehen soll, wenn eine Widget-Instanz zur Anzeige gebracht wird.
	   */
	  public void onLoad() {
	    /*
	     * Bevor wir unsere eigene Formatierung veranslassen, überlassen wir es der
	     * Superklasse eine Initialisierung vorzunehmen.
	     */
	    super.onLoad();

	    /*
	     * Als erstes geben wir stets die Headline des Showcase aus. Da
	     * getHeadlineText() als abstrakte Methode bzw. als Einschubmethode
	     * realisiert wird, obliegt es den Subklassen, für eine Ausgestaltung also
	     * Implementierung zu sorgen.
	     */
	    this.add(this.createHeadline(this.getHeadlineText()));

	    /*
	     * Wenn alles vorbereitet ist, stoßen wir die run()-Methode an. Auch run()
	     * ist als abstrakte Methode bzw. als Einschubmethode realisiert. Auch hier
	     * ist es Aufgabe der Subklassen, für deren Implementierung zu sorgen.
	     */
	    this.run();
	  }

	  /**
	   * Mit Hilfe dieser Methode erstellen wir aus einem String ein mittels CSS
	   * formatierbares HTML-Element. Unter CSS lässt sich das Ergebnis über
	   * <code>.bankproject-headline</code> referenzieren bzw. formatieren.
	   * 
	   * @param text der String, den wir als andernorts HTML setzen wollen.
	   * @return GWT HTML Widget.
	   */
	  protected HTML createHeadline(String text) {
	    HTML content = new HTML(text);
	    content.setStylePrimaryName("bankproject-headline");
	    return content;
	  }

	  /**
	   * Mit Hilfe dieser Methode erstellen wir aus einem Strinng ein mittels CSS
	   * formatierbares HTML-Element, das an das Ende der bisherigen Ausgabe dieses
	   * Showcase angehängt wird. Unter CSS lässt sich das Ergebnis über
	   * <code>.bankproject-simpletext</code> referenzieren bzw. formatieren.
	   * 
	   * @param text der String, den wir als HTML an die bisherige Showcase-Ausgabe
	   *          anhängen wollen.
	   */
	  public void append(String text) {
	    HTML content = new HTML(text);
	    content.setStylePrimaryName("bankproject-simpletext");
	    this.add(content);
	  }

	  /**
	   * Abstrakte Einschubmethode, die in den Subklassen zu realisieren ist.
	   * @return der Text, den wir als Headline setzen wollen. 
	   */
	  protected abstract String getHeadlineText();

	  /**
	   * Abstrakte Einschubmethode, die in den Subklassen zu realisieren ist.
	   */
	  protected abstract void run();
	}
