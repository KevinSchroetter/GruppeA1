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
						"%s,%s;%s,%s,%s,%s;%s,%s\n", spielerNamen[i],
						gamer[i].getFarbe(), figurFeldIDs[i][0],
						figurFeldIDs[i][1], figurFeldIDs[i][2],
						figurFeldIDs[i][3], verhalten, binIchDran[i]);
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
			ArrayList<String> ladenStrings = new ArrayList<String>(0);

			String readBuf = "";
			while (readBuf != null) {

				try {

					readBuf = bw.readLine();
					ladenStrings.add(readBuf);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// Verarbeiten

			String[] workWithMe = new String[ladenStrings.size()];
			workWithMe = (String[]) ladenStrings.toArray();
			ladenStrings = null;

			SpielerLadenWrapper slw[] = new SpielerLadenWrapper[workWithMe.length];
			Spieler gamer[] = new Spieler[workWithMe.length];

			String[] figurenEinzeln = new String[4];
			String[] verhaltenUndAmZug = new String[2];
			String[][][] cutMe = new String [workWithMe.length][3][5];
			String figurenAmStück = null;
			String[] nameUndFarbe = new String[2];

			//figurenEinzeln = figurenAmStück.split(",");

			for (int i = 0; i < workWithMe.length; i++) {
				
				cutMe[i][i] = workWithMe[i].split(";");
				
				nameUndFarbe = cutMe[i][1];
				
				
				slw[i] = new SpielerLadenWrapper();
				slw[i].setName(nameUndFarbe[0]);
				slw[i].setFarbe(nameUndFarbe[1]);
				
				
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
