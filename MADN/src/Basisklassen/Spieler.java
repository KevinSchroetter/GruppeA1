package Basisklassen;

import java.util.ArrayList;
import Spiel.*;

/**
 * Die Klasse Spieler für das MADN-Projekt Sie beinhaltet alle Attribute und
 * Methoden, auf die ein Spieler später im Spiel Zugriff erhält.
 * 
 * @author Kevin Schrötter
 * @version 1.3
 *
 */
public class Spieler {
	/**
	 * Ein Spieler bekommt eine Spielernummer zugewiesen. Diese wird statisch
	 * über den Konstruktor inkrementiert und darf nicht größer als 4 werden.
	 */
	private static int spielernummer = 0;
	/**
	 * Ein Spieler hat eine Spielernummer
	 */
	private int meineNummer = 0;
	/**
	 * Jeder Spieler erhält einen namen vom Typ String
	 */
	private String name;
	/**
	 * Ein Spieler darf sich eine Spielerfarbe aus der Klasse FarbEnum
	 * heraussuchen, um damit im Spiel MADN identifiziert werden zu können.
	 */
	private FarbEnum farbe = null;
	/**
	 * Ein Spieler erhält Kenntnis darüber, was seine Startfelder sind um diese
	 * den Figuren zuzuweisen.
	 */
	private Startfeld[] startFelder = null;
	/**
	 * Ein Spieler erhält Kenntnis darüber, was seine Endfelder sind um diese
	 * den Figuren zuzuweisen.
	 */
	private Endfeld[] endFelder = null;
	/**
	 * Ein Spieler kennt zu jedem Zeitpunkt immer genau 4 figuren vom Typ
	 * Spielfigur
	 */
	private Spielfigur figuren[] = new Spielfigur[4];
	/**
	 * In diesem Attribut wird eine Figur vom Typ Spielfigur gespeichert. Sie
	 * wird benötigt, um einen Spielzug auszuführen.
	 */
	private Spielfigur zugFigur = null;
	/**
	 * Ein Spieler hat immer Kenntnis über einen würfel vom Typ Würfel, mit dem
	 * er sein Spiel bestreitet
	 */
	private Würfel meinWürfel = new Würfel();
	/**
	 * Ein Spieler kann von einem Menschen gesteuert werden. In dem Fall nimmt
	 * das Attribut bedienung den Wert null an. Er kann jedoch auch von einer
	 * Künstlichen Intelligenz KI gesteuert werden, die als Elementklasse der
	 * Klasse Spieler existiert. Eine KI kann entweder aggressives oder
	 * defensives Verhalten aufweisen.
	 */
	private KI bedienung = null;
	/**
	 * Ein Spieler hat kenntnis darüber, ob er gerade an einem Spielzug
	 * teilnimmt oder nicht.
	 */
	private boolean amZug = false;
	/**
	 * Ein Spieler weiß, ob er noch Teil des Spiels ist oder ob er bereits alle
	 * Figuren in die Zielfelder gebracht hat.
	 */
	private boolean imSpiel = true;
	/** Ein Spieler muss das ein Interface iBediener kennen, damit die KI Zugriff auf die Methoden
	 *  des Interfaces hat.
	 */
	// Spiel muss noch im Konstruktor übergeben werden!
	private iBediener iB =new Spiel();

	/**
	 * Elementklasse der Klasse Spieler. Hier soll später der Algorithmus für
	 * die Künstliche Intelligenz eingefügt werden. Sie wird später zwei
	 * abgeleitete Klassen für aggressives und defensives Verhalten bekommen.
	 * 
	 * @author Kevin Schrötter
	 * @version 1.0
	 */
	public abstract class KI {
		/**
		 * Konstruktor für einen Spieler mit "Künstlicher Intelligenz", der
		 * entweder aggressiv oder passiv sein kann. Mit dem Attribut bedienung
		 * soll später im Spiel ausgewählt werden können, ob ein menschlicher
		 * Spieler über die Klasse Spieler oder ein Computergegener mit einer
		 * künstlichen Intelligenz ober die Elementklasse Spieler.KI erstellt
		 * wird.
		 * 
		 * @param meinSpieler
		 *            - Spielerobjekt, das der KI zugewiesen wird.
		 * @param verhalten
		 *            - String, über den die Art der KI später bestimmt wird.
		 */
		private Spieler meinSpieler = null;

