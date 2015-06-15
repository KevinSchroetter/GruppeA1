package Spiel;

import Basisklassen.*;
import Einstellungen.*;
import Hilfsklassen.SpielerLadenWrapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.regex.Pattern;

/**
 * Klasse DatenzugriffCSV - Implementiert iDatenzugriff und bietet Operationen
 * zum Gebrauch von CSV Dateien an.
 * 
 * @author Alexander Brückner
 * @version 3.0
 * @since 3.0
 * 
 * 
 ***/

public class DatenzugriffCSV implements iDatenzugriff {

	/**
	 * openFile - öffnet Datei an angegebenen Pfad mit übergebenem Modus (1 =
	 * Input, 2 = Output) Gibt abhängig von "mode" einen BufferedReader oder
	 * BufferedWriter zurück
	 * 
	 * @param path
	 *            - String
	 * @param mode
	 *            - int
	 * @return Object
	 * @Exception IOException
	 * @Exception IllegalArgumentException
	 * 
	 * **/
	@Override
	public Object openFile(String path, int mode) {

		if (path == null || (mode <= 0 || mode > 2))
			throw new IllegalArgumentException(
					"Dateipfad ungültig! Oder Modus ungültig");
		else {

			if (mode == 2) {

				try {
					PrintWriter bw = new PrintWriter(new FileWriter(path));
					return bw;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else if (mode == 1) {
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

	/**
	 * spielSpeichern - speichert "saveme" in den Strom "stream". Output als
	 * .CSV datei
	 *
	 * @param saveme
	 *            - Object
	 * @param stream
	 *            - Object
	 * @Exception IOException
	 * @Exception IllegalArgumentException
	 * **/
	@Override
	public void spielSpeichern(Object saveme, Object stream) {
		// TODO Auto-generated method stub

		// Parameterchecks
		if (saveme == null || stream == null) {
			System.out.println(saveme);
			System.out.println(stream);
			throw new IllegalArgumentException("Strom oder SpielBean ungültig!");
		}
		if ((!(saveme instanceof SpielBean))
				|| (!(stream instanceof PrintWriter))) {
			System.out.println(saveme);
			System.out.println(stream);
			throw new IllegalArgumentException(
					"Strom bzw SpielBean keine Instanz von geg. Klasse!");
		} else {

			// Anlegen von Variablen, Strom

			PrintWriter saver = (PrintWriter) stream;
			SpielBean buf = (SpielBean) saveme;
			Spieler[] gamer = new Spieler[4];
			String[] speicherMichIchBinFertig = new String[buf
					.getAnzahlSpieler()];
			String[] spielerNamen = new String[4];
			String[][] figurFeldIDs = new String[4][4];
			Spielfigur[] figurenBuffer = new Spielfigur[4];
			String[] binIchDran = new String[4];
			int[][] figurenSchritte = new int[buf.getAnzahlSpieler()][4];

			String[] spielStatus = new String[3];

			if (buf.getHatBegonnen()) {
				spielStatus[0] = "true";
			} else if (!buf.getHatBegonnen()) {
				spielStatus[0] = "false";
			}

			if (buf.getIstBeendet()) {
				spielStatus[1] = "true";
			} else if (!buf.getIstBeendet()) {
				spielStatus[1] = "false";
			}

			if (buf.updateGUIInfo()[0])
				spielStatus[2] = "true";
			else if (!buf.updateGUIInfo()[0])
				spielStatus[2] = "false";

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
					figurenSchritte[i][j] = figurenBuffer[j]
							.getFelderGelaufen();
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

				// Variablen fertig, wrapping in Outputstring via String.format

				if (i == 0) {
					speicherMichIchBinFertig[i] = String.format(
							"%s,%s,%s,%s;%s-%d,%s-%d,%s-%d,%s-%d",
							spielerNamen[i], gamer[i].getFarbe(), verhalten,
							binIchDran[i], figurFeldIDs[i][0],
							figurenSchritte[i][0], figurFeldIDs[i][1],
							figurenSchritte[i][1], figurFeldIDs[i][2],
							figurenSchritte[i][2], figurFeldIDs[i][3],
							figurenSchritte[i][3]);
				} else {
					speicherMichIchBinFertig[i] = String.format(
							"_%s,%s,%s,%s;%s-%d,%s-%d,%s-%d,%s-%d",
							spielerNamen[i], gamer[i].getFarbe(), verhalten,
							binIchDran[i], figurFeldIDs[i][0],
							figurenSchritte[i][0], figurFeldIDs[i][1],
							figurenSchritte[i][1], figurFeldIDs[i][2],
							figurenSchritte[i][2], figurFeldIDs[i][3],
							figurenSchritte[i][3]);
				}
			}

			// Dateikopf mit Strukturbeschreibung
			// try {
			// //
			// saver.write("/Inhalte die mit einem Slash beginnen werden ignoriert\n");
			// // saver.flush();
			// //
			// saver.write("/Name,Farbe,verhalten,binIchDran;Figur1Feld.SchritteGelaufen,Figur2Feld.SchritteGelaufen,Figur3Feld.SchritteGelaufen,Figur4Feld.SchritteGelaufen,hatBegonnen,istBeendet,zugMoeglich %n");
			// // saver.flush();
			//
			// } catch (IOException e) {
			// e.printStackTrace();
			// }

			// Schreiben
			for (int i = 0; i < speicherMichIchBinFertig.length; i++) {
				try {
					saver.write(speicherMichIchBinFertig[i]);
				} catch (Exception e) {
					System.out.println("Fehler: " + e);
					e.printStackTrace();
				}
			}
			saver.write(String.format("_GameParam:%s,%s,%s", spielStatus[0],
					spielStatus[1], spielStatus[2]));
			try {
				saver.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * spielLaden - nimmt einen BufferedReader entgegen und versucht damit, ein
	 * SpielBean zu laden
	 * 
	 * @param stream
	 *            - Object
	 * @return Object
	 * @Exception IOException
	 * @Exception IllegalArgumentException
	 * 
	 * 
	 **/

	// TODO Code aufräumen, ggf. überarbeiten
	@Override
	public Object spielLaden(Object stream) {
		// Parametercheck

		if (!(stream instanceof BufferedReader))
			throw new IllegalArgumentException("Kein BR!");

		BufferedReader reader = (BufferedReader) stream;
		int lineCounter = 0;
		String data = "";

		try {
			data = reader.readLine();
			System.out.println("Daten geladen: " + data);
		} catch (Exception e) {
			System.err
					.println("Fehler beim Laden aus der CSV Datei - StackTrace: ");
			e.printStackTrace();
		}
		String[] firstSplit = new String[5];
		if (data != null && data.length() > 0 && data != "") {
			firstSplit = data.split("_");
			System.out.println("Chunk von firstSplit erfolgreich: ");
			for (String s : firstSplit) {
				if (s != null)
					System.out.println(s);
				else
					System.out.println("String ist null");
			}
		} else {
			System.err
					.println("Fehler beim Verarbeiten der Daten beim ersten Chunk");
			return null;
		}

		String[] firstChunks = new String[(firstSplit.length) - 1];
		String gameParam = new String();

		for (int i = 0; i < firstSplit.length; i++) {
			if (firstSplit[i].startsWith("GameParam")) {
				gameParam = firstSplit[i];
				continue;
			} else
				firstChunks[i] = firstSplit[i];
		}

		System.out.println("\n--- Ausgabe Chunks: ");
		for (String s : firstChunks) {
			if (s != null)
				System.out.println(s);
			else
				System.out.println("String ist null");
		}
		System.out.println();
		System.out.print("Ausgabe Spielparameter: ");
		System.out.println(gameParam);
		System.out.println("--- Ende Ausgabe Chunk 1 ---");

		String[][] secondChunks = new String[(firstSplit.length) - 1][2];

		for (int i = 0; i < secondChunks.length; i++) {
			secondChunks[i] = firstChunks[i].split(";");
		}

		System.out.println("Ausgabe Chunks Stufe 2");
		for (int i = 0; i < secondChunks.length; i++) {
			for (int j = 0; j < secondChunks[i].length; j++) {
				System.out.printf("secondChunks[%d][%d]: %s\n", i, j,
						secondChunks[i][j]);
			}
		}

		String[] spielerChunks = new String[secondChunks.length];
		for (int i = 0; i < spielerChunks.length; i++) {
			spielerChunks[i] = secondChunks[i][0];
			System.out.println(spielerChunks[i]);
		}

		String[][] spielerDaten = new String[secondChunks.length][4];

		for (int i = 0; i < spielerDaten.length; i++) {
			spielerDaten[i] = spielerChunks[i].split(",");
		}

		System.out.println("Chunkation von spielerChunks in nutzbare Daten");

		for (int i = 0; i < spielerDaten.length; i++) {
			for (int j = 0; j < spielerDaten[i].length; j++) {
				System.out.print(spielerDaten[i][j] + " ");

			}
			System.out.println();
		}

		System.out.println("Chunkation von secondChunks in FigurenChunks");
		String[] figurenChunks = new String[secondChunks.length];
		for (int i = 0; i < figurenChunks.length; i++) {
			figurenChunks[i] = secondChunks[i][1];
			System.out.println(figurenChunks[i]);
		}

		System.out.println("Chunkation von FigurChunks in nutzbare Daten");

		String[][] figurenDaten = new String[figurenChunks.length][4];

		for (int i = 0; i < figurenDaten.length; i++) {
			figurenDaten[i] = figurenChunks[i].split(",");
		}

		for (int i = 0; i < figurenDaten.length; i++) {
			for (int j = 0; j < figurenDaten[i].length; j++) {
				System.out.println("Figurenchunk: " + figurenDaten[i][j]);
			}
		}

		String[][][] figurenFertig = new String[figurenDaten.length][4][2];

		for (int i = 0; i < figurenFertig.length; i++) {
			for (int j = 0; j < figurenFertig[i].length; j++) {
				figurenFertig[i][j] = figurenDaten[i][j].split("-");
			}
		}

		for (int i = 0; i < figurenFertig.length; i++) {
			for (int j = 0; j < figurenFertig[i].length; j++) {
				for (int k = 0; k < figurenFertig[i][k].length; k++) {
					System.out.println(figurenFertig[i][j][k]);
				}
			}
		}

		String[] spielerNamenS = new String[4];
		String[] spielerFarbenS = new String[4];
		String[] spielerVerhaltenS = new String[4];
		String[] spielerAmZugS = new String[4];

		for (int i = 0; i < spielerDaten.length; i++) {
			spielerNamenS[i] = spielerDaten[i][0];
			spielerFarbenS[i] = spielerDaten[i][1];
			spielerVerhaltenS[i] = spielerDaten[i][2];
			spielerAmZugS[i] = spielerDaten[i][3];
		}

		System.out.println("Spieler jetzt ladebereit");
		for (int i = 0; i < 4; i++) {
			if (spielerNamenS[i] == null)
				continue;
			System.out.println(spielerNamenS[i]);
			System.out.println(spielerFarbenS[i]);
			System.out.println(spielerVerhaltenS[i]);
			System.out.println(spielerAmZugS[i]);
		}

		String[] spielParaSplit = gameParam.split(":");
		String[] spielParaFertig = spielParaSplit[1].split(",");

		// Spieldaten vorbereiten, Objekt erzeugen

		Spieler[] players = new Spieler[4];
		Spielfigur[][] figuren = new Spielfigur[4][4];

		SpielBean game = new SpielBean();
		Spielbrett sB = new Spielbrett();
		game.setSpielbrett(sB);

		FarbEnum farbBuf = null;
		for (int i = 0; i < spielerNamenS.length; i++) {
			if (spielerFarbenS[i] == null)
				continue;
			if (spielerFarbenS[i].equals("ROT")) {
				farbBuf = FarbEnum.ROT;
			} else if (spielerFarbenS[i].equals("BLAU")) {
				farbBuf = FarbEnum.BLAU;
			} else if (spielerFarbenS[i].equals("GRUEN")) {
				farbBuf = FarbEnum.GRUEN;
			} else if (spielerFarbenS[i].equals("GELB")) {
				farbBuf = FarbEnum.GELB;
			}

			players[i] = new Spieler(spielerNamenS[i], farbBuf,
					sB.getAlleStartFelderEinerFarbe(farbBuf),
					sB.getAlleEndFelderEinerFarbe(farbBuf),
					spielerVerhaltenS[i], game);

			if (spielerAmZugS[i].equals("true")) {
				players[i].setAmZug(true);
				continue;
			}
			continue;

		}

		String[][] figurenFelder = new String[figurenFertig.length][figurenFertig[0].length];
		int[][] figurenSchritte = new int[figurenFertig.length][figurenFertig[0].length];

		int farbID = 0;

		for (int i = 0; i < figuren.length; i++) {
			if (players[i] == null)
				continue;
			for (int j = 0; j < figuren[i].length; j++) {
				if (players[i].getFarbe() == FarbEnum.ROT) {
					farbID = 0;
				} else if (players[i].getFarbe() == FarbEnum.BLAU) {
					farbID = 1;
				} else if (players[i].getFarbe() == FarbEnum.GRUEN) {
					farbID = 2;
				} else if (players[i].getFarbe() == FarbEnum.GELB) {
					farbID = 3;
				}
				figuren[i][j] = new Spielfigur(farbID, FarbEnum.values()[farbID].toString()+j );
			}

		}

		
		for (int i = 0; i < players.length; i++) {
			if (players[i] == null)
				continue;
			players[i].figurenLaden(figuren[i]);
		}

		for (int i = 0; i < players.length; i++) {
			if (players[i] == null)
				continue;
			game.spielerLaden(players[i]);
		}

		for (int i = 0; i < figurenFelder.length; i++) {
			for (int j = 0; j < figurenFelder[i].length; j++) {
				figurenFelder[i][j] = figurenFertig[i][j][0];
				figurenSchritte[i][j] = Integer
						.parseInt(figurenFertig[i][j][1]);
				players[i].alleFiguren()[j].setMeinFeld(game.getSpielbrett()
						.getFeld(figurenFelder[i][j], players[i].getFarbe()));
				players[i].alleFiguren()[j].setFelderGelaufen(figurenSchritte[i][j]);
			}
		}
		

		boolean spielBeendet = false;
		boolean spielBegonnen = false;
		// boolean updateGUI = false;
		//

		if (spielParaFertig[1].equals("true")) {
			spielBeendet = true;
		}
		if (spielParaFertig[0].equals("true")) {
			spielBegonnen = true;
		}
		// if(spielParaFertig[2].equals("true")){
		// updateGUI = true;
		// }
		//
		game.setIstBeendet(spielBeendet);
		game.setHatBegonnen(spielBegonnen);

		for(int i = 0; i<players.length; i++){
			if(players[i] == null) continue;
			if(spielerAmZugS[i].equals("true")){
				players[i].setAmZug(true);
				game.setIstAmZug(players[i]);
			}
		}

		
		System.out.println(game);
		return (Object) game;
	}

	/***
	 * 
	 * closeFile
	 * 
	 * Schließt den übergebenen Datenstrom
	 * 
	 * @param o
	 *            - Object
	 * @Exception IOException
	 * 
	 * 
	 ***/

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
