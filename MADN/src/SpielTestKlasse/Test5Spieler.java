package SpielTestKlasse;

import Spiel.Spiel;
import Spiel.iBediener;

public class Test5Spieler {

	public static void main(String[] args) {
		iBediener i = new Spiel();
		
		i.neuerSpieler("Hermes", 5, 0);
		i.starteSpiel();
		i.ausgabeFiguren();
		i.werfen(6);
		i.zugDurchfuehren("S1");
		i.werfen(6);
		i.zugDurchfuehren("41");
		i.werfen(6);
		i.zugDurchfuehren("47");
		i.werfen(6);
		i.zugDurchfuehren("3");
		i.werfen(6);
		i.zugDurchfuehren("9");
		i.werfen(6);
		i.zugDurchfuehren("15");
		i.werfen(6);
		i.zugDurchfuehren("21");
		i.werfen(6);
		i.zugDurchfuehren("27");
		i.werfen(6);
		i.zugDurchfuehren("33");
		i.werfen(6);
		//i.zugDurchfuehren("39");
	
		
	}

}
