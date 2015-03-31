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
		i.neuerSpieler("Hanswurst", 4, 0); //starteSpiel() wird automatisch aufgerufen, da 4 Spieler hinzugefügt wurden.
		
		i.werfen(6);			//Ralf kann mit 4 Figuren spawnen
		i.zugDurchführen("S1"); //Ralf spawnt mit Figur ROT 1 von Feld S1 auf Feld 1
		i.werfen(5);			//Ralf kann mit 1 Figur laufen
		i.zugDurchführen("1");	//Ralf läuft mit Figur ROT 1 von Feld 1 auf Feld 6: ZUG BEENDET
		
		i.werfen(6);			//Georg kann mit 4 Figuren spawnen
		i.zugDurchführen("S1"); //Georg spawnt mit Figur BLAU 1 von Feld S1 auf Feld 11
		i.werfen(1);			//Georg kann mit 1 Figur laufen
		i.zugDurchführen("11"); //Georg läuft mit Figur BLAU 1 von Feld 11 auf Feld 12
		
		//Peter versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		//Hanswurst versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		i.werfen(5);			//Ralf kann mit 1 Figur laufen
		i.zugDurchführen("6");  //Ralf läuft mit Fogur ROT 1 von Feld 6 auf Feld 11: ZUG BEENDET
		
		i.werfen(6);			//Georg kann mit 1 Figur laufen und mit 3 Figuren spawnen + schlafen!
		i.zugDurchführen("S2"); //Georg schlägt Figur ROT 1 auf Feld 11 mit Figur BLAU 2
		i.werfen(2);			//Georg kann mit 2 Figuren laufen.
		i.zugDurchführen("11"); //Georg Zieht mit Figur BLAU 2 von Feld 11 auf Feld 13: ZUG BEENDET
		
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
		i.zugDurchführen("13"); //Georg läuft mit Figur BLAU 2 von Feld 13 auf Feld 16: ZUG BEENDET
		
		//Peter versagt 3 mal
		i.werfen(2);
		i.werfen(2);
		i.werfen(2);
		
		//Hanswurst versagt 3 mal
		i.werfen(2);
		i.werfen(2);
		i.werfen(2);
		
		i.werfen(4);			//Ralf versagt 1 mal und darf noch mal würfeln, da alle Figuren auf den Startgeldern stehen
		i.werfen(6);			//Ralf darf mit 4 Figuren spawnen
		i.zugDurchführen("S1"); //Ralf spawnt mit Figur ROT 1 von Feld S1 auf Feld 1
		i.werfen(6);			//Ralf darf mit 1 Figur laufen
		i.zugDurchführen("1");  //Ralf läuft mit Figur ROT 1 von Feld 1 auf Feld 7
		i.werfen(5);			//Ralf darf mit 1 Figur laufen und schlagen
		i.zugDurchführen("7");  //Ralf läuft mit Figur Rot 1 von Feld 7 auf Feld 12 und schlägt Figur BLAU 1: ZUG BEENDET
		
		//Georgs Algorithmus, um Figur BLAU 2 zu den Endfeldern zu bringen
		i.ausgabeFiguren();		//Georg sieht sich seine Figur Positionen an
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchführen("16"); //Georg läuft mit Figur BLAU 2 von Feld 16 auf Feld 22
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchführen("22"); //Georg läuft mit Figur BLAU 2 von Feld 22 auf Feld 28;
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchführen("28"); //Georg läuft mit Figur BLAU 2 von Feld 28 auf Feld 34;
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchführen("34"); //Georg läuft mit Figur BLAU 2 von Feld 34 auf Feld 40;
		i.werfen(6);			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 Figur laufen
		i.zugDurchführen("40"); //Georg läuft mit Figur BLAU 2 von Feld 40 auf Feld 6;
		i.werfen(6); 			//Georg darf mit 3 Figuren spawnen und mit Figur BLAU 2 auf die Endfelder ziehen 
		i.zugDurchführen("6");  //Georg bringt die Figur BLAU 2 von Feld 6 ins Endfeld E2
		
		//Georgs Algorithmus, um Figur BLAU 1 zu den Endfeldern zu bringen
		i.werfen(6);
		i.zugDurchführen("S1");
		i.werfen(6);
		i.zugDurchführen("11");
		i.werfen(6);
		i.zugDurchführen("17");
		i.werfen(6);
		i.zugDurchführen("23");
		i.werfen(6);
		i.zugDurchführen("29");
		i.werfen(6);
		i.zugDurchführen("35");
		i.werfen(6);
		i.zugDurchführen("1");
		
		//Georg zieht mit Figur BLAU 1 auf E1
		i.werfen(4);			//Georg darf mit 2 Figuren spawnen und mit Figur BLAU 1 auf die Endfelder ziehen
		i.zugDurchführen("7");  //Georg bringt die Figur BLAU 1 von Feld 7 ins Endfeld E1
		
		//Peter versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		//Hanswurst versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		i.werfen(1); 			//Ralf darf mit Figur ROT 1 laufen
		i.zugDurchführen("12"); //Ralf läuft mit Figur ROT 1 von Feld 12 auf Feld 13
		
		//Georg Würfelt eine 2, kann aber nur mit BLAU 2 laufen, da Figur BLAU 1 auf E1 steht, und die Figur BLAU 2 auf E2 nicht übersprungen werden kann!
		i.werfen(2);			//Georg darf mit Figur BLAU 2 laufen
		i.zugDurchführen("E2"); //Figur BLAU 2 kommt in die ZIELPOSITION E4
		
		//Die anderen Spieler würfeln nun solange, bis Georg wieder dran ist
		i.werfen(1);
		i.werfen(2);
		i.werfen(3);
		i.werfen(4);
		i.werfen(5);
		i.werfen(1);
		i.werfen(1);
		i.zugDurchführen("13"); //Ralfs Figur ROT 1 läuft von Feld 13 auf Feld 14
		
		//Georg ist wieder am Zug und bringt Figur BLAU 1 auf die Endposition E3
		i.werfen(2);			//Georg darf mit Figur BLAU 1 aufdie Endfelder ziehen
		i.zugDurchführen("E1"); //Figur BLAU 1 kommt in die ZIELPOSITION E3
		
		//Die anderen Spieler würfeln nun solange, bis Georg wieder dran ist
		i.werfen(1);
		i.werfen(2);
		i.werfen(3);
		i.werfen(4);
		i.werfen(5);
		i.werfen(1);
		
		i.werfen(1);			//Ralf darf mit Figur ROT 1 laufen
		i.zugDurchführen("14"); //Ralf läuft mit Figur ROT 1 von Feld 13 auf Feld 14
		
		//Georgs Algorithmus, um Figur BLAU 3 zu den Endfeldern zu bringen
		i.werfen(6);
		i.zugDurchführen("S3");
		i.werfen(6);
		i.zugDurchführen("11");
		i.werfen(6);
		i.zugDurchführen("17");
		i.werfen(6);
		i.zugDurchführen("23");
		i.werfen(6);
		i.zugDurchführen("29");
		i.werfen(6);
		i.zugDurchführen("35");
		i.werfen(6);
		i.zugDurchführen("1");
		i.werfen(5);			//Georg darf mit Figur BLAU 3 auf die Endfelder ziehen
		i.zugDurchführen("7");  //Figur BLAU 3 kommt in die ZIELPOSITION E2
		
		//Die anderen Spieler würfeln nun solange, bis Georg wieder dran ist
		i.werfen(1);
		i.werfen(2);
		i.werfen(3);
		i.werfen(4);
		i.werfen(5);
		i.werfen(1);
		i.werfen(1);			//Ralf darf mit Figur ROT 1 laufen
		i.zugDurchführen("15"); //Ralf läuft mit Figur ROT 1 von Feld 15 auf Feld 16
		
		//Georgs Algorithmus, um Figur BLAU 4 zu den Endfeldern zu bringen
		i.werfen(6);
		i.zugDurchführen("S4");
		i.werfen(6);
		i.zugDurchführen("11");
		i.werfen(6);
		i.zugDurchführen("17");
		i.werfen(6);
		i.zugDurchführen("23");
		i.werfen(6);
		i.zugDurchführen("29");
		i.werfen(6);
		i.zugDurchführen("35");
		i.werfen(6);
		i.zugDurchführen("1");
		i.werfen(4);			//Georg darf Figur BLAU 4 in die Endfelder bringen
		i.zugDurchführen("7");	//Figur BLAU 4 kommt in die ZIELPOSITION E1
		i.werfen(2);			//Würfeln nicht möglich, da Spiel beendet
		i.zugDurchführen("1");	//Ziehen nicht möglich, da Spiel beendet
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

		public boolean zugDurchführen(String ID) {
			// TODO Auto-generated method stub
			return false;
		}

		public void neuerSpieler(String name, int FarbID, int verhaltenID) {
			// TODO Auto-generated method stub
			
		}

}