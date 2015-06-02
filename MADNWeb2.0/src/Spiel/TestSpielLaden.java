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
		
		
		//Datenzugriffe anlegen und SpielBean laden, anschließend prüfen, ob Ausgabe äquivalent zu Ausgabe in Speichern
		iDatenzugriff ds = new DatenzugriffSerialisiert();
		iDatenzugriff dCSV = new DatenzugriffCSV();
		
		//Serialsiert Laden
		FileInputStream ichLadeEtwas = (FileInputStream) ds.openFile("savegame.ser", 1);
		SpielBean ladeMich = (SpielBean) ds.spielLaden(ichLadeEtwas);
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
		ladeMich = (SpielBean) dCSV.spielLaden(br);
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
