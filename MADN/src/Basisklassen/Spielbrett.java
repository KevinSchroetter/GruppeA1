package Basisklassen;

import java.io.Serializable;

/**
 * Dies ist die Klasse Spielbrett, in der ueber den Konstruktor das komplette Spielfeld inklusive Start- und Endfelder erstellt. 
 * Die Klasse Spielbrett besitzt die Attribute startFelderRot, startFelderBlau, startFelderGruen, startFelderGelb, endFelderRot, endFelderBlau, endFelderGruen, endFelderGelb, standardFelder , also 9 Arrays, in denen alle einzelnen Spielfelder gespeichert sind.
 *
 * 
 * @author Anna Rosa
 * @version 3.0
 *
 */
public class Spielbrett implements Serializable {

	private static final long serialVersionUID = 1L;
	private Startfeld [] startFelderRot= new Startfeld[4];
	private Startfeld [] startFelderBlau= new Startfeld[4];
	private Startfeld [] startFelderGruen= new Startfeld[4];
	private Startfeld [] startFelderGelb= new Startfeld[4];
	private Endfeld [] endFelderRot= new Endfeld [4];
	private Endfeld [] endFelderBlau= new Endfeld [4];
	private Endfeld [] endFelderGruen= new Endfeld [4];
	private Endfeld [] endFelderGelb= new Endfeld [4];
	private Standardfeld [] standardFelder= new Standardfeld[40];
	
	
	
	
	/**
	 * Der Konstruktor erstellt mit Hilfe der Methoden erstelleStartFelder, erstelleEndFelder und erstelleStandardFelder das Spielbrett.
	 */
	public Spielbrett(){
		erstelleStartFelder();
		erstelleEndFelder();
		erstelleStandardFelder();
		
	}
	/**
	 * Methode, die das Array der Standardfelder zurueckgibt
	 * @return Standardfelder-Array
	 */
	public Standardfeld[] getStandardFelder(){
		return standardFelder;
	}
	
	/** 
	 * Ein Getter, der alle Startfelder der gewuenschten, uebergebenen Farbe zurueckgibt.
	 * @param farbe - Uebergebene Farb vom Typ FarbEnum.
	 * @return Startfeld[] - Array der Startfelder der jeweiligen Farbe
	 */
	public Startfeld[] getAlleStartFelderEinerFarbe(FarbEnum farbe){
		if(farbe.equals(FarbEnum.ROT))
			return getAlleStartFelderRot();
		if( farbe.equals(FarbEnum.BLAU))
			return getAlleStartFelderBlau();
		if(farbe.equals(FarbEnum.GRUEN))
			return getAlleStartFelderGruen();
		if( farbe.equals(FarbEnum.GELB))
				return getAlleStartFelderGelb();
		else return null;
	}
	/**
	 * Ein Getter, der alle Endfelder der gewuenschten, uebergebenen Farbe zurueckgibt.
	 * @param farbe - Uebergebene Farbe vom Typ FarbEnum.
	 * @return Endfeld[] - Array der Endfelder.
	 */
	public Endfeld[] getAlleEndFelderEinerFarbe(FarbEnum farbe) {
		if(farbe.equals(FarbEnum.ROT))
			return getAlleEndFelderRot();
		if( farbe.equals(FarbEnum.BLAU))
			return getAlleEndFelderBlau();
		if(farbe.equals(FarbEnum.GRUEN))
			return getAlleEndFelderGruen();
		if( farbe.equals(FarbEnum.GELB))
				return getAlleEndFelderGelb();
		else return null;
	}
	/**
	 * Ein Getter, der das Startfelder-Array der roten Startfelder zurueckgibt.
	 * @return startFelderRot
	 */
	public Startfeld[] getAlleStartFelderRot(){
		return  startFelderRot;
	}
	
	/**
	 * Ein Getter, der das Startfelder-Array der blauen Startfelder zurueckgibt.
	 * @return startFelderBlau
	 */
	public Startfeld[] getAlleStartFelderBlau(){
		return  startFelderBlau;
	}
	
