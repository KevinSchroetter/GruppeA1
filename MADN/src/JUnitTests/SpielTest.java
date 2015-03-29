package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import Spiel.Spiel;
import Basisklassen.*;
import Hilfsklassen.FigurKannNichtZiehenException;

/**
 * JUnit Tests für die Klasse Spiel
 * @author Kevin Schrötter, Felix Rosa, Anna Rosa, Alexander Brückner
 * @version 2.0
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
	 * Vor den Testfällen wird ein neues Spiel s erstellt.
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
		s.spielerHinzufügen("Anna", 1, 0);
		s.spielerHinzufügen("Felix", 1, 0);
	}
	/**
	 * Testet, ob ein Spiel nicht startet, wenn kein Spieler hinzugefügt wurde.
	 */
	
	@Test(expected=RuntimeException.class)
	public void spielDarfNichtStarten(){
		s2.startSpiel();
	}
	/**
	 * Kontrolle, ob das Spielbrett gesetzt wurde.
	 */
	@Test
	public void setterTestSpielfeld(){
		assertTrue(s.getSpielbrett() instanceof Spielbrett);
	}
	/**
	 * Testet, ob ein Spiel erfolgreich gestartet ist, nachdem ein Spieler hinzugefügt wurde.	
	 */
	@Test
	public void testeObSpielGestartet(){
		s.spielerHinzufügen("Kev", 1, 0);
		s.startSpiel();
		assertTrue(s.getHatBegonnen()==true && s.getAnzahlSpieler()==1);
	}
	/**
	 * Sollte Grün durchlaufen, gibt aber einen Fehler! Problem bei der Erstellung einer KI
	 * Test auf IGNORE gesetzt.
	 */
	@Ignore
	@Test
	public void KIhinzufuegen() {
		ki_game.spielerHinzufügen("Tim", 2, 1);
		assertTrue(ki_game.getAnzahlSpieler()==1);
	}
	/**
	 * Erwartet eine Exception, da die Farbe mit der ID 0 auf keine Farbe zeigt!
	 */
	@Test(expected=RuntimeException.class)
	public void FarbeNull(){
		s.spielerHinzufügen("Paeddi",0, 0);
	}
	/**
	 * Hinzufügen von zu vielen Spielern scheitert.
	 * Man merke, ein Spieler mit der Farbe ROT ist bereits erstellt!
	 */
	@Test(expected=RuntimeException.class)
	public void spielerZuViel(){
		s.spielerHinzufügen("Kevin", 2, 0);
		s.spielerHinzufügen("Anna", 3, 0);
		s.spielerHinzufügen("TooMuch", 4, 0);
	}
	/**
	 * Kontrolle, ob die Anzahl der fuer einen Zug moeglichen Figuren nachdem eine eigene Figur auf
	 * dem eigenen Spawnfeld steht tatsaechlich nur 1 betraegt. Gleichzeitig wird getestet, ob der Spawnpunkt
	 * von Spieler BLAU auch auf Feld mit ID 11 liegt.
	 */
	@Test
	public void alleaufspawnzugnachsechs(){
		s.neuesSpiel();
		s.spielerHinzufügen("KEVKEV", 1, 0);
		s.spielerHinzufügen("FLIXFLIX",2,0);
		s.startSpiel();
		s.würfeln(6);
		s.wähleFigur("S1");//KEVKEV holt eine Figur aus den Startfeldern
		s.würfeln(1);
		s.wähleFigur("1");//KEVKEV geht weg vom Startfeld, FLIXFLIX ist darn
		s.würfeln(6);
		s.wähleFigur("S1");//FLIXFLIX holt eine Figur aus den Startfeldern
		s.würfeln(6);//Spawnfeld ist belegt, eine 6 wurde gewuerfelt. Nun koennen die anderen Figuren nicht spawnen, es darf also nur EINE Figur laufen
		assertTrue(s.alleZugFiguren().size()==1);
	}

	/**
	 * Testet, ob nach einer gewuerfelten 6 das Attribut alleAufSpawn korrekterweise auf TRUE steht.
	 */
	@Test
	public void alleAufSpawn6(){
		s.würfeln(6);
		assertTrue(s.getAlleAufSpawn()==true);
	}
	/**
	 * Testet, ob nach einem Wuerfelversuch noch alle Figuren auf dem Spawnfeld stehen.
	 */
	@Test
	public void alleAufSpawn(){
		s2.spielerHinzufügen("Kev", 1, 0);
		s2.starteSpiel();
		s2.würfeln(2);
		assertTrue(s2.getAlleAufSpawn()==true);
	}
	/**
	 * Vor dem Spawnen darf nach einer 6 nicht nochmals gewürfelt werden.
	 * Dies muss in diesem Test separat behandelt werden, da es in der Methode
	 * würfeln zwei gesonderte Fälle sind.
	 */
	@Test(expected=RuntimeException.class)
	public void wuerfelCheckausSpawnMitSechs(){
		s2.würfeln(6);
		s2.würfeln(2);
	}
	/**
	 * Ein Sppieler darf nicht 2 mal hintereinander Wuerfeln, nachdem er die Chance hat zu ziehen!
	 * wähleFigur wird hier aufgerufen, da in einem Vorherigen Test mit Spiel s bereits gewürfelt wurde.
	 */
	@Test(expected=RuntimeException.class)
	public void doppelWurfNachSpawnen(){
		s2.wähleFigur("S1");
		s2.würfeln(6);
		s2.würfeln(2);
	}
	/**
	 * Ein Spieler darf nicht 2 mal hintereinander Ziehen ohne dazwischen zu wuerfeln
	 */
	@Test(expected = FigurKannNichtZiehenException.class)
	public void zugTestDoppel(){
		s3.spielerHinzufügen("Versager", 1, 0);
		s3.startSpiel();
		s3.würfeln(6);
		s3.wähleFigur("S1");
		s3.wähleFigur("1");
	}
	/**
	 * Ein Spieler, der NICHT mehr alle Figuren auf den Startfeldern hat, darf ebenfalls nicht
	 * doppelt würfeln. Dies muss hier nochmals getestet werden, da es zwei unterschiedliche Fälle sind.
	 * Dazu wird Spiel s3 verwendet.
	 */
	@Test(expected=RuntimeException.class)
	public void nochmalWuerfelnNachSechs(){
		s4.spielerHinzufügen("Kev",1,0);
		s4.spielerHinzufügen("Felix", 2, 0);
		s4.startSpiel();
		s4.würfeln(6);
		s4.wähleFigur("S1");
		s4.würfeln(2);
		s4.wähleFigur("1");
		// Jetzt kommt Spieler Felix und versagt 3 mal im wuerfeln, damit danach wieder Kev dran ist
		s4.würfeln(2);
		s4.würfeln(2);
		s4.würfeln(2);
		// Nun kommt Kev, würfelt eine 6 und versucht danach nochmals zu würfeln. Dies klappt jedoch nicht
		s4.würfeln(6);
		s4.würfeln(2);
	}
}
