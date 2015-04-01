package Basisklassen;

import java.io.Serializable;

/**
 * Klasse Startfeld erbt von Spielfeld. Ueber diese Klasse werden die Startfelder
 * von MADN erstellt. Auf diesen stehen die Figuren bevor Sie auf das
 * eigentliche Spielbrett treten. Die Klasse hat die Attribute iD ueber die jedes
 * Feld identifizierbar ist sowie eine Farbe.
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 3.0
 * @since 2015-03-16
 */

public class Startfeld extends Spielfeld implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String iD;

	private FarbEnum farbe;

	/**
	 * Konstruktor fuer ein Startfeld. Ueber das Attribut iD wird dem Feld eine
	 * genaue ID zugewiesen ueber die das Feld in Kombination mit seiner Farbe
	 * identifizierbar ist. Mit dem Attribut Farbe wird dem Feld eine Farbe
	 * zugewiesen ueber die das Feld im Spiel einem Spieler zugewiesen wird!
	 * Liegt die Eingabe der ID nicht im Wertebereich S1-S4 wird eine
	 * RuntimeException geworfen!
	 * 
	 * @param farbe - Farbe vom Typ FarbEnum des Feldes
	 * @param iD - ID vom Typ int des Feldes
	 * @exception RuntimeException  Muss im Wertebereich S1-S4 liegen
	 * 
	 */
	public Startfeld(String iD, FarbEnum farbe) {
		super();
		if (iD.contains("S1") || iD.contains("S2") || iD.contains("S3")
				|| iD.contains("S4")) {
			this.iD = iD;
		} else {
			throw new RuntimeException("Muss S1-S4 sein!");
		}

		this.farbe = farbe;
	}

	/**
	 * Oeffentlicher Getter der ID des Feldes zurueckgibt
	 * 
	 * @return iD - die ID des Feldes
	 */
	public String getID() {
		return this.iD;
	}

	/**
	 * Oeffentlicher Getter der Farbe des Feldes zurueckgibt.
	 * 
	 * @return FarbEnum farbe - Farbe des Feldes
	 */
	public FarbEnum getFarbe() {
		return this.farbe;
	}

	/**
	 * Ueberschreiben der Equals. Zwei Objekte der Klasse Startfeld sind nur
	 * dann gleich, wenn ihre Ihre ID und ihre Farbe die gleichen sind.
	 * 
	 * @param obj - Uebergebenes Startfeld-Objekt vom Typ Object - wird mit this verglichen
	 * @return boolean - gibt zurueck ob zwei verglichene Startfelder gleich sind
	 */
	@Override
	public boolean equals(Object obj) {
		Startfeld f = null;
		if (obj instanceof Startfeld)
			f = (Startfeld) obj;
		return f.getID() == this.getID()
				&& f.getFarbe().equals(this.getFarbe());
	}

	/**
	 * Ueberschriebene toString angepasst fuer die Klasse Standardfeld. Wandelt
	 * die Farbe und die ID in einen String und gibt diesen aus.
	 * 
	 * @return String - gibt String mit Inhalt Farbe und ID des Feldes zurueck
	 */
	@Override
	public String toString() {
		return String.valueOf(this.getFarbe()) + " " + this.getID();
	}

}