package Hilfsklassen;

/**
 * Klasse FeldIndexOutOfBounds - erbt von {@link RuntimeException}, wird geworfen, wenn ein Feld mit kleiner 1 oder 
 * größer 40 addressiert wird.
 */

public class FeldIndexOutOfBounds extends RuntimeException {

	private static final long serialVersionUID = -7588968462757300534L;
	
	public FeldIndexOutOfBounds(){
		super();
	}
	
	public FeldIndexOutOfBounds(String message){
		super(message);
	}

}
