package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;

import Basisklassen.*;
import Spiel.*;
/**
 * 
 * @author Kevin Schrötter
 * @version 1.3
 * JUnit Testklasse zum Testen der Java Klasse "Spieler" im MADN Projekt
 * Hierbei werden verschiedene Tests abgedeckt, um die Funktionalität von "Spieler" zu gewärleisten.
 * 
 * Attribute
 * spieler[] - Array zum Speichern von bis zu 4 Spieler-Objekten
 * Farbenums rot, blau, grün, gelb - angelegt zum einfacheren zuweisen einer Farbe an einen Spieler 
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
	static Endfeld rotEnd[] = {b.getEndFelderRot(0),b.getEndFelderRot(1),b.getEndFelderRot(2),b.getEndFelderRot(3)};
	static Endfeld blauEnd[] = {b.getEndFelderBlau(0),b.getEndFelderBlau(1),b.getEndFelderBlau(2),b.getEndFelderBlau(3)};
	static Endfeld grünEnd[] = {b.getEndFelderGrün(0),b.getEndFelderGrün(1),b.getEndFelderGrün(2),b.getEndFelderGrün(3)};
	static Endfeld gelbEnd[] = {b.getEndFelderGelb(0),b.getEndFelderGelb(1),b.getEndFelderGelb(2),b.getEndFelderGelb(3)};
	/**
	 * Bevor die Tests starten, werden 3 Spieler angelegt.	
	 */
	@BeforeClass
	public static void erstelleSpieler(){
		
		Spiel s= new Spiel();
		
		spieler[0] = new Spieler("Anna",FarbEnum.ROT,rotStart,rotEnd, s);
		spieler[1] = new Spieler("Felix",FarbEnum.BLAU,blauStart,blauEnd,s);
		spieler[2] = new Spieler("Alex",FarbEnum.GRÜN,grünStart,grünEnd,s);
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
		Spiel s= new Spiel();
		Spieler s4 = new Spieler("Kevin",FarbEnum.GELB,gelbStart,gelbEnd,s);
		Spieler overload = new Spieler("Bonus",FarbEnum.ROT,rotStart,rotEnd,s);
	}
	/**
	 * Ähnlich wie der Test zum Spielernamen. Hierbei wird jedoch die Farbe überprüft.
	 */
	@Test(expected=Exception.class)
	public void FarbOverload(){
		Spiel s= new Spiel();
		Spieler s4 = new Spieler("Kevin",FarbEnum.ROT,rotStart,rotEnd,s);
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
	 */
	@Test
	public void kiSpieler(){
		Spiel s= new Spiel();
		Spieler ki = new Spieler("Kevin",FarbEnum.GELB,gelbStart,gelbEnd,"aggressiv",s );
		assertTrue(ki.getBedienung() instanceof KI);
	}
	/**
	 * Trivial-Test nach Änderung am Attribut "meinWürfel". Wurde ein Würfel korrekt angelegt?
	 */
	@Test
	public void würfelTrivial(){
		int erg=spieler[0].getMeinWürfel().werfen();
		assertTrue(erg==1|erg==2|erg==3|erg==4|erg==5|erg==6);
		assertFalse(erg<1 | erg>6);
	}
	@Test
	public void testSpawn(){
		assertTrue(spieler[0].alleAufSpawn());
	}
	

}
