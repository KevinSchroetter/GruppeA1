package Basisklassen;

/**
 * Klasse Endfeld erbt von Spielfeld. Über diese Klasse werden die Endfelder
 * von MADN erstellt. Auf diesen sammeln sich die Figuren um das Ende des Spiels
 * einzuleiten. Die Klasse hat die Attribute iD über die jedes Feld
 * identifizierbar ist sowie eine Farbe.
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 1.3
 * @since 2015-03-16
 */

public class Endfeld extends Spielfeld {
	/**
	 * FeldID dient der Zurordnung und Identifizierung des Feldes "E1-E4"
	 */
	private String iD;
	/**
	 * Farbe des Feldes über das es einem Spieler zugeordnet wird
	 */
	private FarbEnum farbe;

	/**
	 * Konstruktor für ein Endfeld. Über das Attribut iD wird dem Feld eine
	 * genaue ID zugewiesen über die das Feld in Kombination mit seiner Farbe
	 * identifizierbar ist. Mit dem Attribut Farbe wird dem Feld eine Farbe
	 * zugewiesen über die das Feld im Spiel einem Spieler zugewiesen wird!
	 * Liegt die Eingabe der ID nicht im Wertebereich E1-E4 wird eine
	 * RuntimeException geworfen!
	 * 
	 * @param FarbEnum farbe - Farbe des Feldes
	 * @param String iD - ID des Feldes
	 * @exception RuntimeException Wenn Feld nicht im Wertebereich E1-E4
	 * 
	 */
	public Endfeld(String iD, FarbEnum farbe) {
		super();
		if (iD.contains("E1") || iD.contains("E2") || iD.contains("E3")
				|| iD.contains("E4")) {
			this.iD = iD;
		} else {
			throw new RuntimeException("Muss E1-E4 sein!");
		}
		this.farbe = farbe;
	}

	/**
	 * Öffentlicher Getter der die FeldID zurückgibt.
	 * 
	 * @return int iD - die ID des Feldes
	 */
	public String getID() {
		return this.iD;
	}

	/**
	 * Öffentlicher Getter der die Farbe des Feldes zurückgibt
	 * 
	 * @return FarbEnum farbe - Farbe des Feldes
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * Überschreiben der Equals. Zwei Objekte der Klasse Endfeld sind nur
	 * dann gleich, wenn ihre Ihre ID und ihre Farbe die gleichen sind.
	 * 
	 * @param Object obj - Übergebenes Endfeld-Objekt - wird mit this verglichen
	 * @return boolean - gibt zurück ob zwei verglichene Endfelder gleich sind
	 */
	@Override
	public boolean equals(Object obj) {
		Endfeld f = null;
		if (obj instanceof Endfeld)
			f = (Endfeld) obj;
		return f.getID() == this.getID()
				&& f.getFarbe().equals(this.getFarbe());
	}

	/**
	 * Überschriebene toString angepasst für die Klasse Endfeld. Wandelt die
	 * Farbe und die ID in einen String und gibt diesen aus.
	 * 
	 * @return String - gibt String mit Inhalt Farbe und ID des Feldes zurück
	 */
	@Override
	public String toString() {
		return String.valueOf(this.getFarbe()) + " " + this.getID();
	}

}
