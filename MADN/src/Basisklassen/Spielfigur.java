package Basisklassen;

/**
 * @author Alexander Brückner
 *         (Alexander.Brueckner@Student-Reutlingen-University.de)
 * @version 1
 * @since 2015-03-12
 */

public class Spielfigur {
	/**
	 * Figurenzähler, dient als Verifikation der korrekten Anzahl an Figuren und
	 * zur Erstellung der FigurID
	 */
	private static int anzahlFiguren = 0;
	/**
	 * FigurenID - 1-16;
	 */
	private int ID;
	/**
	 * Feld, auf dem Figur steht
	 */
	private Spielfeld meinFeld;
	/**
	 * Farbe der Figur
	 */
	private FarbEnum farbe;
	/**
	 * "Schrittzähler" - um genaue Position von Figur nachzuvollziehen
	 */
	private int felderGelaufen;

	// Für Zug
	/**
	 * True, wenn Kill möglich
	 */
	private boolean kannSchlagen;
	/**
	 * True wenn NICHT auf Startfeld und NICHT auf finaler Zielposition
	 */
	private boolean istGespawnt;
	/**
	 * True wenn Figur auf Zielfeld angekommen (Also "aus dem Spiel" ist)
	 */
	private boolean istImZiel;
	/**
	 * True wenn Zug in Zielfelder möglich
	 */
	private boolean kannInsZiel;
	/**
	 * True, wenn Zug auf Standardfeld möglich
	 */
	private boolean kannZiehen;
	/**
	 * Wird TRUE, wenn die Figur auf Ihrer Endposition ist.
	 */
	private boolean binIchAufEndposition=false;

	// Getter/Setter
	/**
	 * Gibt meinFeld zurück
	 * 
	 * @return Spielfeld meinFeld
	 */
	public Spielfeld getMeinFeld() {
		return meinFeld;
	}

	/**
	 * Gibt Farbe zurück
	 * 
	 * @return FarbEnum farbe
	 */

	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * Gibt Anzahl gelaufener Felder zurück
	 * 
	 * @return int felderGelaufen
	 */

	public int getFelderGelaufen() {
		return felderGelaufen;
	}

	/**
	 * Gibt Aufschluss ob Kill möglich ist
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
	 * Öffentlicher Setter für Feld der Figur, stellt Kenntnisbeziehung zwischen
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
	 * Privater Setter für Farbe der Figur
	 * 
	 * @param FarbEnum
	 *            farbe
	 */

	private void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	/**
	 * Öffentlicher Setter für Schrittzähler - inkrementiert um Anzahl der
	 * Felder die die Figur gelaufen ist wird bei Kill der Figur resetted
	 * (Deshalb Public, erfolgt später in Klasse Spiel)
	 * 
	 * @param felderGelaufen
	 *            - int
	 */

	public void setFelderGelaufen(int felderGelaufen) {
		this.felderGelaufen += felderGelaufen;
	}

	/**
	 * Privater Setter für kannSchlagen
	 * 
	 * @param boolean kannSchlagen
	 */

	private void setKannSchlagen(boolean kannSchlagen) {
		this.kannSchlagen = kannSchlagen;
	}

	/**
	 * Privater Setter für istGespawnt
	 * 
	 * @param boolean istGespawnt
	 */

	public void setIstGespawnt(boolean istGespawnt) {
		this.istGespawnt = istGespawnt;
	}

	/**
	 * Privater Setter für istimZiel
	 * 
	 * @param boolean istImZiel
	 */

	public void setIstImZiel(boolean istImZiel) {
		this.istImZiel = istImZiel;
	}

	/**
	 * Privater Setter für kannInsZiel
	 * 
	 * @param boolean kannInsZiel
	 */

	public void setKannInsZiel(boolean kannInsZiel) {
		this.kannInsZiel = kannInsZiel;
	}

	/**
	 * Privater Setter für kannZiehen
	 * 
	 * @param boolean kannZiehen
	 */

	public void setKannZiehen(boolean kannZiehen) {
		this.kannZiehen = kannZiehen;
	}

	/**
	 * Öffentlicher, Statischer Getter für Anzahl der Figuren
	 * 
	 * @return int Spielfigur.anzahlFiguren
	 */

	public static int getAnzFiguren() {
		return Spielfigur.anzahlFiguren;

	}

	/**
	 * Privater Setter für ID - Prüft auf Gültigkeit
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
	 * Öffentlicher Getter für ID der Figur
	 * 
	 * @return int ID
	 */

	public int getID() {
		return this.ID;
	}
	/**
	 * Setter für das Attribut binIchAufEndposition
	 * @param bIAE - boolean
	 */
	public void setBinIchAufEndposition(boolean bIAE){
		this.binIchAufEndposition=bIAE;
	}
	/**
	 * Getter für binIchAufEndposition
	 * @return binIchAufEndposition - boolean 
	 */
	public boolean getBinIchAufEndpostion() {
		return this.binIchAufEndposition;
	}
	/**
	 * Konstruktor für Klasse Spielfigur
	 * 
	 * @param farbID - int
	 * @exception RuntimeException - wenn Anzahl Figuren größer gleich 16
	 * @exception IllegalArgumentException - wenn FarbID kleiner gleich Null oder größer 4 ist
	 * 
	 * Generiert aus anzahlFiguren eine ID, prüft ob eine gültige
	 * Farbe übergeben wurde, falls nicht, wirft eine
	 * IllegalArgumentException. Gibt es bereits 16 Figuren,
	 * wirft er eine RuntimeException. ! Im Verlauf der
	 * Implementierung von Klasse Spiel werden eigene Exceptions
	 * generiert - Runtime - und IllegalArgumentException dienen
	 * lediglich als Platzhalter !
	 */

