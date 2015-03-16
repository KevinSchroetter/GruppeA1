package Basisklassen;

/**
 * Dies ist die Klasse Standardfeld für das MADN-Spiel. Sie erbt von der
 * Parent-Klasse Spielfeld. Über diese Klasse werden die Objekte des Typs
 * Standardfeld für MADN erstellt, welche die regulären Felder des Spiels bilden
 * Die Klasse hat die Attribute iD über die jedes Feld identifizierbar.
 * 
 * @author Felix Rosa
 * @version 1.3
 * */
public class Standardfeld extends Spielfeld {
	private int iD;

	/**
	 * Konstruktor der es ermöglicht ein Objekt des Typs Standardfeld zu
	 * erstellen. Das Feld besitzt ein Attribut des Typs figur, welches es von
	 * der Superklasse Spielfeld erbt.
	 * 
	 * */
	public Standardfeld(int iD) {
		super();
		if (iD >= 1 && iD <= 40) {
			this.iD = iD;
		} else {
			throw new RuntimeException("!FEHLER!");
		}
	}

	/**
	 * Methode vom Typ int welche die ID des Feldes zurückgibt.
	 * 
	 * @return iD
	 * */
	public int getID() {
		return this.iD;
	}

	/**
	 * Überschriebene equals Methode, welche ein übergebenes Objekt in ein
	 * Objekt des Typs Standardfeld castet und dieses mit bestehenden Objekt
	 * vergleicht. Der Vergleich erfolgt über die ID.
	 * 
	 * @param Object
	 *            obj
	 * @return f.getID() == this.getID()
	 * */
	@Override
	public boolean equals(Object obj) {
		Standardfeld f = null;
		if (obj instanceof Standardfeld)
			f = (Standardfeld) obj;
		return f.getID() == this.getID();
	}

	/**
	 * Überschriebene toString angepasst für die Klasse Standardfeld. Wandelt
	 * die ID und die Figur in einen String und gibt diesen aus.
	 * 
	 * @return String.valueOf(this.getID()) + " " +
	 *         String.valueOf(this.getFigur())
	 * */
	@Override
	public String toString() {
		return String.valueOf(this.getID());
	}

}
