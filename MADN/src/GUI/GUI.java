package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

import Spiel.ButtonsNavi;


public class GUI implements ActionListener{
	HashMap<String, JButton> map = new HashMap<String, JButton>();
	ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
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
	JLabel game = new JLabel();
	JTextArea console = new JTextArea(10,100);
	//StandardfeldButtons angelegt
		JButton st1 = new JButton("Standardfeld 1");
		JButton st2 = new JButton("Standardfeld 2");
		JButton st3 = new JButton("Standardfeld 3");
		JButton st4 = new JButton("Standardfeld 4");
		JButton st5 = new JButton("Standardfeld 5");
		JButton st6 = new JButton("Standardfeld 6");
		JButton st7 = new JButton("Standardfeld 7");
		JButton st8 = new JButton("Standardfeld 8");
		JButton st9 = new JButton("Standardfeld 9");
		JButton st10 = new JButton("Standardfeld 10");
		JButton st11 = new JButton("Standardfeld 11");
		JButton st12 = new JButton("Standardfeld 12");
		JButton st13 = new JButton("Standardfeld 13");
		JButton st14 = new JButton("Standardfeld 14");
		JButton st15 = new JButton("Standardfeld 15");
		JButton st16 = new JButton("Standardfeld 16");
		JButton st17 = new JButton("Standardfeld 17");
		JButton st18 = new JButton("Standardfeld 18");
		JButton st19 = new JButton("Standardfeld 19");
		JButton st20 = new JButton("Standardfeld 20");
		JButton st21 = new JButton("Standardfeld 21");
		JButton st22 = new JButton("Standardfeld 22");
		JButton st23 = new JButton("Standardfeld 23");
		JButton st24 = new JButton("Standardfeld 24");
		JButton st25 = new JButton("Standardfeld 25");
		JButton st26 = new JButton("Standardfeld 26");
		JButton st27 = new JButton("Standardfeld 27");
		JButton st28 = new JButton("Standardfeld 28");
		JButton st29 = new JButton("Standardfeld 29");
		JButton st30 = new JButton("Standardfeld 30");
		JButton st31 = new JButton("Standardfeld 31");
		JButton st32 = new JButton("Standardfeld 32");
		JButton st33 = new JButton("Standardfeld 33");
		JButton st34 = new JButton("Standardfeld 34");
		JButton st35 = new JButton("Standardfeld 35");
		JButton st36 = new JButton("Standardfeld 36");
		JButton st37 = new JButton("Standardfeld 37");
		JButton st38 = new JButton("Standardfeld 38");
		JButton st39 = new JButton("Standardfeld 39");
		JButton st40 = new JButton("Standardfeld 40");
		//StartfeldButtons angelegt
		JButton s1Rot = new JButton("Startfeld 1 Rot");
		JButton s2Rot = new JButton("Startfeld 2 Rot");
		JButton s3Rot = new JButton("Startfeld 3 Rot");
		JButton s4Rot = new JButton("Startfeld 4 Rot");
		JButton s1Blau = new JButton("Startfeld 1 Blau");
		JButton s2Blau = new JButton("Startfeld 2 Blau");
		JButton s3Blau = new JButton("Startfeld 3 Blau");
		JButton s4Blau = new JButton("Startfeld 4 Blau");
		JButton s1Grün = new JButton("Startfeld 1 Grün");
		JButton s2Grün = new JButton("Startfeld 2 Grün");
		JButton s3Grün = new JButton("Startfeld 3 Grün");
		JButton s4Grün = new JButton("Startfeld 4 Grün");
		JButton s1Gelb = new JButton("Startfeld 1 Gelb");
		JButton s2Gelb = new JButton("Startfeld 2 Gelb");
		JButton s3Gelb = new JButton("Startfeld 3 Gelb");
		JButton s4Gelb = new JButton("Startfeld 4 Gelb");
		//EndfeldButtons angelegt
		JButton e1Rot = new JButton("Endfeld 1 Rot");
		JButton e2Rot = new JButton("Endfeld 2 Rot");
		JButton e3Rot = new JButton("Endfeld 3 Rot");
		JButton e4Rot = new JButton("Endfeld 4 Rot");
		JButton e1Blau = new JButton("Endfeld 1 Blau");
		JButton e2Blau = new JButton("Endfeld 2 Blau");
		JButton e3Blau = new JButton("Endfeld 3 Blau");
		JButton e4Blau = new JButton("Endfeld 4 Blau");
		JButton e1Grün = new JButton("Endfeld 1 Grün");
		JButton e2Grün = new JButton("Endfeld 2 Grün");
		JButton e3Grün = new JButton("Endfeld 3 Grün");
		JButton e4Grün = new JButton("Endfeld 4 Grün");
		JButton e1Gelb = new JButton("Endfeld 1 Gelb");
		JButton e2Gelb = new JButton("Endfeld 2 Gelb");
		JButton e3Gelb = new JButton("Endfeld 3 Gelb");
		JButton e4Gelb = new JButton("Endfeld 4 Gelb");

	
	


	
	public GUI(String titel, int spalten, int zeilen, int index){
		this.setNaviButtonNamesByLanguage(index);
				
		diceGame.addActionListener(new Eventhandler(map));
		
		
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
		
		
		
		
		mitte.setLayout(null);
		
		
		
		

		//Standardfeld 1
				st1.setIcon(images.get(1));
				st1.setVerticalTextPosition(SwingConstants.CENTER);
				st1.setHorizontalTextPosition(SwingConstants.CENTER);
				st1.setBounds(21,204,30,30);
				st1.addActionListener(this);
				st1.setVisible(true);
				//Standardfeld 2
				st2.setIcon(images.get(1));
				st2.setVerticalTextPosition(SwingConstants.CENTER);
				st2.setHorizontalTextPosition(SwingConstants.CENTER);
				st2.setBounds(61,204,30,30);
				st2.addActionListener(this);
				st2.setVisible(true);
				//Standardfeld 3
				st3.setIcon(images.get(1));
				st3.setVerticalTextPosition(SwingConstants.CENTER);
				st3.setHorizontalTextPosition(SwingConstants.CENTER);
				st3.setBounds(104,204,30,30);
				st3.addActionListener(this);
				st3.setVisible(true);
				//Standardfeld 4
				st4.setIcon(images.get(1));
				st4.setVerticalTextPosition(SwingConstants.CENTER);
				st4.setHorizontalTextPosition(SwingConstants.CENTER);
				st4.setBounds(148,204,30,30);
				st4.addActionListener(this);
				st4.setVisible(true);
				//Standardfeld 5
				st5.setIcon(images.get(1));
				st5.setVerticalTextPosition(SwingConstants.CENTER);
				st5.setHorizontalTextPosition(SwingConstants.CENTER);
				st5.setBounds(202,201,30,30);
				st5.addActionListener(this);
				st5.setVisible(true);
				//Standardfeld 6
				st6.setIcon(images.get(1));
				st6.setVerticalTextPosition(SwingConstants.CENTER);
				st6.setHorizontalTextPosition(SwingConstants.CENTER);
				st6.setBounds(202,155,30,30);
				st6.addActionListener(this);
				st6.setVisible(true);
				//Standardfeld 7
				st7.setIcon(images.get(1));
				st7.setVerticalTextPosition(SwingConstants.CENTER);
				st7.setHorizontalTextPosition(SwingConstants.CENTER);
				st7.setBounds(202,110,30,30);
				st7.addActionListener(this);
				st7.setVisible(true);
				//Standardfeld 8
				st8.setIcon(images.get(1));
				st8.setVerticalTextPosition(SwingConstants.CENTER);
				st8.setHorizontalTextPosition(SwingConstants.CENTER);
				st8.setBounds(202,63,30,30);
				st8.addActionListener(this);
				st8.setVisible(true);
				//Standardfeld 9
				st9.setIcon(images.get(1));
				st9.setVerticalTextPosition(SwingConstants.CENTER);
				st9.setHorizontalTextPosition(SwingConstants.CENTER);
				st9.setBounds(202,22,30,30);
				st9.addActionListener(this);
				st9.setVisible(true);
				//Standardfeld 10
				st10.setIcon(images.get(1));
				st10.setVerticalTextPosition(SwingConstants.CENTER);
				st10.setHorizontalTextPosition(SwingConstants.CENTER);
				st10.setBounds(250,22,30,30);
				st10.addActionListener(this);
				st10.setVisible(true);
				//Standardfeld 11
				st11.setIcon(images.get(1));
				st11.setVerticalTextPosition(SwingConstants.CENTER);
				st11.setHorizontalTextPosition(SwingConstants.CENTER);
				st11.setBounds(295,22,30,30);
				st11.addActionListener(this);
				st11.setVisible(true);
				//Standardfeld 12
				st12.setIcon(images.get(1));
				st12.setVerticalTextPosition(SwingConstants.CENTER);
				st12.setHorizontalTextPosition(SwingConstants.CENTER);
				st12.setBounds(295,63,30,30);
				st12.addActionListener(this);
				st12.setVisible(true);
				//Standardfeld 13
				st13.setIcon(images.get(1));
				st13.setVerticalTextPosition(SwingConstants.CENTER);
				st13.setHorizontalTextPosition(SwingConstants.CENTER);
				st13.setBounds(295,105,30,30);
				st13.addActionListener(this);
				st13.setVisible(true);
				//Standardfeld 14
				st14.setIcon(images.get(1));
				st14.setVerticalTextPosition(SwingConstants.CENTER);
				st14.setHorizontalTextPosition(SwingConstants.CENTER);
				st14.setBounds(295,150,30,30);
				st14.addActionListener(this);
				st14.setVisible(true);
				//Standardfeld 15
				st15.setIcon(images.get(1));
				st15.setVerticalTextPosition(SwingConstants.CENTER);
				st15.setHorizontalTextPosition(SwingConstants.CENTER);
				st15.setBounds(295,200,30,30);
				st15.addActionListener(this);
				st15.setVisible(true);
				//Standardfeld 16
				st16.setIcon(images.get(1));
				st16.setVerticalTextPosition(SwingConstants.CENTER);
				st16.setHorizontalTextPosition(SwingConstants.CENTER);
				st16.setBounds(337,200,30,30);
				st16.addActionListener(this);
				st16.setVisible(true);
				//Standardfeld 17
				st17.setIcon(images.get(1));
				st17.setVerticalTextPosition(SwingConstants.CENTER);
				st17.setHorizontalTextPosition(SwingConstants.CENTER);
				st17.setBounds(383,200,30,30);
				st17.addActionListener(this);
				st17.setVisible(true);
				//Standardfeld 18
				st18.setIcon(images.get(1));
				st18.setVerticalTextPosition(SwingConstants.CENTER);
				st18.setHorizontalTextPosition(SwingConstants.CENTER);
				st18.setBounds(430,200,30,30);
				st18.addActionListener(this);
				st18.setVisible(true);
				//Standardfeld 19 
				st19.setIcon(images.get(1));
				st19.setVerticalTextPosition(SwingConstants.CENTER);
				st19.setHorizontalTextPosition(SwingConstants.CENTER);
				st19.setBounds(473,200,30,30);
				st19.addActionListener(this);
				st19.setVisible(true);
				//Standardfeld 20
				st20.setIcon(images.get(1));
				st20.setVerticalTextPosition(SwingConstants.CENTER);
				st20.setHorizontalTextPosition(SwingConstants.CENTER);
				st20.setBounds(473,250,30,30);
				st20.addActionListener(this);
				st20.setVisible(true);
				//Standardfeld 21
				st21.setIcon(images.get(1));
				st21.setVerticalTextPosition(SwingConstants.CENTER);
				st21.setHorizontalTextPosition(SwingConstants.CENTER);
				st21.setBounds(473,295,30,30);
				st21.addActionListener(this);
				st21.setVisible(true);
				//Standardfeld 22
				st22.setIcon(images.get(1));
				st22.setVerticalTextPosition(SwingConstants.CENTER);
				st22.setHorizontalTextPosition(SwingConstants.CENTER);
				st22.setBounds(430,295,30,30);
				st22.addActionListener(this);
				st22.setVisible(true);
				//Standardfeld 23
				st23.setIcon(images.get(1));
				st23.setVerticalTextPosition(SwingConstants.CENTER);
				st23.setHorizontalTextPosition(SwingConstants.CENTER);
				st23.setBounds(383,295,30,30);
				st23.addActionListener(this);
				st23.setVisible(true);
				//Standardfeld 24
				st24.setIcon(images.get(1));
				st24.setVerticalTextPosition(SwingConstants.CENTER);
				st24.setHorizontalTextPosition(SwingConstants.CENTER);
				st24.setBounds(337,295,30,30);
				st24.addActionListener(this);
				st24.setVisible(true);
				//Standardfeld 25
				st25.setIcon(images.get(1));
				st25.setVerticalTextPosition(SwingConstants.CENTER);
				st25.setHorizontalTextPosition(SwingConstants.CENTER);
				st25.setBounds(296,295,30,30);
				st25.addActionListener(this);
				st25.setVisible(true);
				//Standardfeld 26
				st26.setIcon(images.get(1));
				st26.setVerticalTextPosition(SwingConstants.CENTER);
				st26.setHorizontalTextPosition(SwingConstants.CENTER);
				st26.setBounds(296,340,30,30);
				st26.addActionListener(this);
				st26.setVisible(true);
				//Standardfeld 27
				st27.setIcon(images.get(1));
				st27.setVerticalTextPosition(SwingConstants.CENTER);
				st27.setHorizontalTextPosition(SwingConstants.CENTER);
				st27.setBounds(296,383,30,30);
				st27.addActionListener(this);
				st27.setVisible(true);
				//Standardfeld 28
				st28.setIcon(images.get(1));
				st28.setVerticalTextPosition(SwingConstants.CENTER);
				st28.setHorizontalTextPosition(SwingConstants.CENTER);
				st28.setBounds(296,426,30,30);
				st28.addActionListener(this);
				st28.setVisible(true);
				//Standardfeld 29
				st29.setIcon(images.get(1));
				st29.setVerticalTextPosition(SwingConstants.CENTER);
				st29.setHorizontalTextPosition(SwingConstants.CENTER);
				st29.setBounds(296,476,30,30);
				st29.addActionListener(this);
				st29.setVisible(true);
				//Standardfeld 30
				st30.setIcon(images.get(1));
				st30.setVerticalTextPosition(SwingConstants.CENTER);
				st30.setHorizontalTextPosition(SwingConstants.CENTER);
				st30.setBounds(250,476,30,30);
				st30.addActionListener(this);
				st30.setVisible(true);
				//Standardfeld 31
				st31.setIcon(images.get(1));
				st31.setVerticalTextPosition(SwingConstants.CENTER);
				st31.setHorizontalTextPosition(SwingConstants.CENTER);
				st31.setBounds(202,475,30,30);
				st31.addActionListener(this);
				st31.setVisible(true);
				//Standardfeld 32
				st32.setIcon(images.get(1));
				st32.setVerticalTextPosition(SwingConstants.CENTER);
				st32.setHorizontalTextPosition(SwingConstants.CENTER);
				st32.setBounds(202,430,30,30);
				st32.addActionListener(this);
				st32.setVisible(true);
				//Standardfeld 33
				st33.setIcon(images.get(1));
				st33.setVerticalTextPosition(SwingConstants.CENTER);
				st33.setHorizontalTextPosition(SwingConstants.CENTER);
				st33.setBounds(202,385,30,30);
				st33.addActionListener(this);
				st33.setVisible(true);
				//Standardfeld 34
				st34.setIcon(images.get(1));
				st34.setVerticalTextPosition(SwingConstants.CENTER);
				st34.setHorizontalTextPosition(SwingConstants.CENTER);
				st34.setBounds(202,339,30,30);
				st34.addActionListener(this);
				st34.setVisible(true);
				//Standardfeld 35
				st35.setIcon(images.get(1));
				st35.setVerticalTextPosition(SwingConstants.CENTER);
				st35.setHorizontalTextPosition(SwingConstants.CENTER);
				st35.setBounds(202,295,30,30);
				st35.addActionListener(this);
				st35.setVisible(true);
				//Standardfeld 36
				st36.setIcon(images.get(1));
				st36.setVerticalTextPosition(SwingConstants.CENTER);
				st36.setHorizontalTextPosition(SwingConstants.CENTER);
				st36.setBounds(148,295,30,30);
				st36.addActionListener(this);
				st36.setVisible(true);
				//Standardfeld 37
				st37.setIcon(images.get(1));
				st37.setVerticalTextPosition(SwingConstants.CENTER);
				st37.setHorizontalTextPosition(SwingConstants.CENTER);
				st37.setBounds(104,295,30,30);
				st37.addActionListener(this);
				st37.setVisible(true);
				//Standardfeld 38
				st38.setIcon(images.get(1));
				st38.setVerticalTextPosition(SwingConstants.CENTER);
				st38.setHorizontalTextPosition(SwingConstants.CENTER);
				st38.setBounds(61,295,30,30);
				st38.addActionListener(this);
				st38.setVisible(true);
				//Standardfeld 39
				st39.setIcon(images.get(1));
				st39.setVerticalTextPosition(SwingConstants.CENTER);
				st39.setHorizontalTextPosition(SwingConstants.CENTER);
				st39.setBounds(21,295,30,30);
				st39.addActionListener(this);
				st39.setVisible(true);
				//Standardfeld 40
				st40.setIcon(images.get(1));
				st40.setVerticalTextPosition(SwingConstants.CENTER);
				st40.setHorizontalTextPosition(SwingConstants.CENTER);
				st40.setBounds(21,250,30,30);
				st40.addActionListener(this);
				st40.setVisible(true);
				
				
				//StartfelderButtons
				//StartfelderButtonsRot
				s1Rot.setIcon(images.get(1));
				s1Rot.setVerticalTextPosition(SwingConstants.CENTER);
				s1Rot.setHorizontalTextPosition(SwingConstants.CENTER);
				s1Rot.setBounds(66,22,30,30);
				s1Rot.addActionListener(this);
				s1Rot.setVisible(true);
				s2Rot.setIcon(images.get(1));
				s2Rot.setVerticalTextPosition(SwingConstants.CENTER);
				s2Rot.setHorizontalTextPosition(SwingConstants.CENTER);
				s2Rot.setBounds(19,22,30,30);
				s2Rot.addActionListener(this);
				s2Rot.setVisible(true);
				s3Rot.setIcon(images.get(1));
				s3Rot.setVerticalTextPosition(SwingConstants.CENTER);
				s3Rot.setHorizontalTextPosition(SwingConstants.CENTER);
				s3Rot.setBounds(19,66,30,30);
				s3Rot.addActionListener(this);
				s3Rot.setVisible(true);
				s4Rot.setIcon(images.get(1));
				s4Rot.setVerticalTextPosition(SwingConstants.CENTER);
				s4Rot.setHorizontalTextPosition(SwingConstants.CENTER);
				s4Rot.setBounds(66,66,30,30);
				s4Rot.addActionListener(this);
				s4Rot.setVisible(true);
				//StartfelderButtonsBlau
				s1Blau.setIcon(images.get(1));
				s1Blau.setVerticalTextPosition(SwingConstants.CENTER);
				s1Blau.setHorizontalTextPosition(SwingConstants.CENTER);
				s1Blau.setBounds(473,20,30,30);
				s1Blau.addActionListener(this);
				s1Blau.setVisible(true);
				s2Blau.setIcon(images.get(1));
				s2Blau.setVerticalTextPosition(SwingConstants.CENTER);
				s2Blau.setHorizontalTextPosition(SwingConstants.CENTER);
				s2Blau.setBounds(425,20,30,30);
				s2Blau.addActionListener(this);
				s2Blau.setVisible(true);
				s3Blau.setIcon(images.get(1));
				s3Blau.setVerticalTextPosition(SwingConstants.CENTER);
				s3Blau.setHorizontalTextPosition(SwingConstants.CENTER);
				s3Blau.setBounds(425,63,30,30);
				s3Blau.addActionListener(this);
				s3Blau.setVisible(true);
				s4Blau.setIcon(images.get(1));
				s4Blau.setVerticalTextPosition(SwingConstants.CENTER);
				s4Blau.setHorizontalTextPosition(SwingConstants.CENTER);
				s4Blau.setBounds(473,63,30,30);
				s4Blau.addActionListener(this);
				s4Blau.setVisible(true);
				//StartfelderButtonsGrün
				s1Grün.setIcon(images.get(1));
				s1Grün.setVerticalTextPosition(SwingConstants.CENTER);
				s1Grün.setHorizontalTextPosition(SwingConstants.CENTER);
				s1Grün.setBounds(473,430,30,30);
				s1Grün.addActionListener(this);
				s1Grün.setVisible(true);
				s2Grün.setIcon(images.get(1));
				s2Grün.setVerticalTextPosition(SwingConstants.CENTER);
				s2Grün.setHorizontalTextPosition(SwingConstants.CENTER);
				s2Grün.setBounds(427,431,30,30);
				s2Grün.addActionListener(this);
				s2Grün.setVisible(true);
				s3Grün.setIcon(images.get(1));
				s3Grün.setVerticalTextPosition(SwingConstants.CENTER);
				s3Grün.setHorizontalTextPosition(SwingConstants.CENTER);
				s3Grün.setBounds(427,477,30,30);
				s3Grün.addActionListener(this);
				s3Grün.setVisible(true);
				s4Grün.setIcon(images.get(1));
				s4Grün.setVerticalTextPosition(SwingConstants.CENTER);
				s4Grün.setHorizontalTextPosition(SwingConstants.CENTER);
				s4Grün.setBounds(473,479,30,30);
				s4Grün.addActionListener(this);
				s4Grün.setVisible(true);
				//StartfelderButtonsGelb
				s1Gelb.setIcon(images.get(1));
				s1Gelb.setVerticalTextPosition(SwingConstants.CENTER);
				s1Gelb.setHorizontalTextPosition(SwingConstants.CENTER);
				s1Gelb.setBounds(66,428,30,30);
				s1Gelb.addActionListener(this);
				s1Gelb.setVisible(true);
				s2Gelb.setIcon(images.get(1));
				s2Gelb.setVerticalTextPosition(SwingConstants.CENTER);
				s2Gelb.setHorizontalTextPosition(SwingConstants.CENTER);
				s2Gelb.setBounds(20,428,30,30);
				s2Gelb.addActionListener(this);
				s2Gelb.setVisible(true);
				s3Gelb.setIcon(images.get(1));
				s3Gelb.setVerticalTextPosition(SwingConstants.CENTER);
				s3Gelb.setHorizontalTextPosition(SwingConstants.CENTER);
				s3Gelb.setBounds(21,478,30,30);
				s3Gelb.addActionListener(this);
				s3Gelb.setVisible(true);
				s4Gelb.setIcon(images.get(1));
				s4Gelb.setVerticalTextPosition(SwingConstants.CENTER);
				s4Gelb.setHorizontalTextPosition(SwingConstants.CENTER);
				s4Gelb.setBounds(65,478,30,30);
				s4Gelb.addActionListener(this);
				s4Gelb.setVisible(true);
				
				//EndfelderButtons
				//EndfelderButtonsRot
				e1Rot.setIcon(images.get(1));
				e1Rot.setVerticalTextPosition(SwingConstants.CENTER);
				e1Rot.setHorizontalTextPosition(SwingConstants.CENTER);
				e1Rot.setBounds(65,250,30,30);
				e1Rot.addActionListener(this);
				e1Rot.setVisible(true);
				e2Rot.setIcon(images.get(1));
				s2Rot.setVerticalTextPosition(SwingConstants.CENTER);
				e2Rot.setHorizontalTextPosition(SwingConstants.CENTER);
				e2Rot.setBounds(110,250,30,30);
				e2Rot.addActionListener(this);
				e2Rot.setVisible(true);
				e3Rot.setIcon(images.get(1));
				e3Rot.setVerticalTextPosition(SwingConstants.CENTER);
				e3Rot.setHorizontalTextPosition(SwingConstants.CENTER);
				e3Rot.setBounds(157,250,30,30);
				e3Rot.addActionListener(this);
				e3Rot.setVisible(true);
				e4Rot.setIcon(images.get(1));
				e4Rot.setVerticalTextPosition(SwingConstants.CENTER);
				e4Rot.setHorizontalTextPosition(SwingConstants.CENTER);
				e4Rot.setBounds(200,250,30,30);
				e4Rot.addActionListener(this);
				e4Rot.setVisible(true);
				//EndfelderButtonsBlau
				e1Blau.setIcon(images.get(1));
				e1Blau.setVerticalTextPosition(SwingConstants.CENTER);
				e1Blau.setHorizontalTextPosition(SwingConstants.CENTER);
				e1Blau.setBounds(248,65,30,30);
				e1Blau.addActionListener(this);
				e1Blau.setVisible(true);
				e2Blau.setIcon(images.get(1));
				e2Blau.setVerticalTextPosition(SwingConstants.CENTER);
				e2Blau.setHorizontalTextPosition(SwingConstants.CENTER);
				e2Blau.setBounds(248,111,30,30);
				e2Blau.addActionListener(this);
				e2Blau.setVisible(true);
				e3Blau.setIcon(images.get(1));
				e3Blau.setVerticalTextPosition(SwingConstants.CENTER);
				e3Blau.setHorizontalTextPosition(SwingConstants.CENTER);
				e3Blau.setBounds(248,157,30,30);
				e3Blau.addActionListener(this);
				e3Blau.setVisible(true);
				e4Blau.setIcon(images.get(1));
				e4Blau.setVerticalTextPosition(SwingConstants.CENTER);
				e4Blau.setHorizontalTextPosition(SwingConstants.CENTER);
				e4Blau.setBounds(248,200,30,30);
				e4Blau.addActionListener(this);
				e4Blau.setVisible(true);
				//EndfelderButtonsGrün
				e1Grün.setIcon(images.get(1));
				e1Grün.setVerticalTextPosition(SwingConstants.CENTER);
				e1Grün.setHorizontalTextPosition(SwingConstants.CENTER);
				e1Grün.setBounds(430,248,30,30);
				e1Grün.addActionListener(this);
				e1Grün.setVisible(true);
				e2Grün.setIcon(images.get(1));
				e2Grün.setVerticalTextPosition(SwingConstants.CENTER);
				e2Grün.setHorizontalTextPosition(SwingConstants.CENTER);
				e2Grün.setBounds(387,248,30,30);
				e2Grün.addActionListener(this);
				e2Grün.setVisible(true);
				e3Grün.setIcon(images.get(1));
				e3Grün.setVerticalTextPosition(SwingConstants.CENTER);
				e3Grün.setHorizontalTextPosition(SwingConstants.CENTER);
				e3Grün.setBounds(340,248,30,30);
				e3Grün.addActionListener(this);
				e3Grün.setVisible(true);
				e4Grün.setIcon(images.get(1));
				e4Grün.setVerticalTextPosition(SwingConstants.CENTER);
				e4Grün.setHorizontalTextPosition(SwingConstants.CENTER);
				e4Grün.setBounds(295,248,30,30);
				e4Grün.addActionListener(this);
				e4Grün.setVisible(true);
				//EndfelderButtonsGelb
				e1Gelb.setIcon(images.get(1));
				e1Gelb.setVerticalTextPosition(SwingConstants.CENTER);
				e1Gelb.setHorizontalTextPosition(SwingConstants.CENTER);
				e1Gelb.setBounds(248,432,30,30);
				e1Gelb.addActionListener(this);
				e1Gelb.setVisible(true);
				e2Gelb.setIcon(images.get(1));
				e2Gelb.setVerticalTextPosition(SwingConstants.CENTER);
				e2Gelb.setHorizontalTextPosition(SwingConstants.CENTER);
				e2Gelb.setBounds(248,386,30,30);
				e2Gelb.addActionListener(this);
				e2Gelb.setVisible(true);
				e3Gelb.setIcon(images.get(1));
				e3Gelb.setVerticalTextPosition(SwingConstants.CENTER);
				e3Gelb.setHorizontalTextPosition(SwingConstants.CENTER);
				e3Gelb.setBounds(248,340,30,30);
				e3Gelb.addActionListener(this);
				e3Gelb.setVisible(true);
				e4Gelb.setIcon(images.get(1));
				e4Gelb.setVerticalTextPosition(SwingConstants.CENTER);
				e4Gelb.setHorizontalTextPosition(SwingConstants.CENTER);
				e4Gelb.setBounds(248,297,30,30);
				e4Gelb.addActionListener(this);
				e4Gelb.setVisible(true);
				
				
				
				mitte.add(st1);
				mitte.add(st2);
				mitte.add(st3);
				mitte.add(st4);
				mitte.add(st5);
				mitte.add(st6);
				mitte.add(st7);
				mitte.add(st8);
				mitte.add(st9);
				mitte.add(st10);
				mitte.add(st11);
				mitte.add(st12);
				mitte.add(st13);
				mitte.add(st14);
				mitte.add(st15);
				mitte.add(st16);
				mitte.add(st17);
				mitte.add(st18);
				mitte.add(st19);
				mitte.add(st20);
				mitte.add(st21);
				mitte.add(st22);
				mitte.add(st23);
				mitte.add(st24);
				mitte.add(st25);
				mitte.add(st26);
				mitte.add(st27);
				mitte.add(st28);
				mitte.add(st29);
				mitte.add(st30);
				mitte.add(st31);
				mitte.add(st32);
				mitte.add(st33);
				mitte.add(st34);
				mitte.add(st35);
				mitte.add(st36);
				mitte.add(st37);
				mitte.add(st38);
				mitte.add(st39);
				mitte.add(st40);
				mitte.add(s1Rot);
				mitte.add(s2Rot);
				mitte.add(s3Rot);
				mitte.add(s4Rot);
				mitte.add(s1Blau);
				mitte.add(s2Blau);
				mitte.add(s3Blau);
				mitte.add(s4Blau);
				mitte.add(s1Grün);
				mitte.add(s2Grün);
				mitte.add(s3Grün);
				mitte.add(s4Grün);
				mitte.add(s1Gelb);
				mitte.add(s2Gelb);
				mitte.add(s3Gelb);
				mitte.add(s4Gelb);
				mitte.add(e1Rot);
				mitte.add(e2Rot);
				mitte.add(e3Rot);
				mitte.add(e4Rot);
				mitte.add(e1Blau);
				mitte.add(e2Blau);
				mitte.add(e3Blau);
				mitte.add(e4Blau);
				mitte.add(e1Grün);
				mitte.add(e2Grün);
				mitte.add(e3Grün);
				mitte.add(e4Grün);
				mitte.add(e1Gelb);
				mitte.add(e2Gelb);
				mitte.add(e3Gelb);
				mitte.add(e4Gelb);
	    mitte.add(game);
		ImageIcon ico = new ImageIcon("images/game.jpg");
		ico.setImage(ico.getImage().getScaledInstance(534,534,Image.SCALE_DEFAULT)); 
		game.setIcon(ico);
		game.setIconTextGap(10);
		game.setBounds(0,0,534,534);

		frame.getContentPane().add(mitte,BorderLayout.CENTER);
	    frame.setSize(780, 768);
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
	}
	
