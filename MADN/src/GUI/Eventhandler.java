package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Einstellungen.FarbEnum;
import Spiel.DatenzugriffCSV;
import Spiel.DatenzugriffSerialisiert;
import Spiel.Spiel;
import Spiel.iBediener;
import Spiel.iDatenzugriff;

public class Eventhandler implements ActionListener {
	private JPanel spielbrettGUI = null;
	private iBediener myGame = new Spiel();
	private GUI myGUI = null;
	private iDatenzugriff saveSer = new DatenzugriffSerialisiert();
	private iDatenzugriff saveCsv = new DatenzugriffCSV();
	private iDatenzugriff savePDF = new DatenzugriffPDF();
	private HashMap<String, JButton> naviMap = null;
	private HashMap<String, JButton> stdFieldsMap = null;
	private HashMap<String, JButton> startFieldsMap = null;
	private HashMap<String, JButton> endFieldsMap = null;
	private HashMap<String, JLabel> labelMap = null;
	private HashMap<String, ImageIcon> imagesMap = null;
	private HashMap<String, JButton> actionMap = new HashMap<String, JButton>();
	private AnzahlSpielerFenster tempASF = null;
	private SpielerHinzufuegenFenster tempSHF = null;
	private String tempString = null;
	private JLabel tempLabel = null;
	private JFrame frame = null;
	private JFrame guiFrame = null;
	private int counter = 0;
	private FarbEnum tempFarbe = null;
	private ArrayList<FarbEnum> vorhandeneFarben = null;
	private ButtonGroup buttonGroup = null;
	private HashMap<String, JRadioButton> auswahlMenschKi = null;
	private JComboBox<String> farbe = null;
	private JTextField name = null;
	private JFileChooser fileGrabber = null;
	boolean geladenAngefangen = false;

	public Eventhandler(HashMap<String, JButton> naviMap,
			HashMap<String, JLabel> labelMap,
			HashMap<String, ImageIcon> imagesMap,
			HashMap<String, JButton> stdFieldsMap,
			HashMap<String, JButton> startFieldsMap,
			HashMap<String, JButton> endFieldsMap, JFileChooser fileGrabber, JPanel spielbrettGUI, GUI myGui) {
		if (naviMap == null || fileGrabber == null || labelMap == null
				|| imagesMap == null || stdFieldsMap == null
				|| startFieldsMap == null || endFieldsMap == null)
			throw new IllegalArgumentException("Hashmap fehlerhaft");
		this.naviMap = naviMap;
		this.fileGrabber = fileGrabber;
		this.labelMap = labelMap;
		this.imagesMap = imagesMap;
		this.stdFieldsMap = stdFieldsMap;
		this.startFieldsMap = startFieldsMap;
		this.endFieldsMap = endFieldsMap;
		vorhandeneFarben = new ArrayList<FarbEnum>();
		vorhandeneFarben.add(FarbEnum.ROT);
		vorhandeneFarben.add(FarbEnum.BLAU);
		vorhandeneFarben.add(FarbEnum.GRUEN);
		vorhandeneFarben.add(FarbEnum.GELB);
		this.spielbrettGUI = spielbrettGUI;
		this.myGUI=myGui;
				
	}

	public void addStuff(HashMap<String, JButton> eventMap, JFrame frame,
			HashMap<String, JRadioButton> tempMap, ButtonGroup bg,
			JComboBox<String> jcb, JTextField name) {
		if (eventMap != null)
			for (java.util.Map.Entry<String, JButton> entry : eventMap
					.entrySet()) {
				String key = entry.getKey();
				JButton value = entry.getValue();
				naviMap.put(key, value);
			}
		auswahlMenschKi = tempMap;
		this.buttonGroup = bg;
		farbe = jcb;
		this.name = name;
		this.frame = frame;
	}

