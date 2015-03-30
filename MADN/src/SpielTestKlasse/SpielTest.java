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
		i.werfen(2);
		i.werfen(1);
		i.werfen(4);
		
		i.werfen(2);
		i.werfen(6);
		i.werfen(2);
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
