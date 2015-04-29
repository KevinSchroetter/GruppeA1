package GUI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import Einstellungen.FarbEnum;

public class SpielerHinzufuegenFenster extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String,JButton> map = new HashMap<String,JButton>();
	private HashMap<String,JRadioButton> radioButtons = new HashMap<String,JRadioButton>();
	private Eventhandler myHandler = null;
	public JTextField eingabeName= new JTextField(10);
	private String[] auswahlFarben;
	private JComboBox <String> farbAuswahl;
	private ButtonGroup art;

	public SpielerHinzufuegenFenster(Eventhandler myHandler, ArrayList<FarbEnum> vorhandeneFarben){;
		eingabeName.setSize(200, 10);
		this.setResizable(false);
		this.setLocation(400, 150);
		this.setTitle("Neuer Spieler");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setLayout(new GridLayout(6,2));
		
		this.add(new JLabel("Neuer Spieler:"));
		this.add(new JLabel(""));
		this.add(new JLabel("Name:"));
		
		JPanel ename= new JPanel();
		
		ename.add(eingabeName);
		this.add(ename);
		
		this.add(new JLabel("Mensch / KI aggressiv / KI defensiv:"));
		JRadioButton mensch= new JRadioButton("Mensch");
		JRadioButton aki= new JRadioButton("KI aggressiv",true);
		JRadioButton dki= new JRadioButton("KI defensiv");
		ButtonGroup art= new ButtonGroup();
		radioButtons.put("Mensch",mensch);
		radioButtons.put("aki", aki);
		radioButtons.put("dki",dki);
		art= new ButtonGroup();
		art.add(mensch);
		art.add(aki);
		art.add(dki);
		JPanel kiAuswahl= new JPanel();
		kiAuswahl.add(mensch);
		kiAuswahl.add(aki);
		kiAuswahl.add(dki);
		this.add(kiAuswahl);
		
		
		this.add(new JLabel("Farbe:"));
		auswahlFarben= new String[vorhandeneFarben.size()];
		for( int i=0; i<vorhandeneFarben.size(); i++){
			String temp= vorhandeneFarben.get(i).name();
			auswahlFarben[i]=temp;
		}
		farbAuswahl=new JComboBox <String>(auswahlFarben);
		
		JPanel farben= new JPanel();
		farben.add(farbAuswahl);
		this.add(farben);
		
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		
		JPanel okbutt= new JPanel();
		JButton ok= new JButton("OK");
		okbutt.add(ok);
		map.put("addSpieler", ok);
		ok.addActionListener(myHandler);
		myHandler.addStuff(map,this, radioButtons, art, farbAuswahl, eingabeName);
		this.add(okbutt);
		pack();
		
	}

}
