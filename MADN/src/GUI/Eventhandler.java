package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Einstellungen.FarbEnum;
import Einstellungen.SoundEnum;
import Spiel.DatenzugriffCSV;
import Spiel.DatenzugriffSerialisiert;
import Spiel.Spiel;
import Spiel.iBediener;
import Spiel.iDatenzugriff;

public class Eventhandler implements ActionListener {
	private JLayeredPane spielbrettGUI = null;
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
	private HashMap<String, ImageIcon> gifMap = null;
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
			HashMap<String, ImageIcon> gifMap,
			HashMap<String, JButton> stdFieldsMap,
			HashMap<String, JButton> startFieldsMap,
			HashMap<String, JButton> endFieldsMap, JFileChooser fileGrabber,
			JLayeredPane spielbrettGUI, GUI myGui) {
		if (naviMap == null || fileGrabber == null || labelMap == null
				|| imagesMap == null || stdFieldsMap == null
				|| startFieldsMap == null || endFieldsMap == null)
			throw new IllegalArgumentException("Hashmap fehlerhaft");
		this.naviMap = naviMap;
		this.fileGrabber = fileGrabber;
		this.labelMap = labelMap;
		this.imagesMap = imagesMap;
		this.gifMap = gifMap;
		this.stdFieldsMap = stdFieldsMap;
		this.startFieldsMap = startFieldsMap;
		this.endFieldsMap = endFieldsMap;
		vorhandeneFarben = new ArrayList<FarbEnum>();
		vorhandeneFarben.add(FarbEnum.ROT);
		vorhandeneFarben.add(FarbEnum.BLAU);
		vorhandeneFarben.add(FarbEnum.GRUEN);
		vorhandeneFarben.add(FarbEnum.GELB);
		this.spielbrettGUI = spielbrettGUI;
		this.myGUI = myGui;

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

	public void restartGUI() {
		for (java.util.Map.Entry<String, JButton> entry : startFieldsMap
				.entrySet()) {
			JButton value = entry.getValue();
			value.setVisible(true);
			value.setIcon(null);
		}
		for (java.util.Map.Entry<String, JButton> entry : stdFieldsMap
				.entrySet()) {
			JButton value = entry.getValue();
			value.setVisible(false);
			value.setIcon(null);
		}
		for (java.util.Map.Entry<String, JButton> entry : endFieldsMap
				.entrySet()) {
			JButton value = entry.getValue();
			value.setVisible(false);
			value.setIcon(null);
		}
		startFieldsMap.get("S1ROT").setIcon(imagesMap.get("figRed1"));
		startFieldsMap.get("S2ROT").setIcon(imagesMap.get("figRed2"));
		startFieldsMap.get("S3ROT").setIcon(imagesMap.get("figRed3"));
		startFieldsMap.get("S4ROT").setIcon(imagesMap.get("figRed4"));

		startFieldsMap.get("S1BLAU").setIcon(imagesMap.get("figBlue1"));
		startFieldsMap.get("S2BLAU").setIcon(imagesMap.get("figBlue2"));
		startFieldsMap.get("S3BLAU").setIcon(imagesMap.get("figBlue3"));
		startFieldsMap.get("S4BLAU").setIcon(imagesMap.get("figBlue4"));

		startFieldsMap.get("S1GRUEN").setIcon(imagesMap.get("figGreen1"));
		startFieldsMap.get("S2GRUEN").setIcon(imagesMap.get("figGreen2"));
		startFieldsMap.get("S3GRUEN").setIcon(imagesMap.get("figGreen3"));
		startFieldsMap.get("S4GRUEN").setIcon(imagesMap.get("figGreen4"));

		startFieldsMap.get("S1GELB").setIcon(imagesMap.get("figYellow1"));
		startFieldsMap.get("S2GELB").setIcon(imagesMap.get("figYellow2"));
		startFieldsMap.get("S3GELB").setIcon(imagesMap.get("figYellow3"));
		startFieldsMap.get("S4GELB").setIcon(imagesMap.get("figYellow4"));

	}

	public void loadGUIStatus() {
		JButton buf = null;
		int cRed = 0;
		int cBlue = 0;
		int cGreen = 0;
		int cYellow = 0;

		for (java.util.Map.Entry<String, JButton> entry : startFieldsMap
				.entrySet()) {
			JButton value = entry.getValue();
			value.setVisible(false);
			value.setIcon(null);
		}
		for (java.util.Map.Entry<String, JButton> entry : stdFieldsMap
				.entrySet()) {
			JButton value = entry.getValue();
			value.setVisible(false);
			value.setIcon(null);
		}
		for (java.util.Map.Entry<String, JButton> entry : endFieldsMap
				.entrySet()) {
			JButton value = entry.getValue();
			value.setVisible(false);
			value.setIcon(null);
		}

		if (myGame.getStatus() == true) {
			naviMap.get("startGame").setEnabled(false);
			naviMap.get("diceGame").setEnabled(true);
			naviMap.get("newGame").setEnabled(true);
			naviMap.get("saveGame").setEnabled(true);
		}
		String zahl = null;
		String name = null;
		String field = null;
		ArrayList<String> figuren = myGame.updateGUIFigures();
		for (String fig : figuren) {
			if (fig.contains("ROT")) {
				cRed++;
			} else if (fig.contains("BLAU")) {
				cBlue++;
			} else if (fig.contains("GRUEN")) {
				cGreen++;
			} else if (fig.contains("GELB")) {
				cYellow++;
			}
			JButton target = null;
			if (fig.matches("Spieler.*"))
				System.out.println("" + fig);
			else if (fig.matches("Z.*"))
				zahl = fig.substring(1);
			else if (fig.matches("S.*") == true) {
				String[] nameSplit = fig.split("-");
				name = nameSplit[1];
				field = nameSplit[0];
				target = startFieldsMap.get(field);

			} else if (fig.matches("E.*") == true) {
				String[] nameSplit = fig.split("-");
				name = nameSplit[1];
				field = nameSplit[0];
				target = endFieldsMap.get(field);
			} else {
				String[] nameSplit = fig.split("-");
				name = nameSplit[1];
				field = nameSplit[0];
				target = stdFieldsMap.get(field);
			}
			if (target != null) {
				if (name.matches("ROT.*")) {
					name = "figRed" + name.substring(4);
				} else if (name.matches("BLAU.*")) {
					name = "figBlue" + name.substring(5);
				} else if (name.matches("GRUEN.*")) {
					name = "figGreen" + name.substring(6);
				} else if (name.matches("GELB.*")) {
					name = "figYellow" + name.substring(5);
				}
				target.setEnabled(true);
				target.setIcon(imagesMap.get(name));
				target.setVisible(true);
			}
		}
		if (cRed == 0) {
			startFieldsMap.get("S1ROT").setEnabled(true);
			startFieldsMap.get("S1ROT").setIcon(imagesMap.get("figRed1"));
			startFieldsMap.get("S1ROT").setVisible(true);
			startFieldsMap.get("S2ROT").setEnabled(true);
			startFieldsMap.get("S2ROT").setIcon(imagesMap.get("figRed2"));
			startFieldsMap.get("S2ROT").setVisible(true);
			startFieldsMap.get("S3ROT").setEnabled(true);
			startFieldsMap.get("S3ROT").setIcon(imagesMap.get("figRed3"));
			startFieldsMap.get("S3ROT").setVisible(true);
			startFieldsMap.get("S4ROT").setEnabled(true);
			startFieldsMap.get("S4ROT").setIcon(imagesMap.get("figRed4"));
			startFieldsMap.get("S4ROT").setVisible(true);
		}
		if (cBlue == 0) {
			startFieldsMap.get("S1BLAU").setEnabled(true);
			startFieldsMap.get("S1BLAU").setIcon(imagesMap.get("figBlue1"));
			startFieldsMap.get("S1BLAU").setVisible(true);
			startFieldsMap.get("S2BLAU").setEnabled(true);
			startFieldsMap.get("S2BLAU").setIcon(imagesMap.get("figBlue2"));
			startFieldsMap.get("S2BLAU").setVisible(true);
			startFieldsMap.get("S3BLAU").setEnabled(true);
			startFieldsMap.get("S3BLAU").setIcon(imagesMap.get("figBlue3"));
			startFieldsMap.get("S3BLAU").setVisible(true);
			startFieldsMap.get("S4BLAU").setEnabled(true);
			startFieldsMap.get("S4BLAU").setIcon(imagesMap.get("figBlue4"));
			startFieldsMap.get("S4BLAU").setVisible(true);
		}
		if (cGreen == 0) {
			startFieldsMap.get("S1GRUEN").setEnabled(true);
			startFieldsMap.get("S1GRUEN").setIcon(imagesMap.get("figGreen1"));
			startFieldsMap.get("S1GRUEN").setVisible(true);
			startFieldsMap.get("S2GRUEN").setEnabled(true);
			startFieldsMap.get("S2GRUEN").setIcon(imagesMap.get("figGreen2"));
			startFieldsMap.get("S2GRUEN").setVisible(true);
			startFieldsMap.get("S3GRUEN").setEnabled(true);
			startFieldsMap.get("S3GRUEN").setIcon(imagesMap.get("figGreen3"));
			startFieldsMap.get("S3GRUEN").setVisible(true);
			startFieldsMap.get("S4GRUEN").setEnabled(true);
			startFieldsMap.get("S4GRUEN").setIcon(imagesMap.get("figGreen4"));
			startFieldsMap.get("S4GRUEN").setVisible(true);
		}
		if (cYellow == 0) {
			startFieldsMap.get("S1GELB").setEnabled(true);
			startFieldsMap.get("S1GELB").setIcon(imagesMap.get("figYellow1"));
			startFieldsMap.get("S1GELB").setVisible(true);
			startFieldsMap.get("S2GELB").setEnabled(true);
			startFieldsMap.get("S2GELB").setIcon(imagesMap.get("figYellow2"));
			startFieldsMap.get("S2GELB").setVisible(true);
			startFieldsMap.get("S3GELB").setEnabled(true);
			startFieldsMap.get("S3GELB").setIcon(imagesMap.get("figYellow3"));
			startFieldsMap.get("S3GELB").setVisible(true);
			startFieldsMap.get("S4GELB").setEnabled(true);
			startFieldsMap.get("S4GELB").setIcon(imagesMap.get("figYellow4"));
			startFieldsMap.get("S4GELB").setVisible(true);
		}
		/*
		 * if(myGame.ausgabeZugFiguren()!=null)System.out.println(
		 * "Der Spieler Am Zug Exisitiert"); else
		 * System.out.println("KeineZugFigurenvorhangen");
		 * 
		 * System.out.println(myGame.updateGUIFigures().get(0));
		 * System.out.println("ZUGMOEGLICH: "+myGame.updateGUIInfo()[0]);
		 * System.out.println("KI?: "+myGame.updateGUIInfo()[1]);
		 * 
		 * System.out.println(""); System.out.println("");
		 * System.out.println("");
		 */
		if (geladenAngefangen == false)
			labelMap.get("dice").setIcon(imagesMap.get("Dice6"));
		if (myGame.augenzahl() == 0)
			labelMap.get("dice").setIcon(imagesMap.get("Dice6"));
		else
			labelMap.get("dice").setIcon(imagesMap.get("Dice" + zahl));
		if (myGame.updateGUIInfo() != null) {
			if (myGame.updateGUIInfo()[1] == false) {
				if (myGame.updateGUIInfo()[0] == true) {
					if (myGame.getZugInfo() != null) {
						for (String button : myGame.getZugInfo()) {
							if (button.matches("S.*") == true) {
								buf = startFieldsMap.get(button);
								String temp = buf.getIcon().toString().substring(7, buf.getIcon().toString().length()-4);
								buf.setEnabled(true);
								buf.addActionListener(this);
								buf.setVisible(true);								
								button = button.substring(0, 2);
								buf.setIcon(gifMap.get(temp));
								actionMap.put(button, buf);
							} else if (button.matches("E.*") == true) {
								buf = endFieldsMap.get(button);
								String temp = buf.getIcon().toString().substring(7, buf.getIcon().toString().length()-4);
								buf.setEnabled(true);
								buf.addActionListener(this);
								buf.setVisible(true);
								button = button.substring(0, 2);
								buf.setIcon(gifMap.get(temp));
								actionMap.put(button, buf);
							} else {
								buf = stdFieldsMap.get(button);
								String temp = buf.getIcon().toString().substring(7, buf.getIcon().toString().length()-4);
								buf.setEnabled(true);
								buf.addActionListener(this);
								buf.setVisible(true);
								buf.setIcon(gifMap.get(temp));
								actionMap.put(button, buf);
							}
							button = null;
						}
					}
				}
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(e instanceof ActionEvent))
			return;
		// System.out.println(e.getSource());
		JButton buf;
		if (e.getSource() == naviMap.get("endGame")) {
			guiFrame.dispose();
			if (tempASF != null)
				tempASF.dispose();
			tempASF = null;
			if (tempSHF != null)
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
			boolean erfolgreich = false;
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
			if (tempName.equals("Batt")) {
				SoundEnum.BATT.play();
				erfolgreich = myGame.neuerSpieler(tempName, farbId, kIAuswahl);
			} else if (tempName.equals("Alfred")) {
				SoundEnum.ALFRED.play();
				
				erfolgreich=false;
			} else {

				erfolgreich = myGame.neuerSpieler(tempName, farbId, kIAuswahl);}
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
					tempSHF = new SpielerHinzufuegenFenster(this,
							vorhandeneFarben);
				else {
					myGame.starteSpiel();
					labelMap.get("zugFarbe").setText("-- AM ZUG: -- " +myGame.zugFarbe()+" --");
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
			labelMap.get("zugFarbe").setText("-- AM ZUG: -- " +myGame.zugFarbe()+" --");
			labelMap.get("dice").setIcon(imagesMap.get("Dice" + number));
			if (myGame.getZugInfo() != null) {

				if (myGame.ausgabeSpielerAmZug() == true) {
					String[] zugFelder = null;
					zugFelder = myGame.zugDurchfuehrenKI();
					labelMap.get("zugFarbe").setText("-- AM ZUG: -- " +myGame.zugFarbe()+" --");

					JButton home = null;
					JButton target = null;
					JButton targetKilled = null;
					if (zugFelder[0].matches("S.*") == true)
						home = startFieldsMap.get(zugFelder[0]);
					else if (zugFelder[0].matches("E.*") == true)
						home = endFieldsMap.get(zugFelder[0]);
					else
						home = stdFieldsMap.get(zugFelder[0]);
					if (zugFelder[1].matches("S.*") == true)
						target = startFieldsMap.get(zugFelder[1]);
					else if (zugFelder[1].matches("E.*") == true)
						target = endFieldsMap.get(zugFelder[1]);
					else
						target = stdFieldsMap.get(zugFelder[1]);
					if (zugFelder[2] != null) {
						targetKilled = startFieldsMap.get(zugFelder[2]);
						Icon killed = target.getIcon();
						targetKilled.setIcon(killed);
						targetKilled.setVisible(true);
					}

					Icon icon = home.getIcon();
					target.setIcon(icon);
					home.setIcon(null);
					home.setVisible(false);
					target.setVisible(true);

				} else {

					for (String button : myGame.getZugInfo()) {
						
						
						
						if (button.matches("S.*") == true) {
							buf = startFieldsMap.get(button);
							String temp = buf.getIcon().toString().substring(7, buf.getIcon().toString().length()-4);
							buf.setEnabled(true);
							buf.addActionListener(this);
							buf.setVisible(true);
							button = button.substring(0, 2);
							buf.setIcon(gifMap.get(temp));
							actionMap.put(button, buf);
						} else if (button.matches("E.*") == true) {
							buf = endFieldsMap.get(button);
							String temp = buf.getIcon().toString().substring(7, buf.getIcon().toString().length()-4);
							buf.setEnabled(true);
							buf.addActionListener(this);
							buf.setVisible(true);
							button = button.substring(0, 2);
							buf.setIcon(gifMap.get(temp));
							actionMap.put(button, buf);
						} else {
							buf = stdFieldsMap.get(button);
							String temp = buf.getIcon().toString().substring(7, buf.getIcon().toString().length()-4);
							buf.setEnabled(true);
							buf.addActionListener(this);
							buf.setVisible(true);
							buf.setIcon(gifMap.get(temp));
							actionMap.put(button, buf);
						}
						button = null;
//						System.out.println("-------");
//						System.out.println(actionMap.size());
					}
				}
			}
			if (myGame.getBeendet() == true) {
				System.out.println("Spiel ist beendet!");
				naviMap.get("diceGame").setEnabled(false);
				return;
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
			labelMap.get("zugFarbe").setText("-- AM ZUG: -- " +myGame.zugFarbe()+" --");
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
			if (zugFelder[2] != null) {
				targetKilled = startFieldsMap.get(zugFelder[2]);
				Icon killed = target.getIcon();
				targetKilled.setIcon(killed);
				targetKilled.setVisible(true);
			}
			buf.setVisible(false);
			Icon icon = buf.getIcon();
			String temp = buf.getIcon().toString().substring(7, buf.getIcon().toString().length()-4);
			buf.setIcon(null);
			target.setVisible(true);
			target.setIcon(imagesMap.get(temp));
			String bname=buf.getToolTipText();	
			if (bname.matches("S.*") == true) {
				
				bname = bname.substring(0, 2);
				actionMap.remove(bname);
				actionMap.put(target.getToolTipText(), target);
			} else if (bname.matches("E.*") == true) {

				bname = bname.substring(0, 2);
				actionMap.remove(bname);
				actionMap.put(target.getToolTipText(), target);

			} else {
				actionMap.remove(bname);
				actionMap.put(target.getToolTipText(), target);
			}
			

			for(java.util.Map.Entry<String, JButton> entry: actionMap.entrySet()){
				JButton value = entry.getValue();
				String key = entry.getKey();
				value.setIcon(imagesMap.get(value.getIcon().toString().substring(7, value.getIcon().toString().length()-4)));
//				System.out.println(value);
			}
//			System.out.println("reach?");
			actionMap.clear();
//			System.out.println(actionMap.size());
			button = null;
			target = null;
			buf = null;
			zID = null;
			if (myGame.getBeendet() == true) {
				System.out.println("Spiel ist beendet!");
				naviMap.get("diceGame").setEnabled(false);
				return;
			}
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
			myGame = new Spiel();
			System.out.println("Neues Spiel wurde erstellt!");
			vorhandeneFarben.clear();
			vorhandeneFarben.add(FarbEnum.ROT);
			vorhandeneFarben.add(FarbEnum.BLAU);
			vorhandeneFarben.add(FarbEnum.GRUEN);
			vorhandeneFarben.add(FarbEnum.GELB);
			restartGUI();
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
					this.geladenAngefangen = myGame.getStatus();
					loadGUIStatus();
				} else {
					System.out.println("Laden abgebrochen");
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
					this.geladenAngefangen = myGame.getStatus();
					loadGUIStatus();

				} else {
					System.out.println("Laden abgebrochen");
				}

			}

		}

		if (e.getSource() == naviMap.get("sendGame")) {
			Mailversand mv = null;
			String lol = "";
			File datei = null;
			Object[] optionen = { "PDF", "Serialisiert" };
			Object typ = JOptionPane.showOptionDialog(frame,
					"Serialisiert oder PDF?", "Wie magstn Laden dude?",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, optionen, optionen[1]);

			if (typ.equals(0)) {

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"PDF-Dateien", "pdf");
				fileGrabber = new JFileChooser();
				fileGrabber.setFileFilter(filter);

				int returnVal = fileGrabber.showOpenDialog(this.frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					datei = fileGrabber.getSelectedFile();

					lol = datei.getAbsolutePath();
					if (!(lol.endsWith(".pdf"))) {
						lol += ".pdf";
					}
					savePDF.spielSpeichern(this.myGUI,
							savePDF.openFile(lol, 1337));

				} else {
					System.out.println("Speichern abgebrochen");
				}

			} else if (typ.equals(1)) {

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Serialisierte Objektdateien", "ser");
				fileGrabber = new JFileChooser();
				fileGrabber.setFileFilter(filter);

				int returnVal = fileGrabber.showOpenDialog(this.frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					datei = fileGrabber.getSelectedFile();
					lol = datei.getAbsolutePath();
					if (!(lol.endsWith(".ser"))) {
						lol += ".ser";
					}

				} else {
					System.out.println("Speichern abgebrochen");
				}

			}

			
			mv = new Mailversand(lol,myGUI);

		}

	}

}
