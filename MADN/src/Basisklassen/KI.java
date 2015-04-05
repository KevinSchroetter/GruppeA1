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
	KI dieseKI;

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
	private void wuerfeln(int zahl){
		iB.werfen(zahl);
	}
	
	/**
	 * Methode, die ueber das Interface eine Zufallszahl wuerfelt
	 */
	private void werfen(){
		iB.rollTheDice();
	}

	/**
	 * Abstrakte Methode, die in den spezifischen KIs aggressiv und defensiv
	 * implementiert wird. Sie soll die Entscheidungen der KI steuern.
	 */
	public abstract void zugWaehlen();
	/**
	 * Methode, die die KI-Bedienung des am Zug seienden Spielers voruebergehend auf null setzt, so dass dieser auf 
	 * die zugDurchfuehren-Methode des Interfaces zugreifen kann. Ihr uebergibt er die ID des Spielfeldes der Zug-Figur,
	 * die von der zugWaehlen-Methode uebergeben wird, so dass der Zug ausgefuehrt wird. Dann setzt die Methode die 
	 * KI des Spielers wieder auf sich selbst.
	 * @param ID - ID des Spielfeldes, dessen Figur laufen soll
	 */
	protected void zugDurchfuehren(String ID){
		dieseKI=this;
		meinSpieler.setBedienung(null);
		iB.zugDurchfuehren(ID);
		meinSpieler.setBedienung(dieseKI);
	}
}
