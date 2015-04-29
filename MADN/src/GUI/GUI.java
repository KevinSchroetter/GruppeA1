package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import Spiel.ButtonsNavi;


public class GUI {
	
	private HashMap<String, JButton> naviMap = new HashMap<String, JButton>();
	private HashMap<String, ImageIcon> imagesMap = new HashMap<String, ImageIcon>();
	private HashMap<String, JLabel> labelMap = new HashMap<String, JLabel>();
	private HashMap<String, JButton> startMap = new HashMap<String, JButton>();
	private HashMap<String, JButton> endMap = new HashMap<String,JButton>();
	private HashMap<String, JButton> sMap = new HashMap<String, JButton>();
	private JFrame frame = null;
	private ArrayList<ArrayList<String>> language = new ArrayList<ArrayList<String>>();
	private Eventhandler myHandler = null;
	private int zeilen = 0;
	private int spalten = 0;
	private JPanel norden = new JPanel();
	private JPanel sueden = new JPanel();
	private JPanel osten = new JPanel();
	private JPanel westen = new JPanel();
	private JPanel mitte = new JPanel();
	private JTextArea console = new JTextArea(10,100);
	private JFileChooser dateiGrabber = new JFileChooser();

	

	public GUI(String titel, int spalten, int zeilen, int index){
		frame = new JFrame(titel);
		BorderLayout bl = new BorderLayout();
		frame.setLayout(bl);
		initLabelMap();
		initImagesMap();
		initNaviButtonsByLanguage(index);
		initAllFieldButtons();
		
		setEventhandler();
		
		createPanelNORTH();
		createPanelEAST();
		createPanelSOUTH();
		createPanelWEST();
		createPanelCENTER();
		
	    frame.setSize(780, 768);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
   
	}

	private void setEndFieldImage(String imageName) {
		for(java.util.Map.Entry<String, JButton> entry : endMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setIcon(imagesMap.get(imageName));
		}
	}

	private void setStartFieldImage(String imageName) {
		for(java.util.Map.Entry<String, JButton> entry : startMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setIcon(imagesMap.get(imageName));
		}
	}

	private void setStandardFieldImage(String imageName) {
		for(java.util.Map.Entry<String, JButton> entry : sMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setIcon(imagesMap.get(imageName));
		}
		
	}

	private void setEventhandler() {
		this.myHandler = new Eventhandler(naviMap,labelMap,imagesMap,sMap,startMap,endMap,dateiGrabber);	
		myHandler.addGUI(frame);
		for(java.util.Map.Entry<String, JButton> entry : sMap.entrySet()) {
		    JButton value = entry.getValue();
		    mitte.add(value);
		    value.addActionListener(myHandler);
		}
		for(java.util.Map.Entry<String, JButton> entry : startMap.entrySet()) {
		    JButton value = entry.getValue();
		    mitte.add(value);
		    value.addActionListener(myHandler);
		}
		for(java.util.Map.Entry<String, JButton> entry : endMap.entrySet()) {
		    JButton value = entry.getValue();
		    mitte.add(value);
		    value.addActionListener(myHandler);
		}
		for(java.util.Map.Entry<String, JButton> entry : naviMap.entrySet()) {
		    JButton value = entry.getValue();
		    mitte.add(value);
		    value.addActionListener(myHandler);
		}
	}

