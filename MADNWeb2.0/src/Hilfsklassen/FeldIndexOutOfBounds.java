package Hilfsklassen;

import java.io.Serializable;
/**
 * Klasse FeldIndexOutOfBounds - erbt von {@link RuntimeException}, wird geworfen, wenn ein Feld mit kleiner 1 oder 
 * groesser 40 addressiert wird.
 * @author Alexander Brueckner
 * @version 4.0
 * @since v3.0
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
