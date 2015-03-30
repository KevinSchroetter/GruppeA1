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
		
		i.werfen(6);//Ralf zieht raus auf feld 1
		i.zugDurchführen("S1");
		i.werfen(5);//Ralf zieht auf Feld 6
		i.zugDurchführen("1");
		i.werfen(6);//Georg zieht raus auf Feld 11
		i.zugDurchführen("S1");
		i.werfen(1);//Georg zieht auf Feld 12
		i.zugDurchführen("11");
		
		//Peter versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		//Hanswurst versagt 3 mal
		i.werfen(1);
		i.werfen(1);
		i.werfen(1);
		
		i.werfen(5);//Ralf zieht auf Feld 11!
		i.zugDurchführen("6");
		
		i.werfen(6);//Georg kann ROT 1 schlagen
		i.zugDurchführen("S2"); //ROT 1 wurde geschlagen, BLAU 2 steht auf Feld 1
		i.werfen(2);//Georg zieht mit BLAU 2 auf Feld 12
		i.zugDurchführen("11");
		
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
		
		i.werfen(3);//Georg zieht mit BLAU 2 auf 16
		i.zugDurchführen("13");
		
		//Peter versagt 3 mal
		i.werfen(2);
		i.werfen(2);
		i.werfen(2);
		
		//Hanswurst versagt 3 mal
		i.werfen(2);
		i.werfen(2);
		i.werfen(2);
		
		i.werfen(4);//Ralf versagt 1 mal und darf dann Spawnen
		i.werfen(6);
		i.zugDurchführen("S1");//Ralf spawnt eine Figur
		i.werfen(6);
		i.zugDurchführen("1");//Figur ROT 1 zieht auf Feld 7
		i.werfen(5);
		i.zugDurchführen("7");//Ralf killt mit ROT 1 eine Figur BLAU 1 von Blau
		
		i.ausgabeFiguren();//Georg sieht sich seine Figur Positionen an
		i.werfen(6);
		i.zugDurchführen("16");//Georg läuft mit BLAU 2 auf Feld 22
		i.werfen(6);
		i.zugDurchführen("22");//Georg läuft mit BLAU 2 auf Feld 28;
		i.werfen(6);
		i.zugDurchführen("28");//Georg läuft mit BLAU 2 auf Feld 34;
		i.werfen(6);
		i.zugDurchführen("34");//Georg läuft mit BLAU 2 auf Feld 40;
		i.werfen(6);
		i.zugDurchführen("40");//Georg läuft mit BLAU 2 auf Feld 6;
		i.werfen(6); //Georg sieht, dass eine Figur ins ZIEL kann
		i.zugDurchführen("6");
		i.werfen(6);
		i.zugDurchführen("12");
		i.zugDurchführen("13");
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