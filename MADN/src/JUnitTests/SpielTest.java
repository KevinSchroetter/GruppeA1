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
 * @version 1.2
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
		s.spielerHinzufügen("Kevin", FarbEnum.ROT, null);
		s.spielerHinzufügen("Anna", FarbEnum.ROT, null);
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
		s.spielerHinzufügen("Päddi", null, null);
	}
	
	@Test(expected=Exception.class)
	public void spielerZuViel(){
		s.spielerHinzufügen("Alex", FarbEnum.GRÜN, "aggressiv");
		s.spielerHinzufügen("Kevin", FarbEnum.GELB, null);
		s.spielerHinzufügen("Anna", FarbEnum.BLAU, null);
		s.spielerHinzufügen("Sam", FarbEnum.ROT, null);
		
		

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
