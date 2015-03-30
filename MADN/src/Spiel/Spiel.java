package Spiel;

import java.util.ArrayList;
import java.util.Arrays;

import Basisklassen.*;
import Hilfsklassen.*;

/**
 * Klasse Spiel als Regelwerk für MADN. Sie beinhaltet alle notwendigen
 * Methoden, um einen reibungslosen Spielfluss zu gewährleisten. In dieser
 * Klasse sind DebugMethoden implementiert, sie sind ganz am Ende der Datei zu
 * finden.
 * 
 * @author Kevin Schrötter, Felix Rosa, Anna Rosa, Alexander Brückner
 * @version 2.2
 *
 */

public class Spiel implements iBediener{

	private Spieler[] spieler = new Spieler[4];

	private Spieler istAmZug;

	private boolean zugMöglich = false;

	private Spielbrett spielbrett;

	private int anzahlSpieler;

	private boolean hatBegonnen = false;

	private boolean istBeendet = false;

	private int anzWürfe = 0;

	private int augenzahl = 0;

	private ArrayList<Spielfigur> zugFiguren = new ArrayList<Spielfigur>(0);

	private int anzZugFiguren = 0;

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
	private void setIstAmZug(Spieler sIAZ) {
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
	private Spieler getIstAmZug() {
		if (getHatBegonnen()==false) throw new RuntimeException("Spiel hat noch nicht gestartet!");
		return this.istAmZug;
	}

	/**
	 * Setter für zugMöglich
	 * 
	 * @param zugMöglich
	 *            - true oder false boolean
	 */
	private void setZugMöglich(boolean zugMöglich) {
		this.zugMöglich = zugMöglich;
	}

	/**
	 * Getter für zugMöglich
	 * 
	 * @return zugMöglich - true oder false boolean
	 */
	private boolean getZugMöglich() {
		return this.zugMöglich;
	}

	/**
	 * Setter für das Spielbrett Es wird nur gesetzt, wenn es sich beim
	 * Übergabeparameter um ein Objekt der Klasse Spielbrett handelt.
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
	private void incAnzahlSpieler() {
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
	private void setHatBegonnen(boolean hatBegonnen) {
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
	private void setIstBeendet(boolean istBeendet) {
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
	private void incAnzWürfe() {
		this.anzWürfe++;
	}
	/**
	 * Getter für anzWürfe
	 * 
	 * @return anzWürfe - int
	 */
	private int getAnzWürfe() {
		return this.anzWürfe;
	}

	/**
	 * Setter für die Augenzahl HINWEIS: DebugMethode zu Testzwecken als
	 * hilfsmethode am Ende der Datei
	 * 
	 * @param augenzahl
	 *            - Augenzahl als int
	 */
	private void setAugenzahl(int augenzahl) {
		if (augenzahl < 1 || augenzahl > 6)
			throw new RuntimeException("Fehlerhaftes Würfelergebnis");
		this.augenzahl = augenzahl;
	}

	/**
	 * Getter für die Augenzahl
	 * 
	 * @return augenzahl - int Wert
	 */
	private int getAugenzahl() {
		return this.augenzahl;
	}

	/**
	 * Setter für die Anzahl der vorhandenen zugFiguren, inkrementiert
	 */
	private void incAnzZugFiguren() {
		if (this.anzZugFiguren < 0 || this.anzZugFiguren > 4)
			throw new RuntimeException("Fehöer: ZugFigurenLimit erreicht!");
		this.anzZugFiguren++;
	}
	/**
	 * ZusatzMethode zum loeschen der anzZugFiguren
	 */
	private void deleteAnzZugFiguren(){
		this.anzZugFiguren=0;
	}
	/**
	 * Getter für anzTugFiguren
	 * 
	 * @return anzZugFiguren - int Wert
	 */
	private int getAnzZugFiguren() {
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
	private void setZugFiguren(Spielfigur figur) {
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

	private Spielfigur getZugFiguren(int index) {
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
	 * Wichtige Methode fuer den Spielverlauf. Sie gibt alle Figuren zurueck, die ziehen koennen
	 * @return zugFiguren - ArrayList mit allen Figuren, die Ziehen koennen
	 */
	public ArrayList<Spielfigur> alleZugFiguren() {
		return zugFiguren;
	}
	/**
	 * Hilfsmethode für die Mehtode würfeln()
	 * @param figur - Spielfigur
	 */
	private void setChosen(Spielfigur figur){

			this.chosenFigur=figur;
	}
	/**
	 * Hilfsmethode für die Methode würfeln()
	 * @return chosenFigur - Spielfigur
	 */
	private Spielfigur getChosen(){
		return this.chosenFigur;
	}
	/**
	 * Setter für alleAufSpawn
	 * 
	 * @param alleAufSpawn
	 *            - boolean
	 */
	private void setAlleAufSpawn(boolean alleAufSpawn) {
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
	public void spielerHinzufügen(String name, int farbID, int verhaltenID) {
		if (getHatBegonnen() == true)
			throw new RuntimeException("Spiel hat schon begonnen");
		
		FarbEnum farbe=null;
		switch(farbID){
		case 1: farbe = FarbEnum.ROT;break;
		case 2: farbe = FarbEnum.BLAU;break;
		case 3: farbe = FarbEnum.GELB;break;
		case 4: farbe = FarbEnum.GRÜN;break;
		default: throw new RuntimeException("Ungültige Farbe!");
		}
		
		String verhalten = null;
		switch(verhaltenID){
			case 1: verhalten = "aggressiv";break;
			case 2: verhalten = "defensiv";break;
			default: verhalten = null;break;
		}
		
		
		
		if (farbe == null)
			throw new RuntimeException("Spieler braucht eine Farbe");
		
		
		for (int i = 0; i <= 3; i++) {
			if (spieler[i] != null) {
				if (farbe.equals(spieler[i].getFarbe()))
					throw new RuntimeException("Farbe " + spieler[i].getFarbe() +" schon vorhanden! Bitte waehlen Sie eine andere Farbe!");
			}
		}
		Startfeld[] startfelder = getSpielbrett().getAlleStartFelderEinerFarbe(farbe);
		Endfeld[] endfelder = getSpielbrett().getAlleEndFelderEinerFarbe(farbe);
		if (verhalten == null) {
			spieler[getAnzahlSpieler()] = new Spieler(name, farbe, startfelder,endfelder);
		} else if (verhalten != null) {
			spieler[getAnzahlSpieler()] = new Spieler(name, farbe, startfelder,endfelder, verhalten);
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
		if(getHatBegonnen()==true)throw new RuntimeException("Spiel hat bereits begonnen!");
		if(spieler[0]==null)throw new RuntimeException("Ein Spiel braucht mindestens einen Spieler!");
		
		setHatBegonnen(true);
		
		if(spieler[0]!=null){
			spieler[0].setAmZug(true);
			setIstAmZug(spieler[0]);
		}else setIstBeendet(true);
	}

	/**@author Felix Rosa
	 * Methode ermittelt die ZielfeldID des Feldes auf das die ziehenden Figur ziehen soll.
	 * Da in Arrays gearbeitet wird, wird von Zielfeld der Wert 1 abgezogen.
	 * @param figur - Figur von der die ID des Zielfeldes ermittelte werden soll
	 * @return ZielfeldID - 1 - ID des Zielfeldes -1
	 */
	private int ermittleZielfeldID(Spielfigur figur){
		Standardfeld aktFeld =(Standardfeld) figur.getMeinFeld();
		int ZielfeldID = Integer.parseInt(aktFeld.getID())+getAugenzahl();
		if(ZielfeldID==getSpielbrett().getAlleStandardFelder().length){
			return getSpielbrett().getAlleStandardFelder().length-1;
		}if(ZielfeldID>getSpielbrett().getAlleStandardFelder().length){
			return (ZielfeldID-1)-getSpielbrett().getAlleStandardFelder().length;
		}
		return ZielfeldID-1;
	}
	
	/**@author Felix Rosa
	 * Methode ermittelt die Schritte die eine Figur in ihren Endfeldern laufen kann. 
	 * @param figur - Figur von der ermittelt werden soll, wie weit Sie in ihren Endfeldern laufen kann.
	 * @return bisherige gelaufene Feld + gewürfelte Augenzahl abzüglich der Spielfeldlänge
	 */
	private int ermittleEndfeldSchritte(Spielfigur figur){
		return figur.getFelderGelaufen()+getAugenzahl() - getSpielbrett().getAlleStandardFelder().length;

	}
	/** @author Felix Rosa
	 *  v. 1.1(kannIchZiehen um Spawn-Abfrage erweitert!)
	 *  Methode prüft ob übergebene Figur in Kombination mit übergebener Würfelzahl ziehen kann.
	 *  Dabei wird überprüft ob Zug aus Spielfeld ins Endfeld oder Züge im Endfeld möglich sind!
	 *  Desweiteren wird geprüft ob eine Figur eine Figur auf einem belegten Feld schlagen kann und setzt das Attribut figur.kannSchlagen.
	 * @param figur - figur von der überprüft werden soll ob ziehen möglich ist
	 * @return boolean - true wenn ziehen möglich, false wenn nicht
	 */
	private boolean kannIchZiehen(Spielfigur figur) {
		Spieler istAmZug = this.getIstAmZug();
		if(figur.getIstGespawnt()==false&&getAugenzahl()==6){
			//System.out.println("Fall1");
			if(getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).getFigur()==null){
				//System.out.println("Fall1.1");
				return true;
			}else if(!(getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).getFigur().getFarbe().equals(istAmZug.getFarbe()))){
				//System.out.println("Fall1.2");
				figur.setKannSchlagen(true);
				return true;
			}else if(getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).getFigur().getFarbe().equals(istAmZug.getFarbe())){
				return false;
			}
		}
		else if (figur.binIchGespawnt() == true
				& (figur.getBinIchAufEndpostion()==false)& figur !=null) {
			//System.out.println(ZielfeldID);
			if (figur.getIstImZiel() == true){
				if (kannZiehenEndfelder(figur, getAugenzahl()) == true){
					return true;}
					else return false;
				}
			if ((figur.getFelderGelaufen() + getAugenzahl()) > getSpielbrett().getAlleStandardFelder().length) {
				if (ermittleEndfeldSchritte(figur) <= 4){
					if (kannZiehenEndfelder(figur, ermittleEndfeldSchritte(figur)) == true) {
						figur.setKannInsZiel(true);
						//System.out.println("Fall2.2");
						return true;
					} else
						return false;
				}
			}
			int ZielfeldID = ermittleZielfeldID(figur);
			Standardfeld Zielfeld = getSpielbrett().getAlleStandardFelder()[ZielfeldID];
			if (Zielfeld.getFigur() == null){
				//System.out.println("Fall3.1");
				return true;}
			else if (figur.kannSchlagen(Zielfeld) == true){
				//System.out.println("Fall3.2");
				figur.setKannSchlagen(true);
				return true;}
			else
				return false;
		} 
		return false;

	}
	/*
	/**@author Anna Rosa
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
		Endfeld[] endfelderIstAmZug = getIstAmZug().getEndFelder();
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
			if (figur.getMeinFeld().getID().equals(endfelderIstAmZug[0].getID())) {
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
							&& endfelderIstAmZug[2].getFigur() == null)
						return true;
					else
						return false;
				}
				if (zuZiehen == 3) {
					if (kannZiehenEndfelder(figur, 2) == true
							&& endfelderIstAmZug[3].getFigur() == null)
						return true;
					else
						return false;
				}
			}
			if (figur.getMeinFeld().getID().equals(endfelderIstAmZug[1].getID())) {
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
							&& endfelderIstAmZug[2].getFigur() == null)
						return true;
			}

			if (figur.getMeinFeld().getID().equals(endfelderIstAmZug[2].getID())) {
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
	/*
	public boolean kannZiehenEndfelder(Spielfigur f, int zahl){
		if(zahl>4)
			return false;
		Endfeld[] felder = getSpielbrett().getAlleEndFelderEinerFarbe(f.getFarbe());
		int nr = (ermittleEndfeldSchritte(f));
		for(int i=(nr); i<4;i++)
			if(felder[i].getFigur()!=null)
				return false;
			else;
		return true;
		
	}*/
	/** @author Felix Rosa
	 * Lässt ausgewählte Figur um die entsprechende Würfelzahl ziehen. Die Methode ruft nach jedem erfolgreichen Ziehen die Methode incSchritteGelaufen(augenzahl) 
	 * aus der Klasse Spielfigur auf um den Schrittzähler zu erhöhen und nächsterSpieler() um auf den nächsten ziehenden zu verweisen.
	 *  1. Die Figur ist noch nicht gespawnt und es wurde eine 6 gewürfelt
	 *  1.1 Sollte das Spawnfeld durch keine andere Figur belegt sein spawnt die Figur auf das Spawnfeld!
	 *  1.2 Sollte das Spawnfeld durch eine Figur der gegnerischen Farbe belegt sein, so wird diese über die Methode schlagenSpawn() geschlagen und die zu spawnende Figur zieht auf das Spawnfeld!
	 *  2. Die Figur ist gespawnt und es wurde eine 6 gewürfelt!
	 *  2.1 Sollte die Figur vor den Endfeldern stehen und in die Endfelder ziehen können, wird über ermittleEndfeldSchritte() die Anzahl der Schritte im Endfeld ermittelt und
	 *  	diese an die Methode ziehenEndfelder() übergeben, diese zieht mit der Figur in den Endfeldern.
	 *  2.2 Wenn sich eine gegenerische Figur auf dem Zielfeld befindet, wird schlagen() aufgerufen und die übergebene Figur rückt auf das Zielfeld!
	 *  2.3 Sollte die Figur ziehen können und keine gegnerische Figur auf dem Zielfeld stehen, so zieht sie auf das Zielfeld.
	 *  3. Die Figur ist gespawnt und es wurde eine Zahl zwischen 1 und 5 gewürfelt!
	 *  3.1 Sollte die Figur auf den Endfeldern stehen, wird die Methode ziehenEndfelder() aufgerufen und die Figur zieht auf das Zielfeld, dass sich aus ziehenEndfeld() ergibt.
	 *  3.2 Sollte die Figur vor den Endfeldern stehen und in die Endfelder ziehen können, wird über ermittleEndfeldSchritte() die Anzahl der Schritte im Endfeld ermittelt und
	 *  	diese an die Methode ziehenEndfelder() übergeben, diese zieht mit der Figur in den Endfeldern.
	 *  3.3 Wenn sich eine gegenerische Figur auf dem Zielfeld befindet, wird schlagen() aufgerufen und die übergebene Figur rückt auf das Zielfeld!
	 *  3.4 Sollte die Figur ziehen können und keine gegnerische Figur auf dem Zielfeld stehen, so zieht sie auf das Zielfeld.
	 * @param figur - Figur mit der gezogen wird
	 * @param augenZahl - aktuelle gewürfelte Augenzahl des Spielers
	 */
		private void ziehen(Spielfigur figur, int augenZahl){
			//System.out.println("Bin in ziehen");
			for(Spielfigur sf:alleZugFiguren()){
				sf.setKannSchlagen(false);
			}
			alleZugFiguren().clear();
			deleteAnzZugFiguren();
			setChosen(getIstAmZug().getZugFigur());
			Spieler istAmZug = this.getIstAmZug();
			Spielfeld aktFeld = null;
			if(figur.getIstGespawnt()==false&&getAugenzahl()==6){
				//System.out.println("1ter");
				aktFeld = (Startfeld) figur.getMeinFeld();
				String aktFeldIDS = aktFeld.getID();
				if (getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).getFigur() == null) {
					//System.out.println("Fall: Nicht Gespawnt + 6 gewürfelt + 1terFall");
					figur.setMeinFeld(getSpielbrett().getSpawnfeld(istAmZug.getFarbe()));
					figur.setFelderGelaufen(1);
					figur.setIstGespawnt(true);
					for(int i = 0; i<getSpielbrett().getAlleEndFelderEinerFarbe(istAmZug.getFarbe()).length;i++){
						if(getSpielbrett().getAlleStartFelderEinerFarbe(istAmZug.getFarbe())[i].getID().equals(aktFeldIDS)){
							getSpielbrett().getAlleStartFelderEinerFarbe(istAmZug.getFarbe())[i].setFigur(null);
						}
					}
					//System.out.println("Fall: Nicht Gespawnt + 6 gewürfelt + 1terFall:: ENDE");
				}else if(!(getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).getFigur().getFarbe().equals(istAmZug.getFarbe()))){
					//System.out.println("Fall: Nicht Gespawnt + 6 gewürfelt + 2terFall");
					schlagenSpawn(figur, aktFeldIDS);
					//System.out.println("Fall: Nicht Gespawnt + 6 gewürfelt + 2terFall :: ENDE");
				}
			
			}else if(figur.getIstGespawnt()==true&&getAugenzahl()==6){
				    //System.out.println("2ter");
					aktFeld =(Standardfeld) figur.getMeinFeld();
					int ZielfeldID = ermittleZielfeldID(figur);
					int EndfeldSchritte = ermittleEndfeldSchritte(figur);
					int aktFeldID = Integer.parseInt(aktFeld.getID())-1;
					/*if(figur.getIstImZiel()==true){
						ziehenEndfelder(figur, getAugenzahl());
						nächsterSpieler();
					}*/
					if(figur.getKannInsZiel()==true){
						//System.out.println("Fall: Gespawnt + 6 gewürfelt + 1terFall");
						ziehenAufEndfelder(figur, EndfeldSchritte);
						figur.setFelderGelaufen(getSpielbrett().getAlleStandardFelder().length-figur.getFelderGelaufen());
						figur.setKannInsZiel(false);
						figur.setIstImZiel(true);
						//System.out.println("Fall: Gespawnt + 6 gewürfelt + 1terFall :: ENDE");
					}else if(getSpielbrett().getStandardFelder()[ZielfeldID].getFigur() != null && !((getSpielbrett().getStandardFelder()[ZielfeldID].getFigur().getFarbe()).equals(istAmZug.getFarbe()))){
						//System.out.println("Fall: Gespawnt + 6 gewürfelt + 2terFall");
						schlagen(figur, ZielfeldID);
						//System.out.println("Fall: Gespawnt + 6 gewürfelt + 2terFall :: ENDE");					
					}else if(getSpielbrett().getStandardFelder()[ZielfeldID].getFigur()==null){	
						//System.out.println("Fall: Gespawnt + 6 gewürfelt + 3terFall");
						figur.setMeinFeld(getSpielbrett().getStandardFelder()[ZielfeldID]);
						getSpielbrett().getStandardFelder()[aktFeldID].setFigur(null);
						figur.incSchritteGelaufen(getAugenzahl());
						//System.out.println("Fall: Gespawnt + 6 gewürfelt + 3terFall :: ENDE");
						}
					
			}else if(figur.getIstGespawnt()==true&&getAugenzahl()!=6){
						//System.out.println("3ter");
						int EndfeldSchritte = ermittleEndfeldSchritte(figur);
						if(figur.getIstImZiel()==true){
							//System.out.println("Fall: Gespawnt + !6 gewürfelt + 1terFall");
							ziehenInEndfelder(figur);
							nächsterSpieler();
							//System.out.println("Naechster spieler gesetzt");
							//System.out.println("Fall: Gespawnt + !6 gewürfelt + 1terFall :: ENDE");
						}else if(figur.getKannInsZiel()==true){
								//System.out.println("Fall: Gespawnt + !6 gewürfelt + 2terFall");
								ziehenAufEndfelder(figur, EndfeldSchritte);
								figur.setFelderGelaufen(getSpielbrett().getAlleStandardFelder().length-figur.getFelderGelaufen());
								figur.setKannInsZiel(false);
								figur.setIstImZiel(true);
								nächsterSpieler();
								//System.out.println("Naechster spieler gesetzt");
								//System.out.println("Fall: Gespawnt + !6 gewürfelt + 2terFall :: ENDE");
						}else if( getSpielbrett().getStandardFelder()[ermittleZielfeldID(figur)].getFigur() != null &&(!(getSpielbrett().getStandardFelder()[ermittleZielfeldID(figur)].getFigur().getFarbe().equals(istAmZug.getFarbe())))){
								//System.out.println("Fall: Gespawnt + !6 gewürfelt + 3terFall");
								int ZielfeldID = ermittleZielfeldID(figur);
								aktFeld =(Standardfeld) figur.getMeinFeld();
								schlagen(figur, ZielfeldID);
								nächsterSpieler();
								//System.out.println("Naechster spieler gesetzt");
								//System.out.println("Fall: Gespawnt + !6 gewürfelt + 3terFall :: ENDE");
							}
						else if(getSpielbrett().getStandardFelder()[ermittleZielfeldID(figur)].getFigur()==null){
								aktFeld =(Standardfeld) figur.getMeinFeld();
								int aktFeldID = Integer.parseInt(aktFeld.getID())-1;
								//System.out.println("Fall: Gespawnt + !6 gewürfelt + 4terFall");
								figur.setMeinFeld(getSpielbrett().getStandardFelder()[aktFeldID+getAugenzahl()]);
								getSpielbrett().getStandardFelder()[aktFeldID].setFigur(null);
								figur.incSchritteGelaufen(getAugenzahl());
								nächsterSpieler();
								//System.out.println("Naechster spieler gesetzt");
								//System.out.println("Fall: Gespawnt + !6 gewürfelt + 4terFall :: ENDE");
				}
				
			}//IstDasHierNötig?EsIst23.27UhrMeinGehirnIstDezentZerf****UndIchWeißEsNicht -> throw new BrainException("Ich bin zu dumm für RTL! #BöhmiIstDerÖhmi");
			//Unnötig?
			//System.out.println("Figur " + figur.getFelderGelaufen() + " " +  figur.getMeinFeld()  + " " + figur.getIstGespawnt() +"\n" +"\n" + spielbrett.getAlleStandardFelder()[5].getFigur());
			aufEndposition(figur);
		}
		
		/**@author Felix Rosa
		 *  Methode die mit einer übergebenen Figur eine Figur die auf dem Feld mit der ZielfeldID steht schlägt.
		 *  Die geschlagene Figur wird auf ihr Startfeld zurückgesetzt, die schlagende Figur zieht auf das Zielfeld
		 * @param figur - Figur die schlagen soll
		 * @param ZielfeldID - durch gegenerische Figur belegtes Feld, auf die die übergebene Figur laufen soll
		 */
		private void schlagen(Spielfigur figur, int ZielfeldID){
			Spielfigur zuSchlagen = null;
			Standardfeld aktFeld =(Standardfeld) figur.getMeinFeld();
			int aktFeldID = Integer.parseInt(aktFeld.getID())-1;
			zuSchlagen = getSpielbrett().getStandardFelder()[ZielfeldID].getFigur();
			figur.setMeinFeld(getSpielbrett().getStandardFelder()[ZielfeldID]);
			getSpielbrett().getStandardFelder()[aktFeldID].setFigur(null);
			figur.incSchritteGelaufen(getAugenzahl());
			if(zuSchlagen.getFarbe().equals(FarbEnum.GRÜN)){
				for(int i = 0; i< getSpielbrett().getAlleStartFelderGrün().length; i++){
					if(getSpielbrett().getAlleStartFelderGrün()[i].getFigur()==null){
						zuSchlagen.setIstGespawnt(false);
						zuSchlagen.resetFelderGelaufen();
						zuSchlagen.setMeinFeld(getSpielbrett().getAlleStartFelderGrün()[i]);
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.ROT)){
				for(int i = 0; i< getSpielbrett().getAlleStartFelderRot().length; i++){
					if(getSpielbrett().getAlleStartFelderRot()[i].getFigur()==null){
						//System.out.println("Bin ich hier?");
						zuSchlagen.setIstGespawnt(false);
						zuSchlagen.resetFelderGelaufen();
						zuSchlagen.setMeinFeld(getSpielbrett().getAlleStartFelderRot()[i]);
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.BLAU)){
				for(int i = 0; i< getSpielbrett().getAlleStartFelderBlau().length; i++){
					if(getSpielbrett().getAlleStartFelderBlau()[i].getFigur()==null){
						zuSchlagen.setIstGespawnt(false);
						zuSchlagen.resetFelderGelaufen();
						zuSchlagen.setMeinFeld(getSpielbrett().getAlleStartFelderBlau()[i]);
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.GELB)){
				for(int i = 0; i< getSpielbrett().getAlleStartFelderGelb().length; i++){
					if(getSpielbrett().getAlleStartFelderGelb()[i].getFigur()==null){
						zuSchlagen.setIstGespawnt(false);
						zuSchlagen.resetFelderGelaufen();
						zuSchlagen.setMeinFeld(getSpielbrett().getAlleStartFelderGelb()[i]);
						break;
					}
				}
			}
		}
		
		/**@author Felix Rosa
		 * Methode mit der eine spawnende Figur eine gegenerische Figur die auf dem Spawnfeld steht schlägt.
		 * Die geschlagene Figur wird auf ihr Startfeld zurückgesetzt
		 * @param figur - Figur die schlagen soll
		 * @param aktFeldIDS - ID des Feldes auf dem spawnende Figur steht
		 */
		private void schlagenSpawn(Spielfigur figur, String aktFeldIDS){
			Spieler istAmZug = this.getIstAmZug();
			Spielfigur zuSchlagen = null;
			zuSchlagen = getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).getFigur();
			getSpielbrett().getSpawnfeld(istAmZug.getFarbe()).setFigur(null);
			figur.setMeinFeld(getSpielbrett().getSpawnfeld(istAmZug.getFarbe()));
			//System.out.println(figur.getMeinFeld());
			figur.setFelderGelaufen(1);
			figur.setIstGespawnt(true);
			for(int i = 0; i<getSpielbrett().getAlleEndFelderEinerFarbe(istAmZug.getFarbe()).length;i++){
				if(getSpielbrett().getAlleStartFelderEinerFarbe(istAmZug.getFarbe())[i].getID().equals(aktFeldIDS)){
					getSpielbrett().getAlleStartFelderEinerFarbe(istAmZug.getFarbe())[i].setFigur(null);
				}
			}
			if(zuSchlagen.getFarbe().equals(FarbEnum.GRÜN)){
				for(int i = 0; i< getSpielbrett().getAlleStartFelderGrün().length; i++){
					if(getSpielbrett().getAlleStartFelderGrün()[i].getFigur()==null){
						zuSchlagen.setIstGespawnt(false);
						zuSchlagen.resetFelderGelaufen();
						zuSchlagen.setMeinFeld(getSpielbrett().getAlleStartFelderGrün()[i]);
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.ROT)){
				for(int i = 0; i< getSpielbrett().getAlleStartFelderRot().length; i++){
					if(getSpielbrett().getAlleStartFelderRot()[i].getFigur()==null){
						//System.out.println("Bin ich hier?");
						zuSchlagen.setIstGespawnt(false);
						zuSchlagen.resetFelderGelaufen();
						zuSchlagen.setMeinFeld(getSpielbrett().getAlleStartFelderRot()[i]);
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.BLAU)){
				for(int i = 0; i< getSpielbrett().getAlleStartFelderBlau().length; i++){
					zuSchlagen.setMeinFeld(getSpielbrett().getAlleStartFelderBlau()[i]);
					if(getSpielbrett().getAlleStartFelderBlau()[i].getFigur()==null){
						zuSchlagen.setIstGespawnt(false);
						zuSchlagen.resetFelderGelaufen();
						zuSchlagen.setMeinFeld(getSpielbrett().getAlleStartFelderBlau()[i]);
						break;
					}
				}
			}else if(zuSchlagen.getFarbe().equals(FarbEnum.GELB)){
				for(int i = 0; i< getSpielbrett().getAlleStartFelderGelb().length; i++){
					if(getSpielbrett().getAlleStartFelderGelb()[i].getFigur()==null){
						zuSchlagen.setIstGespawnt(false);
						zuSchlagen.resetFelderGelaufen();
						zuSchlagen.setMeinFeld(getSpielbrett().getAlleStartFelderGelb()[i]);
						break;
					}
				}
			}
			
		}
		
		
	private void ziehenInEndfelder(Spielfigur figur){
		Endfeld aktFeld = (Endfeld) figur.getMeinFeld();
		int position = 0;
		for(int i = 0; i < getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe()).length;i++){
			if(getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe())[i].getID().equals(aktFeld.getID())){
				position = i;
				break;
			}
		}
		figur.setMeinFeld(getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe())[position + getAugenzahl()]);
		String aktFeldIDS = aktFeld.getID();
			for(int i = 0; i<getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe()).length;i++){
				if(getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe())[i].getID().equals(aktFeldIDS)){
					getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe())[i].setFigur(null);
				}
			}
		
	}
	/**@author Anna Rosa, Felix Rosa
	 * Methode, die eine Figur um eine bestimmte Anzahl an Zügen in seinem
	 * Endfeld ziehen lässt.
	 * @param figur - Die Figur, die ziehen soll
	 * @param augenZahl - Anzahl der Züge, die Figur ziehen soll
	 */
	private void ziehenAufEndfelder(Spielfigur figur, int restSchritte) {
		Spielfeld aktFeld = figur.getMeinFeld();
		String aktFeldIDS = "";
		int aktFeldID = 0;
		if (kannZiehenEndfelder(figur, restSchritte) != true) {
			System.out.println("Figur kann nicht ziehen!");
			throw new FigurKannNichtZiehenException("Figur kann nicht ziehen!");
		}
		if(aktFeld instanceof Endfeld){
				aktFeldIDS = figur.getMeinFeld().getID();
		}else if(aktFeld instanceof Standardfeld){
				aktFeldID = Integer.parseInt(figur.getMeinFeld().getID());
		}
		figur.setMeinFeld(getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe())[restSchritte-1]);
		if(aktFeld instanceof Endfeld){
			for(int i = 0; i<getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe()).length;i++){
				if(getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe())[i].getID().equals(aktFeldIDS)){
					getSpielbrett().getAlleEndFelderEinerFarbe(getIstAmZug().getFarbe())[i].setFigur(null);
				}
			}
		}else if(aktFeld instanceof Standardfeld){
			getSpielbrett().getAlleStandardFelder()[aktFeldID-1].setFigur(null);
		}
		//aufEndposition(figur);
		//System.out.println("Hollaho.--------------------Ich bin in der ziehenEndfelder");
	}
	/**@author Anna Rosa, Felix Rosa
	 * Methode, die überprüft, ob eine Figur in ihrer endgültigen Endposition ist und wenn dies der Fall 
	 * das Attribut binIchAufEndposition auf true setzt.
	 * @param figur - zu Überprüfende Figur
	 */
	private void aufEndposition(Spielfigur figur){
		if(! (figur.getMeinFeld() instanceof Endfeld))
			return;
		else{
			FarbEnum farbe= figur.getFarbe();
			int index=0;
			for(int i=0; i<getSpielbrett().getAlleEndFelderEinerFarbe(farbe).length; i++){
				if(figur.equals(getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[i].getFigur())){
					index=i;
					break;
				}
			}
			switch(index){
				case 0:
					if(getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[1]!=null& getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[2]!=null & getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[3]!=null)
						figur.setBinIchAufEndposition(true);
						break;
				case 1: 
					if(getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[2].getFigur()!=null && getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[3].getFigur()!=null){
						figur.setBinIchAufEndposition(true);}	
						break;
				case 2:
					if(getSpielbrett().getAlleEndFelderEinerFarbe(farbe)[3]!=null)
						figur.setBinIchAufEndposition(true);
					break;
				case 3: 
					figur.setBinIchAufEndposition(true);
				default: 
					figur.setBinIchAufEndposition(false);
			}
		}
	}
	/**
	 * Methode zu DebugZwecken. Ermöglicht es, zu Testzwecken auf Spieler
	 * zuzugreifen, die nicht am Zug sind.
	 * 
	 * @param spielerzahl - Bestimmter Spielerindex vom Typ int
	 * @return spieler - Ein OPbjekt vom Typ Spieler.
	 */
	public Spieler DebugGetSpieler(int spielerzahl) {
		return spieler[spielerzahl - 1];
	}
	/**
	 * Methode zu DebugZwecken. Ermöglicht es, eine Figur mit einem Zug vor die
	 * Endfelder zu setzen.
	 * 
	 * @param augenzahl - int Wert
	 */
	public void DebugSetAugenzahl(int augenzahl) {
		// Unterschied zur Originalmethode
		if (augenzahl > 40 || augenzahl < 1)
			throw new RuntimeException("Fehlerhaftes Würfelergebnis");
		this.augenzahl = augenzahl;
	}
	/**
	 * Methode zum Auswählen der Figur, die ziehen soll über eine von außen
	 * (Spieler/ KI) übergebene Spielfeld-ID
	 * 
	 * @param id - ID des Spielfeldes, auf dem sich die Figur vor Ausführen des Zuges befindet
	 * @return figur - gibt die Figur, die ziehen soll zurück, falls diese ziehen kann
	 * @author Kevin Schroetter(Update v2.2), Anna Rosa
	 * @since version 2.2
	 */
	public void wähleFigur(String id) {
		if(getZugMöglich()==false)throw new FigurKannNichtZiehenException("Zug nicht möglich!");
		FarbEnum farbeIstAmZug = getIstAmZug().getFarbe();
		Spielfeld f = getSpielbrett().getFeld(id, farbeIstAmZug);
		Spielfigur figur = f.getFigur();
		if (!(alleZugFiguren().contains(figur)))
			throw new FigurKannNichtZiehenException(String.format("Figur " + figur.getID()+" %s kann nicht ziehen!", figur.getFarbe()));
		else{
			getIstAmZug().setZugFigur(figur);
			ziehen(figur,getAugenzahl());
		}
	}
	/**
	 * Dies Methoede gibt zurueck, ob eine Figur die Moeglichkeit hat zu spawnen oder nicht
	 * @param figur - Spielfigur bei der gecheckt werden soll, ob sie spawnen kann
	 * @return boolean - true wenn die Figur spawnen kann, sonst false
	 * @author Kevin Schroetter
	 * @since version 2.1
	 */
	private boolean kannSpawnen(Spielfigur figur) {
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
	/**
	 * Diese Methode wuerfelt fuer den Spieler ein gewolltes Ergebnis
	 * Description: Siehe würfelnOriginal()
	 * @author Kevin Schroetter
	 * @since version 2.2
	 * @param hack - int, der aktuell noch dazu verwendet wird, um konkrete Wuerfelergebnisse fuer Tests zu erarbeiten. Dies wird im spaeteren Verlauf herausgenommen.
	 */
	public void würfeln(int hack) {
		if(getAnzWürfe()==3 && getAlleAufSpawn()==true && getAugenzahl()!=6){
			nächsterSpieler();
		}
		if((alleZugFiguren().size()!=0) ||(getAlleAufSpawn()==false && getAnzWürfe() >= 1 && getAugenzahl()!=6))throw new RuntimeException(getIstAmZug().getName()+", Sie müssen erst einen Zug ausführen, bevor nochmals gewürfelt werden kann!");
		if (getHatBegonnen() == false)
			throw new RuntimeException("Spiel hat noch nicht begonnen");
		
		int spawncounter = 0;
		incAnzWürfe();
		int augenzahl = getIstAmZug().getMeinWürfel().testWurf(hack);
		setAugenzahl(augenzahl);
		
		for (Spielfigur sf : getIstAmZug().alleFiguren()){
			if (kannIchZiehen(sf) == true) {
				sf.setKannZiehen(true);
				setZugFiguren(sf);
			}
			if(sf.binIchGespawnt()==false)
				spawncounter++;
		}
		if (spawncounter == getIstAmZug().getAnzFiguren())
			setAlleAufSpawn(true);
		else
			setAlleAufSpawn(false);
		if(getAlleAufSpawn()==false && getAugenzahl()==6 && getAnzWürfe()<1) throw new RuntimeException(getIstAmZug().getName()+", Sie müssen erst einen Zug ausführen, bevor nochmals gewürfelt werden kann!");
	
		if(alleZugFiguren().isEmpty()==true && getAlleAufSpawn()==true && getAnzWürfe() > 3 && getAugenzahl()!= 6){
			setZugMöglich(false);
			nächsterSpieler();
		} 
		else if (alleZugFiguren().isEmpty()==true && getAlleAufSpawn()==true && getAnzWürfe()<=3 && getAugenzahl()!=6) {
			setZugMöglich(false);
		}
		else if(getAugenzahl()==6){
			setZugMöglich(true);
		}
		else{
			setZugMöglich(true);
			setAlleAufSpawn(false);
		};
			//DEBUG SYSOS
			//System.out.println("ZUGFIGUREN: "+alleZugFiguren().size());
			//System.out.println("Anzahl würfe: "+getAnzWürfe());
			//System.out.println("Augenzahl: "+getAugenzahl());
			//System.out.println("Am Zug: "+getIstAmZug());
			//System.out.println("Zug Möglich: "+getZugMöglich());
			//System.out.println("Alle auf Spawn: "+getAlleAufSpawn());
			//System.out.println("");
	}
	/**
	 * Diese Methode wuerfelt fuer den Spieler
	 * Sie gibt stets die Augenzahl mit und hat ein paar Faelle zum Unterscheiden
	 * 1. Wenn sich alle Figuren auf den Startefeldern befinden, darf ein spieler drei mal wuerfeln
	 * 2. Wenn sich alle Figuren auf den Startfeldern befindet und bereits dreimal gewuerfelt wurde, wird ein naechster Spieler ausgewaehlt
	 * 3. Wenn sich nicht alle Figuren auf den Startfeldern befinden, darf ein Spieler nur dann ein weiteres mal wuerfeln, wenn er eine 6 gewuerfelt hat
	 * 4. Wenn ein Spieler mehrmals hintereinander wuerfeln will, ohne dass er vorher einen Zug ausgefuerht hat, so ist dies nicht moeglich
	 * 
	 * Die Methode nutzt kannIchZiehen(Spielfigur figur), um zu ermitteln, ob eine Figur ziehen kann. Wenn dies der Fall ist, 
	 * so wird die Figur in die ArrayList alleZugFiguren geschrieben, die spaeter in der Methode waehleFigur() verwendet wird.
	 * Sollte sich keine Figur in der ArrayList befinden, wird geprueft, ob sich die Figuren auf den Startfeldern befinden. Ja? Dann darf der Spieler drei mal wuerfeln.
	 * Sollte sich keine Figur in der ArrayList befinden, und es befinden sich NICHT ALLE Figuren auf den Startfeldern, so ist der naechster Spieler mit seinem Zug dran.
	 * @author Kevin Schroetter
	 * @since version 2.2
	 */
	public void würfelnOriginal() {
		if(getAnzWürfe()==3 && getAlleAufSpawn()==true && getAugenzahl()!=6){
			nächsterSpieler();
		}
		if((alleZugFiguren().size()!=0) ||(getAlleAufSpawn()==false && getAnzWürfe() >= 1 && getAugenzahl()!=6))throw new RuntimeException(getIstAmZug().getName()+", Sie müssen erst einen Zug ausführen, bevor nochmals gewürfelt werden kann!");
		if (getHatBegonnen() == false)
			throw new RuntimeException("Spiel hat noch nicht begonnen");
		
		int spawncounter = 0;
		incAnzWürfe();
		int augenzahl = getIstAmZug().getMeinWürfel().werfen();
		setAugenzahl(augenzahl);
		
		for (Spielfigur sf : getIstAmZug().alleFiguren()){
			if (kannIchZiehen(sf) == true) {
				sf.setKannZiehen(true);
				setZugFiguren(sf);
			}
			if(sf.binIchGespawnt()==false)
				spawncounter++;
		}
		if (spawncounter == getIstAmZug().getAnzFiguren())
			setAlleAufSpawn(true);
		else
			setAlleAufSpawn(false);
		if(getAlleAufSpawn()==false && getAugenzahl()==6 && getAnzWürfe()<1) throw new RuntimeException(getIstAmZug().getName()+", Sie müssen erst einen Zug ausführen, bevor nochmals gewürfelt werden kann!");
	
		if(alleZugFiguren().isEmpty()==true && getAlleAufSpawn()==true && getAnzWürfe() > 3 && getAugenzahl()!= 6){
			setZugMöglich(false);
			nächsterSpieler();
		} 
		else if (alleZugFiguren().isEmpty()==true && getAlleAufSpawn()==true && getAnzWürfe()<=3 && getAugenzahl()!=6) {
			setZugMöglich(false);
		}
		else if(getAugenzahl()==6){
			setZugMöglich(true);
		}
		else{
			setZugMöglich(true);
			setAlleAufSpawn(false);
		};
			//DEBUG SYSOS
			//System.out.println("ZUGFIGUREN: "+alleZugFiguren().size());
			//System.out.println("Anzahl würfe: "+getAnzWürfe());
			//System.out.println("Augenzahl: "+getAugenzahl());
			//System.out.println("Am Zug: "+getIstAmZug());
			//System.out.println("Zug Möglich: "+getZugMöglich());
			//System.out.println("Alle auf Spawn: "+getAlleAufSpawn());
			//System.out.println("");
	}
	/**
	 * Methode, die den nächsten Spieler als am Zug seienden Spieler setzt und dem vorherigen 
	 * die Eigenschaft, dass er am Zug ist, auf false setzt.
	 * @author Kevin Schroetter(Update v2.2), Anna Rosa, Felix Rosa
	 * @since version 2.2
	 */
	private void nächsterSpieler() {
		alleZugFiguren().clear();
		deleteAnzZugFiguren();
		deleteAnzWürfe();
		Spieler tempSpieler=getIstAmZug();
		
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
	}
	/**
	 * Methode ausgabeFiguren - gibt ArrayList aller Figuren zurück und gibt diese auf der Systemkonsole aus
	 * @return figurenListe - ArrayList<Spielfigur>
	 * 
	 * 
	 */

	// Interface - ausgabeFiguren: Gibt alle Figuren aller Spieler aus
	public ArrayList<Spielfigur> ausgabeFiguren() {

		
		Spieler s = this.getIstAmZug();
		ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>(Arrays.asList(s.alleFiguren()));
		for (Spielfigur f : s.alleFiguren()) {
			System.out.println(f.getName()+" auf Feld "+f.getMeinFeld().getID());
		}
		return figurenListe;
	}
	/**
	 * Methode ausgabeZugFiguren - gibt ArrayList aller Figuren zurück die ziehen können und gibt diese auf der Systemkonsole aus
	 * @return figurenListe - ArrayList<Spielfigur>
	 * 
	 * 
	 */
	public ArrayList<Spielfigur> ausgabeZugFiguren() {

		Spieler s = this.getIstAmZug();
		ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>(4);
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getKannZiehen()) {
				figurenListe.add(f);
				System.out.println(f.getName()+" auf Feld "+f.getMeinFeld().getID());
			}
		}
		return figurenListe;
	}
	/**
	 * Methode ausgabeZugFiguren - gibt ArrayList aller Figuren zurück die im Ziel sind und gibt diese auf der Systemkonsole aus
	 * @return figurenListe - ArrayList<Spielfigur>
	 * 
	 * 
	 */
	public ArrayList<Spielfigur> ausgabeFigurenImZiel() {

		Spieler s = this.getIstAmZug();
		ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>(4);
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getIstImZiel()) {
				figurenListe.add(f);
				System.out.println(f.getName()+" auf Feld "+f.getMeinFeld().getID());
			}
		}
		return figurenListe;
	}
	/**
	 * Methode ausgabeSpielerListe - Gibt alle Spieler auf der System Konsole aus.
	 * 
	 * 
	 */
	public void ausgabeSpielerListe() {
		for (Spieler s : spieler) {
			if (s == null)
				continue;
			else {
				System.out.println(s.getName()+" mit Farbe: "+s.getFarbe());
			}
		}
	}
	/**
	 * Methode zugDurchführen
	 * @return zugErfolgreich - boolean
	 * @param ID String
	 * 
	 * Versucht einen Zug mit der gewählten Figur durchzuführen. Fängt im Falle dessen, dass 
	 * die gewählte Figur nicht ziehen kann, die Exception ab und gibt false zurück.
	 * War der Zug erfolgreich, gibt true zurück.
	 * 
	 * 
	 */
	public boolean zugDurchführen(String ID) {

		boolean zugErfolgreich;
		try {
			
			
			
			FarbEnum farbeIstAmZug = istAmZug.getFarbe();
			
			Spielfeld f = getSpielbrett().getFeld(ID, farbeIstAmZug);
			Spielfigur figur = f.getFigur();
			wähleFigur(ID);
			Spielfeld zielFeld = figur.getMeinFeld();
			zugErfolgreich = true;
			System.out.println("Zug erfolgreich!");
			
			Spielfeld f2 = getSpielbrett().getFeld(ID, farbeIstAmZug);
			System.out.println(figur.getName()+" zieht von Feld "+f.getID()+" auf Feld "+zielFeld.getID()+"\n\n");
			return zugErfolgreich;
		}

		catch (FigurKannNichtZiehenException e) {
			zugErfolgreich = false;
			System.out.println("Zug fehlgeschlagen!");
			return zugErfolgreich;
		}
		catch (NullPointerException e){
			zugErfolgreich = false;
			System.out.println("Zug fehlgeschlafen!");
			return zugErfolgreich;
		}

	}
	/**
	 * Methode ausgabeZugFiguren - gibt ArrayList aller Figuren zurück die auf einem Startfeld sind und gibt diese auf der Systemkonsole aus
	 * @return figurenListe - ArrayList<Spielfigur>
	 * 
	 * 
	 */
	public ArrayList<Spielfigur> ausgabeFigurenAufStartfeld() {
		Spieler s = this.getIstAmZug();
		ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>(4);
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getMeinFeld() instanceof Startfeld) {
				System.out.println(f.getName()+" auf Feld "+f.getMeinFeld().getID());
				figurenListe.add(f);
			}
		}
		return figurenListe;
	}
	/** Roll the Dice - Rollt den Würfel
	 * @return s.getMeinWürfel().werfen() - int
	 *
	 */
	public int rollTheDice(){
		try{ 
			
			würfelnOriginal();
			System.out.println("Spieler "+getIstAmZug().getName()+" ist am Zug!");
			System.out.println("Spieler "+getIstAmZug().getName() +" "+getIstAmZug().getFarbe()+" hat eine "+getAugenzahl()+" gewuerfelt und darf mit folgenden Figuren ziehen:\n#########################");
			if(alleZugFiguren().size()==0&&getAlleAufSpawn()==false){
				System.out.println("Keine Figur darf ziehen! Neu wuerfeln!\n");
				nächsterSpieler();
			}
			else if(alleZugFiguren().size()==0){
				System.out.println("Keine Figur darf ziehen! Neu wuerfeln!\n");
				//nächsterSpieler();
			}
			else{
				System.out.println("");
				for(Spielfigur sf:alleZugFiguren()){
					System.out.print(sf.getName()+" auf Feld: "+sf.getMeinFeld().getID());
					if (sf.getIstGespawnt()==false && sf.getKannSchlagen()==true)
						System.out.println(" Kann Spawnen & Killen");
					else if(sf.getIstGespawnt()==true && sf.getKannSchlagen()==true)
						System.out.println(" Kann Killen");
					else if(sf.getIstGespawnt()==false && sf.getKannSchlagen()==false)
						System.out.println(" Kann Spawnen");
					else if(sf.getKannInsZiel()==true)
						System.out.println(" Kann ins Ziel");
					else 
						System.out.println(" Kann laufen");
				}
			}
			System.out.println("---------------------------------------\n");
		}
		catch(RuntimeException e){
			System.out.println(e);
		}
		return getAugenzahl();
	}
	/**
	 * Zusaetzliche Methode fuer das Interface, damit dadurch konkrete Wuerfelzahlen verwendet werden koennen.
	 * Nutzung fuer Testzwecke
	 * @author Kevin Schroetter
	 * @since version 2.2
	 * @param zahl - int, die gewuenschte Augenzahl
	 */
	@Override
	public void werfen(int zahl) {
		try{ 
			
			würfeln(zahl);
			System.out.println("Spieler "+getIstAmZug().getName()+" ist am Zug!");
			System.out.println("Spieler "+getIstAmZug().getName() +" "+getIstAmZug().getFarbe()+" hat eine "+getAugenzahl()+" gewuerfelt und darf mit folgenden Figuren ziehen:\n#########################");
			if(alleZugFiguren().size()==0&&getAlleAufSpawn()==false){
				System.out.println("Keine Figur darf ziehen! Neu wuerfeln!\n");
				nächsterSpieler();
			}
			else if(alleZugFiguren().size()==0){
				System.out.println("Keine Figur darf ziehen! Neu wuerfeln!\n");
			}
			else{
				System.out.println("");
				for(Spielfigur sf:alleZugFiguren()){
					System.out.print(sf.getName()+" auf Feld: "+sf.getMeinFeld().getID());
					if (sf.getIstGespawnt()==false && sf.getKannSchlagen()==true)
						System.out.println(" Kann Spawnen & Killen");
					else if(sf.getIstGespawnt()==true && sf.getKannSchlagen()==true)
						System.out.println(" Kann Killen");
					else if(sf.getIstGespawnt()==false && sf.getKannSchlagen()==false)
						System.out.println(" Kann Spawnen");
					else if(sf.getKannInsZiel()==true)
						System.out.println(" Kann ins Ziel");
					else 
						System.out.println(" Kann laufen");
				}
			}
			System.out.println("---------------------------------------\n");
		}
		catch(RuntimeException e){
			System.out.println(e);
		}
	}
	/**
	 * Interface Methode zum Hinzufuegen eines neuen Spielers
	 * @author Kevin Schroetter
	 * @since version 2.2
	 * @param name - String name
	 * @param farbID - int zum Auswaehlen der Farbe
	 * @verhalten - int zum Auswaehlen des Verhaltens
	 */
	@Override
	public void neuerSpieler(String name, int farbID, int verhaltenID) {
		try{

			spielerHinzufügen(name,farbID,verhaltenID);
			System.out.println("Spieler "+getAnzahlSpieler()+": "+spieler[getAnzahlSpieler()-1].getName()+" "+spieler[getAnzahlSpieler()-1].getFarbe()+" wurde Hinzugefuegt!\n");
			if(getAnzahlSpieler()==4){
				setHatBegonnen(false);
				starteSpiel();
			}
		}
		catch(RuntimeException e){
			System.out.println(e);
		}
		
	}
	/**
	 * Methode, die alle Spieleigenschaften zurücksetzt.
	 * Sie wird aktuell nur im JUnit-Test verwendet.
	 * @author Kevin Schroetter
	 * @since version 2.2
	 */
	public void neuesSpiel(){
		if(getAnzahlSpieler()==0)throw new RuntimeException("Es kann kein neues Spiel erstellt werden!");
		deleteSpieler();
		for(int i =0;i<4;i++){
			spieler[i] = null;
		}
		deleteAnzahlSpieler();
		setHatBegonnen(false);
		setIstBeendet(false);
		alleZugFiguren().clear();
		deleteAnzWürfe();
		deleteAnzZugFiguren();
	}
	/**
	 * HilfsMethode zum loeschen aller der static Attribute Spielernummer in Spieler und AzahlFiguren in Spielfigur
	 * @author Kevin Schroetter
	 * @since version 2.2	
	 */
	private void deleteSpieler(){
		if(spieler[0]==null)throw new RuntimeException("Fehler beim Loeschen der Spieler");
		spieler[0].deleteSpielernummer();
		spieler[0].getFiguren(1).deleteAnzahlFiguren();
	}
	/**
	 * HilfsMethode, die die anzahl der Spieler auf 0 zurücksetzt.
	 * @author Kevin Schroetter
	 * @since version 2.2
	 */
	private void deleteAnzahlSpieler(){
		this.anzahlSpieler=0;
	}
	/**
	 * HilfsMethode zum Loeschen der anzWürfe.
	 * @author Kevin Schroetter
	 * @since version 2.2
	 */
	private void deleteAnzWürfe(){
		this.anzWürfe=0;
	}

	@Override
	public void starteSpiel() {
		try{
			if(getHatBegonnen()==false){
				startSpiel();
				System.out.println("\n++++++++++Spiel gestartet!++++++++++\n\nSpieler "+getIstAmZug().getName()+" bitte wuerfeln!\n\n");
			}
		}
		catch(RuntimeException e){
			System.out.println(e);
		}
	}
}
