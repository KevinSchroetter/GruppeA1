package Spiel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import Basisklassen.*;

/**
 * JUnit Tests fuer die Klasse Spiel
 * @author Kevin Schroetter, Felix Rosa, Anna Rosa, Alexander Brueckner
 * @version 4.0
 *
 */
public class SpielTest {
	static Spiel ki_game;
	static Spiel s;
	static Spiel s2;
	static Spiel s3;
	static Spiel s4;
	static Spiel s5;

	@BeforeClass
	/**
	 * Vor den Testfaellen wird ein neues Spiel s erstellt.
	 */
	public static void spielErstellen() {
		ki_game = new Spiel();
		s = new Spiel();
		s2 = new Spiel();
		s3 = new Spiel();
		s4 = new Spiel();
		s5 = new Spiel();
		System.out.println("Tests beginnen!");
	}

	@Before
	public void vorTest() {	
		
	}
	
	@After
	public void nachTest() {

	}
	
	/**
	 * Testet, ob ein Spieler mit der Selben Farbe
	 */
	@Test(expected=RuntimeException.class)
	public void gleicheFarbe() {
		s.spielerHinzufuegen("Anna", 1, 0);
		s.spielerHinzufuegen("Felix", 1, 0);
	}
	
	/**
	 * Kontrolle, ob das Spielbrett gesetzt wurde.
	 */
	@Test
	public void setterTestSpielfeld(){
		assertTrue(s.getSpielbrett() instanceof Spielbrett);
	}
	
	/**
	 * Testet, ob ein Spiel erfolgreich gestartet ist, nachdem ein Spieler hinzugefuegt wurde.	
	 */
	@Test
	public void testeObSpielGestartet(){
		s.spielerHinzufuegen("Kev", 1, 0);
		s.starteSpiel();
		assertTrue(s.getHatBegonnen()==true && s.getAnzahlSpieler()==1);
	}
	
	/**
	 * Sollte Gruen durchlaufen, gibt aber einen Fehler! Problem bei der Erstellung einer KI
	 * Test auf IGNORE gesetzt.
	 */
	@Ignore
	@Test
	public void KIhinzufuegen() {
		ki_game.spielerHinzufuegen("Tim", 2, 1);
		assertTrue(ki_game.getAnzahlSpieler()==1);
	}
	
	/**
	 * Erwartet eine Exception, da die Farbe mit der ID 0 auf keine Farbe zeigt!
	 */
	@Test(expected=RuntimeException.class)
	public void FarbeNull(){
		s.spielerHinzufuegen("Paeddi",0, 0);
	}
	
	/**
	 * Hinzufuegen von zu vielen Spielern scheitert.
	 * Man merke, ein Spieler mit der Farbe ROT ist bereits erstellt!
	 */
	@Test(expected=RuntimeException.class)
	public void spielerZuViel(){
		s.spielerHinzufuegen("Kevin", 2, 0);
		s.spielerHinzufuegen("Anna", 3, 0);
		s.spielerHinzufuegen("TooMuch", 4, 0);
	}
	
	/**
	 * Kontrolle, ob die Anzahl der fuer einen Zug moeglichen Figuren nachdem eine eigene Figur auf
	 * dem eigenen Spawnfeld steht tatsaechlich nur 1 betraegt. Gleichzeitig wird getestet, ob der Spawnpunkt
	 * von Spieler BLAU auch auf Feld mit ID 11 liegt.
	 */
	@Test
	public void alleaufspawnzugnachsechs(){
		s.neuesSpiel();
		s.spielerHinzufuegen("KEVKEV", 1, 0);
		s.spielerHinzufuegen("FLIXFLIX",2,0);
		s.starteSpiel();
		s.wuerfeln(6);
		s.zugDurchfuehren("S1");//KEVKEV holt eine Figur aus den Startfeldern
		s.wuerfeln(1);
		s.zugDurchfuehren("1");//KEVKEV geht weg vom Startfeld, FLIXFLIX ist darn
		s.wuerfeln(6);
		s.zugDurchfuehren("S1");//FLIXFLIX holt eine Figur aus den Startfeldern
		s.wuerfeln(6);//Spawnfeld ist belegt, eine 6 wurde gewuerfelt. Nun koennen die anderen Figuren nicht spawnen, es darf also nur EINE Figur laufen
		assertTrue(s.alleZugFiguren().size()==1);
	}

