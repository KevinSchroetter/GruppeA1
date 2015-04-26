package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

public class Eventhandler implements ActionListener {
	
	private HashMap<String,JButton> eventMap = null;
	
	
	public Eventhandler(HashMap eventMap){
		if(eventMap == null) throw new IllegalArgumentException("Hashmap fehlerhaft");
		else this.eventMap = eventMap;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		
		JButton buf;
		if(e.getSource() == eventMap.get("diceGame")){
			buf = (JButton) e.getSource();
			buf.setText("Meep!");
		}
		
		
	}
	

}
