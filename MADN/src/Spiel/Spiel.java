package Spiel;
import Basisklassen.*;

public class Spiel {
	private Spieler [] spieler= new Spieler[4];
	private Spieler istAmZug;
	private Spielbrett spielbrett;
	private int anzahlSpieler;
	private boolean hatBegonnen=false;
	private boolean istBeendet=false;
	
	public Spiel(){
		spielbrett= new Spielbrett();
	}
	/** 
	 * Methode zum Hinzufügen eines neuen Spielers, solange das Spiel noch nicht gestartet ist. Sind Spieler im Spiel, so wird Spiel automatisch begonnen.
	 * @param name - gewünschter Name des Spielers
	 * @param farbe - gewünschte Farbe des Spielers
	 * @param verhalten - Falls null: Menschlicher Spieler, sonst: KI mit dem übergebenen Verhalten;
	 */
	private void spielerHinzufügen(String name, FarbEnum farbe, String verhalten){
		if(hatBegonnen==true)
			throw new RuntimeException("Spiel hat schon begonnen");
		for(int i=0; i<=3; i++){
			if(spieler[i]!=null){
				if(!(farbe.equals(spieler[i])))
					throw new RuntimeException("Farbe schon vorhanden");
			}
		}
		Startfeld[] startfelder=spielbrett.getAlleStartFelderEinerFarbe(farbe);
		if(verhalten==null){
			spieler[anzahlSpieler]= new Spieler(name, farbe, startfelder); 
		}
		else if(verhalten!=null){
			spieler[anzahlSpieler]=new Spieler(name, farbe, startfelder, verhalten);
		}
		anzahlSpieler++;
		if(anzahlSpieler==3)
			hatBegonnen=true;
		
	}
	/**
	 * Methode, die das Spiel startet, so dass keine Spieler mehr hinzugefügt werden können. Sie setzt den ersten Spieler im Spieler Array als den Spieler, der am Zug ist.
	 */
	private void startSpiel(){
		hatBegonnen=true;
		spieler[0].setAmZug(true);
		istAmZug=spieler[0];
	}
	/**
	 * Methode, die prüft, ob eine Figur innerhalb der Endfelder um die gewollte Anzahl der Züge ziehen kann.
	 * @param figur - Die Figur, die ziehen soll
	 * @param zuZiehen - Anzahl der Züge, die Figur ziehen soll
	 * @return
	 */
	private boolean kannZiehenEndfelder( Spielfigur figur, int zuZiehen){
		if(zuZiehen>4)
			return false;
		Endfelder[] endfelderIstAmZug= istAmZug.getEndfelder();
		if(zuZiehen==1){
			if(endfelderIstAmZug[0].getFigur()== null)
				return true;
		}
		if(zuZiehen==2){
			if(endfelderIstAmZug[1].getFigur()==null&& kannZiehenEndfelder(figur, 1)==true)
				return true;
		}
		if(zuZiehen==3){
			if(endfelderIstAmZug[2].getFigur()==null&& kannZiehenEndfelder(figur, 2)==true){
				return true;
			}
		}
		if(zuZiehen==4){
			if(endfelderIstAmZug[3].getFigur()==null&&kannZiehenEndfelder(figur,3)==true){
				return true;
			}
		}
		
		else
			return false;
				
	}
	/**
	 * Methode, die eine Figur um eine bestimmte Anzahl an Zügen in seinem Endfeld ziehen lässt.
	 * @param figur - Die Figur, die ziehen soll
	 * @param zuZiehen - Anzahl der Züge, die Figur ziehen soll
	 */
	private void ziehenEndfelder(Spielfigur figur, int zuZiehen){
		if(kannZiehenEndfelder(figur, zuZiehen)!=true){
			throw new RuntimeException("Figur kann nicht ziehen!");
		}
		Endfelder[] endfelderIstAmZug= istAmZug.getEndfelder();
		endfelderIstAmZug[zuZiehen-1].setFigur(figur);
		figur.setMeinFeld(endfelderIstAmZug[zuZiehen-1]);
		
	}
	
	

}
