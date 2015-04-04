package Hilfsklassen;

import Einstellungen.FarbEnum;

import java.io.Serializable;

/**
 * Klasse ErgebnisWrapper. Wrapperklasse fuer relevante Daten eines Spielzuges. Beinhaltet boolsche Werte fuer Kills/Zuege, die ID der Figur,
 * die zieht, die ID des Feldes, auf das gezogen wird, die ID der Figur die (falls moeglich) geschlagen wird, sowie deren Farben.
 * ! UNVOLLSTAENDIG !
 *  
 * 
 * @author      Alexander Brueckner (Alexander.Brueckner@Student-Reutlingen-University.de)
 * @version     4.0           
 * @since       v1.0          
 */

public class ErgebnisWrapper implements Serializable {


	private static final long serialVersionUID = 1L;

	private int figurID;

	private FarbEnum figurFarbe;

	private boolean zugMoeglich;

	private int feldID;

	private boolean killMoeglich;

	private int opferID;

	private FarbEnum opferFarbe;

	//private int wuerfelzahl;
	
	
	/**
	 * Konstruktor fuer Klasse ErgebnisWrapper                 
	 * @param figurID - int
	 * @param figurFarbe - FarbEnum
	 * @param feldID - int
	 * @param opferFarbe - FarbEnum
	 * @param wuerfelzahl - int
	 * @param opferID - int
	 * @throws IllegalArgumentException Bei ungueltiger FigurID, FigurFarbe oder FeldID.
	 * 
	 * Prueft Parameter und ruft darauf hin setKillmoeglich und setZugMoeglich auf, um zu Ermitteln ob die Figur
	 * ziehen und ggf. jemanden schlagen kann.
	 */
	

	public ErgebnisWrapper(int figurID, FarbEnum figurFarbe,
			int feldID, int opferID,
			FarbEnum opferFarbe, int wuerfelzahl) {

		if ((figurID <= 0 || figurID > 16))
			throw new IllegalArgumentException("Ungueltige FigurID!");
		else {
			this.setFigurID(figurID);
			this.setOpferID(opferID);
		}

		if (figurFarbe == null)
			throw new IllegalArgumentException("Figurfarbe ungueltig");

		else {
			this.setFigurFarbe(figurFarbe);
			this.setOpferFarbe(opferFarbe);
		}

		if (feldID < 1 || feldID > 40)
			throw new IllegalArgumentException("Ungueltiges Feld");
		else {
			this.setFeldID(feldID);
		}

		this.setKillMoeglich();
		this.setZugMoeglich();
		
		if(wuerfelzahl > 1 || wuerfelzahl < 6) throw new IllegalArgumentException("Ungueltige Wuerfelzahl");

	}
	
	/**
	 * Gibt figurID zurueck                      
	 * @return figurID - int
	 */

	public int getFigurID() {
		return figurID;
	}
	
	/**
	 * Gibt die Farbe der Killer-Figur zurueck                   
	 * @return figurFarbe - FarbEnum
	 */

	public FarbEnum getFigurFarbe() {
		return figurFarbe;
	}
	
	/**
	 * Prueft, ob die Figur ziehen kann                      
	 * @return zugMoeglich - boolean
	 */

	public boolean isZugMoeglich() {
		return zugMoeglich;
	}
	
	/**
	 * Gibt feldID zurueck                      
	 * @return feldID - int
	 */

	public int getFeldID() {
		return feldID;
	}
	
	/**
	 * Sagt aus, ob die Figur schlagen kann                      
	 * @return killMoeglich - boolean
	 */


	public boolean isKillMoeglich() {
		return killMoeglich;
	}
	
	/**
	 * Gibt opferID zurueck                      
	 * @return opferID - int
	 */

	public int getOpferID() {
		return opferID;
	}
	
	/**
	 * Gibt die Farbe der Opfer-Figur zurueck                   
	 * @return OpferFarbe - FarbEnum
	 */


	public FarbEnum getOpferFarbe() {
		return opferFarbe;
	}
	
	/**
	 * Privater Setter fuer figurID                  
	 * @param int figurID
	 */

	private void setFigurID(int figurID) {
		this.figurID = figurID;
	}
	
	
	/**
	 * Privater Setter fuer figurFarbe                 
	 * @param FarbEnum figurFarbe
	 */

	private void setFigurFarbe(FarbEnum figurFarbe) {
		this.figurFarbe = figurFarbe;
	}
	
	/**
	 * Privater Setter fuer zugMoeglich                  
	 * @param boolean zugMoeglich
	 */

	private void setZugMoeglich(boolean zugMoeglich) {
		this.zugMoeglich = zugMoeglich;
	}
	
	/**
	 * Privater Setter fuer feldID                
	 * @param int feldID
	 */

	private void setFeldID(int feldID) {
		this.feldID = feldID;
	}
	
	/**
	 * Privater Setter fuer killMoeglich                  
	 * @param boolean killMoeglich
	 */

	private void setKillMoeglich(boolean killMoeglich) {
		this.killMoeglich = killMoeglich;
	}
	
	/**
	 * Privater Setter fuer opferID                
	 * @param int opferID
	 */
	

	private void setOpferID(int opferID) {
		this.opferID = opferID;
	}
	
	/**
	 * Privater Setter fuer opferFarbe                 
	 * @param FarbEnum opferFarbe
	 */

	private void setOpferFarbe(FarbEnum opferFarbe) {
		this.opferFarbe = opferFarbe;
	}

	// Ueberladung von boolschen Settern
	
	/**
	 * Privater Setter fuer zugMoeglich - Ermittelt anhand der Farben der Figuren, ob ein Zug moeglich ist  
	 * ! UNVOLLLSTAENDIG !               
	 */

	private void setZugMoeglich() {

		if (this.getFigurFarbe() == this.getOpferFarbe()) {
			this.setZugMoeglich(false);
		}

	}
	
	/**
	 * Privater Setter fuer killMoeglich - Ermittelt anhand der Farben der Figuren, ob ein Kill moeglich ist  
	 * ! UNVOLLLSTAENDIG !               
	 */

	private void setKillMoeglich() {
		if (this.getOpferFarbe() == null) {
			this.setKillMoeglich(false);
		}

		else if (this.getFigurFarbe() != this.getOpferFarbe()) {
			this.setKillMoeglich(true);
		}
	}

}
