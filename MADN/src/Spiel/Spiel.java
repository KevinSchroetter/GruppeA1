package Spiel;

import java.util.ArrayList;

import Basisklassen.*;
import Hilfsklassen.*;

/**
 * Klasse Spiel als Regelwerk für MADN. Sie beinhaltet alle notwendigen
 * Methoden, um einen reibungslosen Spielfluss zu gewährleisten. In dieser
 * Klasse sind DebugMethoden implementiert, sie sind ganz am Endeder Datei zu
 * finden.
 * 
 * @author Kevin Schrötter, Felix Rosa, Anna Rosa, Alexander Brückner
 * @version 1.2
 *
 */

public class Spiel implements iBediener{
	/**
	 * Array, in dem alle am Spiel teilnehmenden Spieler gespeichert werden
	 */
	private Spieler[] spieler = new Spieler[4];
	/**
	 * Hier wird der Spieler gespeichert, der gerade ziehen darf
	 */
	private Spieler istAmZug;
	/**
	 * Zeigt an, ob der Spieler tatsächlich ziehen kann
	 */
	private boolean zugMöglich = false;
	/**
	 * Hier wird das Spielbrett gespeichert, auf dem gespielt wird
	 */
	public Spielbrett spielbrett;
	/**
	 * Anzahl der Spieler, die gerade an einem Spiel teilnehmen
	 */
	private int anzahlSpieler;
	/**
	 * Zeigt an, ob ein Spiel bereits begonnen hat, falls TRUE
	 */
	private boolean hatBegonnen = false;
	/**
	 * Zeigt an, ob ein Spiel bereits beendet ist, falls TRUE
	 */
	private boolean istBeendet = false;
	/**
	 * Speichert die Anzahl an Würfel-Würfen, die ein Spieler während eines
	 * Zuges ausgeführt hat. Relevant für den Fall, dass mehrmals hintereinander
	 * eine 6 gewürfelt wird.
	 */
	private int anzWürfe = 0;
	/**
	 * Speichert die zuletzt gewürfelte Augenzahl während eines Zuges
	 */
	private int augenzahl = 0;
	/**
	 * ArrayList, in der die Figuren gespeichert werden, mit denen ein Zug
	 * ausgeführt werden kann
	 */
	private ArrayList<Spielfigur> zugFiguren = new ArrayList<Spielfigur>(0);
	/**
	 * Speichert die Anzahl der Figuren, die laufen können
	 */
	private int anzZugFiguren = 0;
	/**
	 * Zeigt an, ob sich ALLE Figuren auf ihren Startfeldern befinden.
	 */
	private boolean alleAufSpawn;
	private Spielfigur chosenFigur;

	/**
	 * Konstruktor für ein Spiel. Er schreibt ein Spielbrett direkt in das
	 * Attribut spielbrett.
	 */
	public Spiel() {
		setSpielbrett(new Spielbrett());
	}

	/**
	 * Setter für IstAmZug, der den Spieler in das Attribut schreiben soll, der
	 * gerade am Zug ist. Es kann nur ein Objekt der Klasse Spieler gespeichert
	 * werden.
	 * 
	 * @param sIAZ
	 *            - Übergebener Spieler, der gespeichert werden soll.
	 */
	public void setIstAmZug(Spieler sIAZ) {
		if (!(sIAZ instanceof Spieler))
			throw new RuntimeException(
					"Es kann nur ein Spielerobjekt am Zug sein!");
		this.istAmZug = sIAZ;
	}

	/**
	 * Getter, der den Spieler zurück gibt, der gerade am Zug ist.
	 * 
	 * @return istAmZug - Ein Objekt der Klasse Spieler.
	 */
	public Spieler getIstAmZug() {
		if (getHatBegonnen()==false) throw new RuntimeException("Spiel hat noch nicht gestartet!");
		return this.istAmZug;
	}

	/**
	 * Setter für zugMöglich
	 * 
	 * @param zugMöglich
	 *            - true oder false boolean
	 */
	public void setZugMöglich(boolean zugMöglich) {
		this.zugMöglich = zugMöglich;
	}

	/**
	 * Getter für zugMöglich
	 * 
	 * @return zugMöglich - true oder false boolean
	 */
	public boolean getZugMöglich() {
		return this.zugMöglich;
	}

	/**
	 * Setter für das Spielbrett Es wird nur gesetzt, wenn es sich beim
	 * Übergabeparameter um ein Objekt der Klasse Spielbrett handelt.
	 * 
	 * @param spielbrett
	 *            - Objekt der Klasse Spielbrett.
	 */
	public void setSpielbrett(Spielbrett spielbrett) {
		if (!(spielbrett instanceof Spielbrett))
			throw new RuntimeException("Kein Spielfeld gesetzt");
		this.spielbrett = spielbrett;
	}

	/**
	 * Getter für das Spielbrett.
	 * 
	 * @return spielbrett - Objekt der Klasse Spielbrett.
	 */
	public Spielbrett getSpielbrett() {
		return this.spielbrett;
	}

	/**
	 * Setter für die Anzahl der teilnehmenden Spieler, der nur inkrementiert.
	 */
	public void incAnzahlSpieler() {
		this.anzahlSpieler++;
	}

	/**
	 * Getter für anzahlSpieler
	 * 
	 * @return anzahlSpieler - Anzahl der Spieler vom Typ int
	 */
	public int getAnzahlSpieler() {
		return this.anzahlSpieler;
	}

	/**
	 * Setter für hatBegonnen
	 * 
	 * @param hatBegonnen
	 *            - boolean
	 */
	public void setHatBegonnen(boolean hatBegonnen) {
		this.hatBegonnen = hatBegonnen;
	}

	/**
	 * Getter für hatBegonnen
	 * 
	 * @return hatbegonnen - boolean
	 */
	public boolean getHatBegonnen() {
		return this.hatBegonnen;
	}

