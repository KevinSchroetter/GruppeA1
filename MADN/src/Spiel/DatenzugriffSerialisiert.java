package Spiel;

import java.io.*;

public class DatenzugriffSerialisiert implements iDatenzugriff {
	/**
	 * @author Alexander Brückner
	 * @version 1.0
	 * 
	 *          Klasse {@link DatenzugriffSerialisiert} implementiert Interface
	 *          {@link iDatenzugriff} mittels Serialisierung.
	 * 
	 */

	/**
	 * Standardkonstruktor
	 */
	public DatenzugriffSerialisiert() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *
	 * Methode spielSpeichern überrschreibt Methode aus Interface. Serialisiert
	 * ein Spiel und speichert es in "savegame.ser"
	 * 
	 * @param s
	 *            - Spiel
	 * @throws {@link IOException}
	 * @throws {@link FileNotFoundException}
	 * 
	 */

	@Override
	public void spielSpeichern(Spiel s) throws IOException {
		if (s == null)
			throw new IllegalArgumentException("Ungülltiges Spiel");
		ObjectOutputStream speicherStream = null;

		try {
			speicherStream = new ObjectOutputStream(new FileOutputStream(
					"savegame.ser"));
			speicherStream.writeObject(s);
			System.out.println("Erfolgreich gespeicherrt!");

		}

		catch (FileNotFoundException e) {
			System.out.println("Fehler beim erstelllen der Datei!");
			e.printStackTrace();
		}

		catch (IOException e) {
			System.out.println("Fehler beim Speichern der Datei!");
			e.printStackTrace();
		}

		finally {
			try {
				speicherStream.close();
			}

			catch (IOException e) {
				System.out.println("Fehler beim schließen derr Datei!");
				e.printStackTrace();
			}

		}

	}

	/**
	 *
	 * Methode spielLaden überrschreibt Methode aus Interface. Deserialisiert
	 * ein Spiel aus "savegame.ser"
	 * 
	 * @param s
	 *            - Spiel
	 * @throws {@link IOException}
	 * @throws {@link FileNotFoundException}
	 * @throws {@link ClassNotFoundException}
	 * 
	 */

	@Override
	public Spiel spielLaden() throws IOException {
		Spiel s = null;
		ObjectInputStream ladenStream = null;

		try {

			ladenStream = new ObjectInputStream(new FileInputStream(
					"savegame.ser"));
			s = (Spiel) ladenStream.readObject();
			return s;
		}

		catch (FileNotFoundException e) {
			System.out.println("Fehler beim Laden: Datei nicht vorhanden!");
			e.printStackTrace();
			return null;
		}

		catch (IOException e) {
			System.out.println("Fehler beim Laden der Datei!");
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Fataler Fehler: Klasse nicht gefunden");
			e.printStackTrace();
			return null;
		}

		finally {
			try {
				ladenStream.close();
			}

			catch (IOException e) {
				System.out.println("Fehler beim schließen der Datei!");
			}
		}

	}

}
