package Hilfsklassen;

import java.lang.RuntimeException;
import Basisklassen.Spielfigur;
import Basisklassen.Spielfeld;

/** Klasse FeldBelegt - Eigene Exception, wird geworfen wenn ein Feld belegt ist.
 *
 */

public class FeldBelegt extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8301001168647437108L;

	public FeldBelegt(){
		super();
	}
	
	public FeldBelegt(String message, Spielfigur figur, Spielfeld feld){
		
		super(message);
		Spielfigur figurGeworfen = figur;
		Spielfeld feldGeworfen = feld;
		
	}
}
