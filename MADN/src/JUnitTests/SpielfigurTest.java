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
 * @author      Alexander Brückner (Alexander.Brueckner@Student-Reutlingen-University.de)
 * @version     1                 
 * @since       2015-03-15          
 */

public class SpielfigurTest {

	static Spielfigur[] figurenRot;
	static Spielfigur[] figurenBlau;
	static Spielfigur[] figurenGrün;
	static Spielfigur[] figurenGelb;

	@BeforeClass
	public static void erstelleFiguren() {

		figurenRot = new Spielfigur[4];
		figurenBlau = new Spielfigur[4];
		figurenGrün = new Spielfigur[4];
		figurenGelb = new Spielfigur[4];

		for (int i = 0; i < figurenRot.length; i++) {
			figurenRot[i] = new Spielfigur(1);
			System.out.println(figurenRot[i]);
		}

		for (int i = 0; i < figurenBlau.length; i++) {
			figurenBlau[i] = new Spielfigur(2);
			System.out.println(figurenBlau[i]);
		}

		for (int i = 0; i < figurenGrün.length; i++) {
			figurenGrün[i] = new Spielfigur(3);
			System.out.println(figurenGrün[i]);
		}

	}

	@Before
	public void sayStart() {
		System.out.println("Test beginnt..");

	}

	@After
	public void sayEnd() {
		System.out.println("Test endet..");

	}

	@Test(expected = IllegalArgumentException.class)
	public void UngültigeFarbe() {
		figurenGelb[0] = new Spielfigur(0);
		System.out.println(figurenGelb[0]);
	}

	@Test
	public void gelbErstellen() {
		for (int i = 0; i < figurenGelb.length; i++) {
			figurenGelb[i] = new Spielfigur(4);
			System.out.println(figurenGelb[i]);
		}
	}

	@Test(expected = RuntimeException.class)
	public void zuVieleFiguren() {
		Spielfigur overflow = new Spielfigur(1);
		overflow = null;
	}
	
	
	@Test(expected = RuntimeException.class)
	public void fehlerhafterSpawn(){
		figurenGelb[0].spawn(null);
	}
	
	@Test
	public void spawnEineFigur(){
		Spielfeld feld;
		feld = new Standardfeld(1);
		figurenRot[0].spawn((Standardfeld)feld);
		System.out.println(figurenRot[0].toString());
		if(!figurenRot[0].getIstGespawnt()){
			fail();
		} 
	}
	

}
