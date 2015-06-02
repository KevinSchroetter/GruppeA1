package Basisklassen;

import java.io.Serializable;

import Einstellungen.FarbEnum;
import Einstellungen.Settings;

/**
 * Klasse Endfeld erbt von Spielfeld. Ueber diese Klasse werden die Endfelder
 * von MADN erstellt. Auf diesen sammeln sich die Figuren um das Ende des Spiels
 * einzuleiten. Die Klasse hat die Attribute iD ueber die jedes Feld
 * identifizierbar ist sowie eine Farbe.
 * 
 * @author Felix Rosa (Felix_Frederic.Rosa@Student.Reutlingen-University.de)
 * @version 4.0
 * @since v1.0
 */
public class Endfeld extends Spielfeld implements Serializable {

	private static final long serialVersionUID = 1L;

	private FarbEnum farbe;
	
	/**
	 * Konstruktor fuer ein Endfeld. Ueber das Attribut iD wird dem Feld eine
	 * genaue ID zugewiesen ueber die das Feld in Kombination mit seiner Farbe
	 * identifizierbar ist. Mit dem Attribut Farbe wird dem Feld eine Farbe
	 * zugewiesen ueber die das Feld im SpielBean einem Spieler zugewiesen wird!
	 * Liegt die Eingabe der ID nicht im Wertebereich E1-E4 wird eine
	 * RuntimeException geworfen!
	 * 
	 * @param farbe - Farbe vom Typ FarbEnum des Feldes
	 * @param iD - ID vom Typ String des Feldes
	 * @exception RuntimeException Wenn Feld nicht im Wertebereich E1-E4
	 * 
	 */
	public Endfeld(String iD, FarbEnum farbe) {
		super();
		int check = 0;
		for(int i = 0; i < Settings.maxFiguren; i++)
			if(iD.contains("E"+(i+1)))
				check++;
		if(check > 0) setID(iD);
		else throw new RuntimeException("Muss E1-E"+Settings.maxFiguren +" sein!");
		this.farbe = farbe;
		setGuiID(iD+farbe.toString());
	}
	
	/**
	 * Oeffentlicher Getter der die Farbe des Feldes zurueckgibt
	 * 
	 * @return FarbEnum farbe - Farbe des Feldes
	 */
	public FarbEnum getFarbe() {
		return farbe;
	}

	/**
	 * Ueberschreiben der Equals. Zwei Objekte der Klasse Endfeld sind nur
	 * dann gleich, wenn ihre Ihre ID und ihre Farbe die gleichen sind.
	 * 
	 * @param obj - Uebergebenes Endfeld-Objekt - wird mit this verglichen
	 * @return boolean - gibt zurueck ob zwei verglichene Endfelder gleich sind
	 */
	@Override
	public boolean equals(Object obj) {
		Endfeld f = null;
		if (obj instanceof Endfeld)
			f = (Endfeld) obj;
		return f.getID() == this.getID()
				&& f.getFarbe().equals(this.getFarbe());
	}
	
	/**
	 * Ueberschriebene toString angepasst fuer die Klasse Endfeld. Wandelt die
	 * Farbe und die ID in einen String und gibt diesen aus.
	 * 
	 * @return String - gibt String mit Inhalt Farbe und ID des Feldes zurueck
	 */
	@Override
	public String toString() {
		return String.valueOf(this.getFarbe()) + " " + this.getID();
	}
	@Override
	public String toStringOhneFarbe() {
		return String.format("%s",this.getID());
	}
}
