package Hilfsklassen;
import java.io.Serializable;

/**
 * Diese Exception wird geworfen, falls ein menschlicher Spieler die zugDurchfuehren-Methode der KI aufrufen möchte.
 * @author Anna
 * @since version 3.0
 */
public class MethodeFuerKiException extends RuntimeException implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public MethodeFuerKiException(String message){
		super(message);
	}

}
