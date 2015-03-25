package Spiel;

import java.util.ArrayList;

import Basisklassen.*;
import Hilfsklassen.*;

/**
 * Klasse Spiel als Regelwerk für MADN. Sie beinhaltet alle notwendigen
 * Methoden, um einen reibungslosen Spielfluss zu gewährleisten. In dieser
 * Klasse sind DebugMethoden implementiert, sie sind ganz am Endeder Datei zu
 * finden.
 * 
 * @author Kevin Schrötter, Felix Rosa, Anna Rosa, Alexander Brückner
 * @version 1.2
 *
 */

public class Spiel implements iBediener{
	/**
	 * Array, in dem alle am Spiel teilnehmenden Spieler gespeichert werden
	 */
	private Spieler[] spieler = new Spieler[4];
	/**
	 * Hier wird der Spieler gespeichert, der gerade ziehen darf
	 */
	private Spieler istAmZug;
	/**
	 * Zeigt an, ob der Spieler tatsächlich ziehen kann
	 */
	private boolean zugMöglich = false;
	/**
	 * Hier wird das Spielbrett gespeichert, auf dem gespielt wird
	 */
	private Spielbrett spielbrett;
	/**
	 * Anzahl der Spieler, die gerade an einem Spiel teilnehmen
	 */
	private int anzahlSpieler;
	/**
	 * Zeigt an, ob ein Spiel bereits begonnen hat, falls TRUE
	 */
	private boolean hatBegonnen = false;
	/**
	 * Zeigt an, ob ein Spiel bereits beendet ist, falls TRUE
	 */
	private boolean istBeendet = false;
	/**
	 * Speichert die Anzahl an Würfel-Würfen, die ein Spieler während eines
	 * Zuges ausgeführt hat. Relevant für den Fall, dass mehrmals hintereinander
	 * eine 6 gewürfelt wird.
	 */
	private int anzWürfe = 0;
	/**
	 * Speichert die zuletzt gewürfelte Augenzahl während eines Zuges
	 */
	private int augenzahl = 0;
	/**
	 * ArrayList, in der die Figuren gespeichert werden, mit denen ein Zug
	 * ausgeführt werden kann
	 */
	private ArrayList<Spielfigur> zugFiguren = new ArrayList<Spielfigur>(0);
	/**
	 * Speichert die Anzahl der Figuren, die laufen können
	 */
	private int anzZugFiguren = 0;
	/**
	 * Zeigt an, ob sich ALLE Figuren auf ihren Startfeldern befinden.
	 */
	private boolean alleAufSpawn;

	/**
	 * Konstruktor für ein Spiel. Er schreibt ein Spielbrett direkt in das
	 * Attribut spielbrett.
	 */
	public Spiel() {
		setSpielbrett(new Spielbrett());
	}

	/**
	 * Setter für IstAmZug, der den Spieler in das Attribut schreiben soll, der
	 * gerade am Zug ist. Es kann nur ein Objekt der Klasse Spieler gespeichert
	 * werden.
	 * 
	 * @param sIAZ
	 *            - Übergebener Spieler, der gespeichert werden soll.
	 */
	public void setIstAmZug(Spieler sIAZ) {
		if (!(sIAZ instanceof Spieler))
			throw new RuntimeException(
					"Es kann nur ein Spielerobjekt am Zug sein!");
		this.istAmZug = sIAZ;
	}

	/**
	 * Getter, der den Spieler zurück gibt, der gerade am Zug ist.
	 * 
	 * @return istAmZug - Ein Objekt der Klasse Spieler.
	 */
	public Spieler getIstAmZug() {
		return this.istAmZug;
	}

	/**
	 * Setter für zugMöglich
	 * 
	 * @param zugMöglich
	 *            - true oder false boolean
	 */
	public void setZugMöglich(boolean zugMöglich) {
		this.zugMöglich = zugMöglich;
	}

	/**
	 * Getter für zugMöglich
	 * 
	 * @return zugMöglich - true oder false boolean
	 */
	public boolean getZugMöglich() {
		return this.zugMöglich;
	}