	public Spielfigur(int farbID) {

		if (farbID == 1) {
			this.farbe = FarbEnum.ROT;
		} else if (farbID == 2) {
			this.farbe = FarbEnum.BLAU;
		} else if (farbID == 3) {
			this.farbe = FarbEnum.GRÜN;
		} else if (farbID == 4) {
			this.farbe = FarbEnum.GELB;
		} else
			throw new IllegalArgumentException(
					"Ungültige Farbe! 1 = Rot, 2 = Blau, 3 = Grün, 4 = Gelb!");

		if (anzahlFiguren >= 16)
			throw new RuntimeException(
					"Zu viele Figuren! max. 16 (4 pro Spieler!");
		else {
			this.setID(++Spielfigur.anzahlFiguren);
		}

	}

	/**
	 * Ermittelt, ob das Targetfeld gültig ist, und ob eine Figur darauf steht.
	 * Gehört die Figur zur gleichen Farbe, kann sie nicht geschlagen werden.
	 * Ist die Figur feindlich gesinnt, kann sie erledigt werden.
	 * 
	 * @param zielFeld
	 *            - Spielfeld
	 * @return boolean
	 * @exception RuntimeException
	 *                Wenn Zielfeld ungültig
	 */

	public boolean kannSchlagen(Standardfeld zielFeld) {

		if (zielFeld == null) {
			throw new RuntimeException("Zielfeld ungültig!");
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
	 * Ermittelt, ob das jeweilige Startfeld frei ist. Wenn nicht,
	 * RuntimeException. Ist das Spawnfeld ungültig, RuntimeException. Handelt
	 * es sich bei dem Argument nicht um ein Standardfeld, RuntimeException.
	 * Prüft die Farbe des spawnpoints und setzt die Figur an die Entsprechende
	 * Spawnlocation.
	 * 
	 * @param spawnpoint - Spielfeld
	 * @exception RuntimeException - wenn Spawnpoint null, belegt oder kein Standardfeld ist
	 */

	public void spawn(Standardfeld spawnpoint) {
		if (spawnpoint == null)
			throw new RuntimeException("Argument ist kein Spawnpoint!");
		if (spawnpoint.getFigur() != null)
			throw new RuntimeException("Spawnpoint belegt!");
		if (!(spawnpoint instanceof Standardfeld))
			throw new RuntimeException("Argument kein Spawnpoint!");
		else {
			
			switch (this.getFarbe()) {

			case ROT:
				if (spawnpoint.getID().equals("1")) {
					this.setMeinFeld(spawnpoint);
					this.setIstGespawnt(true);
				}
				break;
			case BLAU:
				if (spawnpoint.getID().equals("11")) {
					this.setMeinFeld(spawnpoint);
					this.setIstGespawnt(true);
				}
				break;
			case GRÜN:
				if (spawnpoint.getID().equals("21")) {
					this.setMeinFeld(spawnpoint);
					this.setIstGespawnt(true);
				}
				break;
			case GELB:
				if (spawnpoint.getID().equals("31")) {
					this.setMeinFeld(spawnpoint);
					this.setIstGespawnt(true);
				}
				break;

			}

		}

	}

	/**
	 * Prüft, ob die Figur im Spiel ist.. Gibt True zurück, wenn sie NICHT auf
	 * einem Startfeld steht, nicht im Ziel ist (kann aber auf einem Endfeld
	 * stehen) und das Feld gültig ist. Gibt sonst false zurück.
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
	 * Prüft, mittels der übergebenen Augenzahl, ob die Figur im Stande ist,
	 * einen Zug auf ein Standardfeld durchzuführen. True wenn meinFeld instanz
	 * von Standardfeld ist und die Nummer des aktuellen Feldes plus der
	 * Augenzahl kleiner gleich 40 ist. Wirft bei ungültiger Feldzahl (kleiner
	 * gleich Null, größer 40) eine RuntimeException.
	 * 
	 * @return boolean
	 * @param augenZahl
	 *            - Integer, gewürfelte Zahl
	 * @exception RuntimeException
	 *                Wenn FeldID kleiner gleich Null oder größer 40 ist.
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
	 * Prüft, ob aktuelles Feld ein Standardfeld ist.
	 * 
	 * @return boolean
	 */

	public boolean istAufStandardfeld() {
		if (this.getMeinFeld() instanceof Standardfeld)
			return true;
		else
			return false;
	}

	/**
	 * Prüft, ob aktuelles Feld ein Endfeld ist.
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
	 * Setzt Schrittzähler zurück
	 * 
	 *
	 */

	
	public void resetFelderGelaufen(){
		//Klein aber oho: Muss aufgerufen werden, wenn die Figur geschlagen wird
		this.felderGelaufen = 0;
	}
	
	/**
	 * Inkrementiert Schrittzähler um angegebene zahl
	 * @param i - int
	 *
	 */
	
	public void incSchritteGelaufen(int i){
		if(i <= 0) throw new RuntimeException("Ungültige Schrittzahl!");
		else this.felderGelaufen+=i;
	}

	/**
	 * Klassische toString Methode. Gibt ID, meinFeld und Farbe zurück
	 * 
	 * @return String
	 */

	@Override
	public String toString() {
		return String.format("Figur %d auf Feld %s mit Farbe %s --\n ",
				this.getID(), this.getMeinFeld(), this.getFarbe().toString());
	}
}
