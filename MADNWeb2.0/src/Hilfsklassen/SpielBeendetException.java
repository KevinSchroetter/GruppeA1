package Hilfsklassen;

import java.io.Serializable;

/**
 * Diese Exception wird geworfen, wenn ein SpielBean beendet ist.
 * @author Kevin Schroetter
 * @version 4.0
 * @since v3.0
 */
public class SpielBeendetException extends RuntimeException implements Serializable{
	private static final long serialVersionUID = 1L;
	public SpielBeendetException(){
		super();
	}
	
	public SpielBeendetException(String message)
	{
		super(message);
	}
}
