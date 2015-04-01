package Spiel;

import java.io.*;

/**
 * 
 * Klasse SpieltestSpeichern - Testet Funktionalitaet der
 * Datenzugriffsfunktionen. Serialisiert, CSV (CSV Implementierung ausstehend)
 * 
 * @author Alexander Brueckner
 * @version 3.0
 * @since v2.3
 *
 */
public class SpielTestSpeichern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DatenzugriffSerialisiert DS = new DatenzugriffSerialisiert();
		Spiel game = new Spiel();
		Spiel load = null;

		game.neuerSpieler("Alex", 1, 0);
		game.neuerSpieler("Detlef", 2, 2);

		game.starteSpiel();
		game.rollTheDice();
		game.zugDurchfuehren("S1");

		try {
			DS.spielSpeichern(game);
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern");
			e.printStackTrace();
		}

		try {
			load = DS.spielLaden();
		} catch (IOException e) {
			System.out.println("Fehler beim laden!");
			e.printStackTrace();
		}

		load.ausgabeFiguren();

	}

}