	public void addGUI(JFrame frame) {
		this.guiFrame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(e instanceof ActionEvent))
			return;
		// System.out.println(e.getSource());
		JButton buf;
		if (e.getSource() == naviMap.get("endGame")) {
			guiFrame.dispose();
			if(tempASF != null)
				tempASF.dispose();
			tempASF = null;
			if(tempSHF != null)
				tempSHF.dispose();
			tempSHF = null;
		}
		if (e.getSource() == naviMap.get("OK")) {
			frame.dispose();
			counter = tempASF.auswahlAnzahl.getSelectedIndex();
			tempASF = null;
			frame = null;
			System.out.println("Gewaehlte Spieleranzahl: " + (counter + 1));
			tempSHF = new SpielerHinzufuegenFenster(this, vorhandeneFarben);
		}
		if (e.getSource() == naviMap.get("addSpieler")) {
			frame.dispose();
			int kIAuswahl = 1;
			JRadioButton jrb = null;
			for (java.util.Map.Entry<String, JRadioButton> entry : auswahlMenschKi
					.entrySet()) {
				if (entry.getValue().isSelected())
					jrb = entry.getValue();
			}
			if (jrb.equals(auswahlMenschKi.get("Mensch")))
				kIAuswahl = 0;
			else if (jrb.equals(auswahlMenschKi.get("aki")))
				kIAuswahl = 1;
			else if (jrb.equals(auswahlMenschKi.get("dki")))
				kIAuswahl = 2;

			String farbtemp = (String) farbe.getSelectedItem();
			int farbId = 0;
			switch (farbtemp) {
			case "ROT":
				tempFarbe = FarbEnum.ROT;
				farbId = 1;
				break;
			case "BLAU":
				tempFarbe = FarbEnum.BLAU;
				farbId = 2;
				break;
			case "GRUEN":
				tempFarbe = FarbEnum.GRUEN;
				farbId = 3;
				break;
			case "GELB":
				tempFarbe = FarbEnum.GELB;
				farbId = 4;
			}

			String tempName = name.getText();
			boolean erfolgreich = myGame.neuerSpieler(tempName, farbId,
					kIAuswahl);
			if (erfolgreich == true) {
				vorhandeneFarben.remove(tempFarbe);
				counter--;
			}
			if (erfolgreich == false)
				System.out
						.println("Erstellen des Spielers war nicht erfolgreich. Bitte erneut versuchen!");
			frame = null;
			tempString = null;
			tempFarbe = null;
			if (counter >= 0)
				tempSHF = new SpielerHinzufuegenFenster(this, vorhandeneFarben);
			else {
				myGame.starteSpiel();
			}
			naviMap.get("diceGame").setEnabled(true);
			naviMap.get("loadGame").setEnabled(true);
			naviMap.get("newGame").setEnabled(true);
			naviMap.get("saveGame").setEnabled(true);
			naviMap.get("sendGame").setEnabled(true);
		}
		if (e.getSource() == naviMap.get("diceGame")) {
			HashMap<String, JButton> tempMap = null;
			buf = (JButton) e.getSource();
			int number = myGame.rollTheDice();
			labelMap.get("dice").setIcon(imagesMap.get("Dice" + number));
			if (myGame.getZugInfo() != null) {
				
				if (myGame.ausgabeSpielerAmZug() == true) {
					String[] zugFelder=null;
					zugFelder= myGame.zugDurchfuehrenKI();
					// Hier folgt Umgang mit zugFeldern -> setzen der Figuren-> Have fun, Kevster ;) 
					for(String a:zugFelder){
						System.out.println("REINGEKOMMENE FELDNAMEN: "+a);
					}
					JButton home = null;
					JButton target = null;
					JButton targetKilled = null;
					if(zugFelder[0].matches("S.*")==true)
						home = startFieldsMap.get(zugFelder[0]);
					else if(zugFelder[0].matches("E.*")==true)
						home = endFieldsMap.get(zugFelder[0]);
					else
						home = stdFieldsMap.get(zugFelder[0]);
					if(zugFelder[1].matches("S.*")==true)
						target = startFieldsMap.get(zugFelder[1]);
					else if(zugFelder[1].matches("E.*")==true)
						target = endFieldsMap.get(zugFelder[1]);
					else
						target = stdFieldsMap.get(zugFelder[1]);
					if(zugFelder[2]!=null){
						targetKilled=startFieldsMap.get(zugFelder[2]);
						Icon killed = target.getIcon();
						targetKilled.setIcon(killed);
						targetKilled.setVisible(true);
					}
					
					Icon icon = home.getIcon();
					target.setIcon(icon);
					home.setIcon(null);
					home.setVisible(false);
					target.setVisible(true);
					
					
				}
				else{
				
				
				for (String button : myGame.getZugInfo()) {
					
						if (button.matches("S.*") == true) {
							buf = startFieldsMap.get(button);
							buf.setEnabled(true);
							buf.addActionListener(this);
							buf.setVisible(true);
							button = button.substring(0, 2);
							actionMap.put(button, buf);
						} else if (button.matches("E.*") == true) {
							buf = endFieldsMap.get(button);
							buf.setEnabled(true);
							buf.addActionListener(this);
							buf.setVisible(true);
							button = button.substring(0, 2);
							actionMap.put(button, buf);
						} else {
							buf = stdFieldsMap.get(button);
							buf.setEnabled(true);
							buf.addActionListener(this);
							buf.setVisible(true);
							actionMap.put(button, buf);
						}
						button = null;
					}
				}
			}
		}
		if (actionMap != null && actionMap.size() != 0
				&& actionMap.containsValue(e.getSource())) {
			buf = (JButton) e.getSource();
			String button = buf.getToolTipText();
			if (button.matches("S.*") == true) {
				button = button.substring(0, 2);
			} else if (button.matches("E.*") == true) {
				button = button.substring(0, 2);
			}			
			
			String[] zugFelder = myGame.zugDurchfuehren(button);
			String zID = zugFelder[1];
			JButton target = null;
			JButton targetKilled = null;
			if (zID.matches("S.*") == true) {
				target = startFieldsMap.get(zID);
				
			} else if (zID.matches("E.*") == true) {
				target = endFieldsMap.get(zID);
			} else {
				target = stdFieldsMap.get(zID);
			}
			if(zugFelder[2]!=null){
				targetKilled=startFieldsMap.get(zugFelder[2]);
				Icon killed = target.getIcon();
				targetKilled.setIcon(killed);
				targetKilled.setVisible(true);
			}
			buf.setVisible(false);
			Icon icon = buf.getIcon();
			buf.setIcon(null);
			target.setVisible(true);
			target.setIcon(icon);
			actionMap.clear();
			button = null;
			target = null;
			buf = null;
			zID = null;
		}