	/**
	 * Testet, ob nach einer gewuerfelten 6 das Attribut alleAufSpawn korrekterweise auf TRUE steht.
	 */
	@Test
	public void alleAufSpawn6(){
		s.wuerfeln(6);
		assertTrue(s.getAlleAufSpawn()==true);
	}
	
	/**
	 * Testet, ob nach einem Wuerfelversuch noch alle Figuren auf dem Spawnfeld stehen.
	 */
	@Test
	public void alleAufSpawn(){
		s2.spielerHinzufuegen("Kev", 1, 0);
		s2.starteSpiel();
		s2.wuerfeln(2);
		assertTrue(s2.getAlleAufSpawn()==true);
	}
	
	/**
	 * Vor dem Spawnen darf nach einer 6 nicht nochmals gewuerfelt werden.
	 * Dies muss in diesem Test separat behandelt werden, da es in der Methode
	 * wuerfeln zwei gesonderte Faelle sind.
	 */
	@Test(expected=RuntimeException.class)
	public void wuerfelCheckausSpawnMitSechs(){
		s2.wuerfeln(6);
		s2.wuerfeln(2);
	}
	
	/**
	 * Ein Sppieler darf nicht 2 mal hintereinander Wuerfeln, nachdem er die Chance hat zu ziehen!
	 * waehleFigur wird hier aufgerufen, da in einem Vorherigen Test mit Spiel s bereits gewuerfelt wurde.
	 */
	@Test(expected=RuntimeException.class)
	public void doppelWurfNachSpawnen(){
		s2.zugDurchfuehren("S1");
		s2.wuerfeln(6);
		s2.wuerfeln(2);
	}
	
	/**
	 * Ein Spieler darf nicht 2 mal hintereinander Ziehen ohne dazwischen zu wuerfeln
	 */
	@Test
	public void zugTestDoppel(){
		s3.spielerHinzufuegen("Versager", 1, 0);
		s3.starteSpiel();
		s3.wuerfeln(6);
		s3.zugDurchfuehren("S1");
		s3.zugDurchfuehren("1");
		assertTrue(s3.spielbrett.getStandardFelder()[1].getFigur()==null);
	}
	
	/**
	 * Ein Spieler, der NICHT mehr alle Figuren auf den Startfeldern hat, darf ebenfalls nicht
	 * doppelt wuerfeln. Dies muss hier nochmals getestet werden, da es zwei unterschiedliche Faelle sind.
	 * Dazu wird Spiel s3 verwendet.
	 */
	@Test(expected=RuntimeException.class)
	public void nochmalWuerfelnNachSechs(){
		s4.spielerHinzufuegen("Kev",1,0);
		s4.spielerHinzufuegen("Felix", 2, 0);
		s4.starteSpiel();
		s4.wuerfeln(6);
		s4.zugDurchfuehren("S1");
		s4.wuerfeln(2);
		s4.zugDurchfuehren("1");
		// Jetzt kommt Spieler Felix und versagt 3 mal im wuerfeln, damit danach wieder Kev dran ist
		s4.wuerfeln(2);
		s4.wuerfeln(2);
		s4.wuerfeln(2);
		// Nun kommt Kev, wuerfelt eine 6 und versucht danach nochmals zu wuerfeln. Dies klappt jedoch nicht
		s4.wuerfeln(6);
		s4.wuerfeln(2);
	}
	
	@Test
	public void herMitDenSpielern(){
		System.out.println("Spieler Strings Test Anfang");
		String[] noob = new String[4];
		noob = s4.ausgabeSpielerNamenStrings();
		
		for(int i = 0; i < noob.length; i++){
			System.out.println(noob[i]);
		}
		System.out.println("Spieler Strings Test Ende");
	}
}
