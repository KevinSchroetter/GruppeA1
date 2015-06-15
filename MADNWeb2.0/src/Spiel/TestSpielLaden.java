package Spiel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

import Basisklassen.Spieler;

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
		try{
		System.out.println("--------CSV--------");
		BufferedReader br = (BufferedReader) dCSV.openFile("meep.csv", 1);
		SpielBean ladeMich1 = (SpielBean) dCSV.spielLaden(br);
			for(Spieler s : ladeMich1.getSpieler())
			{
				System.out.println(s);
			}

	ladeMich1.ausgabeSpielerListe();
	System.out.println(ladeMich1.getIstAmZug());
	ladeMich1.ausgabeFiguren();	
		ladeMich1.werfen(6);
		ladeMich1.zugDurchfuehrenKI();
		ladeMich1.werfen(6);
		ladeMich1.zugDurchfuehrenKI();
		ladeMich1.ausgabeFiguren();
		System.out.println("Spiel erfolgreich geladen und getestet");
		ladeMich1 = null;
		dCSV.closeFile(br);	
		}
		catch(Exception e){
			System.out.println("Im Catchblock");
			e.printStackTrace();
			return;
		}

		
	}

}
