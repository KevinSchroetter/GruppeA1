package Spiel;

import Basisklassen.*;
import Einstellungen.*;
import Hilfsklassen.SpielerLadenWrapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

public class DatenzugriffCSV implements iDatenzugriff {

	@Override
	public Object openFile(String path, int mode) {

		if (path == null || (mode <= 0 || mode > 2))
			throw new IllegalArgumentException(
					"Dateipfad ungültig! Oder Modus ungültig");
		else {

			if (mode == 1) {

				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(path));
					return bw;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (mode == 2) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(path));
					return br;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}

		}

		return null;
	}

	@Override
	public void spielSpeichern(Object saveme, Object stream) {
		// TODO Auto-generated method stub

		if (saveme == null || stream == null)
			throw new IllegalArgumentException("Strom oder Spiel ungültig!");
		if ((!(saveme instanceof Spiel))
				|| (!(stream instanceof BufferedWriter)))
			throw new IllegalArgumentException("Strom oder Spiel ungültig!");
		else {

			BufferedWriter saver = (BufferedWriter) stream;
			Spiel buf = (Spiel) saveme;
			Spieler[] gamer = new Spieler[4];
			String[] speicherMichIchBinFertig = new String[buf
					.getAnzahlSpieler()];
			String[] spielerNamen = new String[4];
			String[][] figurFeldIDs = new String[4][4];
			Spielfigur[] figurenBuffer = new Spielfigur[4];
			String[] binIchDran = new String[4];

			gamer = buf.getSpieler();
			for (int i = 0; i < gamer.length; i++) {
				if (gamer[i] == null)
					continue;
				spielerNamen[i] = gamer[i].getName();
			}

			for (int i = 0; i < buf.getAnzahlSpieler(); i++) {
				figurenBuffer = gamer[i].alleFiguren();
				for (int j = 0; j < 4; j++) {

					figurFeldIDs[i][j] = figurenBuffer[j].getMeinFeld()
							.toStringOhneFarbe();
				}

			}

			for (int i = 0; i < binIchDran.length; i++) {
				if (gamer[i] == null || !gamer[i].getAmZug())
					binIchDran[i] = "false";
				else
					binIchDran[i] = "true";
			}

			String verhalten = "mensch";
			for (int i = 0; i < speicherMichIchBinFertig.length; i++) {
				if (gamer[i].getBedienung() instanceof KI_Aggressiv) {
					verhalten = "aggressiv";
				} else if (gamer[i].getBedienung() instanceof KI_Defensiv) {
					verhalten = "defensiv";
					;
				} else if (gamer[i].getBedienung() == null) {
					verhalten = "mensch";
				}

				speicherMichIchBinFertig[i] = String.format(
						"%s,%s,%s,%s,%s,%s,%s,%s\n", spielerNamen[i],
						gamer[i].getFarbe(), verhalten, binIchDran[i],
						figurFeldIDs[i][0], figurFeldIDs[i][1],
						figurFeldIDs[i][2], figurFeldIDs[i][3]);
			}

			for (int i = 0; i < speicherMichIchBinFertig.length; i++) {
				try {
					saver.write(speicherMichIchBinFertig[i]);
				} catch (IOException e) {
					System.out.println("Fehler: " + e);
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	public Object spielLaden(Object stream) {
		// TODO Auto-generated method stub
		if (!(stream instanceof BufferedReader))
			throw new IllegalArgumentException("Strom fehlerhaft/Ungültig");
		else {

			BufferedReader bw = (BufferedReader) stream;

			String readBuf = "";
			String[] saved = new String[4];
			int meep = 0;
			while (readBuf != null) {

				try {
					readBuf = bw.readLine();
					saved[meep] = readBuf;
					meep++;

				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Verarbeiten

			String[][] datenGeladen = new String[4][8];
			String[] figurBuf = new String[4];
			SpielerLadenWrapper slw[] = new SpielerLadenWrapper[4];

			for (int i = 0; i < slw.length; i++) {
				slw[i] = new SpielerLadenWrapper();
			}

			for (int i = 0; i < datenGeladen.length; i++) {
				if (saved[i] == null)
					continue;
				datenGeladen[i] = saved[i].split(",");
				slw[i].setName(datenGeladen[i][0]);
				slw[i].setFarbe(datenGeladen[i][1]);
				slw[i].setVerhalten(datenGeladen[i][2]);
				slw[i].setBinIchDran(datenGeladen[i][3]);

				for (int j = 0; j < 4; j++) {
					figurBuf[j] = datenGeladen[i][4 + j];

				}
				slw[i].setFeldIDs(figurBuf);
			}

			Spielbrett sb = new Spielbrett();

			Spielfigur[][] figuren = new Spielfigur[4][4];

			int farbID = 0;
			String nochEinZwischenStringFuerDenNamen;
			Spielfeld zwischenFeld = null;
			Startfeld[][] startFelder = new Startfeld[4][4];
			

		
			Spielfigur.deleteAnzahlFiguren();
			for (int i = 0; i < 4; i++) {

				if (slw[i] == null || slw[i].getFarbe()==null)
					continue;
				
				switch(slw[i].getFarbe()){
				case ROT:
					farbID = 0;
					break;
				case BLAU:
					farbID = 1;
					break;
				case GRUEN:
					farbID = 2;
					break;
				case GELB:
					farbID = 3;
					break;

				}

				for (int j = 0; j < 4; j++) {
					
					nochEinZwischenStringFuerDenNamen = slw[i].getFarbe().toString()+(j+1);
					figuren[i][j] = new Spielfigur(farbID, nochEinZwischenStringFuerDenNamen);
					
				}
			}

			return null;

		}
	}

	@Override
	public void closeFile(Object o) {
		// TODO Auto-generated method stub

		if (o != null) {

			if (o instanceof BufferedWriter) {
				try {
					((BufferedWriter) o).close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (o instanceof BufferedReader) {
				try {
					((BufferedReader) o).close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
