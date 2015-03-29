package SpielTestKlasse;
import Basisklassen.*;
import Hilfsklassen.FigurKannNichtZiehenException;
import Spiel.Spiel;

public class SpielTest {
	public static void main(String[]args){
		
		Spiel a = new Spiel();
		a.spielerHinzufügen("Ralf", FarbEnum.ROT, null);
		a.spielerHinzufügen("Peter", FarbEnum.GRÜN, null);
		a.spielerHinzufügen("Georg", FarbEnum.GELB, null);
		a.startSpiel();
		a.würfeln(6);
		a.wähleFigur("S1");
		a.würfeln(6);
		a.wähleFigur("1");
		a.würfeln(6);
		a.wähleFigur("7");
		a.würfeln(6);
		a.wähleFigur("13");
		a.würfeln(6);
		a.wähleFigur("19");
		a.würfeln(6);
		a.wähleFigur("25");
		System.out.println(a.spielbrett.getAlleStandardFelder()[24].getFigur());
		a.würfeln(6);
		a.wähleFigur("31");
		a.würfeln(6);
		System.out.println(a.spielbrett.getAlleStandardFelder()[36].getFigur().getKannInsZiel());
		a.wähleFigur("37");
			

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
