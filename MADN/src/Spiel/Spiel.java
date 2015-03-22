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
	
	private void spielerHinzufügen(String name, FarbEnum farbe, String verhalten){
		if(hatBegonnen==true)
			throw new RuntimeException("Spiel hat schon begonnen");
		if(anzahlSpieler==3)
			throw new RuntimeException("Spiel schon voll");
		Startfeld[] startfelder=spielbrett.getAlleStartFelderEinerFarbe(farbe);
		for(int i=0; i<=3; i++){
			if(spieler[i]!=null){
				if(!(farbe.equals(spieler[i])))
					throw new RuntimeException("Farbe schon vorhanden");
			}
		}
		if(verhalten==null){
			spieler[anzahlSpieler]= new Spieler(name, farbe, startfelder); 
		}
		else if(verhalten!=null)
			spieler[anzahlSpieler]=new Spieler(name, farbe, startfelder, verhalten);
		anzahlSpieler++;
		
	}
	
	private void startSpiel(){
		hatBegonnen=true;
		spieler[0].setAmZug(true);
		istAmZug=spieler[0];
	}

}
