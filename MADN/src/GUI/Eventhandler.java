package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class Eventhandler implements ActionListener {
	
	private ArrayList<JButton> elements = null;
	private JButton[] listeAlsArray;
	
	
	public Eventhandler(ArrayList<JButton> elements){
		if(elements==null) throw new IllegalArgumentException("Elementliste fehlerhaft");
		else this.elements = elements;
		listeAlsArray = (JButton[]) elements.toArray();
		
		for(int i = 0; i<listeAlsArray.length; i++){
			if(listeAlsArray[i] == null) continue;
			else listeAlsArray[i].addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		
		
	}
	

}
