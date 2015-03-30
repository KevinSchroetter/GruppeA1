package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;

import Basisklassen.*;

/**
 * @author Alexander Brückner
 *         (Alexander.Brueckner@Student-Reutlingen-University.de)
 * @version 1
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
	 * 4 Grüne Figuren
	 */
	static Spielfigur[] figurenGrün;
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
		figurenGrün = new Spielfigur[4];
		figurenGelb = new Spielfigur[4];

		for (int i = 0; i < figurenRot.length; i++) {
			figurenRot[i] = new Spielfigur(1,"Fig1");
			System.out.println(figurenRot[i]);
		}

		for (int i = 0; i < figurenBlau.length; i++) {
			figurenBlau[i] = new Spielfigur(2,"Fig2");
			System.out.println(figurenBlau[i]);
		}

		for (int i = 0; i < figurenGrün.length; i++) {
			figurenGrün[i] = new Spielfigur(3,"Fig3");
			System.out.println(figurenGrün[i]);
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
	 * Prüft ob Instanziierung abbricht falls ungültige Farbe gegeben
	 */

	@Test(expected = IllegalArgumentException.class)
	public void UngültigeFarbe() {
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
	 * Verifizieren, dass nur 16 Figuren existieren dürfen
	 * */

	@Test(expected = RuntimeException.class)
	public void zuVieleFiguren() {
		Spielfigur overflow = new Spielfigur(1,"Fig1");
		overflow = null;
	}
	/**
	 * Prüfen ob die kannSchlagen Methode funktioniert
	 */

	@Test
	public void kannIchEtwasTöten() {
		Spielfeld feld1;
		Spielfeld feld2;
		feld1 = new Standardfeld(5);
		feld2 = new Standardfeld(7);

		figurenGelb[2].setMeinFeld(feld1);
		figurenGrün[1].setMeinFeld(feld2);

		if (!figurenGelb[2].kannSchlagen((Standardfeld) feld2)) {
			fail();
		}
	}

	/**
	 * Sicherstellen dass keine Teamkills möglich sind
	 */

	@Test
	public void sollteNichtTeamkillen() {

		Spielfeld feld1 = new Standardfeld(5);
		Spielfeld feld2 = new Standardfeld(6);
		figurenGrün[2].setMeinFeld(feld1);
		figurenGrün[3].setMeinFeld(feld2);
		if (figurenGrün[2].kannSchlagen((Standardfeld) feld2)) {
			fail();
		}
	}
}
