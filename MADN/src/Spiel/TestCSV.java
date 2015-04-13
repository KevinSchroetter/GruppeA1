package Spiel;

import java.io.BufferedWriter;

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
		

	}

}
