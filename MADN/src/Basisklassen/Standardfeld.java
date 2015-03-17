package Basisklassen;

/**
 * Klasse Standardfeld erbt von Spielfeld - über diese Klasse werden die 40
 * Standardfelder von MADN erstellt. Jedes Standardfeld hat eine ID über die
 * identifizierbar ist - diese liegt im Wertebereich 1-40
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 1.3
 * @since 2015-03-16
 */
public class Standardfeld extends Spielfeld {
	/**
	 * FeldID 1-40 dient der Identifizierung des Standardfeldes
	 */
	private int iD;

	/**
	 * Konstruktor für ein Standardfeld. Über das Attribut iD wird dem Feld
	 * eine genaue ID zugewiesen über die das Feld identifizierbar ist Liegt die
	 * Eingabe nicht innerhalb des Wertebereiches 1-40 wird eine
	 * RuntimeException geworfen.
	 * 
	 * @param int iD - die iD des Feldes
	 * @exception RuntimeException Wenn iD <1 o. >40
	 * 
	 */
	public Standardfeld(int iD) {
		super();
		if (iD >= 1 && iD <= 40) {
			this.iD = iD;
		} else {
			throw new RuntimeException("iD muss im Bereich 1-40 liegen!");
		}
	}

	/**
	 * Öffentlicher Getter der FeldID zurückgibt.
	 * 
	 * @return int iD - die ID des Feldes
	 */
	public int getID() {
		return this.iD;
	}

	/**
	 * Überschreiben der Equals. Zwei Objekte der Klasse Standardfeld sind nur dann gleich, wenn ihre Ihre ID die gleiche ist.
	 * 
	 * @param Object obj - Übergebenes Standardfeld-Objekt - wird mit this verglichen
	 * @return boolean - gibt zurück ob zwei verglichene Standardfelder gleich sind
	 */
	@Override
	public boolean equals(Object obj) {
		Standardfeld f = null;
		if (obj instanceof Standardfeld)
			f = (Standardfeld) obj;
		return f.getID() == this.getID();
	}

	/**
	 * Überschriebene toString angepasst für die Klasse Standardfeld. Wandelt
	 * die ID in einen String und gibt diesen aus.
	 * 
	 * @return String - gibt String mit Inhalt ID des Feldes zurück
	 */
	@Override
	public String toString() {
		return String.valueOf(this.getID());
	}

}
