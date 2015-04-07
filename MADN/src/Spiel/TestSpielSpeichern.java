package Spiel;

import java.io.*;

public class TestSpielSpeichern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Serialisiert
		Spiel speicherMich = new Spiel();
		speicherMich.neuerSpieler("Hans-Joachim", 1, 1);
		speicherMich.neuerSpieler("Detlef", 2, 2);
		
		speicherMich.starteSpiel();
		speicherMich.werfen(6);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.werfen(6);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.werfen(6);
		speicherMich.zugDurchfuehrenKI();
		speicherMich.werfen(3);
		speicherMich.zugDurchfuehrenKI();

		DatenzugriffSerialisiert ds = new DatenzugriffSerialisiert();
		FileOutputStream closeMe = (FileOutputStream) ds.openFile("savegame.ser", 2);
		ds.spielSpeichern(speicherMich,closeMe);
		ds.closeFile(closeMe);
		

		
		


	}

}
