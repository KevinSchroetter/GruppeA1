package Basisklassen;

/**
 * Dies ist die abstrakte Klasse Spielfeld für das MADN-Spiel. Die Klasse
 * beinhaltet die Elementarklassen Startfeld, Standardfeld und Zielfeld. Die
 * Klasse Spielfeld besitzt das Attribut figur, welches Sie an ihre
 * Elementarklassen vererbt.
 * 
 * @author Felix Rosa
 * @version 1.3
 */

public abstract class Spielfeld {

	private Spielfigur figur;

	/**
	 * Konstruktor der es ermöglicht ein Objekt des Typs Spielfeld zu erstellen.
	 * Das Spielfeld besitzt ein Attribut des Typs figur.
	 * */
	public Spielfeld() {
	}

	/**
	 * Methode welche die Figur zurückgibt, die sich auf dem Spielfeld befindet
	 * 
	 * @return figur
	 * */
	public Spielfigur getFigur() {
		return figur;
	}

	/**
	 * Methode über die ein Objekt des Typs figur auf das Feld gesetzt werden
	 * kann.
	 * 
	 * @param figur
	 * */
	protected void setFigur(Spielfigur figur) {
		if (this.getFigur() == null) {
			this.figur = figur;
		} else {
			throw new RuntimeException("Feld belegt!");
		}
	}

}
