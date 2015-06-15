package Hilfsklassen;

import javax.xml.bind.annotation.XmlType;

import Einstellungen.FarbEnum;

@XmlType(propOrder = { "name", "feldID", "schritteGelaufen", "farbe" })
public class FigurenWrapper {

	private String name;
	private String feldID;
	private int schritteGelaufen;
	private FarbEnum farbe;

	public FigurenWrapper() {
		this.name = "";
		this.feldID = "";
		this.schritteGelaufen = 0;
		this.farbe = null;
	}

	public FigurenWrapper(String name, String feldID, int schritteGelaufen,
			FarbEnum farbe) {
		this.name = name;
		this.feldID = feldID;
		this.schritteGelaufen = schritteGelaufen;
		this.farbe = farbe;
	}

	public String getName() {
		return this.name;
	}

	public String getFeldID() {
		return this.feldID;
	}

	public int getSchritteGelaufen() {
		return this.schritteGelaufen;
	}

	public FarbEnum getFarbe() {
		return this.farbe;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFeldID(String feldID) {
		this.feldID = feldID;
	}

	public void setSchritteGelaufen(int schritteGelaufen) {
		this.schritteGelaufen = schritteGelaufen;
	}

	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}
	
	@Override
	public String toString(){
		return String.format("%s - %s - %s - %d",this.name, this.farbe, this.feldID, this.schritteGelaufen);
	}

}
