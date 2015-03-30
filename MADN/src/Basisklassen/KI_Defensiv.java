package Basisklassen;

import java.util.ArrayList;

import Spiel.iBediener;


/**
 * Die Klasse der defensiven KI, erbt von KI. Über den Konstruktor wird die Komposition zum Spieler gesetzt.
 * @author Anna Rosa
 *
 */
public class KI_Defensiv extends KI {
	iBediener iB;
	/**
	 * Konstruktor der defensiven KI, wird durch den Spieler-Konstruktor aufgerufen.
	 * @param s-Spieler, der eine KI werden soll
	 */
	public KI_Defensiv(Spieler s) {
		super(s);
	}
	
	
	/**
	 * Methode, die nach den Prioritäten entscheidet, welche Figur laufen soll.
	 * Mögliche Fälle: 1. Es ist mindestens eine Figur im Spiel, dann läuft die Spielfigur, die noch im
	 * Spiel und schon am Weitesten gelaufen ist, 2. es ist keine Figur aktiv im Spiel, dann wird 
	 * gespawnt. Die dritte Priorität schlagen kann vernachlässigt werden, da entweder die weiteste
	 * Figur zieht, egal ob sie schlagen kann oder nicht, oder irgendeinen Figur spawnt und mit Glück dabei
	 * schlägt.
	 */
	
	
	public void zugWählen(){
		ArrayList<Spielfigur> kannZiehen = iB.ausgabeZugFiguren();
		Spielfigur[] amWeitesten = new Spielfigur[kannZiehen.size()];
		for (int i = 0; i < kannZiehen.size(); i++) {
			amWeitesten[i] = kannZiehen.get(i);
		}
		for (int i = 0; i < amWeitesten.length; i++) {
			if (amWeitesten[i].getFelderGelaufen() > amWeitesten[i + 1]
					.getFelderGelaufen()) {
				Spielfigur temp = amWeitesten[i + 1];
				amWeitesten[i] = amWeitesten[i + 1];
				amWeitesten[i + 1] = temp;
			}
		}
		String id = "" + amWeitesten[amWeitesten.length - 1].getID();
		iB.zugDurchführen(id);
					
	}


}
