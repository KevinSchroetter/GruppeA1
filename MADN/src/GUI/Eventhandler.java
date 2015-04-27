package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Spiel.Spiel;
import Spiel.iBediener;

public class Eventhandler implements ActionListener {
	private iBediener myGame = new Spiel();
	private HashMap<String,JButton> eventMap = null;
	private String temp = null;
	private JLabel label = null;
	private JFrame frame = null;
	private int counter = 0;
	
	
	public Eventhandler(HashMap eventMap){
		if(eventMap == null) throw new IllegalArgumentException("Hashmap fehlerhaft");
		else this.eventMap = eventMap;
	}
	public Eventhandler(HashMap eventMap, String temp){
		this(eventMap);
		this.temp = temp;
	}
	public Eventhandler(HashMap eventMap, JLabel label){
		this(eventMap);
		this.label = label;
	}
	public Eventhandler(HashMap eventMap, int index,JFrame frame){
		this(eventMap);
		this.counter = counter;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if (!(e instanceof ActionEvent)) return;
		System.out.println(e.getSource());
		JButton buf;
		if(e.getSource() == eventMap.get("OK")){
			frame.dispose();
			System.out.println("bla");
		}
		
		if(e.getSource() == eventMap.get("diceGame")){
			buf = (JButton) e.getSource();
			buf.setText("Meep!");
		}
		if(e.getSource() == eventMap.get("startGame")){
			buf = (JButton) e.getSource();
			buf.setEnabled(false);
			AnzahlSpielerFenster a = new AnzahlSpielerFenster();
		}

		
	}
	

}
