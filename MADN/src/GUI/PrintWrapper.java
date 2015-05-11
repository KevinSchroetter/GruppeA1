package GUI;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class PrintWrapper extends PrintStream {

		private Stromwrapper out;
		
	public PrintWrapper(Stromwrapper out) {
		super(out);
		this.out = out;
		
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void println(Object o){
		if(o instanceof Exception){
			Exception e = (Exception) o;
			JOptionPane.showMessageDialog(null,
				    e.getMessage(),
				    "Fehler",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

}