	/**
	 * Setter für istBeendet
	 * 
	 * @param istBeendet
	 *            - boolean
	 */
	public void setIstBeendet(boolean istBeendet) {
		this.istBeendet = istBeendet;
	}

	/**
	 * Getter für istBeendet
	 * 
	 * @return istBeendet - boolean
	 */
	public boolean getIstBeendet() {
		return this.istBeendet;
	}

	/**
	 * Setter für AnzWürfe, der die Variable inkrementiert.
	 */
	public void incAnzWürfe() {
		this.anzWürfe++;
	}

	/**
	 * Getter für anzWürfe
	 * 
	 * @return anzWürfe - int
	 */
	public int getAnzWÜrfe() {
		return this.anzWürfe;
	}

	/**
	 * Setter für die Augenzahl HINWEIS: DebugMethode zu Testzwecken als
	 * hilfsmethode am Ende der Datei
	 * 
	 * @param augenzahl
	 *            - Augenzahl als int
	 */
	public void setAugenzahl(int augenzahl) {
		if (augenzahl < 1 || augenzahl > 6)
			throw new RuntimeException("Fehlerhaftes Würfelergebnis");
		this.augenzahl = augenzahl;
	}

	/**
	 * Getter für die Augenzahl
	 * 
	 * @return augenzahl - int Wert
	 */
	public int getAugenzahl() {
		return this.augenzahl;
	}

	/**
	 * Setter für die Anzahl der vorhandenen zugFiguren, inkrementiert
	 */
	public void incAnzZugFiguren() {
		if (this.anzZugFiguren < 0 || this.anzZugFiguren > 4)
			throw new RuntimeException("Fehöer: ZugFigurenLimit erreicht!");
		this.anzZugFiguren++;
	}

	/**
	 * Getter für anzTugFiguren
	 * 
	 * @return anzZugFiguren - int Wert
	 */
	public int getAnzZugFiguren() {
		return this.anzZugFiguren;
	}

	/**
	 * Setter, der Figuren in einer ArrayList speichert, falls sie für einen Zug
	 * infrage kommen. Übergabeparameter muss vom Typ Figur sein Die Farbe der
	 * übergebenen Figur muss mit der Farbe des Spielers übereinstimmen, der
	 * gerade am Zug ist. Es können maximal 4 Figuren in der Lage sein, zu
	 * ziehen.
	 * 
	 * @param figur
	 *            - Objekt vom Typ Figur
	 */
	public void setZugFiguren(Spielfigur figur) {
		if (!(figur instanceof Spielfigur))
			throw new RuntimeException("Keine Spielfigur ausgewählt");
		if (!(figur.getFarbe().equals(getIstAmZug().getFarbe())))
			throw new RuntimeException("Ungültige Figur ausgewählt!");
		if (getAnzZugFiguren() > 4)
			throw new RuntimeException(
					"Maximale Anzahl an für einen Zug möglichen Figuren erreicht!");
		this.zugFiguren.add(figur);
		incAnzZugFiguren();
	}

	public Spielfigur getZugFiguren(int index) {
		if (index <0 || index > zugFiguren.size())throw new RuntimeException("Falscher Index!");
		
		Spielfigur figur=zugFiguren.get(index);
		if (!(figur instanceof Spielfigur))
			throw new RuntimeException(
					"FEHLER: Die angeforderte zugFigur ist keine Spielfigur!");
		if (!(figur.getFarbe().equals(getIstAmZug().getFarbe())))
			throw new RuntimeException(
					"Die Angeforderte Figur gehört nicht zum ziehenden Spieler!");

		else
			throw new RuntimeException("Angeforderte Figur existiert nicht");
	}

	/**
	 * Setter für alleAufSpawn
	 * 
	 * @param alleAufSpawn
	 *            - boolean
	 */
	public void setAlleAufSpawn(boolean alleAufSpawn) {
		this.alleAufSpawn = alleAufSpawn;
	}

	/**
	 * Getter für alleAufSpawn
	 * 
	 * @return alleAufSpawn - boolean
	 */
	public boolean getAlleAufSpawn() {
		return this.alleAufSpawn;
	}

	/**
	 * Methode zum Hinzufügen eines neuen Spielers, solange das Spiel noch nicht
	 * gestartet ist. Sind Spieler im Spiel, so wird Spiel automatisch begonnen.
	 * 
	 * @param name
	 *            - gewünschter Name des Spielers
	 * @param farbe
	 *            - gewünschte Farbe des Spielers
	 * @param verhalten
	 *            - Falls null: Menschlicher Spieler, sonst: KI mit dem
	 *            übergebenen Verhalten;
	 */
	public void spielerHinzufügen(String name, FarbEnum farbe, String verhalten) {
		if (getHatBegonnen() == true)
			throw new RuntimeException("Spiel hat schon begonnen");
		if (farbe == null)
			throw new RuntimeException("Spieler braucht eine Farbe");
		for (int i = 0; i <= 3; i++) {
			if (spieler[i] != null) {
				if (farbe.equals(spieler[i].getFarbe()))
					throw new RuntimeException("Farbe schon vorhanden");
			}
		}
		Startfeld[] startfelder = getSpielbrett().getAlleStartFelderEinerFarbe(
				farbe);
		Endfeld[] endfelder = getSpielbrett().getAlleEndFelderEinerFarbe(farbe);
		if (verhalten == null) {
			spieler[getAnzahlSpieler()] = new Spieler(name, farbe, startfelder,
					endfelder);
		} else if (verhalten != null) {
			spieler[getAnzahlSpieler()] = new Spieler(name, farbe, startfelder,
					endfelder, verhalten);
		}
		incAnzahlSpieler();
		if (getAnzahlSpieler() == 4) {
			setHatBegonnen(true);
		}

	}

