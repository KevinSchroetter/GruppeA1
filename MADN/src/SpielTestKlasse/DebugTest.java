package SpielTestKlasse;

import Basisklassen.Spielfigur;
import Spiel.*;

import java.util.Scanner;
import java.util.ArrayList;

public class DebugTest {

	public static void main(String[] args) {
		
		Spiel s = new Spiel();
		ArrayList<Spielfigur> figuren = null;
		System.out.println("----Arraylist erstellt ----");
		s.neuerSpieler("Detlef", 2, 0);
	//	s.neuerSpieler("Hans-Joachim", 1, 0);
		Scanner scan = new Scanner(System.in);
		s.starteSpiel();

		
		while(s.getStatus() && (!(s.getBeendet()))){
			
			
			s.rollTheDice();
			figuren = s.ausgabeFiguren();
			
			if(figuren != null){
				ArrayList<Spielfigur> list = s.ausgabeFiguren();
				for(int i = 0; i<4; i++){
					if(list.get(i).getKannZiehen()){
						s.zugDurchfuehren(list.get(i).getMeinFeld().getID());
					}
				}
			}
			
			
		}
		System.out.println(s.ausgabeFiguren());
		

		
		
	}
}
