package Hilfsklassen;



public class FigurenOverflowException extends RuntimeException {
	
	/** Klasse FigurenoverflowException
	 * Wird geworfen, wenn mehr als 16 Figuren erstellt wurden
	 * 
	 */
	
	
	private static final long serialVersionUID = 6944183139904579155L;
	public FigurenOverflowException(){
		super();
	}
	
	public FigurenOverflowException(String message)
	{
		super(message);
	}

}
