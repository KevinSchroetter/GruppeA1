package Basisklassen;

import java.io.Serializable;
import java.util.ArrayList;

import Hilfsklassen.FigurKannNichtZiehenException;


/**
 * Die Klasse der defensiven KI, erbt von KI. Ueber den Konstruktor wird die Komposition zum Spieler gesetzt,
 *  ueber die zugWaehlen-Methode definiert.
 * @version 4.0
 * @author Anna Rosa
 *
 */
public class KI_Defensiv extends KI implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Konstruktor der defensiven KI, wird durch den Spieler-Konstruktor aufgerufen.
	 * @param s-Spieler, der eine KI werden soll
	 */
	public KI_Defensiv(Spieler s) {
		super(s);
	}
	
	
	/**
	 * Methode, die nach den Prioritaeten entscheidet, welche Figur laufen soll.
	 * Moegliche Faelle: 1. Es ist mindestens eine Figur im Spiel, dann laeuft die Spielfigur, die noch im
	 * Spiel und schon am Weitesten gelaufen ist, 2. es ist keine Figur aktiv im Spiel, dann wird 
	 * gespawnt. Die dritte Prioritaet schlagen kann vernachlaessigt werden, da entweder die weiteste
	 * Figur zieht, egal ob sie schlagen kann oder nicht, oder irgendeinen Figur spawnt und mit Glueck dabei
	 * schlaegt.
	 * Die Methode ruft die zugDurchfuehren-Methode auf, welche dann über den iBediener den Zug durchfuehrt.
	 */
	
	
	public String[] zugWaehlen(){
		String[] zugFelder=null;
		ArrayList<Spielfigur> kannZiehen = iB.ausgabeZugFiguren();
		if(kannZiehen==null)
			throw new FigurKannNichtZiehenException("Es wurde noch nicht gewürfelt. Bitte würfeln!");
		Spielfigur[] amWeitesten = new Spielfigur[kannZiehen.size()];
		for (int i = 0; i < kannZiehen.size(); i++) {
			amWeitesten[i] = kannZiehen.get(i);
		}
		for (int i = 0; i < amWeitesten.length-1; i++) {
			if (amWeitesten[i].getFelderGelaufen() > amWeitesten[i + 1]
					.getFelderGelaufen()) {
				Spielfigur temp = amWeitesten[i + 1];
				amWeitesten[i+1] = amWeitesten[i];
				amWeitesten[i] = temp;
			}
		}
		String id = "" + amWeitesten[amWeitesten.length - 1].getMeinFeld().getID();
		zugFelder=zugDurchfuehren(id);
		return zugFelder;
					
	}
}
