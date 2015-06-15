package Spiel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import Hilfsklassen.SpielXMLWrapper;

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
		if ((!(saveme instanceof SpielXMLWrapper))
				|| (!(stream instanceof FileWriter)))
			throw new IllegalArgumentException(
					"Strom oder Wrapperobjekt ungültig");

		else
			try {

				saveme = ((SpielXMLWrapper) saveme);
				stream = ((FileWriter) stream);
				
			SpielXMLWrapper	spiel = (SpielXMLWrapper) saveme;
			FileWriter schreiber = (FileWriter) stream;
				
				
				JAXBContext context = JAXBContext.newInstance(SpielXMLWrapper.class);
				Marshaller marshall = context.createMarshaller();
				marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshall.marshal(spiel, schreiber);

			}

			catch (Exception e) {
				e.printStackTrace();
			}

	}

	@Override
	public Object spielLaden(Object stream) {
		// TODO
		return null;
	}

	@Override
	public void closeFile(Object o) {
		if (o instanceof FileReader) {
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
