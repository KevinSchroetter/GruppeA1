package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;

import Basisklassen.*;

/**
 * @author Alexander Brueckner
 *         (Alexander.Brueckner@Student-Reutlingen-University.de)
 * @version 3.0
 * @since 2015-03-15
 */

public class SpielfigurTest {

	/**
	 * 4 Rote Figuren
	 */

	static Spielfigur[] figurenRot;
	/**
	 * 4 Blaue Figuren
	 */
	static Spielfigur[] figurenBlau;
	/**
	 * 4 Gruene Figuren
	 */
	static Spielfigur[] figurenGruen;
	/**
	 * 4 Gelbe Figuren
	 */
	static Spielfigur[] figurenGelb;

	/**
	 * Referenzen erstellen, alle ausser gelbe Figuren erzeugen
	 */

	@BeforeClass
	public static void erstelleFiguren() {

		figurenRot = new Spielfigur[4];
		figurenBlau = new Spielfigur[4];
		figurenGruen = new Spielfigur[4];
		figurenGelb = new Spielfigur[4];

		for (int i = 0; i < figurenRot.length; i++) {
			figurenRot[i] = new Spielfigur(1,"Fig1");
			System.out.println(figurenRot[i]);
		}

		for (int i = 0; i < figurenBlau.length; i++) {
			figurenBlau[i] = new Spielfigur(2,"Fig2");
			System.out.println(figurenBlau[i]);
		}

		for (int i = 0; i < figurenGruen.length; i++) {
			figurenGruen[i] = new Spielfigur(3,"Fig3");
			System.out.println(figurenGruen[i]);
		}

	}

	/**
	 * Ausgabe dass Test startet
	 */

	@Before
	public void sayStart() {
		System.out.println("Test beginnt..");

	}

	/**
	 * Ausgabe dass Test endet
	 */

	@After
	public void sayEnd() {
		System.out.println("Test endet..");

	}

	/**
	 * Prueft ob Instanziierung abbricht falls ungueltige Farbe gegeben
	 */

	@Test(expected = IllegalArgumentException.class)
	public void UngueltigeFarbe() {
		figurenGelb[0] = new Spielfigur(0,"Fig0");
		System.out.println(figurenGelb[0]);
	}

	/**
	 * Gelbe Figuren erstellen
	 */

	@Test
	public void gelbErstellen() {
		for (int i = 0; i < figurenGelb.length; i++) {
			figurenGelb[i] = new Spielfigur(4,"Fig4");
			System.out.println(figurenGelb[i]);
		}
	}

	/**
	 * Verifizieren, dass nur 16 Figuren existieren duerfen
	 * */

	@Test(expected = RuntimeException.class)
	public void zuVieleFiguren() {
		Spielfigur overflow = new Spielfigur(1,"Fig1");
		overflow = null;
		System.out.println(overflow);
	}
	/**
	 * Pruefen ob die kannSchlagen Methode funktioniert
	 */

	@Test
	public void kannIchEtwasToeten() {
		Spielfeld feld1;
		Spielfeld feld2;
		feld1 = new Standardfeld(5);
		feld2 = new Standardfeld(7);

		figurenGelb[2].setMeinFeld(feld1);
		figurenGruen[1].setMeinFeld(feld2);

		if (!figurenGelb[2].kannSchlagen((Standardfeld) feld2)) {
			fail();
		}
	}

	/**
	 * Sicherstellen dass keine Teamkills moeglich sind
	 */

	@Test
	public void sollteNichtTeamkillen() {

		Spielfeld feld1 = new Standardfeld(5);
		Spielfeld feld2 = new Standardfeld(6);
		figurenGruen[2].setMeinFeld(feld1);
		figurenGruen[3].setMeinFeld(feld2);
		if (figurenGruen[2].kannSchlagen((Standardfeld) feld2)) {
			fail();
		}
	}
}