		if (e.getSource() == naviMap.get("startGame")) {
			buf = (JButton) e.getSource();
			buf.setEnabled(false);
			naviMap.get("loadGame").setEnabled(false);
			naviMap.get("saveGame").setEnabled(false);
			naviMap.get("sendGame").setEnabled(false);
			tempASF = new AnzahlSpielerFenster(this);
		}
		if (e.getSource() == naviMap.get("newGame")) {
			buf = (JButton) e.getSource();
			buf.setEnabled(false);
			naviMap.get("startGame").setEnabled(true);
			naviMap.get("diceGame").setEnabled(false);
			naviMap.get("saveGame").setEnabled(false);
			myGame.neuesSpielErstellen();
			System.out.println("Neues Spiel wurde erstellt!");
			vorhandeneFarben.clear();
			vorhandeneFarben.add(FarbEnum.ROT);
			vorhandeneFarben.add(FarbEnum.BLAU);
			vorhandeneFarben.add(FarbEnum.GRUEN);
			vorhandeneFarben.add(FarbEnum.GELB);
			myGUI.restartGame();
		}


		if (e.getSource() == naviMap.get("saveGame")) {

			Object[] optionen = { "CSV", "Serialisiert" };
			Object typ = JOptionPane.showOptionDialog(frame,
					"Serialisiert oder CSV?", "Wie magstn Speichern dude?",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, optionen, optionen[1]);

			if (typ.equals(0)) {
				File datei = null;

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CSV-Dateien", "csv");
				fileGrabber = new JFileChooser();
				fileGrabber.setFileFilter(filter);

				int returnVal = fileGrabber.showOpenDialog(this.frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					datei = fileGrabber.getSelectedFile();

					String lol = datei.getPath();
					saveCsv.spielSpeichern(myGame, saveCsv.openFile(lol, 2));
				} else {
					System.out.println("Speichern abgebrochen");
				}

			} else if (typ.equals(1)) {
				File datei = null;

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Serialisierte Objektdateien", "ser");
				fileGrabber = new JFileChooser();
				fileGrabber.setFileFilter(filter);

				int returnVal = fileGrabber.showOpenDialog(this.frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					datei = fileGrabber.getSelectedFile();
					String lol = datei.getPath();
					saveSer.spielSpeichern(myGame, saveSer.openFile(lol, 2));

				} else {
					System.out.println("Speichern abgebrochen");
				}

			}

		}

