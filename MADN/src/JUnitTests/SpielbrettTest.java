package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;

import Basisklassen.*;
import Einstellungen.FarbEnum;
/**
 * Test fuer das Spielbrett
 * @author Anna Rosa
 * @version 4.0
 * @since v1.0
 *
 */
public class SpielbrettTest {
	static Spielbrett s;
	@BeforeClass
	public static void erstelleSpielbrett() {
		s = new Spielbrett();
	}
	
	@Before
	public void vorTest(){
		System.out.println("Test beginnt.");
		System.out.println(s.toString());
	}

	@After
	public void nachTest(){
		System.out.println("Test beendet.");
	
	}
	
	@Test
	public void testStartFelder() {
		Startfeld s1=s.getAlleStartFelderEinerFarbe(FarbEnum.ROT)[1];
		Startfeld s2= s.getAlleStartFelderEinerFarbe(FarbEnum.ROT)[0];
		assertTrue(s1.getFarbe().equals(s2.getFarbe()));
	}
	
	@Test
	public void testStandardfelder(){
		Standardfeld s1=s.getStandardFelder(0);
		Standardfeld s2=s.getStandardFelder(15);
		Standardfeld s3=s.getStandardFelder(39);
		assertTrue(s1.getID().equals("1") & s2.getID().equals("16") & s3.getID().equals("40"));
	}
	
	@Test
	public void testEndfelder(){
		Endfeld s1=s.getAlleEndFelderEinerFarbe(FarbEnum.ROT)[0];
		Endfeld s2=s.getAlleEndFelderEinerFarbe(FarbEnum.BLAU)[3];
		assertTrue(String.valueOf(s1.getID()).equals("E1") & String.valueOf(s2.getID()).equals("E4"));
	}
	
	@Test
	public void testFarben(){
		FarbEnum f1= s.getAlleStartFelderEinerFarbe(FarbEnum.ROT)[2].getFarbe();
		FarbEnum f2= s.getAlleEndFelderEinerFarbe(FarbEnum.BLAU)[0].getFarbe();
		assertTrue(f1.equals(FarbEnum.ROT)& f2.equals(FarbEnum.BLAU));
	}

}
