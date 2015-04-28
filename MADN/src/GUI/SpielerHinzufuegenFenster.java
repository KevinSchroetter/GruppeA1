package GUI;

import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.*;

public class SpielerHinzufuegenFenster extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String,JButton> map = new HashMap<String,JButton>();
	private Eventhandler myHandler = null;
	public JTextField eingabeName= new JTextField(10);
	private String[] auswahlFarben= {"ROT", "BLAU", "GRUEN", "GELB"};
	public JComboBox <String> FarbAuswahl= new JComboBox <String>(auswahlFarben);
	public ButtonGroup art= new ButtonGroup();

	public SpielerHinzufuegenFenster(Eventhandler myHandler){;
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
		JRadioButton aki= new JRadioButton("KI aggressiv");
		JRadioButton dki= new JRadioButton("KI defensiv");
		ButtonGroup art= new ButtonGroup();
		art.add(mensch);
		art.add(aki);
		art.add(dki);
		JPanel kiAuswahl= new JPanel();
		kiAuswahl.add(mensch);
		kiAuswahl.add(aki);
		kiAuswahl.add(dki);
		this.add(kiAuswahl);
		
		
		this.add(new JLabel("Farbe:"));
		
		
		JPanel farben= new JPanel();
		farben.add(FarbAuswahl);
		this.add(farben);
		
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		
		JPanel okbutt= new JPanel();
		JButton ok= new JButton("OK");
		okbutt.add(ok);
		map.put("addSpieler", ok);
		ok.addActionListener(myHandler);
		myHandler.addStuff(map,this);
		this.add(okbutt);
		pack();
		
	}

}
