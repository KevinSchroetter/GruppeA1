package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;
import Basisklassen.*;

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
		Startfeld s2= s.getStartFelderGrün(0);
		assertTrue( String.valueOf(s1.getID()).equals("S3")& String.valueOf(s2.getID()).equals("S1"));
	}
	
	@Test
	public void testStandardfelder(){
		Standardfeld s1=s.getStandardFelder(0);
		Standardfeld s2=s.getStandardFelder(15);
		Standardfeld s3=s.getStandardFelder(39);
		assertTrue(s1.getID()==1 & s2.getID()==16 & s3.getID()==40);
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