	private void setNaviButtonNamesByLanguage(int index){
		ImageIcon icon = new ImageIcon("images/dice_01.jpg");
		icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
		images.add(icon);
		icon = new ImageIcon("images/dice_02.jpg");
		icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
		images.add(icon);
		icon = new ImageIcon("images/dice_03.jpg");
		icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
		images.add(icon);
		icon = new ImageIcon("images/dice_04.jpg");
		icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
		images.add(icon);
		icon = new ImageIcon("images/dice_05.jpg");
		icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
		images.add(icon);
		icon = new ImageIcon("images/dice_06.jpg");
		icon.setImage(icon.getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT));
		images.add(icon);
		dice.setIcon(images.get(5));
		dice.setVerticalTextPosition(SwingConstants.BOTTOM);
	    dice.setHorizontalTextPosition(SwingConstants.CENTER);		
		
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
			map.put("diceGame", diceGame);
			diceGame.addActionListener(new Eventhandler(map));
			map.put("startGame",startGame);
			startGame.addActionListener(new Eventhandler(map));
			map.put("endGame",endGame);
			endGame.addActionListener(new Eventhandler(map));
			map.put("newGame",newGame);
			newGame.addActionListener(new Eventhandler(map));
			map.put("loadGame",startGame);
			loadGame.addActionListener(new Eventhandler(map));
			map.put("sendGame",startGame);
			sendGame.addActionListener(new Eventhandler(map));
			
			
			
			
			
		}
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource().getClass().getName()+"GEKLICKT");
		st1.setVisible(true);
		st1.setBounds(100,100,30,30);

		
		
	}
	
		

}
