package Basisklassen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;

import Einstellungen.FarbEnum;

/**
 * Dies ist die Klasse Spielbrett, in der ueber den Konstruktor das komplette Spielfeld inklusive Start- und Endfelder erstellt. 
 * Die Klasse Spielbrett besitzt die Attribute startFelderRot, startFelderBlau, startFelderGruen, startFelderGelb, endFelderRot, endFelderBlau, endFelderGruen, endFelderGelb, standardFelder , also 9 Arrays, in denen alle einzelnen Spielfelder gespeichert sind.
 *
 * @author Anna Rosa
 * @version 4.0
 * @since v1.0
 *
 */
public class Spielbrett implements Serializable {

	private static final long serialVersionUID = 1L;
	private int maxFiguren = 4;
	public static ArrayList<FarbEnum> farben = new ArrayList<FarbEnum>(EnumSet.allOf(FarbEnum.class));
	private ArrayList<Startfeld[]> startFelder = new ArrayList<Startfeld[]>(farben.size());
	private ArrayList<Endfeld[]> endFelder = new ArrayList<Endfeld[]>(farben.size());
	private Standardfeld [] standardFelder= new Standardfeld[EnumSet.allOf(FarbEnum.class).size()*10];
		
	/**
	 * Der Konstruktor erstellt mit Hilfe der Methoden erstelleStartFelder, erstelleEndFelder und erstelleStandardFelder das Spielbrett.
	 */
	public Spielbrett(){
		erstelleStartFelder();
		erstelleEndFelder();
		erstelleStandardFelder();
	}
	
	/**
	 * Zweiter Konstuktor lädt das Brett mit schon bestehenden Feldern so richtig durch
	 */
	
	public Spielbrett(ArrayList<Startfeld[]> startFelder, ArrayList<Endfeld[]> endFelder, Standardfeld[] standardFelder ){
		
		ladeStartfelder(startFelder);
		ladeEndfelder(endFelder);
		ladeStandardfelder(standardFelder);
		
	}
	
	/** 
	 * Ein Getter, der alle Startfelder der gewuenschten, uebergebenen Farbe zurueckgibt.
	 * @param farbe - Uebergebene Farb vom Typ FarbEnum.
	 * @return Startfeld[] - Array der Startfelder der jeweiligen Farbe
	 */
	public Startfeld[] getAlleStartFelderEinerFarbe(FarbEnum farbe){
		if(!farben.contains(farbe))throw new RuntimeException("Farbe nicht vorhanden!");
		int farbIndex = farben.indexOf(farbe);
		return startFelder.get(farbIndex);
	}
	
	/**
	 * Ein Getter, der alle Endfelder der gewuenschten, uebergebenen Farbe zurueckgibt.
	 * @param farbe - Uebergebene Farbe vom Typ FarbEnum.
	 * @return Endfeld[] - Array der Endfelder.
	 */
	public Endfeld[] getAlleEndFelderEinerFarbe(FarbEnum farbe) {
		if(!farben.contains(farbe))throw new RuntimeException("Farbe nicht vorhanden!");		
		int farbIndex = farben.indexOf(farbe);
		return endFelder.get(farbIndex);
	}
	
