package Basisklassen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;

import Einstellungen.FarbEnum;

/**
 * @author Alexander Brueckner (Alexander.Brueckner@Student-Reutlingen-University.de)
 * @version 4.0
 * @since v1.0
 */

public class Spielfigur implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private static int anzahlFiguren = 0;
	
	private static ArrayList<FarbEnum> farben = new ArrayList<FarbEnum>(EnumSet.allOf(FarbEnum.class));

	private String name;

	private int ID;

	private Spielfeld meinFeld;

	private FarbEnum farbe;

	private int felderGelaufen;

	private boolean kannSchlagen;

	private boolean istGespawnt;

	private boolean istImZiel;

	private boolean kannInsZiel;

	private boolean kannZiehen;

	private boolean binIchAufEndposition=false;

	/**
	 * Getter fuer den Namen der Figur
	 * @return name - der Name der Figur
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Setter fuer den Namen der Figur
	 * @param name - Name der Figur vom Typ String
	 */
	private void setName(String name){
		this.name = name;
	}
	/**
	 * Gibt meinFeld zurueck
	 * 
	 * @return Spielfeld meinFeld
	 */
	public Spielfeld getMeinFeld() {
		return meinFeld;
	}

	/**
	 * Gibt Farbe zurueck
	 * 
	 * @return FarbEnum farbe
	 */

	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * Gibt Anzahl gelaufener Felder zurueck
	 * 
	 * @return int felderGelaufen
	 */

	public int getFelderGelaufen() {
		return felderGelaufen;
	}

	/**
	 * Gibt Aufschluss ob Kill moeglich ist
	 * 
	 * @return boolean kannSchlagen
	 */

	public boolean getKannSchlagen() {
		return kannSchlagen;
	}

	/**
	 * Gibt Aufschluss ob Figur im Spiel ist
	 * 
	 * @return boolean istGespawnt
	 */

	public boolean getIstGespawnt() {
		return istGespawnt;
	}

	/**
	 * Gibt Aufschluss ob Figur aus dem Spiel ist (Auf Endfeld)
	 * 
	 * @return boolean istImZiel
	 */

	public boolean getIstImZiel() {
		return istImZiel;
	}

	/**
	 * Gibt Aufschluss ob Figur sich auf ein Endfeld bewegen kann
	 * 
	 * @return boolean istImZiel
	 */

	public boolean getKannInsZiel() {
		return kannInsZiel;
	}

	/**
	 * Gibt Aufschluss ob Figur auf ein Standardfeld ziehen kann
	 * 
	 * @return boolean istImZiel
	 */

	public boolean getKannZiehen() {
		return kannZiehen;
	}

	/**
	 * Oeffentlicher Setter fuer Feld der Figur, stellt Kenntnisbeziehung zwischen
	 * Figur und Feld her
	 * 
	 * @param meinFeld
	 *            - Spielfeld
	 */

	public void setMeinFeld(Spielfeld meinFeld) {
		this.meinFeld = meinFeld;
		this.meinFeld.setFigur(this);
	}

	/**
	 * Privater Setter fuer Farbe der Figur
	 * 
	 * @param FarbEnum
	 *            farbe
	 */

	private void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	/**
	 * Oeffentlicher Setter fuer Schrittzaehler - inkrementiert um Anzahl der
	 * Felder die die Figur gelaufen ist wird bei Kill der Figur resetted
	 * (Deshalb Public, erfolgt spaeter in Klasse Spiel)
	 * 
	 * @param felderGelaufen
	 *            - int
	 */

	public void setFelderGelaufen(int felderGelaufen) {
			this.felderGelaufen += felderGelaufen;
	}

	/**
	 * Privater Setter fuer kannSchlagen
	 * 
	 * @param kannSchlagen - boolean
	 */

	public void setKannSchlagen(boolean kannSchlagen) {
		this.kannSchlagen = kannSchlagen;
	}

	/**
	 * Privater Setter fuer istGespawnt
	 * 
	 * @param istGespawnt - boolean
	 */

	public void setIstGespawnt(boolean istGespawnt) {
		this.istGespawnt = istGespawnt;
	}

	/**
	 * Privater Setter fuer istimZiel
	 * 
	 * @param istImZiel - boolean
	 */

	public void setIstImZiel(boolean istImZiel) {
		this.istImZiel = istImZiel;
	}

	/**
	 * Privater Setter fuer kannInsZiel
	 * 
	 * @param kannInsZiel - boolean
	 */

	public void setKannInsZiel(boolean kannInsZiel) {
		this.kannInsZiel = kannInsZiel;
	}

	/**
	 * Privater Setter fuer kannZiehen
	 * 
	 * @param kannZiehen - boolean
	 */

	public void setKannZiehen(boolean kannZiehen) {
		this.kannZiehen = kannZiehen;
	}

	/**
	 * Oeffentlicher, Statischer Getter fuer Anzahl der Figuren
	 * 
	 * @return int Spielfigur.anzahlFiguren
	 */

	private int getAnzFiguren() {
		return Spielfigur.anzahlFiguren;

	}

	/**
	 * Privater Setter fuer ID - Prueft auf Gueltigkeit
	 * 
	 * @param int ID
	 */

	private void setID(int ID) {
		if (ID <= 0)
			throw new RuntimeException("ID einer Figur kann nicht 0 sein!");
		else
			this.ID = ID;
	}

	/**
	 * Oeffentlicher Getter fuer ID der Figur
	 * 
	 * @return int ID
	 */

	public int getID() {
		return this.ID;
	}
	/**
	 * Setter fuer das Attribut binIchAufEndposition
	 * @param bIAE - boolean
	 */
	public void setBinIchAufEndposition(boolean bIAE){
		this.binIchAufEndposition=bIAE;
	}
	/**
	 * Getter fuer binIchAufEndposition
	 * @return binIchAufEndposition - boolean 
	 */
	public boolean getBinIchAufEndpostion() {
		return this.binIchAufEndposition;
	}
	/**
	 * Konstruktor fuer Klasse Spielfigur
	 * 
	 * @param farbID - int
	 * @param name - String Name der Spielfigur mit zugehoeriger Farbe des Spielers, der die Figuren erstellt
	 * @exception RuntimeException - wenn Anzahl Figuren groesser gleich 16
	 * @exception IllegalArgumentException - wenn FarbID kleiner gleich Null oder groesser 4 ist
	 * 
	 * Generiert aus anzahlFiguren eine ID, prueft ob eine gueltige
	 * Farbe uebergeben wurde, falls nicht, wirft eine
	 * IllegalArgumentException. Gibt es bereits 16 Figuren,
	 * wirft er eine RuntimeException. ! Im Verlauf der
	 * Implementierung von Klasse Spiel werden eigene Exceptions
	 * generiert - Runtime - und IllegalArgumentException dienen
	 * lediglich als Platzhalter !
	 */

	public Spielfigur(int farbID, String name) {
		FarbEnum farbe = farben.get(farbID);
		setFarbe(farbe);
		setID(++Spielfigur.anzahlFiguren);
		setName(name);
	}

	/**
	 * Ermittelt, ob das Targetfeld gueltig ist, und ob eine Figur darauf steht.
	 * Gehoert die Figur zur gleichen Farbe, kann sie nicht geschlagen werden.
	 * Ist die Figur feindlich gesinnt, kann sie erledigt werden.
	 * 
	 * @param zielFeld
	 *            - Spielfeld
	 * @return boolean
	 * @exception RuntimeException
	 *                Wenn Zielfeld ungueltig
	 */

	public boolean kannSchlagen(Standardfeld zielFeld) {

		if (zielFeld == null) {
			throw new RuntimeException("Zielfeld ungueltig!");
		} else if (zielFeld.getFigur() == null) {
			return false;
		} else if (zielFeld.getFigur() != null) {
			if (zielFeld.getFigur().getFarbe() == this.getFarbe()) {
				return false;
			} else {
				return true;
			}
		}

		return false;
	}
	/**
	 * Prueft, ob die Figur im Spiel ist.. Gibt True zurueck, wenn sie NICHT auf
	 * einem Startfeld steht, nicht im Ziel ist (kann aber auf einem Endfeld
	 * stehen) und das Feld gueltig ist. Gibt sonst false zurueck.
	 * 
	 * @return boolean
	 */

	public boolean binIchGespawnt() {
		if ((this.getMeinFeld() != null)
				&& ((!(this.getMeinFeld() instanceof Startfeld))
						|| this.getMeinFeld() instanceof Endfeld || this
							.getMeinFeld() instanceof Standardfeld)) {
			return true;

		} else
			return false;

	}

	/**
	 * Prueft, mittels der uebergebenen Augenzahl, ob die Figur im Stande ist,
	 * einen Zug auf ein Standardfeld durchzufuehren. True wenn meinFeld instanz
	 * von Standardfeld ist und die Nummer des aktuellen Feldes plus der
	 * Augenzahl kleiner gleich 40 ist. Wirft bei ungueltiger Feldzahl (kleiner
	 * gleich Null, groesser 40) eine RuntimeException.
	 * 
	 * @return boolean
	 * @param augenZahl
	 *            - Integer, gewuerfelte Zahl
	 * @exception RuntimeException
	 *                Wenn FeldID kleiner gleich Null oder groesser 40 ist.
	 */
