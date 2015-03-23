package JUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
		s.spielerHinzufügen("Nala", FarbEnum.ROT, null);

	}
	
	@Test
	
	public void vierterTest(){
		s.get
	}
	

}
