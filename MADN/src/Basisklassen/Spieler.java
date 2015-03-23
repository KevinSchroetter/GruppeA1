package Basisklassen;



/**
 * Die Klasse Spieler für das MADN-Projekt
 * Sie beinhaltet alle Attribute und Methoden, auf die ein Spieler später im Spiel Zugriff erhält.
 * @author Kevin Schrötter
 * @version 1.3
 *
 */
public class Spieler {
	/**
	 * Ein Spieler bekommt eine Spielernummer zugewiesen. Diese wird statisch über den Konstruktor inkrementiert und darf nicht größer als 4 werden.
	 */
	private static int spielernummer = 0;
	/**
	 * Jeder Spieler erhält einen namen vom Typ String
	 */
	private String name; 
	/**
	 * Ein Spieler darf sich eine Spielerfarbe aus der Klasse FarbEnum heraussuchen, um damit im Spiel MADN identifiziert werden zu können.
	 */
	private FarbEnum farbe = null;
	/**
	 * Ein Spieler erhält Kenntnis darüber, was seine Startfelder sind um diese den Figuren zuzuweisen.
	 */
	private Startfeld[] startFelder = null;
	/**
	 * Ein Spieler erhält Kenntnis darüber, was seine Endfelder sind um diese den Figuren zuzuweisen.
	 */
	private Endfeld[] endFelder = null;
	/**
	 * Ein Spieler kennt zu jedem Zeitpunkt immer genau 4 figuren vom Typ Spielfigur
	 */
	private Spielfigur figuren[] = new Spielfigur[4];
	/**
	 * In diesem Attribut wird eine Figur vom Typ Spielfigur gespeichert. Sie wird benötigt, um einen Spielzug auszuführen.
	 */
	private Spielfigur zugFigur = null;
	/** 
	 * Ein Spieler hat immer Kenntnis über einen würfel vom Typ Würfel, mit dem er sein Spiel bestreitet
	 */
	private Würfel meinWürfel = new Würfel();
	/**
	 * Ein Spieler kann von einem Menschen gesteuert werden. In dem Fall nimmt das Attribut bedienung den Wert null an.
	 * Er kann jedoch auch von einer Künstlichen Intelligenz KI gesteuert werden, die als Elementklasse der Klasse Spieler existiert.
	 * Eine KI kann entweder aggressives oder defensives Verhalten aufweisen.
	 */
	private KI bedienung = null;
	/**
	 * Ein Spieler hat kenntnis darüber, ob er gerade an einem Spielzug teilnimmt oder nicht.
	 */
	private boolean amZug = false;
	/** 
	 * Ein Spieler weiß, ob er noch Teil des Spiels ist oder ob er bereits alle Figuren in die Zielfelder gebracht hat.
	 */
	private boolean imSpiel = true;
	/**
	 * Elementklasse der Klasse Spieler.
	 * Hier soll später der Algorithmus für die Künstliche Intelligenz eingefügt werden.
	 * Sie wird später zwei abgeleitete Klassen für aggressives und defensives Verhalten bekommen. 
	 * @author Kevin Schrötter
	 * @version 1.0
	 */
	public class KI {
	/**
	 * Konstruktor für einen Spieler mit "Künstlicher Intelligenz", der entweder aggressiv oder passiv sein kann.
	 * Mit dem Attribut bedienung soll später im Spiel ausgewählt werden können, ob ein menschlicher Spieler über die Klasse Spieler
	 * oder ein Computergegener mit einer künstlichen Intelligenz ober die Elementklasse Spieler.KI erstellt wird.
	 * @param meinSpieler - Spielerobjekt, das der KI zugewiesen wird.
	 * @param verhalten - String, über den die Art der KI später bestimmt wird.
	 */
		private Spieler meinSpieler = null;
		public String verhalten = null;
		/**
		 * Konstruktor für eine Künstliche Intelligenz mit speziellem Verhalten
		 * @param s - Typ Spieler, setzt eine Referenz auf den Spieler
		 * @param verhalten - Typ String, wird verwendet, um das Verhalten der KI zu spezifizieren.
		 */
		public KI(Spieler s,String verhalten) {
			if (s==null)throw new RuntimeException("Kein Spieler übergeben!");
			if(!(verhalten.equals("aggressiv")) && verhalten.equals("defensiv")) throw new RuntimeException("KI kann nur aggressiv oder defensiv sein!");
			this.meinSpieler = s;
			this.verhalten = verhalten;
			//Anweisungen für KI aggressiv und defensiv.
			meinSpieler.setBedienung(this);
			
		}

	}
	/**
	 * Konstruktor zum erstellen eines Objektes, das von einem Menschen gesteuert wird.
	 * Ein Spieler kann nicht ohne Name, Spielerfarbe und Würfel existieren. Zusätzlich wird
	 * eine statische Spielernummer beim erstellen eines Objektes inkrementiert, durch diee in Spieler identifiziert werden kann.
	 * @param name - Der Name vom Typ String, den sich ein Spieler geben darf.
	 * @param farbe - Eine Farbe vom Typ FarbEnum, die sich ein Spieler zu Beginn des Spiels aussuchen darf.
	 * @param startfelder - Die Zugehöreigen Startfelder eines Spielers, auf die die Figuren gesetzt werden. 
	 * @param endfelder - Die Zugehörigen Endfelder eines Spielers, auf die die Figuren ins Ziel kommen.
	 */
	public Spieler(String name, FarbEnum farbe,Startfeld[] startfelder, Endfeld[] endfelder) {
		setSpielernummer();
		setName(name);
		setFarbe(farbe);
		setStartFelder(startfelder);
		setEndFelder(endfelder);
		setFiguren(startfelder);
	}
	/**
	 * Zweiter Konstruktor, über den ein KI Spieler erstellt werden kann.
	 * Die Anforderungen sind die selben, wie bereits im ersten Konstruktor.
	 * Als Erweiterung kommt jedoch die Variable "bedienung" hinzu, über die dem KI-SPieler eine Verhaltensweise
	 * zugewiesen werden kann.
	 * @param name - Name des Spielers vom Typ String
	 * @param farbe - Farbe des Spielers vom Typ FarbEnum
	 * @param startfelder - Die Zugehöreigen Startfelder eines Spielers, auf die die Figuren gesetzt werden.
	 * @param endfelder - Die Zugehörigen Endfelder eines Spielers, auf die die Figuren ins Ziel kommen.
	 * @param verhalten - Bedienung des Spielers vom Typ String, über den eine Künstliche Intelligenz zugeweisen wird (aggressiv oder defensiv).
	 */
	public Spieler(String name, FarbEnum farbe,Startfeld[] startfelder, Endfeld[] endfelder, String verhalten) {
	//	this(name,farbe,meinWürfel,startFelder);
		KI k = new KI(this,verhalten);
		// Anweisungen einfügen
	}
	/**
	 * Setter für die Spielernummer. Diese wird inkrementiert und darf nicht größer als 4 werden.
	 */	
	public void setSpielernummer(){
		if (getSpielernummer() >= 4) throw new RuntimeException("Maximale Spieleranzahl erreicht");
		spielernummer++;
	}
	/**
	 * Getter für die Spielernummer
	 * @return spielernummer - Gibt die spielernummer vom Typ int zurück.
	 */
	public int getSpielernummer(){
		return spielernummer;
	}
	/**
	 * Setter für den Namen. Dieser muss mindestens aus zwei Zeichen bestehen und darf maximal 10 Zeichen lang sein. Es darf jedes Zeichen verwendet werden.
	 * @param name - Der Name des Spielers vom Typ String
	 */
	public void setName(String name){
		if (name.length() < 2 && name.length()<10) throw new RuntimeException("Ungültiger Spielername");
		this.name = name;		
	}
	/**
	 * Getter für den Spielernamen.
	 * @return name - Gibt den Namen des Spielers vom typ String zurück.
	 */
	public String getName(){
		return name;
	}
	/**
	 * Setter für die Spielerfarbe. Setzt die Spielerfarbe mit Hilfe der enum FarbEnum.
	 * @param farbe - Farbe des Spielers vom Typ FarbEnum.
	 */
	public void setFarbe(FarbEnum farbe){
		this.farbe = farbe;
	}
	/**
	 * Getter für die Spielerfarbe
	 * @return farbe - Gibt das Attribut farbe vom Typ FarbEnum zurück.
	 */
	public FarbEnum getFarbe(){
		return farbe;
	}
	/**
	 * Setter für die Startfelder, die benötigt werden, um den Spielfiguren bei der Erstellung das korrekte Startfeld mit der Spielerfarbe zuzuweisen.
	 * @param startFelder - Array vom Typ Startfeld.
	 */
	public void setStartFelder(Startfeld[] startFelder){
		for (Startfeld sf: startFelder){
			if(getFarbe()!=sf.getFarbe())throw new RuntimeException("Ein Spieler kennt nur die Startfelder seiner Farbe!");
		}
		this.startFelder = startFelder;
	}
	/**
	 * Getter für ein Startfeld. Hiermit kann auf die bekannten Startfelder zugegriffen werden.
	 * @param startFeld - Typ int als Index zur Rückgabe eines bestimmten Startfeldes.
	 * @return Startfeld - das gewählte Startfeld
	 */
	public Startfeld getStartFelder(int startFeld){
		if(startFeld <1 | startFeld > 4) throw new RuntimeException("Es können nur startfelder 1-4 angesprochen werden!");
		return startFelder[startFeld-1];
	}
	
