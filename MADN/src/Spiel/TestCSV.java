package Spiel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class TestCSV {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DatenzugriffCSV seeEssVee = new DatenzugriffCSV();
		Spiel spast = new Spiel();
		spast.neuerSpieler("Idiot", 1, 2);
		spast.neuerSpieler("Mongo", 2, 1);
		
		spast.starteSpiel();
		spast.werfen(6);
		spast.zugDurchfuehrenKI();
		spast.werfen(5);
		spast.zugDurchfuehrenKI();
		spast.werfen(6);
		spast.zugDurchfuehrenKI();
		spast.werfen(5);
		

		
		BufferedWriter bw = (BufferedWriter) seeEssVee.openFile("csv.csv", 1);
		seeEssVee.spielSpeichern(spast, bw);
		seeEssVee.closeFile(bw);
		
		BufferedReader br = (BufferedReader) seeEssVee.openFile("csv.csv",2);
		Object fuckme = seeEssVee.spielLaden(br);
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
