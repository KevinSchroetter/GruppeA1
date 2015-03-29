package SpielTestKlasse;
import Basisklassen.*;
import Hilfsklassen.FigurKannNichtZiehenException;
import Spiel.Spiel;
import Spiel.iBediener;

public class SpielTest {
public static void main(String[]args){
		
		iBediener i = new Spiel();
		i.neuerSpieler("Ralf", 1, 0);
		i.neuerSpieler("Georg", 2, 0);
		i.neuerSpieler("Peter",3,0);
		
		i.starteSpiel();
		
		
		
	/*	Spiel a = new Spiel();
		a.spielerHinzufügen("Ralf", 1, 0);
		a.spielerHinzufügen("Peter", 2, 0);
		a.spielerHinzufügen("Georg", 3, 0);
		a.startSpiel();
		
		//ROT würfelt und zieht von Startfelder aus Spawnfeld
		a.würfeln(2);
		a.würfeln(2);
		a.würfeln(6);
		a.wähleFigur("S1");
		//ROT Würfelt und Zieht von Feld 1 auf Feld 2, SPielerwechsel zu GRÜN
		a.würfeln(1);
		a.wähleFigur("1");
		a.nächsterSpieler();
		a.nächsterSpieler();
		a.würfeln(2);
		a.wähleFigur("2");
		
		//GRÜN würfelt und versagt 3 mal, Spielerwechsel zu GELB
		a.würfeln(1);
		a.würfeln(2);
		a.würfeln(3);
		System.out.println("----------------------------");
		//GELB würfelt und versagt 3 mal, Spielerwechsel zu ROT
		a.würfeln(1);
		a.würfeln(2);
		a.würfeln(3);
		
		a.würfeln(61);
		a.würfeln(6);
		//a.würfeln(6);
		

		
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
			}*/
			
			

		
		
		
	}

		public void starteSpiel() {
			// TODO Auto-generated method stub
			
		}

		public void werfen(int zahl) {
			// TODO Auto-generated method stub
			
		}

		public void ausgabeFiguren() {
			// TODO Auto-generated method stub
			
		}

		public void ausgabeZugFiguren() {
			// TODO Auto-generated method stub
			
		}

		public void ausgabeFigurenImZiel() {
			// TODO Auto-generated method stub
			
		}

		public void ausgabeFigurenAufStartfeld() {
			// TODO Auto-generated method stub
			
		}

		public void ausgabeSpielerListe() {
			// TODO Auto-generated method stub
			
		}

		public boolean zugDurchführen(String ID) {
			// TODO Auto-generated method stub
			return false;
		}

		public void neuerSpieler(String name, int FarbID, int verhaltenID) {
			// TODO Auto-generated method stub
			
		}

}
