package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import Einstellungen.SoundEnum;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

import Spiel.ButtonsNavi;


public class GUI {

	
	private HashMap<String, JButton> naviMap = new HashMap<String, JButton>();
	private HashMap<String, ImageIcon> imagesMap = new HashMap<String, ImageIcon>();
	private HashMap<String, ImageIcon> gifMap = new HashMap<String, ImageIcon>();
	private HashMap<String, JLabel> labelMap = new HashMap<String, JLabel>();
	private HashMap<String, JButton> startMap = new HashMap<String, JButton>();
	private HashMap<String, JButton> endMap = new HashMap<String,JButton>();
	private HashMap<String, JButton> sMap = new HashMap<String, JButton>();
	private JFrame frame = null;
	private ArrayList<ArrayList<ImageIcon>> language = new ArrayList<ArrayList<ImageIcon>>();
	private Eventhandler myHandler = null;
	private Mousehandler moha= null;
	private int zeilen = 0;
	private int spalten = 0;
	private JPanel norden = new JPanel();
	private JPanel sueden = new JPanel();
	private JPanel osten = new JPanel();
	private JPanel westen = new JPanel();
	private JLayeredPane mitte = new JLayeredPane();
	private JTextArea console = new JTextArea(8,100);
	private JFileChooser dateiGrabber = new JFileChooser();

	

	public GUI(String titel, int spalten, int zeilen, int index){
		SoundEnum.init();
		SoundEnum.volume = SoundEnum.Volume.MEDIUM;
		frame = new JFrame(titel);
		BorderLayout bl = new BorderLayout();
		frame.setLayout(bl);
		initLabelMap();
		initImagesMap();
		initGifMap();
		initNaviButtonsByLanguage(index);
		initAllFieldButtons();
		
		setEventhandler();
		setMousehandler();
		
		createPanelNORTH();
		createPanelEAST();
		createPanelSOUTH();
		createPanelWEST();
		createPanelCENTER();
		//makeVisible();
		
	 //   disableAllFields();
	    frame.setBounds(400, 100, 1280, 1080);
	    frame.setVisible(true);
	    frame.setResizable(false);
	    frame.getRootPane().setBackground(Color.DARK_GRAY);
	    frame.getRootPane().setForeground(Color.gray);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    
	    
	    //console.setBackground(Color.DARK_GRAY);
	    //console.setForeground(Color.gray);
	}

	private void setEndFieldImage(String imageName) {
		for(java.util.Map.Entry<String, JButton> entry : endMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setIcon(imagesMap.get(imageName));
		}
	}
	
	public void restartGame(){
		setStartFieldPositions(90,121);
		setEndFieldPositions(90,121);
		setStandardFieldPositions(90,121);
		redoIcons();
	}
	private void redoIcons() {
		for(java.util.Map.Entry<String, JButton> entry : endMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setIcon(null);
		}
		for(java.util.Map.Entry<String, JButton> entry : sMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setIcon(null);
		}
		setStartFieldImage("Dice2");
		
	}

	private void setStartFieldImage(String imageName) {
		for(java.util.Map.Entry<String, JButton> entry : startMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setIcon(imagesMap.get(imageName));
		}
		startMap.get("S1ROT").setIcon(imagesMap.get("images/figRed1.gif"));
		startMap.get("S2ROT").setIcon(imagesMap.get("images/figRed2.gif"));
		startMap.get("S3ROT").setIcon(imagesMap.get("images/figRed3.gif"));
		startMap.get("S4ROT").setIcon(imagesMap.get("images/figRed4.gif"));
		startMap.get("S1BLAU").setIcon(imagesMap.get("images/figBlue1.gif"));
		startMap.get("S2BLAU").setIcon(imagesMap.get("images/figBlue2.gif"));
		startMap.get("S3BLAU").setIcon(imagesMap.get("images/figBlue3.gif"));
		startMap.get("S4BLAU").setIcon(imagesMap.get("images/figBlue4.gif"));
		startMap.get("S1GRUEN").setIcon(imagesMap.get("images/figGreen1.gif"));
		startMap.get("S2GRUEN").setIcon(imagesMap.get("images/figGreen2.gif"));
		startMap.get("S3GRUEN").setIcon(imagesMap.get("images/figGreen3.gif"));
		startMap.get("S4GRUEN").setIcon(imagesMap.get("images/figGreen4.gif"));
		startMap.get("S1GELB").setIcon(imagesMap.get("images/figYellow1.gif"));
		startMap.get("S2GELB").setIcon(imagesMap.get("images/figYellow2.gif"));
		startMap.get("S3GELB").setIcon(imagesMap.get("images/figYellow3.gif"));
		startMap.get("S4GELB").setIcon(imagesMap.get("images/figYellow4.gif"));
		
	}

