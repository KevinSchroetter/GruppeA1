package SpielTestKlasse;
import Basisklassen.*;

public class SpielTest {
	public static void main(String[]args){
		iBediener b= new iBediener();
		b.spielerHinzufügen("Kevin", FarbEnum.BLAU, null);
		b.spielereHinzufügen("Alex", FarbEnum.GELB,null);
		b.startSpiel();
		b.würfeln();
		b.figurWählen("S1");
		b.figurWählen("11");
		b.würfeln();
		
		
	}

}
