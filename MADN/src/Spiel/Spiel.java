package Spiel;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import Basisklassen.*;
import Einstellungen.FarbEnum;
import Einstellungen.PlaySounds;
import Einstellungen.Settings;
import Hilfsklassen.*;

/**
 * Klasse Spiel als Regelwerk fuer MADN. Sie beinhaltet alle notwendigen
 * Methoden, um einen reibungslosen Spielfluss zu gewaehrleisten. In dieser
 * Klasse sind DebugMethoden implementiert, sie sind ganz am Ende der Datei zu
 * finden.
 * 
 * @author Kevin Schroetter, Felix Rosa, Anna Rosa, Alexander Brueckner
 * @version 4.0
 *
 */

public class Spiel implements iBediener, Serializable {

	private static final long serialVersionUID = 1L;

	private static ArrayList<FarbEnum> farben = new ArrayList<FarbEnum>(
			EnumSet.allOf(FarbEnum.class));

	private boolean firstBlood = false;
	private Spielfigur debugFigur;
	private int endSoundCounter = 0;

	private Spieler[] spieler = new Spieler[farben.size()];

	private Spieler istAmZug;

	private boolean zugMoeglich = false;

	protected Spielbrett spielbrett;

	private int anzahlSpieler;

	private boolean hatBegonnen = false;

	private boolean istBeendet = false;

	private int anzWuerfe = 0;

	private int augenzahl = 0;

	private ArrayList<Spielfigur> zugFiguren = new ArrayList<Spielfigur>(0);

	private int anzZugFiguren = 0;

	private boolean alleAufSpawn;

	/**
	 * Konstruktor fuer ein Spiel. Er schreibt ein Spielbrett direkt in das
	 * Attribut spielbrett.
	 */
	public Spiel() {
		setSpielbrett(new Spielbrett());
	}

	/**
	 * Setter fuer IstAmZug, der den Spieler in das Attribut schreiben soll, der
	 * gerade am Zug ist. Es kann nur ein Objekt der Klasse Spieler gespeichert
	 * werden.
	 * 
	 * @param sIAZ
	 *            - Uebergebener Spieler, der gespeichert werden soll.
	 */
	protected void setIstAmZug(Spieler sIAZ) {
		if (!(sIAZ instanceof Spieler))
			throw new RuntimeException(
					"Es kann nur ein Spielerobjekt am Zug sein!");
		this.istAmZug = sIAZ;
	}

	/**
	 * Getter, der den Spieler zurueck gibt, der gerade am Zug ist.
	 * 
	 * @return istAmZug - Ein Objekt der Klasse Spieler.
	 */
	public Spieler getIstAmZug() {
		if (getHatBegonnen() == false)
			throw new RuntimeException("Spiel hat noch nicht gestartet!");
		return this.istAmZug;
	}

	/**
	 * Setter fuer zugMoeglich
	 * 
	 * @param zugMoeglich
	 *            - true oder false boolean
	 */
	void setZugMoeglich(boolean zugMoeglich) {
		this.zugMoeglich = zugMoeglich;
	}

	/**
	 * Getter fuer zugMoeglich
	 * 
	 * @return zugMoeglich - true oder false boolean
	 */
	public boolean getZugMoeglich() {
		return this.zugMoeglich;
	}

	/**
	 * Setter fuer das Spielbrett Es wird nur gesetzt, wenn es sich beim
	 * uebergabeparameter um ein Objekt der Klasse Spielbrett handelt.
	 * 
	 * @param spielbrett
	 *            - Objekt der Klasse Spielbrett.
	 */
	private void setSpielbrett(Spielbrett spielbrett) {
		if (!(spielbrett instanceof Spielbrett))
			throw new RuntimeException("Kein Spielfeld gesetzt");
		this.spielbrett = spielbrett;
	}

	/**
	 * Getter fuer das Spielbrett.
	 * 
	 * @return spielbrett - Objekt der Klasse Spielbrett.
	 */
	protected Spielbrett getSpielbrett() {
		return this.spielbrett;
	}

	/**
	 * Setter fuer die Anzahl der teilnehmenden Spieler, der nur inkrementiert.
	 */
	private void incAnzahlSpieler() {
		this.anzahlSpieler++;
	}

	/**
	 * Getter fuer anzahlSpieler
	 * 
	 * @return anzahlSpieler - Anzahl der Spieler vom Typ int
	 */
	protected int getAnzahlSpieler() {
		return this.anzahlSpieler;
	}

	/**
	 * Setter fuer hatBegonnen
	 * 
	 * @param hatBegonnen
	 *            - boolean
	 */
	private void setHatBegonnen(boolean hatBegonnen) {
		this.hatBegonnen = hatBegonnen;
	}

	/**
	 * Getter fuer hatBegonnen
	 * 
	 * @return hatbegonnen - boolean
	 */
	protected boolean getHatBegonnen() {
		return this.hatBegonnen;
	}

	/**
	 * Setter fuer istBeendet
	 * 
	 * @param istBeendet
	 *            - boolean
	 */
	private void setIstBeendet(boolean istBeendet) {
		this.istBeendet = istBeendet;
	}

	/**
	 * Getter fuer istBeendet
	 * 
	 * @return istBeendet - boolean
	 */
	protected boolean getIstBeendet() {
		return this.istBeendet;
	}

	/**
	 * Setter fuer AnzWuerfe, der die Variable inkrementiert.
	 */
	private void incAnzWuerfe() {
		this.anzWuerfe++;
	}

	/**
	 * Getter fuer anzWuerfe
	 * 
	 * @return anzWuerfe - int
	 */
	private int getAnzWuerfe() {
		return this.anzWuerfe;
	}

	/**
	 * Setter fuer die Augenzahl HINWEIS: DebugMethode zu Testzwecken als
	 * hilfsmethode am Ende der Datei
	 * 
	 * @param augenzahl
	 *            - Augenzahl als int
	 */
	private void setAugenzahl(int augenzahl) {
		if (augenzahl < 1 || augenzahl > 6)
			throw new RuntimeException("Fehlerhaftes Wuerfelergebnis");
		this.augenzahl = augenzahl;
	}

	/**
	 * Getter fuer die Augenzahl
	 * 
	 * @return augenzahl - int Wert
	 */
	private int getAugenzahl() {
		return this.augenzahl;
	}

	/**
	 * Setter fuer die Anzahl der vorhandenen zugFiguren, inkrementiert
	 */
	private void incAnzZugFiguren() {
		if (this.anzZugFiguren < 0 || this.anzZugFiguren > farben.size())
			throw new RuntimeException("Fehler: ZugFigurenLimit erreicht!");
		this.anzZugFiguren++;
	}

	/**
	 * ZusatzMethode zum loeschen der anzZugFiguren
	 */
	private void deleteAnzZugFiguren() {
		this.anzZugFiguren = 0;
	}

	/**
	 * Getter fuer anzTugFiguren
	 * 
	 * @return anzZugFiguren - int Wert
	 */
	private int getAnzZugFiguren() {
		return this.anzZugFiguren;
	}

	/**
	 * Setter, der Figuren in einer ArrayList speichert, falls sie fuer einen
	 * Zug infrage kommen. uebergabeparameter muss vom Typ Figur sein Die Farbe
	 * der uebergebenen Figur muss mit der Farbe des Spielers uebereinstimmen,
	 * der gerade am Zug ist. Es koennen maximal maxGamer Figuren in der Lage
	 * sein, zu ziehen.
	 * 
	 * @param figur
	 *            - Objekt vom Typ Figur
	 */
	private void setZugFiguren(Spielfigur figur) {
		if (!(figur instanceof Spielfigur))
			throw new RuntimeException("Keine Spielfigur ausgewaehlt");
		if (!(figur.getFarbe().equals(getIstAmZug().getFarbe())))
			throw new RuntimeException("Ungueltige Figur ausgewaehlt!");
		if (getAnzZugFiguren() > Settings.maxFiguren)
			throw new RuntimeException(
					"Maximale Anzahl an fuer einen Zug moeglichen Figuren erreicht!");
		this.zugFiguren.add(figur);
		incAnzZugFiguren();
	}

	/**
	 * Wichtige Methode fuer den Spielverlauf. Sie gibt alle Figuren zurueck,
	 * die ziehen koennen
	 * 
	 * @return zugFiguren - ArrayList mit allen Figuren, die Ziehen koennen
	 */
	protected ArrayList<Spielfigur> alleZugFiguren() {
		return zugFiguren;
	}

	/**
	 * Setter fuer alleAufSpawn
	 * 
	 * @param alleAufSpawn
	 *            - boolean
	 */
	private void setAlleAufSpawn(boolean alleAufSpawn) {
		this.alleAufSpawn = alleAufSpawn;
	}

	/**
	 * Getter fuer alleAufSpawn
	 * 
	 * @return alleAufSpawn - boolean
	 */

	protected boolean getAlleAufSpawn() {
		return this.alleAufSpawn;
	}

