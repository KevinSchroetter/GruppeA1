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
 * 
 * @author Kevin Schrötter
 * @version 1.0
 * JUnit Testklasse zum Testen der Java Klasse "Spieler" im MADN Projekt
 * Hierbei werden verschiedene Tests abgedeckt, um die Funktionalität von "Spieler" zu gewärleisten.
 * 
 * @Attributes 
 * -> spieler[] - Array zum Speichern von bis zu 4 Spieler-Objekten
 * -> Farbenums rot, blau, grün, gelb - angelegt zum einfacheren zuweisen einer Farbe an einen Spieler 
 * 
 */

public class TestSpieler {
	static Spieler spieler[] = new Spieler[3];
	static Würfel meinWürfel = new Würfel();
	static Spielfigur figuren[] = new Spielfigur[4];

	/**
	 * Bevor die Tests starten, werden 3 Spieler angelegt.	
	 */
	@BeforeClass
	public static void erstelleSpieler(){
		
		spieler[0] = new Spieler("Anna",FarbEnum.ROT,meinWürfel);
		spieler[1] = new Spieler("Felix",FarbEnum.BLAU,meinWürfel);
		spieler[2] = new Spieler("Alex",FarbEnum.GRÜN,meinWürfel);
	}
	
	/**
	 * Vor jedem Test wird eine Ausgabe auf der Konsole dargestellt. Sie signalisiert den Start jedes Testfalls.
	 */
	@Before
	public void startAnzeige(){
		System.out.println("Teststart");
	}
	/**
	 * Für jedes Ende eines Tests wird ebenfalls eine Ausgabe dargestellt.
	 */
	@After
	public void endAnzeige(){
		System.out.println("Testende");
		System.out.println("");
	}
	/**
	 * Erster Test. Hierbei wird überprüft, ob ein Spieler tatsächlich im Besitz von 4 Spielfiguren ist
	 */
	@Ignore
	@Test
	public void trivialTestAnzahlFiguren(){
		int anzFiguren=0;
		for (int i = 1; i <= 4; i++){
			if (spieler[0].getFiguren()!=null) anzFiguren++;
		}
		assertTrue(anzFiguren == 4);
	}
	/**
	 * Dieser Test kontrolliert, ob jeder Spielername einzigartig ist.
	 * Dazu wird bewusst ein Spieler mit einem Namen angelegt, den es bereits gibt.
	 * Erwartet wird hierbei eine Exception.
	 */
	@Ignore
	@Test(expected=Exception.class)
	public void gleicheNamen() {
		Spieler s4 = new Spieler("Alex",FarbEnum.GELB,meinWürfel);
	}
	/**
	 * Kontrolle, das nicht mehr als 4 Spieler erstellt werden können.
	 * Dies soll wird in "Spieler" über ein statisches Attribut zur Spieleranzahl geregelt.
	 * Im Test wird bewusst ein zusätzlicher Spieler angelegt, der die maximale Anzahl übersteigt. 
	 * Erwartet wird eine Exception.
	 */
	@Ignore
	@Test(expected=Exception.class)
	public void SpielerOverload() {
		Spieler s4 = new Spieler("Kevin",FarbEnum.GELB,meinWürfel);
		Spieler overload = new Spieler("Bonus",FarbEnum.ROT,meinWürfel);
	}
	/**
	 * Ähnlich wie der Test zum Spielernamen. Hierbei wird jedoch die Farbe überprüft.
	 */
	@Ignore
	@Test(expected=Exception.class)
	public void FarbOverload(){
		Spieler s4 = new Spieler("Kevin",FarbEnum.ROT,meinWürfel);
	}
	/**
	 * Hier wird getestet, ob das Spieler-Attribut amZug tatsächlich auf False gesetzt wird, nachdem ein Spieler einen Zug ausgeführt hat.
	 */
	@Ignore
	@Test
	public void nächsterZug(){
		spieler[0].kannIchZiehen();
		spieler[0].ziehen();
		assertFalse(spieler[0].getAmZug()==true);
	}
	/**
	 * Kontrolle, ob einem Spieler erfolgreich eine KI der Elementklasse KI zugewiesen wurde.
	 */
	@Ignore
	@Test
	public void kiSpieler(){
		Spieler KI = new Spieler("Kevin",FarbEnum.GELB,meinWürfel,"aggressiv");
		//assertTrue(KI.getBedienung() instanceof Spieler.KI);
	}

}
