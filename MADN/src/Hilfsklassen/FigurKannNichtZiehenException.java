package Hilfsklassen;

import java.io.Serializable;

public class FigurKannNichtZiehenException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 1L;

	public FigurKannNichtZiehenException() {
		// TODO Auto-generated constructor stub
	}

	public FigurKannNichtZiehenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
