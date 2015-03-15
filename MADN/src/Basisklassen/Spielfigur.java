package Basisklassen;

public class Spielfigur {

	private static int anzahlFiguren = 0;

	private int ID;
	private Spielfeld meinFeld;
	private Spielfeld zielFeld;
	private FarbEnum farbe;
	private int felderGelaufen;

	// Für Zug
	private boolean kannSchlagen;
	private boolean istGespawnt;
	private boolean istImZiel;
	private boolean kannInsZiel;
	private boolean kannZiehen;

	// Getter/Setter

	public Spielfeld getMeinFeld() {
		return meinFeld;
	}

	public FarbEnum getFarbe() {
		return farbe;
	}

	public int getFelderGelaufen() {
		return felderGelaufen;
	}

	public boolean getKannSchlagen() {
		return kannSchlagen;
	}

	public boolean getIstGespawnt() {
		return istGespawnt;
	}

	public boolean getIstImZiel() {
		return istImZiel;
	}

	public boolean getKannInsZiel() {
		return kannInsZiel;
	}

	public boolean getKannZiehen() {
		return kannZiehen;
	}

	private void setMeinFeld(Spielfeld meinFeld) {
		this.meinFeld = meinFeld;
	}

	private void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	private void setFelderGelaufen(int felderGelaufen) {
		this.felderGelaufen = felderGelaufen;
	}

	private void setKannSchlagen(boolean kannSchlagen) {
		this.kannSchlagen = kannSchlagen;
	}

	private void setIstGespawnt(boolean istGespawnt) {
		this.istGespawnt = istGespawnt;
	}

	private void setIstImZiel(boolean istImZiel) {
		this.istImZiel = istImZiel;
	}

	private void setKannInsZiel(boolean kannInsZiel) {
		this.kannInsZiel = kannInsZiel;
	}

	private void setKannZiehen(boolean kannZiehen) {
		this.kannZiehen = kannZiehen;
	}

	public static int getAnzFiguren() {
		return Spielfigur.anzahlFiguren;

	}

	private void setID(int ID) {
		if (ID <= 0)
			throw new RuntimeException("ID einer Figur kann nicht 0 sein!");
		else
			this.ID = ID;
	}

	public int getID() {
		return this.ID;
	}

	// Konstruktor

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

	public boolean kannSchlagen() {

		/*
		 * Warte auf Implementierung von Klasse Spielfeld
		 */

		return false;
	}

	public void spawn() {

		/*
		 * Warte auf Implementierung von Klasse Spielfeld
		 */
	}

	public boolean binIchGespawnt() {
		return false;
		/*
		 * Warte auf Implementierung von Klasse Spielfeld
		 */
	}

	public boolean binIchImZiel() {
		/*
		 * Warte auf Implementierung von Klasse Spielfeld
		 */

		return false;
	}

	public boolean kannIchZiehen() {

		if ((this.binIchGespawnt() && (!(this.binIchImZiel())))
				|| this.getKannSchlagen()) {
			return true;
		}

		/*
		 * Hier Algorithmus einfügen um zu ermitteln, ob innerhalb eines
		 * Zielfelds, wenn ja, ist Zug innerhalb des Ziels möglich?
		 */
		else
			return false;
	}

	public String toString() {
		return String.format("Figur %d auf Feld %s mit Farbe %s --\n ",
				this.getID(), this.getMeinFeld(), (this.getFarbe().toString()));
	}

}