	/**
	 * Methode zum Hinzufuegen eines neuen Spielers, solange das Spiel noch
	 * nicht gestartet ist. Sind Spieler im Spiel, so wird Spiel automatisch
	 * begonnen.
	 * 
	 * @param name
	 *            - gewuenschter Name des Spielers
	 * @param farbID
	 *            - gewuenschte Farbe des Spielers
	 * @param verhaltenID
	 *            - Falls null: Menschlicher Spieler, sonst: KI mit dem
	 *            uebergebenen Verhalten;
	 */
	protected boolean spielerHinzufuegen(String name, int farbID,
			int verhaltenID) {
		if (getHatBegonnen() == true)
			throw new RuntimeException("Spiel hat schon begonnen");
		if (getAnzahlSpieler() > farben.size())
			throw new RuntimeException(
					"Maximale Spieleranzahl kann nicht ueberschritten werden!");
		farbID = farbID - 1;
		if (farbID < 0 || farbID > farben.size() - 1)
			throw new RuntimeException("Ungueltige Farbe!");
		FarbEnum farbe = farben.get(farbID);

		String verhalten = null;
		switch (verhaltenID) {
		case 1:
			verhalten = "aggressiv";
			break;
		case 2:
			verhalten = "defensiv";
			break;
		default:
			verhalten = null;
			break;
		}
		for (int i = 0; i <= 3; i++) {
			if (spieler[i] != null) {
				if (farbe.equals(spieler[i].getFarbe()))
					throw new RuntimeException(
							"Farbe "
									+ spieler[i].getFarbe()
									+ " schon vorhanden! Bitte waehlen Sie eine andere Farbe!");
			}
		}
		Startfeld[] startfelder = getSpielbrett().getAlleStartFelderEinerFarbe(
				farbe);
		Endfeld[] endfelder = getSpielbrett().getAlleEndFelderEinerFarbe(farbe);
		if (verhalten == null) {
			spieler[getAnzahlSpieler()] = new Spieler(name, farbe, startfelder,
					endfelder, this);
		} else if (verhalten != null) {
			spieler[getAnzahlSpieler()] = new Spieler(name, farbe, startfelder,
					endfelder, verhalten, this);
		}
		incAnzahlSpieler();
		if (getAnzahlSpieler() == 4) {
			setHatBegonnen(true);
		}
		return true;

	}

	/**
	 * Lädt einen bereits bestehenden Spieler ins Spiel
	 * 
	 * @param loadMe
	 *            - Spieler
	 * @throws IllegalArgumentException
	 * */
	protected void spielerLaden(Spieler loadMe) {
		if (loadMe == null | (!(loadMe instanceof Spieler))) {
			throw new IllegalArgumentException("Spielerobjekt ungültig");
		}
		if (this.getAnzahlSpieler() >= 4)
			throw new RuntimeException("Spielerlimit erreicht!");
		else {
			this.spieler[this.getAnzahlSpieler()] = loadMe;
			this.incAnzahlSpieler();
		}

	}

	/**
	 * Methode, die das Spiel startet, so dass keine Spieler mehr hinzugefuegt
	 * werden koennen. Sie setzt den ersten Spieler im Spieler Array als den
	 * Spieler, der am Zug ist.
	 * 
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	private void startSpiel() throws IOException,
			UnsupportedAudioFileException, LineUnavailableException,
			InterruptedException {
		if (getHatBegonnen() == true)
			throw new RuntimeException("Spiel hat bereits begonnen!");
		if (spieler[0] == null)
			throw new RuntimeException(
					"Ein Spiel braucht mindestens einen Spieler!");
		setHatBegonnen(true);
		// PlaySounds.playStart();
		if (spieler[0] != null) {
			spieler[0].setAmZug(true);
			setIstAmZug(spieler[0]);
		} else
			setIstBeendet(true);
	}

	/**
	 * Methode ermittelt die ZielfeldID des Feldes auf das die ziehenden Figur
	 * ziehen soll.
	 * 
	 * @author Felix Rosa Da in Arrays gearbeitet wird, wird von Zielfeld der
	 *         Wert 1 abgezogen.
	 * @param figur
	 *            - Figur von der die ID des Zielfeldes ermittelte werden soll
	 * @return ZielfeldID - 1 - ID des Zielfeldes -1
	 */
	private int ermittleZielfeldID(Spielfigur figur) {
		Standardfeld aktFeld = (Standardfeld) figur.getMeinFeld();
		int ZielfeldID = Integer.parseInt(aktFeld.getID()) + getAugenzahl();
		if (ZielfeldID == getSpielbrett().getStandardFelder().length) {
			return getSpielbrett().getStandardFelder().length - 1;
		}
		if (ZielfeldID > getSpielbrett().getStandardFelder().length) {
			return (ZielfeldID - 1)
					- getSpielbrett().getStandardFelder().length;
		}
		return ZielfeldID - 1;
	}

	/**
	 * Methode ermittelt die Schritte die eine Figur in ihren Endfeldern laufen
	 * kann.
	 * 
	 * @author Felix Rosa
	 * @param figur
	 *            - Figur von der ermittelt werden soll, wie weit Sie in ihren
	 *            Endfeldern laufen kann.
	 * @return bisherige gelaufene Feld + gewuerfelte Augenzahl abzueglich der
	 *         Spielfeldlaenge
	 */
	private int ermittleEndfeldSchritte(Spielfigur figur) {
		return figur.getFelderGelaufen() + getAugenzahl()
				- getSpielbrett().getStandardFelder().length;

	}

