package Spiel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;

import Basisklassen.Spielbrett;
import Basisklassen.Spieler;
import Basisklassen.Spielfigur;
import Einstellungen.FarbEnum;
import Hilfsklassen.FigurenWrapper;
import Hilfsklassen.SpielXMLWrapper;
import Hilfsklassen.SpielerWrapper;

/**
 * 
 * TestSpielLaden - Testet serialisiertes/CSV-basiertes Laden
 *
 **/
public class TestSpielLaden {

	public static void main(String[] args) {

		// Datenzugriffe anlegen und SpielBean laden, anschließend prüfen, ob
		// Ausgabe äquivalent zu Ausgabe in Speichern
		iDatenzugriff ds = new DatenzugriffSerialisiert();
		iDatenzugriff dCSV = new DatenzugriffCSV();

		// Serialsiert Laden
		FileInputStream ichLadeEtwas = (FileInputStream) ds.openFile(
				"savegame.ser", 1);
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

		// CSV Laden
		try {
			System.out.println("--------CSV--------");
			BufferedReader br = (BufferedReader) dCSV.openFile("meep.csv", 1);
			SpielBean ladeMich1 = (SpielBean) dCSV.spielLaden(br);
			for (Spieler s : ladeMich1.getSpieler()) {
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
		} catch (Exception e) {
			System.out.println("Im Catchblock");
			e.printStackTrace();
			return;
		}

		SpielBean xmlFinal = null;
		SpielXMLWrapper sXML = null;

		iDatenzugriff dXML = new DatenzugriffXML();
		try {
			sXML = (SpielXMLWrapper) dXML.spielLaden(dXML.openFile(
					"D:\\test.xml", 0));

			// for (Object o : sXML.getSpieler().toArray()) {
			// SpielerWrapper sW = (SpielerWrapper) o;
			// System.out.println(sW);
			// }
			//
			// for (Object o : sXML.getFiguren().toArray()) {
			// FigurenWrapper fW = (FigurenWrapper) o;
			// System.out.println(fW);
			// }

			Spielfigur figuren[][] = new Spielfigur[4][4];
			Spieler spieler[] = new Spieler[4];
			Spielbrett spielbrett = new Spielbrett();

			for (int i = 0; i < sXML.getSpieler().toArray().length; i++) {
				SpielerWrapper sW = (SpielerWrapper) sXML.getSpieler()
						.toArray()[i];
				if (sW == null)
					continue;
				else {

					if (sW.getBedienung() == null) {
						spieler[i] = new Spieler(sW.getName(), sW.getFarbe(),
								spielbrett.getAlleStartFelderEinerFarbe(sW
										.getFarbe()),
								spielbrett.getAlleEndFelderEinerFarbe(sW
										.getFarbe()), xmlFinal);
					} else {
						spieler[i] = new Spieler(sW.getName(), sW.getFarbe(),
								spielbrett.getAlleStartFelderEinerFarbe(sW
										.getFarbe()),
								spielbrett.getAlleEndFelderEinerFarbe(sW
										.getFarbe()), sW.getBedienung(),
								xmlFinal);
					}

				}
			}

			FigurenWrapper fW = null;
			Spielfigur.deleteAnzahlFiguren();
			for (int i = 0; i < figuren.length; i++) {
				if (spieler[i] == null)
					continue;
				for (int j = 0; j < figuren[i].length; j++) {
					if (sXML.getFiguren().get(j) == null)
						continue;

					int farbID = 0;
					switch (spieler[i].getFarbe()) {
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
					fW = sXML.getFiguren().get(j);
					figuren[i][j] = new Spielfigur(farbID, spieler[i]
							.getFarbe().toString() + " " + j);
					figuren[i][j].setMeinFeld(spielbrett.getFeld(
							fW.getFeldID(), figuren[i][j].getFarbe()));

					System.out.println(figuren[i][j]);
				}
				spieler[i].figurenLaden(figuren[i]);
			}
			xmlFinal = new SpielBean();
			xmlFinal.setSpielbrett(spielbrett);
			Spieler amZug = null;
			for (int i = 0; i < spieler.length; i++) {
				if (spieler[i] == null)
					continue;
				xmlFinal.spielerLaden(spieler[i]);
				if(sXML.getSpieler().get(i).getIstAmZug()){
					amZug = spieler[i];
					spieler[i].setAmZug(true);
				}
			}
			
			
				xmlFinal.setIstBeendet(sXML.isBeendet());
				xmlFinal.setHatBegonnen(sXML.isGestartet());

			xmlFinal.setIstAmZug(amZug);
			xmlFinal.starteSpiel();
			xmlFinal.ausgabeFiguren();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
