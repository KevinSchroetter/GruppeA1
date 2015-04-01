package Basisklassen;

import java.util.ArrayList;

import Spiel.iBediener;

public class KI_Aggressiv extends KI {

	private static final long serialVersionUID = 1L;

	iBediener iB;
	/**
	 * Konstruktor der aggressiven KI, wird durch den Spieler-Konstruktor aufgerufen.
	 * @version 3.0
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
	 */
	public void zugWaehlen() {
		ArrayList<Spielfigur> kannZiehen = iB.ausgabeZugFiguren();
		ArrayList<Spielfigur> kannSchlagen = new<Spielfigur> ArrayList();

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
					iB.zugDurchfuehren(id);
					return;
				}
				amWeitesten[i] = figur;

			}
			for (int i = 0; i < amWeitesten.length - 1; i++) {
				if (amWeitesten[i].getFelderGelaufen() > amWeitesten[i + 1]
						.getFelderGelaufen()) {
					Spielfigur temp = amWeitesten[i + 1];
					amWeitesten[i] = amWeitesten[i + 1];
					amWeitesten[i + 1] = temp;
				}
			}
			String id = "" + amWeitesten[amWeitesten.length - 1].getMeinFeld().getID();
			iB.zugDurchfuehren(id);
			return;
		} else if (kannSchlagen.isEmpty()) {
			for (Spielfigur figur : kannZiehen) {
				if (figur.binIchGespawnt() == false) {
					String id = "" + figur.getMeinFeld().getID();
					iB.zugDurchfuehren(id);
					return;
				}
			}

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
			String id = "" + amWeitesten[amWeitesten.length - 1].getMeinFeld().getID();
			iB.zugDurchfuehren(id);
		}

	}

}
