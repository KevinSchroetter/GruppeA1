package Spiel;

import java.io.FileInputStream;

public class TestSpielLaden {

	public static void main(String[] args) {
		
		
		DatenzugriffSerialisiert ds = new DatenzugriffSerialisiert();
		
		FileInputStream ichLadeEtwas = (FileInputStream) ds.openFile("savegame.ser", 1);
		Spiel ladeMich = (Spiel) ds.spielLaden(ichLadeEtwas);
		ds.closeFile(ichLadeEtwas);
		
		ladeMich.ausgabeSpielerListe();
		ladeMich.ausgabeFiguren();
		ladeMich.ausgabeFigurenAufStartfeld();

	}

}
