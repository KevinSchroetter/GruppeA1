package Basisklassen;

import java.io.Serializable;
import java.util.ArrayList;

import Spiel.*;

/**
 * Die Klasse Spieler fuer das MADN-Projekt Sie beinhaltet alle Attribute und
 * Methoden, auf die ein Spieler spaeter im Spiel Zugriff erhaelt.
 * 
 * @author Kevin Schroetter
 * @version 3.0
 *
 */
public class Spieler implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int spielernummer = 0;

	private int meineNummer = 0;

	private String name;

	private FarbEnum farbe = null;

	private Startfeld[] startFelder = null;

	private Endfeld[] endFelder = null;

	private Spielfigur figuren[] = new Spielfigur[4];

	private Spielfigur zugFigur = null;

	private Wuerfel meinWuerfel = new Wuerfel();
	/**
	 * Ein Spieler kann von einem Menschen gesteuert werden. In dem Fall nimmt
	 * das Attribut bedienung den Wert null an. Er kann jedoch auch von einer
	 * Kuenstlichen Intelligenz KI gesteuert werden, die als Elementklasse der
	 * Klasse Spieler existiert. Eine KI kann entweder aggressives oder
	 * defensives Verhalten aufweisen.
	 */
	private KI bedienung = null;

	private boolean amZug = false;

	private boolean imSpiel = true;
	/** Ein Spieler muss das ein Interface iBediener kennen, damit die KI Zugriff auf die Methoden
	 *  des Interfaces hat.
	 */
	
	private iBediener iB;

	/**
	

	/**
	 * Konstruktor zum erstellen eines Objektes, das von einem Menschen
	 * gesteuert wird. Ein Spieler kann nicht ohne Name, Spielerfarbe und Wuerfel
	 * existieren. Zusaetzlich wird eine statische Spielernummer beim erstellen
	 * eines Objektes inkrementiert, durch diee in Spieler identifiziert werden
	 * kann.
	 * 
	 * @param name - Der Name vom Typ String, den sich ein Spieler geben darf.
	 * @param farbe - Eine Farbe vom Typ FarbEnum, die sich ein Spieler zu Beginn des Spiels aussuchen darf.
	 * @param startfelder - Die Zugehoerigen Startfelder eines Spielers, auf die die Figuren gesetzt werden.
	 * @param endfelder - Die Zugehoerigen Endfelder eines Spielers, auf die die Figuren ins Ziel kommen.
	 * @param s - Spiel, das der Spieler kennen muss als KI
	 */
	public Spieler(String name, FarbEnum farbe, Startfeld[] startfelder,Endfeld[] endfelder, Spiel s) {
		setSpielernummer();
		setMeineNummer();
		setName(name);
		setFarbe(farbe);
		setStartFelder(startfelder);
		setEndFelder(endfelder);
		setFiguren(startfelder);
		setIBediener(s);
	}

	/**
	 * Zweiter Konstruktor, ueber den ein KI Spieler erstellt werden kann. Die
	 * Anforderungen sind die selben, wie bereits im ersten Konstruktor. Als
	 * Erweiterung kommt jedoch die Variable "bedienung" hinzu, ueber die dem
	 * KI-SPieler eine Verhaltensweise zugewiesen werden kann.
	 * 
	 * @param name - Name des Spielers vom Typ String
	 * @param farbe - Farbe des Spielers vom Typ FarbEnum
	 * @param startfelder - Die Zugehoerigen Startfelder eines Spielers, auf die dieFiguren gesetzt werden.
	 * @param endfelder - Die Zugehoerigen Endfelder eines Spielers, auf die die Figuren ins Ziel kommen.
	 * @param verhalten - Bedienung des Spielers vom Typ String, ueber den eine Kuenstliche Intelligenz zugeweisen wird (aggressiv oder defensiv).
	 * @param s - Spiel, das der Spieler kennen muss als KI
	 */
	public Spieler(String name, FarbEnum farbe, Startfeld[] startfelder,Endfeld[] endfelder, String verhalten, Spiel s) {
		
		this(name, farbe, startfelder, endfelder, s);		
		KI bot = null;
		if (verhalten.equals("aggressiv")) {
			bot = new KI_Aggressiv(this);
			this.setBedienung(bot);
		} else if (verhalten.equals("defensiv")) {
			bot = new KI_Defensiv(this);
			this.setBedienung(bot);
		} else
			throw new IllegalArgumentException(
					"KI darf nur aggressiv oder defensiv sein!");
	}

	/**
	 * Setter fuer die Spielernummer. Diese wird inkrementiert und darf nicht
	 * groesser als 4 werden.
	 */
	public void setSpielernummer() {
		if (getSpielernummer() >= 4)
			throw new RuntimeException("Maximale Spieleranzahl erreicht");
		spielernummer++;
	}

	/**
	 * Getter fuer die Spielernummer
	 * 
	 * @return spielernummer - Gibt die spielernummer vom Typ int zurueck.
	 */
	public int getSpielernummer() {
		return spielernummer;
	}

	/**
	 * Setter fuer den Namen. Dieser muss mindestens aus zwei Zeichen bestehen
	 * und darf maximal 10 Zeichen lang sein. Es darf jedes Zeichen verwendet
	 * werden.
	 * 
	 * @param name
	 *            - Der Name des Spielers vom Typ String
	 */
	public void setName(String name) {
		if (name.length() < 2 && name.length() < 10)
			throw new RuntimeException("Ungueltiger Spielername");
		this.name = name;
	}

	/**
	 * Getter fuer den Spielernamen.
	 * 
	 * @return name - Gibt den Namen des Spielers vom typ String zurueck.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter fuer die Spielerfarbe. Setzt die Spielerfarbe mit Hilfe der enum
	 * FarbEnum.
	 * 
	 * @param farbe
	 *            - Farbe des Spielers vom Typ FarbEnum.
	 */
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	/**
	 * Getter fuer die Spielerfarbe
	 * 
	 * @return farbe - Gibt das Attribut farbe vom Typ FarbEnum zurueck.
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * Setter fuer die Startfelder, die benoetigt werden, um den Spielfiguren bei
	 * der Erstellung das korrekte Startfeld mit der Spielerfarbe zuzuweisen.
	 * 
	 * @param startFelder
	 *            - Array vom Typ Startfeld.
	 */
	public void setStartFelder(Startfeld[] startFelder) {
		for (Startfeld sf : startFelder) {
			if (getFarbe() != sf.getFarbe())
				throw new RuntimeException(
						"Ein Spieler kennt nur die Startfelder seiner Farbe!");
		}
		this.startFelder = startFelder;
	}

	/**
	 * Getter fuer ein Startfeld. Hiermit kann auf die bekannten Startfelder
	 * zugegriffen werden.
	 * 
	 * @param startFeld
	 *            - Typ int als Index zur Rueckgabe eines bestimmten Startfeldes.
	 * @return Startfeld - das gewaehlte Startfeld
	 */
	public Startfeld getStartFelder(int startFeld) {
		if (startFeld < 1 | startFeld > 4)
			throw new RuntimeException(
					"Es koennen nur startfelder 1-4 angesprochen werden!");
		return startFelder[startFeld - 1];
	}

	/**
	 * Setter fuer die Endfelder, die benoetigt werden, um den Spielfiguren ins
	 * Ziel der korrekten Farbe zu bringen.
	 * 
	 * @param endFelder
	 *            - Array vom Typ Endfeld.
	 */
	public void setEndFelder(Endfeld[] endFelder) {
		for (Endfeld ef : endFelder) {
			if (getFarbe() != ef.getFarbe())
				throw new RuntimeException(
						"Ein Spieler kennt nur die Endfelder seiner Farbe!");
		}
		this.endFelder = endFelder;
	}

	/**
	 * Getter fuer alle Endfelder eines Spielers der zugehoerigen Farbe.
	 * 
	 * @return endFelder - Das Endfelder array vom Typ Endfeld.
	 */
	public Endfeld[] getEndFelder() {
		return this.endFelder;
	}

	/**
	 * Setter fuer die Spielfiguren eines Spielers. Hierbei wird der Konstruktor
	 * der Spielfiguren mit der Farbe des Spielers aufgerufen und den
	 * Spielfiguren ein zugehoeriges Startfeld zugewiesen.
	 * 
	 * @param startFelder
	 *            - Array mit den Startfeldern der zugehoerigen Farbe fuer die
	 *            Figuren des Spielers
	 */
	public void setFiguren(Startfeld[] startFelder) {
		if (getFarbe() == null)
			throw new RuntimeException("Es muss eine Farbe vergeben sein!");
		int figCounter=1;
		String name=null;
		int farbID = 0;
		if (getFarbe() == FarbEnum.ROT){
			farbID = 1;
			name = "ROT ";
		}
		else if (getFarbe() == FarbEnum.BLAU){
			farbID = 2;
			name = "BLAU ";
		}
		else if (getFarbe() == FarbEnum.GRUEN){
			farbID = 3;
			name = "GRUEN ";
		}
		else if (getFarbe() == FarbEnum.GELB){
			farbID = 4;
			name = "GELB ";
		}

		for (int i = 0; i < 4; i++) {
			figuren[i] = new Spielfigur(farbID,name+figCounter);
			figCounter++;
			figuren[i].setMeinFeld(startFelder[i]);
			figuren[i].setIstGespawnt(figuren[i].binIchGespawnt());
			figuren[i].setBinIchAufEndposition(false);
		}
	}

	/**
	 * Getter fuer die Spielfiguren.
	 * 
	 * @param figur
	 *            - Index vom Typ int, um eine bestimmte Spielfigur
	 *            anzusprechen.
	 * @return figuren - Gibt ein Spielfigurarray zurueck, in dem alle
	 *         Spielfiguren des Spielers gespeichert sind.
	 */
	public Spielfigur getFiguren(int figur) {
		if (figur < 1 | figur > 4)
			throw new RuntimeException(
					"Spielfiguren koennen nur mit den Zahlen 1,2,3 und 4 angesprochen werden!");
		return figuren[figur - 1];
	}

	/**
	 * Setter fuer eine Spielfigur, mit der gezogen werden soll. Auf die Figuren
	 * kann mit den Zahlen 1-4 zugegriffen werden. Diese werden dann verwendet,
	 * um auf die ArrayIndizes 0-3 zuzugreifen und so ein Spielfigur
	 * zurueckzugeben.
	 * 
	 * @param figur - Ein int, bei dem die Werte 1-4 erlaubt sind, um auf eine Figur zuzugreifen.
	 */
	public void setZugFigur(Spielfigur figur) {
		if (!figur.getFarbe().equals(getFarbe()))
			throw new RuntimeException(
					"Spielfiguren koennen nur mit den Zahlen 1,2,3 und 4 angesprochen werden!");
		this.zugFigur = figur;
	}

	/**
	 * Getter fuer die Spielfigur, mit der ein Spielzug ausgefuehrt werden soll.
	 * 
	 * @return zugFigur - Die Figur vom Typ Spielfigur, mit der ein Zug
	 *         ausgefuehrt werden soll.
	 */
	public Spielfigur getZugFigur() {
		return zugFigur;
	}

	/**
	 * Getter fuer den Wuerfel des Spielers, mit dem er seinen Zug ausfuehren kann.
	 * 
	 * @return meinWuerfel - Der Wuerfel eines Spielers vom Typ Wuerfel
	 */
	public Wuerfel getMeinWuerfel() {
		return meinWuerfel;
	}

	/**
	 * Setter fuer das boolean Attribut amZug
	 * 
	 * @param amZug
	 *            - boolean um festzulegen, ob ein Spieler gerade ziehen darfo
	 *            der nicht.
	 */
	public void setAmZug(boolean amZug) {
		this.amZug = amZug;
	}

	/**
	 * Getter fuer amZug
	 * 
	 * @return amZug - gibt den aktuellen Wert des boolean Attributs amZug
	 *         zurueck.
	 */
	public boolean getAmZug() {
		return amZug;
	}

	/**
	 * Setter fuer das boolean Attribut imSpiel. TRUE - Spieler ist teil der
	 * MADN-Sitzung FALSE - Spieler hat bereits alle Figuren in die Zielfelder
	 * gebracht
	 * 
	 * @param imSpiel
	 *            - Boolean Wert, mit dem festgelegt werden kann, ob ein Spieler
	 *            generell bei einem Zug uebersprungen wird oder nicht.
	 */
	public void setImSpiel(boolean imSpiel) {
		this.imSpiel = imSpiel;
	}

	/**
	 * Getter fuer das boolean Attribut imSpiel.
	 * 
	 * @return imSpiel - Boolean, der anzeigt, ob sich ein Spieler noch im Spiel
	 *         befindet oder bereits alle Figuren in den Zielfeldern hat.
	 */
	public boolean getImSpiel() {
		return imSpiel;
	}

	/**
	 * Setter fuer bedienung.
	 * 
	 * @param ki
	 *            - String, der angibt, ob es sich um einen menschlichen
	 *            Spieler, eine aggressive KI oder eine defensive KI handelt.
	 */
	public void setBedienung(KI ki) {
		this.bedienung = ki;
	}

	/**
	 * Setzt meineNummer eines Spielers auf die jeweils aktuelle Anzahl an
	 * erstellten Spielern
	 */
	private void setMeineNummer() {
		this.meineNummer = getSpielernummer();
	}

	/**
	 * Getter fuer meineNummer
	 * 
	 * @return meineNummer - int Wert
	 */
	public int getMeineNummer() {
		return this.meineNummer;
	}

	/**
	 * Getter fuer bedienung.
	 * 
	 * @return bedienung - gibt zurueck, welche KI eingestellt wurde. Gibt es
	 *         keine KI, so ist die Rueckgabe null.
	 */
	public KI getBedienung() {
		return bedienung;
	}
	
	private void setIBediener(Spiel s){
		this.iB=s;
	}
	public iBediener getIBediener(){
		return iB;
	}


	/**
	 * Override der toString Methode.
	 * 
	 * @return - gibt die Spielernummer, den Spielernamen und die Spielerfarbe
	 *         als String zurueck.
	 */
	@Override
	public String toString() {
		return "Spieler " + getMeineNummer() + " Name: " + getName()
				+ " Farbe: " + getFarbe();
	}

	@Override
	/**
	 * Override der equals. Zwei Objekte der Klasse Spieler sind nur dann gleich, wenn die Namen und die Farben dieselben sind.
	 * @param o - Uebergebenes Spielerobjekt, das mit this verglichen wird.
	 * @return - gibt zurueck, ob zwei miteinander verglichene Spieler gleich sind, oder nicht
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Spieler))
			return false;
		else {
			Spieler s = (Spieler) o;
			return s.getName().equals(this.getName())
					&& s.getFarbe().equals(this.getFarbe());
		}
	}

	public boolean alleAufSpawn() {
		boolean erg = false;
		for (int i = 1; i < 4; i++)
			if (getFiguren(i).getIstGespawnt() == true)
				erg = true;
			else {
				erg = false;
				break;
			}
		return erg;

	}

	/**
	 * Methode, die die toString der Figuren aufruft, um die Positionen der
	 * Figuren eines Spielers anzuzeigen.
	 * 
	 * @return - Ein String, der die toString() der Klasse Figur fuer jede Figur
	 *         des Spielers zurueckgibt.
	 */
	public String figurPositionen() {
		return getFiguren(1).toString() + " " + getFiguren(2).toString() + " "
				+ getFiguren(3).toString() + " " + getFiguren(4).toString();
	}

	/**
	 * Hilfsmethode mit der das Figuren-Array zurueckgegeben werden kann.
	 * 
	 * @return figuren - Array vom Typ Spielfigur
	 */
	public Spielfigur[] alleFiguren() {
		return figuren;
	}
	/**
	 * HilfsMethode, um die Anzahl der Figuren des Spielers zu bekommen (Wird in Spiel benoetigt, daher public)
	 * @return anz - int, Anzahl der Figuren des Spielers
	 */
	public int getAnzFiguren() {
		int anz = 0;
		for (Spielfigur sf : alleFiguren())
			if (sf != null)
				anz++;
		return anz;
	}
	/**
	 * HilfsMethode zum Loeschen der static Spielernummer (Wird in Spiel benoetigt, daher public)
	 */
	public void deleteSpielernummer(){
		this.spielernummer=0;
	}
}
