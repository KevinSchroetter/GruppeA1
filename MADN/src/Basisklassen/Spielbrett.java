package Basisklassen;

/**
 * Dies ist die Klasse Spielbrett, in der über den Konstruktor das komplette Spielfeld inklusive Start- und Endfelder erstellt. 
 * Die Klasse Spielbrett besitzt die Attribute startFelderRot, startFelderBlau, startFelderGrün, startFelderGelb, endFelderRot, endFelderBlau, endFelderGrün, endFelderGelb, standardFelder , also 9 Arrays, in denen alle einzelnen Spielfelder gespeichert sind.
 *
 * 
 * @author Anna Rosa
 * @version 1.5
 *
 */
public class Spielbrett {
	private Startfeld [] startFelderRot= new Startfeld[4];
	private Startfeld [] startFelderBlau= new Startfeld[4];
	private Startfeld [] startFelderGrün= new Startfeld[4];
	private Startfeld [] startFelderGelb= new Startfeld[4];
	private Endfeld [] endFelderRot= new Endfeld [4];
	private Endfeld [] endFelderBlau= new Endfeld [4];
	private Endfeld [] endFelderGrün= new Endfeld [4];
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
	 * Ein Getter, der alle Startfelder der gewünschten, übergebenen Farbe zurückgibt.
	 * @param farbe
	 * @return []Startfeld -> Array der Startfelder der jeweiligen Farbe
	 */
	public Startfeld[] getAlleStartFelderEinerFarbe(FarbEnum farbe){
		if(farbe.equals(FarbEnum.ROT))
			return getAlleStartFelderRot();
		if( farbe.equals(FarbEnum.BLAU))
			return getAlleStartFelderBlau();
		if(farbe.equals(FarbEnum.GRÜN))
			return getAlleStartFelderGrün();
		if( farbe.equals(FarbEnum.GELB))
				return getAlleStartFelderGelb();
		else return null;
	}
	/**
	 * Ein Getter, der das Startfelder-Array der roten Startfelder zurückgibt.
	 * @return startFelderRot
	 */
	public Startfeld[] getAlleStartFelderRot(){
		return  startFelderRot;
	}
	
	/**
	 * Ein Getter, der das Startfelder-Array der blauen Startfelder zurückgibt.
	 * @return startFelderBlau
	 */
	public Startfeld[] getAlleStartFelderBlau(){
		return  startFelderBlau;
	}
	
	/**
	 * Ein Getter, der das Startfelder-Array der grünen Startfelder zurückgibt.
	 * @return startFelderGrün
	 */
	public Startfeld[] getAlleStartFelderGrün(){
		return  startFelderGrün;
	}
	
	/**
	 * Ein Getter, der das Startfelder-Array der gelben Startfelder zurückgibt.
	 * @return startFelderGelb
	 */
	public Startfeld[] getAlleStartFelderGelb(){
		return  startFelderGelb;
	}
	
	/**
	 * Ein Getter, der das Endfelder-Array der roten Endfelder zurückgibt.
	 * @return endFelderRot
	 */
	public Endfeld[] getAlleEndFelderRot(){
		return  endFelderRot;
	}
	/**
	 * Ein Getter, der das Endfelder-Array der blauen Endfelder zurückgibt.
	 * @return endFelderRot
	 */
	public Endfeld[] getAlleEndFelderBlau(){
		return  endFelderBlau;
	}
	
	/**
	 * Ein Getter, der das Endfelder-Array der grünen Endfelder zurückgibt.
	 * @return endFelderGrün
	 */
	public Endfeld[] getAlleEndFelderGrün(){
		return  endFelderGrün;
	}
	
	/**
	 * Ein Getter, der das Endfelder-Array der gelben Endfelder zurückgibt.
	 * @return endFelderGelb
	 */
	public Endfeld[] getAlleEndFelderGelb(){
		return  endFelderGelb;
	}
	
	/**
	 * Ein Getter, der das Standardfelder-Array zurückgibt.
	 * @return standardFelderRot
	 */
	public Standardfeld[] getAlleStandardFelder(){
		return standardFelder;
	}
	/**
	 * Getter für die Inhalte des Arrays der roten Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index 
	 * @return Startfeld 
	 */
	public Startfeld getStartFelderRot(int index){
		if(index<0|index>startFelderRot.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return startFelderRot[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der blauen Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Startfeld
	 */
	public Startfeld getStartFelderBlau(int index){
		if(index<0|index>startFelderBlau.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return startFelderBlau[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der grünen Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Startfeld
	 */
	public Startfeld getStartFelderGrün(int index){
		if(index<0|index>startFelderGrün.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return startFelderGrün[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der gelben Startfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Startfeld
	 */
	public Startfeld getStartFelderGelb(int index){
		if(index<0|index>startFelderGelb.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return startFelderGelb[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der Standardfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Standardfeld 
	 */
	public Standardfeld getStandardFelder(int index){
		if(index<0|index>standardFelder.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		
		return standardFelder[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der roten Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Endfeld
	 */
	public Endfeld getEndFelderRot(int index){
		if(index<0|index>endFelderRot.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return endFelderRot[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der blauen Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Endfeld
	 */
	public Endfeld getEndFelderBlau(int index){
		if(index<0|index>endFelderBlau.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return endFelderBlau[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der grünen Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Endfeld
	 */
	public Endfeld getEndFelderGrün(int index){
		if(index<0|index>endFelderGrün.length)
			throw new RuntimeException("Gewünschte Stelle nicht vorhanden.");
		return endFelderGrün[index];
	}
	
	/**
	 * Getter für die Inhalte des Arrays der gelben Endfelder, gibt das Spielfeld an der Stelle [index] des Arrays zurück.
	 * @param index
	 * @return Endfeld
	 */
	public Endfeld getEndFelderGelb(int index){
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
