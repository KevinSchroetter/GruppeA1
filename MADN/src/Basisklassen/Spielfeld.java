package Basisklassen;

/**
 * Abstrakte Klasse Spielfeld - vererbt ihre Attribute an Startfeld,
 * Standardfeld, Endfeld. Spielfeld kann von genau einer Spielfigur belegt
 * werden und kennt diese dann.
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 1.3
 * @since 2015-03-14
 */

public abstract class Spielfeld {

	/**
	 * Beinhaltet Figur die momentan auf dem Feld steht oder null.
	 */
	private Spielfigur figur;

	/**
	 * Konstruktor für ein Spielfeld. Spielfeld kennt die Belegung durch eine
	 * Figur.
	 */
	public Spielfeld() {
	}

	/**
	 * Öffentlicher Getter der Figur des Feldes zurückgibt
	 * 
	 * @return Spielfigur figur - Figur die auf Feld sitzt
	 */
	public Spielfigur getFigur() {
		return figur;
	}

	/**
	 * Geschützter Setter über den eine Spielfigur auf das Feld gesetzt werden
	 * kann. Wirft Runtime wenn Feld bereits durch eine Figur belegt ist.
	 * 
	 * @param figur - Figur die auf Feld gesetzt wird
	 * @exception RuntimeException
	 *                Wenn Feld belegt.
	 */
	 public void setFigur(Spielfigur figur) {
			this.figur = figur;
	}

}
