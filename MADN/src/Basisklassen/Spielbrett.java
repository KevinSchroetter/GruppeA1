package Basisklassen;

/**
 * Dies ist die Klasse Spielbrett, in der über den Konstruktor das komplette Spielfeld inklusive Start- und Endfelder erstellt. 
 * Die Klasse Spielbrett besitzt die Attribute startFelderRot, startFelderBlau, startFelderGrün, startFelderGelb, endFelderRot, endFelderBlau, endFelderGrün, endFelderGelb, standardFelder , also 9 Arrays, in denen alle einzelnen Spielfelder gespeichert sind.
 *
 * 
 * @author Anna Rosa
 * @version 1.3
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
		erstelleStartFelder();
		erstelleEndFelder();
		erstelleStandardFelder();
		
	}
	
	/**
	 * Getter für die Inhalte des Arrays der roten Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index 
	 * @return Spielfeld 
	 */
	public Spielfeld getStartFelderRot(int index){
		if(index<0|index>startFelderRot.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return startFelderRot[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der blauen Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Spielfeld 
	 */
	public Spielfeld getStartFelderBlau(int index){
		if(index<0|index>startFelderBlau.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return startFelderBlau[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der grünen Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Spielfeld
	 */
	public Spielfeld getStartFelderGrün(int index){
		if(index<0|index>startFelderGrün.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return startFelderGrün[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der gelben Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Spielfeld
	 */
	public Spielfeld getStartFelderGelb(int index){
		if(index<0|index>startFelderGelb.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return startFelderGelb[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der Standardfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Spielfeld 
	 */
	public Spielfeld getStandardFelder(int index){
		if(index<0|index>standardFelder.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		
		return standardFelder[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der roten Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Spielfeld
	 */
	public Spielfeld  getEndFelderRot(int index){
		if(index<0|index>endFelderRot.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return endFelderRot[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der blauen Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Spielfeld
	 */
	public Spielfeld getEndFelderBlau(int index){
		if(index<0|index>endFelderBlau.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return endFelderBlau[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der grünen Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Spielfeld
	 */
	public Spielfeld getEndFelderGrün(int index){
		if(index<0|index>endFelderGrün.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return endFelderGrün[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der gelben Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Spielfeld
	 */
	public Spielfeld getEndFelderGelb(int index){
		if(index<0|index>endFelderGelb.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return endFelderGelb[index];
	}
	/**
	 * Die Methode erstelleStartFelder erstellt für die jeweiligen Farben je 4 Startfelder, die sie im Startfelder-Array der entsprechenden Farbe speichert.
	
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
			startFelderGrün[i]= new Startfeld(iD, FarbEnum.GRÜN);
		}
		for(int i=0; i<4; i++){
			String iD="S"+(i+1);
			startFelderGelb[i]= new Startfeld(iD, FarbEnum.GELB);
		}
		
	}

	/**
	 * Die Methode erstelleEndFelder erstellt für die jeweiligen Farben je 4 Endfelder, die sie im Endfelder-Array der entsprechenden Farbe speichert.
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
			endFelderGrün[i]= new Endfeld(iD, FarbEnum.GRÜN);
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
	 * Override der toString-Methode, die alle Spielfelder zurückgibt.
	 * @return rückgabe
	 */
	@Override
	public String toString(){
		String rückgabe= new String(" Rote Startfelder: ");
		for(int i=0; i<4; i++){
			rückgabe=rückgabe+ startFelderRot[i].toString() + " , ";
		}
		rückgabe+="\n Blaue Startfelder: " ;
		for(int i=0; i<4; i++){
			rückgabe=rückgabe+ startFelderBlau[i].toString() + " , ";
		}
		rückgabe+="\n Grüne Startfelder: " ;
		for(int i=0; i<4; i++){
			rückgabe=rückgabe+ startFelderGrün[i].toString() + " , ";
		}
		rückgabe+="\n Gelbe Startfelder: " ;
		for(int i=0; i<4; i++){
			rückgabe=rückgabe+ startFelderGelb[i].toString() + " , ";
		}
		rückgabe+="\n Standardfelder: " ;
		for(int i=0; i<40; i++){
			rückgabe=rückgabe+ standardFelder[i].toString() + " , ";
		}
		rückgabe+="\n Rote Endfelder: " ;
		for(int i=0; i<4; i++){
			rückgabe=rückgabe+ endFelderRot[i].toString() + " , ";
		}
		rückgabe+="\n Blaue Endfelder: " ;
		for(int i=0; i<4; i++){
			rückgabe=rückgabe+ endFelderBlau[i].toString() + " , ";
		}
		rückgabe+="\n Grüne Endfelder: " ;
		for(int i=0; i<4; i++){
			rückgabe=rückgabe+ endFelderGrün[i].toString() + " , ";
		}
		rückgabe+= "\n Gelbe Endfelder: " ;
		for(int i=0; i<4; i++){
			rückgabe=rückgabe+ endFelderGelb[i].toString() + " , ";
		}
		return(rückgabe);
		
	}
	
	

}
