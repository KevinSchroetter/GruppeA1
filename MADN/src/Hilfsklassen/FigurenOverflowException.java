package Hilfsklassen;

import java.io.Serializable;

/** 
 * Klasse FigurenoverflowException
 * Wird geworfen, wenn mehr als 16 Figuren erstellt wurden
 */

public class FigurenOverflowException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;
	public FigurenOverflowException(){
		super();
	}
	
	public FigurenOverflowException(String message)
	{
		super(message);
	}

}