	/**
	 * Ein Getter, der das Standardfelder-Array zurueckgibt.
	 * @return standardFelderRot
	 */
	public Standardfeld[] getStandardFelder(){
		return standardFelder;
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
			if(iD<1| iD>standardFelder.length)
				throw new RuntimeException("ID nicht gueltig");
			else
				return standardFelder[iD-1];
		}
		else
			throw new RuntimeException("Falsche Feld-ID");
		return null;
	}
	
	/**
	 * Methode, die das Spawnfeld der uebergebenen Farbe zurueckgibt.
	 * @param farbe - die Farbe, fuer die das Spawnfeld gesucht wird
	 * @return spawnfeld der Farbe
	 */
	public Standardfeld getSpawnfeld(FarbEnum farbe){
		ArrayList<Standardfeld> spawnFelder = new ArrayList<Standardfeld>(farben.size());
		int farbIndex = farben.indexOf(farbe);
		for (int i = 0; i < farben.size();i++)
			spawnFelder.add(standardFelder[i*10]);
		return spawnFelder.get(farbIndex);
	}
	
	/**
	 * Die Methode erstelleStartFelder erstellt fuer die jeweiligen Farben je 4 Startfelder, die sie im Startfelder-Array der entsprechenden Farbe speichert.
	 */
	private void erstelleStartFelder(){
		String iD = "S";
		for(int i = 0;i<farben.size();i++){
			 Startfeld[] felder = new Startfeld[maxFiguren];
			 for (int j = 0; j<maxFiguren;j++)
				 felder[j]=new Startfeld(iD+(j+1),farben.get(i));
			 startFelder.add(felder);
		}
	}

	/**
	 * Die Methode erstelleEndFelder erstellt fuer die jeweiligen Farben je 4 Endfelder, die sie im Endfelder-Array der entsprechenden Farbe speichert.
	 */
	private void erstelleEndFelder(){
		String iD = "E";
		for(int i = 0;i<farben.size();i++){
			 Endfeld[] felder = new Endfeld[maxFiguren];
			 for (int j = 0; j<maxFiguren;j++)
				 felder[j]=new Endfeld(iD+(j+1),farben.get(i));
			 endFelder.add(felder);
		}
	}
	
	/**
	 * Die Methode erstelleStandardFelder erstellt 40 Standardfelder, die es im StandardFelder-Array speichert.
	 */
	private void erstelleStandardFelder(){
		for(int i=0; i<standardFelder.length; i++ ){
			int iD= i+1;
			standardFelder[i]=new Standardfeld(iD);
		}
	}
	
	/**
	 * Zum Laden einer bestehenden Startfeldliste
	 * @param startFelderListe - ArrayList<Startfeld[]>
	 * */
	public void ladeStartfelder(ArrayList<Startfeld[]> startFelderListe){
		
		if(startFelderListe == null){
			throw new IllegalArgumentException("Keine Startfeldliste!");
		} else this.startFelder = startFelderListe;
	}
	
	/**
	 * Zum Laden einer bestehenden Endfeldliste
	 * @param endFelderListe - ArrayList<Endfeld[]>
	 * */
	
	public void ladeEndfelder(ArrayList<Endfeld[]> endFelderListe){
		
		if(endFelderListe == null){
			throw new IllegalArgumentException("Keine Endfeldliste!");
		} else this.endFelder = endFelderListe;
		
		
	}
	
	/**
	 * Zum Laden einer bestehenden Standardfeldliste
	 * @param standardFelder - Standardfeld[]
	 * */
	public void ladeStandardfelder(Standardfeld[] standardFelder){
		if(standardFelder == null || standardFelder.length == 0) throw new IllegalArgumentException("Standardfelder ungültig!");
		else this.standardFelder = standardFelder;
	}
	
	
	/**
	 * Override der toString-Methode, die alle Spielfelder zurueckgibt.
	 * @return rueckgabe - alle Spielfelder, geordner nach: Startfelder - Standardfelder - Endfelder
	 */
	@Override
	public String toString(){
		String felder = "########################################\n\n";
		int indexSF = 0;
		int indexEF = 0;
		for(Startfeld[] sfs: startFelder){
			felder+="----------------------------------------\n\nStartfelder "+farben.get(indexSF++)+":\n";
			for(Startfeld sf: sfs ){
				felder+=sf.toString()+", ";
			}
			felder+="\n\n";
		}
		felder+="----------------------------------------\n\n################################################################################################################################################################\n\nStandardfelder:   ";
		for(Standardfeld stdf: standardFelder)
			felder+=stdf.toString()+", ";
		felder+="\n\n################################################################################################################################################################\n\n";
		
		for(Endfeld[] efs: endFelder){
			felder+="----------------------------------------\n\nEndfelder "+farben.get(indexEF++)+":\n";
			for(Endfeld ef: efs ){
				felder+=ef.toString()+", ";
			}
			felder+="\n\n";
		}
		felder+="----------------------------------------\n\n########################################\n\n";
		return(felder);
	}
}