/*
	public boolean kannIchZiehen(int augenZahl) {

		if (this.binIchGespawnt() && (!(this.istAufEndfeld()))
				|| this.getKannSchlagen()) {

			if (this.getMeinFeld() instanceof Standardfeld) {
				if (this.binIchGespawnt() == true) {
					Standardfeld meep = (Standardfeld) this.getMeinFeld();
					if ((meep.getID() + augenZahl) > 40
							|| (meep.getID() + augenZahl) <= 0) {
						throw new RuntimeException("Targetfeld out of bounds");
					} else if (meep.getID() + augenZahl <= 40) {
						return true;
					}
				}
			} else {
				return false;
			}
		}

		return false;

	}*/


	/**
	 * Prueft, ob aktuelles Feld ein Standardfeld ist.
	 * 
	 * @return boolean
	 */

	private boolean istAufStandardfeld() {
		if (this.getMeinFeld() instanceof Standardfeld)
			return true;
		else
			return false;
	}

	/**
	 * Prueft, ob aktuelles Feld ein Endfeld ist.
	 * 
	 * @return boolean
	 */

	public boolean istAufEndfeld() {
		if (this.getMeinFeld() instanceof Endfeld)
			return true;
		else
			return false;
	}
	
	/**
	 * Setzt Schrittzaehler zurueck
	 * 
	 *
	 */

	
	public void resetFelderGelaufen(){
		//Klein aber oho: Muss aufgerufen werden, wenn die Figur geschlagen wird
		this.felderGelaufen = 0;
	}
	
	/**
	 * Inkrementiert Schrittzaehler um angegebene zahl
	 * @param i - int
	 *
	 */
	
	public void incSchritteGelaufen(int i){
		if(i <= 0) throw new RuntimeException("Ungueltige Schrittzahl!");
		else this.felderGelaufen+=i;
	}

	/**
	 * Klassische toString Methode. Gibt ID, meinFeld und Farbe zurueck
	 * 
	 * @return String
	 */

	@Override
	public String toString() {
		return String.format("Figur %d auf Feld %s mit Farbe %s --\n ",
				this.getID(), this.getMeinFeld(), this.getFarbe().toString());
	}
	/**
	 * HilfsMethode zum Loeschen der static anzaglFiguren (Wird in Spiel benoetigt, daher public)
	 */
	public static void deleteAnzahlFiguren(){
		Spielfigur.anzahlFiguren=0;
	}
}
