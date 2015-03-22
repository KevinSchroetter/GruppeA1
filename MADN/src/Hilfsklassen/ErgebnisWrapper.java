package Hilfsklassen;

import Basisklassen.FarbEnum;

public class ErgebnisWrapper {

	private int figurID;
	private FarbEnum figurFarbe;
	private boolean zugMöglich;
	private int feldID;
	private boolean killMöglich;
	private int opferID;
	private FarbEnum opferFarbe;

	public ErgebnisWrapper(int figurID, FarbEnum figurFarbe,
			boolean zugMöglich, int feldID, boolean killMöglich, int opferID,
			FarbEnum opferFarbe) {

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

		this.setKillMöglich(false);
		this.setZugMöglich(false);

	}

	public int getFigurID() {
		return figurID;
	}

	public FarbEnum getFigurFarbe() {
		return figurFarbe;
	}

	public boolean isZugMöglich() {
		return zugMöglich;
	}

	public int getFeldID() {
		return feldID;
	}

	public boolean isKillMöglich() {
		return killMöglich;
	}

	public int getOpferID() {
		return opferID;
	}

	public FarbEnum getOpferFarbe() {
		return opferFarbe;
	}

	private void setFigurID(int figurID) {
		this.figurID = figurID;
	}

	private void setFigurFarbe(FarbEnum figurFarbe) {
		this.figurFarbe = figurFarbe;
	}

	private void setZugMöglich(boolean zugMöglich) {
		this.zugMöglich = zugMöglich;
	}

	private void setFeldID(int feldID) {
		this.feldID = feldID;
	}

	private void setKillMöglich(boolean killMöglich) {
		this.killMöglich = killMöglich;
	}

	private void setOpferID(int opferID) {
		this.opferID = opferID;
	}

	private void setOpferFarbe(FarbEnum opferFarbe) {
		this.opferFarbe = opferFarbe;
	}

	// Überladung von boolschen Settern

	private void setZugMöglich() {

		if (this.getFigurFarbe() == this.getOpferFarbe()) {
			this.setZugMöglich(false);
		}

	}

	private void setKillMöglich() {
		if (this.getOpferFarbe() == null) {
			this.setKillMöglich(false);
		}

		else if (this.getFigurFarbe() != this.getOpferFarbe()) {
			this.setKillMöglich(true);
		}
	}

}
