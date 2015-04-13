package Hilfsklassen;

import Einstellungen.*;
import Basisklassen.*;

public class SpielerLadenWrapper {

	private String name;
	private String verhalten;
	private FarbEnum farbe;
	private boolean binIchDran;
	private String[] feldIDs = new String[4];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVerhalten() {
		return verhalten;
	}

	public void setVerhalten(String verhalten) {
		this.verhalten = verhalten;
	}

	public FarbEnum getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		switch (farbe) {
		case "ROT":
			this.farbe = FarbEnum.ROT;
			break;
		case "BLAU":
			this.farbe = FarbEnum.BLAU;
			break;
		case "GRUEN":
			this.farbe = FarbEnum.GRUEN;
			break;
		case "GELB":
			this.farbe = FarbEnum.GELB;
		default:
			this.farbe = null;
			break;
		}
	}

	public boolean isBinIchDran() {
		return binIchDran;
	}

	public void setBinIchDran(String binIchDran) {

		if (binIchDran == null)
			throw new IllegalArgumentException("T oder F");
		else
			switch (binIchDran) {

			case "true":
				this.binIchDran = true;
				break;
			case "false":
				this.binIchDran = false;

			}
	}

	public String[] getFeldIDs() {
		return feldIDs;
	}

	public void setFeldIDs(String[] feldIDs) {
		this.feldIDs = feldIDs;
	}

	public String getFeldIDNr(int i) {
		try {
			return feldIDs[i];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

}
