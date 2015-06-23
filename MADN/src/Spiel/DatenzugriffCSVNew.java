package Spiel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import Basisklassen.Endfeld;
import Basisklassen.KI_Aggressiv;
import Basisklassen.KI_Defensiv;
import Basisklassen.Spielbrett;
import Basisklassen.Spieler;
import Basisklassen.Spielfeld;
import Basisklassen.Spielfigur;
import Basisklassen.Standardfeld;
import Basisklassen.Startfeld;
import Einstellungen.FarbEnum;

public class DatenzugriffCSVNew implements iDatenzugriff {

	@Override
	public Object openFile(String path, int mode) {

		if (path == null || path.equals("")) {
			throw new IllegalArgumentException("Ungültiger Dateipfad");
		}

		else {

			switch (mode) {
			case 0:
				try {
					return new BufferedReader(new FileReader(path));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			case 1:
				try {
					return new PrintWriter(path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
			default:
				System.err.println("Ungültiger CSV-Zugriff! (0-in/1-out)");
				return null;
			}
		}
		System.err
				.println("Fataler Fehler beim Speichern - Programmierer weiß wahrscheinlich auch nich weiter");
		return null;
	}

	@Override
	public void spielSpeichern(Object saveme, Object stream) {

		if (!(stream instanceof PrintWriter))
			throw new IllegalArgumentException("PrintWriter vorrausgesetzt!");
		if (saveme == null)
			throw new IllegalArgumentException("Zu speicherndes Spiel null");

		PrintWriter writer = (PrintWriter) stream;
		Spiel s = (Spiel) saveme;
		String[] saveData = new String[s.getAnzahlSpieler()];
		String[] spielerVerhalten = new String[s.getAnzahlSpieler()];

		for (int i = 0; i < saveData.length; i++) {
			if (s.getSpieler()[i].getBedienung() == null) {
				spielerVerhalten[i] = null;
			} else if (s.getSpieler()[i].getBedienung() instanceof KI_Aggressiv) {
				spielerVerhalten[i] = "aggressiv";
			} else if (s.getSpieler()[i].getBedienung() instanceof KI_Defensiv) {
				spielerVerhalten[i] = "defensiv";
			}

			saveData[i] = String.format("%s,%s,%s,%s%s",
					s.getSpieler()[i].getName(), s.getSpieler()[i].getFarbe(),
					spielerVerhalten[i], s.getSpieler()[i].figurenAlsString(),
					s.getSpieler()[i].getAmZug());

			writer.println(saveData[i]);
			writer.flush();

		}

		String gameParam = "GameParam,";
		gameParam += s.getHatBegonnen() + "," + s.getBeendet();
		writer.println(gameParam);
		this.closeFile(writer);

	}

	@Override
	public Object spielLaden(Object stream) {
		if (!(stream instanceof BufferedReader))
			throw new IllegalArgumentException("BufferedReader vorrausgesetzt!");

		Spiel spiel = new Spiel();
		int counter = 0;
		int playerLength = 0;
		String buf = null;
		String[] chunks = new String[5];
		BufferedReader reader = (BufferedReader) stream;

		// Strings laden
		try {
			buf = reader.readLine();
			playerLength = buf.length();
			// System.out.println(buf);
			while (buf != null) {
				chunks[counter] = buf;
				buf = reader.readLine();
				counter++;
				// System.out.println(buf);
			}
		} catch (Exception e) {
			System.err.println("Fehler beim Speichern: ");
			e.printStackTrace();
		}

		// Leser schließen
		this.closeFile(reader);

		String[][] daten = new String[chunks.length][playerLength];

		if (daten[0].length <= 0) {
			System.err.println("Fehler beim Laden");
			return null;
		}
		// Chunks aufteilen
		for (int i = 0; i < chunks.length; i++) {

			if (chunks[i] == null)
				continue;
			daten[i] = chunks[i].split(",");

			// For-Each zum Ausgeben der Chunks
			// for (String s : daten[i]) {
			// if (daten[i] == null)
			// continue;
			// System.out.println(s);
			// }

		}

		boolean gestartet = false;
		Spielbrett sB = new Spielbrett();
		Spieler[] zuErstellen = new Spieler[chunks.length - 1];
		Spielfigur figuren[][] = new Spielfigur[chunks.length][4];
		Spieler.deleteSpielernummer();
		Spielfigur.deleteAnzahlFiguren();
		for (int i = 0; i < (chunks.length - 1); i++) {
			if (daten[i] == null)
				continue;
			if (daten[i][0].startsWith("GameParam")) {

				if ((Boolean.parseBoolean(daten[i][1]) == true)) {
					gestartet = true;
				}

				spiel.setIstBeendet(Boolean.parseBoolean(daten[i][2]));
				break;
			}
			zuErstellen[i] = new Spieler(
					daten[i][0],
					FarbEnum.valueOf(daten[i][1]),
					sB.getAlleStartFelderEinerFarbe(farbeErmitteln(daten[i][1])),
					sB.getAlleEndFelderEinerFarbe(farbeErmitteln(daten[i][1])),
					daten[i][2], spiel);
			zuErstellen[i].setAmZug(false);
			if (daten[i][11].equals("true")) {
				zuErstellen[i].setAmZug(true);
				spiel.setIstAmZug(zuErstellen[i]);
			}

			Spielfeld here = null;

			for (int j = 0; j < figuren[0].length; j++) {

				figuren[i][j] = new Spielfigur(farbeErmitteln(daten[i][1]),
						farbeErmitteln(daten[i][1]).toString() + " " + (j + 1));

				switch (j) {
				case 0:
					here = sB.getFeld(daten[i][3], farbeErmitteln(daten[i][1]));

					if (here instanceof Startfeld) {
						here = (Startfeld) here;
					} else if (here instanceof Standardfeld) {
						here = (Standardfeld) here;
					} else if (here instanceof Endfeld) {
						here = (Endfeld) here;
					}
					figuren[i][j].setMeinFeld(here);

					break;
				case 1:
					here = sB.getFeld(daten[i][5], farbeErmitteln(daten[i][1]));
					if (here instanceof Startfeld) {
						here = (Startfeld) here;
					} else if (here instanceof Standardfeld) {
						here = (Standardfeld) here;
					} else if (here instanceof Endfeld) {
						here = (Endfeld) here;
					}
					figuren[i][j].setMeinFeld(here);
					break;
				case 2:

					here = sB.getFeld(daten[i][7], farbeErmitteln(daten[i][1]));
					if (here instanceof Startfeld) {
						here = (Startfeld) here;
					} else if (here instanceof Standardfeld) {
						here = (Standardfeld) here;
					} else if (here instanceof Endfeld) {
						here = (Endfeld) here;
					}
					figuren[i][j].setMeinFeld(here);
					break;
				case 3:
					here = sB.getFeld(daten[i][9], farbeErmitteln(daten[i][1]));
					if (here instanceof Startfeld) {
						here = (Startfeld) here;
					} else if (here instanceof Standardfeld) {
						here = (Standardfeld) here;
					} else if (here instanceof Endfeld) {
						here = (Endfeld) here;
					}
					figuren[i][j].setMeinFeld(here);
					break;
				default:
					break;
				}

			}

			zuErstellen[i].figurenLaden(figuren[i]);
			spiel.spielerLaden(zuErstellen[i]);
		}

		// for (Spieler s : zuErstellen) {
		// if (s == null)
		// continue;
		// System.out.println(s);
		// }

		if (gestartet) {
			spiel.starteSpiel();
		}

		spiel.updateGUIFigures();
		spiel.updateGUIInfo();

		return spiel;

	}

	@Override
	public void closeFile(Object o) {
		// TODO Auto-generated method stub

		if (o instanceof PrintWriter) {
			((PrintWriter) o).close();
		} else if (o instanceof BufferedReader) {
			try {
				((BufferedReader) o).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public FarbEnum farbeErmitteln(String s) {

		if (s.equals("ROT"))
			return FarbEnum.ROT;
		else if (s.equals("BLAU"))
			return FarbEnum.BLAU;
		else if (s.equals("GRUEN"))
			return FarbEnum.GRUEN;
		else if (s.equals("GELB"))
			return FarbEnum.GELB;

		else
			System.err.println("Ungültige Farbe");
		return null;
	}
}
