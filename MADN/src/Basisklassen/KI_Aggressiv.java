package Basisklassen;

import java.util.ArrayList;

import Einstellungen.FarbEnum;
import Hilfsklassen.FigurKannNichtZiehenException;

/**
 * Klasse der aggressiven KI, in der das Verhalten der KI implementiert wird.
 * @author Anna
 * 
 *
 */
public class KI_Aggressiv extends KI {

	private static final long serialVersionUID = 1L;


	/**
	 * Konstruktor der aggressiven KI, wird durch den Spieler-Konstruktor aufgerufen.
	 * @version 4.0
	 * @param s - der zukuenftige KI Spieler
	 */
	public KI_Aggressiv(Spieler s) {
		
		super(s);
		
	}
		
	
	/**
	 * Methode, die nach den Prioritaeten entscheidet, welche Figur laufen soll.
	 * Moegliche Faelle:  1. Eine Figur kann schlagen und dabei spawnen, 2. Figur kann nur schlagen -
	 * am weitesten gelaufene Figur laeuft , 3. Keine Figur kann schlagen, aber eine Figur kann spawnen, 
	 * 4. Keine Figur kann schlagen oder spawnen, dann laeuft die Figur, die am Weitesten vorne ist.
	 * Die Methode ruft die zugDurchfuehren-Methode auf, welche dann über den iBediener den Zug durchfuehrt.
	 */
	public void zugWaehlen() {
		ArrayList<Spielfigur> kannZiehen = iB.ausgabeZugFiguren();
		if(kannZiehen==null)
			throw new FigurKannNichtZiehenException("Es wurde noch nicht gewürfelt. Bitte würfeln!");
		ArrayList<Spielfigur> kannSchlagen = new ArrayList<Spielfigur>();

		for (Spielfigur figur : kannZiehen) {
			if (figur.getKannSchlagen() == true) {
				kannSchlagen.add(figur);
			}

		}
		if (!kannSchlagen.isEmpty()) {
			Spielfigur[] amWeitesten = new Spielfigur[kannSchlagen.size()];
			for (int i = 0; i < kannSchlagen.size(); i++) {
				Spielfigur figur = kannSchlagen.get(i);
				if (figur.getIstGespawnt() == false) {
					String id = "" + figur.getMeinFeld().getID();
					zugDurchfuehren(id);
					return;
				}
				amWeitesten[i] = figur;

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
			zugDurchfuehren(id);
			return;
		} else if (kannSchlagen.isEmpty()) {
			for (Spielfigur figur : kannZiehen) {
				if (figur.binIchGespawnt() == false) {
					String id = "" + figur.getMeinFeld().getID();
					zugDurchfuehren(id);
					return;
				}
			}

			Spielfigur[] amWeitesten = new Spielfigur[kannZiehen.size()];
			for (int i = 0; i < kannZiehen.size(); i++) {
				String idSpawnFeldCheck=kannZiehen.get(i).getMeinFeld().getID();
				switch(idSpawnFeldCheck){
					case "1":
						if(kannZiehen.get(i).getFarbe().equals(FarbEnum.ROT)){
							String id=kannZiehen.get(i).getMeinFeld().getID();
							zugDurchfuehren(id);
							return;
						}
						
					case "11":
						if(kannZiehen.get(i).getFarbe().equals(FarbEnum.BLAU)){
							String id=kannZiehen.get(i).getMeinFeld().getID();
							zugDurchfuehren(id);
							return;
						}
						
						
					case "21":
						if(kannZiehen.get(i).getFarbe().equals(FarbEnum.GRUEN)){
							String id=kannZiehen.get(i).getMeinFeld().getID();
							zugDurchfuehren(id);
							return;
						}
						
						
					case "31":
						if(kannZiehen.get(i).getFarbe().equals(FarbEnum.GELB)){
							String id=kannZiehen.get(i).getMeinFeld().getID();
							zugDurchfuehren(id);
							return;
						}
						
				}
					
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
			zugDurchfuehren(id);
		}

	}
}
