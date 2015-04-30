package Basisklassen;

import java.io.Serializable;

/**
 * Klasse Standardfeld erbt von Spielfeld - ueber diese Klasse werden die 40
 * Standardfelder von MADN erstellt. Jedes Standardfeld hat eine ID ueber die
 * identifizierbar ist - diese liegt im Wertebereich 1-40
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 4.0
 * @since v1.0
 */

public class Standardfeld extends Spielfeld implements Serializable {

	private static final long serialVersionUID = 1L;

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
		String idString=iD+"";
		setID(idString);
		setGuiID(idString);
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
}
