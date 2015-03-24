package Spiel;

import java.util.ArrayList;

import Basisklassen.*;

/**
 * Klasse Spiel als Regelwerk für MADN.
 * Sie beinhaltet alle notwendigen Methoden, um einen reibungslosen Spielfluss zu gewährleisten.
 * In dieser Klasse sind DebugMethoden implementiert, sie sind ganz am Endeder Datei zu finden.
 * @author Kevin Schrötter, Felix Rosa, Anna Rosa, Alexander Brückner
 * @version 1.2
 *
 */

public class Spiel {
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
	private Spielbrett spielbrett;
	/**
	 * Anzahl der Spieler, die gerade an einem Spiel teilnehmen
	 */
	private int anzahlSpieler;
	/**
	 * Zeigt an, ob ein Spiel bereits begonnen hat, falls TRUE
	 */
	private boolean hatBegonnen=false;
	/**
	 * Zeigt an, ob ein Spiel bereits beendet ist, falls TRUE
	 */
	private boolean istBeendet=false;
	/**
	 * Speichert die Anzahl an Würfel-Würfen, die ein Spieler während eines Zuges ausgeführt hat.
	 * Relevant für den Fall, dass mehrmals hintereinander eine 6 gewürfelt wird.
	 */
	private int anzWürfe = 0;
	/**
	 * Speichert die zuletzt gewürfelte Augenzahl während eines Zuges
	 */
	private int augenzahl = 0;
	/**
	 * ArrayList, in der die Figuren gespeichert werden, mit denen ein Zug ausgeführt werden kann
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
	
	/**
	 * Konstruktor für ein Spiel. Er schreibt ein Spielbrett direkt in das Attribut spielbrett.
	 */
	public Spiel() {
		spielbrett = new Spielbrett();
	}
	/**
	 * Setter für IstAmZug, der den Spieler in das Attribut schreiben soll, der gerade am Zug ist.
	 * Es kann nur ein Objekt der Klasse Spieler gespeichert werden.
	 * @param sIAZ - Übergebener Spieler, der gespeichert werden soll.
	 */
	public void setIstAmZug(Spieler sIAZ){
		if(!(sIAZ instanceof Spieler)) throw new RuntimeException("Es kann nur ein Spielerobjekt am Zug sein!");
		this.istAmZug = sIAZ;
	}
	/**
	 * Getter, der den Spieler zurück gibt, der gerade am Zug ist.
	 * @return istAmZug - Ein Objekt der Klasse Spieler.
	 */
	public Spieler getIstAmZug(){
		return this.istAmZug;
	}
	/**
	 * Setter für zugMöglich
	 * @param zugMöglich - true oder false boolean
	 */
	public void setZugMöglich(boolean zugMöglich){
		this.zugMöglich = zugMöglich;
	}
	/**
	 * Getter für zugMöglich
	 * @return zugMöglich - true oder false boolean
	 */
	public boolean getZugMöglich(){
		return this.zugMöglich;
	}
	/**
	 * Setter für das Spielbrett
	 * Es wird nur gesetzt, wenn es sich beim Übergabeparameter um ein Objekt der Klasse Spielbrett handelt.
	 * @param spielbrett - Objekt der Klasse Spielbrett.
	 */
	public void setSpielbrett(Spielbrett spielbrett){
		if(!(spielbrett instanceof Spielbrett)) throw new RuntimeException("Kein Spielfeld gesetzt");
		this.spielbrett = spielbrett;
	}
	/**
	 * Getter für das Spielbrett.
	 * @return spielbrett - Objekt der Klasse Spielbrett.
	 */
	public Spielbrett getSpielbrett(){
		return this.spielbrett;
	}
	/**
	 * Setter für die Anzahl der teilnehmenden Spieler, der nur inkrementiert.
	 */
	public void incAnzahlSpieler(){
		this.anzahlSpieler++;
	}
	/**
	 * Getter für anzahlSpieler
	 * @return anzahlSpieler - Anzahl der Spieler vom Typ int
	 */
	public int getAnzahlSpieler(){
		return this.anzahlSpieler;
	}
	/**
	 * Setter für hatBegonnen
	 * @param hatBegonnen - boolean
	 */
	public void setHatBegonnen(boolean hatBegonnen){
		this.hatBegonnen = hatBegonnen;
	}
	/**
	 * Getter für hatBegonnen
	 * @return hatbegonnen - boolean
	 */
	public boolean getHatBegonnen(){
		return this.hatBegonnen;
	}
	/**
	 * Setter für istBeendet
	 * @param istBeendet - boolean
	 */
	public void setIstBeendet(boolean istBeendet){
		this.istBeendet = istBeendet;
	}
	/**
	 * Getter für istBeendet
	 * @return istBeendet - boolean
	 */
	public boolean getIstBeendet(){
		return this.istBeendet;
	}
	/**
	 * Setter für AnzWürfe, der die Variable inkrementiert.
	 */
	public void incAnzWürfe(){
		this.anzWürfe++;
	}
	/**
	 * Getter für anzWürfe
	 * @return anzWürfe - int
	 */
	public int getAnzWÜrfe(){
		return this.anzWürfe;
	}
	/**
	 * Setter für die Augenzahl
	 * HINWEIS: DebugMethode zu Testzwecken als hilfsmethode am Ende der Datei
	 * @param augenzahl - Augenzahl als int
	 */
	public void setAugenzahl(int augenzahl){
		if (augenzahl <1 || augenzahl > 6) throw new RuntimeException("Fehlerhaftes Würfelergebnis");
		this.augenzahl = augenzahl;
	}
	/**
	 * Getter für die Augenzahl
	 * @return augenzahl - int Wert
	 */
	public int getAugenzahl(){
		return this.augenzahl;
	}
	/**
	 * Setter für die Anzahl der vorhandenen zugFiguren, inkrementiert
	 */
	public void incAnzZugFiguren(){
		if(this.anzZugFiguren<0||this.anzZugFiguren>4)throw new RuntimeException("Fehöer: ZugFigurenLimit erreicht!");
		this.anzZugFiguren++;
	}
	/**
	 * Getter für anzTugFiguren
	 * @return anzZugFiguren - int Wert
	 */
	public int getAnzZugFiguren(){
		return this.anzZugFiguren;
	}
	/**
	 * Setter, der Figuren in einer ArrayList speichert, falls sie für einen Zug infrage kommen.
	 * Übergabeparameter muss vom Typ Figur sein
	 * Die Farbe der übergebenen Figur muss mit der Farbe des Spielers übereinstimmen, der gerade am Zug ist.
	 * Es können maximal 4 Figuren in der Lage sein, zu ziehen.
	 * @param figur - Objekt vom Typ Figur
	 */
	public void setZugFiguren(Spielfigur figur){
		if(!(figur instanceof Spielfigur)) throw new RuntimeException("Keine Spielfigur ausgewählt");
		if(!(figur.getFarbe().equals(getIstAmZug().getFarbe()))) throw new RuntimeException("Ungültige Figur ausgewählt!");
		if(getAnzZugFiguren()>=4) throw new RuntimeException("Maximale Anzahl an für einen Zug möglichen Figuren erreicht!");
		this.zugFiguren.add(figur);
		incAnzZugFiguren();
	}
	public Spielfigur getZugFiguren(Spielfigur figur){
		int index;
		if(!(figur instanceof Spielfigur)) throw new RuntimeException("FEHLER: Die angeforderte zugFigur ist keine Spielfigur!");
		if(!(figur.getFarbe().equals(getIstAmZug().getFarbe()))) throw new RuntimeException("Die Angeforderte Figur gehört nicht zum ziehenden Spieler!");
		if(zugFiguren.contains(figur) == true){
			index = zugFiguren.indexOf(figur);
			return zugFiguren.get(index);
		}
		
		else
			throw new RuntimeException("Angeforderte Figur existiert nicht");
	}
	/**
	 * Setter fpr alleAufSpawn
	 * @param alleAufSpawn - boolean
	 */
	public void setAlleAufSpawn(boolean alleAufSpawn){
		this.alleAufSpawn = alleAufSpawn;
	}
	/**
	 * Getter für alleAufSpawn
	 * @return alleAufSpawn - boolean
	 */
	public boolean getAlleAufSpawn(){
		return this.alleAufSpawn;
	}
	/**
	 * Methode zum Hinzufügen eines neuen Spielers, solange das Spiel noch nicht
	 * gestartet ist. Sind Spieler im Spiel, so wird Spiel automatisch begonnen.
	 * 
	 * @param name - gewünschter Name des Spielers
	 * @param farbe - gewünschte Farbe des Spielers
	 * @param verhalten - Falls null: Menschlicher Spieler, sonst: KI mit dem übergebenen Verhalten;
	 */
	public void spielerHinzufügen(String name, FarbEnum farbe, String verhalten) {
		if (hatBegonnen == true)
			throw new RuntimeException("Spiel hat schon begonnen");
		if(farbe==null)
			throw new RuntimeException("Spieler braucht eine Farbe");
		for (int i = 0; i <= 3; i++) {
			if (spieler[i] != null) {
				if (!(farbe.equals(spieler[i])))
					throw new RuntimeException("Farbe schon vorhanden");
			}
		}
		Startfeld[] startfelder = spielbrett
				.getAlleStartFelderEinerFarbe(farbe);
		Endfeld[] endfelder = spielbrett.getAlleEndFelderEinerFarbe(farbe);
		if (verhalten == null) {
			spieler[anzahlSpieler] = new Spieler(name, farbe, startfelder,
					endfelder);
		} else if (verhalten != null) {
			spieler[anzahlSpieler] = new Spieler(name, farbe, startfelder,
					endfelder, verhalten);
		}
		anzahlSpieler++;
		if (anzahlSpieler == 3)
			hatBegonnen = true;

	}