	/**
	 * Ein Getter, der das Startfelder-Array der gruenen Startfelder zurueckgibt.
	 * @return startFelderGruen
	 */
	public Startfeld[] getAlleStartFelderGruen(){
		return  startFelderGruen;
	}
	
	/**
	 * Ein Getter, der das Startfelder-Array der gelben Startfelder zurueckgibt.
	 * @return startFelderGelb
	 */
	public Startfeld[] getAlleStartFelderGelb(){
		return  startFelderGelb;
	}
	
	/**
	 * Ein Getter, der das Endfelder-Array der roten Endfelder zurueckgibt.
	 * @return endFelderRot
	 */
	public Endfeld[] getAlleEndFelderRot(){
		return  endFelderRot;
	}
	/**
	 * Ein Getter, der das Endfelder-Array der blauen Endfelder zurueckgibt.
	 * @return endFelderRot
	 */
	public Endfeld[] getAlleEndFelderBlau(){
		return  endFelderBlau;
	}
	
	/**
	 * Ein Getter, der das Endfelder-Array der gruenen Endfelder zurueckgibt.
	 * @return endFelderGruen
	 */
	public Endfeld[] getAlleEndFelderGruen(){
		return  endFelderGruen;
	}
	
	/**
	 * Ein Getter, der das Endfelder-Array der gelben Endfelder zurueckgibt.
	 * @return endFelderGelb
	 */
	public Endfeld[] getAlleEndFelderGelb(){
		return  endFelderGelb;
	}
	
	/**
	 * Ein Getter, der das Standardfelder-Array zurueckgibt.
	 * @return standardFelderRot
	 */
	public Standardfeld[] getAlleStandardFelder(){
		return standardFelder;
	}
	/**
	 * Getter fuer die Inhalte des Arrays der roten Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurueck.
	 * @param index - Auswahl eines bestimmten Feldes ueber einen Index vom Typ int 
	 * @return Startfeld 
	 */
	public Startfeld getStartFelderRot(int index){
		if(index<0|index>startFelderRot.length)
			throw new RuntimeException("Gewuenschte Stelle nicht vorhanden.");
		return startFelderRot[index];
	}
	
	/**
	 * Getter fuer die Inhalte des Arrays der blauen Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurueck.
	 * @param index - Auswahl eines bestimmten Feldes ueber einen Index vom Typ int 
	 * @return Startfeld
	 */
	public Startfeld getStartFelderBlau(int index){
		if(index<0|index>startFelderBlau.length)
			throw new RuntimeException("Gewuenschte Stelle nicht vorhanden.");
		return startFelderBlau[index];
	}
	
	/**
	 * Getter fuer die Inhalte des Arrays der gruenen Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurueck.
	 * @param index - Auswahl eines bestimmten Feldes ueber einen Index vom Typ int 
	 * @return Startfeld
	 */
	public Startfeld getStartFelderGruen(int index){
		if(index<0|index>startFelderGruen.length)
			throw new RuntimeException("Gewuenschte Stelle nicht vorhanden.");
		return startFelderGruen[index];
	}
	
	/**
	 * Getter fuer die Inhalte des Arrays der gelben Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurueck.
	 * @param index - Auswahl eines bestimmten Feldes ueber einen Index vom Typ int 
	 * @return Startfeld
	 */
	public Startfeld getStartFelderGelb(int index){
		if(index<0|index>startFelderGelb.length)
			throw new RuntimeException("Gewuenschte Stelle nicht vorhanden.");
		return startFelderGelb[index];
	}
	
	/**
	 * Getter fuer die Inhalte des Arrays der Standardfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurueck.
	 * @param index - Auswahl eines bestimmten Feldes ueber einen Index vom Typ int 
	 * @return Standardfeld 
	 */
	public Standardfeld getStandardFelder(int index){
		if(index<0|index>standardFelder.length)
			throw new RuntimeException("Gewuenschte Stelle nicht vorhanden.");
		
		return standardFelder[index];
	}
	