		/**
		 * Konstruktor für eine Künstliche Intelligenz mit speziellem Verhalten
		 * 
		 * @param s
		 *            - Typ Spieler, setzt eine Referenz auf den Spieler
		 */
		public KI(Spieler s) {
			meinSpieler.setBedienung(this);

		}
		/**
		 * Abstrakte Methode, die in den spezifischen KIs aggressiv und defensiv implementiert wird. 
		 * Sie soll die Entscheidungen der KI steuern. 
		 */
		public abstract void zugWählen();

	}
	/**
	 * Elementklasse der Klasse Spieler, erbt von KI. Dies ist die aggressiv agierende KI-Klasse.
	 * @author Anna Rosa
	 *
	 */
	public class KI_Aggressiv extends KI {
		/**
		 * Konstruktor der aggressiven KI, wird durch den Spieler-Konstruktor aufgerufen.
		 * @param s-Spieler, der eine KI werden soll
		 */
		public KI_Aggressiv(Spieler s) {
			super(s);
		}
		/**
		 * Methode, die würfelt und dann nach den Prioritäten entscheidet, welche Figur laufen soll.
		 * Mögliche Fälle:  1. Eine Figur kann schlagen und dabei spawnen, 2. Figur kann nur schlagen -> 
		 * am weitesten gelaufene Figur läuft , 3. Keine Figur kann schlagen, aber eine Figur kann spawnen, 
		 * 4. Keine Figur kann schlagen oder spawnen, dann läuft die Figur, die am Weitesten vorne ist.
		 */
		public void zugWählen() {
			int augenzahl = iB.rollTheDice();
			ArrayList<Spielfigur> kannZiehen = iB.ausgabeZugFiguren();
			ArrayList<Spielfigur> kannSchlagen = new<Spielfigur> ArrayList();

			for (Spielfigur figur : kannZiehen) {
				if (figur.getKannSchlagen() == true) {
					kannSchlagen.add(figur);
				}

			}
			if (!kannSchlagen.isEmpty()) {
				Spielfigur[] amWeitesten = new Spielfigur[kannSchlagen.size()];
				for (int i = 0; i < kannSchlagen.size(); i++) {
					Spielfigur figur = kannSchlagen.get(i);
					if (figur.getIstGespawnt() == false) {
						String id = "" + figur.getID();
						iB.zugDurchführen(id);
						return;
					}
					amWeitesten[i] = figur;

				}
				for (int i = 0; i < amWeitesten.length - 1; i++) {
					if (amWeitesten[i].getFelderGelaufen() > amWeitesten[i + 1]
							.getFelderGelaufen()) {
						Spielfigur temp = amWeitesten[i + 1];
						amWeitesten[i] = amWeitesten[i + 1];
						amWeitesten[i + 1] = temp;
					}
				}
				String id = "" + amWeitesten[amWeitesten.length - 1].getID();
				iB.zugDurchführen(id);
				return;
			} else if (kannSchlagen.isEmpty()) {
				for (Spielfigur figur : kannZiehen) {
					if (figur.binIchGespawnt() == false) {
						String id = "" + figur.getID();
						iB.zugDurchführen(id);
						return;
					}
				}

				Spielfigur[] amWeitesten = new Spielfigur[kannZiehen.size()];
				for (int i = 0; i < kannZiehen.size(); i++) {
					amWeitesten[i] = kannZiehen.get(i);
				}
				for (int i = 0; i < amWeitesten.length; i++) {
					if (amWeitesten[i].getFelderGelaufen() > amWeitesten[i + 1]
							.getFelderGelaufen()) {
						Spielfigur temp = amWeitesten[i + 1];
						amWeitesten[i] = amWeitesten[i + 1];
						amWeitesten[i + 1] = temp;
					}
				}
				String id = "" + amWeitesten[amWeitesten.length - 1].getID();
				iB.zugDurchführen(id);
			}

		}
	}
	
