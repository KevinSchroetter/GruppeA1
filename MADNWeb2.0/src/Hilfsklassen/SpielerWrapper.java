package Hilfsklassen;

import java.util.List;

import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import Einstellungen.FarbEnum;

@XmlType(propOrder = { "name", "farbe", "istAmZug", "bedienung", "nummer" })
public class SpielerWrapper {

	private String name;
	private FarbEnum farbe;
	private boolean istAmZug;
	private String bedienung = null;
	private int nummer;
	
	public SpielerWrapper(){
		this.name ="nameless";
		this.farbe = null;
		this.istAmZug = false;
		this.bedienung = null;//
		this.nummer = 0;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FarbEnum getFarbe() {
		return this.farbe;
	}

	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	public boolean getIstAmZug() {
		return istAmZug;
	}

	public void setIstAmZug(boolean istAmZug) {
		this.istAmZug = istAmZug;
	}

	public String getBedienung() {
		return this.bedienung;
	}

	public void setBedienung(String bedienung) {
		this.bedienung = bedienung;
	}

	public int getNummer() {
		return this.nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	@Override
	public String toString(){
		return String.format("%s - %s - %s - %s",this.name, this.farbe.toString(), this.istAmZug, this.bedienung);
	}

}
