package Basisklassen;

/** Dies ist die Klasse Würfel für das MADN-Spiel.
 * Die Klasse besitzt die Eigenschaft gewürfelteZahl, in der sie die zuletzt mit der werfen-Methode gewürfelte Zahl speichert.
@author Anna Rosa
@version 1.1
*/

public class Würfel {

	private int augenzahl;
	/**
	 * Eine Methode, die die zuletzt gewürfelte Zahl zurückgibt.
	 * @return augenzahl
	 */
	public int getAugenzahl(){
		return augenzahl;
	} 
	
	/**
	 * Eine Methode, die einem Würfel entsprechend eine Zufallsganzzahl zwischen 1 und 6 zurückgibt.
	 * @return augenzahl
	 */
	public int werfen(){
		int augenzahl = (int)  ((Math.random()*6) + 1);
		return augenzahl;
	}
	
	/**
	 * Eine Methode, die die übergebene Zahl zurückgibt. Diese dient als Testmethode für spezifisch gewollte Züge.
	 * @param zahl - Zahl vom Typ int, über die eine konkrete Augenzahl übergeben wird.
	 * @return zahl, die gewünschte Testzahl.
	 */
	public int testWurf(int zahl){
		return zahl;
	}
}
