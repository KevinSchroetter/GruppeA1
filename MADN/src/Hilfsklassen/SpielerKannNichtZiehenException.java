package Hilfsklassen;

import java.io.Serializable;

/**
 * Wird geworfen, wenn ein Spieler nicht ziehen kann.
 * @author Kevin Schroetter
 * @version 4.0
 * @since v3.0
 */
public class SpielerKannNichtZiehenException extends RuntimeException implements Serializable{
	private static final long serialVersionUID = 1L;
	public SpielerKannNichtZiehenException() {

	}

	public SpielerKannNichtZiehenException(String message) {
		super(message);

	}
}