	/**
	 * Methode, die das Spiel startet, so dass keine Spieler mehr hinzugefügt
	 * werden können. Sie setzt den ersten Spieler im Spieler Array als den
	 * Spieler, der am Zug ist.
	 */
	public void startSpiel() {
		hatBegonnen = true;
		spieler[0].setAmZug(true);
		istAmZug = spieler[0];
	}


	public boolean kannIchZiehen(Spielfigur figur, int zuZiehen){
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
		
		else if(figur.getMeinFeld() instanceof Endfeld){
			if(figur.getMeinFeld().equals(endfelderIstAmZug[0])){
				if(zuZiehen>3)
					return false;
				if(zuZiehen==1){
					if(endfelderIstAmZug[1].getFigur()==null)
						return true;
					else 
						return false;
				}
				if(zuZiehen==2){
					if(kannZiehenEndfelder(figur, 1)==true && endfelderIstAmZug[2]==null)
						return true;
					else return false;
				}
				if(zuZiehen==3){
					if(kannZiehenEndfelder(figur, 2)==true&&endfelderIstAmZug[3]==null)
						return true;
					else return false;
				}
			}
			if(figur.getMeinFeld().equals(endfelderIstAmZug[1])){
				if(zuZiehen>2)
					return false;
				if(zuZiehen==1){
					if(endfelderIstAmZug[2].getFigur()==null)
						return true;
					else return false;
				}
				if(zuZiehen==2)
					if(kannZiehenEndfelder(figur, 1)==true&& endfelderIstAmZug[2]==null)
						return true;
			}
			
			if(figur.getMeinFeld().equals(endfelderIstAmZug[2])){
				if(zuZiehen>1)
					return false;
				if(endfelderIstAmZug[3].getFigur()==null)
					return true;
				else
					return false;
			}
			
		}
		return false;
		

	}

