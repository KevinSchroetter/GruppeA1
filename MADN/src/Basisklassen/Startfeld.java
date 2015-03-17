package Basisklassen;

/**
 * Klasse Startfeld erbt von Spielfeld. Über diese Klasse werden die Startfelder
 * von MADN erstellt. Auf diesen stehen die Figuren bevor Sie auf das
 * eigentliche Spielbrett treten. Die Klasse hat die Attribute iD über die jedes
 * Feld identifizierbar ist sowie eine Farbe.
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 1.3
 * @since 2015-03-16
 * */

public class Startfeld extends Spielfeld {
	/**
	 * FeldID dient der Zurordnung und Identifizierung des Feldes "E1-E4"
	 * */
	private String iD;
	/**
	 * Farbe des Feldes über das es einem Spieler zugeordnet wird
	 * */
	private FarbEnum farbe;

	/**
	 * Konstruktor für ein Startfeld. Über das Attribut iD wird dem Feld eine
	 * genaue ID zugewiesen über die das Feld in Kombination mit seiner Farbe
	 * identifizierbar ist. Mit dem Attribut Farbe wird dem Feld eine Farbe
	 * zugewiesen über die das Feld im Spiel einem Spieler zugewiesen wird!
	 * Liegt die Eingabe der ID nicht im Wertebereich S1-S4 wird eine
	 * RuntimeException geworfen!
	 * 
	 * @param FarbEnum
	 *            farbe - Farbe des Feldes
	 * @param String
	 *            iD - ID des Feldes
	 * @exception RuntimeException
	 *                Muss im Wertebereich S1-S4 liegen
	 * 
	 * */
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
	 * Öffentlicher Getter der ID des Feldes zurückgibt
	 * 
	 * @return iD - die ID des Feldes
	 * */
	public String getID() {
		return this.iD;
	}

	/**
	 * Öffentlicher Getter der Farbe des Feldes zurückgibt.
	 * 
	 * @return FarbEnum farbe - Farbe des Feldes
	 * */
	public FarbEnum getFarbe() {
		return this.farbe;
	}

	/**
	 * Überschreiben der Equals. Zwei Objekte der Klasse Startfeld sind nur
	 * dann gleich, wenn ihre Ihre ID und ihre Farbe die gleichen sind.
	 * 
	 * @param Object
	 *            obj - Übergebenes Startfeld-Objekt - wird mit this verglichen
	 * @return boolean - gibt zurück ob zwei verglichene Startfelder gleich sind
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
	 * Überschriebene toString angepasst für die Klasse Standardfeld. Wandelt
	 * die Farbe und die ID in einen String und gibt diesen aus.
	 * 
	 * @return String - gibt String mit Inhalt Farbe und ID des Feldes zurück
	 * */
	@Override
	public String toString() {
		return String.valueOf(this.getFarbe()) + " " + this.getID();
	}

}