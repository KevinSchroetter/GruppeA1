package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;

import Basisklassen.*;
import Spiel.*;
/**
 * 
 * @author Kevin Schroetter
 * @version 3.0
 * JUnit Testklasse zum Testen der Java Klasse "Spieler" im MADN Projekt
 * Hierbei werden verschiedene Tests abgedeckt, um die Funktionalitaet von "Spieler" zu gewaerleisten.
 * 
 * Attribute
 * spieler[] - Array zum Speichern von bis zu 4 Spieler-Objekten
 * Farbenums rot, blau, gruen, gelb - angelegt zum einfacheren zuweisen einer Farbe an einen Spieler 
 * 
 */

public class TestSpieler {
	static Spieler spieler[] = new Spieler[3];
	static Wuerfel meinWuerfel = new Wuerfel();
	static Spielbrett b = new Spielbrett();
	static Spielfigur figuren[] = new Spielfigur[4];
	static Startfeld rotStart[] = {b.getStartFelderRot(0),b.getStartFelderRot(1),b.getStartFelderRot(2),b.getStartFelderRot(3)};
	static Startfeld blauStart[] = {b.getStartFelderBlau(0),b.getStartFelderBlau(1),b.getStartFelderBlau(2),b.getStartFelderBlau(3)};
	static Startfeld gruenStart[] = {b.getStartFelderGruen(0),b.getStartFelderGruen(1),b.getStartFelderGruen(2),b.getStartFelderGruen(3)};
	static Startfeld gelbStart[] = {b.getStartFelderGelb(0),b.getStartFelderGelb(1),b.getStartFelderGelb(2),b.getStartFelderGelb(3)};
	static Endfeld rotEnd[] = {b.getEndFelderRot(0),b.getEndFelderRot(1),b.getEndFelderRot(2),b.getEndFelderRot(3)};
	static Endfeld blauEnd[] = {b.getEndFelderBlau(0),b.getEndFelderBlau(1),b.getEndFelderBlau(2),b.getEndFelderBlau(3)};
	static Endfeld gruenEnd[] = {b.getEndFelderGruen(0),b.getEndFelderGruen(1),b.getEndFelderGruen(2),b.getEndFelderGruen(3)};
	static Endfeld gelbEnd[] = {b.getEndFelderGelb(0),b.getEndFelderGelb(1),b.getEndFelderGelb(2),b.getEndFelderGelb(3)};
	/**
	 * Bevor die Tests starten, werden 3 Spieler angelegt.	
	 */
	@BeforeClass
	public static void erstelleSpieler(){
		
		Spiel s= new Spiel();
		
		spieler[0] = new Spieler("Anna",FarbEnum.ROT,rotStart,rotEnd, s);
		spieler[1] = new Spieler("Felix",FarbEnum.BLAU,blauStart,blauEnd,s);
		spieler[2] = new Spieler("Alex",FarbEnum.GRUEN,gruenStart,gruenEnd,s);
	}
	
	/**
	 * Vor jedem Test wird eine Ausgabe auf der Konsole dargestellt. Sie signalisiert den Start jedes Testfalls.
	 */
	@Before
	public void startAnzeige(){
		System.out.println("Teststart");
	}
	/**
	 * Fuer jedes Ende eines Tests wird ebenfalls eine Ausgabe dargestellt.
	 */
	@After
	public void endAnzeige(){
		System.out.println("Testende");
		System.out.println("");
	}
	/**
	 * Erster Test. Hierbei wird ueberprueft, ob ein Spieler tatsaechlich im Besitz von 4 Spielfiguren ist
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
	 * Kontrolle, das nicht mehr als 4 Spieler erstellt werden koennen.
	 * Dies soll wird in "Spieler" ueber ein statisches Attribut zur Spieleranzahl geregelt.
	 * Im Test wird bewusst ein zusaetzlicher Spieler angelegt, der die maximale Anzahl uebersteigt. 
	 * Erwartet wird eine Exception.
	 */

	@Test(expected=Exception.class)
	public void SpielerOverload() {
		Spiel s= new Spiel();
		Spieler s4 = new Spieler("Kevin",FarbEnum.GELB,gelbStart,gelbEnd,s);
		System.out.println(s4);
		Spieler overload = new Spieler("Bonus",FarbEnum.ROT,rotStart,rotEnd,s);
		System.out.println(overload);
	}
	/**
	 * Aehnlich wie der Test zum Spielernamen. Hierbei wird jedoch die Farbe ueberprueft.
	 */
	@Test(expected=Exception.class)
	public void FarbOverload(){
		Spiel s= new Spiel();
		Spieler s4 = new Spieler("Kevin",FarbEnum.ROT,rotStart,rotEnd,s);
		System.out.println(s4);
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
	 * Trivial-Test nach aenderung am Attribut "meinWuerfel". Wurde ein Wuerfel korrekt angelegt?
	 */
	@Test
	public void wuerfelTrivial(){
		int erg=spieler[0].getMeinWuerfel().werfen();
		assertTrue(erg==1|erg==2|erg==3|erg==4|erg==5|erg==6);
		assertFalse(erg<1 | erg>6);
	}
	@Test
	public void testSpawn(){
		assertTrue(spieler[0].alleAufSpawn());
	}
	

}