	/**
	 * Elementklasse der Klasse Spieler, erbt von KI. Dies ist die defensiv agierende KI-Klasse.
	 * @author Anna Rosa
	 *
	 */
	public class KI_Defensiv extends KI {
		/**
		 * Konstruktor der defensiven KI, wird durch den Spieler-Konstruktor aufgerufen.
		 * @param s-Spieler, der eine KI werden soll
		 */
		public KI_Defensiv(Spieler s) {
			super(s);
		}
		/**
		 * Methode, die würfelt und dann nach den Prioritäten entscheidet, welche Figur laufen soll.
		 * Mögliche Fälle: 1. Es ist mindestens eine Figur im Spiel, dann läuft die Spielfigur, die noch im
		 * Spiel und schon am Weitesten gelaufen ist, 2. es ist keine Figur aktiv im Spiel, dann wird 
		 * gespawnt. Die dritte Priorität schlagen kann vernachlässigt werden, da entweder die weiteste
		 * Figur zieht, egal ob sie schlagen kann oder nicht, oder irgendeinen Figur spawnt und mit Glück dabei
		 * schlägt.
		 */
		public void zugWählen(){
			int augenzahl = iB.rollTheDice();
			ArrayList<Spielfigur> kannZiehen = iB.ausgabeZugFiguren();
			Spielfigur[] amWeitesten = new Spielfigur[kannZiehen.size()];
			for (int i = 0; i < kannZiehen.size(); i++) {
				amWeitesten[i] = kannZiehen.get(i);
			}
			for (int i = 0; i < amWeitesten.length; i++) {
				if (amWeitesten[i].getFelderGelaufen() > amWeitesten[i + 1]
						.getFelderGelaufen()) {
					Spielfigur temp = amWeitesten[i + 1];
					amWeitesten[i] = amWeitesten[i + 1];
					amWeitesten[i + 1] = temp;
				}
			}
			String id = "" + amWeitesten[amWeitesten.length - 1].getID();
			iB.zugDurchführen(id);
						
		}

	}

	/**
	 * Konstruktor zum erstellen eines Objektes, das von einem Menschen
	 * gesteuert wird. Ein Spieler kann nicht ohne Name, Spielerfarbe und Würfel
	 * existieren. Zusätzlich wird eine statische Spielernummer beim erstellen
	 * eines Objektes inkrementiert, durch diee in Spieler identifiziert werden
	 * kann.
	 * 
	 * @param name
	 *            - Der Name vom Typ String, den sich ein Spieler geben darf.
	 * @param farbe
	 *            - Eine Farbe vom Typ FarbEnum, die sich ein Spieler zu Beginn
	 *            des Spiels aussuchen darf.
	 * @param startfelder
	 *            - Die Zugehöreigen Startfelder eines Spielers, auf die die
	 *            Figuren gesetzt werden.
	 * @param endfelder
	 *            - Die Zugehörigen Endfelder eines Spielers, auf die die
	 *            Figuren ins Ziel kommen.
	 */
	public Spieler(String name, FarbEnum farbe, Startfeld[] startfelder,
			Endfeld[] endfelder) {
		setSpielernummer();
		setMeineNummer();
		setName(name);
		setFarbe(farbe);
		setStartFelder(startfelder);
		setEndFelder(endfelder);
		setFiguren(startfelder);
	}

	/**
	 * Zweiter Konstruktor, über den ein KI Spieler erstellt werden kann. Die
	 * Anforderungen sind die selben, wie bereits im ersten Konstruktor. Als
	 * Erweiterung kommt jedoch die Variable "bedienung" hinzu, über die dem
	 * KI-SPieler eine Verhaltensweise zugewiesen werden kann.
	 * 
	 * @param name
	 *            - Name des Spielers vom Typ String
	 * @param farbe
	 *            - Farbe des Spielers vom Typ FarbEnum
	 * @param startfelder
	 *            - Die Zugehöreigen Startfelder eines Spielers, auf die die
	 *            Figuren gesetzt werden.
	 * @param endfelder
	 *            - Die Zugehörigen Endfelder eines Spielers, auf die die
	 *            Figuren ins Ziel kommen.
	 * @param verhalten
	 *            - Bedienung des Spielers vom Typ String, über den eine
	 *            Künstliche Intelligenz zugeweisen wird (aggressiv oder
	 *            defensiv).
	 */
	public Spieler(String name, FarbEnum farbe, Startfeld[] startfelder,
			Endfeld[] endfelder, String verhalten) {

		KI bot = null;
		if (verhalten.equals("aggressiv")) {
			bot = new KI_Aggressiv(this);
		} else if (verhalten.equals("defensiv")) {
			bot = new KI_Defensiv(this);
		} else
			throw new IllegalArgumentException(
					"KI darf nur aggressiv oder defensiv sein!");
	}

