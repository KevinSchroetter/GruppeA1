package Spiel;

import java.io.*;
/**
 *TestSpielSpeichern
 *Testet Serialisiertes/CSV-basiertes Speichern
 * 
 **/
public class TestSpielSpeichern {

	public static void main(String[] args) {


		//SpielBean anlegen und irgendwas machen
		SpielBean speicherMich = new SpielBean();
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
		PrintWriter bw = (PrintWriter) dCSV.openFile("meep.csv", 2);
		dCSV.spielSpeichern(speicherMich, bw);
		dCSV.closeFile(bw);
		//Serialsiert
		iDatenzugriff ds = new DatenzugriffSerialisiert();
		FileOutputStream closeMe = (FileOutputStream) ds.openFile("savegame.ser", 2);
		ds.spielSpeichern(speicherMich,closeMe);
		ds.closeFile(closeMe);
		
		


		
		


	}

}
