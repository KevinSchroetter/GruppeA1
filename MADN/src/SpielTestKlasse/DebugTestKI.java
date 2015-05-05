package SpielTestKlasse;

import Spiel.*;
import Basisklassen.Spieler;
import Basisklassen.Spielfigur;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

public class DebugTestKI {
	public static void main(String[] args) {

		Spiel s = new Spiel();
		s.neuerSpieler("Detlef", 1, 1);

		s.neuerSpieler("Hans-Joachim", 2, 2);

		s.starteSpiel();

		while (s.getStatus()) {

			try {
				s.rollTheDice();
				s.zugDurchfuehrenKI();
			}

			catch (Exception e) {
				e.printStackTrace();
				return;
			}
			if (s.getBeendet())
				break;
		}
		Spieler[] gamer = s.getSpieler();
		for (Spieler zocker : gamer) {
			try {
				for (Spielfigur f : zocker.alleFiguren()) {
					System.out.println(f);
				}
			} catch (NullPointerException npe) {
				System.out.println("gibbet nüsch");
			}
		}
		 System.out.println("DebugTestKI.main()");
	}
	

}
