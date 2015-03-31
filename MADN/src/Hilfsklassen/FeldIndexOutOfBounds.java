package Hilfsklassen;

import java.io.Serializable;



/**
 * Klasse FeldIndexOutOfBounds - erbt von {@link RuntimeException}, wird geworfen, wenn ein Feld mit kleiner 1 oder 
 * größer 40 addressiert wird.
 */

public class FeldIndexOutOfBounds extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FeldIndexOutOfBounds(){
		super();
	}
	
	public FeldIndexOutOfBounds(String message){
		super(message);
	}

}
