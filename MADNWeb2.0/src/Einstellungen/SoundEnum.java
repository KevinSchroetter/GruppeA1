package Einstellungen;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum SoundEnum{
	   FIRSTBLOOD("sounds/firstblood.wav"),   
	   KILL("sounds/girldie.wav"),         
	   START("sounds/connected.wav"),  
	   ERROR("sounds/error.wav"),
	   BATT("sounds/batt.wav"),
	   ALFRED("sounds/alfred.wav"),
	   GAMEOVER("sounds/gameover.wav");
	   
	   // Innere Klasse für Lautstärke
	   public static enum Volume {
	      MUTE, LOW, MEDIUM, HIGH
	   }
	   
	   public static Volume volume = Volume.LOW;
	   
	   
	   private Clip clip;
	   
	   // Konstruktor, weist jedem Enum-Element seinen eigenen Dateipfad zu
	   SoundEnum(String soundFileName) {
	      try {
	    	  File f = new File(soundFileName);

	        
	         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);

	         clip = AudioSystem.getClip();

	         clip.open(audioInputStream);
	      } catch (UnsupportedAudioFileException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
	   }
	   

	   public void play() {
	      if (volume != Volume.MUTE) {
	         if (clip.isRunning())
	            clip.stop();   // Stop
	         clip.setFramePosition(0);
	         clip.start();     // Start
	      }
	   }
	   
	   //Pre-Load
	   public static void init() {
	      values(); 
	   }

	}