	/**
	 * Setter für das Spielbrett Es wird nur gesetzt, wenn es sich beim
	 * Übergabeparameter um ein Objekt der Klasse Spielbrett handelt.
	 * 
	 * @param spielbrett
	 *            - Objekt der Klasse Spielbrett.
	 */
	public void setSpielbrett(Spielbrett spielbrett) {
		if (!(spielbrett instanceof Spielbrett))
			throw new RuntimeException("Kein Spielfeld gesetzt");
		this.spielbrett = spielbrett;
	}

	/**
	 * Getter für das Spielbrett.
	 * 
	 * @return spielbrett - Objekt der Klasse Spielbrett.
	 */
	public Spielbrett getSpielbrett() {
		return this.spielbrett;
	}

	/**
	 * Setter für die Anzahl der teilnehmenden Spieler, der nur inkrementiert.
	 */
	public void incAnzahlSpieler() {
		this.anzahlSpieler++;
	}

	/**
	 * Getter für anzahlSpieler
	 * 
	 * @return anzahlSpieler - Anzahl der Spieler vom Typ int
	 */
	public int getAnzahlSpieler() {
		return this.anzahlSpieler;
	}

	/**
	 * Setter für hatBegonnen
	 * 
	 * @param hatBegonnen
	 *            - boolean
	 */
	public void setHatBegonnen(boolean hatBegonnen) {
		this.hatBegonnen = hatBegonnen;
	}

	/**
	 * Getter für hatBegonnen
	 * 
	 * @return hatbegonnen - boolean
	 */
	public boolean getHatBegonnen() {
		return this.hatBegonnen;
	}

	/**
	 * Setter für istBeendet
	 * 
	 * @param istBeendet
	 *            - boolean
	 */
	public void setIstBeendet(boolean istBeendet) {
		this.istBeendet = istBeendet;
	}

	/**
	 * Getter für istBeendet
	 * 
	 * @return istBeendet - boolean
	 */
	public boolean getIstBeendet() {
		return this.istBeendet;
	}

	/**
	 * Setter für AnzWürfe, der die Variable inkrementiert.
	 */
	public void incAnzWürfe() {
		this.anzWürfe++;
	}

	/**
	 * Getter für anzWürfe
	 * 
	 * @return anzWürfe - int
	 */
	public int getAnzWÜrfe() {
		return this.anzWürfe;
	}

	/**
	 * Setter für die Augenzahl HINWEIS: DebugMethode zu Testzwecken als
	 * hilfsmethode am Ende der Datei
	 * 
	 * @param augenzahl
	 *            - Augenzahl als int
	 */
	public void setAugenzahl(int augenzahl) {
		if (augenzahl < 1 || augenzahl > 6)
			throw new RuntimeException("Fehlerhaftes Würfelergebnis");
		this.augenzahl = augenzahl;
	}

	/**
	 * Getter für die Augenzahl
	 * 
	 * @return augenzahl - int Wert
	 */
	public int getAugenzahl() {
		return this.augenzahl;
	}

	/**
	 * Setter für die Anzahl der vorhandenen zugFiguren, inkrementiert
	 */
	public void incAnzZugFiguren() {
		if (this.anzZugFiguren < 0 || this.anzZugFiguren > 4)
			throw new RuntimeException("Fehöer: ZugFigurenLimit erreicht!");
		this.anzZugFiguren++;
	}

	/**
	 * Getter für anzTugFiguren
	 * 
	 * @return anzZugFiguren - int Wert
	 */
	public int getAnzZugFiguren() {
		return this.anzZugFiguren;
	}

	/**
	 * Setter, der Figuren in einer ArrayList speichert, falls sie für einen Zug
	 * infrage kommen. Übergabeparameter muss vom Typ Figur sein Die Farbe der
	 * übergebenen Figur muss mit der Farbe des Spielers übereinstimmen, der
	 * gerade am Zug ist. Es können maximal 4 Figuren in der Lage sein, zu
	 * ziehen.
	 * 
	 * @param figur
	 *            - Objekt vom Typ Figur
	 */
	public void setZugFiguren(Spielfigur figur) {
		if (!(figur instanceof Spielfigur))
			throw new RuntimeException("Keine Spielfigur ausgewählt");
		if (!(figur.getFarbe().equals(getIstAmZug().getFarbe())))
			throw new RuntimeException("Ungültige Figur ausgewählt!");
		if (getAnzZugFiguren() >= 4)
			throw new RuntimeException(
					"Maximale Anzahl an für einen Zug möglichen Figuren erreicht!");
		this.zugFiguren.add(figur);
		incAnzZugFiguren();
	}