		if (e.getSource() == naviMap.get("loadGame")) {

			Object[] optionen = { "CSV", "Serialisiert" };
			Object typ = JOptionPane.showOptionDialog(frame,
					"Serialisiert oder CSV?", "Wie magstn Laden dude?",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, optionen, optionen[1]);

			if (typ.equals(0)) {
				File datei = null;

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"CSV-Dateien", "csv");
				fileGrabber = new JFileChooser();
				fileGrabber.setFileFilter(filter);

				int returnVal = fileGrabber.showOpenDialog(this.frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					datei = fileGrabber.getSelectedFile();

					String lol = datei.getPath();
					myGame = (iBediener) saveCsv.spielLaden(saveCsv.openFile(
							lol, 1));
					this.geladenAngefangen=myGame.getStatus();
				} else {
					System.out.println("Speichern abgebrochen");
				}

			} else if (typ.equals(1)) {
				File datei = null;

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Serialisierte Objektdateien", "ser");
				fileGrabber = new JFileChooser();
				fileGrabber.setFileFilter(filter);

				int returnVal = fileGrabber.showOpenDialog(this.frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					datei = fileGrabber.getSelectedFile();
					String lol = datei.getPath();
					myGame = (iBediener) saveSer.spielLaden(saveSer.openFile(
							lol, 1));
					this.geladenAngefangen=myGame.getStatus();

				} else {
					System.out.println("Speichern abgebrochen");
				}

			}
			if(myGame.getStatus()==true)
				naviMap.get("startGame").setEnabled(false);

		}
		
		if(e.getSource()==naviMap.get("sendGame")){
			Mailversand mv = null;

			Object[] optionen = { "PDF", "Serialisiert" };
			Object typ = JOptionPane.showOptionDialog(frame,
					"Serialisiert oder PDF?", "Wie magstn Laden dude?",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, optionen, optionen[1]);

			if (typ.equals(0)) {
				File datei = null;

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"PDF-Dateien", "pdf");
				fileGrabber = new JFileChooser();
				fileGrabber.setFileFilter(filter);

				int returnVal = fileGrabber.showOpenDialog(this.frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					datei = fileGrabber.getSelectedFile();
					
					String lol = datei.getPath();
					savePDF.spielSpeichern(spielbrettGUI, savePDF.openFile(lol,1337));
					mv = new Mailversand(lol);
					
				} else {
					System.out.println("Speichern abgebrochen");
				}

			} else if (typ.equals(1)) {
				File datei = null;

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Serialisierte Objektdateien", "ser");
				fileGrabber = new JFileChooser();
				fileGrabber.setFileFilter(filter);

				int returnVal = fileGrabber.showOpenDialog(this.frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					datei = fileGrabber.getSelectedFile();
					String lol = datei.getPath();
					mv = new Mailversand(lol);

				} else {
					System.out.println("Speichern abgebrochen");
				}

			}
			
			
			
			try{
			mv.run();	
			}
			
			catch(Exception ex){
				ex.printStackTrace();
			}
		}

	}
}
