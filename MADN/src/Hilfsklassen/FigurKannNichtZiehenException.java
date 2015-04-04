package Hilfsklassen;

import java.io.Serializable;

/**
 * Wird geworfen, wenn eine Figur nicht ziehen kann.
 * @author Alexander Brueckner
 * @version 4.0
 * @since v3.0
 */

public class FigurKannNichtZiehenException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;

	public FigurKannNichtZiehenException() {

	}

	public FigurKannNichtZiehenException(String message) {
		super(message);

	}
}