	/**
	 * Setter für die Spielernummer. Diese wird inkrementiert und darf nicht
	 * größer als 4 werden.
	 */
	public void setSpielernummer() {
		if (getSpielernummer() >= 4)
			throw new RuntimeException("Maximale Spieleranzahl erreicht");
		spielernummer++;
	}

	/**
	 * Getter für die Spielernummer
	 * 
	 * @return spielernummer - Gibt die spielernummer vom Typ int zurück.
	 */
	public int getSpielernummer() {
		return spielernummer;
	}

	/**
	 * Setter für den Namen. Dieser muss mindestens aus zwei Zeichen bestehen
	 * und darf maximal 10 Zeichen lang sein. Es darf jedes Zeichen verwendet
	 * werden.
	 * 
	 * @param name
	 *            - Der Name des Spielers vom Typ String
	 */
	public void setName(String name) {
		if (name.length() < 2 && name.length() < 10)
			throw new RuntimeException("Ungültiger Spielername");
		this.name = name;
	}

	/**
	 * Getter für den Spielernamen.
	 * 
	 * @return name - Gibt den Namen des Spielers vom typ String zurück.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter für die Spielerfarbe. Setzt die Spielerfarbe mit Hilfe der enum
	 * FarbEnum.
	 * 
	 * @param farbe
	 *            - Farbe des Spielers vom Typ FarbEnum.
	 */
	public void setFarbe(FarbEnum farbe) {
		this.farbe = farbe;
	}

	/**
	 * Getter für die Spielerfarbe
	 * 
	 * @return farbe - Gibt das Attribut farbe vom Typ FarbEnum zurück.
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * Setter für die Startfelder, die benötigt werden, um den Spielfiguren bei
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
	 * Getter für ein Startfeld. Hiermit kann auf die bekannten Startfelder
	 * zugegriffen werden.
	 * 
	 * @param startFeld
	 *            - Typ int als Index zur Rückgabe eines bestimmten Startfeldes.
	 * @return Startfeld - das gewählte Startfeld
	 */
	public Startfeld getStartFelder(int startFeld) {
		if (startFeld < 1 | startFeld > 4)
			throw new RuntimeException(
					"Es können nur startfelder 1-4 angesprochen werden!");
		return startFelder[startFeld - 1];
	}

	/**
	 * Setter für die Endfelder, die benötigt werden, um den Spielfiguren ins
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
	 * Getter für alle Endfelder eines Spielers der zugehörigen Farbe.
	 * 
	 * @return endFelder - Das Endfelder array vom Typ Endfeld.
	 */
	public Endfeld[] getEndFelder() {
		return this.endFelder;
	}

	/**
	 * Setter für die Spielfiguren eines Spielers. Hierbei wird der Konstruktor
	 * der Spielfiguren mit der Farbe des Spielers aufgerufen und den
	 * Spielfiguren ein zugehöriges Startfeld zugewiesen.
	 * 
	 * @param startFelder
	 *            - Array mit den Startfeldern der zugehörigen Farbe für die
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
		else if (getFarbe() == FarbEnum.GRÜN){
			farbID = 3;
			name = "GRÜN ";
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
	 * Getter für die Spielfiguren.
	 * 
	 * @param figur
	 *            - Index vom Typ int, um eine bestimmte Spielfigur
	 *            anzusprechen.
	 * @return figuren - Gibt ein Spielfigurarray zurück, in dem alle
	 *         Spielfiguren des Spielers gespeichert sind.
	 */
	public Spielfigur getFiguren(int figur) {
		if (figur < 1 | figur > 4)
			throw new RuntimeException(
					"Spielfiguren können nur mit den Zahlen 1,2,3 und 4 angesprochen werden!");
		return figuren[figur - 1];
	}

	/**
	 * Setter für eine Spielfigur, mit der gezogen werden soll. Auf die Figuren
	 * kann mit den Zahlen 1-4 zugegriffen werden. Diese werden dann verwendet,
	 * um auf die ArrayIndizes 0-3 zuzugreifen und so ein Spielfigur
	 * zurückzugeben.
	 * 
	 * @param figurID
	 *            - Eine int, bei dem die Werte 1-4 erlaubt sind, um auf eine
	 *            Figur zuzugreifen.
	 */
	public void setZugFigur(Spielfigur figur) {
		if (!figur.getFarbe().equals(getFarbe()))
			throw new RuntimeException(
					"Spielfiguren können nur mit den Zahlen 1,2,3 und 4 angesprochen werden!");
		this.zugFigur = figur;
	}

