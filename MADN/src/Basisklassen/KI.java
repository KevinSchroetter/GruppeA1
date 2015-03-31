package Basisklassen;

import java.io.Serializable;

import Spiel.iBediener;

/**
* Abstrakte Klasse KI, in der eine künstliche Intelligenz erstellt werden kann und die die abstrakte Methode zugWählen besitzt, 
* die in KI_Aggressiv und KI_Defensiv definiert werden.
* 
* @author Kevin Schrötter, Anna Rosa
* @version 2.0
*/
public abstract class KI implements Serializable {

	private static final long serialVersionUID = 1L;
	protected Spieler meinSpieler = null;
	iBediener iB;

	/**
	 * Konstruktor für eine Künstliche Intelligenz.
	 * 
	 * @param s - Typ Spieler, setzt eine Referenz auf den Spieler, der die KI besitzt.
	 */
	public KI(Spieler s) {
		this.meinSpieler = s;
		iB= meinSpieler.getIBediener();
	}
	
	/**
	 * Methode, die über das Interface die übergebene Zahl "würfelt".
	 * @param zahl - gewuenschtes Wuerfelergebnis
	 */
	public void würfeln(int zahl){
		iB.werfen(zahl);
	}
	
	/**
	 * Methode, die über das Interface eine Zufallszahl würfelt
	 */
	public void werfen(){
		iB.rollTheDice();
	}
	

	/**
	 * Abstrakte Methode, die in den spezifischen KIs aggressiv und defensiv
	 * implementiert wird. Sie soll die Entscheidungen der KI steuern.
	 */
	public abstract void zugWählen();

}