	/**
	 * Setter für die Endfelder, die benötigt werden, um den Spielfiguren ins Ziel der korrekten Farbe zu bringen.
	 * @param endFelder - Array vom Typ Endfeld.
	 */
	public void setEndFelder(Endfeld[] endFelder){
		for (Endfeld ef: endFelder){
			if(getFarbe()!=ef.getFarbe())throw new RuntimeException("Ein Spieler kennt nur die Endfelder seiner Farbe!");
		}
		this.endFelder = endFelder;
	}
	/**
	 * Getter für alle Endfelder eines Spielers der zugehörigen Farbe.
	 * @return endFelder - Das Endfelder array vom Typ Endfeld. 
	 */
	public Endfeld[] getEndFelder(){
		return this.endFelder;
	}
	
	
	
	/** 
	 * Setter für die Spielfiguren eines Spielers.
	 * Hierbei wird der Konstruktor der Spielfiguren mit der Farbe des Spielers aufgerufen und den Spielfiguren ein zugehöriges Startfeld zugewiesen.
	 * @param startFelder - Array mit den Startfeldern der zugehörigen Farbe für die Figuren des Spielers
	 */
	public void setFiguren(Startfeld[] startFelder){
		if (getFarbe() == null) throw new RuntimeException("Es muss eine Farbe vergeben sein!");
		int farbID = 0;
		if (getFarbe()==FarbEnum.ROT) farbID = 1;
		else if (getFarbe()==FarbEnum.BLAU) farbID = 2;
		else if (getFarbe()==FarbEnum.GRÜN) farbID = 3;
		else if (getFarbe()==FarbEnum.GELB) farbID = 4; 
			
		for (int i = 0; i <4; i++){
			figuren[i] = new Spielfigur(farbID);
			figuren[i].setMeinFeld(startFelder[i]);
		}
	}
	/**
	 * Getter für die Spielfiguren.
	 * @param figur - Index vom Typ int, um eine bestimmte Spielfigur anzusprechen.
	 * @return figuren - Gibt ein Spielfigurarray zurück, in dem alle Spielfiguren des Spielers gespeichert sind.
	 */
	public Spielfigur getFiguren(int figur){
		if(figur<1 | figur>4) throw new RuntimeException("Spielfiguren können nur mit den Zahlen 1,2,3 und 4 angesprochen werden!");
		return figuren[figur-1];
	}
	/**
	 * Setter für eine Spielfigur, mit der gezogen werden soll.
	 * Auf die Figuren kann mit den Zahlen 1-4 zugegriffen werden. Diese werden dann verwendet,
	 * um auf die ArrayIndizes 0-3 zuzugreifen und so ein Spielfigur zurückzugeben.
	 * @param figurID - Eine int, bei dem die Werte 1-4 erlaubt sind, um auf eine Figur zuzugreifen.
	 */
	public void setZugFigur(int figurID){
		if(figurID<1 | figurID>4) throw new RuntimeException("Spielfiguren können nur mit den Zahlen 1,2,3 und 4 angesprochen werden!");
		this.zugFigur = getFiguren(figurID);	
	}
	/**
	 * Getter für die Spielfigur, mit der ein Spielzug ausgeführt werden soll.
	 * @return zugFigur - Die Figur vom Typ Spielfigur, mit der ein Zug ausgeführt werden soll.
	 */
	public Spielfigur getZugFigur(){
		return zugFigur;
	}
	/**
	 * Getter für den Würfel des Spielers, mit dem er seinen Zug ausführen kann.
	 * @return meinWürfel - Der Würfel eines Spielers vom Typ Würfel
	 */
	public Würfel getMeinWürfel(){
		return meinWürfel;
	}
	/**
	 * Setter für das boolean Attribut amZug
	 * @param amZug - boolean um festzulegen, ob ein Spieler gerade ziehen darfo der nicht.
	 */
	public void setAmZug(boolean amZug){
		this.amZug = amZug;
	}
	/**
	 * Getter für amZug
	 * @return amZug - gibt den aktuellen Wert des boolean Attributs amZug zurück.
	 */
	public boolean getAmZug(){
		return amZug;
	}
	/**
	 * Setter für das boolean Attribut imSpiel.
	 * TRUE - Spieler ist teil der MADN-Sitzung
	 * FALSE - Spieler hat bereits alle Figuren in die Zielfelder gebracht
	 * @param imSpiel - Boolean Wert, mit dem festgelegt werden kann, ob ein Spieler generell bei einem Zug übersprungen wird oder nicht.
	 */
	public void setImSpiel(boolean imSpiel){
		this.imSpiel = imSpiel;
	}
	/**
	 * Getter für das boolean Attribut imSpiel.
	 * @return imSpiel - Boolean, der anzeigt, ob sich ein Spieler noch im Spiel befindet oder bereits alle Figuren in den Zielfeldern hat.
	 */
	public boolean getImSpiel(){
		return imSpiel;
	}
	/**
	 * Setter für bedienung. 
	 * @param ki - String, der angibt, ob es sich um einen menschlichen Spieler, eine aggressive KI oder eine defensive KI handelt.
	 */
	public void setBedienung(KI ki){
		this.bedienung = ki;
	}
	/**
	 * Getter für bedienung.
	 * @return bedienung - gibt zurück, welche KI eingestellt wurde. Gibt es keine KI, so ist die Rückgabe null.
	 */
	public KI getBedienung() {
		return bedienung;
	}
	/**
	 * Override der toString Methode.
	 * @return - gibt die Spielernummer, den Spielernamen und die Spielerfarbe als String zurück.
	 */
	@Override
	public String toString(){
		return "Spieler " + getSpielernummer() + " Name: " + getName() + " Farbe: "+getFarbe();
	}
	@Override
	/**
	 * Override der equals. Zwei Objekte der Klasse Spieler sind nur dann gleich, wenn die Namen und die Farben dieselben sind.
	 * @param o - Übergebenes Spielerobjekt, das mit this verglichen wird.
	 * @return - gibt zurück, ob zwei miteinander verglichene Spieler gleich sind, oder nicht
	 */
	public boolean equals(Object o){
		if (!(o instanceof Spieler)) return false;
		else{
			Spieler s = (Spieler) o;
			return s.getName().equals(this.getName()) && s.getFarbe().equals(this.getFarbe());
		}
	}
	/**
	 * Methode um zu ermitteln, ob ein Spieler vor seinem Zug direkt übersprungen werden kann.
	 * Sie nutzt dazu die Methode kannIchZiehen(int) der Klasse Spielfigur und geht diese für jede Figur aus dem Attribut figuren durch.
	 * @return  - TRUE, falls mindestens eine Figur ziehen kann, FALSE, falls keine einzige Figur ziehen kann.
	 */
	public boolean kannIchZiehen(){
		int augenzahl = getMeinWürfel().werfen();
		int check = 0;
		for(int i = 0; i<4;i++){
			if (alleFiguren()[i].kannIchZiehen(augenzahl)==true) check++;
		}
		if (check > 0) return true;
		else{
			setAmZug(false);
			return false;
		}
	}
	/**
	 * Methode, die die toString der Figuren aufruft, um die Positionen der Figuren eines Spielers anzuzeigen.
	 * @return - Ein String, der die toString() der Klasse Figur für jede Figur des Spielers zurückgibt.
	 */
	public String figurPositionen(){
		return getFiguren(1).toString()+" "+getFiguren(2).toString()+" "+getFiguren(3).toString()+" "+getFiguren(4).toString();
	}
	/**
	 * Diese Methode nimmt die gespeicherte zugFigur und führt einen Zug aus. Das heisst konkret, dass Sie kontrolliert, ob überhaupt eine
	 * Spielfigur in zugFigur ausgewählt wurde. Falls dies nicht so ist, wirft sie eine RuntimeException.
	 * Anschließend wird geprüft, ob die ausgewählte Figur selbst ziehen kann. Wenn dem so ist, wird die Methode laufen(int) der Klasse Spielfigur aufgerufen.
	 */
	public void ziehen(){
		if (getZugFigur()==null)throw new RuntimeException("Keine Figur ausgewählt");
		int augenzahl = getMeinWürfel().testWurf(6);
		if (getZugFigur().kannIchZiehen(augenzahl)==false)throw new RuntimeException("Diese Figur kann nicht ziehen. Bitte wählen Sie eine andere aus!");
		//getZugFigur().laufen(augenzahl); Methode der Spielfigur fehlt nocH!;	
		setAmZug(false);
	}
	/**
	 * Hilfsmethode mit der das Figuren-Array zurückgegeben werden kann.
	 * @return figuren - Array vom Typ Spielfigur
	 */
	private Spielfigur[] alleFiguren(){
		return figuren;
	}
}