	/**
	 * Getter für die Spielfigur, mit der ein Spielzug ausgeführt werden soll.
	 * 
	 * @return zugFigur - Die Figur vom Typ Spielfigur, mit der ein Zug
	 *         ausgeführt werden soll.
	 */
	public Spielfigur getZugFigur() {
		return zugFigur;
	}

	/**
	 * Getter für den Würfel des Spielers, mit dem er seinen Zug ausführen kann.
	 * 
	 * @return meinWürfel - Der Würfel eines Spielers vom Typ Würfel
	 */
	public Würfel getMeinWürfel() {
		return meinWürfel;
	}

	/**
	 * Setter für das boolean Attribut amZug
	 * 
	 * @param amZug
	 *            - boolean um festzulegen, ob ein Spieler gerade ziehen darfo
	 *            der nicht.
	 */
	public void setAmZug(boolean amZug) {
		this.amZug = amZug;
	}

	/**
	 * Getter für amZug
	 * 
	 * @return amZug - gibt den aktuellen Wert des boolean Attributs amZug
	 *         zurück.
	 */
	public boolean getAmZug() {
		return amZug;
	}

	/**
	 * Setter für das boolean Attribut imSpiel. TRUE - Spieler ist teil der
	 * MADN-Sitzung FALSE - Spieler hat bereits alle Figuren in die Zielfelder
	 * gebracht
	 * 
	 * @param imSpiel
	 *            - Boolean Wert, mit dem festgelegt werden kann, ob ein Spieler
	 *            generell bei einem Zug übersprungen wird oder nicht.
	 */
	public void setImSpiel(boolean imSpiel) {
		this.imSpiel = imSpiel;
	}

	/**
	 * Getter für das boolean Attribut imSpiel.
	 * 
	 * @return imSpiel - Boolean, der anzeigt, ob sich ein Spieler noch im Spiel
	 *         befindet oder bereits alle Figuren in den Zielfeldern hat.
	 */
	public boolean getImSpiel() {
		return imSpiel;
	}

	/**
	 * Setter für bedienung.
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
	 * Getter für meineNummer
	 * 
	 * @return meineNummer - int Wert
	 */
	public int getMeineNummer() {
		return this.meineNummer;
	}

	/**
	 * Getter für bedienung.
	 * 
	 * @return bedienung - gibt zurück, welche KI eingestellt wurde. Gibt es
	 *         keine KI, so ist die Rückgabe null.
	 */
	public KI getBedienung() {
		return bedienung;
	}

	/**
	 * Override der toString Methode.
	 * 
	 * @return - gibt die Spielernummer, den Spielernamen und die Spielerfarbe
	 *         als String zurück.
	 */
	@Override
	public String toString() {
		return "Spieler " + getMeineNummer() + " Name: " + getName()
				+ " Farbe: " + getFarbe();
	}

	@Override
	/**
	 * Override der equals. Zwei Objekte der Klasse Spieler sind nur dann gleich, wenn die Namen und die Farben dieselben sind.
	 * @param o - Übergebenes Spielerobjekt, das mit this verglichen wird.
	 * @return - gibt zurück, ob zwei miteinander verglichene Spieler gleich sind, oder nicht
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
	 * @return - Ein String, der die toString() der Klasse Figur für jede Figur
	 *         des Spielers zurückgibt.
	 */
	public String figurPositionen() {
		return getFiguren(1).toString() + " " + getFiguren(2).toString() + " "
				+ getFiguren(3).toString() + " " + getFiguren(4).toString();
	}

	/**
	 * Hilfsmethode mit der das Figuren-Array zurückgegeben werden kann.
	 * 
	 * @return figuren - Array vom Typ Spielfigur
	 */
	public Spielfigur[] alleFiguren() {
		return figuren;
	}
	/**
	 * HilfsMethode, um die Anzahl der Figuren des Spielers zu bekommen (Wird in Spiel benötigt, daher public)
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
