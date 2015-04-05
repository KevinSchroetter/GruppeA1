package Spiel;
/**
 * Interface, ueber das allgemein gespeichert und geladen werden kann
 * @author Alexander Brueckner
 * @version 3.0
 * @since v2.3
 */
public interface iDatenzugriff{
	
public Object openFile(String path, int mode);
public void spielSpeichern(Object saveme, Object stream);
public Object spielLaden(Object stream);
public void closeFile(Object o);

}
