package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import Basisklassen.*;
/**
 * Test fuer das Spielbrett
 * @author Anna Rosa
 * @version 3.0
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
		Startfeld s1=s.getStartFelderRot(2);
		Startfeld s2= s.getStartFelderGruen(0);
		assertTrue( String.valueOf(s1.getID()).equals("S3")& String.valueOf(s2.getID()).equals("S1"));
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
		Endfeld s1=s.getEndFelderRot(0);
		Endfeld s2=s.getEndFelderBlau(3);
		assertTrue(String.valueOf(s1.getID()).equals("E1") & String.valueOf(s2.getID()).equals("E4"));
	}
	
	@Test
	public void testFarben(){
		FarbEnum f1= s.getStartFelderRot(2).getFarbe();
		FarbEnum f2= s.getEndFelderBlau(0).getFarbe();
		assertTrue(f1.equals(FarbEnum.ROT)& f2.equals(FarbEnum.BLAU));
	}

}
