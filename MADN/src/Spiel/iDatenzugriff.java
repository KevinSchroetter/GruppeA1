package Spiel;

/**
 * Interface, ueber das allgemein gespeichert und geladen werden kann
 * 
 * @author Alexander Brueckner
 * @version 3.0
 * @since v2.3
 */
public interface iDatenzugriff {

	/**
	 * openFile - gibt einen Datenstrom zurück. Rückgabetyp hängt von Parameter
	 * mode ab.
	 * 
	 * @return Object
	 * @param path
	 *            - String
	 * @param mode
	 *            - int
	 * */
	public Object openFile(String path, int mode);

	/**
	 * spieSpeichern - Speichert ein Objekt in den übergebenen Stream. (Setzt
	 * FileInputStream voraus)
	 * 
	 * @param stream
	 *            - Object
	 * @param saveme
	 *            - Object
	 * */

	public void spielSpeichern(Object saveme, Object stream);

	/**
	 * spielLaden - Lädt ein Objekt aus dem übergebenen Strom (FileInputStream
	 * vorrausgesetzt)
	 * 
	 * 
	 * @param stream
	 *            - Object
	 * @return Object
	 * */

	public Object spielLaden(Object stream);
	
	/**
	 * closeFile- schließt den übergebenen Datenstrom
	 * 
	 * 
	 * @param stream
	 *            - Object
	 * */


	public void closeFile(Object o);

}