	/**
	 * Methode prueft ob uebergebene Figur in Kombination mit uebergebener
	 * Wuerfelzahl ziehen kann. Dabei wird ueberprueft ob Zug aus Spielfeld ins
	 * Endfeld oder Zuege im Endfeld moeglich sind! Desweiteren wird geprueft ob
	 * eine Figur eine Figur auf einem belegten Feld schlagen kann und setzt das
	 * Attribut figur.kannSchlagen.
	 * 
	 * @author Felix Rosa v. 1.1(kannIchZiehen um Spawn-Abfrage erweitert!)
	 * @param figur
	 *            - figur von der ueberprueft werden soll ob ziehen moeglich ist
	 * @return boolean - true wenn ziehen moeglich, false wenn nicht
	 */
	private boolean kannIchZiehen(Spielfigur figur) {
		Spieler istAmZug = this.getIstAmZug();
		if (figur.getIstGespawnt() == false && getAugenzahl() == 6) {
			// System.out.println("Fall1");
			if (getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).getFigur() == null) {
				// System.out.println("Fall1.1");
				return true;
			} else if (!(getSpielbrett().getSpawnfeld(istAmZug.getFarbe())
					.getFigur().getFarbe().equals(istAmZug.getFarbe()))) {
				// System.out.println("Fall1.2");
				figur.setKannSchlagen(true);
				return true;
			} else if (getSpielbrett().getSpawnfeld(istAmZug.getFarbe())
					.getFigur().getFarbe().equals(istAmZug.getFarbe())) {
				return false;
			}
		} else if (figur.binIchGespawnt() == true
				& (figur.getBinIchAufEndpostion() == false) & figur != null) {
			// System.out.println(ZielfeldID);
			if (figur.getIstImZiel() == true) {
				if (kannZiehenEndfelder(figur, getAugenzahl()) == true) {
					return true;
				} else
					return false;
			}
			if ((figur.getFelderGelaufen() + getAugenzahl()) > getSpielbrett()
					.getStandardFelder().length) {
				if (ermittleEndfeldSchritte(figur) <= 4) {
					if (kannZiehenEndfelder(figur,
							ermittleEndfeldSchritte(figur)) == true) {
						figur.setKannInsZiel(true);
						// System.out.println("Fall2.2");
						return true;
					} else
						return false;
				}
			}
			int ZielfeldID = ermittleZielfeldID(figur);
			Standardfeld Zielfeld = getSpielbrett().getStandardFelder()[ZielfeldID];
			if (Zielfeld.getFigur() == null
					& (figur.getFelderGelaufen() + getAugenzahl()) <= spielbrett
							.getStandardFelder().length) {
				// System.out.println("Fall3.1");
				return true;
			} else if (figur.kannSchlagen(Zielfeld) == true
					& (figur.getFelderGelaufen() + getAugenzahl()) <= spielbrett
							.getStandardFelder().length) {
				// System.out.println("Fall3.2");
				figur.setKannSchlagen(true);
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * @author Anna Rosa Methode, die prueft, ob eine Figur, die entweder auf
	 *         einem Standard- oder Endfeld steht, innerhalb der Endfelder
	 *         ziehen kann. Sie wird nur von der kannZiehen-Methode aufgerufen,
	 *         falls die Figur in die Endfelder einziehen soll.
	 * 
	 * @param figur
	 *            - Figur, deren Zug-Moeglichkeiten abgefragt werden
	 * @param zuZiehen
	 *            anzahl der Zuege, die innerhalb des Endfeldes noch getaetigt
	 *            werden sollen
	 * @return booleanschen Wert, true falls die Figur ziehen kann, false falls
	 *         nicht
	 */
	private boolean kannZiehenEndfelder(Spielfigur figur, int zuZiehen) {
		Endfeld[] endfelderIstAmZug = getIstAmZug().getEndFelder();
		boolean zug = false;
			if (zuZiehen > Settings.maxFiguren)
				return false;
			if(figur.getMeinFeld() instanceof Standardfeld){
				if (zuZiehen == 1) {
					if (endfelderIstAmZug[0].getFigur() == null){
						return true;
					}
					else
						return false;
				}
				if (zuZiehen == 2) {
					if (endfelderIstAmZug[1].getFigur() == null && endfelderIstAmZug[0].getFigur()==null){
						return true;
					}
					else
						return false;
				}
				if (zuZiehen == 3) {
					if (endfelderIstAmZug[2].getFigur() == null && endfelderIstAmZug[1].getFigur()==null && endfelderIstAmZug[0].getFigur()==null) {
						return true;
					}
					else
						return false;
				}
				if (zuZiehen == 4) {
					if (endfelderIstAmZug[3].getFigur() == null && endfelderIstAmZug[2].getFigur()==null && endfelderIstAmZug[1].getFigur()==null && endfelderIstAmZug[0].getFigur()==null) {
						return true;
					}
					else
						return false;
			
				}
			}
			else{
				for (Endfeld e:endfelderIstAmZug){
					if(e.getFigur()!=null){
					if(e == figur.getMeinFeld())System.out.println("== TRUE");
					else
						System.out.println("== FALSE");
					if(e.equals(figur.getMeinFeld()))System.out.println("equals TREU");
					else
						System.out.println("equals FALSE");
					System.out.println("FIGURVORHANDEN FELDNAME: "+e+" FIGUR AUF DIESEM FELD: "+e.getFigur().getName()+" MEINE FIGUR: "+figur.getName()+" MEIN FELD: "+figur.getMeinFeld());
					}
					else{
						
						if(e == figur.getMeinFeld())System.out.println("== TRUE");
						else
							System.out.println("== FALSE");	
						if(e.equals(figur.getMeinFeld()))System.out.println("equals TREU");
						else
							System.out.println("equals FALSE");
						System.out.println("KEINEFIGURVORHANEN! ");
					}
				}
				
				
				if (zuZiehen == 1) {
					if (figur.getMeinFeld()==endfelderIstAmZug[3]){
						return false;
					}
					if (figur.getMeinFeld()==endfelderIstAmZug[2]&&endfelderIstAmZug[3].getFigur()==null){
						return true;
					}
					if(figur.getMeinFeld()==endfelderIstAmZug[1] && endfelderIstAmZug[2].getFigur()==null){
						return true;
					}

					if (figur.getMeinFeld()==endfelderIstAmZug[0] && endfelderIstAmZug[1].getFigur()==null){
						return true;
					}

				}
			
				else if (zuZiehen == 2) {
					if (figur.getMeinFeld()==endfelderIstAmZug[3]){
						return false;
					}
					else if (figur.getMeinFeld()==endfelderIstAmZug[2]){
						return false;
					}
					else if (figur.getMeinFeld()==endfelderIstAmZug[1] && endfelderIstAmZug[2].getFigur()==null && endfelderIstAmZug[3].getFigur()==null){
						return true;
					}
					else if (figur.getMeinFeld()==endfelderIstAmZug[0] && endfelderIstAmZug[1].getFigur()==null && endfelderIstAmZug[2].getFigur()==null){
						return true;
					}					
				}
				
				else if (zuZiehen == 3) {
					if (figur.getMeinFeld()==endfelderIstAmZug[3]){
						return false;
					}
					else if (figur.getMeinFeld()==endfelderIstAmZug[2]){
						return false;
					}
					else if (figur.getMeinFeld()==endfelderIstAmZug[1]){
						return false;
					}
					else if (figur.getMeinFeld()==endfelderIstAmZug[0] && endfelderIstAmZug[1].getFigur()==null & endfelderIstAmZug[2].getFigur()==null && endfelderIstAmZug[3].getFigur()==null){
						return true;
					}
					else{
						return false;
					}
					
				}
				else if (zuZiehen == 4) {
					return false;
				}
			}
			System.out.println("Daraus folgt ZUG = "+zug);
		return zug;
	}

	/**
	 * Laesst ausgewaehlte Figur um die entsprechende Wuerfelzahl ziehen. Die
	 * Methode ruft nach jedem erfolgreichen Ziehen die Methode
	 * incSchritteGelaufen(augenzahl) aus der Klasse Spielfigur auf um den
	 * Schrittzaehler zu erhoehen und naechsterSpieler() um auf den naechsten
	 * ziehenden zu verweisen. 1. Die Figur ist noch nicht gespawnt und es wurde
	 * eine 6 gewuerfelt 1.1 Sollte das Spawnfeld durch keine andere Figur
	 * belegt sein spawnt die Figur auf das Spawnfeld! 1.2 Sollte das Spawnfeld
	 * durch eine Figur der gegnerischen Farbe belegt sein, so wird diese ueber
	 * die Methode schlagenSpawn() geschlagen und die zu spawnende Figur zieht
	 * auf das Spawnfeld! 2. Die Figur ist gespawnt und es wurde eine 6
	 * gewuerfelt! 2.1 Sollte die Figur vor den Endfeldern stehen und in die
	 * Endfelder ziehen koennen, wird ueber ermittleEndfeldSchritte() die Anzahl
	 * der Schritte im Endfeld ermittelt und diese an die Methode
	 * ziehenEndfelder() uebergeben, diese zieht mit der Figur in den
	 * Endfeldern. 2.2 Wenn sich eine gegenerische Figur auf dem Zielfeld
	 * befindet, wird schlagen() aufgerufen und die uebergebene Figur rueckt auf
	 * das Zielfeld! 2.3 Sollte die Figur ziehen koennen und keine gegnerische
	 * Figur auf dem Zielfeld stehen, so zieht sie auf das Zielfeld. 3. Die
	 * Figur ist gespawnt und es wurde eine Zahl zwischen 1 und 5 gewuerfelt!
	 * 3.1 Sollte die Figur auf den Endfeldern stehen, wird die Methode
	 * ziehenEndfelder() aufgerufen und die Figur zieht auf das Zielfeld, dass
	 * sich aus ziehenEndfeld() ergibt. 3.2 Sollte die Figur vor den Endfeldern
	 * stehen und in die Endfelder ziehen koennen, wird ueber
	 * ermittleEndfeldSchritte() die Anzahl der Schritte im Endfeld ermittelt
	 * und diese an die Methode ziehenEndfelder() uebergeben, diese zieht mit
	 * der Figur in den Endfeldern. 3.3 Wenn sich eine gegenerische Figur auf
	 * dem Zielfeld befindet, wird schlagen() aufgerufen und die uebergebene
	 * Figur rueckt auf das Zielfeld! 3.4 Sollte die Figur ziehen koennen und
	 * keine gegnerische Figur auf dem Zielfeld stehen, so zieht sie auf das
	 * Zielfeld.
	 * 
	 * @author Felix Rosa
	 * @param figur
	 *            - Figur mit der gezogen wird
	 * @param augenZahl
	 *            - aktuelle gewuerfelte Augenzahl des Spielers
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	private String ziehen(Spielfigur figur, int augenZahl)
			throws InterruptedException, LineUnavailableException, IOException,
			UnsupportedAudioFileException {
		String targetArea = null;
		// System.out.println("Bin in ziehen");
		for (Spielfigur sf : alleZugFiguren()) {
			sf.setKannSchlagen(false);
		}
		alleZugFiguren().clear();
		deleteAnzZugFiguren();
		Spieler istAmZug = this.getIstAmZug();
		Spielfeld aktFeld = null;
		if (figur.getIstGespawnt() == false && getAugenzahl() == 6) {
			// System.out.println("1ter");
			aktFeld = (Startfeld) figur.getMeinFeld();
			String aktFeldIDS = aktFeld.getID();
			if (getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).getFigur() == null) {
				// System.out.println("Fall: Nicht Gespawnt + 6 gewuerfelt + 1terFall");
				figur.setMeinFeld(getSpielbrett().getSpawnfeld(
						istAmZug.getFarbe()));
				figur.setFelderGelaufen(1);
				figur.setIstGespawnt(true);
				for (int i = 0; i < getSpielbrett().getAlleEndFelderEinerFarbe(
						istAmZug.getFarbe()).length; i++) {
					if (getSpielbrett().getAlleStartFelderEinerFarbe(
							istAmZug.getFarbe())[i].getID().equals(aktFeldIDS)) {
						getSpielbrett().getAlleStartFelderEinerFarbe(
								istAmZug.getFarbe())[i].setFigur(null);
					}
				}
				// System.out.println("Fall: Nicht Gespawnt + 6 gewuerfelt + 1terFall:: ENDE");
			} else if (!(getSpielbrett().getSpawnfeld(istAmZug.getFarbe())
					.getFigur().getFarbe().equals(istAmZug.getFarbe()))) {
				// System.out.println("Fall: Nicht Gespawnt + 6 gewuerfelt + 2terFall");
				targetArea = schlagenSpawn(figur, aktFeldIDS);
				// System.out.println("Fall: Nicht Gespawnt + 6 gewuerfelt + 2terFall :: ENDE");
			}

		} else if (figur.getIstGespawnt() == true && getAugenzahl() == 6) {
			// System.out.println("2ter");
			aktFeld = (Standardfeld) figur.getMeinFeld();
			int ZielfeldID = ermittleZielfeldID(figur);
			int EndfeldSchritte = ermittleEndfeldSchritte(figur);
			int aktFeldID = Integer.parseInt(aktFeld.getID()) - 1;
			/*
			 * if(figur.getIstImZiel()==true){ ziehenEndfelder(figur,
			 * getAugenzahl()); naechsterSpieler(); }
			 */
			if (figur.getKannInsZiel() == true) {
				// System.out.println("Fall: Gespawnt + 6 gewuerfelt + 1terFall");
				ziehenAufEndfelder(figur, EndfeldSchritte);
				figur.setFelderGelaufen(getSpielbrett().getStandardFelder().length
						- figur.getFelderGelaufen());
				figur.setKannInsZiel(false);
				figur.setIstImZiel(true);
				// System.out.println("Fall: Gespawnt + 6 gewuerfelt + 1terFall :: ENDE");
			} else if (getSpielbrett().getStandardFelder()[ZielfeldID]
					.getFigur() != null
					&& !((getSpielbrett().getStandardFelder()[ZielfeldID]
							.getFigur().getFarbe()).equals(istAmZug.getFarbe()))) {
				// System.out.println("Fall: Gespawnt + 6 gewuerfelt + 2terFall");
				targetArea = schlagen(figur, ZielfeldID);
				// System.out.println("Fall: Gespawnt + 6 gewuerfelt + 2terFall :: ENDE");
			} else if (getSpielbrett().getStandardFelder()[ZielfeldID]
					.getFigur() == null) {
				// System.out.println("Fall: Gespawnt + 6 gewuerfelt + 3terFall");
				figur.setMeinFeld(getSpielbrett().getStandardFelder()[ZielfeldID]);
				getSpielbrett().getStandardFelder()[aktFeldID].setFigur(null);
				figur.incSchritteGelaufen(getAugenzahl());
				// System.out.println("Fall: Gespawnt + 6 gewuerfelt + 3terFall :: ENDE");
			}
		} else if (figur.getIstGespawnt() == true && getAugenzahl() != 6) {
			// System.out.println("3ter");
			int EndfeldSchritte = ermittleEndfeldSchritte(figur);
			if (figur.getIstImZiel() == true) {
				// System.out.println("Fall: Gespawnt + !6 gewuerfelt + 1terFall");
				ziehenInEndfelder(figur);
				naechsterSpieler();
				// System.out.println("Naechster spieler gesetzt");
				// System.out.println("Fall: Gespawnt + !6 gewuerfelt + 1terFall :: ENDE");
			} else if (figur.getKannInsZiel() == true) {
				// System.out.println("Fall: Gespawnt + !6 gewuerfelt + 2terFall");
				ziehenAufEndfelder(figur, EndfeldSchritte);
				figur.setFelderGelaufen(getSpielbrett().getStandardFelder().length
						- figur.getFelderGelaufen());
				figur.setKannInsZiel(false);
				figur.setIstImZiel(true);
				naechsterSpieler();
				// System.out.println("Naechster spieler gesetzt");
				// System.out.println("Fall: Gespawnt + !6 gewuerfelt + 2terFall :: ENDE");
			} else if (getSpielbrett().getStandardFelder()[ermittleZielfeldID(figur)]
					.getFigur() != null
					&& (!(getSpielbrett().getStandardFelder()[ermittleZielfeldID(figur)]
							.getFigur().getFarbe().equals(istAmZug.getFarbe())))) {
				// System.out.println("Fall: Gespawnt + !6 gewuerfelt + 3terFall");
				int ZielfeldID = ermittleZielfeldID(figur);
				aktFeld = (Standardfeld) figur.getMeinFeld();
				targetArea = schlagen(figur, ZielfeldID);
				naechsterSpieler();
				// System.out.println("Naechster spieler gesetzt");
				// System.out.println("Fall: Gespawnt + !6 gewuerfelt + 3terFall :: ENDE");
			} else if (getSpielbrett().getStandardFelder()[ermittleZielfeldID(figur)]
					.getFigur() == null) {
				aktFeld = (Standardfeld) figur.getMeinFeld();
				int aktFeldID = Integer.parseInt(aktFeld.getID()) - 1;
				// System.out.println("Fall: Gespawnt + !6 gewuerfelt + 4terFall");
				figur.setMeinFeld(getSpielbrett().getStandardFelder()[ermittleZielfeldID(figur)]);
				getSpielbrett().getStandardFelder()[aktFeldID].setFigur(null);
				figur.incSchritteGelaufen(getAugenzahl());
				naechsterSpieler();
				// System.out.println("Naechster spieler gesetzt");
				// System.out.println("Fall: Gespawnt + !6 gewuerfelt + 4terFall :: ENDE");
			}
		}// IstDasHierNoetig?EsIst23.27UhrMeinGehirnIstDezentZerf****UndIchWeissEsNicht
			// -> throw new
			// BrainException("Ich bin zu dumm fuer RTL! #BoehmiIstDeroehmi");
			// Unnoetig?
			// System.out.println("Figur " + figur.getFelderGelaufen() + " " +
			// figur.getMeinFeld() + " " + figur.getIstGespawnt() +"\n" +"\n" +
			// spielbrett.getStandardFelder()[5].getFigur());
			// System.out.println(figur.getBinIchAufEndpostion());
		aufEndposition(figur);
		// System.out.println(figur.getBinIchAufEndpostion());
		return targetArea;
	}

