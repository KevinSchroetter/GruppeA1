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
 * @version 1.2
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
	static Spielbrett b = new Spielbrett();
	static Spielfigur figuren[] = new Spielfigur[4];
	static Startfeld rotStart[] = {b.getStartFelderRot(0),b.getStartFelderRot(1),b.getStartFelderRot(2),b.getStartFelderRot(3)};
	static Startfeld blauStart[] = {b.getStartFelderBlau(0),b.getStartFelderBlau(1),b.getStartFelderBlau(2),b.getStartFelderBlau(3)};
	static Startfeld grünStart[] = {b.getStartFelderGrün(0),b.getStartFelderGrün(1),b.getStartFelderGrün(2),b.getStartFelderGrün(3)};
	static Startfeld gelbStart[] = {b.getStartFelderGelb(0),b.getStartFelderGelb(1),b.getStartFelderGelb(2),b.getStartFelderGelb(3)};

	/**
	 * Bevor die Tests starten, werden 3 Spieler angelegt.	
	 */
	@BeforeClass
	public static void erstelleSpieler(){
		
		
		
		spieler[0] = new Spieler("Anna",FarbEnum.ROT,meinWürfel,rotStart);
		spieler[1] = new Spieler("Felix",FarbEnum.BLAU,meinWürfel,blauStart);
		spieler[2] = new Spieler("Alex",FarbEnum.GRÜN,meinWürfel,grünStart);
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

	@Test
	public void trivialTestAnzahlFiguren(){
		int anzFiguren=0;
		for (int i = 1;i <= 4;i++){
			if(spieler[0].getFiguren(i)!=null) anzFiguren++;
		}
		
		assertTrue(anzFiguren==4);
	}
	/**
	 * Kontrolle, das nicht mehr als 4 Spieler erstellt werden können.
	 * Dies soll wird in "Spieler" über ein statisches Attribut zur Spieleranzahl geregelt.
	 * Im Test wird bewusst ein zusätzlicher Spieler angelegt, der die maximale Anzahl übersteigt. 
	 * Erwartet wird eine Exception.
	 */

	@Test(expected=Exception.class)
	public void SpielerOverload() {
		Spieler s4 = new Spieler("Kevin",FarbEnum.GELB,meinWürfel,gelbStart);
		Spieler overload = new Spieler("Bonus",FarbEnum.ROT,meinWürfel,rotStart);
	}
	/**
	 * Ähnlich wie der Test zum Spielernamen. Hierbei wird jedoch die Farbe überprüft.
	 */
	@Test(expected=Exception.class)
	public void FarbOverload(){
		Spieler s4 = new Spieler("Kevin",FarbEnum.ROT,meinWürfel,rotStart);
	}
	/**
	 * Hier wird getestet, ob eine Fehlermeldung erscheint, wenn eine Figur nicht ausgewählt ist.
	 */
	@Test(expected=RuntimeException.class)
	public void kannSpielerZiehen(){
		spieler[0].kannIchZiehen();
		spieler[0].setZugFigur(1);
		spieler[0].ziehen();
	}
	/**
	 * Hier wird getestet, ob eine Figur ziehen kann. Da bislang noch keine Figur laufen kann, wird ein False erwartet
	 */
	@Test
	public void kannEinzelneFIgurZiehen(){
		assertTrue(spieler[0].kannIchZiehen()==false);
	}
	/**
	 * Dieser Test kontrolliert, ob das Attribut amZug eines Spielers nach einem Zug korrekterweise auf false gesetzt wird.
	 * Dies kann erst getestet werden, wenn eine Figur laufen kann.
	 */
	@Ignore
	@Test
	public void nächsterZug(){
		spieler[0].setZugFigur(1);
		spieler[0].ziehen();
		assertTrue(spieler[0].getAmZug()==false);
	}
	/**
	 * Kontrolle, ob sowol der Spieler, seine Spielfiguren und deren Startfelder die selbe Farbe besitzen.
	 */
	@Test 
	public void farbenTest(){
		boolean farbe = false;
		Startfeld x = (Startfeld)spieler[0].getFiguren(1).getMeinFeld();
		if (spieler[0].getFarbe()==spieler[0].getFiguren(1).getFarbe() && spieler[0].getFarbe()==x.getFarbe())farbe = true;
		assertTrue(farbe == true);
	}
	/**
	 * Kontrolle, ob einem Spieler erfolgreich eine KI der Elementklasse KI zugewiesen wurde und diese eine Instanz der Klasse Spieler ist.
	 * Dieser Test wird später vollendet, wenn eine KI implementiert wurde.
	 */
	@Ignore
	@Test
	public void kiSpieler(){
		Spieler KI = new Spieler("Kevin",FarbEnum.GELB,meinWürfel,gelbStart,"aggressiv");
		assertTrue(KI instanceof Spieler.KI);
	}

}
