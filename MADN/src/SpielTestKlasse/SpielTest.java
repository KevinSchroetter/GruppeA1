package SpielTestKlasse;

import Spiel.Spiel;
import Spiel.iBediener;
/**
 * Eine Testklasse, die einen kompletten Spielverlauf des MADN Projekt simulisiert
 * @author Kevin Schroetter
 * @version 3.0
 * @since v2.3
 */
public class SpielTest {
	public static void main(String[]args){
		
		iBediener i = new Spiel();
		i.neuerSpieler("Ralf", 1, 0);
		i.neuerSpieler("Georg", 2, 0);
		i.neuerSpieler("Peter",3,0);
		i.neuerSpieler("Hanswurst", 4, 0); //starteSpiel() wird automatisch aufgerufen, da 4 Spieler hinzugefuegt wurden.
		
		i.werfen(6);			//Ralf kann mit 4 Figuren spawnen
		i.zugDurchfuehren("S1"); //Ralf spawnt mit Figur ROT 1 von Feld S1 auf Feld 1
		i.werfen(5);			//Ralf kann mit 1 Figur laufen
		i.zugDurchfuehren("1");	//Ralf laeuft mit Figur ROT 1 von Feld 1 auf Feld 6: ZUG BEENDET
		
		i.werfen(6);			//Georg kann mit 4 Figuren spawnen
		i.zugDurchfuehren("S1"); //Georg spawnt mit Figur BLAU 1 von Feld S1 auf Feld 11
		i.werfen(1);			//Georg kann mit 1 Figur laufen
		i.zugDurchfuehren("11"); //Georg laeuft mit Figur BLAU 1 von Feld 11 auf Feld 12
		
		//Peter versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		//Hanswurst versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		i.werfen(5);			//Ralf kann mit 1 Figur laufen
		i.zugDurchfuehren("6");  //Ralf laeuft mit Fogur ROT 1 von Feld 6 auf Feld 11: ZUG BEENDET
		
		i.werfen(6);			//Georg kann mit 1 Figur laufen und mit 3 Figuren spawnen + schlafen!
		i.zugDurchfuehren("S2"); //Georg schlaegt Figur ROT 1 auf Feld 11 mit Figur BLAU 2
		i.werfen(2);			//Georg kann mit 2 Figuren laufen.
		i.zugDurchfuehren("11"); //Georg Zieht mit Figur BLAU 2 von Feld 11 auf Feld 13: ZUG BEENDET
		
		//Peter versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		//Hanswurst versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		//Ralf versagt 3 mal
		i.werfen(2);
		i.werfen(2);
		i.werfen(2);
		
		i.werfen(3);			//Georg kann mit 2 Figuren laufen
		i.zugDurchfuehren("13"); //Georg laeuft mit Figur BLAU 2 von Feld 13 auf Feld 16: ZUG BEENDET
		
		//Peter versagt 3 mal
		i.werfen(2);
		i.werfen(2);
		i.werfen(2);
		
		//Hanswurst versagt 3 mal
		i.werfen(2);
		i.werfen(2);
		i.werfen(2);
		
		i.werfen(4);			//Ralf versagt 1 mal und darf noch mal wuerfeln, da alle Figuren auf den Startgeldern stehen
		i.werfen(6);			//Ralf darf mit 4 Figuren spawnen
		i.zugDurchfuehren("S1"); //Ralf spawnt mit Figur ROT 1 von Feld S1 auf Feld 1
		i.werfen(6);			//Ralf darf mit 1 Figur laufen
		i.zugDurchfuehren("1");  //Ralf laeuft mit Figur ROT 1 von Feld 1 auf Feld 7
		i.werfen(5);			//Ralf darf mit 1 Figur laufen und schlagen
		i.zugDurchfuehren("7");  //Ralf laeuft mit Figur Rot 1 von Feld 7 auf Feld 12 und schlaegt Figur BLAU 1: ZUG BEENDET
		
		//Georgs Algorithmus, um Figur BLAU 2 zu den Endfeldern zu bringen
		i.ausgabeFiguren();		//Georg sieht sich seine Figur Positionen an
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchfuehren("16"); //Georg laeuft mit Figur BLAU 2 von Feld 16 auf Feld 22
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchfuehren("22"); //Georg laeuft mit Figur BLAU 2 von Feld 22 auf Feld 28;
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchfuehren("28"); //Georg laeuft mit Figur BLAU 2 von Feld 28 auf Feld 34;
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchfuehren("34"); //Georg laeuft mit Figur BLAU 2 von Feld 34 auf Feld 40;
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchfuehren("40"); //Georg laeuft mit Figur BLAU 2 von Feld 40 auf Feld 6;
		i.werfen(6); 			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 auf die Endfelder ziehen 
		i.zugDurchfuehren("6");  //Georg bringt die Figur BLAU 2 von Feld 6 ins Endfeld E2
		
		//Georgs Algorithmus, um Figur BLAU 1 zu den Endfeldern zu bringen
		i.werfen(6);
		i.zugDurchfuehren("S1");
		i.werfen(6);
		i.zugDurchfuehren("11");
		i.werfen(6);
		i.zugDurchfuehren("17");
		i.werfen(6);
		i.zugDurchfuehren("23");
		i.werfen(6);
		i.zugDurchfuehren("29");
		i.werfen(6);
		i.zugDurchfuehren("35");
		i.werfen(6);
		i.zugDurchfuehren("1");
		
		//Georg zieht mit Figur BLAU 1 auf E1
		i.werfen(4);			//Georg darf mit 2 Figuren spawnen und mit Figur BLAU 1 auf die Endfelder ziehen
		i.zugDurchfuehren("7");  //Georg bringt die Figur BLAU 1 von Feld 7 ins Endfeld E1
		
		//Peter versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		//Hanswurst versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		i.werfen(1); 			//Ralf darf mit Figur ROT 1 laufen
		i.zugDurchfuehren("12"); //Ralf laeuft mit Figur ROT 1 von Feld 12 auf Feld 13
		
		//Georg Wuerfelt eine 2, kann aber nur mit BLAU 2 laufen, da Figur BLAU 1 auf E1 steht, und die Figur BLAU 2 auf E2 nicht uebersprungen werden kann!
		i.werfen(2);			//Georg darf mit Figur BLAU 2 laufen
		i.zugDurchfuehren("E2"); //Figur BLAU 2 kommt in die ZIELPOSITION E4
		
		//Die anderen Spieler wuerfeln nun solange, bis Georg wieder dran ist
		i.werfen(1);
		i.werfen(2);
		i.werfen(3);
		i.werfen(4);
		i.werfen(5);
		i.werfen(1);
		i.werfen(1);
		i.zugDurchfuehren("13"); //Ralfs Figur ROT 1 laeuft von Feld 13 auf Feld 14
		
		//Georg ist wieder am Zug und bringt Figur BLAU 1 auf die Endposition E3
		i.werfen(2);			//Georg darf mit Figur BLAU 1 aufdie Endfelder ziehen
		i.zugDurchfuehren("E1"); //Figur BLAU 1 kommt in die ZIELPOSITION E3
		
		//Die anderen Spieler wuerfeln nun solange, bis Georg wieder dran ist
		i.werfen(1);
		i.werfen(2);
		i.werfen(3);
		i.werfen(4);
		i.werfen(5);
		i.werfen(1);
		
		i.werfen(1);			//Ralf darf mit Figur ROT 1 laufen
		i.zugDurchfuehren("14"); //Ralf laeuft mit Figur ROT 1 von Feld 13 auf Feld 14
		
		//Georgs Algorithmus, um Figur BLAU 3 zu den Endfeldern zu bringen
		i.werfen(6);
		i.zugDurchfuehren("S3");
		i.werfen(6);
		i.zugDurchfuehren("11");
		i.werfen(6);
		i.zugDurchfuehren("17");
		i.werfen(6);
		i.zugDurchfuehren("23");
		i.werfen(6);
		i.zugDurchfuehren("29");
		i.werfen(6);
		i.zugDurchfuehren("35");
		i.werfen(6);
		i.zugDurchfuehren("1");
		i.werfen(5);			//Georg darf mit Figur BLAU 3 auf die Endfelder ziehen
		i.zugDurchfuehren("7");  //Figur BLAU 3 kommt in die ZIELPOSITION E2
		
		//Die anderen Spieler wuerfeln nun solange, bis Georg wieder dran ist
		i.werfen(1);
		i.werfen(2);
		i.werfen(3);
		i.werfen(4);
		i.werfen(5);
		i.werfen(1);
		i.werfen(1);			//Ralf darf mit Figur ROT 1 laufen
		i.zugDurchfuehren("15"); //Ralf laeuft mit Figur ROT 1 von Feld 15 auf Feld 16
		
		//Georgs Algorithmus, um Figur BLAU 4 zu den Endfeldern zu bringen
		i.werfen(6);
		i.zugDurchfuehren("S4");
		i.werfen(6);
		i.zugDurchfuehren("11");
		i.werfen(6);
		i.zugDurchfuehren("17");
		i.werfen(6);
		i.zugDurchfuehren("23");
		i.werfen(6);
		i.zugDurchfuehren("29");
		i.werfen(6);
		i.zugDurchfuehren("35");
		i.werfen(6);
		i.zugDurchfuehren("1");
		i.werfen(4);			//Georg darf Figur BLAU 4 in die Endfelder bringen
		i.zugDurchfuehren("7");	//Figur BLAU 4 kommt in die ZIELPOSITION E1
		i.werfen(2);			//Wuerfeln nicht moeglich, da Spiel beendet
		i.zugDurchfuehren("1");	//Ziehen nicht moeglich, da Spiel beendet
		//GEORG HAT GEWONNEN ------------------------------ALLE FIGUREN AUF DEN ENDELDERN
		
		//#################################
		//#################################
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

		public boolean zugDurchfuehren(String ID) {
			// TODO Auto-generated method stub
			return false;
		}

		public void neuerSpieler(String name, int FarbID, int verhaltenID) {
			// TODO Auto-generated method stub
			
		}

}