	/**
	 * Methode, die das Spiel startet, so dass keine Spieler mehr hinzugefügt
	 * werden können. Sie setzt den ersten Spieler im Spieler Array als den
	 * Spieler, der am Zug ist.
	 */
	public void startSpiel() {
		setHatBegonnen(true);
		if(spieler[0]!=null){
			spieler[0].setAmZug(true);
			setIstAmZug(spieler[0]);
		}else setIstBeendet(true);

	}

	/**@author Felix Rosa
	 * 
	 * @param figur
	 * @return ZielfeldID -
	 */
	public int ermittleZielfeldID(Spielfigur figur){
		Standardfeld aktFeld =(Standardfeld) figur.getMeinFeld();
		int ZielfeldID = Integer.parseInt(aktFeld.getID())+getAugenzahl();
		if(ZielfeldID>=spielbrett.getAlleStandardFelder().length){
			return (ZielfeldID-1)-spielbrett.getAlleStandardFelder().length;
		}
		return ZielfeldID-1;
	}
	
	/**@author Felix Rosa
	 * 
	 * @param figur
	 * @return
	 */
	public int ermittleEndfeldSchritte(Spielfigur figur){
		return figur.getFelderGelaufen()+getAugenzahl() - (spielbrett.getAlleStandardFelder().length-1);

	}
	/** @author Felix Rosa
	 *  v. 1.1(kannIchZiehen um Spawn-Abfrage erweitert!)
	 *  Methode prüft ob übergebene Figur in Kombination mit übergebener Würfelzahl ziehen kann.
	 *  Dabei wird überprüft ob Zug aus Spielfeld ins Endfeld oder Züge im Endfeld möglich sind!
	 * @param figur - figur von der überprüft werden soll ob ziehen möglich ist
	 * @param augenZahl - gewürfelte Augenzahl um die die Figur ziehen soll!
	 * @return boolean - true wenn ziehen möglich, false wenn nicht
	 */
	public boolean kannIchZiehen(Spielfigur figur) {
		Spieler istAmZug = this.getIstAmZug();
		if(figur.getIstGespawnt()==false&&getAugenzahl()==6){
			System.out.println("Fall1");
			if(spielbrett.getSpawnfeld(istAmZug.getFarbe()).getFigur()==null){
				System.out.println("Fall1.1");
				return true;
			}else if(!(spielbrett.getSpawnfeld(istAmZug.getFarbe()).getFigur().getFarbe().equals(istAmZug.getFarbe()))){
				System.out.println("Fall1.2");
				return true;
			}else if(spielbrett.getSpawnfeld(istAmZug.getFarbe()).getFigur().getFarbe().equals(istAmZug.getFarbe())){
				return false;
			}
		}
		else if (figur.binIchGespawnt() == true
				&& (!(figur.getBinIchAufEndpostion()))&& figur !=null) {
			int ZielfeldID = ermittleZielfeldID(figur);
			if (figur.getIstImZiel() == true){
				System.out.println("Fall2");
				if (kannZiehenEndfelder(figur, ermittleEndfeldSchritte(figur)) == true){
					System.out.println("Fall2.1");
					return true;}}
			if ((figur.getFelderGelaufen() + getAugenzahl()) > spielbrett.getAlleStandardFelder().length) {
				if (ermittleEndfeldSchritte(figur) <= 4){
					if (kannZiehenEndfelder(figur, ermittleEndfeldSchritte(figur)) == true) {
						figur.setKannInsZiel(true);
						System.out.println("Fall2.2");
						return true;
					} else
						return false;
				}
			}
			Standardfeld Zielfeld = spielbrett.getAlleStandardFelder()[ZielfeldID];
			if (Zielfeld.getFigur() == null){
				System.out.println("Fall3.1");
				return true;}
			else if (figur.kannSchlagen(Zielfeld) == true){
				System.out.println("Fall3.2");
				return true;}
			else
				return false;
		} 
		return false;

	}

	/**
	 * Methode, die prüft, ob eine Figur, die entweder auf einem Standard- oder
	 * Endfeld steht, innerhalb der Endfelder ziehen kann. Sie wird nur von der
	 * kannZiehen-Methode aufgerufen, falls die Figur in die Endfelder einziehen
	 * soll.
	 * 
	 * @param figur
	 *            - Figur, deren Zug-Möglichkeiten abgefragt werden
	 * @param zuZiehen
	 *            anzahl der Züge, die innerhalb des Endfeldes noch getätigt
	 *            werden sollen
	 * @return booleanschen Wert, true falls die Figur ziehen kann, false falls
	 *         nicht
	 */
	private boolean kannZiehenEndfelder(Spielfigur figur, int zuZiehen) {
		Endfeld[] endfelderIstAmZug = istAmZug.getEndFelder();
		if (figur.getMeinFeld() instanceof Standardfeld) {
			if (zuZiehen > 4)
				return false;
			if (zuZiehen == 1) {
				if (endfelderIstAmZug[0].getFigur() == null)
					return true;
			}
			if (zuZiehen == 2) {
				if (endfelderIstAmZug[1].getFigur() == null
						&& kannZiehenEndfelder(figur, 1) == true)
					return true;
			}
			if (zuZiehen == 3) {
				if (endfelderIstAmZug[2].getFigur() == null
						&& kannZiehenEndfelder(figur, 2) == true) {
					return true;
				}
			}
			if (zuZiehen == 4) {
				if (endfelderIstAmZug[3].getFigur() == null
						&& kannZiehenEndfelder(figur, 3) == true) {
					return true;
				}
			}

			else
				return false;
		}

		else if (figur.getMeinFeld() instanceof Endfeld) {
			if (figur.getMeinFeld().equals(endfelderIstAmZug[0])) {
				if (zuZiehen > 3)
					return false;
				if (zuZiehen == 1) {
					if (endfelderIstAmZug[1].getFigur() == null)
						return true;
					else
						return false;
				}
				if (zuZiehen == 2) {
					if (kannZiehenEndfelder(figur, 1) == true
							&& endfelderIstAmZug[2] == null)
						return true;
					else
						return false;
				}
				if (zuZiehen == 3) {
					if (kannZiehenEndfelder(figur, 2) == true
							&& endfelderIstAmZug[3] == null)
						return true;
					else
						return false;
				}
			}
			if (figur.getMeinFeld().equals(endfelderIstAmZug[1])) {
				if (zuZiehen > 2)
					return false;
				if (zuZiehen == 1) {
					if (endfelderIstAmZug[2].getFigur() == null)
						return true;
					else
						return false;
				}
				if (zuZiehen == 2)
					if (kannZiehenEndfelder(figur, 1) == true
							&& endfelderIstAmZug[2] == null)
						return true;
			}

			if (figur.getMeinFeld().equals(endfelderIstAmZug[2])) {
				if (zuZiehen > 1)
					return false;
				if (endfelderIstAmZug[3].getFigur() == null)
					return true;
				else
					return false;
			}
		}
		return false;

	}

