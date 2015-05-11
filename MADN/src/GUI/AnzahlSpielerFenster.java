package GUI;

import java.awt.Color;
import java.awt.Paint;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.*;

public class AnzahlSpielerFenster extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String,JButton> map = new HashMap<String,JButton>();
	private Eventhandler myHandler = null;
	String [] spielerAuswahl={"1 Spieler", "2 Spieler","3 Spieler","4 Spieler"};
	JComboBox <String> auswahlAnzahl= new JComboBox <String> (spielerAuswahl);
	
	public AnzahlSpielerFenster(Eventhandler myHandler){
		this.myHandler = myHandler;
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
		frage.setBackground(Color.DARK_GRAY);
		frage.setForeground(Color.GRAY);
		auswahl.setBackground(Color.DARK_GRAY);
		auswahl.setForeground(Color.GRAY);
		anzahlSpieler.setBackground(Color.DARK_GRAY);
		anzahlSpieler.setForeground(Color.GRAY);
		this.add(frage);
		this.add(auswahl);
		
		
		
		auswahlAnzahl.setEnabled(true);
		auswahl.add(auswahlAnzahl);
		auswahlAnzahl.setBackground(Color.DARK_GRAY);
		auswahlAnzahl.setForeground(Color.GRAY);
		JPanel okbutton= new JPanel();
		JButton ok= new JButton("OK");
		okbutton.setBackground(Color.DARK_GRAY);
		okbutton.setForeground(Color.GRAY);
		ok.setBackground(Color.DARK_GRAY);
		ok.setForeground(Color.GRAY);
		
		okbutton.add(ok);
		this.add(okbutton);
		map.put("OK",ok);
		ok.addActionListener(myHandler);
		myHandler.addStuff(map,this, null, null, null, null);
		this.pack();
		
		
		

		
	}


}
