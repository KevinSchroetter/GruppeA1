package JUnitTests;


import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import Basisklassen.*;
import Einstellungen.FarbEnum;

/**
 * Testklasse zum Testen der Java-Klasse "Spielfeld" im MADN-Projekt
 * Es werden verschiedene Tests erstellt um zu gewaehrleisten, dass sich die Objekte korrekt erstellen lassen.
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 3.0
 * @since 2015-03-14
 */

public class TestSpielfeld {

	
	/**
	 * Gibt vor jedem Test eine Information aus der Konsole aus, dass der Test gestartet wird.
	 */
	@Before
	public void vorTest() {
		System.out.println("Test beginnt.");
	}

	/**
	 * Gibt nach jedem Test eine Information aus der Konsole aus, dass der Test beendet wurde.
	 */
	@After
	public void nachTest() {
		System.out.println("Test beendet.");
	}
	
	/**
	 * Kontrolle, das Startfeld nicht unter falscher ID erstellt werden kann.
	 * Wird unter "Startfeld" mit einer Einschraenkung der Eingabemoeglichkeiten geregelt.
	 * Exception erwartet
	 */
	
	@Test (expected=Exception.class)
	public void falscherNameStartfeld(){
		Startfeld s = new Startfeld("E3", FarbEnum.BLAU);
		System.out.println(s);
	}
	
	
	/**
	 * Kontrolle, das Endfeld nicht unter falscher ID erstellt werden kann.
	 * Wird unter "Endfeld" mit einer Einschraenkung der Eingabemoeglichkeiten geregelt.
	 * Exception erwartet
	 */
	@Test (expected=Exception.class)
	public void falscherNameEndfeld(){
		Endfeld s2 = new Endfeld("S1", FarbEnum.BLAU);
		System.out.println(s2);
	}
	
	/**
	 * Kontrolle, das Standardfeld nicht mit einer zu hohen ID erstellt werden kann.
	 * Wird unter "Endfeld" mit einer Einschraenkung des Wertebereichs auf 1-40 geregelt.
	 * Exception erwartet
	 */
	@Test (expected=Exception.class)
	public void zuHoheID(){
		Standardfeld f = new Standardfeld(50);
		System.out.println(f);
	}
	
	/**
	 * Kontrolle, das Standardfeld nicht mit einer zu niedrigen ID erstellt werden kann.
	 * Wird unter "Endfeld" mit einer Einschraenkung des Wertebereichs auf 1-40 geregelt.
	 * Exception erwartet
	 */
	@Test (expected=Exception.class)
	public void zuNiedrigeID(){
		Standardfeld f = new Standardfeld(-1);
		System.out.println(f);
	}
	
	/**
	 *  Testet ob eine Spielfigur auf ein Feld gesetzt werden kann und die Spielfigur einem Feld zugewiesen!
	 */
	@Test 
	public void setzeAufFeld(){
		Spielfeld testFeld;
		testFeld = new Standardfeld(34);
		Spielfigur testFigur = new Spielfigur(2,"Fig2");
		testFigur.setMeinFeld(testFeld);
	}
	
	/**
	 *  Testet ob eine neue Figur auf ein belegtes Feld gesetzt werden kann!
	 *  Exception erwartet
	 */
	@Test (expected=Exception.class)
	public void feldIstBelegt(){
		Spielfeld testFeld;
		testFeld = new Standardfeld(34);
		Spielfigur testFigur1 = new Spielfigur(2,"Fig2");
		testFigur1.setMeinFeld(testFeld);
		Spielfigur testFigur2 = new Spielfigur(2,"Fig2");
		testFigur2.setMeinFeld(testFeld);
	}

}
