package Spiel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * 
 * TestSpielLaden - Testet serialisiertes/CSV-basiertes Laden
 *
 **/
public class TestSpielLaden {

	public static void main(String[] args) {
		
		
		//Datenzugriffe anlegen und Spiel laden, anschließend prüfen, ob Ausgabe äquivalent zu Ausgabe in Speichern
		iDatenzugriff ds = new DatenzugriffSerialisiert();
		iDatenzugriff dCSV = new DatenzugriffCSV();
		
		//Serialsiert Laden
		FileInputStream ichLadeEtwas = (FileInputStream) ds.openFile("savegame.ser", 1);
		Spiel ladeMich = (Spiel) ds.spielLaden(ichLadeEtwas);
		ds.closeFile(ichLadeEtwas);	
		ladeMich.ausgabeSpielerListe();
		ladeMich.ausgabeFiguren();	
		ladeMich.werfen(6);
		ladeMich.zugDurchfuehrenKI();
		ladeMich.werfen(6);
		ladeMich.zugDurchfuehrenKI();
		ladeMich.werfen(6);
		ladeMich.zugDurchfuehrenKI();
		ladeMich.werfen(6);
		ladeMich.zugDurchfuehrenKI();
		ladeMich.ausgabeFiguren();
		ladeMich = null;
		
		//CSV Laden
		System.out.println("--------CSV--------");
		BufferedReader br = (BufferedReader) dCSV.openFile("meep.csv", 1);
		long before = System.nanoTime();
		ladeMich = (Spiel) dCSV.spielLaden(br);
		long after = System.nanoTime();
		long time = (after-before) / 1000000;
		System.out.println("Laden nach "+time+" ms beendet");
		dCSV.closeFile(br);		
		ladeMich.ausgabeSpielerListe();
		ladeMich.ausgabeFiguren();	
		ladeMich.werfen(6);
		ladeMich.zugDurchfuehrenKI();
		ladeMich.werfen(6);
		ladeMich.zugDurchfuehrenKI();
		ladeMich.ausgabeFiguren();

		ladeMich = null;
		
	}

}