	/**
	 * Methode die mit einer uebergebenen Figur eine Figur die auf dem Feld mit
	 * der ZielfeldID steht schlaegt. Die geschlagene Figur wird auf ihr
	 * Startfeld zurueckgesetzt, die schlagende Figur zieht auf das Zielfeld
	 * 
	 * @author Felix Rosa
	 * @param figur
	 *            - Figur die schlagen soll
	 * @param ZielfeldID
	 *            - durch gegenerische Figur belegtes Feld, auf die die
	 *            uebergebene Figur laufen soll
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	private String schlagen(Spielfigur figur, int ZielfeldID)
			throws InterruptedException, LineUnavailableException, IOException,
			UnsupportedAudioFileException {
		Spielfigur zuSchlagen = null;
		String targetArea = null;
		Standardfeld aktFeld = (Standardfeld) figur.getMeinFeld();
		FarbEnum myColor = figur.getFarbe();
		int aktFeldID = Integer.parseInt(aktFeld.getID()) - 1;
		zuSchlagen = getSpielbrett().getStandardFelder()[ZielfeldID].getFigur();

		FarbEnum targetColor = zuSchlagen.getFarbe();

		if (!targetColor.equals(myColor)) {
			
			figur.setMeinFeld(getSpielbrett().getStandardFelder()[ZielfeldID]);
			System.err.printf("%s schlägt %s\n", figur, zuSchlagen);
			getSpielbrett().getStandardFelder()[aktFeldID].setFigur(null);
			figur.incSchritteGelaufen(getAugenzahl());
			
			for (int i = 0; i < getSpielbrett().getAlleStartFelderEinerFarbe(
					targetColor).length; i++) {
				if (getSpielbrett().getAlleStartFelderEinerFarbe(targetColor)[i]
						.getFigur() == null) {
					zuSchlagen.setIstGespawnt(false);
					zuSchlagen.resetFelderGelaufen();
					zuSchlagen.setMeinFeld(getSpielbrett()
							.getAlleStartFelderEinerFarbe(targetColor)[i]);
					zuSchlagen.setBinIchAufEndposition(false);
					zuSchlagen.setKannSchlagen(false);
					zuSchlagen.setKannInsZiel(false);
					zuSchlagen.setIstImZiel(false);
					targetArea = zuSchlagen.getMeinFeld().getGuiID();
					break;
				}
			}
		}
		// if (firstBlood == false) {
		// PlaySounds.kill();
		// PlaySounds.firstblood();
		// firstBlood = true;
		// } else
		// PlaySounds.kill();

		return targetArea;
	}

	/**
	 * Methode mit der eine spawnende Figur eine gegenerische Figur die auf dem
	 * Spawnfeld steht schlaegt. Die geschlagene Figur wird auf ihr Startfeld
	 * zurueckgesetzt
	 * 
	 * @author Felix Rosa
	 * @param figur
	 *            - Figur die schlagen soll
	 * @param aktFeldIDS
	 *            - ID des Feldes auf dem spawnende Figur steht
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 */
	private String schlagenSpawn(Spielfigur figur, String aktFeldIDS)
			throws InterruptedException, LineUnavailableException, IOException,
			UnsupportedAudioFileException {
		String targetArea = null;
		Spieler istAmZug = this.getIstAmZug();
		Spielfigur zuSchlagen = null;
		zuSchlagen = getSpielbrett().getSpawnfeld(istAmZug.getFarbe())
				.getFigur();
		getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).setFigur(null);
		figur.setMeinFeld(getSpielbrett().getSpawnfeld(istAmZug.getFarbe()));
		// System.out.println(figur.getMeinFeld());
		figur.setFelderGelaufen(1);
		figur.setIstGespawnt(true);
		FarbEnum myColor = figur.getFarbe();
		FarbEnum targetColor = zuSchlagen.getFarbe();
		for (int i = 0; i < getSpielbrett().getAlleEndFelderEinerFarbe(
				istAmZug.getFarbe()).length; i++) {
			if (getSpielbrett().getAlleStartFelderEinerFarbe(
					istAmZug.getFarbe())[i].getID().equals(aktFeldIDS)) {
				getSpielbrett().getAlleStartFelderEinerFarbe(
						istAmZug.getFarbe())[i].setFigur(null);
			}
		}
		if (!targetColor.equals(myColor))
			for (int i = 0; i < getSpielbrett().getAlleStartFelderEinerFarbe(
					targetColor).length; i++) {
				if (getSpielbrett().getAlleStartFelderEinerFarbe(targetColor)[i]
						.getFigur() == null) {
					zuSchlagen.setIstGespawnt(false);
					zuSchlagen.resetFelderGelaufen();
					zuSchlagen.setMeinFeld(getSpielbrett()
							.getAlleStartFelderEinerFarbe(targetColor)[i]);
					zuSchlagen.setBinIchAufEndposition(false);
					zuSchlagen.setKannSchlagen(false);
					zuSchlagen.setKannInsZiel(false);
					zuSchlagen.setIstImZiel(false);
					targetArea = zuSchlagen.getMeinFeld().getGuiID();

					break;
				}
			}
		// if (firstBlood == false) {
		// PlaySounds.kill();
		// PlaySounds.firstblood();
		// firstBlood = true;
		// } else
		// PlaySounds.kill();
		return targetArea;
	}

	/**
	 * Methode die aufgerufen wird wenn Figur schon in Endfeldern steht. Mit
	 * dieser Methode zieht die Figur!
	 * 
	 * @author Felix Rosa
	 * @param figur
	 *            - Figur mit der gezogen werden soll
	 */
	private void ziehenInEndfelder(Spielfigur figur) {
		Endfeld aktFeld = (Endfeld) figur.getMeinFeld();
		int position = 0;
		for (int i = 0; i < getSpielbrett().getAlleEndFelderEinerFarbe(
				getIstAmZug().getFarbe()).length; i++) {
			if (getSpielbrett().getAlleEndFelderEinerFarbe(
					getIstAmZug().getFarbe())[i].getID()
					.equals(aktFeld.getID())) {
				position = i;
				break;
			}
		}
		figur.setMeinFeld(getSpielbrett().getAlleEndFelderEinerFarbe(
				getIstAmZug().getFarbe())[position + getAugenzahl()]);
		String aktFeldIDS = aktFeld.getID();
		for (int i = 0; i < getSpielbrett().getAlleEndFelderEinerFarbe(
				getIstAmZug().getFarbe()).length; i++) {
			if (getSpielbrett().getAlleEndFelderEinerFarbe(
					getIstAmZug().getFarbe())[i].getID().equals(aktFeldIDS)) {
				getSpielbrett().getAlleEndFelderEinerFarbe(
						getIstAmZug().getFarbe())[i].setFigur(null);
			}
		}
	}

	/**
	 * Methode, die eine Figur um eine bestimmte Anzahl an Zuegen in seinem
	 * Endfeld ziehen laesst.
	 * 
	 * @author Anna Rosa, Felix Rosa
	 * @param figur
	 *            - Die Figur, die ziehen soll
	 * @param augenZahl
	 *            - Anzahl der Zuege, die Figur ziehen soll
	 */
	private void ziehenAufEndfelder(Spielfigur figur, int restSchritte) {
		Spielfeld aktFeld = figur.getMeinFeld();
		String aktFeldIDS = "";
		int aktFeldID = 0;
		if (kannZiehenEndfelder(figur, restSchritte) != true) {
			// System.out.println("Figur kann nicht ziehen!");
			// throw new
			// FigurKannNichtZiehenException("Figur kann nicht ziehen!");
			this.zugFiguren.remove(figur);
			this.naechsterSpieler();
		}
		if (aktFeld instanceof Endfeld) {
			aktFeldIDS = figur.getMeinFeld().getID();
		} else if (aktFeld instanceof Standardfeld) {
			aktFeldID = Integer.parseInt(figur.getMeinFeld().getID());
		}
		if (restSchritte <= 0) {
			figur.setMeinFeld(getSpielbrett().getAlleEndFelderEinerFarbe(
					figur.getFarbe())[0]);
		} else {
			figur.setMeinFeld(getSpielbrett().getAlleEndFelderEinerFarbe(
					figur.getFarbe())[restSchritte - 1]);
		}
		if (aktFeld instanceof Endfeld) {
			for (int i = 0; i < getSpielbrett().getAlleEndFelderEinerFarbe(
					figur.getFarbe()).length; i++) {
				if (getSpielbrett().getAlleEndFelderEinerFarbe(
						figur.getFarbe())[i].getID().equals(aktFeldIDS)) {
					getSpielbrett().getAlleEndFelderEinerFarbe(
							figur.getFarbe())[i].setFigur(null);
				}
			}
		} else if (aktFeld instanceof Standardfeld) {
			getSpielbrett().getStandardFelder()[aktFeldID - 1].setFigur(null);
		}
	}

	/**
	 * Methode, die ueberprueft, ob eine Figur in ihrer endgueltigen Endposition
	 * ist und wenn dies der Fall das Attribut binIchAufEndposition auf true
	 * setzt.
	 * 
	 * @author Anna Rosa, Felix Rosa
	 * @param figur
	 *            - zu ueberpruefende Figur
	 */
	private void aufEndposition(Spielfigur figur) {
		if (!(figur.getMeinFeld() instanceof Endfeld))
			return;
		else {
			FarbEnum farbe = figur.getFarbe();
			int index = 0;
			for (int i = 0; i < getSpielbrett().getAlleEndFelderEinerFarbe(
					farbe).length; i++) {
				if (figur.equals(getSpielbrett().getAlleEndFelderEinerFarbe(
						farbe)[i].getFigur())) {
					index = i;
				}
			}
			switch (index) {
			case 0:
				if (getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[1]
						.getFigur() != null
						&& getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[2]
								.getFigur() != null
						&& getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[3]
								.getFigur() != null)
					figur.setBinIchAufEndposition(true);
				break;
			case 1:
				if (getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[2]
						.getFigur() != null
						&& getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[3]
								.getFigur() != null) {
					figur.setBinIchAufEndposition(true);
				}
				break;
			case 2:
				if (getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[3]
						.getFigur() != null)
					figur.setBinIchAufEndposition(true);
				break;
			case 3:
				figur.setBinIchAufEndposition(true);
			}
		}
	}

	/**
	 * Methode zum Auswaehlen der Figur, die ziehen soll ueber eine von aussen
	 * (Spieler/ KI) uebergebene Spielfeld-ID
	 * 
	 * @param id
	 *            - ID des Spielfeldes, auf dem sich die Figur vor Ausfuehren
	 *            des Zuges befindet
	 * @author Kevin Schroetter(Update v2.2), Anna Rosa
	 * @throws UnsupportedAudioFileException
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws InterruptedException
	 * @since version 2.2
	 */
	private String waehleFigur(String id) throws InterruptedException,
			LineUnavailableException, IOException,
			UnsupportedAudioFileException {

		String targetArea = null;
		try {
			if (getZugMoeglich() == false)
				throw new FigurKannNichtZiehenException("Zug nicht moeglich!");
			FarbEnum farbeIstAmZug = getIstAmZug().getFarbe();
			Spielfeld f = getSpielbrett().getFeld(id, farbeIstAmZug);
			Spielfigur figur = f.getFigur();
			this.debugFigur = figur;
			if (!(alleZugFiguren().contains(figur)))
				// throw new FigurKannNichtZiehenException("Figur " +
				// figur.getID() + " " + figur.getFarbe() +
				// " kann nicht ziehen!");
				throw new FigurKannNichtZiehenException(
						"Jetzt MÜSSTE er eine NPE werfen..");
			else {
				getIstAmZug().setZugFigur(figur);
				targetArea = ziehen(figur, getAugenzahl());
			}
		} catch (NullPointerException e) {
			System.out.println(getSpielbrett().getFeld(id,
					this.getIstAmZug().getFarbe()));
			e.printStackTrace();
			System.exit(1);
		}

		return targetArea;
	}

	/**
	 * Diese Methode wuerfelt fuer den Spieler ein gewolltes Ergebnis
	 * Description: Siehe wuerfelnOriginal()
	 * 
	 * @author Kevin Schroetter
	 * @since version 2.2
	 * @param hack
	 *            - int, der aktuell noch dazu verwendet wird, um konkrete
	 *            Wuerfelergebnisse fuer Tests zu erarbeiten. Dies wird im
	 *            spaeteren Verlauf herausgenommen.
	 * @throws RuntimeException
	 *             - Wenn ein Spiel noch nicht begonnen hat
	 * @throws SpielBeendetException
	 *             - Wenn ein Siel bereits beendet ist
	 */
	protected void wuerfeln(int hack) {
		if (getIstBeendet() == true) {
			throw new SpielBeendetException(
					"SPIEL IST BEREITS BEENDET! KEIN WEITERER ZUG MOEGLICH");
		}
		if (getAnzWuerfe() == 3 && getAlleAufSpawn() == true
				&& getAugenzahl() != 6) {
			naechsterSpieler();
		}
		if ((alleZugFiguren().size() != 0)
				|| (getAlleAufSpawn() == false && getAnzWuerfe() >= 1 && getAugenzahl() != 6))
			throw new RuntimeException(
					getIstAmZug().getName()
							+ ", Sie muessen erst einen Zug ausfuehren, bevor nochmals gewuerfelt werden kann!");
		if (getHatBegonnen() == false)
			throw new RuntimeException("Spiel hat noch nicht begonnen");

		int spawncounter = 0;
		incAnzWuerfe();
		int augenzahl = getIstAmZug().getMeinWuerfel().testWurf(hack);
		setAugenzahl(augenzahl);

		for (Spielfigur sf : getIstAmZug().alleFiguren()) {
			if (kannIchZiehen(sf) == true) {
				sf.setKannZiehen(true);
				setZugFiguren(sf);
			}
			if (sf.binIchGespawnt() == false)
				spawncounter++;
		}
		if (spawncounter == getIstAmZug().getAnzFiguren())
			setAlleAufSpawn(true);
		else
			setAlleAufSpawn(false);
		if (getAlleAufSpawn() == false && getAugenzahl() == 6
				&& getAnzWuerfe() < 1)
			throw new RuntimeException(
					getIstAmZug().getName()
							+ ", Sie muessen erst einen Zug ausfuehren, bevor nochmals gewuerfelt werden kann!");

		if (alleZugFiguren().isEmpty() == true && getAlleAufSpawn() == true
				&& getAnzWuerfe() > 3 && getAugenzahl() != 6) {
			setZugMoeglich(false);
			naechsterSpieler();
		} else if (alleZugFiguren().isEmpty() == true
				&& getAlleAufSpawn() == true && getAnzWuerfe() <= 3
				&& getAugenzahl() != 6) {
			setZugMoeglich(false);
		} else if (getAugenzahl() == 6) {
			setZugMoeglich(true);
		} else {
			setZugMoeglich(true);
			setAlleAufSpawn(false);
		}
		;
		// DEBUG SYSOS
		// System.out.println("ZUGFIGUREN: "+alleZugFiguren().size());
		// System.out.println("Anzahl wuerfe: "+getAnzWuerfe());
		// System.out.println("Augenzahl: "+getAugenzahl());
		// System.out.println("Am Zug: "+getIstAmZug());
		// System.out.println("Zug Moeglich: "+getZugMoeglich());
		// System.out.println("Alle auf Spawn: "+getAlleAufSpawn());
		// System.out.println("");
	}

	/**
	 * Diese Methode wuerfelt fuer den Spieler Sie gibt stets die Augenzahl mit
	 * und hat ein paar Faelle zum Unterscheiden 1. Wenn sich alle Figuren auf
	 * den Startefeldern befinden, darf ein spieler drei mal wuerfeln 2. Wenn
	 * sich alle Figuren auf den Startfeldern befindet und bereits dreimal
	 * gewuerfelt wurde, wird ein naechster Spieler ausgewaehlt 3. Wenn sich
	 * nicht alle Figuren auf den Startfeldern befinden, darf ein Spieler nur
	 * dann ein weiteres mal wuerfeln, wenn er eine 6 gewuerfelt hat 4. Wenn ein
	 * Spieler mehrmals hintereinander wuerfeln will, ohne dass er vorher einen
	 * Zug ausgefuerht hat, so ist dies nicht moeglich
	 * 
	 * Die Methode nutzt kannIchZiehen(Spielfigur figur), um zu ermitteln, ob
	 * eine Figur ziehen kann. Wenn dies der Fall ist, so wird die Figur in die
	 * ArrayList alleZugFiguren geschrieben, die spaeter in der Methode
	 * waehleFigur() verwendet wird. Sollte sich keine Figur in der ArrayList
	 * befinden, wird geprueft, ob sich die Figuren auf den Startfeldern
	 * befinden. Ja? Dann darf der Spieler drei mal wuerfeln. Sollte sich keine
	 * Figur in der ArrayList befinden, und es befinden sich NICHT ALLE Figuren
	 * auf den Startfeldern, so ist der naechster Spieler mit seinem Zug dran.
	 * 
	 * @author Kevin Schroetter
	 * @since version 2.2
	 * @throws RuntimeException
	 *             - Wenn ein Spiel noch nicht begonnen hat
	 * @throws SpielBeendetException
	 *             - Wenn ein Spiel bereits beendet ist
	 */
	private void wuerfelnOriginal() {
		if (getIstBeendet() == true)
			throw new SpielBeendetException(
					"SPIEL IST BEREITS BEENDET! KEIN WEITERER ZUG MOEGLICH");
		if (getAnzWuerfe() == 3 && getAlleAufSpawn() == true
				&& getAugenzahl() != 6) {
			naechsterSpieler();
		}
		if ((alleZugFiguren().size() != 0)
				|| (getAlleAufSpawn() == false && getAnzWuerfe() >= 1 && getAugenzahl() != 6))
			throw new RuntimeException(
					getIstAmZug().getName()
							+ ", Sie muessen erst einen Zug ausfuehren, bevor nochmals gewuerfelt werden kann!");
		if (getHatBegonnen() == false)
			throw new RuntimeException("Spiel hat noch nicht begonnen");

		int spawncounter = 0;
		incAnzWuerfe();
		int augenzahl = getIstAmZug().getMeinWuerfel().werfen();
		setAugenzahl(augenzahl);

		for (Spielfigur sf : getIstAmZug().alleFiguren()) {
			if (kannIchZiehen(sf) == true) {
				sf.setKannZiehen(true);
				setZugFiguren(sf);
			}
			if (sf.binIchGespawnt() == false)
				spawncounter++;
		}
		if (spawncounter == getIstAmZug().getAnzFiguren())
			setAlleAufSpawn(true);
		else
			setAlleAufSpawn(false);
		if (getAlleAufSpawn() == false && getAugenzahl() == 6
				&& getAnzWuerfe() < 1)
			throw new RuntimeException(
					getIstAmZug().getName()
							+ ", Sie muessen erst einen Zug ausfuehren, bevor nochmals gewuerfelt werden kann!");

		if (alleZugFiguren().isEmpty() == true && getAlleAufSpawn() == true
				&& getAnzWuerfe() > 3 && getAugenzahl() != 6) {
			setZugMoeglich(false);
			naechsterSpieler();
		} else if (alleZugFiguren().isEmpty() == true
				&& getAlleAufSpawn() == true && getAnzWuerfe() <= 3
				&& getAugenzahl() != 6) {
			setZugMoeglich(false);
		} else if (getAugenzahl() == 6) {
			setZugMoeglich(true);
		} else {
			setZugMoeglich(true);
			setAlleAufSpawn(false);
		}
		;
		// DEBUG SYSOS
		// System.out.println("ZUGFIGUREN: "+alleZugFiguren().size());
		// System.out.println("Anzahl wuerfe: "+getAnzWuerfe());
		// System.out.println("Augenzahl: "+getAugenzahl());
		// System.out.println("Am Zug: "+getIstAmZug());
		// System.out.println("Zug Moeglich: "+getZugMoeglich());
		// System.out.println("Alle auf Spawn: "+getAlleAufSpawn());
		// System.out.println("");
	}

	/**
	 * Methode, die den naechsten Spieler als am Zug seienden Spieler setzt und
	 * dem vorherigen die Eigenschaft, dass er am Zug ist, auf false setzt.
	 * 
	 * @author Kevin Schroetter(Update v2.2), Anna Rosa, Felix Rosa
	 * @since version 2.2
	 */
	private void naechsterSpieler() {
		alleZugFiguren().clear();
		deleteAnzZugFiguren();
		deleteAnzWuerfe();
		for (int i = 0; i < getAnzahlSpieler(); i++) {
			if (getIstAmZug().equals(spieler[i])) {
				if (i == getAnzahlSpieler() - 1) {
					setIstAmZug(spieler[0]);
				} else {
					setIstAmZug(spieler[i + 1]);
				}
				break;
			}
		}
	}

	/**
	 * Methode ausgabeFiguren - gibt ArrayList aller Figuren zurueck und gibt
	 * diese auf der Systemkonsole aus
	 * 
	 * @return figurenListe - ArrayList vom Typ Spielfigur
	 */
	public ArrayList<Spielfigur> ausgabeFiguren() {
		try {
			Spieler s = this.getIstAmZug();
			ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>(
					Arrays.asList(s.alleFiguren()));
			for (Spielfigur f : s.alleFiguren()) {
				System.out.println(f.getName() + " auf Feld "
						+ f.getMeinFeld().getID());
			}
			return figurenListe;
		} catch (RuntimeException e) {
			System.out.println(e);
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * Methode ausgabeZugFiguren - gibt ArrayList aller Figuren zurueck die
	 * ziehen koennen und gibt diese auf der Systemkonsole aus
	 * 
	 * @return figurenListe - ArrayList vom Typ Spielfigur
	 */
	public ArrayList<Spielfigur> ausgabeZugFiguren() {
		ArrayList<Spielfigur> figurenListe = alleZugFiguren();

		for (Spielfigur f : figurenListe) {
			System.out.println(f.getName() + " auf Feld "
					+ f.getMeinFeld().getID());
		}
		if (!figurenListe.isEmpty())
			return figurenListe;
		else
			return null;
	}

	/**
	 * Methode ausgabeZugFiguren - gibt ArrayList aller Figuren zurueck die im
	 * Ziel sind und gibt diese auf der Systemkonsole aus
	 * 
	 * @return figurenListe - ArrayList vom Typ Spielfigur
	 */
	public ArrayList<Spielfigur> ausgabeFigurenImZiel() {
		Spieler s = this.getIstAmZug();
		ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>(
				Settings.maxFiguren);
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getIstImZiel()) {
				figurenListe.add(f);
				System.out.println(f.getName() + " auf Feld "
						+ f.getMeinFeld().getID());
			}
		}
		return figurenListe;
	}

	/**
	 * Methode ausgabeSpielerListe - Gibt alle Spieler auf der System Konsole
	 * aus.
	 * 
	 * 
	 */
	public void ausgabeSpielerListe() {
		for (Spieler s : spieler) {
			if (s == null)
				continue;
			else {
				System.out.println(s.getName() + " mit Farbe: " + s.getFarbe());
			}
		}
	}

	public String[] ausgabeSpielerNamenStrings() {

		String[] spielerStrings = new String[this.spieler.length];

		for (int i = 0; i < spielerStrings.length; i++) {
			if (this.spieler[i] == null)
				continue;
			spielerStrings[i] = this.spieler[i].getName();
		}
		return spielerStrings;

	}

	/**
	 * Methode zugDurchfuehren Versucht einen Zug mit der gewaehlten Figur
	 * durchzufuehren. Faengt im Falle dessen, dass die gewaehlte Figur nicht
	 * ziehen kann, die Exception ab und gibt false zurueck.
	 * 
	 * @return zugErfolgreich - boolean
	 * @param ID
	 *            - String
	 * @throws SpielBeendetException
	 *             - Wenn ein Spiel bereits beendet ist
	 * @throws FigurKannNichtZiehenException
	 *             - Wenn eine Figur nicht ziehen kann
	 * @throws NullPointerException
	 *             - Wenn auf ein Feld zugegriffen werden soll, auf dem keine
	 *             Figur steht
	 * @throws RuntimeException
	 *             - Wenn auf ein nicht vorhandenes Feld zugegriffen wird. War
	 *             der Zug erfolgreich, gibt true zurueck.
	 */
	public String[] zugDurchfuehren(String ID) {

		String[] zugFelder = new String[3];

		try {
			if (getIstBeendet() == true)
				throw new SpielBeendetException(
						"SPIEL IST BEREITS BEENDET! KEIN WEITERER ZUG MOEGLICH");
			if (istAmZug.getBedienung() != null)
				throw new MethodeFuerKiException(
						"Spieler ist eine KI, bitte andere Methode verwenden!");
			int endCounter = 0;
			FarbEnum farbeIstAmZug = getIstAmZug().getFarbe();
			String amZug = getIstAmZug().getName();
			Spielfeld f = getSpielbrett().getFeld(ID, farbeIstAmZug);
			zugFelder[0] = f.getGuiID();
			Spielfigur figur = f.getFigur();
			for (Spielfigur sf : getIstAmZug().alleFiguren()) {
				if (sf.getBinIchAufEndpostion() == true)
					endCounter++;
			}
			zugFelder[2] = waehleFigur(ID);
			Spielfeld zielFeld = figur.getMeinFeld();
			// zugFelder[1] = zielFeld.getGuiID();
			// zugErfolgreich = true;
			zugFelder[1] = zielFeld.getGuiID();
			System.out.println("Zug erfolgreich!");
			System.out.println(figur.getName() + " zieht von Feld " + f.getID()
					+ " auf Feld " + zielFeld.getID() + "\n\n");
			if (endCounter == 3 && figur.getBinIchAufEndpostion() == true) {
				System.out
						.println("++++++++++++++++++++++++++++++++++"
								+ amZug
								+ " "
								+ farbeIstAmZug
								+ " hat GEWONNEN! SPIEL BEENDET!+++++++++++++++++++++++++++++++++++");
				setIstBeendet(true);
				// PlaySounds.gameOver();
			}
			return zugFelder;
		}

		catch (FigurKannNichtZiehenException e) {
			// zugErfolgreich = false;
			System.out
					.println("Zug fehlgeschlagen, diese Figur kann nicht ziehen!");
			this.resetWuerfe();
			// return zugErfolgreich;
			zugFelder = null;
			return zugFelder;
		} catch (MethodeFuerKiException e) {
			// zugErfolgreich=false;
			System.out.println(e.getMessage());
			// return zugErfolgreich;
			zugFelder = null;
			return zugFelder;
		} catch (NullPointerException e) {
			// zugErfolgreich = false;
			System.out
					.println("Zug fehlgeschlagen, auf diesem Feld steht keine Figur!");
			// return zugErfolgreich;
			zugFelder = null;
			return zugFelder;
		} catch (SpielBeendetException e) {
			// zugErfolgreich = false;
			System.out.println(e);
			// return zugErfolgreich;
			zugFelder = null;
			return zugFelder;
		} catch (RuntimeException e) {

			e.printStackTrace();
			// zugErfolgreich = false;
			System.out.println(e);
			// return zugErfolgreich;
			zugFelder = null;
			return zugFelder;
		} catch (InterruptedException e) {
			System.out.println(e);
			// zugErfolgreich = false;
			// return zugErfolgreich;
			zugFelder = null;
			return zugFelder;
		} catch (LineUnavailableException e) {
			System.out.println(e);
			// zugErfolgreich = false;
			// return zugErfolgreich;
			zugFelder = null;
			return zugFelder;
		} catch (IOException e) {
			System.out.println(e);
			// zugErfolgreich = false;
			// return zugErfolgreich;
			zugFelder = null;
			return zugFelder;
		} catch (UnsupportedAudioFileException e) {
			System.out.println(e);
			// zugErfolgreich = false;
			// return zugErfolgreich;
			zugFelder = null;
			return zugFelder;
		}
	}

	/**
	 * Methode ausgabeZugFiguren - gibt ArrayList aller Figuren zurueck die auf
	 * einem Startfeld sind und gibt diese auf der Systemkonsole aus
	 * 
	 * @return figurenListe - ArrayList vom Typ Spielfigur
	 */
	public ArrayList<Spielfigur> ausgabeFigurenAufStartfeld() {
		Spieler s = this.getIstAmZug();
		ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>(4);
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getMeinFeld() instanceof Startfeld) {
				System.out.println(f.getName() + " auf Feld "
						+ f.getMeinFeld().getID());
				figurenListe.add(f);
			}
		}
		return figurenListe;
	}

	/**
	 * Roll the Dice - Rollt den Wuerfel
	 * 
	 * @return s.getMeinWuerfel().werfen() - int
	 *
	 */
	public int rollTheDice() {
		try {

			wuerfelnOriginal();
			System.out.println("Spieler " + getIstAmZug().getName()
					+ " ist am Zug!");
			System.out
					.println("Spieler "
							+ getIstAmZug().getName()
							+ " "
							+ getIstAmZug().getFarbe()
							+ " hat eine "
							+ getAugenzahl()
							+ " gewuerfelt und darf mit folgenden Figuren ziehen:\n#########################");
			if (alleZugFiguren().size() == 0 && getAlleAufSpawn() == false) {
				System.out.println("Keine Figur darf ziehen! Neu wuerfeln!\n");
				naechsterSpieler();
			} else if (alleZugFiguren().size() == 0) {
				System.out.println("Keine Figur darf ziehen! Neu wuerfeln!\n");
				// naechsterSpieler();
			} else {
				System.out.println("");
				for (Spielfigur sf : alleZugFiguren()) {
					System.out.print(sf.getName() + " auf Feld: "
							+ sf.getMeinFeld().getID());
					if (sf.getIstGespawnt() == false
							&& sf.getKannSchlagen() == true)
						System.out.println(" Kann Spawnen & Killen");
					else if (sf.getIstGespawnt() == true
							&& sf.getKannSchlagen() == true)
						System.out.println(" Kann Killen");
					else if (sf.getIstGespawnt() == false
							&& sf.getKannSchlagen() == false)
						System.out.println(" Kann Spawnen");
					else if (sf.getKannInsZiel() == true)
						System.out.println(" Kann ins Ziel");
					else
						System.out.println(" Kann laufen");
				}
			}
			System.out.println("---------------------------------------\n");
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return getAugenzahl();
	}

	/**
	 * Zusaetzliche Methode fuer das Interface, damit dadurch konkrete
	 * Wuerfelzahlen verwendet werden koennen. Nutzung fuer Testzwecke
	 * 
	 * @author Kevin Schroetter
	 * @since version 2.2
	 * @param zahl
	 *            - int, die gewuenschte Augenzahl
	 */
	@Override
	public void werfen(int zahl) {
		try {
			wuerfeln(zahl);
			System.out.println("Spieler " + getIstAmZug().getName()
					+ " ist am Zug!");
			System.out
					.println("Spieler "
							+ getIstAmZug().getName()
							+ " "
							+ getIstAmZug().getFarbe()
							+ " hat eine "
							+ getAugenzahl()
							+ " gewuerfelt und darf mit folgenden Figuren ziehen:\n#########################");
			if (alleZugFiguren().size() == 0 && getAlleAufSpawn() == false) {
				System.out.println("Keine Figur darf ziehen! Neu wuerfeln!\n");
				naechsterSpieler();
			} else if (alleZugFiguren().size() == 0) {
				System.out.println("Keine Figur darf ziehen! Neu wuerfeln!\n");
			} else {
				System.out.println("");
				for (Spielfigur sf : alleZugFiguren()) {
					System.out.print(sf.getName() + " auf Feld: "
							+ sf.getMeinFeld().getID());
					if (sf.getIstGespawnt() == false
							&& sf.getKannSchlagen() == true)
						System.out.println(" Kann Spawnen & Killen");
					else if (sf.getIstGespawnt() == true
							&& sf.getKannSchlagen() == true)
						System.out.println(" Kann Killen");
					else if (sf.getIstGespawnt() == false
							&& sf.getKannSchlagen() == false)
						System.out.println(" Kann Spawnen");
					else if (sf.getKannInsZiel() == true)
						System.out.println(" Kann ins Ziel");
					else
						System.out.println(" Kann laufen");
				}
			}
			System.out.println("---------------------------------------\n");
		} catch (RuntimeException e) {

			e.printStackTrace();
			System.out.println(e);
		}
	}

	/**
	 * Interface Methode zum Hinzufuegen eines neuen Spielers
	 * 
	 * @author Kevin Schroetter
	 * @since version 2.2
	 * @param name
	 *            - String name
	 * @param farbID
	 *            - int zum Auswaehlen der Farbe
	 * @param verhaltenID
	 *            - int zum Auswaehlen des Verhaltens
	 */
	@Override
	public boolean neuerSpieler(String name, int farbID, int verhaltenID) {
		boolean erfolgreich = false;
		try {

			erfolgreich = spielerHinzufuegen(name, farbID, verhaltenID);
			System.out.println("Spieler " + getAnzahlSpieler() + ": "
					+ spieler[getAnzahlSpieler() - 1].getName() + " "
					+ spieler[getAnzahlSpieler() - 1].getFarbe()
					+ " wurde Hinzugefuegt!\n");
			if (getAnzahlSpieler() == 4) {
				setHatBegonnen(false);
				starteSpiel();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println(e);
		} finally {
			return erfolgreich;
		}
	}

	/**
	 * Methode, die alle Spieleigenschaften zuruecksetzt. Sie wird aktuell nur
	 * im JUnit-Test verwendet.
	 * 
	 * @author Kevin Schroetter
	 * @since version 2.2
	 */
	public void neuesSpiel() {
		if (getAnzahlSpieler() == 0)
			throw new RuntimeException(
					"Es kann kein neues Spiel erstellt werden!");
		deleteSpieler();
		for (int i = 0; i < 4; i++) {
			spieler[i] = null;
		}
		deleteAnzahlSpieler();
		setHatBegonnen(false);
		setIstBeendet(false);
		alleZugFiguren().clear();
		deleteAnzWuerfe();
		deleteAnzZugFiguren();
	}

	/**
	 * HilfsMethode zum loeschen aller der static Attribute Spielernummer in
	 * Spieler und AzahlFiguren in Spielfigur
	 * 
	 * @author Kevin Schroetter
	 * @since version 2.2
	 */
	private void deleteSpieler() {
		if (spieler[0] == null)
			throw new RuntimeException("Fehler beim Loeschen der Spieler");
		spieler[0].deleteSpielernummer();
		spieler[0].getFiguren(1).deleteAnzahlFiguren();
	}

	/**
	 * HilfsMethode, die die anzahl der Spieler auf 0 zuruecksetzt.
	 * 
	 * @author Kevin Schroetter
	 * @since version 2.2
	 */
	private void deleteAnzahlSpieler() {
		this.anzahlSpieler = 0;
	}

	/**
	 * Gibt Spielerarray zurück
	 * */
	public Spieler[] getSpieler() {
		return this.spieler;
	}

	/**
	 * HilfsMethode zum Loeschen der anzWuerfe.
	 * 
	 * @author Kevin Schroetter
	 * @since version 2.2
	 */
	private void deleteAnzWuerfe() {
		this.anzWuerfe = 0;
	}

	public void resetWuerfe() {
		this.anzWuerfe = 0;
	}

	@Override
	public void starteSpiel() {
		try {
			if (getHatBegonnen() == false) {
				startSpiel();
				System.out
						.println("\n++++++++++Spiel gestartet!++++++++++\n\nSpieler "
								+ getIstAmZug().getName()
								+ " bitte wuerfeln!\n\n");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();

			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (UnsupportedAudioFileException e) {
			System.out.println(e);
		} catch (LineUnavailableException e) {
			System.out.println(e);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	/**
	 * Methode, die booleanschen Wert zurückgibt, ob am Zug seiender Spieler KI
	 * ist oder nicht. Falls Spieler KI: return true Falls Spieler Mensch:
	 * return false
	 */
	@Override
	public boolean ausgabeSpielerAmZug() {
		if (istAmZug.getBedienung() != null)
			return true;
		else
			return false;
	}

	/**
	 * /** Methode fuer die KI, die ueberprueft, ob Spieler istAmZug eine KI
	 * hat, falls dies der Fall ist, wird die zugWaehlen-Methode der KI
	 * aufgerufen, falls nicht, wird eine Exception gethrowt und gecatcht.
	 * 
	 * @author Anna Rosa
	 * @since version 3.0
	 * @throws MethodeFuerKiException
	 *             Falls die Methode von einem menschlichen Spieler aufgerufen
	 *             wird.
	 */
	@Override
	public String[] zugDurchfuehrenKI() {
		String[] zugFelder = null;
		try {
			if (getIstBeendet() == true)
				throw new SpielBeendetException("Spiel ist schon beendet.");
			if (istAmZug.getBedienung() == null)
				throw new MethodeFuerKiException("Spieler ist keine KI!");
			else
				zugFelder = istAmZug.getBedienung().zugWaehlen();
			return zugFelder;
		} catch (SpielBeendetException e) {
			System.out.println(e.getMessage());
			return zugFelder;
		} catch (MethodeFuerKiException e) {
			System.out.println(e.getMessage()
					+ " Bitte andere Methode waehlen.");
			return zugFelder;
		} catch (FigurKannNichtZiehenException e) {
			System.out.println(e.getMessage());
			return zugFelder;
		}
	}

	@Override
	public void neuesSpielErstellen() {
		neuesSpiel();
	}

	@Override
	public ArrayList<String> getZugInfo() {
		if (!this.zugFiguren.isEmpty()) {
			ArrayList<String> infos = new ArrayList<String>();
			for (Spielfigur fig : zugFiguren){
				infos.add(fig.getMeinFeld().getGuiID());
			}
			
			return infos;
		} else
			return null;
	}

	@Override
	public boolean getStatus() {
		return this.getHatBegonnen();
	}

	@Override
	public ArrayList<String> updateGUIFigures() {
		ArrayList<String>alleFiguren=new ArrayList<String>();
		alleFiguren.add("Spieler "+this.getIstAmZug().getName()+" "+this.getIstAmZug().getFarbe()+" ist am Zug!");
		alleFiguren.add("Z"+this.getAugenzahl());
		System.out.println(spieler.length);
		for(int i = 0;i<spieler.length;i++){
			if(spieler[i]!=null){
				for(Spielfigur f: spieler[i].alleFiguren()){
					alleFiguren.add(f.getMeinFeld().getGuiID()+"-"+f.getName());
				}
					
			}
		}	
		return alleFiguren;
	}

	@Override
	public Boolean[] updateGUIInfo() {
		Boolean infos[] = new Boolean[2];
		infos[0] = this.getZugMoeglich();
		if (this.istAmZug.getBedienung()==null)
			infos[1] = false;
		else
			infos[1] = true;
		
		return infos;
	}
	public Boolean getBeendet() {
		return this.istBeendet;
	}

	@Override
	public int augenzahl() {
		return this.getAugenzahl();
	}

}
