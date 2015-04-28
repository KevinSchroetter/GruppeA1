package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Einstellungen.FarbEnum;
import Spiel.Spiel;
import Spiel.iBediener;

public class Eventhandler implements ActionListener {
	private iBediener myGame = new Spiel();
	private HashMap<String,JButton> naviMap = null;
	private HashMap<String,JButton> stdFieldsMap = null;
	private HashMap<String,JButton> startFieldsMap = null;
	private HashMap<String,JButton> endFieldsMap = null;
	private HashMap<String,JLabel> labelMap = null;
	private HashMap<String,ImageIcon> imagesMap = null;
	private AnzahlSpielerFenster tempASF = null;	
	private SpielerHinzufuegenFenster tempSHF = null;
	private String tempString = null;
	private JLabel tempLabel = null;
	private JFrame frame = null;
	private int counter = 0;
	private FarbEnum tempFarbe = null;
	
	
	public Eventhandler(HashMap<String,JButton> naviMap, HashMap<String,JLabel> labelMap, HashMap<String,ImageIcon> imagesMap, HashMap<String,JButton> stdFieldsMap, HashMap<String,JButton> startFieldsMap, HashMap<String,JButton> endFieldsMap ){
		if(naviMap == null || labelMap == null || imagesMap == null || stdFieldsMap == null || startFieldsMap == null || endFieldsMap == null) throw new IllegalArgumentException("Hashmap fehlerhaft");
		this.naviMap = naviMap;
		this.labelMap = labelMap;
		this.imagesMap = imagesMap;
		this.stdFieldsMap = stdFieldsMap;
		this.startFieldsMap = startFieldsMap;
		this.endFieldsMap = endFieldsMap;
	}

	public void addStuff(HashMap<String,JButton> eventMap, JFrame frame){
		for(java.util.Map.Entry<String, JButton> entry : eventMap.entrySet()) {
		    String key = entry.getKey();
		    JButton value = entry.getValue();
		    naviMap.put(key, value);
		}
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if (!(e instanceof ActionEvent)) return;
		//System.out.println(e.getSource());
		JButton buf;
		if(e.getSource() == naviMap.get("OK")){
			frame.dispose();
			counter = tempASF.auswahlAnzahl.getSelectedIndex();
			tempASF = null;
			frame = null;
			System.out.println("Gewaehlte Spieleranzahl: "+(counter+1));
			tempSHF = new SpielerHinzufuegenFenster(this);
		}
		if(e.getSource() == naviMap.get("addSpieler")){
			frame.dispose();
			tempString=tempSHF.eingabeName.getText();
			tempFarbe = FarbEnum.valueOf(tempSHF.FarbAuswahl.getSelectedItem().toString());
			System.out.println("Eingabe im Namensfeld:"+ tempString);
			System.out.println("Farbe: "+tempFarbe);
			System.out.println("KEIN PLAN WIE ICH AN DEN RADIOBUTTON RANKOMME: ANNA DO UR THING");
			//Create Spieler hier ueber iBediern Methode myGame.spielerHinzufuegen
			frame=null;
			tempString=null;
			tempFarbe=null;
			counter--;
			if(counter>=0) tempSHF = new SpielerHinzufuegenFenster(this);
		}
		
		
		if(e.getSource() == naviMap.get("diceGame")){
			buf = (JButton) e.getSource();
			buf.setText("Meep!");
		}
		if(e.getSource() == naviMap.get("startGame")){
			buf = (JButton) e.getSource();
			buf.setEnabled(false);
			tempASF = new AnzahlSpielerFenster(this);
		
		
		}

		
	}
	

}
