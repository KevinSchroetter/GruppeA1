package Hilfsklassen;

import java.lang.RuntimeException;
import java.io.Serializable;

import Basisklassen.Spielfigur;
import Basisklassen.Spielfeld;

/** Klasse FeldBelegt - Eigene Exception, wird geworfen wenn ein Feld belegt ist.
 * @author Alexander Brueckner
 * @version 4.0
 * @since v3.0
 */

public class FeldBelegt extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public FeldBelegt(){
		super();
	}
	
	public FeldBelegt(String message, Spielfigur figur, Spielfeld feld){
		
		super(message);
	}
}
