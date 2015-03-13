package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;
import Basisklassen.*;


public class TestSpieler {
	static Spieler spieler[] = new Spieler[3];
	static Würfel meinWürfel = new Würfel();
	static Spielfigur figuren[] = new Spielfigur[4];
	static FarbEnum rot = FarbEnum.ROT;
	static FarbEnum blau = FarbEnum.BLAU;
	static FarbEnum grün = FarbEnum.GRÜN;
	static FarbEnum gelb = FarbEnum.GELB;

	@BeforeClass
	public static void erstelleSpieler(){
		
		spieler[0] = new Spieler("Anna",rot,meinWürfel);
		spieler[1] = new Spieler("Felix",blau,meinWürfel);
		spieler[2] = new Spieler("Alex",grün,meinWürfel);
	}
	
	@Before
	public void startAnzeige(){
		System.out.println("Teststart");
	}
	@After
	public void endAnzeige(){
		System.out.println("Testende");
		System.out.println("");
	}
	@Ignore
	@Test
	public void trivialTestAnzahlFiguren(){
		int anzFiguren=0;
		for (int i = 1; i <= 4; i++){
			if (spieler[0].getFiguren(i)!=null) anzFiguren++;
		}
		assertTrue(anzFiguren == 4);
	}
	@Ignore
	@Test(expected=Exception.class)
	public void gleicheNamen() {
		Spieler s4 = new Spieler("Alex",gelb,meinWürfel);
	}
	@Ignore
	@Test(expected=Exception.class)
	public void SpielerOverload() {
		Spieler s4 = new Spieler("Kevin",gelb,meinWürfel);
		Spieler overload = new Spieler("Bonus",rot,meinWürfel);
	}
	@Ignore
	@Test(expected=Exception.class)
	public void FarbOverload(){
		Spieler s4 = new Spieler("Kevin",rot,meinWürfel);
	}
	@Ignore
	@Test
	public void nächsterZug(){
		spieler[0].würfeln();
		spieler[0].kannIchZiehen();
		spieler[0].ziehen(1);
		assertFalse(spieler[0].getAmZug()==true);
	}
	@Ignore
	@Test
	public void kiSpieler(){
		Spieler KI = new Spieler("Kevin",gelb,meinWürfel,"aggressiv");
		assertTrue(KI.getBedienung() instanceof Spieler.KI);
	}

}
