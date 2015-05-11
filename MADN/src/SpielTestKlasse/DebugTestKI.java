package SpielTestKlasse;

import Spiel.*;

import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import GUI.Stromwrapper;
import Basisklassen.Spieler;
import Basisklassen.Spielfigur;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;

public class DebugTestKI extends Thread {

	public static void main(String[] args) {

		PrintStream logOutput;
		File datei;
		FileOutputStream fileOut = null;
		Date time;

		DebugTestKI dTKI = new DebugTestKI();
		try {
			dTKI.start();
		} catch (Exception e) {

		}

	}

	@Override
	public void run() {

		Spiel s = new Spiel();
		s.neuerSpieler("Detlef", 1, 2);
		s.neuerSpieler("Hans-Joachim", 2, 2);
		s.neuerSpieler("JetztKackkktsAb",3,1);
		s.starteSpiel();
		while (!(s.getBeendet())) {
			
			try {

				s.rollTheDice();
				s.zugDurchfuehrenKI();

				if (s.getBeendet()){
					
					System.out.println("Endpositionen von Figuren:");
					Spieler[] ausgabeSpieler = s.getSpieler();
					
					Spielfigur [][] ausgabeFiguren = new Spielfigur[4][4];
					
					for(int i = 0; i<4; i++){
						if(ausgabeSpieler[i] == null) continue;
							ausgabeFiguren[i] = ausgabeSpieler[i].alleFiguren();
					}
					
					for(int i = 0; i<4; i++){
						
						
						for(Spielfigur f : ausgabeFiguren[i]){
							if(f == null) continue;
							System.out.println(f);
						}
					}
					
					

					// System.out.println("Debugger break");
					break;}

			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
