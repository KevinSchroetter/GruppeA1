package Hilfsklassen;

import java.io.Serializable;

public class SpielerKannNichtZiehenException extends RuntimeException implements Serializable{
	public SpielerKannNichtZiehenException() {
		// TODO Auto-generated constructor stub
	}

	public SpielerKannNichtZiehenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}
