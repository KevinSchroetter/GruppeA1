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
import java.util.regex.Pattern;

/**
 * Klasse DatenzugriffCSV - Implementiert iDatenzugriff und bietet Operationen zum Gebrauch von CSV
 * Dateien an.
 * @author Alexander Brückner
 * @version 3.0
 * @since 3.0
 * 
 * 
 ***/

public class DatenzugriffCSV implements iDatenzugriff {

	/**
	 * openFile - öffnet Datei an angegebenen Pfad mit übergebenem Modus (1 = Input, 2 = Output)
	 * Gibt abhängig von "mode" einen BufferedReader oder BufferedWriter zurück
	 * @param path - String
	 * @param mode - int
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
					BufferedWriter bw = new BufferedWriter(new FileWriter(path));
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
	 * spielSpeichern - speichert "saveme" in den Strom "stream".
	 * Output als .CSV datei
	 *
	 * @param saveme - Object
	 * @param stream - Object
	 * @Exception IOException
	 * @Exception IllegalArgumentException
	 * **/
	@Override
	public void spielSpeichern(Object saveme, Object stream) {
		// TODO Auto-generated method stub

		//Parameterchecks
		if (saveme == null || stream == null){
			System.out.println(saveme);
			System.out.println(stream);
			throw new IllegalArgumentException("Strom oder SpielBean ungültig!");}
		if ((!(saveme instanceof SpielBean))
				|| (!(stream instanceof BufferedWriter))){
			System.out.println(saveme);
			System.out.println(stream);
			throw new IllegalArgumentException("Strom bzw SpielBean keine Instanz von geg. Klasse!");}
		else {
			
			//Anlegen von Variablen, Strom

			BufferedWriter saver = (BufferedWriter) stream;
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
			else if(!buf.updateGUIInfo()[0])
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
				
				//Variablen fertig, wrapping in Outputstring via String.format

				speicherMichIchBinFertig[i] = String.format(
						"%s,%s,%s,%s;%s.%d,%s.%d,%s.%d,%s.%d;%s,%s,%s \n",
						spielerNamen[i], gamer[i].getFarbe(), verhalten,
						binIchDran[i], figurFeldIDs[i][0],
						figurenSchritte[i][0], figurFeldIDs[i][1],
						figurenSchritte[i][1], figurFeldIDs[i][2],
						figurenSchritte[i][2], figurFeldIDs[i][3],
						figurenSchritte[i][3], spielStatus[0], spielStatus[1],spielStatus[2]);
			}

			
			//Dateikopf mit Strukturbeschreibung
//			try {
////				saver.write("/Inhalte die mit einem Slash beginnen werden ignoriert\n");
////				saver.flush();
////				saver.write("/Name,Farbe,verhalten,binIchDran;Figur1Feld.SchritteGelaufen,Figur2Feld.SchritteGelaufen,Figur3Feld.SchritteGelaufen,Figur4Feld.SchritteGelaufen,hatBegonnen,istBeendet,zugMoeglich %n");
////				saver.flush();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

			//Schreiben
			for (int i = 0; i < speicherMichIchBinFertig.length; i++) {
				try {
					saver.write(speicherMichIchBinFertig[i]);
					saver.write(System.lineSeparator());
					saver.flush();
				} catch (IOException e) {
					System.out.println("Fehler: " + e);
					e.printStackTrace();
				}
			}
			try {
				saver.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	
	/**
	 * spielLaden - nimmt einen BufferedReader entgegen und versucht damit, ein SpielBean zu laden
	 * @param stream - Object
	 * @return Object
	 * @Exception IOException
	 * @Exception IllegalArgumentException 
	 * 
	 * 
	 **/
	
	//TODO Code aufräumen, ggf. überarbeiten
	@Override
	public Object spielLaden(Object stream) {
		// Parametercheck	
		
		if (!(stream instanceof BufferedReader))
			throw new IllegalArgumentException("Strom fehlerhaft/Ungültig");
		else {

			BufferedReader bw = (BufferedReader) stream;

			//Daten aus Datei laden
			String readBuf = "";
			String[] saved = new String[4];
			int meep = 0;
			while (readBuf != null) {

				try {

					readBuf = bw.readLine();
					if ((!(readBuf == null)) && readBuf.startsWith("/"))
						continue;
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

			// Verarbeitung
			String[][] ersterSplit = new String[4][3];
			String[] ersterFigurenSplit = new String[4];
			String[][] zweiterFigurenSplit = new String[4][4];
			String[][][] dritterFigurenSplit = new String[4][4][2];
			String[][] figurenIDs = new String[4][4];
			int[][] figurenSchritte = new int[4][4];
			String[][] spielerSchnitt = new String[4][4];
			String[] farbenSplit = new String[4];
			String[] spielerNamen = new String[4];
			String[] spielerVerhalten = new String[4];
			String[] spielBegonnen = new String[4];
			String[] spielBeendet = new String[4];
			String[] amZug = new String[4];
			FarbEnum[] farben = new FarbEnum[4];
			int spielerAmZugID;

			for (int i = 0; i < 4; i++) {
				if (saved[i] == null)
					continue;
				ersterSplit[i] = saved[i].split(";");
			}

			for (int i = 0; i < 4; i++) {
				if (ersterSplit[i][0] == null)
					continue;
				spielerSchnitt[i] = ersterSplit[i][0].split(",");
				spielerNamen[i] = spielerSchnitt[i][0];
				farbenSplit[i] = spielerSchnitt[i][1];
				spielerVerhalten[i] = spielerSchnitt[i][2];
				amZug[i] = spielerSchnitt[i][3];
			}

			for (int i = 0; i < 4; i++) {
				if (farbenSplit[i] == null)
					continue;
				switch (farbenSplit[i]) {
				case "ROT":
					farben[i] = FarbEnum.ROT;
					continue;
				case "BLAU":
					farben[i] = FarbEnum.BLAU;
					continue;
				case "GRUEN":
					farben[i] = FarbEnum.GRUEN;
					continue;
				case "GELB":
					farben[i] = FarbEnum.GELB;
					continue;
				}
			}

			for (int i = 0; i < 4; i++) {
				if (ersterSplit[i] == null || ersterSplit[i][1] == null
						|| ersterSplit.length == 0
						|| ersterSplit[i].length == 0)
					continue;
				ersterFigurenSplit[i] = ersterSplit[i][1];
			}

			for (int i = 0; i < 4; i++) {
				if (ersterFigurenSplit[i] == null)
					continue;
				zweiterFigurenSplit[i] = ersterFigurenSplit[i].split(",");
			}

			// ACHTUNG! Die folgenden Zeilen bereiten dem Leser eventuell genau
			// so viele Kopfschmerzen, wie mir, dem Programmierer.
			// Eine ganze Kanne Kaffee wurde in the making of the following
			// for-loop-ception konsumiert.
			// Eigentlich Schwachsinn, aber warum nicht etwas überdramatisieren?

			for (int i = 0; i < 4; i++) {
				if (zweiterFigurenSplit[i] == null)
					continue;
				for (int j = 0; j < 4; j++) {
					if (zweiterFigurenSplit[i][j] == null)
						continue;
					for (int k = 0; k < 4; k++) {
						if (zweiterFigurenSplit[j][k] == null)
							continue;
						dritterFigurenSplit[j][k] = zweiterFigurenSplit[j][k]
								.split(Pattern.quote("."));
					}
				}
			}

			// Damit sind die Figuren gespalten genug, um sie nun wieder
			// zusammen zu setzen
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (dritterFigurenSplit[i][j][0] == null)
						continue;
					figurenIDs[i][j] = dritterFigurenSplit[i][j][0];
				}
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (dritterFigurenSplit[i][j][1] == null)
						continue;
					figurenSchritte[i][j] = Integer
							.parseInt(dritterFigurenSplit[i][j][1]);
				}
			}

			Spielfigur[][] figurenDieEndlichFertigSind = new Spielfigur[4][4];
			Spielfigur.deleteAnzahlFiguren();

			int figCounter = 1;
			for (int i = 0; i < 4; i++) {
				if (saved[i] == null)
					continue; // Damit auch nur so viele Figurenarrays errzeugt
								// werden wie es spieler gibt
				for (int j = 0; j < 4; j++) {
					figurenDieEndlichFertigSind[i][j] = new Spielfigur(i,
							((farben[i].name()) + " " + (figCounter++)));
				}
				figCounter=1;
			}
			SpielBean gibMichZurueck = new SpielBean();
			

			Spieler[] fastFertig = new Spieler[4];
			Startfeld[][] stF = new Startfeld[4][4];
			Endfeld[][] eF = new Endfeld[4][4];
			Standardfeld[] stanF = gibMichZurueck.getSpielbrett().getStandardFelder();			

			for (int i = 0; i < 4; i++) {
				if(farben[i] == null) continue;
				stF[i] = gibMichZurueck.getSpielbrett()
						.getAlleStartFelderEinerFarbe(farben[i]);
				
				eF[i] = gibMichZurueck.getSpielbrett()
						.getAlleEndFelderEinerFarbe(farben[i]);
			}
			
			for(int i = 0; i<4; i++){
				if(spielerNamen[i]==null || farben[i] == null
					|| stF[i] == null || eF[i] == null || spielerVerhalten[i] == null) continue;
				else{
					if(spielerVerhalten[i].equals("mensch"))fastFertig[i] = new Spieler(spielerNamen[i],farben[i],stF[i],eF[i],gibMichZurueck);
					else
					fastFertig[i] = new Spieler(spielerNamen[i],farben[i],stF[i],eF[i],spielerVerhalten[i],gibMichZurueck);
			}
			}

			for(int i = 0; i<4; i++){
				if(fastFertig[i]==null || figurenDieEndlichFertigSind[i]==null) continue;
				fastFertig[i].figurenLaden(figurenDieEndlichFertigSind[i]);
				gibMichZurueck.spielerLaden(fastFertig[i]);
			}
			

			Spielfeld here = null;
			
			
			for(int i = 0; i<4; i++){
				for(int j = 0; j<4; j++){
					if(figurenIDs[i][j] == null || farben[i] == null) continue;
				
					if(figurenIDs[i][j].startsWith("S")){
						here = (Startfeld) here;
						
					}
					else if(figurenIDs[i][j].startsWith("E")){
						here = (Endfeld) here;
					}
					else here = (Standardfeld) here;
						
					
				
				here = gibMichZurueck.getSpielbrett().getFeld(figurenIDs[i][j], farben[i]);
				figurenDieEndlichFertigSind[i][j].setMeinFeld(here);
				here.setFigur(figurenDieEndlichFertigSind[i][j]);
				here = null;
				}
			}
			Boolean zM=false;
			
			gibMichZurueck.setZugMoeglich(zM);
			for(int i = 0; i<4; i++){
				if(spielerSchnitt[i][3] == null) continue;
					fastFertig[i].setAmZug(spielerSchnitt[i][3].equals("true"));	
					gibMichZurueck.setIstAmZug(fastFertig[i]);
					
			}

			gibMichZurueck.starteSpiel();
		return gibMichZurueck;

		}

	}
	
	/***
	 * 
	 * closeFile
	 * 
	 * Schließt den übergebenen Datenstrom
	 * @param o - Object
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
