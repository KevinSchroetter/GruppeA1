package Basisklassen;
import Basisklassen.*;
/**
 * Dies ist die Klasse Spielbrett, in der über den Konstruktor das komplette Spielfeld inklusive Start- und Endfelder erstellt. 
 * Die Klasse Spielbrett besitzt die Attribute startFelderRot, startFelderBlau, startFelderGrün, startFelderGelb, endFelderRot, endFelderBlau, endFelderGrün, endFelderGelb, standardFelder , also 9 Arrays, in denen alle einzelnen Spielfelder gespeichert sind.
 *
 * 
 * @author Anna Rosa
 * @version 1.0
 *
 */
public class Spielbrett {
	private Spielfeld [] startFelderRot= new Spielfeld[4];
	private Spielfeld [] startFelderBlau= new Spielfeld[4];
	private Spielfeld [] startFelderGrün= new Spielfeld[4];
	private Spielfeld [] startFelderGelb= new Spielfeld[4];
	private Spielfeld [] endFelderRot= new Spielfeld [4];
	private Spielfeld [] endFelderBlau= new Spielfeld [4];
	private Spielfeld [] endFelderGrün= new Spielfeld [4];
	private Spielfeld [] endFelderGelb= new Spielfeld [4];
	private Spielfeld [] standardFelder= new Spielfeld[40];
	
	
	/**
	 * Der Konstruktor erstellt mit Hilfe der Methoden erstelleStartFelder, erstelleEndFelder und erstelleStandardFelder das Spielbrett.
	 */
	public Spielbrett(){
		erstelleStartFelder(FarbEnum.ROT, FarbEnum.BLAU, FarbEnum.GRÜN, FarbEnum.GELB);
		erstelleEndFelder(FarbEnum.ROT,FarbEnum.BLAU, FarbEnum.GRÜN, FarbEnum.GELB);
		erstelleStandardFelder();
		
	}
	/**
	 * Die Methode erstelleStartFelder erstellt für die jeweiligen übergebenen Farben je 4 Startfelder, die sie im Startfelder-Array der entsprechenden Farbe speichert.
	 * @param rot, eine Farbe des FarbEnums
	 * @param blau, eine Farbe des FarbEnums
	 * @param grün, eine Farbe des FarbEnums
	 * @param gelb, eine Farbe des FarbEnums
	 */
	private void erstelleStartFelder(FarbEnum rot, FarbEnum blau, FarbEnum grün, FarbEnum gelb){
		for(int i=0; i<4; i++){
			startFelderRot[i]=new Spielfeld.Startfeld(rot);
		}
		for(int i=0; i<4; i++){
			startFelderBlau[i]= new Spielfeld.Startfeld(blau);
		}
		for(int i=0; i<4; i++){
			startFelderGrün[i]= new Spielfeld.Startfeld(grün);
		}
		for(int i=0; i<4; i++){
			startFelderGelb[i]= new Spielfeld.Startfeld(gelb);
		}
		
	}

	/**
	 * Die Methode erstelleEndFelder erstellt für die jeweiligen übergebenen Farben je 4 Endfelder, die sie im Endfelder-Array der entsprechenden Farbe speichert.
	 * @param rot, eine Farbe des FarbEnums
	 * @param blau, eine Farbe des FarbEnums
	 * @param grün, eine Farbe des FarbEnums
	 * @param gelb, eine Farbe des FarbEnums
	 */
	private void erstelleEndFelder(FarbEnum rot, FarbEnum blau, FarbEnum grün, FarbEnum gelb){
		for(int i=0; i<4; i++){
			endFelderRot[i]=new Spielfeld.Endfeld(rot);
		}
		for(int i=0; i<4; i++){
			endFelderBlau[i]= new Spielfeld.Endfeld(blau);
		}
		for(int i=0; i<4; i++){
			endFelderGrün[i]= new Spielfeld.Endfeld(grün);
		}
		for(int i=0; i<4; i++){
			endFelderGelb[i]= new Spielfeld.Endfeld(gelb);
		}
	}
	
	/**
	 * Die Methode erstelleStandardFelder erstellt 40 Standardfelder, die es im StandardFelder-Array speichert.
	 */
	private void erstelleStandardFelder(){
		for(int i=0; i<40; i++ ){
			standardFelder[i]=new Spielfeld.Standardfeld();
		}
		
	}
	
	

}
