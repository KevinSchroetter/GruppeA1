package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import Spiel.Spiel;
import Basisklassen.*;

public class SpielTest {
	static Spiel s;

	@BeforeClass
	public static void spielErstellen() {
		s = new Spiel();
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
	public void ersterTest() {
		s.spielerHinzufügen("Kevin", FarbEnum.ROT, null);
		s.spielerHinzufügen("Anna", FarbEnum.ROT, null);
	}

	@After
	public void nachTest() {
		System.out.println("Tests beendet");
	}

	/*@Test(expected=Exception.class)
	public void zweiterTest() {
		s.spielerHinzufügen("Tim", FarbEnum.BLAU, "Hallo");
		
		
	}*/
	
	@Test(expected=Exception.class)
	public void dritterTest(){
		s.spielerHinzufügen("Alex", FarbEnum.GRÜN, "aggressiv");
		s.spielerHinzufügen("Kevin", FarbEnum.GELB, null);
		s.spielerHinzufügen("Anna", FarbEnum.BLAU, null);
		s.spielerHinzufügen("Nala", null, null);

	}

	@Test
	/**
	 * Kontrolle, ob die ArrayList wie erwartet funktioniert und ob Farbe von zugFiguren und Spieler übereinstimmen.
	 */
	public void TesteGetZugFigur(){
		s.spielerHinzufügen("Test",FarbEnum.GRÜN,null);
		System.out.println(s.DebugGetSpieler(1));
		System.out.println(s.getIstAmZug());
		s.startSpiel();
		s.DebugGetSpieler(1).setZugFigur(1);
		Spielfigur Test = s.DebugGetSpieler(1).getZugFigur();
		s.setZugFiguren(s.DebugGetSpieler(1).getFiguren(1));
		assertTrue(s.getZugFiguren(s.getIstAmZug().getZugFigur()).equals(s.DebugGetSpieler(1).getZugFigur()));
	}
}
