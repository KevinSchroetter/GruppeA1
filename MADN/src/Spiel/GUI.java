package Spiel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class GUI implements ActionListener{
	
	JFrame frame = null;
	int zeilen = 0;
	int spalten = 0;
	JPanel norden = new JPanel();
	JPanel sueden = new JPanel();
	JPanel osten = new JPanel();
	JPanel westen = new JPanel();
	JPanel mitte = new JPanel();
	ArrayList<ArrayList<String>> language = new ArrayList<ArrayList<String>>();
	JButton diceGame = null;
	JButton startGame = null;
	JButton endGame = null;
	JButton newGame = null;
	JButton saveGame = null;
	JButton loadGame = null;
	JButton sendGame = null;
	JLabel header = new JLabel("MADN - Gore Edition");
	JLabel dice = new JLabel("DICE");
	JLabel consoleHeader = new JLabel("Console");
	JTextArea console = new JTextArea(10,100);
	
	


	
	public GUI(String titel, int spalten, int zeilen, int index){
		this.setNaviButtonNamesByLanguage(index);

		frame = new JFrame(titel);
		BorderLayout bl = new BorderLayout();
		GridLayout gl = new GridLayout(ButtonsNavi.values().length,1);
		frame.setLayout(bl);
		
		norden.add(header);
		frame.getContentPane().add(norden,BorderLayout.NORTH);
		
		frame.getContentPane().add(osten,BorderLayout.EAST);
		osten.setLayout(gl);
		osten.add(diceGame);
		osten.add(startGame);
		osten.add(endGame);
		osten.add(newGame);
		osten.add(saveGame);
		osten.add(loadGame);
		osten.add(sendGame);	
		
		sueden.add(consoleHeader);
		sueden.add(console);
		frame.getContentPane().add(sueden, BorderLayout.SOUTH);
		
		westen.add(dice);
		frame.getContentPane().add(westen, BorderLayout.WEST);
		
		


	    frame.setSize(1000, 700);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	    
	}
	
	private void setNaviButtonNamesByLanguage(int index){
		ArrayList<String> DE = new ArrayList<String>();
		DE.add("Wuerfeln");
		DE.add("Spiel Starten");
		DE.add("Spiel Beenden");
		DE.add("Neues Spiel");
		DE.add("Spiel Speichern");
		DE.add("Spiel Laden");
		DE.add("Speicherstand versenden");
		
		language.add(DE);
		
		ArrayList<String> EN = new ArrayList<String>();
		EN.add("Roll the Dice");
		EN.add("Start Game");
		EN.add("End Game");
		EN.add("New Game");
		EN.add("Save Game");
		EN.add("Load Game");
		EN.add("Send Savegame");
		
		language.add(EN);
		
		if(index <0 || index>ButtonsNavi.values().length)
			System.out.println("Falsche Sprache gewählt");
		else{
			diceGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("roll").ordinal()));
			startGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("start").ordinal()));
			endGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("end").ordinal()));
			newGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("newGame").ordinal()));
			saveGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("save").ordinal()));
			loadGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("load").ordinal()));
			sendGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("send").ordinal()));
		}
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getClass());
		
		
	}
	
		

}