	private void setStandardFieldImage(String imageName) {
		for(java.util.Map.Entry<String, JButton> entry : sMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setIcon(imagesMap.get(imageName));
		}
		
	}

	private void setEventhandler() {
		this.myHandler = new Eventhandler(naviMap,labelMap,imagesMap, gifMap,sMap,startMap,endMap,dateiGrabber,mitte,this);	
		myHandler.addGUI(frame);
		/*for(java.util.Map.Entry<String, JButton> entry : sMap.entrySet()) {
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
		}*/
		for(java.util.Map.Entry<String, JButton> entry : naviMap.entrySet()) {
		    JButton value = entry.getValue();
		    mitte.add(value);
		    value.addActionListener(myHandler);
		}
	}
	private void setMousehandler() {
		this.moha = new Mousehandler(naviMap);
		
		for(java.util.Map.Entry<String, JButton> entry : naviMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.addMouseListener(moha);
		}
	}
	private void addIconToImagesMap(String imageName, String path, int width, int length){
		ImageIcon icon = new ImageIcon(path);
		icon.setImage(icon.getImage().getScaledInstance(width,length,Image.SCALE_SMOOTH));
		imagesMap.put(imageName, icon);
	}
	private void addIconToGifMap(String imageName, String path, int width, int length){
		ImageIcon icon = new ImageIcon(path);
			icon.setImage(icon.getImage().getScaledInstance(width,length,Image.SCALE_FAST));
			gifMap.put(imageName, icon);
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
		addIconToImagesMap("field","images/game.png",986, 886);
		addIconToImagesMap("Dice1","images/dice_01.jpg",101,101);
		addIconToImagesMap("Dice2","images/dice_02.jpg",101,101);
		addIconToImagesMap("Dice3","images/dice_03.jpg",101,101);
		addIconToImagesMap("Dice4","images/dice_04.jpg",101,101);
		addIconToImagesMap("Dice5","images/dice_05.jpg",101,101);
		addIconToImagesMap("Dice6","images/dice_06.jpg",101,101);
		addIconToImagesMap("images/figRed1.gif","images/figRed1.png",90,121);
		addIconToImagesMap("images/figRed2.gif","images/figRed2.png",90,121);
		addIconToImagesMap("images/figRed3.gif","images/figRed3.png",90,121);
		addIconToImagesMap("images/figRed4.gif","images/figRed4.png",90,121);
		addIconToImagesMap("images/figBlue1.gif","images/figBlue1.png",90,121);
		addIconToImagesMap("images/figBlue2.gif","images/figBlue2.png",90,121);
		addIconToImagesMap("images/figBlue3.gif","images/figBlue3.png",90,121);
		addIconToImagesMap("images/figBlue4.gif","images/figBlue4.png",90,121);
		addIconToImagesMap("images/figGreen1.gif","images/figGreen1.png",90,121);
		addIconToImagesMap("images/figGreen2.gif","images/figGreen2.png",90,121);
		addIconToImagesMap("images/figGreen3.gif","images/figGreen3.png",90,121);
		addIconToImagesMap("images/figGreen4.gif","images/figGreen4.png",90,121);
		addIconToImagesMap("images/figYellow1.gif","images/figYellow1.png",90,121);
		addIconToImagesMap("images/figYellow2.gif","images/figYellow2.png",90,121);
		addIconToImagesMap("images/figYellow3.gif","images/figYellow3.png",90,121);
		addIconToImagesMap("images/figYellow4.gif","images/figYellow4.png",90,121);
	}
	
