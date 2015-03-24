package Spiel;

import java.util.ArrayList;

import Basisklassen.*;

/**
 * Klasse Spiel als Regelwerk für MADN.
 * Sie beinhaltet alle notwendigen Methoden, um einen reibnungslosen Spielfluss zu gewährleisten.
 * In dieser Klasse sind DebugMethoden implementiert, sie sind ganz am Endeder Datei zu finden.
 * @author Kevin Schrötter, Felix Rosa, Anna Rosa, Alexander Brückner
 * @version 1.1
 *
 */

public class Spiel {
	private Spieler[] spieler = new Spieler[4];
	/**
	 * Array, in dem alle am Spiel teilnehmenden Spieler gespeichert werden
	 */
	private Spieler[] spieler = new Spieler[4];
	/**
	 * Hier wird der Spieler gespeichert, der gerade ziehen darf
	 */
	private Spieler istAmZug;
	private Spielbrett spielbrett;
	private int anzahlSpieler;
	private boolean hatBegonnen = false;
	private boolean istBeendet = false;

	public Spiel() {
		spielbrett = new Spielbrett();
	}

	/**
	 * Methode zum Hinzufügen eines neuen Spielers, solange das Spiel noch nicht
	 * gestartet ist. Sind Spieler im Spiel, so wird Spiel automatisch begonnen.
	 * 
	 * @param name
	 *            - gewünschter Name des Spielers
	 * @param farbe
	 *            - gewünschte Farbe des Spielers
	 * @param verhalten
	 *            - Falls null: Menschlicher Spieler, sonst: KI mit dem
	 *            übergebenen Verhalten;
	 */
	private void spielerHinzufügen(String name, FarbEnum farbe, String verhalten) {
		if (hatBegonnen == true)
			throw new RuntimeException("Spiel hat schon begonnen");
		for (int i = 0; i <= 3; i++) {
			if (spieler[i] != null) {
				if (!(farbe.equals(spieler[i])))
					throw new RuntimeException("Farbe schon vorhanden");
			}
		}
		Startfeld[] startfelder = spielbrett
				.getAlleStartFelderEinerFarbe(farbe);
		Endfeld[] endfelder = spielbrett.getAlleEndFelderEinerFarbe(farbe);
		if (verhalten == null) {
			spieler[anzahlSpieler] = new Spieler(name, farbe, startfelder,
					endfelder);
		} else if (verhalten != null) {
			spieler[anzahlSpieler] = new Spieler(name, farbe, startfelder,
					endfelder, verhalten);
		}
		anzahlSpieler++;
		if (anzahlSpieler == 3)
			hatBegonnen = true;

	}

	/**
	 * Methode, die das Spiel startet, so dass keine Spieler mehr hinzugefügt
	 * werden können. Sie setzt den ersten Spieler im Spieler Array als den
	 * Spieler, der am Zug ist.
	 */
	private void startSpiel() {
		hatBegonnen = true;
		spieler[0].setAmZug(true);
		istAmZug = spieler[0];
	}

	// Standardfeld[] standardFelder -> auf public gesetzt
	// Spielfigur - setKannInsZiel -> auf public gesetzt
	// Methode binIchAufEndposition() muss in Klasse Spielfigur geschrieben werden
	@SuppressWarnings("unused")
	private boolean kannIchZiehen(Spielfigur figur, int augenZahl) {
		if (figur.binIchGespawnt() == true && (!(figur.binIchAufEndpostion()))) {
			Standardfeld Zielfeld = spielbrett.standardFelder[figur
					.getFelderGelaufen() + augenZahl];
			if (figur.getIstImZiel() == true) {
				/**
				 * if (kannZiehenEndfelder(figur, augenZahl) == true) { return
				 * true; } }
				 */ //muss später weg
				if ((figur.getFelderGelaufen() + augenZahl) > 39) {
					if (((figur.getFelderGelaufen() + augenZahl) - 39 <= 4)) {
						int tempSchritte = (figur.getFelderGelaufen() + augenZahl) - 39;
						/**
						 * if (kannZiehenEndfelder(figur, tempSchritte) == true)
						 * {figur.setKannInsZiel(true); 
						 * return true; } else { return false; }
						 */ //muss später weg
					}
				}

				if (Zielfeld.getFigur() == null) {
					return true;
				} else if (figur.kannSchlagen(Zielfeld) == true) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}//muss später weg
		
		return false; //muss später weg
	}

	/**
	 * Methode, die prüft, ob eine Figur innerhalb der Endfelder um die gewollte
	 * Anzahl der Züge ziehen kann.
	 * 
	 * @param figur
	 *            - Die Figur, die ziehen soll
	 * @param zuZiehen
	 *            - Anzahl der Züge, die Figur ziehen soll
	 * @return
	 */

	/*
	 * private boolean kannZiehenEndfelder(Spielfigur figur, int zuZiehen) { if
	 * (zuZiehen > 4) return false; Endfeld[] endfelderIstAmZug =
	 * istAmZug.getEndFelder(); if (zuZiehen == 1) { if
	 * (endfelderIstAmZug[0].getFigur() == null) return true; } if (zuZiehen ==
	 * 2) { if (endfelderIstAmZug[1].getFigur() == null &&
	 * kannZiehenEndfelder(figur, 1) == true) return true; } if (zuZiehen == 3)
	 * { if (endfelderIstAmZug[2].getFigur() == null &&
	 * kannZiehenEndfelder(figur, 2) == true) { return true; } } if (zuZiehen ==
	 * 4) { if (endfelderIstAmZug[3].getFigur() == null &&
	 * kannZiehenEndfelder(figur, 3) == true) { return true; } }
	 * 
	 * else return false;
	 * 
	 * }*
	 */

	/**
	 * Methode, die eine Figur um eine bestimmte Anzahl an Zügen in seinem
	 * Endfeld ziehen lässt.
	 * 
	 * @param figur
	 *            - Die Figur, die ziehen soll
	 * @param zuZiehen
	 *            - Anzahl der Züge, die Figur ziehen soll
	 */
	private void ziehenEndfelder(Spielfigur figur, int zuZiehen) {
		/**if (kannZiehenEndfelder(figur, zuZiehen) != true) {
			throw new RuntimeException("Figur kann nicht ziehen!");
		}*/ //muss später weg
		Endfeld[] endfelderIstAmZug = istAmZug.getEndFelder();
		endfelderIstAmZug[zuZiehen - 1].setFigur(figur);
		figur.setMeinFeld(endfelderIstAmZug[zuZiehen - 1]);

	}

}
