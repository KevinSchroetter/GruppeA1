package GUI;

import java.awt.Container;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public class Stromwrapper extends OutputStream {

	private JTextArea destOutput;

	// Referenzen auf Ursprungsströme:

	private static PrintStream standardOut = System.out;
	private static PrintStream standardErr = System.err;

	public Stromwrapper(JTextArea hierReinSchreiben) {
		if (hierReinSchreiben == null)
			throw new IllegalArgumentException("JTextArea null");
		this.destOutput = hierReinSchreiben;

	}

	public static PrintStream getStandardOut() {
		return standardOut;
	}

	public static PrintStream getStandardErr() {
		return standardErr;
	}
	
	public Container getTextAreaParent(){
		return this.destOutput.getParent();
	}

	@Override
	public void write(int b) throws IOException {
		// Umleitung auf GUI, schreibt Buchstabe für Buchstabe
		// Problem:: Exceptions sollen als Fehlermeldung erscheinen
		// (JoptionPane)
		// Lösung: Siehe klasse Printwrapper
		destOutput.append(String.valueOf((char) b));
		// Immer schön ans Ende schreiben
		destOutput.setCaretPosition(destOutput.getDocument().getLength());
	}

}
