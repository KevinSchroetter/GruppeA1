package Basisklassen;

import java.io.Serializable;

/** Dies ist die Klasse Wuerfel fuer das MADN-Spiel.
 * Die Klasse besitzt die Eigenschaft gewuerfelteZahl, in der sie die zuletzt mit der werfen-Methode gewuerfelte Zahl speichert.
 *@author Anna Rosa
 *@version 4.0
 *@since v1.0
 */ 

public class Wuerfel implements Serializable {


	private static final long serialVersionUID = 1L;
	private int augenzahl;
	/**
	 * Eine Methode, die die zuletzt gewuerfelte Zahl zurueckgibt.
	 * @return augenzahl
	 */
	public int getAugenzahl(){
		return augenzahl;
	} 
	
	/**
	 * Eine Methode, die einem Wuerfel entsprechend eine Zufallsganzzahl zwischen 1 und 6 zurueckgibt.
	 * @return augenzahl
	 */
	public int werfen(){
		int augenzahl = (int)  ((Math.random()*6) + 1);
		augenzahl = (augenzahl % 7);
		return augenzahl;
	}
	
	/**
	 * Eine Methode, die die uebergebene Zahl zurueckgibt. Diese dient als Testmethode fuer spezifisch gewollte Zuege.
	 * @param zahl - Zahl vom Typ int, ueber die eine konkrete Augenzahl uebergeben wird.
	 * @return zahl, die gewuenschte Testzahl.
	 */
	public int testWurf(int zahl){
		return zahl;
	}
}