	/**
	 * Getter fuer die Inhalte des Arrays der roten Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurueck.
	 * @param index - Auswahl eines bestimmten Feldes ueber einen Index vom Typ int 
	 * @return Endfeld
	 */
	public Endfeld getEndFelderRot(int index){
		if(index<0|index>endFelderRot.length)
			throw new RuntimeException("Gewuenschte Stelle nicht vorhanden.");
		return endFelderRot[index];
	}
	
	/**
	 * Getter fuer die Inhalte des Arrays der blauen Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurueck.
	 * @param index - Auswahl eines bestimmten Feldes ueber einen Index vom Typ int 
	 * @return Endfeld
	 */
	public Endfeld getEndFelderBlau(int index){
		if(index<0|index>endFelderBlau.length)
			throw new RuntimeException("Gewuenschte Stelle nicht vorhanden.");
		return endFelderBlau[index];
	}
	
	/**
	 * Getter fuer die Inhalte des Arrays der gruenen Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurueck.
	 * @param index - Auswahl eines bestimmten Feldes ueber einen Index vom Typ int 
	 * @return Endfeld
	 */
	public Endfeld getEndFelderGruen(int index){
		if(index<0|index>endFelderGruen.length)
			throw new RuntimeException("Gewuenschte Stelle nicht vorhanden.");
		return endFelderGruen[index];
	}
	
	
	
	/**
	 * Getter fuer die Inhalte des Arrays der gelben Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurueck.
	 * @param index - Auswahl eines bestimmten Feldes ueber einen Index vom Typ int 
	 * @return Endfeld
	 */
	public Endfeld getEndFelderGelb(int index){
		if(index<0|index>endFelderGelb.length)
			throw new RuntimeException("Gewuenschte Stelle nicht vorhanden.");
		return endFelderGelb[index];
	}
	
	/** 
	 * Methode, die ein gesuchtes Spielfeld, das ueber ID und unter Umstaenden Farbe identifiziert wird.
	 * @param id - ID des gesuchten Felds
	 * @param farbe - Farbe, falls Feld Start- oder Endfeld ist, bei Standartfeldern null
	 * @return gesuchtes Spielfeld
	 *
	 */
	
	public Spielfeld getFeld( String id, FarbEnum farbe){
		if(id.startsWith("S")){
			Startfeld[] felder= getAlleStartFelderEinerFarbe(farbe);
			for( int i=0; i<felder.length; i++){
				if(felder[i].getID().equals(id))
					return felder[i];
			}
		}
		
		else if(id.startsWith("E")){
			Endfeld[] efelder= getAlleEndFelderEinerFarbe(farbe);
			for( int i=0; i<efelder.length; i++){
				if(efelder[i].getID().equals(id))
					return efelder[i];
			}
			
		}
		else if(id.matches("[0-9]+")){
			int iD=Integer.parseInt(id);
			if(iD<1| iD>40)
				throw new RuntimeException("ID nicht gueltig");
			else
				return standardFelder[iD-1];
		}
		else
			throw new RuntimeException("Falsche ID");
		return null;
	}
	
	/**
	 * Methode, die das Spawnfeld der uebergebenen Farbe zurueckgibt.
	 * @param farbe - die Farbe, fuer die das Spawnfeld gesucht wird
	 * @return spawnfeld der Farbe
	 */
	public Standardfeld getSpawnfeld(FarbEnum farbe){
		switch(farbe){
		case ROT:
			return standardFelder[0];
		case BLAU:
			return standardFelder[10];
		case GRUEN:
			return standardFelder[20];
		case GELB:
			return standardFelder[30];
		default:
			throw new RuntimeException("Falsche Farbe gewaehlt");		
		}
	}
	/**
	 * Die Methode erstelleStartFelder erstellt fuer die jeweiligen Farben je 4 Startfelder, die sie im Startfelder-Array der entsprechenden Farbe speichert.
	
	 */
	private void erstelleStartFelder(){
		for(int i=0; i<4; i++){
			String iD="S"+(i+1);
			startFelderRot[i]=new Startfeld(iD, FarbEnum.ROT);
		}
		
		for(int i=0; i<4; i++){
			String iD="S"+(i+1);
			startFelderBlau[i]= new Startfeld(iD, FarbEnum.BLAU);
		}
		for(int i=0; i<4; i++){
			String iD="S"+(i+1);
			startFelderGruen[i]= new Startfeld(iD, FarbEnum.GRUEN);
		}
		for(int i=0; i<4; i++){
			String iD="S"+(i+1);
			startFelderGelb[i]= new Startfeld(iD, FarbEnum.GELB);
		}
		
	}