	public Spielfigur getZugFiguren(Spielfigur figur) {
		int index;
		if (!(figur instanceof Spielfigur))
			throw new RuntimeException(
					"FEHLER: Die angeforderte zugFigur ist keine Spielfigur!");
		if (!(figur.getFarbe().equals(getIstAmZug().getFarbe())))
			throw new RuntimeException(
					"Die Angeforderte Figur gehört nicht zum ziehenden Spieler!");
		if (zugFiguren.contains(figur) == true) {
			index = zugFiguren.indexOf(figur);
			return zugFiguren.get(index);
		}

		else
			throw new RuntimeException("Angeforderte Figur existiert nicht");
	}

	/**
	 * Setter für alleAufSpawn
	 * 
	 * @param alleAufSpawn
	 *            - boolean
	 */
	public void setAlleAufSpawn(boolean alleAufSpawn) {
		this.alleAufSpawn = alleAufSpawn;
	}

	/**
	 * Getter für alleAufSpawn
	 * 
	 * @return alleAufSpawn - boolean
	 */
	public boolean getAlleAufSpawn() {
		return this.alleAufSpawn;
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
	public void spielerHinzufügen(String name, FarbEnum farbe, String verhalten) {
		if (getHatBegonnen() == true)
			throw new RuntimeException("Spiel hat schon begonnen");
		if (farbe == null)
			throw new RuntimeException("Spieler braucht eine Farbe");
		for (int i = 0; i <= 3; i++) {
			if (spieler[i] != null) {
				if (farbe.equals(spieler[i].getFarbe()))
					throw new RuntimeException("Farbe schon vorhanden");
			}
		}
		Startfeld[] startfelder = getSpielbrett().getAlleStartFelderEinerFarbe(
				farbe);
		Endfeld[] endfelder = getSpielbrett().getAlleEndFelderEinerFarbe(farbe);
		if (verhalten == null) {
			spieler[getAnzahlSpieler()] = new Spieler(name, farbe, startfelder,
					endfelder);
		} else if (verhalten != null) {
			spieler[getAnzahlSpieler()] = new Spieler(name, farbe, startfelder,
					endfelder, verhalten);
		}
		incAnzahlSpieler();
		if (getAnzahlSpieler() == 4) {
			setHatBegonnen(true);
		}

	}

	/**
	 * Methode, die das Spiel startet, so dass keine Spieler mehr hinzugefügt
	 * werden können. Sie setzt den ersten Spieler im Spieler Array als den
	 * Spieler, der am Zug ist.
	 */
	public void startSpiel() {
		setHatBegonnen(true);
		for (int i = 0; i < spieler.length; i++) {
			if (spieler[i] != null) {
				spieler[i].setAmZug(true);
				setIstAmZug(spieler[i]);
				return;
			}
		}
		setIstBeendet(true);

	}

	/**
	 * @author Felix Rosa Methode prüft ob übergebene Figur in Kombination mit
	 *         übergebener Würfelzahl ziehen kann. Dabei wird überprüft ob Zug
	 *         aus Spielfeld ins Endfeld oder Züge im Endfeld möglich sind!
	 * @param figur
	 *            - figur von der überprüft werden soll ob ziehen möglich ist
	 * @param augenZahl
	 *            - gewürfelte Augenzahl um die die Figur ziehen soll!
	 * @return boolean - true wenn ziehen möglich, false wenn nicht
	 */
	public boolean kannIchZiehen(Spielfigur figur, int augenZahl) {
		if (figur.binIchGespawnt() == true
				&& (!(figur.getBinIchAufEndpostion())) && figur != null) {
			if (figur.getIstImZiel() == true)
				if (kannZiehenEndfelder(figur, augenZahl) == true)
					return true;
			if ((figur.getFelderGelaufen() + augenZahl) > 39) {
				if (((figur.getFelderGelaufen() + augenZahl) - 39 <= 4)) {
					int tempSchritte = (figur.getFelderGelaufen() + augenZahl) - 39;
					if (kannZiehenEndfelder(figur, tempSchritte) == true) {
						figur.setKannInsZiel(true);
						return true;
					} else
						return false;
				}
			}
			Standardfeld Zielfeld = getSpielbrett().getStandardFelder()[figur
					.getFelderGelaufen() + augenZahl - 1];
			if (Zielfeld.getFigur() == null)
				return true;
			else if (figur.kannSchlagen(Zielfeld) == true)
				return true;
			else
				return false;
		} else
			return false;

	}

	/**
	 * Methode, die prüft, ob eine Figur, die entweder auf einem Standard- oder
	 * Endfeld steht, innerhalb der Endfelder ziehen kann. Sie wird nur von der
	 * kannZiehen-Methode aufgerufen, falls die Figur in die Endfelder einziehen
	 * soll.
	 * 
	 * @param figur
	 *            - Figur, deren Zug-Möglichkeiten abgefragt werden
	 * @param zuZiehen
	 *            anzahl der Züge, die innerhalb des Endfeldes noch getätigt
	 *            werden sollen
	 * @return booleanschen Wert, true falls die Figur ziehen kann, false falls
	 *         nicht
	 */
	private boolean kannZiehenEndfelder(Spielfigur figur, int zuZiehen) {
		Endfeld[] endfelderIstAmZug = istAmZug.getEndFelder();
		if (figur.getMeinFeld() instanceof Standardfeld) {
			if (zuZiehen > 4)
				return false;
			if (zuZiehen == 1) {
				if (endfelderIstAmZug[0].getFigur() == null)
					return true;
			}
			if (zuZiehen == 2) {
				if (endfelderIstAmZug[1].getFigur() == null
						&& kannZiehenEndfelder(figur, 1) == true)
					return true;
			}
			if (zuZiehen == 3) {
				if (endfelderIstAmZug[2].getFigur() == null
						&& kannZiehenEndfelder(figur, 2) == true) {
					return true;
				}
			}
			if (zuZiehen == 4) {
				if (endfelderIstAmZug[3].getFigur() == null
						&& kannZiehenEndfelder(figur, 3) == true) {
					return true;
				}
			}

			else
				return false;
		}

		else if (figur.getMeinFeld() instanceof Endfeld) {
			if (figur.getMeinFeld().equals(endfelderIstAmZug[0])) {
				if (zuZiehen > 3)
					return false;
				if (zuZiehen == 1) {
					if (endfelderIstAmZug[1].getFigur() == null)
						return true;
					else
						return false;
				}
				if (zuZiehen == 2) {
					if (kannZiehenEndfelder(figur, 1) == true
							&& endfelderIstAmZug[2] == null)
						return true;
					else
						return false;
				}
				if (zuZiehen == 3) {
					if (kannZiehenEndfelder(figur, 2) == true
							&& endfelderIstAmZug[3] == null)
						return true;
					else
						return false;
				}
			}
			if (figur.getMeinFeld().equals(endfelderIstAmZug[1])) {
				if (zuZiehen > 2)
					return false;
				if (zuZiehen == 1) {
					if (endfelderIstAmZug[2].getFigur() == null)
						return true;
					else
						return false;
				}
				if (zuZiehen == 2)
					if (kannZiehenEndfelder(figur, 1) == true
							&& endfelderIstAmZug[2] == null)
						return true;
			}

			if (figur.getMeinFeld().equals(endfelderIstAmZug[2])) {
				if (zuZiehen > 1)
					return false;
				if (endfelderIstAmZug[3].getFigur() == null)
					return true;
				else
					return false;
			}
		}
		return false;

	}

	// INARBEIT
	/**
	 * @author Felix Rosa Lässt ausgewählte Figur um die entsprechende
	 *         Würfelzahl ziehen.
	 * 
	 * @param figur
	 * @param augenZahl
	 */
	public void ziehen(Spielfigur figur, int augenZahl) {
		Standardfeld aktFeld = spielbrett.getStandardFelder()[figur
				.getFelderGelaufen() - 1];
		Standardfeld Zielfeld = spielbrett.getStandardFelder()[figur
				.getFelderGelaufen() + augenZahl - 1];
		Spieler istAmZug = this.istAmZug;
		if (figur.getIstGespawnt() == false && getAugenzahl() == 6) {
			if (spielbrett.getSpawnfeld(istAmZug.getFarbe()).getFigur() != null) {
				spielbrett.getSpawnfeld(istAmZug.getFarbe()).setFigur(figur);
			} else if (spielbrett.getSpawnfeld(istAmZug.getFarbe()).getFigur()
					.getFarbe().equals(istAmZug.getFarbe())) {
				// hier fehlt das Schlagen der bestehenden Figur auf dem
				// Spawnfeld
			}
		} else if (figur.getIstGespawnt() == true && getAugenzahl() == 6) {
			if (kannIchZiehen(figur, augenZahl) == true) {
				if (figur.kannSchlagen(Zielfeld)) {
					// hier fehlt das Schlagen der Figur auf dem Zielfeld!
				} else if (Zielfeld.getFigur() == null) {
					Spielfigur tempFig = aktFeld.getFigur();
					aktFeld.setFigur(null);
					spielbrett.getStandardFelder()[figur.getFelderGelaufen() - 1] = aktFeld;
					Zielfeld.setFigur(tempFig);
					spielbrett.getStandardFelder()[figur.getFelderGelaufen()
							+ augenZahl - 1] = Zielfeld;
					figur.setMeinFeld(Zielfeld);
					nächsterSpieler();
				}

			}

		} else if (figur.getIstGespawnt() == true && getAugenzahl() != 6) {
			if (figur.getIstImZiel() == true) {
				ziehenEndfelder(figur, augenZahl);
			} else if (figur.getKannInsZiel() == true) {
				int tempSchritte = (figur.getFelderGelaufen() + augenZahl) - 39;
				ziehenEndfelder(figur, tempSchritte);
				figur.setKannInsZiel(false);
				figur.setIstImZiel(true);
			} else if (kannIchZiehen(figur, augenZahl) == true) {
				if (figur.kannSchlagen(Zielfeld)) {
					// hier fehlt das Schlagen der bestehenden Figur auf dem
					// Zielfeld
				} else if (Zielfeld.getFigur() == null) {
					Spielfigur tempFig = aktFeld.getFigur();
					aktFeld.setFigur(null);
					spielbrett.getStandardFelder()[figur.getFelderGelaufen() - 1] = aktFeld;
					Zielfeld.setFigur(tempFig);
					spielbrett.getStandardFelder()[figur.getFelderGelaufen()
							+ augenZahl - 1] = Zielfeld;
					figur.setMeinFeld(Zielfeld);
					nächsterSpieler();
				}
			}

		} else {
			nächsterSpieler();
		}

	}

	/**
	 * Methode, die eine Figur um eine bestimmte Anzahl an Zügen in seinem
	 * Endfeld ziehen lässt.
	 * 
	 * @param figur
	 *            - Die Figur, die ziehen soll
	 * @param augenZahl
	 *            - Anzahl der Züge, die Figur ziehen soll
	 */
	public void ziehenEndfelder(Spielfigur figur, int augenZahl) {

		if (kannZiehenEndfelder(figur, augenZahl) != true) {
			throw new RuntimeException("Figur kann nicht ziehen!");
		}
		Endfeld[] endfelderIstAmZug = istAmZug.getEndFelder();
		endfelderIstAmZug[augenZahl - 1].setFigur(figur);
		figur.setMeinFeld(endfelderIstAmZug[augenZahl - 1]);
	}

	/**
	 * Methode zu DebugZwecken. Ermöglicht es, zu Testzwecken auf Spieler
	 * zuzugreifen, die nicht am Zug sind.
	 * 
	 * @param spielerzahl
	 *            - Bestimmter Spielerindex vom Typ int
	 * @return spieler - Ein OPbjekt vom Typ Spieler.
	 */
	public Spieler DebugGetSpieler(int spielerzahl) {
		return spieler[spielerzahl - 1];
	}

	/**
	 * Methode zu DebugZwecken. Ermöglicht es, eine Figur mit einem Zug vor die
	 * Endfelder zu setzen.
	 * 
	 * @param augenzahl
	 *            - int Wert
	 */
	public void DebugSetAugenzahl(int augenzahl) {
		// Unterschied zur Originalmethode
		if (augenzahl > 40 || augenzahl < 1)
			throw new RuntimeException("Fehlerhaftes Würfelergebnis");
		this.augenzahl = augenzahl;
	}

	/**
	 * Methode zum Auswählen der Figur, die ziehen soll über eine von außen
	 * (Spieler/ KI) übergebene Spielfeld-ID
	 * 
	 * @param id
	 *            - ID des Spielfeldes, auf dem sich die Figur vor Ausführen des
	 *            Zuges befindet
	 * @return figur - gibt die Figur, die ziehen soll zurück, falls diese
	 *         ziehen kann
	 */
	public Spielfigur wähleFigur(String id) {
		FarbEnum farbeIstAmZug = istAmZug.getFarbe();
		Spielfeld f = spielbrett.getFeld(id, farbeIstAmZug);
		Spielfigur figur = f.getFigur();
		if (kannIchZiehen(figur, augenzahl) != true)
			throw new FigurKannNichtZiehenException(String.format(
					"Figur %s kann nicht ziehen!", figur));
		else
			return figur;
	}

	/**
	 * @author Alexander Brückner
	 * @version 0.1 Methode für Anfangszug. Sind alle Figuren eines Spielers auf
	 *          Startfeldern wird dreimal gewürfelt. Wird eine 6 gewürfelt, wird
	 *          eine Figur ausgewählt, die ziehen soll. Kann die Figur nicht
	 *          ziehen oder ist nicht auf dem Startfeld, wird solange eine neue
	 *          gewählt, bis es möglich ist, raus aufs Feld zu gehen. Steht auf
	 *          dem jeweiligen Spawnpoint eine feindliche Figur, wird diese
	 *          durch die spawnende Figur geschlagen. Sind nicht alle Figuren
	 *          eines Spielers in der Startregion (also ist mindestens eine auf
	 *          einem Spielfeld), wird die Methode beendet.
	 * @param s
	 *            - Spieler
	 * @throws FigurKannNichtZiehenException
	 * @throws RuntimeException
	 */

	public void ersterZug() {
		// Sicherheitsabfragen, Abbruchbedingung
		Spieler s = this.getIstAmZug();
		if (s == null)
			throw new RuntimeException("Spieler darf nicht null sein");
		if (!s.alleAufSpawn())
			return;

		// Erfolgsbool anlegen
		boolean erfolg = false;

		// Zu spawnende Figur anlegen
		Spielfigur spawnMe = null;
		// Figur anlegen, die ggf. das Zeitliche segnet
		Spielfigur killMe = null;

		// Drei mal würfeln und alles durchchecken
		for (int i = 0; i < 3; i++) {

			if (s.getMeinWürfel().werfen() == 6) {

				// fragt ab, ob Spawnpoint der jeweiligen Farbe belegt ist
				if (spielbrett.getSpawnfeld(s.getFarbe()).getFigur() != null) {
					Spielfigur fig = spielbrett.getSpawnfeld(s.getFarbe())
							.getFigur();

					// falls ja, kann ich die Figur erledigen?
					// falls ja, Figur wählen und ab dafür..
					// falls nicht, ist der Zug nicht möglich, es wird nochmal
					// gewürfelt

					// Hab ich die gleiche Farbe wie die Figur, die auf meinem
					// Spawn steht?
					if (fig.getFarbe() != s.getFarbe()) {

						// Ja? Dann weg damit.
						// S1 ist mit Sicherheit belegt. (Die Methode hier kommt
						// sowieso nur dann zum Einsatz,
						// Wenn alle meine Figürchen auf den Startfeldern
						// stehen.)

						spawnMe = wähleFigur("S1");
						// spawnMe wird nun zum Mörder von killMe:
						killMe = spielbrett.getSpawnfeld(s.getFarbe())
								.getFigur();
						// Hier schlagen-Methode aufrufen, spawnMe erledigt
						// killMe

					}

				}

				// Ist das Startfeld überhaupt nicht belegt, -> Spawn!
				spawnMe = wähleFigur("S1");
				spawnMe.spawn(spielbrett.getSpawnfeld(s.getFarbe()));

			}

		}

	}

	public ArrayList<Spielfigur> alleZugFiguren() {
		return zugFiguren;
	}

	// // hack wird im offiziellen Spiel ersetzt. die Methode testwurf dann
	// durch das richtige würfeln
	public void würfeln(int hack) {
		if (getHatBegonnen() == false)
			throw new RuntimeException("Spiel hat noch nicht begonnen");
		int augenzahl = istAmZug.getMeinWürfel().testWurf(hack);
		setAugenzahl(augenzahl);
		for (Spielfigur sf : istAmZug.alleFiguren())
			if (kannIchZiehen(sf, getAugenzahl()) == true) {
				sf.setKannZiehen(true);
				setZugFiguren(sf);
			} else {
				if (kannSpawnen(sf))
					sf.setKannZiehen(true);
				setZugFiguren(sf);
			}
		if (!alleZugFiguren().isEmpty()
				&& alleZugFiguren().size() == istAmZug.getAnzFiguren())
			for (Spielfigur sf : alleZugFiguren())
				if (sf.binIchGespawnt() == false)
					setAlleAufSpawn(true);
				else {
					setAlleAufSpawn(false);
					break;
				}
		if (!alleZugFiguren().isEmpty()) {
			setZugMöglich(true);
			incAnzWürfe();
		} else {
			setZugMöglich(false);
			nächsterSpieler();
		}
	}

	private void nächsterSpieler() {
		for (int i = 0; i < spieler.length; i++) {
			if (i < 3) {
				if (istAmZug.equals(spieler[i])) {
					istAmZug = spieler[i + 1];
					spieler[i + 1].setAmZug(true);
					spieler[i].setAmZug(false);
				}
			}

			if (i == 3) {
				if (istAmZug.equals(spieler[i])) {
					istAmZug = spieler[0];
					spieler[0].setAmZug(true);
					spieler[i].setAmZug(false);
				}

			}
		}

	}

	public boolean kannSpawnen(Spielfigur figur) {
		if (getAugenzahl() == 6 && figur.binIchGespawnt() == false)
			if (getSpielbrett().getSpawnfeld(getIstAmZug().getFarbe())
					.getFigur() == null
					|| getSpielbrett().getSpawnfeld(getIstAmZug().getFarbe())
							.getFigur().getFarbe() != getIstAmZug().getFarbe())
				return true;
			else
				return false;
		else
			return false;
	}

	// Interface - ausgabeFiguren: Gibt alle Figuren aller Spieler aus
	public void ausgabeFiguren() {

		Spieler s = this.getIstAmZug();
		for (Spielfigur f : s.alleFiguren()) {
			System.out.println(f);
		}

	}

	public void ausgabeZugFiguren() {

		Spieler s = this.getIstAmZug();
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getKannZiehen()) {
				System.out.println(f);
			}
		}

	}

	public void ausgabeFigurenImZiel() {

		Spieler s = this.getIstAmZug();
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getIstImZiel()) {
				System.out.println(f);
			}
		}

	}

	public void ausgabeSpielerListe() {
		for (Spieler s : spieler) {
			if (s == null)
				continue;
			else {
				System.out.println(s);
			}
		}
	}

	public boolean zugDurchführen(String ID) {

		boolean zugErfolgreich;
		try {
			Spielfigur f = wähleFigur(ID);
			ziehen(f, this.getIstAmZug().getMeinWürfel().werfen());
			zugErfolgreich = true;
			System.out.println("Zug erfolgreich!");
			return zugErfolgreich;
		}

		catch (FigurKannNichtZiehenException e) {
			zugErfolgreich = false;
			System.out.println("Zug fehlgeschlagen!");
			return zugErfolgreich;
		}

	}

	public void ausgabeFigurenAufStartfeld() {
		Spieler s = this.getIstAmZug();
		for (Spielfigur f : s.alleFiguren()) {
			if (f.getMeinFeld() instanceof Startfeld) {
				System.out.println(f);
			}
		}

	}
}
