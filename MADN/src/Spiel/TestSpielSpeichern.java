package Spiel;

import java.io.*;
/**
 *TestSpielSpeichern
 *Testet Serialisiertes/CSV-basiertes Speichern
 * 
 **/
public class TestSpielSpeichern {

	public static void main(String[] args) {


		//Spiel anlegen und irgendwas machen
		Spiel speicherMich = new Spiel();
		speicherMich.neuerSpieler("Hans-Joachim", 1, 1);
		speicherMich.neuerSpieler("Detlef", 2, 2);
		
		speicherMich.starteSpiel();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(6);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(6);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(6);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.ausgabeFiguren();
		
		//CSV
		iDatenzugriff dCSV = new DatenzugriffCSV();
		BufferedWriter bw = (BufferedWriter) dCSV.openFile("tutCSVDenn.csv", 2);
		long before = System.nanoTime();
		dCSV.spielSpeichern(speicherMich, bw);
		long after = System.nanoTime();
		long executiontime = (after-before)/1000000;
		System.out.println("CSV nach "+executiontime+" ms beendet.");
		dCSV.closeFile(bw);
		//Serialsiert
		iDatenzugriff ds = new DatenzugriffSerialisiert();
		FileOutputStream closeMe = (FileOutputStream) ds.openFile("savegame.ser", 2);
		before = System.nanoTime();
		ds.spielSpeichern(speicherMich,closeMe);
		after = System.nanoTime();
		executiontime = (after-before)/1000000;
		System.out.println("Serialisiert nach "+executiontime+" ms beendet.");
		ds.closeFile(closeMe);
		
		


		
		


	}

}
