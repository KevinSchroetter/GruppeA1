package GUI;

import java.awt.GridLayout;

import javax.swing.*;

public class AnzahlSpielerFenster extends JFrame{
	public AnzahlSpielerFenster(){
		
		this.setResizable(false);
		this.setLocation(550, 200);
		this.setTitle("Anz. Spieler");
		this.setAlwaysOnTop(true);
		this.setLayout(new GridLayout(3,3));
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		JPanel frage= new JPanel();
		JPanel auswahl= new JPanel();
		JLabel anzahlSpieler= new JLabel("Wieviele Spieler sollen mitspielen?");
		frage.add(anzahlSpieler);
		this.add(frage);
		this.add(auswahl);
		String [] spielerAuswahl={"1 Spieler", "2 Spieler","3 Spieler","4 Spieler"};
		JComboBox <String> auswahlAnzahl= new JComboBox <String> (spielerAuswahl);
		
		auswahlAnzahl.setEnabled(true);
		auswahl.add(auswahlAnzahl);
		JPanel okbutton= new JPanel();
		JButton ok= new JButton("OK");
		okbutton.add(ok);
		this.add(okbutton);
		pack();
		
		
		
	}

}
