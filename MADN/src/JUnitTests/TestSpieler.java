package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;

import Basisklassen.*;
import Einstellungen.FarbEnum;
import Spiel.*;
/**
 * 
 * @author Kevin Schroetter
 * @version 4.0
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
	static Startfeld rotStart[] = b.getAlleStartFelderEinerFarbe(FarbEnum.ROT);
	static Startfeld blauStart[] = b.getAlleStartFelderEinerFarbe(FarbEnum.BLAU);
	static Startfeld gruenStart[] = b.getAlleStartFelderEinerFarbe(FarbEnum.GRUEN);
	static Startfeld gelbStart[] = b.getAlleStartFelderEinerFarbe(FarbEnum.GELB);
	static Endfeld rotEnd[] = b.getAlleEndFelderEinerFarbe(FarbEnum.ROT);
	static Endfeld blauEnd[] = b.getAlleEndFelderEinerFarbe(FarbEnum.BLAU);
	static Endfeld gruenEnd[] = b.getAlleEndFelderEinerFarbe(FarbEnum.GRUEN);
	static Endfeld gelbEnd[] = b.getAlleEndFelderEinerFarbe(FarbEnum.GELB);
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
	 * Aehnlich wie der Test zum Spielernamen. Hierbei wird jedoch die Farbe ueberprueft.
	 * Herausgenommen, da ausgabeSpielerAmZug verändert wurde auf KI-Prüfung.
	 */
	@Test
	public void FarbOverload(){
		Spiel s= new Spiel();
		s.neuerSpieler("Kevin", 1, 0);
		s.neuerSpieler("Holgersson", 1, 0);
		s.starteSpiel();
		s.rollTheDice();
		s.rollTheDice();
		s.rollTheDice();
		//assertFalse(s.ausgabeSpielerAmZug().getName().equals("Holgersson"));
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
