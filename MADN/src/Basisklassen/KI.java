package Basisklassen;

import java.io.Serializable;

import Spiel.iBediener;

/**
* Abstrakte Klasse KI, in der eine kuenstliche Intelligenz erstellt werden kann und die die abstrakte Methode zugWaehlen besitzt, 
* die in KI_Aggressiv und KI_Defensiv definiert werden.
* 
* @author Kevin Schroetter, Anna Rosa
* @version 4.0
* @since v3.0
*/
public abstract class KI implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Spieler meinSpieler = null;
	iBediener iB;

	/**
	 * Konstruktor fuer eine Kuenstliche Intelligenz.
	 * 
	 * @param s - Typ Spieler, setzt eine Referenz auf den Spieler, der die KI besitzt.
	 */
	public KI(Spieler s) {
		this.meinSpieler = s;
		iB= meinSpieler.getIBediener();
	}
	
	/**
	 * Methode, die ueber das Interface die uebergebene Zahl "wuerfelt".
	 * @param zahl - gewuenschtes Wuerfelergebnis
	 */
	public void wuerfeln(int zahl){
		iB.werfen(zahl);
	}
	
	/**
	 * Methode, die ueber das Interface eine Zufallszahl wuerfelt
	 */
	public void werfen(){
		iB.rollTheDice();
	}

	/**
	 * Abstrakte Methode, die in den spezifischen KIs aggressiv und defensiv
	 * implementiert wird. Sie soll die Entscheidungen der KI steuern.
	 */
	public abstract void zugWaehlen();
}
