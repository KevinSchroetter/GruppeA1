package Basisklassen;

import java.io.Serializable;

/**
 * Abstrakte Klasse Spielfeld - vererbt ihre Attribute an Startfeld,
 * Standardfeld, Endfeld. Spielfeld kann von genau einer Spielfigur belegt
 * werden und kennt diese dann.
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 3.0
 * @since 2015-03-14
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
	 
	public void setID(String iD){
		this.iD = iD;
	}

	public String getID() {
		// TODO Auto-generated method stub
		return this.iD;
	}

}
