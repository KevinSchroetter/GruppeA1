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
 * Testklasse zum Testen der Java-Klasse "Spielfeld" im MADN-Projekt
 * Es werden verschiedene Tests erstellt um zu gewährleisten, dass sich die Objekte korrekt erstellen lassen.
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 1.0
 * @since 2015-03-14
 */

public class TestSpielfeld {

	
	/**
	 * Gibt vor jedem Test eine Information aus der Konsole aus, dass der Test gestartet wird.
	 * */
	@Before
	public void vorTest() {
		System.out.println("Test beginnt.");
	}

	/**
	 * Gibt nach jedem Test eine Information aus der Konsole aus, dass der Test beendet wurde.
	 * */
	@After
	public void nachTest() {
		System.out.println("Test beendet.");
	}
	
	/**
	 * Kontrolle, das Startfeld nicht unter falscher ID erstellt werden kann.
	 * Wird unter "Startfeld" mit einer Einschränkung der Eingabemöglichkeiten geregelt.
	 * Exception erwartet
	 * */
	
	@Test (expected=Exception.class)
	public void falscherNameStartfeld(){
		Startfeld s = new Startfeld("E3", FarbEnum.BLAU);
	}
	
	
	/**
	 * Kontrolle, das Endfeld nicht unter falscher ID erstellt werden kann.
	 * Wird unter "Endfeld" mit einer Einschränkung der Eingabemöglichkeiten geregelt.
	 * Exception erwartet
	 * */
	@Test (expected=Exception.class)
	public void falscherNameEndfeld(){
		Endfeld s2 = new Endfeld("S1", FarbEnum.BLAU);
	}
	
	/**
	 * Kontrolle, das Standardfeld nicht mit einer zu hohen ID erstellt werden kann.
	 * Wird unter "Endfeld" mit einer Einschränkung des Wertebereichs auf 1-40 geregelt.
	 * Exception erwartet
	 * */
	@Test (expected=Exception.class)
	public void zuHoheID(){
		Standardfeld f = new Standardfeld(50);
	}
	
	/**
	 * Kontrolle, das Standardfeld nicht mit einer zu niedrigen ID erstellt werden kann.
	 * Wird unter "Endfeld" mit einer Einschränkung des Wertebereichs auf 1-40 geregelt.
	 * Exception erwartet
	 * */
	@Test (expected=Exception.class)
	public void zuNiedrigeID(){
		Standardfeld f = new Standardfeld(-1);
	}

}
