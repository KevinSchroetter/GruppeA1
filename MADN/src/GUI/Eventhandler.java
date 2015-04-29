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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private iBediener myGame = new Spiel();
	private iDatenzugriff saveSer = new DatenzugriffSerialisiert();
	private iDatenzugriff saveCsv = new DatenzugriffCSV();
	private HashMap<String, JButton> naviMap = null;
	private HashMap<String, JButton> stdFieldsMap = null;
	private HashMap<String, JButton> startFieldsMap = null;
	private HashMap<String, JButton> endFieldsMap = null;
	private HashMap<String, JLabel> labelMap = null;
	private HashMap<String, ImageIcon> imagesMap = null;
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

	public Eventhandler(HashMap<String, JButton> naviMap,
			HashMap<String, JLabel> labelMap,
			HashMap<String, ImageIcon> imagesMap,
			HashMap<String, JButton> stdFieldsMap,
			HashMap<String, JButton> startFieldsMap,
			HashMap<String, JButton> endFieldsMap, JFileChooser fileGrabber) {
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
			System.out.println("KI oder Mensch?" + " " + kIAuswahl);
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
			System.out.println("Farbe: " + tempFarbe);
			String tempName = name.getText();
			System.out.println("Eingabe im Namensfeld:" + name);
			System.out.println("Farbe: " + tempFarbe);

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
		}
		if (e.getSource() == naviMap.get("diceGame")) {
			buf = (JButton) e.getSource();
			buf.setText("Meep!");
			System.out.println("Meep?");
		}
		if (e.getSource() == naviMap.get("startGame")) {
			buf = (JButton) e.getSource();
			buf.setEnabled(false);
			naviMap.get("newGame").setEnabled(true);
			naviMap.get("diceGame").setEnabled(true);
			naviMap.get("saveGame").setEnabled(true);
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
			if (!vorhandeneFarben.contains(FarbEnum.GELB))
				vorhandeneFarben.add(FarbEnum.GELB);
			if (!vorhandeneFarben.contains(FarbEnum.GRUEN))
				vorhandeneFarben.add(FarbEnum.GRUEN);
			if (!vorhandeneFarben.contains(FarbEnum.BLAU))
				vorhandeneFarben.add(FarbEnum.BLAU);
			if (!vorhandeneFarben.contains(FarbEnum.ROT))
				vorhandeneFarben.add(FarbEnum.ROT);

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

	}
}
