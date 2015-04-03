package Spiel;

import java.io.*;

public class DatenzugriffSerialisiert implements iDatenzugriff {

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

	@Override
	public void spielSpeichern(Object saveme, Object stream) {

		if (!(stream instanceof FileOutputStream) || !(saveme instanceof Spiel)) {
			throw new IllegalArgumentException(
					"Ungültiger Dateistrom/Spielobjekt ungültig");
		}

		else {

			try {

				ObjectOutputStream oos = new ObjectOutputStream(
						(FileOutputStream) stream);
				oos.writeObject(saveme);

			}

			catch (FileNotFoundException e) {
				System.out.println("Fehler beim Lesen/erstellen der Datei");
			}

			catch (IOException e) {
				System.out.println("Fehler beim Lesen/Erstellen der Datei");
			}

		}

	}

	@Override
	public Object spielLaden(Object stream) {

		if (!(stream instanceof FileInputStream)) {
			throw new IllegalArgumentException("Dateistrom ungültig!");
		}

		else {

			try {
				ObjectInputStream ois = new ObjectInputStream(
						(FileInputStream) stream);
				Object buf = ois.readObject();
				if ((buf == null) || (!(buf instanceof Spiel))) {
					throw new IOException("Spielobjekt ungültig!");
				} else
					return buf;

			}

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

	@Override
	public void closeFile(Object o) {
		// TODO Auto-generated method stub

		if ((!(o instanceof FileInputStream))
				&& (!(o instanceof FileOutputStream))) {
			throw new IllegalArgumentException(
					"Dateistrom ungültig - FileInputStream oder FileOutputStream!");
		}

		else if (o instanceof FileInputStream) {

			try {
				((FileInputStream) o).close();
			}

			catch (IOException e) {
				System.out.println("Fehler beim schließen der Datei");
			}

		}

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
