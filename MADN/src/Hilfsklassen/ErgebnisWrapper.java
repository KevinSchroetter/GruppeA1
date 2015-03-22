package Hilfsklassen;

import Basisklassen.FarbEnum;

/**
 * Klasse ErgebnisWrapper. Wrapperklasse für relevante Daten eines Spielzuges. Beinhaltet boolsche Werte für Kills/Züge, die ID der Figur,
 * die zieht, die ID des Feldes, auf das gezogen wird, die ID der Figur die (falls möglich) geschlagen wird, sowie deren Farben.
 * ! UNVOLLSTÄNDIG !
 *  
 * 
 * @author      Alexander Brückner (Alexander.Brueckner@Student-Reutlingen-University.de)
 * @version     1                 
 * @since       2015-03-22          
 */

public class ErgebnisWrapper {

	/**
	 * ID der gewählten Figur
	 */
	private int figurID;
	/**
	 * Farbe der gewählten Figur
	 */
	private FarbEnum figurFarbe;
	
	/**
	 * True, wenn Figur ziehen kann, false, wenn nicht.
	 */
	private boolean zugMöglich;
	/**
	 * ID des Feldes, auf das gezogen wird
	 */
	private int feldID;
	/**
	 * True wenn Zielfeld belegt und die Figur darauf NICHT die gleiche Farbe hat, wie die, die zieht.
	 * Sonst false
	 */
	private boolean killMöglich;
	/**
	 * ID der Figur, die das Zeitliche segnen soll
	 */
	private int opferID;
	/**
	 * Farbe der Figur, die das Zeitliche segnen soll
	 */
	private FarbEnum opferFarbe;
	/**
	 * Gewürfelte Zahl.
	 */
	private int würfelzahl;
	
	
	/**
	 * Konstruktor für Klasse ErgebnisWrapper                 
	 * @param figurID - int
	 * @param figurFarbe - FarbEnum
	 * @param feldID - int
	 * @param opferFarbe - FarbEnum
	 * @param würfelzahl - int
	 * @param opferID - int
	 * @throws IllegalArgumentException Bei ungültiger FigurID, FigurFarbe oder FeldID.
	 * 
	 * Prüft Parameter und ruft darauf hin setKillmöglich und setZugMöglich auf, um zu Ermitteln ob die Figur
	 * ziehen und ggf. jemanden schlagen kann.
	 */
	

	public ErgebnisWrapper(int figurID, FarbEnum figurFarbe,
			int feldID, int opferID,
			FarbEnum opferFarbe, int würfelzahl) {

		if ((figurID <= 0 || figurID > 16))
			throw new IllegalArgumentException("Ungültige FigurID!");
		else {
			this.setFigurID(figurID);
			this.setOpferID(opferID);
		}

		if (figurFarbe == null)
			throw new IllegalArgumentException("Figurfarbe ungültig");

		else {
			this.setFigurFarbe(figurFarbe);
			this.setOpferFarbe(opferFarbe);
		}

		if (feldID < 1 || feldID > 40)
			throw new IllegalArgumentException("Ungültiges Feld");
		else {
			this.setFeldID(feldID);
		}

		this.setKillMöglich();
		this.setZugMöglich();
		
		if(würfelzahl > 1 || würfelzahl < 6) throw new IllegalArgumentException("Ungültige Würfelzahl");

	}
	
	/**
	 * Gibt figurID zurück                      
	 * @return figurID - int
	 */

	public int getFigurID() {
		return figurID;
	}
	
	/**
	 * Gibt die Farbe der Killer-Figur zurück                   
	 * @return figurFarbe - FarbEnum
	 */

	public FarbEnum getFigurFarbe() {
		return figurFarbe;
	}
	
	/**
	 * Prüft, ob die Figur ziehen kann                      
	 * @return zugMöglich - boolean
	 */

	public boolean isZugMöglich() {
		return zugMöglich;
	}
	
	/**
	 * Gibt feldID zurück                      
	 * @return feldID - int
	 */

	public int getFeldID() {
		return feldID;
	}
	
	/**
	 * Sagt aus, ob die Figur schlagen kann                      
	 * @return killMöglich - boolean
	 */


	public boolean isKillMöglich() {
		return killMöglich;
	}
	
	/**
	 * Gibt opferID zurück                      
	 * @return opferID - int
	 */

	public int getOpferID() {
		return opferID;
	}
	
	/**
	 * Gibt die Farbe der Opfer-Figur zurück                   
	 * @return OpferFarbe - FarbEnum
	 */


	public FarbEnum getOpferFarbe() {
		return opferFarbe;
	}
	
	/**
	 * Privater Setter für figurID                  
	 * @param int figurID
	 */

	private void setFigurID(int figurID) {
		this.figurID = figurID;
	}
	
	
	/**
	 * Privater Setter für figurFarbe                 
	 * @param FarbEnum figurFarbe
	 */

	private void setFigurFarbe(FarbEnum figurFarbe) {
		this.figurFarbe = figurFarbe;
	}
	
	/**
	 * Privater Setter für zugMöglich                  
	 * @param boolean zugMöglich
	 */

	private void setZugMöglich(boolean zugMöglich) {
		this.zugMöglich = zugMöglich;
	}
	
	/**
	 * Privater Setter für feldID                
	 * @param int feldID
	 */

	private void setFeldID(int feldID) {
		this.feldID = feldID;
	}
	
	/**
	 * Privater Setter für killMöglich                  
	 * @param boolean killMöglich
	 */

	private void setKillMöglich(boolean killMöglich) {
		this.killMöglich = killMöglich;
	}
	
	/**
	 * Privater Setter für opferID                
	 * @param int opferID
	 */
	

	private void setOpferID(int opferID) {
		this.opferID = opferID;
	}
	
	/**
	 * Privater Setter für opferFarbe                 
	 * @param FarbEnum opferFarbe
	 */

	private void setOpferFarbe(FarbEnum opferFarbe) {
		this.opferFarbe = opferFarbe;
	}

	// Überladung von boolschen Settern
	
	/**
	 * Privater Setter für zugMöglich - Ermittelt anhand der Farben der Figuren, ob ein Zug möglich ist  
	 * ! UNVOLLLSTÄNDIG !               
	 */

	private void setZugMöglich() {

		if (this.getFigurFarbe() == this.getOpferFarbe()) {
			this.setZugMöglich(false);
		}

	}
	
	/**
	 * Privater Setter für killMöglich - Ermittelt anhand der Farben der Figuren, ob ein Kill möglich ist  
	 * ! UNVOLLLSTÄNDIG !               
	 */

	private void setKillMöglich() {
		if (this.getOpferFarbe() == null) {
			this.setKillMöglich(false);
		}

		else if (this.getFigurFarbe() != this.getOpferFarbe()) {
			this.setKillMöglich(true);
		}
	}

}
