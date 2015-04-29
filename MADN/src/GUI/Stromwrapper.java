package GUI;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

public class Stromwrapper extends OutputStream {

	private JTextArea destOutput;
	
	//Referenzen auf Ursprungsströme:
	
	private static PrintStream standardOut = System.out;
	private static PrintStream standardErr = System.err;
	
	public Stromwrapper(JTextArea hierReinSchreiben){
		if(hierReinSchreiben == null) throw new IllegalArgumentException("JTextArea null");
		this.destOutput = hierReinSchreiben;
	}
	
	public static PrintStream getStandardOut(){
		return standardOut;
	}
	public static PrintStream getStandardErr(){
		return standardErr;
	}
	
	 @Override
	    public void write(int b) throws IOException {
	        // redirects data to the text area
	        destOutput.append(String.valueOf((char)b));
	        // scrolls the text area to the end of data
	        destOutput.setCaretPosition(destOutput.getDocument().getLength());
	    }

	
	
}
