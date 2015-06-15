package Hilfsklassen;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.*;

import Basisklassen.Spieler;


@XmlRootElement(name = "spiel") 
//@XmlAccessorType(XmlAccessType.FIELD)
public class SpielXMLWrapper {


	private ArrayList<SpielerWrapper> spielerListe = new ArrayList<SpielerWrapper>(4);
	private ArrayList<FigurenWrapper> figurenListe = new ArrayList<FigurenWrapper>(16);
	@XmlElement(name = "gestartet", required = true)
	private boolean istGestartet;

	private boolean istBeendet;
	
	public void addSpieler(SpielerWrapper s){
		if(spielerListe.contains(s) || s==null) throw new IllegalArgumentException("Spieler vorhanden oder null!");
		else spielerListe.add(s);
	}
	
	public void setSpieler(ArrayList<SpielerWrapper> liste){
		if(liste == null) throw new IllegalArgumentException("Liste ungültig");
		else this.spielerListe = liste;
	}
	
	@XmlElementWrapper(name = "listeSpieler")
	@XmlElement(name = "spieler", required = true)
	public ArrayList<SpielerWrapper> getSpieler(){
		return this.spielerListe;
	}
	
	public void addFigur(FigurenWrapper f){
		if(figurenListe.contains(f) || f == null) throw new IllegalArgumentException("Figur vorhanden oder null");
		else figurenListe.add(f);
	} 
	
	public void setFiguren(ArrayList<FigurenWrapper> liste){
		if(liste == null) throw new IllegalArgumentException("Liste ungültig");
		else this.figurenListe = liste;
	} 
	
	@XmlElementWrapper(name = "listeFiguren")
	@XmlElement(name = "figur", required = true)
	public ArrayList<FigurenWrapper> getFiguren(){
		return this.figurenListe;		
	}

	public boolean isGestartet() {
		return istGestartet;
	}

	public void setIstGestartet(boolean istGestartet) {
		this.istGestartet = istGestartet;
	}

	public boolean isBeendet() {
		return istBeendet;
	}

	public void setBeendet(boolean istBeendet) {
		this.istBeendet = istBeendet;
	}
	
	
	

}
