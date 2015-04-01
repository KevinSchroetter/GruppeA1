package Basisklassen;

import java.io.Serializable;

/**
 * Klasse Standardfeld erbt von Spielfeld - ueber diese Klasse werden die 40
 * Standardfelder von MADN erstellt. Jedes Standardfeld hat eine ID ueber die
 * identifizierbar ist - diese liegt im Wertebereich 1-40
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 3.0
 * @since 2015-03-16
 */
public class Standardfeld extends Spielfeld implements Serializable {

	private static final long serialVersionUID = 1L;
	private String iD;

	/**
	 * Konstruktor fuer ein Standardfeld. ueber das Attribut iD wird dem Feld
	 * eine genaue ID zugewiesen ueber die das Feld identifizierbar ist Liegt die
	 * Eingabe nicht innerhalb des Wertebereiches 1-40 wird eine
	 * RuntimeException geworfen.
	 * 
	 * @param iD - Die iD des Feldes vom Typ int
	 * @exception RuntimeException Wenn iD kleiner 1 oder groesser 40
	 * 
	 */
	public Standardfeld(int iD) {
		super();
		if (iD >= 1 && iD <= 40) {
			String idString=iD+"";
			this.iD = idString;
		} else {
			throw new RuntimeException("iD muss im Bereich 1-40 liegen!");
		}
	}

	/**
	 * Oeffentlicher Getter der FeldID zurueckgibt.
	 * 
	 * @return int iD - die ID des Feldes
	 */
	public String getID() {
		return this.iD;
	}

	/**
	 * Ueberschreiben der Equals. Zwei Objekte der Klasse Standardfeld sind nur dann gleich, wenn ihre Ihre ID die gleiche ist.
	 * 
	 * @param obj - Ubergebenes Standardfeld-Objekt vom Typ Object - wird mit this verglichen
	 * @return boolean - gibt zurueck ob zwei verglichene Standardfelder gleich sind
	 */
	@Override
	public boolean equals(Object obj) {
		Standardfeld f = null;
		if (obj instanceof Standardfeld)
			f = (Standardfeld) obj;
		return f.getID() == this.getID();
	}

	/**
	 * Ueberschriebene toString angepasst fuer die Klasse Standardfeld. Wandelt
	 * die ID in einen String und gibt diesen aus.
	 * 
	 * @return String - gibt String mit Inhalt ID des Feldes zurueck
	 */
	@Override
	public String toString() {
		return String.valueOf(this.getID());
	}

}
