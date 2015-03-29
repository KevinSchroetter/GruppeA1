package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import Spiel.Spiel;
import Basisklassen.*;

/**
 * JUnit Tests für die Klasse Spiel
 * @author Kevin Schrötter, Felix Rosa, Anna Rosa, Alexander Brückner
 * @version 2.0
 *
 */
public class SpielTest {
	static Spiel s;
	static Spiel b;
	//static Spiel t;

	@BeforeClass
	public static void spielErstellen() {
		s = new Spiel();
		b = new Spiel();
		//t= new Spiel();
		
	}

	@Before
	public void vorTest() {
		System.out.println("Test beginnt");
	}
	
	@Test
	public void setterTestSpielfeld(){
		assertTrue(s.getSpielbrett() instanceof Spielbrett);
	}
	@Test(expected = Exception.class)
	public void gleicheFarbe() {
		s.spielerHinzufügen("Kevin", 1, 0);
		s.spielerHinzufügen("Anna", 1, 0);
	}

	@After
	public void nachTest() {
		System.out.println("Tests beendet");
	}

	/*@Test(expected=Exception.class)
	public void ungültigeKI() {
		s.spielerHinzufügen("Tim", FarbEnum.BLAU, "Hallo");
		
		
	}*/
	
	@Test(expected=Exception.class)
	public void FarbeNull(){
		s.spielerHinzufügen("Päddi",0, 0);
	}
	
	@Test(expected=Exception.class)
	public void spielerZuViel(){
		s.spielerHinzufügen("Alex", 1, 1);
		s.spielerHinzufügen("Kevin", 2, 0);
		s.spielerHinzufügen("Anna", 3, 0);
		s.spielerHinzufügen("Sam", 4, 0);
		
		

	}
	
	/*@Test(expected=Exception.class)
	public void zuVieleSpieler(){
		t.spielerHinzufügen("Jana", FarbEnum.GELB, null);
		t.spielerHinzufügen("Kevin", FarbEnum.GRÜN, null);
		t.spielerHinzufügen("Anna", FarbEnum.BLAU, null);
		t.spielerHinzufügen("Sam", FarbEnum.ROT, null);
		t.spielerHinzufügen("Anna", FarbEnum.GRÜN, null);
	}*/
	
	@Test
	public void testeObSpielGestartet(){
		assertTrue(s.getHatBegonnen());
	}
	@Test
	public void würfeln(){
		b.startSpiel();
		b.würfeln(6);
		assertTrue(s.getAlleAufSpawn()==true);
	}
}