	private void initGifMap(){
		addIconToGifMap("images/figRed1.png", "images/figRed1.gif", 90, 121);
		addIconToGifMap("images/figRed2.png", "images/figRed2.gif", 90, 121);
		addIconToGifMap("images/figRed3.png", "images/figRed3.gif", 90, 121);
		addIconToGifMap("images/figRed4.png", "images/figRed4.gif", 90, 121);
		addIconToGifMap("images/figBlue1.png", "images/figBlue1.gif", 90, 121);
		addIconToGifMap("images/figBlue2.png", "images/figBlue2.gif", 90, 121);
		addIconToGifMap("images/figBlue3.png", "images/figBlue3.gif", 90, 121);
		addIconToGifMap("images/figBlue4.png", "images/figBlue4.gif", 90, 121);
		addIconToGifMap("images/figGreen1.png", "images/figGreen1.gif", 90, 121);
		addIconToGifMap("images/figGreen2.png", "images/figGreen2.gif", 90, 121);
		addIconToGifMap("images/figGreen3.png", "images/figGreen3.gif", 90, 121);
		addIconToGifMap("images/figGreen4.png", "images/figGreen4.gif", 90, 121);
		addIconToGifMap("images/figYellow1.png", "images/figYellow1.gif", 90, 121);
		addIconToGifMap("images/figYellow2.png", "images/figYellow2.gif", 90, 121);
		addIconToGifMap("images/figYellow3.png", "images/figYellow3.gif", 90, 121);
		addIconToGifMap("images/figYellow4.png", "images/figYellow4.gif", 90, 121);
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
		ArrayList<ImageIcon> DE= new ArrayList<ImageIcon>();
		DE.add(new ImageIcon("images/rollDiceNormal.png"));
		DE.add(new ImageIcon("images/StartGameNormal.png"));
		DE.add(new ImageIcon("images/EndGameNormal.png"));
		DE.add(new ImageIcon("images/NewGameNormal.png"));
		DE.add(new ImageIcon("images/SaveGameNormal.png"));
		DE.add(new ImageIcon("images/LoadGameNormal.png"));
		DE.add(new ImageIcon("images/SendGameNormal.png"));
		for( ImageIcon x:DE){
			x.setImage(x.getImage().getScaledInstance(170,70,  1));
		}

//		ArrayList<String> DE = new ArrayList<String>();
//		DE.add("Wuerfeln");
//		DE.add("Spiel Starten");
//		DE.add("Spiel Beenden");
//		DE.add("Neues Spiel");
//		DE.add("Spiel Speichern");
//		DE.add("Spiel Laden");
//		DE.add("Speicherstand versenden");
		
		language.add(DE);
		
		ArrayList<ImageIcon> EN = new ArrayList<ImageIcon>();
		EN.add(new ImageIcon("images/dice.png"));
		EN.add(new ImageIcon("images/startgame.png"));
		EN.add(new ImageIcon("images/endgame.png"));
		EN.add(new ImageIcon("images/newgame.png"));
		EN.add(new ImageIcon("images/savegame.png"));
		EN.add(new ImageIcon("images/loadgame.png"));
		EN.add(new ImageIcon("images/sendgame.png"));
		for( ImageIcon x:EN){
			x.setImage(x.getImage().getScaledInstance(170,70,  1));
		}
		language.add(EN);
		
		if(index <0 || index>ButtonsNavi.values().length)
			System.out.println("Falsche Sprache gewählt");
		else{
			JButton diceGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("roll").ordinal()));
			naviMap.put("diceGame", diceGame);
			Dimension d= new Dimension(language.get(0).get(ButtonsNavi.valueOf("roll").ordinal()).getIconWidth(), language.get(0).get(1).getIconHeight());
			diceGame.setPreferredSize(d);
			diceGame.setEnabled(false);;
			JButton startGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("start").ordinal()));
			startGame.setPreferredSize(d);
			naviMap.put("startGame", startGame);
			JButton endGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("end").ordinal()));
			endGame.setPreferredSize(d);
			endGame.setSize(130, 80);
			naviMap.put("endGame", endGame);
			JButton newGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("newGame").ordinal()));
			newGame.setPreferredSize(d);
			naviMap.put("newGame", newGame);
			newGame.setEnabled(false);
			
			JButton saveGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("save").ordinal()));
			saveGame.setPreferredSize(d);
			naviMap.put("saveGame", saveGame);
			saveGame.setEnabled(false);

			JButton loadGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("load").ordinal()));
			loadGame.setPreferredSize(d);
			naviMap.put("loadGame", loadGame);

			JButton sendGame = new JButton(language.get(index).get(ButtonsNavi.valueOf("send").ordinal()));	
			sendGame.setPreferredSize(d);
			naviMap.put("sendGame", sendGame);
		
			
			
		}
		
	}
	private void initStartFieldButtons(){
		JButton s1Rot = new JButton("S1ROT");startMap.put("S1ROT", s1Rot);
		JButton s2Rot = new JButton("S2ROT");startMap.put("S2ROT", s2Rot);
		JButton s3Rot = new JButton("S3ROT");startMap.put("S3ROT", s3Rot);
		JButton s4Rot = new JButton("S4ROT");startMap.put("S4ROT", s4Rot);
		JButton s1Blau = new JButton("S1BLAU");startMap.put("S1BLAU", s1Blau);
		JButton s2Blau = new JButton("S2BLAU");startMap.put("S2BLAU", s2Blau);
		JButton s3Blau = new JButton("S3BLAU");startMap.put("S3BLAU", s3Blau);
		JButton s4Blau = new JButton("S4BLAU");startMap.put("S4BLAU", s4Blau);
		JButton s1Gruen = new JButton("S1GRUEN");startMap.put("S1GRUEN", s1Gruen);
		JButton s2Gruen = new JButton("S2GRUEN");startMap.put("S2GRUEN", s2Gruen);
		JButton s3Gruen = new JButton("S3GRUEN");startMap.put("S3GRUEN", s3Gruen);
		JButton s4Gruen = new JButton("S4GRUEN");startMap.put("S4GRUEN", s4Gruen);
		JButton s1Gelb = new JButton("S1GELB");startMap.put("S1GELB", s1Gelb);
		JButton s2Gelb = new JButton("S2GELB");startMap.put("S2GELB", s2Gelb);
		JButton s3Gelb = new JButton("S3GELB");startMap.put("S3GELB", s3Gelb);
		JButton s4Gelb = new JButton("S4GELB");startMap.put("S4GELB", s4Gelb);
	}
	private void initEndFieldButtons() {
		JButton e1Rot = new JButton("E1ROT");endMap.put("E1ROT", e1Rot);
		JButton e2Rot = new JButton("E2ROT");endMap.put("E2ROT", e2Rot);
		JButton e3Rot = new JButton("E3ROT");endMap.put("E3ROT", e3Rot);
		JButton e4Rot = new JButton("E4ROT");endMap.put("E4ROT", e4Rot);
		JButton e1Blau = new JButton("E1BLAU");endMap.put("E1BLAU", e1Blau);
		JButton e2Blau = new JButton("E2BLAU");endMap.put("E2BLAU", e2Blau);
		JButton e3Blau = new JButton("E3BLAU");endMap.put("E3BLAU", e3Blau);
		JButton e4Blau = new JButton("E4BLAU");endMap.put("E4BLAU", e4Blau);
		JButton e1Gruen = new JButton("E1GRUEN");endMap.put("E1GRUEN", e1Gruen);
		JButton e2Gruen = new JButton("E2GRUEN");endMap.put("E2GRUEN", e2Gruen);
		JButton e3Gruen = new JButton("E3GRUEN");endMap.put("E3GRUEN", e3Gruen);
		JButton e4Gruen = new JButton("E4GRUEN");endMap.put("E4GRUEN", e4Gruen);
		JButton e1Gelb = new JButton("E1GELB");endMap.put("E1GELB", e1Gelb);
		JButton e2Gelb = new JButton("E2GELB");endMap.put("E2GELB", e2Gelb);
		JButton e3Gelb = new JButton("E3GELB");endMap.put("E3GELB", e3Gelb);
		JButton e4Gelb = new JButton("E4GELB");endMap.put("E4GELB", e4Gelb);
	}
	private void initStandardFieldButtons() {
		JButton s1 = new JButton("1");sMap.put("1", s1);
		JButton s2 = new JButton("2");sMap.put("2", s2);
		JButton s3 = new JButton("3");sMap.put("3", s3);
		JButton s4 = new JButton("4");sMap.put("4", s4);
		JButton s5 = new JButton("5");sMap.put("5", s5);
		JButton s6 = new JButton("6");sMap.put("6", s6);
		JButton s7 = new JButton("7");sMap.put("7", s7);
		JButton s8 = new JButton("8");sMap.put("8", s8);
		JButton s9 = new JButton("9");sMap.put("9", s9);
		JButton s10 = new JButton("10");sMap.put("10", s10);
		JButton s11 = new JButton("11");sMap.put("11", s11);
		JButton s12 = new JButton("12");sMap.put("12", s12);
		JButton s13 = new JButton("13");sMap.put("13", s13);
		JButton s14 = new JButton("14");sMap.put("14", s14);
		JButton s15 = new JButton("15");sMap.put("15", s15);
		JButton s16 = new JButton("16");sMap.put("16", s16);
		JButton s17 = new JButton("17");sMap.put("17", s17);
		JButton s18 = new JButton("18");sMap.put("18", s18);
		JButton s19 = new JButton("19");sMap.put("19", s19);
		JButton s20 = new JButton("20");sMap.put("20", s20);
		JButton s21 = new JButton("21");sMap.put("21", s21);
		JButton s22 = new JButton("22");sMap.put("22", s22);
		JButton s23 = new JButton("23");sMap.put("23", s23);
		JButton s24 = new JButton("24");sMap.put("24", s24);
		JButton s25 = new JButton("25");sMap.put("25", s25);
		JButton s26 = new JButton("26");sMap.put("26", s26);
		JButton s27 = new JButton("27");sMap.put("27", s27);
		JButton s28 = new JButton("28");sMap.put("28", s28);
		JButton s29 = new JButton("29");sMap.put("29", s29);
		JButton s30 = new JButton("30");sMap.put("30", s30);
		JButton s31 = new JButton("31");sMap.put("31", s31);
		JButton s32 = new JButton("32");sMap.put("32", s32);
		JButton s33 = new JButton("33");sMap.put("33", s33);
		JButton s34 = new JButton("34");sMap.put("34", s34);
		JButton s35 = new JButton("35");sMap.put("35", s35);
		JButton s36 = new JButton("36");sMap.put("36", s36);
		JButton s37 = new JButton("37");sMap.put("37", s37);
		JButton s38 = new JButton("38");sMap.put("38", s38);
		JButton s39 = new JButton("39");sMap.put("39", s39);
		JButton s40 = new JButton("40");sMap.put("40", s40);
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
		field.setOpaque(false);
		field.setContentAreaFilled(false);
		field.setToolTipText(field.getText());
		field.setText("");
		field.setFocusPainted(false);
		field.setBorderPainted(false);
		field.setRolloverEnabled(true);
		field.setVisible(visible);
		
	}
	
	
	
	
	private void setStartFieldPositions(int width, int height) {
		//StartfelderButtonsRot
		setFieldPosition(startMap.get("S1ROT"),141,23,width,height,true);
		setFieldPosition(startMap.get("S2ROT"),66,21,width,height,true);
		setFieldPosition(startMap.get("S3ROT"),66,94,width,height,true);
		setFieldPosition(startMap.get("S4ROT"),141,93,width,height,true);
		//StartfelderButtonsBlau
		setFieldPosition(startMap.get("S1BLAU"),825,20,width,height,true);
		setFieldPosition(startMap.get("S2BLAU"),755,21,width,height,true);
		setFieldPosition(startMap.get("S3BLAU"),753,99,width,height,true);
		setFieldPosition(startMap.get("S4BLAU"),828,95,width,height,true);;
		//StartfelderButtonsGruen
		setFieldPosition(startMap.get("S1GRUEN"),827,646,width,height,true);
		setFieldPosition(startMap.get("S2GRUEN"),752,646,width,height,true);
		setFieldPosition(startMap.get("S3GRUEN"),755,723,width,height,true);
		setFieldPosition(startMap.get("S4GRUEN"),832,723,width,height,true);
		//StartfelderButtonsGelb
		setFieldPosition(startMap.get("S1GELB"),151,646,width,height,true);
		setFieldPosition(startMap.get("S2GELB"),76,646,width,height,true);
		setFieldPosition(startMap.get("S3GELB"),66,723,width,height,true);
		setFieldPosition(startMap.get("S4GELB"),144,721,width,height,true);		
	}
	
	private void setEndFieldPositions(int width, int height) {
		//EndfelderButtonsRot
		setFieldPosition(endMap.get("E1ROT"),120,373,width,height,false);
		setFieldPosition(endMap.get("E2ROT"),205,373,width,height,false);
		setFieldPosition(endMap.get("E3ROT"),285,372,width,height,false);
		setFieldPosition(endMap.get("E4ROT"),371,373,width,height,false);
		//EndfelderButtonsBlau
		setFieldPosition(endMap.get("E1BLAU"),445,68,width,height,false);
		setFieldPosition(endMap.get("E2BLAU"),445,143,width,height,false);
		setFieldPosition(endMap.get("E3BLAU"),445,217,width,height,false);
		setFieldPosition(endMap.get("E4BLAU"),445,293,width,height,false);
		//EndfelderButtonsGruen
		setFieldPosition(endMap.get("E1GRUEN"),772,373,width,height,false);
		setFieldPosition(endMap.get("E2GRUEN"),689,373,width,height,false);
		setFieldPosition(endMap.get("E3GRUEN"),604,373,width,height,false);
		setFieldPosition(endMap.get("E4GRUEN"),521,371,width,height,false);
		//EndfelderButtonsGelb
		setFieldPosition(endMap.get("E1GELB"),445,675,width,height,false);
		setFieldPosition(endMap.get("E2GELB"),445,600,width,height,false);
		setFieldPosition(endMap.get("E3GELB"),445,520,width,height,false);
		setFieldPosition(endMap.get("E4GELB"),445,445,width,height,false);		
	}
	
	private void setStandardFieldPositions(int width, int height) {
		//StandardFelder fuer Spieler ROT
		setFieldPosition(sMap.get("1"),33,290,width,height,false);  //fixed y
		setFieldPosition(sMap.get("2"),120,290,width,height,false); //fixed y
		setFieldPosition(sMap.get("3"),205,290,width,height,false); //fixed y
		setFieldPosition(sMap.get("4"),284,290,width,height,false); //fixed y
		setFieldPosition(sMap.get("5"),371,290,width,height,false); //fixed y 
		setFieldPosition(sMap.get("6"),371,213,width,height,false); //fixed y
		setFieldPosition(sMap.get("7"),371,138,width,height,false); //fixed y
		setFieldPosition(sMap.get("8"),371,63,width,height,false); //fixed y
		setFieldPosition(sMap.get("9"),371,-8,width,height,false); //fixed y
		setFieldPosition(sMap.get("10"),443,-8,width,height,false); //fixed y +35
		//StandardFelder fuer Spieler BLAU
		setFieldPosition(sMap.get("11"),521,-8,width,height,false); //fixed y
		setFieldPosition(sMap.get("12"),521,63,width,height,false);  //fixed y
		setFieldPosition(sMap.get("13"),521,138,width,height,false); //fixed y +33
		setFieldPosition(sMap.get("14"),521,213,width,height,false); //fixed y
		setFieldPosition(sMap.get("15"),521,293,width,height,false); //fixed y
		setFieldPosition(sMap.get("16"),604,293,width,height,false); //fixed y
		setFieldPosition(sMap.get("17"),689,293,width,height,false); //fixed y 
		setFieldPosition(sMap.get("18"),772,293,width,height,false); //fixed y
		setFieldPosition(sMap.get("19"),854,293,width,height,false); //fixed y
		setFieldPosition(sMap.get("20"),854,371,width,height,false); //fixed y
		//StandardFelder fuer Spieler GRUEN
		setFieldPosition(sMap.get("21"),852,447,width,height,false); //fixed y
		setFieldPosition(sMap.get("22"),772,447,width,height,false); //fixed y	
		setFieldPosition(sMap.get("23"),689,447,width,height,false); //fixed y
		setFieldPosition(sMap.get("24"),604,447,width,height,false); //fixed y +20?
		setFieldPosition(sMap.get("25"),521,447,width,height,false); //fixed y
		setFieldPosition(sMap.get("26"),521,523,width,height,false); //fixed y
		setFieldPosition(sMap.get("27"),521,598,width,height,false); //fixed y 
		setFieldPosition(sMap.get("28"),521,673,width,height,false); //fixed y 
		setFieldPosition(sMap.get("29"),521,748,width,height,false); //fixed y
		setFieldPosition(sMap.get("30"),443,748,width,height,false); //fixed y
		//StandardFelder fuer Spieler GELB
		setFieldPosition(sMap.get("31"),370,748,width,height,false); //fixed y
		setFieldPosition(sMap.get("32"),370,673,width,height,false);
		setFieldPosition(sMap.get("33"),370,598,width,height,false);
		setFieldPosition(sMap.get("34"),370,523,width,height,false);
		setFieldPosition(sMap.get("35"),370,447,width,height,false); //fixed y
		setFieldPosition(sMap.get("36"),284,447,width,height,false); //fixed y
		setFieldPosition(sMap.get("37"),199,445,width,height,false); //fixed y
		setFieldPosition(sMap.get("38"),117,445,width,height,false); //fixed y
		setFieldPosition(sMap.get("39"),35,447,width,height,false); //fixed y
		setFieldPosition(sMap.get("40"),35,370,width,height,false); //fixed y
	}
	
	private void createPanelNORTH() {
		norden.add(labelMap.get("header"));
		labelMap.get("header").setForeground(Color.gray);
		norden.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(norden,BorderLayout.NORTH);
	}
	private void createPanelEAST() {
		GridLayout gl = new GridLayout(ButtonsNavi.values().length,1);
		osten.setLayout(gl);
		JPanel dicePanel= new JPanel();
		dicePanel.add(naviMap.get("diceGame"));
		dicePanel.setBackground(Color.DARK_GRAY);
		osten.add(dicePanel);
		JPanel startPanel= new JPanel();
		startPanel.add(naviMap.get("startGame"));
		startPanel.setBackground(Color.DARK_GRAY);
		osten.add(startPanel);
		JPanel newPanel= new JPanel();
		newPanel.add(naviMap.get("newGame"));
		newPanel.setBackground(Color.DARK_GRAY);
		osten.add(newPanel);
		JPanel savePanel=new JPanel();
		savePanel.add(naviMap.get("saveGame"));
		savePanel.setBackground(Color.DARK_GRAY);
		osten.add(savePanel);
		JPanel loadPanel= new JPanel();
		loadPanel.add(naviMap.get("loadGame"));
		loadPanel.setBackground(Color.DARK_GRAY);
		osten.add(loadPanel);
		JPanel sendPanel= new JPanel();
		sendPanel.add(naviMap.get("sendGame"));
		sendPanel.setBackground(Color.DARK_GRAY);
		osten.add(sendPanel);
		JPanel endPanel=new JPanel();
		endPanel.add(naviMap.get("endGame"));
		endPanel.setBackground(Color.DARK_GRAY);
		osten.add(endPanel);
		osten.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(osten,BorderLayout.EAST);
	}
	private void createPanelSOUTH() {
		sueden.add(labelMap.get("consoleHeader"));
		sueden.add(console);
		labelMap.get("consoleHeader").setForeground(Color.GRAY);
		sueden.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(sueden, BorderLayout.SOUTH);
		console.setBackground(Color.DARK_GRAY);
		console.setForeground(Color.gray);
		console.add(new JScrollBar());
		sueden.add(new JScrollPane(console));
		System.out.println("Test standard");
		//Stromumleiten
		System.setOut(new PrintWrapper(new Stromwrapper(this.console)));
	
		this.console.setVisible(true);
		System.out.println("Test GUI");
		

	}
	private void createPanelWEST() {
		setDiceImage("Dice6");
		westen.add(labelMap.get("dice"));
		labelMap.get("dice").setForeground(Color.gray);
		westen.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(westen, BorderLayout.WEST);
	}
	private void createPanelCENTER() {
		//mitte.setLayout(null);
		this.mitte = new JLayeredPane();
		setStandardFieldPositions(90,121);
		setStartFieldPositions(90,121);
		setEndFieldPositions(90,121);
		setFieldBackgroundImage("field");
		setStandardFieldImage("images/figBlue1.gif");
		setStartFieldImage("Dice2");
		setEndFieldImage("images/figRed1.gif");
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
		labelMap.get("game").setBounds(0,0,986, 886);
		//this.pane = new JLayeredPane();
		//mitte.add(pane);
		mitte.setPreferredSize(new Dimension(986,886));
		mitte.add(startMap.get("S1ROT"), new Integer(2));
		mitte.add(startMap.get("S2ROT"), new Integer(2));
		mitte.add(startMap.get("S3ROT"), new Integer(3));
		mitte.add(startMap.get("S4ROT"), new Integer(3));
		mitte.add(startMap.get("S1BLAU"), new Integer(2));
		mitte.add(startMap.get("S2BLAU"), new Integer(2));
		mitte.add(startMap.get("S3BLAU"), new Integer(3));
		mitte.add(startMap.get("S4BLAU"), new Integer(3));
		mitte.add(startMap.get("S1GRUEN"), new Integer(10));
		mitte.add(startMap.get("S2GRUEN"), new Integer(10));
		mitte.add(startMap.get("S3GRUEN"), new Integer(11));
		mitte.add(startMap.get("S4GRUEN"), new Integer(11));
		mitte.add(startMap.get("S1GELB"), new Integer(10));
		mitte.add(startMap.get("S2GELB"), new Integer(10));
		mitte.add(startMap.get("S3GELB"), new Integer(11));
		mitte.add(startMap.get("S4GELB"), new Integer(11));
		mitte.add(endMap.get("E4GELB"), new Integer(7));
		mitte.add(endMap.get("E3GELB"), new Integer(8));
		mitte.add(endMap.get("E2GELB"), new Integer(9));
		mitte.add(endMap.get("E1GELB"), new Integer(10));
		mitte.add(sMap.get("1"), new Integer(5));
		mitte.add(sMap.get("2"), new Integer(5));
		mitte.add(sMap.get("3"), new Integer(5));
		mitte.add(sMap.get("4"), new Integer(5));
		mitte.add(sMap.get("9"), new Integer(1));
		mitte.add(sMap.get("8"), new Integer(2));
		mitte.add(sMap.get("7"), new Integer(3));
		mitte.add(sMap.get("6"), new Integer(5));
		mitte.add(sMap.get("5"), new Integer(5));
		mitte.add(endMap.get("E1ROT"), new Integer(6));
		mitte.add(endMap.get("E2ROT"), new Integer(6));
		mitte.add(endMap.get("E3ROT"), new Integer(6));
		mitte.add(endMap.get("E4ROT"), new Integer(6));
		mitte.add(sMap.get("10"), new Integer(1));
		mitte.add(sMap.get("11"), new Integer(1));
		mitte.add(endMap.get("E1BLAU"), new Integer(2));
		mitte.add(endMap.get("E2BLAU"), new Integer(3));
		mitte.add(endMap.get("E3BLAU"), new Integer(4));
		mitte.add(endMap.get("E4BLAU"), new Integer(5));
		mitte.add(sMap.get("12"), new Integer(3));
		mitte.add(sMap.get("13"), new Integer(4));
		mitte.add(sMap.get("14"), new Integer(5));
		mitte.add(sMap.get("15"), new Integer(5));
		mitte.add(sMap.get("16"), new Integer(5));
		mitte.add(sMap.get("17"), new Integer(5));
		mitte.add(sMap.get("18"), new Integer(5));
		mitte.add(sMap.get("19"), new Integer(5));
		mitte.add(endMap.get("E1GRUEN"), new Integer(6));
		mitte.add(endMap.get("E2GRUEN"), new Integer(6));
		mitte.add(endMap.get("E3GRUEN"), new Integer(6));
		mitte.add(endMap.get("E4GRUEN"), new Integer(6));
		mitte.add(sMap.get("20"), new Integer(6));
		mitte.add(sMap.get("21"), new Integer(7));
		mitte.add(sMap.get("22"), new Integer(7));
		mitte.add(sMap.get("23"), new Integer(7));
		mitte.add(sMap.get("24"), new Integer(7));
		mitte.add(sMap.get("25"), new Integer(7));
		mitte.add(sMap.get("26"), new Integer(8));
		mitte.add(sMap.get("27"), new Integer(9));
		mitte.add(sMap.get("28"), new Integer(10));
		mitte.add(sMap.get("29"), new Integer(11));
		mitte.add(sMap.get("30"), new Integer(11));	
		mitte.add(sMap.get("34"), new Integer(8));		
		mitte.add(sMap.get("33"), new Integer(9));
		mitte.add(sMap.get("32"), new Integer(10));
		mitte.add(sMap.get("31"), new Integer(11));
		mitte.add(sMap.get("35"), new Integer(7));
		mitte.add(sMap.get("36"), new Integer(7));
		mitte.add(sMap.get("37"), new Integer(7));
		mitte.add(sMap.get("38"), new Integer(7));
		mitte.add(sMap.get("39"), new Integer(7));
		mitte.add(sMap.get("40"), new Integer(6));
		frame.getContentPane().add(mitte, BorderLayout.CENTER);
		
	}
	
	public void makeVisible(){
		 for(java.util.Map.Entry<String, JButton> entry : startMap.entrySet()) {
		  JButton value = entry.getValue();
		  value.setVisible(true);
		  
		 }
		 for(java.util.Map.Entry<String, JButton> entry : endMap.entrySet()) {
		  JButton value = entry.getValue();
		  value.setVisible(true);
		  
		 }
		 for(java.util.Map.Entry<String, JButton> entry : sMap.entrySet()) {
		  JButton value = entry.getValue();
		  value.setVisible(true);
		  
		 }
		}
	private void disableStartFields(){
		for(java.util.Map.Entry<String, JButton> entry : startMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setEnabled(false);
		}
	}
	private void disableEndFields(){
		for(java.util.Map.Entry<String, JButton> entry : endMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setEnabled(false);
		}
	}
	private void disableStandardFields(){
		for(java.util.Map.Entry<String, JButton> entry : sMap.entrySet()) {
		    JButton value = entry.getValue();
		    value.setEnabled(false);
		}
	}
	
	
	private void disableAllFields(){
		disableStartFields();
		disableEndFields();
		disableStandardFields();
	}

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return this.frame;
	}
}

