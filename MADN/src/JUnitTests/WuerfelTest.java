package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import Basisklassen.*;
/**
 * Test fuer den Wuerfel
 * @author Anna Rosa
 * @version 4.0
 *
 */
public class WuerfelTest {
	
	public static Wuerfel wuerfel;
	
	@BeforeClass
	public static void erstelleWuerfel(){
		wuerfel=new Wuerfel();
	}
	
	@Before
	public void ausgabeVorher(){
		System.out.println("Test gestartet");
	}
	
	@After 
	public void ausgabeNachher(){
		System.out.println("Test beendet");
	}
	
	
	@Test
	public void test() {
			int erg=wuerfel.werfen();
			assertTrue(erg==1|erg==2|erg==3|erg==4|erg==5|erg==6);
			assertFalse(erg<1 | erg>6);
		
		
	}

}
