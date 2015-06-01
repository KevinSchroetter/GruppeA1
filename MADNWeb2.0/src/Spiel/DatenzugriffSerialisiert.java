package Spiel;

import java.io.*;

/**
 * Klasse DatenzugriffSerialisiert
 * Implementiert iDatenzugriff - bietet Dateioperationen für serialisiertes Speichern/Laden an
 * @author Alexander Brückner
 * @version 3.0
 * @since 3.0
 *
 */


public class DatenzugriffSerialisiert implements iDatenzugriff {

	
	/**
	 *openFile - öffnet Datei mit Modus (1 = Input, 2 = Output) an Pfad "path"
	 *Gibt einen FileInputStream bzw FileOutputStream zurück
	 *@param path - String
	 *@param mode- int
	 *@return Object
	 *@Exception IOException
	 *@Exception IllegalArgumentException
	 * 
	 ***/
	@Override
	public Object openFile(String path, int mode) {

		// 1 = Input, 2 = Output
		switch (mode) {
		case 1:

			try {

				FileInputStream fileIn = new FileInputStream(path);
				return fileIn;
			}

			catch (FileNotFoundException e) {
				System.out.println("Fehler beim öffnen der Datei");
				e.printStackTrace();
			}

		case 2:

			try {
				FileOutputStream fileOut = new FileOutputStream(path);
				return fileOut;
			}

			catch (FileNotFoundException e) {
				System.out.println("Fehler beim Erstellen der Datei!");
				return null;
			}

		default:
			throw new IllegalArgumentException("1 für input oder 2 für output!");
		}

	}

	/**
	 *
	 * spielSpeichern - speichert serialisiert ein Spiel
	 * @param saveme - Object
	 * @param stream - Object
	 * @Exception IOException
	 * @Exception IllegalArgumentException
	 * 
	 **/
	@Override
	public void spielSpeichern(Object saveme, Object stream) {
		
		//Parameterchecks

		if (!(stream instanceof FileOutputStream) || !(saveme instanceof Spiel)) {
			throw new IllegalArgumentException(
					"Ungültiger Dateistrom/Spielobjekt ungültig");
		}

		else {

			try {
				
				//Spielobjekt saveme speichern
				ObjectOutputStream oos = new ObjectOutputStream(
						(FileOutputStream) stream);
				oos.writeObject(saveme);

			}

			//Exceptionhandling
			catch (FileNotFoundException e) {
				System.out.println("Fehler beim Lesen/erstellen der Datei");
			}

			catch (IOException e) {
				System.out.println("Fehler beim Lesen/Erstellen der Datei");
			}

		}

	}

	/**
	 * SpielLaden - Lädt ein Spiel serialisiert as einer Datei
	 * @return Object
	 * @param stream - Object
	 * @Exception IllegalArgumentException
	 * @Exception IOException
	 * @Exception FileNotFoundException
	 * @Exception ClassNotFoundException
	 * **/
	@Override
	public Object spielLaden(Object stream) {

		//Parameterchecks
		if (!(stream instanceof FileInputStream)) {
			throw new IllegalArgumentException("Dateistrom ungültig!");
		}

		else {

			//Laden und Überprüfen
			try {
				ObjectInputStream ois = new ObjectInputStream(
						(FileInputStream) stream);
				Object buf = ois.readObject();
				if ((buf == null) || (!(buf instanceof Spiel))) {
					throw new IOException("Spielobjekt ungültig!");
				} else
					return buf;

			}

			
			//Exceptionhandling
			catch (FileNotFoundException e) {
				System.out.println("Fehler: Datei nicht gefunden!");
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				System.out.println("Fataler Fehler beim Öffnen der Datei!");
				e.printStackTrace();
				return null;
			} catch (ClassNotFoundException e) {
				System.out.println("Fataler Fehler: Klasse nicht gefunden!");
				e.printStackTrace();
				return null;
			}

		}

	}

	/**
	 * closeFile
	 * schließt übergebenen Datenstrom
	 * @param o - Object
	 * 
	 */
	
	@Override
	public void closeFile(Object o) {
		// TODO Auto-generated method stub

		//Parametercheck
		if ((!(o instanceof FileInputStream))
				&& (!(o instanceof FileOutputStream))) {
			throw new IllegalArgumentException(
					"Dateistrom ungültig - FileInputStream oder FileOutputStream!");
		}

		//Instanzprüfung und schließen von Inputstream
		else if (o instanceof FileInputStream) {

			try {
				((FileInputStream) o).close();
			}

			catch (IOException e) {
				System.out.println("Fehler beim schließen der Datei");
			}

		}
		//Instanzprüfung und schließen von Outputstream
		else if (o instanceof FileOutputStream) {

			try {
				((FileOutputStream) o).close();
			}

			catch (IOException e) {
				System.out.println("Fehler beim schließen der Datei");
			}

		}

	}


}
