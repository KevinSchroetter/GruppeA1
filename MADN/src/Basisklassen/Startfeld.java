package Basisklassen;

/**
 * Dies ist die Klasse Startfeld für das MADN-Spiel. Sie erbt von der
 * Parent-Klasse Spielfeld. Über diese Klasse werden die Objekte des Typs
 * Startfeld für MADN erstellt, von welcher die Spielfiguren auf dem Spielbrett
 * in das Spiel starten. Die Klasse hat die Attribute iD über die jedes Feld
 * identifizierbar ist sowie eine Farbe.
 * 
 * @author Felix Rosa
 * @version 1.3
 * */

public class Startfeld extends Spielfeld {
	private String iD;
	private FarbEnum farbe;

	/**
	 * Konstruktor der es ermöglicht ein Objekt des Typs Startfeld zu erstellen.
	 * Das Feld besitzt ein Attribut des Typs figur, welches es von der
	 * Superklasse Spielfeld erbt. Übergeben werden die Farbe des Feldes.
	 * 
	 * @param farbe
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
	 * @return iD
	 * */
	public String getID() {
		return this.iD;
	}

	/**
	 * Öffentlicher Getter der Farbe des Feldes zurückgibt.
	 * 
	 * @return FarbEnum farbe
	 * */
	public FarbEnum getFarbe() {
		return this.farbe;
	}

	/**
	 * Überschriebene equals Methode, welche ein übergebenes Objekt in ein
	 * Objekt des Typs Startfeld castet und dieses mit dem bestehenden Objekt
	 * vergleicht. Der Vergleich erfolgt über die ID.
	 * 
	 * @param Object
	 *            obj
	 * @return f.getID() == this.getID()
	 * */
	@Override
	public boolean equals(Object obj) {
		Startfeld f = null;
		if (obj instanceof Startfeld)
			f = (Startfeld) obj;
		return f.getID() == this.getID()
				&& f.getFarbe().equals(this.getFarbe());
	}

	/**
	 * Überschriebene toString angepasst für die Klasse Startfeld. Wandelt die
	 * Farbe, die ID und die Figur in einen String und gibt diesen aus.
	 * 
	 * @return String.valueOf(this.getFarbe()) + " " +
	 *         String.valueOf(this.getID()) + " " +
	 *         String.valueOf(this.getFigur())
	 * */
	@Override
	public String toString() {
		return String.valueOf(this.getFarbe()) + " " + this.getID();
	}

}