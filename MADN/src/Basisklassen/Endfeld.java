package Basisklassen;

/**
 * Dies ist die Klasse Zielfeld für das MADN-Spiel. Sie erbt von der
 * Parent-Klasse Spielfeld. Über diese Klasse werden die Objekte des Typs
 * Zielfeld für MADN erstellt, in welchen sich die Spielfiguren einer Farbe
 * sammeln um das Spielziel zu erreichen Die Klasse hat die Attribute iD über
 * die jedes Feld identifizierbar ist sowie eine Farbe.
 * 
 * @author Felix Rosa
 * @version 1.3
 * */

public class Endfeld extends Spielfeld {
	private String iD;
	private FarbEnum farbe;

	/**
	 * Konstruktor der es ermöglicht ein Objekt des Typs Endfeld zu erstellen.
	 * Das Feld besitzt ein Attribut des Typs figur, welches es von der
	 * Superklasse Spielfeld erbt. Übergeben werden die farbe des Spielfeldes.
	 * 
	 * @param farbe
	 * 
	 * */
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
	 * Methode vom Typ int welche die ID des Feldes zurückgibt.
	 * 
	 * @return iD
	 * */
	public String getID() {
		return this.iD;
	}

	/**
	 * Methode welche die Farbe des Feldes zurückgibt.
	 * 
	 * @return farbe
	 * */
	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * Überschriebene equals Methode, welche ein übergebenes Objekt in ein
	 * Objekt des Typs Endfeld castet und dieses mit dem bestehenden Objekt
	 * vergleicht. Der Vergleich erfolgt über die ID.
	 * 
	 * @param Object
	 *            obj
	 * @return f.getID() == this.getID()
	 * */
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
	 * Farbe, die ID, die Figur und Letztes in einen String und gibt diesen aus.
	 * 
	 * @return String.valueOf(this.getFarbe()) + " " +
	 *         String.valueOf(this.getID()) + " " +
	 *         String.valueOf(this.getFigur()) + " " +
	 *         String.valueOf(this.isLetztes())
	 * */
	@Override
	public String toString() {
		return String.valueOf(this.getFarbe()) + " " + this.getID() + " "
				+ String.valueOf(this.getFigur());
	}

}