	/**
	 * Methode, die prüft, ob eine Figur innerhalb der Endfelder um die gewollte
	 * Anzahl der Züge ziehen kann.
	 * 
	 * @param figur
	 *            - Die Figur, die ziehen soll
	 * @param augenZahl
	 *            - Anzahl der Züge, die Figur ziehen soll
	 * @return
	 */

	
	  private boolean kannZiehenEndfelder(Spielfigur figur, int augenZahl){ 	
		  	if(augenZahl > 4) 
		  		return false; 
		  	Endfeld[] endfelderIstAmZug = istAmZug.getEndFelder(); 
		  		if (augenZahl == 1)
		  			if(endfelderIstAmZug[0].getFigur() == null) 
		  				return true;
		  		if (augenZahl == 2)
		  			if (endfelderIstAmZug[1].getFigur() == null && kannZiehenEndfelder(figur, 1) == true) 
		  				return true; 
		  		if (augenZahl == 3)
		  			if (endfelderIstAmZug[2].getFigur() == null && kannZiehenEndfelder(figur, 2) == true)
		  				return true; 		
		  		if (augenZahl == 4) 
		  			if (endfelderIstAmZug[3].getFigur() == null && kannZiehenEndfelder(figur, 3) == true)
		  				return true; 
		  		else 
		  			return false;
		  		return false;
	  
	  }
	 
	/**
	 * Methode, die eine Figur um eine bestimmte Anzahl an Zügen in seinem
	 * Endfeld ziehen lässt.
	 * 
	 * @param figur - Die Figur, die ziehen soll
	 * @param augenZahl - Anzahl der Züge, die Figur ziehen soll
	 */
	public void ziehenEndfelder(Spielfigur figur, int augenZahl) {
		/**if (kannZiehenEndfelder(figur, augenZahl) != true) {
			throw new RuntimeException("Figur kann nicht ziehen!");
		}*/ //muss später weg
		Endfeld[] endfelderIstAmZug = istAmZug.getEndFelder();
		endfelderIstAmZug[augenZahl - 1].setFigur(figur);
		figur.setMeinFeld(endfelderIstAmZug[augenZahl - 1]);
	}
	/**
	 * Methode zu DebugZwecken.
	 * Ermöglicht es, zu Testzwecken auf Spieler zuzugreifen, die nicht am Zug sind.
	 * @param spielerzahl - Bestimmter Spielerindex vom Typ int
	 * @return spieler - Ein OPbjekt vom Typ Spieler.
	 */
	public Spieler DebugGetSpieler(int spielerzahl) {
		return spieler[spielerzahl-1];
	}
	/**
	 * Methode zu DebugZwecken.
	 * Ermöglicht es, eine Figur mit einem Zug vor die Endfelder zu setzen.
	 * @param augenzahl - int Wert
	 */
	public void DebugSetAugenzahl(int augenzahl){
		//Unterschied zur Originalmethode
		if (augenzahl >40 || augenzahl<1) throw new RuntimeException("Fehlerhaftes Würfelergebnis");
		this.augenzahl = augenzahl;
	}

}
