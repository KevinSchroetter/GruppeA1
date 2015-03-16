package Basisklassen;

public class Spielfigur {

	private static int anzahlFiguren = 0;

	private int ID;
	private Spielfeld meinFeld;
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

	public void setFelderGelaufen(int felderGelaufen) {
		this.felderGelaufen += felderGelaufen;
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
				if (spawnpoint.getID() == 1) {
					this.setMeinFeld(spawnpoint);
					this.setIstGespawnt(true);
				}
				break;
			case BLAU:
				if (spawnpoint.getID() == 11) {
					this.setMeinFeld(spawnpoint);
					this.setIstGespawnt(true);
				}
				break;
			case GRÜN:
				if (spawnpoint.getID() == 21) {
					this.setMeinFeld(spawnpoint);
					this.setIstGespawnt(true);
				}
				break;
			case GELB:
				if (spawnpoint.getID() == 31) {
					this.setMeinFeld(spawnpoint);
					this.setIstGespawnt(true);
				}
				break;

			}

		}

	}

	// True wenn meinFeld KEIN Startfeld ist ODER ein normales Feld ist ODER ein
	// Zielfeld ist
	public boolean binIchGespawnt() {
		if ((this.getMeinFeld() != null)
				&& ((!(this.getMeinFeld() instanceof Startfeld))
						|| this.getMeinFeld() instanceof Endfeld || this
							.getMeinFeld() instanceof Standardfeld)) {
			return true;

		} else
			return false;

	}

	public boolean kannIchZiehen(int augenZahl) {

		if ((this.binIchGespawnt() && (!(this.istAufEndfeld())))
				|| this.getKannSchlagen()) {

			if (this.getMeinFeld() instanceof Standardfeld) {

				Standardfeld meep = (Standardfeld) this.getMeinFeld();
				if ((meep.getID() + augenZahl) > 40) {
					throw new RuntimeException("Targetfeld out of bounds");
				} else if (meep.getID() + augenZahl <= 40) {
					return true;
				}
			} else {
				return false;
			}

		}

		return false;

	}

	public boolean istAufStandardfeld() {
		if (this.getMeinFeld() instanceof Standardfeld)
			return true;
		else
			return false;
	}

	public boolean istAufEndfeld() {
		if (this.getMeinFeld() instanceof Endfeld)
			return true;
		else
			return false;
	}

	public String toString() {
		return String.format("Figur %d auf Feld %s mit Farbe %s --\n ",
				this.getID(), this.getMeinFeld(), (this.getFarbe().toString()));
	}

}
