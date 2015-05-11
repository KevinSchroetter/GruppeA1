package GUI;

import java.io.OutputStream;
import java.io.PrintStream;
import Einstellungen.SoundEnum;
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
			if((e.getMessage().contains("muessen erst einen Zug"))){
				print(e.getMessage()+"\n");
				return;
			}
		SoundEnum.ERROR.play();
			JOptionPane.showMessageDialog(null,
				    e,
				    "Fehler",
				    JOptionPane.ERROR_MESSAGE);
			
		}
		
		print(o+"\n");
	}

}
