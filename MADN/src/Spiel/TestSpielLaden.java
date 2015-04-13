package Spiel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

public class TestSpielLaden {

	public static void main(String[] args) {
		
		
		DatenzugriffSerialisiert ds = new DatenzugriffSerialisiert();
		DatenzugriffCSV dCSV = new DatenzugriffCSV();
		
		FileInputStream ichLadeEtwas = (FileInputStream) ds.openFile("savegame.ser", 1);
		Spiel ladeMich = (Spiel) ds.spielLaden(ichLadeEtwas);
		ds.closeFile(ichLadeEtwas);
		

		
		ladeMich.ausgabeSpielerListe();
		ladeMich.ausgabeFiguren();
		ladeMich.ausgabeFigurenAufStartfeld();
		
		ladeMich = null;
		System.out.println("--------CSV--------");
		
		BufferedReader br = (BufferedReader) dCSV.openFile("tutCSVDenn.csv", 1);
		ladeMich = (Spiel) dCSV.spielLaden(br);
		dCSV.closeFile(br);

		
		ladeMich.ausgabeSpielerListe();
		ladeMich.ausgabeFiguren();
		ladeMich.ausgabeFigurenAufStartfeld();
		
	}

}
