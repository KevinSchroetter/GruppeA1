package Basisklassen;

import java.io.Serializable;

/**
 * Abstrakte Klasse Spielfeld - vererbt ihre Attribute an Startfeld,
 * Standardfeld, Endfeld. Spielfeld kann von genau einer Spielfigur belegt
 * werden und kennt diese dann.
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 4.0
 * @since v1.0
 */
public abstract class Spielfeld implements Serializable {

	private static final long serialVersionUID = 1L;
	private Spielfigur figur;
	private String iD;
	
	/**
	 * Konstruktor fuer ein Spielfeld. Spielfeld kennt die Belegung durch eine
	 * Figur.
	 */
	public Spielfeld() {
	}

	/**
	 * Oeffentlicher Getter der Figur des Feldes zurueckgibt
	 * 
	 * @return Spielfigur figur - Figur die auf Feld sitzt
	 */
	public Spielfigur getFigur() {
		return figur;
	}

	/**
	 * Geschuetzter Setter ueber den eine Spielfigur auf das Feld gesetzt werden
	 * kann. Wirft Runtime wenn Feld bereits durch eine Figur belegt ist.
	 * 
	 * @param figur - Figur die auf Feld gesetzt wird
	 * @exception RuntimeException
	 *                Wenn Feld belegt.
	 */
	 public void setFigur(Spielfigur figur) {
			this.figur = figur;
	}
	 
	protected void setID(String iD){
		this.iD = iD;
	}

	public String getID() {
		return this.iD;
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