	//INARBEIT
		/** @author Felix Rosa
		 * Lässt ausgewählte Figur um die entsprechende Würfelzahl ziehen. Die Methode ruft nach jedem erfolgreichen Ziehen die Methode incSchritteGelaufen(augenzahl) aus der Klasse Spielfigur auf um den Schrittzähler zu erhöhen und nächsterSpieler() um auf den nächsten ziehenden zu verweisen.
		 *  1. Die Figur ist noch nicht gespawnt und es wurde eine 6 gewürfelt
		 *  1.1 Sollte das Spawnfeld durch keine andere Figur belegt sein spawnt die Figur auf das Spawnfeld!
		 *  1.2 Sollte das Spawnfeld durch eine Figur der eigenen Farbe belegt sein, so wird für diese Figur die Methode ziehen() aufgerufen!
		 *  1.3 Sollte das Spawnfeld durch eine Figur der gegnerischen Farbe belegt sein, so wird diese geschlagen und die zu spawnende Figur zieht auf das Feld!
		 *  2. Die Figur ist gespawnt und es wurde eine 6 gewürfelt!
		 *  2.1 Sollte die Figur auf den Endfeldern stehen, wird die Methode inEndefelderZiehen() auferufen
		 *  2.2 Sollte die Figur vor den Endfeldern stehen und in die Endfelder ziehen können, wir die Methode aufgerufen und über eine Berechnung der int zuZiehen für die Methode angepasst
		 *  2.3 Sollte die Figur ziehen können und keine gegnerische Figur auf dem Zielfeld stehen, so zieht sie
		 *  2.4 Sollte die Figur ziehen können, aber eine gegnerische Figur befindet sich auf dem Zielfeld so schläg sie die Figur und zieht auf das Zielfeld.
		 *  3. Die Figur ist gespawnt und es wurde eine Zahl zwischen 1 und 5 gewürfelt!
		 *  3.1 Sollte die Figur auf den Endfeldern stehen, wird die Methode inEndefelderZiehen() auferufen
		 *  3.2 Sollte die Figur vor den Endfeldern stehen und in die Endfelder ziehen können, wir die Methode aufgerufen und über eine Berechnung der int zuZiehen für die Methode angepasst
		 *  3.3 Sollte die Figur ziehen können und keine gegnerische Figur auf dem Zielfeld stehen, so zieht sie
		 *  3.4 Sollte die Figur ziehen können, aber eine gegnerische Figur befindet sich auf dem Zielfeld so schläg sie die Figur und zieht auf das Zielfeld.
		 * @param figur
		 * @param augenZahl
		 */
		public void ziehen(Spielfigur figur, int augenZahl){
			System.out.println("Bin in ziehen");
			alleZugFiguren().clear();
			anzZugFiguren=0;
			setChosen();
			Spieler istAmZug = this.istAmZug;
			Spielfeld aktFeld = null;
			if(figur.getIstGespawnt()==false&&getAugenzahl()==6){
				System.out.println("1ter");
				aktFeld = (Startfeld) figur.getMeinFeld();
				String aktFeldIDS = aktFeld.getID();
				if (spielbrett.getSpawnfeld(istAmZug.getFarbe()).getFigur() == null) {
					System.out.println("Fall: Nicht Gespawnt + 6 gewürfelt + 1terFall");
					figur.setMeinFeld(spielbrett.getSpawnfeld(istAmZug.getFarbe()));
					figur.setFelderGelaufen(1);
					figur.setIstGespawnt(true);
					for(int i = 0; i<spielbrett.getAlleEndFelderEinerFarbe(istAmZug.getFarbe()).length;i++){
						if(spielbrett.getAlleStartFelderEinerFarbe(istAmZug.getFarbe())[i].getID()==aktFeldIDS){
							spielbrett.getAlleStartFelderEinerFarbe(istAmZug.getFarbe())[i].setFigur(null);
						}
					}
					System.out.println("Fall: Nicht Gespawnt + 6 gewürfelt + 1terFall:: ENDE");
				}else if(!(spielbrett.getSpawnfeld(istAmZug.getFarbe()).getFigur().getFarbe().equals(istAmZug.getFarbe()))){
					System.out.println("Fall: Nicht Gespawnt + 6 gewürfelt + 2terFall");
					schlagenSpawn(figur, aktFeldIDS);
					System.out.println("Fall: Nicht Gespawnt + 6 gewürfelt + 2terFall :: ENDE");
				}
			
			}else if(figur.getIstGespawnt()==true&&getAugenzahl()==6){
				    System.out.println("2ter");
					aktFeld =(Standardfeld) figur.getMeinFeld();
					int ZielfeldID = ermittleZielfeldID(figur);
					int EndfeldSchritte = ermittleEndfeldSchritte(figur);
					int aktFeldID = Integer.parseInt(aktFeld.getID())-1;
					/*if(figur.getIstImZiel()==true){
						ziehenEndfelder(figur, getAugenzahl());
						nächsterSpieler();
					}*/
					if(figur.getKannInsZiel()==true){
						System.out.println("Fall: Gespawnt + 6 gewürfelt + 1terFall");
						ziehenEndfelder(figur, EndfeldSchritte);
						figur.setKannInsZiel(false);
						figur.setIstImZiel(true);
						System.out.println("Fall: Gespawnt + 6 gewürfelt + 1terFall :: ENDE");
					}else if(spielbrett.getStandardFelder()[ZielfeldID].getFigur() != null && !((spielbrett.getStandardFelder()[ZielfeldID].getFigur().getFarbe()).equals(istAmZug.getFarbe()))){
						System.out.println("Fall: Gespawnt + 6 gewürfelt + 2terFall");
						schlagen(figur, ZielfeldID);
						System.out.println("Fall: Gespawnt + 6 gewürfelt + 2terFall :: ENDE");					
					}else if(spielbrett.getStandardFelder()[ZielfeldID].getFigur()==null){	
						System.out.println("Fall: Gespawnt + 6 gewürfelt + 3terFall");
						figur.setMeinFeld(spielbrett.getStandardFelder()[ZielfeldID]);
						spielbrett.getStandardFelder()[aktFeldID].setFigur(null);
						figur.incSchritteGelaufen(getAugenzahl());
						System.out.println("Fall: Gespawnt + 6 gewürfelt + 3terFall :: ENDE");
						}
					
			}else if(figur.getIstGespawnt()==true&&getAugenzahl()!=6){
						System.out.println("3ter");
						int ZielfeldID = ermittleZielfeldID(figur);
						int EndfeldSchritte = ermittleEndfeldSchritte(figur);
						aktFeld =(Standardfeld) figur.getMeinFeld();
						int aktFeldID = Integer.parseInt(aktFeld.getID())-1;
						if(figur.getIstImZiel()==true){
							System.out.println("Fall: Gespawnt + !6 gewürfelt + 1terFall");
							ziehenEndfelder(figur, getAugenzahl());
							nächsterSpieler();
							System.out.println("Fall: Gespawnt + !6 gewürfelt + 1terFall :: ENDE");
						}else if(figur.getKannInsZiel()==true){
								System.out.println("Fall: Gespawnt + !6 gewürfelt + 2terFall");
								ziehenEndfelder(figur, EndfeldSchritte);
								figur.setKannInsZiel(false);
								figur.setIstImZiel(true);
								figur.incSchritteGelaufen(getAugenzahl());
								nächsterSpieler();
								System.out.println("Fall: Gespawnt + !6 gewürfelt + 2terFall :: ENDE");
						}else if( spielbrett.getStandardFelder()[ermittleZielfeldID(figur)].getFigur() != null &&(!(spielbrett.getStandardFelder()[ermittleZielfeldID(figur)].getFigur().getFarbe().equals(istAmZug.getFarbe())))){
								System.out.println("Fall: Gespawnt + !6 gewürfelt + 3terFall");
								schlagen(figur, ZielfeldID);
								nächsterSpieler();
								System.out.println("Fall: Gespawnt + !6 gewürfelt + 3terFall :: ENDE");
							}
						else if(spielbrett.getStandardFelder()[ermittleZielfeldID(figur)].getFigur()==null){
								System.out.println("Fall: Gespawnt + !6 gewürfelt + 4terFall");
								figur.setMeinFeld(spielbrett.getStandardFelder()[aktFeldID+getAugenzahl()]);
								spielbrett.getStandardFelder()[aktFeldID].setFigur(null);
								figur.incSchritteGelaufen(getAugenzahl());
								nächsterSpieler();
								System.out.println("Fall: Gespawnt + !6 gewürfelt + 4terFall :: ENDE");
				}
				
			}//IstDasHierNötig?EsIst23.27UhrMeinGehirnIstDezentZerf****UndIchWeißEsNicht -> throw new BrainException("Ich bin zu dumm für RTL! #BöhmiIstDerÖhmi");
			//Unnötig?
			System.out.println("Figur " + figur.getFelderGelaufen() + " " +  figur.getMeinFeld()  + " " + figur.getIstGespawnt() +"\n" +"\n");
			
		}
		
