package SpielTestKlasse;
import Basisklassen.*;
import Hilfsklassen.FigurKannNichtZiehenException;
import Spiel.Spiel;

public class SpielTest {
	public static void main(String[]args){
		/*iBediener b= new iBediener();
		b.spielerHinzufügen("Kevin", FarbEnum.BLAU, null);
		b.spielereHinzufügen("Alex", FarbEnum.GELB,null);
		b.startSpiel();
		b.würfeln();
		b.figurWählen("S1");
		b.figurWählen("11");
		b.würfeln();
		*/
			
			Spiel a = new Spiel();
			a.spielerHinzufügen("Ralf", FarbEnum.ROT, null);
			a.spielerHinzufügen("Peter", FarbEnum.GRÜN, null);
			a.spielerHinzufügen("Georg", FarbEnum.GELB, null);
			a.startSpiel();
			/*Spielfigur b = a.getIstAmZug().getFiguren(1);
			Spielfigur c = a.getIstAmZug().getFiguren(2);
			Spielfigur d = a.getIstAmZug().getFiguren(3);
			Spielfigur b2 = a.DebugGetSpieler(2).getFiguren(1);
			Spielfigur c2 = a.DebugGetSpieler(2).getFiguren(2);
			Spielfigur d2 = a.DebugGetSpieler(2).getFiguren(3);
			Spielfigur b3 = a.DebugGetSpieler(3).getFiguren(1);
			Spielfigur c3 = a.DebugGetSpieler(3).getFiguren(2);
			Spielfigur d3 = a.DebugGetSpieler(3).getFiguren(3);*/
			a.würfeln(4);
			a.würfeln(2);
			a.würfeln(6);
			//a.würfeln(2);
			a.wähleFigur("S1");
			a.würfeln(2);
			System.out.println("a");
			//a.wähleFigur("1");
			a.nächsterSpieler();
			a.nächsterSpieler();
			//a.nächsterSpieler();
			a.würfeln(2);
			a.würfeln(3);
			a.würfeln(4);
			a.würfeln(2);
			a.würfeln(2);
			a.würfeln(2);
			//a.wähleFigur("S1");
			/*ausgabe(a,6);
			wählen(a,"1");
			ausgabe(a,6);
			wählen(a,"7");
			ausgabe(a,2);
			wählen(a,"13");
			a.nächsterSpieler();
			System.out.println("----------------------------");
			System.out.println(a.getIstAmZug());
			a.würfeln(4);
			System.out.println(a.getIstAmZug());
	*/
			

		}
		public static void ausgabe(Spiel s, int augenzahl){
			try{
			System.out.println(s.getIstAmZug()+" ist am ZUG!");
		
			s.würfeln(augenzahl);
			System.out.println(s.getIstAmZug()+" würfelt eine "+s.getAugenzahl() +" und kann mit insgesamt "+s.alleZugFiguren().size()+" Figuren ziehen!");
			System.out.println("Am Zug ist nun: " + s.getIstAmZug());
			}
			catch(RuntimeException e){
				System.out.println(e);
			}
		}
		public static void wählen(Spiel s,String feld){
			boolean test = false;
			try{
			s.wähleFigur(feld);
			test = true;
			}
			catch (FigurKannNichtZiehenException e){
				System.out.println(e);
				
			}
			finally{
				
				
				}
			if(test == true){
				System.out.println("Zug mit "+s.getChosen());
				System.out.println("-----------------");
			}
			
			

		
		
		
	}

}