	private void addIconToImagesMap(String imageName, String path, int width, int length){
		ImageIcon icon = new ImageIcon(path);
		icon.setImage(icon.getImage().getScaledInstance(width,length,Image.SCALE_DEFAULT));
		imagesMap.put(imageName, icon);
	}
	private void initLabelMap(){
		JLabel header = new JLabel("MADN - Gore Edition");
		labelMap.put("header",header);
		JLabel dice = new JLabel("DICE");
		labelMap.put("dice",dice);
		JLabel consoleHeader = new JLabel("Console");
		labelMap.put("consoleHeader",consoleHeader);
		JLabel game = new JLabel();
		labelMap.put("game",game);
	}
	private void initImagesMap(){
		addIconToImagesMap("field","images/game.jpg",534,534);
		addIconToImagesMap("Dice1","images/dice_01.jpg",40,40);
		addIconToImagesMap("Dice2","images/dice_02.jpg",40,40);
		addIconToImagesMap("Dice3","images/dice_03.jpg",40,40);
		addIconToImagesMap("Dice4","images/dice_04.jpg",40,40);
		addIconToImagesMap("Dice5","images/dice_05.jpg",40,40);
		addIconToImagesMap("Dice6","images/dice_06.jpg",40,40);
	}
	private void setFieldBackgroundImage(String image){ 
		if(labelMap.isEmpty()) throw new RuntimeException("labelMap leer");
		labelMap.get("game").setIcon(imagesMap.get(image));
		labelMap.get("game").setIconTextGap(10);
	}
	private void setDiceImage(String image){
		if(labelMap.isEmpty()) throw new RuntimeException("labelMap leer");
		labelMap.get("dice").setIcon(imagesMap.get(image));
		labelMap.get("dice").setVerticalTextPosition(SwingConstants.BOTTOM);
	    labelMap.get("dice").setHorizontalTextPosition(SwingConstants.CENTER);		
	}
	private void initNaviButtonsByLanguage(int index){
				
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
			JButton diceGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("roll").ordinal()));
			naviMap.put("diceGame", diceGame);
			diceGame.setEnabled(false);;
			JButton startGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("start").ordinal()));
			naviMap.put("startGame", startGame);
			JButton endGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("end").ordinal()));
			naviMap.put("endGame", endGame);
			JButton newGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("newGame").ordinal()));
			naviMap.put("newGame", newGame);
			newGame.setEnabled(false);
			JButton saveGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("save").ordinal()));
			naviMap.put("saveGame", saveGame);
			saveGame.setEnabled(false);
			JButton loadGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("load").ordinal()));
			naviMap.put("loadGame", loadGame);
			JButton sendGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("send").ordinal()));			
			naviMap.put("sendGame", sendGame);
		}
		
	}
	private void initStartFieldButtons(){
		JButton s1Rot = new JButton("Startfeld 1 Rot");startMap.put("s1Rot", s1Rot);
		JButton s2Rot = new JButton("Startfeld 2 Rot");startMap.put("s2Rot", s2Rot);
		JButton s3Rot = new JButton("Startfeld 3 Rot");startMap.put("s3Rot", s3Rot);
		JButton s4Rot = new JButton("Startfeld 4 Rot");startMap.put("s4Rot", s4Rot);
		JButton s1Blau = new JButton("Startfeld 1 Blau");startMap.put("s1Blau", s1Blau);
		JButton s2Blau = new JButton("Startfeld 2 Blau");startMap.put("s2Blau", s2Blau);
		JButton s3Blau = new JButton("Startfeld 3 Blau");startMap.put("s3Blau", s3Blau);
		JButton s4Blau = new JButton("Startfeld 4 Blau");startMap.put("s4Blau", s4Blau);
		JButton s1Gruen = new JButton("Startfeld 1 Gruen");startMap.put("s1Gruen", s1Gruen);
		JButton s2Gruen = new JButton("Startfeld 2 Gruen");startMap.put("s2Gruen", s2Gruen);
		JButton s3Gruen = new JButton("Startfeld 3 Gruen");startMap.put("s3Gruen", s3Gruen);
		JButton s4Gruen = new JButton("Startfeld 4 Gruen");startMap.put("s4Gruen", s4Gruen);
		JButton s1Gelb = new JButton("Startfeld 1 Gelb");startMap.put("s1Gelb", s1Gelb);
		JButton s2Gelb = new JButton("Startfeld 2 Gelb");startMap.put("s2Gelb", s2Gelb);
		JButton s3Gelb = new JButton("Startfeld 3 Gelb");startMap.put("s3Gelb", s3Gelb);
		JButton s4Gelb = new JButton("Startfeld 4 Gelb");startMap.put("s4Gelb", s4Gelb);
	}
	private void initEndFieldButtons() {
		JButton e1Rot = new JButton("Endfeld 1 Rot");endMap.put("e1Rot", e1Rot);
		JButton e2Rot = new JButton("Endfeld 2 Rot");endMap.put("e2Rot", e2Rot);
		JButton e3Rot = new JButton("Endfeld 3 Rot");endMap.put("e3Rot", e3Rot);
		JButton e4Rot = new JButton("Endfeld 4 Rot");endMap.put("e4Rot", e4Rot);
		JButton e1Blau = new JButton("Endfeld 1 Blau");endMap.put("e1Blau", e1Blau);
		JButton e2Blau = new JButton("Endfeld 2 Blau");endMap.put("e2Blau", e2Blau);
		JButton e3Blau = new JButton("Endfeld 3 Blau");endMap.put("e3Blau", e3Blau);
		JButton e4Blau = new JButton("Endfeld 4 Blau");endMap.put("e4Blau", e4Blau);
		JButton e1Gruen = new JButton("Startfeld 1 Gruen");endMap.put("e1Gruen", e1Gruen);
		JButton e2Gruen = new JButton("Startfeld 2 Gruen");endMap.put("e2Gruen", e2Gruen);
		JButton e3Gruen = new JButton("Startfeld 3 Gruen");endMap.put("e3Gruen", e3Gruen);
		JButton e4Gruen = new JButton("Startfeld 4 Gruen");endMap.put("e4Gruen", e4Gruen);
		JButton e1Gelb = new JButton("Startfeld 1 Gelb");endMap.put("e1Gelb", e1Gelb);
		JButton e2Gelb = new JButton("Startfeld 2 Gelb");endMap.put("e2Gelb", e2Gelb);
		JButton e3Gelb = new JButton("Startfeld 3 Gelb");endMap.put("e3Gelb", e3Gelb);
		JButton e4Gelb = new JButton("Startfeld 4 Gelb");endMap.put("e4Gelb", e4Gelb);
	}
	private void initStandardFieldButtons() {
		JButton s1 = new JButton("Standardfeld 1");sMap.put("S1", s1);
		JButton s2 = new JButton("Standardfeld 2");sMap.put("S2", s2);
		JButton s3 = new JButton("Standardfeld 3");sMap.put("S3", s3);
		JButton s4 = new JButton("Standardfeld 4");sMap.put("S4", s4);
		JButton s5 = new JButton("Standardfeld 5");sMap.put("S5", s5);
		JButton s6 = new JButton("Standardfeld 6");sMap.put("S6", s6);
		JButton s7 = new JButton("Standardfeld 7");sMap.put("S7", s7);
		JButton s8 = new JButton("Standardfeld 8");sMap.put("S8", s8);
		JButton s9 = new JButton("Standardfeld 9");sMap.put("S9", s9);
		JButton s10 = new JButton("Standardfeld 10");sMap.put("S10", s10);
		JButton s11 = new JButton("Standardfeld 11");sMap.put("S11", s11);
		JButton s12 = new JButton("Standardfeld 12");sMap.put("S12", s12);
		JButton s13 = new JButton("Standardfeld 13");sMap.put("S13", s13);
		JButton s14 = new JButton("Standardfeld 14");sMap.put("S14", s14);
		JButton s15 = new JButton("Standardfeld 15");sMap.put("S15", s15);
		JButton s16 = new JButton("Standardfeld 16");sMap.put("S16", s16);
		JButton s17 = new JButton("Standardfeld 17");sMap.put("S17", s17);
		JButton s18 = new JButton("Standardfeld 18");sMap.put("S18", s18);
		JButton s19 = new JButton("Standardfeld 19");sMap.put("S19", s19);
		JButton s20 = new JButton("Standardfeld 20");sMap.put("S20", s20);
		JButton s21 = new JButton("Standardfeld 21");sMap.put("S21", s21);
		JButton s22 = new JButton("Standardfeld 22");sMap.put("S22", s22);
		JButton s23 = new JButton("Standardfeld 23");sMap.put("S23", s23);
		JButton s24 = new JButton("Standardfeld 24");sMap.put("S24", s24);
		JButton s25 = new JButton("Standardfeld 25");sMap.put("S25", s25);
		JButton s26 = new JButton("Standardfeld 26");sMap.put("S26", s26);
		JButton s27 = new JButton("Standardfeld 27");sMap.put("S27", s27);
		JButton s28 = new JButton("Standardfeld 28");sMap.put("S28", s28);
		JButton s29 = new JButton("Standardfeld 29");sMap.put("S29", s29);
		JButton s30 = new JButton("Standardfeld 30");sMap.put("S30", s30);
		JButton s31 = new JButton("Standardfeld 31");sMap.put("S31", s31);
		JButton s32 = new JButton("Standardfeld 32");sMap.put("S32", s32);
		JButton s33 = new JButton("Standardfeld 33");sMap.put("S33", s33);
		JButton s34 = new JButton("Standardfeld 34");sMap.put("S34", s34);
		JButton s35 = new JButton("Standardfeld 35");sMap.put("S35", s35);
		JButton s36 = new JButton("Standardfeld 36");sMap.put("S36", s36);
		JButton s37 = new JButton("Standardfeld 37");sMap.put("S37", s37);
		JButton s38 = new JButton("Standardfeld 38");sMap.put("S38", s38);
		JButton s39 = new JButton("Standardfeld 39");sMap.put("S39", s39);
		JButton s40 = new JButton("Standardfeld 40");sMap.put("S40", s40);
	}
	private void initAllFieldButtons(){
		initStartFieldButtons();
		initEndFieldButtons();
		initStandardFieldButtons();
	}
	
	private void setFieldPosition(JButton field, int x, int y, int width, int height, boolean visible){
		field.setVerticalTextPosition(SwingConstants.CENTER);
		field.setHorizontalTextPosition(SwingConstants.CENTER);
		field.setBounds(x,y,width,height);
		field.setVisible(visible);
	}
	private void setStartFieldPositions() {
		//StartfelderButtonsRot
		setFieldPosition(startMap.get("s1Rot"),66,22,30,30,true);
		setFieldPosition(startMap.get("s2Rot"),19,22,30,30,true);
		setFieldPosition(startMap.get("s3Rot"),19,66,30,30,true);
		setFieldPosition(startMap.get("s4Rot"),66,66,30,30,true);
		//StartfelderButtonsBlau
		setFieldPosition(startMap.get("s1Blau"),473,20,30,30,true);
		setFieldPosition(startMap.get("s2Blau"),425,20,30,30,true);
		setFieldPosition(startMap.get("s3Blau"),425,63,30,30,true);
		setFieldPosition(startMap.get("s4Blau"),473,63,30,30,true);;
		//StartfelderButtonsGruen
		setFieldPosition(startMap.get("s1Gruen"),473,430,30,30,true);
		setFieldPosition(startMap.get("s2Gruen"),427,431,30,30,true);
		setFieldPosition(startMap.get("s3Gruen"),423,477,30,30,true);
		setFieldPosition(startMap.get("s4Gruen"),473,479,30,30,true);
		//StartfelderButtonsGelb
		setFieldPosition(startMap.get("s1Gelb"),66,428,30,30,true);
		setFieldPosition(startMap.get("s2Gelb"),20,428,30,30,true);
		setFieldPosition(startMap.get("s3Gelb"),21,478,30,30,true);
		setFieldPosition(startMap.get("s4Gelb"),65,478,30,30,true);		
	}
	
	private void setEndFieldPositions() {
		//EndfelderButtonsRot
		setFieldPosition(endMap.get("e1Rot"),65,250,30,30,false);
		setFieldPosition(endMap.get("e2Rot"),110,250,30,30,false);
		setFieldPosition(endMap.get("e3Rot"),157,250,30,30,false);
		setFieldPosition(endMap.get("e4Rot"),200,250,30,30,false);
		//EndfelderButtonsBlau
		setFieldPosition(endMap.get("e1Blau"),248,65,30,30,false);
		setFieldPosition(endMap.get("e2Blau"),248,111,30,30,false);
		setFieldPosition(endMap.get("e3Blau"),248,157,30,30,false);
		setFieldPosition(endMap.get("e4Blau"),248,200,30,30,false);
		//EndfelderButtonsGruen
		setFieldPosition(endMap.get("e1Gruen"),430,248,30,30,false);
		setFieldPosition(endMap.get("e2Gruen"),387,248,30,30,false);
		setFieldPosition(endMap.get("e3Gruen"),340,248,30,30,false);
		setFieldPosition(endMap.get("e4Gruen"),295,248,30,30,false);
		//EndfelderButtonsGelb
		setFieldPosition(endMap.get("e1Gelb"),248,432,30,30,false);
		setFieldPosition(endMap.get("e2Gelb"),248,386,30,30,false);
		setFieldPosition(endMap.get("e3Gelb"),248,340,30,30,false);
		setFieldPosition(endMap.get("e4Gelb"),248,297,30,30,false);		
	}
	
	private void setStandardFieldPositions() {
		//StandardFelder fuer Spieler ROT
		setFieldPosition(sMap.get("S1"),21,204,30,30,false);
		setFieldPosition(sMap.get("S2"),61,204,30,30,false);
		setFieldPosition(sMap.get("S3"),104,204,30,30,false);
		setFieldPosition(sMap.get("S4"),148,204,30,30,false);
		setFieldPosition(sMap.get("S5"),202,201,30,30,false);
		setFieldPosition(sMap.get("S6"),202,155,30,30,false);
		setFieldPosition(sMap.get("S7"),202,110,30,30,false);
		setFieldPosition(sMap.get("S8"),202,63,30,30,false);
		setFieldPosition(sMap.get("S9"),202,22,30,30,false);
		setFieldPosition(sMap.get("S10"),250,22,30,30,false);
		//StandardFelder fuer Spieler BLAU
		setFieldPosition(sMap.get("S11"),295,22,30,30,false);
		setFieldPosition(sMap.get("S12"),295,63,30,30,false);
		setFieldPosition(sMap.get("S13"),295,105,30,30,false);
		setFieldPosition(sMap.get("S14"),295,150,30,30,false);
		setFieldPosition(sMap.get("S15"),295,200,30,30,false);
		setFieldPosition(sMap.get("S16"),337,200,30,30,false);
		setFieldPosition(sMap.get("S17"),383,200,30,30,false);
		setFieldPosition(sMap.get("S18"),430,200,30,30,false);
		setFieldPosition(sMap.get("S19"),473,200,30,30,false);
		setFieldPosition(sMap.get("S20"),473,250,30,30,false);
		//StandardFelder fuer Spieler GRUEN
		setFieldPosition(sMap.get("S21"),473,295,30,30,false);
		setFieldPosition(sMap.get("S22"),430,295,30,30,false);
		setFieldPosition(sMap.get("S23"),383,295,30,30,false);
		setFieldPosition(sMap.get("S24"),337,295,30,30,false);
		setFieldPosition(sMap.get("S25"),296,295,30,30,false);
		setFieldPosition(sMap.get("S26"),296,340,30,30,false);
		setFieldPosition(sMap.get("S27"),296,383,30,30,false);
		setFieldPosition(sMap.get("S28"),296,426,30,30,false);
		setFieldPosition(sMap.get("S29"),296,476,30,30,false);
		setFieldPosition(sMap.get("S30"),250,476,30,30,false);
		//StandardFelder fuer Spieler GELB
		setFieldPosition(sMap.get("S31"),202,475,30,30,false);
		setFieldPosition(sMap.get("S32"),202,430,30,30,false);
		setFieldPosition(sMap.get("S33"),202,385,30,30,false);
		setFieldPosition(sMap.get("S34"),202,339,30,30,false);
		setFieldPosition(sMap.get("S35"),202,295,30,30,false);
		setFieldPosition(sMap.get("S36"),148,295,30,30,false);
		setFieldPosition(sMap.get("S37"),104,295,30,30,false);
		setFieldPosition(sMap.get("S38"),61,295,30,30,false);
		setFieldPosition(sMap.get("S39"),21,295,30,30,false);
		setFieldPosition(sMap.get("S40"),21,250,30,30,false);
	}
	
	private void createPanelNORTH() {
		norden.add(labelMap.get("header"));
		frame.getContentPane().add(norden,BorderLayout.NORTH);
	}
	private void createPanelEAST() {
		GridLayout gl = new GridLayout(ButtonsNavi.values().length,1);
		osten.setLayout(gl);
		osten.add(naviMap.get("diceGame"));
		osten.add(naviMap.get("startGame"));
		osten.add(naviMap.get("endGame"));
		osten.add(naviMap.get("newGame"));
		osten.add(naviMap.get("saveGame"));
		osten.add(naviMap.get("loadGame"));
		osten.add(naviMap.get("sendGame"));	
		frame.getContentPane().add(osten,BorderLayout.EAST);
	}
	private void createPanelSOUTH() {
		sueden.add(labelMap.get("consoleHeader"));
		sueden.add(console);
		frame.getContentPane().add(sueden, BorderLayout.SOUTH);
		System.out.println("Test standard");
		//Stromumleiten
	//	System.setOut(new PrintStream(new Stromwrapper(this.console)));
		this.console.setVisible(true);
		System.out.println("Test GUI");
		

	}
	private void createPanelWEST() {
		setDiceImage("Dice6");
		westen.add(labelMap.get("dice"));
		frame.getContentPane().add(westen, BorderLayout.WEST);
	}
	private void createPanelCENTER() {
		mitte.setLayout(null);
		setStandardFieldPositions();
		setStartFieldPositions();
		setEndFieldPositions();
		setFieldBackgroundImage("field");
		setStandardFieldImage("Dice1");
		setStartFieldImage("Dice2");
		setEndFieldImage("Dice3");
		for(java.util.Map.Entry<String, JButton> entry : sMap.entrySet()) {
		    JButton value = entry.getValue();
		    mitte.add(value);
		}
		for(java.util.Map.Entry<String, JButton> entry : startMap.entrySet()) {
		    JButton value = entry.getValue();
		    mitte.add(value);
		}
		for(java.util.Map.Entry<String, JButton> entry : endMap.entrySet()) {
		    JButton value = entry.getValue();
		    mitte.add(value);
		}
	    mitte.add(labelMap.get("game"));
		labelMap.get("game").setBounds(0,0,534,534);
		frame.getContentPane().add(mitte,BorderLayout.CENTER);
	}
}