		/**@author Felix Rosa
		 * 
		 * @param figur
		 * @param ZielfeldID
		 */
		public void schlagen(Spielfigur figur, int ZielfeldID){
			Spielfigur zuSchlagen = null;
			Standardfeld aktFeld =(Standardfeld) figur.getMeinFeld();
			int aktFeldID = Integer.parseInt(aktFeld.getID())-1;
			zuSchlagen = spielbrett.getStandardFelder()[ZielfeldID].getFigur();
			figur.setMeinFeld(spielbrett.getStandardFelder()[ZielfeldID]);
			spielbrett.getStandardFelder()[aktFeldID].setFigur(null);
			figur.incSchritteGelaufen(getAugenzahl());
			if(zuSchlagen.getFarbe().equals(FarbEnum.GRÜN)){
				for(int i = 0; i< spielbrett.getAlleStartFelderGrün().length; i++){
					if(spielbrett.getAlleStartFelderGrün()[i].getFigur()==null){
						zuSchlagen.setMeinFeld(spielbrett.getAlleStartFelderGrün()[i]);
						zuSchlagen.resetFelderGelaufen();
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.ROT)){
				for(int i = 0; i< spielbrett.getAlleStartFelderRot().length; i++){
					if(spielbrett.getAlleStartFelderRot()[i].getFigur()==null){
						zuSchlagen.setMeinFeld(spielbrett.getAlleStartFelderRot()[i]);
						zuSchlagen.resetFelderGelaufen();
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.BLAU)){
				for(int i = 0; i< spielbrett.getAlleStartFelderBlau().length; i++){
					if(spielbrett.getAlleStartFelderBlau()[i].getFigur()==null){
						zuSchlagen.setMeinFeld(spielbrett.getAlleStartFelderBlau()[i]);
						zuSchlagen.resetFelderGelaufen();
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.GELB)){
				for(int i = 0; i< spielbrett.getAlleStartFelderGelb().length; i++){
					if(spielbrett.getAlleStartFelderGelb()[i].getFigur()==null){
						zuSchlagen.setMeinFeld(spielbrett.getAlleStartFelderGelb()[i]);
						zuSchlagen.resetFelderGelaufen();
						break;
					}
				}
			}
		}
		
		/**@author Felix Rosa
		 * 
		 * @param figur
		 */
		public void schlagenSpawn(Spielfigur figur, String aktFeldIDS){
			Spieler istAmZug = this.istAmZug;
			Spielfigur zuSchlagen = null;
			zuSchlagen = spielbrett.getSpawnfeld(istAmZug.getFarbe()).getFigur();
			spielbrett.getSpawnfeld(istAmZug.getFarbe()).setFigur(null);
			figur.setMeinFeld(spielbrett.getSpawnfeld(istAmZug.getFarbe()));
			figur.setFelderGelaufen(1);
			figur.setIstGespawnt(true);
			for(int i = 0; i<spielbrett.getAlleEndFelderEinerFarbe(istAmZug.getFarbe()).length;i++){
				if(spielbrett.getAlleStartFelderEinerFarbe(istAmZug.getFarbe())[i].getID()==aktFeldIDS){
					spielbrett.getAlleStartFelderEinerFarbe(istAmZug.getFarbe())[i].setFigur(null);
				}
			}
			if(zuSchlagen.getFarbe().equals(FarbEnum.GRÜN)){
				for(int i = 0; i< spielbrett.getAlleStartFelderGrün().length; i++){
					if(spielbrett.getAlleStartFelderGrün()[i].getFigur()==null){
						zuSchlagen.setMeinFeld(spielbrett.getAlleStartFelderGrün()[i]);
						zuSchlagen.resetFelderGelaufen();
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.ROT)){
				for(int i = 0; i< spielbrett.getAlleStartFelderRot().length; i++){
					if(spielbrett.getAlleStartFelderRot()[i].getFigur()==null){
						zuSchlagen.setMeinFeld(spielbrett.getAlleStartFelderRot()[i]);
						zuSchlagen.resetFelderGelaufen();
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.BLAU)){
				for(int i = 0; i< spielbrett.getAlleStartFelderBlau().length; i++){
					zuSchlagen.setMeinFeld(spielbrett.getAlleStartFelderBlau()[i]);
					if(spielbrett.getAlleStartFelderBlau()[i].getFigur()==null){
						zuSchlagen.setMeinFeld(spielbrett.getAlleStartFelderBlau()[i]);
						zuSchlagen.resetFelderGelaufen();
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.GELB)){
				for(int i = 0; i< spielbrett.getAlleStartFelderGelb().length; i++){
					if(spielbrett.getAlleStartFelderGelb()[i].getFigur()==null){
						zuSchlagen.setMeinFeld(spielbrett.getAlleStartFelderGelb()[i]);
						zuSchlagen.resetFelderGelaufen();
						break;
					}
				}
			}
			
		}
	

	/**@author Anna Rosa, Felix Rosa
	 * Methode, die eine Figur um eine bestimmte Anzahl an Zügen in seinem
	 * Endfeld ziehen lässt.
	 * 
	 * @param figur
	 *            - Die Figur, die ziehen soll
	 * @param augenZahl
	 *            - Anzahl der Züge, die Figur ziehen soll
	 */
	public void ziehenEndfelder(Spielfigur figur, int restSchritte) {
		Spielfeld aktFeld = figur.getMeinFeld();
		restSchritte = restSchritte - 1;
		String aktFeldIDS = "";
		int aktFeldID = 0;
		if (kannZiehenEndfelder(figur, restSchritte) != true) {
			System.out.println("Figur kann nicht ziehen!");
			throw new RuntimeException("Figur kann nicht ziehen!");
		}
		if(aktFeld instanceof Endfeld){
				aktFeldIDS = figur.getMeinFeld().getID();
		}else if(aktFeld instanceof Standardfeld){
				aktFeldID = Integer.parseInt(figur.getMeinFeld().getID());
		}
		figur.setMeinFeld(spielbrett.getAlleEndFelderEinerFarbe(istAmZug.getFarbe())[restSchritte]);
		if(aktFeld instanceof Endfeld){
			for(int i = 0; i<spielbrett.getAlleEndFelderEinerFarbe(istAmZug.getFarbe()).length;i++){
				if(spielbrett.getAlleEndFelderEinerFarbe(istAmZug.getFarbe())[i].getID()==aktFeldIDS){
					spielbrett.getAlleEndFelderEinerFarbe(istAmZug.getFarbe())[i].setFigur(null);
				}
			}
		}else if(aktFeld instanceof Standardfeld){
			spielbrett.getAlleStandardFelder()[aktFeldID-1].setFigur(null);
		}
		aufEndposition(figur);
		System.out.println("Hollaho.--------------------Ich bin in der ziehenEndfelder");
	}
	
	/**
	 * Methode, die überprüft, ob eine Figur in ihrer endgültigen Endposition ist und wenn dies der Fall 
	 * das Attribut binIchAufEndposition auf true setzt.
	 * @param figur - zu Überprüfende Figur
	 */
	public void aufEndposition(Spielfigur figur){
		if(! (figur.getMeinFeld() instanceof Endfeld))
			return;
		else{
			FarbEnum farbe= figur.getFarbe();
			int index=0;
			for(int i=0; i<spielbrett.getAlleEndFelderEinerFarbe(farbe).length; i++){
				if(figur.equals(spielbrett.getAlleEndFelderEinerFarbe(farbe)[i])){
					index=i;
				}
			}
			switch(index){
				case 0:
					if(!(spielbrett.getAlleEndFelderEinerFarbe(farbe)[1]==null)&& !(spielbrett.getAlleEndFelderEinerFarbe(farbe)[2]==null) && !(spielbrett.getAlleEndFelderEinerFarbe(farbe)[3]==null)){
						figur.setBinIchAufEndposition(true);
					}
					break;
				case 1: 
					if(!(spielbrett.getAlleEndFelderEinerFarbe(farbe)[2]==null) && !(spielbrett.getAlleEndFelderEinerFarbe(farbe)[3]==null)){
						figur.setBinIchAufEndposition(true);
					}	
					break;
				case 2:
					if(!(spielbrett.getAlleEndFelderEinerFarbe(farbe)[3]==null))
						figur.setBinIchAufEndposition(true);
					break;
				case 3: 
					figur.setBinIchAufEndposition(true);					
			}
		}
	}

	/**
	 * Methode zu DebugZwecken. Ermöglicht es, zu Testzwecken auf Spieler
	 * zuzugreifen, die nicht am Zug sind.
	 * 
	 * @param spielerzahl
	 *            - Bestimmter Spielerindex vom Typ int
	 * @return spieler - Ein OPbjekt vom Typ Spieler.
	 */
	public Spieler DebugGetSpieler(int spielerzahl) {
		return spieler[spielerzahl - 1];
	}

	/**
	 * Methode zu DebugZwecken. Ermöglicht es, eine Figur mit einem Zug vor die
	 * Endfelder zu setzen.
	 * 
	 * @param augenzahl
	 *            - int Wert
	 */
	public void DebugSetAugenzahl(int augenzahl) {
		// Unterschied zur Originalmethode
		if (augenzahl > 40 || augenzahl < 1)
			throw new RuntimeException("Fehlerhaftes Würfelergebnis");
		this.augenzahl = augenzahl;
	}
	public void setChosen(){
		if(getIstAmZug()!=null)
			this.chosenFigur=getIstAmZug().getZugFigur();
	}
	public Spielfigur getChosen(){
		return this.chosenFigur;
	}

	/**
	 * Methode zum Auswählen der Figur, die ziehen soll über eine von außen
	 * (Spieler/ KI) übergebene Spielfeld-ID
	 * 
	 * @param id
	 *            - ID des Spielfeldes, auf dem sich die Figur vor Ausführen des
	 *            Zuges befindet
	 * @return figur - gibt die Figur, die ziehen soll zurück, falls diese
	 *         ziehen kann
	 */
	@SuppressWarnings("finally")
	public Spielfigur wähleFigur(String id) {
		if(getZugMöglich()==false)throw new RuntimeException("Zug nicht möglich!");
		FarbEnum farbeIstAmZug = istAmZug.getFarbe();
		Spielfeld f = spielbrett.getFeld(id, farbeIstAmZug);
		Spielfigur figur = f.getFigur();
		try{
		if (!(alleZugFiguren().contains(figur)))
			throw new FigurKannNichtZiehenException(String.format(
					"Figur %s kann nicht ziehen!", figur));
		else{
			getIstAmZug().setZugFigur(figur);
			ziehen(figur,getAugenzahl());
			return figur;}
		}
		catch(FigurKannNichtZiehenException e){
			System.out.println(e);
		}
		finally{
			return figur;
		}
		
	}

	/**
	 * @author Alexander Brückner
	 * @version 0.1 Methode für Anfangszug. Sind alle Figuren eines Spielers auf
	 *          Startfeldern wird dreimal gewürfelt. Wird eine 6 gewürfelt, wird
	 *          eine Figur ausgewählt, die ziehen soll. Kann die Figur nicht
	 *          ziehen oder ist nicht auf dem Startfeld, wird solange eine neue
	 *          gewählt, bis es möglich ist, raus aufs Feld zu gehen. Steht auf
	 *          dem jeweiligen Spawnpoint eine feindliche Figur, wird diese
	 *          durch die spawnende Figur geschlagen. Sind nicht alle Figuren
	 *          eines Spielers in der Startregion (also ist mindestens eine auf
	 *          einem Spielfeld), wird die Methode beendet.
	 * @param s
	 *            - Spieler
	 * @throws FigurKannNichtZiehenException
	 * @throws RuntimeException
	 */

/*	public void ersterZug() {
		// Sicherheitsabfragen, Abbruchbedingung
		Spieler s = this.getIstAmZug();
		if (s == null)
			throw new RuntimeException("Spieler darf nicht null sein");
		if (!s.alleAufSpawn())
			return;

		// Erfolgsbool anlegen
		boolean erfolg = false;

		// Zu spawnende Figur anlegen
		Spielfigur spawnMe = null;
		// Figur anlegen, die ggf. das Zeitliche segnet
		Spielfigur killMe = null;

		// Drei mal würfeln und alles durchchecken
		for (int i = 0; i < 3; i++) {

			if (s.getMeinWürfel().werfen() == 6) {

				// fragt ab, ob Spawnpoint der jeweiligen Farbe belegt ist
				if (spielbrett.getSpawnfeld(s.getFarbe()).getFigur() != null) {
					Spielfigur fig = spielbrett.getSpawnfeld(s.getFarbe())
							.getFigur();

					// falls ja, kann ich die Figur erledigen?
					// falls ja, Figur wählen und ab dafür..
					// falls nicht, ist der Zug nicht möglich, es wird nochmal
					// gewürfelt

					// Hab ich die gleiche Farbe wie die Figur, die auf meinem
					// Spawn steht?
					if (fig.getFarbe() != s.getFarbe()) {

						// Ja? Dann weg damit.
						// S1 ist mit Sicherheit belegt. (Die Methode hier kommt
						// sowieso nur dann zum Einsatz,
						// Wenn alle meine Figürchen auf den Startfeldern
						// stehen.)

						spawnMe = wähleFigur("S1");
						// spawnMe wird nun zum Mörder von killMe:
						killMe = spielbrett.getSpawnfeld(s.getFarbe())
								.getFigur();
						// Hier schlagen-Methode aufrufen, spawnMe erledigt
						// killMe

					}

				}

				// Ist das Startfeld überhaupt nicht belegt, -> Spawn!
				spawnMe = wähleFigur("S1");
				spawnMe.spawn(spielbrett.getSpawnfeld(s.getFarbe()));

			}

		}

	}*/

	public ArrayList<Spielfigur> alleZugFiguren() {
		return zugFiguren;
	}
	public boolean kannSpawnen(Spielfigur figur) {
		if (getAugenzahl() == 6 && figur.binIchGespawnt() == false)
			if (getSpielbrett().getSpawnfeld(getIstAmZug().getFarbe())
					.getFigur() == null
					|| getSpielbrett().getSpawnfeld(getIstAmZug().getFarbe())
							.getFigur().getFarbe() != getIstAmZug().getFarbe())
				return true;
			else
				return false;
		else
			return false;
	}
	// // hack wird im offiziellen Spiel ersetzt. die Methode testwurf dann
	// durch das richtige würfeln
	public void würfeln(int hack) {
		try{
			incAnzWürfe();
			int spawncounter = 0;
			if (getHatBegonnen() == false)
				throw new RuntimeException("Spiel hat noch nicht begonnen");
			int augenzahl = istAmZug.getMeinWürfel().testWurf(hack);
			setAugenzahl(augenzahl);
			for (Spielfigur sf : istAmZug.alleFiguren()){
				if (kannIchZiehen(sf) == true) {
					sf.setKannZiehen(true);
					setZugFiguren(sf);
				}
				if(sf.binIchGespawnt()==false)
					spawncounter++;
			}
			if (spawncounter == getIstAmZug().getAnzFiguren())
				setAlleAufSpawn(true);
			
			//DEBUG SYSO
			System.out.println("Für Zug Mögliche Figuren: "+alleZugFiguren().size());
		
			if (alleZugFiguren().isEmpty()==true && getAlleAufSpawn()==true && anzWürfe<=3 && getAugenzahl()!=6) {
				setZugMöglich(false);
			} 
			else if(alleZugFiguren().isEmpty()==true && getAlleAufSpawn()==true && anzWürfe > 3 && getAugenzahl()!= 6){
				setZugMöglich(false);
				nächsterSpieler();
			}
			else{
				setZugMöglich(true);
				setAlleAufSpawn(false);
			}
			//DEBUG SYSOS
			//System.out.println("Anzahl würfe: "+anzWürfe);
			System.out.println("Augenzahl: "+getAugenzahl());
			System.out.println("Am Zug: "+getIstAmZug());
			//System.out.println("Zug Möglich: "+getZugMöglich());
			//System.out.println("Alle auf Spawn: "+getAlleAufSpawn());
			//System.out.println("");
			}
			catch(RuntimeException e){
				System.out.println(e);
			}		
	}
	/**
	 * Methode, die den nächsten Spieler als am Zug seienden Spieler setzt und dem vorherigen 
	 * die Eigenschaft, dass er am Zug ist, auf false setzt.
	 */
	public void nächsterSpieler() {
		alleZugFiguren().clear();
		anzZugFiguren=0;
		anzWürfe=0;
		
		for (int i = 0; i< getAnzahlSpieler();i++){
			if(getIstAmZug().equals(spieler[i])){
				if(i == getAnzahlSpieler()-1){
					setIstAmZug(spieler[0]);
				}
				else{
					setIstAmZug(spieler[i+1]);
				}
			break;
			}
		}

	/*	for (int i = 0; i<getAnzahlSpieler();i++){
			if(getIstAmZug().equals(spieler[i]))
				if (i == getAnzahlSpieler()-1){
					setIstAmZug(spieler[0]);
				}
				else
					setIstAmZug(spieler[i]);
		}
		
		*/
		/*			
		for (int i = 0; i < getAnzahlSpieler(); i++) {
			if (i < getAnzahlSpieler()) {
				if (istAmZug.equals(spieler[i])) {
					istAmZug = spieler[i + 1];
					spieler[i + 1].setAmZug(true);
					spieler[i].setAmZug(false);
					break;
				}
			}

			if (i == getAnzahlSpieler()) {
				if (istAmZug.equals(spieler[i])) {
					istAmZug = spieler[0];
					spieler[0].setAmZug(true);
					spieler[i].setAmZug(false);
				}

			}
		}
*/
	}

	// Interface - ausgabeFiguren: Gibt alle Figuren aller Spieler aus
	public void ausgabeFiguren() {

		Spieler s = this.getIstAmZug();
		for (Spielfigur f : s.alleFiguren()) {
			System.out.println(f);
		}

	}

	public void ausgabeZugFiguren() {

		Spieler s = this.getIstAmZug();
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getKannZiehen()) {
				System.out.println(f);
			}
		}

	}

	public void ausgabeFigurenImZiel() {

		Spieler s = this.getIstAmZug();
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getIstImZiel()) {
				System.out.println(f);
			}
		}

	}

	public void ausgabeSpielerListe() {
		for (Spieler s : spieler) {
			if (s == null)
				continue;
			else {
				System.out.println(s);
			}
		}
	}

	public boolean zugDurchführen(String ID) {

		boolean zugErfolgreich;
		try {
			Spielfigur f = wähleFigur(ID);
			ziehen(f, this.getIstAmZug().getMeinWürfel().werfen());
			zugErfolgreich = true;
			System.out.println("Zug erfolgreich!");
			return zugErfolgreich;
		}

		catch (FigurKannNichtZiehenException e) {
			zugErfolgreich = false;
			System.out.println("Zug fehlgeschlagen!");
			return zugErfolgreich;
		}

	}

	public void ausgabeFigurenAufStartfeld() {
		Spieler s = this.getIstAmZug();
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getMeinFeld() instanceof Startfeld) {
				System.out.println(f);
			}
		}

	}
}