	/**
	 * Die Methode erstelleEndFelder erstellt fuer die jeweiligen Farben je 4 Endfelder, die sie im Endfelder-Array der entsprechenden Farbe speichert.
	 */
	private void erstelleEndFelder(){
		for(int i=0; i<4; i++){
			String iD="E"+(i+1);
			endFelderRot[i]=new Endfeld(iD, FarbEnum.ROT);
		}
		for(int i=0; i<4; i++){
			String iD="E"+(i+1);
			endFelderBlau[i]= new Endfeld(iD, FarbEnum.BLAU);
		}
		for(int i=0; i<4; i++){
			String iD="E"+(i+1);
			endFelderGruen[i]= new Endfeld(iD, FarbEnum.GRUEN);
		}
		for(int i=0; i<4; i++){
			String iD="E"+(i+1);
			endFelderGelb[i]= new Endfeld(iD, FarbEnum.GELB);
		}
	}
	
	/**
	 * Die Methode erstelleStandardFelder erstellt 40 Standardfelder, die es im StandardFelder-Array speichert.
	 */
	private void erstelleStandardFelder(){
		for(int i=0; i<40; i++ ){
			int iD= i+1;
			standardFelder[i]=new Standardfeld(iD);
		}
		
	}
	
	/**
	 * Override der toString-Methode, die alle Spielfelder zurueckgibt.
	 * @return rueckgabe - alle Spielfelder, geordner nach: Startfelder - Standardfelder - Endfelder
	 */
	@Override
	public String toString(){
		String rueckgabe= new String(" Rote Startfelder: ");
		for(int i=0; i<4; i++){
			rueckgabe=rueckgabe+ startFelderRot[i].toString() + " , ";
		}
		rueckgabe+="\n Blaue Startfelder: " ;
		for(int i=0; i<4; i++){
			rueckgabe=rueckgabe+ startFelderBlau[i].toString() + " , ";
		}
		rueckgabe+="\n Gruene Startfelder: " ;
		for(int i=0; i<4; i++){
			rueckgabe=rueckgabe+ startFelderGruen[i].toString() + " , ";
		}
		rueckgabe+="\n Gelbe Startfelder: " ;
		for(int i=0; i<4; i++){
			rueckgabe=rueckgabe+ startFelderGelb[i].toString() + " , ";
		}
		rueckgabe+="\n Standardfelder: " ;
		for(int i=0; i<40; i++){
			rueckgabe=rueckgabe+ standardFelder[i].toString() + " , ";
		}
		rueckgabe+="\n Rote Endfelder: " ;
		for(int i=0; i<4; i++){
			rueckgabe=rueckgabe+ endFelderRot[i].toString() + " , ";
		}
		rueckgabe+="\n Blaue Endfelder: " ;
		for(int i=0; i<4; i++){
			rueckgabe=rueckgabe+ endFelderBlau[i].toString() + " , ";
		}
		rueckgabe+="\n Gruene Endfelder: " ;
		for(int i=0; i<4; i++){
			rueckgabe=rueckgabe+ endFelderGruen[i].toString() + " , ";
		}
		rueckgabe+= "\n Gelbe Endfelder: " ;
		for(int i=0; i<4; i++){
			rueckgabe=rueckgabe+ endFelderGelb[i].toString() + " , ";
		}
		return(rueckgabe);
		
	}
}
