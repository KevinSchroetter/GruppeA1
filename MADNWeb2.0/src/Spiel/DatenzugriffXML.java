package Spiel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class DatenzugriffXML implements iDatenzugriff {

	@Override
	public Object openFile(String path, int mode) {
		// mode 1 = out, mode 0 = in

		switch (mode) {
		case 0:
			try {
				return new FileReader(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case 1:
			try {
				return new FileWriter(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			throw new IllegalArgumentException("Ungültiger Modus!");
		}

		return null;
	}

	@Override
	public void spielSpeichern(Object saveme, Object stream) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object spielLaden(Object stream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closeFile(Object o) {
		if ((!(o instanceof FileReader)) || (!(o instanceof FileWriter)))
			throw new IllegalArgumentException("Zu schließender Strom ungültig");
		else if (o instanceof FileReader) {
			try {
				((FileReader) o).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		else if (o instanceof FileWriter) {
			try {
				((FileWriter) o).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
