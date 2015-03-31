package Hilfsklassen;
/**
 * Diese Exception wird geworfen, wenn ein Spiel beendet ist.
 * @author kevin
 *
 */
public class SpielBeendetException extends RuntimeException {
	private static final long serialVersionUID = 6944183139904579155L;
	public SpielBeendetException(){
		super();
	}
	
	public SpielBeendetException(String message)
	{
		super(message);
	}
}
