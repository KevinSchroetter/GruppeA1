package Spiel;

import java.io.IOException;
/**
 * Interface, ueber das allgemein gespeichert und geladen werden kann
 * @author Alexander Brueckner
 * @version 3.0
 * @since v2.3
 */
public interface iDatenzugriff{
	
public void spielSpeichern(Spiel s) throws IOException;
public Spiel spielLaden() throws IOException;

}
