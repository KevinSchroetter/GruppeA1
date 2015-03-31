package Spiel;

import java.io.IOException;

public interface iDatenzugriff{
	
public void spielSpeichern(Spiel s) throws IOException;
public Spiel spielLaden() throws IOException;

}
