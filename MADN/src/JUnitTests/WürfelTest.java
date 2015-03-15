package JUnitTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;
import Basisklassen.*;

public class WürfelTest {
	
	public static Würfel würfel;
	
	@BeforeClass
	public static void erstelleWürfel(){
		würfel=new Würfel();
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
			int erg=würfel.werfen();
			assertTrue(erg==1|erg==2|erg==3|erg==4|erg==5|erg==6);
			assertFalse(erg<1 | erg>6);
		
		
	}

}
