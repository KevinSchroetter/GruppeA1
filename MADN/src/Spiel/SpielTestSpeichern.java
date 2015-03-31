package Spiel;

import java.io.*;

/**
 * 
 * Klasse SpieltestSpeichern - Testet Funktionalität der
 * Datenzugriffsfunktionen. Serialisiert, CSV (CSV Implementierung ausstehend)
 * 
 * @author Alexander Brückner
 * 
 *
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
		game.zugDurchführen("S1");

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
