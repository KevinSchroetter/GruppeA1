package GUI;

import java.awt.GridLayout;

import javax.swing.*;

public class SpielerHinzufuegenFenster extends JFrame{
	
	public SpielerHinzufuegenFenster(){
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
		JTextField eingabeName= new JTextField(" Name des Spielers ");
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
		String[] auswahlFarben= {"ROT", "BLAU", "GRUEN", "GELB"};
		JComboBox <String> FarbAuswahl= new JComboBox <String>(auswahlFarben);
		JPanel farben= new JPanel();
		farben.add(FarbAuswahl);
		this.add(farben);
		
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		
		JPanel okbutt= new JPanel();
		JButton ok= new JButton("OK");
		okbutt.add(ok);
		this.add(okbutt);
		
		pack();
		
	}

}
